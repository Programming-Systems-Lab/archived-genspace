package org.geworkbench.components.genspace.ui;

import javax.swing.*;
import org.geworkbench.components.genspace.RuntimeEnvironmentSettings;
import org.geworkbench.engine.config.VisualPlugin;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

class ToolCellRenderer implements ListCellRenderer {

	@Override
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		JLabel ret = new JLabel((index + 1) + ": " + (String) value);
		return ret;
	}

}

public class WorkflowStatistics extends JPanel implements VisualPlugin {
	private JList popularFirstTools;
	private JList popularTools;
	private JList popularWorkflows;
	private JComboBox toolListing;
	private JLabel toolStats;
	private JPanel popToolsPanel;
	private JPanel popWFPanel;
	private JPanel popFirstToolsPan;
	private JPanel statsPan;

	private void updateAllToolList() {
		SwingWorker<ArrayList<String>, Void> worker = new SwingWorker<ArrayList<String>, Void>() {

			@Override
			protected void done() {
				try {
					ArrayList<String> results = get();
					DefaultComboBoxModel m = new DefaultComboBoxModel();
					m.addElement("");
					for (String s : results) {
						m.addElement(s);
					}
					toolListing.setModel(m);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				super.done();
			}

			@Override
			protected ArrayList<String> doInBackground() throws Exception {
				// TODO Auto-generated method stub
				return getAllTools();
			}

		};
		worker.run();
	}

	private void updatePopularTools() {
		SwingWorker<ArrayList<String>, Void> worker = new SwingWorker<ArrayList<String>, Void>() {

			@Override
			protected void done() {
				try {
					ArrayList<String> results = get();
					DefaultComboBoxModel m = new DefaultComboBoxModel();
					int lim = 10;
					for (String s : results) {
						m.addElement(s);
						lim--;
						if (lim <= 0)
							break;
					}
					popularTools.setModel(m);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				super.done();
			}

			@Override
			protected ArrayList<String> doInBackground() throws Exception {
				// TODO Auto-generated method stub
				return getMostPopularTools();
			}

		};
		worker.run();
	}

	private void updatePopularWorkflows() {
		SwingWorker<ArrayList<String>, Void> worker = new SwingWorker<ArrayList<String>, Void>() {

			@Override
			protected void done() {
				try {
					ArrayList<String> results = get();
					DefaultComboBoxModel m = new DefaultComboBoxModel();
					int lim = 10;
					for (String s : results) {
						m.addElement(s);
						lim--;
						if (lim <= 0)
							break;
					}
					popularWorkflows.setModel(m);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				super.done();
			}

			@Override
			protected ArrayList<String> doInBackground() throws Exception {
				// TODO Auto-generated method stub
				return getMostPopularWorkflows();
			}

		};
		worker.run();
	}

	private void updatePopularWFHeads() {
		SwingWorker<ArrayList<String>, Void> worker = new SwingWorker<ArrayList<String>, Void>() {

			@Override
			protected void done() {
				try {
					ArrayList<String> results = get();
					DefaultComboBoxModel m = new DefaultComboBoxModel();
					int lim = 10;
					for (String s : results) {
						m.addElement(s);
						lim--;
						if (lim <= 0)
							break;
					}
					popularFirstTools.setModel(m);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				super.done();
			}

			@Override
			protected ArrayList<String> doInBackground() throws Exception {
				// TODO Auto-generated method stub
				return getMostPopularWFHeads();
			}

		};
		worker.run();
	}

	public void updateFormFields() {
		updateAllToolList();
		updatePopularTools();
		updatePopularWFHeads();
		updatePopularWorkflows();
	}

	private void updateItemStats() {
		toolStats.setText("Loading...");
		final String tool = (String) toolListing.getSelectedItem();
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				super.done();
			}

			@Override
			protected String doInBackground() throws Exception {
				String ret = "";
				String usageRate = getUsageRate(tool);
				ret += "Total usage rate: " + usageRate + "<br>";

				String usageRateAsWFHead = getUsageRateAsWFHead(tool);
				ret += "Total usage rate at start of workflow: "
						+ usageRateAsWFHead + " <br>";

				String mostPopularNextTool = getMostPopularNextTool(tool);
				ret += "The most popular tool used next to this tool: "
						+ mostPopularNextTool + "<br>";

				String mostPopularPreviousTool = getMostPopularPreviousTool(tool);
				ret += "The most popular tool used before this tool: "
						+ mostPopularPreviousTool;

				return ret;
			}

		};
		SwingUtilities.invokeLater(worker);
	}

	private String getMostPopularNextTool(String toolApplied) throws Exception {

		clientSideID = "feature2,getMostPopularNextTool," + toolApplied;
		Socket s = new Socket(serverIP, serverPort);
		ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
		oos.writeObject(clientSideID);
		oos.flush();

		ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
		String resultValue = (String) ois.readObject();

		return resultValue;
	}

	private String getMostPopularPreviousTool(String toolApplied)
			throws Exception {

		clientSideID = "feature2,getMostPopularPreviousTool," + toolApplied;
		Socket s = new Socket(serverIP, serverPort);
		ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
		oos.writeObject(clientSideID);
		oos.flush();
		ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
		String resultValue = (String) ois.readObject();

		return resultValue;
	}

	public WorkflowStatistics() {
		$$$setupUI$$$();
		popularTools.setBackground(this.getBackground());
		popularTools.setCellRenderer(new ToolCellRenderer());

		popularFirstTools.setBackground(this.getBackground());
		popularFirstTools.setCellRenderer(new ToolCellRenderer());

		popularWorkflows.setBackground(this.getBackground());
		popularWorkflows.setCellRenderer(new ToolCellRenderer());
		popToolsPanel.setBorder(BorderFactory.createEtchedBorder());
		popFirstToolsPan.setBorder(BorderFactory.createEtchedBorder());
		popWFPanel.setBorder(BorderFactory.createEtchedBorder());
		statsPan.setBorder(BorderFactory.createEtchedBorder());
		toolListing.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				updateItemStats();
			}
		});

