package genspace.db;

import genspace.common.Logger;

import java.sql.*;
import java.util.ArrayList;

public class WorkflowDatabaseManager extends DatabaseConnector {
	
	Connection conn;
	
	/**
	 * Gets all workflows involving the given analysis
	 * @param analysis The name of the analysis for which we want to find all workflows
	 * @return List of Workflows
	 */
	public String[] getAllWorkflows(String analysis) {
		try {
			conn = getConnection();
			
			if (conn != null) {
				// create a Statement
				Statement stmt = con.createStatement();

				// now create the SQL query
				String query = "select analysis, transaction_id from [Genspace].[dbo].[test_analysis_events] where transaction_id in (select transaction_id from (select transaction_id, count(transaction_id) as t_id_count from [Genspace].[dbo].[test_analysis_events] where transaction_id in (select distinct transaction_id from [Genspace].[dbo].[test_analysis_events] where analysis = '" + analysis + "') group by transaction_id ) p	where p.t_id_count > 1 ) order by date";
				
				// execute the query
				ResultSet rs = stmt.executeQuery(query);
				
				// we can't actually know the size of the ResultSet in advance
				// so we can't create an array... yet
				// use an ArrayList for now
				ArrayList<String> results = new ArrayList<String>();

				// iterate through the ResultSet and populate the ArrayList
				int count = 0;
				String transaction_id = "-1";
				String workflow = "";
				while (rs.next())
				{
					// get the "analysis" column for this particular result
					String current_analysis = rs.getString(1);
					String current_transaction = rs.getString(2);
					if (transaction_id.equals(current_transaction)) {
						workflow += "," + current_analysis;
					} else {
						if (!workflow.equals("")) {
							results.add(workflow);
							count++;
						}
							workflow = current_analysis;
							transaction_id = current_transaction;
					}
				}
				
				results.add(workflow);
				count++;

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
	
	public String getMostRepeatedWorkflowContaining(String analysis) {
		return getMostRepeatedWorkflow(getAllWorkflows(analysis));
	}
	
	public String getMostRepeatedWorkflowStartingWith(String analysis) {
		ArrayList<String> results = new ArrayList<String>();
		int count = 0;
		String[] workflows = getAllWorkflows(analysis);
		
		for (String s : workflows) {
			if (s.startsWith(analysis)) {
				results.add(s);
				count++;
			}
		}	
		
		return getMostRepeatedWorkflow(results.toArray(new String[count]));
	}
	
	private String getMostRepeatedWorkflow(String[] workflows) {
		int i, j;
		int max_count = 0;
		String mostRepeated = "";
		
		for(i = 0; i<workflows.length; i++) {
			String current = workflows[i];
			int count = 1;
			for (j=i+1; j<workflows.length; j++) {
				if (current.equals(workflows[j])) {
					count++;
				}
			}
			if (count > max_count) {
				max_count = count;
				mostRepeated = current;
			}
		}
		
		return mostRepeated;
	}
	
	public static void main(String[] args) {
		WorkflowDatabaseManager wdm = new WorkflowDatabaseManager();
		String analysis = "Net Boost";
		String[] results = wdm.getAllWorkflows(analysis);
		
		for (String s : results) {
			System.out.println(s);
		}
		
		System.out.println("\n\n");
		System.out.println(wdm.getMostRepeatedWorkflowContaining(analysis));
		
		System.out.println("\n\n");
		System.out.println(wdm.getMostRepeatedWorkflowStartingWith(analysis));
	}
}
