package org.geworkbench.components.genspace.server;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;


import org.geworkbench.components.genspace.entity.AnalysisEvent;
import org.geworkbench.components.genspace.entity.Tool;
import org.geworkbench.components.genspace.entity.Transaction;
import org.geworkbench.components.genspace.entity.User;
import org.geworkbench.components.genspace.entity.TasteUser;
import org.geworkbench.components.genspace.entity.Workflow;
import org.geworkbench.components.genspace.entity.WorkflowComment;

@Stateless
@WebService
public class ToolUsageInformation extends GenericUsageInformation{
	public ToolUsageInformation() {
		super(Tool.class);
	}

	@Override
	@WebMethod
	public List<WorkflowComment> getWFComments(Workflow w) {
		// TODO Auto-generated method stub
		return super.getWFComments(w);
	}
	
	@Override
	@WebMethod
	public List<Tool> getToolsByPopularity() {
		return super.getToolsByPopularity();
	}

	@Override
	@WebMethod
	public List<Workflow> getWorkflowsByPopularity() {
		return super.getWorkflowsByPopularity();
	}
	@WebMethod(exclude=true)
	@Override
	public byte[] getWorkflowsByPopularityBytes() {
		return super.getWorkflowsByPopularityBytes();
	}

	@Override
	@WebMethod
	public List<Tool> getMostPopularWFHeads() {
		return super.getMostPopularWFHeads();
	}

	@Override
	@WebMethod
	public Tool getMostPopularNextTool(int toolID) {
		return super.getMostPopularNextTool(toolID);
	}

	@Override
	@WebMethod
	public Tool getMostPopularPreviousTool(int toolId) {
		return super.getMostPopularPreviousTool(toolId);
	}

	@Override
	@WebMethod
	public List<Tool> getAllTools() {
		return super.getAllTools();
	}

	@Override
	@WebMethod
	public List<Workflow> getMostPopularWorkflowStartingWith(int toolId) {
		return super.getMostPopularWorkflowStartingWith(toolId);
	}

	@Override
	@WebMethod
	public List<Workflow> getMostPopularWorkflowIncluding(int toolId) {
		return super.getMostPopularWorkflowIncluding(toolId);
	}

	@Override
	@WebMethod
	public List<Workflow> getAllWorkflowsIncluding(int toolId) {
		return super.getAllWorkflowsIncluding(toolId);
	}

	@Override
	@WebMethod
	public List<Workflow> getToolSuggestion(int workflowID) {
		return super.getToolSuggestion(workflowID);
	}
	
	@Override
	@WebMethod
	public List<Workflow> getMahoutToolSuggestion(int userID, int filterMethod) {
		return super.getMahoutToolSuggestion(userID, filterMethod);
	}
	
	@Override
	@WebMethod
	public List<TasteUser> getMahoutUserSuggestion(int userID, int filterMethod) {
		return super.getMahoutUserSuggestion(userID, filterMethod);
	}
	
	@Override
	@WebMethod
	public List<Workflow> getMahoutUserWorkflowsSuggestion(int userID, int filterMethod) {
		return super.getMahoutUserWorkflowsSuggestion(userID, filterMethod);
	}
	
	@Override
	@WebMethod
	public List<Workflow> getMahoutSimilarWorkflowsSuggestion(List<Tool> tools) {
		return super.getMahoutSimilarWorkflowsSuggestion(tools);
	}
	
	@Override
	@WebMethod
	public void refreshMahoutRecommender() {
		super.refreshMahoutRecommender();
	}
	@Override
	@WebMethod
	public void analysisEventCompleted(Transaction transaction, String toolName) {
		// TODO Auto-generated method stub
		super.analysisEventCompleted(transaction, toolName);
	}
	@Override
	@WebMethod
	public Transaction popAnalysisFromTransaction(Transaction transaction, String toolName) {
		// TODO Auto-generated method stub
		return super.popAnalysisFromTransaction(transaction, toolName);
	}
	
	@WebMethod(exclude=true)
	@Override
	public byte[] sendUsageSingleEvent(byte[] analysisEvent) {
		return super.sendUsageSingleEvent(analysisEvent);
	}

	@WebMethod(exclude=true)
	@Override
	public byte[] sendMultipeEvents(byte[] analysisEvent) {
		return super.sendMultipeEvents(analysisEvent);
	}

	@Override
	@WebMethod
	public Transaction sendUsageEvent(AnalysisEvent e) {
		return super.sendUsageEvent(e);
	}

	@Override
	@WebMethod
	public Transaction sendUsageLog(List<AnalysisEvent> e) {
		return super.sendUsageLog(e);
	}

	@Override
	@WebMethod
	public User getExpertUserFor(int toolId) {
		return super.getExpertUserFor(toolId);
	}

}
