package org.geworkbench.components.genspace.workflowRepository;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import org.geworkbench.components.genspace.GenSpace;
import org.geworkbench.components.genspace.RuntimeEnvironmentSettings;
import org.geworkbench.components.genspace.ServerRequest;
import org.geworkbench.components.genspace.bean.User;
import org.geworkbench.components.genspace.bean.UserSession;
import org.geworkbench.components.genspace.bean.UserWorkflow;
import org.geworkbench.components.genspace.bean.Workflow;
import org.geworkbench.components.genspace.bean.WorkflowInbox;
import org.geworkbench.components.genspace.ui.LoginManager;
import org.geworkbench.engine.config.VisualPlugin;

public class WorkflowDetailsPanel extends JPanel implements VisualPlugin,
		ActionListener {

	private WorkflowRepository workflowRepository;
	private JTextArea textArea;
	private Workflow workflow;
	private JButton sendButton = new JButton("Send");
	private JButton importButton = new JButton("Import");
	private JButton exportButton = new JButton("Export");
	private JButton publishButton = new JButton("Publish");
	private JButton refreshButton = new JButton("Refresh");
	private final JFileChooser fc = new JFileChooser();

	public WorkflowDetailsPanel(WorkflowRepository workflowRepository) {
		super(new BorderLayout());
		this.workflowRepository = workflowRepository;
		JPanel buttonPanel = new JPanel(new FlowLayout());
		buttonPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		sendButton.addActionListener(this);
		buttonPanel.add(sendButton);
		importButton.addActionListener(this);
		buttonPanel.add(importButton);
		exportButton.addActionListener(this);
		buttonPanel.add(exportButton);
		publishButton.addActionListener(this);
		buttonPanel.add(publishButton);
		refreshButton.addActionListener(this);
		buttonPanel.add(refreshButton);
		add(buttonPanel, BorderLayout.NORTH);
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setText("Select a Workflow from the Repository to display its information.\n");
		textArea.setBorder(BorderFactory
				.createTitledBorder("Workflow Information"));
		JScrollPane sp = new JScrollPane(textArea);
		add(sp, BorderLayout.CENTER);
	}

	@Override
	public Component getComponent() {
		return this;
	}

	public void setAndPrintWorkflow(Workflow workflow) {
		this.workflow = workflow;
		if (workflow != null)
			textArea.setText(getWorkflowDetailsString(workflow));
		else
			clearData();
	}

	public void clearData() {
		workflow = null;
		textArea.setText("");
	}

	public String getWorkflowDetailsString(Workflow w) {
		String result = "ID: " + w.ID + "\n";
		result += "Creator: " + w.creatorUsername + "\n";
		result += "Creation date: " + w.creationDate.toString() + "\n";
		result += "Average rating; " + w.getAvgRating() + "\n";
		result += "Usage count: " + w.usageCount + "\n";
		result += "Comments count: " + w.comments.size() + "\n";
		result += "Ratings count: " + w.ratings.size() + "\n";
		result += "Tools list: " + w.getIdentifier() + "\n";
		return result;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		final Object o = e.getSource();
		org.jdesktop.swingworker.SwingWorker<Void, Void> worker = new org.jdesktop.swingworker.SwingWorker<Void, Void>() {

			@Override
			protected Void doInBackground() throws Exception {
				String result = "";
				try {
					User u = UserSession.getInstance();
					if (u != null) {
						if (o.equals(sendButton)) {
							result = send(u);
						} else if (o.equals(refreshButton)) {
							result = refresh(u);
						} else if (o.equals(importButton)) {
							result = importWorkflow(u);
						} else if (o.equals(exportButton)) {
							result = exportWorkflow();
						}
					} else {
						result = "Please login first.";
					}
				} catch (Exception e) {
					e.printStackTrace();
					result = e.getMessage();
				}
				if (result != null && !result.trim().equals(""))
					JOptionPane.showMessageDialog(null, result);
				return null;
			}

			private String importWorkflow(User u) throws FileNotFoundException,
					IOException, ClassNotFoundException {
				int returnVal = fc.showOpenDialog(workflowRepository);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					ObjectInputStream input = new ObjectInputStream(
							new FileInputStream(file));
					UserWorkflow w = (UserWorkflow) input.readObject();
					input.close();
					ArrayList<Object> params = new ArrayList<Object>();
					params.add(u.username);
					params.add(w.workflow.ID);
					params.add(w.name);
					w = (UserWorkflow) ServerRequest.get(
							RuntimeEnvironmentSettings.ISBU_SERVER, "addUWF",
							params);
					if (w == null) {
						return "Operation cancelled: Make sure that the Workflow is not already present in your repository";
					} else {
						if (!u.workflows.contains(w)) {
							u.workflows.add(w);
							workflowRepository.repositoryPanel.tree
									.recalculateAndReload();
						} else
							return "The selected workflow is already present in the repository";
					}
				}
				return null;
			}

			private String exportWorkflow() throws FileNotFoundException,
					IOException {
				TreePath currentSelection = workflowRepository.repositoryPanel.tree.tree
						.getSelectionPath();
				if (currentSelection != null) {
					DefaultMutableTreeNode currentNode = (DefaultMutableTreeNode) (currentSelection
							.getLastPathComponent());
					if (currentNode instanceof WorkflowNode) {// it can't be a
																// folder
						int returnVal = fc.showSaveDialog(workflowRepository);
						if (returnVal == JFileChooser.APPROVE_OPTION) {
							File file = fc.getSelectedFile();
							WorkflowNode wn = (WorkflowNode) currentNode;
							ObjectOutputStream output = new ObjectOutputStream(
									new FileOutputStream(file));
							output.writeObject(wn.userWorkflow);
							output.flush();
							output.close();
						}
					} else
						return "An entire folder can't be exported";
				} else
					return "Select a workflow from the repository";
				return "Workflow exported successfully";
			}

			private String refresh(User u) throws Exception {
				// TODO: there should be a thread in background that calls this
				// function periodically to refresh stuff automatically
				User newUser = LoginManager.getUser(u);
				UserSession.setUser(newUser);
				GenSpace.getInstance().getWorkflowRepository()
						.updateUser(newUser);
				return "User data has been refreshed successfully";
			}

			private String send(User u) {
				TreePath currentSelection = workflowRepository.repositoryPanel.tree.tree
						.getSelectionPath();
				if (currentSelection != null) {
					DefaultMutableTreeNode currentNode = (DefaultMutableTreeNode) (currentSelection
							.getLastPathComponent());
					if (currentNode instanceof WorkflowNode) {// it can't be a
																// folder
						String receiver = JOptionPane
								.showInputDialog("Input the receiver username");
						if (receiver != null && !receiver.trim().equals("")) {
							WorkflowNode wn = (WorkflowNode) currentNode;
							WorkflowInbox newW = new WorkflowInbox();
							newW.date = new Date();
							newW.name = wn.userWorkflow.name;
							newW.workflow = wn.userWorkflow.workflow;
							newW.sender = u.username;
							ArrayList<Object> params = new ArrayList<Object>();
							params.add(u.username);
							params.add(receiver);
							params.add(newW);
							String result = (String) ServerRequest
									.get(RuntimeEnvironmentSettings.WORKFLOW_REPOSITORY_SERVER,
											"send_workflow", params);
							if (result == null || result.equals("")) {
								return "Workflow sent successfully!";
							} else
								return result;
						} else
							return "Input a valid username";
					} else
						return "An entire folder can't be sent";
				} else
					return "Select a workflow from the repository";
			}// SEND

		};
		worker.execute();
	}

}
