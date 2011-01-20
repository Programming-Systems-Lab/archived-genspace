package genspace.networks.processors;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

import org.geworkbench.components.genspace.bean.networks.Network;
import org.geworkbench.components.genspace.bean.networks.NetworkMessage;
import org.geworkbench.components.genspace.bean.networks.Profile;

import genspace.networks.MessageProcessor;
import genspace.networks.NetworksHandler;

public class NetworkMessageProcessor extends MessageProcessor {

	public NetworkMessage processMessage(NetworkMessage m) {
		// TODO Auto-generated method stub
		if(m.reqType.equals(NetworkMessage.Request.CREATE))
		{
			//Process create network
			Network t = (Network) m;
			t.creator=t.sender;
			return create(t);
		}
		else if(m.reqType.equals(NetworkMessage.Request.FIND))
		{
			return find(m.subject);
		}
		else if(m.reqType.equals(NetworkMessage.Request.JOIN))
		{
			return joinNetwork((Network) m);
		}
		else if(m.reqType.equals(NetworkMessage.Request.LEAVE))
		{
			return leaveNetwork((Network) m);
		}
		else if(m.reqType.equals(NetworkMessage.Request.LIST))
		{
			if(((Network) m).networkScope.equals(Network.NetScope.ALL))
			{
				return findAllNetworks();
			}
			else if(((Network) m).networkScope.equals(Network.NetScope.ME))
			{
				return findNetworksByUser(m.sender);
			}
			else if(((Network) m).networkScope.equals(Network.NetScope.USER))
			{
				return findNetworksByUser(m.subject);
			}
			else if(((Network) m).networkScope.equals(Network.NetScope.NETWORK))
			{
				return listUsersInNetwork((Network) m);
			}
			else if(((Network) m).networkScope.equals(Network.NetScope.PENDING))
			{
				return listPendingUsersInNetwork(m.subject);
			}
		}
		else if(m.reqType.equals(NetworkMessage.Request.UPDATE))
		{
			return updateVisibility(m.sender,Integer.valueOf(m.subject),m.visible);
		}
		return null;
	}
	public NetworkMessage updateVisibility(String user, int network, boolean visible)
	{
		con = getConnection();
		try
		{
			ProfileMessageProcessor pm = new ProfileMessageProcessor();
			Profile u1_p=pm.find(user);

			int u1 = u1_p.bus_id;

			PreparedStatement st;
			st = con.prepareStatement("UPDATE User_Network set visible=? where user_id=? and network_id=?;");
			st.setBoolean(1, visible);
			st.setInt(2,u1);
			st.setInt(3,network);
			st.executeUpdate();
			
			HashSet<String> a = new HashSet<String>();
			st = con.prepareStatement("SELECT r.username FROM registration r inner join user_network un on un.user_id=r.id " +
					"where un.network_id=?");
			st.setInt(1, network);
			ResultSet rs=st.executeQuery();
			while(rs.next())
				a.add(rs.getString(1));
			NetworksHandler.notifyAll(user, a, visible);
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	public Network listUsersInNetwork(Network netr)
	{
		Network ret = new Network();
		String net = netr.subject;
		ret.children = new LinkedList<NetworkMessage>();
		System.out.println("Listing users in " + net);
		con = getConnection();
		int myId = 0;
		ProfileMessageProcessor p = new ProfileMessageProcessor();
		FriendMessageProcessor f = new FriendMessageProcessor();
		myId = p.find(netr.sender).bus_id;
		
		// create a PreparedStatement
		PreparedStatement stmt;
		try {
//			stmt = con.createStatement();
			System.out.println(net);
			stmt = con.prepareStatement("SELECT r.username,r.email,r.first_name,r.last_name,r.work_title,r.phone,r.lab_affiliation,r.addr1,r.addr2,r.city,r.state,r.zipcode,r.id,r.interests" +
					" FROM registration r inner join user_network un on un.user_id=r.id " +
					"inner join network n on n.id=un.network_id " +
					"where n.name=? and un.verified=1");
			stmt.setString(1, net);
			ResultSet rs=stmt.executeQuery();
			while(rs.next())
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
				if(f.isFriends(netr.sender,n.profile.get("username")))
					n.profile.put("friend", "NO");
				else
					n.profile.put("friend","YES");
				if(f.isVisible(netr.sender, n.profile.get("username")))
					n.details = "YES";
				else
					n.details = "NO";
				n.subject=rs.getString(1);
				System.out.println(n.profile.get("username"));
				ret.children.add(n);
			}
			closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
	public Network listPendingUsersInNetwork(String net)
	{
		Network ret = new Network();
		ret.children = new LinkedList<NetworkMessage>();
		
		con = getConnection();
		// create a PreparedStatement
		PreparedStatement stmt;
		try {
//			stmt = con.createStatement();
			
			stmt = con.prepareStatement("SELECT r.username,r.email,r.first_name,r.last_name,r.work_title,r.phone,r.lab_affiliation,r.addr1,r.addr2,r.city,r.state,r.zipcode,r.id,r.interests FROM registration r inner join user_network un on un.user_id=r.id inner join network n on n.id=un.network_id where n.name=? and un.verified=0");
			stmt.setString(1, net);
			ResultSet rs=stmt.executeQuery();
			while(rs.next())
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
				n.details = net;
				ret.children.add(n);
			}
			closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
	public Network findAllNetworks()
	{
		Network ret = new Network();
		ret.children = new LinkedList<NetworkMessage>();
		
		con = getConnection();
		// create a PreparedStatement
		Statement stmt;
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT n.id,n.name,r.username,first_name + ' ' + last_name + ' - ' + lab_affiliation as fullname FROM Network n inner join registration r on r.id=n.owner");
			
			while (rs.next())
			{
				Network n = new Network();
				n.bus_id = rs.getInt(1);
				n.subject = rs.getString(2);
				n.creator = rs.getString(3);
				n.details = rs.getString(4);
				ret.children.add(n);
			}
			closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
	public Network findNetworksByUser(String user)
	{
		Network ret = new Network();
		ret.children = new LinkedList<NetworkMessage>();
		
		con = getConnection();
		// create a PreparedStatement
		Statement stmt;
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT n.id,n.name,r2.username,un.visible,r2.first_name + ' ' + r2.last_name + ' - ' + r2.lab_affiliation as fullname FROM" +
					" Network n inner join registration r2 on r2.id=n.owner " +
					"inner join user_network un on un.network_id=n.id inner join registration r on r.id=un.user_id where r.username = '" + user+ "' and un.verified = 1");
			
			while (rs.next())
			{
				Network n = new Network();
				n.bus_id = rs.getInt(1);
				n.subject = rs.getString(2);
				n.creator = rs.getString(3);
				n.visible=rs.getBoolean(4);
				n.details = rs.getString(5);
				ret.children.add(n);
			}
			closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
	public Network leaveNetwork(Network n)
	{
		con = getConnection();
		try {
			// create a Statement
			PreparedStatement stmt = null;

			ProfileMessageProcessor mp = new ProfileMessageProcessor();
			
			
			Network ret = find(n.getName());
			
			String query;
			
			if(n.details == null)
			{
				query = "delete from User_Network where user_id=? and network_id=?;";
				stmt = con.prepareStatement(query);
				stmt.setInt(2, ret.bus_id);
				stmt.setInt(1, mp.find(n.sender).bus_id);	
			}
			else
			{
				query = "delete from User_Network where user_id=? and network_id=?;";
				stmt = con.prepareStatement(query);
				stmt.setInt(2, ret.bus_id);
				stmt.setInt(1, mp.find(n.details).bus_id);
			}
			stmt.executeUpdate();
			
			closeConnection();
			return findNetworksByUser(n.sender);

	} catch (Exception e) {
		e.printStackTrace();
		
		return null;
	} finally {
		closeConnection();
	}
	}
	/**
	 * Handles joining and confirming network joins
	 * @param n
	 * @return
	 */
	public Network joinNetwork(Network n)
	{

		con = getConnection();
		try {
			// create a Statement
			PreparedStatement stmt = null;

			ProfileMessageProcessor mp = new ProfileMessageProcessor();
			
			
			Network ret = find(n.getName());
			
			String query;
			if(n.details == null)
			{
				query = "INSERT INTO User_Network (user_id,network_id,verified)" +
				" VALUES (?,?,0)";
				stmt = con.prepareStatement(query);
				stmt.setInt(2, ret.bus_id);
				stmt.setInt(1, mp.find(n.sender).bus_id);		
			}
			else
			{
				query = "UPDATE User_Network set verified=1 where user_id=? and network_id=?;";
				stmt = con.prepareStatement(query);
				stmt.setInt(2, ret.bus_id);
				stmt.setInt(1, mp.find(n.details).bus_id);	
			}
			stmt.executeUpdate();
			
			closeConnection();
			return ret;

	} catch (Exception e) {
		e.printStackTrace();
		
		return null;
	} finally {
		closeConnection();
	}
	}
	public Network create(Network n)
	{
		con = getConnection();

		if(find(n.getName()) != null)
		{
			//Already exists
			throw new IllegalArgumentException("Error A network already exists with the name \""+n.getName()+"\"");
		}
		try {
			// create a Statement
			PreparedStatement stmt = null;

			ProfileMessageProcessor mp = new ProfileMessageProcessor();
			String query = "INSERT INTO Network (name,owner)" +
					" VALUES (?,?)";
			stmt = con.prepareStatement(query);
			stmt.setString(1, n.getName());
			stmt.setInt(2, mp.find(n.creator).bus_id);		
			stmt.executeUpdate();
			con=getConnection();
			Network ret = find(n.getName());
			query = "INSERT INTO User_Network (user_id,network_id,verified)" +
			" VALUES (?,?,1)";
			PreparedStatement stmt2 = con.prepareStatement(query);
			stmt2.setInt(1, mp.find(n.creator).bus_id);
			stmt2.setInt(2, ret.bus_id);
		
			stmt2.executeUpdate();
			
			closeConnection();
			return ret;

	} catch (Exception e) {
		e.printStackTrace();
		
		return null;
	} finally {
		closeConnection();
	}
	}
	public Network find(String name)
	{

		con = getConnection();
			// create a PreparedStatement
			Statement stmt;
			try {
				stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT n.id,n.name,r.username FROM Network n inner join registration r on r.id=n.owner where n.name = '" + name + "'");
				
				if (rs.next())
				{
					Network n = new Network();
					n.bus_id = rs.getInt(1);
					n.subject = rs.getString(2);
					n.creator = rs.getString(3);
					
					return n;
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return null;
	}
	public Network save(Network n)
	{
		con = getConnection();

		try {
			// create a Statement
			PreparedStatement stmt = null;

			
			String query = "UPDATE Network SET name=?,owner=?" +
					" WHERE id=?";
			stmt = con.prepareStatement(query);
			stmt.setString(1, n.getName());
			stmt.setString(2, n.creator);
			stmt.setInt(3, n.bus_id);
			stmt.executeUpdate();
			closeConnection();
			return find(n.getName());

	} catch (Exception e) {
		e.printStackTrace();
		
		return null;
	} finally {
		closeConnection();
	}
	}

	
}
