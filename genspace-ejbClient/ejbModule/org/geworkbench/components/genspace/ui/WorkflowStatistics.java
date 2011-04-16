<<<<<<< HEAD
package org.geworkbench.components.genspace.ui;


import javax.swing.*;

import org.geworkbench.components.genspace.GenSpace;
import org.geworkbench.components.genspace.LoginManager;
import org.geworkbench.components.genspace.entity.Tool;
import org.geworkbench.components.genspace.entity.Workflow;
import org.geworkbench.components.genspace.server.ToolInformationRemote;
import org.geworkbench.engine.config.VisualPlugin;

=======
 package org.geworkbench.components.genspace.ui;


import java.awt.Color;
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
<<<<<<< HEAD
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

=======
import java.io.Serializable;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextPane;
import javax.swing.ListCellRenderer;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.text.html.HTMLDocument;

import org.geworkbench.components.genspace.GenSpace;
import org.geworkbench.components.genspace.LoginFactory;
import org.geworkbench.components.genspace.entity.Tool;
import org.geworkbench.components.genspace.entity.Workflow;
import org.geworkbench.components.genspace.server.UsageInformationRemote;
import org.geworkbench.engine.config.VisualPlugin;

>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7
class ToolCellRenderer implements ListCellRenderer {

	@Override
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
<<<<<<< HEAD
		JLabel ret = new JLabel((index + 1) + ": " + (String) value);
		return ret;
	}

}

public class WorkflowStatistics extends JPanel implements VisualPlugin {
	private JList popularFirstTools;
	private JList popularTools;
	private JList popularWorkflows;
=======
		JLabel ret = new JLabel("<html>"+(index + 1) + ": " + (String) value+"</html>");
//		ret.setPreferredSize(new Dimension((int) list.getParent().getSize().getWidth(),20));
//		ret.set
//		ret.setPreferredSize(new Dimension(list.getSize().width,10));
		return ret;
	}
}

