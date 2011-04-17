package org.geworkbench.components.genspace.server;
<<<<<<< HEAD
import java.util.List;

import javax.ejb.Remote;

import org.geworkbench.components.genspace.entity.AnalysisEvent;
import org.geworkbench.components.genspace.entity.Tool;
import org.geworkbench.components.genspace.entity.Transaction;
import org.geworkbench.components.genspace.entity.User;
import org.geworkbench.components.genspace.entity.Workflow;
=======
import javax.ejb.Remote;

import org.geworkbench.components.genspace.entity.User;
import org.geworkbench.components.genspace.entity.WorkflowFolder;
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7

@Remote
public interface UserFacadeRemote {
	public boolean userExists(String username);
<<<<<<< HEAD
	public User register(User u);
	public User login(String username, String password);
	public void updateUser(User user);
	
	public List<Tool> getToolsByPopularity();

	public List<Workflow> getWorkflowsByPopularity();

	public List<Tool> getMostPopularWFHeads();

	public Tool getMostPopularNextTool(Tool tool);

	public Tool getMostPopularPreviousTool(Tool tool);
	public List<Tool> getAllTools();
	public List<Workflow> getMostPopularWorkflowStartingWith(Tool tool);
	public List<Workflow> getMostPopularWorkflowIncluding(Tool tool);
	public List<Workflow> getAllWorkflowsIncluding(Tool tool);
	public List<Workflow> getToolSuggestion(Workflow cwf);
	public Transaction sendUsageEvent(AnalysisEvent e);
	public List<User> getFriendRequests();
=======

	public User getMe();
	public void updateUser(User user);
	public User getProfile(String who);	
	public WorkflowFolder getRootFolder();
	
	

	
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7
	
}
