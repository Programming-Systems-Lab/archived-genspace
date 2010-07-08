package genspace.db;

import genspace.common.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/***********************************************************************************
 * 
 * This class holds all the code for dealing with suggestions of networks to join,
 * people to get in touch with, etc.
 * 
 * @author cmurphy
 *
 **********************************************************************************/


public class SuggestionManager extends NetworkManager 
{
	public SuggestionManager() { }

	
	/**
	 * This method suggests a network for a given user.
	 * It works by looking at all the people in the networks that the user currently belongs to.
	 * It then looks at THEIR networks, and returns the one that is most popular.
	 */
	public String suggestNetwork(String user)
	{
		// first get all the networks this user belongs to
		String[] myNetworks = getNetworksByUser(user);
		
		// if the user isn't in any networks or a db error occurred, just return null
		if (myNetworks == null || myNetworks.length == 0)
		{
			return null;
		}
		
		// now keep track of all the people in these networks
		ArrayList<String> userList = new ArrayList<String>();
		
		// loop through the Networks and put all the users into the ArrayList
		for (String network : myNetworks)
		{
			String[] users = getUsersByNetwork(network);
			
			for (String u : users)
			{
				// don't add it if (a) it's already in there or (b) it's the same user as the one from the parameter
				if (!userList.contains(u) && !u.equals(user)) 
				{
					userList.add(u);
					//System.out.println("Added user " + u.getUsername());
				}
			}
		}
		
		// TODO: I bet there's some fancy-ass data structures we could use here, but
		// I'm doing it brute-force style, baby

		// create a nifty fast-searchable HashSet for quick lookup later on
		HashSet<String> myNetworkSet = new HashSet<String>();
		for (String network : myNetworks)
		{
			myNetworkSet.add(network);
			//System.out.println("Added network " + network.getName());
		}
		
		// store all of the Networks and the number of members
		HashMap<String, Integer> networkMemberCount = new HashMap<String, Integer>();
		String[] allNetworks = getAllNetworks();
		for (String network : allNetworks)
		{
			// we only care about the networks this user is NOT in
			if (!myNetworkSet.contains(network))
			{
				networkMemberCount.put(network, 0);
				//System.out.println("Initialized " + network.getName());
			}
		}
		
		
		// loop through all the users in userList and count how many are in each network
		for (int i = 0; i < userList.size(); i++)
		{
			// get the user... we know from above that it won't be the user we are suggesting to
			String username = userList.get(i);
			//System.out.println("Considering " + username);
			// get that user's networks
			String[] networks = getNetworksByUser(username);
			
			// loop through that user's networks
			for (String network : networks)
			{
				// only increment if the user requesting the suggestion does NOT belong to this network
				if (!myNetworkSet.contains(network))
				{
					// now increment the counter
					Integer count = networkMemberCount.get(network);
					networkMemberCount.put(network, new Integer(++count));
					//System.out.println("Updated " + network.getName() + " to " + count);
				}
			}
		}
		
		// now find the entry in networkMemberCount with the highest number of members
		String suggestion = null;
		int max = 0;
		Object[] keys = networkMemberCount.keySet().toArray();
		for (int i = 0; i < keys.length; i++)
		{
			// figure out how many members this one has
			Integer count = networkMemberCount.get(keys[i]);
			// update the max if needed
			if (count.intValue() > max) 
			{
				max = count.intValue();
				suggestion = keys[i].toString();
			}
		}
		
		return suggestion;
	}
	
	
	/**
	 *  For the user specified in the parameter, this method tries to find another
	 *  user who belongs to similar groups.
	 */
	public String suggestFriend(String user)
	{
		// first get all the networks this user belongs to
		String[] myNetworks = getNetworksByUser(user);
		
		// if the user isn't in any networks or a db error occurred, just return null
		if (myNetworks == null || myNetworks.length == 0)
		{
			return null;
		}
		
		// TODO: can this be done more efficiently?
		
		// this tracks the number of groups that I am in with this other user
		HashMap<String, Integer> memberCount = new HashMap<String, Integer>();
		
		// loop through the Networks I'm in
		for (String network : myNetworks)
		{
			//System.out.println("Network " + network.getName());

			// get the users for this network
			String[] users = getUsersByNetwork(network);
			
			// loop through all the users
			for (String u : users)
			{
				//System.out.println("User " + u.getUsername());
				// ignore it if the user is me
				if (!u.equals(user))
				{
					// initialize to one if we don't have a record for this user
					if (!memberCount.containsKey(u))
					{
						memberCount.put(u, new Integer(0));
						//System.out.println("Initialized " + u.getUsername());
					}
					else
					{
						// increment the number of groups we're in together
						int count = memberCount.get(u).intValue();
						memberCount.put(u, ++count);
						//System.out.println("Updated " + u.getUsername() + " to " + count);
					}
				}
			}
		}
		

		// now find the entry in networkMemberCount with the highest number of members
		String suggestion = null;
		int max = 0;
		Object[] keys = memberCount.keySet().toArray();
		for (int i = 0; i < keys.length; i++)
		{
			// figure out how many groups in common this one has
			Integer count = memberCount.get(keys[i]);
			// update the max if needed
			if (count.intValue() > max) 
			{
				max = count.intValue();
				suggestion = keys[i].toString();
			}
		}
		
		return suggestion;
		
	}
	
	
	/**
	 * This method suggests an analysis tool for a given user.
	 * It returns a ranked list of tools, though currently without their respective weighting. 
	 * The myNetworks flag indicates whether or not to limit the search
	 */
	public String[] suggestAnalysisTool(String user, boolean myNetworks)
	{
		// first get all the analysis tools this user has used
		String[] myTools = getModifiedAnalysisByUser(user);
		// TODO: perhaps the tools the user has used should be weighted also
		
		// if the user hasn't used any analysis tools or a db error occurred, just return null
		if (myTools == null || myTools.length == 0)
		{
			return null;
		}
		
		// now keep track of all the people who have used these tools
		ArrayList<String> userList = new ArrayList<String>();
		
		// loop through the Networks and put all the users into the ArrayList
		for (String tool : myTools)
		{
			//System.out.println("MY TOOL: " + tool);
			
			String[] users = null;
			// call a different method depending on whether we want to limit the search
			if (myNetworks) users = getUsersByAnalysis(tool, user);
			else users = getUsersByAnalysis(tool);
			
			for (String u : users)
			{
				// don't add it if (a) it's already in there or (b) it's the same user as the one from the parameter
				if (!userList.contains(u) && !u.equals(user)) 
				{
					userList.add(u);
					//System.out.println("Added user " + u);
				}
			}
		}
		
		// TODO: I bet there's some fancy-ass data structures we could use here, but
		// I'm doing it brute-force style, baby

		// create a nifty fast-searchable HashSet for quick lookup later on
		HashSet<String> myAnalysisSet = new HashSet<String>();
		for (String tool : myTools)
		{
			myAnalysisSet.add(tool);
		}
		
		// store all of the tools and the total weight based on the exponential time decay function
		HashMap<String, Double> toolMemberCount = new HashMap<String, Double>();
		String[] allTools = getAllAnalysisTools();
		for (String tool : allTools)
		{
			// we only care about the tools this user has NOT used
			if (!myAnalysisSet.contains(tool))
			{
				toolMemberCount.put(tool, 0.0);
				//System.out.println("Initialized " + network.getName());
			}
		}
		
		
		// loop through all the users in userList and count how many are in each network
		for (int i = 0; i < userList.size(); i++)
		{
			// get the user... we know from above that it won't be the user we are suggesting to
			String username = userList.get(i);
			//System.out.println("Considering " + username);
			
			// get that user's analysis tools
			HashMap<String, Double> theirTools = getAnalysisByUser(username);
			
			
			// loop through that user's tools and update the overall total weight
			for (String tool : theirTools.keySet())
			{
				// only update if the user has NOT already used this tool
				if (!myAnalysisSet.contains(tool))
				{
					// update the total weight
					double weight = toolMemberCount.get(tool);
					weight += theirTools.get(tool);
					toolMemberCount.put(tool, weight);
					//System.out.println("Updated " + tool + " to " + weight);
				}
			}
		}

		/*
		// now find the entry in toolMemberCount with the highest weight
		String suggestion = null;
		double max = 0;
		for (String key : toolMemberCount.keySet())
		{
			// figure out how many members this one has
			double weight = toolMemberCount.get(key);
			//System.out.println(key + ": " + weight);
			// update the max if needed
			if (weight > max) 
			{
				max = weight;
				suggestion = key;
			}
		}
		*/

		
		// TODO: maybe return with the weights, too, so that the calling method can filter
		
		// fill up the array with the tools, sorted by their values
		String[] suggestions = new String[toolMemberCount.size()];
		
		for (int i = 0; i < suggestions.length; i++)
		{
			String suggestion = null;
			double max = 0;
			for (String key : toolMemberCount.keySet())
			{
				// figure out how many members this one has
				double weight = toolMemberCount.get(key);
				//System.out.println(key + ": " + weight);
				// update the max if needed
				if (weight > max) 
				{
					max = weight;
					suggestion = key;
				}
			}
			suggestions[i] = suggestion;
			toolMemberCount.remove(suggestion);
		}

		//for (String s : suggestions) { System.out.println(s); }
		
		return suggestions;
	}
	
