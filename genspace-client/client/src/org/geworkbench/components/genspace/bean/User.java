package org.geworkbench.components.genspace.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.tree.DefaultMutableTreeNode;

import org.geworkbench.components.genspace.workflowRepository.WorkflowNode;

public class User implements Serializable{
	
	/**
	 * TODO: Some attributes defined in dbo.Registration are not considered and loaded from the DB right now
	 */
	public String username;
	public String password;
	public String email;
	public ArrayList<WorkflowInbox> inbox = new ArrayList<WorkflowInbox>();
	public ArrayList<UserWorkflow> workflows = new ArrayList<UserWorkflow>();
	public ArrayList<String> folders = new ArrayList<String>();
	
	@Override
	public String toString(){
		String result = "User - username: "+username+"\n";
		result += "UserWorkflows: "+workflows.size()+"\n";
		for(UserWorkflow uw: workflows){
			result += uw.toString()+"\n";
		}
		System.out.println(result);
		result += "Inbox: "+inbox.size()+"\n";
		for(WorkflowInbox wi: inbox){
			result +=  wi.toString()+"\n";
		}
		return result;
	}

	//NOT USED NOW, REPLACED BY addUserWorkflowTree
	public WorkflowNode getUserWorkflowTree() {
    	UserWorkflow fake = new UserWorkflow();
    	fake.name = "Workflow Repository";
    	WorkflowNode rootNode = new WorkflowNode(fake);
    	for(UserWorkflow w: workflows){
    		rootNode.add(new WorkflowNode(w));
    	}
    	return rootNode;
    	
	}

	public void addUserWorkflowTree(WorkflowNode rootNode) {
		
		HashMap<String, DefaultMutableTreeNode> folders = new HashMap<String, DefaultMutableTreeNode>();
		//first add all folders
		//Whenever a folder was added in the ADD function the list of folders is ordered by name 
		//so we don't worry about it here.Å
		for(String f: this.folders){
			DefaultMutableTreeNode fnode = new DefaultMutableTreeNode(f);
			folders.put(f, fnode);
			rootNode.add(fnode);
		}
		//add workflows to folders
		for(UserWorkflow w: workflows){
			if(w.folder == null){
				rootNode.add(new WorkflowNode(w));
			}
			else{
				DefaultMutableTreeNode folderNode = folders.get(w.folder);
				if(folderNode == null){
					folderNode = new DefaultMutableTreeNode(w.folder);
					folders.put(w.folder, folderNode);
					rootNode.add(folderNode);
				}
				folderNode.add(new WorkflowNode(w));
			}
    	}
	}
}
