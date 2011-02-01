package genspace.db;

import genspace.common.Logger;

import org.geworkbench.components.genspace.bean.Tool;
import org.geworkbench.components.genspace.bean.Workflow;
import org.geworkbench.components.genspace.bean.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.*;
import java.util.*;

/**
 * This class extends DatabaseManager, as the DB Manager class for the ISBU feature
 * It defines database operation methods for the "Immediate Suggestion Based on Use (ISBU)" feature of genSpace
 * @author cheng
 *
 */




public class ISBUManager extends DatabaseManager implements Serializable {

	private ArrayList <String> analysisToolSet = new ArrayList();//a global set of all the tools
	public HashMap <String, String> toolIDIndex = new HashMap();//an index to hold a unique ID for each tool
	
	private ArrayList <String> completeTransactionList = new ArrayList();//or we can say, this is a complete work flow list
	private HashMap <String, ArrayList> analysisToolIndex = new HashMap(); //"index 1"
	private HashMap <String, Integer> workFlowIndex = new HashMap(); //"index 2"
	private HashMap <String, Float> workFlowIndexCopyForEBValue = new HashMap();//another copy of index 2, spacially used for sorting
	
	private HashMap <String, ArrayList> nextToolIndex = new HashMap();//"index 3"
	private HashMap <String, ArrayList> previousToolIndex = new HashMap();//"index 4"
	
	//sort results for the indices
	private ArrayList <CompareUnit> sortedIndex1By1stValue = new ArrayList();
	private ArrayList <CompareUnit> sortedIndex1By2ndValue = new ArrayList();
	private ArrayList <CompareUnit> sortedIndex2ByOnlyValue = new ArrayList();
	
	//WORKFLOW REPOSITORY MODEL structures (computed in storeWorkflowModel())
	public HashMap <String, Tool> toolsTable = new HashMap<String, Tool>(); //The actual tools table available in the DB, given a tool name you can access the corresponding tool object
	HashMap<String, Workflow> workflows =  new HashMap<String, Workflow>();
	
	static final long serialVersionUID = 6503560436267816846L;
	private String table_name = "[Genspace].[dbo].[analysis_events]";
	
	
	
