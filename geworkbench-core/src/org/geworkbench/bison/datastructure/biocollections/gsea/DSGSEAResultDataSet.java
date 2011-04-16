package org.geworkbench.bison.datastructure.biocollections.gsea;

import org.geworkbench.bison.datastructure.biocollections.DSAncillaryDataSet;
import org.geworkbench.bison.datastructure.bioobjects.DSBioObject;

/**
 * Created by IntelliJ IDEA.
 * User: nazaire
 * @version $Id: DSGSEAResultDataSet.java 7707 2011-04-07 18:38:11Z zji $
 */
public interface DSGSEAResultDataSet extends DSAncillaryDataSet<DSBioObject> 
{
	public abstract String getReportFile();

}
