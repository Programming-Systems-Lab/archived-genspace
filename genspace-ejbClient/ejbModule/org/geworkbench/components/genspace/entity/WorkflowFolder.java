package org.geworkbench.components.genspace.entity;

import java.io.Serializable;
<<<<<<< HEAD
=======
import java.util.ArrayList;
import java.util.List;
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
<<<<<<< HEAD

@Entity
public class WorkflowFolder implements Serializable{
=======
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class WorkflowFolder implements Serializable, Comparable<WorkflowFolder>{
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7
	/**
	 * 
	 */
	private static final long serialVersionUID = -1365608467302705177L;
	private int id;
	private User owner;
	private String name;
	private WorkflowFolder parent;
<<<<<<< HEAD
=======
	private List<UserWorkflow> workflows = new ArrayList<UserWorkflow>();
	
	private List<WorkflowFolder> children = new ArrayList<WorkflowFolder>();
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7
	
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
<<<<<<< HEAD
=======
	@OneToMany(mappedBy="parent")
	public List<WorkflowFolder> getChildren(){
		return children;
	}
	public void setChildren(List<WorkflowFolder> children) {
		this.children = children;
	}
	@ManyToOne
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7
	public WorkflowFolder getParent() {
		return parent;
	}
	public void setParent(WorkflowFolder parent) {
		this.parent = parent;
	}
<<<<<<< HEAD
	
	 
=======
	@Override
	public int compareTo(WorkflowFolder o) {
		return this.getName().compareTo(o.getName());
	}
	@OneToMany(mappedBy="folder")
	public List<UserWorkflow> getWorkflows() {
		return workflows;
	}
	public void setWorkflows(List<UserWorkflow> workflows) {
		this.workflows = workflows;
	}
	@Override
	public String toString() {
		return this.getName();
	}
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof WorkflowFolder)
			return ((WorkflowFolder) obj).getId() == getId();
		return false;
	}
	@Override
	public int hashCode() {
		return new Integer(id).hashCode();
	}
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7
}
