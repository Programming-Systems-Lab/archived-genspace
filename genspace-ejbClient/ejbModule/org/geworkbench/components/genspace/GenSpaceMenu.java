package org.geworkbench.components.genspace;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.geworkbench.engine.config.MenuListener;

/**
 * A menu for the genSpace component.
 * 
 * @author sheths
 */
public class GenSpaceMenu implements MenuListener {

	@Override
	public ActionListener getActionListener(String var) {
		if (var.equalsIgnoreCase("Tools.genSpace")) {
			return new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
<<<<<<< HEAD
=======
					@SuppressWarnings("unused")
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7
					GenSpace genspace = new GenSpace();
				}
			};
		}
		return null;
	}
}
