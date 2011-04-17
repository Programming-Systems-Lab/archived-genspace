package org.geworkbench.components.genspace.server;

<<<<<<< HEAD
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
=======
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.TextOutputCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

import org.geworkbench.components.genspace.entity.AnalysisEvent;
import org.geworkbench.components.genspace.entity.Friend;
import org.geworkbench.components.genspace.entity.Network;
import org.geworkbench.components.genspace.entity.Tool;
import org.geworkbench.components.genspace.entity.ToolRating;
import org.geworkbench.components.genspace.entity.Transaction;
import org.geworkbench.components.genspace.entity.User;
import org.geworkbench.components.genspace.entity.UserNetwork;
import org.geworkbench.components.genspace.entity.UserWorkflow;
import org.geworkbench.components.genspace.entity.Workflow;
import org.geworkbench.components.genspace.entity.WorkflowFolder;
import org.geworkbench.components.genspace.entity.WorkflowRating;
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7

/**
 * Session Bean implementation class UserFacade
 */
@Stateful
<<<<<<< HEAD
public class UserFacade extends AbstractFacade<User> implements UserFacadeRemote {
	private User myUser = null;
=======
@RolesAllowed("user")
public class UserFacade extends AbstractFacade<User> implements UserFacadeRemote {
	
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7
	
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

<<<<<<< HEAD
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

=======
	

	@Resource SessionContext ctx;
	@Override
	public User getMe() {
		return fullySerialize(getUser());
	}

	
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7
	@Override
	public void updateUser(User user) {
		this.edit(user);
	}
	
<<<<<<< HEAD
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
=======
	

	@Override
	public User getProfile(String who) {
		User o = findByUserName(who);
		if(o == null)
			return null;
//		if(o.isVisibleTo(getUser()))
		return fullySerialize(o);
	}

	@Override
	public WorkflowFolder getRootFolder() {
		invalidateCache();
		WorkflowFolder ret = getUser().getRootFolder();
		getEntityManager().refresh(ret);
		if(ret == null)
		{
			ret = new WorkflowFolder();
			ret.setOwner(getUser());
			ret.setName("Root");
			getEntityManager().persist(ret);
			User u = getUser();
			u.setRootFolder(ret);
			getEntityManager().merge(u);
			ret = getMe().getRootFolder();
		}
		for(WorkflowFolder r : ret.getChildren())
		{
			for(UserWorkflow uw : r.getWorkflows())
			{
				getEntityManager().refresh(uw.getWorkflow());
				uw.getWorkflow().getTools().size();
				if(uw.getWorkflow().getCreator() != null)
					uw.getWorkflow().getCreator().getUsername();
				uw.getWorkflow().getRatings().size();
				uw.getWorkflow().getCreator();
				uw.getWorkflow().getComments().size();
			}
		}
		for(UserWorkflow uw : ret.getWorkflows())
		{
			System.out.println("Wkflw name: " + uw.getName());
			uw.getWorkflow().getTools().size();
			if(uw.getWorkflow().getCreator() != null)
				uw.getWorkflow().getCreator().getUsername();
			uw.getWorkflow().getRatings().size();
			uw.getWorkflow().getCreator();
			uw.getWorkflow().getComments().size();
		}
		return ret;
	} 
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7
}
