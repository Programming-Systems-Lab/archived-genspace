package org.geworkbench.components.genspace.server;

import java.util.List;

import javax.ejb.Remote;

import org.geworkbench.components.genspace.entity.Friend;
import org.geworkbench.components.genspace.entity.User;

@Remote
public interface FriendFacadeRemote {
	public List<User> getFriendRequests();
	public void addFriend(User selectedValue);
	public void rejectFriend(User selectedValue);

	public void updateFriendVisibility(Friend friend, Boolean boolean1);
	public List<User> getFriendsProfiles();
	public void removeFriend(User u);
}
