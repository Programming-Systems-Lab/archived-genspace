package org.geworkbench.components.cutenet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import org.geworkbench.engine.config.VisualPlugin;
import org.geworkbench.engine.config.MenuListener;

import cgc.cutenet.Cutenet;
import cgc.cutenet.gui.CutenetFrame;
import cgc.cutenet.graphic.CutenetWorld;
import cgc.cutenet.graphic.MouseClickHandler;

/**
 * <p>Title: geWorkbench </p>
 * <p/>
 * <p>Description: Framework for geneways integration with geWornbench</p>
 * <p/>
 * <p>Copyright: Copyright (c) 2003 -2004</p>
 * <p/>
 * <p>Company: Columbia University</p>
 *
 * @author Kaushal Kumar
 * @version 1.0
 */

public class GeCutenetPanel implements VisualPlugin, ActionListener {

    CutenetFrame cutenetFrame;
    JPanel cutenetJPanel;
    CutenetManager cutenetManager;
    public Component getComponent(){
        cutenetJPanel = new JPanel(new BorderLayout());
        JButton button = new JButton("Run Test Query");
        button.addActionListener(this);
        button.setSize(20,20);

        cutenetJPanel.add(button);
        return cutenetJPanel;
    }


    public GeCutenetPanel(){

        cutenetManager = CutenetManager.getCutenetManager();
        try {
            cutenetManager.initialize();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent actionEvent) {
        cutenetManager.getCutenetHelper().queryProtein();
        //cutenetManager.loadAdjMatrix();
    }
}