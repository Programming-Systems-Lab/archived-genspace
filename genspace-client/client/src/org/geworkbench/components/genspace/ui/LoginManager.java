//package genspace.ui;
package org.geworkbench.components.genspace.ui;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

import org.geworkbench.components.genspace.GenSpace;
import org.geworkbench.components.genspace.GenSpaceSSLSocketFactory;
import org.geworkbench.components.genspace.RuntimeEnvironmentSettings;
import org.geworkbench.components.genspace.ServerRequest;
import org.geworkbench.components.genspace.bean.DataVisibilityBean;
import org.geworkbench.components.genspace.bean.NetworkVisibilityBean;
import org.geworkbench.components.genspace.bean.RegisterBean;
import org.geworkbench.components.genspace.bean.SecurityMessageBean;
import org.geworkbench.components.genspace.bean.User;
import org.geworkbench.components.genspace.bean.UserSession;
import org.geworkbench.engine.properties.PropertiesManager;

public class LoginManager {

	private static final String PROPERTY_GENSPACE_LOGIN_USER = "LoginUserId";
	RegisterBean bean = null;
	DataVisibilityBean dvb = null;
	NetworkVisibilityBean nvb = null;
	User user;

	public static String loggedUser = null;

	public LoginManager() {
		super();
	}

	public LoginManager(String userId, char[] password) {
		bean.setUName(userId);
		bean.setPassword(password);
	}

	public LoginManager(RegisterBean regBean) {
		bean = regBean;
	}

	public LoginManager(DataVisibilityBean dvbBean) {
		dvb = dvbBean;
	}

	public LoginManager(NetworkVisibilityBean nvbBean) {
		nvb = nvbBean;
	}

	public LoginManager(String message, String userId, char[] password,
			char[] confirmPassword, String firstName, String lastName,
			String labAffiliation, String eMail, String phoneNumber,
			String address1, String address2, String city, String state,
			String zipCode) {

		bean.setMessage(message);

		bean.setUName(userId);

		bean.setPassword(password);
		bean.setConfirmPasswd(confirmPassword);

		bean.setFName(firstName);
		bean.setLName(lastName);

		bean.setLabAffiliation(labAffiliation);

		bean.setEmail(eMail);

		bean.setPhoneNumber(phoneNumber);

		bean.setAddr1(address1);
		bean.setAddr2(address2);
		bean.setCity(city);
		bean.setState(state);
		bean.setZipcode(zipCode);
	}

	private Socket getSocket() {
		try {
			Socket s = (SSLSocket) GenSpaceSSLSocketFactory.createSocket(
					RuntimeEnvironmentSettings.SECURITY_SERVER.getHost(),
					RuntimeEnvironmentSettings.SECURITY_SERVER.getPort());
			OutputStream pw = s.getOutputStream();

			return s;
		} catch (Exception ex) {
			ex.printStackTrace();

			return null;
		}
	}

	public boolean userDupCheck() {
		bean.setMessage("Duplication");

		try {
			Socket s = getSocket();

			ObjectOutputStream os = new ObjectOutputStream(s.getOutputStream());
			os.writeObject(bean);
			os.flush();
			boolean ret = false;
			BufferedReader socketBr = new BufferedReader(new InputStreamReader(
					s.getInputStream()));
			if (socketBr.readLine().equals("true")) {
				ret = true;
			} else {
				ret = false;
			}

			socketBr.close();
			os.close();
			s.close();

			return ret;
		} catch (Exception ex) {
			ex.printStackTrace();

			// in case there's an exception such as losing network connections,
			// this used to give a false error message that the user id is
			// duplicated.
			// assume that the user is not a duplicate for now, and try
			// registering
			// changed the return value to true
			return true;
		}

	}

	public boolean userRegister() {
		bean.setMessage("Register");

		try {
			byte[] buf;
			Socket s = getSocket();

			ObjectOutputStream os = new ObjectOutputStream(s.getOutputStream());
			os.writeObject(bean);
			os.flush();
			boolean ret = false;
			BufferedReader socketBr = new BufferedReader(new InputStreamReader(
					s.getInputStream()));
			if (socketBr.readLine().equals("true")) {
				ret = true;
			} else {
				ret = false;
			}

			os.close();
			socketBr.close();
			s.close();

			return ret;
		} catch (Exception ex) {
			ex.printStackTrace();

			return false;
		}
	}

	public boolean userLogin() {
		bean.setMessage("Login"); // This is set to Login for logging in.
									// Appropriately set in Jpanel for
									// registration/login

		try {
		
			Socket s = getSocket();
			
			ObjectOutputStream os = new ObjectOutputStream(s.getOutputStream());
			os.writeObject(bean);
			os.flush();
			
//			System.out.println("Wrote");
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			user = (User) ois.readObject();
//			System.out.println("Read");
			// System.out.println(user);
			boolean ret = false;
			// BufferedReader socketBr = new BufferedReader(new
			// InputStreamReader(s.getInputStream()));
			// if (socketBr.readLine().equals("true")) {
			if (user != null) {
				loggedUser = bean.getUsername();
				UserSession.setUser(user);
				GenSpace.getInstance().getWorkflowRepository().updateUser(user);

				try {
					PropertiesManager properties = PropertiesManager
							.getInstance();
					properties.setProperty(GenSpaceLogin.class,
							PROPERTY_GENSPACE_LOGIN_USER, bean.getUsername());
					// Message msg = new Message();
					// msg.startMessage();
				} catch (Exception ex) {
				}
				ret = true;
			} else {
				ret = false;
			}

			ois.close();
			os.close();
			// socketBr.close();
			s.close();

			return ret;
		} catch (Exception ex) {
			ex.printStackTrace();

			return false;
		}
	}

