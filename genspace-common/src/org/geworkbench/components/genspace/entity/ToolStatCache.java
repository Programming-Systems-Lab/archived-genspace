package org.geworkbench.components.genspace.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class ToolStatCache extends LazyCycleBreaker implements Serializable {
	private int toolid;
	private Tool mostPopularNext;
	private Tool mostPopularBefore;
	private Date updated;
	
	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return toolid;
	}
	@Id
	public int getToolid() {
		return toolid;
	}
	public void setToolid(int toolid) {
		this.toolid = toolid;
	}
	public Tool getMostPopularNext() {
		return mostPopularNext;
	}
	public void setMostPopularNext(Tool mostPopularNext) {
		this.mostPopularNext = mostPopularNext;
	}
	public Tool getMostPopularBefore() {
		return mostPopularBefore;
	}
	public void setMostPopularBefore(Tool mostPopularBefore) {
		this.mostPopularBefore = mostPopularBefore;
	}
	@Temporal(TemporalType.TIMESTAMP)
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
}
