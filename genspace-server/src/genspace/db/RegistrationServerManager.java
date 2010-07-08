package genspace.db;

import genspace.common.Logger;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.geworkbench.components.genspace.bean.RegisterBean;

import sun.misc.BASE64Encoder;

public class RegistrationServerManager extends DatabaseManager {
	
	protected final static String HEX_DIGITS = "0123456789abcdef";

	/**
	 * This method is used to encrypt the password using SHA encryption.
	 * 
	 * @param password
	 *            represents the password to be encrypted
	 * @return returns password in encrypted form
	 */
	private static String getEncryptPassword(char[] c_password) {
		String plaintext = new String(c_password);
		
		java.security.MessageDigest d =null;
				try {
					d = java.security.MessageDigest.getInstance("SHA-1");
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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

	/**
	 * This method is used to register a user
	 * 
	 * @param reg
	 *            represents the object of RegisterBean
	 * @return returns true on successful registration else returns false
	 */
	public boolean register(RegisterBean reg) {
		try {
			// get a database connection
			con = getConnection();

			if (con != null) {
				// create a Statement
				PreparedStatement stmt = null;

				String query = "INSERT INTO registration VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				stmt = con.prepareStatement(query);
				stmt.setString(1, reg.getUsername());
				stmt.setString(2, getEncryptPassword(reg.getPassword()));
				stmt.setString(3, reg.getEmail());
				stmt.setString(4, reg.getIMEmail());
				stmt.setString(5, reg.getIMPasswd());
				stmt.setString(6, reg.getFName());
				stmt.setString(7, reg.getLName());
				stmt.setString(8, reg.getWorkTitle());
				stmt.setString(9, reg.getPhoneNumber());
				stmt.setString(10, reg.getLabAffliation());
				stmt.setString(11, reg.getAddr1());
				stmt.setString(12, reg.getAddr2());
				stmt.setString(13, reg.getCity());
				stmt.setString(14, reg.getState());
				stmt.setString(15, reg.getZipCode());
				
				stmt.executeUpdate();

				// this query sets the default data visibilty of new user as 1
				// ie. data visible within network.
				query = "INSERT INTO data_visibility VALUES (?,1,1)";
				stmt = con.prepareStatement(query);
				stmt.setString(1, reg.getUsername());
				stmt.executeUpdate();

				// this query sets the default user visibilty of new user as 1
				// ie. user visible within network.
				query = "INSERT INTO user_visibility VALUES (?,1)";
				stmt = con.prepareStatement(query);
				stmt.setString(1, reg.getUsername());
				stmt.executeUpdate();

			} else
				System.out.println("Error: No active Connection");
		} catch (Exception e) {
			e.printStackTrace();
			if (Logger.isLogError())
				Logger.logError(e);
			return false;
		} finally {
			closeConnection();
		}

		return true;
	}

	/**
	 * This method is used to validate user's login information. It encrypts the
	 * password and comapres the encrypted password with the one stored in the
	 * database against the username specified.
	 * 
	 * @param username
	 *            represents username
	 * @param password
	 *            represents password
	 * @return returns true if user validated successfully else returns false
	 */
	public boolean login(RegisterBean reg) {

		try {
			// get a database connection
			con = getConnection();

			if (con != null) {
				// create a Statement

				String encryptedPassword = null;
				String query = "SELECT password FROM registration where username = ?";
				PreparedStatement stmt = con.prepareStatement(query);
				stmt.setString(1, reg.getUsername());
				ResultSet rs = stmt.executeQuery();

				if (rs.next()) {
					encryptedPassword = rs.getString("password");
				} else {
					// no user with this username exists
					return false;
				}

				if (encryptedPassword.equals(getEncryptPassword(reg
						.getPassword()))) {
					// password matches
					return true;
				} else {
					return false;
				}

			} else
				System.out.println("Error: No active Connection");
		} catch (Exception e) {
			e.printStackTrace();
			if (Logger.isLogError())
				Logger.logError(e);
			return false;
		} finally {
			closeConnection();
		}

		return false;
	}

	public boolean userDupCheck(RegisterBean reg) {

		try {
			// get a database connection
			con = getConnection();

			if (con != null) {

				String query = "SELECT username FROM registration where username = ?";
				PreparedStatement stmt = con.prepareStatement(query);
				stmt.setString(1, reg.getUsername());
				ResultSet rs = stmt.executeQuery();

				if (rs.next()) {
					return false;
				} else {
					// no user with this username exists
					return true;
				}

			} else
				System.out.println("Error: No active Connection");
		} catch (Exception e) {
			e.printStackTrace();
			if (Logger.isLogError())
				Logger.logError(e);
			return false;
		} finally {
			closeConnection();
		}

		return false;
	}
	public boolean updateUserInfo(RegisterBean reg){
		try {
			System.out.println("Updating "+reg.getUsername());
			// get a database connection
			con = getConnection();
			//System.out.println("Details in server mgr: "+reg.getLName()+" "+reg.getFName()+"pass: "+reg.getPassword()+" "+reg.getAddr1());
			if (con != null) {
				// create a Statement
				
				
//				if(reg.getPassword()!=null)
//					query = "UPDATE registration set password = '"
//						+ getEncryptPassword(reg.getPassword()) + "', email= '"
//						+ reg.getEmail() + "', first_name='"  + reg.getFName() + "', last_name='"
//						+ reg.getLName() + "', work_title='" + reg.getWorkTitle() + "', phone='"
//						+ reg.getPhoneNumber() + "', lab_affiliation='"
//						+ reg.getLabAffliation() + "', addr1='" + reg.getAddr1()
//						+ "', addr2='" + reg.getAddr2() + "', city='" + reg.getCity()
//						+ "', state= '" + reg.getState() + "', zipcode='" + reg.getZipCode()
//						+ "' where username = '"+reg.getUsername()+"'";
//				else


				String query = "UPDATE  registration set email = ?, first_name =?," +
						"last_name= ?, work_title=?, phone = ?, lab_affiliation =?, addr1 = ?, addr2 = ?," +
						"city=?, state = ?,zipcode =? where username = ? ";
				PreparedStatement stmt = con.prepareStatement(query);
				stmt.setString(1, reg.getEmail());
				stmt.setString(2, reg.getFName());
				stmt.setString(3, reg.getLName());
				stmt.setString(4, reg.getWorkTitle());
				stmt.setString(5, reg.getPhoneNumber());
				stmt.setString(6, reg.getLabAffliation());
				stmt.setString(7, reg.getAddr1());
				stmt.setString(8, reg.getAddr2());
				stmt.setString(9, reg.getCity());
				stmt.setString(10, reg.getState());
				stmt.setString(11, reg.getZipCode());
				stmt.setString(12, reg.getUsername());
				
				
				
				stmt.executeUpdate();
				System.out.println("update complete");
				//stmt.executeUpdate(query);
				
				
			} else
				System.out.println("Error: No active Connection");
		} catch (Exception e) {
			e.printStackTrace();
			if (Logger.isLogError())
				Logger.logError(e);
			return false;
		} finally {
			closeConnection();
		}

		return true;
	}
	
	public RegisterBean getUserInfo(String username){
		System.out.println("Called");
		RegisterBean rbean = new RegisterBean();
		try {
			// get a database connection
			con = getConnection();
			Statement stmt1 = con.createStatement();
			String cntQuery = "SELECT first_name,last_name,email,work_title,phone,lab_affiliation,addr1,addr2,city,state,zipcode from registration where username='"+username+"'";
			ResultSet rs1 = stmt1.executeQuery(cntQuery);
			int cnt=0;
			if(rs1.next()){
				rbean.setFName(rs1.getString("first_name"));
				rbean.setLName(rs1.getString("last_name"));
				rbean.setEmail(rs1.getString("email"));
				rbean.setWorkTitle(rs1.getString("work_title"));
				rbean.setPhoneNumber(rs1.getString("phone"));
				rbean.setLabAffiliation(rs1.getString("lab_affiliation"));
				rbean.setAddr1(rs1.getString("addr1"));
				rbean.setAddr2(rs1.getString("addr2"));
				rbean.setCity(rs1.getString("city"));
				rbean.setState(rs1.getString("state"));
				rbean.setZipcode(rs1.getString("zipcode"));
						
			} else
				System.out.println("Error: No active Connection");
		} catch (Exception e) {
			e.printStackTrace();
			if (Logger.isLogError())
				Logger.logError(e);
				return null;
		} finally {
			closeConnection();
		}
		return rbean;

	}

	
	
}
