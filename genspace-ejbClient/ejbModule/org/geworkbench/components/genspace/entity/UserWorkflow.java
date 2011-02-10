package org.geworkbench.components.genspace.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class UserWorkflow implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6313031043276205912L;
	private int id;
	private User owner;
	private Workflow workflow;
	private String name;
	private WorkflowFolder folder;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@ManyToOne
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public Workflow getWorkflow() {
		return workflow;
	}
	public void setWorkflow(Workflow workflow) {
		this.workflow = workflow;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public WorkflowFolder getFolder() {
		return folder;
	}
	public void setFolder(WorkflowFolder folder) {
		this.folder = folder;
	}
	
	@Override
	public String toString() {
		return "UserWorkflow - name: " + name + ", " + owner.getUsername() + ", "
				+ workflow + ", " + folder;
	}

	@Override
	public boolean equals(Object o) {
		try {
			UserWorkflow uw = (UserWorkflow) o;
			return owner.equals(uw.owner) && workflow.equals(uw.workflow);
		} catch (ClassCastException e) {
			e.printStackTrace();
		}
		return false;
	}
}
