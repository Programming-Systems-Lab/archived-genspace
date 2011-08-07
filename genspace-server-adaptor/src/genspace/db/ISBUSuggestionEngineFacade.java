package genspace.db;

import genspace.GenSpaceServerFactory;
import genspace.wrapper.WorkflowWrapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.geworkbench.components.genspace.server.stubs.Tool;
import org.geworkbench.components.genspace.server.stubs.Workflow;
import org.geworkbench.components.genspace.server.stubs.WorkflowTool;

public class ISBUSuggestionEngineFacade implements ISBUSuggestionEngine {

	@Override
	public void engineInitialization() throws Exception {
		// TODO Auto-generated method stub
		
	}
	HashMap<String, Tool> toolsMap;
	public ISBUSuggestionEngineFacade() {
		toolsMap = new HashMap<String, Tool>();
		for(Tool t : GenSpaceServerFactory.getUsageOps().getAllTools())
		{
			toolsMap.put(t.getName(), t);
		}
		
	}
	@Override
	public ArrayList<String> getAllAnalysisTools1() {
		ArrayList<String> ret = new ArrayList<String>();
		ret.addAll(toolsMap.keySet());
		return ret;
	}

	@Override
	public ArrayList<String> getTop3MostPopularTools() {
		List<Tool> all = GenSpaceServerFactory.getUsageOps().getToolsByPopularity();
		
		ArrayList<String> ret = new ArrayList<String>();
		ret.add(all.get(0).getName());
		ret.add(all.get(1).getName());
		ret.add(all.get(2).getName());
		return ret;
	}

	@Override
	public ArrayList<String> getTop3MostPopularWFHead() {
		List<Tool> all = GenSpaceServerFactory.getUsageOps().getMostPopularWFHeads();
		
		ArrayList<String> ret = new ArrayList<String>();
		ret.add(all.get(0).getName());
		ret.add(all.get(1).getName());
		ret.add(all.get(2).getName());
		return ret;
	}
	public static String wfToStr(Workflow w)
	{
		String r = "";
		WorkflowWrapper wr = new WorkflowWrapper(w);
		wr.loadToolsFromCache();
		int i = 0;
		for(WorkflowTool t : wr.getTools())
		{
			r += t.getTool().getName();
			if(i < wr.getTools().size() -1)
				r += ",";
			i++;
		}
		
		return r;
	}
	
	@Override
	public ArrayList<String> getTop3MostPopularWF() {
		List<Workflow> all = GenSpaceServerFactory.getUsageOps().getWorkflowsByPopularity();
		
		ArrayList<String> ret = new ArrayList<String>();
		ret.add(wfToStr(all.get(0)));
		ret.add(wfToStr(all.get(1)));
		ret.add(wfToStr(all.get(2)));
		return ret;
	}

	@Override
	public String getUsageRate(String toolName) {
		return "" + toolsMap.get(toolName).getUsageCount();
	}

	@Override
	public String getUsageRateAsWFHead(String toolName) {
		return "" + toolsMap.get(toolName).getWfCountHead();
	}

	@Override
	public String getMostPopularNextTool(String toolName) {
		return GenSpaceServerFactory.getUsageOps().getMostPopularNextTool(toolsMap.get(toolName).getId()).getName();
	}

	@Override
	public String getMostPopularPreviousTool(String toolName) {
		return GenSpaceServerFactory.getUsageOps().getMostPopularPreviousTool(toolsMap.get(toolName).getId()).getName();
	}

	@Override
	public ArrayList<String> getTop3MostPopularWFForThisTool(String toolName) {
		List<Workflow> all = GenSpaceServerFactory.getUsageOps().getMostPopularWorkflowIncluding(toolsMap.get(toolName).getId());
		
		ArrayList<String> ret = new ArrayList<String>();
		ret.add(wfToStr(all.get(0)));
		ret.add(wfToStr(all.get(1)));
		ret.add(wfToStr(all.get(2)));
		return ret;
	}

	@Override
	public HashSet<String> getAllWFsIncludingThisTool(String toolName) {
		List<Workflow> all = GenSpaceServerFactory.getUsageOps().getAllWorkflowsIncluding(toolsMap.get(toolName).getId());
		
		HashSet<String> ret = new HashSet<String>();
		for(Workflow w : all)
			ret.add(wfToStr(w));
		return ret;
	}

	@Override
	public String getMostCommonWFIncludingThisTool(String toolName) {
		 List<Workflow> w = GenSpaceServerFactory.getUsageOps().getMostPopularWorkflowIncluding(toolsMap.get(toolName).getId());
		return wfToStr(w.get(0));
	}

	@Override
	public String getMostCommonWFStartingWithThisTool(String toolName) {
		List<Workflow> w = GenSpaceServerFactory.getUsageOps().getMostPopularWorkflowStartingWith(toolsMap.get(toolName).getId());
		return wfToStr(w.get(0));
	}

	@Override
	public ArrayList<String> getListOfUserNames(String current_user) {
		return null;
	}

	@Override
	public ArrayList<String> getListOfNetworks(String current_user) {

		return null;
	}

	@Override
	public ArrayList<String> getUserCount() {
		return null;
	}

	@Override
	public boolean insertIntoMessagebox(String fromUser, String toUser,
			String message) {
		return false;
	}

	@Override
	public boolean insertIntoGroupMessagebox(String fromUser,
			String networkName, String message) {
		return false;
	}

	@Override
	public ArrayList<String> getAllInboxMsgForCurrentUser(String currentUserId) {
		return null;
	}

	@Override
	public boolean deleteFromInbox(String messageID) {
		return false;
	}

	@Override
	public ArrayList<String> getAllOutboxMsgForCurrentUser(String currentUserId) {
		return null;
	}

	@Override
	public boolean deleteFromOutbox(String messageID) {
		return false;
	}

	@Override
	public boolean insertIntoWFRatings(String identifier, String rating,
			String user) {
		return false;
	}

	@Override
	public boolean insertIntoToolsRatings(String identifier, String rating,
			String user) {
		return false;
	}

	@Override
	public ArrayList<String> getAllWFRating(String id, String username) {
		return null;
	}

	@Override
	public ArrayList<String> getAllToolsRating(String id, String username) {
		return null;
	}

	@Override
	public ArrayList<String> getNetworkVisibility(String username) {
		return null;
	}

	@Override
	public ArrayList<String> getAllRatingsForCurrentUser(String currentUserId) {
		return null;
	}

	@Override
	public ArrayList getRealTimeWorkFlowSuggestion(String cwfString) {
		ArrayList ret = new ArrayList();
		ret.add("Please upgrade geWorkbench to utilize this feature");
		return ret;
	}

}
