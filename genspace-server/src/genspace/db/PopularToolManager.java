package genspace.db;

import genspace.common.Logger;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Date;

public class PopularToolManager extends DatabaseManager {

	/**
	 * Returns the most popular tool in terms of usage, across ALL networks
	 */
	public String getMostPopularTool()
	{
		String query = "SELECT analysis, date FROM analysis_events";
		return findMostPopularTool(query);
	}
	
	/**
	 * Returns the most popular tool amongst users in the network
	 */
	public String getMostPopularToolInNetwork(String network)
	{
		String query = "SELECT analysis, date FROM analysis_events WHERE username IN (SELECT DISTINCT username FROM user_networks WHERE network = '" + network + "')";
		return findMostPopularTool(query);
	}
	
	/** 
	 * Returns the most popular tool in the networks to which the user belongs
	 */
	public String getMostPopularToolInUsersNetworks(String username)
	{
		String query = "SELECT analysis, date FROM analysis_events WHERE username IN (select DISTINCT username FROM user_networks WHERE network IN (SELECT DISTINCT network FROM user_networks WHERE username = '" + username + "') AND username <> '" + username + "')";	
		return findMostPopularTool(query);
	}	
	
	/**
	 * Helper method that executes the query and returns the result
	 */
	protected String findMostPopularTool(String query)
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
				
				// the current date
				Date now = new Date();

				// keep track of the tools (key) and their weight (value), based on an exponential time-decay function
				HashMap<String, Double> usage = new HashMap<String, Double>();
				
				while (rs.next())
				{
					String tool = rs.getString("analysis");
					Date date = rs.getDate("date");
					
					long diff = (now.getTime() - date.getTime()) / (3600 * 24 * 1000);
					double value = Math.exp(-diff);
					
					if (usage.containsKey(tool) == false)
					{
						// if it's not already there, put it there
						usage.put(tool, value);
						//System.out.println("Initialized " + tool + " to " + value);
					}
					else
					{
						// already there, so update it
						double val = usage.get(tool);
						val += value;
						usage.put(tool, val);
						//System.out.println("Updated " + tool + " by " + value + " to " + val);
					}
				}

				// now find the one with the most weight
				String mostPopular = null;
				double max = 0;
				for (String tool : usage.keySet())
				{
					double value = usage.get(tool);
					if (value > max)
					{
						max = value;
						mostPopular = tool;
					}
				}

				return mostPopular;
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
	 * @param args
	 */
	public static void main(String[] args) {
		PopularToolManager m = new PopularToolManager();
		System.out.println("Most popular tool is " + m.getMostPopularTool());
		System.out.println("----------------");
		System.out.println("Most popular tool in Columbia network is " + m.getMostPopularToolInNetwork("Columbia"));
		System.out.println("----------------");
		System.out.println("Most popular tool in chris' networks is " + m.getMostPopularToolInUsersNetworks("chris"));
		
		

	}

}
