package genspace.db;

import genspace.common.Logger;
import genspace.common.Event;
import genspace.common.AnalysisEvent;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/***********************************************************************************
 * 
 * This class holds all the code for dealing with the backend database.
 * 
 * General TODO list:
 * 1. Remove hardcoded queries from methods
 * 2. What to do when there is a database connectivity problem
 * 3. Possibly separate functionality
 * 4. Introduce some sort of caching
 * 5. Security
 * 6. Thread-safety
 * 
 * @author cmurphy
 *
 **********************************************************************************/


public class DatabaseManager extends DatabaseConnector
{
	// Constructor
	public DatabaseManager()
	{

	}

	
	
	/**
	 * This method inserts an event into the database by figuring out what type of event it is 
	 * and calling the appropriate method
	 * @param e the Event object to insert into the database
	 * @return whether or not the operation succeeded
	 */
	public boolean insertEvent(Event e)
	{
		if (e instanceof AnalysisEvent)
			return insertAnalysisEvent((AnalysisEvent)e);
		else
			return false;
	}
	
	
	/**
	 * This method inserts an AnalysisEvent into the database table.
	 * @param e
	 * @return true if success, false if a database error occurred
	 */
	public boolean insertAnalysisEvent(AnalysisEvent e)
	{
		try
		{
			// get a database connection
			con = getConnection();
			
			if(con != null)
			{
				// create a PreparedStatement
				PreparedStatement stmt = con.prepareStatement("INSERT INTO analysis_events (username, host, date, analysis, dataset, transaction_id) VALUES (?, ?, ?, ?, ?, ?)");
				
				stmt.setString(1, e.getUser());
				stmt.setString(2, e.getHost());
				stmt.setString(3, e.getTime());
				stmt.setString(4, e.getAnalysis());
				stmt.setString(5, e.getDataset());
				stmt.setString(6, e.getTransaction_id());
				
				// execute the query
				stmt.executeUpdate();
				
				Map parameters = e.getParameters();
				Set keys = parameters.keySet();
				
				for (Object key : keys) {
					Object value = parameters.get(key);
					
					PreparedStatement stmt1 = con.prepareStatement("INSERT INTO analysis_events_parameters (date, analysis, transaction_id, parameter_key, parameter_value) VALUES (?, ?, ?, ?, ?)");
					
					stmt1.setString(1, e.getTime());
					stmt1.setString(2, e.getAnalysis());
					stmt1.setString(3, e.getTransaction_id());
					stmt1.setString(4, key.toString());
					stmt1.setString(5, value.toString());
					
					// execute the query
					stmt1.executeUpdate();
					
				}
				
				
				
				//update the tools table
				if (getToolId(e.getAnalysis()) == -1){
					//Statement insertToolStmt = con.createStatement();
					//insertToolStmt.executeQuery("INSERT INTO tools (tool) VALUES ('" + e.getAnalysis() + "')");
				}
					
				
			}
			else if (Logger.isLogError()) Logger.logError("Error: No active Connection");
			
		}
		catch(Exception error)
		{
			error.printStackTrace();
            if (Logger.isLogError()) Logger.logError(error);
		}
		finally
		{
			closeConnection();
		}
		
		return true;		
	}
	
