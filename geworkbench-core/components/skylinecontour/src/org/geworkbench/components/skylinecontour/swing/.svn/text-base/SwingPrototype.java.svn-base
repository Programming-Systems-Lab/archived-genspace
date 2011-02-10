package org.geworkbench.components.skylinecontour.swing;


import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.*;

import javax.swing.*;

import edu.columbia.cs.skys.data.Point;
import org.geworkbench.components.skylinecontour.proto.PointsGenerator;

import edu.columbia.cs.skys.skyline.DCBatchedSkyline;


/* TopLevelDemo.java requires no other files. */
public class SwingPrototype extends JPanel {
	
	public static int DEFAULT_NUM_POINTS = 1000;
	public static int DEFAULT_NUM_CONTOURS = 5;
	public static int DEFAULT_DISTR = 0;
	
	SkylinePanel spanel;
	DCBatchedSkyline skyline = new DCBatchedSkyline(0);
	
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    public SwingPrototype() {
        final int internalX = 1000;
        final int internalY = 800;
        spanel = new SkylinePanel(DEFAULT_NUM_CONTOURS, internalX,internalY,20);
        
        final JTextField numPointsField = new JTextField(4);
        numPointsField.setText("" + DEFAULT_NUM_POINTS);
        JLabel textFieldLabel = new JLabel("Number of Points: ");
        textFieldLabel.setLabelFor(numPointsField);
        
        JPanel numPanel = new JPanel();                
        numPanel.setLayout(new BoxLayout(numPanel, BoxLayout.LINE_AXIS));        
        numPanel.add(textFieldLabel);
        numPanel.add(numPointsField);

        final JTextField numContoursField = new JTextField(2);
        numContoursField.setText("" + DEFAULT_NUM_CONTOURS);
        JLabel contoursLabel = new JLabel("Number of Contours: ");
        contoursLabel.setLabelFor(numContoursField);
        
        JPanel contoursPanel = new JPanel();                
        contoursPanel.setLayout(new BoxLayout(contoursPanel, BoxLayout.LINE_AXIS));        
        contoursPanel.add(contoursLabel);
        contoursPanel.add(numContoursField);
                
        String[] distributions = {"Random", "Correlated", "Anti-Correlated"};

        final JComboBox distSelection = new JComboBox(distributions);
        distSelection.setSelectedIndex(DEFAULT_DISTR);
        JLabel distLabel = new JLabel("Data Distribution: ");
        textFieldLabel.setLabelFor(distSelection);
        JPanel distPanel = new JPanel();                
        distPanel.setLayout(new BoxLayout(distPanel, BoxLayout.LINE_AXIS));        
        distPanel.add(distLabel);
        distPanel.add(distSelection);
        
        JButton displayButton = new JButton("Display");
        displayButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int numPoints = DEFAULT_NUM_POINTS;
				int contours = DEFAULT_NUM_CONTOURS;
				try {
					numPoints = Integer.parseInt(numPointsField.getText());
				} catch (NumberFormatException e) {
					numPointsField.setText("" + DEFAULT_NUM_POINTS);
				}
				try {
					numPoints = Integer.parseInt(numPointsField.getText());
					contours = Integer.parseInt(numContoursField.getText());
				} catch (NumberFormatException e) {
					numContoursField.setText("" + DEFAULT_NUM_CONTOURS);
				}
				final int distr = distSelection.getSelectedIndex();
				final int tnumPoints = numPoints;
				Runnable updater = new SPUpdater(tnumPoints, internalX, internalY, distr, contours);
				Thread thread = new Thread(updater);
				thread.start();
			}
        });

        JPanel controlsPanel = new JPanel();        
        controlsPanel.setLayout(new FlowLayout());

        controlsPanel.setBorder(
                BorderFactory.createCompoundBorder(
                                BorderFactory.createTitledBorder("Data Parameters"),
                                BorderFactory.createEmptyBorder(5,5,5,5)));
        
        controlsPanel.add(numPanel);
        controlsPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        controlsPanel.add(contoursPanel);
        controlsPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        controlsPanel.add(distPanel);
        controlsPanel.add(Box.createRigidArea(new Dimension(50, 0)));
        controlsPanel.add(displayButton);
        
        setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
        
        add(controlsPanel);
        add(spanel);
        // launch an immediate update
		Runnable updater = new SPUpdater(DEFAULT_NUM_POINTS, internalX, internalY, DEFAULT_DISTR, DEFAULT_NUM_CONTOURS);
		Thread thread = new Thread(updater);
		thread.start();

    }

    public Point<?,?> [] calculateContours(Point<?,?> [] points, int numContours) {
    	skyline = new DCBatchedSkyline(0);
    	return skyline.getSkyline(points,numContours);    	
    }
    
    
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Skyline Prototype (Swing)");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(50,50);

        //Add content to the window.
        frame.add(new SwingPrototype());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
    
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    class SPUpdater implements Runnable {
    	
    	int _numPoints, _internalX, _internalY, _dist, _numContours;
    	
    	SPUpdater(int numPoints, int internalX, int internalY, int dist, int numContours) {
    		_numPoints = numPoints;
    		_internalX = internalX;
    		_internalY = internalY;
    		_dist = dist;
    		_numContours = numContours;
    	}
    	
    	@Override
    	public void run() {
    		Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);
			spanel.reset();
    		setCursor(hourglassCursor);
     		Point [] points = PointsGenerator.generate(_numPoints, 0, 0, _internalX, _internalY, _dist);	
     		Point<?,?> [] contouredPoints = calculateContours(points, _numContours);
    		spanel.showSkyline(contouredPoints, _numContours, null);
    		Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
    		spanel.repaint();
    		setCursor(normalCursor);
    	}
    };

}

	
	
