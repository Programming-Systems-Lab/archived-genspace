package org.geworkbench.components.skylinecontour.swing;

import java.awt.Color;

/**
 * $Id: SwingUtil.java,v 1.3 2009-11-18 16:57:42 jiz Exp $
 *
 * Swing utilities.
 *
 * @author William Mee <wjm2107@cs.columbia.edu>
 * Copyright (c) 2008 The Trustees of Columbia University
 */
public class SwingUtil {
	
	public static String colorToCssString(Color color) {
		StringBuffer buffer = new StringBuffer("#");		
		buffer.append(intToPaddedHex(color.getRed()));
		buffer.append(intToPaddedHex(color.getGreen()));
		buffer.append(intToPaddedHex(color.getBlue()));
		return buffer.toString();
	}
	
	private static String intToPaddedHex(int val) {
		String hexVal = Integer.toHexString(val);
		return hexVal.length()==1?"0"+hexVal:hexVal;		
	}

}
