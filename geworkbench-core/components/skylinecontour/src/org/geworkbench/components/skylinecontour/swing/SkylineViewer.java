package org.geworkbench.components.skylinecontour.swing;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import edu.columbia.cs.skys.Config;
import edu.columbia.cs.skys.cache.CacheException;
import edu.columbia.cs.skys.cache.ICache;
import edu.columbia.cs.skys.data.Point;
import org.geworkbench.components.skylinecontour.query.QueryManager;
import org.geworkbench.components.skylinecontour.query.QuerySession;
import edu.columbia.cs.skys.skyline.DCBatchedSkyline;
import edu.columbia.cs.skys.skyline.DCBatchedUBSkyline;
import edu.columbia.cs.skys.skyline.IDominanceComparator;
import edu.columbia.cs.skys.skyline.ISkylineQuery;
import edu.columbia.cs.skys.skyline.LargerBetterDominance;
import edu.columbia.cs.skys.skyline.SmallerBetterDominance;
import org.geworkbench.components.skylinecontour.swing.SkylineGraph;
import org.geworkbench.components.skylinecontour.swing.SkylinePanel;
import org.geworkbench.components.skylinecontour.swing.SkylineViewer;
import org.geworkbench.components.skylinecontour.swing.SkylineViewer.QueryExecution;
import org.geworkbench.components.skylinecontour.swing.SkylineViewer.SelectItemListener;
import edu.columbia.cs.skys.util.DBUtils;


