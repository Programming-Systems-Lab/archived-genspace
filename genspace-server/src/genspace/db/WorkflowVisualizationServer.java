package genspace.db;

import genspace.common.Logger;

import java.net.Socket;
import java.util.Scanner;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.ArrayList;

public class WorkflowVisualizationServer extends Server {

	public static final int DEFAULT_PORT = 22222;
	
    /* This is the main method for starting the Server */
    public static void main(String[] args)
    {
    	int port = DEFAULT_PORT;
		if (args.length >= 1)
		{
			port = Integer.parseInt(args[0]);
		}
		else
		{
			System.out.println("Port not specified, using " + port + " as default");
		}

		WorkflowVisualizationServer s = new WorkflowVisualizationServer(port);
		s.run();
    }

    /**
     * Creates a socket server listening on the specified port
     * @param port the port to listen to
     */
    public WorkflowVisualizationServer(int port)
    {
    	super(port);
    }

    /**
     * This method does all the work
     */
    public void run()
    {
    	// TODO: need a graceful shutdown
    	while (true)
    	{
    		try
    		{
    			// wait for a client
    			Socket socket = server.accept();
    			//System.out.println("Connection established");

    			// spin off a new thread
    			WorkflowVisualizationServerThread h = new WorkflowVisualizationServerThread(socket);
    			h.start();
    		}
    		catch (Exception e)
    		{
    			e.printStackTrace();
                if (Logger.isLogError()) Logger.logError(e);
    		}
    	}
    }

    
    /**
     * A class to handle the actual work of dealing with events
     *
     */
    class WorkflowVisualizationServerThread extends Thread
    {
    	Socket socket = null;
    	private org.apache.log4j.Logger log;
    	
    	WorkflowVisualizationServerThread(Socket s)
    	{
    		socket = s;
    		log = util.MyLogger.getInstance(this.getClass().getName());
    	}

    	public void run()
    	{
    	    // the input stream for reading from the network
    	    Scanner in = null; 
    	    PrintWriter pw;
    	    
    	    try
    		{
    			// get the input stream
    			in = new Scanner(socket.getInputStream());
    			
    			String type = in.nextLine();
//    			System.out.println("Type: " + type);
    			log.info("Type: " + type);

    			
    			WorkflowManager wdm = new WorkflowManager();
    			
    			if (type.equals("ALL")) {
        			// read the analysis name
        			String analysis = in.nextLine();
        			//System.out.println("Analysis: " + analysis);
        			log.info("Analysis: " + analysis);
        			
        			// read the username and whether or not to limit the search
        			String login = in.nextLine();
        			boolean myNetworks = in.nextBoolean();
        			//System.out.println("Login: " + login + " " + myNetworks);

        			HashMap<String, Double> workflows = wdm.getAllWorkflows(analysis, login, myNetworks);
        			
        			pw = new PrintWriter(socket.getOutputStream());
        			
        			for (String s : workflows.keySet()) {
        				pw.write(s + "\n");
        				System.out.println(s);
        				pw.flush();
        			}
        			pw.write("END\n");
        			pw.close();
    			} else if (type.equals("START")) {
        			// read the analysis name
        			String analysis = in.nextLine();
        			//System.out.println("Analysis: " + analysis);
        			log.info("Analysis: " + analysis);

        			// read the username and whether or not to limit the search
        			String login = in.nextLine();
        			boolean myNetworks = in.nextBoolean();
        			//System.out.println("Login: " + login + " " + myNetworks);

        			String workflow = wdm.getMostRepeatedWorkflowStartingWith(analysis, login, myNetworks);
        			
        			pw = new PrintWriter(socket.getOutputStream());
        			
        			pw.write(workflow + "\n");
        			System.out.println(workflow);
        			pw.flush();
        			pw.write("END\n");
        			pw.close();
    			} else if (type.equals("INCLUDE")) {
        			// read the analysis name
        			String analysis = in.nextLine();
        			//System.out.println("Analysis: " + analysis);
        			log.info("Analysis: " + analysis);

        			// read the username and whether or not to limit the search
        			String login = in.nextLine();
        			boolean myNetworks = in.nextBoolean();
        			//System.out.println("Login: " + login + " " + myNetworks);
        			
        			String workflow = wdm.getMostRepeatedWorkflowContaining(analysis, login, myNetworks);
        			
        			pw = new PrintWriter(socket.getOutputStream());
        			
        			pw.write(workflow + "\n");
        			System.out.println(workflow);
        			pw.flush();
        			pw.write("END\n");
        			pw.close();
    			}
    			else if (type.equals("TOOLS"))
    			{
    				ArrayList<String> allTools = wdm.getAllTools();
    				pw = new PrintWriter(socket.getOutputStream());
    				for (String tool : allTools)
    				{
    					pw.println(tool);
    					//System.out.println(tool);
    					pw.flush();
    				}
    				pw.println("END");
    				pw.flush();
    			}
    			
    		}
    		catch (Exception e)
    		{
    			e.printStackTrace();
                if (Logger.isLogError()) Logger.logError(e);
    		}
    	}
    }
}
