package genspace.db;

import genspace.common.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.*;
import java.util.*;


/**
 * This class is part of the server side suggestion engine implemented 
 * This class implements all the abstract methods defined in ISBUSuggestionEngine
 * @author cheng
 *
 */

public class ISBUSuggestionEngineImpl extends DatabaseManager implements ISBUSuggestionEngine {

	private static final String FILE_PATH = "DataAnalysisResult.gs";
	private ObjectInputStream input;
	private ISBUManager dbManager;



	/**
	 * Constructor
	 *  
	 */
	public ISBUSuggestionEngineImpl()  {

	}



	public void engineInitialization() throws Exception{

		try {

			//first we read in the analysis result file stored on the server side
			System.out.println("ISBU Suggestion Engine - reading index file");
			File fileFromStorage = new File(FILE_PATH);
			input = new ObjectInputStream(new FileInputStream(fileFromStorage));
			dbManager = (ISBUManager) input.readObject();
			input.close();
		}
		catch (Exception e) {
			System.out.println("Error:" + e);
		}

	}




	public ArrayList <String> getAllAnalysisTools1() {

		return dbManager.getAnalysisToolSet();
	}


	public ArrayList <String> getTop3MostPopularTools() {

		ArrayList <String> top3Tools = new ArrayList<String>();

		ArrayList sortedList = dbManager.getSortedIndex1By1stValue();

		//we are sure that the total number of tools is larger than 3, so we just get the first 3 elements
		for (int i = 0; i < 3; i++) {
			top3Tools.add(((CompareUnit)(sortedList.get(i))).getName());
		}
		return top3Tools;
	}

	public ArrayList <String> getMostPopularTools() {

		ArrayList <String> top3Tools = new ArrayList<String>();

		ArrayList<CompareUnit> sortedList = dbManager.getSortedIndex1By1stValue();

		for(CompareUnit u : sortedList)
			top3Tools.add(u.getName());

		return top3Tools;
	}
	public ArrayList <String> getMostPopularWFHead() {

		ArrayList <String> top3Heads = new ArrayList();

		ArrayList<CompareUnit> sortedList = dbManager.getSortedIndex1By2ndValue();

		for(CompareUnit u : sortedList)
			top3Heads.add(u.getName());
		return top3Heads;
	}
	public ArrayList <String> getTop3MostPopularWFHead() {

		ArrayList <String> top3Heads = new ArrayList();

		ArrayList sortedList = dbManager.getSortedIndex1By2ndValue();

		for (int i = 0; i < 3 ; i++) {
			top3Heads.add(((CompareUnit)(sortedList.get(i))).getName());
		}
		return top3Heads;
	}


	public ArrayList <String> getTop3MostPopularWF() {

		ArrayList <String> top3WFs = new ArrayList();

		ArrayList sortedList = dbManager.getSortedIndex2ByOnlyValue();

		for (int i = 0; i < 3; i++) {
			top3WFs.add(((CompareUnit)(sortedList.get(i))).getName());
		}
		return top3WFs;
	}
	public ArrayList <String> getMostPopularWF() {

		ArrayList <String> top3WFs = new ArrayList<String>();

		ArrayList<CompareUnit> sortedList = dbManager.getSortedIndex2ByOnlyValue();

		for(CompareUnit u : sortedList)
			top3WFs.add(u.getName());

		return top3WFs;
	}



	public String getUsageRate(String toolName){

		ArrayList valueList = dbManager.getAnalysisToolIndex().get(toolName);
		Integer result = (Integer)(valueList.get(0));
		return result.toString();

	}



	public String getUsageRateAsWFHead(String toolName) {

		ArrayList valueList = dbManager.getAnalysisToolIndex().get(toolName);
		Integer result = (Integer)(valueList.get(1));
		return result.toString();
	}



	public String getMostPopularNextTool(String toolName) {

		ArrayList valueList = dbManager.getAnalysisToolIndex().get(toolName);
		String result = (String) (valueList.get(2));
		return result.toString();
	}


	public String getMostPopularPreviousTool(String toolName) {

		ArrayList valueList = dbManager.getAnalysisToolIndex().get(toolName);
		String result = (String) (valueList.get(3));
		return result.toString();
	}



