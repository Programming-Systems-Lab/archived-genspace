package org.geworkbench.components.genspace.entity;

import java.io.Serializable;
<<<<<<< HEAD
import java.util.HashSet;
=======
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
<<<<<<< HEAD
import javax.persistence.ManyToMany;
=======
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7
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
	
	
<<<<<<< HEAD
	private Set<UserNetwork> members = new HashSet<UserNetwork>();
=======
	private List<UserNetwork> members = new ArrayList<UserNetwork>();
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7
	
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
<<<<<<< HEAD
	public Set<UserNetwork> getMembers() {
		return members;
	}
	public void setMembers(Set<UserNetwork> members) {
		this.members = members;
	}
	
	
=======
	public List<UserNetwork> getMembers() {
		return members;
	}
	public void setMembers(List<UserNetwork> members) {
		this.members = members;
	}
	
	@Override
	public String toString() {
		return name;
	}
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Network)
		{
			Network o = (Network) obj;
			return o.getId() == getId() && o.getName().equals(getName());
		}
		return false;
	}
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7
}
