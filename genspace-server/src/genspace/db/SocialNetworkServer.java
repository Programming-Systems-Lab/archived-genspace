package genspace.db;

import genspace.common.Logger;
import genspace.jclaim.BotEventHandler;
import java.net.Socket;
import java.util.Scanner;
import java.io.PrintWriter;

public class SocialNetworkServer extends Server {

	public static final int DEFAULT_PORT = 8181;
	
	private BotEventHandler handler = new BotEventHandler();
	
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

		SocialNetworkServer s = new SocialNetworkServer(port);
		s.run();
    }

    /**
     * Creates a socket server listening on the specified port
     * @param port the port to listen to
     */
    public SocialNetworkServer(int port)
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
    	
    	SocialNetworkServerThread(Socket s)
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

    			// get the command
    			String line = in.nextLine().trim();
    			System.out.println("Command: " + line);
    			
    			
    			String reply = handler.getReply(login, line);
    			System.out.println("Reply: " + reply);
    			
    			pw = new PrintWriter(socket.getOutputStream());
        			
    			pw.println(reply);
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
