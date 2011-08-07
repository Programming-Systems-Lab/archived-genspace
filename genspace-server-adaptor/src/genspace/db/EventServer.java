package genspace.db;

/**
 * This class is the front-end server for getting XML files sent from the field and then writing them
 * to the database. 
 * 
 * TODO: handle errors more gracefully
 */

import genspace.GenSpaceServerFactory;
import genspace.common.Logger;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

import org.geworkbench.components.genspace.server.stubs.AnalysisEvent;


public class EventServer extends Server
{
	public static final int DEFAULT_PORT = 12346;
	
    // the tool for writing to the database
    private XMLLoader loader;
    
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

		EventServer xs = new EventServer(port);
		xs.start();
    }

    /**
     * Creates a socket server listening on the specified port
     * @param port the port to listen to
     */
    public EventServer(int port)
    {
    	super(port);
    	loader = new XMLLoader();
    }

    
    /**
     * This method just listens for new connections and then hands them off to another thread (the Handler)
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
    			
    			// spin off a new thread to deal with the work
    			Handler h = new Handler(socket);
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
    class Handler extends Thread
    {
    	Socket socket = null;
    	
    	Handler(Socket s)
    	{
    		socket = s;
    	}

    	public void run()
    	{
    	    // the output stream for writing the file
    	    PrintWriter out = null;

    	    // the input stream for reading from the network
    	    Scanner in = null; 

    	    try
    		{
    			// get the input stream
    			in = new Scanner(socket.getInputStream());

    			// the name of the file should be on the first line
    			String fileName = "_" + in.nextLine().replace(":", "-");
    			//System.out.println("File is " + fileName);

    			// create the File object
    			File file = new File(fileName);

    			// create the PrintWriter to write to the file
    			out = new PrintWriter(file);

    			// flag to indicate whether we should keep reading
    			boolean keepGoing = true;

    			while (keepGoing)
    			{
    				// read the next line
    				String line = in.nextLine();

    				// TODO: how do we know when we're at the end of the message?
    				if(!line.equals("END"))
    				{
    					// echo it out
    					//System.out.println(line);

    					// write to the file
    					out.println(line);
    					out.flush();
    				}
    				else
    				{
    					// if we're at the end, it's time to stop
    					keepGoing = false;
    				}
    			}
		    
    			// we have the file, now write it to the database using the "XMLLoader"
    			ArrayList<AnalysisEvent> events = loader.readAndLoad(fileName);
    			GenSpaceServerFactory.getUsageOps().sendUsageLog(events);
    		}
    		catch (Exception e)
    		{
    			e.printStackTrace();
                if (Logger.isLogError()) Logger.logError(e);
    		}
    		finally
    		{
    			try { out.flush(); } catch (Exception e) { }
    			try { out.close(); } catch (Exception e) { }
    			// try { server.close(); } catch (Exception e) { }
    		}
    	    
    	}
    	
    }

}