public class WorkflowStatistics extends JPanel implements VisualPlugin {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6212607358147972583L;
	private JList popularFirstTools;
	private JList popularTools;
	private JTextPane popWorkflows;
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7
	private JComboBox toolListing;
	private JLabel toolStats;
	private JPanel popToolsPanel;
	private JPanel popWFPanel;
	private JPanel popFirstToolsPan;
	private JPanel statsPan;
<<<<<<< HEAD

=======
	private boolean instrument = true;
	
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7
	private void updateAllToolList() {
		SwingWorker<List<Tool>, Void> worker = new SwingWorker<List<Tool>, Void>() {

			@Override
			protected void done() {
				try {
					List<Tool>results = get();
					DefaultComboBoxModel m = new DefaultComboBoxModel();
					m.addElement("");
<<<<<<< HEAD
=======
					if(results != null)
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7
					for (Tool s : results) {
						m.addElement(s);
					}
					toolListing.setModel(m);
<<<<<<< HEAD
=======
					if(instrument)
						System.out.println("Entire tool list size: " + GenSpace.getObjectSize((Serializable) results));
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7
				} catch (InterruptedException e) {
					GenSpace.logger.fatal("Error talking to server: ",e);
				} catch (ExecutionException e) {
					GenSpace.logger.fatal("Error talking to server: ",e);
				}
				super.done();
			}

			@Override
			protected List<Tool> doInBackground() throws Exception {
<<<<<<< HEAD
				return LoginManager.getFacade().getAllTools();
			}

		};
		worker.run();
=======
				return LoginFactory.getUsageOps().getAllTools();
			}

		};
		worker.execute();
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7
	}

	private void updatePopularTools() {
		SwingWorker<List<Tool>, Void> worker = new SwingWorker<List<Tool>, Void>() {

			@Override
			protected void done() {
				try {
					List<Tool> results = get();
					DefaultComboBoxModel m = new DefaultComboBoxModel();
					int lim = 10;
<<<<<<< HEAD
=======
					if(results != null)
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7
					for (Tool s : results) {
						m.addElement(s.getName());
						lim--;
						if (lim <= 0)
							break;
					}
					popularTools.setModel(m);
<<<<<<< HEAD
=======
					if(instrument)
						System.out.println("Popular tools size: " + GenSpace.getObjectSize((Serializable) results));
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7
				} catch (InterruptedException e) {
					GenSpace.logger.fatal("Error talking to server: ",e);
				} catch (ExecutionException e) {
					GenSpace.logger.fatal("Error talking to server: ",e);
				}
				super.done();
			}

			@Override
			protected List<Tool> doInBackground() throws Exception {
<<<<<<< HEAD
				return LoginManager.getFacade().getToolsByPopularity();
			}

		};
		worker.run();
=======
				return LoginFactory.getUsageOps().getToolsByPopularity();
			}

		};
		worker.execute();
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7
	}

	private void updatePopularWorkflows() {
		SwingWorker<List<Workflow>, Void> worker = new SwingWorker<List<Workflow>, Void>() {

			@Override
			protected void done() {
				try {
					List<Workflow> results = get();
<<<<<<< HEAD
					DefaultComboBoxModel m = new DefaultComboBoxModel();
					int lim = 10;
					for (Workflow s : results) {
						m.addElement(s.toString());
						lim--;
						if (lim <= 0)
							break;
					}
					popularWorkflows.setModel(m);
=======
					int lim = 10;
					int i = 1;
					String txt = "";
					if(results != null)
					for (Workflow s : results) {
						txt = txt + "<li>" + s.toString() + "</li>";
						lim--;
						i++;
						if (lim <= 0)
							break;
					}
					txt =txt.substring(0, txt.length()-5);
					popWorkflows.setText("<html><body><ol>"+txt+"</ol></body></html>");
					popWFPanel.revalidate();
					revalidate();
					repaint();
					if(instrument)
						System.out.println("Popular workflows size: " + GenSpace.getObjectSize((Serializable) results));
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7
				} catch (InterruptedException e) {
					GenSpace.logger.fatal(e);
				} catch (ExecutionException e) {
					GenSpace.logger.fatal(e);				}
				super.done();
			}

			@Override
			protected List<Workflow> doInBackground() throws Exception {

<<<<<<< HEAD
				return LoginManager.getFacade().getWorkflowsByPopularity();
			}

		};
		worker.run();
=======
				return LoginFactory.getUsageOps().getWorkflowsByPopularity();
			}

		};
		worker.execute();
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7
	}

	private void updatePopularWFHeads() {
		SwingWorker<List<Tool>, Void> worker = new SwingWorker<List<Tool>, Void>() {

			@Override
			protected void done() {
				try {
					List<Tool> results = get();
					DefaultComboBoxModel m = new DefaultComboBoxModel();
					int lim = 10;
<<<<<<< HEAD
=======
					if(results != null)
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7
					for (Tool s : results) {
						m.addElement(s.getName());
						lim--;
						if (lim <= 0)
							break;
					}
<<<<<<< HEAD
=======
					if(instrument)
						System.out.println("Popular first tools size: " + GenSpace.getObjectSize((Serializable) results));
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7
					popularFirstTools.setModel(m);
				} catch (InterruptedException e) {
					GenSpace.logger.fatal("Error talking to server: ",e);
				} catch (ExecutionException e) {
					GenSpace.logger.fatal("Error talking to server: ",e);
				}
				super.done();
			}

			@Override
			protected List<Tool> doInBackground() throws Exception {
<<<<<<< HEAD
				return LoginManager.getFacade().getMostPopularWFHeads();
			}

		};
		worker.run();
=======
				return LoginFactory.getUsageOps().getMostPopularWFHeads();
			}

		};
		worker.execute();
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7
	}

	public void updateFormFields() {
		updateAllToolList();
		updatePopularTools();
		updatePopularWFHeads();
		updatePopularWorkflows();
	}

	private void updateItemStats() {
		toolStats.setText("Loading...");
		final Tool tool = (Tool) toolListing.getSelectedItem();
		if (tool.equals("")) {
			toolStats.setText("Please select a tool to view its statistics");
			return;
		}
		SwingWorker<String, Void> worker = new SwingWorker<String, Void>() {
			@Override
			protected void done() {
				try {
					String r = get();
					toolStats.setText("<html>" + r + "</html>");
				} catch (InterruptedException e) {
					GenSpace.logger.fatal("Error talking to server: ",e);
				} catch (ExecutionException e) {
					GenSpace.logger.fatal("Error talking to server: ",e);
				}

				super.done();
			}

			@Override
			protected String doInBackground() throws Exception {
				String ret = "";
				String usageRate = "" + tool.getUsageCount();
				ret += "Total usage rate: " + usageRate + "<br>";

				String usageRateAsWFHead = "" + tool.getWfCountHead();
				ret += "Total usage rate at start of workflow: "
						+ usageRateAsWFHead + " <br>";

<<<<<<< HEAD
				Tool mostPopularNextTool = LoginManager.getFacade().getMostPopularNextTool(tool);
=======
				Tool mostPopularNextTool = LoginFactory.getUsageOps().getMostPopularNextTool(tool);
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7
				if(mostPopularNextTool == null)
					ret += "No tools are used after this one"+ "<br>";
				else
					ret += "The most popular tool used next to this tool: "
						+ mostPopularNextTool.getName() + "<br>";

<<<<<<< HEAD
				Tool mostPopularPreviousTool = LoginManager.getFacade().getMostPopularPreviousTool(tool);
=======
				Tool mostPopularPreviousTool = LoginFactory.getUsageOps().getMostPopularPreviousTool(tool);
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7
				if(mostPopularPreviousTool == null)
					ret += "No tools are used before this one"+ "<br>";
				else
					ret += "The most popular tool used before this tool: "
						+ mostPopularPreviousTool.getName();

				return ret;
			}

		};
<<<<<<< HEAD
		worker.run();
=======
		worker.execute();
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7
	}


	public WorkflowStatistics() {
		$$$setupUI$$$();
		popularTools.setBackground(this.getBackground());
		popularTools.setCellRenderer(new ToolCellRenderer());

		popularFirstTools.setBackground(this.getBackground());
		popularFirstTools.setCellRenderer(new ToolCellRenderer());

<<<<<<< HEAD
		popularWorkflows.setBackground(this.getBackground());
		popularWorkflows.setCellRenderer(new ToolCellRenderer());
		popToolsPanel.setBorder(BorderFactory.createEtchedBorder());
		popFirstToolsPan.setBorder(BorderFactory.createEtchedBorder());
		popWFPanel.setBorder(BorderFactory.createEtchedBorder());
=======
		popToolsPanel.setBorder(BorderFactory.createEtchedBorder());
		popFirstToolsPan.setBorder(BorderFactory.createEtchedBorder());
		popWFPanel.setBorder(BorderFactory.createEtchedBorder());
		
		popWorkflows.setDisabledTextColor(Color.black);
		popWorkflows.setBackground(this.getBackground());

		 Font font = UIManager.getFont("Label.font");
	        String bodyRule = "body { font-family: " + font.getFamily() + "; " +
	                "font-size: " + font.getSize() + "pt; padding: 0; margin: 0;} ";
	        ((HTMLDocument)popWorkflows.getDocument()).getStyleSheet().addRule(bodyRule);
	        String liRule = "ol { font-family: " + font.getFamily() + "; " +
            "font-size: " + font.getSize() + "pt; padding: 1em; margin: 20px;} ";
    ((HTMLDocument)popWorkflows.getDocument()).getStyleSheet().addRule(liRule);

>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7
		statsPan.setBorder(BorderFactory.createEtchedBorder());
		toolListing.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				updateItemStats();
			}
		});
