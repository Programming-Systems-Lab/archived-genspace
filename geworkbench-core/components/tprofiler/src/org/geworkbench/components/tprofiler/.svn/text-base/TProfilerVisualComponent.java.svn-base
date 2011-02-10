package org.geworkbench.components.tprofiler;

import org.geworkbench.bison.datastructure.biocollections.DSDataSet;
import org.geworkbench.engine.config.VisualPlugin;
import org.geworkbench.engine.management.AcceptTypes;
import org.geworkbench.engine.management.Subscribe;
import org.geworkbench.events.*;

import javax.swing.*;
import java.awt.*;



/**
 * Created by IntelliJ IDEA.
 * User: magsber    adapted from @author John Watkinson
 * Date: Mar 21, 2006
 * Time: 9:03:33 PM
 * To change this template use File | Settings | File Templates.
 */
@AcceptTypes({TProfilerResults.class}) public class TProfilerVisualComponent implements VisualPlugin {


    public TProfilerResults results;


    private JPanel mainPanel;


    private TProfilerTable table = new TProfilerTable();

    private JLabel infoLabel;

    /**
     * Constructor lays out the component and adds behaviors.
     */
    public TProfilerVisualComponent() {

        //// Create and lay out components


        StringBuffer htmlText = new StringBuffer() ;
        htmlText.append("<html><body>");
        htmlText.append("<h1>t-profiler: ");
        htmlText.append("</h1>Scoring the activity of pre-defined groups of genes using gene expression data");
        htmlText.append("<div><br>");
        infoLabel = new JLabel("");
        infoLabel.setText(htmlText.toString());
       // mainPanel.add(infoLabel);




       JScrollPane pane =   new JScrollPane(table);
        table.setSize(500, 400);
       pane.setWheelScrollingEnabled(true);
        mainPanel = new JPanel(new BorderLayout());
       mainPanel.add(pane);
       // selectorPanel.add(pane);


    }

    public Component getComponent() {
        //return bottomPanel;
        return mainPanel;
    }

        @Subscribe public void receive(ProjectEvent event, Object source) {
        DSDataSet dataSet =  event.getDataSet();
        if (dataSet instanceof TProfilerResults)
        {
            results = (TProfilerResults)dataSet;
            table.setResults(results);
            table.sortByTValue();



        }

    }


    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }
}