	public RegisterBean getUserInfo() {
		bean.setMessage("GetUserInfo");
		try {

			Socket s = getSocket();
			ObjectOutputStream os = new ObjectOutputStream(s.getOutputStream());
			os.writeObject(bean);
			os.flush();
			

			// System.out.println("Sent socket!");
			ObjectInputStream is = new ObjectInputStream(s.getInputStream());

			RegisterBean bean = (RegisterBean) is.readObject();
			s.close();
			return bean;
		} catch (Exception ex) {
			return null;
		}
	}

	public boolean userUpdate() {

		try {
			bean.setMessage("Update");

			Socket s = getSocket();
			BufferedReader socketBr = new BufferedReader(new InputStreamReader(
					s.getInputStream()));
			ObjectOutputStream os = new ObjectOutputStream(s.getOutputStream());
			os.writeObject(bean);
			os.flush();
			
			// System.out.println("Sent socket!");

			if (socketBr.readLine().equalsIgnoreCase("true")) {
				// System.out.println("Sent true!");
				return true;
			} else {
				return false;
			}
		} catch (Exception ex) {
			return false;
		}
	}

	public ArrayList<String> getAllNetworks() {
		nvb = new NetworkVisibilityBean();
		nvb.setMessage("getAllNetworks");
		ArrayList<String> allNetworks = new ArrayList<String>();

		try {
			byte[] buf;
			Socket s = getSocket();

			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(nvb);
			oos.flush();
		

			BufferedReader socketBr = new BufferedReader(new InputStreamReader(
					s.getInputStream()));
			int allNetworksLength = Integer.parseInt(socketBr.readLine());
			for (int i = 0; i < allNetworksLength; i++) {
				allNetworks.add(socketBr.readLine());
			}

			oos.close();
			socketBr.close();
			s.close();

			return allNetworks;
		} catch (Exception ex) {
			ex.printStackTrace();

			return null;
		}
	}

	public boolean saveDataVisibility() {

		dvb.setMessage("datavisibilityedit");

		try {
			byte[] buf;
			
			Socket s = getSocket();

			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(dvb);
			oos.flush();


			boolean ret = false;
			BufferedReader socketBr = new BufferedReader(new InputStreamReader(
					s.getInputStream()));
			if (socketBr.readLine().equals("true")) {
				ret = true;
			} else {
				ret = false;
			}

			oos.close();
			socketBr.close();
			s.close();

			return ret;
		} catch (Exception ex) {
			ex.printStackTrace();

			return false;
		}
	}

	public boolean saveNetworkVisibility(NetworkVisibilityBean nvb) {

		nvb.setMessage("networkvisibilityedit");

		try {		
			Socket s = getSocket();

			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(nvb);
			oos.flush();
			

			boolean ret = false;
			BufferedReader socketBr = new BufferedReader(new InputStreamReader(
					s.getInputStream()));
			if (socketBr.readLine().equals("true")) {
				ret = true;
			} else {
				ret = false;
			}

			oos.close();
			socketBr.close();
			s.close();

			return ret;
		} catch (Exception ex) {
			ex.printStackTrace();

			return false;
		}
	}

	public DataVisibilityBean getDataVisibilityBean(String userId) {
		dvb = new DataVisibilityBean();
		dvb.setMessage("DataVisibilityLoad");
		dvb.setUName(userId);

		try {
			Socket s = getSocket();

			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(dvb);
			oos.flush();
			
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			Object obj = ois.readObject();
			String msg = ((SecurityMessageBean) obj).getMessage();

			oos.close();
			ois.close();
			s.close();

			if (msg.equalsIgnoreCase("success")) {
				return (DataVisibilityBean) obj;
			} else {
				return null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();

			return null;
		}

	}

	public NetworkVisibilityBean getNWVisibilityBean(String userId) {
		nvb = new NetworkVisibilityBean();
		nvb.setMessage("networkvisibilityload");
		nvb.setUName(userId);

		try {
			
			Socket s = getSocket();

			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(nvb);
			oos.flush();
			
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			Object obj = ois.readObject();
			String msg = ((SecurityMessageBean) obj).getMessage();

			oos.close();
			ois.close();
			s.close();

			if (msg.equalsIgnoreCase("Success")) {
				return (NetworkVisibilityBean) obj;
			} else {
				return null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();

			return null;
		}
	}

	public static String getLoggedInUser() {
		String userId = "";
		try {
			PropertiesManager properties = PropertiesManager.getInstance();
			userId = properties.getProperty(GenSpaceLogin.class,
					PROPERTY_GENSPACE_LOGIN_USER, "");

		} catch (Exception e) {
		}
		return userId;
	}

	public boolean logout() {
		try {
			PropertiesManager properties = PropertiesManager.getInstance();
			properties.setProperty(GenSpaceLogin.class,
					PROPERTY_GENSPACE_LOGIN_USER, "");
			return true;
		} catch (Exception ex) {
			return false;
		}

	}

	public static User getUser(User u) throws Exception {
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(u.username);
		Object result = ServerRequest.get(
				RuntimeEnvironmentSettings.WORKFLOW_REPOSITORY_SERVER,
				"get_user", params);
		if (result instanceof User) {
			return (User) result;
		} else
			throw new Exception(result.toString());
	}
}
