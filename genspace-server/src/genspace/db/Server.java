package genspace.db;

/**
 * This is the base class for our socket server classes.
 */

import genspace.common.Logger;

import java.net.*;

import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;

import com.sun.net.ssl.internal.ssl.SSLServerSocketFactoryImpl;

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
			SSLServerSocketFactoryImpl socketFactory = new SSLServerSocketFactoryImpl();
			
		    server = socketFactory.createServerSocket(port);
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
