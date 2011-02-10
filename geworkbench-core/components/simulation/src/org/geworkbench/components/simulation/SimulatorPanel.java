package org.geworkbench.components.simulation;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileFilter;
import org.geworkbench.algorithms.BWAbstractAlgorithm;
import org.geworkbench.bison.datastructure.biocollections.microarrays.CSExprMicroarraySet;
import org.geworkbench.bison.datastructure.biocollections.microarrays.DSMicroarraySet;
import org.geworkbench.bison.datastructure.bioobjects.markers.DSGeneMarker;
import org.geworkbench.bison.datastructure.bioobjects.microarray.DSMicroarray;
import org.geworkbench.bison.datastructure.bioobjects.microarray.DSMutableMarkerValue;
import org.geworkbench.bison.util.RandomNumberGenerator;
import org.geworkbench.components.simulation.modeling.GeneInteraction;
import org.geworkbench.components.simulation.modeling.GeneModel;
import org.geworkbench.components.simulation.simulator.ISimulator;
import org.geworkbench.events.ProjectNodeAddedEvent;
import org.geworkbench.util.PropertiesMonitor;
import org.geworkbench.util.Util;
import org.geworkbench.util.pathwaydecoder.mutualinformation.AdjacencyMatrix;
import org.geworkbench.util.pathwaydecoder.mutualinformation.AdjacencyMatrixComparator;
import org.geworkbench.util.pathwaydecoder.mutualinformation.Parameter;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataItem;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.systemsbiology.chem.Model;

/**
 * <p>Title: Bioworks</p>
 * <p>Description: Modular Application Framework for Gene Expession, Sequence and Genotype Analysis</p>
 * <p>Copyright: Copyright (c) 2003 -2004</p>
 * <p>Company: Columbia University</p>
 *
 * @author Hooman Ahmadi
 * @version 1.0
 */

public class SimulatorPanel extends JPanel implements Serializable, PropertyChangeListener {
    double[][] simResults;
    String[] geneNames;
    GridBagLayout gridBagLayout1 = new GridBagLayout();
    Parameter parms = new Parameter();
    DSMicroarraySet maSet = null;
    JProgressBar progressBar = new JProgressBar();
    AdjacencyMatrix adjMatrix = null;
    JPanel jPanel4 = new JPanel();
    JButton jButton3 = new JButton();
    JButton phenosButton = new JButton();
    GridBagLayout gridBagLayout5 = new GridBagLayout();
    static final String configFile = System.getProperty(
            "temporary.files.directory") + "config.ini";
    double noisePercent = 0.05;
    int steps = 10;
    int maNo = 10;
    int time = 50;
    Long threadId = null;
    HashMap<Long, BWAbstractAlgorithm> threadGroup = new HashMap<Long, BWAbstractAlgorithm>();
    CSExprMicroarraySet set;
    
    private JFreeChart chart = ChartFactory.createXYLineChart(null, // Title
            "Time", // X-Axis label
            "Expression", // Y-Axis label
            new XYSeriesCollection(), // Dataset
            PlotOrientation.VERTICAL,
            false, // Show legend
            true,
            true);
    ChartPanel graph = new ChartPanel(chart);
    
    private JFreeChart errorChart = ChartFactory.createXYLineChart(null, // Title
            "Iteration", // X-Axis label
            "Error", // Y-Axis label
            new XYSeriesCollection(), // Dataset
            PlotOrientation.VERTICAL,
            false, // Show legend
            true,
            true);
    
    ChartPanel errorGraph = new ChartPanel(errorChart);
    
    SimulatorCytoscape cytoscape = new SimulatorCytoscape();
    JLabel jLabel4 = new JLabel();
    JTextField numGeneBox = new JTextField();
    JLabel jLabel8 = new JLabel();
    JTextField geneConBox = new JTextField();
    JTextField timeSteps = new JTextField();
    JLabel jLabel9 = new JLabel();
    JLabel jLabel10 = new JLabel();
    JLabel jLabel11 = new JLabel();
    JLabel jLabel12 = new JLabel();
    JLabel fanInLabel = new JLabel();
    JLabel actLabel = new JLabel();
    JLabel numPhenosLabel = new JLabel();
    JLabel vmaxRangeLabel = new JLabel();
    JLabel degRangeLabel = new JLabel();
    JTextField numPhenosBox = new JTextField();
    JTextField fanInBox = new JTextField();
    JTextField actBox = new JTextField();
    JTextField vmaxRangeBox = new JTextField();
    JTextField degRangeBox = new JTextField();
    JTextField noisePercentBox = new JTextField();
    JTextField numKinBox = new JTextField();
    JTextField kinConBox = new JTextField();
    JRadioButton newSBMLBox = new JRadioButton();
    JRadioButton loadSBMLBox = new JRadioButton();
    JRadioButton optimOnBox = new JRadioButton();
    JRadioButton optimOffBox = new JRadioButton();
    JRadioButton phenosOnBox = new JRadioButton();
    JRadioButton phenosOffBox = new JRadioButton();
    JRadioButton proteinsOnBox = new JRadioButton();
    JRadioButton proteinsOffBox = new JRadioButton();
    ButtonGroup grpSimulationType = new ButtonGroup();
    JButton concBox = new JButton();
    JButton visualBox = new JButton();
    JPanel jPanel1 = new JPanel();
    BorderLayout borderLayout1 = new BorderLayout();
    Component component1;
    JPanel jPanel2 = new JPanel();
    Box optimPanel = Box.createVerticalBox();
    JPanel phenoPanel = new JPanel();
    Box phenoBoxPanel = Box.createVerticalBox();
    JPanel optimToolPanel = new JPanel();
    Box phenoToolPanel = Box.createVerticalBox();
    JPanel phenoContainer = new JPanel();
    BorderLayout borderLayout2 = new BorderLayout();
    Component component2;
    ButtonGroup graphType = new ButtonGroup();
    ButtonGroup optimType = new ButtonGroup();
    ButtonGroup phenosType = new ButtonGroup();
    String fileName = null;
    JTabbedPane container = new JTabbedPane();
    JPanel tab1 = new JPanel();
    JPanel tab2 = new JPanel();
    JPanel tab3 = new JPanel();
    javax.swing.JFrame jframe = new javax.swing.JFrame();
    //SimulatorDraw foo = new SimulatorDraw(jframe, false);
    SimulatorDraw foo = new SimulatorDraw(jframe);
    
