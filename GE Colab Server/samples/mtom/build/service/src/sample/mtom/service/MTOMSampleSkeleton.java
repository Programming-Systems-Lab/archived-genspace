/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package sample.mtom.service;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.SQLException;
import java.rmi.RemoteException;
import java.util.Date;

import javax.activation.DataHandler;

import org.apache.ws.axis2.mtomsample.AttachmentResponse;
import org.apache.ws.axis2.mtomsample.AttachmentType;
import org.w3.www._2005._05.xmlmime.Base64Binary;
import org.geworkbench.builtin.projects.RegisterBean;

/**
 * MTOMServiceSkeleton java skeleton for the axisService
 */
public class MTOMSampleSkeleton {
	private static final String wspRoot = "C:/Docume~1/mw2518.CGC/axis2-1.5.3/samples/testdld/WorkspaceServiceResources/";
	private static final String META_DELIMIETER = "::";
	private static final String USER_INFO_DELIMIETER = "==";
	private Statement stmt = null;

	/**
	 * Auto generated method signature
	 * 
	 * @param param0
	 * @throws Exception
	 * 
	 */
	public org.apache.ws.axis2.mtomsample.AttachmentResponse attachment(
			org.apache.ws.axis2.mtomsample.AttachmentRequest param0)
			throws Exception

	{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/workspace_db", "root", "admin");
		stmt = conn.createStatement();

		AttachmentType attachmentRequest = param0.getAttachmentRequest();
		Base64Binary binaryData = attachmentRequest.getBinaryData();
		DataHandler dataHandler = binaryData==null?null:binaryData.getBase64Binary();
		String meta = attachmentRequest.getFileName();
		System.out.println(meta.length());
		if (meta.length()==0){
			byte[] bytes = new byte[1024];
			dataHandler.getInputStream().read(bytes);
			RegisterBean rb = (RegisterBean) RegisterBean.read(bytes);

			String msg = registerUser(rb);
			AttachmentResponse response = new AttachmentResponse();
			response.setAttachmentResponse(msg);
			return response;
		}

		if (meta.contains("'"))
			meta = meta.replaceAll("'", "\\\\'");
		String[] tokens = meta.split(META_DELIMIETER, 3);
		String userinfo = tokens[2];
		if (!authorized(userinfo)) {
			AttachmentResponse response = new AttachmentResponse();
			response.setAttachmentResponse("User authentication failed.");
			return response;
		}

		String msg = "";
		int id = 0;
		if (dataHandler==null) {
		    id = createNewWsp(tokens[0], tokens[1]);
		    msg = String.valueOf(id);
		} else {
		    id = Integer.valueOf(tokens[0]);
		    msg = updateWsp(dataHandler, id, tokens[1]);
		}

		AttachmentResponse response = new AttachmentResponse();
		response.setAttachmentResponse(msg);
		return response;
	}

	private int uid = 0;
	private boolean authorized(String userinfo) throws RemoteException {
		if (userinfo != null && !userinfo.trim().equals("")) {
			String[] s = userinfo.split(USER_INFO_DELIMIETER, 2);
			if (s.length >= 2 && !s[0].trim().equals("")) {
				ResultSet rs = null;
				String dbpwd = null;
				try {
					rs = stmt.executeQuery
					    ("select id, password from registration where username = '" + s[0] + "'");
					if (rs.next()) {
						uid = rs.getInt(1);
						dbpwd = rs.getString(2);
					}
				} catch (SQLException e) {
					String msg = e.getMessage()
					    + "\nFailed to retrieve authentication info from database for user " + s[0];
					System.out.println(msg);
					throw new RemoteException(msg);
				}
				if (dbpwd == null) {
					String msg = "No record found in database for user " + s[0];
					System.out.println(msg);
					throw new RemoteException(msg);
				}
				if (!dbpwd.equals(s[1])) {
					String msg = "User authentication failed for user " + s[0];
					System.out.println(msg);
					throw new RemoteException(msg);
				}
			} else {
				String msg = "Malformed user authentication info for user " + s[0];
				System.out.println(msg);
				throw new RemoteException(msg);
			}
		} else {
			String msg = "Missing user authentication info.";
			System.out.println(msg);
			throw new RemoteException(msg);
		}
		return true;
	}
	