	/**
	 *  For the user specified in the parameter, this method tries to find another
	 *  user who uses similar tools. Pretty much the same as the suggestFriend method.
	 *  The myNetworks flag indicates whether or not to limit the search to people
	 *  in this user's networks.
	 */
	public String suggestAnalysisToolPeer(String user, boolean myNetworks)
	{
		// first get all the tools this user uses, including the weights
		// that way, we favor tools that the user has recently used
		HashMap<String, Double> tools = getAnalysisByUser(user);
		
		// we also just need the list of tools
		String[] myTools = tools.keySet().toArray(new String[tools.size()]);
		
		// if the user doesn't use any tools or a db error occurred, just return null
		if (myTools == null || myTools.length == 0)
		{
			return null;
		}
		
		// TODO: can this be done more efficiently?
		
		// this tracks the number of tools that I have in common with the user, but multiplied by the "recency" factor
		HashMap<String, Double> toolCount = new HashMap<String, Double>();
		
		// loop through the tools I've used
		for (String tool : myTools)
		{
			// get the weight to indicate recency
			double factor = tools.get(tool);
			//System.out.println("Tool: " + tool + "; factor: " + factor);
			
			// get the users who have used this tool
			String[] users = null;
			// call a different method depending on whether we're limiting the search to the user's networks
			if (myNetworks) users = getUsersByAnalysis(tool, user);
			else users = getUsersByAnalysis(tool);
			
			// TODO: we only consider the current user's recent usage, not the others... 
			// this means that someone who used the tool a long time ago is considered equal to someone who recently used it
			
			// get the list of users who have opted out
			ArrayList<String> optedOut = getOptedOutUsers();
			if (optedOut == null) optedOut = new ArrayList<String>();
			
			// loop through all the users
			for (String u : users)
			{
				// ignore it if the user is me or if the user has opted out
				if (!u.equals(user) && optedOut.contains(u) == false)
				{
					// initialize if we don't have a record for this user
					if (!toolCount.containsKey(u))
					{
						toolCount.put(u, factor);
						//System.out.println("Initializing " + u + " to " + factor);
					}
					else
					{
						// increment the value by the recency factor
						double count = toolCount.get(u);
						toolCount.put(u, count + factor);
						//System.out.println("Updating " + u + " to " + (count+factor));
					}
				}
			}
		}
		

		// now find the entry in memberCount with the highest number of tools in common
		String suggestion = null;
		double max = 0;
		Object[] keys = toolCount.keySet().toArray();
		for (int i = 0; i < keys.length; i++)
		{
			// figure out how many tools in common this one has
			double count = toolCount.get(keys[i]);
			// update the max if needed
			if (count > max) 
			{
				max = count;
				suggestion = keys[i].toString();
			}
		}
		
		return suggestion;
		
	}
	
	
	/**
	 * Suggests a power user in genSpace, ie a person who uses it a lot. It's basically like looking for an expert
	 * except that it doesn't consider a particular tool.
	 * The myNetworks flag indicates whether or not to limit the search to the user's networks.
	 * 
	 * We use an exponential time decay function to weight usage.
	 * 
	 */
	public String suggestPowerUser(String user, boolean myNetworks)
	{
		String query;
		// NB: this takes into account the fact that some users have opted out, and it does not use the getOptedOutUsers method
		if (myNetworks) query = "SELECT username, date FROM analysis_events WHERE username <> '" + user + "' AND username <> 'anonymous' AND username NOT IN (SELECT DISTINCT username FROM opt_out) AND username IN (select DISTINCT username FROM user_networks WHERE network IN (SELECT DISTINCT network FROM user_networks WHERE username = '" + user + "') AND username <> '" + user + "') ORDER BY date";
		else query = "SELECT username, date FROM analysis_events WHERE username <> '" + user + "' AND username <> 'anonymous' AND username NOT IN (SELECT DISTINCT username FROM opt_out) ORDER BY date";
		return executeSuggestExpertQuery(query);
	}
	
