package org.geworkbench.components.filtering;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.geworkbench.bison.datastructure.biocollections.microarrays.DSMicroarraySet;
import org.geworkbench.bison.datastructure.bioobjects.markers.DSGeneMarker;
import org.geworkbench.bison.datastructure.bioobjects.markers.annotationparser.AnnotationParser;
import org.geworkbench.bison.datastructure.bioobjects.microarray.DSMicroarray;
import org.geworkbench.bison.datastructure.complex.panels.CSPanel;
import org.geworkbench.bison.datastructure.complex.panels.DSItemList;
import org.geworkbench.bison.datastructure.complex.panels.DSPanel;
import org.geworkbench.bison.model.analysis.FilteringAnalysis;
import org.geworkbench.components.filtering.MultipleEntrezGeneIDFilterPanel.Action;
import org.geworkbench.engine.management.Publish;

/**
 * Multiple Entrez ID filter.
 * 
 * The user preferences are collected in this filter's associate parameters GUI
 * (<code>MultipleEntrezGeneIDFilter</code>).
 * 
 * @version $Id: MultipleEntrezGeneIDFilter.java 7478 2011-02-23 16:39:10Z zji $
 */
public class MultipleEntrezGeneIDFilter extends FilteringAnalysis {
	private static final long serialVersionUID = -3603151182199536102L;

	private boolean filterNoEntrezID = false;
	private boolean filterMultipleEntrezIDs = false;

	List<Integer> noEntrezIDList = new ArrayList<Integer>();
	List<Integer> multipleEntrezIDsList = new ArrayList<Integer>();

	private Action filterAction;

	public MultipleEntrezGeneIDFilter() {
		setDefaultPanel(new MultipleEntrezGeneIDFilterPanel());
	}

