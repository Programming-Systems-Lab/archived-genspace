package org.geworkbench.components.skylinecontour;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.geworkbench.engine.config.MenuListener;
import org.geworkbench.components.skylinecontour.swing.SkylineViewer;

public class SkyLineContourMenu implements MenuListener {

	public ActionListener getActionListener(String var) {
		if (var.equalsIgnoreCase("Tools.skylineContour")) {
			return new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					SkylineViewer viewer = new SkylineViewer();
					viewer.execute();
				}
			};
		}
		return null;
	}
}