	/**
	 * For the given analysis, recommend an "expert" (different from the specified user, of course).
	 * The myNetworks flag indicates whether or not to limit the search to the user's networks.
	 * 
	 * We use an exponential time decay function to weight usage.
	 * 
	 */
	public String suggestExpert(String analysis, String user, boolean myNetworks)
	{
		String query = null;
		// NB: this takes into account the fact that some users have opted out, and it does not use the getOptedOutUsers method
		if (myNetworks) query = "SELECT username, date FROM analysis_events WHERE username <> '" + user + "' AND username <> 'anonymous' AND username AND analysis = '" + analysis + "' AND username IN (select DISTINCT username FROM user_networks WHERE network IN (SELECT DISTINCT network FROM user_networks WHERE username = '" + user + "') AND username <> '" + user + "') ORDER BY date";
		else query = "SELECT username, date FROM analysis_events WHERE username <> '" + user + "' AND username <> 'anonymous' AND analysis = '" + analysis + "' ORDER BY date";
		return executeSuggestExpertQuery(query);
	}
	
	/**
	 * Helper method that can be used by the suggestPowerUser or suggestExpert method to run the query.
	 * 
	 * TODO: could we combine this with other "find" methods?
	 */
	private String executeSuggestExpertQuery(String query)
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
				java.util.Date now = new java.util.Date();
				
