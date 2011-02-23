package org.geworkbench.components.genspace.server;

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
import org.geworkbench.components.genspace.entity.Workflow;
import org.geworkbench.components.genspace.entity.WorkflowRating;

/**
 * Session Bean implementation class UserFacade
 */
@Stateful
@RolesAllowed("user")
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
		return fullySerialize(getUser());
	}

	public User fullySerialize(User u) {
		u.getFolders().size();
		u.getFriends().size();
		u.getIncomingWorkflows().size();
		u.getWorkflowComments().size();
		u.getWorkflows().size();
		u.getNetworks().size();
		return u;
	}

	@Override
	public void updateUser(User user) {
		this.edit(user);
	}
	
	

	@Override
	public User getProfile(String who) {
		// TODO Auto-generated method stub
		return null;
	} 
}
