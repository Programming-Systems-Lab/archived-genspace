package genspace.db;

import genspace.common.Logger;

import java.net.Socket;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.ObjectInputStream;
import java.io.PrintWriter;

import org.geworkbench.components.genspace.bean.RatingBean;

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
    	
    	public ToolServerHandler(Socket s) {
			super(s);
			log = util.MyLogger.getInstance(this.getClass().getName());

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
        			
    				SuggestionManager sn = new SuggestionManager();
    				String result = sn.suggestExpert(toolName, username, false);
    				if (result == null)
    					result = "none";
        			
    				respond(result);
    			}    
    			else if (command.equals("getToolRating")) {
    				Integer toolId = (Integer)args.get(0);
    				String username = (String)args.get(1);
//    				System.out.println(username + " is requesting rating for " + toolId + ".");	
    				log.info(username + " is requesting rating for " + toolId + ".");
    				
    				RatingManager rm = new RatingManager();
    				RatingBean result = rm.getRating(toolId, "tool_ratings", username);
//    				System.out.println("Rating for " + toolId + " is: " + result);
    				log.info("Rating for " + toolId + " is: " + result);
    				
    				
    				respond(result);
    			}   
    			else if (command.equals("writeToolRating")) {
    				Integer toolId = (Integer)args.get(0);
    				String username = (String)args.get(1);
    				int rating = (Integer)args.get(2);
//    				System.out.println(username + " wants to rate " + toolId + " as a " + rating + ".");	
    				log.info(username + " wants to rate " + toolId + " as a " + rating + ".");
    				
    				RatingManager rm = new RatingManager();
    				RatingBean result = rm.writeRating(toolId, rating, "tool_ratings", username);
//    				System.out.println("Rating for " + toolId + " is now:" + result);
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
    }
    
    private int getToolId(String toolName){
    	int id = 0;
    	try{
			DatabaseConnector dc = new DatabaseConnector();
			PreparedStatement pstmt = dc.getConnection().prepareStatement("select id from tools where tool like ?");
			pstmt.setString(1, toolName);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			id = rs.getInt(1);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return id;
    }
}
