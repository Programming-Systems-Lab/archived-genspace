package edu.columbia.cs.psl.genspace.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class WorkflowFolder {
	private int id;
	private User owner;
	private String name;
	private WorkflowFolder parent;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User ownerId) {
		this.owner = ownerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public WorkflowFolder getParent() {
		return parent;
	}
	public void setParent(WorkflowFolder parent) {
		this.parent = parent;
	}
	
	 
}
