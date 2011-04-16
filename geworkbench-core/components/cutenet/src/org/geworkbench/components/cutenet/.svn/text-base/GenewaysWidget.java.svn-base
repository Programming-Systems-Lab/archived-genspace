package org.geworkbench.components.cutenet;

import geneways.util.dag.DAGType;
import geneways.util.dag.DAGTypeComponent;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.CellEditor;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.JViewport;
import javax.swing.ListModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.geworkbench.bison.datastructure.biocollections.DSDataSet;
import org.geworkbench.bison.datastructure.biocollections.microarrays.DSMicroarraySet;
import org.geworkbench.bison.datastructure.bioobjects.markers.DSGeneMarker;
import org.geworkbench.bison.datastructure.bioobjects.markers.annotationparser.AnnotationParser;
import org.geworkbench.bison.datastructure.bioobjects.markers.annotationparser.GeneOntologyUtil;
import org.geworkbench.bison.datastructure.complex.panels.CSItemList;
import org.geworkbench.bison.datastructure.complex.panels.DSItemList;
import org.geworkbench.bison.datastructure.complex.panels.DSPanel;
import org.geworkbench.engine.config.VisualPlugin;
import org.geworkbench.engine.management.AcceptTypes;
import org.geworkbench.engine.management.Publish;
import org.geworkbench.engine.management.Subscribe;
import org.geworkbench.events.AdjacencyMatrixEvent;
import org.geworkbench.events.GeneSelectorEvent;
import org.geworkbench.events.ProjectEvent;
import org.geworkbench.events.ProjectNodeAddedEvent;
import org.geworkbench.util.pathwaydecoder.mutualinformation.AdjacencyMatrix;
import org.geworkbench.util.pathwaydecoder.mutualinformation.AdjacencyMatrixDataSet;

import com.borland.jbcl.layout.VerticalFlowLayout;

/**
 * @author manjunath at genomecenter dot columbia dot edu,  xiaoqing zhang
 */
@AcceptTypes({DSMicroarraySet.class})
public class GenewaysWidget extends javax.swing.JScrollPane implements VisualPlugin {

    static Log log = LogFactory.getLog(GenewaysWidget.class);
    public static Integer nodeId = new Integer(1);

    private static String GOTERMCOLUMN = "GO Description";
    private static String MARKERLABEL = "Marker";
    private static String GENELABEL = "Gene";
    private static String GENETYPELABEL = "Gene Type";
    private static String TOTALNUMBERLABEL = "# of interactions";
    private static String[] columnLabels = new String[]{MARKERLABEL, GENELABEL, GENETYPELABEL, GOTERMCOLUMN, TOTALNUMBERLABEL};
    private static TableColumn[] tableColumns;
    private boolean cancelAction = false;
    /**
     * Creates new form Interactions
     */
    public GenewaysWidget() {
        initComponents();
        activatedMarkerTable.getTableHeader().setEnabled(true);
        // setting the size of the table and its columns
        activatedMarkerTable.setPreferredScrollableViewportSize(new Dimension(280, 100));
        activatedMarkerTable.getColumnModel().getColumn(0).setPreferredWidth(200);
        activatedMarkerTable.getColumnModel().getColumn(1).setPreferredWidth(50);
        activatedMarkerTable.getColumnModel().getColumn(2).setPreferredWidth(30);

        detailTable.getTableHeader().setEnabled(true);
        detailTable.setDefaultRenderer(String.class, new ColorRenderer(true));
        detailTable.setDefaultRenderer(Integer.class, new IntegerRenderer(true));
    }

    public Component getComponent() {
        return this;
    }

    private void cancelCellEditing() {
        CellEditor ce = detailTable.getCellEditor();
        if (ce != null) {
            ce.cancelCellEditing();
        }
    }


    /**
     * The old methold to create the GUI. It was generated by IDE than edited  manually.
     */

