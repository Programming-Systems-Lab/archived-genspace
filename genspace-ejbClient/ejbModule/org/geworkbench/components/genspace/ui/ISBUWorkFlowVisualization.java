package org.geworkbench.components.genspace.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.geworkbench.components.genspace.LoginFactory;
import org.geworkbench.components.genspace.RuntimeEnvironmentSettings;
import org.geworkbench.components.genspace.entity.Tool;
import org.geworkbench.engine.config.VisualPlugin;

public class ISBUWorkFlowVisualization extends JPanel implements VisualPlugin,
		Runnable {

	private Log log = LogFactory.getLog(this.getClass());

	// "ALL TOOLS" - control panel
	private JPanel top3ControlPanel = new JPanel();
	private JLabel top3Label = new JLabel("ALL TOOLS: ");
	private JButton top3Button = new JButton("See the top 3 of all tools");

	// "ALL TOOLS" - result panels
	private JPanel top3Panel = new JPanel();
	private JPanel top3Panel_1 = new JPanel();
	private JPanel top3Panel_2 = new JPanel();
	private JPanel top3SubPanel1 = new JPanel();
	private JPanel top3SubPanel2 = new JPanel();
	private JPanel top3SubPanel3 = new JPanel();
	private JLabel top3Tool = new JLabel("Top3 Most Popular Tools");
	private JLabel top3WF = new JLabel("Top3 Most Popular Workflows");
	private JLabel top3ToolAsHead = new JLabel(
			"Top3 Most Popular Tools at Start of Workflows");
	private JLabel top1 = new JLabel("1: ");
	private JLabel top2 = new JLabel("2: ");
	private JLabel top3 = new JLabel("3: ");
	private JLabel top11 = new JLabel("1: ");
	private JLabel top22 = new JLabel("2: ");
	private JLabel top33 = new JLabel("3: ");
	private JLabel top111 = new JLabel("1: ");
	private JLabel top222 = new JLabel("2: ");
	private JLabel top333 = new JLabel("3: ");

	// AN INDIVISUAL TOOL
	private JPanel singleControlPanel = new JPanel();
	private JLabel singleLabel = new JLabel("AN INDIVIDUAL TOOL: ");
	private JPanel singleToolPanel = new JPanel();
	private JPanel subSingleToolPanel = new JPanel();
	private JLabel single1 = new JLabel("Total usage rate:<br> "
	+"Total usage rate at start of workflow:<br>"
	+"The most popular tool used next to this tool:<br>"
	+"The most popular tool used before this tool:<br>");
	private JLabel message = new JLabel(
			"*The ranking of tools and workflows in the \"ALL TOOLS\" section is based on an exponential time-decay function.");
	private JLabel message1 = new JLabel(
			"The \"INDIVIDUAL TOOL\" section shows the raw data for a tool usage, which is not exponentially weighted.");

	// All the above Panels are addded to the northPanel
	private JPanel northPanel = new JPanel();

	// JPanel to display the disclaimer message
	private JPanel messagePanel = new JPanel();

	private JComboBox tools = new JComboBox();


	List<Tool> allAnalysisTools = null; 

	public ISBUWorkFlowVisualization() {
		log.debug("Workflow Visualization started");
	}

	@Override
	public void run() {
		initComponents();
	}

	private void initComponents() {
		setLayout(new BorderLayout());
		tools.addItem("-- select tool --");
		tools.setRenderer(new NameRenderer());
		SwingWorker<List<Tool>,Void> worker = new SwingWorker<List<Tool>, Void>()
		{
			@Override
			protected void done() {
				try {
					for (Tool t : get())
						tools.addItem(t);
				} catch (InterruptedException e) {

				} catch (ExecutionException e) {
				}
				
				super.done();
			}
			@Override
			protected List<Tool> doInBackground() throws Exception {
				return LoginFactory.getUsageOps().getAllTools();
			}
			
		};
		
		worker.execute();
		
		tools.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {

				SwingWorker<String, Void> worker = new SwingWorker<String, Void>() {
					@Override
					protected void done() {
						try {
							single1.setText(get());
						} catch (InterruptedException e) {
						} catch (ExecutionException e) {
						}
						super.done();
					}
					@Override
					public String doInBackground() {

						try {
							if (tools.getSelectedItem().equals("-- select tool --")) {
								String ret = "Total usage rate:<br> ";

								ret += ("Total usage rate at start of workflow:<br>");
								ret += ("The most popular tool used next to this tool:<br>");
								ret += ("The most popular tool used before this tool:<br>");
								return ret;

							} else {
								Tool tool = (Tool) tools.getSelectedItem();
								String ret = "";
								String usageRate = "" + tool.getUsageCount();
								ret += "Total usage rate: " + usageRate + "<br>";

								String usageRateAsWFHead = "" + tool.getWfCountHead();
								ret += "Total usage rate at start of workflow: "
										+ usageRateAsWFHead + " <br>";

								Tool mostPopularNextTool = LoginFactory.getUsageOps().getMostPopularNextTool(tool);
								if(mostPopularNextTool == null)
									ret += "No tools are used after this one"+ "<br>";
								else
									ret += "The most popular tool used next to this tool: "
										+ mostPopularNextTool.getName() + "<br>";

								Tool mostPopularPreviousTool = LoginFactory.getUsageOps().getMostPopularPreviousTool(tool);
								if(mostPopularPreviousTool == null)
									ret += "No tools are used before this one"+ "<br>";
								else
									ret += "The most popular tool used before this tool: "
										+ mostPopularPreviousTool.getName();

								return ret;

							}
						} catch (Exception e) {
							e.printStackTrace();
						}
						return null;
					}
				};
				worker.execute();
			}
		});
		/* K added (END)-singleTool */

		top3Panel.setLayout(new BoxLayout(top3Panel, BoxLayout.Y_AXIS)); // K
		// top3SubPanel1.setBackground(Color.RED); //K

		top3ControlPanel.setBackground(Color.LIGHT_GRAY);
