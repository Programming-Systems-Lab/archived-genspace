package org.geworkbench.components.genspace.server;
import javax.ejb.Remote;

import org.geworkbench.components.genspace.entity.User;

@Remote
public interface PublicFacadeRemote extends ToolInformationProvider{
	public User register(User u);
}
