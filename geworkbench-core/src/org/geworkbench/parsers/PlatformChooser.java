/**
 * 
 */
package org.geworkbench.parsers;

import javax.swing.JOptionPane;

/**
 * @author zji
 * @version $Id: PlatformChooser.java 7592 2011-03-16 21:34:19Z zji $
 * 
 */
public class PlatformChooser implements Runnable {

	final private String[] possibilities;
	volatile String choice;

	PlatformChooser(final String[] possibilities) {
		this.possibilities = possibilities;
	}

	@Override
	public void run() {
		choice = (String) JOptionPane.showInputDialog(null,
				"There are multiple platforms in this file.\n"
						+ "Please choose one to load to geWorkbench",
				"Platform Chooser", JOptionPane.PLAIN_MESSAGE, null,
				possibilities, null);
	}
}
