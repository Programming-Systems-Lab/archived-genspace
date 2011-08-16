package org.geworkbench.components.genspace.server;
import java.util.List;

import javax.ejb.Remote;

import org.geworkbench.components.genspace.entity.TasteUser;
import org.geworkbench.components.genspace.entity.Tool;
import org.geworkbench.components.genspace.entity.Workflow;

@Remote
public interface MahoutRecommenderRemote {
	
	void refresh();
	public List<Workflow> getToolSuggestions(int userId, int filterMethod);
	public List<TasteUser> getUserSuggestions(int userId, int filterMethod);
	public List<Workflow> getSimilarWorkflows(List<Tool> tools);
	public List<Workflow> getSimilarUserWorkflows(int userId, int filterMethod);
	public List<Workflow> getToolsSuggestionsForUser(int userId, int filterMethod);

}