				// keeps track of each user's use of this particular tool, with the username as the key and the weight as the value
				HashMap<String, Double> usage = new HashMap<String, Double>();
				
				while (rs.next())
				{
					String username = rs.getString("username");
					Date date = rs.getDate("date");
					//System.out.println(username + " " + date);
					
					long diff = (now.getTime() - date.getTime()) / (3600 * 24 * 1000);
					//System.out.println(diff);
					double value = Math.exp(-diff);
					
					if (usage.containsKey(username) == false)
					{
						// if it's not already in there, put it in
						usage.put(username, value);
						//System.out.println("Initialized " + username + " to " + value);
					}
					else
					{
						// it's already there, so update it
						double val = usage.get(username);
						val += value;
						usage.put(username, val);
						//System.out.println("Updated " + username + " by " + value + " to " + val);
					}
				}
				
				// now find the user with the maximum weight
				double max = 0;
				String expert = null;
				for (String username : usage.keySet())
				{
					double value = usage.get(username);
					if (value > max)
					{
						max = value;
						expert = username;
					}
				}
				
				return expert;
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
	 * For the given analysis, recommend an "expert" (different from the specified user, of course)
	 * who is currently logged into genSpace.
	 * 
	 * Currently, we just see who has used that analysis the most.
	 * TODO: make this smarter wrt who the expert is
	 * TODO: only consider registered genSpace users 
	 * 
	 */
	public String suggestLoggedInExpert(String analysis, String user)
	{
		try
		{
			con = getConnection();

			if (con != null)
			{
				// create a Statement
				Statement stmt = con.createStatement();

				// now create the SQL query
				String query = "SELECT username, count(*) FROM analysis_events a WHERE username <> '" + user + "' AND analysis = '" + analysis +"' AND username in (SELECT username FROM login_events WHERE datediff(minute, date, getdate()) < 60) GROUP BY username ORDER BY count(*) DESC";

				// execute the query
				ResultSet rs = stmt.executeQuery(query);

				if (rs.next())
				{
					return rs.getString("username");
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
	 * Add an analysis tool so that it appears that the user has actually used it. This
	 * is handy for the other suggestions.
	 */
	public void includeAnalysis(String username, String analysis)
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
				String query = "INSERT INTO include_exclude_analysis (username, analysis, action) VALUES ('" + username + "', '" + analysis + "', 'INCLUDE')";

				// execute the query
				stmt.executeUpdate(query);
            }
            else System.out.println("Error: No active Connection");
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
	}

	/*
	 * Add an analysis tool so that it appears that the user has NOT actually used it. This
	 * is handy for the other suggestions.
	 */
	public void excludeAnalysis(String username, String analysis)
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
				String query = "INSERT INTO include_exclude_analysis (username, analysis, action) VALUES ('" + username + "', '" + analysis + "', 'EXCLUDE')";

				// execute the query
				stmt.executeUpdate(query);
            }
            else System.out.println("Error: No active Connection");
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
	}

