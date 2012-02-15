package org.geworkbench.components.genspace.server;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.annotation.Resource;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import javax.sql.DataSource;

import org.apache.mahout.cf.taste.impl.model.jdbc.ReloadFromJDBCDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.CachingRecommender;
import org.apache.mahout.cf.taste.impl.similarity.CachingItemSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.CachingUserSimilarity;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;


import org.eclipse.persistence.config.QueryHints;
import org.geworkbench.components.genspace.entity.AnalysisEvent;
import org.geworkbench.components.genspace.entity.AnalysisEventParameter;
import org.geworkbench.components.genspace.entity.TasteUser;
import org.geworkbench.components.genspace.entity.Tool;
import org.geworkbench.components.genspace.entity.ToolStatCache;
import org.geworkbench.components.genspace.entity.Transaction;
import org.geworkbench.components.genspace.entity.User;
import org.geworkbench.components.genspace.entity.Workflow;
import org.geworkbench.components.genspace.entity.WorkflowComment;
import org.geworkbench.components.genspace.entity.WorkflowTool;

import org.geworkbench.components.genspace.server.mahout.GenspaceItemRecommender;
import org.geworkbench.components.genspace.server.mahout.GenspaceItemSimilarity;
import org.geworkbench.components.genspace.server.mahout.GenspaceMySQLJDBCDataModel;
import org.geworkbench.components.genspace.server.mahout.GenspaceUserSimilarity;
import org.geworkbench.components.genspace.server.task.WorkflowStatisticsMaintainer;


