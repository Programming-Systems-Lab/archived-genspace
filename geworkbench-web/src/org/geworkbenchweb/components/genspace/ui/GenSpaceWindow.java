package org.geworkbenchweb.components.genspace.ui;

import com.vaadin.ui.Label;
import com.vaadin.ui.Window;

public class GenSpaceWindow extends Window{
	private static final long serialVersionUID = -4091993515000311665L;

	private GenSpaceComponent component;
	public GenSpaceWindow()
	{
		setCaption("genSpace");
		component = new GenSpaceComponent();
		setContent(component);
	}
}
