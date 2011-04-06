package org.geworkbench.util;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
/*
 * Extended file chooser with overwrite-warning if a selected file exists
 * $LastChangedDate: 2011-03-17 09:37:54 -0400 (Thu, 17 Mar 2011) $
 * $Author: wangmen $
 * $Revision: 7593 $
 */
public class OWFileChooser extends JFileChooser {
	private static final long serialVersionUID = -8841673089062156967L;

	public OWFileChooser() {
	}

	public OWFileChooser(String currentDirectory) {
		super(currentDirectory);
	}

	public OWFileChooser(File currentDirectory) {
		super(currentDirectory);
	}

	public void approveSelection() {
		if (this.getSelectedFile().exists()
				&& JOptionPane.showConfirmDialog(null, this.getSelectedFile()
						+ " already exists.\nDo you want to overwrite it?",
						"Save", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION)
			return;
		super.approveSelection();
	}

	public void cancelSelection() {
		this.setSelectedFile(null);
		super.cancelSelection();
	}
}
