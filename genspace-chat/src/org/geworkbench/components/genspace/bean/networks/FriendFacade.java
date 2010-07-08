package org.geworkbench.components.genspace.bean.networks;

import java.util.List;

import org.geworkbench.components.genspace.bean.networks.NetworkMessage.Request;

public class FriendFacade extends Facade{
	
	/**
	 * Get a list of friends of this user
	 * @return
	 */
	public List<Friend> getFriends()
	{
		Friend request = new Friend();
		request.reqType = Request.LIST;
		List<Friend> response = (List<Friend>) sendNetworkMessage(request);
		return response;
	}
	
	/**
	 * Add a user as a friend
	 * @param user
	 * @return List of friends after the action
	 */
	public List<Friend> addFriend(String user)
	{
		Friend request = new Friend();
		request.reqType = Request.CREATE;
		request.subject = user;
		List<Friend> response = (List<Friend>) sendNetworkMessage(request);
		return response;
	}
	
	public List<Friend> deleteFriend(String user)
	{
		Friend request = new Friend();
		request.reqType = Request.DELETE;
		request.subject = user;
		List<Friend> response = (List<Friend>) sendNetworkMessage(request);
		return response;
	}
}
