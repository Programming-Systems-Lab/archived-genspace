package edu.columbia.cs.psl.genspace.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Workflow {
	private int id;
	private User creator;
	private java.util.Date createdAt;
	private Transaction creationTransaction;
	private int numUsage;
	private	List<Tool> tools = new ArrayList<Tool>();
	private List<WorkflowComment> comments = new ArrayList<WorkflowComment>();
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public User getCreator() {
		return creator;
	}
	public void setCreator(User creator) {
		this.creator = creator;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	public java.util.Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(java.util.Date createdAt) {
		this.createdAt = createdAt;
	}
	public Transaction getCreationTransaction() {
		return creationTransaction;
	}
	public void setCreationTransaction(Transaction creationTransaction) {
		this.creationTransaction = creationTransaction;
	}
	public int getNumUsage() {
		return numUsage;
	}
	public void setNumUsage(int numUsage) {
		this.numUsage = numUsage;
	}
	
	@ManyToMany
	public List<Tool> getTools() {
		return tools;
	}
	public void setTools(List<Tool> tools) {
		this.tools = tools;
	}
	
	@OneToMany(mappedBy="workflow")
	public List<WorkflowComment> getComments() {
		return comments;
	}
	public void setComments(List<WorkflowComment> comments) {
		this.comments = comments;
	}
	
	
}
