package org.geworkbench.components.genetegrate;

import org.geworkbench.bison.datastructure.biocollections.sequences.DSSequenceSet;
import org.geworkbench.bison.datastructure.biocollections.sequences.CSSequenceSet;
import org.geworkbench.bison.datastructure.biocollections.DSCollection;
import org.geworkbench.bison.datastructure.biocollections.Collection;
import org.geworkbench.bison.datastructure.biocollections.DSDataSet;
import org.geworkbench.bison.datastructure.bioobjects.sequence.CSSequence;
import org.geworkbench.bison.datastructure.bioobjects.sequence.DSSequence;
import org.geworkbench.bison.datastructure.bioobjects.markers.DSGeneMarker;
import org.geworkbench.bison.datastructure.complex.pattern.sequence.CSSeqRegistration;
import org.geworkbench.bison.datastructure.complex.pattern.sequence.DSMatchedSeqPattern;
import org.geworkbench.bison.datastructure.complex.pattern.DSMatchedPattern;
import org.geworkbench.bison.datastructure.complex.panels.DSPanel;
import org.geworkbench.util.patterns.PatternSequenceDisplayUtil;
import org.geworkbench.util.patterns.CSMatchedSeqPattern;
import org.geworkbench.util.patterns.PatternOperations;
import org.geworkbench.util.patterns.PatternLocations;
import org.geworkbench.util.Util;
import org.geworkbench.util.sequences.SequenceViewWidgetPanel;
import org.geworkbench.util.sequences.PDBFileGenerator;
import org.geworkbench.engine.management.Subscribe;
import org.geworkbench.events.GeneSelectorEvent;
import org.geworkbench.events.MicroarraySetViewEvent;
import org.geworkbench.events.SequenceDiscoveryTableEvent;
import org.apache.axis.types.URI;

import javax.swing.*;
import java.util.HashMap;
import java.util.TreeSet;
import java.util.Date;
import java.awt.event.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.beans.PropertyChangeEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.net.URL;
import java.rmi.RemoteException;

import gov.nih.nci.cagrid.client.*;

/**
 * <p>
 * Widget provides all GUI services for sequence panel displays.
 * </p>
 * <p>
 * Widget is controlled by its associated component, SequenceViewAppComponent
 * </p>
 * <p>
 * Copyright: Copyright (c) 2003
 * </p>
 * <p>
 * Company: Califano Lab
 * </p>
 * 
 * @author
 * @version 1.0
 */

public class GenetegrateViewWidget extends JPanel {
	private HashMap listeners = new HashMap();
	private ActionListener listener = null;
	private final int xOff = 60;
	private final int yOff = 20;
	private final int xStep = 5;
	private final int yStep = 14;
	private int prevSeqId = -1;
	private int prevSeqDx = 0;
	private DSSequenceSet sequenceDB = new CSSequenceSet();
	private DSSequenceSet orgSequenceDB = new CSSequenceSet();
	private DSSequenceSet displaySequenceDB = new CSSequenceSet();
	public HashMap<CSSequence, PatternSequenceDisplayUtil> patternLocationsMatches;
	// Layouts
	private GridBagLayout gridBagLayout1 = new GridBagLayout();
	private GridBagLayout gridBagLayout2 = new GridBagLayout();
	private BorderLayout borderLayout1 = new BorderLayout();
	private BorderLayout borderLayout2 = new BorderLayout();
	private BorderLayout borderLayout3 = new BorderLayout();
	// Panels and Panes
	private GenetegrateViewWidget.JDetailPanel sequencedetailPanel = new GenetegrateViewWidget.JDetailPanel();
	private JPanel bottomPanel = new JPanel();
	private JButton leftShiftButton = new JButton();
	private JButton rightShiftButton = new JButton();
	private JScrollPane seqScrollPane = new JScrollPane();
	private JProgressBar serviceProgressBar = new JProgressBar();
	protected SequenceViewWidgetPanel seqViewWPanel = new SequenceViewWidgetPanel();
	public DSCollection<DSMatchedPattern<DSSequence, CSSeqRegistration>> selectedPatterns = new Collection<DSMatchedPattern<DSSequence, CSSeqRegistration>>();
	public JToolBar jToolBar1 = new JToolBar();
	private JToggleButton showAllBtn = new JToggleButton();
	private JCheckBox jAllSequenceCheckBox = new JCheckBox();
	private JComboBox functionViewComboBox = new JComboBox();
	private JButton executeButton = new JButton();
	private JButton stopButton = new JButton();
	private ImageIcon executeButtonIcon = Util
			.createImageIcon("/images/start.gif");
	private JLabel jViewLabel = new JLabel();
	private JComboBox jViewComboBox = new JComboBox();
	private static final String LINEVIEW = "Line";
	private static final String FULLVIEW = "Full Sequence";
	private static final String SWISSMODEL = "SWISSMODEL";
	private static final String ESYPRED = "ESYPRED";
	private static final String PUDGE = "PUDGE";
	// private static final String PUDGEURL =
	// "http://156.111.188.2:8080/wsrf/services/cagrid/PUDGEDEMO";
	// private static final String PUDGEURL =
	// "http://156.145.29.52:8080/wsrf/services/cagrid/HelloPUDGE";
	// private static final String PUDGEURL =
	// "http://156.145.29.52:8080/wsrf/services/cagrid/HelloPUDGE";
	private static final String PUDGEURL = "http://156.111.188.2:8080/wsrf/services/cagrid/HelloPUDGE";
	private static final String PUDGEWEBURL = "http://pudge2.cu-genome.org/pudge/fastaFile/";
	private JTextField jSequenceSummaryTextField = new JTextField();
	private boolean isLineView = true; // true is for LineView.
	private boolean onlyShowPattern = false;
	protected CSSequenceSet activeSequenceDB = null;
	protected boolean subsetMarkerOn = true;
	protected DSPanel<? extends DSGeneMarker> activatedMarkers = null;
	public static final String NONBASIC = "NONBASIC";
	private static final String LEFT = "left";
	private static final String RIGHT = "right";
	private boolean goLeft = false;
	private int xStartPoint = -1;
	private static final int GAP = 40;
	private String[] inputFileNames;
	private GenetegrateViewAppComponent genetegrateViewAppComponent;
	static String tempFolder = System.getProperties().getProperty(
			"temporary.files.directory");
	private static int GAPSECOND = 60000;
	private boolean isNormal = true;
	private boolean isStopped = false;
	private boolean normalEnding = true;
	static HashMap<String, String> map = new HashMap<String, String>();

