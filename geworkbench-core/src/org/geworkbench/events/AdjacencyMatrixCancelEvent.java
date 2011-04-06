package org.geworkbench.events;

import org.geworkbench.engine.config.events.Event;
import org.geworkbench.util.pathwaydecoder.mutualinformation.AdjacencyMatrix;

/**
 * 
 * The event for communication from CNKB to cytoscape when you want to cancel the drawing of network.
 *  
 * @version $Id: AdjacencyMatrixCancelEvent.java 7682 2011-03-29 17:41:54Z zji $
 */
public class AdjacencyMatrixCancelEvent extends Event {

    private final AdjacencyMatrix adjacencyMatrix;

    public AdjacencyMatrixCancelEvent(final AdjacencyMatrix adjacencyMatrix) {
        super(null);
        this.adjacencyMatrix = adjacencyMatrix;
    }

    public AdjacencyMatrix getAdjacencyMatrix() {
        return adjacencyMatrix;
    }
}
