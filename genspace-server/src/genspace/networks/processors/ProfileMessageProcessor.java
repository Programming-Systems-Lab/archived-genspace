package genspace.networks.processors;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;

import org.geworkbench.components.genspace.bean.networks.Friend;
import org.geworkbench.components.genspace.bean.networks.Network;
import org.geworkbench.components.genspace.bean.networks.NetworkMessage;
import org.geworkbench.components.genspace.bean.networks.Profile;

import genspace.networks.MessageProcessor;

public class ProfileMessageProcessor extends MessageProcessor {

	public NetworkMessage processMessage(NetworkMessage m) {
		System.out.println("PRofile request from " + m.sender);
		if(m.reqType.equals(NetworkMessage.Request.FIND))
		{
			return find(m.subject,m.sender);
		}
		else if(m.reqType.equals(NetworkMessage.Request.UPDATE))
		{
			return updateProfile((Profile) m);
		}
		else if(m.reqType.equals(NetworkMessage.Request.LIST))
		{
			if("reqCount".equals(m.subject))
			{
				return numRequests(m.sender);
			}
			else if(m.details.equals("FriendsCanSeeMe"))
			{
				Friend f  = new Friend();
				f.sender=m.sender;
				return (new FriendMessageProcessor()).listFriends(f);
			}
			else if(m.details.equals("NetworksCanSeeMe"))
			{
				return (new NetworkMessageProcessor()).findNetworksByUser(m.sender);
			}
		}
		return null;
	}
	
	private NetworkMessage numRequests(String sender) {
		// TODO Auto-generated method stub
		Profile ret = new Profile();

		int numFriend = 0;
		int numNetwork = 0;
		con = getConnection();
		// create a PreparedStatement
		Statement stmt;
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT r2.username,f.mutual FROM Friend f inner join registration r on r.id=f.id_2 inner join registration r2 on r2.id=f.id_1 where r.username = '" + sender+ "' and f.mutual =0");
			
			while (rs.next())
			{
				numFriend++;
			}
			rs=stmt.executeQuery("SELECT r.username FROM registration r " +
					"inner join user_network un on un.user_id=r.id " +
					"inner join network n on n.id=un.network_id " +
					"inner join registration r2 on r2.id=n.owner where r2.username='"+sender+"' and un.verified=0");
			while(rs.next())
			{
				numNetwork++;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeConnection();
		System.out.println("req:" + numFriend+","+numNetwork);
		ret.subject = "" +( numFriend + numNetwork);
		return ret;
	}

	private NetworkMessage getFriendsThatCanSeeMe() {
		// TODO Auto-generated method stub
		return null;
	}

	private NetworkMessage updateProfile(Profile m) {
		getConnection();
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement("UPDATE registration set first_name=?,last_name=?,email=?,work_title=?,phone=?,lab_affiliation=?,addr1=?,addr2=?,city=?,state=?,zipcode=?,interests=? where id=?");
			stmt.setString(1, m.profile.get("first_name"));
			stmt.setString(2, m.profile.get("last_name"));
			stmt.setString(3, m.profile.get("email"));
			stmt.setString(4, m.profile.get("work_title"));
			stmt.setString(5, m.profile.get("phone"));
			stmt.setString(6, m.profile.get("lab_affiliation"));
			stmt.setString(7, m.profile.get("addr1"));
			stmt.setString(8, m.profile.get("addr2"));
			stmt.setString(9, m.profile.get("city"));
			stmt.setString(10, m.profile.get("state"));
			stmt.setString(11, m.profile.get("zipcode"));
			stmt.setString(12, m.profile.get("interests"));
			stmt.setInt(13, m.bus_id);
			stmt.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	FriendMessageProcessor f = new FriendMessageProcessor();
	public Profile find(String username, String sender)
	{
		con = getConnection();
		// create a PreparedStatement
		Statement stmt;
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT username,email,first_name,last_name,work_title,phone,lab_affiliation,addr1,addr2,city,state,zipcode,id,interests FROM registration where username = '" + username + "'");
			
			if (rs.next())
			{
				Profile n = new Profile();
				n.profile = new HashMap<String, String>();
				n.profile.put("username",rs.getString(1));
				n.profile.put("email",rs.getString(2));
				n.profile.put("first_name",rs.getString(3));
				n.profile.put("last_name",rs.getString(4));
				n.profile.put("work_title",rs.getString(5));
				n.profile.put("phone",rs.getString(6));
				n.profile.put("lab_affiliation",rs.getString(7));
				n.profile.put("addr1",rs.getString(8));
				n.profile.put("addr2",rs.getString(9));
				n.profile.put("city",rs.getString(10));
				n.profile.put("state",rs.getString(11));
				n.profile.put("zipcode",rs.getString(12));
				
				n.bus_id=rs.getInt(13);
				n.profile.put("interests",rs.getString(14));
				n.subject=rs.getString(1);
				if(sender != "" && f.isVisible(sender, username))
				{
					n.details= "YES";
					n.profile.put("visible", "YES");
				}
				else
					n.profile.put("visible","NO");
				if(sender != "" && f.isFriends(sender, username))
					n.profile.put("friends","YES");
				else
					n.profile.put("friends", "NO");
				
				if(sender != "" && f.isFriends(sender, username))
					n.profile.put("friend","YES");
				else
					n.profile.put("friend", "NO");
				closeConnection();
				return n;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	public Profile find(String username)
	{
		return find(username,"");
	}
}
