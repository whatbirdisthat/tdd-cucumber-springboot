package net.tqxr.lib.redbook;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Race {
    public String cashOutEligibility;
    public String raceStartTime;
    public boolean willHaveFixedOdds;
    public boolean hasFixedOdds;
    public boolean hasParimutuel;
    public boolean allIn;
    public int raceNumber;
    public int raceDistance;
    public ArrayList<Scratching> scratchings;
    public String raceName;
    public boolean bestOddsGuaranteed;
    public String id;
    public String raceStatus;
    public int[][] results;

    public Race() {

    }
}
