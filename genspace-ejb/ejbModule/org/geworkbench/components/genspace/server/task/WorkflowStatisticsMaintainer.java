package org.geworkbench.components.genspace.server.task;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.geworkbench.components.genspace.entity.AnalysisEvent;
import org.geworkbench.components.genspace.entity.Network;
import org.geworkbench.components.genspace.entity.Tool;
import org.geworkbench.components.genspace.entity.Transaction;
import org.geworkbench.components.genspace.entity.UserNetwork;
import org.geworkbench.components.genspace.entity.Workflow;
import org.geworkbench.components.genspace.entity.WorkflowTool;

/**
 * Session Bean implementation class WorkflowStatisticsMaintainer
 */
@Stateful 
public class WorkflowStatisticsMaintainer implements WorkflowStatisticsMaintainerLocal {
    @PersistenceContext(unitName="genspace_persist") private EntityManager em;

    /**
     * Default constructor. 
     */
    public WorkflowStatisticsMaintainer() {
        System.out.println("Init statistics maintainer");
    }

	@Override
	public void calculateWorkflowUsage() {
		
	}

	private void reverseEngineerWorkflows()
	{
		Query q = em.createQuery("select object(c) from Transaction as c");
		List<Transaction> trans = q.getResultList();
		int zz =0;
		for(Transaction t : trans)
		{
			System.out.println("Trans starting " + t.getId());
			Workflow last_w = null;
			int i = 1;
			ArrayList<Tool> tools = new ArrayList<Tool>();
			for(AnalysisEvent e : t.getAnalysisEvents())
			{
				tools.add(e.getTool());
				
				Query q2 = em.createNativeQuery("select w.* from WORKFLOW as w " +
						"inner join WORKFLOWTOOL as wt on wt.workflow_id=w.id " +
						"where "+(last_w == null ? "w.parent_id is null" : "w.parent_id = " + last_w.getId()) + " and wt.cardinality=? and wt.tool_id=? ",Workflow.class);
				q2.setParameter(1, i);
				q2.setParameter(2, e.getTool().getId());
				try
				{
					last_w = (Workflow) q2.getSingleResult();
					i++;
				}
				catch(NoResultException ex)
				{
					Workflow w = new Workflow();
					w.setParent(last_w);
					w.setCreationTransaction(t);
					w.setUsageCount(0);
					List<WorkflowTool> wfTools = new ArrayList<WorkflowTool>();
					int k = 1;
					for(Tool to: tools)
					{
						WorkflowTool nwt = new WorkflowTool();
						nwt.setTool(to);
						nwt.setOrder(k);
						nwt.setWorkflow(w);
						em.persist(nwt);
						k++;
					}
					w.setTools(wfTools);
					em.persist(w);
					last_w = w; 
					//asdf 
					i++;
				}
			} 
			System.out.println("Trans " + t.getId() +" -> "+last_w.getId());
			t.setWorkflow(last_w);
			last_w.setUsageCount(last_w.getUsageCount() + 1);
			em.merge(last_w);
			em.merge(t);
			zz++;
		}
	}
	@Override
	public void calculateToolUsage() {
//		Query q = em.createQuery("select object(c) from AnalysisEvent as c");
//		List<AnalysisEvent> trans = q.getResultList();
//		int zz =0;
//		for(AnalysisEvent e : trans)
//		{
//			Tool t = e.getTool();
//			t.setUsageCount(t.getUsageCount() + 1);
//			em.merge(t);
//		}
		
		Query q = em.createQuery("select object(c) from AnalysisEvent as c where c.transaction.workflow.parent is null");
		List<AnalysisEvent> trans = q.getResultList();
		int zz =0;
		for(AnalysisEvent e : trans)
		{
			Tool t = e.getTool();
			t.setWfCountHead(t.getWfCountHead()+ 1);
			em.merge(t);
		}
	}

}
 