	/**
	 * constructor
	 */
	public ISBUManager() {
		
	}
	
	
	/**
	 * This method is used to get the global set of all the analysis tools extracted from the current system log
	 * It involves 2 main steps.
	 * We first put all the individual tools in an arrayList - analysisToolSet, each with a unique index (0,1,2,...)
	 * Then we need to build up a global hash index which stores the unique tool ID (as the value) for each tool (as the key)
	 * Such a global ID index will later be used when we build up index 3 and index 4
	 * The hash index will be in the following form:
	 * Tool name            Global unique tool ID
	 * 		A						0
	 * 		B						1
	 * 		C						2
	 * 		...						...
	 * @return
	 */
	public void getSetOfAnalysisTools(){
		
		try
		{
			// get a database connection
			con = getConnection();

			if(con != null)
			{
				// create a Statement
				Statement stmt = con.createStatement();
				
				String sql = "SELECT DISTINCT analysis FROM " + table_name +
				             "where transaction_id <> 'NULL' " +
						      "ORDER BY analysis";
				
				ResultSet rs = stmt.executeQuery(sql);
				
				while (rs.next()) {
					analysisToolSet.add(rs.getString(1));
				}
            }
//            else System.out.println("Error: No active Connection");
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
		
		
		//Now we build the tool ID index
		for (int i = 0; i < analysisToolSet.size(); i++) {
			toolIDIndex.put(analysisToolSet.get(i), String.valueOf(i));
		}

	}
	
	
	
	
	
	
	
	
	/**
	 * This method has two main tasks:
	 * 1. get the complete transaction list, in the form of a ArrayList
	 * 2. create and fill in the index for individual tools (we say "index 1")
	 * 
	 * NOTE: @@@ modified by Cheng on 10-18-2008 for providing support for Chris' client side's WF visualization feature:
	 *           "All work flows including a certain tool"
	 *  
	 * particularly, the transaction list is in following format:
	 * Transaction_index         Transaction_workFLow   
	 * 		0						"A,B,C,D"
	 * 		1						"B,D,C,"
	 * 		2						"D,C,B,B,A,C,"
	 * 		...						...
	 */
	public void getCompleteTransactionList() {
		
		
		try
		{
			// get a database connection
			con = getConnection();

			if(con != null)
			{
				// create a Statement
				Statement stmt = con.createStatement();
				
				String sql = "select transaction_id, date, analysis " +
							 "from " + table_name + " A " +
							 "where A.transaction_id <> 'NULL' " +
							 "group by transaction_id, date, analysis " +
							 "order by date ";
				
				ResultSet rs = stmt.executeQuery(sql);
				
				boolean isRSHead = true;
				String previousTid = null;
				String currentTransactionWorkFlow = "";
				Date currentTimeForUseOfLastTInRS = null;
				
				//for each of the log records selected, we perform 2 tasks
				while (rs.next()) {
					
					//##### for the purpose of Exp-Backoff
					Date currentTime = rs.getDate("date");
					
					//###
					//System.out.println();
					//System.out.println("Scanning a log record, the time stamp for this tool (log record)is: " + currentTime.toString());
					//System.out.println("Calculating Expinential Backoff for the current TOOL usage ...");
					
					Date now = new Date();
					long diff = (now.getTime() - currentTime.getTime()) / (3600 * 24 * 1000);
					//System.out.println("'diff' value, from 'now time', in terms of day, is " + diff);
					
					//this number seems to be too large, we divide it by 1000
					//??? QUESTION: what would be the most appropriate coefficient???
					//remember, two places to change if you want to change this coefficient, another place below...
					double smallerDiff = (double)diff / (double)1;
					//System.out.println("we make diff smaller by divide it by 1000 : " + smallerDiff);
					
					double value = Math.exp(-smallerDiff);//##This is what we want!!!! weight of Exp-backoff
														  //note that we have -x as the param here, the smaller the x is, the larger final weight will be, also the sharper difference there will be
					//System.out.println("weight value for this time stamp: " + value);
					
					
					
					
					
					
					//Task 1: for each of the log records retrieved, we put the tool name in "index 1"
					//### also in task 1, we need to add the Exp-backoff feature, so when we add or update tool info in index 1,
					//### we also calculate the EB-weight value (at this time only for 1st value in the value list, but later for the 2nd) and store it in index 1
					
					String currentTool = rs.getString("analysis");
					//first we test whether the current tool already exist in our index
					if (!analysisToolIndex.containsKey(currentTool)) { //if the current tool is NEW to our index
						
						
						
						
						//we generate the value for this index entry, !!!this is the first time we add values into index 1
						ArrayList currentIndexValue = new ArrayList();
						
						//### Important!!!: for the Exp-backoff part, we have to use "NULL" to INITIALIZE the elements in index 1 entry value
						//### we initialize first 8 elements (0-7) of the indexValue, 
						//### and the 7th entry is the accumulate Exp-backoff weight value of the current tool
						//@@@ ------------ by Cheng on 10-18-2008 --------------------
						//@@@ we initialize the first 8 elements (0-8), and the 8th entry is used for Chris' feature
						//@@@ that is to store all work flows including the current tool
						
						for (int index = 0; index < 9; index++) {//we just initialize the first @@@9 elements
							//this is the ONLY place we use 'add' method, all later modifications to the index must use 'set' method!
							//the reason is that we have to "accumulate" the sum of weight values multiple times later on, not to set the value only once
							currentIndexValue.add(index,null);
						}
						
						
						//since this's the first time we encounter this tool, we set the count value to 1
						Integer historicalTimesOfUse = new Integer(1);
						//then we put this count value into the first position of the arraylist (index value)
						currentIndexValue.set(0, historicalTimesOfUse);
						
						
						
						
						
						//**now for the convenience of later use, we INITIALIZE the second element of the index value list
						Integer initialValue = 0;
						currentIndexValue.set(1, initialValue);
						
						
						
						//### we add the EB-value of the current tool to the index, into the 7th position of current value list
						//### REMEMBER, the EBvalue is a very small value, so we have to use DOUBLE here!!
						Double EBvalue = new Double(value);
						currentIndexValue.set(7, EBvalue);
						//System.out.println("Succeed!!! add into 7th position!!!");
						
						
						//@@@ ------ by Cheng on 10-18-2008 
						//for later use we initialize the 8th value entry with an HashSet
						HashSet <String> EighthValue = new HashSet();
						//ArrayList <String> EighthValue = new ArrayList();
						currentIndexValue.set(8, EighthValue);
						
						analysisToolIndex.put(currentTool, currentIndexValue);
						
					}
					else {//if the index already has a key for this tool, we increment its value (tool count)
						//first we get the current index value
						ArrayList currentIndexValue = analysisToolIndex.get(currentTool);
						//then we increment the historical count by 1 
						Integer temp = (Integer)currentIndexValue.get(0);
						temp ++;
						currentIndexValue.set(0, temp);
						
						
						//### we update the EB-value of the current tool to the index, into the 7th position of current value list
						//### REMEMBER, the EBvalue is a very small value, so we have to use DOUBLE here!!
						Double oldValue = (Double)currentIndexValue.get(7);
						Double EBvalue = new Double(value);
						//accumulate the weight value
						oldValue = oldValue + EBvalue;
						currentIndexValue.set(7, oldValue);
						
						
						
						//ANOTHER MISTAKE!! NO NEED TO INITIALIZE HERE!
						//**now for the convenience of later use, we INITIALIZE the second element of the index value list
						//Integer initialValue = 0;
						//currentIndexValue.add(1, initialValue);
						
						//??? no need to "put" it back into hashmap here??
						analysisToolIndex.put(currentTool, currentIndexValue);
					}
					
					
					
					
					
					
					
					//Task 2: we extract the proper work flow from EACH TRANSACTION and fill in the completeTransactionList
					String currentTid = rs.getString("transaction_id");
					
					
					currentTimeForUseOfLastTInRS = currentTime;//####
					
					//System.out.println(currentTid);
					
					
					if (isRSHead == true) {//if this is the first line of RS
						currentTransactionWorkFlow = currentTransactionWorkFlow + currentTool + ",";
						previousTid = currentTid;
					}
					else {//if this is not the first line of RS
						//first we check whether the current line represents a new T with a different Tid
						if (currentTid.equals(previousTid)) {//we are still in the same transaction
							currentTransactionWorkFlow = currentTransactionWorkFlow + currentTool + ",";
							previousTid = currentTid;
						}
						else {//we have entered a new transaction
							
							//###now that we have entered a new T, before we save the last WF, we get the time stamp for the last WF and calculate Exp backoff
							//System.out.println();
							//System.out.println("This is the end of one Transaction, the ending time stamp for this T (WF) is " + currentTime.toString());
							//System.out.println("Calculating Expinential Backoff for the current WORK FLOW appearance....");
							
							/* we have moved this part above the if-else structure, because both tool and work flow need the same Exp-backoff calculation
							Date now = new Date();
							long diff = (now.getTime() - currentTime.getTime()) / (3600 * 24 * 1000);
							System.out.println("'diff' value, from 'now time', in terms of day, is " + diff);
							
							//this number seems to be too large, we divide it by 100
							double smallerDiff = (double)diff / (double)100;
							System.out.println("we make diff smaller by divide it by 100 : " + smallerDiff);
							
							double value = Math.exp(-smallerDiff);
							System.out.println("weight value for this time stamp: " + value);
							*/
							
							
							
							//###then we append this backoff value to the work flow string
							currentTransactionWorkFlow = currentTransactionWorkFlow + "#" + value + "#";
							
							//System.out.println("we look at the final version of current WF to be saved: " + currentTransactionWorkFlow);
							
							
							
							//first we must save the currentTransactionWorkFlow into completeTransactionList
							completeTransactionList.add(currentTransactionWorkFlow);
							//then we EMPTY the currentTransactionWorkFlow for later use by the current new transaction
							currentTransactionWorkFlow = "";
							//then we can append the first tool node of the new transaction work flow
							currentTransactionWorkFlow = currentTransactionWorkFlow + currentTool + ",";
							previousTid = currentTid;
						}
					}
					isRSHead = false;
					
				}
				
				//save the last "currentTransactionWorkFlow"
				
				//###here , a tiny problem still exists??
				//###now that we have entered a new T, before we save the last WF, we get the time stamp for the last WF and calculate Exp backoff
				
//				System.out.println();
				//System.out.println("This is the end of a Transaction, the ending time stamp for this T (WF) is " + currentTimeForUseOfLastTInRS.toString());
				//System.out.println("Calculating Expinential Backoff for the current WORK FLOW appearance....");
				Date now = new Date();
				long diff = (now.getTime() - currentTimeForUseOfLastTInRS.getTime()) / (3600 * 24 * 1000);
				//System.out.println("'diff' value, from 'now time', in terms of day, is " + diff);
				
				//this number seems to be too large, we divide it by 1000
				double smallerDiff = (double)diff / (double)1;
				//System.out.println("we make diff smaller by divide it by 1000 : " + smallerDiff);
				
				double EBvalue = Math.exp(-smallerDiff);
				//System.out.println("weight value for this time stamp: " + EBvalue);
				
				
				//###then we append this backoff value to the work flow string
				currentTransactionWorkFlow = currentTransactionWorkFlow + "#" + EBvalue + "#";
				
				//System.out.println("we look at the final version of current WF to be saved: " + currentTransactionWorkFlow);
				
				
				completeTransactionList.add(currentTransactionWorkFlow);
				currentTransactionWorkFlow = "";
				
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
	
	
	
	
	
	/**
	 * This method is used to build the index for all the work flows (index 2)
	 * The key of the index is the uniform string representation of work flow - "A,B,C,D,"
	 * The value of the index is an Integer, representing the times of historical use for each work flow
	 * 
	 * NOTE: this method is modified by Cheng on Oct.18 2008, for the purpose of eliminating all "workflows" with only one single node
	 * 
	 */
	public void buildWorkFlowIndex(){
		
		for (int i = 0; i < completeTransactionList.size(); i++ ) {
			String currentWorkFlowWholeString = completeTransactionList.get(i);
			
			
			//###first we need to parse the WF part and the EB value part
			//System.out.println("parsing 'WF#EBValue#' ...");
			StringTokenizer tokens = new StringTokenizer(currentWorkFlowWholeString, "#");
			String currentWorkFlow = tokens.nextToken();
			
			//@@@--------- modified by Cheng, 10-18-2008 -------------
			//now we have gain the currentWorkFLow, we test that whether it contains only one single node
			//if yes, we will not put such a flow into the workflow index
			StringTokenizer testTokens = new StringTokenizer(currentWorkFlow, ",");
			int numOfTokens = 0;
			while (testTokens.hasMoreTokens()) {
				numOfTokens ++;
				testTokens.nextToken();
			}
			//System.out.println("@@@For the current workflow: " + numOfTokens + " tokens!");
			if (numOfTokens == 1) {
				continue;
			}
			
			
			//System.out.println("the current WF we have parsed out is : " + currentWorkFlow);
			String EBValue = tokens.nextToken();
			//System.out.println("the EBValue we have parsed out is : " + EBValue);
			float EBValueFloat = Float.parseFloat(EBValue);
			
			
			//first we test whether the current work flow already exist in our index
			if (!workFlowIndex.containsKey(currentWorkFlow)) { //if the current work flow is NEW to our index
				//we add this new work flow into the index, since this's the first time we encounter this WF, we set the count value to 1
				workFlowIndex.put(currentWorkFlow, 1);
			
			}	
			else {//if the index already has a key for this WF, we increment its count value
				Integer currentIndexValue = workFlowIndex.get(currentWorkFlow);
				currentIndexValue ++;
				workFlowIndex.put(currentWorkFlow, currentIndexValue);
			}	
			
			
			//### we perform exactly the same process on "workFlowIndexCopyForEBValue"
			if (!workFlowIndexCopyForEBValue.containsKey(currentWorkFlow)) { //if the current work flow is NEW to our index
				//we add this new work flow into the index, since this's the first time we encounter this WF, we set the count value to 1
				workFlowIndexCopyForEBValue.put(currentWorkFlow, EBValueFloat);
				
			}	
			else {//if the index already has a key for this WF, we increment its count value
				Float currentIndexValue = workFlowIndexCopyForEBValue.get(currentWorkFlow);
				currentIndexValue = currentIndexValue + EBValueFloat;
				workFlowIndexCopyForEBValue.put(currentWorkFlow, currentIndexValue);
			}
			
		}
		
		//Now we have already filled in the index 2, let's print it out
//		System.out.println();
//		System.out.println("Now we go through the work flow index (index 2): work flow name & historical times of use");
		
		Set entries = workFlowIndex.entrySet();
		Iterator it = entries.iterator();
		while (it.hasNext()) {
			
			Map.Entry entry = (Map.Entry)it.next();
			Object key = entry.getKey();
			Object value = entry.getValue();
//			System.out.println(key + ": " + value);
		}
		

		
	}
	
	
	
	
	
	
	/**
	 * This method is used to fill in the second value of index 1 entry:
	 * The historical times of being the head of any work flow for each tool in index 1
	 */
	public void fillInIndex1SecondValue() {
		
		Set entries = workFlowIndex.entrySet();
		Iterator it = entries.iterator();
		while (it.hasNext()) {
			
			Map.Entry entry = (Map.Entry)it.next();
			Object key = entry.getKey();
			String currentWorkFlow = (String) key;
			StringTokenizer tokens = new StringTokenizer(currentWorkFlow, ",");
			//get the first node of each work flow
			String headOfWorkFlow = tokens.nextToken();
			//System.out.println("the header node of current WF is " + headOfWorkFlow);
			
			Object value = entry.getValue();
			Integer historicalTimesOfCurrentWorkFlow = (Integer) value;
			//when we update the second value of index 1, we need to multiply by this factor!
			int multiple = historicalTimesOfCurrentWorkFlow.intValue();
			
			//now we fill in the respective entry in index 1
			ArrayList currentIndexValue = analysisToolIndex.get(headOfWorkFlow);
			Integer timesOfHeadersForCurrentTool = (Integer) currentIndexValue.get(1);
			timesOfHeadersForCurrentTool = timesOfHeadersForCurrentTool + multiple;
			currentIndexValue.set(1, timesOfHeadersForCurrentTool);
			//??? no need to put back into hashmap??
			analysisToolIndex.put(headOfWorkFlow, currentIndexValue);
			
		}
		
		//now we have finished filling in the second index value of index 1, let's print out this newer version of index 1
//		System.out.println();
//		System.out.println("Now we go through the 2nd version of index 1 (with 2 index values):");
		Set entries1 = analysisToolIndex.entrySet();
		Iterator it1 = entries1.iterator();
		while (it1.hasNext()) {
			
			Map.Entry entry1 = (Map.Entry)it1.next();
			Object key = entry1.getKey();
			Object value = entry1.getValue();
			System.out.println(key + ": " + value);
		}
		
	}
	
	
	
	
	
	
	/**
	 * This method is used to analyze what is the most popular tool prior to or next to each tool
	 * We need several steps to finish this complicated task
	 * Step 1: we need to build two subsidiary indices (index 3 and index 4) to store the information about the "next tool" and "previous tool" for each tool
	 * Step 2: once we have build up this two indices, we can then easily find out the "most popular" "next tool" and "previous tool"
	 * Step 3: for each tool, we store in index 1 its most popular "next tool" & "previous tool" (in the 3rd and 4th value position)
	 */
	public void calculatePreviousAndNextTool() {
		
		//Step 1 -------------------
		//first we need to initialize index 3 and index 4 (fill them with keys - tool names)
		for (int i = 0; i < analysisToolSet.size(); i++) {
			//initialize the value for the current entry (ArrayList [0,0,0,0,...] as many 0s as the # of tools)
			ArrayList <Integer> initialValue =  new  ArrayList <Integer>();
			for (int j = 0; j < analysisToolSet.size(); j++){
				initialValue.add(new Integer(0));
			}
			
			//DO REMEMBER THE HUGE MISTAKE HERE!!!!!! put the same obj into two different hash map
			
			ArrayList <Integer> initialValue1 =  new  ArrayList <Integer>();
			for (int x = 0; x < analysisToolSet.size(); x++){
				initialValue1.add(new Integer(0));
			}
			
			//then we put this initial value (arraylist) into the current index 3 and index 4 entry
			nextToolIndex.put(analysisToolSet.get(i), initialValue);
			previousToolIndex.put(analysisToolSet.get(i), initialValue1);
			
		}//finish initialization of index 3 and index 4
		
		
		//we need to go through the work flow index (index 2) in order to fill in index 3 and index 4
		Set entries = workFlowIndex.entrySet();
		Iterator it = entries.iterator();
		while (it.hasNext()) {
			
			Map.Entry entry = (Map.Entry)it.next();
			String currentWorkFlow = (String)entry.getKey();
			
			//System.out.println();
			//System.out.println("##ERROR CHECK: now we are analyzing the WF: " + currentWorkFlow );
			
			Integer historicalTimesOfCurrentWorkFlow = (Integer)entry.getValue();
			//when we fill in index 3 and index 4, DON'T FORGET to multiply by the following factor!
			int multiple = historicalTimesOfCurrentWorkFlow.intValue();
			
			//next we analyze the current work flow
			//tokenize the current work flow and put the tokens in an arrayList
			StringTokenizer tokens = new StringTokenizer(currentWorkFlow, ",");
			ArrayList <String> currentWorkFlowTokens = new ArrayList();
			while (tokens.hasMoreTokens()) {
				String currentToken = tokens.nextToken();
				currentWorkFlowTokens.add(currentToken);
			}
			
			//System.out.println(currentWorkFlowTokens.size() + "size!!");
			if (currentWorkFlowTokens.size() > 1) {
				
				/*
				System.out.println();
				System.out.println("current WF being processed: " + currentWorkFlowTokens.toString());
				System.out.println("current Multiple: " + multiple);
				*/
				
				for (int k = 0; k < currentWorkFlowTokens.size(); k++) {
					String currentNode = currentWorkFlowTokens.get(k);
					
					//System.out.println("##ERROR CHECK: now we are analyzing the node " + currentNode + " in current WF.");
					
					if (k > 0) {//we find its previous node (except for the head node)
						String previousOfCurrentNode = currentWorkFlowTokens.get(k - 1);
						//search the tool ID index for the ID of this previous node
						String previousNodeID = toolIDIndex.get(previousOfCurrentNode);
						
						//System.out.println();
						//System.out.println("#ERROR CHECK: NODE: " + previousOfCurrentNode + " ID: " + previousNodeID);
						
						//now we update the value in index 4 based on ID
						ArrayList index4Entry = previousToolIndex.get(currentNode);
						Integer value = (Integer)index4Entry.get(Integer.parseInt(previousNodeID));//??why I have to cast here??
						value = value + multiple;
						index4Entry.set(Integer.parseInt(previousNodeID), value);
						//previousToolIndex.put(currentNode, index4Entry);//no need, we have modified the reference
						
						//System.out.println("##ERROR CHECK: previous node found! its ID is: " + previousNodeID );
						
					}
					if (k < (currentWorkFlowTokens.size() - 1)){//we find its next node (except for the rear node)
						
						String nextOfCurrentNode = currentWorkFlowTokens.get(k + 1);
						
						//System.out.println("current node: " + currentNode);
						//System.out.println("next node: " + nextOfCurrentNode);
						
						//search the tool ID index for the ID of this next node
						String nextNodeID = toolIDIndex.get(nextOfCurrentNode);
						
						//System.out.println();
						//System.out.println("#ERROR CHECK: NODE: " + nextOfCurrentNode + " ID: " + nextNodeID);
						
						//now we update the value in index 3 based on ID
						ArrayList index3Entry = nextToolIndex.get(currentNode);
						Integer value = (Integer) index3Entry.get(Integer.parseInt(nextNodeID));
						
						//System.out.println("old value: " + value);
						
						value = value + multiple;
						
						//System.out.println("new value: " + value);
						
						index3Entry.set(Integer.parseInt(nextNodeID), value);
						//nextToolIndex.put(currentNode, index3Entry);//no need, we have modified the reference
						
					}
						
				}
			}//if there is only one node in the current work flow, we just ignore such WF
			
			
		} //end of going through the work flow index
		
		
		
		
		
		//now we have finished step 1, index 3 and index 4 have been built up and filled in, now we print them out
//		System.out.println();
//		System.out.println("Done building index 3 and index 4.");
//		System.out.println();
//		System.out.println("Here is index 3: a " + analysisToolSet.size() + " by " + analysisToolSet.size() + " matrix representing the relationship between each node and its next node:");
		Set entries1 = nextToolIndex.entrySet();
		Iterator it1 = entries1.iterator();
		while (it1.hasNext()) {
			
			Map.Entry entry1 = (Map.Entry)it1.next();
			Object key = entry1.getKey();
			Object value = entry1.getValue();
			System.out.println(key + ": " + value);
		}
		
//		System.out.println();
//		System.out.println("Here is index 4: a " + analysisToolSet.size() + " by " + analysisToolSet.size() + " matrix representing the relationship between each node and its previous node:");
		Set entries2 = previousToolIndex.entrySet();
		Iterator it2 = entries2.iterator();
		while (it2.hasNext()) {
			
			Map.Entry entry2 = (Map.Entry)it2.next();
			Object key = entry2.getKey();
			Object value = entry2.getValue();
//			System.out.println(key + ": " + value);
		}
//		System.out.println();
		
		
		
		
		
		
		//Step 2 & Step 3 ---------------------
		//we traverse index 3 and then index 4, finding out the most popular "previous tool" and "next tool" for each tool,
		//and then fill such data in index 1
		
		//Traversing index 3
		Set entries3 = nextToolIndex.entrySet();
		Iterator it3 = entries3.iterator();
		while (it3.hasNext()) {
			
			Map.Entry entry3 = (Map.Entry)it3.next();
			String currentToolInIndex3 = (String) entry3.getKey();//we are searching for this tool's most popular "next tool"
			ArrayList currentValueListInIndex3 = (ArrayList) entry3.getValue();
			//go through all the values and find the largest (most popular) one
			int mostPopularNextToolID = 0;
			int mostPopularNextToolTimes = 0;
			int y;
			for (y = 0 ; y < currentValueListInIndex3.size(); y++) {
				int currentValue = ((Integer)(currentValueListInIndex3.get(y))).intValue();
				if (currentValue > mostPopularNextToolTimes) {
					mostPopularNextToolTimes = currentValue;
					mostPopularNextToolID = y;
				}
			}
			String mostPopularNextTool = analysisToolSet.get(mostPopularNextToolID);
			//System.out.println("Now we get the most popular 'next tool' for the tool " + currentToolInIndex3 + ":        Tool ID: " + mostPopularNextToolID + "     Tool name: " + mostPopularNextTool );
			//next we put this result in the respective entry in index 1 (the 3rd position in index 1 value)
			//System.out.println("CAOCAO!!! " + currentToolInIndex3);
			ArrayList index1EntryValue = analysisToolIndex.get(currentToolInIndex3);
			//System.out.println("WOCAO!!!! " + index1EntryValue);
			
			//$$$ HERE WE HAVE THE PROBLEM OF "Net Boost" and "NetBoostAnalysis", 
			//the name of "net boost" still exist but there are actually NO workflows containing this name, so nullPoint exceptions here
			//this problem has been fixed!!
			//if (index1EntryValue == null) 
			//	continue;
			
			index1EntryValue.set(2, mostPopularNextTool);
				
		}//by now we should already updated all the 3rd values in index 1
		
		
		//Traversing index 4
		Set entries4 = previousToolIndex.entrySet();
		Iterator it4 = entries4.iterator();
		while (it4.hasNext()) {
			
			Map.Entry entry4 = (Map.Entry)it4.next();
			String currentToolInIndex4 = (String) entry4.getKey();//we are searching for this tool's most popular "previous tool"
			ArrayList currentValueListInIndex4 = (ArrayList) entry4.getValue();
			//go through all the values and find the largest (most popular) one
			int mostPopularPreviousToolID = 0;
			int mostPopularPreviousToolTimes = 0;
			int z;
			for (z = 0 ; z < currentValueListInIndex4.size(); z++) {
				int currentValue = ((Integer)(currentValueListInIndex4.get(z))).intValue();
				if (currentValue > mostPopularPreviousToolTimes) {
					mostPopularPreviousToolTimes = currentValue;
					mostPopularPreviousToolID = z;
				}
			}
			String mostPopularPreviousTool = analysisToolSet.get(mostPopularPreviousToolID);
			//System.out.println("Now we get the most popular 'previous tool' for the tool " + currentToolInIndex4 + ":        Tool ID: " + mostPopularPreviousToolID + "     Tool name: " + mostPopularPreviousTool );
			//next we put this result in the respective entry in index 1 (the 4th position in index 1 value)
			ArrayList index1EntryValue = analysisToolIndex.get(currentToolInIndex4);
			
			//HERE WE HAVE THE SAME PROBLEM OF "Net boost", nullPointerException happens!!
			//this problem has been fixed!!!
			//if (index1EntryValue == null)
				//continue;
			
			index1EntryValue.set(3, mostPopularPreviousTool);
			
		}//by now we should already updated all the 4th values in index 1
			
		System.out.println();
		System.out.println("Now let's have a look at the latest (3rd) version of index 1 - with the 3rd position value and 4th position value:");
		System.out.println("The 3rd position value represents the most popular 'next tool' for this tool.");
		System.out.println("The 4th position value represents the most popular 'previous tool' for this tool.");
		Set entries5 = analysisToolIndex.entrySet();
		Iterator it5 = entries5.iterator();
		while (it5.hasNext()) {
			
			Map.Entry entry5 = (Map.Entry)it5.next();
			Object key = entry5.getKey();
			Object value = entry5.getValue();
			System.out.println(key + ": " + value);
		}
		
		
	}//end of method
	
	
	
	
	
	
	/**
	 * This method is used to sort the key sets of index 1 and index 2 based on their entry values
	 * We have to sort the following indices:
	 * Step 1: sort index 1 by its 1st value
	 * Step 2: sort index 1 by its 2nd value
	 * Step 3: sort index 2 by its only value
	 * 
	 * The sorting results will finally be stored in separate ArrayLists, which can be used when necessary
	 */
	public void indexSorting() {
		
		//Step 1
		Set entries1 = analysisToolIndex.entrySet();
		Iterator it1 = entries1.iterator();
		while (it1.hasNext()) {
			
			Map.Entry entry1 = (Map.Entry)it1.next();
			
			String wantedKey = (String) entry1.getKey();
			ArrayList valueList = (ArrayList) entry1.getValue();
			double wantedValue = ((Double) valueList.get(7)).doubleValue();//we want the 1st value
			
			CompareUnit newUnit = new CompareUnit(wantedKey, wantedValue);
			sortedIndex1By1stValue.add(newUnit);
		}
		//sorting
		Collections.sort(sortedIndex1By1stValue);
		
		//Step 2
		Set entries2 = analysisToolIndex.entrySet();
		Iterator it2 = entries2.iterator();
		while (it2.hasNext()) {
			
			Map.Entry entry2 = (Map.Entry)it2.next();
			
			String wantedKey = (String) entry2.getKey();
			ArrayList valueList = (ArrayList) entry2.getValue();
			int wantedValue = ((Integer) valueList.get(1)).intValue();//we want the 2nd value
			
			CompareUnit newUnit = new CompareUnit(wantedKey, wantedValue);
			sortedIndex1By2ndValue.add(newUnit);
		}
		//sorting
		Collections.sort(sortedIndex1By2ndValue);		
		
		//Step 3 ####
		Set entries3 = workFlowIndexCopyForEBValue.entrySet();//####
		Iterator it3 = entries3.iterator();
		while (it3.hasNext()) {
			
			Map.Entry entry3 = (Map.Entry)it3.next();
			
			String wantedKey = (String) entry3.getKey();
			//####
			float wantedValue = ((Float) entry3.getValue()).floatValue();//we want the only value
			CompareUnit newUnit = new CompareUnit(wantedKey, wantedValue);
			sortedIndex2ByOnlyValue.add(newUnit);
		}
		//sorting
		Collections.sort(sortedIndex2ByOnlyValue);		
		
	}//end of method
	
	
	
	
	/**
	 * This method is used to calculate Top 3 most popular work flows containing each tool
	 * To achieve this, we have to use the sorted index 2
	 * after we have found the top X for each tool, we update index 1 again to store such data 
	 * 
	 */
	public void calculateTop3MostPopularWFsContainingEachTool() {
		
		//Traverse each tool in index 1
		Set entries1 = analysisToolIndex.entrySet();
		Iterator it1 = entries1.iterator();
		while (it1.hasNext()) {
			
			Map.Entry entry1 = (Map.Entry)it1.next();
			
			String currentTool = (String) entry1.getKey();
			ArrayList currentValueList = (ArrayList) entry1.getValue();
			
			//now, for each tool in index 1, we search sorted index 2 for top 3 work flows containing this tool
			ArrayList <String> top3WF = new ArrayList();
			int count = 0; //this counter should finally be no greater than 3
			for (int i = 0; i < sortedIndex2ByOnlyValue.size(); i++) {
				String currentWorkFlow = sortedIndex2ByOnlyValue.get(i).getName();
				if (count == 3) {
					break;
				}
				if (currentWorkFlow.contains(currentTool)){
					count++;
					top3WF.add(currentWorkFlow);
				}
				
			}
			//now we have gained the top3 WF for the current tool
			/*
			System.out.println();
			System.out.println("For current tool '" + currentTool + "', the Top 3 WFs containing it are: ");
			for (String s : top3WF) {
				System.out.println(s);
			}
			*/
			
			//now we store the top 3 WF in index 1
			int toBeginWith = 4; //we should put the first of the top 3 in the 4th value position
			for (int j = 0; j < top3WF.size(); j++) {
				currentValueList.set(toBeginWith, top3WF.get(j));
				toBeginWith ++;
			}
		
		}
		
	}//end of method
	
	
	
	
	
	/**
	 * @@@ ------By Cheng 10-18-2008----------------
	 * this method is used specially for providing support for Chris' client side feature:
	 * "All work flows including 'XXX'"
	 * to do this we have to store all the work flows containing each tool in the index
	 * we are using index1 again, and to store such info in its 8th (index = 8) value entry
	 */
	public void fillInIndex1EighthValue() {
		
		//We need to travese the work flow index
		Set entries = workFlowIndex.entrySet();
		Iterator it = entries.iterator();
		while (it.hasNext()) {//the loop to traverse each work flow in index 2
			
			Map.Entry entry = (Map.Entry)it.next();
			String currentWorkFlow = (String)entry.getKey();
			
			StringTokenizer tokens = new StringTokenizer(currentWorkFlow, ",");
			while (tokens.hasMoreTokens()) {
				String currentTool = tokens.nextToken();
				//now we have get one tool in the current work flow
				//the next thing to do is to add the current work flow into index 1
				ArrayList currentIndexValue = analysisToolIndex.get(currentTool);
				HashSet <String> allWFsForCurrentTool = (HashSet)currentIndexValue.get(8);
				allWFsForCurrentTool.add(currentWorkFlow);
				currentIndexValue.set(8, allWFsForCurrentTool);
				analysisToolIndex.put(currentTool, currentIndexValue);
			}
		}
		
		//now we print index 1 again to see whether the 8th value entry has been filled in
		/*
		Set entries1 = analysisToolIndex.entrySet();
		Iterator it1 = entries1.iterator();
		while (it1.hasNext()) {
			Map.Entry entry = (Map.Entry)it1.next();
			Object key = entry.getKey();
			Object value = entry.getValue();
			System.out.println(key + "      " + value);
		}
		*/
		
	}
	
	
	/**
	 * next are some accessor methods for the private members 
	 */
	public ArrayList <String> getAnalysisToolSet() {
		return this.analysisToolSet;
	}
	
	public ArrayList <CompareUnit> getSortedIndex1By1stValue() {
		return this.sortedIndex1By1stValue;
	}
	
	public ArrayList <CompareUnit> getSortedIndex1By2ndValue() {
		return this.sortedIndex1By2ndValue;
	}
	
	public ArrayList <CompareUnit> getSortedIndex2ByOnlyValue() {
		return this.sortedIndex2ByOnlyValue;
	}
	
	public HashMap <String, ArrayList> getAnalysisToolIndex() {
		return this.analysisToolIndex;
	}
	
	public HashMap <String, Integer> getWorkflowIndex() {
		return this.workFlowIndex;
	}
	
	
	
	/**
	 * method for Real Time WF Suggestion feature, statistics A
	 * used for checking that whether a certain workflow exists in the work flow index
	 * if it does, return the times of use
	 * if not, return null
	 * 
	 * @return
	 */
	
	public int getStatA(String cwf) {
		
		int times = 0;
		
//		System.out.println("Now we are looking up CWF: " + cwf);
		if (workFlowIndex.get(cwf) != null) {//if we do have such a work flow in index2
			times = ((Integer)workFlowIndex.get(cwf)).intValue();
//			System.out.println("CWF exists! time of use: " + times);
			
		}
//		else {//if the cwf does NOT exist in index2
//			System.out.println("CWF not in index!");	
//		}
		
		return times;
		
	}
	
	
	
	/**
	 * method for Real Time WF Suggestion feature, statistics B
	 * this is used to find all super flows of CWF in index 2
	 * it returns an arrayList of all the super flows found
	 * if nothing is found, it returns an empty list
	 * @param cwf
	 * @return
	 */
	public ArrayList getStatB(String cwf) {
		
		ArrayList <String> superWorkFlowList = new ArrayList();
		
		//We need to travese the work flow index
		Set entries = workFlowIndex.entrySet();
		Iterator it = entries.iterator();
		while (it.hasNext()) {//the loop to traverse each work flow in index 2
			
			Map.Entry entry = (Map.Entry)it.next();
			String currentWorkFlow = (String)entry.getKey();
			
			if (currentWorkFlow.contains(cwf)) {
				String currentSuperWorkFlowRecord = currentWorkFlow + "#" + ((Integer)entry.getValue()).toString();
				superWorkFlowList.add(currentSuperWorkFlowRecord);
			}
			
		}
		return superWorkFlowList;
	}

	
	/**
	 * Stores all the model information associated with workflows into the DB
	 * Exception are just thrown to the caller who is going to handle them
	 */
	public void storeWorkflowModel() throws Exception {
		con = getConnection();
		try{
			con.setAutoCommit(false);
			
			/**Load the tools table, given the name returns the tool object with all the infos**/
			//we can't use toolIDindex map already provided because it doesn't coincide with DB tools table
			toolsTable = new HashMap<String, Tool>();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select t.id, t.tool,t.description, tp.parameters, tp.usage_count "+
											 "from [Genspace].[dbo].[tools] t left join [Genspace].[dbo].[tool_parameters] tp on t.id = tp.tool_id "+
											 "where tp.parameters is null or tp.usage_count = (select max(usage_count) "+
											 												  "from [Genspace].[dbo].[tool_parameters] "+
											 												  "where tool_id = tp.tool_id)");
			while(rs.next()){
				Tool t = new Tool();
				t.name = rs.getString("tool");
				t.id = rs.getInt("id");	
				t.description = rs.getString("description");
				t.mostCommonParameters = rs.getString("parameters");
				if(t.mostCommonParameters != null){
					t.mostCommonParametersCount = rs.getInt("usage_count");
				}
				toolsTable.put(t.name, t);
			}

			
			
			/** Retrieve all workflows information from ANALYSIS_EVENTS **/
			stmt = con.createStatement();
			String sql = "select * " +
						 "from " + table_name + " A " +
						 "where A.transaction_id <> 'NULL' " +
						 "order by transaction_id, date ";
			rs = stmt.executeQuery(sql);
			//HashMap of all workflows identified so far (workflow.getIdentifier(), workflow)
			Workflow currentInstance = null;
			int oneDayInMS = 1000 * 60 * 60 * 24;
			while (rs.next()) {
				//get the info of one tuple (a single tool usage within a workflow)
				String tid = rs.getString("transaction_id");
				Date date = rs.getDate("date");
				String user = rs.getString("username");
				String toolName = rs.getString("analysis");
				Tool tool = toolsTable.get(toolName);
				
				//check weather the tuple is part of the same workflow instance (same TID and no more than one day difference)
				boolean newWorkflowInstance = false;
				if(currentInstance == null)
					newWorkflowInstance = true;
				else if(!currentInstance.creationTransactionId.equals(tid))
					newWorkflowInstance = true; 
				//NO CHECK ON DATE, a workflow can last for more than a day
				//else if(Math.abs(currentInstance.creationDate.getTime() - date.getTime()) > oneDayInMS)
				//	newWorkflowInstance = true;
					
				
				if(newWorkflowInstance){
					//store this instance in the hashmap before initializing a new instance
					if(currentInstance != null){
						Workflow w = workflows.get(currentInstance.getIdentifier());
						if(w != null){
							//we already found this workflow before, just keep the info of the oldest one
							if(w.creationDate.getTime() > currentInstance.creationDate.getTime()){
								w.creationDate = currentInstance.creationDate;
								w.creationTransactionId = currentInstance.creationTransactionId;
								w.creatorUsername = currentInstance.creatorUsername;
							}
							w.usageCount += 1;
						}
						else{
							//first time we find this workflow
							w = currentInstance;
							w.usageCount = 1;
						}
						//update the map with the update workflow
						workflows.put(w.getIdentifier(), w);
					}
					//previous workflow stored, now create the new one
					currentInstance = new Workflow();
					currentInstance.creationDate = date;
					currentInstance.creationTransactionId = tid;
					currentInstance.creatorUsername  = user;
					//currentInstance.tools.add(tool);
				}
				
				//add next tool to the same workflow instance
				//if the same tool is used multiple times within a small interval, we still add it
				currentInstance.tools.add(tool);
					
			}
			
			/*DEBUG*/
//			System.out.println("workflows size: "+workflows.size());
//			Collection<Workflow> c = workflows.values();
//			int count = 0;
//			for(Workflow w: c){
//				count+= w.usageCount;
//			}
//			System.out.println("total # of workflow usage: "+count);
			
			
			/** for each workflow, find the corresponding id in the workflows DB table*/
			WorkflowManager wm = new WorkflowManager();
			Collection<Workflow> ws = workflows.values();
			int problematic = 0;
			for(Workflow w: ws){
				int parent = -1;
				for(Tool t: w.tools){
					WorkflowNode existsNode = wm.getWorkflowNode(t.name, parent, true);
					if(existsNode == null){
						System.out.println("WORKFLOW PROBLEMATIC\n "+w);
						System.out.println("TOOL\n"+t);
						problematic++;
						break;
						//throw new RuntimeException("Some workflows in Analysis_event are not stored yet into workflows. Therefore, the getWorkflowNode() has to be invoked with true");
					}
					else{
						parent = existsNode.getId();
						w.workflowsTableToolIds.add(parent);
					}
				}
				w.ID = w.workflowsTableToolIds.get(w.workflowsTableToolIds.size()-1);
			}
			System.out.println("PROBLEMS: "+problematic);
			
			/** Store each workflow in the DB, if the workflow exists just update the count */
			ws = workflows.values();
			for(Workflow w: ws){
				String sqlInsert = "insert into workflow_info values (?, ?, ?, ?, ?, ?)";
				PreparedStatement pstmt = con.prepareStatement(sqlInsert);
				pstmt.setInt(1,w.ID);
				pstmt.setString(2,w.creatorUsername);
				pstmt.setDate(3, new java.sql.Date(w.creationDate.getTime()));
				pstmt.setString(4, w.creationTransactionId);
				pstmt.setInt(5, w.usageCount);
				pstmt.setString(6, w.getIdentifier());
				try{
					//insert 
					System.out.println(w);
					pstmt.executeUpdate();
					for(int i = 0; i < w.tools.size(); i++){
						Tool t = w.tools.get(i);
						String sqlInsertTool = "insert into workflow_tool values (?,?,?)";
						PreparedStatement pstmt2 = con.prepareStatement(sqlInsertTool);
						pstmt2.setInt(1, w.ID);
						pstmt2.setInt(2, t.id);
						pstmt2.setInt(3, i);
						pstmt2.executeUpdate();
					}
				}
				catch(SQLException e){
					if(e.getErrorCode() == 2627){
						//Workflow already existing, just update 
						System.out.println("The workflow exists already, just update: \n"+w.tools);
						String sqlUpdate = "update workflow_info set num_usage = ? where id = ?";
						pstmt = con.prepareStatement(sqlUpdate);
						pstmt.setInt(1, w.usageCount);
						pstmt.setInt(2, w.ID);
						pstmt.executeUpdate();
					}
					else{
						throw e;
					}

				}
			}
			
		}
		catch(Exception e){
			con.rollback();
			closeConnection();
			throw e;
		}
		con.commit();
		closeConnection();
	}
	
	

	
}//end of class










/**
 * This class is used as a subsidiary class for SORTING the entries in our indices (index 1 and index 3)
 * In order to make our compareUnits comparable to each other, we have to implement Java interface Comparable
 * 
 * @author cheng
 *
 */
class CompareUnit implements Comparable, Serializable{
	
	private String name;
	private double value; //##the type is changed to long because we need to further add "Exponential Back off"
	
	
	/**
	 * Constructor
	 */
	CompareUnit() {
		
	}
	
	/**
	 * Constructor
	 * @param name
	 * @param value
	 */
	CompareUnit (String name, double value) {
		this.name = name;
		this.value = value;
	}
	
	/**
	 * we need to implement this abstract method defined in the Interface Comparable 
	 */
	public int compareTo(Object obj) {
		CompareUnit unitComparingTo = (CompareUnit) obj;
		return value < unitComparingTo.value? 1 : (value == unitComparingTo.value ? 0 : -1);
	}
	
	/**
	 * mutator
	 * @param name
	 */
	void setName(String name) {
		this.name = name;
	}
	
	/**
	 * accessor
	 * @return
	 */
	String getName() {
		return this.name;
	}
	
	/**
	 * mutator
	 * @param value
	 */
	void setValue (double value) {
		this.value = value;
	}
	
	/**
	 * accessor
	 * @return
	 */
	double getValue() {
		return this.value;
	}
	
}











//