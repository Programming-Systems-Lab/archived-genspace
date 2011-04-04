package org.geworkbench.components.genspace.rating;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Date;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.SwingWorker;

import org.geworkbench.components.genspace.GenSpace;
import org.geworkbench.components.genspace.LoginFactory;
import org.geworkbench.components.genspace.RuntimeEnvironmentSettings;
import org.geworkbench.components.genspace.entity.Tool;
import org.geworkbench.components.genspace.entity.User;
import org.geworkbench.components.genspace.entity.UserWorkflow;
import org.geworkbench.components.genspace.entity.Workflow;
import org.geworkbench.util.BrowserLauncher;

public class WorkflowVisualizationPopup extends JPopupMenu implements
		ActionListener {


	private static final long serialVersionUID = 2674054526848758204L;
	private boolean toolOptions = false;
	private boolean toolRating = false;
	private boolean workflowOptions = false;
	private boolean workflowRating = false;

	// tool options
	private JMenuItem gotoToolPage = new JMenuItem();
	private JMenuItem contactEU = new JMenuItem();

	// tool rating
	private StarRatingPanel toolSRP = new StarRatingPanel(this);

	// workflow specific
	private JMenuItem viewWorkflowCommentsPage = new JMenuItem(
			"View/add workflow comments");
	private StarRatingPanel workflowSRP = new StarRatingPanel(this);
	private JMenuItem addWorkflowRepository = new JMenuItem(
			"Add workflow to your repository");

	// we store the tool name each time the menu is invoked
	// so we can speed up the process
	private Tool tool;
	private Workflow workflow;

	public WorkflowVisualizationPopup() {
		super();

		gotoToolPage.addActionListener(this);
		contactEU.addActionListener(this);
		viewWorkflowCommentsPage.addActionListener(this);
		addWorkflowRepository.addActionListener(this);
	}

	public JPopupMenu getThisPopupMenu() {
		return this;
	}
	private User expert;
	public void initialize(Tool tn, Workflow workflow) {
		this.workflow = workflow;
		this.tool = tn;
		getThisPopupMenu().removeAll();

		if (tool != null) {

			tool = tn;
			toolSRP.setTitle("Rate " + tn.getName());

			if (toolOptions) {
				gotoToolPage
						.setText("Goto GenSpace page for " + tn.getName());
				add(gotoToolPage);

				// add username for expert user request

				expert = LoginFactory.getUsageOps().getExpertUserFor(tn);

				if (expert != null) {
					contactEU.setText("Contact Expert User - ("
							+ expert.getFullName() + ")");
					add(contactEU);
				}
			}

			if (toolRating && tool.getId() > 0) {
				toolSRP.setTitle("Rate " + tn);
				toolSRP.loadRating(tn);
				add(toolSRP);
			}
		}

		if (workflow != null) {
			if (workflowOptions) {
			
				// display only if we have a good id
				if (workflow.getId() > 0) {
					addWorkflowRepository.setVisible(true);
					add(addWorkflowRepository);
					viewWorkflowCommentsPage.setVisible(true);
					add(viewWorkflowCommentsPage);
				} else
					viewWorkflowCommentsPage.setVisible(false);

			}
			if (workflowRating && workflow.getId() > 0) {
				workflowSRP.setTitle("Rate workflow until here");
				workflowSRP.loadRating(workflow);
				add(workflowSRP);
			}
		}
		
	}

	public void showWorkflowRating() {
		workflowRating = true;
		workflowSRP.setVisible(true);
	}

	public void showWorkflowOptions() {
		workflowOptions = true;
		viewWorkflowCommentsPage.setVisible(true);
		addWorkflowRepository.setVisible(true);
	}

	public void showToolOptions() {
		toolOptions = true;

		gotoToolPage.setVisible(true);
		contactEU.setVisible(true);
	}

	public void showToolRating() {
		toolRating = true;
		toolSRP.setVisible(true);
	}

	public void hideWorkflowRating() {
		workflowRating = false;
		workflowSRP.setVisible(false);
	}

	public void hideWorkflowOptions() {
		workflowOptions = false;
		viewWorkflowCommentsPage.setVisible(false);
		addWorkflowRepository.setVisible(false);
	}

	public void hideToolOptions() {
		toolOptions = false;

		gotoToolPage.setVisible(false);
		contactEU.setVisible(false);
	}

	public void hideToolRating() {
		toolRating = false;
		toolSRP.setVisible(false);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String args = "";

		JMenuItem item = (JMenuItem) event.getSource();
		boolean browser = true;
		if (item == gotoToolPage && tool.getId() > 0)
			args = "tool/index/" + tool.getId();
		else if (item == viewWorkflowCommentsPage && workflow.getId() > 0)
			args = "workflow/index/" + workflow.getId();
		else if (item == contactEU) {
			if (LoginFactory.isLoggedIn()) {
				GenSpace.bringUpProfile(expert);
			} else {
				JOptionPane
						.showMessageDialog(null,
								"You need to be logged in to use GenSpace's social features.");
			}
			return;
		} else if (item == addWorkflowRepository && workflow != null && workflow.getId() > 0) {
			browser = false;
			addWorkFlowToRepository();
		}

		if (browser) {
			try {
				BrowserLauncher.openURL(RuntimeEnvironmentSettings.GS_WEB_ROOT
						+ args);
			} catch (IOException e) {
				GenSpace.logger.error("Error",e);
			}
		}
	}

	private void addWorkFlowToRepository() {
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
			@Override
			public Void doInBackground() {
				if (LoginFactory.isLoggedIn()) {
					String name = JOptionPane.showInputDialog("Type a name for the workflow to be added:",
							"");
					if (name != null && name.trim().length() > 0) {
						UserWorkflow uw = new UserWorkflow();
						uw.setName(name);
						uw.setWorkflow(workflow);
						uw.setFolder(LoginFactory.getUser().getRootFolder());
						uw.setOwner(LoginFactory.getUser());
						uw.setCreatedAt(new Date());
						
						LoginFactory.getWorkflowOps().addWorkflow(uw, LoginFactory.getUser().getRootFolder());
						LoginFactory.updateCachedUser();
							JOptionPane
							.showMessageDialog(null,
									"Workflow added succesfully to repository");
						
					} else {
						JOptionPane
								.showMessageDialog(null,
										"Operation cancelled: A valid name has to be specified");
					}
				} else {
					JOptionPane
							.showMessageDialog(null,
									"You need to be logged in to manage the repository.");
				}
				return null;
			}
		};
		worker.execute();
		//TODO
	}

}