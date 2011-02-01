package edu.columbia.cs.psl.genspace.server;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import edu.columbia.cs.psl.genspace.entity.Friend;
import edu.columbia.cs.psl.genspace.entity.User;

/**
 * Session Bean implementation class FriendFacade
 */
@Stateless
public class FriendFacade extends AbstractFacade<Friend> implements FriendFacadeRemote  {
    /**
     * Default constructor. 
     */
    public FriendFacade() {
        super(Friend.class);
    }
	@Override
	public void createNewUser() {
		User u = new User();
		getEntityManager().persist(u);
	}

}
