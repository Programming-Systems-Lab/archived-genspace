package genspace.db;

/**
 * This is the base class for our socket server classes.
 */

import genspace.common.Logger;

import java.net.*;

public abstract class Server extends Thread 
{

    // the server
    protected ServerSocket server;


    /**
     * Creates a socket server listening on the specified port
     * @param port the port to listen to
     */
    public Server(int port)
    {
    	init(port);
    	System.out.println("Starting " + this.getClass().getName() + " on port " + port);
    }


    private void init(int port)
    {
		try
		{
		    server = new ServerSocket(port);
		    System.out.println("Server started... waiting for connection");
		}
		catch (Exception e)
		{
		    System.out.println("Cannot start Server!");
		    e.printStackTrace();
            if (Logger.isLogError()) Logger.logError(e);
		}
    }
    
    
}
