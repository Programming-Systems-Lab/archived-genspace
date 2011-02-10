package org.geworkbench.components.genspace.server;

import javax.ejb.Remote;

@Remote
public interface FriendFacadeRemote {
	void createNewUser();
}
