package org.geworkbench.components.genspace.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;

import org.geworkbench.components.genspace.entity.AnalysisEvent;
import org.geworkbench.components.genspace.entity.AnalysisEventParameter;
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
public class UsageInformation extends GenericUsageInformation implements
		UsageInformationRemote {

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
		logUsage();

		Query q = getEntityManager()
				.createQuery(
						"select object(c) from ToolRating as c where c.creator=:user and c.tool.id=:tool");
		q.setParameter("user", getUser());
		q.setParameter("tool", tool);
		ToolRating r = null;
		try {
			r = (ToolRating) q.getSingleResult();
		} catch (NoResultException e) {

		} catch (NonUniqueResultException e) {
			q = getEntityManager()
					.createQuery(
							"delete from ToolRating as c where c.creator=:user and c.tool.id=:tool");
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
		logUsage();

		ToolRating t = getMyToolRating(tool);
		if (t != null) {
			Tool z = t.getTool();
			z.setNumRating(z.getNumRating() - 1);
			z.setSumRating(z.getSumRating() - t.getRating());
			getEntityManager().merge(z);
			getEntityManager().remove(t);
		}
		t = new ToolRating();
		t.setCreator(getUser());
		t.setCreatedAt(new Date());
		t.setTool(getEntityManager().find(Tool.class, tool));
		t.setRating(rating);
		getEntityManager().persist(t);
		// t.getTool().updateRatingCache();
		Tool z = t.getTool();
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
		logUsage();

		Query q = getEntityManager()
				.createQuery(
						"select object(c) from WorkflowRating as c where c.creator=:user and c.workflow.id=:workflow");
		q.setParameter("user", getUser());
		q.setParameter("workflow", workflow);
		WorkflowRating r = null;
		try {
			r = (WorkflowRating) q.getSingleResult();
		} catch (NoResultException e) {

		}
		return r;
	}

	@WebMethod
	@Override
	@RolesAllowed("user")
	public Workflow saveWorkflowRating(int workflow, int rating) {
		logUsage();

		WorkflowRating t = getMyWorkflowRating(workflow);
		if (t != null) {
			Workflow z = t.getWorkflow();
			z.setNumRating(z.getNumRating() - 1);
			z.setSumRating(z.getSumRating() - t.getRating());
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
		// t.getWorkflow().updateRatingsCache();
		z = getEntityManager().merge(z);

		return z;
	}

	@WebMethod
	@Override
	public Tool getTool(int id) {
		Tool r = getEntityManager().find(Tool.class, id);
		getEntityManager().refresh(r);
		return r;
	}

	@WebMethod
	@Override
	public Workflow getWorkflow(int id) {
		return getEntityManager().find(Workflow.class, id);
	}

	@WebMethod
	@RolesAllowed("user")
	public void saveNote(AnalysisEvent e) {
		AnalysisEvent e1= getEntityManager().find(AnalysisEvent.class, e.getId());
		e1.setNote(e.getNote());
		getEntityManager().merge(e1);
	}
	@WebMethod
	@RolesAllowed("user")
	public Set<AnalysisEventParameter> getParametersForEvent(AnalysisEvent event)
	{
		AnalysisEvent ed = getEntityManager().find(AnalysisEvent.class, event.getId());
		if(ed.getTransaction().getUser().equals(getUser()))
			return ed.getParameters();
		return null;
	}
	@WebMethod
	@RolesAllowed("user")
	public List<AnalysisEvent> getMyNotes(String filter, String sortOn) {
		Query q = getEntityManager().createQuery("SELECT ev FROM AnalysisEvent ev join ev.transaction tr where tr.user=:user "+(filter != null ? "AND (ev.note LIKE :filter1 OR ev.tool.name LIKE :filter2)" : "")+" ORDER BY " + ("tool".equals(sortOn) ? "ev.tool.name" : "ev.createdAt"));
		q.setParameter("user", getUser());
		if(filter != null)
		{
			q.setParameter("filter1", "%"+filter+"%");
			q.setParameter("filter2", "%"+filter+"%");
		}
		ArrayList<AnalysisEvent> ret = new ArrayList<AnalysisEvent>();
		for(Object o : q.getResultList())
		{
			getEntityManager().detach(o);
			((AnalysisEvent) o).getTransaction().setWorkflow(null);
			((AnalysisEvent) o).getTransaction().setAnalysisEvents(null);
			((AnalysisEvent) o).setToolname(((AnalysisEvent)o).getTool().getName());
			((AnalysisEvent) o).setParameters(null);
			ret.add((AnalysisEvent) o);
		}
		System.out.println(ret.size() +"being ret");
		return ret;
	}
}
