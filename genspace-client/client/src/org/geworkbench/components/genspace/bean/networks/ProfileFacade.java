package org.geworkbench.components.genspace.bean.networks;

import java.util.List;

import org.geworkbench.components.genspace.ui.GenSpaceLogin;
import org.geworkbench.components.genspace.bean.networks.NetworkMessage.Request;

public class ProfileFacade extends Facade {
	public int getNumRequests() {
		System.out.println("Calling out fo rnum requests");
		Profile p = new Profile();
		p.subject = "reqCount";
		p.reqType = NetworkMessage.Request.LIST;
		p = (Profile) sendNetworkMessage(p);
		return Integer.valueOf(p.subject);
	}

	/**
	 * Gets the profile of a user. Do NOT call this from a swing thread!
	 * 
	 * @param otherUser
	 * @return a hashmap, keys are profile field names, values are the values
	 */
	public Profile getUsersProfile(String otherUser) {
		Profile p = new Profile();
		p.subject = otherUser;
		p.reqType = NetworkMessage.Request.FIND;
		// Make the request
		p = (Profile) sendNetworkMessage(p);
		return p;
	}

	public void updateFriendVisibility(String friend, boolean visible) {
		Friend request = new Friend();
		request.subject = friend;
		request.visible = visible;
		request.reqType = Request.UPDATE;
		sendNetworkMessage(request);
	}

	public void updateNetworkVisibility(int net, boolean visible) {
		Network request = new Network();
		request.subject = "" + net;
		request.visible = visible;
		request.reqType = Request.UPDATE;
		sendNetworkMessage(request);
	}

	public List<NetworkMessage> getFriendsThatCanSeeMe() {
		Profile request = new Profile();
		request.reqType = Request.LIST;
		request.details = "FriendsCanSeeMe";
		List<NetworkMessage> response = sendNetworkMessage(request).children;
		return response;
	}

	public List<NetworkMessage> getNetworksThatCanSeeMe() {
		Profile request = new Profile();
		request.reqType = Request.LIST;
		request.details = "NetworksCanSeeMe";
		List<NetworkMessage> response = sendNetworkMessage(request).children;
		return response;
	}

	/**
	 * Updates the current user's profile as requested
	 * 
	 * @param profile
	 */
	public void updateMyProfile(Profile p) {
		p.subject = GenSpaceLogin.genspaceLogin;
		p.reqType = NetworkMessage.Request.UPDATE;
		sendNetworkMessage(p);
	}

	public String getMyUsername() {
		return GenSpaceLogin.genspaceLogin;
	}

	public Profile getMyProfile() {
		return getUsersProfile(GenSpaceLogin.genspaceLogin);
	}

	public static String renderProfileToHTML(Profile p) {
		return renderProfileToHTML(p, true);
	}

	public static String renderProfileToHTML(Profile p, boolean details) {
		// TODO Auto-generated method stub
		String r = "<html><body><b>" + p.profile.get("first_name") + " "
				+ p.profile.get("last_name") + " (" + p.subject + ")</b><br>";
		if (details) {
			r += "<i>"
					+ (p.profile.get("work_title") != null
							&& p.profile.get("work_title") != "" ? p.profile
							.get("work_title") + " at " : "")
					+ p.profile.get("lab_affiliation") + "</i><br><br>";
			r += "<b>Research Interests:</b><br />"
					+ p.profile.get("interests") + "<br><br>";
			r += "<b>Contact information:</b><br /><br>Phone: "
					+ p.profile.get("phone") + "<br>Email: "
					+ p.profile.get("email") + "<br><br>Mailing Address:<br>"
					+ p.profile.get("addr1") + "<br>" + p.profile.get("addr2")
					+ "<br>" + p.profile.get("city") + ", "
					+ p.profile.get("state") + ", " + p.profile.get("zipcode");
		} else {
			r += "This user is not visible to you. Please add them as a friend or join one of their networks to see their profile.";
		}
		r += "</body>";
		r += "</html>";
		return r;
	}
}