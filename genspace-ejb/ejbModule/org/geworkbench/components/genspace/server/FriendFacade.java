package org.geworkbench.components.genspace.server;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.geworkbench.components.genspace.entity.Friend;
import org.geworkbench.components.genspace.entity.User;

/**
 * Session Bean implementation class FriendFacade
 */
@Stateless
@RolesAllowed("user")
public class FriendFacade extends AbstractFacade<Friend> implements FriendFacadeRemote  {
    /**
     * Default constructor. 
     */
    public FriendFacade() {
        super(Friend.class);
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getFriendRequests() {
		User me = getUser();
		Query q = getEntityManager().createQuery("select object(c) from User as c " +
				"inner join Friend as f on f.id_1=c.id " +
				"where f.id_2=:user and f.mutual = 0");
		q.setParameter("user", me.getId());
		List<User> r = null;
		try
		{
			r = q.getResultList();
		}
		catch(NoResultException e)
		{
			return new ArrayList<User>();
		}
		return r;
	}
	
	@Override
	public void addFriend(User o) {
		User me = getUser();
		Friend f = o.isFriendsWith(me);
		boolean mutual = false;
		if(f != null)
		{
			mutual = true;
			//Annotate their relationship to be mutual
			f.setMutual(true);
			getEntityManager().merge(f);
		}
		f = me.isFriendsWith(o);
		if(f == null)
		{
			//Add our relation iff we are not already friends
			f = new Friend();
			f.setLeftUser(me);
			f.setRightUser(o);
			f.setMutual(mutual);
			f.setVisible(true);
			getEntityManager().persist(f);
		}
	}
	@Override
	public void rejectFriend(User o) {
		User me = getUser();
		Friend f = o.isFriendsWith(me);
		if(f != null)
		{
			getEntityManager().remove(f);
		}

	}
	@Override
	public void updateFriendVisibility(Friend friend, Boolean boolean1) {
		if(friend.getLeftUser().equals(getUser()))
		{
			friend.setVisible(boolean1);
			getEntityManager().merge(friend);
		}
	}
	@Override
	public List<User> getFriendsProfiles() {
		ArrayList<User> res = new ArrayList<User>();
		for(Friend f : getUser().getFriends())
		{
			res.add(f.getRightUser());
		}
		return res;
	}

}