    private void initComponents() {

        cutenetManager = CutenetManager.getCutenetManager();

        mainPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        JPanel topPanel = new JPanel();
        gwControlPanel = new JPanel();
        minInstanceCountSlider = new JSlider();
        newerThanSlider = new JSlider();
        olderThanSlider = new JSlider();
        newerThanLabel = new JLabel();
        olderThanLabel = new JLabel();
        minArticleCountSlider = new JSlider();
        minInstancePanel = new JPanel();
        minArticlePanel = new JPanel();
        directionPanel = new JPanel();
        newerThanPanel = new JPanel();
        newerThanSubPanel = new JPanel();
        olderThanPanel = new JPanel();
        olderThanSubPanel = new JPanel();
        newOldPanel = new JPanel();
        outDirectionsRadioButton = new JRadioButton();
        inDirectionsRadioButton = new JRadioButton();
        bothDirectionsRadioButton = new JRadioButton();
        directionButtonGroup = new ButtonGroup();
        drawSubNetworkButton = new JRadioButton();
        drawAllNetworkButton = new JRadioButton();
        drawNetworkButtonGroup = new ButtonGroup();

        titledBorder1 = new TitledBorder(BorderFactory.createEtchedBorder(Color.
            white, new Color(148, 145, 140)), "Minimum Instance Count");
        titledBorder2 = new TitledBorder(BorderFactory.createEtchedBorder(Color.
            white, new Color(148, 145, 140)), "Minimum Article Count");
        titledBorder3 = new TitledBorder(BorderFactory.createEtchedBorder(Color.
            white, new Color(148, 145, 140)), "Newer Than");
        titledBorder4 = new TitledBorder(BorderFactory.createEtchedBorder(Color.
            white, new Color(148, 145, 140)), "Older Than");
        titledBorder5 = new TitledBorder(BorderFactory.createEtchedBorder(Color.
            white, new Color(148, 145, 140)), "Direction");
        minInstanceCountSlider.setMaximum(30);
        minInstanceCountSlider.setPaintTicks(true);
        minInstanceCountSlider.addChangeListener(new javax.swing.event.
                                                ChangeListener() {
          public void stateChanged(ChangeEvent e) {
            minInstanceCountSlider_stateChanged(e);
          }
        });

        minInstanceCountSlider.setValue(10);
        minInstanceCountSlider.setPaintLabels(true);
        minInstanceCountSlider.setMajorTickSpacing(5);
        minInstanceCountSlider.setMinorTickSpacing(1);
        minInstanceCountSlider.setBorder(titledBorder1);
        minArticleCountSlider.setMinorTickSpacing(1);
        minArticleCountSlider.setMajorTickSpacing(5);
        minArticleCountSlider.setPaintLabels(true);
        minArticleCountSlider.setValue(0);
        minArticleCountSlider.setPaintTicks(true);
        minArticleCountSlider.setBorder(titledBorder2);
        minArticleCountSlider.setMaximum(30);
        minArticleCountSlider.addChangeListener(new javax.swing.event.
                                                ChangeListener() {
          public void stateChanged(ChangeEvent e) {
            minArticleCountSlider_stateChanged(e);
          }
        });
        newerThanSlider.setMinorTickSpacing(100);
        newerThanSlider.setMajorTickSpacing(100);
        newerThanSlider.setValue(5);
        newerThanSlider.addChangeListener(new javax.swing.event.
                                                ChangeListener() {
          public void stateChanged(ChangeEvent e) {
            newerThanSlider_stateChanged(e);
          }
        });
        newerThanSlider.setPreferredSize(new Dimension(100, 24));
        newerThanLabel.setText("jLabel1");
        newerThanLabel.setHorizontalAlignment(SwingConstants.CENTER);
        newerThanLabel.setBackground(new Color(212, 0, 200));
        olderThanSlider.setMinorTickSpacing(100);
        olderThanSlider.setMajorTickSpacing(100);
        olderThanSlider.setValue(5);
        olderThanSlider.addChangeListener(new javax.swing.event.
                                                ChangeListener() {
          public void stateChanged(ChangeEvent e) {
            olderThanSlider_stateChanged(e);
          }
        });
        olderThanSlider.setPreferredSize(new Dimension(100, 24));
        olderThanLabel.setText("jLabel1");
        olderThanLabel.setHorizontalAlignment(SwingConstants.CENTER);
        olderThanLabel.setBackground(new Color(212, 0, 200));
        initNewerThan(); // set the min-max values of newer and older than buttons
        newerThanPanel.setBorder(titledBorder3);
        newerThanSubPanel.setLayout(new BorderLayout());
        newerThanSubPanel.add(newerThanSlider, BorderLayout.SOUTH);
        newerThanSubPanel.add(newerThanLabel, BorderLayout.CENTER);
        newerThanPanel.add(newerThanSubPanel);
        olderThanPanel.setBorder(titledBorder4);
        olderThanSubPanel.setLayout(new BorderLayout());
        olderThanSubPanel.add(olderThanSlider, BorderLayout.SOUTH);
        olderThanSubPanel.add(olderThanLabel, BorderLayout.CENTER);
        olderThanPanel.add(olderThanSubPanel);
        newOldPanel.setLayout(new BoxLayout(newOldPanel, BoxLayout.X_AXIS));
        newOldPanel.add(newerThanPanel);
        newOldPanel.add(olderThanPanel);

        outDirectionsRadioButton.setText("Out");
        inDirectionsRadioButton.setText("In");
        bothDirectionsRadioButton.setText("Both");
        bothDirectionsRadioButton.setSelected(true);
        directionButtonGroup.add(bothDirectionsRadioButton);
        directionButtonGroup.add(inDirectionsRadioButton);
        directionButtonGroup.add(outDirectionsRadioButton);
        directionPanel.setBorder(titledBorder5);
        directionPanel.add(bothDirectionsRadioButton);
        directionPanel.add(inDirectionsRadioButton);
        directionPanel.add(outDirectionsRadioButton);

        drawSubNetworkButton.setText("for proteins in the micro-array data only");
        drawAllNetworkButton.setText("for all proteins");
        drawAllNetworkButton.setSelected(true);
        drawNetworkButtonGroup.add(drawSubNetworkButton);
        drawNetworkButtonGroup.add(drawAllNetworkButton);

        minInstancePanel.add(minInstanceCountSlider);
        minArticlePanel.add(minArticleCountSlider);

        gwControlPanel.setLayout(new VerticalFlowLayout());
        gwControlPanel.add(minInstancePanel);
        gwControlPanel.add(minArticlePanel);
        gwControlPanel.add(directionPanel);
        gwControlPanel.add(newOldPanel);


        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        allGeneList = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        selectedGenesList = new javax.swing.JList();
        addButton = new javax.swing.JButton();
        removeButton = new javax.swing.JButton();
        refreshButton = new javax.swing.JButton();
        displayPreferenceButton = new JButton("Display Preference");
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        detailTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        createNetWorkButton = new javax.swing.JButton();
        progressBar = new JProgressBar();
        topPane = new JSplitPane();
        upPanel = new JSplitPane();
        activatedMarkerTable = new JTable();
        commandToolBar = new JToolBar();
        progressDisplayBar = new JToolBar();

        cancelButton = new JButton();
        displayPreferenceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayPreferenceButton_actionPerformed(e);
            }
        });
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(204, 204, 255)));
        //   jPanel2.setMaximumSize(new Dimension(200, 80));
        jPanel2.setMinimumSize(new Dimension(230, 80));
        jPanel2.setPreferredSize(new Dimension(230, 80));
        jLabel2.setText("Obtain Interactions for Gene(s):");

        allGeneList.setToolTipText("Available Genes");
        allGeneList.setModel(allGeneModel);
        allGeneList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                allGeneListHandler(evt);
            }
        });

        jScrollPane1.setViewportView(allGeneList);

        selectedGenesList.setToolTipText("Selected Genes");
        selectedGenesList.setModel(selectedGenesModel);
        selectedGenesList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                selectedGenesListHandler(evt);
            }
        });

        jScrollPane2.setViewportView(selectedGenesList);

        addButton.setText(">>");
        addButton.setToolTipText("Add to selection");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonHandler(evt);
            }
        });

        removeButton.setText("<<");
        removeButton.setToolTipText("Remove From Selection");
        removeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeButtonHandler(evt);
            }
        });

        refreshButton.setText("Refresh");
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previewSelectionsHandler(evt);
            }
        });

        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelTheAction(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(204, 204, 255)));
        //jPanel1.setMaximumSize(new Dimension(587, 382));
        jPanel1.setMinimumSize(new Dimension(300, 50));
        jPanel1.setPreferredSize(new Dimension(587, 182));


        detailTable.setModel(previewTableModel);
        detailTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JTable target = (JTable) e.getSource();
                    int row = target.getSelectedRow();
                    if (row < hits.size()) {
                        GenewaysRowInformation marker = hits.get(row);
                        hits.remove(row);
                        if (marker != null && !allGenes.contains(marker.getdSGeneMarker())) {
                            allGenes.add(marker.getdSGeneMarker());

                        }
                        activatedMarkerTable.revalidate();
                        detailTable.revalidate();
                    }
                }
            }

            private void maybeShowPopup(MouseEvent e) {

                if (e.isPopupTrigger() && detailTable.isEnabled()) {
                    Point p = new Point(e.getX(), e.getY());
                    int col = detailTable.columnAtPoint(p);
                    int row = detailTable.rowAtPoint(p);

                    // translate table index to model index
                    int mcol = detailTable.getColumn(
                            detailTable.getColumnName(col)).getModelIndex();

                    if (row >= 0 && row < detailTable.getRowCount()) {
                        cancelCellEditing();

                    }
                }
            }

            public void mousePressed(MouseEvent e) {
                maybeShowPopup(e);
            }

            public void mouseReleased(MouseEvent e) {
                maybeShowPopup(e);
            }

        });
        activatedMarkerTable.setModel(activeMarkersTableModel);
        activatedMarkerTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JTable target = (JTable) e.getSource();
                    int row = target.getSelectedRow();
                    if (row < allGenes.size()) {
                        DSGeneMarker marker = allGenes.get(row);
                        if (marker != null) {
                            GenewaysRowInformation genewaysRowInformation = new GenewaysRowInformation(marker);
                            boolean newEntry = true;
                            allGenes.remove(row);
                            for (GenewaysRowInformation cell : hits) {
                                if (cell.equals(genewaysRowInformation)) {
                                    newEntry = false;
                                    break;
                                }
                            }
                            if (newEntry) {
                               hits.addElement(new GenewaysRowInformation(marker));
                            }
                            activatedMarkerTable.revalidate();
                            detailTable.revalidate();
                        }
                    }
                }
            }
        });

        jScrollPane3.setViewportView(detailTable);
        jScrollPane4.setViewportView(activatedMarkerTable);
        jLabel1.setText("<html><font color=blue><B>Selected Marker List: </b></font></html>");
        jLabel1.setMaximumSize(new Dimension(90, 40));
        createNetWorkButton.setText("Create Network");
        createNetWorkButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createNetworks(evt);
            }
        });

        jPanel1.setLayout(new BorderLayout());
        progressDisplayBar.add(jLabel1);
        jProgressBar1.setForeground(Color.green);
        jProgressBar1.setMinimumSize(new Dimension(10, 16));
        jProgressBar1.setBorderPainted(true);
        jProgressBar1.setMaximum(100);
        jProgressBar1.setMinimum(0);
        progressDisplayBar.add(jProgressBar1);
        progressDisplayBar.add(displayPreferenceButton);
        jPanel1.add(progressDisplayBar, BorderLayout.NORTH);
        jPanel1.add(jScrollPane3, BorderLayout.CENTER);

        //  jPanel1.add(commandToolBar, BorderLayout.SOUTH);

        topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.add(upPanel, BorderLayout.CENTER);
        topPanel.add(commandToolBar, BorderLayout.SOUTH);
        this.getViewport().add(topPanel);
        upPanel.setOrientation(JSplitPane.VERTICAL_SPLIT);
        activeMarkersLabel = new JLabel("<html><font color=blue><B>Activated Marker List: </b></font></html>");
        jPanel2.setLayout(new BorderLayout());
        jPanel2.add(activeMarkersLabel, BorderLayout.NORTH);
        jPanel2.add(jScrollPane4, BorderLayout.CENTER);
        topPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        topPane.add(jPanel2, JSplitPane.LEFT);
        //topPane.add(gwControlPanel, JSplitPane.RIGHT);
        upPanel.add(topPane, JSplitPane.TOP);
        upPanel.add(jPanel1, JSplitPane.BOTTOM);
        upPanel.setOneTouchExpandable(true);
        commandToolBar.add(refreshButton);
        cancelButton.setText("Cancel");
        commandToolBar.add(cancelButton);
        commandToolBar.add(createNetWorkButton);
        commandToolBar.add(drawSubNetworkButton);
        commandToolBar.add(drawAllNetworkButton);
        commandToolBar.add(progressBar);
        progressBar.setVisible(false);
        hits = new Vector<GenewaysRowInformation>();


        mainDialogPanel.setLayout(borderLayout1);
        tableColumns = new TableColumn[columnLabels.length];
        TableColumnModel model = detailTable.getColumnModel();
        for (int i = 0; i < jCheckBoxes.length; i++) {
            jCheckBoxes[i] = new JCheckBox(columnLabels[i]);
            jCheckBoxes[i].setSelected(true);
            tableColumns[i] = model.getColumn(i);
        }

        jCenterPanel.setLayout(gridLayout2);
        gridLayout2.setColumns(2);
        gridLayout2.setRows(4);
        jButton1.setText("Ok");
        jButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updatePreferenceButton_actionPerformed(e);
            }
        });

        jSouthPanel.add(jButton1);
        for (int i = 0; i < jCheckBoxes.length; i++) {
            jCheckBoxes[i].setPreferredSize(new Dimension(60, 22));
            jCenterPanel.add(jCheckBoxes[i], null);
        }

        mainDialogPanel.add(jCenterPanel, java.awt.BorderLayout.CENTER);
        mainDialogPanel.add(jSouthPanel, java.awt.BorderLayout.SOUTH);
        Frame frame = JOptionPane.getFrameForComponent(this);
        dialog = new JDialog(frame, "Display Preference", false);
        dialog.getContentPane().add(mainDialogPanel);
        dialog.setMinimumSize(new Dimension(100, 100));
        dialog.setPreferredSize(new Dimension(300, 300));
        dialog.pack();
        dialog.setLocationRelativeTo(null);

        actionTypeDAG = new DAGTypeComponent();
        try{
            actionTypeDAG.setDAG(cutenetManager.getCutenetHelper().getDBHelper().getActionTypeDAG(),
                             cutenetManager.getCutenetHelper().getDBHelper().getActionType2ActionCountMap());
            DAGType dagType = cutenetManager.getCutenetHelper().getDBHelper().getActionTypeDAG();
            //m_tabbedPane.addTab("Controls", gwControlPanel);
            JTabbedPane tabbedPane = (JTabbedPane)actionTypeDAG.getComponent(0);
            Object temp = (JList)((JViewport)(((JScrollPane)(((JPanel)(tabbedPane.getComponent(0))).getComponent(0))).getComponent(0))).getComponent(0);
            tabbedPane.addTab("Controls", gwControlPanel);
            topPane.add(tabbedPane, JSplitPane.RIGHT);
        }catch(Exception e){
            // catching exception, in case connection to the geneways db is not established.
            JTabbedPane tabbedPane = new JTabbedPane();
            tabbedPane.addTab("Controls", gwControlPanel);
            topPane.add(tabbedPane, JSplitPane.RIGHT);
        }




    }// </editor-fold>



    /**
     * Respond to display the table perference. Make the selection dialog visible.
     *
     * @param e
     */
    public void displayPreferenceButton_actionPerformed(ActionEvent e) {
        Frame frame = JOptionPane.getFrameForComponent(this);

        dialog.setVisible(true);

    }

    /**
     * @param e
     */
    public void updatePreferenceButton_actionPerformed(ActionEvent e) {

        dialog.setVisible(false);
        TableColumnModel model = detailTable.getColumnModel();

        for (int i = 0; i < jCheckBoxes.length; i++) {
            model.removeColumn(tableColumns[i]);
            boolean item = jCheckBoxes[i].isSelected();
            if (jCheckBoxes[i].isSelected()) {
                model.addColumn(tableColumns[i]);
            }
        }

        detailTable.tableChanged(new TableModelEvent(previewTableModel));
        detailTable.repaint();


    }


    void updateProgressBar(final double percent, final String text) {
        Runnable r = new Runnable() {
            public void run() {
                try {
                    jProgressBar1.setForeground(Color.GREEN);
                    jProgressBar1.setString(text);
                    jProgressBar1.setValue((int) (percent * 100));
                    if (text.startsWith("Stop")) {
                        jProgressBar1.setForeground(Color.RED);
                    }
                } catch (Exception e) {
                }
            }
        };
        SwingUtilities.invokeLater(r);
    }

    private void createNetworks(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadfromDBHandler
        DSItemList<DSGeneMarker> markers = dataset.getMarkers();
        DSItemList<DSGeneMarker> copy = new CSItemList<DSGeneMarker>();
        copy.addAll(markers);
        AdjacencyMatrixDataSet amDataSet = null;
        boolean isEmpty = true;
        AdjacencyMatrix matrix = new AdjacencyMatrix();
        matrix.setMicroarraySet(dataset);
        int serial = 0;
        ArrayList<CutenetInteraction> allInteractions = new ArrayList<CutenetInteraction>();
        for (GenewaysRowInformation genewaysRowInformation : hits) {
            ArrayList<CutenetInteraction> interactions = genewaysRowInformation.getSelectedInteractions();
            if(interactions != null)
                allInteractions.addAll(interactions);
        }   //end for loop
        if(allInteractions.size() == 0){
            JOptionPane.showMessageDialog(null, "No interactions Found", "No Interactions", JOptionPane.ERROR_MESSAGE);
            return;
        }
        serial = copy.get(0).getSerial(); // taking first marker's serial number
        CutenetInteraction[] allCutenetInteractions = allInteractions.toArray(new CutenetInteraction[0]);

        AdjacencyMatrix am = cutenetManager.getNewAdjacencyMatrix(allCutenetInteractions, dataset);
        am.setMicroarraySet(dataset);
        amDataSet = cutenetManager.createAdjacencyMatrixDataSet(am, "geneways interactions");
        HashMap genewaysGeneidNameMap = cutenetManager.getGenewaysIdProteinMap();

        if(amDataSet != null && amDataSet.size()>0)
            isEmpty = false;

         if (amDataSet != null) {
            if(drawAllNetworkButton.isSelected()){
//                publishAdjacencyMatrixEvent(new AdjacencyMatrixEvent(am, genewaysGeneidNameMap, "Geneways Adjacency Matrix", -1, 2, 0.5f, AdjacencyMatrixEvent.Action.DRAW_GENEWAYS_NETWORK));
                publishProjectNodeAddedEvent(new ProjectNodeAddedEvent("Geneways Adjacency Matrix Added", null, amDataSet));            
            }
            else{
//                publishAdjacencyMatrixEvent(new AdjacencyMatrixEvent(am, genewaysGeneidNameMap, "Geneways Adjacency Matrix", -1, 2, 0.5f, AdjacencyMatrixEvent.Action.DRAW_GENEWAYS_NETWORK));
                publishProjectNodeAddedEvent(new ProjectNodeAddedEvent("Geneways Adjacency Matrix Added", null, amDataSet));            
//                publishAdjacencyMatrixEvent(new AdjacencyMatrixEvent(am, "Geneways Adjacency Matrix", -1, 2, 0.5f, AdjacencyMatrixEvent.Action.DRAW_NETWORK_ON_MICROARRAY));
            }
        } else {
            JOptionPane.showMessageDialog(null, "No interactions exists in the current dataset", "Empty Set", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_loadfromDBHandler

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private CutenetManager cutenetManager;
    private JButton addButton;
    private JButton cancelButton;
    private JList allGeneList;
    private JButton refreshButton;
    private JButton createNetWorkButton;
    private JProgressBar progressBar;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JPanel mainPanel;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPanel gwControlPanel; // geneways controls
    private JPanel minInstancePanel;
    private JPanel minArticlePanel;
    private JPanel directionPanel;
    private JPanel newerThanPanel;
    private JPanel newerThanSubPanel;
    private JPanel olderThanPanel;
    private JPanel olderThanSubPanel;
    private JPanel newOldPanel;
    TitledBorder titledBorder1;
    TitledBorder titledBorder2;
    TitledBorder titledBorder3;
    TitledBorder titledBorder4;
    TitledBorder titledBorder5;
    JSlider minInstanceCountSlider;
    JSlider minArticleCountSlider;
    JSlider newerThanSlider;
    JSlider olderThanSlider;
    JLabel newerThanLabel;
    JLabel olderThanLabel;

    JRadioButton outDirectionsRadioButton;
    JRadioButton inDirectionsRadioButton;
    JRadioButton bothDirectionsRadioButton;
    ButtonGroup directionButtonGroup;

    JRadioButton drawSubNetworkButton; // draw network of the proteins existing in the micro-array set only
    JRadioButton drawAllNetworkButton; // draw network of all proteins
    ButtonGroup drawNetworkButtonGroup;

    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JScrollPane jScrollPane3;
    private JScrollPane jScrollPane4;
    private JTable detailTable;
    private JButton removeButton;
    private JButton displayPreferenceButton;
    private JList selectedGenesList;
    private JSplitPane topPane = new JSplitPane();
    private JSplitPane upPanel = new JSplitPane();
    private JTable activatedMarkerTable;
    private JProgressBar jProgressBar1 = new JProgressBar();
    private JToolBar progressDisplayBar;
    private JToolBar commandToolBar;
    private JLabel activeMarkersLabel;

    DAGTypeComponent actionTypeDAG;

    // End of variables declaration//GEN-END:variables


    JPanel mainDialogPanel = new JPanel();

    JCheckBox[] jCheckBoxes = new JCheckBox[columnLabels.length];

    GridLayout gridLayout1 = new GridLayout();
    GridBagLayout gridBagLayout1 = new GridBagLayout();

    JPanel jCenterPanel = new JPanel();
    JPanel jSouthPanel = new JPanel();
    GridLayout gridLayout2 = new GridLayout();
    JButton jButton1 = new JButton();
    BorderLayout borderLayout1 = new BorderLayout();

    JDialog dialog = new JDialog();
    JDialog goDialog = new JDialog();

    private void allGeneListHandler(MouseEvent evt) {
        if (evt.getClickCount() == 2) {
            int index = allGeneList.locationToIndex(evt.getPoint());
            DSGeneMarker m = allGenes.get(index);
            selectedGenes.add(m);
            allGenes.remove(m);
            allGeneList.setModel(new DefaultListModel());
            allGeneList.setModel(allGeneModel);
            selectedGenesList.setModel(new DefaultListModel());
            selectedGenesList.setModel(selectedGenesModel);
        }
    }

    private void selectedGenesListHandler(MouseEvent evt) {
        if (evt.getClickCount() == 2) {
            int index = selectedGenesList.locationToIndex(evt.getPoint());
            if (index >= 0) {
                DSGeneMarker m = selectedGenes.get(index);
                allGenes.add(m);
                selectedGenes.remove(m);
                allGeneList.setModel(new DefaultListModel());
                allGeneList.setModel(allGeneModel);
                selectedGenesList.setModel(new DefaultListModel());
                selectedGenesList.setModel(selectedGenesModel);
            }
        }
    }

    private void addButtonHandler(ActionEvent e) {
        int[] indices = allGeneList.getSelectedIndices();
        if (indices != null && indices.length > 0) {
            Vector<DSGeneMarker> markers = new Vector<DSGeneMarker>();
            for (int index : indices) {
                DSGeneMarker marker = allGenes.get(index);
                selectedGenes.add(marker);
                markers.add(marker);
            }
            for (DSGeneMarker marker : markers) {
                allGenes.remove(marker);
            }
            allGeneList.setModel(new DefaultListModel());
            allGeneList.setModel(allGeneModel);
            selectedGenesList.setModel(new DefaultListModel());
            selectedGenesList.setModel(selectedGenesModel);
        }
    }

    private void removeButtonHandler(ActionEvent e) {
        int[] indices = selectedGenesList.getSelectedIndices();
        if (indices != null && indices.length > 0) {
            Vector<DSGeneMarker> markers = new Vector<DSGeneMarker>();
            for (int index : indices) {
                DSGeneMarker marker = selectedGenes.get(index);
                allGenes.add(marker);
                markers.add(marker);
            }
            for (DSGeneMarker marker : markers) {
                selectedGenes.remove(marker);
            }
            allGeneList.setModel(new DefaultListModel());
            allGeneList.setModel(allGeneModel);
            selectedGenesList.setModel(new DefaultListModel());
            selectedGenesList.setModel(selectedGenesModel);
        }
    }

     private void cancelTheAction(ActionEvent e) {
        cancelAction = true;
     }



    private void previewSelectionsHandler(ActionEvent e) {
        if(! cutenetManager.isConnected()){
            JOptionPane.showMessageDialog(null, "geWorkbench is not connected to the geneways database.", "No DB connection", JOptionPane.ERROR_MESSAGE);
            return;
        }
        cancelAction = false;
        Runnable r = new Runnable() {
            public void run() {
                progressBar.setVisible(true);
                progressBar.setStringPainted(true);
                progressBar.setString("Fetching interactions ... ");
                progressBar.setIndeterminate(true);
                try {
                    HashSet<String> selectedMarkerIDs = new HashSet<String>();
                    updateProgressBar(0, "Querying the Knowledge Base...");

                    int minInstanceCount;
                    int minArticleCount;
                    String direction = "";
                    Date newerThan;
                    Date olderThan;
                    minInstanceCount = minInstanceCountSlider.getValue();
                    minArticleCount = minArticleCountSlider.getValue();
                    if(bothDirectionsRadioButton.isSelected())
                            direction = "both";
                    else if(inDirectionsRadioButton.isSelected())
                            direction = "in";
                    else if(outDirectionsRadioButton.isSelected())
                            direction = "out";
                    newerThan = dateSlider2Date(newerThanSlider);
                    olderThan = dateSlider2Date(olderThanSlider);
                    GenewaysControlParams genewaysControlParams = new GenewaysControlParams(minInstanceCount,
                                                                                            minArticleCount,
                                                                                            direction,
                                                                                            newerThan,
                                                                                            olderThan,
                                                                                            actionTypeDAG);
                    cutenetManager.setGenewaysControlParams(genewaysControlParams);

                    int retrievedQueryNumber = 0;
                    for (GenewaysRowInformation genewaysRowInformation : hits) {
                        if(cancelAction){
                            break;
                        }
                        DSGeneMarker marker = genewaysRowInformation.getdSGeneMarker();
                        selectedMarkerIDs.add(marker.getLabel());
                    }
                    Set<String> swissProtIDs = cutenetManager.getSwissProtIDsForMarkers(selectedMarkerIDs);
                    int i = 0;
                    try{
                        for (GenewaysRowInformation genewaysRowInformation : hits) {
                            i++;
                            DSGeneMarker marker = genewaysRowInformation.getdSGeneMarker();
                            ArrayList<CutenetInteraction> tempCI = new ArrayList<CutenetInteraction>();
                            Set<String> tempSPIds = new HashSet<String>();
                            // putting a separate try-catch so that program flow can go ahead even
                            // when an exception is thrown.
                            try{
                                tempSPIds = AnnotationParser.getSwissProtIDs(marker.getLabel());
                            }catch(Exception e){
                                log.debug("Caught an exception");
                            }
                            if(tempSPIds != null){
                                log.debug("\n\nFor marker: "+marker.getLabel()+" numberOfSPIds =  "+tempSPIds.size()+"\n\n");
                                for(String swissProtID : tempSPIds){
                                    if(cancelAction){
                                        break;
                                    }
                                    ArrayList<CutenetInteraction> ci = cutenetManager.getInteractionsBySPId(swissProtID, marker.getGeneId(), marker.getGeneName(), 0);
                                    if(ci != null)
                                        tempCI.addAll(ci);
                                }
                                genewaysRowInformation.setInteractionDetails(tempCI);
                                retrievedQueryNumber+=tempSPIds.size();
                                updateProgressBar(((double) retrievedQueryNumber) / swissProtIDs.size(), "Querying the Knowledge Base...");
                            }
                        }
                    }catch(Exception e){
                        JOptionPane.showMessageDialog(null, "There was a problem quering geneways database.", "probably No DB connection !", JOptionPane.ERROR_MESSAGE);
                    }

                    if(!cancelAction){
                    updateProgressBar(1, "Query is finished.");
                    gwControlPanel.repaint();

                    }else{
                      updateProgressBar(1, "Stopped");
                    }
                    previewTableModel.fireTableDataChanged();
                    detailTable.revalidate();

                } catch (Exception e) {
                }
                progressBar.setString("");
                progressBar.setIndeterminate(false);
                progressBar.setVisible(false);
            }
        };

        Thread thread = new Thread(r);
        thread.start();

    }



    ListModel allGeneModel = new AbstractListModel() {
        public Object getElementAt(int index) {
            return allGenes.get(index);
        }

        public int getSize() {
            return allGenes.size();
        }
    };

    ListModel selectedGenesModel = new AbstractListModel() {
        public Object getElementAt(int index) {
            return selectedGenes.get(index);
        }

        public int getSize() {
            return selectedGenes.size();
        }
    };

    DefaultTableModel activeMarkersTableModel = new DefaultTableModel() {

        public boolean isCellEditable(int r, int c) {
            return false;
        }

        public int getColumnCount() {
            return 3;
        }

        public int getRowCount() {
            if (allGenes != null)
                return allGenes.size();
            return 0;
        }

        public String getColumnName(int index) {
            switch (index) {
                case 0:
                    return "Marker ";
                case 1:
                    return "Gene";
                case 2:
                    return "Type";

                default:
                    return "";
            }
        }

        synchronized public Object getValueAt(int row, int column) {
            Thread.currentThread().setContextClassLoader(GenewaysWidget.this.getClass().getClassLoader());
            if (allGenes != null) {

                DSGeneMarker value = allGenes.get(row);
                if (value != null) {
                    switch (column) {
                        case 0: {


                            return value.getLabel();

                        }
                        case 1: {
                            if (value.getGeneName() != null) {
                                return value.getGeneName();
                            } else {
                                return AnnotationParser.getGeneName(value.getLabel());

                            }
                        }
                        case 2: {

                            return GeneOntologyUtil.getOntologyUtil().checkMarkerFunctions(value);
                        }
                        case 3: {

                            return cachedPreviewData.get(row).get(3);
                        }
                        default:
                            return "loading ...";
                    }
                }

            }
            return "loading ...";
        }
    };


    DefaultTableModel previewTableModel;

    {
        previewTableModel = new DefaultTableModel() {


            public int getColumnCount() {
                return 5;
            }

            public int getRowCount() {
                if (hits != null)
                    return hits.size();
                return 0;
            }

            public String getColumnName(int index) {
                switch (index) {
                    case 0:
                        return MARKERLABEL;
                    case 1:
                        return GENELABEL;
                    case 2:
                        return GENETYPELABEL;
                    case 3:
                        return GOTERMCOLUMN;
                    case 4:
                        return TOTALNUMBERLABEL;
                    default:
                        return "";
                }
            }

            /* get the Object data to be displayed at (row, col) in table*/
            public Object getValueAt(int row, int col) {

                GenewaysRowInformation hit = hits.get(row);
                //display data depending on which column is chosen
                switch (col) {
                    case 0:
                        return hit.getdSGeneMarker().getLabel();
                    case 1:
                        return hit.getdSGeneMarker().getGeneName();
                    case 2:
                        return hit.getGeneType();

                    case 3:
                        return hit.getGoInfoStr();
                    case 4:
                        if(hit != null
                                && hit.getInteractionDetails() != null
                                && hit.getInteractionDetails().size()>0)
                            return hit.getInteractionDetails().size();
                        else
                            return 0;
                }
                return "Loading";
            }

            /*returns the Class type of the column c*/
            public Class getColumnClass(int c) {
                if (getValueAt(0, c) != null) {
                    return getValueAt(0, c).getClass();
                }
                return String.class;
            }

            /*returns if the cell is editable; returns false for all cells in columns except column 6*/
            public boolean isCellEditable(int row, int col) {
                //Note that the data/cell address is constant,
                //no matter where the cell appears onscreen.
                //if (col >= detailTable.getColumnCount()) {
                //    return false;
                //}
                //TableColumn tableColumn = detailTable.getColumnModel().getColumn(col);
                //return tableColumn.getHeaderValue().toString().equalsIgnoreCase(INCLUDEPDLABEL) || tableColumn.getHeaderValue().toString().equalsIgnoreCase(INCLUDEPPLABEL);
                return false;
            }

            /*detect change in cell at (row, col); set cell to value; update the table */
            public void setValueAt(Object value, int row, int col) {
                /*CellularNetWorkElementInformation hit = hits.get(row);
                TableColumn tableColumn = detailTable.getColumnModel().getColumn(col);
                if (tableColumn.getHeaderValue().toString().equalsIgnoreCase(INCLUDEPDLABEL)) {

                    hit.setIncludePDInteraction((Boolean) value);
                } else if (tableColumn.getHeaderValue().toString().equalsIgnoreCase(INCLUDEPPLABEL)) {
                    hit.setIncludePPInteraction((Boolean) value);
                }
                fireTableCellUpdated(row, col);
                detailTable.repaint();*/
            }


        };
    }

    public class IntegerRenderer extends JLabel
            implements TableCellRenderer {
        Border unselectedBorder = null;
        Border selectedBorder = null;
        boolean isBordered = true;

        public IntegerRenderer(boolean isBordered) {
            this.isBordered = isBordered;
            setOpaque(true); //MUST do this for background to show up.
        }

        public IntegerRenderer() {
            this(true);
        }

        public Component getTableCellRendererComponent(
                JTable table, Object color,
                boolean isSelected, boolean hasFocus,
                int row, int column) {
            TableColumn tableColumn = detailTable.getColumnModel().getColumn(column);

            if (hits != null && hits.size() > row) {
                GenewaysRowInformation genewaysRowInformation = hits.get(row);
                    //setBackground(Color.gray);
                    if (isSelected) {
                        if (selectedBorder == null) {
                            selectedBorder = BorderFactory.createMatteBorder(2, 5, 2, 5,
                                    table.getSelectionBackground());
                        }
                        setBorder(selectedBorder);
                        //setBackground(Color.blue);
                        // setForeground(list.getSelectionForeground());
                        setText("<html><font color=red><b>" + color + "</b></font></html>");
                        setToolTipText(color.toString());
                    } else {

                        //setForeground(Color.red);
                        // setForeground(list.getSelectionForeground());
                        setText("<html><font color=blue><b>" + color + "<b></font></html>");
                        if (unselectedBorder == null) {
                            unselectedBorder = BorderFactory.createMatteBorder(2, 5, 2, 5,
                                    table.getBackground());
                        }
                        setBorder(unselectedBorder);
                    }
                //}
            }

            return this;
        }
    }

    public class ColorRenderer extends JLabel
            implements TableCellRenderer {
        Border unselectedBorder = null;
        Border selectedBorder = null;
        boolean isBordered = true;

        public ColorRenderer(boolean isBordered) {
            this.isBordered = isBordered;
            setOpaque(true); //MUST do this for background to show up.
        }

        public ColorRenderer() {
            this(true);
        }

        public Component getTableCellRendererComponent(
                JTable table, Object color,
                boolean isSelected, boolean hasFocus,
                int row, int column) {

            if (hits != null && hits.size() > row) {
                GenewaysRowInformation genewaysRowInformation = hits.get(row);
                    if (isSelected) {
                        if (selectedBorder == null) {
                            selectedBorder = BorderFactory.createMatteBorder(2, 5, 2, 5,
                                    table.getSelectionBackground());
                        }
                        setBorder(selectedBorder);

                        // setForeground(list.getSelectionForeground());
                        setText("<html><font color=RED><b>" + color + "</b></font></html>");
                    } else {

                        setForeground(Color.black);
                        // setForeground(list.getSelectionForeground());
                        setText("<html><font color=BLUE><b>" + color + "<b></font></html>");
                        if (unselectedBorder == null) {
                            unselectedBorder = BorderFactory.createMatteBorder(2, 5, 2, 5,
                                    table.getBackground());
                        }
                        setBorder(unselectedBorder);
                    }
                }
            //}
            setToolTipText("Value:  " + color);
            return this;
        }
    }

    TableCellRenderer tableHeaderRenderer = new TableCellRenderer() {
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            return new JLabel("<html><b> " + (String) value + "</b></html>");
        }
    };


    private Vector<DSGeneMarker> allGenes = new Vector<DSGeneMarker>();
    private Vector<DSGeneMarker> selectedGenes = new Vector<DSGeneMarker>();
    private Vector<BigDecimal> entrezIds = new Vector<BigDecimal>();
    private Vector<String> geneNames;

    {
        geneNames = new Vector<String>();
    }

    private Vector<Vector<Object>> cachedPreviewData = new Vector<Vector<Object>>();
    private Vector<GenewaysRowInformation> hits = new Vector<GenewaysRowInformation>();

    private DSMicroarraySet dataset = null;

    @Subscribe
    public void receive(GeneSelectorEvent gse, Object source) {
        DSPanel<DSGeneMarker> panel = gse.getPanel();
        if (panel != null) {
            allGenes.clear();
            for (DSGeneMarker marker : panel) {
                boolean alreadyAdded = false;
                for(DSGeneMarker m : selectedGenes)
                    if(marker.getLabel().equals(m.getLabel()))
                        alreadyAdded = true;
                for(int cx=0;cx<hits.size();cx++){                	
                    GenewaysRowInformation selectedMarker=hits.get(cx);
                    if(marker.getLabel().equals(selectedMarker.getdSGeneMarker().getLabel()))
                    	alreadyAdded = true;
                }
                	
                if (!alreadyAdded)
                    allGenes.add(marker);
                /*
                if (!selectedGenes.contains(marker))
                    allGenes.add(marker);
                */
            }
            activeMarkersTableModel.fireTableDataChanged();
            Vector<DSGeneMarker> temp = new Vector<DSGeneMarker>();
            for (DSGeneMarker marker : selectedGenes) {
                if (!panel.contains(marker))
                    temp.add(marker);
            }
            for (DSGeneMarker marker : temp)
                selectedGenes.remove(marker);
            allGeneList.setModel(new DefaultListModel());
            allGeneList.setModel(allGeneModel);
            selectedGenesList.setModel(new DefaultListModel());
            selectedGenesList.setModel(selectedGenesModel);
        }
        repaint();
    }

    @Subscribe
    public void receive(ProjectEvent pe, Object source) {
        DSDataSet ds = pe.getDataSet();
        if (ds != null && ds instanceof DSMicroarraySet) {
            dataset = (DSMicroarraySet) ds;
        }
    }

    @Publish
    public AdjacencyMatrixEvent publishAdjacencyMatrixEvent(AdjacencyMatrixEvent ae) {
        return ae;
    }

    @Publish
    public ProjectNodeAddedEvent publishProjectNodeAddedEvent(ProjectNodeAddedEvent pe) {
        return pe;
    }

    class EntrezIdComparator implements Comparator<DSGeneMarker> {
        public int compare(DSGeneMarker m1, DSGeneMarker m2) {
            return (new Integer(m1.getGeneId())).compareTo(new Integer(m2.getGeneId()));
        }
    }


    void minInstanceCountSlider_stateChanged(ChangeEvent e) {
      if (minArticleCountSlider.getValue() > minInstanceCountSlider.getValue()) {
        minArticleCountSlider.setValue(minInstanceCountSlider.getValue());
      }
    }

    void minArticleCountSlider_stateChanged(ChangeEvent e) {
      if (minArticleCountSlider.getValue() > minInstanceCountSlider.getValue()) {
        minInstanceCountSlider.setValue(minArticleCountSlider.getValue());
      }
    }


    private int dateDifferenceInMonths(Date db, Date de) {
      if (db.after(de)) {
        log.debug("ASSERT(Invalid dates order)");
        Date t = db;
        db = de;
        de = t;
      }

      Calendar cal = Calendar.getInstance();
      cal.setTime(db);
      int yearB = cal.get(Calendar.YEAR);
      int monthB = cal.get(Calendar.MONTH);

      cal.setTime(de);
      int yearE = cal.get(Calendar.YEAR);
      int monthE = cal.get(Calendar.MONTH);

      if (yearE == yearB) {
        return monthE - monthB;
      }
      return 12 - monthB + (yearE - yearB - 1) * 12 + monthE;
    }

    private void initNewerThan() {
      Date beg = cutenetManager.getMinDate();
      Date end = cutenetManager.getMaxDate();

      int maxV = dateDifferenceInMonths(beg, end);
      int newerThanV = 0;
      Date newerThanDateV = beg;
      if (newerThanDateV != null) {
        if (!beg.after(newerThanDateV) && !end.before(newerThanDateV)) {
          newerThanV = dateDifferenceInMonths(beg, newerThanDateV);
        }
      }
      else {
        newerThanDateV = beg;
      }

      int olderThanV = maxV;
      Date olderThanDateV = end;
      if (olderThanDateV != null) {
        if (!newerThanDateV.after(newerThanDateV) && !end.before(newerThanDateV)) {
          olderThanV = dateDifferenceInMonths(beg, olderThanDateV) + 1;
        }
      }


      newerThanSlider.setMinimum(0);
      newerThanSlider.setMaximum(maxV);
      newerThanSlider.setValue(newerThanV);

      olderThanSlider.setMinimum(0);
      olderThanSlider.setMaximum(maxV);
      olderThanSlider.setValue(olderThanV);
    }

    void newerThanSlider_stateChanged(ChangeEvent e) {
        if (newerThanSlider.getValue() > olderThanSlider.getValue()) {
            olderThanSlider.setValue(newerThanSlider.getValue());
        }
        Date dv = dateSlider2Date(newerThanSlider);
        setDateLabel(newerThanLabel, dv);
    }

    void olderThanSlider_stateChanged(ChangeEvent e) {
        if (newerThanSlider.getValue() > olderThanSlider.getValue()) {
            newerThanSlider.setValue(olderThanSlider.getValue());
        }
        Date dv = dateSlider2Date(olderThanSlider);
        setDateLabel(olderThanLabel, dv);
    }

    void setDateLabel(JLabel label, Date date) {
        String s = DateFormat.getDateInstance(DateFormat.SHORT).format(date);
        label.setText(s);
    }

        Date dateSlider2Date(JSlider slider) {
        int sv = slider.getValue();
        Calendar cal = Calendar.getInstance();
        cal.setTime(cutenetManager.getMinDate());
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), 1);
        cal.add(Calendar.MONTH, sv);
        return cal.getTime();
    }

    class ColumnKeeper implements ActionListener {
        protected TableColumn m_column;

        public ColumnKeeper(TableColumn column) {
            m_column = column;

        }

        public void actionPerformed(ActionEvent e) {
            JCheckBoxMenuItem item = (JCheckBoxMenuItem) e.getSource();
            TableColumnModel model = detailTable.getColumnModel();
            if (item.isSelected()) {
                model.addColumn(m_column);
            } else {
                model.removeColumn(m_column);
            }
            detailTable.tableChanged(new TableModelEvent(previewTableModel));
            detailTable.repaint();
        }
    }

}
