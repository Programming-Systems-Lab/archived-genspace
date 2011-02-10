package org.geworkbench.components.tprofiler;

import org.apache.commons.math.stat.descriptive.SummaryStatistics;
import org.apache.commons.math.distribution.*;
import org.apache.commons.math.stat.descriptive.SummaryStatisticsImpl;
import org.geworkbench.bison.annotation.CSAnnotationContext;
import org.geworkbench.bison.annotation.CSAnnotationContextManager;
import org.geworkbench.bison.annotation.DSAnnotationContext;
import org.geworkbench.bison.datastructure.biocollections.DSDataSet;
import org.geworkbench.bison.datastructure.biocollections.microarrays.DSMicroarraySet;
import org.geworkbench.bison.datastructure.bioobjects.markers.DSGeneMarker;
import org.geworkbench.bison.datastructure.bioobjects.markers.annotationparser.AnnotationParser;
import org.geworkbench.bison.datastructure.bioobjects.markers.goterms.GOTerm;
import org.geworkbench.bison.datastructure.bioobjects.markers.goterms.GeneOntologyTree;
import org.geworkbench.bison.datastructure.bioobjects.markers.goterms.GoMapping;
import org.geworkbench.bison.datastructure.bioobjects.microarray.*;
import org.geworkbench.bison.datastructure.complex.panels.DSItemList;
import org.geworkbench.bison.datastructure.complex.panels.DSPanel;
import org.geworkbench.engine.config.VisualPlugin;
import org.geworkbench.engine.management.AcceptTypes;
import org.geworkbench.engine.management.Publish;
import org.geworkbench.engine.management.Subscribe;
import org.geworkbench.events.PhenotypeSelectorEvent;
import org.geworkbench.events.ProjectEvent;
import org.geworkbench.events.ProjectNodeAddedEvent;
import org.geworkbench.events.SingleMicroarrayEvent;


import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.io.*;
import java.text.NumberFormat;
import java.text.DecimalFormat;

import cern.jet.random.StudentT;
import cern.jet.random.engine.RandomEngine;

/**
 * Created by IntelliJ IDEA.
 * User: magsber
 * Date: Apr 10, 2006
 * Time: 8:23:48 PM
 * To change this template use File | Settings | File Templates.
 */
@AcceptTypes({DSMicroarraySet.class})
public class TProfiler extends JPanel implements VisualPlugin {

    private DSDataSet dataSet;
    private DSMicroarraySet microarraySet;
    private JLabel infoLabel;
    protected JPanel mainPanel;
    private ButtonGroup group;
    private TProfilerResults result = null;
    private DSMicroarray singleMicroarray;
    final String USERSETTINGSFILE = "userSettings.txt";


