package genspace.jclaim;

import genspace.common.Logger;
import genspace.db.UserManager;
import genspace.db.SuggestionManager;
import genspace.db.LoginManager;
import genspace.db.PopularToolManager;

import java.util.Date;
import java.util.Hashtable;
import java.util.HashMap;
import java.util.ArrayList;

import com.itbs.aimcer.bean.Contact;
import com.itbs.aimcer.bean.Message;
import com.itbs.aimcer.bean.MessageImpl;
import com.itbs.aimcer.bean.Nameable;
import com.itbs.aimcer.bean.Status;
import com.itbs.aimcer.commune.Connection;
import com.itbs.aimcer.commune.ConnectionEventListener;
import com.itbs.aimcer.commune.FileTransferSupport;
import com.itbs.aimcer.commune.IconSupport;
import com.itbs.aimcer.commune.MessageSupport;
import com.itbs.util.GeneralUtils;

public class BotEventHandler implements ConnectionEventListener {
	
	private SuggestionManager smanager = new SuggestionManager();
	private LoginManager lmanager = new LoginManager();
	private UserManager umanager = new UserManager();
	private PopularToolManager ptmanager = new PopularToolManager();
	
	/* This maps a genSpace login to a StatefulObject that is used for anything that requires maintaining state  */
	private Hashtable<String, StatefulObject> userStates = new Hashtable<String, StatefulObject>();
	
	/* This is a cache of the mapping between IM handles and genSpace logins */
	private Hashtable<String, String> userLogins = new Hashtable<String, String>();

    public boolean messageReceived(MessageSupport connection, Message message) {
        if (message.isOutgoing()) // Not doing anything with outgoing messages
            return true;
        
        try {
            String line = GeneralUtils.stripHTML(message.getText()).trim();
            System.out.println(message.getContact().getName()+ " says: " + message.getPlainText());
            if (Logger.isLogInfo()) Logger.logInfo(message.getContact().getName()+ " says: " + message.getPlainText());
            
            
            String login;
            // let's see if there's a corresponding genSpace login
            String loginKey = message.getContact().getName() + "@" + connection.getServiceName();
            if (userLogins.containsKey(loginKey))
            {
            	//System.out.println("Found " + loginKey + " in the cache");
            	login = userLogins.get(loginKey);
            }
            else
            {
            	//System.out.println("Looking up " + loginKey + " in the database");
            	login = lmanager.lookupLogin(message.getContact().getName(), connection.getServiceName());
            	// if it's found, cache this for further use
            	if (login != null) userLogins.put(loginKey, login);
                // if we can't find a genspace login for this person, there's nothing we can do
            	else login = message.getContact().getName();
            }

            // this is the reply we intend to send back
            String reply = "";
            
            // see if this user is currently doing something with a StatefulObject
            if (userStates.containsKey(login)) 
            {
            	// if so, let that object deal with handling the input
            	StatefulObject obj = userStates.get(login);
            	if (obj.isActive()) 
            	{
            		// only send back the object's reply if it's active
            		reply = obj.handleInput(line);
            		connection.sendMessage(new MessageImpl(message.getContact(), true, reply));
            		return true;
            	}
            	else
            	{
            		// if it's inactive, then remove it from the dictionary and handle this as normal
            		userStates.remove(login);
            	}
            }

            // now get the reply
            reply = getReply(login, line, connection);
            
            // now send the reply
            connection.sendMessage(new MessageImpl(message.getContact(), true, reply));
            if (Logger.isLogInfo()) Logger.logInfo(message.getContact().getName()+ " reply: " + reply);
                        
        }
        catch (Exception e) 
        {
            System.out.println("Failed while processing a message.");
            e.printStackTrace();
            if (Logger.isLogError()) Logger.logError(e);
        }
        return true;
    } 

    
    
    
    
