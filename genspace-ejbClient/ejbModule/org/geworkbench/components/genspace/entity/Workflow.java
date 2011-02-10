package org.geworkbench.components.genspace.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.geworkbench.components.genspace.bean.DomainUtil;
import org.geworkbench.components.genspace.bean.RatingBean;

@Entity
public class Workflow implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -344401848390541281L;
	private int id;
	private User creator;
	private java.util.Date createdAt;
	private Transaction creationTransaction;
	private	List<WorkflowTool> tools = new ArrayList<WorkflowTool>();
	private List<WorkflowComment> comments = new ArrayList<WorkflowComment>();
	private List<WorkflowRating> ratings = new ArrayList<WorkflowRating>();
	private int usageCount;
	private Workflow parent;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public User getCreator() {
		return creator;
	}
	public void setCreator(User creator) {
		this.creator = creator;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	public java.util.Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(java.util.Date createdAt) {
		this.createdAt = createdAt;
	}
	public Transaction getCreationTransaction() {
		return creationTransaction;
	}
	public void setCreationTransaction(Transaction creationTransaction) {
		this.creationTransaction = creationTransaction;
	}

	@OneToMany(mappedBy="workflow", cascade=CascadeType.PERSIST, fetch=FetchType.EAGER)
	@OrderBy("order ASC")
	public List<WorkflowTool> getTools() {
		return tools;
	}
	public void setTools(List<WorkflowTool> tools) {
		this.tools = tools;
	}
	
	@OneToMany(mappedBy="workflow")
	public List<WorkflowComment> getComments() {
		return comments;
	}
	public void setComments(List<WorkflowComment> comments) {
		this.comments = comments;
	}
	
	@OneToMany(mappedBy="workflow")
	public List<WorkflowRating> getRatings() {
		return ratings;
	}
	public void setRatings(List<WorkflowRating> ratings) {
		this.ratings = ratings;
	}
	public int getUsageCount() {
		return usageCount;
	}
	public void setUsageCount(int usageCount) {
		this.usageCount = usageCount;
	}
	private String cachedIdentifier;

	
	@Transient
	public String getIdentifier() {
//		if (cachedIdentifier == null)
//			cachedIdentifier = DomainUtil.getStringID(tools);
//		return cachedIdentifier;
		return "FIXME";
	}
	
	public double getAvgRating() {
		double result = 0;
		if (ratings.size() > 0) {
			for (WorkflowRating r : ratings) {
				result += r.getRating();
			}
			result /= ratings.size();
		}
		return result;
	}
	
	@Override
	public String toString() {
		String r = "";
		for(WorkflowTool wt : tools)
		{
			r += wt.getTool().getName() + ", ";
		}
		r = r.substring(0,r.length()-2);
		return r;
	}
	
	public Workflow getParent() {
		return parent;
	}
	public void setParent(Workflow parent) {
		this.parent = parent;
	}
	
	public String getLastToolName()
	{
		return this.getTools().get(this.getTools().size() -1).getTool().getName();
	}
}