/* TopLevelDemo.java requires no other files. */
public class SkylineViewer extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static int DEFAULT_NUM_POINTS = 500;
	public static int DEFAULT_NUM_CONTOURS = 5;
	public static int DEFAULT_DISTR = 0;
	// this really needs to resize according to the screen size!!
	public static int WIDTH = 800; //1000;
	public static int HEIGHT = 610; //800;
	
	public static int _numContours = DEFAULT_NUM_CONTOURS;
	public static String scoreType;
	
	JPanel controlsPanel;
	SkylinePanel _spanel;
	SkylineGraph _sgraph;
	Logger _logger = Logger.getLogger(SkylineViewer.class.toString());
	ISkylineQuery _skylineQuery;
	
	ICache.Score_Type _scoreType = ICache.Score_Type.SPECIFICITY;
	
    public SkylineViewer() {
    	if (!Config.USE_UB) {
    		_skylineQuery = new DCBatchedSkyline(0, true);	// sorted on x-axis, in batched mode
        	_skylineQuery.setDominanceComparators(new IDominanceComparator[] {new SmallerBetterDominance<Double>(), new LargerBetterDominance<Double>()});
    	}
    	/*
        final JTextField queryField = new JTextField(80);
        queryField.setText("");
        JLabel textFieldLabel = new JLabel("Query: ");
        textFieldLabel.setLabelFor(queryField);
		*/
        
    	String[] fixedCacheQueries = {
    			"Autoimmune Diseases[Mesh Terms] AND Pregnancy[MeSH Terms]",
    			"Diabetes Mellitus[MeSH Terms] AND Myocardial Infarction[MeSH Terms]",
    			"Lupus Erythematosus, Systemic[Mesh Terms] OR Antiphospholipid Antibodies[MeSH Terms]",
    			"Connective Tissue Diseases[Mesh Terms] AND Autoimmune Diseases[MeSH Terms]",
    			"receptors, g-protein-coupled[MeSH Terms] AND mice[MeSH Terms]",
    			//"Alzheimer Disease", 
    			//"antiphospholipid antibodies", 
    			//"Asthma",
    			"CD4-Positive T-Lymphocytes", 
    			//"G-Protein-Coupled receptors",
    			"Gram-Negative Bacterial Infections", 
    			//"hepatitis human",
    			//"low-density lipoprotein",
    			"Phosphotransferases (Alcohol Group Acceptor)", "Systemic Lupus" };
    	
    	//String[] scoreTypes = {"Overlap", "Coverage", "Specificity", "Jaccard", "Conditional", "Balanced"};
    	String[] scoreTypes = {"Coverage", "Specificity", "Jaccard", "Conditional", "Balanced"};
    	
    	final JRadioButton rb1 = new JRadioButton("Type in your MeSH query",true);
		final JRadioButton rb2 = new JRadioButton("We also suggest");
		ButtonGroup bg = new ButtonGroup();
		bg.add(rb1);
		bg.add(rb2);
		
    	final JTextField queryTextField = new JTextField(20);
    	
        final JComboBox queryField = new JComboBox(fixedCacheQueries);
        queryField.setSelectedIndex(DEFAULT_DISTR);
        queryField.setEnabled(false);
        rb1.addItemListener(new SelectItemListener(queryTextField,queryField));
		rb2.addItemListener(new SelectItemListener(queryTextField,queryField));
                
		JPanel queryPanel1 = new JPanel();   
		queryPanel1.setLayout(new BoxLayout(queryPanel1, BoxLayout.Y_AXIS));      
        queryPanel1.add(rb1);
        queryPanel1.add(rb2);
        
        JPanel queryPanel2 = new JPanel();  
        queryPanel2.setLayout(new BoxLayout(queryPanel2, BoxLayout.Y_AXIS));  
        queryPanel2.add(Box.createRigidArea(new Dimension(10, 10)));
        queryPanel2.add(queryTextField);
        queryPanel2.add(Box.createRigidArea(new Dimension(10, 10)));
        queryPanel2.add(queryField);
		
        final JComboBox scoreTypeField = new JComboBox(scoreTypes);
        scoreTypeField.setSelectedIndex(DEFAULT_DISTR);
        JLabel scoreTypeLabel = new JLabel("Score Type: ");
        scoreTypeLabel.setLabelFor(scoreTypeField);
        
        final JTextField numContoursField = new JTextField(2);
        numContoursField.setText("" + DEFAULT_NUM_CONTOURS);
        JLabel contoursLabel = new JLabel("# Contours: ");
        contoursLabel.setLabelFor(numContoursField);
        
        JPanel contoursPanel = new JPanel();                
        contoursPanel.setLayout(new BoxLayout(contoursPanel, BoxLayout.LINE_AXIS));        
        contoursPanel.add(contoursLabel);
        contoursPanel.add(numContoursField);
                        
        JButton displayButton = new JButton("Go");
        displayButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				_numContours = DEFAULT_NUM_CONTOURS;
				try {
					_numContours = Integer.parseInt(numContoursField.getText());
				} catch (NumberFormatException e) {
					numContoursField.setText("" + DEFAULT_NUM_CONTOURS);
				}
				
				String query="";
				if (rb1.isSelected()){
					query = queryTextField.getText();
				} else {
					query = (String)queryField.getSelectedItem();
				}
				
				String score = (String)scoreTypeField.getSelectedItem();
				if (score.equalsIgnoreCase("Overlap")) {
					score = "Score";
				}
				
				scoreType = score;
				_scoreType = ICache.Score_Type.valueOf(score.toUpperCase());	
				
				// log some query details in the genSpace wiki
				//DBUtils.logQueryInWiki(query, _scoreType.toString(), _numContours);
				
				QueryExecution queryExec = new QueryExecution(query, _numContours, controlsPanel);
				Thread queryThread = new Thread(queryExec);
				queryThread.start();
			}
        });

        controlsPanel = new JPanel();        
        controlsPanel.setLayout(new FlowLayout());

        /*controlsPanel.setBorder(
                BorderFactory.createCompoundBorder(
                                BorderFactory.createTitledBorder("Query"),
                                BorderFactory.createEmptyBorder(5,5,5,5)));*/
        
        controlsPanel.add(queryPanel1);
        controlsPanel.add(queryPanel2);
        controlsPanel.add(contoursPanel);
        controlsPanel.add(scoreTypeLabel);
        controlsPanel.add(scoreTypeField);
        controlsPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        controlsPanel.add(displayButton);
        
        setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
        
        add(controlsPanel);
    	
        setBackground(Color.black);
        _sgraph = new SkylineGraph(_numContours, WIDTH, HEIGHT);
        _sgraph.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        add(_sgraph);
    }
		
	private Point<?,?>[] filterPoints(Point<?,?> [] points) {
		if (points==null || points.length==0) {
			return points;
		}
		List<Point<?,?>> filteredPoints = new ArrayList<Point<?,?>>(points.length);
		for (Point<?,?> point: points) {
			if (point.getId() == 0) {
				continue;
			}
			if (point.xToDouble()==0 && point.yToDouble()==0) {
				continue;
			}
			filteredPoints.add(point);
		}
		if (filteredPoints.size() < points.length) {
			_logger.info("removed " + (points.length - filteredPoints.size()) + " points");
		}
		return filteredPoints.toArray(new Point<?,?>[0]);
	}
        
    public static SkylineViewer createAndShowGUI(Point<?,?>[] points) {
        //Create and set up the window.
        JFrame frame = new JFrame("Skyline Query Viewer");
       // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(50,50);
        
		//Add content to the window.
		SkylineViewer viewer = new SkylineViewer();
        frame.add(viewer);
        
        //Display the window.
        frame.pack();
        frame.setVisible(true);
        return viewer;
    }
    
    public void execute() {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).setLevel(Level.ALL);
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	//SkylineViewer viewer = createAndShowGUI(null);
            	createAndShowGUI(null);
            }
        });
        
    }
    
    class SelectItemListener implements ItemListener{
    	
    	private JTextField queryTextField;
    	private JComboBox queryField;
    	
    	public SelectItemListener(JTextField queryTextField, JComboBox queryField){
    		this.queryTextField=queryTextField;
    		this.queryField=queryField;
    	}
    	
		public void itemStateChanged(ItemEvent e){
			AbstractButton sel = (AbstractButton)e.getItemSelectable();
			if(e.getStateChange() == ItemEvent.SELECTED){
				if (sel.getText().equals("Type in your MeSH query")){
					queryField.setEnabled(false);
					queryTextField.setEnabled(true);
				}else if (sel.getText().equals("We also suggest")){
					queryTextField.setEnabled(false);
					queryField.setEnabled(true);
				}
			}
		}
	}
    
    class QueryExecution implements Runnable {

    	String _query;
    	int _numContours;
    	JPanel controlsPanel;
    	
    	QueryExecution(String queryText, int numContours, JPanel controlsPanel) {
    		super();
    		_query = queryText;
    		_numContours = numContours;
    		this.controlsPanel=controlsPanel;
    	}
    	
    	@Override
    	public void run() {
    		// Config.MAX_BATCHES = 10;
    		Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);
    		setCursor(hourglassCursor);
    		_sgraph.reset();  
    		_sgraph.repaint();
    		boolean noResults = false;
    		try {
    			QueryManager qm = new QueryManager();
    			int reqId = -1;	
    			// start a query session
    			QuerySession qs = qm.spawnQuerySession(_query, reqId);
    			if(qs.get_meshQuery().getNumResults()==0){
    				noResults = true;
    				JOptionPane.showMessageDialog(null, "Sorry, your query did not match any PubMed articles");
    			}
    			if(!noResults && qs.getQueryMeta().getScore()==0){
    				noResults = true;
    				JOptionPane.showMessageDialog(null, "Sorry, your query did not match any MeSH terms");
    			}
    			
    			if(!noResults) {
	    			if (Config.USE_UB) {
	    			    boolean exact = false;
	    				_skylineQuery = new DCBatchedUBSkyline(qm.getCache(), qs.getReqId(), _scoreType, exact);
	    			    _skylineQuery.setDominanceComparators(new IDominanceComparator[] 
	      			                      	                   {new SmallerBetterDominance<Double>(), new LargerBetterDominance<Double>()});	
	    			}
	    			qs.setMaxBatches(Config.MAX_BATCHES);
	    			qs.runQuery();
	    			
	    			//Set<Point<Integer,Integer>> batchPoints = null;
	    			Point<?,?>[] batchPoints = null;
	    			_sgraph.setNumContours(this._numContours);
	    			do {
	    				batchPoints = qs.readNextBatch();
	    				if (!Config.USE_UB) {
	    					batchPoints = filterPoints(batchPoints);
	    				}
	    				if (batchPoints == null) {
	    					_logger.warning("QuerySession returned null for points");
	    				} else {
	    					_logger.info("QuerySession retured " + batchPoints.length + " point(s)");
	    				}
	    				if ((batchPoints!=null) && (batchPoints.length > 0)) {
	    					_skylineQuery.setIsLastBatch(qs.isLastBatch());
	        				batchPoints = _skylineQuery.getSkyline(batchPoints, _numContours);
	    					_sgraph.extendSkyline(batchPoints);
	    					_sgraph.repaint();
	    				} else {
	    					// sleep
	    					try {
	    						_logger.info("sleeping 2 secs");
	    						Thread.sleep(2000);
	    						_logger.info("finished");
	    					} catch (InterruptedException e) {
	    						// interrupted
	    					}
	    				}
	    			} while (batchPoints!=null);
    			/*
    			// final call
				batchPoints = _skylineQuery.getSkyline(new Point<?,?> [] {});
				_sgraph.extendSkyline(batchPoints);
				_sgraph.repaint();
				*/
    			}
    		} catch (CacheException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
			_logger.info("Finished query");
    		setCursor(normalCursor);
    	}
    };
}

	
	
