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
public class UsageInformation extends AbstractFacade<Tool> implements UsageInformationRemote {

    /**
     * Default constructor. 
     */
    public UsageInformation() {
        super(Tool.class);
    }

	
	@SuppressWarnings("unchecked")
	public List<Tool> getToolsByPopularity() {
		Query q = getEntityManager().createQuery("select object(c) from Tool as c order by c.usageCount");
        List<Tool> r = q.getResultList();
        return r;
        
	}

	public List<Workflow> getWorkflowsByPopularity() {
		CriteriaQuery<Object> cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(Workflow.class));
        List<Object> r = getEntityManager().createQuery(cq).getResultList();
        List<Workflow> wr = new ArrayList<Workflow>();
        for(Object o : r)
        {
        	wr.add((Workflow) o);
        	
        	((Workflow) o).getTools().size();
        }
        return wr;
	}
	
	@SuppressWarnings("unchecked")
	public List<Tool> getMostPopularWFHeads() {
		Query q = getEntityManager().createQuery("select object(c) from Tool as c order by c.wfCountHead");
        List<Tool> r = q.getResultList();
        return r;
	}

	public Tool getMostPopularNextTool(Tool tool) {
		Query q = getEntityManager().createNativeQuery("select t.* from TOOL t " +
				"inner join WORKFLOWTOOL wt on wt.tool_id=t.id " +
				"inner join WORKFLOWTOOL wt2 on wt2.workflow_id=wt.workflow_id and wt2.cardinality = wt.cardinality-1 " +
				"inner join TOOL t2 on t2.id=wt2.tool_id " +
				"inner join WORKFLOW w on w.id=wt.workflow_id " +
				"where t2.id=? order by w.usageCount desc limit 1", Tool.class);
		q.setParameter(1, tool.getId());
        Tool r = null;
        try
		{
		r = (Tool) q.getSingleResult();
		}
		catch(NoResultException e)
		{
			
		}
		return r;
	}

	public Tool getMostPopularPreviousTool(Tool tool) {
		Query q = getEntityManager().createNativeQuery("select t.* from TOOL t " +
				"inner join WORKFLOWTOOL wt on wt.tool_id=t.id " +
				"inner join WORKFLOWTOOL wt2 on wt2.workflow_id=wt.workflow_id and wt2.cardinality = wt.cardinality+1 " +
				"inner join TOOL t2 on t2.id=wt2.tool_id " +
				"inner join WORKFLOW w on w.id=wt.workflow_id " +
				"where t2.id=? order by w.usageCount desc limit 1", Tool.class);
		q.setParameter(1, tool.getId());
        Tool r = null;
        try
		{
		r = (Tool) q.getSingleResult();
		}
		catch(NoResultException e)
		{
			
		}
		return r;
	}
	@Override
	@PermitAll
	public List<Tool> getAllTools() {
		return findAll();
	}



	@SuppressWarnings("unchecked")
	@Override
	public List<Workflow> getAllWorkflowsIncluding(Tool tool) {
		Query q = getEntityManager().createNativeQuery("select distinct w.* from WORKFLOW w " +
				"inner join WORKFLOWTOOL wt on w.id=wt.workflow_id " +
				"where wt.tool_id=? order by w.usageCount", Workflow.class);
		q.setParameter(1, tool.getId());
		List<Workflow> wf = null;
        try
		{
        	wf = q.getResultList();
        	for(Workflow w : wf)
        		for(WorkflowTool wft : w.getTools())
        			wft.getTool();
		}
		catch(NoResultException e)
		{
			
		}
		return wf;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Workflow> getMostPopularWorkflowStartingWith(
			Tool tool) {
		Query q = getEntityManager().createNativeQuery("select distinct w.* from WORKFLOW w " +
				"inner join WORKFLOWTOOL wt on w.id=wt.workflow_id " +
				"where wt.tool_id=? and wt.cardinality=0 order by w.usageCount desc limit 1", Workflow.class);
		q.setParameter(1, tool.getId());
		List<Workflow> wf = null;
        try
		{
        	wf = q.getResultList();
        	for(Workflow w : wf)
        		for(WorkflowTool wft : w.getTools())
        		{
        			System.out.println(wft.getTool());
        			wft.getTool();
        		}
		}
		catch(NoResultException e)
		{
			System.err.println("No result");
		}
		return wf;
	}



	@SuppressWarnings("unchecked")
	@Override
	public List<Workflow> getMostPopularWorkflowIncluding(Tool tool) {
		Query q = getEntityManager().createNativeQuery("select distinct w.* from WORKFLOW w " +
				"inner join WORKFLOWTOOL wt on w.id=wt.workflow_id " +
				"where wt.tool_id=? order by w.usageCount desc limit 1", Workflow.class);
		q.setParameter(1, tool.getId());
		List<Workflow> wf = null;
        try
		{
        	wf = q.getResultList();
        	for(Workflow w : wf)
        		for(WorkflowTool wft : w.getTools())
        			wft.getTool();
		}
		catch(NoResultException e)
		{
			
		}
		return wf;
	}



	@SuppressWarnings("unchecked")
	@Override
	public List<Workflow> getToolSuggestion(Workflow cwf) {
		Query q = getEntityManager().createNativeQuery("select distinct w.* from WORKFLOW w " +
				"where w.parent_id=? order by w.usageCount desc", Workflow.class);
		q.setParameter(1, cwf.getId());
		List<Workflow> wf = null;
        try
		{
        	wf = q.getResultList();
        	for(Workflow w : wf)
        		for(WorkflowTool wft : w.getTools())
        			wft.getTool();
		}
		catch(NoResultException e)
		{
			
		}
		return wf;
	}



	@Override
	public Transaction sendUsageEvent(AnalysisEvent e) {
		if(
			(e.getTransaction().getUser() == null && getUser() != null )
			||
			(e.getTransaction().getUser() != null && !e.getTransaction().getUser().equals(getUser())))
			{
				System.err.println("Received missmatched username for analysis event " + e);
			}
		if(!getEntityManager().contains(e.getTransaction()))
		{
			e.setTransaction(createOrFindTransaction(e.getTransaction()));
		}
		if(e.getTool() == null || !getEntityManager().contains(e.getTool()))
		{
			e.setTool(getToolByName(e.getToolname()));
			if(e.getTool() == null)
			{
				//Create the tool
				Tool tool = new Tool();
				tool.setName(e.getToolname());
				tool.setUsageCount(0);
				getEntityManager().persist(tool);
				e.setTool(tool);
			}
		}
		getEntityManager().persist(e);
		
		//Update the workflow for the transaction
		e.getTransaction().setWorkflow(getExtendedWorkflow(e.getTransaction(),e.getTool()));
		
		e.getTransaction().getWorkflow().setUsageCount(e.getTransaction().getWorkflow().getUsageCount() + 1);
		
		e.getTool().setUsageCount(e.getTool().getUsageCount() + 1);
		getEntityManager().merge(e.getTool());
		getEntityManager().merge(e.getTransaction());

		for(WorkflowTool t : e.getTransaction().getWorkflow().getTools())
		{
			t.getTool();
		}

		return e.getTransaction();
	}
	
	private Tool getToolByName(String toolname) {
		Query q = getEntityManager().createQuery("select object(c) from Tool as c where c.name=:toolname"); //and c.user=:user
		q.setParameter("toolname", toolname);
//		q.setParameter("user", user);
		Tool r = null;
		try
		{
		r = (Tool) q.getSingleResult();
		}
		catch(NoResultException e)
		{
			
		}
		return r;
	}


	/**
	 * Returns a workflow that is based on the given workflow, PLUS the tool specified
	 * Createst the workflow if it doesn't exist already
	 * @param workflow
	 * @param tool
	 * @return
	 */
	private Workflow getExtendedWorkflow(Transaction t, Tool tool) {
		if(t.getWorkflow() == null)
			return createNewWorkflowForTrans(t, tool);
		Query q = getEntityManager().createNativeQuery("select w.* from WORKFLOW w " +
				"inner join WORKFLOWTOOL wt on wt.workflow_id=w.id " + 
				"where w.parent_id=? and wt.cardinality=? and wt.tool_id=? limit 1", Tool.class);
		q.setParameter(1, t.getWorkflow().getId());
		q.setParameter(2, t.getWorkflow().getTools().size());
		q.setParameter(3, tool.getId());
        Workflow r = null;
        try
		{
        	r = (Workflow) q.getSingleResult();
        	return r;
		}
		catch(NoResultException e)
		{
			return createNewWorkflowForTrans(t, tool);
		}
		
	}



	private Workflow createNewWorkflowForTrans(Transaction trans, Tool tool) {
		//Need to create the workflow
		Workflow r = new Workflow();
		r.setParent(trans.getWorkflow());
		r.setCreatedAt(new Date());
		r.setCreationTransaction(trans);
		r.setUsageCount(0);
		r.setCreator(trans.getUser());
		List<WorkflowTool> newTools = new ArrayList<WorkflowTool>();
		if(trans.getWorkflow() != null && trans.getWorkflow().getTools() != null)
			for(WorkflowTool to : trans.getWorkflow().getTools())
			{
				WorkflowTool nt = new WorkflowTool();
				nt.setOrder(to.getOrder());
				nt.setTool(to.getTool());
				nt.setWorkflow(r);
				newTools.add(nt);
			}
		WorkflowTool nt = new WorkflowTool();
		nt.setOrder(newTools.size());
		nt.setTool(tool);
		nt.setWorkflow(r);
		newTools.add(nt);
		r.setTools(newTools);
		getEntityManager().persist(r);
		return r;
	}


	private Transaction createOrFindTransaction(Transaction t)
	{
		Transaction r = findTransactionByName(t.getClientID(), t.getUser());
		if(r == null)
		{
			r = t;
			getEntityManager().persist(r);
		}
		return r;
	}
	private Transaction findTransactionByName(String name,User user)
	{
		Query q;
		if(user == null)
			q = getEntityManager().createQuery("select object(c) from Transaction as c where c.clientID=:clientid and c.user is null");
		else
			q = getEntityManager().createQuery("select object(c) from Transaction as c where c.clientID=:clientid and c.user=:user"); //

		q.setParameter("clientid", name);
//		q.setParameter("user", user);
		Transaction r = null;
		try
		{
		r = (Transaction) q.getSingleResult();
		}
		catch(NoResultException e)
		{
			
		}
		return r;
	}


	@Override
	public User getExpertUserFor(Tool tn) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	@RolesAllowed("user")
	public ToolRating getMyRating(Tool tool) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	@RolesAllowed("user")
	public Tool saveRating(ToolRating tr) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	@RolesAllowed("user")
	public WorkflowRating getMyRating(Workflow workflow) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	@RolesAllowed("user")
	public Workflow saveRating(WorkflowRating tr) {
		// TODO Auto-generated method stub
		return null;
	}
}
