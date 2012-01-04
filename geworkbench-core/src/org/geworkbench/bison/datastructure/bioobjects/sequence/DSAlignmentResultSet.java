package org.geworkbench.bison.datastructure.bioobjects.sequence;

import org.geworkbench.bison.datastructure.biocollections.DSAncillaryDataSet;
import org.geworkbench.bison.datastructure.biocollections.sequences.DSSequenceSet;
import org.geworkbench.bison.datastructure.bioobjects.DSBioObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.RandomAccess;
import java.util.Vector;

/**
 * @author John Watkinson
 * @version $Id: DSAlignmentResultSet.java 8454 2011-10-24 21:55:30Z youmi $
 */
public interface DSAlignmentResultSet extends RandomAccess, Cloneable,
		DSAncillaryDataSet<DSBioObject>, Serializable {
	
	public DSSequenceSet<? extends DSSequence> getBlastedParentDataSet();
	public ArrayList<Vector<BlastObj>> getBlastDataSet(); 
	public String getSummary(); 
	public int getHitCount();

	 
}
