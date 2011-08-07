package genspace.db;

import java.net.*;
import java.io.*;
import java.util.*;

public class TestClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestClient t = new TestClient();
		t.go();
	}
	
	public void go()
	{
		try
		{
			// connect to the server
			Socket s = new Socket("localhost", 8282);
			
			// send the name
			PrintWriter pw = new PrintWriter(s.getOutputStream());
			pw.println("chris");
			pw.flush();
			
			// write the header and such
			print("<?xml version=\"1.0\"?>");
			print("<!DOCTYPE GraphXML SYSTEM \"GraphXML.dtd\">");
			print("<graph id=\"My First Graph\">");
			
			// keep track of the user nodes we've created so far
			ArrayList<String> users = new ArrayList<String>();

			// this is the network that we're currently dealing with
			String currentNetwork = null;
			
			// now start reading
			Scanner in = new Scanner(s.getInputStream());
			while(in.hasNext())
			{
				String line = in.nextLine();
				//System.out.println(line);
				
				// if it's a network, update the currentNetwork
				if (line.startsWith("NETWORK"))
				{
					currentNetwork = line.split(" ")[1].trim();
					
					// now create the node for this network
					print("<node name=\"" + currentNetwork + "\"><label>" + currentNetwork + "</label></node>");
				}
				// if it's a user, create a node (if it doesn't already exist) and add an edge
				else if (line.startsWith("USER"))
				{
					String user = line.split(" ")[1].trim();
					
					// add a node if the user doesn't already exist
					if (users.contains(user) == false)
					{
						print("<node name=\"" + user + "\"><label>" + user + "</label></node>");
						users.add(user);
					}
					
					// now connect the user to the network
					print("<edge source=\"" + user + "\" target=\"" + currentNetwork + "\" />");
					
				}
				
			}
			print("</graph>");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}
	
	

	private void print(String s)
	{
		System.out.println(s);
	}

}