    public SimulatorPanel() {
        PropertiesMonitor props = new PropertiesMonitor(configFile);
        noisePercent = Double.parseDouble(props.get("Simulation.NoisePercent",
                Double.toString(noisePercent)));
        steps = Integer.parseInt(props.get("Simulation.Steps",
                Integer.toString(steps)));
        maNo = Integer.parseInt(props.get("Simulation.MicroarrayNo",
                Integer.toString(maNo)));
        try {
            jbInit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    void setSelections(){
        if (loadSBMLBox.isSelected()){
            numGeneBox.setEnabled(false);
            geneConBox.setEnabled(false);
//            timeSteps.setEnabled(false);
//            noisePercentBox.setEnabled(false);
            numKinBox.setEnabled(false);
            kinConBox.setEnabled(false);
            fanInBox.setEnabled(false);
            actBox.setEnabled(false);
        } else {
            numGeneBox.setEnabled(true);
            geneConBox.setEnabled(true);
            fanInBox.setEnabled(true);
            actBox.setEnabled(true);
//            timeSteps.setEnabled(true);
//            noisePercentBox.setEnabled(true);
            numKinBox.setEnabled(true);
            kinConBox.setEnabled(true);
            if (proteinsOffBox.isSelected()){
                numKinBox.setEnabled(false);
                kinConBox.setEnabled(false);
            }
        }
        if (phenosOffBox.isSelected()) {
            numPhenosBox.setEnabled(false);
            vmaxRangeBox.setEnabled(false);
            degRangeBox.setEnabled(false);
        }else {
            numPhenosBox.setEnabled(true);
            vmaxRangeBox.setEnabled(true);
            degRangeBox.setEnabled(true);
        }
    }

    void jbInit() throws Exception {
        component1 = Box.createHorizontalStrut(8);
        component2 = Box.createHorizontalStrut(8);
//        this.setLayout(gridBagLayout1);
        progressBar.setBorder(BorderFactory.createEtchedBorder());
        progressBar.setPreferredSize(new Dimension(150, 20));
        phenoContainer.setLayout(new BorderLayout());
//        btnSimAndRevEng.setMaximumSize(new Dimension(120, 25));
//        btnSimAndRevEng.setMinimumSize(new Dimension(120, 25));
//        btnSimAndRevEng.setPreferredSize(new Dimension(120, 25));
        
//        jButton3.setMaximumSize(new Dimension(120, 25));
//        jButton3.setMinimumSize(new Dimension(120, 25));
//        jButton3.setPreferredSize(new Dimension(120, 25));
        phenosButton.setText("Generate SBML Files");
        
        jButton3.setText("Simulate");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton3_actionPerformed(e);
            }
        });
        jPanel4.setLayout(gridBagLayout5);
        jPanel4.setBorder(BorderFactory.createEtchedBorder());
        jLabel4.setText("# of Genes");
        numGeneBox.setText(Integer.toString(maNo));
        numGeneBox.setToolTipText("The number of gene nodes in the network");
        numGeneBox.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel8.setText("# of Edges");
        geneConBox.setText(Integer.toString(steps));
        geneConBox.setToolTipText("The number of random activation/inhibition interaction edges in the network that define the kinetics");
        geneConBox.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel9.setText("Noise");
        timeSteps.setText(Integer.toString(time));
        timeSteps.setToolTipText("The number of seconds that the simulation will run for");
        timeSteps.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel10.setText("Time (s)");
        noisePercentBox.setText(Double.toString(noisePercent));
        noisePercentBox.setToolTipText("The amount of langevin noise added to the simulation");
        noisePercentBox.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel11.setText("# of Kinases");
        numKinBox.setText("0");
        numKinBox.setToolTipText("This number determines how many of the protein nodes will be designated as kinases in the network");
        numKinBox.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel12.setText("Targets/Kin.");
        kinConBox.setText("0");
        kinConBox.setToolTipText("This number determines how many protein targets each kinase activates");
        kinConBox.setHorizontalAlignment(SwingConstants.CENTER);
        numPhenosBox.setText("100");
        numPhenosBox.setToolTipText("The number of phenotype simulations");
        numPhenosBox.setHorizontalAlignment(SwingConstants.CENTER);
        fanInLabel.setText("% Fan In");
        actLabel.setText("% Activators");
        fanInBox.setText("50");
        fanInBox.setToolTipText("The ratio of in edges to total in/out edges, for each gene (fan in / (fan in + fan out))");
        fanInBox.setHorizontalAlignment(SwingConstants.CENTER);
        actBox.setText("50");
        actBox.setToolTipText("The percentage of random activator edges, where remaining edges are inhibitors");
        actBox.setHorizontalAlignment(SwingConstants.CENTER);
        
        numPhenosLabel.setText("# of Phenos");
        vmaxRangeLabel.setText("Vmax Range");
        degRangeLabel.setText("Deg. Range");
        vmaxRangeBox.setText("5");
        vmaxRangeBox.setToolTipText("This number determines the range for which the vmax values are generated");
        vmaxRangeBox.setHorizontalAlignment(SwingConstants.CENTER);
        degRangeBox.setText("2");
        degRangeBox.setToolTipText("This number determines the range for which the degradation values are generated");
        degRangeBox.setHorizontalAlignment(SwingConstants.CENTER);
        newSBMLBox.setText("New Random SBML");
        newSBMLBox.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                setSelections();
            }
        });
        loadSBMLBox.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                setSelections();
            }
        });
        loadSBMLBox.setSelected(true);
        loadSBMLBox.setText("Load SBML");
        proteinsOffBox.setSelected(true);
        graphType.add(newSBMLBox);
        graphType.add(loadSBMLBox);
        
        optimToolPanel.setLayout(new BorderLayout());
        optimOnBox.setText("Simplex Optimizer On");
        optimOnBox.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                phenosOnBox.setSelected(false);
                phenosOffBox.setSelected(true);
                setSelections();
            }
        });
        optimOffBox.setSelected(true);
        optimOffBox.setText("Simplex Optimizer Off");
        optimType.add(optimOnBox);
        optimType.add(optimOffBox);
        
        phenosOnBox.setText("Phenotype Generator On");
        phenosOnBox.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                optimOnBox.setSelected(false);
                optimOffBox.setSelected(true);
                setSelections();
            }
        });
        phenosOnBox.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                setSelections();
            }
        });
        phenosOffBox.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                setSelections();
            }
        });
        phenosOffBox.setSelected(true);
        phenosOffBox.setText("Phenotype Generator Off");
        phenosType.add(phenosOnBox);
        phenosType.add(phenosOffBox);
        
        proteinsOnBox.setText("Proteins On");
        proteinsOnBox.setToolTipText("When proteins are on, each mRNA node has a protein node associated with it in the network");
        proteinsOnBox.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                setSelections();
            }
        });
        proteinsOffBox.setText("Proteins Off");
        proteinsOffBox.setToolTipText("When proteins are off, the network contains only mRNA nodes (no post-transcriptional dynamics)");
        proteinsOffBox.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                setSelections();
            }
        });
        
        concBox.setText("Save Concentrations");
        concBox.setToolTipText("Save the concentration of each mRNA/protein/kinase, at each time step, into an excel file ");
        concBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton4_actionPerformed(e);
            }
        });
        
        visualBox.setText("Network Editor");
        visualBox.setToolTipText("Visual editor that allows you to view or create artificial networks with hill kinetics");
        visualBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                foo.setCytoscape(cytoscape);
                jframe.setLayout(new BorderLayout());
                jframe.add(foo.getContentPane(), BorderLayout.CENTER);
                jframe.setSize(foo.getSize());
                jframe.setPreferredSize(foo.getPreferredSize());
                jframe.setTitle("Network Editor");
                jframe.setVisible(true);
                //foo.setVisible(true);
                container.setSelectedComponent(tab3);
            }
        });
        
        jPanel1.setLayout(new GridLayout(2,1));
        jPanel1.setBorder(BorderFactory.createEtchedBorder());
        jPanel2.setBorder(BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new GridLayout(2,1));
        tab1.setLayout(gridBagLayout1);
        tab1.add(progressBar, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
                , GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        tab1.add(jPanel4, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0
                , GridBagConstraints.CENTER,
                GridBagConstraints.BOTH,
                new Insets(2, 2, 2, 2), 0, 0));
        container.add("Network Generator", tab1);
        jPanel1.add(loadSBMLBox);
        jPanel1.add(newSBMLBox);
