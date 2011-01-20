package org.geworkbench.components.genspace.bean.networks;

import java.util.List;

import org.geworkbench.components.genspace.RuntimeEnvironmentSettings;
import org.geworkbench.components.genspace.bean.networks.NetworkMessage.Request;
import org.geworkbench.components.genspace.chat.ChatReceiver;
import org.geworkbench.components.genspace.ui.GenSpaceLogin;
import org.jivesoftware.smack.XMPPException;

public class FriendFacade extends Facade {

	/**
	 * Get a list of friends of this user
	 * 
	 * @return
	 */
	public List<NetworkMessage> getFriends() {
		Friend request = new Friend();
		request.reqType = Request.LIST;
		List<NetworkMessage> response = sendNetworkMessage(request).children;
		return response;
	}

	/**
	 * Add a user as a friend
	 * 
	 * @param user
	 * @return List of friends after the action
	 */
	public List<NetworkMessage> addFriend(String user) {
		Friend request = new Friend();
		request.reqType = Request.CREATE;
		request.subject = user;
		System.out.println("Adding friend " + user);
		List<NetworkMessage> response = sendNetworkMessage(request).children;
		// Also do the XMPP roster change

		Profile p = (new ProfileFacade()).getUsersProfile(user);
		try {
			ChatReceiver.connection.getRoster().createEntry(
					p.profile.get("username") + "@"
							+ RuntimeEnvironmentSettings.XMPP_HOST,
					p.profile.get("first_name") + " "
							+ p.profile.get("last_name"), null);
			// ChatReceiver.manager.
			// System.out.println("Added friend <"+p.profile.get("username")+"@"+RuntimeEnvironmentSettings.XMPP_HOST+"> for "
			// + GenSpaceLogin.genspaceLogin);
		} catch (XMPPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}

	public List<NetworkMessage> deleteFriend(String user) {
		Friend request = new Friend();
		request.reqType = Request.DELETE;
		request.subject = user;
		List<NetworkMessage> response = sendNetworkMessage(request).children;
		return response;
	}

	public List<NetworkMessage> findAllUsers() {
		Friend request = new Friend();
		request.reqType = Request.FIND;
		request.subject = null;
		List<NetworkMessage> response = sendNetworkMessage(request).children;
		return response;
	}

	public List<NetworkMessage> getPendingRequests() {
		Friend request = new Friend();
		request.reqType = Request.FIND;
		request.subject = GenSpaceLogin.genspaceLogin;
		List<NetworkMessage> response = sendNetworkMessage(request).children;
		return response;
	}
}
