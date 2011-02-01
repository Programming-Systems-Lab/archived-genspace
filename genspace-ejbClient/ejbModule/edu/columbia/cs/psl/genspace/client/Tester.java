package edu.columbia.cs.psl.genspace.client;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import edu.columbia.cs.psl.genspace.server.FriendFacadeRemote;

public class Tester {
	public static void main(String[] args) {
		
//		System.setProperty("org.omg.CORBA.ORBInitialHost", "localhost");
		try {
			InitialContext ctx = new InitialContext();
			FriendFacadeRemote ffr = (FriendFacadeRemote) ctx.lookup("edu.columbia.cs.psl.genspace.server.FriendFacadeRemote");
			ffr.createNewUser();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
 