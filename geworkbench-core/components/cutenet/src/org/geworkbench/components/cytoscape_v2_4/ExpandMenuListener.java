package org.geworkbench.components.cytoscape_v2_4;

import geneways.geneways60test.object_model.Name;
import geneways.geneways60test.object_model.NamedEntity;
import giny.model.Node;
import giny.view.NodeView;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JProgressBar;
import javax.swing.JRadioButtonMenuItem;

import org.apache.commons.collections15.MultiMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.geworkbench.bison.datastructure.biocollections.microarrays.DSMicroarraySet;
import org.geworkbench.bison.datastructure.bioobjects.markers.DSGeneMarker;
import org.geworkbench.bison.datastructure.bioobjects.microarray.DSMicroarray;
import org.geworkbench.bison.datastructure.complex.panels.CSPanel;
import org.geworkbench.bison.datastructure.complex.panels.DSPanel;
import org.geworkbench.components.cutenet.CutenetHelper;
import org.geworkbench.components.cutenet.CutenetManager;
import org.geworkbench.components.cutenet.CutenetProtein;
import org.geworkbench.events.SubpanelChangedEvent;
import org.geworkbench.util.pathwaydecoder.mutualinformation.AdjacencyMatrix;

import cgc.cutenet.database.view.DBId;
import cytoscape.CyEdge;
import cytoscape.CyNetwork;
import cytoscape.CyNode;
import cytoscape.Cytoscape;
import ding.view.DNodeView;
import ding.view.NodeContextMenuListener;

public class ExpandMenuListener implements NodeContextMenuListener {
	final static Log log = LogFactory.getLog(ExpandMenuListener.class);
	private CytoscapeWidget cytoscapeWidget = null;

	CutenetManager cutenetManager;
	CutenetHelper cutenetHelper;

	// following fields are a temporary solution for refactoring
	private AdjacencyMatrix adjMatrix;
	protected MultiMap<String, Integer> swissprotIdToMarkerIdMap = null;
	protected DSMicroarraySet<? extends DSMicroarray> maSet = null;
	protected JProgressBar jProgressBar = null;
	protected CyNetwork cytoNetwork = null;

	protected List<Long> runningThreads = null;

	public ExpandMenuListener(CytoscapeWidget cytoscapeWidget) {
		cutenetManager = CutenetManager.getCutenetManager();
		cutenetHelper = cutenetManager.getCutenetHelper();

		this.cytoscapeWidget = cytoscapeWidget;
		adjMatrix = cytoscapeWidget.adjMatrix;
		swissprotIdToMarkerIdMap = cytoscapeWidget.swissprotIdToMarkerIdMap;
		maSet = cytoscapeWidget.maSet;
		jProgressBar = cytoscapeWidget.jProgressBar;
		cytoNetwork = cytoscapeWidget.cytoNetwork;

		runningThreads = new ArrayList<Long>();
	}

	/**
	 * @param nodeView
	 *            The clicked NodeView
	 * @param menu
	 *            popup menu to add the Bypass menu
	 */
	public void addNodeContextMenuItems(final NodeView nodeView, JPopupMenu menu) {

		if (menu == null) {
			menu = new JPopupMenu();
		}

		JMenu genewaysMenu = new JMenu("Geneways Options ");
		JMenuItem menuItem1 = new JMenuItem(
				new ExpandAction(nodeView, "expand"));
		JMenuItem menuItem2 = new JMenuItem(
				new ShrinkAction(nodeView, "shrink"));
		JMenuItem menuItem3 = new JMenuItem(
				new RemoveAction(nodeView, "remove"));
		JMenu nodeLabelMenu = new JMenu("Node Label ");
		ArrayList<String> accessionNos = getAccessionNumbers(nodeView);
		final ButtonGroup labelGroup = new ButtonGroup();
		if (accessionNos != null && accessionNos.size() > 0) {
			for (Iterator<String> itr = accessionNos.iterator(); itr.hasNext();) {
				String nextLabel = itr.next();
				JRadioButtonMenuItem nextButton = new JRadioButtonMenuItem(
						new LabelChangeAction(nodeView, cutenetManager,
								nextLabel));
				if (nodeView.getLabel().getText().equals(nextLabel))
					nextButton.setSelected(true);
				nextButton.setHorizontalTextPosition(JMenuItem.RIGHT);
				// nextButton.setBorderPainted(true);
				// nextButton.areFocusTraversalKeysSet()
				labelGroup.add(nextButton);
				nodeLabelMenu.add(nextButton);
			}
		}
		genewaysMenu.add(menuItem1);
		genewaysMenu.add(menuItem2);
		genewaysMenu.add(menuItem3);
		menu.add(genewaysMenu);
		menu.add(nodeLabelMenu);

		JMenu addToSetMenu = new JMenu("Add to set ");
		JMenuItem menuItemIntersection = new JMenuItem(new IntersectionAction(
				"Intersection"));
		JMenuItem menuItemUnion = new JMenuItem(new UnionAction("Union"));
		addToSetMenu.add(menuItemIntersection);
		addToSetMenu.add(menuItemUnion);
		menu.add(addToSetMenu);
	}

