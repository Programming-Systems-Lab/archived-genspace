package org.geworkbench.events;

import org.geworkbench.engine.config.events.Event;
import org.ginkgo.labs.ws.GridEndpointReferenceType;

/**
 * 
 * @author kk2457
 * @version $Id: PendingNodeCancelledEvent.java 7263 2010-11-30 20:08:04Z zji $
 */
public class PendingNodeCancelledEvent extends Event {

	private GridEndpointReferenceType gridEpr = null;

	public PendingNodeCancelledEvent(GridEndpointReferenceType gridEpr) {
		super(null);
		this.gridEpr = gridEpr;
	}

	public GridEndpointReferenceType getGridEpr() {
		return gridEpr;
	}

}