    public TProfiler() {

        infoLabel = new JLabel("");
        add(infoLabel);
        StringBuffer htmlText = new StringBuffer();
        htmlText.append("<html><body>");
        htmlText.append("<h1>t-profiler: ");
        htmlText.append("</h1>Scoring the activity of pre-defined groups of genes using gene expression data");
        htmlText.append("<div><br>");
        infoLabel.setText(htmlText.toString());

        JRadioButton ratio = new JRadioButton("Single Experminent Analysis");
        JRadioButton absolute = new JRadioButton("Multiple Experiment Analysis");

        absolute.setName("multi");
        ratio.setName("single");

        // Associate the two buttons with a button group
         group = new ButtonGroup();
         group.add(ratio);
         group.add(absolute);

        add(ratio);
        add(absolute);

        JButton loadCustomButton = new JButton("Load Custom Data Annotations...");

        loadCustomButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadCustomAnnotations();
            }
        });

        add(loadCustomButton);


        JButton runTProfiler = new JButton("Run TProfiler");
        runTProfiler.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Thread(new Runnable() {
                    public void run() {
                        try {
                            JRadioButton sel = getSelection(group);
                            String name = sel.getName();
                            tProfilerStuff(name);
                        } catch (Exception x) {
                            x.printStackTrace();
                        }
                    }
                }).start();
            }
            });
        add(runTProfiler);

        /*DSAnnotationContext<DSMicroarray> context = CSAnnotationContextManager.getInstance().getCurrentContext(microarraySet);

        if (context != null)
        {
            infoLabel = new JLabel("");
            add(infoLabel);
            htmlText = new StringBuffer();
            htmlText.append("<html><body>");
            htmlText.append("Annotation Information: ");
            htmlText.append((context.getAnnotationTypesForItem((DSMicroarray) microarraySet.get(1)).toString()));
            htmlText.append("<div><br>");
            infoLabel.setText(htmlText.toString());
        }
        */


    }

    public TProfiler(LayoutManager layoutManager, boolean b, DSMicroarraySet microarraySet) {
        super(layoutManager, b);
        this.microarraySet = microarraySet;
    }

    // This method returns the selected radio button in a button group

     public static JRadioButton getSelection(ButtonGroup group) {
             for (Enumeration e=group.getElements(); e.hasMoreElements(); ) {
                 JRadioButton b = (JRadioButton)e.nextElement();
                 if (b.getModel() == group.getSelection()) {
                     return b;
                 }
             }
             return null;
     }


    /**
     * This method fulfills the contract of the {@link VisualPlugin} interface.
     * It returns the GUI component for this visual plugin.
     */
    public Component getComponent() {
        // In this case, this object is also the GUI component.
        return this;
    }



    public void tProfilerStuff(String runType) throws Exception {




        if (runType.equals("multi"))
        {
            runMultiArrayTTest();

        }
        else if (runType.equals("single"))
        {
            runSingleArrayTTest();

        }
       if (result.getNumberOfGroups() > 0) {


        final ProjectNodeAddedEvent event = new ProjectNodeAddedEvent("t-profiler result", null, result);
        publishProjectNodeAddedEvent(event);

        return;


        }
    }


        private void loadCustomAnnotations() {

        JFileChooser fc = new JFileChooser(this.getLastDirectory());
        javax.swing.filechooser.FileFilter filter = new FileFilter() {

            public String getDescription() {
                return "Comma Separated Values Files";
            }

            public boolean accept(File f) {
                boolean returnVal = false;
                if (f.isDirectory() || f.getName().toLowerCase().endsWith(".csv")) {
                    return true;
                }
                return returnVal;
            }

        };

        fc.setFileFilter(filter);
        fc.setDialogTitle("Open Annotation Set");
        int choice = fc.showOpenDialog(mainPanel);
        if (choice == JFileChooser.APPROVE_OPTION) {
        try {

                String filepath = fc.getCurrentDirectory().getCanonicalPath();
                setLastDirectory(filepath);


            } catch (Exception ex) {
                ex.printStackTrace();

            }

            // Load custom annotations in annotation parser
            AnnotationParser.parseCustomAnnotations(fc.getSelectedFile(), dataSet);

        }
    }

        public String getLastDirectory() {
        String dir = ".";
        try {
            String filename = USERSETTINGSFILE;
            if (System.getProperty("tProfilerSettings") != null) {
                filename = System.getProperty("tProfilerSettings");
            }

            File file = new File(filename);
            if (file.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(file));

                dir = br.readLine();
                br.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        if (dir == null) {
            dir = ".";
        }
        return dir;
    }


    public void setLastDirectory(String dir) {
        try { //save current settings.
            String outputfile = USERSETTINGSFILE;
            if (System.getProperty("tProfilerSettings") != null) {
                outputfile = System.getProperty(
                        "tProfilerSettings");
            }
            BufferedWriter br = new BufferedWriter(new FileWriter(
                    outputfile));
            br.write(dir);
            br.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    public void runMultiArrayTTest() throws Exception {

        result = new TProfilerResults(microarraySet, "T-Profiler Results");
        // Get all markers in the microarrays
        DSItemList<DSGeneMarker> markers = microarraySet.getMarkers();
        // Get case and control sets
        DSAnnotationContext<DSMicroarray> context = CSAnnotationContextManager.getInstance().getCurrentContext(microarraySet);

        DSPanel<DSMicroarray> controlMicroararys = context.getActivatedItemsForClass(CSAnnotationContext.CLASS_CONTROL);
        DSPanel<DSMicroarray> caseMicroararys = context.getActivatedItemsForClass(CSAnnotationContext.CLASS_CASE);

        /*getRatio values*/
         int size = markers.size();
         int index = 0;

        int prog = 0;




         SummaryStatistics avControlStats;
         SummaryStatistics avCaseStats;




        float[] ratios = new float[size];



        ListIterator allMarkers = markers.listIterator();
        while (allMarkers.hasNext()) {



            DSGeneMarker nextElement = (DSGeneMarker) allMarkers.next();

            avControlStats = new SummaryStatisticsImpl();
            avCaseStats = new SummaryStatisticsImpl();

            for (DSMicroarray microarray : controlMicroararys) {

                avControlStats.addValue(microarray.getMarkerValue(nextElement).getValue());

            }
            for (DSMicroarray microarray : caseMicroararys) {

                avCaseStats.addValue(microarray.getMarkerValue(nextElement).getValue());

            }
            double caseMeanValue = avCaseStats.getMean();
            double controlMeanValue = avControlStats.getMean();
            double ratio = Math.log(caseMeanValue/controlMeanValue)/Math.log(2);

            ratios[index] = (float)ratio;
            index ++;
            prog++;

        }



          int x = ratios.length;
            CSMicroarray array = new CSMicroarray(x);
            for (int i = 0; i < x; i++) {
                array.setMarkerValue(i, new CSExpressionMarkerValue(ratios[i]));
            }



        /*ratioValues is now a microarray of just like single microarray for single microarray experiment*/


        //// GO Term stuff
        // These two lines are a bit of a hack right now-- we will probably load this in a standard way later
        GeneOntologyTree tree =  GeneOntologyTree.getInstance();
       // tree.parseOBOFile("data/gene_ontology.obo");
        // Construct a mapping of the microarray set markers on to the tree
        GoMapping mapping = new GoMapping(tree, microarraySet);
        // Get all GO terms
        Collection<GOTerm> terms = tree.getAllTerms();
        int progress = 0;
        int maxProgress = terms.size();
        ProgressMonitor progressMonitor = new ProgressMonitor(mainPanel, "Computing t-values...", "", 0, maxProgress);
        for (GOTerm term : terms) {
            progressMonitor.setProgress(progress);
            progressMonitor.setNote(term.getName());
            progress++;
            // For each term, find the associated markers
            Set<String> markerIDs = mapping.getMarkersForGOTerm(term.getId());
            SummaryStatistics caseStats = new SummaryStatisticsImpl();
            SummaryStatistics controlStats = new SummaryStatisticsImpl();


            // Ignore the group if there are fewer than, say 4 markers in the group
            if (markerIDs.size() >= 7) /* threshold to give power to t-test*/ {
                // You can now iterate through these markers, just like we do below (see  "* Iterate..." in comments below)
                // * Iterate through the markers

                   //System.out.println(term.getName());
                int n = markerIDs.size();
                Vector caseValues = new Vector(n);
                Vector controlValues = new Vector(markers.size()-n);

                int groupACounter = 0;
                int groupBCounter = 0;



                ListIterator fullMarkers = markers.listIterator();

                while (fullMarkers.hasNext())
                {
                    DSGeneMarker nextElement = (DSGeneMarker) fullMarkers.next();
                    String markerID = nextElement.getGeneName();
                    System.out.println(nextElement.getDescription() + " - " + markerID);

                    if (markerIDs.contains(markerID))
                    {
                        caseValues.add(array.getMarkerValue(nextElement).getValue());
                        groupACounter++;
                    }
                    else
                    {
                        controlValues.add(array.getMarkerValue(nextElement).getValue());
                        groupBCounter++;
                    }
                }

                Collections.sort(caseValues);
                /*remove the high and the low to get rid of outliers*/

                caseValues.removeElementAt(0);
                caseValues.removeElementAt(caseValues.size()-1);

                Collections.sort(controlValues);
                /*remove the high and the low to get rid of outliers*/

                controlValues.removeElementAt(0);
                controlValues.removeElementAt(controlValues.size()-1);



               double value;
               int vecSize = caseValues.size();

               for (int i = 0; i<vecSize; i++)
                {
                    value = (Double)((caseValues.elementAt(i)));
                    caseStats.addValue(value);



                }

                vecSize = controlValues.size();

                for (int i = 0; i<vecSize; i++)
                {
                    value = (Double)((controlValues.elementAt(i)));
                    controlStats.addValue(value);


                }

                double caseMeanValue = caseStats.getMean();
                double controlMeanValue = controlStats.getMean();
                double caseVar =   caseStats.getVariance();
                double controlVar = controlStats.getVariance();
                //double pooledStandard = pooledStats.getStandardDeviation();
                double pooledSD;
                double pValue;

                int kB = groupBCounter-2;
                int kA = groupACounter-2;

            }
        }

    }



        public void runSingleArrayTTest() throws Exception {
        result = new TProfilerResults(microarraySet, "T-Profiler Results");
        Vector groups = new Vector (100);
        // Get all markers in the microarrays
        DSItemList<DSGeneMarker> markers = microarraySet.getMarkers();
        // Get case and control sets
        DSAnnotationContext<DSMicroarray> context = CSAnnotationContextManager.getInstance().getCurrentContext(microarraySet);
        DSPanel<DSMicroarray> contextMicroarrays = context.getActivatedItemsForClass(CSAnnotationContext.CLASS_CASE);

        //// GO Term stuff
        // These two lines are a bit of a hack right now-- we will probably load this in a standard way later
        //Now it is loaded system-widely.
        GeneOntologyTree tree   =GeneOntologyTree.getInstance();
        // Construct a mapping of the microarray set markers on to the tree
        GoMapping mapping = new GoMapping(tree, microarraySet);

        // Get all GO terms
        Collection<GOTerm> terms = tree.getAllTerms();
        int totalORFs = 0;

        int progress = 0;
        int maxProgress = terms.size();
        ProgressMonitor progressMonitor = new ProgressMonitor(mainPanel, "Computing t-values...", "", 0, maxProgress);
        for (GOTerm term : terms) {
            progressMonitor.setProgress(progress);
            progressMonitor.setNote(term.getName());
            progress++;
            // For each term, find the associated markers
            Set<String> markerIDs = mapping.getMarkersForGOTerm(term.getId());
            SummaryStatistics caseStats = new SummaryStatisticsImpl();
            SummaryStatistics controlStats = new SummaryStatisticsImpl();
            SummaryStatistics pooledStats = new SummaryStatisticsImpl();


            // Ignore the group if there are fewer than, say 4 markers in the group
            if (markerIDs.size() >= 7) /* threshold to give power to t-test*/ {
                // You can now iterate through these markers, just like we do below (see  "* Iterate..." in comments below)
                // * Iterate through the markers

                   //System.out.println(term.getName());
                int n = markerIDs.size();
                Vector caseValues = new Vector(n);
                Vector controlValues = new Vector(markers.size()-n);

                int groupACounter = 0;
                int groupBCounter = 0;

                for (String markerID : markerIDs) {

                    DSGeneMarker marker = markers.get(markerID);


                    //System.out.println(term.getName() + " " + marker.getGeneName() + marker.getValue());

                    for (DSMicroarray microarray : contextMicroarrays) {

                        caseValues.add(microarray.getMarkerValue(marker).getValue());
                        groupACounter++;
                    //caseStats.addValue(microarray.getMarkerValue(marker).getValue());
                   // System.out.println(term.getName() + " " + marker.getGeneName() + microarray.getMarkerValue(marker).getValue());
                   }



                }



               Collections.sort(caseValues);
                /*remove the high and the low to get rid of outliers*/

                caseValues.removeElementAt(0);
                caseValues.removeElementAt(caseValues.size()-1);


               /*now get the rest of the microarray values*/

                singleMicroarray = (DSMicroarray) microarraySet.get(1);

                ListIterator controlMarkers = markers.listIterator();

                while (controlMarkers.hasNext())
                {
                    DSGeneMarker nextElement = (DSGeneMarker) controlMarkers.next();
                    String markerID = nextElement.getShortName();
                    if (markerIDs.contains(markerID))
                    {
                        continue;
                    }

                     controlValues.add(singleMicroarray.getMarkerValue(nextElement).getValue());
                     groupBCounter++;
                }


                Collections.sort(controlValues);
                /*remove the high and the low to get rid of outliers*/

                controlValues.removeElementAt(0);
                controlValues.removeElementAt(controlValues.size()-1);



               double value;
               int vecSize = caseValues.size();

               for (int i = 0; i<vecSize; i++)
                {
                    value = (Double)((caseValues.elementAt(i)));
                    caseStats.addValue(value);
                    pooledStats.addValue(value);


                }

                vecSize = controlValues.size();

                for (int i = 0; i<vecSize; i++)
                {
                    value = (Double)((controlValues.elementAt(i)));
                    controlStats.addValue(value);
                    pooledStats.addValue(value);

                }

                double caseMeanValue = caseStats.getMean();
                double controlMeanValue = controlStats.getMean();
                double caseVar =   caseStats.getVariance();
                double controlVar = controlStats.getVariance();
                double pooledStandard = pooledStats.getStandardDeviation();
                double pooledSD;
                double pValue;

                int kB = groupBCounter-2;
                int kA = groupACounter-2;



                pooledSD = Math.sqrt((((kA -1) * caseVar) + ((kB - 1) * controlVar)) / (kA + kB -2));


                double tResult = (caseMeanValue - controlMeanValue) / (pooledSD * Math.sqrt(1.0/kA + 1.0/kB));

                cern.jet.random.StudentT tTest = new StudentT((Math.min(kA,kB)) - 1, cern.jet.random.StudentT.makeDefaultGenerator());

                 pValue = tTest.cdf(-(Math.abs(tResult)));

                //double eValue = pValue * 1236;


                NumberFormat formatter = new DecimalFormat("0.###E0");

                //double t2 = calculateTValue(groupAValues,groupBValues);
                //System.out.println(term.getName() + " case mean " + caseMeanValue + "# of ORFs " + kA + "#of other markers "  + kB + " t-Value" + t2);
                TProfilerGroup goGroup = new TProfilerGroup(term.getName(), round(tResult,2), pValue,round(caseMeanValue,3), kA, kB, markerIDs);
                groups.addElement(goGroup);

              totalORFs ++;
            }



        }


        int gSize = groups.size();
        for (int y = 0; y < gSize; y++)
        {
            TProfilerGroup tGroup = (TProfilerGroup)groups.elementAt(y);
            double eValue = tGroup.getPValue() * totalORFs;
            //tGroup.
            if (eValue < 0.05)
            {
                        result.addGroup(tGroup);
            }
        }
        System.out.println(new StringBuilder().append("Number of GO terms scored: ").append(totalORFs).toString());
        progressMonitor.close();


    }



    public static double round(double val, int places) {
    long factor = (long)Math.pow(10,places);

    // Shift the decimal the correct number of places
    // to the right.
    val = val * factor;

    // Round to the nearest integer.
    long tmp = Math.round(val);

    // Shift the decimal the correct number of places
    // back to the left.
    return (double)tmp / factor;
    }


    @Publish public ProjectNodeAddedEvent publishProjectNodeAddedEvent(ProjectNodeAddedEvent event) {
        return event;
    }


    @Subscribe public void receive(SingleMicroarrayEvent event, Object source) {
        DSMicroarray array = event.getMicroarray();
        if (array != null) {
            singleMicroarray = array;
            // microarraySet.add(1,singleMicroarray);

        }
    }

       /**
     * This is a <b>Subscribe</b> method. The annotation before the method alerts
     * the engine that it should route published objects to this method.
     * The type of objects that are routed to this method are indicated by the first parameter of the method.
     * In this case, it is {@link ProjectEvent}.
     *
     * @param event  the received object.
     * @param source the entity that published the object.
     */
    @Subscribe
    public void receive(PhenotypeSelectorEvent event, Object source) {
        dataSet = event.getDataSet();

        // We will act on this object if it is a DSMicroarraySet
        if (dataSet instanceof DSMicroarraySet) {
            microarraySet = (DSMicroarraySet) dataSet;

        }



    }

}
