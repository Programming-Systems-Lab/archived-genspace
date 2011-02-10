package org.geworkbench.components.skylinecontour.swing;

import java.awt.Component;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

/**
 * $Id: HorizontalAxis.java,v 1.4 2009-11-18 16:57:42 jiz Exp $
 * 
 * 
 * 
 * @author William Mee <wjm2107@cs.columbia.edu> Copyright (c) 2008 The Trustees
 *         of Columbia University
 */
public class HorizontalAxis<T extends Number> extends AbstractNumericAxis<T> {

	protected HorizontalAxis(T from, T to, int numTics, int width, int height) {
		super(from, to, numTics, width, height);
	}

	protected HorizontalAxis(T from, T to, int width, int height) {
		super(from, to, width, height);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		if (_amRepainting) {
			g2d.setColor(getBackground());
			g2d.fillRect(0, 0, getWidth(), getHeight());
			_amRepainting = false;
		}
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		FontMetrics fm = g.getFontMetrics();
		int labelAscent = fm.getAscent();
		g2d.setColor(_lineColor); 
		g2d.drawLine(0, 0, getWidth(), 0);
		String lable = null;
		int xTic = 0;
		int ticCount = 0;
		SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM yy");

		for (Map.Entry<Float, String> tic : _tics.entrySet()) {
			if (_tics.lastKey() == 99999.0f) {
				dateFormatter = new SimpleDateFormat("dd MMM yy");
			}
			break;
		}
		
		for (Map.Entry<Float, String> tic : _tics.entrySet()) {
			lable = tic.getValue();
			if(lable.equals("")){
				continue;
			}
			float offset = tic.getKey() - _tics.firstKey();
			xTic = getXOffset(offset);
			if (xTic < 0 || xTic > getWidth() || getWidth() - xTic < 20) {
				continue;
			}
			g2d.setColor(_lineColor);
			g2d.drawLine(xTic, 0, xTic, 3);
			g2d.setColor(_fontColor);
			Calendar now = Calendar.getInstance();
			now.add(Calendar.DATE, -new Integer(lable));
			if (ticCount == 0) {
				// first label
				g2d.drawString(dateFormatter.format(now.getTime()), xTic,
						5 + labelAscent);
				
				g2d.drawString("Publication Date", (getWidth()/2)-20,50);
				
			} else {
				g2d.drawString(dateFormatter.format(now.getTime()), xTic - 15,
						5 + labelAscent);
			}
			ticCount++;
		}
		if (lable != null && xTic == getWidth()) {
			int width = getWidth() - 1;
			g2d.setColor(_lineColor);
			g2d.drawLine(width, 0, width, 3);
			g2d.setColor(_fontColor);
			// last label gets special treatment
			Calendar now = Calendar.getInstance();
			now.add(Calendar.DATE, -new Integer(lable));
			int lastLabelWidth = fm.stringWidth(dateFormatter.format(now
					.getTime()));
			g2d.drawString(dateFormatter.format(now.getTime()), width
					- lastLabelWidth, 5 + labelAscent);
		}
	}
}
