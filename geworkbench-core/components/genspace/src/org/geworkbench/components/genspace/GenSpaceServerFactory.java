//package genspace.ui;
package org.geworkbench.components.genspace;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.swing.JOptionPane;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Stub;
import org.apache.log4j.Logger;
import org.geworkbench.components.genspace.server.stubs.FriendFacadeServiceLocator;
import org.geworkbench.components.genspace.server.stubs.NetworkFacadeServiceLocator;
import org.geworkbench.components.genspace.server.stubs.PublicFacadeServiceLocator;
import org.geworkbench.components.genspace.server.stubs.Tool;
import org.geworkbench.components.genspace.server.stubs.ToolInformationProvider;
import org.geworkbench.components.genspace.server.stubs.UsageInformationServiceLocator;
import org.geworkbench.components.genspace.server.stubs.User;
import org.geworkbench.components.genspace.server.stubs.UserFacadeServiceLocator;
import org.geworkbench.components.genspace.server.stubs.UserNetwork;
import org.geworkbench.components.genspace.server.stubs.FriendFacade;
import org.geworkbench.components.genspace.server.stubs.NetworkFacade;
import org.geworkbench.components.genspace.server.stubs.PublicFacade;
import org.geworkbench.components.genspace.server.stubs.UsageInformation;
import org.geworkbench.components.genspace.server.stubs.UserFacade;
import org.geworkbench.components.genspace.server.stubs.WorkflowRepository;
import org.geworkbench.components.genspace.server.stubs.WorkflowRepositoryServiceLocator;
import org.geworkbench.components.genspace.server.wrapper.UserWrapper;



public class GenSpaceServerFactory {

	private static User user;
	private static String username = null;
	private static String password = null;
	
	public static Logger logger = Logger.getLogger(GenSpaceServerFactory.class);
	private static UserFacade userFacade;
	private static UsageInformation usageFacade;
	private static FriendFacade friendFacade;
	private static NetworkFacade networkFacade;
	private static PublicFacade publicFacade;
	private static WorkflowRepository workflowFacade;
	private static InitialContext ctx;
	private static Object lock = new Object();
	
	private static void loadTools()
	{
		if(RuntimeEnvironmentSettings.tools == null)
		{
			RuntimeEnvironmentSettings.tools = new HashMap<Integer, Tool>();
			try {
				for(Tool t : getPublicFacade().getAllTools())
				{
					RuntimeEnvironmentSettings.tools.put(t.getId(), t);
				}
			} catch (RemoteException e) {
				handleException(e);
			}
		}
	}
	public static void handleExecutionException(Exception e)
	{
		e.printStackTrace();
		GenSpaceServerFactory.clearCache();
		JOptionPane.showMessageDialog(null, "There was an error communicating with the genSpace server.\n Please try your request again", "Error communicating with server", JOptionPane.ERROR_MESSAGE);
	}
	public static void clearCache()
	{
		userFacade = null;
		usageFacade = null;
		friendFacade = null;
		networkFacade = null;
		publicFacade = null;
		workflowFacade = null;	
	}
	public static void handleException(Exception e)
	{
		e.printStackTrace();
	}
	public synchronized static WorkflowRepository getWorkflowOps()
	{
		if(workflowFacade == null)
		{
			loadTools();
			try {
				workflowFacade = (new WorkflowRepositoryServiceLocator()).getWorkflowRepositoryPort();
			} catch (ServiceException e) {
				handleException(e);
			}
			if(username != null && password != null)
			{
				((Stub) workflowFacade).setUsername(username);
				((Stub) workflowFacade).setPassword(password);
			}
		}
		return workflowFacade;
	}
	public synchronized static UserFacade getUserOps()
	{
		if(userFacade == null)
		{
			try {
				userFacade = (new UserFacadeServiceLocator()).getUserFacadePort();
			} catch (ServiceException e) {
				handleException(e);
			}
			if(username != null && password != null)
			{
				((Stub) userFacade).setUsername(username);
				((Stub) userFacade).setPassword(password);
			}
		}
		return userFacade;
	}
	
