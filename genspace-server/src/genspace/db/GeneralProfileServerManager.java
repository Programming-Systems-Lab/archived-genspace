package genspace.db;

import genspace.common.Logger;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.Statement;

import org.geworkbench.components.genspace.bean.RegisterBean;

import sun.misc.BASE64Encoder;

public class GeneralProfileServerManager extends DatabaseManager{
	
	protected final static String HEX_DIGITS = "0123456789abcdef";
	
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
	
	public boolean updateUserInfo(RegisterBean reg){
		try {
			// get a database connection
			con = getConnection();

			if (con != null) {
				// create a Statement
				Statement stmt = con.createStatement();
				String  query = null;
				if(reg.getPassword()!=null)
					query = "UPDATE registration set password = '"
						+ getEncryptPassword(reg.getPassword()) + "', email= '"
						+ reg.getEmail() + "', first_name='"  + reg.getFName() + "', last_name='"
						+ reg.getLName() + "', work_title='" + reg.getWorkTitle() + "', phone='"
						+ reg.getPhoneNumber() + "', lab_affiliation='"
						+ reg.getLabAffliation() + "', addr1='" + reg.getAddr1()
						+ "', addr2='" + reg.getAddr2() + "', city='" + reg.getCity()
						+ "', state= '" + reg.getState() + "', zipcode='" + reg.getZipCode()
						+ "' where username = '"+reg.getUsername()+"'";
				else
					query="UPDATE registration set email= '"
						+ reg.getEmail() + "', first_name='"  + reg.getFName() + "', last_name='"
						+ reg.getLName() + "', work_title='" + reg.getWorkTitle() + "', phone='"
						+ reg.getPhoneNumber() + "', lab_affiliation='"
						+ reg.getLabAffliation() + "', addr1='" + reg.getAddr1()
						+ "', addr2='" + reg.getAddr2() + "', city='" + reg.getCity()
						+ "', state= '" + reg.getState() + "', zipcode='" + reg.getZipCode()
						+ "' where username = '"+reg.getUsername()+"'";
			
				
				stmt.executeUpdate(query);
				
				stmt.executeUpdate(query);
				
				
			} //else
				//System.out.println("Error: No active Connection");
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
//		System.out.println("Called");
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
						
			} //else
				//System.out.println("Error: No active Connection");
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

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RegisterBean bean = new RegisterBean();
		
		GeneralProfileServerManager gmgr = new GeneralProfileServerManager();
		bean = gmgr.getUserInfo("gk2263");
		
		System.out.println("First name: "+bean.getFName());

	}

}
