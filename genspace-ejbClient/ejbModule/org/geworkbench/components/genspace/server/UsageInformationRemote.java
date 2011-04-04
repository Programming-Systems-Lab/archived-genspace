package org.geworkbench.components.genspace.server;
import java.util.List;

import javax.ejb.Remote;

import org.geworkbench.components.genspace.entity.AnalysisEvent;
import org.geworkbench.components.genspace.entity.Tool;
import org.geworkbench.components.genspace.entity.ToolRating;
import org.geworkbench.components.genspace.entity.Transaction;
import org.geworkbench.components.genspace.entity.User;
import org.geworkbench.components.genspace.entity.Workflow;
import org.geworkbench.components.genspace.entity.WorkflowRating;

@Remote
public interface UsageInformationRemote  extends ToolInformationProvider {

	
	public ToolRating getMyRating(Tool tool);
	public Tool saveRating(ToolRating tr);
	public WorkflowRating getMyRating(Workflow workflow);
	public Workflow saveRating(WorkflowRating tr);
}