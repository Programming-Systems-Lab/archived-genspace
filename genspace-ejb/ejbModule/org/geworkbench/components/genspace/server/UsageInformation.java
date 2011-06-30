package org.geworkbench.components.genspace.server;

import java.util.Date;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
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
import org.geworkbench.components.genspace.entity.WorkflowComment;
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
    @WebMethod
    @Override
    public List<WorkflowComment> getWFComments(Workflow w) {
    	return super.getWFComments(w);
    }
    @WebMethod
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

    @WebMethod
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

    @WebMethod
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

    @WebMethod
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
    @WebMethod
	@Override
	public Tool getTool(int id) {
		Tool r= getEntityManager().find(Tool.class, id);
		getEntityManager().refresh(r);
		return r;
	}
    @WebMethod
	@Override
	public Workflow getWorkflow(int id) {
		return getEntityManager().find(Workflow.class, id);
	}

}
