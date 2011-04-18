package org.geworkbench.components.genspace.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

import org.geworkbench.components.genspace.RuntimeEnvironmentSettings;
import org.geworkbench.components.genspace.entity.AnalysisEvent;
import org.geworkbench.components.genspace.entity.Tool;
import org.geworkbench.components.genspace.entity.Transaction;
import org.geworkbench.components.genspace.entity.User;
import org.geworkbench.components.genspace.entity.Workflow;
import org.geworkbench.components.genspace.entity.WorkflowTool;


public abstract class GenericUsageInformation extends AbstractFacade<Tool>  implements ToolInformationProvider{

	public GenericUsageInformation() {
		super(Tool.class);
	}
	public GenericUsageInformation(Class<Tool> T)
	{
		super(T);
	}

	@SuppressWarnings("unchecked")
	public List<Tool> getToolsByPopularity() {
		Query q = getEntityManager().createQuery("select object(c) from Tool as c order by c.usageCount desc");
        List<Tool> r = q.getResultList();
        return r;
        
	}

	public List<Workflow> getWorkflowsByPopularity() {
		System.out.println("Gettign workflows by popularity");
		long start = System.currentTimeMillis();
		CriteriaQuery<Object> cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(Workflow.class));//.joinList("tools")); 
        List<Object> r = getEntityManager().createQuery(cq).getResultList();
        List<Workflow> wr = new ArrayList<Workflow>();
        for(Object o : r)
        {
        	wr.add(((Workflow) o).slimDown());
        } 
        System.out.println("Finished getting workflows in time " + ((System.currentTimeMillis() - start)/1000));
        return wr;
	} 
	
	@SuppressWarnings("unchecked")
	public List<Tool> getMostPopularWFHeads() {
		Query q = getEntityManager().createQuery("select object(c) from Tool as c order by c.wfCountHead desc");
        List<Tool> r = q.getResultList();
        return r;
	}

	public Tool getMostPopularNextTool(int id) {
		System.out.println("Selecting next popular tool for " + id);
		long start = System.currentTimeMillis();
		Query q = getEntityManager().createNativeQuery("select t.* from TOOL t " +
				"inner join WORKFLOWTOOL wt on wt.tool_id=t.id " +
				"inner join WORKFLOWTOOL wt2 on wt2.workflow_id=wt.workflow_id and wt2.cardinality = wt.cardinality-1 " +
				"inner join TOOL t2 on t2.id=wt2.tool_id " +
				"inner join WORKFLOW w on w.id=wt.workflow_id " +
				"where t2.id=? order by w.usageCount desc limit 1", Tool.class);
		q.setParameter(1, id);
        Tool r = null;
        try
		{
		r = (Tool) q.getSingleResult();
		}
		catch(NoResultException e)
		{
			
		}
		System.out.println("Finished after " + (System.currentTimeMillis() - start) + " secs");
		return r;
	}

	public Tool getMostPopularPreviousTool(int tool) {
		Query q = getEntityManager().createNativeQuery("select t.* from TOOL t " +
				"inner join WORKFLOWTOOL wt on wt.tool_id=t.id " +
				"inner join WORKFLOWTOOL wt2 on wt2.workflow_id=wt.workflow_id and wt2.cardinality = wt.cardinality+1 " +
				"inner join TOOL t2 on t2.id=wt2.tool_id " +
				"inner join WORKFLOW w on w.id=wt.workflow_id " +
				"where t2.id=? order by w.usageCount desc limit 1", Tool.class);
		q.setParameter(1, tool);
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
	public List<Workflow> getAllWorkflowsIncluding(int tool) {
		Query q = getEntityManager().createNativeQuery("select distinct w.* from WORKFLOW w " +
				"inner join WORKFLOWTOOL wt on w.id=wt.workflow_id " +
				"where wt.tool_id=? order by w.usageCount desc", Workflow.class);
		q.setParameter(1, tool);
		List<Workflow> wf = null;
		ArrayList<Workflow> ret = new ArrayList<Workflow>();
        try
		{
        	wf = q.getResultList();
        	for(Workflow w : wf)
        	{
        		ret.add(w.slimDown());
        	}
		}
		catch(NoResultException e)
		{
			
		}
		return ret;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Workflow> getMostPopularWorkflowStartingWith(
			int tool) {
		Query q = getEntityManager().createNativeQuery("select distinct w.* from WORKFLOW w " +
				"inner join WORKFLOWTOOL wt on w.id=wt.workflow_id " +
				"where wt.tool_id=? and wt.cardinality=0 order by w.usageCount desc limit 1", Workflow.class);
		q.setParameter(1, tool);
		List<Workflow> wf = null;
		ArrayList<Workflow> ret = new ArrayList<Workflow>();
        try
		{
        	wf = q.getResultList();
        	for(Workflow w : wf)
        	{
        		ret.add(w.slimDown());
        	}
		}
		catch(NoResultException e)
		{
			
		}
		return ret;
	}



	@SuppressWarnings("unchecked")
	@Override
	public List<Workflow> getMostPopularWorkflowIncluding(int tool) {
		System.out.println("Starting to get most popular workflow including " + tool);
		Query q = getEntityManager().createNativeQuery("select distinct w.* from WORKFLOW w " +
				"inner join WORKFLOWTOOL wt on w.id=wt.workflow_id " +
				"where wt.tool_id=? order by w.usageCount desc limit 1", Workflow.class);
		q.setParameter(1, tool);
		List<Workflow> wf = null;
		ArrayList<Workflow> ret = new ArrayList<Workflow>();
        try
		{
        	wf = q.getResultList();
        	for(Workflow w : wf)
        	{
        		ret.add(w.slimDown());
        	}
		}
		catch(NoResultException e)
		{
			
		}
		return ret;
	}



	@SuppressWarnings("unchecked")
	@Override
	public List<Workflow> getToolSuggestion(int cwf) {
		Query q = getEntityManager().createNativeQuery("select distinct w.* from WORKFLOW w " +
				"where w.parent_id=? order by w.usageCount desc", Workflow.class);
		q.setParameter(1, cwf);
		List<Workflow> wf = null;
		List<Workflow> ret = new ArrayList<Workflow>();
        try
		{
        	wf = q.getResultList();
        	for(Workflow w : wf)
        		ret.add(w.slimDown());
		}
		catch(NoResultException e)
		{
			
		}
		return ret;
	}


	@Override
	public Transaction sendUsageLog(List<AnalysisEvent> e)
	{
		HashMap<String,Transaction> trans = new HashMap<String, Transaction>();
		Transaction ret = null;
		for(AnalysisEvent ev : e)
		{
			Transaction t;
			if(!trans.containsKey(ev.getTransaction().getClientID()))
			{
				ev.getTransaction().setUser(getUser());
				t = createOrFindTransaction(ev.getTransaction());
				trans.put(ev.getTransaction().getClientID(), t);
			}
			t = trans.get(ev.getTransaction().getClientID());
			ev.setTransaction(t);
			ret = sendUsageEvent(ev);
		}
		return ret;
	}
	
	@Override
	public Transaction sendUsageEvent(AnalysisEvent e) {
		if(
			(e.getTransaction().getUser() != null && !e.getTransaction().getUser().equals(getUser())))
			{
				e.getTransaction().setUser(getUser());
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
	 * Creates the workflow if it doesn't exist already
	 * @param workflow
	 * @param tool
	 * @return
	 */
	private Workflow getExtendedWorkflow(Transaction t, Tool tool) {
		if(t.getWorkflow() == null)
		{
			Query q = getEntityManager().createNativeQuery("select w.* from WORKFLOW w " +
					"inner join WORKFLOWTOOL wt on wt.workflow_id=w.id " + 
					"where w.parent_id is null and wt.cardinality=1 and wt.tool_id=? limit 1", Workflow.class);
			q.setParameter(1, tool.getId());
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
		Query q = getEntityManager().createNativeQuery("select w.* from WORKFLOW w " +
				"inner join WORKFLOWTOOL wt on wt.workflow_id=w.id " + 
				"where w.parent_id=? and wt.cardinality=? and wt.tool_id=? limit 1", Workflow.class);
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
		nt.setOrder(newTools.size()+1);
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
		{
			q = getEntityManager().createQuery("select object(c) from Transaction as c where c.clientID=:clientid and c.user=:user"); //
			q.setParameter("user", user);
		}
		q.setParameter("clientid", name);

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
	public User getExpertUserFor(int tn) {
		Query q;
			q = getEntityManager().createNativeQuery("select r.* from registration r " +
					"inner join `TRANSACTION` t on t.user_id=r.id " +
					"inner join ANALYSISEVENT e on e.transaction_id=t.id " +
					"where e.tool_id="+tn+" group by r.id order by count(*) DESC LIMIT 1",User.class);
		User r = null;
		try
		{
			r = (User) q.getSingleResult();
			r.loadVisibility(getUser());
		}
		catch(NoResultException e)
		{
		}
		return r;
	}


	@Override
	public byte[] sendUsageSingleEvent(byte[] analysisEvent) {
		return RuntimeEnvironmentSettings.writeObject(sendUsageEvent((AnalysisEvent) RuntimeEnvironmentSettings.readObject(analysisEvent)));
	}

	@SuppressWarnings("unchecked")
	@Override
	public byte[] sendMultipeEvents(byte[] analysisEvent) {
		return RuntimeEnvironmentSettings.writeObject(sendUsageLog((List<AnalysisEvent>) RuntimeEnvironmentSettings.readObject(analysisEvent)));
	}


}
