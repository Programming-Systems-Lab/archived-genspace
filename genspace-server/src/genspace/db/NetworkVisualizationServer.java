package genspace.db;

import genspace.common.Logger;
import java.net.Socket;
import java.util.Scanner;
import java.io.PrintWriter;

public class NetworkVisualizationServer extends Server {

	public static final int DEFAULT_PORT = 8282;
		
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

		NetworkVisualizationServer s = new NetworkVisualizationServer(port);
		s.run();
    }

    /**
     * Creates a socket server listening on the specified port
     * @param port the port to listen to
     */
    public NetworkVisualizationServer(int port)
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
    			NetworkVisualizationServerThread t = new NetworkVisualizationServerThread(socket);
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
    class NetworkVisualizationServerThread extends Thread
    {
    	Socket socket = null;
    	
    	NetworkVisualizationServerThread(Socket s)
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

    			// read the genspaceLogin
    			String login = in.next();
    			System.out.println("name: " + login);

    			pw = new PrintWriter(socket.getOutputStream());
    			
    			NetworkManager nm = new NetworkManager();
    			
    			// get this user's networks
    			String[] networks = nm.getNetworksByUser(login);
    			for (String n : networks)
    			{
    				System.out.println("NETWORK: " + n);
    				pw.println("NETWORK: " + n);
    				
    				// now get all the users in the network
    				String[] users = nm.getUsersByNetwork(n);
    				for (String u : users)
    				{
    					System.out.println("USER: " + u);
    					pw.println("USER: " + u);
    				}
    			}
    			pw.println("END");
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
