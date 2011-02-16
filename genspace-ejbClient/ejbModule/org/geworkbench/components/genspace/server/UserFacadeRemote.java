package org.geworkbench.components.genspace.server;
import java.util.Collection;
import java.util.List;

import javax.ejb.Remote;

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

@Remote
public interface UserFacadeRemote {
	public boolean userExists(String username);

	public User getMe();
	public void updateUser(User user);
	public User getProfile(String who);	
	
	
	

	
	
}
