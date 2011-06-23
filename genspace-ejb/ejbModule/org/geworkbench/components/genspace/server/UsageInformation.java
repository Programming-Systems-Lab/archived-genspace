package org.geworkbench.components.genspace.server;

import java.util.Date;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

import org.geworkbench.components.genspace.entity.AnalysisEvent;
import org.geworkbench.components.genspace.entity.Tool;
import org.geworkbench.components.genspace.entity.ToolRating;
import org.geworkbench.components.genspace.entity.Transaction;
import org.geworkbench.components.genspace.entity.User;
import org.geworkbench.components.genspace.entity.Workflow;
import org.geworkbench.components.genspace.entity.WorkflowRating;

/**
 * Session Bean implementation class ToolInformation
 */
@Stateless
@WebService
public class UsageInformation extends GenericUsageInformation implements UsageInformationRemote {

    /**
     * Default constructor. 
     */
    public UsageInformation() {
        super(Tool.class);
    }

	@Override
	@RolesAllowed("user")
	public ToolRating getMyToolRating(int tool) {
		Query q = getEntityManager().createQuery("select object(c) from ToolRating as c where c.creator=:user and c.tool.id=:tool");
		q.setParameter("user", getUser());
		q.setParameter("tool", tool);
		ToolRating r = null;
		try
		{
		r = (ToolRating) q.getSingleResult();
		}
		catch(NoResultException e)
		{
			
		}
		catch(NonUniqueResultException e)
		{
			q = getEntityManager().createQuery("delete from ToolRating as c where c.creator=:user and c.tool.id=:tool");
			q.setParameter("user", getUser());
			q.setParameter("tool", tool);
			q.executeUpdate();
		}
		return r;
	}


	@Override
	@RolesAllowed("user")
	public Tool saveToolRating(int tool, int rating) {
		ToolRating t = getMyToolRating(tool);
		if(t != null)
		{
			getEntityManager().remove(t);
		}
		t = new ToolRating();
		t.setCreator(getUser());
		t.setCreatedAt(new Date());
		t.setTool(getEntityManager().find(Tool.class, tool));
		t.setRating(rating);
		getEntityManager().persist(t);
//		t.getTool().updateRatingCache();
		Tool z= t.getTool();
		z.setNumRating(z.getNumRating() + 1);
		z.setSumRating(z.getSumRating() + rating);
		getEntityManager().merge(z);
		getEntityManager().flush();
		
		return z;
	}


	@Override
	@RolesAllowed("user")
	public WorkflowRating getMyWorkflowRating(int workflow) {
		Query q = getEntityManager().createQuery("select object(c) from WorkflowRating as c where c.creator=:user and c.workflow.id=:workflow");
		q.setParameter("user", getUser());
		q.setParameter("workflow", workflow);
		WorkflowRating r = null;
		try
		{
		r = (WorkflowRating) q.getSingleResult();
		}
		catch(NoResultException e)
		{
			
		}
		return r;
	}


	@Override
	@RolesAllowed("user")
	public Workflow saveWorkflowRating(int workflow, int rating) {
		WorkflowRating t = getMyWorkflowRating(workflow);
		if(t != null)
		{
			getEntityManager().remove(t);
		}
		t = new WorkflowRating();
		t.setCreator(getUser());
		t.setCreatedAt(new Date());
		t.setWorkflow(getEntityManager().find(Workflow.class, workflow));
		t.setRating(rating);
		getEntityManager().persist(t);
		
		Workflow z = t.getWorkflow();
		z.setNumRating(z.getNumRating() + 1);
		z.setSumRating(z.getSumRating() + rating);
//		t.getWorkflow().updateRatingsCache();
		z = getEntityManager().merge(z);

		return z;
	}

	@Override
	public Tool getTool(int id) {
		Tool r= getEntityManager().find(Tool.class, id);
		getEntityManager().refresh(r);
		return r;
	}

	@Override
	public Workflow getWorkflow(int id) {
		return getEntityManager().find(Workflow.class, id);
	}

	@Override
	public List<Tool> getToolsByPopularity() {
		// TODO Auto-generated method stub
		return super.getToolsByPopularity();
	}

	@Override
	public List<Workflow> getWorkflowsByPopularity() {
		// TODO Auto-generated method stub
		return super.getWorkflowsByPopularity();
	}

	@Override
	public List<Tool> getMostPopularWFHeads() {
		// TODO Auto-generated method stub
		return super.getMostPopularWFHeads();
	}

	@Override
	public Tool getMostPopularNextTool(int id) {
		// TODO Auto-generated method stub
		return super.getMostPopularNextTool(id);
	}

	@Override
	public Tool getMostPopularPreviousTool(int tool) {
		// TODO Auto-generated method stub
		return super.getMostPopularPreviousTool(tool);
	}

	@Override
	public List<Tool> getAllTools() {
		// TODO Auto-generated method stub
		return super.getAllTools();
	}

	@Override
	public List<Workflow> getAllWorkflowsIncluding(int tool) {
		// TODO Auto-generated method stub
		return super.getAllWorkflowsIncluding(tool);
	}

	@Override
	public List<Workflow> getMostPopularWorkflowStartingWith(int tool) {
		// TODO Auto-generated method stub
		return super.getMostPopularWorkflowStartingWith(tool);
	}

	@Override
	public List<Workflow> getMostPopularWorkflowIncluding(int tool) {
		// TODO Auto-generated method stub
		return super.getMostPopularWorkflowIncluding(tool);
	}

	@Override
	public List<Workflow> getToolSuggestion(int cwf) {
		// TODO Auto-generated method stub
		return super.getToolSuggestion(cwf);
	}

	@Override
	public Transaction sendUsageLog(List<AnalysisEvent> e) {
		// TODO Auto-generated method stub
		return super.sendUsageLog(e);
	}

	@Override
	public Transaction sendUsageEvent(AnalysisEvent e) {
		// TODO Auto-generated method stub
		return super.sendUsageEvent(e);
	}

	@Override
	public User getExpertUserFor(int tn) {
		// TODO Auto-generated method stub
		return super.getExpertUserFor(tn);
	}

	@Override
	public byte[] sendUsageSingleEvent(byte[] analysisEvent) {
		// TODO Auto-generated method stub
		return super.sendUsageSingleEvent(analysisEvent);
	}

	@Override
	public byte[] sendMultipeEvents(byte[] analysisEvent) {
		// TODO Auto-generated method stub
		return super.sendMultipeEvents(analysisEvent);
	}

	@Override
	public byte[] getWorkflowsByPopularityBytes() {
		// TODO Auto-generated method stub
		return super.getWorkflowsByPopularityBytes();
	}


}
