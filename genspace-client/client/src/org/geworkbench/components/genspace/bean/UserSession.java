package org.geworkbench.components.genspace.bean;

public class UserSession {
	
	private static User user;
	
	public static void setUser(User u){
		user = u;
	}
	
	public static User getInstance(){
		return user;
	}
	
	public static boolean isLogged(){
		return user != null;
	}
	
}
