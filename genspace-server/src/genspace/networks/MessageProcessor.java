package genspace.networks;

import genspace.db.DatabaseManager;

import org.geworkbench.components.genspace.bean.networks.NetworkMessage;

public abstract class MessageProcessor extends DatabaseManager {
	public abstract NetworkMessage processMessage(NetworkMessage m);
}
