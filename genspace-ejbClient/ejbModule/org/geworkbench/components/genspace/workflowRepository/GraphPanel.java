package org.geworkbench.components.genspace.workflowRepository;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.geworkbench.components.genspace.bean.DomainUtil;
import org.geworkbench.components.genspace.entity.Workflow;
import org.geworkbench.components.genspace.rating.WorkflowVisualizationPopup;
import org.geworkbench.engine.config.VisualPlugin;
import org.jgraph.JGraph;
import org.jgraph.event.GraphModelEvent;
import org.jgraph.event.GraphModelListener;
import org.jgraph.event.GraphSelectionEvent;
import org.jgraph.event.GraphSelectionListener;
import org.jgraph.graph.DefaultCellViewFactory;
import org.jgraph.graph.DefaultEdge;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.DefaultGraphModel;
import org.jgraph.graph.DefaultPort;
import org.jgraph.graph.GraphConstants;
import org.jgraph.graph.GraphLayoutCache;
import org.jgraph.graph.GraphModel;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.graph.DirectedOrderedSparseMultigraph;
import edu.uci.ics.jung.graph.util.Pair;

public class GraphPanel extends JPanel implements VisualPlugin {

	private JPanel graphPanel = new JPanel();
	private JGraph graph;
	private DefaultGraphCell[] cells;
	private JScrollPane scroller = new JScrollPane();
	private String workflowString;
	private Workflow workflow;
	private WorkflowVisualizationPopup popup = new WorkflowVisualizationPopup();
	private WorkflowRepository workflowRepository;

	public GraphPanel(WorkflowRepository workflowRepository) {
		super(new BorderLayout());
		add(scroller);
		this.workflowRepository = workflowRepository;
	}

	public void setAndPaintWorkflow(Workflow w) {
		if (w != null) {
			workflow = w;
			workflowString = w.getIdentifier();
			draw();
		} else
			clearData();
	}

	public void clearData() {
		workflow = null;
		workflowString = "";
		cells = new DefaultGraphCell[0];
		GraphModel model = new DefaultGraphModel();
		GraphLayoutCache view = new GraphLayoutCache(model,
				new DefaultCellViewFactory());
		graph = new JGraph(model, view);
		graphPanel = new JPanel();
		graphPanel.setLayout(new BorderLayout());
		graphPanel.add(graph, BorderLayout.CENTER);
		remove(scroller);
		scroller = new JScrollPane(graphPanel);
		add(scroller, BorderLayout.CENTER);
		graphPanel.setVisible(true);
		scroller.setVisible(true);
		validate();
		repaint();
	}

	public void draw() {
		if (workflowString == null) {
			workflowString = "";
		}
		String[] nodeNames = workflowString.split(",");
		// create an array of Nodes
		Node[] nodes = new Node[nodeNames.length];

		// populate the array of Nodes
		for (int i = 0; i < nodes.length; i++) {
			nodes[i] = new Node(nodeNames[i]);
		}

		// mark the starting node
		nodes[0].isStart = true;

		// draw!
		draw(nodes);
	}

