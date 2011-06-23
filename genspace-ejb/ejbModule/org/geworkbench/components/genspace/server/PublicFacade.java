package org.geworkbench.components.genspace.server;

import java.util.List;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;

import org.geworkbench.components.genspace.entity.AnalysisEvent;
import org.geworkbench.components.genspace.entity.Tool;
import org.geworkbench.components.genspace.entity.Transaction;
import org.geworkbench.components.genspace.entity.User;
import org.geworkbench.components.genspace.entity.Workflow;

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
	public User register(User u) {
		if(userExists(u.getUsername()))
			return null;
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

    @WebMethod
	@Override
	public List<Tool> getToolsByPopularity() {
		// TODO Auto-generated method stub
		return super.getToolsByPopularity();
	}

    @WebMethod
	@Override
	public List<Workflow> getWorkflowsByPopularity() {
		// TODO Auto-generated method stub
		return super.getWorkflowsByPopularity();
	}

    @WebMethod
	@Override
	public List<Tool> getMostPopularWFHeads() {
		// TODO Auto-generated method stub
		return super.getMostPopularWFHeads();
	}
    @WebMethod
	@Override
	public Tool getMostPopularNextTool(int id) {
		// TODO Auto-generated method stub
		return super.getMostPopularNextTool(id);
	}
    @WebMethod
	@Override
	public Tool getMostPopularPreviousTool(int tool) {
		// TODO Auto-generated method stub
		return super.getMostPopularPreviousTool(tool);
	}
    @WebMethod
	@Override
	public List<Tool> getAllTools() {
		// TODO Auto-generated method stub
		return super.getAllTools();
	}
    @WebMethod
	@Override
	public List<Workflow> getAllWorkflowsIncluding(int tool) {
		// TODO Auto-generated method stub
		return super.getAllWorkflowsIncluding(tool);
	}
    @WebMethod
	@Override
	public List<Workflow> getMostPopularWorkflowStartingWith(int tool) {
		// TODO Auto-generated method stub
		return super.getMostPopularWorkflowStartingWith(tool);
	}
    @WebMethod
	@Override
	public List<Workflow> getMostPopularWorkflowIncluding(int tool) {
		// TODO Auto-generated method stub
		return super.getMostPopularWorkflowIncluding(tool);
	}
    @WebMethod
	@Override
	public List<Workflow> getToolSuggestion(int cwf) {
		// TODO Auto-generated method stub
		return super.getToolSuggestion(cwf);
	}
    @WebMethod
	@Override
	public Transaction sendUsageLog(List<AnalysisEvent> e) {
		// TODO Auto-generated method stub
		return super.sendUsageLog(e);
	}
    @WebMethod
	@Override
	public Transaction sendUsageEvent(AnalysisEvent e) {
		// TODO Auto-generated method stub
		return super.sendUsageEvent(e);
	}
    @WebMethod
	@Override
	public User getExpertUserFor(int tn) {
		// TODO Auto-generated method stub
		return super.getExpertUserFor(tn);
	}
    @WebMethod
	@Override
	public byte[] sendUsageSingleEvent(byte[] analysisEvent) {
		// TODO Auto-generated method stub
		return super.sendUsageSingleEvent(analysisEvent);
	}
    @WebMethod
	@Override
	public byte[] sendMultipeEvents(byte[] analysisEvent) {
		// TODO Auto-generated method stub
		return super.sendMultipeEvents(analysisEvent);
	}
    @WebMethod
	@Override
	public byte[] getWorkflowsByPopularityBytes() {
		// TODO Auto-generated method stub
		return super.getWorkflowsByPopularityBytes();
	}

}
