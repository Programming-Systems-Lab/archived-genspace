package genspace.networks;

import genspace.common.Logger;
import genspace.db.DatabaseManager;

import java.sql.PreparedStatement;
import java.util.HashSet;
import java.util.Set;

import org.geworkbench.components.genspace.bean.networks.Friend;
import org.geworkbench.components.genspace.bean.networks.Network;
import org.geworkbench.components.genspace.bean.networks.NetworkMessage;
import org.geworkbench.components.genspace.bean.networks.Profile;

public class NetworksHandler {
	public static Set<DatabaseManager> listeners = new HashSet<DatabaseManager>();
	
	public static void register(DatabaseManager r)
	{
		listeners.add(r);
	}
	public static void notifyAll(String user, Set<String> affected, boolean isNowVisible)
	{
		System.out.println("Changing visibility for user " + user + (isNowVisible? " to visible" : " to invisible"));
		System.out.println("Affected users: ");
		for(String t: affected)
		{
			System.out.println(t);
		}
		for(DatabaseManager m : listeners)
		{
			m.notifyVisbilityChange(user, affected,isNowVisible);
		}
	}
	public Object processMessage(Object m)
	{
		return MessageProcessorFactory.getProcessor(m.getClass()).processMessage((NetworkMessage) m);
	}

}
