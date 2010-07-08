package genspace.db;
import java.util.*;

/**
 *  This class is used to keep track of all the networks and the user mappings.
 *  It could be replaced by a backend database, or act as a cache. I'm not sure yet.
 *  
 * @author cmurphy
 *
 */

public class CachingNetworkManager 
{
	// use a Hashtable because we need synchronization
	private Hashtable users = new Hashtable(); // key = user
	private Hashtable networks = new Hashtable(); // key = network
	
	public CachingNetworkManager()
	{
		networks.put("Columbia", new Vector<String>());
		networks.put("Dartmouth", new Vector<String>());
	}
	
	/**
	 *  This method adds a new network to the system.
	 *  @return true if the network was added successfully, false if there was a problem (like it already exists)
	 */
	public boolean addNetwork(String name)
	{
		if (networks.containsKey(name)) return false;
		else
		{
			networks.put(name, new Vector<String>());
			return true;
		}
	}

	
	/**
	 * This method is used to get all of the Networks in the system. 
	 * 
	 * @return an array of all Networks in the database
	 */
	public String[] getAllNetworks()
	{
		Set<String> keys = networks.keySet();
		return keys.toArray(new String[keys.size()]);
	}


	/**
	 * This method is used to get all of the Networks to which a user belongs.
	 * 
	 * @param the ID of the user
	 * @return an array of all Networks to which the user belongs
	 */
	public String[] getNetworksByUser(String name)
	{
		Object nets = users.get(name);
		if (nets == null)
		{
			return new String[0];
		}
		else
		{
			Vector<String> n = (Vector<String>)nets;
			return n.toArray(new String[n.size()]);
		}
	}
	

	/**
	 *  This method adds a new user to a network.
	 *  @return true if the user was successfully added, false if there was a problem (like the user is already in the network)
	 */
	public boolean addUserToNetwork(String user_name, String network_name)
	{
		// if the user is already a member of some network, just update
		if (users.containsKey(user_name))
		{
			Vector<String> nets = (Vector<String>)users.get(user_name);

			// if the user is already in this network, return false
			if (nets.contains(network_name)) 
			{
				return false;
			}
			else
			{
				nets.add(network_name);
			}
		}
		// if the user is not a member of any network, add to the system
		else
		{
			Vector<String> nets = new Vector<String>();
			nets.add(network_name);
			users.put(user_name, nets);
		}

		// now update the list of users in this network
		if (networks.containsKey(network_name)) 
		{
			Vector<String> u = (Vector<String>)networks.get(network_name);

			if (u.contains(user_name))
			{
				// if we get here, that's a problem. It would mean that the first check
				// passed up above, which means we have a data integrity problem...
				System.out.println("Oh crap: " + network_name + " contains " + user_name + " but not the other way around");
				return false;
			}
			else
			{
				u.add(user_name);
			}
		}
		else
		{
			Vector<String> u = new Vector<String>();
			u.add(user_name);
			networks.put(network_name, u);
		}
		return true;
	}

	
	/**
	 * This method is used to get all of the Users in the system.
	 * 
	 * @return an array of all Users in the database
	 */
	public String[] getAllUsers()
	{
		Set<String> keys = users.keySet();
		return keys.toArray(new String[keys.size()]);
	}

	/**
	 * This method is used to get all of the users who belong to a network. 
	 * 
	 * @param the name of the network
	 * @return an array of all Users who belong to the network
	 */
	public String[] getUsersByNetwork(String name)
	{
		Object u = networks.get(name);
		if (u == null)
		{
			return new String[0];
		}
		else
		{
			Vector<String> list = (Vector<String>)u;
			return list.toArray(new String[list.size()]);
		}
	}
		

}
