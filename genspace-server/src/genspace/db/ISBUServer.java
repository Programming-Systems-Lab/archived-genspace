package genspace.db;

import genspace.common.Logger;
import genspace.db.ToolServer.ToolServerHandler;

import java.io.*;
import java.net.*;
import java.util.*;


import org.geworkbench.components.genspace.bean.RatingBean;

public class ISBUServer extends Server {

	public static final int DEFAULT_PORT = 12345;

	public static void main(String args[]) throws Exception {

		int port = DEFAULT_PORT;
		if (args.length >= 1) {
			port = Integer.parseInt(args[0]);
		} else {
			System.out.println("Port not specified, using " + port	+ " as default");
		}

		ISBUServer s = new ISBUServer(port);
		s.run();

	}

	public ISBUServer(int port) {
		super(port);

	}

	public void run() {
		try {
			System.out.println("Initializing suggestion engine...");
			ISBUSuggestionEngine engine = new ISBUSuggestionEngineImpl();
			// initialize the suggestion engine
			engine.engineInitialization();
			System.out.println("Server initialized");
			
			long i = 0;
			long count = 1000;
			
			// TODO: need a graceful shutdown
			while (true) {
				try {
					// wait for a client
					Socket socket = server.accept();

					// spin off a new thread
					Handler h = new Handler(socket, engine);
					h.start();
					
					//stupid hack - for every count number of requests, we want to re-read the index file
					i++;
					if (i % count == 0) engine.engineInitialization();

				} catch (Exception e) {
					e.printStackTrace();
					if (Logger.isLogError())
						Logger.logError(e);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

}

class Handler extends ServerHandler {

	private Socket s;
	private String clientSideID = null;
	private ISBUSuggestionEngine engine;
	private org.apache.log4j.Logger log;

	public Handler(Socket s, ISBUSuggestionEngine engine) {
		super(s);
		this.s = s;
		this.engine = engine;
		log = util.MyLogger.getInstance(this.getClass().getName());
	}

	public void run() {

		try {

			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			clientSideID = (String) ois.readObject();

			// now we see what the client need
			if (clientSideID.equals("getAllAnalysisTools")) {

//				System.out.println();
//				System.out.println("the client is requesting " + clientSideID);
				log.info("the client is requesting " + clientSideID);
				
				ArrayList result = engine.getAllAnalysisTools1();
//				System.out.println("Server has the result: "
//						+ result.toString());
				log.info("Server has the result: "
						+ result.toString());
				
				ObjectOutputStream oos = new ObjectOutputStream(s
						.getOutputStream());
				oos.writeObject(result);
				oos.flush();
//				System.out.println("Response sent back to client.");

			} else if (clientSideID.equals("getTop3MostPopularTools")) {

//				System.out.println();
//				System.out.println("the client is requesting " + clientSideID);
				log.info("the client is requesting " + clientSideID);
				
				ArrayList result = engine.getTop3MostPopularTools();
//				System.out.println("Server has the result: "
//						+ result.toString());
				log.info("Server has the result: "
						+ result.toString());
				
				ObjectOutputStream oos = new ObjectOutputStream(s
						.getOutputStream());
				oos.writeObject(result);
				oos.flush();
//				System.out.println("Response sent back to client.");

			} else if (clientSideID.equals("getTop3MostPopularWFHead")) {

//				System.out.println();
//				System.out.println("the client is requesting " + clientSideID);
				log.info("the client is requesting " + clientSideID);
				
				ArrayList result = engine.getTop3MostPopularWFHead();
//				System.out.println("Server has the result: "
//						+ result.toString());
				log.info("Server has the result: "
						+ result.toString());
				
				ObjectOutputStream oos = new ObjectOutputStream(s
						.getOutputStream());
				oos.writeObject(result);
				oos.flush();
//				System.out.println("Response sent back to client.");

			} else if (clientSideID.equals("getTop3MostPopularWF")) {

//				System.out.println();
//				System.out.println("the client is requesting " + clientSideID);
				log.info("the client is requesting " + clientSideID);
				
				ArrayList result = engine.getTop3MostPopularWF();
//				System.out.println("Server has the result: "
//						+ result.toString());
				log.info("Server has the result: "
						+ result.toString());
				
				ObjectOutputStream oos = new ObjectOutputStream(s
						.getOutputStream());
				oos.writeObject(result);
				oos.flush();
//				System.out.println("Response sent back to client.");

			} else if (clientSideID.contains("feature2")) {

				StringTokenizer tokens = new StringTokenizer(clientSideID, ",");
				tokens.nextToken(); // skip the token "feature2"
				String whichMethod = tokens.nextToken();
//				System.out.println();
//				System.out.println("the client is requesting feature2, "
//						+ whichMethod);
				log.info("the client is requesting feature2, "
						+ whichMethod);

				String toolName = tokens.nextToken();
//				System.out
//						.println("the user is requesting info about the tool '"
//								+ toolName + "'");
				log.info("the user is requesting info about the tool '"
						+ toolName + "'");

				// see which method it is
				if (whichMethod.equals("getUsageRate")) {

					String result = engine.getUsageRate(toolName);
					ObjectOutputStream oos = new ObjectOutputStream(s
							.getOutputStream());
					oos.writeObject(result);
					oos.flush();
//					System.out.println("Response sent back to client.");

				} else if (whichMethod.equals("getUsageRateAsWFHead")) {

					String result = engine.getUsageRateAsWFHead(toolName);
					ObjectOutputStream oos = new ObjectOutputStream(s
							.getOutputStream());
					oos.writeObject(result);
					oos.flush();
//					System.out.println("Response sent back to client.");

				} else if (whichMethod.equals("getMostPopularNextTool")) {

					String result = engine.getMostPopularNextTool(toolName);
					ObjectOutputStream oos = new ObjectOutputStream(s
							.getOutputStream());
					oos.writeObject(result);
					oos.flush();
//					System.out.println("Response sent back to client.");

				} else if (whichMethod.equals("getMostPopularPreviousTool")) {

					String result = engine.getMostPopularPreviousTool(toolName);
					ObjectOutputStream oos = new ObjectOutputStream(s
							.getOutputStream());
					oos.writeObject(result);
					oos.flush();
//					System.out.println("Response sent back to client.");

				} else if (whichMethod
						.equals("getTop3MostPopularWFForThisTool")) {

					ArrayList<String> result = engine
							.getTop3MostPopularWFForThisTool(toolName);
					ObjectOutputStream oos = new ObjectOutputStream(s
							.getOutputStream());
					oos.writeObject(result);
					oos.flush();
//					System.out.println("Response sent back to client.");

				}

			} else if (clientSideID.contains("fall08Enhance")) {// the following
				// block is to
				// handle some
				// enhanced
				// features for
				// fall 08 term

				StringTokenizer tokens = new StringTokenizer(clientSideID, ",");
				tokens.nextToken(); // skip the token "fall08Enhance"
				String whichMethod = tokens.nextToken();
//				System.out.println();
//				System.out.println("the client is requesting fall08Enhance, "
//						+ whichMethod);
				log.info("the client is requesting fall08Enhance, "
						+ whichMethod);

				String toolName = tokens.nextToken();
//				System.out
//						.println("the user is requesting info about the tool '"
//								+ toolName + "'");
				log.info("the user is requesting info about the tool '"
						+ toolName + "'");

				if (whichMethod.equals("getAllWFsIncludingThisTool")) {
					HashSet<String> result = engine
							.getAllWFsIncludingThisTool(toolName);
					ObjectOutputStream oos = new ObjectOutputStream(s
							.getOutputStream());
					oos.writeObject(result);
					oos.flush();
//					System.out.println("Response sent back to client.");

				} else if (whichMethod
						.equals("getMostCommonWFIncludingThisTool")) {
					String result = engine
							.getMostCommonWFIncludingThisTool(toolName);
					ObjectOutputStream oos = new ObjectOutputStream(s
							.getOutputStream());
					oos.writeObject(result);
					oos.flush();
//					System.out.println("Response sent back to client.");
				} else if (whichMethod
						.equals("getMostCommonWFStartingWithThisTool")) {
					String result = engine
							.getMostCommonWFStartingWithThisTool(toolName);
					ObjectOutputStream oos = new ObjectOutputStream(s
							.getOutputStream());
					oos.writeObject(result);
					oos.flush();
//					System.out.println("Response sent back to client.");
				}

			}

			// **************************************************************************
			// ************************ Workflow Rating
			// *********************************

			// given a workflow (arraylist of tool name strings), this will
			// return the highest tool rated next
			else if (clientSideID.contains("nextBestRatedTool")) {

				ArrayList args = (ArrayList) ois.readObject();

				// get username
				ArrayList<String> workflow = (ArrayList) args.get(0);
				String user = (String) args.get(1);
			
//				System.out.println(user
//								+ " is requesting the next best rated tool for workflow.");
				log.info(clientSideID + ":" + user + ":" + workflow.toString());

				WorkflowManager wManager = new WorkflowManager();
				WorkflowNode currentNode = wManager.getWorkflowNode(workflow
						.toArray(new String[1]), true);

				if (currentNode == null) {
					System.out
							.println("Could not find or create this requested workflow.");
					respond("none");
					return;
				}

				WorkflowNode[] children = wManager.getChildren(currentNode
						.getId());

				RatingManager rManager = new RatingManager();
				RatingBean max = null;

				if (children != null){
					for (WorkflowNode c : children) {
						// get the rating for the child
						RatingBean r = rManager.getRating(c.getId(),
								"workflow_ratings", user);
	
						if (max == null)
							max = r;
						else if (r.compareTo(max) == 1)
							max = r;
					}
				}

				// write out rating
				//if there are no ratings for any children
				if (max == null || max.getOverallRating() == 0){
					respond("none");
					System.out.println("No ratings available for workflow children.");
				}
				else {
					String result = 
						wManager.getWorkflowNode(max.getIdentifier()).getToolName().trim() 
						+ " (" + max.getOverallRating() + " - " 
						+ max.getTotalRatings() + " times)";
					
					respond(result);
//					System.out.println("Best rated tool to use next: " + result);
					log.info("Best rated tool to use next: " + result);
				}
				
				
				
			} else if (clientSideID.contains("getWFId")) {

				ArrayList<String> workflow = (ArrayList) ois.readObject();

//				System.out.println("Workflow ID request.");
				log.info("Workflow ID request:" + workflow.toString());

				WorkflowManager wManager = new WorkflowManager();
				WorkflowNode currentNode = wManager.getWorkflowNode(workflow
						.toArray(new String[1]), false);

				if (currentNode == null) {
					System.out
							.println("Could not find or create this requested workflow.");
					respond(null);
					return;
				}

				// write out rating
				System.out.println("Workflow ID: " + currentNode.getId());
				respond(new Integer(currentNode.getId()));
			}
			// given a list of tool strings in workflow order, this will give
			// you the rating of the workflow
			else if (clientSideID.contains("getWFRating")) {

				ArrayList args = (ArrayList) ois.readObject();
				
				

				// get username
				Integer wid = (Integer) args.get(0);
				String user = (String) args.get(1);
				
				log.info("getWFRating:"+user+":"+wid);

				// just in case
				if (wid == -1) {
					respond(null);
					return;
				}

				RatingManager rManager = new RatingManager();
				RatingBean r = rManager
						.getRating(wid, "workflow_ratings", user);

				// write out rating
//				System.out.println("Current workflow rating: " + r);
				log.info("Current workflow rating: " + r);
				respond(r);
			}

			// creates a workflow rating based on a workflow node id, a username, and an integer rating (1-5)
			else if (clientSideID.contains("writeWFRating")) {

				ArrayList args = (ArrayList) ois.readObject();

				// get username
				int wid = (Integer) args.get(0);
				String user = (String) args.get(1);
				int rating = (Integer) args.get(2);
				
				log.info("writeWFRating:"+user+":"+wid+":"+rating);

				// just in case
				if (wid == -1) {
					respond(null);
					return;
				}

//				System.out.println(user
//						+ " is creating a rating for a workflow.");

				RatingManager rManager = new RatingManager();
				RatingBean newRating = rManager.writeRating(wid, rating,
						"workflow_ratings", user);

				// write out rating
//				System.out.println("Current workflow rating: " + newRating);

				respond(newRating);

			}

			
			
			
			
			
			
			
			//**************************************************************************
			//************************ Instant Messaging***********************************
			else if (clientSideID.startsWith("insert into Messagebox")) {
				System.out.println(clientSideID);
				StringTokenizer tokens = new StringTokenizer(clientSideID, "#");
				tokens.nextToken();
				String message = tokens.nextToken();
				String fromUser = tokens.nextToken();
				String toUser = tokens.nextToken();
//				System.out.println();
//				System.out.println();
//				System.out.println("the client is requesting " + clientSideID);
				log.info(clientSideID + ":" + message + ":" + fromUser + ":" + toUser);
				try{
				Boolean result = engine.insertIntoMessagebox(fromUser, toUser, message);
//				System.out.println("Server has the result: " + result.toString());
				ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
				oos.writeObject(result);
				oos.flush();
//				System.out.println("Response sent back to client.");
				}
				catch(Exception e){
				}	
		}
			else if (clientSideID.startsWith("insert into Group Messagebox")) {
				System.out.println(clientSideID);
				StringTokenizer tokens = new StringTokenizer(clientSideID, "#");
				tokens.nextToken();
				String message = tokens.nextToken();
				String fromUser = tokens.nextToken();
				String networkName = tokens.nextToken();
//				System.out.println();
//				System.out.println();
//				System.out.println(message);
//				System.out.println(fromUser);
//				System.out.println(networkName);
//				System.out.println("the client is requesting " + clientSideID);
				log.info(clientSideID + ":" + message + ":" + fromUser + ":" + networkName);
				try{
				Boolean result = engine.insertIntoGroupMessagebox(fromUser, networkName, message);			
//				System.out.println("Server has the result: " + result.toString());
				ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
				oos.writeObject(result);
				oos.flush();
//				System.out.println("Response sent back to client.");
				}
				catch(Exception e){
				}	
		}
			else if (clientSideID.startsWith("select from inbox")) {
//					System.out.println();
					String userid = null;
					for(int i = 0; i< clientSideID.length(); i++){
						if (clientSideID.charAt(i) == '#'){
							userid = clientSideID.substring(i+1);
//							System.out.println("MessageId: "+userid);
							log.info("MessageId: "+userid);
							
							break;
						}
					}
//					System.out.println("the client is requesting " + clientSideID);
					log.info("the client is requesting " + clientSideID);
					
					ArrayList result = engine.getAllInboxMsgForCurrentUser(userid);
//					System.out.println("Server has the result: " + result.toString());
					log.info("Server has the result: " + result.toString());
					
					ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
					oos.writeObject(result);
					oos.flush();
//					System.out.println("Response sent back to client.");
			}
			
			else if (clientSideID.equals("select user row count")) {
//				System.out.println();
//				System.out.println("the client is requesting " + clientSideID);
				log.info("the client is requesting " + clientSideID);
				
				ArrayList result = engine.getAllInboxMsgForCurrentUser("ad2660");
//				System.out.println("Server has the result: " + result.toString());
				log.info("Server has the result: " + result.toString());
				
				ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
				oos.writeObject(result);
				oos.flush();
//				System.out.println("Response sent back to client.");
		}
			else if (clientSideID.equals("select from network visibility")) {
//				System.out.println();
//				System.out.println("the client is requesting " + clientSideID);
				log.info("the client is requesting " + clientSideID);
				
				ArrayList result = engine.getNetworkVisibility("user1");
//				System.out.println("Server has the result: " + result.toString());
				log.info("Server has the result: " + result.toString());
				
				ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
				oos.writeObject(result);
				oos.flush();
//				System.out.println("Response sent back to client.");
		}
			
			
			else if (clientSideID.startsWith("select from outbox")) {
//				System.out.println();
//				System.out.println();
				String userid = null;
				for(int i = 0; i< clientSideID.length(); i++){
					if (clientSideID.charAt(i) == '#'){
						userid = clientSideID.substring(i+1);
//						System.out.println("MessageId: "+userid);
						log.info("MessageId: "+userid);
						
						break;
					}
				}
//				System.out.println("the client is requesting " + clientSideID);
				log.info("the client is requesting " + clientSideID);
				
				ArrayList result = engine.getAllOutboxMsgForCurrentUser(userid);
//				System.out.println("Server has the result: " + result.toString());
				log.info("Server has the result: " + result.toString());
				
				ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
				oos.writeObject(result);
				oos.flush();
//				System.out.println("Response sent back to client.");
								
		}
			
			else if (clientSideID.startsWith("select users from registration")) {
				StringTokenizer tokens = new StringTokenizer(clientSideID, "#");
				tokens.nextToken();
				String current_user = tokens.nextToken();
//				System.out.println("the client is requesting " + clientSideID);
				log.info("the client is requesting " + clientSideID);
				
				ArrayList <String> result = engine.getListOfUserNames(current_user);
//				System.out.println("Server has the result: " + result.toString());
				log.info("Server has the result: " + result.toString());
				
				ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
				oos.writeObject(result);
				oos.flush();
//				System.out.println("Response sent back to client.");
		}
			else if (clientSideID.startsWith("select network")) {
				StringTokenizer tokens = new StringTokenizer(clientSideID, "#");
				tokens.nextToken();
				String current_user = tokens.nextToken();
//				System.out.println("the client is requesting " + clientSideID);
				log.info("the client is requesting " + clientSideID);
				
				ArrayList <String> result = engine.getListOfNetworks(current_user);
//				System.out.println("Server has the result: " + result.toString());
				log.info("Server has the result: " + result.toString());
				
				ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
				oos.writeObject(result);
				oos.flush();
//				System.out.println("Response sent back to client.");
		}
			
			else if (clientSideID.startsWith("delete from inbox")) {
				String messageID = null;
				for(int i = 0; i< clientSideID.length(); i++){
					if (clientSideID.charAt(i) == '#'){
						messageID = clientSideID.substring(i+1);
//						System.out.println("MessageId: "+messageID);
						log.info("MessageId: "+messageID);
						
						break;
					}
				}
//				System.out.println();
//				System.out.println("the client is requesting " + clientSideID);
				log.info("the client is requesting " + clientSideID);
				
				try{
				Boolean result = engine.deleteFromInbox(messageID);
//				System.out.println("Server has the result: " + result.toString());
				log.info("Server has the result: " + result.toString());
				
				ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
				oos.writeObject(result);
				oos.flush();
//				System.out.println("Response sent back to client.");
				}
				catch(Exception e){
					e.printStackTrace();
				}
				
		}
			
			else if (clientSideID.startsWith("delete from outbox")) {
				String messageID = null;
				for(int i = 0; i< clientSideID.length(); i++){
					if (clientSideID.charAt(i) == '#'){
						messageID = clientSideID.substring(i+1);
//						System.out.println("MessageId: "+messageID);
						log.info("MessageId: "+messageID);
						
						break;
					}
				}
//				System.out.println();
//				System.out.println("the client is requesting " + clientSideID);
				log.info("the client is requesting " + clientSideID);
				
				try{
				Boolean result = engine.deleteFromOutbox(messageID);
//				System.out.println("Server has the result: " + result.toString());
				log.info("Server has the result: " + result.toString());
				
				ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
				oos.writeObject(result);
				oos.flush();
//				System.out.println("Response sent back to client.");
				}
				catch (Exception e){
					e.printStackTrace();
				}
		}

			
			
			
			
			
			
			// **************************************************************************
			// ************************ USER Ratings
			// ************************************

			// void rate(String tableName, String identifier, int rating,
			// String/int
			// rating) and also Rating getRating(String tableName, String
			// identifier, String/int user)

			else if (clientSideID.startsWith("insert into ratings")) {
				Boolean result = false;
				StringTokenizer tokens = new StringTokenizer(clientSideID, ",");
				String tableName = tokens.nextToken();
				String identifier = tokens.nextToken();
				String rating = tokens.nextToken();
				String user = tokens.nextToken();
//				System.out.println();
//				System.out.println("the client is requesting " + clientSideID);
				log.info("the client is requesting " + clientSideID);
				
				if (tableName.equals("workflows")) {
					result = engine.insertIntoWFRatings(identifier, rating,
							user);
				} else if (tableName.equals("tools")) {
					result = engine.insertIntoToolsRatings(identifier, rating,
							user);
				}
//				System.out.println("Server has the result: "
//						+ result.toString());
				log.info("Server has the result: " + result.toString());
				
				ObjectOutputStream oos = new ObjectOutputStream(s
						.getOutputStream());
				oos.writeObject(result);
				oos.flush();
//				System.out.println("Response sent back to client.");
			}

			else if (clientSideID.startsWith(("select from ratings"))) {
				StringTokenizer tokens = new StringTokenizer(clientSideID, ",");
				String tableName = tokens.nextToken();
				String id = tokens.nextToken();
				String user = tokens.nextToken();
				ArrayList result = null;
//				System.out.println();
//				System.out.println("the client is requesting " + clientSideID);
				log.info("the client is requesting " + clientSideID);
				
				if (tableName.equals("workflows")) {
					result = engine.getAllWFRating(id, user);
				} else if (tableName.equals("tools")) {
					result = engine.getAllToolsRating(id, user);
				}
//				System.out.println("Server has the result: "
//						+ result.toString());
				log.info("Server has the result: "+ result.toString());
				ObjectOutputStream oos = new ObjectOutputStream(s
						.getOutputStream());
				oos.writeObject(result);
				oos.flush();
//				System.out.println("Response sent back to client.");
			} 
			
			
			
			
			
			
			//********************for Real Time Work Flow Suggestion***************************
			//by Cheng
			else if (clientSideID.startsWith("RTWFS")) {
				StringTokenizer tokens = new StringTokenizer(clientSideID, "#");
				tokens.nextToken();
				String whichMethod = tokens.nextToken();
				
//				System.out.println();
//				System.out.println("the client is requesting RTWFS, " + whichMethod);
				log.info("the client is requesting RTWFS, " + whichMethod);
				
				String currentWF = tokens.nextToken();
//				System.out.println("Server has get CWF: " + currentWF);
				log.info("Server has get CWF: " + currentWF);
				
				//call the API
				ArrayList result = engine.getRealTimeWorkFlowSuggestion(currentWF);
				
//				System.out.println("Server has the result for RTWFS! " );
				log.info("Server has the result for RTWFS! " );
				
				ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
				oos.writeObject(result);
				oos.flush();
//				System.out.println("Response sent back to client.(RTWFS)");
				
			}
			
			
			else {

				System.err.println("Invalid Client side ID!!");
			}

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

}
