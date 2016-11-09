package net.tqxr.cucumber.support.redbook.comparison.meetings;

import lombok.Getter;
import lombok.Setter;

public class MeetingsApiDefinition {

    @Getter
    @Setter
    String server;

    @Getter
    @Setter
    String api;

    @Getter
    @Setter
    String jurisdiction;

    @Getter
    @Setter
    String location;

    @Getter
    @Setter
    ReduceMethod method;

    @Override
    public String toString() {
        return String.format("SERVER: %s, API: %s, Jurisdiction: %s, Locations: %s",
                server, api, jurisdiction, location);
    }

    public String getUrl() {
        String fullUrl = String.format("https://%s//v1/%s/racing/dates/today/meetings", getServer(), getApi());
        if (!getJurisdiction().isEmpty()) {
            fullUrl += String.format("?jurisdiction=%s", getJurisdiction());
        }
        return fullUrl;
    }

}
