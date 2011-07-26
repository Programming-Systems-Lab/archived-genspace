package org.geworkbench.components.genspace.server;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.geworkbench.components.genspace.entity.AnalysisEvent;
import org.geworkbench.components.genspace.entity.Tool;
import org.geworkbench.components.genspace.entity.Transaction;
import org.geworkbench.components.genspace.entity.User;
import org.geworkbench.components.genspace.entity.Workflow;
import org.geworkbench.components.genspace.entity.WorkflowComment;

/**
 * Session Bean implementation class PublicFacade
 */
@Stateless
@WebService
public class PublicFacade extends GenericUsageInformation implements PublicFacadeRemote {

    /**
     * Default constructor. 
     */
    public PublicFacade() {
        super(Tool.class);
    }
    
    @WebMethod
    public boolean userExists(String username) {
		return findByUserName(username) != null;
	}

    @WebMethod
    @Override
    public List<WorkflowComment> getWFComments(Workflow w) {
    	logUsage();
    	return super.getWFComments(w);
    }
    
    @WebMethod
    @Override
	public User register(User u) {
		if(userExists(u.getUsername()))
			return null;
    	logUsage();
		getEntityManager().persist(u);
		User myUser = findByUserName(u.getUsername());
		getEntityManager().detach(myUser);
		getEntityManager().clear();
		return myUser;
	}
    
    @WebMethod(exclude=true)
	@Override
	public User register(byte[] userObj) {
		return register((User) AbstractFacade.readObject(userObj));
	}
    

   
}
