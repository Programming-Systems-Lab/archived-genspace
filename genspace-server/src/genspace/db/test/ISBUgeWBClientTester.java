package genspace.db.test;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * This class is used to simulate the geWorkBench client side operations when it needs to talk to genSpace ISBU suggestion
 * server, the final working code should be put into the real client side GUI. This class is only for testing purpose 
 * @author cheng
 *
 */

public class ISBUgeWBClientTester {
	
	private static String clientSideID = null;
	private static String serverIP = "127.0.0.1";
	private static int serverPort = 12345; 
	
	private static String toolApplied = "SOM Analysis";
	
	public static void main(String args[]) throws Exception {
		
		System.out.println("Now suppose geWB client side application has been started...");
		System.out.println("Now we simulate the process that the client request the genSpace server for suggestion INFO.");
		System.out.println("We test requesting each of the server side methods one by one, using separate Socket sessions.");
		System.out.println();
		
		testGetAllAnalysisTools();
		
		testGetTop3MostPopularTools();
		
		testGetTop3MostPopularWFHead();
		
		testGetTop3MostPopularWF();
		
		//-------------------- feature 2 -----------
		
		testGetUsageRate();
		
		testGetUsageRateAsWFHead();
		
		testGetMostPopularNextTool();
		
		testGetMostPopularPreviousTool();
		
		testGetTop3MostPopularWFForThisTool();
		
		//-------------------- fall 08 ------------
		
		testGetAllWFsIncludingThisTool();
		
		testGetMostCommonWFIncludingThisTool();
		
		testGetMostCommonWFStartingWithThisTool();
		
		
		
	}
	
	
	
	
	
	private static void testGetAllAnalysisTools() throws Exception {
		
		//now we test the method "getAllAnalysisTools"
		clientSideID = "getAllAnalysisTools";
		Socket s = new Socket(serverIP, serverPort);
		ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
		oos.writeObject(clientSideID);
		oos.flush();
		System.out.println("client request sent");
		
		//waiting for server response...
		ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
		System.out.println("waiting response from server.....");
		ArrayList listBack = (ArrayList) ois.readObject();
		System.out.println("response from server received: All Analysis Tools: " + listBack.toString());
		System.out.println();
	}
	
	
	
	
	
	private static void testGetTop3MostPopularTools() throws Exception {
		
		//now we test the method "getTop3MostPopularTools"
		clientSideID = "getTop3MostPopularTools";
		Socket s = new Socket(serverIP, serverPort);
		ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
		oos.writeObject(clientSideID);
		oos.flush();
		System.out.println("client request sent");
		
		//waiting for server response...
		ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
		System.out.println("waiting response from server.....");
		ArrayList listBack = (ArrayList) ois.readObject();
		System.out.println("response from server received: Top 3 most Pop Tools: " + listBack.toString());
		System.out.println();
	}
	
	
	
	private static void testGetTop3MostPopularWFHead() throws Exception {
		
		//now we test the method "getTop3MostPopularWFHead"
		clientSideID = "getTop3MostPopularWFHead";
		Socket s = new Socket(serverIP, serverPort);
		ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
		oos.writeObject(clientSideID);
		oos.flush();
		System.out.println("client request sent");
		
		//waiting for server response...
		ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
		System.out.println("waiting response from server.....");
		ArrayList listBack = (ArrayList) ois.readObject();
		System.out.println("response from server received: top 3 most Pop WF heads: " + listBack.toString());
		System.out.println();
	}
	
	
	
	
	private static void testGetTop3MostPopularWF() throws Exception {
		
		//now we test the method "getTop3MostPopularWF"
		clientSideID = "getTop3MostPopularWF";
		Socket s = new Socket(serverIP, serverPort);
		ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
		oos.writeObject(clientSideID);
		oos.flush();
		System.out.println("client request sent");
		
		//waiting for server response...
		ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
		System.out.println("waiting response from server.....");
		ArrayList listBack = (ArrayList) ois.readObject();
		System.out.println("response from server received: top 3 most Pop WFs: " + listBack.toString());
		System.out.println();
	}
	
	
	
	
	//------------------ feature 2 ------------------
	
	
	
