package org.geworkbench.components.skylinecontour.swing;

import java.awt.Color;

import org.geworkbench.components.skylinecontour.util.Gradient;

/**
 * $Id: ColorGradient.java,v 1.3 2009-11-18 16:57:42 jiz Exp $
 *
 * Handles gradients of colours.
 * 
 * Currently wrapper around external code from generation5.
 *
 * @author William Mee <wjm2107@cs.columbia.edu>
 * Copyright (c) 2008 The Trustees of Columbia University
 */
public class ColorGradient {

	private Gradient _gen5Gradient;
	int _numSteps;
	
	public ColorGradient(Color [] colorList) {
		this(colorList, 256);
	}
	
	public ColorGradient(Color [] colorList, int numSteps) {
		_gen5Gradient = new Gradient();
		for (Color color: colorList) {
			_gen5Gradient.addPoint(color);
		}
		_gen5Gradient.createGradient(numSteps);
		_numSteps = numSteps;
	}
	
	public Color getColor(int idx) {
		if (idx >= _numSteps) {
			idx = _numSteps-1;
		}
		return _gen5Gradient.getColour(idx);
	}
	
	public Color getColor(float dist) {
		if (dist>1) {
			dist = 1.0f;
		}
		if (dist < 0) {
			dist = 0.0f;
		}
		int idx = (int)(_numSteps * dist);
		return _gen5Gradient.getColour(idx);
	}
	
}
