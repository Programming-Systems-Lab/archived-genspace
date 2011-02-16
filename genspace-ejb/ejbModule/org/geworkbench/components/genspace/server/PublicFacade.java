package org.geworkbench.components.genspace.server;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.geworkbench.components.genspace.entity.AnalysisEvent;
import org.geworkbench.components.genspace.entity.Tool;
import org.geworkbench.components.genspace.entity.Transaction;
import org.geworkbench.components.genspace.entity.User;
import org.geworkbench.components.genspace.entity.Workflow;

/**
 * Session Bean implementation class PublicFacade
 */
@Stateless
public class PublicFacade extends AbstractFacade<Tool> implements PublicFacadeRemote {

    /**
     * Default constructor. 
     */
    public PublicFacade() {
        super(Tool.class);
    }

    public boolean userExists(String username) {
		return findByUserName(username) != null;
	}

    @Override
	public User register(User u) {
		if(userExists(u.getUsername()))
			return null;
		if(u.getId() > 0)
			return null;
		getEntityManager().persist(u);
		User myUser = findByUserName(u.getUsername());
		myUser = fullySerialize(myUser);
		return myUser;
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
	public List<Tool> getToolsByPopularity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Workflow> getWorkflowsByPopularity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Tool> getMostPopularWFHeads() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tool getMostPopularNextTool(Tool tool) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tool getMostPopularPreviousTool(Tool tool) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Tool> getAllTools() {
			return findAll();
	}

	@Override
	public List<Workflow> getMostPopularWorkflowStartingWith(Tool tool) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Workflow> getMostPopularWorkflowIncluding(Tool tool) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Workflow> getAllWorkflowsIncluding(Tool tool) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Workflow> getToolSuggestion(Workflow cwf) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transaction sendUsageEvent(AnalysisEvent e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getExpertUserFor(Tool tn) {
		// TODO Auto-generated method stub
		return null;
	}
}
