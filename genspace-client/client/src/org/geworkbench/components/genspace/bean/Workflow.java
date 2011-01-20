package org.geworkbench.components.genspace.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Workflow implements Serializable {

	public int ID; // the ID may not always be available if not explicitly
					// loaded from the DB
	public String creatorUsername;
	public Date creationDate;
	public String creationTransactionId;
	public int usageCount = 0;
	public ArrayList<Tool> tools = new ArrayList<Tool>();
	public ArrayList<RatingBean> ratings = new ArrayList<RatingBean>();
	public ArrayList<WorkflowComment> comments = new ArrayList<WorkflowComment>();
	public String cachedIdentifier;

	// In the DB there is a tree-like representation of the workflows in the
	// "workflows table"
	// a workflow is a sequence of nodes where each node is a pair (parent,
	// workflow, toolname)
	// a general sequence is (-1, workflow1, toolname1) -> (workflow1,
	// workflow2, toolname2) -> and so on
	// the workflows ids are kept in this array, the parent id is -1 for the
	// first one or the id of the prev node
	// the actual ID of a workflow is the id of the last element in the list
	public ArrayList<Integer> workflowsTableToolIds = new ArrayList<Integer>();

	public Workflow() {
	}

	public Workflow(int id) {
		ID = id;
	}

	public String getIdentifier() {
		if (cachedIdentifier == null)
			cachedIdentifier = DomainUtil.getStringID(tools);
		return cachedIdentifier;
	}

	@Override
	public int hashCode() {
		return getIdentifier().hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Workflow) {
			Workflow w = (Workflow) o;
			return w.getIdentifier().equals(this.getIdentifier());
		}
		return false;
	}

	@Override
	public String toString() {
		return ID + " " + creatorUsername + " " + creationDate + " "
				+ creationTransactionId + " " + usageCount + " " + tools.size();
	}

	public double getAvgRating() {
		double result = 0;
		if (ratings.size() > 0) {
			for (RatingBean r : ratings) {
				result += r.getUserRating();
			}
			result /= ratings.size();
		}
		return result;
	}
}
