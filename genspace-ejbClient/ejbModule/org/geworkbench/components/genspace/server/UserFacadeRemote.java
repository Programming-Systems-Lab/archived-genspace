package org.geworkbench.components.genspace.server;
import javax.ejb.Remote;

import org.geworkbench.components.genspace.entity.User;
import org.geworkbench.components.genspace.entity.WorkflowFolder;

@Remote
public interface UserFacadeRemote {
	public boolean userExists(String username);

	public User getMe();
	public void updateUser(User user);
	public User getProfile(String who);	
	public WorkflowFolder getRootFolder();
	
	

	
	
}
