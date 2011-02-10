package org.geworkbench.components.genspace.server;

import java.util.List;

import javax.ejb.Local;

import org.geworkbench.components.genspace.entity.AnalysisEvent;
import org.geworkbench.components.genspace.entity.Tool;
import org.geworkbench.components.genspace.entity.Transaction;
import org.geworkbench.components.genspace.entity.User;
import org.geworkbench.components.genspace.entity.Workflow;

@Local
public interface ToolInformationLocal {
	public List<Tool> getToolsByPopularity(User u);

	public List<Workflow> getWorkflowsByPopularity(User u);

	public List<Tool> getMostPopularWFHeads(User u);

	public Tool getMostPopularNextTool(User u,Tool tool);

	public Tool getMostPopularPreviousTool(User u,Tool tool);
	public List<Tool> getAllTools();

	public List<Workflow> getAllWorkflowsIncluding(User myUser, Tool tool);

	public List<Workflow> getMostPopularWorkflowStartingWith(User myUser,
			Tool tool);

	public List<Workflow> getMostPopularWorkflowIncluding(User myUser, Tool tool);

	public List<Workflow> getToolSuggestion(User myUser, Workflow cwf);

	public Transaction logUsageEvent(User myUser, AnalysisEvent e);
}
