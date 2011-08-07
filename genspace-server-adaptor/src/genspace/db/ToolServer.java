package genspace.db;

import genspace.GenSpaceServerFactory;
import genspace.common.Logger;
import genspace.common.RatingBean;

import java.net.Socket;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.io.ObjectInputStream;

import org.geworkbench.components.genspace.server.stubs.Tool;
import org.geworkbench.components.genspace.server.stubs.User;


public class ToolServer extends Server {

	public static final int DEFAULT_PORT = 22224;
	
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

		ToolServer s = new ToolServer(port);
		s.run();
    }

    /**
     * Creates a socket server listening on the specified port
     * @param port the port to listen to
     */
    public ToolServer(int port)
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

    			// spin off a new thread
    			ToolServerHandler h = new ToolServerHandler(socket);
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
    class ToolServerHandler extends ServerHandler
    {

    	private org.apache.log4j.Logger log;
    	HashMap<String, Tool> toolsMap = new HashMap<String,Tool>();
    	public ToolServerHandler(Socket s) {
			super(s);
			log = util.MyLogger.getInstance(this.getClass().getName());
			for(Tool t : GenSpaceServerFactory.getUsageOps().getAllTools())
			{
				toolsMap.put(t.getName(), t);
			}
		}

		public void run()
    	{
    	    // the input stream for reading from the network
    	    Scanner in = null; 
    	    
    	    try
    		{
    			// get the input stream
    			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
    			
    			String command = (String)ois.readObject();
    			ArrayList args = (ArrayList)ois.readObject();
//    			System.out.println("Command: " + command);
    			log.info("Command: " + command);
    			
    			if (command.equals("getToolId")) {
    				String toolName = (String)args.get(0);
//    				System.out.println(toolName);
    				log.info(toolName);
    				
    				int id = getToolId(toolName);
    				
        			respond(new Integer(id));
    			} 
    			else if (command.equals("getExpertUser")) {
    				String toolName = (String)args.get(0);
    				String username = (String)args.get(1);
    				log.info(toolName+":"+username);
    				User ret = GenSpaceServerFactory.getUsageOps().getExpertUserFor(getToolId(toolName));
    				String result;
    				if (ret == null)
    					result = "none";
    				else
    					result = ret.getUsername();
    				respond(result);
    			}    
    			else if (command.equals("getToolRating")) {
    				Integer toolId = (Integer)args.get(0);
    				String username = (String)args.get(1);
//    				System.out.println(username + " is requesting rating for " + toolId + ".");	
    				log.info(username + " is requesting rating for " + toolId + ".");
    				
    				RatingBean result = new RatingBean(toolId,0 , 0,0);
//    				System.out.println("Rating for " + toolId + " is: " + result);
    				log.info("Rating for " + toolId + " is: " + result);
    				
    				
    				respond(result);
    			}   
    			else if (command.equals("writeToolRating")) {
    				Integer toolId = (Integer)args.get(0);
    				String username = (String)args.get(1);
    				int rating = (Integer)args.get(2);
    				RatingBean result = new RatingBean(toolId,0 , 0,0);
    				log.info("Rating for " + toolId + " is now:" + result);
    				
    				respond(result);
    			}   
    		}
    		catch (Exception e)
    		{
    			e.printStackTrace();
    			respond(null);
                if (Logger.isLogError()) Logger.logError(e);
    		}
    	}
		private int getToolId(String toolName){
	    	return toolsMap.get(toolName).getId();
	    }
    }
    
    
}
