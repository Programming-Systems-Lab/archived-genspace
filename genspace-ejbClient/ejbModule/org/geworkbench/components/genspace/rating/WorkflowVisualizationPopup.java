package org.geworkbench.components.genspace.rating;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingWorker;
import javax.swing.border.MatteBorder;

import org.geworkbench.components.genspace.GenSpace;
import org.geworkbench.components.genspace.LoginManager;
import org.geworkbench.components.genspace.RuntimeEnvironmentSettings;
import org.geworkbench.components.genspace.entity.Tool;
import org.geworkbench.components.genspace.entity.User;
import org.geworkbench.components.genspace.entity.Workflow;
import org.geworkbench.components.genspace.workflowRepository.WorkflowRepository;
import org.geworkbench.util.BrowserLauncher;

public class WorkflowVisualizationPopup extends JPopupMenu implements
		ActionListener {

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
	private TitleItem workflowTitle = new TitleItem("Workflow");
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

	public void initialize(final Tool tn, final Workflow workflow) {

		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
			@Override
			public Void doInBackground() {

				// only perform setup if we need to
				if (tool == null) {

					getThisPopupMenu().removeAll();
					tool = tn;
					toolSRP.setTitle("Rate " + tn.getName());

					if (toolOptions) {
						gotoToolPage
								.setText("Goto GenSpace page for " + tn.getName());
						add(gotoToolPage);

						// add username for expert user request

						User expert = LoginManager.getUsageOps().getExpertUserFor(tn);

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
						workflowTitle.setVisible(true);
						add(workflowTitle);
					
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
				return null;
			}
		};
		worker.execute();
	}

	public void showWorkflowRating() {
		workflowRating = true;
		workflowTitle.setVisible(true);
		workflowSRP.setVisible(true);
	}

	public void showWorkflowOptions() {
		workflowOptions = true;
		workflowTitle.setVisible(true);
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
		workflowTitle.setVisible(false);
		workflowSRP.setVisible(false);
	}

	public void hideWorkflowOptions() {
		workflowOptions = false;
		workflowTitle.setVisible(false);
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
			JOptionPane
					.showMessageDialog(
							this,
							"This functionality has not yet been enabled.\n"
									+ "Please use the messaging plugin to contact the expert user.",
							"Warning", JOptionPane.WARNING_MESSAGE);
			return;
		} else if (item == addWorkflowRepository && workflow.getId() > 0) {
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
//		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
//			@Override
//			public Void doInBackground() {
//				User u = UserSession.getInstance();
//				if (u != null) {
//					String name = JOptionPane.showInputDialog(this,
//							"Type a name for the workflow to be added:");
//					if (name != null && name.trim().length() > 0) {
//						ArrayList<Object> params = new ArrayList<Object>();
//						params.add(u.username);
//						params.add(wni);
//						params.add(name);
//						UserWorkflow uw = (UserWorkflow) ServerRequest.get(
//								RuntimeEnvironmentSettings.ISBU_SERVER,
//								"addUWF", params);
//						if (uw == null) {
//							JOptionPane
//									.showMessageDialog(
//											null,
//											"Operation cancelled: Make sure that the Workflow is not already present in your repository.");
//						} else {
//							if (!u.workflows.contains(uw)) {
//								u.workflows.add(uw);
//								WorkflowRepository wr = GenSpace.getInstance()
//										.getWorkflowRepository();
//								wr.repositoryPanel.tree.recalculateAndReload();
//								// wr.repositoryPanel.tree.addWorkflowToRoot(uw);
//								JOptionPane
//										.showMessageDialog(null,
//												"Workflow added succesfully to repository");
//							}
//						}
//					} else {
//						JOptionPane
//								.showMessageDialog(null,
//										"Operation cancelled: A valid name has to be specified");
//					}
//				} else {
//					JOptionPane
//							.showMessageDialog(null,
//									"You need to be logged in to manage the repository.");
//				}
//				return null;
//			}
//		};
//		worker.execute();
		//TODO
	}

}

class TitleItem extends JPanel {
	Color backgroundColor = new Color(35, 35, 142);
	Color foregroundColor = Color.WHITE;
	Font font = new Font("Verdana", Font.BOLD, 9);
	JLabel label = new JLabel();

	public TitleItem(String title) {
		setBackground(backgroundColor);
		MatteBorder border = new MatteBorder(3, 3, 3, 3, backgroundColor);
		setBorder(border);
		label.setText(title.toUpperCase());
		label.setFont(font);
		label.setBackground(foregroundColor);
		label.setForeground(foregroundColor);
		setLayout(new BorderLayout());
		add(label);
	}

	public void setTitle(String title) {
		label.setText(title.toUpperCase());
	}
}