	public synchronized static PublicFacade getPublicFacade()
	{
		if(publicFacade == null)
		{
			loadTools();
			try {
				publicFacade = (new PublicFacadeServiceLocator()).getPublicFacadePort();
			} catch (ServiceException e) {
				handleException(e);
			}
			if(username != null && password != null)
			{
				((Stub) publicFacade).setUsername(username);
				((Stub) publicFacade).setPassword(password);
			}
		}
		return publicFacade;
	}
	
	public synchronized static UsageInformation getPrivUsageFacade()
	{
		if(user == null)
			return null;
		if(usageFacade == null)
		{
			loadTools();
			try {
				usageFacade = (new UsageInformationServiceLocator()).getUsageInformationPort();
			} catch (ServiceException e) {
				handleException(e);
			}
			if(username != null && password != null)
			{
				((Stub) usageFacade).setUsername(username);
				((Stub) usageFacade).setPassword(password);
			}
		}
		return usageFacade;
	}
	public synchronized static ToolInformationProvider getUsageOps()
	{
		if(user != null)
			return getPrivUsageFacade();
		else
			return getPublicFacade();
	}

	public synchronized static FriendFacade getFriendOps()
	{
		if(friendFacade == null)
		{
			try {
				friendFacade = (new FriendFacadeServiceLocator()).getFriendFacadePort();
			} catch (ServiceException e) {
				handleException(e);
			}
			if(username != null && password != null)
			{
				((Stub) friendFacade).setUsername(username);
				((Stub) friendFacade).setPassword(password);
			}
		}
		return friendFacade;
	}
	public synchronized static NetworkFacade getNetworkOps()
	{
		if(networkFacade == null)
		{
			try {
				networkFacade = (new NetworkFacadeServiceLocator()).getNetworkFacadePort();
			} catch (ServiceException e) {
				handleException(e);
			}
			if(username != null && password != null)
			{
				((Stub) networkFacade).setUsername(username);
				((Stub) networkFacade).setPassword(password);
			}
		}
		return networkFacade;
	}
	
	
	public GenSpaceServerFactory() {
		super();
	}

	public static UserWrapper getWrappedUser() {
		return new UserWrapper(user);
	}
	public static User getUser() {
		return user;
	}
	public static boolean userRegister(User u) {
		System.out.println("Sending up user of size " + getObjectSize(u));
		try {
			user = getPublicFacade().register(u);
		} catch (RemoteException e) {
			handleExecutionException(e);
		}
		System.out.println("Sent info");
		if(user != null)
			return true;
		return false;
	}
	public static String getObjectSize(Serializable s)
	{
		 ByteArrayOutputStream baos = new ByteArrayOutputStream();
		 ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(baos);
			oos.writeObject(s);
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}		 
		 return " " + ((double) baos.size())/(1024) + " KB";
	}
	@SuppressWarnings("deprecation")
	public static boolean userLogin(String username, String password) {
		synchronized(lock)
		{
			System.setProperty("java.security.auth.login.config", "components/genspace/classes/org/geworkbench/components/genspace/login.conf");
			try {
//				if(pm.login(username, password,"GELogin",true))
//					user = getUserOps().getMe();
//				else
				//TODO
					return false;
			} 
			catch (SecurityException e)
			{
				return false;
			}
			catch (Exception e) {
				return false;
			}
//			return true;
		}
	
	}
	public static void updateCachedUser()
	{
		try {
			user = getUserOps().getMe();
		} catch (RemoteException e) {
			handleException(e);
		}
	}
	public static boolean userUpdate() {

		try {
			getUserOps().updateUser(user);
		} catch (RemoteException e) {
			handleException(e);
		}
		return true;
	}

	public static List<UserNetwork> getAllNetworks() {
		return Arrays.asList(user.getNetworks());
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
		try {
			//TODO
//			pm.logout(true);
		} catch (Exception e) {
		}
		userFacade = null;
		usageFacade = null;
		friendFacade = null;
		networkFacade = null;
		publicFacade = null;
		workflowFacade = null;
		user = null;
	}

	/**
	 * Returns if the currently logged in user may view the profile of the specified user
	 * @param user2
	 * @return
	 */
	public static boolean isVisible(User user2) {
		return user2.isVisible();
	}

	
}
