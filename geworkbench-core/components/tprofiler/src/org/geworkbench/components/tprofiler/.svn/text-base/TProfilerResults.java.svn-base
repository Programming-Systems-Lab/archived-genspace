package org.geworkbench.components.tprofiler;

import org.geworkbench.bison.datastructure.biocollections.CSAncillaryDataSet;
import org.geworkbench.bison.datastructure.biocollections.DSDataSet;

import java.util.Comparator;
import java.util.ArrayList;
import java.util.Collections;
import java.io.File;

/**
 * Stores the results of a T-Profiler run.
 */
public class TProfilerResults extends CSAncillaryDataSet {

    public static final Comparator<TProfilerGroup> T_VALUE_COMPARATOR = new Comparator<TProfilerGroup>() {
        public int compare(TProfilerGroup o1, TProfilerGroup o2) {
            if (o1.getTValue() > o2.getTValue()) {
                return 1;
            } else if (o1.getTValue() < o2.getTValue()) {
                return -1;
            } else {
                return 0;
            }
        }
    };

    public static final Comparator<TProfilerGroup> NAME_COMPARATOR = new Comparator<TProfilerGroup>() {
        public int compare(TProfilerGroup o1, TProfilerGroup o2) {
            return o1.getGroupName().compareTo(o2.getGroupName());
        }
    };

    private ArrayList<TProfilerGroup> groups;

    public TProfilerResults(DSDataSet parent, String label) {
        super(parent, label);
        groups = new ArrayList<TProfilerGroup>();
    }

    public void addGroup(TProfilerGroup group) {
        groups.add(group);
    }

    public int getNumberOfGroups() {
        return groups.size();
    }

    public TProfilerGroup getGroup(int index) {
        return groups.get(index);
    }

    public void sort(Comparator<TProfilerGroup> comparator) {
        Collections.sort(groups, comparator);
    }


    public File getDataSetFile() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setDataSetFile(File file) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
