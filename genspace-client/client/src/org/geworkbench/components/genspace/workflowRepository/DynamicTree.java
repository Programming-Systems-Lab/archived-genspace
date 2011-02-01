package org.geworkbench.components.genspace.workflowRepository;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import org.geworkbench.components.genspace.RuntimeEnvironmentSettings;
import org.geworkbench.components.genspace.ServerRequest;
import org.geworkbench.components.genspace.bean.User;
import org.geworkbench.components.genspace.bean.UserSession;
import org.geworkbench.components.genspace.bean.UserWorkflow;

public class DynamicTree extends JPanel implements ActionListener,
		TreeSelectionListener {

	protected WorkflowNode rootNode;
	protected DefaultTreeModel treeModel;
	protected JTree tree;
	private Toolkit toolkit = Toolkit.getDefaultToolkit();
	private static String ADD_COMMAND = "add";
	private static String REMOVE_COMMAND = "remove";
	private static String NEW_COMMAND = "new";
	RepositoryPanel repositoryPanel;

	public DynamicTree(RepositoryPanel rp) {
		super(new BorderLayout());
		repositoryPanel = rp;
		JPanel treePanel = new JPanel(new GridLayout(1, 0));

		JButton addButton = new JButton("Add");
		addButton.setActionCommand(ADD_COMMAND);
		addButton.addActionListener(this);

		JButton newButton = new JButton("New");
		newButton.setActionCommand(NEW_COMMAND);
		newButton.addActionListener(this);

		JButton removeButton = new JButton("Delete");
		removeButton.setActionCommand(REMOVE_COMMAND);
		removeButton.addActionListener(this);

		// Lay everything out.
		// treePanel.setPreferredSize(new Dimension(300, 150));
		add(treePanel, BorderLayout.CENTER);

		JPanel panel = new JPanel(new GridLayout(0, 3));
		panel.add(newButton);
		panel.add(addButton);
		panel.add(removeButton);
		add(panel, BorderLayout.SOUTH);

		recalculateTree();
		treeModel = new DefaultTreeModel(rootNode);
		treeModel.addTreeModelListener(new MyTreeModelListener());
		tree = new JTree(treeModel);
		tree.setEditable(true);
		tree.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.addTreeSelectionListener(this);
		tree.setShowsRootHandles(true);
		JScrollPane scrollPane = new JScrollPane(tree);
		add(scrollPane);
	}

	public void recalculateTree() {
		User u = UserSession.getInstance();
		UserWorkflow fake = new UserWorkflow();
		fake.name = "Workflow Repository";
		rootNode = new WorkflowNode(fake);
		if (u != null) {
			u.addUserWorkflowTree(rootNode);
		}
	}

	public void recalculateAndReload() {
		recalculateTree();
		treeModel.setRoot(rootNode);
		// treeModel.reload();
		// this.repaint();
	}

	/** Remove the currently selected node. */
	public void removeCurrentNode() {
		TreePath currentSelection = tree.getSelectionPath();
		if (currentSelection != null) {
			DefaultMutableTreeNode currentNode = (DefaultMutableTreeNode) (currentSelection
					.getLastPathComponent());
			MutableTreeNode parent = (MutableTreeNode) (currentNode.getParent());
			if (parent != null) {// Don't delete the root
				treeModel.removeNodeFromParent(currentNode);
				return;
			}
		}

		// Either there was no selection, or the root was selected.
		toolkit.beep();
	}

	/** Add child to the root */
	public void addWorkflowToRoot(UserWorkflow child) {
		addWorkflowObject(rootNode, child, true);
	}

	private void addWorkflowObject(WorkflowNode parent, UserWorkflow workflow,
			boolean visibile) {
		WorkflowNode childNode = new WorkflowNode(workflow);
		treeModel.insertNodeInto(childNode, parent, parent.getChildCount());

		// Make sure the user can see the lovely new node.
		if (visibile) {
			tree.scrollPathToVisible(new TreePath(childNode.getPath()));
		}
	}

	/** Add child to the currently selected node. */
	public DefaultMutableTreeNode addObjectToSelected(Object child) {
		DefaultMutableTreeNode parentNode = null;
		TreePath parentPath = tree.getSelectionPath();

		if (parentPath == null) {
			parentNode = rootNode;
		} else {
			parentNode = (DefaultMutableTreeNode) (parentPath
					.getLastPathComponent());
		}

		return addObject(parentNode, child, true);
	}

	public DefaultMutableTreeNode addObject(DefaultMutableTreeNode parent,
			Object child) {
		return addObject(parent, child, true);
	}

	public DefaultMutableTreeNode addObject(DefaultMutableTreeNode parent,
			Object child, boolean shouldBeVisible) {
		DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(child);

		if (parent == null) {
			parent = rootNode;
		}

		// It is key to invoke this on the TreeModel, and NOT
		// DefaultMutableTreeNode
		treeModel.insertNodeInto(childNode, parent, parent.getChildCount());

		// Make sure the user can see the lovely new node.
		if (shouldBeVisible) {
			tree.scrollPathToVisible(new TreePath(childNode.getPath()));
		}
		return childNode;
	}

	class MyTreeModelListener implements TreeModelListener {
		@Override
		public void treeNodesChanged(TreeModelEvent e) {
			DefaultMutableTreeNode node;
			node = (DefaultMutableTreeNode) (e.getTreePath()
					.getLastPathComponent());

			/*
			 * If the event lists children, then the changed node is the child
			 * of the node we've already gotten. Otherwise, the changed node and
			 * the specified node are the same.
			 */

			int index = e.getChildIndices()[0];
			node = (DefaultMutableTreeNode) (node.getChildAt(index));

//			System.out.println("The user has finished editing the node.");
//			System.out.println("New value: " + node.getUserObject());
			// TODO change the name in the DB
		}

		@Override
		public void treeNodesInserted(TreeModelEvent e) {
		}

		@Override
		public void treeNodesRemoved(TreeModelEvent e) {
		}

		@Override
		public void treeStructureChanged(TreeModelEvent e) {
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		final String command = e.getActionCommand();
		javax.swing.SwingWorker<Void, Void> worker = new javax.swing.SwingWorker<Void, Void>() {
			@Override
			public Void doInBackground() {
				try {
					User u = UserSession.getInstance();
					if (u != null) {

						if (NEW_COMMAND.equals(command)) {
							newCommand(u);
						} else if (ADD_COMMAND.equals(command)) {
							addCommand(u);
						} else if (REMOVE_COMMAND.equals(command)) {
							removeCommand(u);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}

			private void removeCommand(User u) {
				TreePath currentSelection = tree.getSelectionPath();
				if (currentSelection != null) {
					DefaultMutableTreeNode currentNode = (DefaultMutableTreeNode) (currentSelection
							.getLastPathComponent());
					MutableTreeNode parent = (MutableTreeNode) (currentNode
							.getParent());
					if (parent != null) {// Don't delete the root
						if (currentNode instanceof WorkflowNode) {
							// Removing a workflow
							WorkflowNode wf = (WorkflowNode) currentNode;
							UserWorkflow uw = wf.userWorkflow;
							ArrayList<Object> params = new ArrayList<Object>();
							params.add(u.username);
							params.add(uw.workflow.ID);
							String result = (String) ServerRequest
									.get(RuntimeEnvironmentSettings.WORKFLOW_REPOSITORY_SERVER,
											"delete_workflow", params);
							if (result == null || result.equals("")) {
								u.workflows.remove(uw);
								recalculateAndReload();
								repositoryPanel.workflowRepository
										.clearWorkflowData();
							}
						} else {
							// removing a folder
							int option = JOptionPane
									.showConfirmDialog(null,
											"All workflows in the folder will be lost. Continue?");
							if (option == JOptionPane.YES_OPTION) {
								String folderName = currentNode.getUserObject()
										.toString();
								ArrayList<Object> params = new ArrayList<Object>();
								params.add(u.username);
								params.add(folderName);
								String result = (String) ServerRequest
										.get(RuntimeEnvironmentSettings.WORKFLOW_REPOSITORY_SERVER,
												"delete_folder", params);
								if (result == null || result.equals("")) {
									u.folders.remove(folderName);
									Iterator<UserWorkflow> it = u.workflows
											.iterator();
									while (it.hasNext()) {
										UserWorkflow uw = it.next();
										if (uw.folder != null
												&& uw.folder.equals(folderName))
											it.remove();
									}
									recalculateAndReload();
								} else {
									JOptionPane.showMessageDialog(null, result);
								}
							}
						}
					}
				}
			}

			private void addCommand(User u) {
				String folderName = JOptionPane
						.showInputDialog("Input a folder name:");
				if (folderName != null && !folderName.trim().equals("")
						|| u.folders.contains(folderName)) {
					DefaultMutableTreeNode root = (DefaultMutableTreeNode) tree
							.getModel().getRoot();
					if (folderName.equalsIgnoreCase(root.getUserObject()
							.toString())) {
						folderName = null;
					}
					TreePath currentSelection = tree.getSelectionPath();
					if (currentSelection != null) {
						DefaultMutableTreeNode currentNode = (DefaultMutableTreeNode) (currentSelection
								.getLastPathComponent());
						if (currentNode instanceof WorkflowNode) {
							WorkflowNode wf = (WorkflowNode) currentNode;
							UserWorkflow uw = wf.userWorkflow;
							ArrayList<Object> params = new ArrayList<Object>();
							params.add(u.username);
							params.add(uw.workflow.ID);
							params.add(folderName);
							String result = (String) ServerRequest
									.get(RuntimeEnvironmentSettings.WORKFLOW_REPOSITORY_SERVER,
											"add_to_folder", params);
							if (result == null || result.equals("")) {
								uw.folder = folderName;
								recalculateAndReload();
							} else {
								JOptionPane.showMessageDialog(null, result);
							}
						}
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"Input an existing folder name.");
				}
			}

			private void newCommand(User u) {
				// Adds a folder as a child of the root folder
				// Add button clicked
				String folderName = JOptionPane
						.showInputDialog("Select a folder name");
				if (folderName != null && !folderName.trim().equals("")
						&& !u.folders.contains(folderName)) {
					// send add_folder to the server
					ArrayList<Object> params = new ArrayList<Object>();
					params.add(u.username);
					params.add(folderName);
					String result = (String) ServerRequest
							.get(RuntimeEnvironmentSettings.WORKFLOW_REPOSITORY_SERVER,
									"new_folder", params);
					if (result == null || result.equals("")) {
						u.folders.add(folderName);
						Collections.sort(u.folders);
						recalculateAndReload();
						// addObject(rootNode, folderName);
					} else {
						JOptionPane.showMessageDialog(null, result);
					}
				}

			}
		};
		worker.execute();
	}

	/**
	 * Invoked when a node of the tree is selected (the rename is performed by
	 * myTreeModeListener though.
	 */
	@Override
	public void valueChanged(TreeSelectionEvent e) {
		if (tree.getSelectionPath() != null
				&& tree.getSelectionPath().getPathCount() > 1) {
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree
					.getSelectionPath().getLastPathComponent();
			if (node instanceof WorkflowNode) {
				WorkflowNode wf = (WorkflowNode) node;
				repositoryPanel.workflowRepository.graphPanel
						.setAndPaintWorkflow(wf.userWorkflow.workflow);
				repositoryPanel.workflowRepository.workflowDetailsPanel
						.setAndPrintWorkflow(wf.userWorkflow.workflow);
				repositoryPanel.workflowRepository.workflowCommentsPanel
						.setData(wf.userWorkflow.workflow);
			}
		}
	}
}
