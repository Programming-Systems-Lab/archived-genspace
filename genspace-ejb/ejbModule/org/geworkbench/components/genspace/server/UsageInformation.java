package org.geworkbench.components.genspace.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

import org.geworkbench.components.genspace.entity.AnalysisEvent;
import org.geworkbench.components.genspace.entity.Tool;
import org.geworkbench.components.genspace.entity.ToolRating;
import org.geworkbench.components.genspace.entity.Transaction;
import org.geworkbench.components.genspace.entity.User;
import org.geworkbench.components.genspace.entity.Workflow;
import org.geworkbench.components.genspace.entity.WorkflowRating;
import org.geworkbench.components.genspace.entity.WorkflowTool;

/**
 * Session Bean implementation class ToolInformation
 */
@Stateless
public class UsageInformation extends GenericUsageInformation implements UsageInformationRemote {

    /**
     * Default constructor. 
     */
    public UsageInformation() {
        super(Tool.class);
    }

	@Override
	@RolesAllowed("user")
	public ToolRating getMyRating(Tool tool) {
		Query q = getEntityManager().createQuery("select object(c) from ToolRating as c where c.creator=:user and c.tool=:tool");
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
		return r;
	}


	@Override
	@RolesAllowed("user")
	public Tool saveRating(ToolRating tr) {
		ToolRating t = getMyRating(tr.getTool());
		if(t != null)
		{
			getEntityManager().remove(t);
		}
		getEntityManager().persist(tr);
		getEntityManager().refresh(tr.getTool());
		return tr.getTool();
	}


	@Override
	@RolesAllowed("user")
	public WorkflowRating getMyRating(Workflow workflow) {
		Query q = getEntityManager().createQuery("select object(c) from WorkflowRating as c where c.creator=:user and c.workflow=:workflow");
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
	public Workflow saveRating(WorkflowRating tr) {
		WorkflowRating t = getMyRating(tr.getWorkflow());
		if(t != null)
		{
			getEntityManager().remove(t);
		}
		getEntityManager().persist(tr);
		getEntityManager().refresh(tr.getWorkflow());
		return tr.getWorkflow();
	}
}
