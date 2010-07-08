package org.geworkbench.components.genspace.bean.networks;

import java.util.List;

import org.geworkbench.components.genspace.bean.networks.Network.NetScope;
import org.geworkbench.components.genspace.bean.networks.NetworkMessage.Request;

public class NetworkFacade  extends Facade {
	
	public List<NetworkMessage> listAllNetworks()
	{
		Network n = new Network();
		n.reqType=Request.LIST;
		n.networkScope = NetScope.ALL;
		return sendNetworkMessage(n).children;
	}
	
	public List<NetworkMessage> listMyNetworks()
	{
		Network n = new Network();
		n.reqType=Request.LIST;
		n.networkScope = NetScope.ME;
		return sendNetworkMessage(n).children;
	}
	
	public List<NetworkMessage> listUsersNetworks(String otherUser)
	{
		Network n = new Network();
		n.reqType=Request.LIST;
		n.subject = otherUser;
		n.networkScope = NetScope.USER;
		return sendNetworkMessage(n).children;
	}
	
	public Network createNetwork(String name)
	{
		Network n = new Network();
		n.reqType=Request.CREATE;
		n.subject = name;
		return (Network) sendNetworkMessage(n);
	}
	
	public Network joinNetwork(String name)
	{
		Network n = new Network();
		n.reqType=Request.JOIN;
		n.subject = name;
		return (Network) sendNetworkMessage(n);
	}
	
	public Network acceptNetworkRequest(String net, String user)
	{
		Network n = new Network();
		n.reqType=Request.JOIN;
		n.subject = net;
		n.details = user;
		return (Network) sendNetworkMessage(n);
	}
	
	public List<NetworkMessage> leaveNetwork(String name)
	{
		Network n = new Network();
		n.reqType=Request.LEAVE;
		n.subject = name;
		return sendNetworkMessage(n).children;
	}
	public static void main(String[] args) {
		NetworkFacade f = new NetworkFacade();
		System.out.println(f.createNetwork("test network"));
	}
}
