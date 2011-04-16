package org.geworkbench.components.genspace.ui;

<<<<<<< HEAD
import java.awt.Color;
=======
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import org.geworkbench.components.genspace.entity.Tool;

public class NameRenderer extends JLabel implements ListCellRenderer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7600527294850408384L;
	public NameRenderer() {
		setOpaque(true);
	}
	@Override
	 public Component getListCellRendererComponent(
	         JList list,
	         Object value,
	         int index,
	         boolean isSelected,
	         boolean cellHasFocus)
	     {
		if(value instanceof String)
			setText((String) value);
		if(value instanceof Tool)
			setText(((Tool) value).getName());
		setBackground(isSelected ? list.getSelectionBackground() : list.getBackground());
        setForeground(isSelected ? list.getSelectionForeground() : list.getForeground());
		return this;
	}

}
