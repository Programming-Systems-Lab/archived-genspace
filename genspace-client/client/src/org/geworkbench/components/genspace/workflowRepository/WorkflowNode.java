package org.geworkbench.components.genspace.workflowRepository;

import javax.swing.tree.DefaultMutableTreeNode;

import org.geworkbench.components.genspace.bean.UserWorkflow;

public class WorkflowNode extends DefaultMutableTreeNode {

	public UserWorkflow userWorkflow;

	public WorkflowNode(UserWorkflow uw) {
		super(uw.name);
		userWorkflow = uw;
	}
}
