package org.geworkbench.components.genspace.server;

import java.security.AccessController;
import java.security.Principal;
import java.util.List;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.security.auth.Subject;
import javax.security.jacc.PolicyContext;
import javax.security.jacc.PolicyContextException;

import org.geworkbench.components.genspace.entity.Network;
import org.geworkbench.components.genspace.entity.User;
import org.geworkbench.components.genspace.entity.UserNetwork;


/**
 * Session Bean implementation class NetworkFacade
 */
@Stateful
@RolesAllowed("user")
public class NetworkFacade extends AbstractFacade<Network> implements NetworkFacadeRemote {

    
    
    
	public NetworkFacade() {
		super(Network.class);
	}


	@Override
	public List<User> getProfilesByNetwork(Network networkFilter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void joinNetwork(Network n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createNetwork(String text) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void leaveNetwork(UserNetwork selected) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<UserNetwork> getMyNetworks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Network> getAllNetworks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void joinNetwork(String text) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<UserNetwork> getNetworkRequests(Network nt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void acceptNetworkRequest(UserNetwork selectedValue) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rejectNetworkRequest(UserNetwork selectedValue) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateNetworkVisibility(UserNetwork userNetwork,
			Boolean boolean1) {
		// TODO Auto-generated method stub
		
	}

}