    private String getReply(String login, String line, MessageSupport connection)
    {
    	String reply = "";
    	
        if (line.startsWith("status")) 
        {
        	reply = "up! " + new Date();
        } 
        else if (line.equals("help"))
        {
        	reply = "Here are the commands I currently understand:\n";
        	reply += "status: get the current date/time\n";
        	//reply += "shutdown: shut down the server\n";
        	reply += "join [network]: join a network\n";
        	reply += "create [network]: create a network\n";
        	reply += "my networks: list all networks you are a member of\n";
        	reply += "list [network]: list everyone in the network\n";
        	reply += "list networks: show all networks that exist\n";
        	// reply += "suggest network: recommend a network\n";
        	// reply += "suggest friend: recommend a friend\n";
        	reply += "suggest peer: recommend someone who uses the same analysis tools\n";
        	reply += "suggest analysis: recommend an analysis tool\n";
        	reply += "suggest expert [tool]: recommend an expert on the given analysis tool\n";
        	reply += "power user: suggest a geWorkbench expert for all tools\n";
        	reply += "opt out: exclude yourself from searches for peers and experts\n";
        	reply += "opt in: include yourself in searches for peers and experts\n";
        	reply += "who: list everyone currently logged into genSpace\n";
        	reply += "lookup [username]: show contact information for that user\n";
        	reply += "include [tool]: include an analysis tool when making recommendations\n";
        	reply += "exclude [tool]: exclude an analysis tool when making recommendations\n";
        	reply += "my tools: list all the analysis tools you've used (or have included)\n";
        	reply += "popular tool: show the most popular tool\n";
        	reply += "add friend [username]: add the user to your list of friends\n";
        	reply += "my friends: list all of your friends\n";
        }
        /*
        else if (line.equals("shutdown")) 
        {
                connection.sendMessage(new MessageImpl(message.getContact(), true, "shutting down."));
                Thread.sleep(1000); // Let it finalize comminications.
                connection.disconnect(true);
                System.exit(1);
                
        }
        */
        else if (line.startsWith("join"))
        {
        	String[] tok = line.split(" ");
        	if (tok.length < 2) reply = "Please specify a network to join";
        	else
        	{
        		String network = line.split(" ")[1];
       		
        		if (smanager.addUserToNetwork(login, network))
        		{
            		reply = "You are now a member of " + network;
        		}
        		else
        		{
        			reply = "You are already a member of " + network;
        		}
        	}
        }
        else if (line.startsWith("create"))
        {
        	String[] tok = line.split(" ");
        	if (tok.length < 2) reply = "Please specify a network to create";
        	else
        	{
        		String network = line.substring(7, line.length()).trim();
       		
        		if (smanager.addNetwork(network))
        		{
        			reply = "The network " + network + " has been created";
        		}
        		else
        		{
        			reply = "The network " + network + " already exists";
        		}
        	}
        }            
        else if (line.equals("list networks"))
        {
        	String[] networks = smanager.getAllNetworks();
        	if (networks == null || networks.length == 0)
        	{
        		reply = "There are currently no networks";
        	}
        	else if (networks.length == 1)
        	{
        		reply = "Currently the only network is " + networks[0];
        	}
        	else
        	{
        		reply = "There are " + networks.length + " networks:\n";
        		for (String network : networks)
        		{
        			reply += network + "\n";
        		}
        	}
        }
        else if (line.equals("my networks"))
        {
        	String[] networks = smanager.getNetworksByUser(login);
        	if (networks == null || networks.length == 0)
        	{
        		reply = "You are not currently in any network";
        	}
        	else if (networks.length == 1)
        	{
        			reply = "Currently your only network is " + networks[0];
        	}
        	else
        	{
        		reply = "You are in " + networks.length + " networks:\n";
        		for (String network : networks)
        		{
        			reply += network + "\n";
        		}
        	}
        }
        /*
        else if (line.equals("suggest network"))
        {
        	String network = manager.suggestNetwork(login);
        	if (network == null) reply = "I'm sorry, I don't have any suggestions for you";
        	else reply = "I would suggest the " + network + " network";
        }
        else if (line.equals("suggest friend"))
        {
        	String user = manager.suggestFriend(login);
        	if (user == null) reply = "I'm sorry, I don't have any suggestions for you";
        	else reply = "I would suggest " + user;
        }
        */
        else if (line.contains("suggest peer"))
        {
        	boolean myNetworks = false; // whether or not to limit the search to the user's networks
        	if (line.contains("my networks")) myNetworks = true;
        	String user = smanager.suggestAnalysisToolPeer(login, myNetworks);
        	if (user == null) reply = "I'm sorry, I don't have any suggestions for you";
        	else
        	{
        		String[] myTools = smanager.getModifiedAnalysisByUser(login);
        		HashMap<String, Double> userToolMap = smanager.getAnalysisByUser(user); 
        		String[] theirTools = userToolMap.keySet().toArray(new String[userToolMap.size()]);
        		String[] tools = findIntersection(myTools, theirTools);
        		reply = user + " is someone who uses the same analysis tools as you (";
        		if (tools.length == 1)
        			reply += tools[0] + ")";
        		else 
        		{
        			// TODO: there's an extra comma if there are only two tools in common
        			for (int i = 0; i < tools.length - 1; i++)
        			{
        				reply += tools[i] + ", ";
        			}
        			reply += "and " + tools[tools.length-1] + ")";
        		}
        	}
        }
        else if (line.contains("suggest analysis") || line.contains("suggest tool"))
        {
        	boolean myNetworks = false; // whether or not to limit the scope to the user's networks
        	if (line.contains("my networks")) myNetworks = true; 
        	String[] suggestedTools = smanager.suggestAnalysisTool(login, myNetworks);
        	if (suggestedTools == null || suggestedTools.length == 0) reply = "I'm sorry, I don't have any suggestions for you";
        	else
        	{
        		String[] tools = smanager.getModifiedAnalysisByUser(login);
        		reply = "People who use the same analysis tool";
        		if (tools.length > 1) reply += "s";
        		reply += " as you (";
        		if (tools.length == 1)
        			reply += tools[0] + ")";
        		else 
        		{
        			// TODO: there's an extra comma if there are only two tools
        			for (int i = 0; i < tools.length - 1; i++)
        			{
        				reply += tools[i] + ", ";
        			}
        			reply += "and " + tools[tools.length-1] + ")";
        		}
        		reply += " also use " + suggestedTools[0] + ".";
        		// if there's more, show a few
        		if (suggestedTools.length > 1)
        		{
        			reply += " You might also want to consider " + suggestedTools[1];
        			if (suggestedTools.length > 2) reply += " and " + suggestedTools[2];
        			reply += ".";
        		}
        	}
        }
        else if (line.startsWith("suggest expert"))
        {
        	String[] tok = line.split(" ");
        	if (tok.length < 3) reply = "Please specify a tool to find an expert for";
        	else
        	{
            	// assumes that the line starts with "suggest expert" of course
        		String analysis = line.substring(15, line.length()).trim();

        		boolean myNetworks = false; // whether or not to limit the scope to the user's networks
            	if (line.contains("my networks"))
            	{	
            		analysis = analysis.replace("my networks", "").trim();
            		myNetworks = true; 
            	}

        		String expert = smanager.suggestExpert(analysis, login, myNetworks);
            	if (expert == null)
            	{
            		reply = "There are no experts for " + analysis;
            		if (myNetworks) reply += " in your networks";
            	}
            	else
            	{
            		// we know the expert, but see if he/she is logged in
            		String[] users = umanager.getActiveUsers(login);
            		boolean online = false;
            		// TODO: make this more efficient!
            		for (int i = 0; i < users.length && !online; i++)
            			if (users[i].equals(expert)) online = true;
            		if (online) reply = "The expert for " + analysis + " is " + expert + " (currently in genSpace)";
            		else
            		{
            			// if we get here, the expert is NOT logged in, so we need to find someone who is
            			String loggedInExpert = smanager.suggestLoggedInExpert(analysis, login);
            			if (loggedInExpert == null) reply = "The expert for " + analysis + " is " + expert + ", but that user is not currently in genSpace. No one else currently in genSpace is an expert.";
            			else reply = "The expert for " + analysis + " is " + expert + ", but that user is not currently in genSpace. " + loggedInExpert + " is another frequent user currently in genSpace.";
            		}
            	}

        	}
        }
        else if (line.contains("power user"))
        {
        	boolean myNetworks = false; // whether or not to limit the scope to the user's networks
        	if (line.contains("my networks")) myNetworks = true;
        	
        	String powerUser = smanager.suggestPowerUser(login, myNetworks);
        	
        	if (powerUser == null)
        	{
        		reply = "There are no power users";
        	}
        	else
        	{
        		reply = powerUser + " is a power user";
        	}
    		if (myNetworks) reply += " in your networks";
        }
        else if (line.equals("opt out"))
        {
        	if (smanager.optOut(login)) reply = "You will no longer be considered for suggestions of peers and experts";
        	else reply = "I'm sorry, it looks like a database error occurred. I'll make sure someone looks into it";
        }
        else if (line.equals("opt in"))
        {
        	if (smanager.optIn(login)) reply = "You will now be considered for suggestions of peers and experts";
        	else reply = "I'm sorry, it looks like a database error occurred. I'll make sure someone looks into it";
        }
        else if (line.equals("my tools"))
        {
        	String[] myTools = smanager.getModifiedAnalysisByUser(login);
        	if (myTools == null || myTools.length == 0)
        		reply = "You have not used or included any analysis tools";
        	else if (myTools.length == 1)
        		reply = "The only tool you have used or included is " + myTools[0];
        	else
        	{
        		reply = "You have used or included " + myTools.length + " tools:\n";
        		for (String tool : myTools) reply += tool + "\n";
        	}
        }
        else if (line.contains("popular tool"))
        {
        	if (line.contains("my networks"))
        	{
        		String popularTool = ptmanager.getMostPopularToolInUsersNetworks(login);
        		if (popularTool == null) reply = "There are no popular tools at the moment in your networks";
        		else reply = "The most popular tool in your networks is " + popularTool;
        	}
        	else
        	{
        		String popularTool = ptmanager.getMostPopularTool();
        		if (popularTool == null) reply = "There are no popular tools at the moment";
        		else reply = "The most popular tool in genSpace is " + popularTool;
        	}
        }
        else if (line.equals("who"))
        {
        	String[] users = umanager.getActiveUsers(login);
           	if (users == null || users.length == 0)
           	{
           		reply = "The only person currently in genSpace is you";
           	}
           	else if (users.length == 1)
           	{
           		String username = users[0];
           		reply = "The only other person who recently logged into genSpace is " + username;
           	}
           	else if (users.length > 100) // TODO: how many is too many?
           	{
           		reply = "There are currently " + users.length + " people who recently logged into in genSpace";
           	}
           	else
           	{
           		reply = "There are " + users.length + " other people who recently logged into genSpace:\n";
           		for (String user : users)
           		{
           			reply += user + "\n";
           		}
        	}
        }
        else if (line.startsWith("lookup"))
        {
        	String[] tok = line.split(" ");
        	if (tok.length < 2) reply = "Please specify a user to get info about";
        	else if (connection == null)
        	{
        		reply = "I'm sorry, I don't know what chat network you are on";
        	}
        	else
        	{
        		String username = line.substring(7, line.length()).trim();
        		String handle = smanager.getUserIMHandle(username, connection.getServiceName());
        		if (handle == null)
        		{
        			reply = "I'm sorry, I don't have any contact information for " + username + " on this chat network";
        		}
        		else
        		{
        			reply = "The IM handle for genSpace user " + username + " on this chat network is " + handle;
        		}
        	}
        }
        else if (line.startsWith("include"))
        {
        	String[] tok = line.split(" ");
        	if (tok.length < 2) reply = "Please specify an analysis tool to include";
        	else
        	{
        		String tool = line.substring(8, line.length()).trim();
        		smanager.includeAnalysis(login, tool);
        		reply = "Okay, from now on I will consider that you have used " + tool + " in any suggestions I make";
        	}
        }
        else if (line.startsWith("exclude"))
        {
        	String[] tok = line.split(" ");
        	if (tok.length < 2) reply = "Please specify an analysis tool to exclude";
        	else
        	{
        		String tool = line.substring(8, line.length()).trim();
        		smanager.excludeAnalysis(login, tool);
        		reply = "Okay, from now on I will not consider that you used " + tool + " in any suggestions I make";
        	}
        }
        else if (line.startsWith("list"))
        {
        	String[] tok = line.split(" ");
        	if (tok.length < 2) reply = "Please specify a network to list users for";
        	else
        	{
        		String network = line.substring(5, line.length()).trim();
       		
        		String[] users = smanager.getUsersByNetwork(network);
            	if (users == null || users.length == 0)
            	{
            		reply = "No one is in the network " + network + " or it does not exist";
            	}
            	else if (users.length == 1)
            	{
            		String username = users[0];
        			if (username.equals(login)) username = "you";
            		reply = "The only person in " + network + " is " + username;
            	}
            	else
            	{
            		reply = "There are " + users.length + " people in " + network + ":\n";
            		for (String username : users)
            		{
            			if (username.equals(login)) username = "you";
            			reply += username + "\n";
            		}
            	}

        	}
        }
        else if (line.startsWith("leave"))
        {
        	String[] tok = line.split(" ");
        	if (tok.length < 2) reply = "Please specify a network to leave";
        	else
        	{
        		//String group = line.substring(6, line.length()).trim();
        		reply = "I'm sorry, they haven't taught me how to do that yet";
        		// NOT IMPLEMENTED YET!
        	}
        }
        else if (line.equals("my friends"))
        {
        	String[] friends = umanager.getFriends(login);
        	if (friends == null || friends.length == 0)
        		reply = "You have no friends in genSpace";
        	else if (friends.length == 1)
        		reply = "Your only friend in genSpace is " + friends[0];
        	else if (friends.length > 100) // how much is too much?
        		reply = "You have " + friends.length + " friends in genSpace";
        	else
        	{
        		reply = "You have " + friends.length + " friends in genSpace:\n";
        		for (String friend : friends)
        			reply += friend + "\n";
        	}
        }
        else if (line.startsWith("add friend"))
        {
        	String[] tok = line.split(" ");
        	if (tok.length < 3) reply = "Please specify the name of a genSpace user to add as your friend";
        	else
        	{
        		String friend = line.substring(11, line.length()).trim();
        		umanager.addFriend(login, friend);
        		reply = "You and " + friend + " are now friends";
        	}
        }
        /* EASTER EGG TIME!!! */
        else if (line.contains("fuck"))
        {
        	reply = "You kiss your mother with that mouth?";
        }
        else if (line.equals("hello"))
        {
        	reply = "Hello, " + login + ", how are you?";
        }
        /* Experimenting with stateful objects */
        else if (line.equals("add"))
        {
        	Calculator c = new Calculator("add");
        	userStates.put(login, c);
        	reply = "Please enter the first number to add.";
        }
        else if (line.equals("multiply"))
        {
        	Calculator c = new Calculator("multiply");
        	userStates.put(login, c);
        	reply = "Please enter the first number to multiply.";
        }
        else 
        {
        	reply = "I'm sorry, I don't understand " + line + "\nType help if you need help";
        }
        
        return reply;
    	
    }
    
    
    public String getReply(String login, String line)
    {
    	return getReply(login, line, null);
    }
    
    
    /*
     * TEST METHOD
     */
    public static void main(String[] args)
    {
    	BotEventHandler h = new BotEventHandler();
    	System.out.println(h.getReply("floratos", "suggest tool"));
    }
    
    
    