<<<<<<< HEAD
=======
		
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7
		toolListing.setRenderer(new NameRenderer());
		updateFormFields();
	}

	private void $$$setupUI$$$() {
		JPanel panel1 = this;
<<<<<<< HEAD
		panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(5,
				4, new Insets(0, 0, 0, 0), -1, -1));
		popWFPanel = new JPanel();
		popWFPanel
				.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(
						2, 1, new Insets(0, 0, 0, 0), -1, -1));
		panel1.add(
				popWFPanel,
				new com.intellij.uiDesigner.core.GridConstraints(
						1,
						1,
						1,
						2,
						com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER,
						com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
						null, null, null, 0, false));
		final JLabel label1 = new JLabel();
		label1.setFont(new Font(label1.getFont().getName(), Font.BOLD, 16));
		label1.setText("Most Popular Workflows");
		popWFPanel
				.add(label1,
						new com.intellij.uiDesigner.core.GridConstraints(
								0,
								0,
								1,
								1,
								com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER,
								com.intellij.uiDesigner.core.GridConstraints.FILL_NONE,
								com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW,
								com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
								null, null, null, 0, false));
		popularWorkflows = new JList();
		popWFPanel
				.add(popularWorkflows,
						new com.intellij.uiDesigner.core.GridConstraints(
								1,
								0,
								1,
								1,
								com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER,
								com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH,
								com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW,
								com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
								null, new Dimension(150, 200), null, 0, false));
		popToolsPanel = new JPanel();
		popToolsPanel
				.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(
						2, 1, new Insets(0, 0, 0, 0), -1, -1));
		panel1.add(
				popToolsPanel,
				new com.intellij.uiDesigner.core.GridConstraints(
						0,
						1,
						1,
						1,
						com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER,
						com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK
								| com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
						null, null, null, 0, false));
		final JLabel label2 = new JLabel();
		label2.setFont(new Font(label2.getFont().getName(), Font.BOLD, 16));
		label2.setText("Most Popular Tools");
		popToolsPanel
				.add(label2,
						new com.intellij.uiDesigner.core.GridConstraints(
								0,
								0,
								1,
								1,
								com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER,
								com.intellij.uiDesigner.core.GridConstraints.FILL_NONE,
								com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
								com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
								null, new Dimension(175, 17), null, 0, false));
		popularTools = new JList();
		popToolsPanel
				.add(popularTools,
						new com.intellij.uiDesigner.core.GridConstraints(
								1,
								0,
								1,
								1,
								com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER,
								com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH,
								com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW,
								com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
								null, new Dimension(175, 200), null, 0, false));
		popFirstToolsPan = new JPanel();
		popFirstToolsPan
				.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(
						2, 1, new Insets(0, 0, 0, 0), -1, -1));
		panel1.add(
				popFirstToolsPan,
				new com.intellij.uiDesigner.core.GridConstraints(
						0,
						2,
						1,
						1,
						com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER,
						com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK
								| com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW,
						1, null, null, null, 0, false));
		final JLabel label3 = new JLabel();
		label3.setFont(new Font(label3.getFont().getName(), Font.BOLD, 16));
		label3.setText("Most Popular Tools at Start of Workflow");
		popFirstToolsPan
				.add(label3,
						new com.intellij.uiDesigner.core.GridConstraints(
								0,
								0,
								1,
								1,
								com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER,
								com.intellij.uiDesigner.core.GridConstraints.FILL_NONE,
								com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
								com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
								null, null, null, 0, false));
		popularFirstTools = new JList();
		popFirstToolsPan
				.add(popularFirstTools,
						new com.intellij.uiDesigner.core.GridConstraints(
								1,
								0,
								1,
								1,
								com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER,
								com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH,
								com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW,
								com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
								null, new Dimension(175, 200), null, 0, false));
		final JSeparator separator1 = new JSeparator();
		panel1.add(
				separator1,
				new com.intellij.uiDesigner.core.GridConstraints(
						2,
						1,
						1,
						2,
						com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER,
						com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
						null, null, null, 0, false));
		statsPan = new JPanel();
		statsPan.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(
				3, 1, new Insets(0, 0, 0, 0), -1, -1));
		panel1.add(
				statsPan,
				new com.intellij.uiDesigner.core.GridConstraints(
						3,
						1,
						1,
						2,
						com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER,
						com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK
								| com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK
								| com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW,
						null, null, null, 0, false));
		final JLabel label4 = new JLabel();
		label4.setFont(new Font(label4.getFont().getName(), Font.BOLD, 16));
		label4.setText("Individual Tool Statistics");
		statsPan.add(label4, new com.intellij.uiDesigner.core.GridConstraints(
				0, 0, 1, 1,
				com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER,
				com.intellij.uiDesigner.core.GridConstraints.FILL_NONE,
				com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
				com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
				null, null, null, 0, false));
		toolListing = new JComboBox();
		statsPan.add(
				toolListing,
				new com.intellij.uiDesigner.core.GridConstraints(
						1,
						0,
						1,
						1,
						com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST,
						com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
						null, null, null, 0, false));
		toolStats = new JLabel();
		toolStats.setText("Select a tool to see its statistics");
		statsPan.add(
				toolStats,
				new com.intellij.uiDesigner.core.GridConstraints(
						2,
						0,
						1,
						1,
						com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER,
						com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
						null, null, null, 0, false));
		final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
		panel1.add(
				spacer1,
				new com.intellij.uiDesigner.core.GridConstraints(
						1,
						3,
						1,
						1,
						com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER,
						com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW,
						1, null, null, null, 0, false));
		final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
		panel1.add(
				spacer2,
				new com.intellij.uiDesigner.core.GridConstraints(
						1,
						0,
						1,
						1,
						com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER,
						com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW,
						1, null, new Dimension(78, 11), null, 0, false));
		final com.intellij.uiDesigner.core.Spacer spacer3 = new com.intellij.uiDesigner.core.Spacer();
		panel1.add(
				spacer3,
				new com.intellij.uiDesigner.core.GridConstraints(
						4,
						2,
						1,
						1,
						com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER,
						com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL,
						1,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW,
						null, null, null, 0, false));