		updateFormFields();
	}

	private void $$$setupUI$$$() {
		JPanel panel1 = this;
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
	}

	@Override
	public Component getComponent() {
		// TODO Auto-generated method stub
		return this;
	}

	/**
	 * Connects to the server and gets a list of all the analysis tools
	 */
	private ArrayList<String> getAllTools() {
		ArrayList<String> allTools = new ArrayList<String>();

		// connect to the server and get the info we need
		PrintWriter out = null;
		Socket s = null;
		try {
			s = new Socket(HOST, PORT);
			out = new java.io.PrintWriter(s.getOutputStream());

			// send the action keyword and the name of the tool
			out.write("TOOLS\n");
			out.flush();

			// read in the response and store the values in an ArrayList
			Scanner in = new Scanner(s.getInputStream());
			while (in.hasNext()) {
				String line = in.nextLine();
				// System.out.println(line);
				if (line.equals("END"))
					break;
				allTools.add(line);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			// TODO: handle the error more gracefully
		} finally {
			try {
				out.close();
			} catch (Exception ex) {
			}
			try {
				s.close();
			} catch (Exception ex) {
			}
		}
		return allTools;
	}

	// K added
	private ArrayList<String> getMostPopularTools() throws Exception {

		// now we test the method "getTop3MostPopularTools"
		clientSideID = "getMostPopularTools";
		Socket s = new Socket(serverIP, serverPort);
		ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
		oos.writeObject(clientSideID);
		oos.flush();
		// System.out.println("client request sent");

		// waiting for server response...
		ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
		// System.out.println("waiting response from server.....");
		ArrayList<String> listBack = (ArrayList<String>) ois.readObject();
		// System.out.println("response from server received: Top 3 most Pop Tools: "
		// + listBack.toString());
		// System.out.println();
		return listBack;
	}

	// K added
	private static ArrayList<String> getMostPopularWFHeads() throws Exception {

		// now we test the method "getTop3MostPopularWFHead"
		clientSideID = "getMostPopularWFHead";
		Socket s = new Socket(serverIP, serverPort);
		ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
		oos.writeObject(clientSideID);
		oos.flush();
		// System.out.println("client request sent");

		// waiting for server response...
		ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
		// System.out.println("waiting response from server.....");
		ArrayList<String> listBack = (ArrayList<String>) ois.readObject();
		// System.out.println("response from server received: top 3 most Pop WF heads: "
		// + listBack.toString());
		// System.out.println();
		return listBack;
	}

	// K added
	private ArrayList<String> getMostPopularWorkflows() throws Exception {

		// now we test the method "getTop3MostPopularWF"
		clientSideID = "getMostPopularWF";
		Socket s = new Socket(serverIP, serverPort);
		ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
		oos.writeObject(clientSideID);
		oos.flush();
		// System.out.println("client request sent");

		// waiting for server response...
		ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
		// System.out.println("waiting response from server.....");
		ArrayList<String> listBack = (ArrayList<String>) ois.readObject();
		// System.out.println("@@@@@response from server received: top 3 most Pop WFs: "
		// + listBack.toString());
		// System.out.println();
		return listBack;
	}

	private ArrayList<String> getAllAnalysisTools() throws Exception {

		clientSideID = "getAllAnalysisTools";
		Socket s = new Socket(serverIP, serverPort);
		ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
		oos.writeObject(clientSideID);
		oos.flush();

		ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
		ArrayList<String> listBack = (ArrayList) ois.readObject();

		return listBack;
	}

	// K added from SuggestionBasedOnSingleTool
	private String getUsageRate(String toolApplied) throws Exception {

		clientSideID = "feature2,getUsageRate," + toolApplied;// zhu yi kong ge
		// shi zi dai de
		// ...
		Socket s = new Socket(serverIP, serverPort);
		ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
		oos.writeObject(clientSideID);
		oos.flush();
		// System.out.println("client request sent");

		// waiting for server response...
		ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
		// System.out.println("waiting response from server.....");
		String resultValue = (String) ois.readObject();
		// System.out.println("response from server received: usage rate for this tool: "
		// + resultValue);
		// System.out.println();
		return resultValue;

	}

	// K added from SuggestionBasedOnSingleTool
	private String getUsageRateAsWFHead(String toolApplied) throws Exception {

		clientSideID = "feature2,getUsageRateAsWFHead," + toolApplied;// zhu yi
		// kong
		// ge
		// shi
		// zi
		// dai
		// de
		// ...
		Socket s = new Socket(serverIP, serverPort);
		ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
		oos.writeObject(clientSideID);
		oos.flush();
		// System.out.println("client request sent");

		// waiting for server response...
		ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
		// System.out.println("waiting response from server.....");
		String resultValue = (String) ois.readObject();
		// System.out.println("response from server received: usage rate for this tool as WF head: "
		// + resultValue);
		// System.out.println();
		return resultValue;
	}

	private static String clientSideID = null; // K
	private static final int PORT = RuntimeEnvironmentSettings.WORKFLOW_VIS_SERVER
			.getPort();
	private static final String HOST = RuntimeEnvironmentSettings.WORKFLOW_VIS_SERVER
			.getHost();
	private static String serverIP = RuntimeEnvironmentSettings.ISBU_SERVER
			.getHost();
	private static int serverPort = RuntimeEnvironmentSettings.ISBU_SERVER
			.getPort(); // K

}