	static {

		map.put("gi|42544167", "P06978");
		map.put("gi|71774083", "P06979");
		map.put("gi|46361980", "P06980");
	}

	public GenetegrateViewWidget(
			GenetegrateViewAppComponent _genetegrateViewAppComponent) {
		genetegrateViewAppComponent = _genetegrateViewAppComponent;
		try {
			jbInit();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	void jbInit() throws Exception {

		this.setLayout(borderLayout2);
		sequencedetailPanel.setBorder(BorderFactory.createEtchedBorder());
		sequencedetailPanel.setMinimumSize(new Dimension(50, 40));
		sequencedetailPanel.setPreferredSize(new Dimension(50, 40));
		seqScrollPane.setBorder(BorderFactory.createEtchedBorder());
		seqViewWPanel.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				jDisplayPanel_mouseClicked(e);
			}
		});
		this.addInputMethodListener(new java.awt.event.InputMethodListener() {
			public void inputMethodTextChanged(InputMethodEvent e) {
			}

			public void caretPositionChanged(InputMethodEvent e) {
				this_caretPositionChanged(e);
			}
		});
		this.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent e) {
				this_propertyChange(e);
			}
		});
		showAllBtn
				.setToolTipText("Push down to show sequences with selected patterns.");
		showAllBtn.setText("All / Matching Pattern");
		showAllBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showAllBtn_actionPerformed(e);
			}
		});
		jAllSequenceCheckBox.setToolTipText("Click to display all sequences.");
		jAllSequenceCheckBox.setSelected(false);
		jAllSequenceCheckBox.setText("All Sequences");
		executeButtonIcon = Util.createImageIcon("/images/start.gif");
		executeButton.setIcon(executeButtonIcon);
		executeButton.setMaximumSize(new Dimension(35, 35));
		// executeButton.setText("Run");
		// executeButton.setText("Run");

		stopButton.setMaximumSize(new Dimension(35, 35));
		stopButton.setMinimumSize(new Dimension(35, 35));
		stopButton.setPreferredSize(new Dimension(35, 35));
		stopButton.setToolTipText("Stop");
		ImageIcon stopButtonIcon = Util.createImageIcon("/images/stop.gif");
		stopButton.setMaximumSize(new Dimension(35, 35));
		stopButton.setIcon(stopButtonIcon);
		stopButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stopButton_actionPerformed(e);
			}
		});
		executeButton.setToolTipText("Run selected function.");
		jAllSequenceCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jAllSequenceCheckBox_actionPerformed(e);
			}
		});
		jViewLabel.setText("View: ");
		jSequenceSummaryTextField
				.setText("Move the mouse over to see details.");

		seqViewWPanel.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseMoved(MouseEvent e) {
				seqViewWPanel_mouseMoved(e);
			}
		});
		jViewComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jViewComboBox_actionPerformed(e);
			}
		});
		jViewComboBox.setToolTipText("Select a view to display results.");
		bottomPanel = new JPanel();
		leftShiftButton = new JButton();

		ImageIcon leftButtonIcon = Util.createImageIcon("/images/back.gif");
		leftShiftButton.setIcon(leftButtonIcon);
		ImageIcon rightButtonIcon = Util.createImageIcon("/images/forward.gif");
		rightShiftButton = new JButton();
		rightShiftButton.setIcon(rightButtonIcon);
		bottomPanel.setLayout(new BorderLayout());
		bottomPanel.add(leftShiftButton, BorderLayout.WEST);
		bottomPanel.add(sequencedetailPanel, BorderLayout.CENTER);
		bottomPanel.add(rightShiftButton, BorderLayout.EAST);
		leftShiftButton.setEnabled(false);
		rightShiftButton.setEnabled(false);
		leftShiftButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setMoveDirection(GenetegrateViewWidget.LEFT);
				updateBottomPanel();
			}
		});
		rightShiftButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setMoveDirection(GenetegrateViewWidget.RIGHT);
				updateBottomPanel();
			}
		});
		executeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setMoveDirection(GenetegrateViewWidget.RIGHT);
				invokeFunction();
			}
		});
		this.add(bottomPanel, BorderLayout.SOUTH);
		this.add(seqScrollPane, BorderLayout.CENTER);
		this.add(jToolBar1, BorderLayout.NORTH);

		serviceProgressBar.setOrientation(JProgressBar.HORIZONTAL);
		serviceProgressBar.setBorder(BorderFactory.createEtchedBorder());
		serviceProgressBar.setMaximumSize(new Dimension(32767, 35));
		serviceProgressBar.setMinimumSize(new Dimension(10, 35));
		serviceProgressBar.setPreferredSize(new Dimension(104, 35));
		serviceProgressBar.setStringPainted(true);
		jToolBar1.add(jViewLabel);
		jToolBar1.add(jViewComboBox);
		// jToolBar1.add(showAllBtn);
		jToolBar1.add(functionViewComboBox);
		jToolBar1.add(executeButton);
		jToolBar1.add(stopButton);
		jToolBar1.add(serviceProgressBar);
		jToolBar1.setMaximumSize(new Dimension(32767, 35));
		jToolBar1.setMinimumSize(new Dimension(10, 35));
		jToolBar1.setPreferredSize(new Dimension(300, 35));
		// jToolBar1.add(jAllSequenceCheckBox);
		// jToolBar1.addSeparator();
		// jToolBar1.add(jSequenceSummaryTextField);
		jViewComboBox.addItem(GenetegrateViewWidget.LINEVIEW);
		jViewComboBox.addItem(GenetegrateViewWidget.FULLVIEW);
		// functionViewComboBox.addItem(GenetegrateViewWidget.SWISSMODEL);
		// functionViewComboBox.addItem(GenetegrateViewWidget.ESYPRED);
		functionViewComboBox.addItem(GenetegrateViewWidget.PUDGE);
		executeButton = new JButton("Run");

		jViewComboBox.setSize(jViewComboBox.getPreferredSize());
		if (sequenceDB != null) {
			seqViewWPanel.setMaxSeqLen(sequenceDB.getMaxLength());
		}
		// seqViewWPanel.initialize(selectedPatterns, sequenceDB);
		seqScrollPane.getViewport().add(seqViewWPanel, null);
		seqViewWPanel.setShowAll(showAllBtn.isSelected());
	}

	/**
	 * cleanButtons
	 * 
	 * @param aString
	 *            String
	 */
	public void removeButtons(String aString) {
		if (aString.equals(GenetegrateViewWidget.NONBASIC)) {
			jToolBar1.remove(showAllBtn);
			jToolBar1.remove(jAllSequenceCheckBox); // fix bug 924
			jToolBar1.remove(jSequenceSummaryTextField);
			repaint();
		}
	}

	private void setMoveDirection(String directionStr) {
		if (directionStr.equals(GenetegrateViewWidget.LEFT)) {
			goLeft = true;
		} else {
			goLeft = false;
		}
	}

	/**
	 * receiveProjectSelection
	 * 
	 * @param e
	 *            ProjectEvent
	 */
	@Subscribe
	public void receive(org.geworkbench.events.ProjectEvent e, Object source) {
		if (e.getMessage().equals(org.geworkbench.events.ProjectEvent.CLEARED)) {
			// refMASet = null;
			fireModelChangedEvent(null);
		} else {
			DSDataSet dataSet = e.getDataSet();
			if (dataSet instanceof DSSequenceSet) {
				if (orgSequenceDB != dataSet) {
					this.orgSequenceDB = (DSSequenceSet) dataSet;
					selectedPatterns = null;
					// activatedMarkers = null;
				}
			}
			// refreshMaSetView();
		}
		refreshMaSetView();
	}

	/**
	 * geneSelectorAction
	 * 
	 * @param e
	 *            GeneSelectorEvent
	 */
	public void sequenceDBUpdate(GeneSelectorEvent e) {
		if (e.getPanel() == null) {
			return;
		}
		if (e.getPanel() != null && e.getPanel().size() > 0) {
			activatedMarkers = e.getPanel().activeSubset();
		} else {
			activatedMarkers = null;
		}
		refreshMaSetView();
	}

	protected void refreshMaSetView() {
		getDataSetView();
	}

	protected void fireModelChangedEvent(MicroarraySetViewEvent event) {
		this.repaint();
	}

	void chkActivateMarkers_actionPerformed(ActionEvent e) {
		subsetMarkerOn = !((JCheckBox) e.getSource()).isSelected();
		refreshMaSetView();
	}

	public void getDataSetView() {
		subsetMarkerOn = !jAllSequenceCheckBox.isSelected();
		if (subsetMarkerOn) {
			if (activatedMarkers != null && activatedMarkers.size() > 0) {

				if (subsetMarkerOn && (orgSequenceDB != null)) {
					// createActivatedSequenceSet();
					activeSequenceDB = (CSSequenceSet) ((CSSequenceSet) orgSequenceDB)
							.getActiveSequenceSet(activatedMarkers);
				}

			} else if (orgSequenceDB != null) {
				activeSequenceDB = (CSSequenceSet) orgSequenceDB;
			}

		} else if (orgSequenceDB != null) {
			activeSequenceDB = (CSSequenceSet) orgSequenceDB;
		}
		if (activeSequenceDB != null) {
			sequenceDB = activeSequenceDB;
			initPanelView();
		}
	}

	public void patternSelectionHasChanged(SequenceDiscoveryTableEvent e) {
		setPatterns(e.getPatternMatchCollection());
		refreshMaSetView();

	}

	/**
	 * invoked by the execuatebutton.
	 * 
	 * @param boo
	 * @param text
	 */
	public void updateProgressBar(final boolean boo, final String text) {
		Runnable r = new Runnable() {
			public void run() {
				try {
					serviceProgressBar.setString(text);
					serviceProgressBar.setIndeterminate(boo);
				} catch (Exception e) {
				}
			}
		};
		SwingUtilities.invokeLater(r);
	}

	public void stopButton_actionPerformed(ActionEvent e) {
		updateProgressBar(false, "Stopped at " + new Date());
		isStopped = true;
	}

	public void invokeFunction() {
		isNormal = true;
		preProcess();
		Runnable r = new Runnable() {
			public void run() {
				try {
					runPDBGeneratingFunction();
				} catch (Exception e) {
				}
			}
		};
		if (isNormal) {
			new Thread(r).start();
		}
	}

	/**
	 * create a sequence per file from the original sequence file and update the
	 * query UI.
	 */
	public void preProcess() {
		if (activeSequenceDB.isDNA()) {
			isNormal = false;
			JOptionPane
					.showMessageDialog(
							null,
							"The sequences are DNA sequence, please load a protein sequence first.",
							"DNA Sequence", JOptionPane.ERROR_MESSAGE);
			return;
		}
		tempFolder = System.getProperties().getProperty(
				"temporary.files.directory");
		inputFileNames = new String[activeSequenceDB.size()];
		for (int i = 0; i < activeSequenceDB.size(); i++) {
			DSSequence seq = activeSequenceDB.getSequence(i);
			String fastaFilename = activeSequenceDB.getFile().getName();
			String name = fastaFilename;
			if (fastaFilename.lastIndexOf(".") > 0) {
				name = fastaFilename.substring(0, fastaFilename
						.lastIndexOf("."));
			}
			String tempString = name + "_" + seq.getLabel() + ".fasta";
			tempString = tempString.replace('|', '_');
			tempString = tempString.replace(' ', '_');
			tempString = tempString.replace('/', '_');
			if (tempFolder == null) {
				tempFolder = ".";
			}
			File tempFile = new File(tempFolder + tempString);
			if (tempFile.exists()) {
				tempFile.delete();
			}
			try {
				PrintWriter out = new PrintWriter(
						new FileOutputStream(tempFile));
				out.println(">" + seq.getLabel());
				out.println(seq.getSequence());
				out.flush();
				out.close();
				inputFileNames[i] = tempFile.getAbsolutePath();
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null,
						"Some temp files cannot be created. " + e, "Error",
						JOptionPane.ERROR_MESSAGE);
				isNormal = false;
				postProcess();
			}
		}
		executeButton.setEnabled(false);
		serviceProgressBar.setIndeterminate(true);
		serviceProgressBar.setString("Your job is submitted at " + new Date());
		revalidate();
		repaint();
	}

	public void runPDBGeneratingFunction() {
		normalEnding = true;
		String functionName = (String) functionViewComboBox.getSelectedItem();
		if (inputFileNames == null || inputFileNames.length == 0) {
			JOptionPane.showMessageDialog(null, "No sequence is activated.",
					"Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		String[] inputParameters = new String[2];
		inputParameters[0] = functionName;
		int sequenceCount = 0;
		for (String inputfilename : inputFileNames) {

			inputParameters[1] = inputfilename;
			String progressInfo = "";
			String outputfilename = "";
			int dot = inputfilename.lastIndexOf('.');
			if (inputfilename.charAt(dot - 2) == '_')
				outputfilename = inputfilename.substring(0, dot - 2) + "."
						+ inputParameters[0].toLowerCase();
			else {
				outputfilename = inputfilename.substring(0, dot) + "."
						+ inputParameters[0].toLowerCase();
			}
			// Temp solution. genetegrate can use any location of inputfile, but
			// the output file always is saved at the root folder.
			File file = new File(outputfilename);
			file = new File(file.getName());
			boolean finished = false;
			while (!finished) {
				if (isStopped) {
					isStopped = false;
					break;
				}
				if (!functionName.equalsIgnoreCase(PUDGE)) {
					// progressInfo =
					// genetegrate.demos.sendRequest(inputParameters);

					if (isStopped) {
						isStopped = false;
						break;
					}
					// System.out.println("progressInfo(" + progressInfo +
					// file.exists() + ")" + executeButton.isEnabled());
					if (progressInfo == null) {
						if (file.exists() && file.length() > 0) {
							finished = true;
							serviceProgressBar.setString("The job is done at "
									+ new Date());
						} else {
							updateProgressBar(true,
									"Waiting the response from the server.");
						}

					} else if (progressInfo.startsWith("Response expected")) {
						updateProgressBar(true, file.getName() + ": "
								+ progressInfo);
						try {
							Thread.sleep(GAPSECOND);
						} catch (Exception e) {
						}
						;
					} else {
						finished = true;
						updateProgressBar(false, progressInfo);
					}

				} else {
					DSSequence seq = activeSequenceDB
							.getSequence(sequenceCount);
					String jobID = null;
					try {
						HelloPUDGEClient pudgedemoClient = new HelloPUDGEClient(
								PUDGEURL);
						// Hacked for Benrd's Demo
						// existedJob = ""

						if (map.containsKey(seq.getLabel())) {
							jobID = map.get(seq.getLabel());
						} else {
							jobID = pudgedemoClient.submitQuery(seq.getLabel(),
									seq.getSequence());
							 
							updateProgressBar(true, "For Sequence "
									+ seq.getLabel() + ", its Job ID is " + jobID);
				 
							Thread.sleep(1000);
							String existedJob = checkCache(pudgedemoClient, seq);
							if (existedJob != null) {
								jobID = existedJob;
							}else{
								//resubmit your job.
								jobID = pudgedemoClient.submitQuery(seq.getLabel(),
										seq.getSequence());
							}
						}
						updateProgressBar(true, "For Sequence "
								+ seq.getLabel() + ", its Job ID is " + jobID);

						// //updateProgressBar(true, "For " + seq.getLabel() + "
						// Job ID is " + jobID);
						Thread.sleep(500);
						PrintWriter out = null;


						boolean isDone = pudgedemoClient.isJobDone(jobID);
						while (!isDone) {
							Thread.sleep(20000);
							isDone = pudgedemoClient.isJobDone(jobID);
						}
						finished = true;
						out = new PrintWriter(new FileOutputStream(file
								.getAbsolutePath()));
						out.println("#Result from PUDGE");
						String output = pudgedemoClient.getOutput(jobID);

						if (output == null) {
							return;
						}
						String[] lines = output.split("\\|");
						for (String line : lines) {
							out.println(line);
						}

						out.flush();
						out.close();

						// }catch(java.net.SocketException se){
						// se.printStackTrace();
						// normalEnding = false;
						// JOptionPane.showMessageDialog(null, "Error: " +
						// se.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
						//
						// }
						// catch (Exception e) {
						// e.printStackTrace();
						// normalEnding = false;
						// JOptionPane.showMessageDialog(null, "Error: " +
						// e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
						// return;
						// }

						updateProgressBar(false, "Job ID " + jobID
								+ " is finished.");
					} catch (URI.MalformedURIException e) {
						finished = true;
						normalEnding = false;
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, "URI Error: "
								+ e.getMessage(), "Error",
								JOptionPane.ERROR_MESSAGE);
					} catch (RemoteException e) {
						finished = true;
						normalEnding = false;
						e.printStackTrace();
						JOptionPane.showMessageDialog(null,
								"Connection Error, please see the detail below:\n "
										+ e.getMessage(), "Error",
								JOptionPane.ERROR_MESSAGE);
						updateProgressBar(false,
								"The job cannot be submitted to the server.");

					} catch (java.net.SocketException se) {
						finished = true;
						se.printStackTrace();
						normalEnding = false;
						JOptionPane.showMessageDialog(null, se.getMessage(),
								"Error", JOptionPane.ERROR_MESSAGE);
						updateProgressBar(false,
								"The job cannot be submitted to the server.");
					} catch (Exception e) {
						finished = true;
						normalEnding = false;
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, "Exception "
								+ e.getMessage(), "Error",
								JOptionPane.ERROR_MESSAGE);

					}
				}

			}

			postProcess(file.getAbsolutePath());
		}
	}

	public String checkCache(HelloPUDGEClient pudgedemoClient, DSSequence seq)
			throws Exception {

		TreeSet<String> jobs = new TreeSet<String>();
		String label = seq.getLabel();
		File f = new File(PUDGEWEBURL);
		if (f.canRead()) {

		}
		URL lists = new URL(PUDGEWEBURL);
		BufferedReader in = new BufferedReader(new InputStreamReader(lists
				.openStream()));

		String inputLine;

		while ((inputLine = in.readLine()) != null) {
			if (inputLine.indexOf(label + ".P") > 0) {
				int jobIDLocation = inputLine.indexOf(".P");
				String jobId = inputLine.substring(jobIDLocation + 1,
						jobIDLocation + 7);
				jobs.add(jobId);
			}

		}

		in.close();

		if (jobs != null && jobs.size() > 0) {
			for (String job : jobs) {
				if (pudgedemoClient.isJobDone(job)
						&& pudgedemoClient.getOutput(job) != null) {
					return job;
				}
			}
		}

		return null;

	}

	public void postProcess(String outputFilename) {
		postProcess();
		// serviceProgressBar.setString("The job is done at " + new Date());
		if (normalEnding) {
			File pdbFile = PDBFileGenerator
					.generatePDBFileFromSwissModel(new File(outputFilename));
			new File(outputFilename).delete();
			if (pdbFile.exists() && pdbFile.length() > 10) {
				genetegrateViewAppComponent.publishPDBDataSet(pdbFile);
			} else {
				JOptionPane.showMessageDialog(null,
						"Error: No model can be generated by the program.",
						"Error", JOptionPane.ERROR_MESSAGE);

			}
		}
		isNormal = true;
	}

	/**
	 * To restore GUI when some error happen.
	 */
	public void postProcess() {

		executeButton.setEnabled(true);
		serviceProgressBar.setIndeterminate(false);
		// serviceProgressBar.setString("Please resubmit your query again.");
		revalidate();
		repaint();
	}

	public void updateBottomPanel() {

		DSSequence selectedSequence = seqViewWPanel.getSelectedSequence();
		if (selectedSequence == null) {
			Graphics g = sequencedetailPanel.getGraphics();
			if (g != null) {
				g.clearRect(0, 0, sequencedetailPanel.getWidth(),
						sequencedetailPanel.getHeight());
			}
			return;
		}
		if (goLeft) {
			if (xStartPoint - GenetegrateViewWidget.GAP > 0) {
				xStartPoint = xStartPoint >= GenetegrateViewWidget.GAP ? xStartPoint
						- GenetegrateViewWidget.GAP
						: 1;
			} else {
				xStartPoint = 1;
				leftShiftButton.setEnabled(false);
			}
		} else {
			if (xStartPoint < selectedSequence.length()
					- GenetegrateViewWidget.GAP) {
				xStartPoint += GenetegrateViewWidget.GAP;
			} else {
				rightShiftButton.setEnabled(false);
			}
		}
		sequencedetailPanel.repaint();
		prevSeqDx = xStartPoint;
		sequencedetailPanel.setOpaque(false);

	}

	/**
	 * Update the detail of sequence.
	 * 
	 * @param e
	 *            MouseEvent
	 */

	void jDisplayPanel_mouseClicked(MouseEvent e) {
		seqViewWPanel.this_mouseClicked(e);
		xStartPoint = seqViewWPanel.getSeqXclickPoint();
		sequencedetailPanel.repaint();
	}

	private int getSeqId(int y) {
		int seqId = (y - yOff) / yStep;
		return seqId;
	}

	private int getSeqDx(int x) {
		double scale = Math.min(5.0,
				(double) (seqViewWPanel.getWidth() - 20 - xOff)
						/ (double) displaySequenceDB.getMaxLength());
		int seqDx = (int) ((double) (x - xOff) / scale);
		return seqDx;
	}

	void showPatterns() {
		if (selectedPatterns.size() > 0) {
			for (int i = 0; i < selectedPatterns.size(); i++) {
				DSMatchedSeqPattern pattern = (DSMatchedSeqPattern) selectedPatterns
						.get(i);
				if (pattern instanceof CSMatchedSeqPattern) {
					if (pattern.getASCII() == null) {
						PatternOperations.fill((CSMatchedSeqPattern) pattern,
								displaySequenceDB);
					}
					// ( (DefaultListModel)
					// patternList.getModel()).addElement(pattern);
					this.repaint();
				}
			}
		}
	}

	void this_caretPositionChanged(InputMethodEvent e) {
		showPatterns();
	}

	void this_propertyChange(PropertyChangeEvent e) {

		showPatterns();
	}

	public void deserialize(String filename) {

	}

	public void setSequenceDB(DSSequenceSet db) {
		orgSequenceDB = db;
		sequenceDB = db;
		displaySequenceDB = db;
		refreshMaSetView();
		// selectedPatterns = new ArrayList();
		if (sequenceDB != null) {
			seqViewWPanel.setMaxSeqLen(sequenceDB.getMaxLength());
			// seqViewWPanel.initialize(null, db);
			selectedPatterns.clear();
			repaint();
		}
	}

	public void setDirection(boolean direction) {
		this.goLeft = direction;
	}

	public DSSequenceSet getSequenceDB() {
		return sequenceDB;
	}

	public SequenceViewWidgetPanel getSeqViewWPanel() {
		return seqViewWPanel;
	}

	public boolean isDirection() {
		return goLeft;
	}

	public void setPatterns(
			DSCollection<DSMatchedPattern<DSSequence, CSSeqRegistration>> matches) {
		selectedPatterns.clear();
		for (int i = 0; i < matches.size(); i++) {
			selectedPatterns.add(matches.get(i));
		}
	}

	public void showAllBtn_actionPerformed(ActionEvent e) {
		if (selectedPatterns == null && showAllBtn.isSelected()) {
			if (sequenceDB == null) {
				JOptionPane
						.showMessageDialog(
								null,
								"No sequence is stored right now, please load sequences first.",
								"No Pattern", JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane
						.showMessageDialog(
								null,
								"No pattern is stored right now, please generate patterns with Pattern Discory module first.",
								"No Pattern", JOptionPane.ERROR_MESSAGE);
				seqViewWPanel.setMaxSeqLen(sequenceDB.getMaxLength());
				displaySequenceDB = sequenceDB;
				seqViewWPanel.setShowAll(false);
				// seqViewWPanel.initialize(selectedPatterns, sequenceDB,
				// isLineView);

			}

			showAllBtn.setSelected(false);

		}
		initPanelView();

	}

	public void jViewComboBox_actionPerformed(ActionEvent e) {

		initPanelView();

	}

	public void seqViewWPanel_mouseMoved(MouseEvent e) {

		seqViewWPanel.this_mouseMoved(e);
		jSequenceSummaryTextField.setText(seqViewWPanel.getDisplayInfo());

	}

	/**
	 * Transform the patterns to patternsUtil class. Child class should override
	 * this method.
	 */
	public void updatePatternSeqMatches() {
		patternLocationsMatches = PatternOperations.processPatterns(
				selectedPatterns, sequenceDB);

	}

	/**
	 * Initate the Panel, which should be used as the entry point.
	 * 
	 * @return boolean
	 */
	public boolean initPanelView() {
		updatePatternSeqMatches();
		isLineView = jViewComboBox.getSelectedItem().equals(
				GenetegrateViewWidget.LINEVIEW);
		onlyShowPattern = showAllBtn.isSelected();
		HashMap<CSSequence, PatternSequenceDisplayUtil> onlyPatternSeqMatches = new HashMap<CSSequence, PatternSequenceDisplayUtil>();
		if (onlyShowPattern) {
			displaySequenceDB = new CSSequenceSet();
		}

		// if (patternLocationsMatches != null && sequenceDB != null) {
		if (patternLocationsMatches != null && sequenceDB != null) {
			seqViewWPanel.setMaxSeqLen(sequenceDB.getMaxLength());
			for (int i = 0; i < sequenceDB.size(); i++) {
				DSSequence sequence = sequenceDB.getSequence(i);
				PatternSequenceDisplayUtil pdu = patternLocationsMatches
						.get(sequence);
				if (onlyShowPattern) {
					if (pdu.hasPattern()) {
						displaySequenceDB.addASequence(sequence);
					}
				}
			}
			if (onlyShowPattern) {
				seqViewWPanel.initialize(patternLocationsMatches,
						displaySequenceDB, isLineView);
			} else {
				seqViewWPanel.initialize(patternLocationsMatches, sequenceDB,
						isLineView);
			}

		} else {
			if (sequenceDB != null) {
				seqViewWPanel.setMaxSeqLen(sequenceDB.getMaxLength());
				displaySequenceDB = sequenceDB;
				seqViewWPanel.setShowAll(false);
				// seqViewWPanel.initialize(selectedPatterns, sequenceDB,
				// isLineView);
				showAllBtn.setSelected(false);
				seqViewWPanel.initialize(patternLocationsMatches,
						displaySequenceDB, isLineView);

			} else {
				seqViewWPanel.removeAll();
			}
		}

		return true;
	}

	public void jAllSequenceCheckBox_actionPerformed(ActionEvent e) {
		refreshMaSetView();
	}

	private class JDetailPanel extends JPanel {
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			DSSequence selectedSequence = seqViewWPanel.getSelectedSequence();
			if (selectedSequence == null) {
				g.clearRect(0, 0, sequencedetailPanel.getWidth(),
						sequencedetailPanel.getHeight());
				rightShiftButton.setEnabled(false);
				leftShiftButton.setEnabled(false);
				return;

			}
			if (xStartPoint < 0 || xStartPoint >= selectedSequence.length()) {
				rightShiftButton.setEnabled(false);
				leftShiftButton.setEnabled(false);
				return;
			}
			if (xStartPoint >= 0
					&& xStartPoint < selectedSequence.length() - 2
							* GenetegrateViewWidget.GAP) {
				rightShiftButton.setEnabled(true);
			} else {
				rightShiftButton.setEnabled(false);
			}
			if (xStartPoint > GenetegrateViewWidget.GAP) {
				leftShiftButton.setEnabled(true);
			} else {
				leftShiftButton.setEnabled(false);
			}
			final Font font = new Font("Courier", Font.BOLD, 10);
			int seqId = -1;
			int seqDx = -1;
			if (sequenceDB != null) {
				for (int i = 0; i < sequenceDB.size(); i++) {
					DSSequence seq = sequenceDB.getSequence(i);
					if (seq == selectedSequence) {
						seqId = i;
					}
				}
			}
			seqDx = xStartPoint;

			DSSequence sequence = selectedSequence;
			// Check if we are clicking on a new sequence
			if ((seqId >= 0) && (seqId != prevSeqId) || (seqDx != prevSeqDx)) {
				g.clearRect(0, 0, sequencedetailPanel.getWidth(),
						sequencedetailPanel.getHeight());
				g.setFont(font);
				if (sequence != null && (seqDx >= 0)
						&& (seqDx < sequence.length())) {
					// turn anti alising on
					((Graphics2D) g).setRenderingHint(
							RenderingHints.KEY_ANTIALIASING,
							RenderingHints.VALUE_ANTIALIAS_ON);
					// shift the selected pattern/sequence into middle of the
					// panel.
					int startPoint = 0;
					if (seqDx > GenetegrateViewWidget.GAP) {
						startPoint = seqDx / 10 * 10
								- GenetegrateViewWidget.GAP;
					}
					FontMetrics fm = g.getFontMetrics(font);

					String seqAscii = sequence.getSequence().substring(
							startPoint);
					Rectangle2D r2d = fm.getStringBounds(seqAscii, g);
					int seqLength = seqAscii.length();
					double xscale = (r2d.getWidth() + 3) / (double) (seqLength);
					double yscale = 0.6 * r2d.getHeight();
					g.drawString(seqAscii, 10, 20);
					int paintPoint = 0;
					while (paintPoint < seqLength) {
						g.drawString("|", 10 + (int) (paintPoint * xscale),
								(int) (GenetegrateViewWidget.GAP / 2 + yscale));
						g
								.drawString(
										new Integer(paintPoint + 1 + startPoint)
												.toString(),
										10 + (int) (paintPoint * xscale),
										(int) (GenetegrateViewWidget.GAP / 2 + 2 * yscale));
						paintPoint += GenetegrateViewWidget.GAP;
					}

					if (patternLocationsMatches != null) {
						PatternSequenceDisplayUtil psd = patternLocationsMatches
								.get(sequence);
						if (psd == null) {
							return;
						}
						TreeSet<PatternLocations> patternsPerSequence = psd
								.getTreeSet();
						if (patternsPerSequence != null
								&& patternsPerSequence.size() > 0) {
							for (PatternLocations pl : patternsPerSequence) {
								CSSeqRegistration registration = pl
										.getRegistration();
								if (registration != null) {
									Rectangle2D r = fm.getStringBounds(
											seqAscii, g);
									double scale = (r.getWidth() + 3)
											/ (double) (seqAscii.length());
									CSSeqRegistration seqReg = (CSSeqRegistration) registration;
									int patLength = pl.getAscii().length();
									int dx = seqReg.x1;
									double x1 = (dx - startPoint) * scale + 10;
									double x2 = ((double) patLength) * scale;
									if (pl.getPatternType().equals(
											PatternLocations.TFTYPE)) {
										x2 = Math.abs(registration.x2
												- registration.x1)
												* scale;
									}
									g.setColor(PatternOperations
											.getPatternColor(new Integer(pl
													.getIdForDisplay())));
									g.drawRect((int) x1, 2, (int) x2, 23);
									g
											.drawString(
													"|",
													(int) x1,
													(int) (GenetegrateViewWidget.GAP / 2 + yscale));
									g
											.drawString(
													"|",
													(int) (x1 + x2 - scale),
													(int) (GenetegrateViewWidget.GAP / 2 + yscale));
									g
											.drawString(
													new Integer(dx + 1)
															.toString(),
													(int) x1,
													(int) (GenetegrateViewWidget.GAP / 2 + 2 * yscale));
									g
											.drawString(
													new Integer(dx
															+ seqReg.length()
															+ 1).toString(),
													(int) (x1 + x2 - scale),
													(int) (GenetegrateViewWidget.GAP / 2 + 2 * yscale));
									if (pl.getPatternType().equals(
											PatternLocations.TFTYPE)) {

										g
												.setColor(SequenceViewWidgetPanel.DRECTIONCOLOR);

										int shape = 3;
										int[] xi = new int[shape];
										int[] yi = new int[shape];
										int triangleSize = 8;
										if (registration.strand == 0) {
											xi[0] = xi[1] = (int) x1;
											yi[0] = 2;
											yi[1] = 2 + triangleSize;
											xi[2] = xi[0] + triangleSize / 2;
											yi[2] = 2 + triangleSize / 2;
											// g.drawPolyline(xi, yi,
											// addtionalPoint);
										} else {
											xi[0] = xi[1] = (int) (x1 + x2);
											yi[0] = 2;
											yi[1] = 2 + triangleSize;
											xi[2] = xi[0] - triangleSize / 2;
											yi[2] = 2 + triangleSize / 2;

										}

										g.drawPolygon(xi, yi, shape);
										g.fillPolygon(xi, yi, shape);

									}

								}

							}
						}
					}

				}

			}

		}
	}

}