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
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Alignment implements Serializable {

	private int id;
	
	private String emblId;
	
	private Set<ProteinSequence> sequences;
	
	private String definition;
	
	private String keywords;
	
	private String comments;
	
	private Set<Reference> references;
	
	@Override
	public String toString() {
		return emblId;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@XmlElement
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToMany(mappedBy="alignments", cascade=CascadeType.ALL)
	public Set<ProteinSequence> getSequences() {
		return sequences;
	}

	public void setSequences(Set<ProteinSequence> sequences) {
		this.sequences = sequences;
	}

	@Column(unique=true, nullable=false)
	public String getEmblId() {
		return emblId;
	}

	public void setEmblId(String emblId) {
		this.emblId = emblId;
	}

	public String getDefinition() {
		return definition;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
	}

	@Column(length=2000)
	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	
	@Column(length=4000)
	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@OneToMany(mappedBy="alignment", cascade=CascadeType.ALL)
	public Set<Reference> getReferences() {
		return references;
	}

	public void setReferences(Set<Reference> references) {
		this.references = references;
	}

}