	public void draw(Node[] nodes) {
		// general setup stuff
		GraphModel model = new DefaultGraphModel();
		DirectedOrderedSparseMultigraph<Integer, Integer> backGraph = new DirectedOrderedSparseMultigraph<Integer, Integer>();
		final ArrayList<String> reverseMap = new ArrayList<String>();

		GraphLayoutCache view = new GraphLayoutCache(model,
				new DefaultCellViewFactory());
		graph = new JGraph(model, view);

		// figure out the size of the Cell array
		int size = nodes.length * 2 - 1;
		cells = new DefaultGraphCell[size];
		DefaultGraphCell[] extraCells = new DefaultGraphCell[size];

		// to keep track of the mapping between node numbers and the names
		HashMap<String, Integer> map = new HashMap<String, Integer>();

		// get the height and width of the window so we can show the components
		int height = graphPanel.getHeight() - 100; // subtract 100 to have a
													// little buffer
		int width = graphPanel.getWidth();
		// the average width per node
		double avgWidth = ((double) width) / nodes.length;

		// create the Nodes. Do a preliminary (random) layout.
		int count = 0;
		for (int i = 0; i < nodes.length; i++) {
			String node = nodes[i].value;

			// figure out the factor to multiply by the height
			double factor = 0.5;

			// figure out which color - default is gray
			Color myColor = Color.gray;
			// System.out.println("Node is " + node + "; target is " + target);
			// else if (nodes[i].isStart) color = Color.green;
			// else if (count == nodes.length - 1) color = Color.red;

			int myHeight = 40;
			// use the number of characters to figure out the width
			int minWidth = 80;
			int tempWidth = node.trim().length() * 7; // figure 7 pixels per
														// character
			int myWidth = (tempWidth < minWidth) ? minWidth : tempWidth;
			backGraph.addVertex(count);
			cells[count] = createNode(node, node, new Rectangle2D.Double(
					avgWidth * count + 50, height * factor, myWidth, myHeight),
					myColor);
			map.put(node, count);
			reverseMap.add(node);
			// increment the counter
			count++;
		}

		// this is for the case when we don't have a tree, just a sequence of
		// nodes
		int max = count - 1; // the maximum node number
		for (; count < size; count++) {

			Pair<Integer> temp = new Pair<Integer>((count - max - 1),
					(count - max));
			backGraph.addEdge(count, count - max - 1, count - max);
		}

		// Now actually do the layout, then recreate the display graph using the
		// calculated co-ords.
		CircleLayout<Integer, Integer> layout = new CircleLayout<Integer, Integer>(
				backGraph);
		layout.setSize(new Dimension(scroller.getWidth() - 100, scroller
				.getHeight() - 100));
		count = 0;
		int[] shifts = new int[nodes.length];
		for (int i = 0; i < nodes.length; i++) {
			String node = nodes[i].value;

			// figure out which color - default is gray
			Color myColor = Color.gray;
			int myHeight = 40;
			// use the number of characters to figure out the width
			int minWidth = 80;
			String toDisplay = node;
			if (node.trim().length() > 21) {
				// Add a line break
				toDisplay = "<html>" + node.trim().substring(0, 20) + "-<br>"
						+ node.trim().substring(20) + "</html>";
			}
			int tempWidth = 23 * 7; // figure 7 pixels per character
			int myWidth = (tempWidth < minWidth) ? minWidth : tempWidth;
			double myX = layout.getX(i);
			double myY = layout.getY(i);
			shifts[i] = 0;
			if (i > 0) {
				// Check to see if the guy to the left is too close

				if (Math.abs(layout.getY(i - 1) - layout.getY(i)) < 60) {
					// They are relatively "on top" vertically
					if (layout.getY(i - 1) > layout.getY(i))
						myY -= 40;
					else
						myY += 40;
					myX -= myWidth / 2 + shifts[i - 1];
					shifts[i] += (myWidth / 2 + shifts[i - 1]);
				}
			}
			cells[count] = createNode(node, toDisplay, new Rectangle2D.Double(
					myX, myY, myWidth, myHeight), myColor);

			// increment the counter
			count++;
		}
		// create the Edges for REAL

		// this is for the case when we don't have a tree, just a sequence of
		// nodes
		max = count - 1; // the maximum node number
		for (; count < size; count++) {
			cells[count] = createEdge(cells[count - max - 1],
					cells[count - max]);

		}

		graph.setDoubleBuffered(true);

		// to handle any changes to the graph
		GraphListener listener = new GraphListener(this);
		graph.getModel().addGraphModelListener(listener);
		graph.getSelectionModel().addGraphSelectionListener(listener);

		// load up the graph
		graph.getGraphLayoutCache().insert(cells);

		graphPanel = new JPanel();
		graphPanel.setLayout(new BorderLayout());
		graphPanel.add(graph, BorderLayout.CENTER);
		remove(scroller);
		scroller = new JScrollPane(graphPanel);

		add(scroller, BorderLayout.CENTER);
		graphPanel.setVisible(true);
		scroller.setVisible(true);

		validate();
		repaint();
	}

	@Override
	public Component getComponent() {
		// In this case, this object is also the GUI component.
		return this;
	}

	private DefaultEdge createEdge(DefaultGraphCell source,
			DefaultGraphCell target) {
		GraphEdge edge = new GraphEdge(); // new
											// DefaultEdge(source.getUserObject()
											// + ":" + target.getUserObject());
		edge.setSource(source.getChildAt(0));
		edge.setTarget(target.getChildAt(0));
		edge.sourceNode = ((GraphNode) source).toolName.toString();
		edge.destNode = ((GraphNode) target).toolName.toString();
		int arrow = GraphConstants.ARROW_CLASSIC;
		GraphConstants.setLineEnd(edge.getAttributes(), arrow);
		GraphConstants.setEndFill(edge.getAttributes(), true);
		return edge;
	}

