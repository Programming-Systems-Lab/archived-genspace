package org.geworkbench.util;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
/*
 * Extended file chooser with overwrite-warning if a selected file exists
 * $LastChangedDate: 2010-05-07 15:17:38 -0400 (Fri, 07 May 2010) $
 * $Author: wangmen $
 * $Revision: 6487 $
 */
public class OWFileChooser extends JFileChooser {
	private static final long serialVersionUID = -8841673089062156967L;

	public OWFileChooser() {
		super();
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
