package org.geworkbench.components.genspace.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="taste_users")
@XmlRootElement
public class TasteUser implements Serializable {
	

	private static final long serialVersionUID = 3796674992532941677L;
	private int id;
	private User user;
	private String hostname;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@XmlElement
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@ManyToOne
	@JoinColumn(name="registration_id")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@Column(name="hostname", nullable = true)
	public String getHostname() {
		return hostname;
	}
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	
	@Override
	public String toString() {
		String result = "TasteUser [ user={" + user.toString() + "}]" + "[hostname={" + hostname + "}]";
		return result;
	}

	@Override
	public int hashCode() {
		return this.getId();
	}
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof TasteUser)
		{
			return ((TasteUser) obj).getId() == this.getId();
		}
		return false;
	}

	public String getTasteUsername()
	{
		if (user == null)
			return hostname;
		else
			return user.getUsername();
	}
}
