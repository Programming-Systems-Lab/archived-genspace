package genspace.db;

import java.util.*;

public interface ISBUSuggestionEngine {
	
	//method for suggestion engine initialization
	public void engineInitialization()throws Exception;;
	
	//methods for suggestion feature 1
	public ArrayList <String> getAllAnalysisTools1();
	public ArrayList <String> getTop3MostPopularTools();
	public ArrayList <String> getTop3MostPopularWFHead();
	public ArrayList <String> getTop3MostPopularWF();
	
	
	//methods for suggestion feature 2
	//public HashMap <String, ArrayList> getSuggestionForEachTool();
	public String getUsageRate(String toolName);
	public String getUsageRateAsWFHead(String toolName);
	public String getMostPopularNextTool(String toolName);
	public String getMostPopularPreviousTool(String toolName);
	public ArrayList <String> getTop3MostPopularWFForThisTool(String toolName);
	
	
	//methods for ISBU enhancements for Fall 08 term
	//especially for Chris' client features
	public HashSet <String> getAllWFsIncludingThisTool(String toolName);
	public String getMostCommonWFIncludingThisTool(String toolName);
	public String getMostCommonWFStartingWithThisTool(String toolName);
	
	
	

	//methods for the genSpace DM group's Instant Messaging feature *******************
	//Nov. 11 2008
	public ArrayList <String> getListOfUserNames(String current_user);
	public ArrayList <String> getListOfNetworks(String current_user);
	public ArrayList <String> getUserCount();
	public boolean insertIntoMessagebox(String fromUser, String toUser, String message);
	public boolean insertIntoGroupMessagebox(String fromUser, String networkName, String message);
	//for each of the element in the arrayList, we should have name+msgBODY
	public ArrayList <String> getAllInboxMsgForCurrentUser(String currentUserId);
	public boolean deleteFromInbox(String messageID);//how can you know the msg ID???
	//for each of the element in the arrayList, we should have name+msgBODY
	public ArrayList <String> getAllOutboxMsgForCurrentUser(String currentUserId);
	public boolean deleteFromOutbox(String messageID);//how can you know the ID???
	public boolean insertIntoWFRatings(String identifier, String rating, String user);
	public boolean insertIntoToolsRatings(String identifier, String rating, String user);
	public ArrayList <String> getAllWFRating(String id,String username);
	public ArrayList <String> getAllToolsRating(String id,String username);
	public ArrayList <String> getNetworkVisibility(String username);
	
	
	
	
	
	
	
	//methods for the genSpace DM group's Ratings feature *******************
	//Nov. 18 2008
	public ArrayList <String> getAllRatingsForCurrentUser(String currentUserId);
	
	
	

	//methods for the Real Time Workflow Suggestion feature *****************
	//by Cheng
	public ArrayList getRealTimeWorkFlowSuggestion( String cwfString);
	
	
	
	
	
	
	

}
