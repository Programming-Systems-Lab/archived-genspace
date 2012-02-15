package org.geworkbench.components.genspace.server.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

import org.eclipse.persistence.exceptions.DatabaseException;
import org.geworkbench.components.genspace.entity.AnalysisEvent;
import org.geworkbench.components.genspace.entity.Network;
import org.geworkbench.components.genspace.entity.Tool;
import org.geworkbench.components.genspace.entity.ToolStatCache;
import org.geworkbench.components.genspace.entity.Transaction;
import org.geworkbench.components.genspace.entity.UserNetwork;
import org.geworkbench.components.genspace.entity.Workflow;
import org.geworkbench.components.genspace.entity.WorkflowTool;

/**
 * Session Bean implementation class WorkflowStatisticsMaintainer
 */
@Stateful 
@LocalBean
public class WorkflowStatisticsMaintainer {
    @PersistenceContext(unitName="genspace_persist") private EntityManager em;

    
    public void fixWorkflows()
    {
    	Query  q= em.createNativeQuery("SELECT w.* from WORKFLOW w WHERE length >12 and toolhash is not null  order by length asc",Workflow.class);
    	
		List<Workflow> res = q.getResultList();
		int i = 0;
		for(Workflow t : res)
		{
//			validateTools(t);
			setParent(t);
			i++;
			if(i> 2000)
				return;
		}
    }
    private void setParent(Workflow w)
    {
		em.getEntityManagerFactory().getCache().evictAll();

    	if(w.getTools().size() == 1)
    		return;
    	ArrayList<Tool> tools = new ArrayList<Tool>(w.getTools().size()+1);
    	for(WorkflowTool t : w.getTools())
    		tools.add(t.getOrder()-1, t.getTool());
    	
    	Workflow prev = null;
    	
		System.out.println(w.getId() + "(" + w.getTools().size()+")");

		
    	for(int i = 0; i<w.getTools().size()-1;i++)
    	{
    		if(prev == null && i > 0)
    			break;
    		Query q = em
    				.createNativeQuery(
    						"select w.* from WORKFLOW w " +
    						"inner join WORKFLOWTOOL wt on wt.workflow_id=w.id " +
    						"where wt.tool_id=? and wt.cardinality=? and w.length=?" + (prev == null ? "" : " and w.parent_id="+prev.getId()),
    						Workflow.class);
    		q.setParameter(1,tools.get(i).getId());
    		q.setParameter(2,i+1);
    		q.setParameter(3,i+1);
//    		System.out.println("select w.* from WORKFLOW w " +
//    						"inner join WORKFLOWTOOL wt on wt.workflow_id=w.id " +
//    						"where wt.tool_id="+tools.get(i).getId()+" and wt.cardinality="+(i+1)+" and w.length="+(i+1)+ (prev == null ? "" : " and w.parent_id="+prev.getId()));
    		Workflow r = null;
    		try {
    			r = (Workflow) q.getSingleResult();
    		} catch (NoResultException e) {
    			System.out.println("broken parent for " + w.getId());
    		}
    		prev = r;
    	}
    	w.setParent(prev);
    	em.merge(w);
    }
    private void validateTools(Workflow t) {
		em.getEntityManagerFactory().getCache().evictAll();

		int m = t.getTools().size();
    	System.out.println(t.getId() + " has size " + m);
		boolean[] valid = new boolean[m+100];
		for(int i = 0;i<m;i++)
			valid[i]= false;
		Query  q= em.createNativeQuery("SELECT w.* from WORKFLOWTOOL w where workflow_id="+t.getId()+" ORDER BY CARDINALITY ASC;",WorkflowTool.class);
    	
		List<WorkflowTool> tools = q.getResultList();
		
		for(WorkflowTool wt : tools)
		{
			valid[wt.getOrder()-1]=true;
		}
		for(int i = 0;i<m;i++)
		{
			if(valid[i] == false)
			{
				i++;
//				System.out.println("Fixing slot " + (i+1) + "  on " + t.getId());
				for(WorkflowTool wt : tools)
				{
					if(wt.getOrder() > i)
					{
						System.out.println(wt.getOrder() + " to " + (wt.getOrder()-1));
//						System.out.println("UPDATE workflowtool set cardinality=cardinality-1 WHERE id="+wt.getId());
						q = em.createNativeQuery("UPDATE workflowtool set cardinality=cardinality-1 WHERE id="+wt.getId());
						try{
						q.executeUpdate();
						}
						catch(DatabaseException ex)
						{
							System.out.println(ex.getMessage());
							return;
						}
//						wt.setOrder(wt.getOrder()-1);
//						em.merge(wt);
					}
				}
				em.flush();
				em.clear();
				validateTools(t);
				break;
			}
		}
	}

	/**
     * Default constructor. 
     */
    public WorkflowStatisticsMaintainer() {

    }
    public void refreshToolStatCache()
    {
    	CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
		cq.select(cq.from(Tool.class));
		List<Tool> res = em.createQuery(cq).getResultList();
		for(Tool t : res)
			calculateToolStats(t.getId());
		
    }
	public ToolStatCache calculateToolStats(int t)
	{
		ToolStatCache c = null;
		try
		{ 
			c = em.find(ToolStatCache.class, t);
		}
		catch(Exception e)
		{
			
		}
		if(c == null)
		{
			c = new ToolStatCache();
			c.setToolid(t);
			em.persist(c);
		}
		Query q = em
				.createNativeQuery(
						"select t.* from TOOL t " +
						"inner join WORKFLOWTOOL wt on wt.tool_id=t.id " +
						"inner join WORKFLOWTOOL wt2 on wt2.workflow_id=wt.workflow_id and wt2.cardinality = wt.cardinality-1 " +
						"inner join WORKFLOW w on w.id=wt.workflow_id " +
						"where wt2.tool_id=? and t.replacedby_id is null group by t.id order by SUM(w.usagecount) limit 1",
						Tool.class);
		q.setParameter(1, t);
		Tool r = null;
		try {
			r = (Tool) q.getSingleResult();
		} catch (NoResultException e) {

		}
		c.setMostPopularNext(r);
		
		q = em.createNativeQuery(
						"select t.* from TOOL t " +
						"inner join WORKFLOWTOOL wt on wt.tool_id=t.id " +
						"inner join WORKFLOWTOOL wt2 on wt2.workflow_id=wt.workflow_id and wt2.cardinality = wt.cardinality+1 " +
						"inner join WORKFLOW w on w.id=wt.workflow_id " +
						"where wt2.tool_id=? and t.replacedby_id is null group by t.id order by SUM(w.usagecount) limit 1",
						Tool.class);
		q.setParameter(1, t);
		r = null;
		try {
			r = (Tool) q.getSingleResult();
		} catch (NoResultException e) {

		}
		c.setMostPopularBefore(r);
		c.setUpdated(new Date());
		em.merge(c);
		return c;
	}

}
 