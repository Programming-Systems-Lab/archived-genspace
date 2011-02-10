package org.geworkbench.components.cutenet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.collections15.MultiMap;
import org.apache.commons.collections15.multimap.MultiHashMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.geworkbench.bison.datastructure.biocollections.microarrays.DSMicroarraySet;
import org.geworkbench.bison.datastructure.bioobjects.markers.DSGeneMarker;
import org.geworkbench.bison.datastructure.bioobjects.markers.annotationparser.AnnotationParser;
import org.geworkbench.bison.datastructure.complex.panels.DSItemList;
import org.geworkbench.util.pathwaydecoder.mutualinformation.AdjacencyMatrix;
import org.geworkbench.util.pathwaydecoder.mutualinformation.AdjacencyMatrixDataSet;

import cgc.cutenet.database.DatabaseException;

/**
 * @author John Watkinson, Kaushal Kumar
 */
public class CutenetManager {
	static Log log = LogFactory.getLog(CutenetHelper.class);
	private static CutenetManager ref;

	private CutenetManager() {

	}

	public static synchronized CutenetManager getCutenetManager() {
		if (ref == null) {
			ref = new CutenetManager();
			try {
				ref.initialize();
			} catch (Exception e) {
			}
		}
		return ref;
	}

	private CutenetHelper cutenetHelper;
	private GenewaysControlParams genewaysControlsParams;

	// These two hashmaps are created and populated, so that we can find out the
	// gene name from cytoscape window
	// remember, cytoscape is another component and it can't access variables in
	// the geneways widget.
	private HashMap genewaysIdProteinMap;
	private HashMap genewaysNameIdMap = new HashMap();

	public MultiMap<String, Integer> getSwissProtToMarkerIDMapping(
			DSMicroarraySet microarraySet) {
		MultiHashMap<String, Integer> map = new MultiHashMap<String, Integer>();
		DSItemList<DSGeneMarker> markers = microarraySet.getMarkers();
		int index = 0;
		for (DSGeneMarker marker : markers) {
			if (marker != null && marker.getLabel() != null) {
				try {
					Set<String> swissProtIDs = AnnotationParser
							.getSwissProtIDs(marker.getLabel());
					for (String s : swissProtIDs) {
						map.put(s, new Integer(index));
					}
					index++;
				} catch (Exception e) {
					continue;
				}
			}
		}
		return map;
	}

	public Set<String> getSwissProtIDsForMarkers(Set<String> markerIDs) {
		HashSet<String> results = new HashSet<String>();
		if (markerIDs != null) {
			for (String id : markerIDs) {
				try {
					results.addAll(AnnotationParser.getSwissProtIDs(id));
				} catch (Exception e) {
					continue;
				}
			}
		}
		return results;
	}

	public void initialize() throws Exception {
		cutenetHelper = new CutenetHelper();
	}

	/**
	 * Get all interactions for the protein corresponding to the given SwissProt
	 * ID that have a strength above the given minimum.
	 */
	public CutenetInteraction[] getInteractions(String swissProtID,
			float minimumStrength) throws Exception {
		CutenetProtein cp = cutenetHelper.findProteinBySwissProtId(swissProtID);
		if (cp != null) {
			return (CutenetInteraction[]) cutenetHelper.getInteractions(cp,
					genewaysControlsParams).toArray();
		} else {
			return new CutenetInteraction[0];
		}
	}

	/**
	 * Get all interactions for the protein corresponding to the given SwissProt
	 * ID that have a strength above the given minimum.
	 */
	public ArrayList<CutenetInteraction> getInteractionsBySPId(
			String swissProtID, int geneId, String geneName,
			float minimumStrength) throws Exception {
		CutenetProtein fromCP = cutenetHelper.queryByAccNo(swissProtID,
				"swissprot", false, genewaysControlsParams);
		ArrayList<CutenetInteraction> interactions = new ArrayList<CutenetInteraction>();
		if (fromCP != null) {
			interactions = cutenetHelper.getInteractions(fromCP,
					genewaysControlsParams);
		}
		return interactions;
	}

	/**
	 * Get all interactions for the protein corresponding to the given Name that
	 * have a strength above the given minimum.
	 */
	public CutenetInteraction[] getInteractionsByName(String name,
			float minimumStrength) throws Exception {
		ArrayList results = (ArrayList) cutenetHelper.queryByName(name, false);
		CutenetProtein fromCP = new CutenetProtein(name);
		ArrayList<CutenetInteraction> interactions = new ArrayList();
		for (Iterator itr = results.iterator(); itr.hasNext();) {
			CutenetProtein toCP = (CutenetProtein) itr.next();
			// FIXME: next line, we should find a way to get the action type and
			// set to the last parameter.
			CutenetInteraction newInteraction = new CutenetInteraction(fromCP,
					toCP, 0, "");
			interactions.add(newInteraction);
		}
		return (CutenetInteraction[]) interactions.toArray();
	}

