//package genspace.ui;
package org.geworkbench.components.genspace;


import java.util.List;

import javax.naming.InitialContext;


import org.geworkbench.components.genspace.chat.ChatReceiver;
import org.geworkbench.components.genspace.entity.Friend;
import org.geworkbench.components.genspace.entity.User;
import org.geworkbench.components.genspace.entity.UserNetwork;
import org.geworkbench.components.genspace.server.FriendFacadeRemote;
import org.geworkbench.components.genspace.server.NetworkFacadeRemote;
import org.geworkbench.components.genspace.server.PublicFacadeRemote;
import org.geworkbench.components.genspace.server.ToolInformationProvider;
import org.geworkbench.components.genspace.server.UsageInformationRemote;
import org.geworkbench.components.genspace.server.UserFacadeRemote;
import org.geworkbench.components.genspace.ui.GenSpaceLogin;
import org.geworkbench.engine.properties.PropertiesManager;
import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.XMPPException;

import com.sun.appserv.security.ProgrammaticLogin;

public class LoginManager {

	private static final String PROPERTY_GENSPACE_LOGIN_USER = "LoginUserId";

	private static User user;

	private static UserFacadeRemote userFacade;
	private static UsageInformationRemote usageFacade;
	private static FriendFacadeRemote friendFacade;
	private static NetworkFacadeRemote networkFacade;
	private static PublicFacadeRemote publicFacade;
	
	public synchronized static UserFacadeRemote getUserOps()
	{
		if(userFacade == null)
			userFacade = (UserFacadeRemote) GenSpace.getRemote("UserFacade");
		return userFacade;
	}
	
	public synchronized static PublicFacadeRemote getPublicFacade()
	{
		if(publicFacade == null)
			publicFacade = (PublicFacadeRemote) GenSpace.getRemote("PublicFacade");
		return publicFacade;
	}
	
	public synchronized static UsageInformationRemote getPrivUsageFacade()
	{
		if(user == null)
			return null;
		if(usageFacade == null)
			usageFacade = (UsageInformationRemote) GenSpace.getRemote("UsageInformation");
		return usageFacade;
	}
	public synchronized static ToolInformationProvider getUsageOps()
	{
		if(user != null)
			return getPrivUsageFacade();
		else
			return getPublicFacade();
	}

	public synchronized static FriendFacadeRemote getFriendOps()
	{
		if(friendFacade == null)
			friendFacade = (FriendFacadeRemote) GenSpace.getRemote("FriendFacade");
		return friendFacade;
	}
	public synchronized static NetworkFacadeRemote getNetworkOps()
	{
		if(networkFacade == null)
			networkFacade = (NetworkFacadeRemote) GenSpace.getRemote("NetworkFacade");
		return networkFacade;
	}
	
	
	public LoginManager() {
		super();
	}

	
	public static User getUser() {
		return user;
	}
	public static boolean userRegister(User u) {
		user = getPublicFacade().register(u);
		if(user != null)
			return true;
		return false;
	}

	public static boolean userLogin(String username, String password) {
		
		System.setProperty("java.security.auth.login.config", "login.conf");
		try {
			ProgrammaticLogin pm = new ProgrammaticLogin();
			pm.login(username, password,"GELogin",true);
			InitialContext ctx = new InitialContext();
			ctx.lookup("org.geworkbench.components.genspace.server.UserFacadeRemote");
			user = getUserOps().getMe();
		} catch (Exception e) {
			return false;
		}
		

			GenSpace.getInstance().getWorkflowRepository().updateUser(user);

				try {
					PropertiesManager properties = PropertiesManager
							.getInstance();
					properties.setProperty(GenSpaceLogin.class,
							PROPERTY_GENSPACE_LOGIN_USER, user.getUsername()); //todo: probably unused
				} catch (Exception ex) {
				}

		return true;
	
	}

	public static boolean userUpdate() {

		getUserOps().updateUser(user);
		return true;
	}

	public static List<UserNetwork> getAllNetworks() {
		return user.getNetworks();
	}

	public static String getUsername() {
		if(user == null)
			return null;
		return user.getUsername();
	}

	public static boolean isLoggedIn() {
		return user != null;
	}

	public static void logout() {
		user = null;
	}

	/**
	 * Returns if the currently logged in user may view the profile of the specified user
	 * @param user2
	 * @return
	 */
	public static boolean isVisible(User user2) {
		Friend f = user2.isFriendsWith(getUser());
		if(f != null && f.isVisible())
		{
			return true;
		}
		//Check the networks
		for(UserNetwork u1 : user2.getNetworks())
		{
			if(u1.isVisible())
				for(UserNetwork u2 : getUser().getNetworks())
				{
					if(u2.getNetwork().equals(u1.getNetwork()))
						return true;
				}
		}
		return false;
	}

	public static void removeFriend(User u) {
		Roster r = ChatReceiver.connection.getRoster();
		RosterEntry e = r.getEntry(u.getUsername()
				+ "@boris.cs.columbia.edu");
		if (e != null)
			try {
				ChatReceiver.connection.getRoster()
						.removeEntry(e);
			} catch (XMPPException e1) {
				GenSpace.logger.error("Unable to remove friend from roster",e1);
			}
	}

	public static void addFriend(User u) {
		try {
			ChatReceiver.connection.getRoster()
			.createEntry(
					u.getUsername()
							+ "@boris.cs.columbia.edu",
					u.getFirstName()
							+ " "
							+ u.getLastName(),
					null);
		} catch (XMPPException e) {
			GenSpace.logger.error("Unable to add friend to roster",e);
		}
		
	}

	
}
