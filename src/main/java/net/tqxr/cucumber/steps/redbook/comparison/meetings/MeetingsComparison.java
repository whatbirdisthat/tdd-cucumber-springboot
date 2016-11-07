package net.tqxr.cucumber.steps.redbook.comparison.meetings;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.tqxr.cucumber.support.DataContainer;
import net.tqxr.cucumber.support.SpringStep;
import net.tqxr.lib.redbook.Meetings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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
        Given("the meetings data is acquired from \"(.*)\"", this::acquireDataFromServer);
        When("the two are compared", this::compareData);
        Then("they are equivalent", this::dataIsEquivalent);
    }

    private void acquireDataFromServer(String serverName) {

        String fullUrl = String.format("https://%s//v1/info-service/racing/dates/today/meetings", serverName);

        try {

            URL url = new URL(fullUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            StringBuilder meetingData = new StringBuilder();
            String output;
            while ((output = br.readLine()) != null) {
                meetingData.append(output);
            }

            envStrings.put(serverName, meetingData.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void acquireData(String envCode) {

        String envDataString = getStringResource("redbook/comparison/meetings/" + envCode + "-meetings.json");
        envStrings.put(envCode, envDataString);

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