	private class IntersectionAction extends AbstractAction {

		/**
		 * 
		 */
		private static final long serialVersionUID = -1559843540544628381L;

		public IntersectionAction(String name) {
			super(name);
		}

		@SuppressWarnings( { "unchecked", "deprecation" })
		public void actionPerformed(ActionEvent actionEvent) {
			if (Cytoscape.getCurrentNetworkView() != null
					&& Cytoscape.getCurrentNetwork() != null) {
				java.util.List nodes = Cytoscape.getCurrentNetworkView()
						.getSelectedNodes();
				log.debug(nodes.size() + " node(s) selected");

				DSPanel<DSGeneMarker> IntersectionMarkers = new CSPanel<DSGeneMarker>(
						"Intersection Genes", "Cytoscape");
				Set<Node> neighborsOfAllNodes = new HashSet<Node>();
				/*
				 * If we have N nodes, we'll need N lists to hold their
				 * neighbors
				 */
				List[] neighborsOfNodes = new ArrayList[nodes.size()];
				for (int i = 0; i < nodes.size(); i++) {
					DNodeView pnode = (DNodeView) nodes.get(i);
					Node node = pnode.getNode();
					List<Node> neighbors = Cytoscape.getCurrentNetworkView()
							.getNetwork().neighborsList(node);
					neighborsOfNodes[i] = neighbors;
				}
				/* Then, we'll need to get the intersection from those lists. */
				/*
				 * The logic here is, if a node does not existing in one of the
				 * lists, it does not exist in the intersection.
				 */
				for (int i = 0; i < neighborsOfNodes[0].size(); i++) {
					boolean atListOneNotContains = false;
					for (int n = 0; n < nodes.size(); n++) {
						if (!neighborsOfNodes[n].contains(neighborsOfNodes[0]
								.get(i))) {
							atListOneNotContains = true;
						}
					}
					if (!atListOneNotContains)// this node exist in all lists
						neighborsOfAllNodes.add((Node) neighborsOfNodes[0]
								.get(i));
				}

				log.debug("neighborsOfAllNodes:#" + neighborsOfAllNodes.size());
				IntersectionMarkers.addAll(nodesToMarkers(neighborsOfAllNodes));
				IntersectionMarkers.setActive(true);
				/*
				 * skip if GeneTaggedEvent is being processed, to avoid event
				 * cycle.
				 */
				if (cytoscapeWidget.publishEnabled)
					publishSubpanelChangedEvent(new org.geworkbench.events.SubpanelChangedEvent<DSGeneMarker>(
							DSGeneMarker.class,
							IntersectionMarkers,
							org.geworkbench.events.SubpanelChangedEvent.SET_CONTENTS));

			}
		}

	}

	private void publishSubpanelChangedEvent(
			SubpanelChangedEvent<DSGeneMarker> subpanelChangedEvent) {
		cytoscapeWidget.publishSubpanelChangedEvent(subpanelChangedEvent);

	}