    /**
     * This method takes two String arrays and finds the elements that exist in both.
     * TODO: make this more efficient
     */
    private String[] findIntersection(String[] A, String[] B)
    {
    	// store the intersection in an arraylist
    	ArrayList<String> inter = new ArrayList<String>();
    	
    	// now loop through each and find the intersection
    	for (String a : A)
    	{
    		for (String b : B)
    			if (a.equals(b)) inter.add(a);
    	}
    	
    	return inter.toArray(new String[inter.size()]);
    }

    
    
	public void connectionEstablished(Connection arg0) {
		// TODO Auto-generated method stub
		
	}

	public void connectionFailed(Connection arg0, String arg1) {
		// TODO Auto-generated method stub
		
	}

	public void connectionInitiated(Connection arg0) {
		// TODO Auto-generated method stub
		
	}

	public void connectionLost(Connection arg0) {
		// TODO Auto-generated method stub
		
	}

	public boolean contactRequestReceived(String arg0, MessageSupport arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean emailReceived(MessageSupport arg0, Message arg1) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	public void errorOccured(String arg0, Exception arg1) {
		// TODO Auto-generated method stub
		
	}

	public void fileReceiveRequested(FileTransferSupport arg0, Contact arg1, String arg2, String arg3, Object arg4) {
		// TODO Auto-generated method stub
		
	}

	public void pictureReceived(IconSupport arg0, Contact arg1) {
		// TODO Auto-generated method stub
		
	}

	public void statusChanged(Connection arg0) {
		// TODO Auto-generated method stub
		
	}

	public void statusChanged(Connection arg0, Contact arg1, boolean arg2, boolean arg3, int arg4) {
		// TODO Auto-generated method stub
		
	}

	public void typingNotificationReceived(MessageSupport connection, Nameable arg1) {

		
	}





	@Override
	public void statusChanged(Connection connection, Contact contact,
			Status oldStatus) {
		// TODO Auto-generated method stub
		
	}
}