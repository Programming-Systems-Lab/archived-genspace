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
		for(UserNetwork un : networkFilter.getMembers())
		{
			if(un.isVisible() && un.isVerified())
				ret.add(un.getUser());
		}
		return ret;
	}

	@Override
	public void joinNetwork(Network n) {
		User me = getUser();
		if(me.isInNetwork(n) != null)
		{
			UserNetwork un = new UserNetwork();
			un.setUser(me);
			un.setNetwork(n);
			un.setVisible(true);
			un.setVerified(n.getOwner().equals(me));
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
			getEntityManager().remove(selected);
		}
	}

	@Override
	public List<UserNetwork> getMyNetworks() {
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
		Query q = getEntityManager().createQuery("select object(c) from UserNetwork as c " +
				"inner join Network as n on n.id_1=c.network " +
				"where n.owner=:user and un.verified= 0");
		q.setParameter("user", me);
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

}
