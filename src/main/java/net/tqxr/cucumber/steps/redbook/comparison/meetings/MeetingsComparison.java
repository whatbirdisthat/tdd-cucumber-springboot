package net.tqxr.cucumber.steps.redbook.comparison.meetings;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.tqxr.cucumber.support.DataContainer;
import net.tqxr.cucumber.support.SpringStep;
import net.tqxr.lib.redbook.Meetings;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class MeetingsComparison extends SpringStep<Meetings, Meetings> {

    private HashMap<String, String> envStrings = new HashMap<>();
    private Meetings theSourceMeetings;
    private Meetings theComparisonMeetings;

    public MeetingsComparison(Meetings meetings, DataContainer<Meetings> dataContainer) {
        this.dataContainer = dataContainer;
        this.testComponent = meetings;
    }

    @Override
    protected void setUpTestSteps() {
        Given("the meetings data from \"(.*)\"", this::acquireData);
        When("the two are compared", this::compareData);
        Then("they are equivalent", this::dataIsEquivalent);
    }

    private void acquireData(String envCode) {

        try {

            URL resource = getClass().getClassLoader()
                    .getResource("redbook/comparison/meetings/" + envCode + "-meetings.json");

            if (null != resource) {
                URI uri = resource.toURI();
                String content = new String(Files.readAllBytes(Paths.get(uri)));
                envStrings.put(envCode, content);
            }

        } catch (NullPointerException | URISyntaxException | IOException e) {
            e.printStackTrace();
        }

//        InputStream resourceAsStream = Meetings.class.getClassLoader().getResourceAsStream("redbook/comparison/meetings/" + envCode + "-meetings.json");
//
//        try {
//
//            int bytesAvailable = resourceAsStream.available();
//            byte[] b = new byte[bytesAvailable];
//            int read = resourceAsStream.read(b);
//            String meetingData = new String(b, StandardCharsets.UTF_8);
//            envStrings.put(envCode, meetingData);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

    private void compareData() {

        try {

            ArrayList<String> envs = new ArrayList<>();
            envStrings.keySet().forEach(envs::add);

            String srcStringKey = envs.get(0);
            String cmpStringKey = envs.get(1);

            String srcString = envStrings.get(srcStringKey);
            String cmpString = envStrings.get(cmpStringKey);

            assertThat(cmpString).isNotEqualTo(srcString);

            ObjectMapper mapper = new ObjectMapper();

            theSourceMeetings = mapper.readValue(srcString, Meetings.class);
            theComparisonMeetings = mapper.readValue(cmpString, Meetings.class);

        } catch (Exception e) {
            e.printStackTrace();
        }

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
