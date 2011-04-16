package org.geworkbench.components.genspace.entity;

import java.io.Serializable;
<<<<<<< HEAD

import javax.persistence.Entity;
=======
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
<<<<<<< HEAD
=======
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7


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
<<<<<<< HEAD
=======
	private Date createdAt;
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7
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
<<<<<<< HEAD
=======
	
	@ManyToOne(fetch = FetchType.EAGER)
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7
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
<<<<<<< HEAD
=======
	
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	@ManyToOne
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7
	public WorkflowFolder getFolder() {
		return folder;
	}
	public void setFolder(WorkflowFolder folder) {
		this.folder = folder;
	}
<<<<<<< HEAD
	
	@Override
	public String toString() {
		return "UserWorkflow - name: " + name + ", " + owner.getUsername() + ", "
				+ workflow + ", " + folder;
	}

	@Override
	public boolean equals(Object o) {
=======
	@Override
	public String toString() {
		return "UserWorkflow - name: " + name + ", " + owner.getUsername() + ", "
				+ workflow;
	}
	@Override
	public int hashCode() {
		return this.getId();
	}
	@Override
	public boolean equals(Object o) {
		if(o == null)
			return false;
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7
		try {
			UserWorkflow uw = (UserWorkflow) o;
			return owner.equals(uw.owner) && workflow.equals(uw.workflow);
		} catch (ClassCastException e) {
			e.printStackTrace();
		}
		return false;
	}
}
