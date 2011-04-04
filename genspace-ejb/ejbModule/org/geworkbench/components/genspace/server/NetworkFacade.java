package org.geworkbench.components.genspace.server;

import java.security.AccessController;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.persistence.NoResultException;
import javax.persistence.Query;
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
		ArrayList<User> ret = new ArrayList<User>();
		networkFilter = find(networkFilter.getId());
		for(UserNetwork un : networkFilter.getMembers())
		{
			if(un.isVisible() && un.isVerified())
				ret.add(fullySerialize(un.getUser()));
		}
		return ret;
	}

	@Override
	public void joinNetwork(Network n) {
		User me = getUser();
		if(me.isInNetwork(n) == null)
		{
			UserNetwork un = new UserNetwork();
			un.setUser(me);
			un.setNetwork(n);
			un.setVisible(true);
			un.setVerified(n.getOwner().equals(me));
			System.out.println(un.getUser() + " ; " + un.getNetwork());
			getEntityManager().persist(un);
		}
	}

	private Network findByName(String name)
	{
		Query q = getEntityManager().createQuery("select object(c) from Network as c where c.name=:netname");
		q.setParameter("netname", name);
		Network r = null;
		try
		{
		r = (Network) q.getSingleResult();
		}
		catch(NoResultException e)
		{
			
		}
		return r;
	}
	@Override
	public void createNetwork(String name) {
		if(findByName(name) == null)
		{
			Network n = new Network();
			n.setName(name);
			n.setOwner(getUser());
			getEntityManager().persist(n);
			joinNetwork(n);
		}
	}

	@Override
	public void leaveNetwork(UserNetwork selected) {
		User me = getUser();
		if (selected != null && selected.getUser().equals(me))
		{
			getEntityManager().merge(selected);
			getEntityManager().remove(selected);
		}
	}

	@Override
	public List<UserNetwork> getMyNetworks() {
		getUser().getNetworks().size();
		return getUser().getNetworks();
	}

	@Override
	public List<Network> getAllNetworks() {
		return findAll();
	}

	@Override
	public void joinNetwork(String text) {
		Network n = findByName(text);
		if(n != null)
			joinNetwork(n);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserNetwork> getNetworkRequests(Network nt) {
		User me = getUser();
		Query q = getEntityManager().createNativeQuery("select c.* from User_Network as c " +
				"inner join NETWORK as n on n.id=c.network_id " +
				"where n.owner=? and n.id=? and c.verified= 0",UserNetwork.class);
		q.setParameter(1, me.getId());
		q.setParameter(2, nt.getId());
		List<UserNetwork> r = null;
		try
		{
			r = q.getResultList();
		}
		catch(NoResultException e)
		{
			return new ArrayList<UserNetwork>();
		}
		return r;
	}

	@Override
	public void acceptNetworkRequest(UserNetwork un) {
		if(un.getNetwork().getOwner().equals(getUser()))
		{
			un.setVerified(true);
			getEntityManager().merge(un);
		}
	}

	@Override
	public void rejectNetworkRequest(UserNetwork un) {
		if(un.getNetwork().getOwner().equals(getUser()))
		{
			getEntityManager().merge(un);
			getEntityManager().remove(un);
		}
	}

	@Override
	public void updateNetworkVisibility(UserNetwork un,
			Boolean v) {
		if(un.getUser().equals(getUser()))
		{
			un.setVisible(v);
			getEntityManager().merge(un);
		}
	}


	@Override
	public int getNumberOfNetworkRequests() {
		User me = getUser();
		Query q = getEntityManager().createNativeQuery("select c.* from User_Network as c " +
				"inner join NETWORK as n on n.id=c.network_id " +
				"where n.owner=? and c.verified= 0",UserNetwork.class);
		q.setParameter(1, me.getId());
		try
		{
			return q.getResultList().size();
		}
		catch(NoResultException e)
		{
			return 0;
		}

	}

}