package org.geworkbench.components.genspace.server;

import java.util.ArrayList;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;

import org.geworkbench.components.genspace.entity.User;
import org.geworkbench.components.genspace.entity.UserWorkflow;
import org.geworkbench.components.genspace.entity.WorkflowFolder;

/**
 * Session Bean implementation class UserFacade
 */
@Stateless
@RolesAllowed("user")
@WebService
public class UserFacade extends AbstractFacade<User> implements UserFacadeRemote {
	
	
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

	

	@Resource SessionContext ctx;
	@Override
	public User getMe() {
		return getUser();
	}

	
	@Override
	public void updateUser(User user) {
		this.edit(user);
	}
	
	

	@Override
	public User getProfile(String who) {
		User o = findByUserName(who);
		if(o == null)
			return null;
//		if(o.isVisibleTo(getUser()))
		return o.loadVisibility(getUser());
	}

	@Override
	public WorkflowFolder getRootFolder() {
		invalidateCache();
		WorkflowFolder ret = getUser().getRootFolder();

		if(ret == null)
		{
			ret = new WorkflowFolder();
			ret.setOwner(getUser());
			ret.setName("Repository");
			getEntityManager().persist(ret);
			User u = getUser();
			u.setRootFolder(ret);
			getEntityManager().merge(u);
			ret = getMe().getRootFolder();
		}
		else
			getEntityManager().refresh(ret);
		WorkflowFolder retz = new WorkflowFolder();
		retz.setId(ret.getId());
		retz.setName(ret.getName());
		retz.setOwner(ret.getOwner());
		retz.setWorkflows(new ArrayList<UserWorkflow>());
//		for(WorkflowFolder r : ret.getChildren())
//		{
//			for(UserWorkflow uw : r.getWorkflows())
//			{
//				getEntityManager().refresh(uw.getWorkflow());
//				uw.getWorkflow().getTools().size();
//				if(uw.getWorkflow().getCreator() != null)
//					uw.getWorkflow().getCreator().getUsername();
//				uw.getWorkflow().getRatings().size();
//				uw.getWorkflow().getCreator();
//				uw.getWorkflow().getComments().size();
//			}
//		}
		for(UserWorkflow uw : ret.getWorkflows())
		{
			getEntityManager().refresh(uw);
			getEntityManager().refresh(uw.getWorkflow());
			UserWorkflow uww = new UserWorkflow();
			uww.setWorkflow(uw.getWorkflow().slimDown());
			uww.getWorkflow().getCreator();
			uww.getWorkflow().getRatings().size();
			uww.getWorkflow().getComments().size();
			uww.getWorkflow().getId();
			uww.setName(uw.getName());
			uww.setId(uw.getId());
			retz.getWorkflows().add(uww);
		}
		getEntityManager().clear();
		return retz;
	}

	@WebMethod(exclude=true)
	@Override
	public void updateUser(byte[] userObj) {
		updateUser((User) AbstractFacade.readObject(userObj));
	}

	@Override
	public byte[] getRootFolderBytes() {
		return AbstractFacade.writeObject(getRootFolder());
	} 
}
