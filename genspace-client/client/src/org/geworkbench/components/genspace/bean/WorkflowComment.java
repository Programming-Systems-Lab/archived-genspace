package org.geworkbench.components.genspace.bean;

import java.io.Serializable;
import java.util.Date;

public class WorkflowComment implements Serializable {

	public String username;
	public String comment;
	public Date postedOn;
	public int tableKey;

	@Override
	public String toString() {
		return "WorkflowComment - username: " + username + ", postedOn: "
				+ postedOn + ", comment: " + comment;
	}

}