	private class UnionAction extends AbstractAction {

		/**
		 * 
		 */
		private static final long serialVersionUID = 5057482753345747180L;

		public UnionAction(String name) {
			super(name);
		}

		@SuppressWarnings( { "unchecked", "deprecation" })
		public void actionPerformed(ActionEvent actionEvent) {
			if (Cytoscape.getCurrentNetworkView() != null
					&& Cytoscape.getCurrentNetwork() != null) {
				java.util.List nodes = Cytoscape.getCurrentNetworkView()
						.getSelectedNodes();
				log.debug(nodes.size() + " node(s) selected");

				DSPanel<DSGeneMarker> UnionMarkers = new CSPanel<DSGeneMarker>(
						"Union Genes", "Cytoscape");
				Set<Node> neighborsOfAllNodes = new HashSet<Node>();
				/* Add all neighbors */
				for (int i = 0; i < nodes.size(); i++) {
					DNodeView pnode = (DNodeView) nodes.get(i);
					Node node = pnode.getNode();
					List<Node> neighbors = Cytoscape.getCurrentNetworkView()
							.getNetwork().neighborsList(node);
					if (neighbors != null) {
						neighborsOfAllNodes.addAll(neighbors);
					}
				}
				/* Remove selected nodes if exist in neighbor nodes. */
				for (int i = 0; i < nodes.size(); i++) {
					neighborsOfAllNodes.remove(((DNodeView) nodes.get(i))
							.getNode());
				}
				log.debug("neighborsOfAllNodes:#" + neighborsOfAllNodes.size());
				UnionMarkers.addAll(nodesToMarkers(neighborsOfAllNodes));
				UnionMarkers.setActive(true);
				/*
				 * Skip if GeneTaggedEvent is being processed, to avoid event
				 * cycle.
				 */
				if (cytoscapeWidget.publishEnabled)
					publishSubpanelChangedEvent(new org.geworkbench.events.SubpanelChangedEvent<DSGeneMarker>(
							DSGeneMarker.class,
							UnionMarkers,
							org.geworkbench.events.SubpanelChangedEvent.SET_CONTENTS));

			}
		}
	}

	@SuppressWarnings("unchecked")
	private DSPanel<DSGeneMarker> nodesToMarkers(Set<Node> nodes) {
		DSPanel<DSGeneMarker> selectedMarkers = new CSPanel<DSGeneMarker>(
				"Selected Genes", "Cytoscape");
		for (Node node : nodes) {
			if (node instanceof CyNode) {
				String id = node.getIdentifier();
				// System.out.println("id = "+id);
				List<String> spIDs = Cytoscape.getNodeAttributes()
						.getListAttribute(id, "swissprotIDs");
				if (spIDs != null) {
					for (Iterator<String> isp = spIDs.iterator(); isp.hasNext();) {
						Collection<Integer> markerIds = swissprotIdToMarkerIdMap
								.get(isp.next());
						if (markerIds != null) {
							for (Integer markerId : markerIds) {
								selectedMarkers.add((DSGeneMarker) maSet
										.getMarkers().get(markerId));
							}
						}
					}
				}
				if (swissprotIdToMarkerIdMap.size() == 0)
					// probably user doesn't load annotation file, so
					// swissprotIdToMarkerIdMap contains nothing
					selectedMarkers.add((DSGeneMarker) maSet.getMarkers().get(
							id));
			}
		}
		return selectedMarkers;
	}

