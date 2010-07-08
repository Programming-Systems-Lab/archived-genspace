package org.geworkbench.components.genspace.bean.networks;

import java.util.HashMap;

import org.geworkbench.components.genspace.genspaceLogin;
import org.geworkbench.components.genspace.bean.networks.NetworkMessage.Request;

public class ProfileFacade extends Facade {
	
	/**
	 * Gets the profile of a user. Do NOT call this from a swing thread!
	 * @param otherUser
	 * @return a hashmap, keys are profile field names, values are the values
	 */
	public HashMap<String, String> getUsersProfile(String otherUser)
	{
		Profile p = new Profile();
		p.subject = otherUser;
		p.reqType = NetworkMessage.Request.FIND;
		//Make the request
		p = (Profile) sendNetworkMessage(p);
		return p.profile;
	}
	
	/**
	 * Updates the current user's profile as requested
	 * @param profile
	 */
	public void updateMyProfile(HashMap<String, String> profile)
	{
		Profile p = new Profile();
		p.subject=genspaceLogin.genspaceLogin;
		p.reqType = NetworkMessage.Request.UPDATE;
		p.profile = profile;
		sendNetworkMessage(p);
	}	

}