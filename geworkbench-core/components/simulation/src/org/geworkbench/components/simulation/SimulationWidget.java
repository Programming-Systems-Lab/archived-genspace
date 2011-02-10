package org.geworkbench.components.simulation;

import java.awt.Component;
import org.geworkbench.bison.datastructure.biocollections.microarrays.DSMicroarraySet;
import org.geworkbench.engine.config.VisualPlugin;
import org.geworkbench.engine.management.AcceptTypes;

/**
 * SimulationWidget.java
 * Created on March 1, 2006, 5:31 PM
 * @author Hooman Ahmadi
 */

public class SimulationWidget implements VisualPlugin{
    
    SimulatorPanel panel = new SimulatorPanel();
    
    /** Creates a new instance of SimulationWidget */
    public SimulationWidget() {
    }
    
    public Component getComponent(){
        return panel;
    }
}