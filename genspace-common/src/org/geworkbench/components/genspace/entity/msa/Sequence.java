package org.geworkbench.components.genspace.entity.msa;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.geworkbench.components.genspace.entity.LazyCycleBreaker;

@Entity
@XmlRootElement
public class Sequence extends LazyCycleBreaker implements Serializable {

	private int id;

	private String accessionNo;
	private String abbrevation;
	private String description;
	
	private Set<Alignment> alignments;

	@Override
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@XmlElement
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(unique=true)
	public String getAccessionNo() {
		return accessionNo;
	}
	public void setAccessionNo(String accessionNo) {
		this.accessionNo = accessionNo;
	}
	public String getAbbrevation() {
		return abbrevation;
	}
	public void setAbbrevation(String abbrevation) {
		this.abbrevation = abbrevation;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@ManyToMany(cascade=CascadeType.ALL)
	public Set<Alignment> getAlignments() {
		return alignments;
	}
	public void setAlignments(Set<Alignment> alignments) {
		this.alignments = alignments;
	}
	
}
