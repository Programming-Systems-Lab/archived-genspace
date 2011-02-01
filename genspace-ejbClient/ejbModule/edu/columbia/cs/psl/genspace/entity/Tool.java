package edu.columbia.cs.psl.genspace.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Tool {
	private int id;
	private String name;
	private String description;
	private List<ToolComment> comments = new ArrayList<ToolComment>();
	
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@OneToMany(mappedBy="tool")
	public List<ToolComment> getComments() {
		return comments;
	}
	public void setComments(List<ToolComment> comments) {
		this.comments = comments;
	}
}
