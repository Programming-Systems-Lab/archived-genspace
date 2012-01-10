package org.geworkbench.components.genspace.server.task;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class ManualRunner
 */
@Stateless
@LocalBean
public class ManualRunner implements ManualRunnerRemote {

    /**
     * Default constructor. 
     */
    public ManualRunner() {
        // TODO Auto-generated constructor stub
    }

    @EJB
    WorkflowStatisticsMaintainerLocal maintainer;
    
	@Override
	public void runWorkflowStats() {
		maintainer.calculateToolUsage();
//		maintainer.calculateWorkflowUsage();
	}

}