	private DefaultGraphCell createNode(String label, String toDisplay,
			Rectangle2D bounds, Color color) {
		GraphNode cell = new GraphNode(label, toDisplay, color);

		GraphConstants.setBounds(cell.getAttributes(), bounds);
		GraphConstants.setGradientColor(cell.getAttributes(), color);
		GraphConstants.setOpaque(cell.getAttributes(), true);
		GraphConstants.setAutoSize(cell.getAttributes(), false);
		GraphConstants.setResize(cell.getAttributes(), false);
		GraphConstants.setEditable(cell.getAttributes(), false);
		GraphConstants.setSizeable(cell.getAttributes(), false);
		GraphConstants.setBorder(cell.getAttributes(),
				BorderFactory.createRaisedBevelBorder());
		DefaultPort port0 = new DefaultPort();
		cell.add(port0);
		return cell;

	}

	public class Edge {
		String src, dest;

		public Edge(String a, String b) {
			src = a;
			dest = b;
		}

		@Override
		public boolean equals(Object o) {
			if (o instanceof Edge) {
				Edge other = (Edge) o;
				return other.src.equals(src) && other.dest.equals(dest);
			} else
				return false;
		}
	}

	public class Node {
		// the value to be displayed in the node
		String value;
		// the total sum of the position numbers for this node
		private int totalPos = 0;
		// the number of workflows in which this node appears
		private int workflows = 0;
		// the average position
		double avgPos;
		// whether it's a starting node
		boolean isStart = false;

		public Node(String v) {
			value = v;
		}

		public void addPosition(int p) {
			totalPos += p;
			workflows++;
			avgPos = ((double) totalPos) / workflows;
		}

	}

