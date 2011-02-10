package org.geworkbench.components.patterns;

import org.geworkbench.bison.datastructure.biocollections.DSCollection;
import org.geworkbench.bison.datastructure.bioobjects.sequence.DSSequence;
import org.geworkbench.bison.datastructure.complex.pattern.DSMatchedPattern;
import org.geworkbench.bison.datastructure.complex.pattern.sequence.CSSeqRegistration;
import org.geworkbench.bison.datastructure.complex.pattern.sequence.DSMatchedSeqPattern;
import org.geworkbench.events.SequenceDiscoveryTableEvent;
import org.geworkbench.util.patterns.PatternCellRenderer;
import org.geworkbench.util.patterns.PatternDB;
import org.geworkbench.util.patterns.CSMatchedSeqPattern;

import javax.swing.*;
import java.awt.*;

/**
 * <p>Title: Sequence and Pattern Plugin</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */

public class PatternViewWidget extends JPanel {
    JScrollPane patternPane = null;
    JList patternList = new JList(new DefaultListModel());
    BorderLayout borderLayout1 = new BorderLayout();
    JToolBar jToolBar1 = new JToolBar();
    JButton jButton1 = new JButton();
    JPanel jPanel1 = new JPanel();
    GridBagLayout gridBagLayout1 = new GridBagLayout();
    JLabel jLabel1 = new JLabel();

    public PatternViewWidget() {
        try {
            jbInit();
        } catch (Exception e) {
            System.out.println("PatternViewWidget::::::::::::::constructor: " + e.getCause());
            System.out.println("PatternViewWidget::::::::::::::constructor: " + e.getMessage());
            e.printStackTrace();
        }

    }

    private void jbInit() throws Exception {
        patternPane = new JScrollPane();
        patternPane.setBorder(BorderFactory.createEtchedBorder());
        patternList.setFont(new java.awt.Font("Monospaced", 0, 11));
        patternList.setCellRenderer(new PatternCellRenderer());
        this.setLayout(borderLayout1);
        jButton1.setOpaque(true);
        jButton1.setText("Clear");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                ((DefaultListModel) patternList.getModel()).clear();
            }
        });
        jPanel1.setLayout(gridBagLayout1);
        jLabel1.setBorder(null);
        jLabel1.setMinimumSize(new Dimension(0, 20));
        jLabel1.setPreferredSize(new Dimension(0, 20));
        jLabel1.setHorizontalAlignment(SwingConstants.LEFT);
        jPanel1.setBorder(BorderFactory.createEtchedBorder());
        this.add(patternPane, BorderLayout.CENTER);
        this.add(jToolBar1, BorderLayout.NORTH);
        this.add(jPanel1, BorderLayout.SOUTH);
        jPanel1.add(jLabel1, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(2, 4, 2, 4), 0, 0));
        jToolBar1.add(jButton1, null);
        patternPane.getViewport().add(patternList, null);
    }

    public JList getPatternList() {
        return patternList;

    }

    public void setPatternList(JList pList) {
        patternList = pList;
    }

    public void updateList(PatternDB patternDB){
        ((DefaultListModel) patternList.getModel()).clear();
        if(patternDB!=null && patternDB.size()>0){

            for(int i=0; i<patternDB.size(); i++){
                DSMatchedSeqPattern pattern =  patternDB.getPattern(i);
                ((DefaultListModel) patternList.getModel()).addElement(pattern);
            }
            this.repaint();
        }
    }

    public void sequenceDiscoveryTableRowSelected(SequenceDiscoveryTableEvent e) {
        try {
             // System.out.println("e=" + e);
            DSCollection<DSMatchedPattern<DSSequence, CSSeqRegistration>> patternMatches = e.getPatternMatchCollection();
            //Pattern[] patterns = e.getPatterns();
            //if (patterns.length > 0) {
          //System.out.println("paternMatches=" + patternMatches);
            if (patternMatches.size() > 0) {
                //CSSequenceSet seqDB = e.getSequenceDB();
                ((DefaultListModel) patternList.getModel()).clear();
                //for (int i = 0; i < patterns.length; i++) {
                for (int i = 0; i < patternMatches.size(); i++) {
                    //Pattern pattern = patterns[i];
                    DSMatchedPattern<DSSequence, CSSeqRegistration> patternMatch = patternMatches.get(i);
                    //          patternMatch.toString();
                    //          if (pattern instanceof PatternImpl) {
                    //            if (pattern.getASCII() == null) {
                    //              PatternOperations.fill( (PatternImpl) pattern, seqDB);
                    //            }
                    //            ( (DefaultListModel) patternList.getModel()).addElement(pattern);
                    ((DefaultListModel) patternList.getModel()).addElement(patternMatch);
                    //          }
                }

                this.repaint();
            }

        } catch (Exception ex) {
            System.out.println("PatternViewWidget::::::::::::::::::sequenceDiscoveryTableRowSelected(SequenceDiscoveryTableEvent e) " + ex.toString());
        }
    }
}
