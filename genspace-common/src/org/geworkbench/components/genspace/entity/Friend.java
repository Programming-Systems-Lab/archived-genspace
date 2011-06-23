package org.geworkbench.components.genspace.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.persistence.oxm.annotations.XmlInverseReference;

@Entity
@Table(name="Friend")
public class Friend extends LazyCycleBreaker implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7217903012674558469L;
	private int id;
	private User leftUser;
	private User rightUser;
	private boolean mutual;
	private boolean visible;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@XmlElement
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
	@JoinColumn(name="id_1")
    @XmlIDREF
	public User getLeftUser() {
		return leftUser;
	}
	public void setLeftUser(User leftUser) {
		this.leftUser = leftUser;
	}
	
	@JoinColumn(name="id_2")
	@XmlIDREF
	public User getRightUser() {
		return rightUser;
	}
	public void setRightUser(User rightUser) {
		this.rightUser = rightUser;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Friend ID " + getId();
	}
	
}
