package org.geworkbench.components.genspace.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class IgnoredHost {
	private int ip;
	private String comment;
	
	@Id
	public int getIp() {
		return ip;
	}
	public void setIp(int ip) {
		this.ip = ip;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
}
