package org.geworkbench.components.genspace.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.geworkbench.components.genspace.entity.Tool;
import org.geworkbench.components.genspace.entity.Workflow;
import org.geworkbench.components.genspace.entity.WorkflowTool;
import org.geworkbench.components.genspace.rating.WorkflowVisualizationPopup;
import org.geworkbench.components.genspace.ui.graph.myGraph;
import org.geworkbench.components.genspace.ui.graph.myStackLayout;
import org.geworkbench.engine.config.VisualPlugin;

import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxICell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxPoint;

/**
 * This is used to display received workflows.
 * 
 * @author jon
 * 
 */
public class WorkflowVisualizationPanel extends JPanel implements VisualPlugin {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3300246926475166675L;
	private ArrayList<Workflow> workflows = new ArrayList<Workflow>();
	private WorkflowVisualizationPopup popup = new WorkflowVisualizationPopup();
	
	private myGraph graph;
	private mxGraphComponent graphComponent;
	private JScrollPane scroller = new JScrollPane();;

	public WorkflowVisualizationPanel()
	{
		add(scroller, BorderLayout.CENTER);
		this.addComponentListener(new ComponentListener() {
			
			@Override
			public void componentShown(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void componentResized(ComponentEvent e) {
				refreshLayout();
			}
			
			@Override
			public void componentMoved(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void componentHidden(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	public void clearData() {
		this.remove(scroller);
	}
	public void render(Workflow w)
	{
		render(w, null);
	}
	public void render(Workflow w, Tool selected) {
		workflows.add(w);
		initGraph();
		renderSingleWorkflow(w, selected, null);
		layoutAndShowGraph();	
	}
	private void refreshLayout()
	{
		if(graph != null)
		{
			Object parent = graph.getDefaultParent();
			myStackLayout layout = new myStackLayout(graph,false,20,10,10);
			layout.setResizeParent(true);
			layout.execute(pool);
	
			layout = new myStackLayout(graph,swimlanes.size() == 0,40,10,0);
			if(swimlanes.size() == 0)
			{
				layout.setWrap((int) this.getSize().getWidth());
			}
			layout.setResizeParent(true);
			layout.execute(parent);
			
			if(swimlanesBack.size() > 0)
			for(mxICell i : swimlanes.values())
			{
				Workflow parentW = swimlanesBack.get(i).getParent();
				int drawOffset = 0;
				if(parentW != null)
				{
					if(wkflTails.get(parentW) != null)
					{
					drawOffset = (int) (wkflTails.get(parentW).getGeometry().getX()+wkflTails.get(parentW).getGeometry().getWidth());
					Object[] es = graph.getEdgesBetween(wkflTails.get(parentW),i.getChildAt(0));
					if(es.length == 1)
					{
					mxICell edge = (mxICell) es[0];
					ArrayList<mxPoint> pts = new ArrayList<mxPoint>();
					pts.add(new mxPoint(wkflTails.get(parentW).getGeometry().getCenterX() +((mxICell) parent).getGeometry().getX(), (i.getChildAt(0)).getGeometry().getCenterY() + i.getGeometry().getY()));
					edge.getGeometry().setPoints(pts);
					}
					}
				}
				layout = new myStackLayout(graph,true,10,drawOffset,0);
				layout.setWrap((int) this.getSize().getWidth() + drawOffset);
				layout.setResizeParent(true);
				layout.execute(i);
			}
			
			this.removeAll();
			add(graphComponent, BorderLayout.CENTER);
			graphComponent.setVisible(true);
			graphComponent.setPreferredSize(this.getSize());
			revalidate();
			repaint();
		}
	}
	private void layoutAndShowGraph() {
		
			graph.getModel().endUpdate();
		
			graphComponent = new mxGraphComponent(graph);
			graphComponent.getGraphControl().addMouseListener(new MouseAdapter()
			{
			
				public void mouseReleased(MouseEvent e)
				{
					Object cell = graphComponent.getCellAt(e.getX(), e.getY());
					
					if (cell != null)
					{
						if(cell instanceof mxCell)
						{
							mxCell mx = (mxCell) cell;
							cell = mx.getValue();
						}
						if(cell.getClass().equals(Tool.class))
						{
							Tool selected = (Tool) cell;
							System.out.println("Selected " + cell);
							Workflow workflow = null;
							if (workflows != null && workflows.size() > 0) {
								workflow = workflows.get(workflows.size() -1 );
								popup.showWorkflowOptions();
								popup.showWorkflowRating();
								popup.showToolOptions();
								popup.showToolRating();
								popup.initialize(selected, workflow);
								popup.show(WorkflowVisualizationPanel.this, (int) e.getX(),
										(int) e.getY());

							}
						}
						else
						{
							System.out.println("Not a tool selected: " + cell.getClass());
						}
					}
				}
			});
			
			refreshLayout();
			refreshLayout();
	}
	@Override
	public Component getComponent() {
		return this;
	}
	private Object pool;
	public void render(List<Workflow> ret, Tool selected) {

		//Sort by # of children
		Collections.sort(ret, new Comparator<Workflow>() {

			@Override
			public int compare(Workflow o1, Workflow o2) {
				if(o1.getChildren().size() < o2.getChildren().size())
					return -1;
				else if(o1.getChildren().size() > o2.getChildren().size())
					return 1;
				else
					return 0;
			}
		});
		initGraph();
		pool = graph.insertVertex(graph.getDefaultParent(), null, "", 0, 0, 10, 10,"POOL");
		renderAsSubs(ret,selected,false,null);
		layoutAndShowGraph();
		
	}
	private HashMap<Workflow, mxICell> wkflTails;
	private HashMap<Workflow, mxICell> swimlanes;
	private HashMap<mxICell,Workflow> swimlanesBack;
	private void renderAsSubs(List<Workflow> ret, Tool selected,boolean collapseLevel, Workflow parent) {
		if(ret.size() == 0)
			return;
		for(Workflow w: ret)
		{
			if(parent == null)
			{
				if(!ret.contains(w.getParent()))
				{
					if(w.getTools().size() > 0)
					{
						Object lane = graph.insertVertex(pool, null, "", 0, 0, this.getWidth(), 10,"SWIMLANE");
						swimlanes.put(w, (mxICell) lane);
						renderSingleWorkflow(w,selected,lane);
						
						renderAsSubs(ret, selected, true, w);
					}
				}
			}
			else if(parent.equals(w.getParent()))
			{
				if(w.getTools().size() > 0)
				{
					Object lane = graph.insertVertex(pool, null, "", 0, 0, this.getWidth(), 10,"SWIMLANE");
					swimlanes.put(w, (mxICell) lane);
					renderSingleWorkflow(w,selected,lane,w.getParent().getTools().size(),wkflTails.get(w.getParent()));
					renderAsSubs(ret, selected, true, w);
				}
			}
		}
	}

	private void renderSingleWorkflow(Workflow w, Tool selected, Object parent,
			int toolOffset, mxICell drawFrom) {
		Font f = new Font("Helvetica",Font.PLAIN,11);

		int drawOffset;
		if(drawFrom == null)
			drawOffset = 10;
		else
			drawOffset = (int) (drawFrom.getGeometry().getX() + drawFrom.getGeometry().getWidth());
		
		if(parent == null)
			parent = graph.getDefaultParent();

		Object lastCell = null;

		int i = 0;
		for(WorkflowTool to : w.getTools())
		{
			if(i < toolOffset)
			{
				i++;
				continue;
			}
			String styl;
			if(to.getTool().equals(selected))
				styl = "WORKFLOW;fillColor=#e8f2dd";
			else
				styl = "WORKFLOW";
			Rectangle2D r = f.getStringBounds(to.getTool().getName(), ((Graphics2D) this.getGraphics()).getFontRenderContext());
			Object v1 = graph.insertVertex(parent, null, to.getTool(), 10, 10, r.getWidth()+10, r.getHeight()+10,styl);
			if(lastCell != null)
				graph.insertEdge(parent, null, "", lastCell, v1,"editable=0");
			else if(drawFrom != null)
			{
				myStackLayout layout = new myStackLayout(graph,false,20,10,10);
				layout.setResizeParent(true);
				layout.execute(pool);
				
				layout = new myStackLayout(graph,true,20,0,0);
				layout.setWrap((int) this.getSize().getWidth());
				layout.setResizeParent(true);
				layout.execute(parent);

				
				mxICell e = (mxICell) graph.insertEdge(graph.getDefaultParent(), null, "", drawFrom, v1,"CROSSOVER;editable=0");
				ArrayList<mxPoint> pts = new ArrayList<mxPoint>();
				pts.add(new mxPoint(drawFrom.getGeometry().getCenterX() +((mxICell) parent).getGeometry().getX(), ((mxICell) v1).getGeometry().getCenterY() + ((mxICell) parent).getGeometry().getY()));
				e.getGeometry().setPoints(pts);
			}
			lastCell = v1;
		}

		swimlanesBack.put((mxICell) parent,w);
		myStackLayout layout = new myStackLayout(graph,true,20,drawOffset,0);
		layout.setWrap((int) this.getSize().getWidth() + drawOffset);
		layout.setResizeParent(true);
		layout.execute(parent);
		wkflTails.put(w, ((mxICell) lastCell));


		
	}
	private void renderSingleWorkflow(Workflow w, Tool selected,Object parent) {
		renderSingleWorkflow(w, selected, parent,0,null);	
	}
	private void initGraph() {
		graph = new myGraph();



		graph.getModel().beginUpdate();
		

		Hashtable<String, Object> style = new Hashtable<String, Object>();
		style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_RECTANGLE);
		style.put(mxConstants.STYLE_OPACITY, 50);
		style.put(mxConstants.STYLE_FONTCOLOR, "#774400");
		style.put(mxConstants.STYLE_FONTSIZE, 11);
		style.put(mxConstants.STYLE_FONTFAMILY, "Helvetica");		
		style.put(mxConstants.STYLE_EDITABLE, false);
		graph.getStylesheet().putCellStyle("WORKFLOW", style);

		Hashtable<String, Object> style2 = new Hashtable<String, Object>();
		style2.put(mxConstants.STYLE_STARTARROW, mxConstants.ARROW_OVAL);
		style2.put(mxConstants.STYLE_ORTHOGONAL, true);
		style2.put(mxConstants.STYLE_BENDABLE, true);
		style2.put(mxConstants.STYLE_ELBOW, mxConstants.ELBOW_HORIZONTAL);
		graph.getStylesheet().putCellStyle("CROSSOVER", style2);
		
		
		Hashtable<String, Object> style3 = new Hashtable<String, Object>();
		style3.put(mxConstants.STYLE_FILLCOLOR, "#FAFAFA");
		style3.put(mxConstants.STYLE_STROKECOLOR, "#CACACA");
		style3.put(mxConstants.STYLE_STROKEWIDTH, .25);
		style3.put(mxConstants.STYLE_VERTICAL_ALIGN, mxConstants.ALIGN_CENTER);
		graph.getStylesheet().putCellStyle("SWIMLANE", style3);
		
		Hashtable<String, Object> style4 = new Hashtable<String, Object>();
		style4.put(mxConstants.STYLE_OPACITY, 0);
		graph.getStylesheet().putCellStyle("POOL", style4);
		
		
		graph.setConnectableEdges(false);
		graph.setAllowDanglingEdges(false);
		graph.setCellsEditable(false);
		graph.setCellsSelectable(false);
		graph.setCellsDisconnectable(false);
		graph.setCellsResizable(false);
		graph.setConnectableEdges(false);
		graph.setEnabled(false);
		
		wkflTails = new HashMap<Workflow, mxICell>();
		swimlanes = new HashMap<Workflow, mxICell>();
		swimlanesBack = new HashMap<mxICell, Workflow>();
	}
	

}