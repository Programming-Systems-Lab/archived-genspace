package genspace.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.geworkbench.components.genspace.bean.*;
import genspace.common.Logger;

/** 
 * 
 * This class is used to manage the data visibility preferences of the user. 
 * 
 * */

public class DataVisibilityServerManager extends DatabaseManager {

	/**
	 * This method checks whether the user has the data visibility option for specified network.
	 * 
	 * @param username represents the username of user
	 * 
	 * @param network represents the network name.
	 * 
	 * @return returns true if data is visible else returns false.
	 */
	public boolean isUserDataVisibile4Network(String username, String network) {
		try {
			con = getConnection();

			if (con != null) {
				String query = "SELECT networkname from network_visibility where username = ? and user_data_option = 0";
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
	 * This method is used to set the data visibility preference of the user for selected networks.
	 * 
	 * @param dbean represents the DataVisibilityBean from which user's data can be extracted.
	 * 
	 * @return returns true when successful else returns false.
	 */
	public boolean setDataVisibilityWithinNetworks(DataVisibilityBean dbean) {
		try {
			// get a database connection
			con = getConnection();

			if (con != null) {
				// create a Statement
				List net = dbean.getSelectedNetworks();
				Object[] networks = net.toArray();
				
				for (int i = 0; i < networks.length; i++) {
					System.out.println("Working on " +networks[i].toString());
					String query = "INSERT INTO network_visibility(username, user_data_option, networkname) VALUES (?,0,?)";
					PreparedStatement stmt = con.prepareStatement(query);
					stmt.setString(1, dbean.getUsername());
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
	 * This method returns the data visibility preference of a user.
	 * 
	 * @param username represents the username of the user.
	 * 
	 * @return returns the datavisibility preference of user else returns -1.
	 * 
	 * */
	public int getDataVisibility(String username) {
		int datavisibility = -1;

		try {
			// get a database connection
			con = getConnection();

			if (con != null) {
				String query = "SELECT datavisibility from data_visibility where username = ?;";
				PreparedStatement stmt = con.prepareStatement(query);
				stmt.setString(1, username);
				ResultSet rs = stmt.executeQuery();

				if (rs.next()) {
					datavisibility = rs.getInt("datavisibility");
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
		return datavisibility;
	}

	/** 
	 * This method checks the user's log data preference.
	 * 
	 * @param dbean represents the DataVisibilityBean from which user's data can be extracted.
	 * 
	 * @return returns logdata value else returns -1.
	 * 
	 * */
	public int canLogData(DataVisibilityBean dbean) {
		int logdata = 0;
		try {
			con = getConnection();

			if (con != null) {
				String query = "SELECT logdata from data_visibility where username = ?";
				PreparedStatement stmt = con.prepareStatement(query);
				stmt.setString(1, dbean.getUsername());
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					logdata = rs.getInt("logdata");
				} else {
					System.out
							.println("No corresponding row for the username: "
									+ dbean.getUsername());
					return -1;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (Logger.isLogError())
				Logger.logError(e);
			return -1;
		} finally {
			closeConnection();
		}
		return logdata;
	}

	/** 
	 * This method is used to update the user's data preferences.
	 * 
	 * @param dbean represents the DataVisibilityBean from which user's data can be extracted.
	 * 
	 * @return returns true on successful update else returns false.
	 * 
	 * */
	public boolean editLoggingOption(DataVisibilityBean dbean) {
		try {
			con = getConnection();

			if (con != null) {
				String query = "UPDATE data_visibility set logdata = ?, datavisibility = ? where username = ?";
				PreparedStatement stmt = con.prepareStatement(query);
				stmt.setInt(1, dbean.getLogData());
				stmt.setInt(2, dbean.getDataVisibility());
				stmt.setString(3, dbean.getUsername());
				stmt.executeUpdate();
			}

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
	 * This method is used to get the user's data preference.
	 * 
	 * @param userId representst the username of the user.
	 * 
	 * @return returns dbean which represents the DataVisibilityBean from which user's data can be extracted.
	 * 
	 * */
	DataVisibilityBean getBeanForUser(String userId) {
		DataVisibilityBean bean = new DataVisibilityBean();
		try {
			con = getConnection();
			String query = "select logData, dataVisibility from data_visibility "
					+ " where username = ?";
			String query1 = "select networkname from network_visibility where username =? and user_data_option = 0";
			if (con != null) {
				PreparedStatement ps = con.prepareStatement(query);
				ps.setString(1, userId);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) // expect only one record.
				{
					bean.setLogData(rs.getShort("logData"));
					bean.setDataVisibility(rs.getShort("dataVisibility"));
					ps = con.prepareStatement(query1);
					ps.setString(1, userId);
					ResultSet rs1 = ps.executeQuery();
					List<String> selectedNetworks = new ArrayList<String>();
					while (rs1.next()) {
						String name = rs1.getString("networkname");
						selectedNetworks.add(name);
						System.out.println("Addeed "+name);
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
	 * This method is used to remove the selected networks of the user having data visibility.
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
			String query = "delete  from network_visibility " 
				+" where user_data_option = 0 and username = ?";
			System.out.println("Deleting for "+userId);
			if(con != null){ 
				PreparedStatement ps = con.prepareStatement(query);
				ps.setString(1, userId);
				int rows  =ps.executeUpdate();
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
	

	/** 
	 * This method returns all the users who has the visibility permission for
	 * data that belongs to userA. It takes into account the userA data visibility preference 
	 * and returns the usernames accordingly.
	 * 
	 * @param userA represents the username of user A
	 * 
	 * @return returns the list of usernames who has the data visibility permission.
	 *  
	 * */
	public List<String> getAllUsersVisibleToUserAData(String userA) {
		List<String> usernames = new ArrayList<String>();

		int datavisibility = getDataVisibility(userA);
		String query = "";

		try {
			// get a database connection
			con = getConnection();

			if (datavisibility == 1) {
				query = "SELECT  distinct username  FROM user_networks where network IN "
						+ "(select network from user_networks un, data_visibility dv where "
						+ "un.username = dv.username and dv.username = ? and dv.datavisibility = 1)";
			} else if (datavisibility == 2) {
				query = "SELECT  distinct username  FROM user_networks where network IN "
						+ "(select nv.networkname from network_visibility nv, data_visibility dv where "
						+ " nv.user_data_option = 0 and nv.username = dv.username and dv.username = ? and dv.datavisibility = 2)";
			} else {
				// data not visible.
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
	 * data that belongs to userA. It takes into account the userA data visibility preference 
	 * and returns the networks accordingly.
	 * 
	 * @param userA represents the username of user A
	 * 
	 * @return returns the list of networks that has the data visibility permission.
	 *  
	 * */
	public List<String> getAllNetworksForUserADataVisibility(String userA) {
		List<String> networks = new ArrayList<String>();

		int datavisibility = getDataVisibility(userA);
		String query = "";

		try {
			// get a database connection
			con = getConnection();

			if (datavisibility == 1) {
				query = "select distinct network from user_networks un, data_visibility dv where "
						+ "un.username = dv.username and dv.username = ? and dv.datavisibility = 1";
			} else if (datavisibility == 2) {
				query = "select distinct nv.networkname from network_visibility nv, data_visibility dv where "
						+ " nv.user_data_option = 0 and nv.username = dv.username and dv.username = ? and dv.datavisibility = 2";
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
}