	public ArrayList <String> getTop3MostPopularWFForThisTool(String toolName) {
		ArrayList valueList = dbManager.getAnalysisToolIndex().get(toolName);
		ArrayList <String> results = new ArrayList();
		if (valueList.get(4) != null) {
			results.add((String) (valueList.get(4)));
		}
		if (valueList.get(5) != null) {
			results.add((String) (valueList.get(5)));
		}
		if (valueList.get(6) != null) {
			results.add((String) (valueList.get(6)));
		}

		return results;

	}


	public HashSet <String> getAllWFsIncludingThisTool(String toolName) {
		ArrayList valueList = dbManager.getAnalysisToolIndex().get(toolName);
		HashSet <String> results = (HashSet) valueList.get(8);
		return results;
	}


	public String getMostCommonWFIncludingThisTool(String toolName) {
		ArrayList valueList = dbManager.getAnalysisToolIndex().get(toolName);
		String result = (String) valueList.get(4);
		return result;
	}


	public String getMostCommonWFStartingWithThisTool(String toolName) {
		ArrayList <CompareUnit> sortedIndex2 = (ArrayList) dbManager.getSortedIndex2ByOnlyValue();
		for (int i = 0; i < sortedIndex2.size(); i++) {
			String currentWorkFlow = ((CompareUnit) sortedIndex2.get(i)).getName();
			StringTokenizer tokens = new StringTokenizer(currentWorkFlow, ",");
			String head = tokens.nextToken();
			if (head.equals(toolName)) { //we get the most common Workflow starting with this tool
				return currentWorkFlow;
			}

		}
		return "N/A";

	}






	//Instance messaging APIs-----------------------------------------------
	//************************By Gaurav*************************************