//		top3Button.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent topE) {
//
//				SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
//					@Override
//					public Void doInBackground() {
//
//						/* K added START */
//						// KTop3 top3 = new KTop3();
//						top3ToolString = new ArrayList();
//						try {
//							top3ToolString = getTop3MostPopularTools();
//							for (int i = 0; i < 3; i++) {
//								// System.out.println("****************");
//								// System.out.println(top3ToolString.get(i));
//								// System.out.println("****************");
//								if (i == 0)
//									top1.setText("1: "
//											+ (String) top3ToolString.get(i));
//								if (i == 1)
//									top2.setText("2: "
//											+ (String) top3ToolString.get(i));
//								if (i == 2)
//									top3.setText("3: "
//											+ (String) top3ToolString.get(i));
//							}
//						} catch (Exception except) {
//
//						}
//						top3ToolAsHeadString = new ArrayList();
//						try {
//							top3ToolAsHeadString = getTop3MostPopularWFHead();
//							for (int i = 0; i < 3; i++) {
//								// System.out.println("****************");
//								// System.out.println((String)top3ToolString.get(i));
//								// System.out.println("****************");
//								if (i == 0)
//									top11.setText("1: "
//											+ (String) top3ToolAsHeadString
//													.get(i));
//								if (i == 1)
//									top22.setText("2: "
//											+ (String) top3ToolAsHeadString
//													.get(i));
//								if (i == 2)
//									top33.setText("3: "
//											+ (String) top3ToolAsHeadString
//													.get(i));
//							}
//						} catch (Exception except) {
//
//						}
//						top3WFString = new ArrayList();
//						try {
//							top3WFString = getTop3MostPopularWF();
//							// System.out.println(top3WFString.size());
//							// top111.setText("1: "+
//							// (String)top3WFString.get(0));
//							// top222.setText("2: "+
//							// (String)top3WFString.get(1));
//							// top333.setText("3: "+
//							// (String)top3WFString.get(2));
//							// System.out.println((String)top3WFString.get(0));
//							// System.out.println((String)top3WFString.get(1));
//							// System.out.println((String)top3WFString.get(2));
//							String s3 = (String) top3WFString.get(2);
//
//							for (int i = 0; i < 3; i++) {
//								// System.out.println("****************");
//								// System.out.println(top3WFString.get(i));
//								// System.out.println("****************");
//								if (i == 0) {
//									// System.out.println("No1No1No1No1");
//									top111.setText("1: "
//											+ (String) top3WFString.get(i));
//								}
//								if (i == 1) {
//									// System.out.println("No2No2No2No2");
//									top222.setText("2: "
//											+ (String) top3WFString.get(i));
//								} // ARACNE,MRA Analysis,MRA Analysis,T Test
//									// Analysis,T
//									// Test Analysis,T Test Analysis,T Test
//									// Analysis,
//									// "123456789 123456789 123456789 123456789 123456789 123456789"
//								if (i == 2) {
//									// System.out.println("No3No3No3No3" + s3);
//									top333.setText("3: " + s3);
//								}
//
//							}
//
//						} catch (Exception except) {
////							System.out.println("$$$$$$$$$$$$$$$$Exception");
//							except.printStackTrace();
//						}
//						/* Koichrio added END */
//						return null;
//					}
//				};
//				worker.execute();
//			}
//		});
		top3Label.setFont(new Font("Courier New", Font.BOLD, 20));
		top3Label.setForeground(Color.RED);

		top3ControlPanel.setLayout(new FlowLayout());

		top3ControlPanel.add(top3Label);
		top3ControlPanel.add(top3Button);

		singleControlPanel.setBackground(Color.LIGHT_GRAY);
		singleControlPanel.setLayout(new FlowLayout());
		singleLabel.setFont(new Font("Courier New", Font.BOLD, 20));
		singleLabel.setForeground(Color.BLUE);
		singleControlPanel.add(singleLabel);
		singleControlPanel.add(tools);

		top3SubPanel1.setBackground(Color.PINK); // K
		top3SubPanel2.setBackground(Color.PINK); // K
		top3SubPanel3.setBackground(Color.PINK); // K
		top3SubPanel1.setBorder(new TitledBorder(new EtchedBorder(), ""));
		top3SubPanel2.setBorder(new TitledBorder(new EtchedBorder(), ""));
		top3SubPanel3.setBorder(new TitledBorder(new EtchedBorder(), ""));

		top3Tool.setFont(new Font("Courier New", Font.BOLD, 12));
		top3SubPanel1.setLayout(new BoxLayout(top3SubPanel1, BoxLayout.Y_AXIS));
		top3SubPanel1.add(top3Tool);
		top3SubPanel1.add(top1);
		top3SubPanel1.add(top2);
		top3SubPanel1.add(top3);

		top3ToolAsHead.setFont(new Font("Courier New", Font.BOLD, 12));
		top3SubPanel2.setLayout(new BoxLayout(top3SubPanel2, BoxLayout.Y_AXIS));
		top3SubPanel2.add(top3ToolAsHead);
		top3SubPanel2.add(top11);
		top3SubPanel2.add(top22);
		top3SubPanel2.add(top33);

		top3WF.setFont(new Font("Courier New", Font.BOLD, 12));
		top3SubPanel3.setLayout(new BoxLayout(top3SubPanel3, BoxLayout.Y_AXIS));
		top3SubPanel3.add(top3WF);
		top3SubPanel3.add(top111);
		top3SubPanel3.add(top222);
		top3SubPanel3.add(top333);

		top3Panel_1.add(top3SubPanel1);
		top3Panel_1.add(top3SubPanel2);
		top3Panel_2.add(top3SubPanel3);
		top3Panel.add(top3Panel_1);
		top3Panel.add(top3Panel_2);

		subSingleToolPanel.setBorder(new TitledBorder(new EtchedBorder(), ""));
		subSingleToolPanel.setBackground(new Color(149, 174, 226));
		subSingleToolPanel.setLayout(new BoxLayout(subSingleToolPanel,
				BoxLayout.Y_AXIS));
		// singleToolPanel.add(singleTool);
		single1.setFont(new Font("Courier New", Font.BOLD, 12));
		subSingleToolPanel.add(single1);

		singleToolPanel.setLayout(new FlowLayout());
		singleToolPanel.add(subSingleToolPanel);

		northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS)); // K
		northPanel.add(top3ControlPanel); // K
		northPanel.add(top3Panel); // K
		northPanel.add(singleControlPanel); // K
		northPanel.add(singleToolPanel); // K
		// northPanel.add(selectPanel); // K

		messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.Y_AXIS));
		messagePanel.add(message);
		messagePanel.add(message1);

		// setBackground(Color.CYAN);
		add(northPanel, BorderLayout.CENTER); // K
		add(messagePanel, BorderLayout.SOUTH);
		// add(graphPanel, BorderLayout.CENTER);

	}


	@Override
	public Component getComponent() {
		return this;
	}

}
