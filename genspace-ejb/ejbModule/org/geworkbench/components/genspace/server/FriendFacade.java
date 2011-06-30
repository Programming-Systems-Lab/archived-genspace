package org.geworkbench.components.genspace.server;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.geworkbench.components.genspace.entity.Friend;
import org.geworkbench.components.genspace.entity.User;

/**
 * Session Bean implementation class FriendFacade
 */
@Stateless
@RolesAllowed("user")
@WebService
public class FriendFacade extends AbstractFacade<Friend> implements FriendFacadeRemote  {
    /**
     * Default constructor. 
     */
    public FriendFacade() {
        super(Friend.class);
    }
    
    private Friend getFriendRecord(int u1, int u2)
    {
    	Query q = getEntityManager().createNativeQuery("select c.* from Friend c" +
				"where f.id_1=? and f.id_2=?",Friend.class);
		q.setParameter(1, u1);
		q.setParameter(2, u2);
//		q.setParameter("user", me.getId());
		Friend r = null;
		try
		{
			r = (Friend) q.getSingleResult();
		}
		catch(NoResultException e)
		{
			
		}
		return r;
    }

    @WebMethod
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getFriendRequests() {
		User me = getUser();
		Query q = getEntityManager().createNativeQuery("select c.* from registration as c " +
				"inner join Friend as f on f.id_1=c.id " +
				"where f.id_2=? and f.mutual = 0",User.class);
		q.setParameter(1, me.getId());
//		q.setParameter("user", me.getId());
		List<User> r = null;
		try
		{
			r = q.getResultList();
		}
		catch(NoResultException e)
		{
			return new ArrayList<User>();
		}
		getEntityManager().clear();
		return r;
	}
	private User getUser(int id)
	{
		return getEntityManager().find(User.class,id);
	}
	@WebMethod
	@Override
	public void addFriend(int o) {
		User me = getUser();
		getEntityManager().refresh(me);
		
		if(me.getId() == o)
			return;
		User other = getUser(o);
		getEntityManager().refresh(other);
		
		Friend f = other.isFriendsWith(me);
		boolean mutual = false;
		if(f != null)
		{
			mutual = true;
			//Annotate their relationship to be mutual
			f.setMutual(true);
			getEntityManager().merge(f);
		}
		f = me.isFriendsWith(other);
		if(f == null)
		{
			//Add our relation iff we are not already friends
			f = new Friend();
			f.setLeftUser(me);
			f.setRightUser(other);
			f.setMutual(mutual);
			f.setVisible(true);
			getEntityManager().persist(f);
		}

	}
	@WebMethod
	@Override
	public void rejectFriend(int o) {
		User me = getUser();
		Friend f = getUser(o).isFriendsWith(me);
		if(f != null)
		{
			getEntityManager().remove(f);
		}

	}
	@WebMethod
	@Override
	public void updateFriendVisibility(int user_id, Boolean boolean1) {
		Friend friend = getFriendRecord(getUser().getId(),user_id);
		friend.setVisible(boolean1);
		getEntityManager().merge(friend);
	}
	@WebMethod
	public void updateFriendVisibilityByFriend(Friend f)
	{
		updateFriendVisibility(f.getRightUser().getId(), f.isVisible());
	}
	@WebMethod
	@Override
	public void removeFriend(int uid) {
		User me = getUser();
		User u = getUser(uid);
		Friend f = u.isFriendsWith(me);
		if(f != null)
		{
			//Annotate their relationship to be NOT mutual
			f.setMutual(false);
			getEntityManager().merge(f);
		}
		f = me.isFriendsWith(u);
		if(f != null)
		{
			getEntityManager().remove(f);
		}

	}

	@WebMethod
	@Override
	public List<User> getFriends() {
		User me = getUser();
		List<User> ret = new ArrayList<User>();
		for(Friend f : me.getFriends())
		{
			User r = f.getRightUser();
			ret.add(r.loadVisibility(getUser()));
		}
		getEntityManager().clear();
		return ret;
	}
	@WebMethod(exclude = true)
	@Override
	public byte[] getFriendsBytes() {
		List<User> friends = getFriends();
		return AbstractFacade.writeObject(friends);
	}
	@WebMethod(exclude=true)
	@Override
	public byte[] getFriendRequestsList() {
		List<User> friends = getFriendRequests();
		return AbstractFacade.writeObject(friends);
	}

}
