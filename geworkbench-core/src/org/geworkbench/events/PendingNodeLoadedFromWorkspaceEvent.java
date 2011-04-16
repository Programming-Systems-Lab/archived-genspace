package org.geworkbench.events;

import java.util.Collection;

import org.geworkbench.engine.config.events.Event;
import org.ginkgo.labs.ws.GridEndpointReferenceType;

/**
 * 
 * @author kk2457
 * @version $Id: PendingNodeLoadedFromWorkspaceEvent.java 7257 2010-11-30 18:34:35Z zji $
 */
public class PendingNodeLoadedFromWorkspaceEvent extends Event {

	private Collection<GridEndpointReferenceType> gridEprs = null;

	public PendingNodeLoadedFromWorkspaceEvent(
			Collection<GridEndpointReferenceType> gridEprs) {
		super(null);
		this.gridEprs = gridEprs;
	}

	public Collection<GridEndpointReferenceType> getGridEprs() {
		return gridEprs;
	}

}
