package genspace.db;

import genspace.common.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;

public class WorkflowManager extends DatabaseConnector {
	
	
	/**
	 *  finds the workflow node of the last element in toolListing by traversing the tree
	 *  returns null if not found (or not created, if createifnotexist = true)
	 * @param toolListing A string array of all tools from the beginning of the workflow
	 */
	public WorkflowNode getWorkflowNode(String[] toolListing, boolean createIfNotExist){
		int parent = -1;
		WorkflowNode result = null;
		
		for(String tool : toolListing){
			result = getWorkflowNode(tool, parent, createIfNotExist);
			parent = result.getId();
		}
		
		return result;
	}
	
	public WorkflowNode getWorkflowNode(int id){
		try {		
			PreparedStatement selectNodeStatement = getConnection().prepareStatement(
				"SELECT * FROM workflows WHERE id = ?");
	
			selectNodeStatement.setInt(1, id);
			
			ResultSet rs = selectNodeStatement.executeQuery();
			rs.next();
			return new WorkflowNode(rs.getInt("id"), rs.getInt("parent"), rs.getString("tool"));
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
	
	
	public WorkflowNode[] getChildren(int parent){
		ArrayList<WorkflowNode> children = new ArrayList();
		try {		
			Statement selectChildren = getConnection().createStatement();
			
			ResultSet rs = selectChildren.executeQuery("SELECT * FROM workflows WHERE parent = " + parent);
			
			while (rs.next()){
				WorkflowNode current = 
					new WorkflowNode(rs.getInt("id"), rs.getInt("parent"), rs.getString("tool"));
				children.add(current);
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
		if (children.size() == 0) return null;
		return children.toArray(new WorkflowNode[1]);
	}
	
	/**
	 * gives you the workflow node with toolName and parent
	 * @param createIfNotExist If true and the node does not exist, 
	 * 							it will be created in the tree (so make sure the parent is actually there!)
	 * @return the WorkflowNode found or null otherwise
	 */
	public WorkflowNode getWorkflowNode(String toolName, int parent, boolean createIfNotExist){
		try {		
			PreparedStatement selectNodeStatement = getConnection().prepareStatement(
				"SELECT * FROM workflows WHERE tool COLLATE SQL_Latin1_General_CP1_CS_AS = ? and parent = ?");
	
			selectNodeStatement.setString(1, toolName);
			selectNodeStatement.setInt(2, parent);
			
			ResultSet rs = selectNodeStatement.executeQuery();
			
			if (!rs.next()) {
				if (createIfNotExist){
					PreparedStatement insertIntoTreeStatement = getConnection().prepareStatement(
							"INSERT INTO workflows (tool, parent) VALUES (?, ?)");
				
					PreparedStatement scopeIdentityStatement = getConnection().prepareStatement(
							"SELECT IDENT_CURRENT('workflows')");
					
					insertIntoTreeStatement.setString(1, toolName);
					insertIntoTreeStatement.setInt(2, parent);
					insertIntoTreeStatement.execute();
					
					ResultSet scopeIdRS = scopeIdentityStatement.executeQuery();
					scopeIdRS.next();
					
					return new WorkflowNode(scopeIdRS.getInt(1), parent, toolName);
				}
				else return null;
			}
			return new WorkflowNode(rs.getInt("id"), parent, toolName);
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
	 * Gets all workflows involving the given analysis
	 * @param analysis The name of the analysis for which we want to find all workflows
	 * @return a HashMap mapping the workflow names to their weights, determined by recency
	 * The myNetworks flag indicates whether to search only in the user's networks, assuming username is not null
	 */
	public HashMap<String, Double> getAllWorkflows(String analysis, String username, boolean myNetworks) {
		try {
			con = getConnection();
			
			if (con != null) {
				// create a Statement
				Statement stmt = con.createStatement();

				// now create the SQL query
				String query;
				// see if we need to limit the query to workflows conducted by people in the user's networks
				if (myNetworks && username != null)
				{
					// note that this WILL return workflows conducted by this user
					query = "select date, analysis, transaction_id from [Genspace].[dbo].[analysis_events] where " +
							"username IN (select DISTINCT username FROM user_networks WHERE network IN (SELECT DISTINCT network FROM user_networks WHERE username = '" + username + "')) and " +
							"transaction_id in (select transaction_id from (select transaction_id, count(transaction_id) as t_id_count from [Genspace].[dbo].[analysis_events] where transaction_id in (select distinct transaction_id from [Genspace].[dbo].[analysis_events] where analysis = '" + analysis + "') group by transaction_id ) p	where p.t_id_count > 1 ) order by transaction_id, date";
				}
				else
					query = "select date, analysis, transaction_id from [Genspace].[dbo].[analysis_events] where transaction_id in (select transaction_id from (select transaction_id, count(transaction_id) as t_id_count from [Genspace].[dbo].[analysis_events] where transaction_id in (select distinct transaction_id from [Genspace].[dbo].[analysis_events] where analysis = '" + analysis + "') group by transaction_id ) p	where p.t_id_count > 1 ) order by transaction_id, date";
				
				// execute the query
				ResultSet rs = stmt.executeQuery(query);

				// store the workflow name and its exponentially decayed recency
				HashMap<String, Double> results = new HashMap<String, Double>();

				// iterate through the ResultSet and populate the ArrayList
				String transaction_id = "-1";
				String workflow = "";
				Date last_date = null;
				Date now = new Date();
				
				// indicates whether there were any results
				boolean foundResults = false;
				
				while (rs.next())
				{
					foundResults = true;
					
					// get the "analysis" column for this particular result
					String current_analysis = rs.getString("analysis");
					String current_transaction = rs.getString("transaction_id");
					// it's the same workflow, so keep appending
					if (transaction_id.equals(current_transaction)) {
						workflow += "," + current_analysis;
						last_date = rs.getDate("date");
					} 
					// it's a new workflow, so update the previous one
					else 
					{
						if (!workflow.equals("")) 
						{
							// use the last_date to get the exponentially decayed weight
							long diff = (now.getTime() - last_date.getTime()) / (3600 * 24 * 1000);
							//System.out.println(diff);

							// now figure out the "value", using the exponential decay function
							double value = Math.exp(-diff);
							
							// if the workflow is already in there, update it... otherwise, create a new entry
							if (results.keySet().contains(workflow))
							{
								double val = results.get(workflow);
								results.put(workflow, value + val);
								//System.out.println("Updated " + workflow + " to " + (value+val));
							}
							else
							{
								results.put(workflow, value);
								//System.out.println("Initialized " + workflow + " to " + value);
							}
						}

						// since we're on to a new workflow, update the variables
						workflow = current_analysis;
						transaction_id = current_transaction;
						last_date = rs.getDate("date");
					}
				}
				
				if (foundResults)
				{
					// don't forget the last one... assuming there is one
					long diff = (now.getTime() - last_date.getTime()) / (3600 * 24 * 1000);
					double value = Math.exp(-diff);
					if (results.keySet().contains(workflow))
					{
						double val = results.get(workflow);
						results.put(workflow, value + val);
						//System.out.println("Updated " + workflow + " to " + (value+val));
					}
					else
					{
						results.put(workflow, value);
						//System.out.println("Initialized " + workflow + " to " + value);
					}
				}
				
				/*
				for (String key : results.keySet())
				{
					System.out.println(key + ": " + results.get(key));
				}
				*/
				
				/*
				double[] values = new double[results.size()];
				for (int i = 0; i < values.length; i++)
				{
					String maxKey = null;
					double max = 0;
					for (String key : results.keySet())
					{
						double weight = results.get(key);
						if (weight > max) 
						{
							max = weight;
							maxKey = key;
						}
					}
					values[i] = results.get(maxKey);
					results.remove(maxKey);
				}
				for (double d : values) System.out.println(d);
				*/
				
				// purge anything that is more than two orders of magnitude smaller than the max
				
				// first find the max value
				double max = 0;
				for (String key : results.keySet())
				{
					double weight = results.get(key);
					if (weight > max) 
					{
						max = weight;
					}
				}
				//System.out.println("MAX " + max);

				// now remove anything that is too small
				// NB: we can't remove stuff while iterating
				String[] keys = results.keySet().toArray(new String[results.size()]);
				for (String key : keys)
				{
					double weight = results.get(key);
					if (max / weight > 100)
					{
						results.remove(key);
					}
				}
				
				//for (String key : results.keySet()) System.out.println(key + " " + results.get(key));
				
				return results;
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
	 * This finds the most common workflow containing the analysis. It looks for ALL workflows containing
	 * the analysis, and then finds the one that has the highest value based on recency of use.
	 * The myNetworks flag indicates whether to search only in the user's networks, assuming username is not null
	 */
	public String getMostRepeatedWorkflowContaining(String analysis, String username, boolean myNetworks) {
		return getMostRepeatedWorkflow(getAllWorkflows(analysis, username, myNetworks));
	}
	
	/**
	 * This finds the most common workflow starting with the analysis. It looks for ALL workflows containing
	 * the analysis, weeds out the ones not starting with the analysis, and then finds the one that has the 
	 * highest value based on recency of use.
	 * The myNetworks flag indicates whether to search only in the user's networks, assuming username is not null
	 */
	public String getMostRepeatedWorkflowStartingWith(String analysis, String username, boolean myNetworks) {
		// get all the workflows
		HashMap<String, Double> workflows = getAllWorkflows(analysis, username, myNetworks);
		
		// loop through the workflows and remove any that don't start with the analysis
		String[] workflowNames = workflows.keySet().toArray(new String[workflows.size()]);
		// NB: we can't iterate using workflows.keySet because we'll get an exception when we try to remove something
		for (String workflow : workflowNames)
		{
			if (workflow.startsWith(analysis) == false)
			{
				workflows.remove(workflow);
			}
		}
		
		// now find the most common one
		return getMostRepeatedWorkflow(workflows);
	}

	/**
	 * This is a helper method that looks through the HashMap containing workflows mapped to 
	 * values (based on frequency and recency) and returns the one with the highest value
	 */
	private String getMostRepeatedWorkflow(HashMap<String, Double> workflows)
	{
		String mostRepeated = null;
		double max = 0;
		for (String workflow : workflows.keySet())
		{
			if (workflows.get(workflow) > max)
			{
				max = workflows.get(workflow);
				mostRepeated = workflow;
			}
		}
		return mostRepeated;
	}

	
	/**
	 * Returns a list of all tools in the analysis_event table. This is used by the Workflow Visualization UI
	 * component to populate the dropdown list of tools from which the user can choose one.
	 * TODO: this is a duplicate of DatabaseManager.getAllAnalysisTools()!!!!
	 */
	public ArrayList<String> getAllTools()
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
				String query = "SELECT DISTINCT analysis FROM analysis_events ORDER BY analysis";

				// execute the query
				ResultSet rs = stmt.executeQuery(query);
				
				// store the results
				ArrayList<String> tools = new ArrayList<String>();
				while(rs.next())
				{
					tools.add(rs.getString("analysis"));
				}
				
				return tools;
				
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
	
	public static void main(String[] args) {
		WorkflowManager wdm = new WorkflowManager();
		
		String[] testArray = {"ARACNE", "ARACNE", "T Test Analysis"};
		System.out.println(wdm.getWorkflowNode(testArray, false));
	}
}