//        jPanel1.add(component1, BorderLayout.EAST);
        jPanel2.add(proteinsOffBox);
        jPanel2.add(proteinsOnBox);
//        jPanel2.add(component2, BorderLayout.EAST);
        progressBar.setMaximum(100);
        progressBar.setMinimum(0);
        grpSimulationType.add(proteinsOnBox);
        grpSimulationType.add(proteinsOffBox);
        jPanel4.add(jButton3, new GridBagConstraints(0, 0, 2, 1, 0.0, 0.0
                , GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        jPanel4.add(jLabel4, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0
                , GridBagConstraints.EAST, GridBagConstraints.NONE,
                new Insets(2, 2, 2, 2), 0, 0));
        jPanel4.add(numGeneBox, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0
                , GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        jPanel4.add(jLabel8, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0
                , GridBagConstraints.EAST, GridBagConstraints.NONE,
                new Insets(0, 0, 0, 0), 0, 0));
        jPanel4.add(geneConBox, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0
                , GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        jPanel4.add(jLabel9, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
                , GridBagConstraints.EAST, GridBagConstraints.NONE,
                new Insets(2, 2, 2, 2), 0, 0));
        jPanel4.add(noisePercentBox,
                new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0
                , GridBagConstraints.CENTER,
                GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        jPanel4.add(jLabel10, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
                , GridBagConstraints.EAST, GridBagConstraints.NONE,
                new Insets(2, 2, 2, 2), 0, 0));
        jPanel4.add(timeSteps,
                new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0
                , GridBagConstraints.CENTER,
                GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        jPanel4.add(fanInBox, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0
                , GridBagConstraints.CENTER,
                GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        jPanel4.add(actBox, new GridBagConstraints(1, 6, 1, 1, 0.0, 0.0
                , GridBagConstraints.CENTER,
                GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        jPanel4.add(actLabel, new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0
                , GridBagConstraints.EAST, GridBagConstraints.NONE,
                new Insets(2, 2, 2, 2), 0, 0));
        jPanel4.add(fanInLabel, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0
                , GridBagConstraints.EAST, GridBagConstraints.NONE,
                new Insets(2, 2, 2, 2), 0, 0));
        jPanel4.add(numKinBox, new GridBagConstraints(1, 7, 1, 1, 0.0, 0.0
                , GridBagConstraints.CENTER,
                GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        jPanel4.add(jLabel11, new GridBagConstraints(0, 7, 1, 1, 0.0, 0.0
                , GridBagConstraints.EAST, GridBagConstraints.NONE,
                new Insets(2, 2, 2, 2), 0, 0));
        jPanel4.add(kinConBox, new GridBagConstraints(1, 8, 1, 1, 0.0, 0.0
                , GridBagConstraints.CENTER,
                GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        jPanel4.add(jLabel12, new GridBagConstraints(0, 8, 1, 1, 0.0, 0.0
                , GridBagConstraints.EAST, GridBagConstraints.NONE,
                new Insets(2, 2, 2, 2), 0, 0));
        jPanel4.add(concBox,
                new GridBagConstraints(0, 11, 2, 1, 0.0, 0.0
                , GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 0, 0), 0, 0));
        jPanel4.add(visualBox,
                new GridBagConstraints(0, 12, 2, 1, 0.0, 0.0
                , GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 0, 0), 0, 0));
        
        jPanel4.add(jPanel1, new GridBagConstraints(0, 9, 2, 1, 0.0, 0.0
                , GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));
        jPanel4.add(jPanel2, new GridBagConstraints(0, 10, 2, 1, 0.0, 0.0
                , GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));
        jPanel4.add(graph, new GridBagConstraints(3, 0, 1, 12, 1.0, 1.0
                , GridBagConstraints.CENTER,
                GridBagConstraints.BOTH,
                new Insets(2, 2, 2, 2), 0, 0));
//        jPanel4.add(errorGraph, new GridBagConstraints(0, 8, 2, 1, 0.0, 0.0
//                , GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
//                new Insets(0, 0, 0, 0), 0, 150));
        
        
        phenoPanel.setLayout(new GridLayout(3,2));
//        phenoBoxPanel.setLayout(new GridLayout(2,1));
//        phenoBoxPanel.setPreferredSize(new Dimension(150, 50));
//        phenoBoxPanel.setMinimumSize(new Dimension(150, 50));
//        phenoBoxPanel.setMaximumSize(new Dimension(150, 50));
        phenoPanel.setPreferredSize(new Dimension(150, 75));
        phenoPanel.setMinimumSize(new Dimension(150, 75));
        phenoPanel.setMaximumSize(new Dimension(150, 75));
        tab3.setLayout(new BorderLayout());
        tab3.setBorder(BorderFactory.createEtchedBorder());
        phenoPanel.setBorder(BorderFactory.createEtchedBorder());
        phenoBoxPanel.setBorder(BorderFactory.createEtchedBorder());
        
        tab3.add(cytoscape.getComponent(), BorderLayout.CENTER);
        phenoContainer.setPreferredSize(new Dimension(150, 150));
        phenoContainer.setMinimumSize(new Dimension(150, 150));
        phenoContainer.setMaximumSize(new Dimension(150, 150));        
        phenoContainer.add(phenoBoxPanel, BorderLayout.NORTH);
        phenoContainer.add(optimToolPanel, BorderLayout.SOUTH);
        phenoContainer.add(phenoPanel, BorderLayout.CENTER);
        phenoToolPanel.add(phenoContainer);
        phenoBoxPanel.add(phenosOnBox);
        phenoBoxPanel.add(phenosOffBox);
        phenoPanel.add(numPhenosLabel);
        phenoPanel.add(numPhenosBox);
        phenoPanel.add(vmaxRangeLabel);
        phenoPanel.add(vmaxRangeBox);
        phenoPanel.add(degRangeLabel);
        phenoPanel.add(degRangeBox);
        tab2.setLayout(new BorderLayout());
        tab2.setBorder(BorderFactory.createEtchedBorder());
        tab2.add(errorGraph, BorderLayout.CENTER);
        optimPanel.setBorder(BorderFactory.createEtchedBorder());
        //tab2.add(optimToolPanel, BorderLayout.WEST);
        tab2.add(phenoToolPanel, BorderLayout.WEST);
        optimToolPanel.add(optimPanel, BorderLayout.NORTH);
        optimPanel.add(optimOnBox);
        optimPanel.add(optimOffBox);
        container.add("Phenotype & Optimizer Options", tab2);
        container.add("Interaction Display", tab3);
        this.setLayout(new BorderLayout());
        this.add(container, BorderLayout.CENTER);
        
        cytoscape.createNewNetwork();
        cytoscape.updateNetwork();
        setSelections();
    }
    
    
    public void setMicroarraySet(DSMicroarraySet set) {
        maSet = set;
    }
    
    
    void jButton4_actionPerformed(ActionEvent e) {
        if (simResults == null || geneNames == null) {
            JOptionPane.showMessageDialog(null,
                    "Must run a simulation first!","Warning",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new RMAFilter());
            fileChooser.setFileFilter(new ExpFilter());
            fileChooser.setCurrentDirectory(new File("data"));
            fileChooser.setSelectedFile(new File("sim_concentrations.exp"));
            if (JFileChooser.APPROVE_OPTION == fileChooser.showSaveDialog((Component)e.getSource())) {
                fileName = fileChooser.getSelectedFile().getPath();
                if (fileChooser.getSelectedFile().exists()) {
                    int response = JOptionPane.showConfirmDialog(null,
                            "Overwrite existing file?","Confirm Overwrite",
                            JOptionPane.OK_CANCEL_OPTION,
                            JOptionPane.QUESTION_MESSAGE);
                    if (response != JOptionPane.CANCEL_OPTION) {
                        saveConcentrations(fileName, fileChooser.getFileFilter());
                    }
                } else {
                    saveConcentrations(fileName, fileChooser.getFileFilter());
                }
                
                
                System.out.println("");
            }
        }
    }
    
    public void saveConcentrations(String fileName, FileFilter filter) {
        int response = JOptionPane.showConfirmDialog(null,
                "Would you like to save steady-state concentrations only?","",
                JOptionPane.NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        
        try {
            File writeFile = new File(fileName);
            if (filter instanceof ExpFilter) {
                set.writeToFile(fileName);
            }
            else if (filter instanceof RMAFilter) {
                FileOutputStream os = new FileOutputStream(writeFile);
                PrintWriter writer = new PrintWriter(os);
                double timeStep = (double)Double.valueOf(timeSteps.getText()) / (double)simResults.length;
                double time = 0;
            
                writer.print("Time (s)");
                writer.print("\t");
                if (response == JOptionPane.OK_OPTION) {
                    writer.print(timeSteps.getText().toString());
                }
                else {
                    for(int i = 0; i < simResults.length; i++) {
                        writer.print(time);
                        time += timeStep;
                        writer.print("\t");
                    }
                }
                writer.flush();
                writer.println("");
            
                for (int i = 0; i < geneNames.length; i++) {
                    writer.print(geneNames[i]);
                    writer.print("\t");
                    if (response == JOptionPane.OK_OPTION) {
                        int foo = simResults[i].length - 1;
                        writer.print(simResults[foo][i]);
                    } else {
                        for (int j = 0; j < simResults.length; j++) {
                            writer.print(simResults[j][i]);
                            writer.print("\t");
                        }
                    }
                    writer.flush();
                    writer.println("");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    void jButton3_actionPerformed(ActionEvent e) {
        
        if (jButton3.getText().contains("Stop")) {
            threadGroup.get(threadId).stop();
            jButton3.setText("Simulate");
            
        } else if(newSBMLBox.isSelected() && jButton3.getText().contains("Simulate")){
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new SBMLFilter());
            fileChooser.setCurrentDirectory(new File("data"));
            fileChooser.setSelectedFile(new File("new_random_simulation.xml"));
            if (JFileChooser.APPROVE_OPTION == fileChooser.showSaveDialog((Component)e.getSource())) {
                fileName = fileChooser.getSelectedFile().getPath();
                if (fileChooser.getSelectedFile().exists()) {
                    int response = JOptionPane.showConfirmDialog(null,
                            "Overwrite existing file?","Confirm Overwrite",
                            JOptionPane.OK_CANCEL_OPTION,
                            JOptionPane.QUESTION_MESSAGE);
                    if (response != JOptionPane.CANCEL_OPTION) {
                        jButton3.setText("Stop");
                        runTimeCourseSimulation();
                       
                    }
                } else {
                    jButton3.setText("Stop");
                    runTimeCourseSimulation();
                   
                }
            }
        } else if (loadSBMLBox.isSelected() && jButton3.getText().contains("Simulate")){
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new SBMLFilter());
            fileChooser.setCurrentDirectory(new File("data"));
            fileChooser.setSelectedFile(new File("sbml_simulation.xml"));
            if (JFileChooser.APPROVE_OPTION == fileChooser.showOpenDialog((Component)e.getSource())) {
                fileName = fileChooser.getSelectedFile().getPath();
                if (!fileChooser.getSelectedFile().exists()) {
                    JOptionPane.showMessageDialog(null,
                            "File does not exist!","Warning",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    jButton3.setText("Stop");
                    runTimeCourseSimulation();
                
                }
            }
        }
    }
    
    void runSteadyStateSimulation() {
        JFileChooser chooser = new JFileChooser(
                "Z:/Simulations/Params/SimParamsTest");
        chooser.setMultiSelectionEnabled(true);
        int returnVal = chooser.showOpenDialog(this);
        if (returnVal != JFileChooser.APPROVE_OPTION) {
            return;
        }
        
        //String fileName = chooser.getSelectedFile().getAbsolutePath();
        File[] selectedFiles = chooser.getSelectedFiles();
        String[] selectedFileNames = new String[selectedFiles.length];
        for (int i = 0; i < selectedFiles.length; i++) {
            selectedFileNames[i] = selectedFiles[i].getAbsolutePath();
        }
        
        SimulationMain sim = new SimulationMain();
        
        HashMap propsMap = Util.readHashMapFromFiles(
                selectedFileNames, "#");
        
        double[][] simResults = sim.runSimulations(propsMap);
        
        String[] geneNames = sim.getSpeciesNames(propsMap);
        
        CSExprMicroarraySet set = new CSExprMicroarraySet();
        set.setLabel("Synthetic Data" + (int) (Math.random() * 100));
        set.setLabel("Synthetic Data" + (int) (Math.random() * 100));
        set.initialize(simResults.length, geneNames.length);
        
        for (DSGeneMarker m : set.getMarkers()) {
            m.setDescription(geneNames[m.getSerial()]);
            m.setLabel(geneNames[m.getSerial()]);
        }
        
        for (int simCtr = 0; simCtr < simResults.length; simCtr++) {
            set.get(simCtr).setLabel("Chip " + simCtr);
            for (int geneCtr = 0; geneCtr < simResults[simCtr].length; geneCtr++) {
                DSMicroarray microarray = set.get(simCtr);
                DSMutableMarkerValue marker = (DSMutableMarkerValue) microarray.
                        getMarkerValue(geneCtr);
                marker.setValue(simResults[simCtr][geneCtr] * 1000);
                marker.setConfidence(0.0);
            }
        }
        
//        set.postCreation();
        try {
            ProjectNodeAddedEvent event = new ProjectNodeAddedEvent("message",
                    set, null);
            //geneProfiler.publishProjectNodeAddedEvent(event);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }
    
    void runTimeCourseSimulation() {
        final SimulationMain sim = new SimulationMain();
        sim.addPropertyChangeListener(this);
        HashMap<SimulationMain.attributes, Integer> attributes = new HashMap<SimulationMain.attributes, Integer>();
        attributes.put(SimulationMain.attributes.NUM_GENES, Integer.parseInt(numGeneBox.getText()));
        attributes.put(SimulationMain.attributes.NUM_CONNECTIONS, Integer.parseInt(geneConBox.getText()));
        attributes.put(SimulationMain.attributes.NUM_KINASES, Integer.parseInt(numKinBox.getText()));
        attributes.put(SimulationMain.attributes.KINASE_CONNECTIONS, Integer.parseInt(kinConBox.getText()));
        attributes.put(SimulationMain.attributes.NUM_PHENOS, Integer.parseInt(numPhenosBox.getText()));
        attributes.put(SimulationMain.attributes.VMAX_RANGE, Integer.parseInt(vmaxRangeBox.getText()));
        attributes.put(SimulationMain.attributes.DEG_RANGE, Integer.parseInt(degRangeBox.getText()));
        attributes.put(SimulationMain.attributes.FAN_IN_PERCENT, Integer.parseInt(fanInBox.getText()));
        attributes.put(SimulationMain.attributes.ACT_PERCENT, Integer.parseInt(actBox.getText()));
        
        if (phenosOnBox.isSelected()) {
            attributes.put(SimulationMain.attributes.PHENOS_ON, 1);
        } else {
            attributes.put(SimulationMain.attributes.PHENOS_ON, 0);
        }
        if (optimOnBox.isSelected()) {
            attributes.put(SimulationMain.attributes.OPTIM_ON, 1);
        } else {
            attributes.put(SimulationMain.attributes.OPTIM_ON, 0);
        }
        
        sim.setAttributes(attributes);
        sim.setSaveFile(fileName);
        final HashMap propsMap = new HashMap();        
        {
            propsMap.put("Simulator", "SimulatorDefault");
            propsMap.put("NumTimePoints", timeSteps.getText().toString());
            propsMap.put("SimulationLangevinAmplification", noisePercentBox.getText().toString());
            propsMap.put("NetworkProcessors", "ReactionEfficiencyProcessor");
            propsMap.put("ReactionNoise", "0.1");
            
            propsMap.put("NetworkGenesProcessor", "org.geworkbench.components.simulation.networkGenesProcessor.NetworkGenesProcessor");
            propsMap.put("GeneExcludeName", "_void_");
            propsMap.put("SbmlFileName", fileName);
            if (newSBMLBox.isSelected()){
                if (proteinsOnBox.isSelected())
                    propsMap.put("NetworkGenerator", "RandomProteinNetworkGenerator");
                else
                    propsMap.put("NetworkGenerator", "RandomNetworkGenerator");
            } else {
                propsMap.put("NetworkGenerator", "SbmlNetworkGenerator");
            }
        }
        
        BWAbstractAlgorithm algo = new BWAbstractAlgorithm(){
            public void execute(){
                try {
                    if (!stopRequested){
                        simResults = sim.runTimeCourse(propsMap, 0);
                                              
                        cytoscape.setSim(sim);

                        geneNames = sim.getSpeciesNames(propsMap);
                        if (simResults != null && geneNames != null) {
                            if (!stopRequested){
                                XYSeriesCollection plots = new XYSeriesCollection();
                                XYSeries[] series = new XYSeries[geneNames.length];
                                set = new CSExprMicroarraySet();
                                set.setLabel("Synthetic Data" +
                                        (int) (Math.random() * 100));
                                set.initialize(simResults.length, geneNames.length);
                                
                                for (int k = 0; k < series.length; k++) {
                                    if (!stopRequested){
                                        series[k] = new XYSeries(geneNames[k]);
                                        plots.addSeries(series[k]);
                                        
                                        set.getMarkers().get(k).setDescription(geneNames[k]);
                                        set.getMarkers().get(k).setLabel(geneNames[k] +
                                                "_");
                                    }
                                }
                                
                                for (int simCtr = 0; simCtr < simResults.length; simCtr++) {
                                    if (!stopRequested){
                                        set.get(simCtr).setLabel("Time_" + simCtr + "_(s)");
                                        for (int geneCtr = 0;
                                        geneCtr < simResults[simCtr].
                                                length;
                                        geneCtr++) {
                                            series[geneCtr].add(simCtr,
                                                    simResults[simCtr][geneCtr]);
                                            
                                            DSMicroarray microarray = set.get(simCtr);
                                            DSMutableMarkerValue marker = (
                                                    DSMutableMarkerValue)
                                                    microarray.getMarkerValue(
                                                    geneCtr);
                                            marker.setValue(simResults[simCtr][geneCtr]);
                                            marker.setMissing(false);
                                        }
                                    }
                                }
                                
                                chart = ChartFactory.createXYLineChart(
                                        "Time Course", // Title
                                        "Time", //(, // X-Axis label
                                        "Expression", // Y-Axis label
                                        plots, // Dataset
                                        PlotOrientation.VERTICAL,
                                        true, // Show legend
                                        true,
                                        false);
                                
                                graph.setChart(chart);
                            }
                        }
                    }
                    jButton3.setText("Simulate");
                } catch (IllegalArgumentException iae) {iae.printStackTrace();}
            }
            
            public void stop(){
                super.stop();
                sim.stopRequested = true;
            }
        };
        algo.start();
        threadId = Long.parseLong(RandomNumberGenerator.getID());
        threadGroup.put(threadId, algo);
    }
    
//    set.postCreation();
//    try {
//      ES es = new ES();
//      ProjectNodeAddedEvent event = new ProjectNodeAddedEvent(null, "message",
//          set, null);
//      es.throwEvent(ProjectNodeAddedListener.class, "projectNodeAdded", event);
//    }
//    catch (Exception ex) {
//      ex.printStackTrace();
//    }
//    File file = new File("./mydata/test.res");
    //set.save(file);
    
    //}
    
    
    DSMicroarraySet doAndreaSimulation() {
        String noisePercentText = noisePercentBox.getText();
        if (Double.parseDouble(noisePercentText) != noisePercent) {
            PropertiesMonitor pm = new PropertiesMonitor(configFile);
            pm.set("Simulation.NoisePercent", noisePercentText);
            noisePercent = Double.parseDouble(noisePercentText);
        }
        String stepsText = geneConBox.getText();
        if (Integer.parseInt(stepsText) != steps) {
            PropertiesMonitor pm = new PropertiesMonitor(configFile);
            pm.set("Simulation.Steps", stepsText);
            steps = Integer.parseInt(stepsText);
        }
        String arrayNoText = numGeneBox.getText();
        if (Integer.parseInt(arrayNoText) != maNo) {
            PropertiesMonitor pm = new PropertiesMonitor(configFile);
            pm.set("Simulation.MicroarrayNo", arrayNoText);
            maNo = Integer.parseInt(arrayNoText);
        }
        double[] parmsExp = {0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.89, 0.45, 0.67};
        double[] parmsDeg = {0.3, 0.3, 0.3, 0.3, 0.3, 0.3, 0.63, 0.34, 0.55};
        
        // Model 1: coupled oscillator
//    int markerNo = 2;
//    int edgeNo   = 2;
//    int pumpNo   = 0;
//    GeneModel[]        node   = new GeneModel[markerNo];
//    GeneInteraction[]  edge   = new GeneInteraction[edgeNo];
//    GeneInteraction[]  pumps  = new GeneInteraction[pumpNo];
//
//      node[0] = new GeneModel(parmsExp[0], 0);
//      node[1] = new GeneModel(parmsExp[1], 0);
//
//      edge[0] = new GeneInteraction(node[0]);
//      edge[1] = new GeneInteraction(node[1]);
//
//      edge[0].add(node[1], 1, 1, 0, false);
//      edge[1].add(node[0], 1, 1, 0, true);
        
        // Model 4: Hartemink Model
        int markerNo = 12;
        int edgeNo = 13;
        int pumpNo = 0;
        GeneModel[] node = new GeneModel[markerNo];
        GeneInteraction[] edge = new GeneInteraction[edgeNo];
        GeneInteraction[] pumps = new GeneInteraction[pumpNo];
        node[0] = new GeneModel(parmsExp[0], 0, 0.0);
        node[1] = new GeneModel(parmsExp[0], 0, 0.0);
        node[2] = new GeneModel(parmsExp[0], 0, 0.0);
        node[3] = new GeneModel(parmsExp[0], 0, 0.0);
        node[4] = new GeneModel(parmsExp[0], 0, 0.0);
        node[5] = new GeneModel(parmsExp[0], 0, 0.0);
        node[6] = new GeneModel(parmsExp[0], 0, 0.0);
        node[7] = new GeneModel(parmsExp[0], 0, 0.0);
        node[8] = new GeneModel(parmsExp[0], 0, 0.0);
        node[9] = new GeneModel(parmsExp[0], 0, 0.0);
        node[10] = new GeneModel(parmsExp[0], 0, 0.0);
        node[11] = new GeneModel(parmsExp[0], 0, 0.0);
        
        edge[0] = new GeneInteraction(node[2]);
        edge[1] = new GeneInteraction(node[3]);
        edge[2] = new GeneInteraction(node[7]);
        edge[3] = new GeneInteraction(node[0]);
        edge[4] = new GeneInteraction(node[8]);
        edge[5] = new GeneInteraction(node[10]);
        edge[6] = new GeneInteraction(node[11]);
        edge[7] = new GeneInteraction(node[4]);
        edge[8] = new GeneInteraction(node[5]);
        edge[9] = new GeneInteraction(node[9]);
        edge[10] = new GeneInteraction(node[10]);
        edge[11] = new GeneInteraction(node[9]);
        edge[12] = new GeneInteraction(node[2]);
        
        node[2].add(edge[0]);
        node[3].add(edge[1]);
        node[7].add(edge[2]);
        node[0].add(edge[3]);
        node[8].add(edge[4]);
        node[10].add(edge[5]);
        node[11].add(edge[6]);
        node[4].add(edge[7]);
        node[5].add(edge[8]);
        node[9].add(edge[9]);
        node[10].add(edge[10]);
        node[9].add(edge[11]);
        node[2].add(edge[12]);
        
        edge[0].add(node[0], 0.1, 1, 0, true);
        edge[1].add(node[2], 0.1, 1, 0, true);
        edge[2].add(node[3], 0.1, 1, 0, true);
        edge[3].add(node[7], 0.1, 1, 0, false);
        edge[4].add(node[3], 0.1, 1, 0, false);
        edge[5].add(node[8], 0.1, 1, 0, true);
        edge[10].add(node[9], 0.1, 1, 0, true);
        edge[6].add(node[10], 0.1, 1, 0, false);
        edge[7].add(node[1], 0.1, 1, 0, true);
        edge[8].add(node[4], 0.1, 1, 0, false);
        edge[9].add(node[5], 0.1, 1, 0, true);
        edge[11].add(node[6], 0.1, 1, 0, false);
        edge[12].add(node[1], 0.1, 1, 0, false);
        
//      // Model 4: Modified Hartemink Model
//    int markerNo = 12;
//    int edgeNo   = 13;
//    int pumpNo   = 0;
//    GeneModel[]        node   = new GeneModel[markerNo];
//    GeneInteraction[]  edge   = new GeneInteraction[edgeNo];
//    GeneInteraction[]  pumps  = new GeneInteraction[pumpNo];
//    node[0]   = new GeneModel(parmsExp[0], 0, 0.0);
//    node[1]   = new GeneModel(parmsExp[0], 0, 0.0);
//    node[2]   = new GeneModel(parmsExp[0], 0, 0.0);
//    node[3]   = new GeneModel(parmsExp[0], .1, 0.0);
//    node[4]   = new GeneModel(parmsExp[0], 0, 0.0);
//    node[5]   = new GeneModel(parmsExp[0], 0, 0.0);
//    node[6]   = new GeneModel(parmsExp[0], 0, 0.0);
//    node[7]   = new GeneModel(parmsExp[0], .1, 0.0);
//    node[8]   = new GeneModel(parmsExp[0], .1, 0.0);
//    node[9]   = new GeneModel(parmsExp[0], 0, 0.0);
//    node[10]  = new GeneModel(parmsExp[0], 0, 0.0);
//    node[11]  = new GeneModel(parmsExp[0], 0, 0.0);
//
//
//    edge[0] = new GeneInteraction(node[2]);
//    edge[1] = new GeneInteraction(node[3]);
//    edge[2] = new GeneInteraction(node[7]);
//    edge[3] = new GeneInteraction(node[0]);
//    edge[4] = new GeneInteraction(node[8]);
//    edge[5] = new GeneInteraction(node[10]);
//    edge[6] = new GeneInteraction(node[11]);
//    edge[7] = new GeneInteraction(node[4]);
//    edge[8] = new GeneInteraction(node[5]);
//    edge[9] = new GeneInteraction(node[9]);
//    edge[10] = new GeneInteraction(node[10]);
//    edge[11] = new GeneInteraction(node[9]);
//    edge[12] = new GeneInteraction(node[2]);
//
//    node[2].add(edge[0]);
//    node[3].add(edge[1]);
//    node[7].add(edge[2]);
//    node[0].add(edge[3]);
//    node[8].add(edge[4]);
//    node[10].add(edge[5]);
//    node[11].add(edge[6]);
//    node[4].add(edge[7]);
//    node[5].add(edge[8]);
//    node[9].add(edge[9]);
//    node[10].add(edge[10]);
//    node[9].add(edge[11]);
//    node[2].add(edge[12]);
//
//    edge[0].add(node[0],  0.1, 1, 0, true);
//    edge[1].add(node[2],  10, 10, 1, true);
//    edge[2].add(node[3],  10, 10, 1, true);
//    edge[3].add(node[7],  0.1, 1, 0, false);
//    edge[4].add(node[3],  10, 10, 1, false);
//    edge[5].add(node[8],  0.1, 1, 0, true);
//    edge[10].add(node[9],  0.1, 1, 0, true);
//    edge[6].add(node[10], 0.1, 1, 0, false);
//    edge[7].add(node[1],  0.1, 1, 0, true);
//    edge[8].add(node[4],  0.1, 1, 0, false);
//    edge[9].add(node[5],  0.1, 1, 0, true);
//    edge[11].add(node[6],  0.1, 1, 0, false);
//    edge[12].add(node[1],  0.1, 1, 0, false);
        
        // Model 2: Loop with 5 nodes and multiple pumps
//    int markerNo = 5;
//    int edgeNo   = 5;
//    int pumpNo   = 1;
//    GeneModel[]        node   = new GeneModel[markerNo];
//    GeneInteraction[]  edge   = new GeneInteraction[edgeNo];
//    GeneInteraction[]  pumps  = new GeneInteraction[pumpNo];
//
//    node[0]   = new GeneModel(.1, 0.6, 0.0);
//    node[1]   = new GeneModel(.1, 0.4, 0.0);
//    node[2]   = new GeneModel(.1, 0.4, 0.0);
//    node[3]   = new GeneModel(.1, 0.4, 0.0);
//    node[4]   = new GeneModel(.1, 0.4, 0.0);
        //node[5]   = new GeneModel(.1, 0.4, 0.3);
//    edge[0] = new GeneInteraction(node[1]);
//    edge[1] = new GeneInteraction(node[2]);
//    edge[2] = new GeneInteraction(node[3]);
//    edge[3] = new GeneInteraction(node[4]);
//    edge[4] = new GeneInteraction(node[0]);
        //edge[5] = new GeneInteraction(node[0]);
//
//    edge[0].add(node[0], 1, 1, 1, true);
//    edge[1].add(node[1], 1, 1, 1, true);
//    edge[2].add(node[2], 1, 1, 1, false);
//    edge[3].add(node[3], 1, 1, 1, true);
//    edge[4].add(node[4], 1, 1, 1, true);
        //edge[5].add(node[5], 1, 1, 1, true);
//
//    node[1].add(edge[0]);
//    node[2].add(edge[1]);
//    node[3].add(edge[2]);
//    node[4].add(edge[3]);
//    node[0].add(edge[4]);
        //node[0].add(edge[5]);
//
//    pumps[0] = new GeneInteraction(node[0]);
//    pumps[1] = new GeneInteraction(node[1]);
//    pumps[2] = new GeneInteraction(node[2]);
//    pumps[3] = new GeneInteraction(node[3]);
//    pumps[4] = new GeneInteraction(node[4]);
        
        // Model 3: Triangle Inequality
//    int markerNo = 3;
//    int edgeNo   = 2;
//    int pumpNo   = 1;
//    GeneModel[]        node   = new GeneModel[markerNo];
//    GeneInteraction[]  edge   = new GeneInteraction[edgeNo];
//    GeneInteraction[]  pumps  = new GeneInteraction[pumpNo];
//
//    node[0] = new GeneModel(parmsExp[0], .2, 0.0);
//    node[1] = new GeneModel(parmsExp[1], .2, 0.0);
//    node[2] = new GeneModel(parmsExp[2], .2, 0.0);
//
//    edge[0] = new GeneInteraction(node[1]);
//    edge[1] = new GeneInteraction(node[2]);
//
//    edge[0].add(node[0], .5, .2, 3, true);
//    edge[1].add(node[0], .5, .2, 3, false);
//
//    pumps[0] = new GeneInteraction(node[0]);
        
        // Model 2: coupled oscillator + loop
//    int markerNo = 5;
//    int edgeNo   = 5;
//    int pumpNo   = 1;
//    GeneModel[]        node   = new GeneModel[markerNo];
//    GeneInteraction[]  edge   = new GeneInteraction[edgeNo];
//    GeneInteraction[]  pumps  = new GeneInteraction[pumpNo];
//
//    node[0] = new GeneModel(parmsExp[0], 1, 0);
//    node[1] = new GeneModel(parmsExp[1], 1, 0);
//    node[2] = new GeneModel(parmsExp[2], 1, 0);
//    node[3] = new GeneModel(parmsExp[3], 1, 0);
//    node[4] = new GeneModel(parmsExp[4], 1, 0);
//
//    edge[0] = new GeneInteraction(node[0]);
//    edge[1] = new GeneInteraction(node[1]);
//    edge[2] = new GeneInteraction(node[2]);
//    edge[3] = new GeneInteraction(node[3]);
//    edge[4] = new GeneInteraction(node[4]);
//
//    edge[0].add(node[1], 1, 0, 1, false); // 1 -> 0
//    edge[1].add(node[0], .5, 1.3, 1, true); // 0 -> 1
//    edge[2].add(node[1], .5, 2, 1, true); // 1 -> 2
//    edge[1].add(node[4], .5, 1, 1, true); // 4 -> 2
//    edge[3].add(node[2], .5, 1, 1, true); // 2 -> 3
//    edge[4].add(node[3], .5, 1, 1, true); // 3 -> 4
//
//    pumps[0] = new GeneInteraction(node[0]);
        
        
        // Set up the microarrays
        XYSeriesCollection plots = new XYSeriesCollection();
        XYSeries[] series = new XYSeries[markerNo];
        CSExprMicroarraySet set = new CSExprMicroarraySet();
        set.setLabel("Synthetic Data" + (int) (Math.random() * maNo));
        set.setLabel("Synthetic Data" + (int) (Math.random() * maNo));
        set.initialize(maNo, markerNo);
        for (int k = 0; k < markerNo; k++) {
            set.getMarkers().get(k).setDescription("G" + k);
            set.getMarkers().get(k).setLabel("G" + k);
            if (node[k] != null) {
                //series[k] = new XYSeries("Gene " + k);
                series[k] = new XYSeries("G" + k);
                plots.addSeries(series[k]);
            }
        }
        
        for (int k = 0; k < markerNo; k++) {
            if (node[k] != null) {
//        node[k].set(Math.random() * 100);
            }
        }
        for (int i = 0; i < maNo; i++) {
            // It is critical that we first update all the values and then we perturb
            
            set.get(i).setLabel("Chip " + i);
            for (int pumpId = 0; pumpId < pumpNo; pumpId++) {
                pumps[pumpId].setPump((double) i / (double) maNo); //Math.random() * 0.1);
                //pumps[pumpId].setPump(Math.random() * 0.5);
            }
            for (int j = 0; j < steps; j++) {
                for (int k = 0; k < pumpNo; k++) {
                    pumps[k].update(j, noisePercent);
                }
                for (int k = 0; k < markerNo; k++) {
                    if (node[k] != null) {
                        //node[k].set(node[k].getPerturbed(j, noisePercent));
                        ArrayList list = node[k].getEdges();
                        if (list.size() > 0) {
                            for (Iterator iter = list.iterator(); iter.hasNext(); ) {
                                GeneInteraction nodeEdge = (GeneInteraction)
                                iter.next();
                                nodeEdge.update(j, noisePercent);
                            }
                        } else {
                            node[k].set(node[k].getPerturbed(j, noisePercent));
                        }
                        node[k].update();
                        if (newSBMLBox.isSelected()) {
                            if ((i == 0) && ((j % 100) == 0)) {
                                series[k].add(j, node[k].get());
                            }
                        }
                    }
                }
            }
            if (loadSBMLBox.isSelected()) {
                for (int k = 0; k < markerNo; k++) {
                    if (node[k] != null) {
                        series[k].add(i, node[k].get());
                    }
                }
            }
            for (int k = 0; k < markerNo; k++) {
                DSMicroarray microarray = set.get(i);
                DSMutableMarkerValue marker = (DSMutableMarkerValue) microarray.
                        getMarkerValue(k);
                if (node[k] != null) {
                    marker.setValue(node[k].get() * 1000);
                    marker.setConfidence(0.0);
                } else {
                    marker.setValue(0);
                    marker.setConfidence(0.0);
                }
            }
        }
        chart = ChartFactory.createXYLineChart("Time Course", // Title
                "Time", //(, // X-Axis label
                "Expression", // Y-Axis label
                plots, // Dataset
                PlotOrientation.VERTICAL,
                true, // Show legend
                true,
                false);
        
        graph.setChart(chart);
        //set.postCreation();
        try {
            ProjectNodeAddedEvent event = new ProjectNodeAddedEvent("message",
                    set, null);
            //geneProfiler.publishProjectNodeAddedEvent(event);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        File file = new File("./mydata/test.res");
        set.save(file);
        
        return set;
    }
    
    void btnSimAndRevEng_actionPerformed(ActionEvent e) {
        float miThresh = 0.02f;
        AdjacencyMatrix adjMatrix = new AdjacencyMatrix();
        
        Vector simulationAdjMatrices = new Vector();
        
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("data"));
        chooser.setSelectedFile(new File("new_random_simulation.xml"));
        int returnVal = chooser.showOpenDialog(this);
        if (returnVal != JFileChooser.APPROVE_OPTION) {
            return;
        }
        
        DSMicroarraySet maSet;
        
        double threshold = 0.1;
        maSet = doAndreaSimulation();
        adjMatrix.compute(maSet, threshold);
        adjMatrix.clean(maSet, 0.0, 0.0);
        AdjacencyMatrix simAdjMatrix = new AdjacencyMatrix(adjMatrix.
                getGeneRows());
        
        AdjacencyMatrix trueAdjMatrix = new AdjacencyMatrix();
        trueAdjMatrix.read(chooser.getSelectedFile().getAbsolutePath(), maSet, null);
        
        AdjacencyMatrixComparator adjComparator = new AdjacencyMatrixComparator();
        adjComparator.compare(trueAdjMatrix, simAdjMatrix, miThresh);
        
        int numTrueConnections = adjComparator.getNumTrueConnections();
        int numRecoveredConnections = adjComparator.getNumRecoveredConnections();
        int numFalseConnections = adjComparator.getNumFalseConnections();
        
        System.out.println("True Connections " + numTrueConnections);
        System.out.println("Recovered Connections " + numRecoveredConnections);
        System.out.println("False Connections " + numFalseConnections);
        
    }
    
    
    public void propertyChange(PropertyChangeEvent evt) {
        HashMap foo = (HashMap) evt.getNewValue();
        
        //HashMap propsMap = (HashMap) foo.get("propsMap");
        final Model model = (Model) foo.get("model");
        final ISimulator sim = (ISimulator) foo.get("simulator");
        ArrayList<Double> chi2Values = (ArrayList<Double>) foo.get("array");
        //HashMap propsMap = org.bioworks.util.Util.readHashMapFromFiles(selectedFileNames, "#");
        
        double[][] simResults = sim.getTimeCourseValues();
        
        String[] geneNames = model.getDynamicSymbolNames();
        if (simResults != null && geneNames != null) {
            
            XYSeriesCollection plots = new XYSeriesCollection();
            XYSeries[] series = new XYSeries[geneNames.length];
            CSExprMicroarraySet set = new CSExprMicroarraySet();
            set.setLabel("Synthetic Data" + (int) (Math.random() * 100));
            set.initialize(simResults.length, geneNames.length);
            
            for (int k = 0; k < series.length; k++) {
                series[k] = new XYSeries(geneNames[k]);
                plots.addSeries(series[k]);
                
                set.getMarkers().get(k).setDescription(geneNames[k]);
                set.getMarkers().get(k).setLabel(geneNames[k] + "_");
            }
            
            for (int simCtr = 0; simCtr < simResults.length; simCtr++) {
                set.get(simCtr).setLabel("Chip " + simCtr);
                for (int geneCtr = 0;
                geneCtr < simResults[simCtr].length;
                geneCtr++) {
                    series[geneCtr].add(simCtr,
                            simResults[simCtr][geneCtr]);
                    
                    DSMicroarray microarray = set.get(simCtr);
                    DSMutableMarkerValue marker = (DSMutableMarkerValue)
                    microarray.getMarkerValue(
                            geneCtr);
                    marker.setValue(simResults[simCtr][geneCtr] * 1000);
                    marker.setConfidence(0.0);
                    
                }
            }
            
            chart = ChartFactory.createXYLineChart("Time Course", // Title
                    "Time", //(, // X-Axis label
                    "Expression", // Y-Axis label
                    plots, // Dataset
                    PlotOrientation.VERTICAL,
                    true, // Show legend
                    true,
                    false);
            
            graph.setChart(chart);
            
            //  repaint();
        }
        
        XYSeriesCollection plots = new XYSeriesCollection();
        XYSeries[] series = new XYSeries[1];
        series[0] = new XYSeries("Errors");
        
        for (int k = 0; k < chi2Values.size(); k++) {
            series[0].add(new XYDataItem(new Double(k), chi2Values.get(k)));
        }
        
        plots.addSeries(series[0]);
        
        errorChart = ChartFactory.createXYLineChart("", // Title
                "Iterations", //(, // X-Axis label
                "Error", // Y-Axis label
                plots, // Dataset
                PlotOrientation.VERTICAL,
                true, // Show legend
                true,
                false);
        errorChart.removeLegend();
        //errorChart.clearSubtitles();
        errorGraph.setChart(errorChart);
        repaint();
    }
    
    class SBMLFilter extends FileFilter {
        
        String[] extensions = new String[]{"sbml", "xml"};
        public String getDescription() {
            return "Systems Biology Markup Language (SBML)";
        }
        
        public boolean accept(File f) {
            boolean returnVal = false;
            for (int i = 0; i < extensions.length; ++i)
                if (f.isDirectory() || f.getName().endsWith(extensions[i])) {
                return true;
                }
            return returnVal;
        }
    }
    
    class ExpFilter extends FileFilter {
        
        String[] extensions = new String[]{"exp"};
        public String getDescription() {
            return "Affymetrix File Matrix";
        }
        
        public boolean accept(File f) {
            boolean returnVal = false;
            for (int i = 0; i < extensions.length; ++i)
                if (f.isDirectory() || f.getName().endsWith(extensions[i])) {
                return true;
                }
            return returnVal;
        }
    }
    
       class RMAFilter extends FileFilter {
        
        String[] extensions = new String[]{"txt"};
        public String getDescription() {
            return "RMA Express";
        }
        
        public boolean accept(File f) {
            boolean returnVal = false;
            for (int i = 0; i < extensions.length; ++i)
                if (f.isDirectory() || f.getName().endsWith(extensions[i])) {
                return true;
                }
            return returnVal;
        }
    }
}