	@SuppressWarnings("unchecked")
	private ArrayList<String> getAccessionNumbers(NodeView node) {
		ArrayList<String> accessionNumbers = new ArrayList<String>();
		int geneId;

		try {
			geneId = (Integer) cutenetManager.getGenewaysNameIdMap().get(
					node.getNode().getIdentifier());
		} catch (Exception e) {
			return accessionNumbers;
		}
		CutenetProtein currCP = (CutenetProtein) cutenetManager
				.getGenewaysIdProteinMap().get(geneId);
		if (currCP != null) {
			DBId[] dbids = currCP.getDbIds();
			ArrayList<Name> ar = new ArrayList<Name>();
			for (int i = 0; i < dbids.length; i++) {
				try {
					Name name = cutenetHelper.getNameByDBId(dbids[i]);
					if (name != null)
						ar.add(name);
				} catch (Exception e) {
				}
			}
			for (Iterator<Name> itr = ar.iterator(); itr.hasNext();) {
				Name nextName = itr.next();
				Set<NamedEntity> neSet = nextName.getNamedEntities();
				for (Iterator<NamedEntity> itr2 = neSet.iterator(); itr2
						.hasNext();) {
					NamedEntity ne = itr2.next();
					if (ne.getDbId().equals("swissprot")) {
						if (!accessionNumbers.contains("S:"
								+ ne.getAccessionNumber()))
							accessionNumbers
									.add("S:" + ne.getAccessionNumber());
					} else if (ne.getDbId().equals("locaslink")) {
						if (!accessionNumbers.contains("L:"
								+ ne.getAccessionNumber()))
							accessionNumbers
									.add("L:" + ne.getAccessionNumber());
					} else if (ne.getDbId().equals("flybase")) {
						if (!accessionNumbers.contains("F:"
								+ ne.getAccessionNumber()))
							accessionNumbers
									.add("F:" + ne.getAccessionNumber());
					} else if (ne.getDbId().equals("Disease")) {
						if (!accessionNumbers.contains("D:"
								+ ne.getAccessionNumber()))
							accessionNumbers
									.add("D:" + ne.getAccessionNumber());
					} else if (ne.getDbId().equals("Species")) {
						if (!accessionNumbers.contains("Spc:"
								+ ne.getAccessionNumber()))
							accessionNumbers.add("Spc:"
									+ ne.getAccessionNumber());
					}
				}
				if (!accessionNumbers.contains("G:" + nextName.getNameId()))
					accessionNumbers.add("G:" + nextName.getNameId());
			}
		}

		Collections.sort(accessionNumbers, new LabelComparator());
		return accessionNumbers;
	}

	// following are a few inner classes

	private class LabelChangeAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 6906874685858815077L;
		NodeView nodeView;
		CutenetManager cutenetManager;
		String label;

		public LabelChangeAction(NodeView nodeView,
				CutenetManager cuteneteManager, String icon) {
			super(icon);
			this.nodeView = nodeView;
			this.cutenetManager = cuteneteManager;
			this.label = icon;
		}

