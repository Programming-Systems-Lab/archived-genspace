package org.geworkbench.components.simulation.util;

import java.util.*;

public class NumberedGeneNameComparator implements Comparator{
    public NumberedGeneNameComparator() {
    }

    /**
     * equals
     *
     * @param obj Object
     * @return boolean
     */
    public boolean equals(Object obj) {
        return false;
    }

    /**
     * compare
     *
     * @param o1 Object
     * @param o2 Object
     * @return int
     */
    public int compare(Object o1, Object o2) {
        int int1 = getIntVal(o1);
        int int2 = getIntVal(o2);

        return int1 - int2;
    }

    int getIntVal(Object o){
        String strVal = (String)o;
        try{
            return Integer.parseInt(strVal.substring(1));
        }catch(NumberFormatException e){
            System.out.println("Number format exception in class NumberedGeneNameComparator for string " + strVal);
            return -1;
        }
    }
}