=======
		panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(5, 4, new Insets(0, 0, 0, 0), -1, -1));
        panel1.setEnabled(false);
        popWFPanel = new JPanel();
        popWFPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(popWFPanel, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setFont(new Font(label1.getFont().getName(), Font.BOLD, 16));
        label1.setText("Most Popular Workflows");
        popWFPanel.add(label1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        popWorkflows = new JTextPane();
        popWorkflows.setContentType("text/html");
        popWorkflows.setEditable(false);
        popWorkflows.setEnabled(false);
        popWorkflows.setText("<html>\n  <head>\n    \n  </head>\n  <body>\n    <p style=\"margin-top: 0\">\n      Loading...\n    </p>\n  </body>\n</html>\n");
        popWFPanel.add(popWorkflows, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(250, 200), null, 0, false));
        popToolsPanel = new JPanel();
        popToolsPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(popToolsPanel, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setFont(new Font(label2.getFont().getName(), Font.BOLD, 16));
        label2.setText("Most Popular Tools");
        popToolsPanel.add(label2, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(175, 17), null, 0, false));
        popularTools = new JList();
        popToolsPanel.add(popularTools, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(175, 200), null, 0, false));
        popFirstToolsPan = new JPanel();
        popFirstToolsPan.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(popFirstToolsPan, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, 1, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setFont(new Font(label3.getFont().getName(), Font.BOLD, 16));
        label3.setText("Most Popular Tools at Start of Workflow");
        popFirstToolsPan.add(label3, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        popularFirstTools = new JList();
        popFirstToolsPan.add(popularFirstTools, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(175, 200), null, 0, false));
        final JSeparator separator1 = new JSeparator();
        panel1.add(separator1, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        statsPan = new JPanel();
        statsPan.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(statsPan, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setFont(new Font(label4.getFont().getName(), Font.BOLD, 16));
        label4.setText("Individual Tool Statistics");
        statsPan.add(label4, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        toolListing = new JComboBox();
        statsPan.add(toolListing, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        toolStats = new JLabel();
        toolStats.setText("Select a tool to see its statistics");
        statsPan.add(toolStats, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        panel1.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(1, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        panel1.add(spacer2, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 1, false));
        final com.intellij.uiDesigner.core.Spacer spacer3 = new com.intellij.uiDesigner.core.Spacer();
        panel1.add(spacer3, new com.intellij.uiDesigner.core.GridConstraints(4, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7
	}

	@Override
	public Component getComponent() {
		return this;
	}
<<<<<<< HEAD
	private static ToolInformationRemote facade;
	public static ToolInformationRemote getFacade()
	{
		if(facade == null)
			facade = (ToolInformationRemote) GenSpace.getRemote("ToolInformation");
=======
	private static UsageInformationRemote facade;
	public static UsageInformationRemote getFacade()
	{
		if(facade == null)
			facade = (UsageInformationRemote) GenSpace.getRemote("ToolInformation");
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7
		return facade;
	}


}
