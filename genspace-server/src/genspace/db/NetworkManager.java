package genspace.db;

import genspace.common.Logger;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class NetworkManager extends DatabaseManager {

	/**
	 * This method adds a new network to the system.
	 * 
	 * @param name the name of the network to add
	 * @return whether or not adding the network succeeded
	 */
	public boolean addNetwork(String name)
	{
		try
		{
			// get a database connection
			con = getConnection();

			if(con != null)
			{
				// create a Statement
				Statement stmt = con.createStatement();

				// now create the SQL query
				// TO DO: how do we get the id??
				String query = "INSERT INTO networks (network) VALUES ('" + name + "')";

				// execute the query
				stmt.executeUpdate(query);
            }
            else System.out.println("Error: No active Connection");
        }
        catch(Exception e)
        {
			e.printStackTrace();
            if (Logger.isLogError()) Logger.logError(e);
			return false;
		}
		finally
		{
			closeConnection();
		}
		
		return true;
	}

	
	
	/**
	 * This method is used to get all of the Networks in the system. It returns null
	 * if there is a database error.
	 * 
	 * @return an array of all Networks in the database
	 */
	public String[] getAllNetworks()
	{
		String query = "SELECT network FROM networks ORDER BY network";
		return findNetworks(query);
	}
		
	
	/**
	 * This method is used to get all of the Networks to which a user belongs. It returns null
	 * if there is a database error.
	 * 
	 * @param the ID of the user
	 * @return an array of all networks to which the user belongs
	 */
	public String[] getNetworksByUser(String user_name)
	{
		String query = "SELECT DISTINCT network FROM user_networks where username = '" + user_name + "' ORDER BY network";
		return findNetworks(query);
	}
	
	/**
	 * A helper method to run a query to get a bunch of networks.
	 */
	private String[] findNetworks(String query)
	{
		try
		{
			con = getConnection();

			if (con != null)
			{
				// create a Statement
				Statement stmt = con.createStatement();

				// execute the query
				ResultSet rs = stmt.executeQuery(query);

				// we can't actually know the size of the ResultSet in advance
				// so we can't create an array... yet
				// use an ArrayList for now
				ArrayList<String> results = new ArrayList<String>();

				// iterate through the ResultSet and populate the ArrayList
				int count = 0;
				while (rs.next())
				{
					// get the "network" column for this particular result
					String network = rs.getString("network");
					results.add(network);
					count++;
				}

				// now return the results
				if (count == 0)
				{
					return new String[0];
				}
				else
				{
					return results.toArray(new String[count]);
				}
			}
		}
        catch(Exception e)
        {
			e.printStackTrace();
            if (Logger.isLogError()) Logger.logError(e);
		}
		finally
		{
			closeConnection();
		}

		return null;
	}
	

	/**
	 *  This method adds a new user to a network.
	 *  
	 *  @return whether or not the operation succeeded
	 */
	public boolean addUserToNetwork(String user, String network)
	{
		try
		{
			// get a database connection
			con = getConnection();

			if(con != null)
			{
				// create a Statement
				Statement stmt = con.createStatement();

				// now create the SQL query
				// TODO: if the user is already in the network, then this will yield a database error because of the primary key constraint 
				String query = "INSERT INTO user_networks (username, network) VALUES ('" + user + "', '" + network + "')";

				// execute the query
				stmt.executeUpdate(query);
            }
            else System.out.println("Error: No active Connection");
        }
        catch(Exception e)
        {
			e.printStackTrace();
            if (Logger.isLogError()) Logger.logError(e);
			return false;
		}
		finally
		{
			closeConnection();
		}
		
		return true;
	}

	/**
	 * This method is used to get all of the users who belong to a network. It returns null
	 * if there is a database error.
	 * 
	 * @param the ID of the network
	 * @return an array of all Users who belong to the network
	 */
	public String[] getUsersByNetwork(String network)
	{
		try
		{
			con = getConnection();

			if (con != null)
			{
				// create a Statement
				Statement stmt = con.createStatement();

				// now create the SQL query
				// String query = "SELECT u.id, u.username, u.email, u.organization FROM users u, user_networks un where un.network = '" + network + "' and un.username = u.id";
				String query = "SELECT username FROM user_networks where network = '" + network + "' ORDER BY username";

				// execute the query
				ResultSet rs = stmt.executeQuery(query);

				// we can't actually know the size of the ResultSet in advance
				// so we can't create an array... yet
				// use an ArrayList for now
				ArrayList<String> results = new ArrayList<String>();

				// iterate through the ResultSet and populate the ArrayList
				int count = 0;
				while (rs.next())
				{
					/*
					int id = rs.getInt("id");
					String name = rs.getString("username");
					String email = rs.getString("email");
					String organization = rs.getString("organization");
					results.add(new User(id, name, email, organization));
					*/
					results.add(rs.getString("username"));
					count++;
				}

				// now return the results
				if (count == 0)
				{
					return new String[0];
				}
				else
				{
					return results.toArray(new String[count]);
				}
	
			}
		}
        catch(Exception e)
        {
			e.printStackTrace();
            if (Logger.isLogError()) Logger.logError(e);
		}
		finally
		{
			closeConnection();
		}

		return null;
	}
		
	/*
	 * FOR TEST PURPOSES
	 */
	public static void main(String[] args)
	{
		NetworkManager nm = new NetworkManager();
		System.out.println(nm.addUserToNetwork("chris", "Columbia"));
	}
	
}