	/**
	 * Gets the strength of the interaction between two proteins, given their
	 * SwissProt IDs.
	 * 
	 * @return the strength, or 0 if there is no interaction.
	 */
	public float getInteractionStrength(String swissProtID1, String swissProtID2)
			throws Exception {
		CutenetProtein cp1 = cutenetHelper
				.findProteinBySwissProtId(swissProtID1);
		CutenetProtein cp2 = cutenetHelper
				.findProteinBySwissProtId(swissProtID2);
		if (cp1 == null || cp2 == null)
			return 0;
		else
			return cutenetHelper.getInteractionStrength(cp1, cp2);
	}

	/**
	 * Given a list of CuteNet interactions and a microarray set, construct an
	 * adjacency matrix.
	 */
	public AdjacencyMatrix getAdjacencyMatrix(
			CutenetInteraction[] interactions, DSMicroarraySet microarraySet) {
		MultiMap<String, Integer> swissMap = getSwissProtToMarkerIDMapping(microarraySet);
		HashMap<Integer, HashMap<Integer, Float>> geneRows = new HashMap<Integer, HashMap<Integer, Float>>();
		for (CutenetInteraction interaction : interactions) {
			CutenetProtein p1 = interaction.getProtein1();
			CutenetProtein p2 = interaction.getProtein2();
			Set<String> ids1 = p1.getSwissProtIDs();
			Set<String> ids2 = p2.getSwissProtIDs();
			HashSet<Integer> markers1 = new HashSet<Integer>();
			HashSet<Integer> markers2 = new HashSet<Integer>();
			for (String s1 : ids1) {
				Collection<Integer> matchedMarkers = swissMap.get(s1);
				if (matchedMarkers != null) {
					markers1.addAll(matchedMarkers);
				}
			}
			for (String s2 : ids2) {
				Collection<Integer> matchedMarkers = swissMap.get(s2);
				if (matchedMarkers != null) {
					markers2.addAll(matchedMarkers);
				}
			}
			for (Integer m1 : markers1) {
				for (Integer m2 : markers2) {
					if (!m1.equals(m2)) { // Would be odd for this to be
											// false, but possible
						HashMap<Integer, Float> row = geneRows.get(m1);
						if (row == null) {
							row = new HashMap<Integer, Float>();
							geneRows.put(m1, row);
						}
						row.put(m2, interaction.getStrength());
					}
				}
			}
		}
		AdjacencyMatrix am = new AdjacencyMatrix(geneRows);
		am.setMicroarraySet(microarraySet);
		am.setLabel("CuteNet Interactions");
		return am;
	}

	/**
	 * Given a list of CuteNet interactions and a microarray set, construct an
	 * adjacency matrix.
	 */
	// TODO: change the method name to something meaningful
	public AdjacencyMatrix getNewAdjacencyMatrix(
			CutenetInteraction[] interactions, DSMicroarraySet microarraySet) {
		HashMap<Integer, HashMap<Integer, Float>> geneRows = new HashMap<Integer, HashMap<Integer, Float>>();
		HashMap<Integer, CutenetProtein> genewaysGeneidNameMap = new HashMap<Integer, CutenetProtein>();

		for (CutenetInteraction interaction : interactions) {
			CutenetProtein p1 = interaction.getProtein1();
			CutenetProtein p2 = interaction.getProtein2();
			int id1 = p1.getId();
			int id2 = p2.getId();

			Integer m1 = new Integer(id1);
			Integer m2 = new Integer(id2);
			String name1 = null;
			String name2 = null;
			if (p1.getSwissProtIDs() != null && p1.getSwissProtIDs().size() > 0)
				name1 = p1.getSwissProtIDs().iterator().next();
			if (p2.getSwissProtIDs() != null && p2.getSwissProtIDs().size() > 0)
				name2 = p2.getSwissProtIDs().iterator().next();
			if (name1 == null)
				name1 = p1.getName();
			if (name2 == null)
				name2 = p2.getName();
			genewaysGeneidNameMap.put(m1, p1);
			genewaysGeneidNameMap.put(m2, p2);
			genewaysNameIdMap.put(name1, m1);
			genewaysNameIdMap.put(name2, m2);
			// System.out.println("m1: "+m1+" m2: "+m2);
			if (!m1.equals(m2)) { // Would be odd for this to be false, but
									// possible
				HashMap<Integer, Float> row = geneRows.get(m1);
				if (row == null) {
					row = new HashMap<Integer, Float>();
					geneRows.put(m1, row);
				}
				// row.put(m2, interaction.getStrength());
				row.put(m2, new Float((p1.getName().length() + 0.1)
						/ (p1.getName().length() + p2.getName().length() + 1)));
			}
		}
		this.genewaysIdProteinMap = genewaysGeneidNameMap;
		AdjacencyMatrix am = new AdjacencyMatrix(geneRows);
		am.setMicroarraySet(microarraySet);
		am.setLabel("CuteNet Interactions");
		return am;
	}

