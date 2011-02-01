package edu.columbia.cs.psl.genspace.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Friend {
	private int id;
	private User leftUser;
	private User rightUser;
	private boolean mutual;
	private boolean visible;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isMutual() {
		return mutual;
	}
	public void setMutual(boolean mutual) {
		this.mutual = mutual;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	@ManyToOne
	public User getLeftUser() {
		return leftUser;
	}
	public void setLeftUser(User leftUser) {
		this.leftUser = leftUser;
	}
	public User getRightUser() {
		return rightUser;
	}
	public void setRightUser(User rightUser) {
		this.rightUser = rightUser;
	}
	
	
}
