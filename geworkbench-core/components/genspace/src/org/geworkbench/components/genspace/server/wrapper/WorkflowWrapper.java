package org.geworkbench.components.genspace.server.wrapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.geworkbench.components.genspace.RuntimeEnvironmentSettings;
import org.geworkbench.components.genspace.server.stubs.Transaction;
import org.geworkbench.components.genspace.server.stubs.User;
import org.geworkbench.components.genspace.server.stubs.WorkflowComment;
import org.geworkbench.components.genspace.server.stubs.WorkflowRating;
import org.geworkbench.components.genspace.server.stubs.WorkflowTool;


public class WorkflowWrapper {
	 org.geworkbench.components.genspace.server.stubs.Workflow delegate;
	 public WorkflowWrapper( org.geworkbench.components.genspace.server.stubs.Workflow delegate)
	 {
		 this.delegate = delegate;
	 }
	public int getCachedChildrenCount() {
		return delegate.getCachedChildrenCount();
	}
	public void setCachedChildrenCount(int cachedChildrenCount) {
		delegate.setCachedChildrenCount(cachedChildrenCount);
	}
	public int getCachedParentId() {
		return delegate.getCachedParentId();
	}
	public void setCachedParentId(int cachedParentId) {
		delegate.setCachedParentId(cachedParentId);
	}
	public org.geworkbench.components.genspace.server.stubs.Workflow[] getChildren() {
		return delegate.getChildren();
	}
	public void setChildren(
			org.geworkbench.components.genspace.server.stubs.Workflow[] children) {
		delegate.setChildren(children);
	}
	public org.geworkbench.components.genspace.server.stubs.Workflow getChildren(int i) {
		return delegate.getChildren(i);
	}
	public void setChildren(int i,
			org.geworkbench.components.genspace.server.stubs.Workflow _value) {
		delegate.setChildren(i, _value);
	}
	public WorkflowComment[] getComments() {
		return delegate.getComments();
	}
	public void setComments(WorkflowComment[] comments) {
		delegate.setComments(comments);
	}
	public WorkflowComment getComments(int i) {
		return delegate.getComments(i);
	}
	public void setComments(int i, WorkflowComment _value) {
		delegate.setComments(i, _value);
	}
	public Calendar getCreatedAt() {
		return delegate.getCreatedAt();
	}
	public void setCreatedAt(Calendar createdAt) {
		delegate.setCreatedAt(createdAt);
	}
	public Transaction getCreationTransaction() {
		return delegate.getCreationTransaction();
	}
	public void setCreationTransaction(Transaction creationTransaction) {
		delegate.setCreationTransaction(creationTransaction);
	}
	public User getCreator() {
		return delegate.getCreator();
	}
	public void setCreator(User creator) {
		delegate.setCreator(creator);
	}
	public int getId() {
		return delegate.getId();
	}
	public void setId(int id) {
		delegate.setId(id);
	}
	public int getNumRating() {
		return delegate.getNumRating();
	}
	public void setNumRating(int numRating) {
		delegate.setNumRating(numRating);
	}
	public org.geworkbench.components.genspace.server.stubs.Workflow getParent() {
		return delegate.getParent();
	}
	public void setParent(
			org.geworkbench.components.genspace.server.stubs.Workflow parent) {
		delegate.setParent(parent);
	}
	public WorkflowRating[] getRatings() {
		return delegate.getRatings();
	}
	public void setRatings(WorkflowRating[] ratings) {
		delegate.setRatings(ratings);
	}
	public WorkflowRating getRatings(int i) {
		return delegate.getRatings(i);
	}
	public void setRatings(int i, WorkflowRating _value) {
		delegate.setRatings(i, _value);
	}
	public int getSumRating() {
		return delegate.getSumRating();
	}
	public void setSumRating(int sumRating) {
		delegate.setSumRating(sumRating);
	}
	public Integer[] getToolIds() {
		return delegate.getToolIds();
	}
	public void setToolIds(Integer[] toolIds) {
		delegate.setToolIds(toolIds);
	}
	public Integer getToolIds(int i) {
		return delegate.getToolIds(i);
	}
	public void setToolIds(int i, Integer _value) {
		delegate.setToolIds(i, _value);
	}
	public List<WorkflowTool> getTools() {
		return Arrays.asList(delegate.getTools());
	}
	public void setTools(List<WorkflowTool> tools) {
		WorkflowTool[] temp = new WorkflowTool[tools.size()];
		tools.toArray(temp);
		delegate.setTools(temp);
	}
	public WorkflowTool getTools(int i) {
		return delegate.getTools(i);
	}
	public void setTools(int i, WorkflowTool _value) {
		delegate.setTools(i, _value);
	}
	public int getUsageCount() {
		return delegate.getUsageCount();
	}
	public void setUsageCount(int usageCount) {
		delegate.setUsageCount(usageCount);
	}
	public boolean equals(Object obj) {
		return delegate.equals(obj);
	}
	public int hashCode() {
		return delegate.hashCode();
	}

	
	public double getAvgRating() {
		double result = 0;
		if (getRatings().length > 0) {
			for (WorkflowRating r : getRatings()) {
				result += r.getRating();
			}
			result /= getRatings().length;
		}
		return result;
	}
	
	@Override
	public String toString() {
		String r = "";
		for(WorkflowTool wt : getTools())
		{
			r += wt.getTool().getName() + ", ";
		}
		if(r.length() > 2)
			r = r.substring(0,r.length()-2);
		return r;
	}
	public void updateRatingsCache()
	{
		//TODO make this called automatically
		int numRating =0;
		int totalRating =0;
		for(WorkflowRating tr : getRatings())
		{
			numRating++;
			totalRating += tr.getRating();
		}
		setNumRating(numRating);
		setSumRating(totalRating);
	}
	
	public ToolWrapper getLastTool()
	{
		return new ToolWrapper(getTools().get(getTools().size() -1).getTool());
	}
	
	public String getLastToolName()
	{
		return this.getLastTool().getName();
	}
	
	public double getOverallRating() {
		if(getNumRating() == 0)
			return 0;
		else
			return (double) getSumRating() / (double) getNumRating();
	}
	
	public void loadToolsFromCache()
	{
		if(getToolIds() != null && RuntimeEnvironmentSettings.tools != null)
		{
			ArrayList<WorkflowTool> ret = new ArrayList<WorkflowTool>();
			int j = 1;
			for(int i : getToolIds())
			{
				WorkflowTool t = new WorkflowTool();
				t.setOrder(j);
				t.setTool(RuntimeEnvironmentSettings.tools.get(i));
				t.setWorkflow(this.delegate);
				ret.add(t);
				j++;
			}
			setTools(ret);
		}
	}
	
	public org.geworkbench.components.genspace.server.stubs.Workflow getDelegate() {
		return delegate;
	}
}


