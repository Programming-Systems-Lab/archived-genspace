package edu.columbia.cs.psl.genspace.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class AnalysisEvent {
	private int id;
	private java.util.Date createdAt;
	private Tool tool;
	private Transaction transaction;
	private Set<AnalysisEventParameter> parameters = new HashSet<AnalysisEventParameter>();
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public Tool getTool() {
		return tool;
	}
	public void setTool(Tool tool) {
		this.tool = tool;
	}
	@ManyToOne
	public Transaction getTransaction() {
		return transaction;
	}
	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	@OneToMany(mappedBy="event")
	public Set<AnalysisEventParameter> getParameters() {
		return parameters;
	}
	public void setParameters(Set<AnalysisEventParameter> parameters) {
		this.parameters = parameters;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	public java.util.Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(java.util.Date createdAt) {
		this.createdAt = createdAt;
	}
	
}
