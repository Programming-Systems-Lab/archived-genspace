package org.geworkbench.bison.datastructure.bioobjects.structure;

import org.geworkbench.bison.datastructure.biocollections.DSAncillaryDataSet;
import org.geworkbench.bison.datastructure.bioobjects.DSBioObject;

import java.util.HashMap;

/**
 * User: mhall
 * Date: Mar 13, 2006
 * Time: 11:43:13 AM
 * @version $Id: DSProteinStructure.java 7572 2011-03-12 15:27:59Z zji $
 */
public interface DSProteinStructure extends DSAncillaryDataSet<DSBioObject> {
    public HashMap<String, Integer> getChains();
}
