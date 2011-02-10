package org.geworkbench.components.skylinecontour.swing;

import java.awt.Component;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Map;

import org.geworkbench.components.skylinecontour.swing.SkylineViewer;

/**
 * $Id: VerticalAxis.java,v 1.4 2009-11-18 16:57:42 jiz Exp $
 *
 *
 *
 * @author William Mee <wjm2107@cs.columbia.edu>
 * Copyright (c) 2008 The Trustees of Columbia University
 */
public class VerticalAxis<T extends Number> extends AbstractNumericAxis<T> {

	protected VerticalAxis(T from, T to, int numTics,  int width, int height) {
		super(from, to, numTics, width, height);
	}

	protected VerticalAxis(T from, T to,  int width, int height) {
		super(from, to, width, height);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		int width = getWidth();
		int maxX = width;
		int maxY = getHeight();
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		FontMetrics fm = g.getFontMetrics();
		int labelAscent = fm.getAscent();
		g2d.setColor(_lineColor);
		g2d.drawLine(maxX-1, 0, maxX-1, getHeight());
		String lable = null;
		int yTic = 0;
		int count = 0;
		for (Map.Entry<Float, String> tic : _tics.entrySet()) {
			lable = tic.getValue();
			float offset = tic.getKey();
			yTic = maxY - getYOffset(offset);
			if (yTic <0 || yTic > getHeight()) {
				continue;
			}
			g2d.setColor(_lineColor);
			g2d.drawLine(maxX-_ticLength,yTic,maxX,yTic);
			g2d.setColor(_fontColor);
			int labelWidth = fm.stringWidth(lable);
			g2d.drawString(lable, maxX - _ticLength - labelWidth - 2, yTic);
			
			if(count == 0) {
				String[] score = SkylineViewer.scoreType.split("");
				for(int i=0;i<score.length;i++)
					g2d.drawString(score[i],0,(getHeight()/2)-50+(20*i));
			}
			count++;
		}
		if (lable != null && yTic == 0) {
			g2d.setColor(_lineColor);
			g2d.drawLine(maxX-_ticLength,0,maxX,0);
			g2d.setColor(_fontColor);
			// last label gets special treatment
			int labelWidth = fm.stringWidth(lable);
			g2d.drawString(lable, maxX - _ticLength - labelWidth - 2, labelAscent);
		}
		
	}
}