	/**
	 * Upon double-clicking on a node, this method fetches interactions for that
	 * node, and updates the adjacency matrix, and returns it.
	 */
	public AdjacencyMatrix updateAdjacencyMatrix(int geneId,
			AdjacencyMatrix adjMat, DSMicroarraySet microarraySet)
			throws DatabaseException {
		HashMap<Integer, HashMap<Integer, Float>> geneRows = new HashMap<Integer, HashMap<Integer, Float>>();

		ArrayList<CutenetInteraction> interactions = new ArrayList<CutenetInteraction>();
		CutenetProtein currCP = (CutenetProtein) this.genewaysIdProteinMap
				.get(new Integer(geneId));

		interactions = cutenetHelper
				.getIncidentActions2(currCP, genewaysControlsParams,
						genewaysNameIdMap, genewaysIdProteinMap);
		/*
		 * IncidentActionQueryResult [] r =
		 * cutenetHelper.getIncidentActions(currCP, genewaysControlsParams); for
		 * (int i=0; i<r.length; i++) { CutenetProtein nextToCP = null; String
		 * newOrOldName = cutenetHelper.getNodeName(r[i].nodeDbids);
		 * if(genewaysNameIdMap.containsKey(newOrOldName)){ Integer oldId =
		 * (Integer)genewaysNameIdMap.get(newOrOldName); if(oldId != null)
		 * nextToCP = (CutenetProtein)genewaysIdProteinMap.get(oldId); }else{
		 * nextToCP = new CutenetProtein(r[i].nodeDbids, newOrOldName); }
		 * nextToCP.setActionsCount(r.length); interactions.add(new
		 * CutenetInteraction(currCP, nextToCP, 1)); }
		 */

		geneRows = adjMat.getGeneRows();
		HashMap<Integer, Float> row = geneRows.get(geneId);
		if (row == null) {
			row = new HashMap<Integer, Float>();
		}

		for (CutenetInteraction interaction : interactions) {
			CutenetProtein p1 = interaction.getProtein1();
			CutenetProtein p2 = interaction.getProtein2();
			long id1 = p1.getId();
			long id2 = p2.getId();

			Integer m1 = new Integer((int) id1);
			Integer m2 = new Integer((int) id2);
			String name1 = null;
			String name2 = null;
			if (p1.getSwissProtIDs() != null && p1.getSwissProtIDs().size() > 0)
				name1 = p1.getSwissProtIDs().iterator().next();
			if (p2.getSwissProtIDs() != null && p2.getSwissProtIDs().size() > 0)
				name2 = p2.getSwissProtIDs().iterator().next();
			if (name1 == null)
				name1 = p1.getName();
			if (name2 == null)
				name2 = p2.getName();
			if (!genewaysIdProteinMap.containsKey(m1))
				genewaysIdProteinMap.put(m1, p1);
			if (!genewaysIdProteinMap.containsKey(m2))
				genewaysIdProteinMap.put(m2, p2);
			if (!genewaysNameIdMap.containsKey(name1))
				genewaysNameIdMap.put(name1, m1);
			if (!genewaysNameIdMap.containsKey(name2))
				genewaysNameIdMap.put(name2, m2);
			if (!m1.equals(m2)) {
				row.put(m2, interaction.getStrength());
				row.put(m2, new Float((p1.getName().length() + 0.1)
						/ (p1.getName().length() + p2.getName().length() + 1)));
			}
		}
		geneRows.put(geneId, row);

		AdjacencyMatrix am = new AdjacencyMatrix(geneRows);
		am.setMicroarraySet(microarraySet);
		am.setLabel("CuteNet Interactions");
		return am;
	}

	public AdjacencyMatrixDataSet createAdjacencyMatrixDataSet(
			AdjacencyMatrix matrix, String name) {
		DSMicroarraySet mSet = matrix.getMicroarraySet();
		if (mSet != null)
			log.debug("DAMicroArraySet.size(): " + mSet.size());
		else
			log.debug("DSMIccroArraySet is NULL .");
		return new AdjacencyMatrixDataSet(matrix, -1, 0.000001, 100, name,
				name, mSet);
	}

	public CutenetHelper getCutenetHelper() {
		return cutenetHelper;
	}

	public void setGenewaysControlParams(
			GenewaysControlParams genewaysControlParams) {
		this.genewaysControlsParams = genewaysControlParams;
	}

	public GenewaysControlParams getGenewaysControlParams() {
		return this.genewaysControlsParams;
	}

	public Date getMinDate() {
		Date minDate = new Date(0);
		try {
			minDate = cutenetHelper.getDBHelper().getMinDate();
		} catch (Exception e) {
		}
		return minDate;
	}

	public Date getMaxDate() {
		Date maxDate = new Date(0);
		try {
			maxDate = cutenetHelper.getDBHelper().getMaxDate();
		} catch (Exception e) {
		}
		return maxDate;
	}

	public HashMap getGenewaysIdProteinMap() {
		return genewaysIdProteinMap;
	}

	public HashMap getGenewaysNameIdMap() {
		return genewaysNameIdMap;
	}

	public void setGenewaysNameIdMap(HashMap genewaysNameIdMap) {
		this.genewaysNameIdMap = genewaysNameIdMap;
	}

	public boolean isConnected() {
		boolean retVal = false;
		try {
			retVal = cutenetHelper.isConnected();
		} catch (Exception e) {
		}
		return retVal;
	}

}
