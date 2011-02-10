package org.geworkbench.components.skylinecontour.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.swing.JPanel;

/**
 * $Id: AbstractNumericAxis.java,v 1.3 2009-11-18 16:57:42 jiz Exp $
 * 
 * 
 * 
 * @author William Mee <wjm2107@cs.columbia.edu> Copyright (c) 2008 The Trustees
 *         of Columbia University
 */
public class AbstractNumericAxis<T extends Number> extends JPanel {

	private static final long serialVersionUID = 6067032876492143882L;
	public static final int NO_NUM_TICS = -1;
	public static final int NUM_DAYS_IN_MONTH = 30;
	public static final int MIN_TICS = 4;
	private T _from, _to;
	protected SortedMap<Float, String> _tics = new TreeMap<Float, String>();
	protected boolean _integer = false;
	// private NumberFormat _fpFormat = new DecimalFormat("");
	protected Color _lineColor = Color.white;
	protected Color _fontColor = Color.white;
	protected int _ticLength = 3;
	protected boolean _amRepainting = false;

	protected AbstractNumericAxis(T from, T to, int width, int height) {
		this(from, to, NO_NUM_TICS, width, height);
	}

	protected AbstractNumericAxis(T from, T to, int numTics, int width,
			int height) {
		setSize(width, height);
		Dimension size = new Dimension(width, height);
		setPreferredSize(size);
		if (numTics == NO_NUM_TICS) {
			calcTics(from, to);
		} else {
			calcTics(from, to, numTics);
		}
	}

	public void refresh(T from, T to, int numTics) {
		if (numTics == NO_NUM_TICS) {
			calcTics(from, to);
		} else {
			calcTics(from, to, numTics);
		}
		_amRepainting = true;
		if (!isVisible()) {
			setVisible(true);
		}
		repaint();
	}

	public void refresh(T from, T to) {
		refresh(from, to, NO_NUM_TICS);
	}

	protected String getLabel(float number) {
		if (_integer) {
			return "" + (int) number;
		} else {
			return getSignificant(number, 2);
		}
	}

	protected static String getSignificant(float value, int sigFigs) {
		MathContext mc = new MathContext(sigFigs, RoundingMode.DOWN);
		BigDecimal bigDecimal = new BigDecimal(value, mc);
		return bigDecimal.toPlainString();
	}

	protected int getXOffset(float ticVal) {
		int offset = (int) (getWidth() * ticVal / (_to.doubleValue() - _from
				.doubleValue()));
		return offset;
	}

	protected int getYOffset(float ticVal) {
		int offset = (int) (getHeight() * ticVal / (_to.doubleValue() - _from
				.doubleValue()));
		return offset;
	}

	public void reset() {
		_tics = new TreeMap<Float, String>();
		_to = _from;
	}

	public void setLineColor(Color lineColor) {
		_lineColor = lineColor;
	}

	protected void calcTics(T from, T to, int numTics) {
		_from = from;
		_to = to;
		_integer = from instanceof Integer || from instanceof Byte;
		float fromVal = _from.floatValue();
		float ticDist = (_to.floatValue() - fromVal) / numTics;
		if (numTics == NO_NUM_TICS) {
			float interval = to.floatValue() - from.floatValue();
			if (interval >= 2 && interval <= 10) {

			}
		}
		_tics.clear();
		if (from.equals(to)) {
			return;
		}
		for (int i = 0; i <= numTics; i++) {
			float ticVal = fromVal + i * ticDist;
			_tics.put(ticVal, getLabel(ticVal));
		}
	}

	protected void calcTics(T from, T to) {
		_from = from;
		_to = to;
		_tics.clear();
		if (from.equals(to)) {
			return;
		}
		_integer = from instanceof Integer || from instanceof Byte;
		float firstTic = 0, lastTic = 0, ticIncr = 0;
		float fromVal = _from.floatValue();
		float toVal = _to.floatValue();
		float interval = to.floatValue() - from.floatValue();
		ticIncr = interval > 1.0 ? (float) Math.pow(10, (int) Math
				.log10(interval * 0.9)) : (float) Math.pow(10, (int) Math
				.log10(interval) - 1);
		firstTic = ticIncr * ((int) (Math.floor(fromVal / ticIncr)));
		lastTic = ticIncr * ((int) (Math.ceil(toVal / ticIncr)));
		float ticVal = firstTic;
		float previousTicValue = 0;
		boolean dateFlag = false;
		int idx = 0;
		while (ticVal <= lastTic && getXOffset(ticVal - firstTic) <= getWidth()) {
			_tics.put(ticVal, getLabel(ticVal));
			idx++;
			previousTicValue = ticVal;
			ticVal = firstTic + idx * ticIncr;
			if (ticVal - previousTicValue < NUM_DAYS_IN_MONTH) {
				dateFlag = true;
			}
		}

		int divisionCount = 2;
		while (idx < MIN_TICS) {
			_tics.clear();
			ticIncr = ticIncr / divisionCount;
			firstTic = ticIncr * ((int) (Math.floor(fromVal / ticIncr)));
			lastTic = ticIncr * ((int) (Math.ceil(toVal / ticIncr)));
			ticVal = firstTic;
			idx = 0;
			dateFlag = false;
			while (ticVal <= lastTic
					&& getXOffset(ticVal - firstTic) <= getWidth()) {
				_tics.put(ticVal, getLabel(ticVal));
				idx++;
				previousTicValue = ticVal;
				ticVal = firstTic + idx * ticIncr;
				if (ticVal - previousTicValue < NUM_DAYS_IN_MONTH) {
					dateFlag = true;
				}
			}
			divisionCount *= 2;
		}
		if (interval != 1 && dateFlag) {
			// flag value
			_tics.put(99999.0f, "");
		}
	}
}
