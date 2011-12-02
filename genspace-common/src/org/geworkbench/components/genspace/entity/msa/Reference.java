package org.geworkbench.components.genspace.entity.msa;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.geworkbench.components.genspace.entity.LazyCycleBreaker;

@Entity
@XmlRootElement
public class Reference extends LazyCycleBreaker implements Serializable {

	private int id;

	private String title;

	private String authors;
	
	private String locator;
	
	private Alignment alignment;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@XmlElement
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthors() {
		return authors;
	}
	public void setAuthors(String authors) {
		this.authors = authors;
	}
	public String getLocator() {
		return locator;
	}
	public void setLocator(String locator) {
		this.locator = locator;
	}
	
	@ManyToOne(cascade=CascadeType.ALL)
	public Alignment getAlignment() {
		return alignment;
	}
	public void setAlignment(Alignment alignment) {
		this.alignment = alignment;
	}
	
}
