package org.geworkbench.components.genspace.server.auth;

import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

import com.sun.appserv.security.AppservRealm;
import com.sun.enterprise.security.auth.realm.BadRealmException;
import com.sun.enterprise.security.auth.realm.InvalidOperationException;
import com.sun.enterprise.security.auth.realm.NoSuchRealmException;
import com.sun.enterprise.security.auth.realm.NoSuchUserException;
import com.sun.enterprise.security.auth.realm.Realm;
import com.sun.enterprise.security.auth.realm.User;

public class GERealm extends AppservRealm  {
	 private static String AUTH_TYPE_PARAM = "auth-type";
	 private String authType;
	@Override
	protected void init(Properties props) throws BadRealmException,
			NoSuchRealmException {
		super.init(props);
		System.out.println("GERealm initing...");
		for(Object o : props.keySet())
		{
			System.out.println("Prop " + o + "="+props.getProperty(o.toString()));
		}
		
		super.init(props);

	       /* 
	        * Set the jaas context, otherwise server doesn't indentify the login module.
	        * jaas-context is the property specified in domain.xml and 
	        * is the name corresponding to LoginModule
	        * config/login.conf
	        */
	       String jaasCtx = props.getProperty(AppservRealm.JAAS_CONTEXT_PARAM);
	       this.setProperty(AppservRealm.JAAS_CONTEXT_PARAM, jaasCtx);   
	       
	       /* 
	        * Get any other interested properties from configuration file - domain.xml
	        * say auth-type defined in domain.xml.
	        */
	       String authTypeProp = props.getProperty(AUTH_TYPE_PARAM);
	            this.authType = authTypeProp;
	     System.out.println("My context is: " + this.getJAASContext());
		
	}
	@Override
	public String getAuthType() {
		// TODO Auto-generated method stub
		return this.authType;
	}

	@Override
	public Enumeration getGroupNames(String arg0)
			throws InvalidOperationException, NoSuchUserException {
		Vector<String> v = new Vector<String>();
		v.add("users");
		
		return v.elements();
	}
	@Override
	public User getUser(String name) throws NoSuchUserException,
			BadRealmException {
		System.out.println("Get user called");
		MyUser u = new MyUser(name);
		return u;
	}

}

class MyUser implements User
{
	String name;
	public MyUser(String name)
	{
		this.name = name;
	}
	@Override
	public String getName() {
		return name;
	}

	@Override
	public Object getAttribute(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enumeration getAttributeNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Realm getRealm() throws NoSuchRealmException {
		// TODO Auto-generated method stub
		return null;
	}
	
}