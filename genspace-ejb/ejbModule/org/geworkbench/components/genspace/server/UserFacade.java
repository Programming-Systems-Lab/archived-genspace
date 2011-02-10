package org.geworkbench.components.genspace.server;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.geworkbench.components.genspace.entity.AnalysisEvent;
import org.geworkbench.components.genspace.entity.Tool;
import org.geworkbench.components.genspace.entity.Transaction;
import org.geworkbench.components.genspace.entity.User;
import org.geworkbench.components.genspace.entity.Workflow;

/**
 * Session Bean implementation class UserFacade
 */
@Stateful
public class UserFacade extends AbstractFacade<User> implements UserFacadeRemote {
	private User myUser = null;
	
    /**
     * Default constructor. 
     */
    public UserFacade() {
    	super(User.class);
    }

	@Override
	public boolean userExists(String username) {
		return findByUserName(username) != null;
	}

	@Override
	public User register(User u) {
		if(userExists(u.getUsername()))
			return null;
		if(u.getId() > 0)
			return null;
		create(u);
		myUser = findByUserName(u.getUsername());
		myUser = fullySerialize(myUser);
		return myUser;
	}

	@Override
	public User login(String username, String password) {
		User u = findByUserName(username);
		if(u != null && u.passwordMatches(password))
		{
			myUser = fullySerialize(u);
			return (myUser);
		}
		return null;
	}

	public User fullySerialize(User u) {
		u.getFolders().size();
		u.getFriends().size();
		u.getIncomingWorkflows().size();
		u.getWorkflowComments().size();
		u.getWorkflows().size();
		u.getNetworks().size();
		u.getMyWorkflows().size();
		return u;
	}

	@Override
	public void updateUser(User user) {
		this.edit(user);
	}
	
	private User findByUserName(String username)
	{
		Query q = getEntityManager().createQuery("select object(c) from User as c where c.username=:user");
		q.setParameter("user", username);
		User r = null;
		try
		{
		r = (User) q.getSingleResult();
		}
		catch(NoResultException e)
		{
			
		}
		return r;
	}
	@EJB
	ToolInformationLocal ti;
	
	@Override
	public List<Tool> getToolsByPopularity() {
		return ti.getToolsByPopularity(myUser);
	}

	@Override
	public List<Workflow> getWorkflowsByPopularity() {
		return ti.getWorkflowsByPopularity(myUser);
	}

	@Override
	public List<Tool> getMostPopularWFHeads() {
		return ti.getMostPopularWFHeads(myUser);
	}

	@Override
	public Tool getMostPopularNextTool(Tool tool) {
		return ti.getMostPopularNextTool(myUser,tool);
	}

	@Override
	public Tool getMostPopularPreviousTool(Tool tool) {
		return ti.getMostPopularPreviousTool(myUser,tool);
	}
	@Override
	public List<Tool> getAllTools() {
		
		return ti.getAllTools();
	}

	@Override
	public List<Workflow> getAllWorkflowsIncluding(Tool tool) {
		return ti.getAllWorkflowsIncluding(myUser, tool);
	}

	@Override
	public List<Workflow> getMostPopularWorkflowStartingWith(Tool tool) {
		return ti.getMostPopularWorkflowStartingWith(myUser, tool);
	}

	@Override
	public List<Workflow> getMostPopularWorkflowIncluding(Tool tool) {
		return ti.getMostPopularWorkflowIncluding(myUser, tool);

	}

	@Override
	public List<Workflow> getToolSuggestion(Workflow cwf) {
		return ti.getToolSuggestion(myUser,cwf);
	}

	@Override
	public Transaction sendUsageEvent(AnalysisEvent e) {
		return ti.logUsageEvent(myUser,e);
	}

	@Override
	public List<User> getFriendRequests() {
		// TODO Auto-generated method stub
		return null;
	}
}
