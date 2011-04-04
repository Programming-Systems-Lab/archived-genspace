package org.geworkbench.bison.datastructure.bioobjects.microarray;

import org.geworkbench.bison.datastructure.biocollections.DSAncillaryDataSet;
import org.geworkbench.bison.datastructure.biocollections.microarrays.DSMicroarraySet;
import org.geworkbench.bison.datastructure.bioobjects.markers.DSGeneMarker;
import org.geworkbench.bison.datastructure.complex.panels.DSPanel;

/**
 * @author John Watkinson
 * @version $Id: DSSignificanceResultSet.java 7411 2011-02-04 20:28:55Z zji $
 */
public interface DSSignificanceResultSet <T extends DSGeneMarker> extends DSAncillaryDataSet<DSMicroarray> {

    public static final int CASE = 0;
    public static final int CONTROL = 1;

    /**
     * Gets the significance value for the marker.
     */
    public Double getSignificance(T marker);

    public void setSignificance(T marker, double signficance);

    public void setTValue(T marker, double value);
    
    public Double getTValue(T marker);
    
    public Double getFoldChange(T marker);

    public void setFoldChange(T marker, double signficance);
    
    public DSPanel<T> getSignificantMarkers();

    public double getCriticalPValue();

    public String[] getLabels(int index);

    public DSMicroarraySet<DSMicroarray> getParentDataSet();

    public void sortMarkersBySignificance();
  
    public void setMarker(T marker, double signficance);
    
    public void addSigGenToPanel(T marker);   
    
}