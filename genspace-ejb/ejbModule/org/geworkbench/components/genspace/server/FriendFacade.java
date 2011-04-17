package org.geworkbench.components.genspace.server;

<<<<<<< HEAD
import javax.ejb.Stateless;
=======
import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7

import org.geworkbench.components.genspace.entity.Friend;
import org.geworkbench.components.genspace.entity.User;

/**
 * Session Bean implementation class FriendFacade
 */
@Stateless
<<<<<<< HEAD
=======
@RolesAllowed("user")
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7
public class FriendFacade extends AbstractFacade<Friend> implements FriendFacadeRemote  {
    /**
     * Default constructor. 
     */
    public FriendFacade() {
        super(Friend.class);
    }
<<<<<<< HEAD
	@Override
	public void createNewUser() {
		User u = new User();
		getEntityManager().persist(u);
=======


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
			for(User u : r)
			{
				fullySerialize(u);
			}
		}
		catch(NoResultException e)
		{
			return new ArrayList<User>();
		}
		return r;
	}
	private User getUser(int id)
	{
		return getEntityManager().find(User.class,id);
	}
	@Override
	public void addFriend(int o) {
		User me = getUser();
		if(me.getId() == o)
			return;
		User other = getUser(o);
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
	@Override
	public void rejectFriend(int o) {
		User me = getUser();
		Friend f = getUser(o).isFriendsWith(me);
		if(f != null)
		{
			getEntityManager().remove(f);
		}

	}
	@Override
	public void updateFriendVisibility(int friend_id, Boolean boolean1) {
		Friend friend = getEntityManager().find(Friend.class, friend_id);
		if(friend.getLeftUser().equals(getUser()))
		{
			friend.setVisible(boolean1);
			getEntityManager().merge(friend);
		}
	}
	@Override
	public List<User> getFriendsProfiles() {
		ArrayList<User> res = new ArrayList<User>();
		System.out.println("request from" + getUser());
		for(Friend f : getUser().getFriends())
		{
			res.add(fullySerialize(f.getRightUser()));
		}
		return res;
	}

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

>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7
	}

}