	private String registerUser(RegisterBean rb) throws RemoteException, SQLException{
		String responseMsg="";
		String username = rb.getUsername();
		String password = new String(rb.getPassword());
		char[] opwd = rb.getOldPasswd();
		String oldPasswd = opwd == null ? null : new String(opwd);
		String firstname = rb.getFName();
		String lastname = rb.getLName();
		String labaff = rb.getLabAffliation();
		String email = rb.getEmail();
		String phone = rb.getPhoneNumber();
		String addr1 = rb.getAddr1();
		String addr2 = rb.getAddr2();
		String city = rb.getCity();
		String state = rb.getState();
		String zip = rb.getZipCode();

		if (opwd == null) {
		    stmt.executeUpdate("insert into registration(username, password, first_name, last_name, lab_affiliation, email, phone, addr1, addr2, city, state, zipcode, online_status) values('"+username+"', '"+password+"', '"+firstname+"', '"+lastname+"', '"+labaff+"', '"+email+"', '"+phone+"', '"+addr1+"', '"+addr2+"', '"+city+"', '"+state+"', '"+zip+"', 1)", Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = stmt.getGeneratedKeys();
			int rid = 0;
			if (rs.next())
				rid = rs.getInt(1);
			System.out.println("inserted user id: " + rid);

			// by default a new user can read the first 5 wsp;
		    stmt.executeUpdate("insert into workspace_user(wspid, uid, gid) select w.id, "+rid+", a.id from workspace w, access a where a.name='read' limit 5");
			responseMsg = username + " was successfully registered.";
		} else {
			ResultSet rs = stmt.executeQuery("select password from registration where username='"+username+"'");
			String pwd = "";
			if (rs.next()) {
				pwd = rs.getString(1);
				if (!pwd.equals(oldPasswd)) {
					String msg = "Incorrect old password for user " + username;
					System.out.println(msg);
					throw new RemoteException(msg);
				} else {
					String query = "update registration set password='"+password+"', first_name='"+firstname+"', last_name='"+lastname+"', lab_affiliation='"+labaff+"', email='"+email+"', phone='"+phone+"', addr1='"+addr1+"', addr2='"+addr2+"', city='"+city+"', state='"+state+"', zipcode='"+zip+"', online_status=1 where username='"+username+"'";
					stmt.executeUpdate(query);
					responseMsg = username + " user profile was successfully updated.";
				}
			} else {
				String msg = "Cannot retrieve old password for user " + username;
				System.out.println(msg);
				throw new RemoteException(msg);
			}
		}
	    return responseMsg;
	}

	private int createNewWsp(String fname, String desc) throws Exception{
		int version = 1;
		String query = "insert into workspace(title, description, creator, version, locked, lastLockedUser) values('" 
		    + fname + "', '" + desc + "', " + uid + ", " + version + ", TRUE, " + uid + ")";
		System.out.println(query);
		stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
		ResultSet rs = stmt.getGeneratedKeys();
		int id = 0;
		if (rs.next())
			id = rs.getInt(1);
		System.out.println("inserted wspid: " + id);
		return id;
	}

 	private String updateWsp(DataHandler dataHandler, int id, String checkoutstr) throws Exception{
		Timestamp now = new Timestamp(new Date().getTime());
		if (checkoutstr!=null && checkoutstr.equals("new")){
			String fpath = wspRoot + id + ".wsp";
			File file = new File(fpath);
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			dataHandler.writeTo(fileOutputStream);
			fileOutputStream.flush();
			fileOutputStream.close();
			System.out.println("saved successfully " + file);

			now = new Timestamp(new Date().getTime());
			stmt.executeUpdate("update workspace set createdAt='" + now + "', lastSync='"
					   + now + "', location='" + fpath + "' where id=" + id);
			stmt.executeUpdate("insert into workspace_user(wspid, uid, gid) select "
					   + id + ", " + uid + ", id from access where name='owner'");
			//by default user 'dba' can admin a new workspace 
			stmt.executeUpdate("insert into workspace_user(wspid, uid, gid) select "
					   + id + ", r.id, a.id from access a, registration r where a.name='admin' and r.username='dba'");
			stmt.executeUpdate("insert into history(wspid, uid, type, accessedAt) values ("
					   + id + ", " + uid + ", 'C', '" + now + "')");
		} else {
			ResultSet rs = null;
			Boolean locked = true;
			String query = "select locked from workspace where id="+id;
			rs = stmt.executeQuery(query);
			if (rs.next())
			    locked = rs.getBoolean(1);
			if (locked == true){
			    String msg = "This workspace is locked " + id;
			    throw new RemoteException(msg);
			}
	
			String access = null;
			query = "select ga.name from access ga, workspace_user wu where "
			    + "wu.wspid="+id +" and wu.uid=" +uid+" and wu.gid=ga.id";
			try {
			    rs = stmt.executeQuery(query);
			    if (rs.next())
				access = rs.getString(1);
			} catch (SQLException e) {
			    String msg = e.getMessage() + "\nFailed to retrieve access info from database for user " + uid;
			    System.out.println(msg);
			    throw new RemoteException(msg);
			}
			if (access==null || access.equals("read")){
			    String msg = "User "+uid+" doesn't have privilege to update wsp " + id;
			    throw new RemoteException(msg);
			}
	
			Timestamp lastSync=null;
			rs = stmt.executeQuery("select lastSync from workspace where id="+id);
			if (rs.next())
			    lastSync = rs.getTimestamp(1);
			System.out.println("checkout: "+checkoutstr);
			if (checkoutstr!=null && !checkoutstr.equals("") && !checkoutstr.equals("null")){
			    Timestamp checkOut = Timestamp.valueOf(checkoutstr);
			    if (checkOut.before(lastSync))
				throw new RemoteException("Checked out version is older than the latest in database");
			}
			stmt.executeUpdate("update workspace set createdAt=createdAt, lastSync=lastSync, locked=TRUE, lastLockedUser=" + uid +" where id="+id);
	
			String fpath = wspRoot + id + ".wsp";
			File file = new File(fpath);
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			dataHandler.writeTo(fileOutputStream);
			fileOutputStream.flush();
			fileOutputStream.close();
			System.out.println("saved successfully " + file);
	
			now = new Timestamp(new Date().getTime());
			query = "update workspace set createdAt=createdAt, lastSync='"+now+"' where id="+id;
			System.out.println(query);
			stmt.executeUpdate(query);
			stmt.executeUpdate("insert into history(wspid, uid, type, accessedAt) values ("
					   + id + ", " + uid + ", 'U', '" + now + "')");
		}

		String lastSync = "";
		ResultSet rs = stmt.executeQuery("select lastSync from workspace where id="+id);
		if (rs.next()) lastSync = rs.getTimestamp(1).toString();

		return lastSync;
	}
}