	/**
	 * Returns a list of all the analysis tools to include when making suggestions.
	 */
	public String[] getInclusions(String username)
	{
		try
		{
			con = getConnection();

			if (con != null)
			{
				// create a Statement
				Statement stmt = con.createStatement();

				// now create the SQL query
				String query = "SELECT distinct analysis from include_exclude_analysis where username = '" + username + "' and action = 'INCLUDE'";

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
					// get the "analysis" column for this particular result
					String analysis = rs.getString("analysis");
					results.add(analysis);
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
	 * Returns a list of all the analysis tools to exclude when making suggestions.
	 */
	public String[] getExclusions(String username)
	{
		try
		{
			con = getConnection();

			if (con != null)
			{
				// create a Statement
				Statement stmt = con.createStatement();

				// now create the SQL query
				String query = "SELECT distinct analysis from include_exclude_analysis where username = '" + username + "' and action = 'EXCLUDE'";

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
					// get the "analysis" column for this particular result
					String analysis = rs.getString("analysis");
					results.add(analysis);
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
	 * This method returns the list of analysis tools used by this user, but also
	 * considers the explicit inclusions and exclusions
	 */
	public String[] getModifiedAnalysisByUser(String user)
	{
		// an ArrayList to hold all the tools
		ArrayList<String> toolSet = new ArrayList<String>();
		
		// get the keySet (distinct tool names) from the getAnalysisByUser method
		toolSet.addAll(getAnalysisByUser(user).keySet());

		// now, include the ones that the user has explicitly asked to include
		String[] include = getInclusions(user);
		for (String tool : include) if (!toolSet.contains(tool)) toolSet.add(tool);
		
		// then, the ones that the user does NOT want to include
		// TODO: this give priority to exclusions... is that what we want?
		String[] exclude = getExclusions(user);
		for (String tool : exclude) if (toolSet.contains(tool)) toolSet.remove(tool);
		
		return toolSet.toArray(new String[toolSet.size()]);
		
	}
	
	/**
	 * This method adds a user to the list of people who don't want to be considered for peer and expert suggestions.
	 * It returns true if the operation was successful and false if there was an error
	 */
	public boolean optOut(String username)
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
				String query = "INSERT INTO opt_out (username) VALUES ('" + username + "')";

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
	 * This method removes a user from the list of people who don't want to be considered for peer and expert suggestions.
	 * It returns true if the operation was successful and false if there was an error
	 */
	public boolean optIn(String username)
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
				String query = "DELETE FROM opt_out WHERE username = '" + username + "'";

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
	 * Returns the list of users who have opted out of being recommended for suggestions of peers and experts
	 * Currently only used by suggestAnalysisToolPeer
	 */
	private ArrayList<String> getOptedOutUsers()
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
				String query = "SELECT DISTINCT username FROM opt_out";

				// execute the query
				ResultSet rs = stmt.executeQuery(query);
				
				// store the results
				ArrayList<String> users = new ArrayList<String>();
				while(rs.next())
				{
					users.add(rs.getString("username"));
				}
				
				return users;
				
            }
            else System.out.println("Error: No active Connection");
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
	 * FOR TEST PURPOSES ONLY
	 */
	public static void main(String[] args)
	{
		SuggestionManager s = new SuggestionManager();
		String expert = s.suggestExpert("ARACNE", "chris", false);
		System.out.println(expert);

		//String tool = s.suggestAnalysisTool("floratos", false)[0];
		//System.out.println(tool);
		
		//String user = s.suggestPowerUser("chris", true);
		//System.out.println(user);
		
	}
}