	public ArrayList <String> getListOfUserNames(String current_user) {

		ArrayList <String> allUserList = new ArrayList();

		try
		{
			// get a database connection
			con = getConnection();

			if(con != null)
			{
				String cntQuery = "(select username from [Genspace].[dbo].[registration]) EXCEPT (SELECT [username] FROM [Genspace].[dbo].[registration] where username = '" + current_user +"')";
				PreparedStatement stmt1 = con.prepareStatement(cntQuery);
				ResultSet rs = stmt1.executeQuery();


				while (rs.next()) {
					allUserList.add(rs.getString(1));
				}
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

		return allUserList;

	}

	public ArrayList <String> getListOfNetworks(String current_user) {

		ArrayList <String> allUserList = new ArrayList();

		try
		{
			// get a database connection
			con = getConnection();

			if(con != null)
			{
				String cntQuery = "SELECT [networkname] FROM [Genspace].[dbo].[network_visibility] where username= '" + current_user + "' order by networkname";
				PreparedStatement stmt1 = con.prepareStatement(cntQuery);
				ResultSet rs = stmt1.executeQuery();


				// create a Statement
//				Statement stmt = con.createStatement();

//				String sql =  "SELECT [networkname] FROM [Genspace].[dbo].[network_visibility] where username= '" + current_user + "'";

//				ResultSet rs = stmt.executeQuery(sql);

				while (rs.next()) {
					allUserList.add(rs.getString(1));
				}
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

		return allUserList;

	}

	public ArrayList <String> getUserCount() {

		ArrayList <String> allUserList = new ArrayList();

		try
		{
			// get a database connection
			con = getConnection();

			if(con != null)
			{
				String cntQuery = "select count(*) from [Genspace].[dbo].[registration]";
				PreparedStatement stmt1 = con.prepareStatement(cntQuery);
				ResultSet rs = stmt1.executeQuery();

				// create a Statement
//				Statement stmt = con.createStatement();

//				String sql =  "select count(*) from [Genspace].[dbo].[registration]";

//				ResultSet rs = stmt.executeQuery(sql);

				while (rs.next()) {
					allUserList.add(rs.getString(1));
				}
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

		return allUserList;

	}


	public ArrayList <String> getNetworkVisibility(String User) {

		ArrayList <String> allUserList = new ArrayList();

		try
		{
			// get a database connection
			con = getConnection();

			if(con != null)
			{
				String cntQuery = "select networkname from [Genspace].[dbo].[NetworkVisibility] where username = '" + User + "'";
				PreparedStatement stmt1 = con.prepareStatement(cntQuery);
				ResultSet rs = stmt1.executeQuery();

				// create a Statement
//				Statement stmt = con.createStatement();

//				String sql =  "select networkname from [Genspace].[dbo].[NetworkVisibility] where username = '" + User + "'";

//				ResultSet rs = stmt.executeQuery(sql);

				while (rs.next()) {
					allUserList.add(rs.getString(1));
				}
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

		return allUserList;

	}


	public boolean insertIntoMessagebox(String fromUser, String toUser, String message) {


		try
		{
			// get a database connection
			con = getConnection();

			if(con != null)
			{

				String sql1 = "insert into [Genspace].[dbo].[Inbox](Date,FromUser, ToUser, Message) values(GETDATE(),'" + fromUser + "','" + toUser + "','" + message +"')";
				String sql2 = "insert into [Genspace].[dbo].[Outbox](Date,FromUser, ToUser, Message) values(GETDATE(),'" + fromUser + "','" + toUser + "','" + message +"')";
				PreparedStatement stmt2 = con.prepareStatement(sql1);
				PreparedStatement stmt3 = con.prepareStatement(sql2);
				stmt2.executeUpdate();
				stmt3.executeUpdate();


				// create a Statement
//				Statement stmt = con.createStatement();
//				String sql = "insert into [Genspace].[dbo].[Inbox](Date,FromUser, ToUser, Message) values(GETDATE(),'" + fromUser + "','" + toUser + "','" + message +"')";
//				String sql1 = "insert into [Genspace].[dbo].[Outbox](Date,FromUser, ToUser, Message) values(GETDATE(),'" + fromUser + "','" + toUser + "','" + message +"')";

//				int result = stmt.executeUpdate(sql);
//				int result1 = stmt.executeUpdate(sql1);
//				//???what is the error code here???

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

		return true;
	}


	public boolean insertIntoGroupMessagebox(String fromUser, String networkName, String message) {


		try
		{
			// get a database connection
			con = getConnection();

			if(con != null)
			{
				// create a Statement
				PreparedStatement stmt = null;
				int value = 0;
				String[] users = null;

				String cntQuery = "(SELECT COUNT(username) as count FROM [GENSPACE].[dbo].[network_visibility] where networkname in (SELECT [networkname] FROM [Genspace].[dbo].[network_visibility] where username='" + fromUser +"'))";
				PreparedStatement stmt1 = con.prepareStatement(cntQuery);
				ResultSet rs = stmt1.executeQuery();
				if (rs.next()) {
					value = rs.getInt("count");
				}
				System.out.println(value);
				users = new String[value];

				String query = "(SELECT username FROM [GENSPACE].[dbo].[network_visibility] where networkname in (SELECT [networkname] FROM [Genspace].[dbo].[network_visibility] where username='demo') EXCEPT (SELECT [username] FROM [Genspace].[dbo].[network_visibility] where username='" + fromUser + "'))";
				stmt = con.prepareStatement(query);
				ResultSet rs1 = stmt.executeQuery();
				int i = 0;
				while (rs1.next()) {
					users[i] = rs1.getString("username");
					i++;
				}

				value = i;

				for(int j=0;j<value;j++)
				{
					String sql1 = "insert into [Genspace].[dbo].[Inbox](Date,FromUser, ToUser, Message) values(GETDATE(),'" + fromUser + "','" + users[j] + "','" + message +"')";
					String sql2 = "insert into [Genspace].[dbo].[Outbox](Date,FromUser, ToUser, Message) values(GETDATE(),'" + fromUser + "','" + users[j] + "','" + message +"')";
					PreparedStatement stmt2 = con.prepareStatement(sql1);
					PreparedStatement stmt3 = con.prepareStatement(sql2);
					stmt2.executeUpdate();
					stmt3.executeUpdate();
				}

//				int result = stmt.executeUpdate(query);

				//???what is the error code here???

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

		return true;
	}






	public ArrayList <String> getAllInboxMsgForCurrentUser(String currentUserId) {

		ArrayList <String> allInboxMsgList = new ArrayList();

		try
		{
			// get a database connection
			con = getConnection();

			if(con != null)
			{
				String cntQuery = "select MessageID,FromUser,Date,Message from [Genspace].[dbo].[Inbox] where ToUser = '" + currentUserId + "' order by Date desc";
				PreparedStatement stmt1 = con.prepareStatement(cntQuery);
				ResultSet rs = stmt1.executeQuery();

				// create a Statement
//				Statement stmt = con.createStatement();

//				String sql =  "select MessageID,FromUser,Date,Message from [Genspace].[dbo].[Inbox] where ToUser = '" + currentUserId + "' order by Date desc";

//				ResultSet rs = stmt.executeQuery(sql);

				while (rs.next()) {
					allInboxMsgList.add(rs.getString(1) + "#" + rs.getString(2)+"#"+rs.getString(3)+"#"+rs.getString(4)+"#");//ATTENTION we use '#' as the delimeter
				}
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

		return allInboxMsgList;
	}




	public boolean deleteFromInbox(String messageID) {
		boolean res = false;
		try
		{
			// get a database connection
			con = getConnection();

			if(con != null)
			{
				String sql1 = "delete from [Genspace].[dbo].[Inbox] where MessageID = '" + messageID + "'";
				PreparedStatement stmt2 = con.prepareStatement(sql1);
				stmt2.executeUpdate();

				// create a Statement
//				Statement stmt = con.createStatement();

//				String sql = "delete from [Genspace].[dbo].[Inbox] where MessageID = '" + messageID + "'"; 
//				System.out.println("DELETED!"+messageID);
//				int result = stmt.executeUpdate(sql);
//				res = true;
				//???what is the error code here???

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
		return res;
	}






	public ArrayList <String> getAllOutboxMsgForCurrentUser(String currentUserId) {

		ArrayList <String> allOutboxMsgList = new ArrayList();

		try
		{
			// get a database connection
			con = getConnection();

			if(con != null)
			{
				String cntQuery = "select MessageID,ToUser,Date,Message from [Genspace].[dbo].[Outbox] where FromUser = '" + currentUserId + "' order by Date desc";
				PreparedStatement stmt1 = con.prepareStatement(cntQuery);
				ResultSet rs = stmt1.executeQuery();

				// create a Statement
//				Statement stmt = con.createStatement();

//				String sql =  "select MessageID,ToUser,Date,Message from [Genspace].[dbo].[Outbox] where FromUser = '" + currentUserId + "' order by Date desc";

//				ResultSet rs = stmt.executeQuery(sql);

				while (rs.next()) {
					allOutboxMsgList.add(rs.getString(1) + "#" + rs.getString(2)+"#"+rs.getString(3)+"#"+rs.getString(4)+"#");//ATTENTION we use '#' as the delimeter
				}
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

		return allOutboxMsgList;

	}





	public boolean deleteFromOutbox(String messageID) {

		boolean res = false;
		try
		{
			// get a database connection
			con = getConnection();

			if(con != null)
			{
				String sql1 = "delete from [Genspace].[dbo].[Outbox] where MessageID = '" + messageID + "'";
				PreparedStatement stmt2 = con.prepareStatement(sql1);
				stmt2.executeUpdate();

				// create a Statement
//				Statement stmt = con.createStatement();

//				String sql = "delete from [Genspace].[dbo].[Outbox] where MessageID = '" + messageID + "'"; 
//				System.out.println("DELETED!"+messageID);
//				int result = stmt.executeUpdate(sql);
//				res = true;
				//???what is the error code here???

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
		return res;


	}











	// Ratings***************************************************************
	//***********************************************************************


	public boolean insertIntoWFRatings(String identifier, String ratings, String user) {


		try
		{
			// get a database connection
			con = getConnection();

			if(con != null)
			{
				// create a Statement
				Statement stmt = con.createStatement();

				String sql = "insert into [Genspace].[dbo].[workflow_ratings](id, username, rating) values('" + identifier + "','" + user + ',' + ratings +"')";
				int result = stmt.executeUpdate(sql);
				//int result1 = stmt.executeUpdate(sql1);
				//???what is the error code here???

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

		return true;
	}


	public boolean insertIntoToolsRatings(String identifier, String ratings, String user) {


		try
		{
			// get a database connection
			con = getConnection();

			if(con != null)
			{
				// create a Statement
				Statement stmt = con.createStatement();

				String sql = "insert into [Genspace].[dbo].[tool_ratings](id, username, rating) values('" + identifier + "','" + user + ',' + ratings +"')";
				int result = stmt.executeUpdate(sql);
				//int result1 = stmt.executeUpdate(sql1);
				//???what is the error code here???

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

		return true;
	}


	public ArrayList <String> getAllWFRating(String id, String user) {

		ArrayList <String> allRatingList = new ArrayList();

		try
		{
			// get a database connection
			con = getConnection();

			if(con != null)
			{
				// create a Statement
				Statement stmt = con.createStatement();

				String sql =  "select rating from [Genspace].[dbo].[workflow_ratings] " +
				"where username = '" + user + "'" +"AND id = '" + id;

				ResultSet rs = stmt.executeQuery(sql);

				while (rs.next()) {
					allRatingList.add(rs.getString(1) + "#" + rs.getString(2));//ATTENTION we use '#' as the delimeter
				}
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

		return allRatingList;

	}


	public ArrayList <String> getAllToolsRating(String id, String user) {

		ArrayList <String> allOutboxMsgList = new ArrayList();

		try
		{
			// get a database connection
			con = getConnection();

			if(con != null)
			{
				// create a Statement
				Statement stmt = con.createStatement();

				String sql =  "select rating from [Genspace].[dbo].[tool_ratings] " +
				"where username = '" + user + "'" +"AND id = '" + id;

				ResultSet rs = stmt.executeQuery(sql);

				while (rs.next()) {
					allOutboxMsgList.add(rs.getString(1) + "#" + rs.getString(2));//ATTENTION we use '#' as the delimeter
				}
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

		return allOutboxMsgList;

	}



	
	public ArrayList<String> getAllRatingsForCurrentUser(String currentUserId) {
		// TODO Auto-generated method stub
		return null;
	}








	/**
	 * for Real Time work flow suggestion
	 * by cheng
	 */
	public ArrayList getRealTimeWorkFlowSuggestion(String cwfString) {

		ArrayList resultList = new ArrayList();

		//first we do statA
		int statAResult = dbManager.getStatA(cwfString);
		System.out.println(" ");
		System.out.println("Stat A result acquired:");


		//then we do statB
		ArrayList <String> statBResult = dbManager.getStatB(cwfString);
		System.out.println(" ");
		System.out.println("Stat B result acquired:");



		//last we do statC, "next possible step"
		ArrayList <String> statCResult = getStatC(cwfString);
		System.out.println(" ");
		System.out.println("Stat C result acquired:");




		//now we add the result of statA, statB, and statC in to the final resultList
		resultList.add(Integer.valueOf(statAResult));
		resultList.add(statBResult);
		resultList.add(statCResult);


		return resultList;
	}


	/**
	 * this is a helper method used by the above method "getRealTimeWorkFlowSuggestion"
	 * this method is used for calculating statC
	 * by cheng
	 */
	public ArrayList getStatC(String cwf) {

		ArrayList <String> statCResultList = new ArrayList();


		ArrayList <String> cwfList = new ArrayList();

		StringTokenizer tokens = new StringTokenizer(cwf, ",");

		//now we get the arrayList representation of the cwf
		while (tokens.hasMoreTokens()) {

			cwfList.add(tokens.nextToken());

		}

		//now we get the array representation of the cwf
		String[] cwfArray = cwfList.toArray(new String[cwfList.size()]);

		WorkflowManager wdm = new WorkflowManager();

		//get the last node of cwf
		WorkflowNode node = wdm.getWorkflowNode(cwfArray, true);

		//now we get the possible next step nodes of cwf
		WorkflowNode[] nextList = wdm.getChildren(node.getId());

		int howManyChildren = 0;

		//if we do find some "next step node", we will do the rest part
		if (nextList != null && nextList.length > 0) {

			System.out.println("the NEXT possible steps of cwf:");

			//the loop iterating each of the next step candidates
			for (WorkflowNode each: nextList) {
				//in order to avoid the bug, we have to see whether the obj in the array is null
				if (each != null) {

					//used to store the whole work flow in which the current next step candidate is the last node
					String wholeWFOfCurrentNextCandidate = "";

					//first we add in the current next step candidate node - which should be the last node in the whole flow
					wholeWFOfCurrentNextCandidate =  trimString(each.getToolName()) + "," + wholeWFOfCurrentNextCandidate;
					//System.out.println("Current next candidate " + wholeWFOfCurrentNextCandidate);

					System.out.println("current next candidate id: " + each.getId());
					System.out.println("current next candidate name: " + trimString(each.getToolName()) + "***");
					howManyChildren ++;


					//then, for each possible next step candidate, we try to extract the whole workflow ending with it

					int nodeIdToBeSearched = 0;
					//the next node to be queried by sql is the parent node of the current node, as long as the current one is not a root
					//if the current one is a root, we will get "-1" in the following line, and the while loop will not continue
					nodeIdToBeSearched = each.getParent();

					while (nodeIdToBeSearched != -1) {

						try
						{
							// get a database connection
							con = getConnection();

							if(con != null)
							{
								// create a Statement
								Statement stmt = con.createStatement();

								String sql =  "SELECT parent,tool FROM [Genspace].[dbo].[workflows] WHERE id = " + nodeIdToBeSearched;

								ResultSet rs = stmt.executeQuery(sql);

								String currentTool = null;
								int currentToolParent = 0;


								while (rs.next()) {
									currentTool = trimString(rs.getString(2));
									currentToolParent = rs.getInt(1);
								}

								//we add the currentTool queried into the whole next candidate work flow
								wholeWFOfCurrentNextCandidate =  currentTool + "," + wholeWFOfCurrentNextCandidate;


								//at last we need to refresh the "nodeIdToBeSearched"
								nodeIdToBeSearched = currentToolParent;

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

					}//end of while loop

					//now we have already get the whole work flow of current next candidate

					System.out.println("The whole work flow of current next candidate: ***" + wholeWFOfCurrentNextCandidate + "***");

					//next, we look this wholeWFOfCurrentNextCandidate up in index to to get the times of use

					int data = 0;

					if (dbManager.getWorkflowIndex().get(wholeWFOfCurrentNextCandidate) == null) {//which means the work flow index does not contain this whole WF of Current next candidate
						//such a case should not happen because this indicates the inconsistency between work flow index and work flow table 
						System.out.println("wholeWFOfCurrentNextCandidate NOT exist in work flow index! Historical times set to 0!!");
						//what to do?
						data = 0;//currently we just set the data to 0, anyway this should not happen!
					}
					else {
						Integer dataValue = (Integer)dbManager.getWorkflowIndex().get(wholeWFOfCurrentNextCandidate);
						data = dataValue.intValue();
					}
					System.out.println("the historical times for this wholeWFOfCurrentNextCandidate is: " + data);
					System.out.println();


					//now we combine the wholeWFOfCurrentNextCandidate and its data and then add this result item into result list
					String currentResultItem = trimString(each.getToolName()) + "#" + data;
					statCResultList.add(currentResultItem);


				}//end of inner if


			}//end of for loop
			System.out.println("Altogether we have found " + howManyChildren + " possible next steps");


		}//end of outter if




		return statCResultList;


	}


	/**
	 * this is a helper method used to trim the extra spaces after each tool string returned from "getToolName()"
	 * "MRA Analysis______" to "MRA Analysis"
	 * @param str
	 * @return
	 */
	private static String trimString(String str) {

		char[] charArray = str.toCharArray();
		int i = 0;
		for (i = charArray.length - 1; i >= 0; i --) {
			if (charArray[i] != ' ') {
				break;
			}
		}
		return str.substring(0, i + 1);

	}





}
