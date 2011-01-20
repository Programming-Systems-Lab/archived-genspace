package org.geworkbench.components.genspace.bean.networks;

import java.io.Serializable;
import java.util.List;

public abstract class NetworkMessage implements Serializable {
	public String sender; // Who sends the message
	public String subject; // Who the message is about

	public enum Request {
		FIND, LIST, UPDATE, CREATE, DELETE, JOIN, LEAVE
	}

	public Request reqType;
	public String details;
	public int bus_id;
	public boolean visible;
	public List<NetworkMessage> children;

	public String fancyName = "Generic Network Message";

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[" + fancyName + ": from <" + sender + "> about <" + subject
				+ "> request type:<" + reqType + ">]";
	}
}
