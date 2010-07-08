package genspace.db;

import genspace.common.Logger;

import java.net.Socket;
import java.util.Scanner;
import java.io.PrintWriter;

public class WorkflowServer extends Server {

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

		WorkflowServer s = new WorkflowServer(port);
		s.run();
    }

    /**
     * Creates a socket server listening on the specified port
     * @param port the port to listen to
     */
    public WorkflowServer(int port)
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
    			Workflow h = new Workflow(socket);
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
    class Workflow extends Thread
    {
    	Socket socket = null;
    	
    	Workflow(Socket s)
    	{
    		socket = s;
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
    			System.out.println("Type: " + type);

    			// read the genspaceLogin
    			String analysis = in.nextLine();
    			System.out.println("Analysis: " + analysis);
    			
    			WorkflowDatabaseManager wdm = new WorkflowDatabaseManager();
    			
    			if (type.equals("ALL")) {
    				String[] workflows = wdm.getAllWorkflows(analysis);
        			
        			pw = new PrintWriter(socket.getOutputStream());
        			
        			for (String s : workflows) {
        				pw.write(s + "\n");
        				System.out.println(s);
        				pw.flush();
        			}
        			pw.write("END\n");
        			pw.close();
    			} else if (type.equals("START")) {
    				String workflow = wdm.getMostRepeatedWorkflowStartingWith(analysis);
        			
        			pw = new PrintWriter(socket.getOutputStream());
        			
        			pw.write(workflow + "\n");
        			System.out.println(workflow);
        			pw.flush();
        			pw.write("END\n");
        			pw.close();
    			} else if (type.equals("INCLUDE")) {
    				String workflow = wdm.getMostRepeatedWorkflowContaining(analysis);
        			
        			pw = new PrintWriter(socket.getOutputStream());
        			
        			pw.write(workflow + "\n");
        			System.out.println(workflow);
        			pw.flush();
        			pw.write("END\n");
        			pw.close();
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
