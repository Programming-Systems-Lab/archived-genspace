package genspace.networks.processors;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import org.geworkbench.components.genspace.bean.networks.Friend;
import org.geworkbench.components.genspace.bean.networks.Network;
import org.geworkbench.components.genspace.bean.networks.NetworkMessage;
import org.geworkbench.components.genspace.bean.networks.Profile;

import genspace.RuntimeEnvironmentSettings;
import genspace.networks.MessageProcessor;
import genspace.networks.NetworksHandler;

/**
 * Supported requests: list, create, delete
 * @author jon
 *
 */
public class FriendMessageProcessor extends MessageProcessor  {

	public NetworkMessage processMessage(NetworkMessage m) {
		if(m.reqType.equals(NetworkMessage.Request.CREATE))
		{
			return addFriend((Friend) m);
		}
		else if(m.reqType.equals(NetworkMessage.Request.LIST))
		{
			return listFriends((Friend) m);
		}
		else if(m.reqType.equals(NetworkMessage.Request.DELETE))
		{
			return deleteFriend((Friend) m);
		}
		else if(m.reqType.equals(NetworkMessage.Request.FIND) && m.subject == null)
		{
			return getAllUsers((Friend) m);
		}
		else if(m.reqType.equals(NetworkMessage.Request.FIND) && m.subject==m.sender)
		{
			return getPendingRequests((Friend) m);
		}
		else if(m.reqType.equals(NetworkMessage.Request.UPDATE))
		{
			return updateVisibility(m.sender,m.subject,m.visible);
		}
		return null;
	}
	/**
	 * Sets visibility of friend_other to user
	 * @param user
	 * @param friend_other
	 * @param visible
	 * @return
	 */
	public NetworkMessage updateVisibility(String user, String friend_other, boolean visible)
	{
		con = getConnection();
		
		try
		{
			ProfileMessageProcessor pm = new ProfileMessageProcessor();
			Profile u1_p=pm.find(user);
			Profile u2_p=pm.find(friend_other);
			int u1 = u1_p.bus_id;
			int u2 = u2_p.bus_id;
			PreparedStatement st;
			st = con.prepareStatement("UPDATE Friend set visible=? where id_1=? and id_2=?");
			st.setInt(1, (visible? 1 : 0));
			st.setInt(2,u1);
			st.setInt(3,u2);
			st.executeUpdate();
			HashSet<String> a = new HashSet<String>();
			a.add(friend_other);
			NetworksHandler.notifyAll(user, a, visible);
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	public NetworkMessage getPendingRequests(Friend m)
	{
		Friend ret = new Friend();
		ret.children = new LinkedList<NetworkMessage>();
		ProfileMessageProcessor p = new ProfileMessageProcessor();
		con = getConnection();
		// create a PreparedStatement
		Statement stmt;
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT r2.username,f.mutual FROM Friend f inner join registration r on r.id=f.id_2 inner join registration r2 on r2.id=f.id_1 where r.username = '" + m.sender+ "' and f.mutual =0");
			
			while (rs.next())
			{
				Friend n = new Friend();
				n.subject=rs.getString(1);
				n.isMutual=rs.getBoolean(2);
				n.profile=p.find(n.subject);
				ret.children.add(n);
			}
			closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
	/**
	 * Returns true if user1 can see user2
	 * @param user1
	 * @param user2
	 * @return
	 */
	public boolean isVisible(String user1,String user2)
	{
		if(user1.equals(user2))
			return true;
		con  = getConnection();
		Statement stmt;
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT f.visible FROM registration r2 inner join friend f on f.id_1=r2.id inner join registration r on r.id=f.id_2 and r.username = '" + user1+ "' and r2.username ='"+user2+"';");
			
			while (rs.next())
			{
				boolean t=rs.getBoolean(1);
				if(t)
					return true;
				else
					return false;
			}
			rs = stmt.executeQuery("SELECT un2.visible FROM registration r2 inner join user_network un2 on un2.user_id=r2.id inner join user_network un on un.network_id=un2.network_id" +
					" inner join registration r on r.id=un2.user_id and r.username = '" + user1+ "' and r2.username ='"+user2+"';");
			while (rs.next())
			{
				boolean t=rs.getBoolean(1);
				if(t)
					return true;
				else
					return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * Returns true if user1 is friends with user2
	 * @param user1
	 * @param user2
	 * @return
	 */
	public boolean isFriends(String user1,String user2)
	{
		if(user1.equals(user2))
			return true;
		con  = getConnection();
		Statement stmt;
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT f.id_2 FROM registration r2 inner join friend f on f.id_1=r2.id inner join registration r on r.id=f.id_2 and r.username = '" + user1+ "' and r2.username ='"+user2+"';");
			
			while (rs.next())
			{
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public NetworkMessage getAllUsers(Friend m)
	{
		con = getConnection();
		// create a PreparedStatement
		Friend ret = new Friend();
		ret.children = new ArrayList<NetworkMessage>();
		Statement stmt;
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT distinct r2.username,f.mutual,f.visible FROM registration r2 left join friend f on f.id_2=r2.id  and f.mutual=null left join registration r on r.id=f.id_1 and r.username = '" + m.sender+ "';");
			
			while (rs.next())
			{
				Friend n = new Friend();
				n.subject=rs.getString(1);
				n.isMutual=rs.getBoolean(2);
				n.visible=rs.getBoolean(3);
				n.details=(isVisible(m.sender,n.subject) ? "YES" : "NO");
				ret.children.add(n);
			}
			closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
	public NetworkMessage deleteFriend(Friend m) {
		con = getConnection();
		try {
			// create a Statement
			PreparedStatement stmt = null;

			ProfileMessageProcessor mp = new ProfileMessageProcessor();
			
			String query;
			
			query = "delete from Friend where id_1=? and id_2=?;";
			stmt = con.prepareStatement(query);
			stmt.setInt(2, mp.find(m.subject).bus_id);
			stmt.setInt(1, mp.find(m.sender).bus_id);	
			stmt.executeUpdate();
			
			query = "delete from Friend where id_2=? and id_1=?;";
			stmt = con.prepareStatement(query);
			stmt.setInt(2, mp.find(m.subject).bus_id);
			stmt.setInt(1, mp.find(m.sender).bus_id);	
			stmt.executeUpdate();
			
			
			closeConnection();
			return listFriends(m);

		} catch (Exception e) {
			e.printStackTrace();
			
			return null;
		} finally {
			closeConnection();
		}
	}

	public NetworkMessage listFriends(Friend m) {
		Friend ret = new Friend();
		ret.children = new LinkedList<NetworkMessage>();
		ProfileMessageProcessor p = new ProfileMessageProcessor();
		con = getConnection();
		// create a PreparedStatement
		Statement stmt;
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT r2.username,f.mutual,f.visible FROM Friend f inner join registration r on r.id=f.id_1 inner join registration r2 on r2.id=f.id_2 where f.mutual =1 and r.username = '" + m.sender+ "' ORDER BY r2.last_name asc, r2.first_name asc");
			
			while (rs.next())
			{
				Friend n = new Friend();
				n.subject=rs.getString(1);
				n.isMutual=rs.getBoolean(2);
				n.visible=rs.getBoolean(3);
				n.profile = p.find(n.subject,m.sender);
				n.details=(isVisible(m.sender,n.subject) ? "YES" : "NO");
				
				ret.children.add(n);
			}
			closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}

	public NetworkMessage addFriend(Friend m) {
		con = getConnection();
		Statement stmt;
		try
		{
			ProfileMessageProcessor pm = new ProfileMessageProcessor();
			System.out.println(m.sender + " wants to friend " + m.subject);
			Profile u1_p=pm.find(m.sender,m.sender);
			Profile u2_p=pm.find(m.subject,m.sender);
			int u1 = u1_p.bus_id;
			int u2 = u2_p.bus_id;
			stmt = con.createStatement();
			PreparedStatement st;
			ResultSet rs = stmt.executeQuery("SELECT f.id_1 FROM Friend f where  id_1= '" + u1 + "' and id_2='"+u2+"'");
			if(!rs.next())
			{
				st = con.prepareStatement("INSERT INTO Friend (mutual,id_1,id_2,visible) values(0,?,?,1);");
				st.setInt(1,u1);
				st.setInt(2,u2);
				st.executeUpdate();
				stmt = con.createStatement();
				
				rs = stmt.executeQuery("SELECT f.id_1 FROM Friend f where  id_2= '" + u1 + "' and id_1='"+u2+"'");
				if(rs.next())
				{
					//there is already a reverse friendship
					 st = con.prepareStatement("UPDATE Friend set mutual=1 where id_1=? and id_2=?");
					st.setInt(1,u1);
					st.setInt(2,u2);
					st.executeUpdate();
					st = con.prepareStatement("UPDATE Friend set mutual=1 where id_1=? and id_2=?");
					st.setInt(1,u2);
					st.setInt(2,u1);
					st.executeUpdate();
//					makeChatFriends(u1_p,u2_p);
				}
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return listFriends(m);
	}
	public void makeChatFriends(Profile c1, Profile c2)
	{
		friend(c1,c2);
		friend(c2,c1);
	}
	private void friend(Profile c1, Profile c2)
	{
		tigCon = getTigConnection();
		Statement stmt;
		try
		{
			stmt=tigCon.createStatement();
			PreparedStatement st;
			st = tigCon.prepareStatement("SELECT pval,nid from tig_pairs p inner join tig_users u on u.uid=p.uid where u.user_id=? and pkey='roster'");
			st.setString(1, c1.profile.get("username")+"@"+RuntimeEnvironmentSettings.XMPP_HOST);
			String roster ="";
			ResultSet rs = st.executeQuery();
			if(rs.next())
			{
				roster=rs.getString(0);
				roster = roster + "<contact name=\""+c2.profile.get("first_name") + " " + c2.profile.get("last_name")+"\" jid=\""+c2.profile.get("username")+"@"+RuntimeEnvironmentSettings.XMPP_HOST+"\" subs=\"both\"/>";
				PreparedStatement st2=tigCon.prepareStatement("UPDATE tig_pairs SET pval=? where nid=?");
				st2.setString(1, roster);
				st2.setInt(2, rs.getInt(2));
				st2.executeUpdate();
			}
			else
			{
				roster = "<contact name=\""+c2.profile.get("first_name") + " " + c2.profile.get("last_name")+"\" jid=\""+c2.profile.get("username")+"@"+RuntimeEnvironmentSettings.XMPP_HOST+"\" subs=\"both\"/>";
				st=tigCon.prepareStatement("SELECT u.uid,n.nid from tig_users u inner join tig_nodes n on u.uid=n.uid where u.user_id=? and n.node='root'");
				st.setString(1, c1.profile.get("username")+"@"+RuntimeEnvironmentSettings.XMPP_HOST);
				rs=st.executeQuery();
				rs.next();
				int uid = rs.getInt(1);
				int nid=rs.getInt(2);
				
				PreparedStatement st2=tigCon.prepareStatement("INSERT INTO tig_pairs (nid,uid,pkey,pval) VALUES (?,?,?,?)");
				st2.setInt(1, nid);
				st2.setInt(2,uid);
				st2.setString(3, "roster");
				st2.setString(4, roster);
				st2.executeUpdate();
				
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}

	}
}
