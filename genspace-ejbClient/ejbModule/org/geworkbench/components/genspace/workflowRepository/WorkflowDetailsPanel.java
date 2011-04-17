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
<<<<<<< HEAD
=======
import java.util.concurrent.ExecutionException;
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
<<<<<<< HEAD
=======
import javax.swing.SwingWorker;
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import org.geworkbench.components.genspace.GenSpace;
<<<<<<< HEAD
import org.geworkbench.components.genspace.LoginManager;
import org.geworkbench.components.genspace.RuntimeEnvironmentSettings;
import org.geworkbench.components.genspace.ServerRequest;
=======
import org.geworkbench.components.genspace.LoginFactory;
import org.geworkbench.components.genspace.RuntimeEnvironmentSettings;
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7
import org.geworkbench.components.genspace.entity.IncomingWorkflow;
import org.geworkbench.components.genspace.entity.User;
import org.geworkbench.components.genspace.entity.UserWorkflow;
import org.geworkbench.components.genspace.entity.Workflow;
import org.geworkbench.engine.config.VisualPlugin;

public class WorkflowDetailsPanel extends JPanel implements VisualPlugin,
<<<<<<< HEAD
		ActionListener {

=======
ActionListener {


	private static final long serialVersionUID = -220085507992207251L;
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7
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
		String result = "ID: " + w.getId() + "\n";
<<<<<<< HEAD
		result += "Creator: " + w.getCreator().getUsername() + "\n";
		result += "Creation date: " + w.getCreatedAt().toString() + "\n";
=======
		result += "Creator: " + ( w.getCreator() == null ? "system" : w.getCreator().getUsername()) + "\n";
		if(w.getCreatedAt() != null)
			result += "Creation date: " + w.getCreatedAt().toString()	 + "\n";
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7
		result += "Average rating; " + w.getAvgRating() + "\n";
		result += "Usage count: " + w.getUsageCount() + "\n";
		result += "Comments count: " + w.getComments().size() + "\n";
		result += "Ratings count: " + w.getRatings().size() + "\n";
<<<<<<< HEAD
		result += "Tools list: " + w.getIdentifier() + "\n";
=======
		result += "Tools list: " + w.toString() + "\n";
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7
		return result;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		final Object o = e.getSource();
<<<<<<< HEAD
		javax.swing.SwingWorker<Void, Void> worker = new javax.swing.SwingWorker<Void, Void>() {

			@Override
			protected Void doInBackground() throws Exception {
				String result = "";
				try {
					User u = LoginManager.getUser();
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
//				int returnVal = fc.showOpenDialog(workflowRepository);
//				if (returnVal == JFileChooser.APPROVE_OPTION) {
//					File file = fc.getSelectedFile();
//					ObjectInputStream input = new ObjectInputStream(
//							new FileInputStream(file));
//					UserWorkflow w = (UserWorkflow) input.readObject();
//					input.close();
//					ArrayList<Object> params = new ArrayList<Object>();
//					params.add(u.getUsername());
//					params.add(w.getWorkflow());
//					params.add(w.getName());
//					w = (UserWorkflow) ServerRequest.get(
//							RuntimeEnvironmentSettings.ISBU_SERVER, "addUWF",
//							params);
//					if (w == null) {
//						return "Operation cancelled: Make sure that the Workflow is not already present in your repository";
//					} else {
//						if (!u.workflows.contains(w)) {
//							u.workflows.add(w);
//							workflowRepository.repositoryPanel.tree
//									.recalculateAndReload();
//						} else
//							return "The selected workflow is already present in the repository";
//					}
//				}
				return null; //TODO
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
//				User newUser = LoginManager.getUser(u);
//				UserSession.setUser(newUser);
//				GenSpace.getInstance().getWorkflowRepository()
//						.updateUser(newUser);
				return "User data has been refreshed successfully"; //TODO
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
//							IncomingWorkflow newW = new IncomingWorkflow();
//							newW.date = new Date();
//							newW.name = wn.userWorkflow.name;
//							newW.workflow = wn.userWorkflow.workflow;
//							newW.sender = u.username;
//							ArrayList<Object> params = new ArrayList<Object>();
//							params.add(u.username);
//							params.add(receiver);
//							params.add(newW);
//							String result = (String) ServerRequest
//									.get(RuntimeEnvironmentSettings.WORKFLOW_REPOSITORY_SERVER,
//											"send_workflow", params);
//							if (result == null || result.equals("")) {
//								return "Workflow sent successfully!";
//							} else
//								return result;
							return null;//TODO
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
=======

		String result = "";
			if (o.equals(sendButton)) {
				send();
			} else if (o.equals(refreshButton)) {
				refresh();
			} else if (o.equals(importButton)) {
				importWorkflow();
			} else if (o.equals(exportButton)) {
				exportWorkflow();
			}
	}
	private void messageUser(String m)
	{
		JOptionPane.showMessageDialog(null, m);
	}
	private void importWorkflow() {
		int returnVal = fc.showOpenDialog(workflowRepository);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			SwingWorker<UserWorkflow, Void> worker = new SwingWorker<UserWorkflow, Void>() {
				protected UserWorkflow doInBackground() throws Exception {
					File file = fc.getSelectedFile();
					ObjectInputStream input = new ObjectInputStream(
							new FileInputStream(file));
					UserWorkflow w = (UserWorkflow) input.readObject();
					input.close();
					w.setId(0);
					w.setOwner(LoginFactory.getUser());
					UserWorkflow ret = LoginFactory.getWorkflowOps()
							.importWorkflow(w);
					LoginFactory.updateCachedUser();
					return ret;
				};

				protected void done() {
					try {
						UserWorkflow ret = get();
						if (ret != null) {
							workflowRepository.repositoryPanel.tree
								.recalculateAndReload();
						}
						else
							messageUser( "Operation cancelled: Make sure that the Workflow is not already present in your repository");
					} catch (InterruptedException e) {
						GenSpace.logger.error("Unable to talk to server", e);
					} catch (ExecutionException e) {
						GenSpace.logger.error("Unable to talk to server", e);
					}
				};
			};
			worker.execute();

		}
	}

	private void exportWorkflow() {
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
					ObjectOutputStream output;
					try {
						output = new ObjectOutputStream(
								new FileOutputStream(file));
						output.writeObject(wn.userWorkflow);
						output.flush();
						output.close();
					} catch (FileNotFoundException e) {
						messageUser("Unable to write file");
					} catch (IOException e) {
						messageUser("Unable to write file");
					}
					
				}
			} else
				messageUser("An entire folder can't be exported");
		} else
			messageUser("Select a workflow from the repository");
		messageUser("Workflow exported successfully");
	}

	private String refresh() {
		// TODO: there should be a thread in background that calls this
		// function periodically to refresh stuff automatically
		LoginFactory.updateCachedUser();
		GenSpace.getInstance().getWorkflowRepository()
		.updateUser();
		return "User data has been refreshed successfully"; 
	}

	private void send() {
		TreePath currentSelection = workflowRepository.repositoryPanel.tree.tree
		.getSelectionPath();
		if (currentSelection != null) {
			DefaultMutableTreeNode currentNode = (DefaultMutableTreeNode) (currentSelection
					.getLastPathComponent());
			if (currentNode instanceof WorkflowNode) {// it can't be a
				// folder
				final String receiver = JOptionPane
				.showInputDialog("Input the receiver username");
				if (receiver != null && !receiver.trim().equals("")) {
					final WorkflowNode wn = (WorkflowNode) currentNode;
					

					SwingWorker<Boolean, Void> worker = new SwingWorker<Boolean, Void>() {
						protected Boolean doInBackground() throws Exception {
							IncomingWorkflow newW = new IncomingWorkflow();
							newW.setCreatedAt(new Date());
							newW.setName(wn.userWorkflow.getName());
							newW.setWorkflow(wn.userWorkflow.getWorkflow());
							newW.setSender(LoginFactory.getUser());
							return LoginFactory.getWorkflowOps().sendWorkflow(newW,receiver);
						};

						protected void done() {
							try {
								Boolean ret = get();
								if (ret) {
									messageUser("Workflow sent successfully!");
								}
								else
									messageUser( "Operation cancelled: Make sure that the Workflow is not already present in your repository");
							} catch (InterruptedException e) {
								GenSpace.logger.error("Unable to talk to server", e);
							} catch (ExecutionException e) {
								GenSpace.logger.error("Unable to talk to server", e);
							}
						};
					};
					worker.execute();
					
				} else
					messageUser("Input a valid username");
			} else
				messageUser("An entire folder can't be sent");
		} else
			messageUser("Select a workflow from the repository");
	}// SEND

>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7

}
