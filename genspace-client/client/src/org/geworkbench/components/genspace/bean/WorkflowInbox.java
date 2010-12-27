package org.geworkbench.components.genspace.bean;

import java.io.Serializable;
import java.util.Date;

public class WorkflowInbox implements Serializable{
	
	public Workflow workflow;
	public Date date;
	public String sender;
	public String name;
	
	@Override
	public String toString(){
		return "WorkflowInbox - name: "+name+", sender: "+sender+", date: "+date+", "+workflow;
	}

}
