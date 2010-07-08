package genspace.db;

import org.geworkbench.components.genspace.RuntimeEnvironmentSettings;
import org.geworkbench.components.genspace.bean.*;

import genspace.common.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/** 
 * 
 * This class is used to manage the network visibility preferences of the user. 
 * 
 * */
public class NetworkVisibilityServerManager extends DatabaseManager {

	/**
	 * This method checks whether the user has the visibility option for specified network.
	 * 
	 * @param username represents the username of user
	 * 
	 * @param network represents the network name.
	 * 
	 * @return returns true if user is visible else returns false.
	 */
	public boolean isUserVisibile4Network(String username, String network) {
		try {
			con = getConnection();

			if (con != null) {

				String query = "SELECT networkname from network_visibility where username = ? and user_data_option = 1";
				PreparedStatement stmt = con.prepareStatement(query);
				stmt.setString(1, username);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					String net = rs.getString("networkname");
					if (net.compareToIgnoreCase(network) == 0)
						return true;
				}
			}
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

	/**
	 * This method is used to set the user visibility preference for selected networks.
	 * 
	 * @param nbean represents the NetworkVisibilityBean from which user's data can be extracted.
	 * 
	 * @return returns true when successful else returns false.
	 */

	public boolean setUserVisibilityWithinNetworks(NetworkVisibilityBean nbean) {
		try {
			// get a database connection
			con = getConnection();

			if (con != null) {

				List net = nbean.getSelectedNetworks();
				Object[] networks = net.toArray();
				for (int i = 0; i < networks.length; i++) {
					String query = "INSERT INTO network_visibility(username,user_data_option, networkname) VALUES (?,1,?)";
					PreparedStatement stmt = con.prepareStatement(query);
					stmt.setString(1, nbean.getUsername());
					stmt.setString(2, networks[i].toString());
					stmt.executeUpdate();
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
		return true;
	}

	/** 
	 * This method is used to update the user's visibility preferences.
	 * 
	 * @param nbean represents the NetworkVisibilityBean from which user's data can be extracted.
	 * 
	 * @return returns true on successful update else returns false.
	 * 
	 * */
	public boolean setUserVisibility(NetworkVisibilityBean nbean) {
		try {
			// get a database connection
			con = getConnection();

			if (con != null) {
				String query = "UPDATE user_visibility set uservisibility = ? where username = ?";
				PreparedStatement stmt = con.prepareStatement(query);
				stmt.setInt(1, nbean.getUserVisibility());
				stmt.setString(2, nbean.getUsername());
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
	 * This method returns the user visibility preference of a user.
	 * 
	 * @param username represents the username of the user.
	 * 
	 * @return represents the uservisibility preference of user.
	 * 
	 * */
	public int getUserVisibility(String username) {
		int uservisibility = -1;
		
		try {
			// get a database connection
			con = getConnection();

			if (con != null) {
				String query = "SELECT uservisibility from user_visibility where username = ?;";
				PreparedStatement stmt = con.prepareStatement(query);
				stmt.setString(1, username);
				ResultSet rs = stmt.executeQuery();

				if (rs.next()) {
					uservisibility = rs.getInt("uservisibility");
				} else {
					// no user with this username exists
					return -1;
				}

			} else
				System.out.println("Error: No active Connection");
		} catch (Exception e) {
			e.printStackTrace();
			if (Logger.isLogError())
				Logger.logError(e);
			return -1;
		} finally {
			closeConnection();
		}
		return uservisibility;
	}
	
	/** 
	 * This method returns the list of all the networks available in the system.
	 * 
	 * @return represents the list of networks.
	 * 
	 * */
	public String[] listNetworks() {
		String[] networks = null;
		try {
			// get a database connection
			con = getConnection();

			String cntQuery = "SELECT COUNT(network) as count from networks";
			PreparedStatement stmt = con.prepareStatement(cntQuery);
			ResultSet rs1 = stmt.executeQuery();
			int cnt = 0;
			if (rs1.next()) {
				cnt = rs1.getInt("count");
			}
			networks = new String[cnt];
			if (con != null) {
				String query = "SELECT network from networks";
				stmt = con.prepareStatement(query);
				rs1 = stmt.executeQuery();
				int i = 0;
				while (rs1.next()) {
					networks[i] = rs1.getString("network");
					i++;
				}
			} else
				System.out.println("Error: No active Connection");
		} catch (Exception e) {
			e.printStackTrace();
			if (Logger.isLogError())
				Logger.logError(e);
			return networks;
		} finally {
			closeConnection();
		}

		return networks;
	}

	/** 
	 * This method is used to get the user's preference.
	 * 
	 * @param userId represents the username of the user.
	 * 
	 * @return returns nbean which represents the NetworkVisibilityBean from which user's data can be extracted.
	 * 
	 * */

	NetworkVisibilityBean getBeanForUser(String userId) {
		NetworkVisibilityBean bean = new NetworkVisibilityBean();
		try {
			con = getConnection();
			String query = "select uservisibility from user_visibility "
					+ " where username = ?";
			String query1 = "select networkname from network_visibility where username =? and user_data_option = 1";
			if (con != null) {
				PreparedStatement ps = con.prepareStatement(query);
				ps.setString(1, userId);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) // expect only one record.
				{
					bean.setUserVisibility(rs.getShort(1));
					ps = con.prepareStatement(query1);
					ps.setString(1, userId);
					ResultSet rs1 = ps.executeQuery();
					List<String> selectedNetworks = new ArrayList<String>();
					while (rs1.next()) {
						String name = rs1.getString(1);
						selectedNetworks.add(name);
					}
					bean.setSelectedNetworks(selectedNetworks);
					bean.setMessage("Success");
				} else {
					bean.setMessage("Failure");
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			if (Logger.isLogError())
				Logger.logError(e);
			return null;
		} finally {
			closeConnection();
		}
		return bean;
	}

	/** 
	 * This method is used to check whether userA is visible to userB.
	 * 
	 * @param userA represents the username of the userA.
	 * 
	 * @param userB represents the username of the userB.
	 * 
	 * @return returns true if visible else returns false.
	 * 
	 * */

	public boolean isUserAVisibileToUserB(String userA, String userB) {
		
		if ( userA.equals(RuntimeEnvironmentSettings.DEFAULT_USER)) 
			return true;
		
		boolean retFlag = false;
		
		try {
			NetworkVisibilityBean userAPreferences = getBeanForUser(userA);
			List networkListA ;

			if(userAPreferences.getUserVisibility() == 0)
				return false;
			if (userAPreferences.getUserVisibility() == 1) // visibile in his networks
			{
				// get his networks
				networkListA = new ArrayList<String>();
				con = getConnection();
				if (con != null) {

					String query = "SELECT distinct network from user_networks where username = ? ";
					PreparedStatement stmt = con.prepareStatement(query);
					stmt.setString(1, userA);
					ResultSet rs = stmt.executeQuery();
					while (rs.next()) {
						String net = rs.getString("network");
						networkListA.add(net);				}
				}
			}
			else // (userAPreferences.getUserVisibility() == 2) // visibile in his networks
			{
				networkListA = userAPreferences.getSelectedNetworks();
			}
			// Now get user B's networks
			ArrayList<String> networkListB = new ArrayList<String>();
			con = getConnection();
			if (con != null) {

				String query = "SELECT distinct network from user_networks where username = ? ";
				PreparedStatement stmt = con.prepareStatement(query);
				stmt.setString(1, userB);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					String net = rs.getString("network");
					networkListB.add(net);				}
			}

			//iterate thorugh A list and check if it is a part of B's list
			for (int i =0; i < networkListA.size(); i++)
			{
				boolean ret = networkListB.contains( networkListA.get(i));
				if (ret)
				{
					retFlag = true;
					break;
				}
			}	
			//retFlag = false;
		
		} catch (Exception e) {
			e.printStackTrace();
			if (Logger.isLogError())
				Logger.logError(e);
			retFlag= false;
		} finally {
			closeConnection();
		}
		return retFlag;
	}
	

	/** 
	 * This method returns all the users who has the visibility permission for
	 * networks that belongs to userA. It takes into account the userA visibility preference 
	 * and returns the usernames accordingly.
	 * 
	 * @param userA represents the username of user A
	 * 
	 * @return returns the list of usernames who has the network visibility permission.
	 *  
	 * */
	public List<String> getAllUsersVisibleToUserA(String userA) {
		List<String> usernames = new ArrayList<String>();

		int uservisibility = getUserVisibility(userA);
		String query = "";

		try {
			// get a database connection
			con = getConnection();

			if (uservisibility == 1) {
				query = "SELECT  distinct username  FROM user_networks where network IN "
						+ "(select network from user_networks un, user_visibility uv where "
						+ "un.username = uv.username and uv.username = ? and uv.uservisibility = 1)";
			} else if (uservisibility == 2) {
				query = "SELECT  distinct username  FROM user_networks where network IN "
						+ "(select nv.networkname from network_visibility nv, user_visibility uv where "
						+ " nv.user_data_option = 1 and nv.username = uv.username and uv.username = ? and uv.uservisibility = 2)";
			} else {
				// user not visible.
				return usernames;
			}
			if (con != null) {
				PreparedStatement stmt = con.prepareStatement(query);
				stmt.setString(1, userA);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					String username = rs.getString("username");
					usernames.add(username);
				}
			} else
				System.out.println("Error: No active Connection");
		} catch (Exception e) {
			e.printStackTrace();
			if (Logger.isLogError())
				Logger.logError(e);
			return usernames;
		} finally {
			closeConnection();
		}

		return usernames;
	}
	
	/** 
	 * This method returns all the networks which has the visibility permission for
	 * userA. It takes into account the userA visibility preference and returns the
	 * networks accordingly.
	 * 
	 * @param userA represents the username of user A
	 * 
	 * @return returns the list of networks that has the user visibility permission.
	 *  
	 * */

	public List<String> getAllNetworksForUserAVisibility(String userA) {
		List<String> networks = new ArrayList<String>();

		int uservisibility = getUserVisibility(userA);
		String query = "";

		try {
			// get a database connection
			con = getConnection();

			if (uservisibility == 1) {
				query = "select network from user_networks un, user_visibility uv where "
						+ "un.username = uv.username and uv.username = ? and uv.uservisibility = 1";
			} else if (uservisibility == 2) {
				query = "select nv.networkname from network_visibility nv, user_visibility uv where "
						+ " nv.user_data_option = 1 and nv.username = uv.username and uv.username = ? and uv.uservisibility = 2";
			} else {
				// user not visible.
				return networks;
			}
			if (con != null) {
				PreparedStatement stmt = con.prepareStatement(query);
				stmt.setString(1, userA);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					String network = rs.getString(1);
					networks.add(network);
				}
			} else
				System.out.println("Error: No active Connection");
		} catch (Exception e) {
			e.printStackTrace();
			if (Logger.isLogError())
				Logger.logError(e);
			return networks;
		} finally {
			closeConnection();
		}

		return networks;
	}
	
	/**
	 * This method is used to remove the selected networks of the user having user visibility.
	 * 
	 * @param userId represents the username of user.
	 * 
	 * @return returns true on success else returns false.
	 */
	boolean removeSelectedNetworks(String userId)
	{
        boolean success= false;
		DataVisibilityBean bean = new DataVisibilityBean();
		try{
			System.out.println("User:"+userId);
			con = getConnection();
			String query = "delete from network_visibility " 
				+" where user_data_option = 1 and username = ?";
			if(con != null){ 
				PreparedStatement ps = con.prepareStatement(query);
				ps.setString(1, userId);
				ps.executeUpdate();
				success = true;
				}
		}
		catch(Exception e){
			e.printStackTrace();
			if (Logger.isLogError())
				Logger.logError(e);
			success = false;
		}
		finally{
			closeConnection();
		}
		return success;	
	}
}