	private static void testGetUsageRate() throws Exception {
		
		clientSideID = "feature2,getUsageRate," + toolApplied;//zhu yi kong ge shi zi dai de ...
		Socket s = new Socket(serverIP, serverPort);
		ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
		oos.writeObject(clientSideID);
		oos.flush();
		System.out.println("client request sent");
		
		//waiting for server response...
		ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
		System.out.println("waiting response from server.....");
		String resultValue = (String) ois.readObject();
		System.out.println("response from server received: usage rate for this tool: " + resultValue);
		System.out.println();
		
	}
	
	
	private static void testGetUsageRateAsWFHead() throws Exception {
		
		clientSideID = "feature2,getUsageRateAsWFHead," + toolApplied;//zhu yi kong ge shi zi dai de ...
		Socket s = new Socket(serverIP, serverPort);
		ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
		oos.writeObject(clientSideID);
		oos.flush();
		System.out.println("client request sent");
		
		//waiting for server response...
		ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
		System.out.println("waiting response from server.....");
		String resultValue = (String) ois.readObject();
		System.out.println("response from server received: usage rate for this tool as WF head: " + resultValue);
		System.out.println();
	}
	
	
	private static void testGetMostPopularNextTool() throws Exception {
		
		clientSideID = "feature2,getMostPopularNextTool," + toolApplied;//zhu yi kong ge shi zi dai de ...
		Socket s = new Socket(serverIP, serverPort);
		ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
		oos.writeObject(clientSideID);
		oos.flush();
		System.out.println("client request sent");
		
		//waiting for server response...
		ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
		System.out.println("waiting response from server.....");
		String resultValue = (String) ois.readObject();
		System.out.println("response from server received: most Pop next tool of this tool: " + resultValue);
		System.out.println();
	}
	
	
	
	private static void testGetMostPopularPreviousTool() throws Exception {
		
		clientSideID = "feature2,getMostPopularPreviousTool," + toolApplied;//zhu yi kong ge shi zi dai de ...
		Socket s = new Socket(serverIP, serverPort);
		ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
		oos.writeObject(clientSideID);
		oos.flush();
		System.out.println("client request sent");
		
		//waiting for server response...
		ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
		System.out.println("waiting response from server.....");
		String resultValue = (String) ois.readObject();
		System.out.println("response from server received: most Pop previous tool of this tool: " + resultValue);
		System.out.println();
	}
	
	
	
	private static void testGetTop3MostPopularWFForThisTool() throws Exception {
		
		clientSideID = "feature2,getTop3MostPopularWFForThisTool," + toolApplied;//zhu yi kong ge shi zi dai de ...
		Socket s = new Socket(serverIP, serverPort);
		ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
		oos.writeObject(clientSideID);
		oos.flush();
		System.out.println("client request sent");
		
		//waiting for server response...
		ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
		System.out.println("waiting response from server.....");
		ArrayList <String> resultValues = (ArrayList) ois.readObject();
		System.out.println("response from server received: top3 most Pop WF for this tool: " + resultValues);
		System.out.println();
		
		
	}
	
	
	
	//------------------- fall 08 -----------------
	
	
	
	private static void testGetAllWFsIncludingThisTool() throws Exception {
		
		clientSideID = "fall08Enhance,getAllWFsIncludingThisTool," + toolApplied;//zhu yi kong ge shi zi dai de ...
		Socket s = new Socket(serverIP, serverPort);
		ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
		oos.writeObject(clientSideID);
		oos.flush();
		System.out.println("client request sent");
		
		//waiting for server response...
		ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
		System.out.println("waiting response from server.....");
		HashSet <String> resultValues = (HashSet) ois.readObject();
		System.out.println("response from server received: All WFs including the tool - " + toolApplied + ": " + resultValues);
		System.out.println();
		
		
	}
	
	
	
	private static void testGetMostCommonWFIncludingThisTool() throws Exception {
		
		clientSideID = "fall08Enhance,getMostCommonWFIncludingThisTool," + toolApplied;//zhu yi kong ge shi zi dai de ...
		Socket s = new Socket(serverIP, serverPort);
		ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
		oos.writeObject(clientSideID);
		oos.flush();
		System.out.println("client request sent");
		
		//waiting for server response...
		ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
		System.out.println("waiting response from server.....");
		String resultValue = (String) ois.readObject();
		System.out.println("response from server received: most common WF including the tool - " + toolApplied + ": " + resultValue);
		System.out.println();
		
		
	}
	
	
	
	private static void testGetMostCommonWFStartingWithThisTool() throws Exception {
		
		clientSideID = "fall08Enhance,getMostCommonWFStartingWithThisTool," + toolApplied;//zhu yi kong ge shi zi dai de ...
		Socket s = new Socket(serverIP, serverPort);
		ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
		oos.writeObject(clientSideID);
		oos.flush();
		System.out.println("client request sent");
		
		//waiting for server response...
		ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
		System.out.println("waiting response from server.....");
		String resultValue = (String) ois.readObject();
		System.out.println("response from server received: most common WF starting the tool - " + toolApplied + ": " + resultValue);
		System.out.println();
		
		
	}
	
	
	
	
	

	
	
	
	
	

}
