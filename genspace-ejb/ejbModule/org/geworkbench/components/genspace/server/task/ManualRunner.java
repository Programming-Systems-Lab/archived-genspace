package org.geworkbench.components.genspace.server.task;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Session Bean implementation class ManualRunner
 */
@Stateless
@WebService
public class ManualRunner implements ManualRunnerRemote {

    /**
     * Default constructor. 
     */
    public ManualRunner() {
        // TODO Auto-generated constructor stub
    }
//
//    @EJB
//    WorkflowStatisticsMaintainerLocal maintainer;
//    
//	@Override
	public void runWorkflowStats() {
//		maintainer.calculateToolUsage();
////		maintainer.calculateWorkflowUsage();
	}
	
	@EJB
	WorkflowStatisticsMaintainer maintainer;
	@WebMethod
	public void recalculateStats()
	{
		maintainer.refreshToolStatCache();
	}
@WebMethod
	public void fixWorkflows()
	{
//		maintainer.fixWorkflows();
	}
}
