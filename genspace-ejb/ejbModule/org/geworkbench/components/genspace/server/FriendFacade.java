package org.geworkbench.components.genspace.server;

import javax.ejb.Stateless;

import org.geworkbench.components.genspace.entity.Friend;
import org.geworkbench.components.genspace.entity.User;

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