	@Override
	public List<Integer> getMarkersToBeRemoved(DSMicroarraySet<?> input) {
		
		if (filterAction == Action.REMOVE) {
			return remove(input);
		}else if (filterAction == Action.CREATE_FROM_MATCHING) {
			createFromMatching(input);
		}else if (filterAction == Action.CREATE_FROM_EXCLUDING) {
			createFromExcluding(input);
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	private List<Integer> remove(DSMicroarraySet<?> input) {
		maSet = (DSMicroarraySet<DSMicroarray>) input;
		DSItemList<DSGeneMarker> dsItemList = maSet.getMarkers();

		createFilterLists(dsItemList);
		
		List<Integer> removeList = new ArrayList<Integer>();

		if (filterNoEntrezID) {
			removeList.addAll(noEntrezIDList);
		}

		if (filterMultipleEntrezIDs) {
			removeList.addAll(multipleEntrezIDsList);
		}

		return removeList;
	}

	private List<Integer> createFilterLists(DSItemList<DSGeneMarker> dsItemList) {
		List<Integer> removeList = new ArrayList<Integer>();
		getParametersFromPanel();

		noEntrezIDList.clear();
		multipleEntrezIDsList.clear();
		int markerCount = dsItemList.size();
		for (int i = 0; i < markerCount; i++) {
			DSGeneMarker dsGeneMarker = dsItemList.get(i);
			String geneMarkerLabel = dsGeneMarker.getLabel();
			Set<String> geneIDs = AnnotationParser.getGeneIDs(geneMarkerLabel);

			if (filterNoEntrezID) {
				if (geneIDs.size() == 0	|| (geneIDs.size() == 1 && (geneIDs.contains("---") || geneIDs.contains("")))) {
					noEntrezIDList.add(i);
					removeList.add(i);
				}
			}

			if (filterMultipleEntrezIDs && geneIDs.size() > 1) {
				multipleEntrezIDsList.add(i);
				removeList.add(i);
			}
		}

		return removeList;
	}

	@SuppressWarnings("unchecked")
	private void createFromMatching(DSMicroarraySet<?> input){
		maSet = (DSMicroarraySet<DSMicroarray>) input;
		String label = input.getLabel();
		DSItemList<DSGeneMarker> dsItemList = maSet.getMarkers();
		getParametersFromPanel();
		DSPanel<DSGeneMarker> selectedMarkersNoEntrezIDs = new CSPanel<DSGeneMarker>(label + "_GID_none", "Filter");
		DSPanel<DSGeneMarker> selectedMarkersMultipleEntrezIDs = new CSPanel<DSGeneMarker>(label + "_GID_multiple", "Filter");

		int markerCount = dsItemList.size();
		for (int i = 0; i < markerCount; i++) {
			DSGeneMarker dsGeneMarker = dsItemList.get(i);
			String geneMarkerLabel = dsGeneMarker.getLabel();
			Set<String> geneIDs = AnnotationParser.getGeneIDs(geneMarkerLabel);
			if (filterNoEntrezID) {
				if (geneIDs.size() == 0	|| (geneIDs.size() == 1 && (geneIDs.contains("---") || geneIDs.contains("")))) {
					selectedMarkersNoEntrezIDs.add(dsGeneMarker);
				}

				if (filterMultipleEntrezIDs && geneIDs.size() > 1) {
					selectedMarkersMultipleEntrezIDs.add(dsGeneMarker);
				}
			}
		}

		 selectedMarkersNoEntrezIDs.setActive(true);
		 publishSubpanelChangedEvent(new org.geworkbench.events.SubpanelChangedEvent<DSGeneMarker>(DSGeneMarker.class, selectedMarkersNoEntrezIDs,
		 org.geworkbench.events.SubpanelChangedEvent.NEW));
		
		 selectedMarkersMultipleEntrezIDs.setActive(true);
		 publishSubpanelChangedEvent(new org.geworkbench.events.SubpanelChangedEvent<DSGeneMarker>( DSGeneMarker.class, selectedMarkersMultipleEntrezIDs,
		 org.geworkbench.events.SubpanelChangedEvent.NEW));

	}

	@SuppressWarnings("unchecked")
	private void createFromExcluding(DSMicroarraySet<?> input){
		maSet = (DSMicroarraySet<DSMicroarray>) input;
		String label = input.getLabel();
		DSItemList<DSGeneMarker> dsItemList = maSet.getMarkers();
		getParametersFromPanel();
		DSPanel<DSGeneMarker> selectedMarkersNonFilteredEntrezIDs = new CSPanel<DSGeneMarker>(label + "_GID_filtered", "Filter");
		int markerCount = dsItemList.size();
		for (int i = 0; i < markerCount; i++) {
			DSGeneMarker dsGeneMarker = dsItemList.get(i);
			String geneMarkerLabel = dsGeneMarker.getLabel();
			
			Set<String> geneIDs = AnnotationParser.getGeneIDs(geneMarkerLabel);

			if (filterNoEntrezID) {
				if (geneIDs.size() == 0 || (geneIDs.size() == 1 && (geneIDs.contains("---") || geneIDs.contains("")))) {
					continue;
				}
				
				if (filterMultipleEntrezIDs && geneIDs.size() > 1) {
					continue;
				}
			}
			
			selectedMarkersNonFilteredEntrezIDs.add(dsGeneMarker);
		}
		
		selectedMarkersNonFilteredEntrezIDs.setActive(true);
		publishSubpanelChangedEvent(new org.geworkbench.events.SubpanelChangedEvent<DSGeneMarker>(DSGeneMarker.class, 
																						selectedMarkersNonFilteredEntrezIDs,
																						org.geworkbench.events.SubpanelChangedEvent.NEW));
	}
	
	@SuppressWarnings("rawtypes")
	@Publish
	public org.geworkbench.events.SubpanelChangedEvent publishSubpanelChangedEvent(
		   org.geworkbench.events.SubpanelChangedEvent event) {
		return event;
	}

	@Override
	protected void getParametersFromPanel() {
		MultipleEntrezGeneIDFilterPanel multipleEntrezGeneIDFilterPanel = (MultipleEntrezGeneIDFilterPanel) aspp;
		filterNoEntrezID = multipleEntrezGeneIDFilterPanel.isNoEntrezIDsStatusSelected();
		filterMultipleEntrezIDs = multipleEntrezGeneIDFilterPanel.isMultipleEntrezIDsStatusSelected();

		filterAction = multipleEntrezGeneIDFilterPanel.getFilterAction();
	}

	@Override
	protected boolean isMissing(int arrayIndex, int markerIndex) {
		return true;
	}
}