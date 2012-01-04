package org.geworkbench.bison.datastructure.bioobjects.markers;

import org.geworkbench.bison.datastructure.bioobjects.microarray.DSMarkerValue;
import org.geworkbench.bison.util.Range;

/**
 * <p>Title: Plug And Play</p>
 * <p>Description: Dynamic Proxy Implementation of enGenious</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: First Genetic Trust Inc.</p>
 *
 * @author Manjunath Kustagi
 * @version $Id: DSRangeMarker.java 8460 2011-10-25 20:06:14Z zji $
 */

public interface DSRangeMarker {

    Range getRange();

    void updateRange(DSMarkerValue marker);

    void reset(int id);
}
