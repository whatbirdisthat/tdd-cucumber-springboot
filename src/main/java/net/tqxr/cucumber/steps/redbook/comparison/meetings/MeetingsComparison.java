package net.tqxr.cucumber.steps.redbook.comparison.meetings;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.tqxr.cucumber.support.DataContainer;
import net.tqxr.cucumber.support.SpringStep;
import net.tqxr.lib.redbook.Meetings;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class MeetingsComparison extends SpringStep<Meetings, Meetings> {

    private RestTemplate restTemplate = new RestTemplate();

    private Meetings theSourceMeetings;
    private Meetings theComparisonMeetings;

    private HashMap<String, Meetings> meetingsMap = new HashMap<>();

    public MeetingsComparison(Meetings meetings, DataContainer<Meetings> dataContainer) {
        this.dataContainer = dataContainer;
        this.testComponent = meetings;
    }

    @Override
    protected void setUpTestSteps() {
        Given("the meetings data from \"(.*)\"", this::acquireData);
        Given("the meetings data is acquired from \"(.*)\"", this::acquireDataFromServer);
        When("the two are compared", this::compareData);
        Then("they are equivalent", this::dataIsEquivalent);
    }

    private void acquireDataFromServer(String serverName) {

        String fullUrl = String.format("https://%s//v1/info-service/racing/dates/today/meetings", serverName);
        Meetings theMeetings = restTemplate.getForObject(fullUrl, Meetings.class);

        assertThat(theMeetings)
                .withFailMessage("Meetings object returned null")
                .isNotNull();

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
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void compareData() {

        ArrayList<String> envs = new ArrayList<>();
        meetingsMap.keySet().forEach(envs::add);

        String srcStringKey = envs.get(0);
        String cmpStringKey = envs.get(1);

        theSourceMeetings = meetingsMap.get(srcStringKey);
        theComparisonMeetings = meetingsMap.get(cmpStringKey);

        assertThat(theSourceMeetings.meetings.size())
                .isGreaterThan(0);
        assertThat(theComparisonMeetings.meetings.size())
                .isGreaterThan(0);

    }

    private void dataIsEquivalent() {

        assertThat(theComparisonMeetings.meetings)
                .containsAll(theSourceMeetings.meetings);

    }
}
