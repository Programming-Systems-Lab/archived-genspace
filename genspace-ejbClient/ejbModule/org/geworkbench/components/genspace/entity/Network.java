package org.geworkbench.components.genspace.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Network implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 330141032033420545L;
	private int id;
	private String name;
	private User owner;
	
	
	private Set<UserNetwork> members = new HashSet<UserNetwork>();
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@ManyToOne
	@JoinColumn(name="owner")
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	
	@OneToMany(mappedBy="network")
	public Set<UserNetwork> getMembers() {
		return members;
	}
	public void setMembers(Set<UserNetwork> members) {
		this.members = members;
	}
	
	
}
