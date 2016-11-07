package net.tqxr.lib.redbook;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Meeting {

    @Getter
    @Setter
    String raceType;
    @Getter
    @Setter
    String meetingName;
    @Getter
    @Setter
    String trackCondition;
    @Getter
    @Setter
    String venueMnemonic;
    @Getter
    @Setter
    String location;
    @Getter
    @Setter
    String meetingDate;
    @Getter
    @Setter
    Race[] races;

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
        return String.format("%s", obj).equals(String.format("%s", this));
    }
}