	public int getToolId(String tn){
		try
		{
			// get a database connection
			con = getConnection();
			
			if(con != null)
			{
				// create a PreparedStatement
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT id FROM tools where tool = '" + tn + "'");
				
				if (rs.next())
					return rs.getInt(1);
				else return -1;
			}
			else if (Logger.isLogError()) Logger.logError("Error: No active Connection");
			
		}
		catch(Exception error)
		{
			error.printStackTrace();
            if (Logger.isLogError()) Logger.logError(error);
		}
		finally
		{
			closeConnection();
		}
		
		return -1;		
	}

	
	/**
	 * This method is used to get all of the analysis tools that a user has used. It returns null
	 * if there is a database error. It's pretty much the same as getNetworksByUser.
	 * 
	 * TODO: refactor!!!
	 * 
	 * @param the ID of the user
	 * @return a HashMap with the names of the analysis tools as keys and their weighted values as, um, values
	 */
	public HashMap<String, Double> getAnalysisByUser(String username)
	{
		// store all the results with the String as the key and the exponentially weighted time as the value
		HashMap<String, Double> results = new HashMap<String, Double>(); 
		
		try
		{
			con = getConnection();

			if (con != null)
			{
				// create a Statement
				Statement stmt = con.createStatement();

				// now create the SQL query
				String query = "SELECT analysis, date FROM analysis_events WHERE username = '" + username + "' ORDER BY date";

				// execute the query
				ResultSet rs = stmt.executeQuery(query);

				// the current date
				Date now = new Date();
				
				// iterate through the ResultSet and populate the HashMap
				while (rs.next())
				{
					// figure out how long ago they used the tool
					Date date = rs.getDate("date");
					//System.out.println(date);
					
					long diff = (now.getTime() - date.getTime()) / (3600 * 24 * 1000);
					//System.out.println(diff);

					// now figure out the "value", using the exponential decay function
					double value = Math.exp(-diff);
					//System.out.println(value);
					
					// get the "analysis" column for this particular result
					String analysis = rs.getString("analysis");

					if (results.containsKey(analysis) == false) 
					{	
						// if it's not already in there, put it in
						results.put(analysis, value);
						//System.out.println("Initializing " + analysis + " to " + value);
					}
					else
					{
						// if we already have it, update the value
						double val = results.get(analysis);
						val += value;
						results.put(analysis, val);
						//System.out.println("Updated " + analysis + " by " + value + " to " + val);
					}
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

		return results;
	}

		

	
	/**
	 * This method is used to get all of the users who have used a particular analysis tool.
	 * The search is limited to only those people in the specified user's networks 
	 * It returns null
	 * if there is a database error. It's pretty much the same as getUsersByNetwork.
	 * 
	 * @param the name of the analysis tool
	 * @return an array of Strings of the user names
	 */
	public String[] getUsersByAnalysis(String analysis, String username)
	{
		String query = "SELECT DISTINCT username FROM analysis_events WHERE analysis = '" + analysis + "' AND username IN (select DISTINCT username FROM user_networks WHERE network IN (SELECT DISTINCT network FROM user_networks WHERE username = '" + username + "') AND username <> '" + username + "')";
		return executeGetUsersByAnalysisQuery(query);
	}
	
	/**
	 * This method is used to get all of the users who have used a particular analysis tool. It returns null
	 * if there is a database error. It's pretty much the same as getUsersByNetwork.
	 * 
	 * @param the name of the analysis tool
	 * @return an array of Strings of the user names
	 */
	public String[] getUsersByAnalysis(String analysis)
	{
		String query = "SELECT DISTINCT username FROM analysis_events WHERE analysis = '" + analysis + "' AND username <> 'anonymous' ORDER BY username";
		return executeGetUsersByAnalysisQuery(query);
	}
	
	
	/**
	 * Helper method to run the query to find users based on their analysis tool usage.
	 */
	private String[] executeGetUsersByAnalysisQuery(String query)
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
	
	
	
	/**
	 * This method is used to get all of the analysis tools in the system. It returns null
	 * if there is a database error.
	 * TODO: this is a duplicate of WorkflowManager.getAllTools()!!!!
	 * 
	 */
	public String[] getAllAnalysisTools()
	{
		try
		{
			con = getConnection();

			if (con != null)
			{
				// create a Statement
				Statement stmt = con.createStatement();

				// now create the SQL query
				String query = "SELECT DISTINCT analysis FROM analysis_events ORDER BY analysis";

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
					results.add(rs.getString("analysis"));
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
	 * This method finds out the IM handle of the given user on the given IM service (YAHOO, MSN, etc.)
	 */
	public String getUserIMHandle(String user, String service)
	{
		try
		{
			con = getConnection();

			if (con != null)
			{
				// create a Statement
				Statement stmt = con.createStatement();

				// now create the SQL query
				String query = "SELECT IM_handle FROM user_IM_handles WHERE username = '" + user + "' AND IM_service = '" + service + "'";

				// execute the query
				ResultSet rs = stmt.executeQuery(query);

				if (rs.next())
				{
					return rs.getString("IM_handle");
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
	

	public static void main(String[] args)
	{
		DatabaseManager m = new DatabaseManager();
		HashMap<String, Double> results = m.getAnalysisByUser("Swapneel");
		for (String key : results.keySet())
			System.out.println(key + " " + results.get(key));

	}
	

}