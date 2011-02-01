package org.geworkbench.components.genspace.bean.networks;

import java.util.List;

import org.geworkbench.components.genspace.bean.networks.Network.NetScope;
import org.geworkbench.components.genspace.bean.networks.NetworkMessage.Request;
import org.geworkbench.components.genspace.ui.GenSpaceLogin;

public class NetworkFacade extends Facade {

	public List<NetworkMessage> listUsersByNetwork(String network) {
		Network n = new Network();
		n.reqType = Request.LIST;
		n.networkScope = NetScope.NETWORK;
		n.subject = network;
		return sendNetworkMessage(n).children;
	}

	public List<NetworkMessage> listPendingUsersByNetwork(String network) {
		Network n = new Network();
		n.reqType = Request.LIST;
		n.networkScope = NetScope.PENDING;
		n.subject = network;
		return sendNetworkMessage(n).children;
	}

	public List<NetworkMessage> listAllNetworks() {
		Network n = new Network();
		n.reqType = Request.LIST;
		n.networkScope = NetScope.ALL;
		return sendNetworkMessage(n).children;
	}

	public List<NetworkMessage> listMyNetworks() {
		Network n = new Network();
		n.reqType = Request.LIST;
		n.networkScope = NetScope.ME;
		return sendNetworkMessage(n).children;
	}

	public List<NetworkMessage> listUsersNetworks(String otherUser) {
		Network n = new Network();
		n.reqType = Request.LIST;
		n.subject = otherUser;
		n.networkScope = NetScope.USER;
		return sendNetworkMessage(n).children;
	}

	public Network find(String net) {
		Network n = new Network();
		n.reqType = Request.FIND;
		n.subject = net;
		return (Network) sendNetworkMessage(n);
	}

	public Network createNetwork(String name) {
		Network n = new Network();
		n.reqType = Request.CREATE;
		n.creator = GenSpaceLogin.genspaceLogin;
		n.subject = name;
		return (Network) sendNetworkMessage(n);
	}

	public Network joinNetwork(String name) {
		Network n = new Network();
		n.reqType = Request.JOIN;
		n.subject = name;
		return (Network) sendNetworkMessage(n);
	}

	public Network acceptNetworkRequest(String net, String user) {
		Network n = new Network();
		n.reqType = Request.JOIN;
		n.subject = net;
		n.details = user;
		return (Network) sendNetworkMessage(n);
	}

	public Network rejectNetworkRequest(String net, String user) {
		Network n = new Network();
		n.reqType = Request.LEAVE;
		n.subject = net;
		n.details = user;
		return (Network) sendNetworkMessage(n);
	}

	public List<NetworkMessage> leaveNetwork(String name) {
		Network n = new Network();
		n.reqType = Request.LEAVE;
		n.subject = name;
		return sendNetworkMessage(n).children;
	}
}
