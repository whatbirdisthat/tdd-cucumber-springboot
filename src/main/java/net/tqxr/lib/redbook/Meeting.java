package net.tqxr.lib.redbook;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Meeting {
    public String raceType;
    public String meetingName;
    public String trackCondition;
    public String venueMnemonic;
    public String location;
    public String meetingDate;
    public Race[] races;

    public Meeting() {

    }

    @Override
    public String toString() {
        return String.format("%s (%s) %s %s %s (%s)",
                location, raceType, meetingName,
                venueMnemonic, trackCondition, meetingDate);
    }

    @Override
    public boolean equals(Object obj) {
        return String.format("%s",obj).equals(String.format("%s",this));
    }
}
