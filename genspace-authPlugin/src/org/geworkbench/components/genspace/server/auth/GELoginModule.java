package org.geworkbench.components.genspace.server.auth;


import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.LoginException;

import com.sun.appserv.security.AppservPasswordLoginModule;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;




public class GELoginModule extends AppservPasswordLoginModule {

    private Connection conn = null;
    
    private Connection getConnection() throws LoginException {
    	try {
			if(conn == null || conn.isClosed())
			{
				String userName = "genspace";
			    String password = "g3nsp4c3";
			    String url = "jdbc:mysql://localhost/genspace";
			    Class.forName ("com.mysql.jdbc.Driver").newInstance ();
			    conn = DriverManager.getConnection (url, userName, password);
			}
		} catch (Exception e) {
			LoginException ex = new LoginException("Unable to connect to backing datastore");
			throw ex;
		}
		return conn;
	}

	protected final static String HEX_DIGITS = "0123456789abcdef";

	private String getEncryptedPassword(String plaintext) {
		
		java.security.MessageDigest d =null;
				try {
					d = java.security.MessageDigest.getInstance("SHA-1");
				} catch (NoSuchAlgorithmException e) {
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
    protected void authenticateUser() throws LoginException {


		try {
            Connection conn = getConnection();
			PreparedStatement s = conn.prepareStatement("SELECT id FROM registration where username=? and password=?");
			s.setString(1, _username);
			s.setString(2, getEncryptedPassword(_password));
			s.execute();
			ResultSet rs = s.getResultSet();
			if(rs.next())
			{
				String[] groups = new String[1];
				groups[0] = "users";

				commitUserAuthentication(groups);
				conn.close();
				return;
			}
        } catch (Exception ex) {
            LoginException le = new LoginException("Unable to read username/password or connect to database");
            le.initCause(ex);
            throw le;
        }
        throw new LoginException("Login Failed for user " + _username);
	}

	 
}
