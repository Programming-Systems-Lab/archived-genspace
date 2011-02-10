package org.geworkbench.components.patterns;

import org.geworkbench.bison.datastructure.biocollections.DSDataSet;
import org.geworkbench.bison.datastructure.biocollections.DSAncillaryDataSet;
import org.geworkbench.builtin.projects.ProjectPanel;
import org.geworkbench.builtin.projects.ProjectSelection;
import org.geworkbench.engine.config.MenuListener;
import org.geworkbench.engine.config.VisualPlugin;
import org.geworkbench.engine.management.AcceptTypes;
import org.geworkbench.engine.management.Subscribe;
import org.geworkbench.events.ProjectEvent;
import org.geworkbench.util.patterns.PatternDB;

import javax.swing.event.EventListenerList;
import java.awt.*;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * <p>Title: PatternViewAppComponent</p>
 * <p>Description: Follows the structure of the other ViewAppComponents (e.g. MicroArrayViewAppComponent</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Califano Lab</p>
 *
 * @author
 * @version 1.0
 */

// todo - make PatternDB the only way to interface with this component, rather than the sequence table row stuff
@AcceptTypes({PatternDB.class}) public class PatternViewAppComponent implements VisualPlugin, MenuListener, PropertyChangeListener {

    PatternViewWidget pWidget = null;
    EventListenerList listenerList = new EventListenerList();

    public PatternViewAppComponent() {
        try {
            pWidget = new PatternViewWidget();
        } catch (Exception e) {
            System.out.println("PatternViewAppComponent:::::::::::::::constructor: " + e.toString());
            e.printStackTrace();
        }
    }

    public Component getComponent() {
        return pWidget;
    }

    public ActionListener getActionListener(String var) {
        //what action?
        return null; //currently null so compiler won't bleat at me!
    }

    @Subscribe public void receive(ProjectEvent e, Object source) {
        try {
            if(e==null||source==null){
                return;
            }
                ProjectSelection selection = ((ProjectPanel) source).getSelection();
            if(selection!=null){
             DSAncillaryDataSet dataFile = selection.getDataSubSet();
                if(dataFile!=null){
              

                if (dataFile instanceof org.geworkbench.util.patterns.PatternDB) {
                    pWidget.updateList((PatternDB)dataFile);
                    System.out.println(dataFile + " patternDB=  " + ((PatternDB)dataFile).size() );
                    // Do something if a new pattern file or database has been loaded
                }
                }
            }
        } catch (Exception ex) {
            System.out.println("PatternViewAppComponent:::::::::::::::::receiveProjectSelection(ProjectEvent e): " + ex.toString());
            ex.printStackTrace();
        }
    }

    public void propertyChange(PropertyChangeEvent e) {
        String propertyName = e.getPropertyName();
        //what next???
    }

    @Subscribe public void sequenceDiscoveryTableRowSelected(org.geworkbench.events.SequenceDiscoveryTableEvent e, Object publisher) {
        pWidget.sequenceDiscoveryTableRowSelected(e);
    }
}
