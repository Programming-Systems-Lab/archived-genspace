package org.geworkbench.components.tprofiler;

import java.util.Set;

/**
 * Stores a row of T-Profiler data.
 */
public class ProfileGroup {

    private String groupName;
    private double tValue;
    private Set<String> markerIDs;



    private double pValue;
    //private String pValue;
    private double mean;
    private int orfCount;
    private int otherCount;




    /**
     * Constructs a new ProfileGroup.
     * @param groupName the name of this group (for example, GO Term or Pathway)
     * @param tValue the t value of the group.
     * @param markerIDs the set of marker IDs that belong to this group.
     */
    public ProfileGroup(String groupName, double tValue, double pValue, double mean, Set<String> markerIDs) {
        this.groupName = groupName;
        this.tValue = tValue;
        this.markerIDs = markerIDs;
        this.pValue = pValue;
        this.mean = mean;
        this.orfCount = markerIDs.size();

    }

    public ProfileGroup(String groupName, double tValue, double pValue, double mean, int numberOfOrfs, int numberOtherMarkers, Set<String> markerIDs) {
        this.groupName = groupName;
        this.tValue = tValue;
        this.markerIDs = markerIDs;
        this.pValue = pValue;
        this.mean = mean;
        this.orfCount = numberOfOrfs;
        this.otherCount = numberOtherMarkers;

    }

    public String getGroupName() {
        return groupName;
    }
    
    public double getMean() {
        return mean;
    }

    public int getOrfCount() {
        return orfCount;
    }

    public int getOtherCount() {
        return otherCount;
    }

    public double getTValue() {
        return tValue;
    }



    public double getPValue() {
        return pValue;
    }

    public Set<String> getMarkerIDs() {
        return markerIDs;
    }

}