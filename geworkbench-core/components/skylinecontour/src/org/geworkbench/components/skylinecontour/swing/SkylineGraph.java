package org.geworkbench.components.skylinecontour.swing;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Arrays;

import javax.swing.JPanel;

import edu.columbia.cs.skys.data.Point;
import edu.columbia.cs.skys.data.PointUtil;


/**
 * $Id: SkylineGraph.java,v 1.4 2009-11-18 16:57:42 jiz Exp $
 *
 * A skyline panel with axes.
 *
 * @author William Mee <wjm2107@cs.columbia.edu>
 * Copyright (c) 2008 The Trustees of Columbia University
 */
public class SkylineGraph extends JPanel  {
	
	VerticalAxis _vaxis;
	HorizontalAxis _haxis;
	SkylinePanel _spanel;
	Point<?, ?> [] _bounds;
	int _hAxisHeight = 50;
	int _vAxisWidth = 50;
	int _numContours = 0;

	
	public SkylineGraph(int numContours, int width, int height) {
		super(new GridBagLayout());
		_numContours = numContours;
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 1;
		constraints.gridy = 0;
		_spanel = new SkylinePanel(numContours, width-_vAxisWidth, height-_hAxisHeight);
		add(_spanel, constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		_vaxis = new VerticalAxis<Float>(0f, 0f,_vAxisWidth, height-_hAxisHeight);
		_vaxis.setBackground(Color.black);
		_vaxis.setLineColor(Color.darkGray);
		//_vaxis.setVisible(false);
		add(_vaxis, constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 1;
		constraints.gridy = 1;
		_haxis = new HorizontalAxis<Float>(0f,0f,width-_vAxisWidth, _hAxisHeight);
		_haxis.setBackground(Color.black);
		_haxis.setLineColor(Color.darkGray);
		//_haxis.setVisible(false);
		add(_haxis, constraints);
		setBackground(Color.black);
	}
	
	public SkylineGraph(Point<?,?> [] points, int numContours, int width, int height) {
		super(new GridBagLayout());
		getPointBounds(points);
		_numContours = numContours;
		
		double startX = _bounds[0].xToDouble();
 		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 1;
		constraints.gridy = 0;
		_spanel = new SkylinePanel(points, numContours, width-_vAxisWidth, height-_hAxisHeight,_bounds);
		add(_spanel, constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		_vaxis = new VerticalAxis<Float>((float)_bounds[0].yToDouble(),(float)_bounds[1].yToDouble(),_vAxisWidth, height-_hAxisHeight);
		_vaxis.setBackground(Color.black);
		_vaxis.setLineColor(Color.darkGray);
		add(_vaxis, constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 1;
		constraints.gridy = 1;
		_haxis = new HorizontalAxis<Float>((float)_bounds[0].xToDouble(),(float)_bounds[1].xToDouble(),width-_vAxisWidth, _hAxisHeight);
		_haxis.setBackground(Color.black);
		_haxis.setLineColor(Color.darkGray);
		add(_haxis, constraints);
		setBackground(Color.black);
	}

	public void refreshSkyline(Point<?,?> [] newpoints) {
		_spanel.showSkyline(newpoints, _numContours, _bounds);
	}
	
	/**
	 * Extend the existing skyline with a new set of points
	 * @param extraPoints
	 */
	public void extendSkyline(Point<?,?> [] extraPoints) {
		getPointBounds(extraPoints);
		if (_bounds==null) {
			return;
		}
		_spanel.extendSkyline(extraPoints, _bounds);
		_vaxis.refresh((float)_bounds[0].yToDouble(),(float)_bounds[1].yToDouble());
		_haxis.refresh((float)_bounds[0].xToDouble(),(float)_bounds[1].xToDouble());
	}
	
	public void setNumContours(int numContours) {
		_spanel._maxContours = numContours;
	}
	
	public void getPointBounds(Point<?,?> [] points) {
		if (_bounds==null && (points==null || points.length==0)) {
			// do not set
			return;
		}
		Point<?,?> [] bounds = (_bounds==null)?PointUtil.bounds(points):PointUtil.extendBounds(points,_bounds);
		_bounds = new Point[2];
		double loX = bounds[0].xToDouble();
		double hiX = bounds[1].xToDouble();
		double loY = bounds[0].yToDouble();
		double hiY = bounds[1].yToDouble();
		if (hiX - loX > 100) {
			loX=100*Math.floor(loX/100);
			hiX=100*Math.ceil(hiX/100);
		}
		if (hiY - loY > 100) {
			loY=100*Math.floor(loY/100);
			hiY=100*Math.ceil(hiY/100);
		} else if (hiY - loY > 0.1 && hiY - loY <= 1) {
			loY=0;
			hiY=1;			
		}
		_bounds[0] = new Point<Float,Float>((float)loX, (float)loY);		
		_bounds[1] = new Point<Float,Float>((float)hiX, (float)hiY);
			
	}

	public SkylinePanel getSkylinePanel() {
		return _spanel;
	}
	
	public void reset() {
		_bounds=null;
		_spanel.reset();
		_vaxis.reset();
		_haxis.reset();
	}
}
