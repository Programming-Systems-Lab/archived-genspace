package genspace.db;
/**
 * TODO: handle errors more gracefully
 */

import genspace.common.Logger;

import java.io.*;
import java.net.*;
import java.util.Scanner;


public class LoginServer extends Server
{
	public static final int DEFAULT_PORT = 12345;
	
    // the login manager
    private LoginManager manager;

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

		LoginServer s = new LoginServer(port);
		s.run();
    }

    /**
     * Creates a socket server listening on the specified port
     * @param port the port to listen to
     */
    public LoginServer(int port)
    {
    	super(port);
		manager = new LoginManager();
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
    	    // the input stream for reading from the network
    	    Scanner in = null; 

    	    try
    		{
    			// get the input stream
    			in = new Scanner(socket.getInputStream());

    			// read the genspaceLogin
    			String genspaceLogin = in.nextLine();
    			//System.out.println("GenspaceLogin: " + genspaceLogin);
    			
    			// read the IM Login
    			String IMHandle = in.nextLine();
    			//System.out.println("IM Login: " + IMHandle);
    			
    			// read the IM Service
    			String IMService = in.nextLine();
    			//System.out.println("IM Service: " + IMService);
    			
    			if (IMHandle.equals("null")) // see if this is just a plain old login
    			{
    				manager.addLoginEvent(genspaceLogin);
    				//System.out.println(genspaceLogin + " is logging in");
    			}
    			// now update the database
    			else if (!manager.addToLoginTable(genspaceLogin, IMHandle, IMService))
    				System.out.println("An error occurred while adding the user to the table");

    		}
    		catch (Exception e)
    		{
    			e.printStackTrace();
                if (Logger.isLogError()) Logger.logError(e);
    		}
    	    
    	}
    	
    }

    
    
}
