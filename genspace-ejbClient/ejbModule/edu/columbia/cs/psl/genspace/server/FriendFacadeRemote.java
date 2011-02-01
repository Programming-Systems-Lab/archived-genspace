package edu.columbia.cs.psl.genspace.server;

import javax.ejb.Remote;

@Remote
public interface FriendFacadeRemote {
	void createNewUser();
}
