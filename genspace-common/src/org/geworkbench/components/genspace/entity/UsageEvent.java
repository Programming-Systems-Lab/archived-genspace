package org.geworkbench.components.genspace.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class UsageEvent {
	
	
	private int id;
	private long ipAddress;
	private java.util.Date createdAt;
	private User user;
	private LogAction actionType;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public long getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(long ipAddress) {
		this.ipAddress = ipAddress;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	public java.util.Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(java.util.Date createdAt) {
		this.createdAt = createdAt;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public LogAction getActionType() {
		return actionType;
	}
	public void setActionType(LogAction actionType) {
		this.actionType = actionType;
	}
	
	
}
