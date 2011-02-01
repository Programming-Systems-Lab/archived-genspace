package edu.columbia.cs.psl.genspace.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Transaction {

	private int id;
	private String clientID;
	private String hostname;
	private java.util.Date date;
	private User user;
	private Set<AnalysisEvent> analysisEvents;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getClientID() {
		return clientID;
	}
	public void setClientID(String clientID) {
		this.clientID = clientID;
	}
	public String getHostname() {
		return hostname;
	}
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	@Temporal(TemporalType.TIMESTAMP)
	public java.util.Date getDate() {
		return date;
	}
	public void setDate(java.util.Date date) {
		this.date = date;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@OneToMany(mappedBy="transaction")
	public Set<AnalysisEvent> getAnalysisEvents() {
		return analysisEvents;
	}
	public void setAnalysisEvents(Set<AnalysisEvent> analysisEvents) {
		this.analysisEvents = analysisEvents;
	}
}