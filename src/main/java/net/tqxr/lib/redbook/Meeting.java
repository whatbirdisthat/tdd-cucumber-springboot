package net.tqxr.lib.redbook;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

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
        return String.format("%s %s (%s/%s) %s (%s)",
                location,
                meetingName, raceType, venueMnemonic,
                trackCondition, meetingDate);
    }

    @Override
    public boolean equals(Object obj) {

        if (null == obj) return false;
        Meeting cmp = (Meeting) obj;
        return null != cmp &&
                (
                        Objects.equals(raceType, cmp.raceType)
                        && Objects.equals(meetingName, cmp.meetingName)
                        && Objects.equals(meetingDate, cmp.meetingDate)
                );

    }
}
