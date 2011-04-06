package org.geworkbench.bison.datastructure.bioobjects.sequence;

import org.geworkbench.bison.datastructure.biocollections.DSAncillaryDataSet;
import org.geworkbench.bison.datastructure.bioobjects.DSBioObject;

import java.io.Serializable;
import java.util.RandomAccess;

/**
 * @author John Watkinson
 * @version $Id: DSAlignmentResultSet.java 7572 2011-03-12 15:27:59Z zji $
 */
public interface DSAlignmentResultSet extends RandomAccess, Cloneable, DSAncillaryDataSet<DSBioObject>, Serializable {

    String getResultFilePath();
}
