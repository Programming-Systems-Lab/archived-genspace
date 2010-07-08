package genspace.db.util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import genspace.common.Logger;
import genspace.db.DatabaseConnector;
import genspace.db.WorkflowManager;
import genspace.db.WorkflowNode;

public class WorkflowTreePopulator extends DatabaseConnector{

	public ArrayList<String> obtainTransactionIds(){
		ArrayList<String> result = new ArrayList();
		try {
			// create a Statement
			Statement stmt = getConnection().createStatement();
			
			// execute the query
			ResultSet rs = stmt.executeQuery("SELECT DISTINCT transaction_id FROM analysis_events WHERE transaction_id IS NOT NULL");

			while (rs.next())
				result.add(rs.getString("transaction_id"));	
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
		return result;
	}
	
	public void populateTree(ArrayList<String> transactionIds, boolean writeToDb){
		try {
			PreparedStatement transactionWorkflowsStatement = getConnection().prepareStatement(
					"SELECT ae.analysis, ae.date " +
					"FROM analysis_events ae " +
					"WHERE transaction_id = ? ORDER BY ae.date ASC");
			
			WorkflowManager wm = new WorkflowManager();
		
			for (String transaction : transactionIds){
				System.out.print("Inserting transaction '" + transaction + "' into tree...(\n");
				
				transactionWorkflowsStatement.setString(1, transaction);
				ResultSet rs = transactionWorkflowsStatement.executeQuery(); 
				
				int parent = -1, counter = 0;
				
				while (rs.next()){
					WorkflowNode existsNode = wm.getWorkflowNode(rs.getString(1), parent, false);
					if (existsNode == null){
						counter++;
						System.out.println("\t" + rs.getString(1) + " (NOT EXISTS)");
					}
					else {
						System.out.println("\t" + rs.getString(1) + " (" + existsNode.getId() + ")");
					}
					
					
					WorkflowNode currentNode = null;
					
					if (writeToDb)
						currentNode = wm.getWorkflowNode(rs.getString(1), parent, true);
					else 
						currentNode = existsNode;
					parent = currentNode.getId();
				}
					
				
				System.out.println(")\nCompleted. (Created " + counter + " new nodes.)");
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
	}
	
	public static void main(String args[]){
		WorkflowTreePopulator populator = new WorkflowTreePopulator();
		System.out.print("Obtaining all workflow transactions in db...");
		ArrayList<String> a = populator.obtainTransactionIds();
		System.out.println("Done.");
		
		
		System.out.print("Populating tree...");
		populator.populateTree(a, false);
		System.out.println("Done.");
	}
	
}
