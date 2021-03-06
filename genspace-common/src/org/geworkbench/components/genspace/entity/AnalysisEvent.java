package org.geworkbench.components.genspace.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@XmlRootElement
public class AnalysisEvent extends LazyCycleBreaker implements Serializable {

	private static final long serialVersionUID = -8668839464594843903L;
	private int id;
	private java.util.Date createdAt;
	private Tool tool;
	private Transaction transaction;
	private Set<AnalysisEventParameter> parameters;
	private String toolname;
	private String note;
	private java.util.Date finishedAt;
	
//	@Override 
//	protected Object clone() throws CloneNotSupportedException {
//		AnalysisEvent ret = new AnalysisEvent();
//		
//		ret.id = id;
//		ret.createdAt = (Date) createdAt.clone();
//		ret.transaction = (Transaction) transaction.clone();
//		ret.parameters = parameters;
//		ret.toolname = toolname.cl
//		return ret;
//	}
	@Transient
	public String getToolname() {
		if(tool != null)
			return tool.getName();
		return toolname;
	}
	public void setToolname(String toolname) {
		this.toolname = toolname;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@XmlElement
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

	@OneToMany(mappedBy="event", cascade=CascadeType.PERSIST, fetch=FetchType.EAGER)
	@XmlElement
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
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@XmlTransient
	public java.util.Date getFinishedAt() {
		return finishedAt;
	}
	public void setFinishedAt(java.util.Date finishedAt) {
		this.finishedAt = finishedAt;
	}
}