public abstract class GenericUsageInformation extends AbstractFacade<Tool>
		implements ToolInformationProvider {

	@EJB
	MahoutRecommenderLocal mahoutBean;
	
	@EJB WorkflowStatisticsMaintainer statsMaintainer;
	/*
    @Schedule(minute="0/5",hour="*", persistent=false)
    public void doWork(){
        System.out.println("Refreshing: " + System.currentTimeMillis());
        mahoutBean.refresh();
    }
    */
	
	public GenericUsageInformation() {
		super(Tool.class);
	}

	public GenericUsageInformation(Class<Tool> T) {
		super(T);
	}
	@SuppressWarnings("unchecked")
	public List<Tool> getToolsByPopularity() {
		Query q = getEntityManager().createQuery(
				"select object(c) from Tool as c where c.replacedBy is null order by c.usageCount desc");
		List<Tool> r = q.getResultList();
		return r;

	}

	public List<WorkflowComment> getWFComments(Workflow w)
	{
		Workflow z = getEntityManager().find(Workflow.class, w.getId());
		return z.getComments();
	}
	
	public List<Workflow> getWorkflowsByPopularity() {
//		System.out.println("Getting wf by pop");
    	logUsage();

		Query cq = getEntityManager().createQuery("SELECT OBJECT(w) from Workflow w ORDER BY w.usageCount desc").setMaxResults(20); //.setHint(QueryHints.JDBC_FETCH_SIZE, 256).setHint("eclipselink.join-fetch", "w.tools").setHint("eclipselink.join-fetch", "w.parent").setHint("eclipselink.join-fetch", "w.children");
//		Query cq = getEntityManager().createNativeQuery("SELECT ID, CREATEDAT, NUMRATING, SUMRATING, USAGECOUNT, CREATIONTRANSACTION_ID, CREATOR_ID, PARENT_ID from WORKFLOW w ORDER BY w.USAGECOUNT desc LIMIT 20",
//				Workflow.class);//.setHint(QueryHints.JDBC_FETCH_SIZE, 256).setHint("eclipselink.join-fetch", "w.creationTransaction");
		@SuppressWarnings("rawtypes")
		List r = cq.getResultList();
		List<Workflow> wr = new ArrayList<Workflow>();
//		System.out.println("queried");
		for (Object o : r) {
			wr.add(((Workflow) o).slimDown());
		}
//		System.out.println("returning");
		return wr;
	}

	@SuppressWarnings("unchecked")
	public List<Tool> getMostPopularWFHeads() {
    	logUsage();

		Query q = getEntityManager().createQuery(
				"select object(c) from Tool as c where c.replacedBy is null order by c.wfCountHead desc");
		List<Tool> r = q.getResultList();
		return r;
	}

	public Tool getMostPopularNextTool(int id) {
    	logUsage();
		return getToolStats(id).getMostPopularNext();
	}
	private ToolStatCache getToolStats(int tool)
	{
		ToolStatCache c;
		try{
			c = getEntityManager().find(ToolStatCache.class, tool);
		}
		catch(Exception ex)
		{
			c = statsMaintainer.calculateToolStats(tool);
		}
		GregorianCalendar tenDaysAgo = new GregorianCalendar();
		tenDaysAgo.add(Calendar.DATE, -10);
		if(c == null || c.getUpdated().before(tenDaysAgo.getTime()))
		{
			c = statsMaintainer.calculateToolStats(tool);
		}
		return c;
	}
	public Tool getMostPopularPreviousTool(int tool) {
    	logUsage();
		return getToolStats(tool).getMostPopularBefore();
	}

	@Override
	@PermitAll
	public List<Tool> getAllTools() {
//		System.out.println("Caleld get all tools");
    	logUsage();

		Query q = getEntityManager().createQuery(
				"select object(c) from Tool as c where c.replacedBy is null order by c.name asc");
		@SuppressWarnings("unchecked")
		List<Tool> r = q.getResultList();
		return r;
	}
	
	private List<Workflow> fixParentChildrenCaches(List<Workflow> in)
	{
		HashMap<Integer, Workflow> wkflws = new HashMap<Integer, Workflow>();
		
		for(Workflow w : in)
		{
			wkflws.put(w.getId(), w);
			w.setCachedChildrenCount(0);
		}
		
		for(Workflow w : in)
		{
			if(wkflws.containsKey(w.getCachedParentId()))
			{
				wkflws.get(w.getCachedParentId()).setCachedChildrenCount(wkflws.get(w.getCachedParentId()).getCachedChildrenCount() + 1);
			}
			else
				w.setCachedParentId(-1);
		}
		return in;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Workflow> getAllWorkflowsIncluding(int tool) {
    	logUsage();

//		Query q = getEntityManager().createQuery("SELECT DISTINCT w,w.parent.id FROM Workflow w, WorkflowTool wt WHERE wt MEMBER OF w.tools AND wt.tool=?1 ORDER BY w.usageCount DESC",Object[].class).
//		setMaxResults(150);//.setHint(QueryHints.CACHE_USAGE, CacheUsage.DoNotCheckCache).setHint(QueryHints.QUERY_TYPE, QueryType.ReadAll);//.setHint("eclipselink.join-fetch", "w.children");//.setHint("eclipselink.join-fetch", "w.tools").setHint("eclipselink.join-fetch", "w.parent").setHint("eclipselink.join-fetch", "w.children");
		
		Query q = getEntityManager().createNativeQuery(
				"select distinct w.* from WORKFLOW w "
						+ "inner join WORKFLOWTOOL wt on w.id=wt.workflow_id "
						+ "where wt.tool_id=? order by w.usageCount desc LIMIT 150",
				Workflow.class);//.setHint(QueryHints.JDBC_FETCH_SIZE, 256).setHint("eclipselink.join-fetch", "w.tools").setHint("eclipselink.join-fetch", "w.parent").setHint("eclipselink.join-fetch", "w.children");;
		q.setParameter(1, tool);
		List<Workflow> wf = null;
		ArrayList<Workflow> ret = new ArrayList<Workflow>();
		try {
			wf = q.getResultList();
			for (Workflow o : wf) {
				Workflow w = o;
//				w.setCachedParentId(w.getParent().getId());
				ret.add(w.slimDownTiny());
			}
		} catch (NoResultException e) {
		}

//		System.out.println("Returning " + ret.size());
		return fixParentChildrenCaches(ret);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Workflow> getMostPopularWorkflowStartingWith(int tool) {
		
    	logUsage();

		Query q = getEntityManager()
				.createNativeQuery(
						"select distinct w.* from WORKFLOW w "
								+ "inner join WORKFLOWTOOL wt on w.id=wt.workflow_id "
								+ "where wt.tool_id=? and wt.cardinality=1 order by w.usageCount desc limit 1",
						Workflow.class);
		q.setParameter(1, tool);
		List<Workflow> wf = null;
		ArrayList<Workflow> ret = new ArrayList<Workflow>();
		try {
			wf = q.getResultList();
			for (Workflow w : wf) {
				ret.add(w.slimDown());
			}
		} catch (NoResultException e) {

		}
		return ret;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Workflow> getMostPopularWorkflowIncluding(int tool) {
    	logUsage();

//		System.out.println("Starting to get most popular workflow including "
//				+ tool);
		Query q = getEntityManager()
				.createNativeQuery(
						"select distinct w.* from WORKFLOW w "
								+ "inner join WORKFLOWTOOL wt on w.id=wt.workflow_id "
								+ "where wt.tool_id=? order by w.usageCount desc limit 1",
						Workflow.class).setHint(QueryHints.JDBC_FETCH_SIZE, 256);
		q.setParameter(1, tool);
		List<Workflow> wf = null;
		ArrayList<Workflow> ret = new ArrayList<Workflow>();
		try {
			wf = q.getResultList();
			for (Workflow w : wf) {
				ret.add(w.slimDown());
			}
		} catch (NoResultException e) {

		}
		return ret;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Workflow> getToolSuggestion(int cwf) {
    	logUsage();

		Query q = getEntityManager().createNativeQuery(
				"select distinct w.* from WORKFLOW w "
						+ "where w.parent_id=? order by w.usageCount desc",
				Workflow.class);
		q.setParameter(1, cwf);
		List<Workflow> wf = null;
		List<Workflow> ret = new ArrayList<Workflow>();
		try {
			wf = q.getResultList();
			for (Workflow w : wf)
				ret.add(w.slimDown());
		} catch (NoResultException e) {

		}
		return ret;
	}
	
	// Added by efedotov
	public List<Workflow> getMahoutToolSuggestion(int userId, int filterMethod) {
		return mahoutBean.getToolSuggestions(userId, filterMethod);
	}
	
	// Added by efedotov
	public List<TasteUser> getMahoutUserSuggestion(int userId, int filterMethod) {
		return mahoutBean.getUserSuggestions(userId, filterMethod);
	}
	
	// Added by efedotov
	@Override
	public List<Workflow> getMahoutSimilarWorkflowsSuggestion(List<Tool> tools) {
		
		return mahoutBean.getSimilarWorkflows(tools);
	}
	
	// Added by efedotov
	@Override
	public List<Workflow> getMahoutUserWorkflowsSuggestion(int userID, int filterMethod) {
		return mahoutBean.getSimilarUserWorkflows(userID, filterMethod);
	}
	
	// Added by efedotov
	@Override
	public void refreshMahoutRecommender() {
		mahoutBean.refresh();
	}

	@Override
	public Transaction sendUsageLog(List<AnalysisEvent> e) {
    	logUsage();

		HashMap<String, Transaction> trans = new HashMap<String, Transaction>();
		Transaction ret = null;
		for (AnalysisEvent ev : e) {
			Transaction t;
			try
			{
				Integer.valueOf(ev.getTransaction().getClientID());
				ev.getTransaction().setClientID(ev.getTransaction().getUserName()+ev.getTransaction().getHostname()+ev.getTransaction().getClientID());
			}
			catch(NumberFormatException ex)
			{
				//do nothing, it's OK as is
			}
			if (!trans.containsKey(ev.getTransaction().getClientID())) {
				ev.getTransaction().setUser(null);
				t = createOrFindTransaction(ev.getTransaction());
				trans.put(ev.getTransaction().getClientID(), t);
			}
			t = trans.get(ev.getTransaction().getClientID());
			ev.setTransaction(t);
			ret = handleSingleUsageEvent(ev);
		}
		return ret;
	}

	@Override
	public Transaction sendUsageEvent(AnalysisEvent e) {
		e.setCreatedAt(new Date());
//		System.out.println(">>>Incoming tool: " + e.getTool() + " -> " + e.getToolname());
		e.setTransaction(createOrFindTransaction(e.getTransaction()));
		return handleSingleUsageEvent(e);
	}

	private Transaction handleSingleUsageEvent(AnalysisEvent e) {
//		System.out.println(e.getTool() + " -> " + e.getToolname());
		if (e.getTool() == null || !getEntityManager().contains(e.getTool())) {
			e.setTool(getToolByName(e.getToolname()));
			if (e.getTool() == null) {
				// Create the tool
				Tool tool = new Tool();
				tool.setName(e.getToolname());
				tool.setUsageCount(0);
				getEntityManager().persist(tool);
				getEntityManager().merge(tool);
				e.setTool(tool);
				getEntityManager().flush();
			}
		}
		if(e.getTool().getReplacedBy() != null)
			e.setTool(e.getTool().getReplacedBy());
		if(e.getParameters() != null)
			for(AnalysisEventParameter p : e.getParameters())
			{
				if(p.getParameterValue().length() > 1500)
				{
					System.out.println("Overflow parameter value: " + p.getParameterValue());
					System.out.println("Source event was " + e.getTool().getName());
					p.setParameterValue("Overflowed");
				}
			}
		getEntityManager().persist(e);
		if(e.getParameters() != null)
			for(AnalysisEventParameter p : e.getParameters())
			{
				try
				{
				p.setEvent(e);
				getEntityManager().merge(p);
				}
				catch(Exception ex)
				{
					System.out.println("Overflow parameter value: " + p.getParameterValue());
					System.out.println("Source event was " + e.getTool().getName());
					p.setParameterValue("Overflowed");
					getEntityManager().merge(p);
				}
			}
		// Update the workflow for the transaction
		e.getTransaction().setWorkflow(
				getExtendedWorkflow(e.getTransaction(), e.getTool()));

		e.getTransaction()
				.getWorkflow()
				.setUsageCount(
						e.getTransaction().getWorkflow().getUsageCount() + 1);

		e.getTool().setUsageCount(e.getTool().getUsageCount() + 1);
		

//		e.getTransaction().setIpAddr(ctx.getUs);
		getEntityManager().merge(e.getTool());
		getEntityManager().merge(e.getTransaction());
		getEntityManager().persist(e);
		getEntityManager().flush();
		if(getUser() != null && getUser().getLogData() == 1)
			e.getTransaction().setUser(null);
		getEntityManager().merge(e.getTransaction());

		for (WorkflowTool t : e.getTransaction().getWorkflow().getTools()) {
			t.getTool();
		}
		e.getTransaction().getWorkflow().setParent(null);
		e.getTransaction().getWorkflow().setChildren(new ArrayList<Workflow>());
		return e.getTransaction();
	}

	private Tool getToolByName(String toolname) {
		Query q = getEntityManager().createNativeQuery(
				"select c.* from Tool as c where c.name=? COLLATE latin1_bin",
				Tool.class); // and c.user=:user
		q.setParameter(1, toolname);
		// q.setParameter("user", user);
		Tool r = null;
		try {
			r = (Tool) q.getSingleResult();
			if(r.getReplacedBy() != null)
				return r.getReplacedBy();
		} catch (NoResultException e) {

		}
		return r;
	}

	/**
	 * Returns a workflow that is based on the given workflow, PLUS the tool
	 * specified Creates the workflow if it doesn't exist already
	 * 
	 * @param workflow
	 * @param tool
	 * @return
	 */
	private Workflow getExtendedWorkflow(Transaction t, Tool tool) {
		if (t.getWorkflow() == null) {
			Query q = getEntityManager()
					.createNativeQuery(
							"select w.* from WORKFLOW w "
									+ "inner join WORKFLOWTOOL wt on wt.workflow_id=w.id "
									+ "where w.parent_id is null and wt.cardinality=1 and wt.tool_id=? limit 1",
							Workflow.class);
			q.setParameter(1, tool.getId());
			Workflow r = null;
			try {
				r = (Workflow) q.getSingleResult();
				return r;
			} catch (NoResultException e) {
				return createNewWorkflowForTrans(t, tool);
			}
		}
		Query q = getEntityManager()
				.createNativeQuery(
						"select w.* from WORKFLOW w "
								+ "inner join WORKFLOWTOOL wt on wt.workflow_id=w.id "
								+ "where w.parent_id=? and wt.cardinality=? and wt.tool_id=? limit 1",
						Workflow.class);
		q.setParameter(1, t.getWorkflow().getId());
		q.setParameter(2, t.getWorkflow().getTools().size() + 1);
		q.setParameter(3, tool.getId());
		Workflow r = null;
		try {
			r = (Workflow) q.getSingleResult();
			return r;
		} catch (NoResultException e) {
			return createNewWorkflowForTrans(t, tool);
		}

	}

	private Workflow createNewWorkflowForTrans(Transaction trans, Tool tool) {
		// Need to create the workflow
		Workflow r = new Workflow();
		r.setParent(trans.getWorkflow());
		r.setCreatedAt(new Date());
		r.setCreationTransaction(trans);
		r.setUsageCount(0);
		r.setCreator(trans.getUser());
		List<WorkflowTool> newTools = new ArrayList<WorkflowTool>();
		if (trans.getWorkflow() != null
				&& trans.getWorkflow().getTools() != null)
			for (WorkflowTool to : trans.getWorkflow().getTools()) {
				WorkflowTool nt = new WorkflowTool();
				nt.setOrder(to.getOrder());
				nt.setTool(to.getTool());
				nt.setWorkflow(r);
				getEntityManager().persist(nt);
				newTools.add(nt);
			}
		WorkflowTool nt = new WorkflowTool();
		nt.setOrder(newTools.size() + 1);
		nt.setTool(tool);
		nt.setWorkflow(r);
		getEntityManager().persist(nt);	
		newTools.add(nt);
		r.setTools(newTools);
		getEntityManager().persist(r);
		return r;
	}

	

	    
	private Transaction createOrFindTransaction(Transaction t) {
		Transaction r = findTransactionByName(t.getClientID(), t.getUser());
		if (r == null) {
			r = t;
			r.setIpAddr(getRemoteIP());
			getEntityManager().persist(r);
		}
		return r;
	}

	private Transaction findTransactionByName(String name, User user) {
		Query q;
//		System.out.println("Checking user " + user + " clientid <" +name+">");
		if (user == null)
		{
			q = getEntityManager()
					.createQuery(
							"select object(c) from Transaction as c where c.clientID=:clientid and c.user is null").setMaxResults(1);
//			System.out.println("Looking for anon transaction " + name);
		}
		else {
			q = getEntityManager()
					.createQuery(
							"select object(c) from Transaction as c where c.clientID=:clientid and c.user=:user").setMaxResults(1); //
//			System.out.println("Looking for non-anon trans " + name + " for " + user );
			q.setParameter("user", user);
		}
		q.setParameter("clientid", name);

		Transaction r = null;
		try {
			r = (Transaction) q.getSingleResult();
		} catch (NoResultException e) {

		}
//		System.out.println("using Trans " + r);
		return r;
	}

	@Override
	public User getExpertUserFor(int tn) {
//System.out.println("Getting expert");
    	logUsage();

		Query q;
		q = getEntityManager()
				.createNativeQuery(
						"select r.* from registration r "
								+ "inner join `TRANSACTION` t on t.user_id=r.id "
								+ "inner join ANALYSISEVENT e on e.transaction_id=t.id "
								+ "where e.tool_id="
								+ tn
								+ " group by r.id order by count(*) DESC LIMIT 1",
						User.class);
		User r = null;
		try {
			r = (User) q.getSingleResult();
			r.loadVisibility(getUser());
		} catch (Exception e) {
		}
		return r;
	}

	@Override
	public byte[] sendUsageSingleEvent(byte[] analysisEvent) {
		return AbstractFacade
				.writeObject(sendUsageEvent((AnalysisEvent) AbstractFacade
						.readObject(analysisEvent)));
	}

	@SuppressWarnings("unchecked")
	@Override
	public byte[] sendMultipeEvents(byte[] analysisEvent) {
		return AbstractFacade
				.writeObject(sendUsageLog((List<AnalysisEvent>) AbstractFacade
						.readObject(analysisEvent)));
	}
	@Override
	public byte[] getWorkflowsByPopularityBytes() {
		return AbstractFacade.writeObject(getWorkflowsByPopularity());
	}
}
