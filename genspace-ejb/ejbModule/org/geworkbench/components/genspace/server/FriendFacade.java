package org.geworkbench.components.genspace.server;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;

import org.geworkbench.components.genspace.entity.Friend;
import org.geworkbench.components.genspace.entity.User;

/**
 * Session Bean implementation class FriendFacade
 */
@Stateless
@RolesAllowed("user")
public class FriendFacade extends AbstractFacade<Friend> implements FriendFacadeRemote  {
    /**
     * Default constructor. 
     */
    public FriendFacade() {
        super(Friend.class);
    }

	@Override
	public List<User> getFriendRequests() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void addFriend(User selectedValue) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void rejectFriend(User selectedValue) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void updateFriendVisibility(Friend friend, Boolean boolean1) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<User> getFriendsProfiles() {
		// TODO Auto-generated method stub
		return null;
	}

}
