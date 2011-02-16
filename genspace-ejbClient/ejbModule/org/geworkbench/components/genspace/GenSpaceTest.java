package org.geworkbench.components.genspace;

import java.io.IOException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.resource.spi.security.PasswordCredential;
import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.TextOutputCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

import org.geworkbench.components.genspace.entity.User;
import org.geworkbench.components.genspace.server.NetworkFacadeRemote;

import com.sun.appserv.security.ProgrammaticLogin;

public class GenSpaceTest {
	public static void main(String[] args) {
		System.out.println("Logging in...");
//		User u = LoginManager.getFacade().login("jontest" ,"test123");
		System.setProperty("java.security.auth.login.config", "login.conf");
//		ProgrammaticLogin pm = new ProgrammaticLogin();
		try {
//			pm.login("jontest", "test1232","GELogin",true);
			InitialContext ctx = new InitialContext();
			ctx.lookup("org.geworkbench.components.genspace.server.UserFacadeRemote");
		} catch (Exception e) {
			System.out.println("Unable to login");
			e.printStackTrace();
		} //pass the username and password here

		System.out.println("Programmatic Login Successful...");
		
//		if(u != null) 
//			System.out.println(u);
//		else
//			System.out.println("Login failed");
		System.out.println("Trying to call a method protected now");
		
//		System.out.println(n.doTest());
	}
}
 