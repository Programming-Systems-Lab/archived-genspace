//package genspace.ui;
package org.geworkbench.components.genspace;


import java.util.ArrayList;
import java.util.List;


import org.geworkbench.components.genspace.entity.User;
import org.geworkbench.components.genspace.entity.UserNetwork;
import org.geworkbench.components.genspace.server.UserFacadeRemote;
import org.geworkbench.components.genspace.ui.GenSpaceLogin;
import org.geworkbench.engine.properties.PropertiesManager;

public class LoginManager {

	private static final String PROPERTY_GENSPACE_LOGIN_USER = "LoginUserId";

	private static User user;

	private static UserFacadeRemote facade;

	public synchronized static UserFacadeRemote getFacade()
	{
		if(facade == null)
			facade = (UserFacadeRemote) GenSpace.getRemote("UserFacade");
		return facade;
	}

	public LoginManager() {
		super();
	}

	
	public static User getUser() {
		return user;
	}
	public static boolean userRegister(User u) {
		user = getFacade().register(u);
		if(user != null)
			return true;
		return false;
	}

	public static boolean userLogin(String username, String password) {
		
		user = getFacade().login(username,password);
		boolean ret = false;

		if (user != null) {
			GenSpace.getInstance().getWorkflowRepository().updateUser(user);

				try {
					PropertiesManager properties = PropertiesManager
							.getInstance();
					properties.setProperty(GenSpaceLogin.class,
							PROPERTY_GENSPACE_LOGIN_USER, user.getUsername()); //todo: probably unused
				} catch (Exception ex) {
				}
				ret = true;
			} else {
				ret = false;
			}
		return ret;
	
	}

	public static boolean userUpdate() {

		getFacade().updateUser(user);
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

	
}