		@SuppressWarnings("unchecked")
		public void actionPerformed(ActionEvent actionEvent) {
			String prevIdentifier = nodeView.getNode().getIdentifier();
			int geneId = (Integer) cutenetManager.getGenewaysNameIdMap().get(
					prevIdentifier);
			cutenetManager.getGenewaysNameIdMap().remove(prevIdentifier);
			cutenetManager.getGenewaysNameIdMap().put(label, geneId);
			nodeView.getNode().setIdentifier(label);
			nodeView.getLabel().setText(label);
			if (nodeView.isSelected())
				nodeView.unselect();
			nodeView.select();
		}
	}

	private class ExpandAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = -7431896496793248741L;
		NodeView nodeView;

		public ExpandAction(NodeView nodeView, String icon) {
			super(icon);
			this.nodeView = nodeView;
		}

		public void actionPerformed(ActionEvent actionEvent) {
			Runnable r = new Runnable() {
				public void run() {
					long id = Thread.currentThread().getId();
					try {
						runningThreads.add(id);
						jProgressBar.setVisible(true);
						jProgressBar.setStringPainted(true);
						jProgressBar.setString("Fetching interactions ... ");
						jProgressBar.setIndeterminate(true);
						log.debug("Clicked Node: "
								+ nodeView.getNode().getIdentifier()
								+ " !!!!!!!!!!!!!!!!!!!!!!");
						CutenetManager cutenetManager = CutenetManager
								.getCutenetManager();
						int geneId = -100;

						String nodeIdentifier = nodeView.getNode()
								.getIdentifier();
						String geneName = Cytoscape.getNodeAttributes()
								.getStringAttribute(nodeIdentifier, "geneName");
						geneId = (Integer) cutenetManager
								.getGenewaysNameIdMap().get(geneName);
						AdjacencyMatrix newAdjMat = cutenetManager
								.updateAdjacencyMatrix(geneId, adjMatrix, maSet);
						drawCompleteNetwork(newAdjMat, 0);
					} catch (Exception e) {
					} finally {
						runningThreads.remove(id);
						if (runningThreads.size() == 0) {
							jProgressBar.setString("");
							jProgressBar.setIndeterminate(false);
							jProgressBar.setVisible(false);
						}
					}
				}
			};
			Thread thread = new Thread(r);
			thread.start();
		}
	}

	private void drawCompleteNetwork(AdjacencyMatrix adjMatrix, double threshold) {
		cytoscapeWidget.drawCompleteNetwork(adjMatrix, threshold);
	}

	private class ShrinkAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 7250929346620012182L;
		NodeView nodeView;
		CutenetManager cutenetManager = CutenetManager.getCutenetManager();

		public ShrinkAction(NodeView nodeView, String icon) {
			super(icon);
			this.nodeView = nodeView;
		}

		public void actionPerformed(ActionEvent actionEvent) {
			Runnable r = new Runnable() {
				@SuppressWarnings("unchecked")
				public void run() {
					long id = Thread.currentThread().getId();
					try {
						runningThreads.add(id);
						jProgressBar.setVisible(true);
						jProgressBar.setStringPainted(true);
						jProgressBar.setString("Fetching interactions ... ");
						jProgressBar.setIndeterminate(true);
						log.debug("Clicked Node: "
								+ nodeView.getNode().getIdentifier()
								+ " !!!!!!!!!!!!!!!!!!!!!!");
						int geneId = -100;

						String nodeIdentifier = nodeView.getNode()
								.getIdentifier();
						String geneName = Cytoscape.getNodeAttributes()
								.getStringAttribute(nodeIdentifier, "geneName");
						geneId = (Integer) cutenetManager
								.getGenewaysNameIdMap().get(geneName);
						CyNode geneNode = Cytoscape.getCyNode(String
								.valueOf(geneId));

						HashMap<Integer, HashMap<Integer, Float>> geneRows = new HashMap<Integer, HashMap<Integer, Float>>();
						geneRows = adjMatrix.getGeneRows();
						HashMap<Integer, Float> row = geneRows.get(geneId);
						Set<Map.Entry<Integer, Float>> entrySet = row
								.entrySet();
						for (Iterator<Map.Entry<Integer, Float>> itr = entrySet
								.iterator(); itr.hasNext();) {
							Map.Entry<Integer, Float> nextEntry = (Map.Entry<Integer, Float>) itr
									.next();
							Integer nextKey = (Integer) nextEntry.getKey();
							CyNode n1 = Cytoscape.getCyNode(String
									.valueOf(nextKey));
							if (cytoNetwork.getDegree(n1) == 1) {
								cytoNetwork.removeNode(n1.getRootGraphIndex(),
										true);
							} else {
								List<CyEdge> edgeList = cytoNetwork.edgesList(
										geneNode, n1);
								if (edgeList != null && edgeList.size() > 0) {
									CyEdge nextEdge = (CyEdge) edgeList
											.iterator().next();
									if (nextEdge.isDirected()) {
										if (nextEdge.getSource()
												.getRootGraphIndex() == geneNode
												.getRootGraphIndex())
											cytoNetwork.removeEdge(nextEdge
													.getRootGraphIndex(), true);
									}
								}
							}

						}
						geneRows.remove(geneId);
						adjMatrix = new AdjacencyMatrix(geneRows);
					} catch (Exception e) {
						log.debug("Exception : " + e);
					} finally {
						runningThreads.remove(id);
						if (runningThreads.size() == 0) {
							jProgressBar.setString("");
							jProgressBar.setIndeterminate(false);
							jProgressBar.setVisible(false);
						}
					}
				}
			};
			Thread thread = new Thread(r);
			thread.start();
		}
	}

	private class RemoveAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = -444209893176310338L;
		NodeView nodeView;
		CutenetManager cutenetManager = CutenetManager.getCutenetManager();

		public RemoveAction(NodeView nodeView, String icon) {
			super(icon);
			this.nodeView = nodeView;
		}

		public void actionPerformed(ActionEvent actionEvent) {
			Runnable r = new Runnable() {
				public void run() {
					long id = Thread.currentThread().getId();
					try {
						runningThreads.add(id);
						jProgressBar.setVisible(true);
						jProgressBar.setStringPainted(true);
						jProgressBar.setString("Fetching interactions ... ");
						jProgressBar.setIndeterminate(true);
						log.debug("Clicked Node: "
								+ nodeView.getNode().getIdentifier());
						int geneId = -100;

						String nodeIdentifier = nodeView.getNode()
								.getIdentifier();
						String geneName = Cytoscape.getNodeAttributes()
								.getStringAttribute(nodeIdentifier, "geneName");
						geneId = (Integer) cutenetManager
								.getGenewaysNameIdMap().get(geneName);

						CyNode n1 = Cytoscape.getCyNode(String.valueOf(geneId));
						boolean success = cytoNetwork.removeNode(n1
								.getRootGraphIndex(), true);
						log.debug("Success: " + success);
						HashMap<Integer, HashMap<Integer, Float>> geneRows = new HashMap<Integer, HashMap<Integer, Float>>();
						geneRows = adjMatrix.getGeneRows();
						geneRows.remove(geneId);

						Set<Map.Entry<Integer, HashMap<Integer, Float>>> entries = geneRows
								.entrySet();
						for (Iterator<Map.Entry<Integer, HashMap<Integer, Float>>> itr = entries
								.iterator(); itr.hasNext();) {
							Map.Entry<Integer, HashMap<Integer, Float>> entry = itr
									.next();

							HashMap<Integer, Float> nextValue = (HashMap<Integer, Float>) entry
									.getValue();
							nextValue.remove(geneId);
						}

						adjMatrix = new AdjacencyMatrix(geneRows);
					} catch (Exception e) {
						log.debug("Exception : " + e);
					} finally {
						runningThreads.remove(id);
						if (runningThreads.size() == 0) {
							jProgressBar.setString("");
							jProgressBar.setIndeterminate(false);
							jProgressBar.setVisible(false);
						}
					}
				}
			};
			Thread thread = new Thread(r);
			thread.start();
		}
	}

	private class LabelComparator implements Comparator<String> {

		public int compare(String label1, String label2) {

			if (label1.startsWith("S:") && label2.startsWith("S:"))
				return label1.compareTo(label2);
			else if (label1.startsWith("S:"))
				return -1;
			else if (label2.startsWith("S:"))
				return 1;

			else if (label1.startsWith("L:") && label2.startsWith("L:"))
				return label1.compareTo(label2);
			else if (label1.startsWith("L:"))
				return -1;
			else if (label2.startsWith("L:"))
				return 1;

			else if (label1.startsWith("F:") && label2.startsWith("F:"))
				return label1.compareTo(label2);
			else if (label1.startsWith("F:"))
				return -1;
			else if (label2.startsWith("F:"))
				return 1;

			else if (label1.startsWith("D:") && label2.startsWith("D:"))
				return label1.compareTo(label2);
			else if (label1.startsWith("D:"))
				return -1;
			else if (label2.startsWith("D:"))
				return 1;

			else if (label1.startsWith("Spc:") && label2.startsWith("Spc:"))
				return label1.compareTo(label2);
			else if (label1.startsWith("Spc:"))
				return -1;
			else if (label2.startsWith("Spc:"))
				return 1;

			else if (label1.startsWith("G:") && label2.startsWith("G:"))
				return label1.compareTo(label2);
			else if (label1.startsWith("G:"))
				return -1;
			else if (label2.startsWith("G:"))
				return 1;

			return 0; // To change body of implemented methods use File |
			// Settings | File Templates.
		}
	}

}