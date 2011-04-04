package org.geworkbench.components.genspace;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.geworkbench.components.genspace.entity.Tool;
import org.geworkbench.components.genspace.entity.Workflow;
import org.geworkbench.components.genspace.rating.WorkflowVisualizationPopup;
import org.geworkbench.components.genspace.server.UsageInformationRemote;
import org.geworkbench.components.genspace.ui.LoadingPanel;
import org.geworkbench.components.genspace.ui.WorkflowVisualizationPanel;
import org.geworkbench.engine.config.VisualPlugin;

public class WorkflowVisualization extends JPanel implements VisualPlugin,
		ActionListener, Runnable {

	private static final long serialVersionUID = -7354810678034537180L;
	private Log log = LogFactory.getLog(this.getClass());
	private JComboBox tools = new JComboBox();
	private JComboBox actions = new JComboBox();
	private JButton button = new JButton("Search");
	private JLabel label = new JLabel();
	private JPanel selectPanel = new JPanel();
	private ArrayList<Workflow> workflows = new ArrayList<Workflow>();
	private LoadingPanel loader = new LoadingPanel();
	private static UsageInformationRemote facade;
	public static UsageInformationRemote getFacade()
	{
		if(facade == null)
			facade = (UsageInformationRemote) GenSpace.getRemote("ToolInformation");
		return facade;
	}
	public static final String[] NUMBERS = { "No", "One", "Two", "Three",
			"Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten" };

	public WorkflowVisualization() {
		log.debug("Workflow Visualization started");

	}

	@Override
	public void run() {
		initComponents();
	}

	private void initComponents() {
		setLayout(new BorderLayout());

		tools.addItem("-- select tool --");
		// the rest of the app
		SwingWorker<List<Tool>, Void> worker = new SwingWorker<List<Tool>, Void>(){
			@Override
			protected void done() {
				try {
					for (Tool tool : get())
						tools.addItem(tool);
				} catch (InterruptedException e) {
				} catch (ExecutionException e) {
				}
			}
			@Override
			protected List<Tool> doInBackground() throws Exception {
				return LoginFactory.getUsageOps().getAllTools();
			}
			
		};
		worker.execute();


		actions.addItem("-- select action --");
		actions.addItem("Most common workflow starting with");
		actions.addItem("Most common workflow including");
		actions.addItem("All workflows including");


		selectPanel.add(actions);
		selectPanel.add(tools);

		button.addActionListener(this);
		selectPanel.add(button);
		label.setText("Please select an action and a tool to search for");
		selectPanel.add(label);

		add(selectPanel, BorderLayout.NORTH);
	
		add(vis, BorderLayout.CENTER);
		vis.setBackground(Color.white);
	}
	WorkflowVisualizationPanel vis = new WorkflowVisualizationPanel();

	@Override
	public void actionPerformed(ActionEvent e) {
		SwingWorker<List<Workflow>, Void> worker = new SwingWorker<List<Workflow>, Void>() {
			@Override
			protected void done() {
				List<Workflow> ret = null;
				try {
					ret = get();
				} catch (InterruptedException e) {
					GenSpace.logger.error("Unable to talk to server: ", e);
				} catch (ExecutionException e) {
					GenSpace.logger.error("Unable to talk to server: ", e);
				}
				
				// make sure we got some results!
				if (ret == null || ret.size() == 0) {
					// no results came back!
					JOptionPane.showMessageDialog(null,
							"There are no workflows matching that criteria");
					label.setText("No Workflows found");
				}
				String noun = "workflow";
				if (ret.size() > 1)
					noun = "workflows";
				label.setText(ret.size() + " " + noun + " found");
				remove(loader);
				loader.stop();

				add(vis, BorderLayout.CENTER);
				revalidate();
				repaint();
				
				if(ret.size() == 1)
					vis.render(ret.get(0),((Tool) tools.getSelectedItem()));
				else if(ret.size() > 1)
					vis.render(ret,((Tool) tools.getSelectedItem()));
				
				super.done();
			}
			@Override
			public List<Workflow> doInBackground() {

				// get the name of the selected tool and the action
				if(tools.getSelectedIndex() > 0)
				{
					Tool tool = (Tool) tools.getSelectedItem();
					String action = actions.getSelectedItem().toString();
					
					if (action.equals("All workflows including")) {
						return LoginFactory.getUsageOps().getAllWorkflowsIncluding(tool);
					} else if(action.equals("Most common workflow starting with")){
						return LoginFactory.getUsageOps().getMostPopularWorkflowStartingWith(tool);
					} else if(action.equals("Most common workflow including")){
						return LoginFactory.getUsageOps().getMostPopularWorkflowIncluding(tool);
					}
					else
					{
						GenSpace.logger.error("Unknown action selected: " + action);
					}
				}
				GenSpace.logger.error("Unknown tool selected: " + tools.getSelectedItem().toString());
				return new ArrayList<Workflow>();
			}
		};
		remove(vis);
		add(loader, BorderLayout.CENTER);
		loader.setSize(this.getSize());
		loader.start();
		repaint();
		worker.execute();	
	}

	@Override
	public Component getComponent() {
		return this;
	}
}