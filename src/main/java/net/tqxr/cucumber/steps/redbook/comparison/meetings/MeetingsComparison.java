package net.tqxr.cucumber.steps.redbook.comparison.meetings;

import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.api.DataTable;
import net.tqxr.cucumber.support.DataContainer;
import net.tqxr.cucumber.support.SpringStep;
import net.tqxr.cucumber.support.redbook.comparison.meetings.MeetingsApiDefinition;
import net.tqxr.cucumber.support.redbook.comparison.meetings.ReduceMethod;
import net.tqxr.lib.redbook.Meeting;
import net.tqxr.lib.redbook.Meetings;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class MeetingsComparison extends SpringStep<Meetings, Meetings> {

    private RestTemplate restTemplate = new RestTemplate();

    private Meetings theSourceMeetings;
    private Meetings theComparisonMeetings;

    private HashMap<String, Meetings> meetingsMap = new HashMap<>();
    private ArrayList<String> meetingsKeys = new ArrayList<>();

    public MeetingsComparison(Meetings meetings, DataContainer<Meetings> dataContainer) {
        this.dataContainer = dataContainer;
        this.testComponent = meetings;
    }

    @Override
    protected void setUpTestSteps() {
        Given("the meetings data from \"(.*)\"", this::acquireData);
        Given("the meetings data is acquired from \"(.*)\"", this::acquireDataFromServer);
        Given("^the meetings data is acquired from:$", this::acquireDataFromServerApi);
        When("^the two are compared$", this::compareData);
        When("^the data is compared$", this::compareData);
        Then("^they are equivalent$", this::dataIsEquivalent);
    }

    private void acquireDataFromServerApi(DataTable serverApiInfo) {

        serverApiInfo.asList(MeetingsApiDefinition.class).forEach((v) -> {

            System.out.println(
                    String.format("%s", v)
            );

            Meetings theMeetings = restTemplate.getForObject(v.getUrl(), Meetings.class);

            ReduceMethod theMethod = v.getMethod();
            if (theMethod == ReduceMethod.FILTER) {

                List<String> filterList = Arrays.asList(v.getLocation().split(","));

                theMeetings.meetings = theMeetings.meetings.stream()
                        .filter(
                                m ->
                                        filterList.contains(m.getLocation())
                        )
                        .collect(Collectors.toCollection(ArrayList::new));

            }

            assertThat(theMeetings)
                    .withFailMessage("Meetings object returned null")
                    .isNotNull();

            meetingsMap.put(v.getServer(), theMeetings);
            meetingsKeys.add(v.getServer());

        });

    }

    private void acquireDataFromServer(String serverName) {

        String fullUrl = String.format("https://%s//v1/info-service/racing/dates/today/meetings", serverName);
        Meetings theMeetings = restTemplate.getForObject(fullUrl, Meetings.class);

        assertThat(theMeetings)
                .withFailMessage("Meetings object returned null")
                .isNotNull();

        meetingsKeys.add(serverName);
        meetingsMap.put(serverName, theMeetings);


    }

    private void acquireData(String envCode) {

        String envDataString = getStringResource("redbook/comparison/meetings/" + envCode + "-meetings.json");

        assertThat(envDataString)
                .withFailMessage(
                        "\n|\t\tData for " + envCode + " is not present in the resources folder.\n"
                                + "|\t\tRun the 'curl-the-data.sh' script in resources/redbook/comparison/meetings to get it."
                )
                .isNotEmpty();

        try {
            ObjectMapper mapper = new ObjectMapper();
            Meetings theMeetings = mapper.readValue(envDataString, Meetings.class);
            meetingsMap.put(envCode, theMeetings);
            meetingsKeys.add(envCode);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void compareData() {

//        ArrayList<String> envs = new ArrayList<>();
//        meetingsMap.keySet().forEach(envs::add);
//
//        String srcStringKey = envs.get(0);
//        String cmpStringKey = envs.get(1);

        String srcStringKey = meetingsKeys.get(0);
        String cmpStringKey = meetingsKeys.get(1);

        theSourceMeetings = meetingsMap.get(srcStringKey);
        theComparisonMeetings = meetingsMap.get(cmpStringKey);

        assertThat(theSourceMeetings.meetings.size())
                .isGreaterThan(0);
        assertThat(theComparisonMeetings.meetings.size())
                .isGreaterThan(0);

    }

    private void dataIsEquivalent() {

        List<Meeting> missing = theSourceMeetings.meetings;
        missing.removeAll(theComparisonMeetings.meetings);

//        List<Meeting> missing = theComparisonMeetings.meetings;
//        missing.removeAll(theSourceMeetings.meetings);

        assertThat(missing)
                .isEmpty();

        assertThat(theComparisonMeetings.meetings)
                .containsAll(theSourceMeetings.meetings);

    }
}
