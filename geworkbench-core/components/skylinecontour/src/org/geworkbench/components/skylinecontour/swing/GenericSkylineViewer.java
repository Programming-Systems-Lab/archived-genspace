package org.geworkbench.components.skylinecontour.swing;


import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.columbia.cs.skys.data.Point;
import org.geworkbench.components.skylinecontour.swing.GenericSkylineViewer;
import org.geworkbench.components.skylinecontour.swing.SkylineGraph;
import org.geworkbench.components.skylinecontour.swing.SkylinePanel;

import org.geworkbench.components.skylinecontour.proto.PointsGenerator;


/* TopLevelDemo.java requires no other files. */
public class GenericSkylineViewer extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5695621259515133500L;
	public static int DEFAULT_NUM_POINTS = 500;
	public static int DEFAULT_NUM_CONTOURS = 5;
	public static int DEFAULT_DISTR = 0;
	public static int WIDTH = 1000;
	public static int HEIGHT = 800;
	
	SkylinePanel spanel;
	SkylineGraph _sgraph;

    private GenericSkylineViewer() {
        setBackground(Color.black);
        _sgraph = new SkylineGraph(DEFAULT_NUM_CONTOURS, WIDTH, HEIGHT);
        _sgraph.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        add(_sgraph);
    }
		
    private GenericSkylineViewer(Point<?,?>[] points) {
        setBackground(Color.black);
        _sgraph = new SkylineGraph(points, DEFAULT_NUM_CONTOURS, WIDTH, HEIGHT);
        _sgraph.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        add(_sgraph);
    }
    
    public void extend(Point<?,?>[] extraPoints) {
    	_sgraph.extendSkyline(extraPoints);
    }
    
    public static GenericSkylineViewer createAndShowGUI(Point<?,?>[] points) {
        //Create and set up the window.
        JFrame frame = new JFrame("Skyline Viewer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(50,50);
        //Add content to the window.
		GenericSkylineViewer viewer = points==null?new GenericSkylineViewer():new GenericSkylineViewer(points);
        frame.add(viewer);
        //Display the window.
        frame.pack();
        frame.setVisible(true);
        return viewer;
    }
    
    @SuppressWarnings("static-access")
	public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        GenericSkylineViewer viewer = createAndShowGUI(null);
        
        try {
			Thread.currentThread().sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

        final Point<?,?> [] points = PointsGenerator.generate(DEFAULT_NUM_POINTS, 0, 0, 500, 400, DEFAULT_DISTR);
        viewer.extend(points);
        
        /*
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                viewer = createAndShowGUI(points);
            }
        });
        */
        try {
			Thread.currentThread().sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        Point<?,?> [] newPoints = PointsGenerator.generate(DEFAULT_NUM_POINTS, 501, 0, 1000, 400, DEFAULT_DISTR);
        viewer.extend(newPoints);
        
    }


}

	
	
