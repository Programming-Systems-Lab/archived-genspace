/*
 * Copyright 2005,2006 WSO2, Inc. http://www.wso2.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sample.service;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.SQLException;
import java.rmi.RemoteException;
import java.util.Date;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axis2.context.MessageContext;
import org.apache.axis2.context.OperationContext;
import org.apache.axis2.wsdl.WSDLConstants;

public class WorkspaceService {

	private Statement stmt = null;
	private static final String META_DELIMIETER = "::";
	private static final String USER_INFO_DELIMIETER = "==";

	public OMElement getWorkspace(OMElement element) throws Exception {
		FileDataSource wspBinDataSource;
		DataHandler wspBinDataHandler;

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection conn = DriverManager.getConnection
			    ("jdbc:mysql://localhost:3306/workspace_db", "root", "admin");
			stmt = conn.createStatement();
		} catch (Exception e) {
			String msg = "Database connection failure: workspace_db";
			System.out.println(msg);
			throw new RemoteException(msg);
		}

		// We can obtain the request (incoming) MessageContext as follows
		MessageContext inMessageContext = MessageContext.getCurrentMessageContext();
		// We can obtain the operation context from the request message context
		OperationContext operationContext = inMessageContext.getOperationContext();
		// Now we can obtain the response (outgoing) message context from the
		// operation context
		MessageContext outMessageContext = operationContext.getMessageContext(WSDLConstants.MESSAGE_LABEL_OUT_VALUE);

		String projectName = element.getFirstElement().getText();
		if (projectName.startsWith("LIST")) {
			return createListWorkspaceElement(projectName.substring(4));
		} else if (projectName.startsWith("INFO")) {
			return createInfoWorkspaceElement(projectName.substring(4));
		} else if (projectName.startsWith("DESC")) {
			return createDescWorkspaceElement(projectName.substring(4));
		} else if (projectName.startsWith("REMOVE")) {
			return createRemoveWorkspaceElement(projectName.substring(6));
		} else if (projectName.startsWith("ACCESS")) {
			return createAccessWorkspaceElement(projectName.substring(6));
		} else if (projectName.startsWith("BREAK")) {
		    return createReleaseWorkspaceElement(projectName.substring(5), "break");
		} else if (projectName.startsWith("RELEASE")) {
		    return createReleaseWorkspaceElement(projectName.substring(7), "release");
		} else if (projectName.startsWith("ADDUSER")) {
			return createAddUserWorkspaceElement(projectName.substring(7));
		} else if (projectName.startsWith("ADDGROUP")) {
			return createAddGroupWorkspaceElement(projectName.substring(8));
		} else if (projectName.startsWith("ADDANNO")) {
			return createAddAnnoWorkspaceElement(projectName.substring(7));
		} else if (projectName.startsWith("GROUP")) {
			return createGroupWorkspaceElement(projectName.substring(5));
		} else if (projectName.startsWith("USERGROUP")) {
			return createGroupUserWorkspaceElement(projectName.substring(9));
		} else if (projectName.startsWith("DOWNLOAD")) {
			projectName=projectName.substring(8);    
			String wspFilePath = getFilePath(projectName);
			// Create a data source for the workspace
			wspBinDataSource = new FileDataSource(wspFilePath);
			// Create the dataHandler out of the above created data source
			wspBinDataHandler = new DataHandler(wspBinDataSource);
			// We can add the created data handler as an attachment to the
			// outgoing message.
			String wspBinID = outMessageContext.addAttachment(wspBinDataHandler);
			return createDownloadWorkspaceElement(projectName, wspBinID);
		} else {
			throw new RemoteException("No workspace available for "	+ projectName);
		}
	}

	private String getFilePath(String projectName) throws RemoteException {
		String filepath = "";
		ResultSet rs = null;
		int id = 0;
		int pid = projectName.indexOf(".wsp");
		if (pid > 0)
			id = Integer.valueOf(projectName.substring(0, pid));
		try {
			rs = stmt.executeQuery("select location from workspace where id = " + id);
			if (rs.next())
				filepath = rs.getString(1);

			Timestamp now = new Timestamp(new Date().getTime());
			stmt.executeUpdate("insert into history(wspid, uid, type, accessedAt) values ("
					   + id + ", " + uid + ", 'D', '" + now + "')");

		} catch (Exception e) {
			e.printStackTrace();
			String msg = e.getMessage()
			    + "\nFailed to retrieve info and update history for wsp " + id;
			System.out.println(msg);
			throw new RemoteException(msg);
		}

		return filepath;
	}

	private OMElement createDownloadWorkspaceElement(String projectName, String wspCID) {
		OMFactory factory = OMAbstractFactory.getOMFactory();
		OMNamespace omNs = factory.createOMNamespace("http://service.sample/xsd", "swa");
		OMElement wrapperElement = factory.createOMElement("getWorkspaceResponse", omNs);
		OMElement nameElement = factory.createOMElement("projectName", omNs, wrapperElement);
		nameElement.setText(projectName);
		OMElement wspElement = factory.createOMElement("wsp", omNs, wrapperElement);
		wspCID = "cid:" + wspCID;
		wspElement.addAttribute("href", wspCID, null);
		return wrapperElement;
	}

	private OMElement createRemoveWorkspaceElement(String projectName) throws Exception {
		OMFactory factory = OMAbstractFactory.getOMFactory();
		OMNamespace omNs = factory.createOMNamespace("http://service.sample/xsd", "swa");
		OMElement wrapperElement = factory.createOMElement("getWorkspaceResponse", omNs);
		OMElement nameElement = factory.createOMElement("projectName", omNs, wrapperElement);

		String[] tokens = projectName.split(META_DELIMIETER, 2);
		int id = Integer.valueOf(tokens[0]);
		String userinfo = tokens[1];
		if (!authorized(userinfo)) {
			String msg = "User authentication failed";
			System.out.println(msg);
			throw new RemoteException(msg);
		}

		String query = "select ga.name from access ga, workspace_user wu where wu.wspid="+id
		    +" and wu.uid=" +uid+" and wu.gid=ga.id";
		ResultSet rs = null;
		String access = null;
		try {
		    rs = stmt.executeQuery(query);
		    if (rs.next())
			access = rs.getString(1);
		} catch (SQLException e) {
		    String msg = e.getMessage()
			+ "\nFailed to retrieve access info from database for user " + uid;
		    System.out.println(msg);
		    throw new RemoteException(msg);
		}
		if (access==null || !access.equals("owner")){
		    String msg = "User "+uid+" doesn't have privilege to remove wsp " + id;
		    System.out.println(msg);
		    throw new RemoteException(msg);
		}

		String filepath = "location";
		rs = stmt.executeQuery("select location from workspace where id = " + id);
		if (rs.next())
			filepath = rs.getString(1);
		File file = new File(filepath);
		if (file.exists() && filepath.endsWith(".wsp")) {
			System.gc();
			if (!file.delete())
				file.deleteOnExit();
		} else {
			throw new RemoteException("Workspace file cannot be found on the server: " + filepath);
		}

		query = "delete from workspace where id=" + id;
		System.out.println(query);
		stmt.executeUpdate(query);

		nameElement.setText(id + ".wsp was successfully removed");
		return wrapperElement;
	}

	private OMElement createAccessWorkspaceElement(String projectName) throws Exception {
		OMFactory factory = OMAbstractFactory.getOMFactory();
		OMNamespace omNs = factory.createOMNamespace("http://service.sample/xsd", "swa");
		OMElement wrapperElement = factory.createOMElement("getWorkspaceResponse", omNs);
		OMElement nameElement = factory.createOMElement("projectName", omNs, wrapperElement);

		String[] tokens = projectName.split(META_DELIMIETER, 2);
		int id = Integer.valueOf(tokens[0]);
		String username = tokens[1];
		System.out.println(id+"; "+username);

		String query = "select ga.name from registration r, access ga, workspace_user wu where "
		     +"r.username='"+username+"' and r.id = wu.uid and wu.wspid="+id+" and wu.gid=ga.id";
		ResultSet rs = null;
		String access = null;
		try {
		    rs = stmt.executeQuery(query);
		    if (rs.next())
			access = rs.getString(1);
		} catch (SQLException e) {
		    String msg = e.getMessage()
			+ "\nFailed to retrieve access info from database for user " + uid;
		    System.out.println(msg);
		    throw new RemoteException(msg);
		}
		if (access==null){
		    String msg = "User "+uid+" doesn't have access to wsp " + id;
		    System.out.println(msg);
		    throw new RemoteException(msg);
		}

		nameElement.setText(access);
		return wrapperElement;
	}

	private OMElement createReleaseWorkspaceElement(String projectName, String type) throws Exception {
		OMFactory factory = OMAbstractFactory.getOMFactory();
		OMNamespace omNs = factory.createOMNamespace("http://service.sample/xsd", "swa");
		OMElement wrapperElement = factory.createOMElement("getWorkspaceResponse", omNs);
		OMElement nameElement = factory.createOMElement("projectName", omNs, wrapperElement);

		String[] tokens = projectName.split(META_DELIMIETER, 2);
		int id = Integer.valueOf(tokens[0]);
		String userinfo = tokens[1];
		if (!authorized(userinfo)) {
			String msg = "User authentication failed";
			System.out.println(msg);
			throw new RemoteException(msg);
		}

		String query;
		if (type.equals("release")) query="select id from workspace where id="+id+" and lastLockedUser="+uid;
		else query = "select w.id from workspace w, workspace_user wu, access a where w.id = wu.wspid and wu.gid=a.id and a.name in ('owner', 'admin') and w.id = "+id + " and wu.uid=" +uid;

		ResultSet rs = null;
		int retid = 0;
		try {
		    rs = stmt.executeQuery(query);
		    if (rs.next())
			retid = rs.getInt(1);
		    else{
			String msg = "User "+uid+" doesn't have privilege to "+type+" lock for wsp " + id;
			System.out.println(msg);
			throw new RemoteException(msg);
		    }
		} catch (SQLException e) {
		    String msg = e.getMessage()
			+ "\nFailed to retrieve access info from database for user " + uid;
		    System.out.println(msg);
		    throw new RemoteException(msg);
		}

		query = "update workspace set createdAt=createdAt, lastSync=lastSync, locked=FALSE where id=" + id;
		System.out.println(query);
		stmt.executeUpdate(query);

		nameElement.setText(id + ".wsp was successfully released");
		return wrapperElement;
	}

	private OMElement createDescWorkspaceElement(String projectName) throws Exception {
		OMFactory factory = OMAbstractFactory.getOMFactory();
		OMNamespace omNs = factory.createOMNamespace("http://service.sample/xsd", "swa");
		OMElement wrapperElement = factory.createOMElement("getWorkspaceResponse", omNs);
		OMElement nameElement = factory.createOMElement("projectName", omNs, wrapperElement);

		String[] tokens = projectName.split(META_DELIMIETER, 3);
		int id = Integer.valueOf(tokens[0]);
		String desc = tokens[1];
		String userinfo = tokens[2];
		if (!authorized(userinfo)) {
			String msg = "User authentication failed";
			System.out.println(msg);
			throw new RemoteException(msg);
		}

		ResultSet rs = null;
		String access = null;
		try {
			rs = stmt.executeQuery("select a.name from access a, workspace_user wu where wu.gid=a.id and wu.wspid = "+id+" and wu.uid = "+uid);
			if (rs.next())
			    access = rs.getString(1);
		} catch (Exception e) {
		    String msg = e.getMessage()
			+ "\nFailed to retrieve access info from database for user " + uid;
		    System.out.println(msg);
		    throw new RemoteException(msg);
		}

		if (access==null || access.equals("read") || access.equals("write")){
		    String msg = "User "+uid+" doesn't have privilege to change description for wsp " + id;
		    System.out.println(msg);
		    throw new RemoteException(msg);
		}

		stmt.executeUpdate("update workspace set createdAt=createdAt, lastSync=lastSync, description='" + desc+"' where id="+id);
		nameElement.setText("Description for " + id + ".wsp was successfully updated");
		return wrapperElement;
	}

	private OMElement createAddAnnoWorkspaceElement(String projectName) throws Exception {
		OMFactory factory = OMAbstractFactory.getOMFactory();
		OMNamespace omNs = factory.createOMNamespace("http://service.sample/xsd", "swa");
		OMElement wrapperElement = factory.createOMElement("getWorkspaceResponse", omNs);
		OMElement nameElement = factory.createOMElement("projectName", omNs, wrapperElement);

		String[] tokens = projectName.split(META_DELIMIETER, 3);
		int id = Integer.valueOf(tokens[0]);
		String anno = tokens[1];
		String userinfo = tokens[2];
		if (!authorized(userinfo)) {
			String msg = "User authentication failed";
			System.out.println(msg);
			throw new RemoteException(msg);
		}

		String query = "select ga.name from access ga, workspace_user wu where wu.wspid="+id
		    +" and uid=" +uid+" and wu.gid=ga.id";
		ResultSet rs = null;
		String access = null;
		try {
		    rs = stmt.executeQuery(query);
		    if (rs.next())
			access = rs.getString(1);
		} catch (SQLException e) {
		    String msg = e.getMessage()
			+ "\nFailed to retrieve access info from database for user " + uid;
		    System.out.println(msg);
		    throw new RemoteException(msg);
		}
		if (access==null || access.equals("read")){
		    String msg = "User "+uid+" doesn't have privilege to add annotation for wsp " + id;
		    System.out.println(msg);
		    throw new RemoteException(msg);
		}

		Timestamp now = new Timestamp(new Date().getTime());
		query = "insert into annotation (wspid, annotation, creator, createdAt) values (" 
		    + id + ", '" + anno + "', " + uid + ", '" + now + "')"; 
		System.out.println(query);
		stmt.executeUpdate(query);

		nameElement.setText("Annotation for " + id + ".wsp was successfully added");
		return wrapperElement;
	}

	private OMElement createAddUserWorkspaceElement(String projectName) throws Exception {
		OMFactory factory = OMAbstractFactory.getOMFactory();
		OMNamespace omNs = factory.createOMNamespace("http://service.sample/xsd", "swa");
		OMElement wrapperElement = factory.createOMElement("getWorkspaceResponse", omNs);
		OMElement nameElement = factory.createOMElement("projectName", omNs, wrapperElement);

		String[] tokens = projectName.split(META_DELIMIETER, 4);
		int id = Integer.valueOf(tokens[0]);
		String username = tokens[1];
		String groupname = tokens[2];
		String userinfo = tokens[3];
		if (!authorized(userinfo)) {
			String msg = "User authentication failed";
			System.out.println(msg);
			throw new RemoteException(msg);
		}

		String query = "select ga.name from access ga, workspace_user wu where wu.wspid="+id
		    +" and uid=" +uid+" and wu.gid=ga.id";
		ResultSet rs = null;
		String access = null;
		try {
		    rs = stmt.executeQuery(query);
		    if (rs.next())
			access = rs.getString(1);
		} catch (SQLException e) {
		    String msg = e.getMessage()
			+ "\nFailed to retrieve access info from database for user " + uid;
		    System.out.println(msg);
		    throw new RemoteException(msg);
		}
		if (access==null || access.equals("read") || access.equals("write") 
		    || (groupname.equals("admin") && access.equals("admin"))) {
		    String msg = "User "+uid+" doesn't have admin/owner privilege to update user access for wsp " + id;
		    throw new RemoteException(msg);
		}

		stmt.executeUpdate("delete from workspace_user where wspid="+id+" and uid in (select id from registration where username='"+username+"')");
		if (!groupname.equals("remove")){
			query = "insert into workspace_user (wspid, uid, gid) select " + id + ", " + 
		    "r.id, ga.id from access ga, registration r where ga.name='" + groupname +"' and r.username='" + username +"'"; 
			System.out.println(query);
			stmt.executeUpdate(query);
		}

		nameElement.setText(username+ " user access for " + id + ".wsp was successfully updated");
		return wrapperElement;
	}

	private OMElement createAddGroupWorkspaceElement(String projectName) throws Exception {
		OMFactory factory = OMAbstractFactory.getOMFactory();
		OMNamespace omNs = factory.createOMNamespace("http://service.sample/xsd", "swa");
		OMElement wrapperElement = factory.createOMElement("getWorkspaceResponse", omNs);
		OMElement nameElement = factory.createOMElement("projectName", omNs, wrapperElement);

		String[] tokens = projectName.split(META_DELIMIETER, 4);
		int id = Integer.valueOf(tokens[0]);
		String networkname = tokens[1];
		String groupname = tokens[2];
		String userinfo = tokens[3];
		if (!authorized(userinfo)) {
			String msg = "User authentication failed";
			System.out.println(msg);
			throw new RemoteException(msg);
		}

		String query = "select ga.name from access ga, workspace_user wu where wu.wspid="+id
		    +" and uid=" +uid+" and wu.gid=ga.id";
		ResultSet rs = null;
		String access = null;
		try {
		    rs = stmt.executeQuery(query);
		    if (rs.next())
			access = rs.getString(1);
		} catch (SQLException e) {
		    String msg = e.getMessage()
			+ "\nFailed to retrieve access info from database for user " + uid;
		    System.out.println(msg);
		    throw new RemoteException(msg);
		}
		if (access==null || access.equals("read") || access.equals("write")) {
		    String msg = "User "+uid+" doesn't have admin/owner privilege to update group access for wsp " + id;
		    throw new RemoteException(msg);
		}

		query = "delete from workspace_user where wspid="+id+" and gid in (select id from access where name in ('read', 'write')) and uid in (select un.user_id from user_network un, network n where un.network_id = n.id and n.name='"+networkname+"')";
		System.out.println(query);
		stmt.executeUpdate(query);

		if (!groupname.equals("remove")){
		query = "insert into workspace_user (wspid, uid, gid) select "+id+", "+"un.user_id, ga.id from access ga, user_network un, network n where ga.name='" + groupname +"' and un.network_id=n.id and n.name='" + networkname +"' and un.user_id not in (select wu.uid from workspace_user wu, access a where wu.gid=a.id and a.name in ('admin', 'owner') and wu.wspid="+id+")"; 
		System.out.println(query);
		stmt.executeUpdate(query);
		}

		nameElement.setText(networkname+ " group access for " + id + ".wsp was successfully updated");
		return wrapperElement;
	}

	private static int uid = 0;
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

	private OMElement createInfoWorkspaceElement(String projectName) throws RemoteException {

		OMFactory factory = OMAbstractFactory.getOMFactory();
		OMNamespace omNs = factory.createOMNamespace("http://service.sample/xsd", "swa");
		OMElement wrapperElement = factory.createOMElement("getWorkspaceResponse", omNs);

		int id = Integer.valueOf(projectName);
		boolean isAdmin = false;

		ResultSet rs = null;
		String access = null;
		try {
			rs = stmt.executeQuery("select a.name from access a, workspace_user wu where wu.gid=a.id and wu.wspid = "+id+" and wu.uid = "+uid);
			if (rs.next())
			    access = rs.getString(1);
		} catch (Exception e) {
			e.printStackTrace();
		}

		OMElement wspElement = factory.createOMElement("histcount", omNs, wrapperElement);
		if (access==null || access.equals("read") || access.equals("write"))
			wspElement.setText("0");
		else {
		    isAdmin = true;
			rs = null;
			try {
				rs = stmt.executeQuery("select count(*) from history where wspid = "+id);
				int count = rs.next() ? rs.getInt(1) : 0;
				wspElement.setText(Integer.toString(count));
				rs = stmt.executeQuery("select r.username, h.type, h.accessedAt from history h, registration r where h.uid = r.id and h.wspid = "+id+" order by h.accessedAt desc");
				while (rs.next()) {
					wspElement = factory.createOMElement("histlist", omNs, wrapperElement);
					wspElement.addAttribute("Username", rs.getString(1), null);
					wspElement.addAttribute("Type", rs.getString(2), null);
					wspElement.addAttribute("AccessedAt", rs.getTimestamp(3).toString(), null);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}


		rs = null;
		try {
			wspElement = factory.createOMElement("usercount", omNs, wrapperElement);
			rs = stmt.executeQuery("select count(*) from workspace_user where wspid = "+id);
			int count = rs.next() ? rs.getInt(1) : 0;
			wspElement.setText(Integer.toString(count));
			rs = stmt.executeQuery("select r.username, r.online_status, a.name, B.at from (select wu.wspid wuwspid, wu.uid wuuid, wu.gid wugid, max(h.accessedAt) at from workspace_user wu left join history h on wu.uid=h.uid and wu.wspid=h.wspid group by wu.wspid, wu.uid) B, registration r, access a where r.id=B.wuuid and B.wugid=a.id and B.wuwspid="+id);
			while (rs.next()) {
				wspElement = factory.createOMElement("userlist", omNs, wrapperElement);
				wspElement.addAttribute("Username", rs.getString(1), null);
				wspElement.addAttribute("OnlineStatus", Integer.toString(rs.getInt(2)), null);
				if (isAdmin){
				wspElement.addAttribute("Access", rs.getString(3), null);
				wspElement.addAttribute("LastAccessTime", rs.getTimestamp(4)==null?"":rs.getTimestamp(4).toString(), null);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		rs = null;
		try {
			wspElement = factory.createOMElement("annocount", omNs, wrapperElement);
			rs = stmt.executeQuery("select count(*) from annotation where wspid = "+id);
			int count = rs.next() ? rs.getInt(1) : 0;
			wspElement.setText(Integer.toString(count));
			rs = stmt.executeQuery("select r.username, a.createdAt, a.annotation from annotation a, registration r where a.creator = r.id and a.wspid = "+id+" order by r.username, a.createdAt");
			while (rs.next()) {
				wspElement = factory.createOMElement("annolist", omNs, wrapperElement);
				wspElement.addAttribute("Creator", rs.getString(1), null);
				wspElement.addAttribute("CreatedAt", rs.getTimestamp(2).toString(), null);
				wspElement.addAttribute("Annotation", rs.getString(3), null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return wrapperElement;
	}

	private OMElement createListWorkspaceElement(String userinfo) throws RemoteException {

		OMFactory factory = OMAbstractFactory.getOMFactory();
		OMNamespace omNs = factory.createOMNamespace("http://service.sample/xsd", "swa");
		OMElement wrapperElement = factory.createOMElement("getWorkspaceResponse", omNs);

		if (!authorized(userinfo)) {
			String msg = "User authentication failed";
			System.out.println(msg);
			throw new RemoteException(msg);
		}

		ResultSet rs = null;
		try {
			rs = stmt.executeQuery("select id, username, first_name, last_name, lab_affiliation, email, phone, addr1, addr2, city, state, zipcode from registration where id="+uid);
			System.out.print("User: ");
			if (rs.next()) {
				OMElement wspElement = factory.createOMElement("profile", omNs, wrapperElement);
				wspElement.addAttribute("id", Integer.toString(rs.getInt(1)), null);
				wspElement.addAttribute("username", rs.getString(2), null);
				System.out.println(rs.getString(2));
				wspElement.addAttribute("fname", rs.getString(3), null);
				wspElement.addAttribute("lname", rs.getString(4), null);
				wspElement.addAttribute("labaff", rs.getString(5), null);
				wspElement.addAttribute("email", rs.getString(6)==null?"":rs.getString(6), null);
				wspElement.addAttribute("phone", rs.getString(7)==null?"":rs.getString(7), null);
				wspElement.addAttribute("addr1", rs.getString(8)==null?"":rs.getString(8), null);
				wspElement.addAttribute("addr2", rs.getString(9)==null?"":rs.getString(9), null);
				wspElement.addAttribute("city", rs.getString(10)==null?"":rs.getString(10), null);
				wspElement.addAttribute("state", rs.getString(11)==null?"":rs.getString(11), null);
				wspElement.addAttribute("zipcode", rs.getString(12)==null?"":rs.getString(12), null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		OMElement wspElement = factory.createOMElement("groupcount", omNs, wrapperElement);
		rs = null;
		try {
			rs = stmt.executeQuery("select count(*) from user_network where user_id="+uid);
			int count = rs.next() ? rs.getInt(1) : 0;
			System.out.print("groups("+count+"): ");
			wspElement.setText(Integer.toString(count));
			rs = stmt.executeQuery("select n.name, r.username from network n, registration r, user_network un where n.owner=r.id and n.id=un.network_id and un.user_id="+uid+" order by n.name, r.username");
			while (rs.next()) {
				wspElement = factory.createOMElement("grouplist", omNs, wrapperElement);
				wspElement.addAttribute("Group", rs.getString(1), null);
				System.out.print(rs.getString(1)+", ");
				wspElement.addAttribute("Owner", rs.getString(2), null);
			}
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}

		wspElement = factory.createOMElement("wspcount", omNs, wrapperElement);
		rs = null;
		try {
			rs = stmt.executeQuery("select count(*) from workspace_user where uid="+uid);
			int count = rs.next() ? rs.getInt(1) : 0;
			wspElement.setText(Integer.toString(count));
			System.out.print("remote workspaces(" + count + "): ");
			rs = stmt.executeQuery("select w.id, w.title, u.username, ga.name, w.locked, l.username, w.lastSync, w.createdAt, w.description from workspace w, registration u, registration l, workspace_user wu, access ga where w.creator = u.id and w.lastLockedUser = l.id and wu.wspid = w.id and wu.gid = ga.id and wu.uid = "+uid+ " order by w.id");
			while (rs.next()) {
				wspElement = factory.createOMElement("wsplist", omNs, wrapperElement);
				wspElement.addAttribute("ID", Integer.toString(rs.getInt(1)), null);
				wspElement.addAttribute("Title", rs.getString(2), null);
				System.out.print(rs.getString(2) + "; ");
				wspElement.addAttribute("Owner", rs.getString(3), null);
				wspElement.addAttribute("Access", rs.getString(4), null);
				wspElement.addAttribute("Lock", Boolean.toString(rs.getBoolean(5)), null);
				wspElement.addAttribute("LocUser", rs.getString(6), null);
				wspElement.addAttribute("LastSync", rs.getTimestamp(7).toString(), null);
				wspElement.addAttribute("CreatedAt", rs.getTimestamp(8).toString(), null);
				wspElement.addAttribute("Description", rs.getString(9), null);
			}
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return wrapperElement;
	}

	private OMElement createGroupWorkspaceElement(String projectName) throws Exception {
		OMFactory factory = OMAbstractFactory.getOMFactory();
		OMNamespace omNs = factory.createOMNamespace("http://service.sample/xsd", "swa");
		OMElement wrapperElement = factory.createOMElement("getWorkspaceResponse", omNs);
		OMElement nameElement = factory.createOMElement("projectName", omNs, wrapperElement);

		String[] tokens = projectName.split(META_DELIMIETER, 2);
		String group = tokens[0];
		String userinfo = tokens[1];
		if (!authorized(userinfo)) {
			String msg = "User authentication failed";
			System.out.println(msg);
			throw new RemoteException(msg);
		}

		String query = "insert into network(name, owner) values ('" + group + "', " + uid + ")";
		System.out.println(query);
		stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
		ResultSet rs = stmt.getGeneratedKeys();
		int gid = 0;
		if (rs.next())
		    gid = rs.getInt(1);

		query = "insert into user_network(user_id, network_id, visible) values ('" + uid + "', " + gid + ", 1)";
		System.out.println(query);
		stmt.executeUpdate(query);

		nameElement.setText("Group " + group + " was successfully added");
		return wrapperElement;
	}

	private OMElement createGroupUserWorkspaceElement(String projectName) throws Exception {
		OMFactory factory = OMAbstractFactory.getOMFactory();
		OMNamespace omNs = factory.createOMNamespace("http://service.sample/xsd", "swa");
		OMElement wrapperElement = factory.createOMElement("getWorkspaceResponse", omNs);
		OMElement nameElement = factory.createOMElement("projectName", omNs, wrapperElement);

		String[] tokens = projectName.split(META_DELIMIETER, 3);
		String group = tokens[0];
		String user = tokens[1];
		String userinfo = tokens[2];
		if (!authorized(userinfo)) {
			String msg = "User authentication failed";
			System.out.println(msg);
			throw new RemoteException(msg);
		}

		String query = "select id from registration where username='"+user+"'";
		ResultSet rs = stmt.executeQuery(query);
		int rid = 0;
		if (rs.next())
		    rid = rs.getInt(1);
		else{
			String msg = "User cannot be found in database";
			System.out.println(msg);
			throw new RemoteException(msg);
		}

		query = "select id from network where name='"+group + "' and owner="+uid;
		rs = stmt.executeQuery(query);
		int gid = 0;
		if (rs.next())
		    gid = rs.getInt(1);
		else{
			String msg = "Only group owner can add user to the group";
			System.out.println(msg);
			throw new RemoteException(msg);
		}

		query = "select * from user_network where network_id="+gid + " and user_id="+rid;
		rs = stmt.executeQuery(query);
		if (rs.next()) {
			String msg = "User is already in the group";
			System.out.println(msg);
			throw new RemoteException(msg);
		}

		query = "insert into user_network(user_id, network_id, visible) values ("+rid+", "+gid+", 1)";
		System.out.println(query);
		stmt.executeUpdate(query);

		nameElement.setText(user + " was successfully added to group "+group);
		return wrapperElement;
	}
}
