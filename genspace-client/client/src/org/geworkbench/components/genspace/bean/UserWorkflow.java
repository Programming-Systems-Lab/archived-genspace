package org.geworkbench.components.genspace.bean;

import java.io.Serializable;

public class UserWorkflow implements Serializable {
	
	public String name;
	public String folder;
	public Workflow workflow;
	public String username;
	
	/**
	 * Only used to create a fake Workflow folder node in the Repository folder hierarchy
	 * @param n
	 */
	public UserWorkflow(String n){
		name = n;
	}
	
	public UserWorkflow(){}
	
	@Override
	public String toString(){
		return "UserWorkflow - name: "+name+", "+username+", "+workflow+", "+folder;
	}
	
	@Override
	public boolean equals(Object o){
		try{
			UserWorkflow uw = (UserWorkflow)o;
			return username.equals(uw.username) && workflow.equals(uw.workflow);
		}
		catch(ClassCastException e){
			e.printStackTrace();
		}
		return false;
	}
	
}
