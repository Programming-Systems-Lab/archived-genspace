//package genspace.ui;
package genspace;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;

import javax.naming.InitialContext;
import javax.swing.JOptionPane;
import javax.xml.ws.BindingProvider;

import org.apache.log4j.Logger;

import org.geworkbench.components.genspace.server.stubs.FriendFacade;
import org.geworkbench.components.genspace.server.stubs.FriendFacadeService;
import org.geworkbench.components.genspace.server.stubs.NetworkFacade;
import org.geworkbench.components.genspace.server.stubs.NetworkFacadeService;
import org.geworkbench.components.genspace.server.stubs.PublicFacade;
import org.geworkbench.components.genspace.server.stubs.PublicFacadeService;
import org.geworkbench.components.genspace.server.stubs.Tool;
import org.geworkbench.components.genspace.server.stubs.ToolUsageInformation;
import org.geworkbench.components.genspace.server.stubs.ToolUsageInformationService;
import org.geworkbench.components.genspace.server.stubs.UsageInformation;
import org.geworkbench.components.genspace.server.stubs.UsageInformationService;
import org.geworkbench.components.genspace.server.stubs.User;
import org.geworkbench.components.genspace.server.stubs.UserFacade;
import org.geworkbench.components.genspace.server.stubs.UserFacadeService;
import org.geworkbench.components.genspace.server.stubs.UserNetwork;
import org.geworkbench.components.genspace.server.stubs.WorkflowFolder;
import org.geworkbench.components.genspace.server.stubs.WorkflowRepository;
import org.geworkbench.components.genspace.server.stubs.WorkflowRepositoryService;



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
	private static ToolUsageInformation toolUsageFacade;
	
	private static InitialContext ctx;
	private static Object lock = new Object();

	public static void init()
	{
		loadTools();
	}
	
	private static void loadTools()
	{
		if(RuntimeEnvironmentSettings.tools == null)
		{
			RuntimeEnvironmentSettings.tools = new HashMap<Integer, Tool>();
				for(Tool t : getUsageOps().getAllTools())
				{
					RuntimeEnvironmentSettings.tools.put(t.getId(), t);
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
	private static void addCredentials(BindingProvider svc)
	{
		if(username != null && password != null)
		{
			((BindingProvider)svc).getRequestContext().put(BindingProvider.USERNAME_PROPERTY, username);
			((BindingProvider)svc).getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);
		}
	}
	public synchronized static WorkflowRepository getWorkflowOps()
	{
		if(workflowFacade == null)
		{
			loadTools();
			workflowFacade = (new WorkflowRepositoryService()).getWorkflowRepositoryPort();
			addCredentials((BindingProvider) workflowFacade);
		}
		return workflowFacade;
	}
	public synchronized static ToolUsageInformation getUsageOps()
	{
		if(toolUsageFacade == null)
		{
			toolUsageFacade = (new ToolUsageInformationService()).getToolUsageInformationPort();
		}
		return toolUsageFacade;
	}
	public synchronized static UserFacade getUserOps()
	{
		if(userFacade == null)
		{
			userFacade = (new UserFacadeService()).getUserFacadePort();
			addCredentials((BindingProvider) userFacade);
		}
		return userFacade;
	}
	
	public synchronized static PublicFacade getPublicFacade()
	{
		if(publicFacade == null)
		{
			loadTools();
			publicFacade = (new PublicFacadeService()).getPublicFacadePort();
			addCredentials((BindingProvider) publicFacade);
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
			usageFacade = (new UsageInformationService()).getUsageInformationPort();
			addCredentials((BindingProvider) usageFacade);
		}
		return usageFacade;
	}
	public synchronized static FriendFacade getFriendOps()
	{
		if(friendFacade == null)
		{
			friendFacade = (new FriendFacadeService()).getFriendFacadePort();
			addCredentials((BindingProvider) friendFacade);
		}
		return friendFacade;
	}
	public synchronized static NetworkFacade getNetworkOps()
	{
		if(networkFacade == null)
		{
			networkFacade = (new NetworkFacadeService()).getNetworkFacadePort();
			addCredentials((BindingProvider) networkFacade);
		}
		return networkFacade;
	}
	
	
	public GenSpaceServerFactory() {
		super();
	}
	public static WorkflowFolder rootFolder = null;
	
	public static User getUser() {
		return user;
	}
	public static boolean userRegister(User u) {
		try {
			user = getPublicFacade().register(u);
		} catch (Exception e) {
			handleExecutionException(e);
		}

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
				logout();
				GenSpaceServerFactory.username = username;
				GenSpaceServerFactory.password = getEncryptedPassword(password.toCharArray());
				
				user = getUserOps().getMe();
				//TODO
					return true;
			} 
			catch (Exception e) {
				handleException(e);
				return false;
			}
//			return true;
		}
	
	}
	
	protected final static String HEX_DIGITS = "0123456789abcdef";

	public static String getEncryptedPassword(char[] c_password) {
		String plaintext = new String(c_password);
		
		java.security.MessageDigest d =null;
				try {
					d = java.security.MessageDigest.getInstance("SHA-1");
				} catch (NoSuchAlgorithmException e) {
//					GenSpaceServerFactory.logger.error("Error",e);
				}
				d.reset();
				d.update(plaintext.getBytes());
				byte[] hashedBytes =  d.digest();
				StringBuffer sb = new StringBuffer(hashedBytes.length * 2);
		        for (int i = 0; i < hashedBytes.length; i++) {
		             int b = hashedBytes[i] & 0xFF;
		             sb.append(HEX_DIGITS.charAt(b >>> 4)).append(HEX_DIGITS.charAt(b & 0xF));
		        }
		        return sb.toString();	
	}

	public static void updateCachedUser()
	{
		try {
			user = getUserOps().getMe();
		} catch (Exception e) {
			handleException(e);
		}
	}
	public static boolean userUpdate() {

		try {
			getUserOps().updateUser(user);
		} catch (Exception e) {
			handleException(e);
		}
		return true;
	}

	public static List<UserNetwork> getAllNetworks() {
		return getUserOps().getMyNetworks();
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
		rootFolder = null;
		userFacade = null;
		usageFacade = null;
		username = null;
		password = null;
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
