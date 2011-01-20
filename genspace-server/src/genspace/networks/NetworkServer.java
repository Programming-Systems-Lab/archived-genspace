package genspace.networks;

import genspace.common.Logger;
import genspace.db.Server;
import java.net.Socket;
import java.util.Scanner;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

public class NetworkServer extends Server {

	public static final int DEFAULT_PORT = 8182;
	
	
    /* This is the main method for starting the Server */
    public static void main(String[] args)
    {
    	int port = 12348;
		if (args.length >= 1)
		{
			port = Integer.parseInt(args[0]);
		}
		else
		{
			System.out.println("Port not specified, using " + port + " as default");
		}

		NetworkServer s = new NetworkServer(port);
		s.run();
    }

    /**
     * Creates a socket server listening on the specified port
     * @param port the port to listen to
     */
    public NetworkServer(int port)
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
    			System.out.println("Connection established");

    			// spin off a new thread
    			SocialNetworkServerThread t = new SocialNetworkServerThread(socket);
    			t.start();
    			
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
    class SocialNetworkServerThread extends Thread
    {
    	Socket socket = null;
    	NetworksHandler n = new NetworksHandler();
    	SocialNetworkServerThread(Socket s)
    	{
    		socket = s;
    	}

    	public void run()
    	{
    	    // the input stream for reading from the network
    	    ObjectInputStream in = null; 
    	    ObjectOutputStream pw = null;
    	    
    	    try
    		{
    			// get the input stream
    			in = new ObjectInputStream(socket.getInputStream());
    			Object response = n.processMessage(in.readObject());
    			pw = new ObjectOutputStream(socket.getOutputStream());
    			pw.writeObject(response);
    			
    			pw.flush();
    			pw.close();
    		}
    		catch (Exception e)
    		{
    			e.printStackTrace();
                if (Logger.isLogError()) Logger.logError(e);
    		}
    	}
    }
}
