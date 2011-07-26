package org.geworkbench.components.genspace.server;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.geworkbench.components.genspace.entity.Network;
import org.geworkbench.components.genspace.entity.User;
import org.geworkbench.components.genspace.entity.UserNetwork;


/**
 * Session Bean implementation class NetworkFacade
 */
@Stateless
@RolesAllowed("user")
@WebService
public class NetworkFacade extends AbstractFacade<Network> implements NetworkFacadeRemote {
 	public NetworkFacade() {
		super(Network.class);
	}

 	@WebMethod
	@Override
	public List<User> getProfilesByNetwork(int networkFilter_id) {
		Network networkFilter = getEntityManager().find(Network.class, networkFilter_id);
		ArrayList<User> ret = new ArrayList<User>();
		networkFilter = find(networkFilter.getId());
		for(UserNetwork un : networkFilter.getMembers())
		{
			if(un.isVisible() && un.isVerified())
			{
				User zz = un.getUser();
				getEntityManager().refresh(zz);
				ret.add(zz.loadVisibility(getUser()));
			}
		}
		return ret;
	}

	@WebMethod(exclude=true)
	@Override
	public void joinNetwork(int n_id) { 
		User me = getUser();
    	logUsage();
		Network n = getEntityManager().find(Network.class, n_id);
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
	@WebMethod
	public void createNetwork(String name) {
		if(findByName(name) == null)
		{
	    	logUsage();

			Network n = new Network();
			n.setName(name);
			n.setOwner(getUser());
			getEntityManager().persist(n);
			n = findByName(n.getName());
			joinNetwork(n.getId());
		}
	}

	@Override
	@WebMethod
	public void leaveNetwork(int usernetwork_id) {
		User me = getUser();
    	logUsage();

		UserNetwork selected = getEntityManager().find(UserNetwork.class, usernetwork_id);
		if (selected != null && selected.getUser().equals(me))
		{
			getEntityManager().merge(selected);
			getEntityManager().remove(selected);
		}
	}
	@WebMethod
	@Override
	public List<UserNetwork> getMyNetworks() {
		getEntityManager().refresh(getUser());
		getUser().getNetworks().size();
    	logUsage();

		return getUser().getNetworks();
	}
	@WebMethod
	@Override
	public List<Network> getAllNetworks() {
		return findAll();
	}
	@WebMethod
	@Override
	public void joinNetwork(String text) {
		Network n = findByName(text);
		if(n != null)
			joinNetwork(n.getId());
	}
	@WebMethod
	@SuppressWarnings("unchecked")
	@Override
	public List<UserNetwork> getNetworkRequests(int network_id) {
		User me = getUser();
		Query q = getEntityManager().createNativeQuery("select c.* from User_Network as c " +
				"inner join NETWORK as n on n.id=c.network_id " +
				"where n.owner=? and n.id=? and c.verified= 0",UserNetwork.class);
		q.setParameter(1, me.getId());
		q.setParameter(2, network_id);
		List<UserNetwork> r = null;
		try
		{
			r = q.getResultList();
			for(UserNetwork un : r)
			{
				un.getUser().loadVisibility(getUser());
			}
		}
		catch(NoResultException e)
		{
			return new ArrayList<UserNetwork>();
		}
		System.out.println("Requests for " + network_id + " : " + r);
		return r;
	}
	@WebMethod
	@Override
	public void acceptNetworkRequest(int un_id) {
		UserNetwork un = getEntityManager().find(UserNetwork.class, un_id);
		if(un.getNetwork().getOwner().equals(getUser()))
		{
	    	logUsage();

			un.setVerified(true);
			getEntityManager().merge(un);
		}
	}
	@WebMethod
	@Override
	public void rejectNetworkRequest(int un_id) {
		UserNetwork un = getEntityManager().find(UserNetwork.class, un_id);
		if(un.getNetwork().getOwner().equals(getUser()))
		{
	    	logUsage();

			getEntityManager().merge(un);
			getEntityManager().remove(un);
		}
	}
	@WebMethod
	@Override
	public void updateNetworkVisibility(int un_id,
			Boolean v) {
		UserNetwork un = getEntityManager().find(UserNetwork.class, un_id);
		if(un.getUser().equals(getUser()))
		{
			un.setVisible(v);
			getEntityManager().merge(un);
		}
	}

	@WebMethod
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
