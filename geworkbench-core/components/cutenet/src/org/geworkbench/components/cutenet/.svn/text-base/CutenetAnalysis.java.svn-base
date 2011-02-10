package org.geworkbench.components.cutenet;

import org.geworkbench.analysis.AbstractAnalysis;
import org.geworkbench.bison.datastructure.biocollections.DSDataSet;
import org.geworkbench.bison.datastructure.biocollections.microarrays.DSMicroarraySet;
import org.geworkbench.bison.datastructure.biocollections.views.DSMicroarraySetView;
import org.geworkbench.bison.datastructure.bioobjects.markers.DSGeneMarker;
import org.geworkbench.bison.datastructure.bioobjects.microarray.DSMicroarray;
import org.geworkbench.bison.model.analysis.AlgorithmExecutionResults;
import org.geworkbench.bison.model.analysis.ClusteringAnalysis;
import org.geworkbench.engine.management.Publish;
import org.geworkbench.engine.management.Subscribe;
import org.geworkbench.events.ProjectEvent;
import org.geworkbench.events.ProjectNodeAddedEvent;
import org.geworkbench.events.SubpanelChangedEvent;
import org.geworkbench.util.pathwaydecoder.mutualinformation.AdjacencyMatrix;
import org.geworkbench.util.pathwaydecoder.mutualinformation.AdjacencyMatrixDataSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: kk2457
 * Date: Nov 30, 2006
 * Time: 3:52:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class CutenetAnalysis extends AbstractAnalysis implements ClusteringAnalysis {

    // Maximum number of markers that we are willing to query at once
    public static final int MAX_MARKERS = 50;

    private CutenetAnalysisPanel panel;

    private DSMicroarraySet ma;

    private HashSet<String> selectedMarkerIDs = null;

    public CutenetAnalysis() {
        panel = new CutenetAnalysisPanel();
        setDefaultPanel(panel);
    }

    public AlgorithmExecutionResults execute(Object input) {
        assert (input instanceof DSMicroarraySetView);
        DSMicroarraySetView<DSGeneMarker, DSMicroarray> view = (DSMicroarraySetView<DSGeneMarker, DSMicroarray>) input;
        ma = view.getMicroarraySet();
        AdjacencyMatrixDataSet amDataSet = null;
        try {
            amDataSet = loadAdjMatrix();
        } catch (Exception e) {
            e.printStackTrace();
            return new AlgorithmExecutionResults(false, "Could not connect to Cutenet.", null);
        }
        if (amDataSet != null) {
            return new AlgorithmExecutionResults(true, "successful execution", amDataSet);
        } else {
            return new AlgorithmExecutionResults(false, "No markers were selected.", null);
        }
    }

    public int getAnalysisType() {
        return AbstractAnalysis.IGNORE_TYPE;
    }

    @Publish public ProjectNodeAddedEvent publishProjectNodeAddedEvent(ProjectNodeAddedEvent event) {
        return event;
    }

    public AdjacencyMatrixDataSet loadAdjMatrix() throws Exception {

        // Only operate if there are selected markers
        if (selectedMarkerIDs != null) {


            CutenetManager cutenetMgr = CutenetManager.getCutenetManager();
            cutenetMgr.initialize();
            CutenetHelper cutenetHelper = cutenetMgr.getCutenetHelper();
            int count = 0;

            HashSet<String> reducedSet = new HashSet<String>();
            for (String id : selectedMarkerIDs) {
                reducedSet.add(id);
                count++;
                if (count == MAX_MARKERS) {
                    break;
                }
            }
            Set<String> swissProtIDs = cutenetMgr.getSwissProtIDsForMarkers(selectedMarkerIDs);
            ArrayList<CutenetInteraction> interactions = new ArrayList<CutenetInteraction>();
            try {
                for (String swissProtID : swissProtIDs) {
                    CutenetInteraction[] ci = cutenetMgr.getInteractions(swissProtID, 0);
                    interactions.addAll(Arrays.asList(ci));
                }
                CutenetInteraction[] ci = interactions.toArray(new CutenetInteraction[0]);
                AdjacencyMatrix am = cutenetMgr.getAdjacencyMatrix(ci, ma);
                AdjacencyMatrixDataSet amDataSet = cutenetMgr.createAdjacencyMatrixDataSet(am, "geneways test-matrix");
                return amDataSet;
            } catch (Exception e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        return null;
    }

    @Subscribe public void receive(ProjectEvent event, Object source) {
        DSDataSet dataSet = event.getDataSet();
        if ((dataSet != null) && (dataSet instanceof DSMicroarraySet)) {
            panel.setMaSet((DSMicroarraySet) dataSet);
            //panel.rebuildForm();
        }
    }


    @Subscribe public void receive(org.geworkbench.events.GeneSelectorEvent e, Object source) {
        if (e.getPanel() != null) {
            selectedMarkerIDs = new HashSet<String>();
            for (DSGeneMarker marker : e.getPanel()) {
                selectedMarkerIDs.add(marker.getLabel());
            }
        } else {
            selectedMarkerIDs = null;
        }
    }

    @Publish public SubpanelChangedEvent publishSubpanelChangedEvent(SubpanelChangedEvent event) {
        return event;
    }


}