	public class GraphListener implements GraphModelListener,
			GraphSelectionListener {
		JPanel parent;

		public GraphListener(JPanel p) {
			parent = p;
		}

		// stores the edges that have been highlighted in the graph
		ArrayList<GraphEdge> highlightedEdges = new ArrayList<GraphEdge>();
		// stores the nodes that have been highlighted in the graph
		ArrayList<GraphNode> highlightedNodes = new ArrayList<GraphNode>();

		/**
		 * This method will be called when the whole graph is changed, like if a
		 * node gets moved
		 */
		@Override
		public void graphChanged(GraphModelEvent e) {
			// System.out.println("GRAPH CHANGED! " + e.toString());
		}

		// stores the time when "valueChanged" was last called
		private long lastChange = 0;

		/**
		 * This method is called when a node or edge is simply selected
		 */
		@Override
		public void valueChanged(GraphSelectionEvent e) {
			// make sure we don't register a double-click
			long now = System.currentTimeMillis();
			if (now - lastChange < 20)
				return;
			else
				lastChange = now;

			// get the thing that was changed
			Object o = e.getCell();

			// if they click on a node, highlight all workflows going through
			// that node
			if (o instanceof GraphNode) {
				GraphNode selectedNode = (GraphNode) o;

				// if it's highlighted already, unhighlight it
				if (highlightedNodes.contains(selectedNode))
					highlightedNodes.remove(selectedNode);
				else
					highlightedNodes.add(selectedNode);
				/*
				 * if (selectedNode.highlighted) { selectedNode.highlighted =
				 * false; highlightedNodes.remove(selectedNode); } else {
				 * selectedNode.highlighted = true;
				 * highlightedNodes.add(selectedNode); }
				 */

				// remove all the highlighted edges
				graph.getGraphLayoutCache().remove(
						highlightedEdges.toArray(new GraphEdge[highlightedEdges
								.size()]));

				ArrayList<Edge> workflowEdges = new ArrayList<Edge>();

				// loop through all the workflows and all the highlighted nodes
				// to get the set of edges
				int count = 0;

				// see if it contains the nodes that have been highlighted
				if (contains(workflowString, highlightedNodes)) {
					count++;
					// if so, get all the edges for that workflow
					String[] nodes = workflowString.split(",");
					for (int i = 0; i < nodes.length; i++) {
						// create an edge between this one and the next, only if
						// the edge doesn't already exist
						if (i < nodes.length - 1) {
							Edge edge = new Edge(nodes[i], nodes[i + 1]);
							if (workflowEdges.contains(edge) == false) {
								workflowEdges.add(edge);
							}
						}
					}
				}

				// put together the String with the list of all the selected
				// nodes
				String highlighted = "";
				if (highlightedNodes.size() == 1)
					highlighted = highlightedNodes.get(0).getToolName();
				else if (highlightedNodes.size() == 2)
					highlighted = highlightedNodes.get(0).getToolName()
							+ " and " + highlightedNodes.get(1).getToolName();
				else {
					for (int i = 0; i < highlightedNodes.size(); i++) {
						if (i > 0)
							highlighted += ", ";
						if (i == highlightedNodes.size() - 1)
							highlighted += "and ";
						highlighted += highlightedNodes.get(i).getToolName();
					}
				}

				// if there are no workflows for the selected nodes, then just
				// "reset" everything
				// this should be done AFTER the part that prints out the status
				// message, though
				if (workflowEdges.isEmpty()) {
					highlightedNodes = new ArrayList<GraphNode>();
				}

				// temp variable that is needed to force a "refresh" of the
				// graph
				GraphEdge temp = null;

				// get all the objects in the graph - nodes and edges
				Object[] views = graph.getGraphLayoutCache().getCells(false,
						true, false, true);
				// now loop through them to update
				for (Object cell : views) {
					// if it's an edge, we figure out whether we want to
					// highlight it
					if (cell instanceof GraphEdge) {
						GraphEdge edge = (GraphEdge) cell;
						// System.out.println("edge: " + edge.sourceNode + " " +
						// edge.destNode);

						// see if it's in the list of edges for these workflows
						if (workflowEdges.contains(new Edge(edge.sourceNode,
								edge.destNode))) {
							// make a copy of the edge
							GraphEdge newEdge = (GraphEdge) (edge.clone());
							// make the line bold
							GraphConstants.setLineWidth(
									newEdge.getAttributes(), 5);
							// add it to the graph... note that this just lays
							// it on top of the existing one
							graph.getGraphLayoutCache().insertEdge(newEdge,
									edge.getSource(), edge.getTarget());
							// add it to the list of highlighted edges, so it
							// can be removed later
							highlightedEdges.add(newEdge);
						}
						temp = edge;
					}
					// if it's a node, we might need to change its color
					else if (cell instanceof GraphNode) {
						GraphNode theCell = (GraphNode) cell;

						/*
						 * // if there are no workflows, then un-highlight any
						 * cell if (workflowEdges.isEmpty()) {
						 * theCell.highlighted = false; }
						 */

						// now determine how to color the cells
						if (highlightedNodes.contains(theCell)) {
							GraphConstants.setGradientColor(
									theCell.getAttributes(), Color.blue);
						}
						// otherwise, set it back to its original color
						else {
							GraphConstants.setGradientColor(
									theCell.getAttributes(), theCell.color);
							// System.out.println("reset " +
							// theCell.getUserObject());
						}

					}
				}

				/*
				 * This is a bit of a hack, but I "save" one of the edges to
				 * display until after everything is done, because this seems to
				 * cause a refresh whereas simply changing the cell colors does
				 * NOT automatically refresh the entire graph
				 */
				graph.getGraphLayoutCache().insertEdge(temp, temp.getSource(),
						temp.getTarget());

				// Display the right click menu for rating

				Rectangle2D rect = GraphConstants.getBounds(selectedNode
						.getAttributes());

				popup.showToolOptions();
				popup.showToolRating();

				// Added by Flavio
				// popup.hideWorkflowOptions();
				// popup.hideWorkflowRating();
				ArrayList<String> toolsInWorkflow = new ArrayList<String>();
				if (workflowString != null) {
					popup.showWorkflowOptions();
					popup.showWorkflowRating();
					// TODO: only the first workflow is considered
					toolsInWorkflow = DomainUtil
							.fromWorkflowStringToList(workflowString);
				}
				popup.initialize(selectedNode.getToolName(), toolsInWorkflow);
				popup.show(GraphPanel.this, (int) rect.getCenterX(),
						(int) rect.getCenterY());
			}
			/*
			 * // if they click on an edge... though a DefaultEdge *is* a
			 * DefaultGraphCell, so this would need to move up!!!!! else if (o
			 * instanceof DefaultEdge) System.out.println("clicked on " +
			 * ((DefaultEdge)o).getUserObject());
			 */
		}

		/**
		 * A helper method to make sure that the String argument contains all of
		 * the Strings stored in the ArrayList
		 */
		private boolean contains(String workflow, ArrayList<GraphNode> nodes) {
			if (nodes.isEmpty())
				return false;
			for (GraphNode node : nodes)
				if (workflow.contains(node.getToolName()) == false)
					return false;

			return true;
		}
	}

	public class GraphEdge extends DefaultEdge {
		String sourceNode, destNode;
	}

	public class GraphNode extends DefaultGraphCell {
		Color color;
		String toolName;
		String toDisplay;

		public String getToolName() {
			return toolName;
		}

		public GraphNode(String label, String toDisplay, Color c) {
			super(toDisplay);
			toolName = label;
			this.toDisplay = toDisplay;
			color = c;
		}

	}

}
