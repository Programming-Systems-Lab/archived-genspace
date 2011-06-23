package org.geworkbench.components.genspace.server;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.geworkbench.components.genspace.RuntimeEnvironmentSettings;
import org.geworkbench.components.genspace.entity.AnalysisEvent;
import org.geworkbench.components.genspace.entity.Tool;
import org.geworkbench.components.genspace.entity.Transaction;
import org.geworkbench.components.genspace.entity.User;
import org.geworkbench.components.genspace.entity.Workflow;

/**
 * Session Bean implementation class PublicFacade
 */
@Stateless
public class PublicFacade extends GenericUsageInformation implements PublicFacadeRemote {

    /**
     * Default constructor. 
     */
    public PublicFacade() {
        super(Tool.class);
    }

    public boolean userExists(String username) {
		return findByUserName(username) != null;
	}

    @Override
	public User register(User u) {
		if(userExists(u.getUsername()))
			return null;
		getEntityManager().persist(u);
		User myUser = findByUserName(u.getUsername());
		getEntityManager().detach(myUser);
		getEntityManager().clear();
		return myUser;
	}

	@Override
	public User register(byte[] userObj) {
		return register((User) RuntimeEnvironmentSettings.readObject(userObj));
	}

}
