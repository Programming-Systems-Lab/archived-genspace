package org.geworkbench.components.genspace.server;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;

import org.geworkbench.components.genspace.entity.Network;
import org.geworkbench.components.genspace.entity.User;
import org.geworkbench.components.genspace.entity.UserNetwork;

@Remote
public interface NetworkFacadeRemote {
	public List<User> getProfilesByNetwork(Network networkFilter);
	public void joinNetwork(Network n);
	public void createNetwork(String text);
	public void leaveNetwork(UserNetwork selected);
	public List<UserNetwork> getMyNetworks();
	public List<Network> getAllNetworks();
	public void joinNetwork(String text);
	
	
	public List<UserNetwork> getNetworkRequests(Network nt);
	public void acceptNetworkRequest(UserNetwork selectedValue);
	public void rejectNetworkRequest(UserNetwork selectedValue);
	public void updateNetworkVisibility(UserNetwork userNetwork,
			Boolean boolean1);
	public int getNumberOfNetworkRequests();
}
