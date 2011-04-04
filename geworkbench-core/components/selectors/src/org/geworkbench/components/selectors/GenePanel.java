package org.geworkbench.components.selectors;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; 
import java.io.File;
import java.io.FileInputStream; 
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet; 
import java.util.List;
import java.util.Set;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton; 
import javax.swing.JFileChooser; 
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTree; 
import javax.swing.tree.TreePath;

import org.geworkbench.bison.annotation.DSAnnotationContext;
import org.geworkbench.bison.datastructure.biocollections.DSAncillaryDataSet;
import org.geworkbench.bison.datastructure.biocollections.DSDataSet;
import org.geworkbench.bison.datastructure.biocollections.microarrays.DSMicroarraySet;
import org.geworkbench.bison.datastructure.biocollections.sequences.DSSequenceSet;
import org.geworkbench.bison.datastructure.bioobjects.markers.DSGeneMarker;
import org.geworkbench.bison.datastructure.bioobjects.microarray.DSSignificanceResultSet;
import org.geworkbench.bison.datastructure.complex.panels.CSAnnotPanel;
import org.geworkbench.bison.datastructure.complex.panels.CSItemList;
import org.geworkbench.bison.datastructure.complex.panels.CSPanel;
import org.geworkbench.bison.datastructure.complex.panels.DSAnnotatedPanel;
import org.geworkbench.bison.datastructure.complex.panels.DSItemList;
import org.geworkbench.bison.datastructure.complex.panels.DSPanel;
import org.geworkbench.engine.management.Overflow;
import org.geworkbench.engine.management.Publish;
import org.geworkbench.engine.management.Script;
import org.geworkbench.engine.management.Subscribe;
import org.geworkbench.events.GeneSelectorEvent;
import org.geworkbench.events.GeneTaggedEvent;
import org.geworkbench.events.MarkerSelectedEvent;
import org.geworkbench.events.SubpanelChangedEvent;
import org.geworkbench.util.FilePathnameUtils;
import org.geworkbench.util.Util;

import com.Ostermiller.util.CSVPrinter;
import com.Ostermiller.util.ExcelCSVParser;

/**
 * A panel that handles the creation and management of gene panels, as well as
 * individual gene selection.
 * 
 * @author John Watkinson
 */
public class GenePanel extends SelectorPanel<DSGeneMarker> {
	private String taggedSelection = null; // tagged for cytoscape visualization
	private boolean tagEventEnabled = true;
	private JMenuItem sortGeneItem  = new JMenuItem("by gene name");
	private JMenuItem sortProbeItem = new JMenuItem("by probe set id");
	private JMenuItem sortOriginalItem = new JMenuItem("into the original order");
	private JMenuItem sortMenu = new JMenu("Sort markers");

	/**
	 * <code>FileFilter</code> that is used by the <code>JFileChoose</code>
	 * to show just panel set files on the filesystem
	 */
	protected static class MarkerPanelSetFileFilter extends
			javax.swing.filechooser.FileFilter {
		private String fileExt;

		MarkerPanelSetFileFilter() {
			fileExt = ".csv";
		}

		public String getExtension() {
			return fileExt;
		}

		public String getDescription() {
			return "Comma Separated Values Files";
		}

		public boolean accept(File f) {
			boolean returnVal = false;
			if (f.isDirectory() || f.getName().endsWith(fileExt)) {
				return true;
			}
			return returnVal;
		}
	}

	public GenePanel() {
		super(DSGeneMarker.class, "Marker");
		tagEventEnabled = true;
		// Add gene panel specific menu items.
		sortMenu.add(sortGeneItem);
		sortMenu.add(sortProbeItem);
		sortMenu.add(sortOriginalItem);
		itemListPopup.add(sortMenu);
		treePopup.insert(newPanelItem2, 4);
		treePopup.add(tagPanelMenuItem);
		rootPopup.add(loadPanelItem);
		rootPopup.add(newPanelItem);
		rootPopup.add(deleteSetGroupItem);

		tagPanelMenuItem.add(highlightPanelItem);
		tagPanelMenuItem.add(useVisualProPanelItem);
		highlightPanelItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!tagEventEnabled) return; // to avoid event cycle
				String selected = (String)panelTree.getSelectionPath().getLastPathComponent();
				taggedSelection = selected;
				panelTree.repaint();
				publishGeneTaggedEvent(new GeneTaggedEvent(context.getItemsWithLabel(taggedSelection), GeneTaggedEvent.HIGHLIGHT));
			}
		});
		
		 
		useVisualProPanelItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!tagEventEnabled) return; // to avoid event cycle
				String selected = (String)panelTree.getSelectionPath().getLastPathComponent();
				taggedSelection = selected;
				panelTree.repaint();
				DSPanel<DSGeneMarker> panel = context.getItemsWithLabel(taggedSelection);
				publishGeneTaggedEvent(new GeneTaggedEvent(panel, GeneTaggedEvent.USE_VISUAL_PROPERTY, getPanelIndex(panel)));
			}
		});
		
		exportPanelItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportPanelPressed();
			}
		});
		loadPanelItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadButtonPressed();
			}
		});
		deleteSetGroupItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteContext();
			}
		});
		newPanelItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createNewSubset();
			}
		});
		newPanelItem2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createNewSubset();
			}
		});
		sortGeneItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				maSet.setSelectorMarkerOrder("gene");
				sortByGene();
			}
		});
		sortProbeItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				maSet.setSelectorMarkerOrder("probe");
				sortByProbe();
			}
		});
		sortOriginalItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				maSet.setSelectorMarkerOrder("original");
				sortOriginal();
			}
		});
		// Load button at bottom of component
		JPanel loadPanel = new JPanel();
		loadPanel.setLayout(new BoxLayout(loadPanel, BoxLayout.X_AXIS));
		JButton loadButton = new JButton("Load Set");
		JButton loadSymbolsButton = new JButton("Load By Symbols");
		loadPanel.add(loadButton);
		loadPanel.add(loadSymbolsButton);
		loadPanel.add(Box.createHorizontalGlue());
		loadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadButtonPressed();
			}
		});
		loadSymbolsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadSymbols();
			}
		});
		lowerPanel.add(loadPanel);

		taggedSelection = "Selection"; // default initial tagged selection
		setTreeRenderer(new CustomizedRenderer());
	}

	private void sortByGene(){
		Collections.sort(itemList, new MarkerOrderByGene());
		sortGeneItem.setEnabled(false);
		sortProbeItem.setEnabled(true);
		sortOriginalItem.setEnabled(true);
		mainPanel.repaint();
	}
	private void sortByProbe(){
		Collections.sort(itemList, new MarkerOrderByProbe());
		sortProbeItem.setEnabled(false);
		sortGeneItem.setEnabled(true);
		sortOriginalItem.setEnabled(true);
		mainPanel.repaint();
	}
	private void sortOriginal(){
		Collections.sort(itemList, new MarkerOrderOriginal());
		sortProbeItem.setEnabled(true);
		sortGeneItem.setEnabled(true);
		sortOriginalItem.setEnabled(false);
		mainPanel.repaint();
	}
	
	private JMenuItem loadPanelItem = new JMenuItem("Load Set");
	private JMenuItem deleteSetGroupItem = new JMenuItem("Delete Group");
	private JMenuItem exportPanelItem = new JMenuItem("Export");
	private JMenuItem tagPanelMenuItem = new JMenu("Tag for visualization");
	private JMenuItem highlightPanelItem = new JMenuItem("Highlight");
	private JMenuItem useVisualProPanelItem = new JMenuItem("Use visual properties color");
	private JMenuItem newPanelItem = new JMenuItem("New Set");
	private JMenuItem newPanelItem2 = new JMenuItem("New Set");

	protected void setSelectorLastDirConf(){
		selectorLastDirConf = FilePathnameUtils.getUserSettingDirectoryPath()
				+ "selectors" + FilePathnameUtils.FILE_SEPARATOR
				+ "selectorLastDir.conf";
	}

	protected SelectorHelper<DSGeneMarker> getSelectorHelper() {
		helper = new SelectorHelper<DSGeneMarker>(this);
		return helper;
	}
	private void loadButtonPressed() {
		helper = getSelectorHelper();
		/**
		 * The line below sets root directory for JFileChooser to set to home
		 * directory user commented line JFileChooser without any parameters
		 */
		JFileChooser fc = new JFileChooser(".");
		// JFileChooser fc = new JFileChooser();
		javax.swing.filechooser.FileFilter filter = new MarkerPanelSetFileFilter();
		fc.setAcceptAllFileFilterUsed(false);
		fc.setFileFilter(filter);
		fc.setDialogTitle("Open Marker Set");
		if (!lastDir.equals("")) {
			fc.setCurrentDirectory(new File(lastDir));
		}
		int choice = fc.showOpenDialog(mainPanel.getParent());

		if (choice == JFileChooser.APPROVE_OPTION) {
			lastDir = fc.getSelectedFile().getPath();
			try {
				helper.setLastDataDirectory(fc.getCurrentDirectory()
						.getCanonicalPath());
			} catch (Exception e) {
				e.printStackTrace();
			}
			DSPanel<DSGeneMarker> panel = deserializePanel(fc.getSelectedFile());
			addPanel(panel);
			throwLabelEvent();
		}
	}
	
	private void createNewSubset() {
		String label = JOptionPane.showInputDialog("Set Label:",
				"");
		if (label == null) {
			return;
		} else {
			if (context.indexOfLabel(label) == -1) {
				addPanel(new CSPanel(label));
			} else {
				JOptionPane.showMessageDialog(null,
					    "Label already in use.",
					    "Warning",
					    JOptionPane.WARNING_MESSAGE);
				return;
			}
			panelTree.scrollPathToVisible(new TreePath(new Object[] {
					context, label }));
			treeModel.fireLabelItemsChanged(label);
			throwLabelEvent();
		}
	}
	
	/** action for load symbols button */
	private void loadSymbols() {
		helper = getSelectorHelper();
		JFileChooser fc = new JFileChooser(".");
		javax.swing.filechooser.FileFilter filter = new MarkerPanelSetFileFilter();
		fc.setFileFilter(filter);
		fc.setDialogTitle("Open Symbols");
		if (!lastDir.equals("")) {
			fc.setCurrentDirectory(new File(lastDir));
		}
		int choice = fc.showOpenDialog(mainPanel.getParent());

		if (choice == JFileChooser.APPROVE_OPTION) {
			lastDir = fc.getSelectedFile().getPath();
			try {
				helper.setLastDataDirectory(fc.getCurrentDirectory()
						.getCanonicalPath());
			} catch (Exception e) {
				e.printStackTrace();
			}
			DSPanel<DSGeneMarker> panel = getPanelFromSymbols(fc.getSelectedFile());
			addPanel(panel);
			throwLabelEvent();
		}
	}

	private void exportPanelPressed() {
		JOptionPane.showMessageDialog(getComponent(), "To be implemented...");
		// todo
	}

	protected void throwLabelEvent() {
		GeneSelectorEvent event = null;
		event = new GeneSelectorEvent(context.getLabelTree());
		if (event != null) {
			publishGeneSelectorEvent(event);
		}
	}

    private class MarkerOrderByGene implements Comparator<DSGeneMarker> {
		public int compare(DSGeneMarker o1, DSGeneMarker o2) {
			int res = o1.getGeneName().compareToIgnoreCase(((DSGeneMarker)o2).getGeneName());
			if (res == 0)
				return o1.getLabel().compareToIgnoreCase(((DSGeneMarker)o2).getLabel());
			return res;
		}
    }

    private class MarkerOrderByProbe implements Comparator<DSGeneMarker> {
		public int compare(DSGeneMarker o1, DSGeneMarker o2) {
			return o1.getLabel().compareToIgnoreCase(((DSGeneMarker)o2).getLabel());
		}
    }

    private class MarkerOrderOriginal implements Comparator<DSGeneMarker> {
		public int compare(DSGeneMarker o1, DSGeneMarker o2) {
			return o1.getSerial() - ((DSGeneMarker)o2).getSerial();
		}
    }
    
	/**
	 * Utility to obtain the stored panel sets from the filesystem
	 * 
	 * @param file
	 *            file which contains the stored panel set
	 */
	private DSPanel<DSGeneMarker> deserializePanel(final File file) {
		FileInputStream inputStream = null;
		String filename = file.getName();
		if (filename.toLowerCase().endsWith(".csv")) {
			filename = filename.substring(0, filename.length() - 4);
		}
		// Ensure loaded file has unique name
		Set<String> nameSet = new HashSet<String>();
		int n = context.getNumberOfLabels();
		for (int i = 0; i < n; i++) {
			nameSet.add(context.getLabel(i));
		}
		filename = Util.getUniqueName(filename, nameSet);
		DSPanel<DSGeneMarker> panel = new CSPanel<DSGeneMarker>(filename);
		try {
			inputStream = new FileInputStream(file);
			ExcelCSVParser parser = new ExcelCSVParser(inputStream);
			String[][] data = parser.getAllValues();
			for (int i = 0; i < data.length; i++) {
				String[] line = data[i];
				if (line.length > 0) {
					String label = line[0];
					DSGeneMarker marker = itemList.get(label);
					if (marker != null) {
						panel.add(marker);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					// Lost cause
				}
			}
		}
		return panel;
	}

	/**
	 * Get DSPanel of gene marks based a file of symbols - gene names.
	 * This is a feature requested in mantis issue 1477.
	 * @param file
	 * @return
	 */
	private DSPanel<DSGeneMarker> getPanelFromSymbols(final File file) {
		FileInputStream inputStream = null;
		String filename = file.getName();
		if (filename.toLowerCase().endsWith(".csv")) {
			filename = filename.substring(0, filename.length() - 4);
		}
		// Ensure loaded file has unique name
		Set<String> nameSet = new HashSet<String>();
		int n = context.getNumberOfLabels();
		for (int i = 0; i < n; i++) {
			nameSet.add(context.getLabel(i));
		}
		filename = Util.getUniqueName(filename, nameSet);
		DSPanel<DSGeneMarker> panel = new CSPanel<DSGeneMarker>(filename);
		
		List<String> selectedNames = new ArrayList<String>();
		try {
			inputStream = new FileInputStream(file);
			ExcelCSVParser parser = new ExcelCSVParser(inputStream);
			String[][] data = parser.getAllValues();
			for (int i = 0; i < data.length; i++) {
				String[] line = data[i];
				if (line.length > 0) {
					selectedNames.add(line[0]);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					// Lost cause
				}
			}
		}
		for(DSGeneMarker marker: itemList) {
			if(selectedNames.contains(marker.getGeneName()))
				panel.add(marker);
		}

		return panel;
	}

	private DSMicroarraySet maSet = null;
	@SuppressWarnings("unchecked")
	protected boolean dataSetChanged(DSDataSet dataSet) {

		if (dataSet instanceof DSMicroarraySet) {
			sortMenu.setEnabled(true);
			sortProbeItem.setEnabled(true);
			sortGeneItem.setEnabled(true);
			sortOriginalItem.setEnabled(false);
			maSet = (DSMicroarraySet) dataSet;
			setItemList(maSet.getMarkers());
			itemList = new CSItemList<DSGeneMarker>();
			itemList.addAll(maSet.getMarkers());
			String order = maSet.getSelectorMarkerOrder();
			if (order != null && !order.equals("original")) {
				if (order.equals("gene"))       sortByGene();
				else if (order.equals("probe")) sortByProbe();
			}
			return true;
		} else if (dataSet instanceof DSSequenceSet) {
			sortMenu.setEnabled(false);
			DSItemList items = (DSItemList) ((DSSequenceSet) dataSet).getMarkerList();
			setItemList(items);
			return true;
		}
		return false;
	}

	/**
	 * A new method to update the selected Panel marker numbers after the
	 * filtering.
	 */
	@SuppressWarnings("unchecked")
	private void updateSelectedPanel() {
		int childTotalNumber = context.getNumberOfLabels();
		for (int i = 0; i < childTotalNumber; i++) {
			String label = context.getLabel(i);
			DSPanel panel = context.getItemsWithLabel(label);
			DSPanel removedPanel = new CSPanel();
			if (panel != null && panel.size() > 0) {
				for (Object o : panel) {
					if (!itemList.contains(o)) {
						removedPanel.add(o);
					}
				}
				for (Object o : removedPanel) {
					panel.remove(o);
				}
			}
			treeModel.fireLabelChanged(label);

		}
	}

	/**
	 * For receiving the results of applying a filter to a microarray set.
	 * 
	 * @param fe
	 */
	@Subscribe
	public void receive(org.geworkbench.events.FilteringEvent fe, Object source) {
		if (fe == null) {
			return;
		}
		DSMicroarraySet sourceMA = fe.getOriginalMASet();
		if (sourceMA == null) {
			return;
		}

		updateSelectedPanel();
	}

	/**
	 * Called when a single marker is selected by a component.
	 */
	@Subscribe
	public void receive(MarkerSelectedEvent event, Object source) {
		JList list = itemAutoList.getList();
		int index = itemList.indexOf(event.getMarker());
		list.setSelectedIndex(index);
		list.scrollRectToVisible(list.getCellBounds(index, index));
	}

	@Publish
	@SuppressWarnings("unchecked")
	public SubpanelChangedEvent publishSubpanelChangedEvent(
			SubpanelChangedEvent event) {
		return event;
	}

	@Publish
	public GeneSelectorEvent publishGeneSelectorEvent(GeneSelectorEvent event) {
		return event;
	}

	@Publish
	public GeneTaggedEvent publishGeneTaggedEvent(GeneTaggedEvent event) {
		return event;
	}

	protected void publishSingleSelectionEvent(DSGeneMarker item) {
		publishGeneSelectorEvent(new GeneSelectorEvent(item));
	}

	@Script
	@SuppressWarnings("unchecked")
	public void setDataSet(DSDataSet dataSet) {
		processDataSet(dataSet);
	}

	@Script
	@SuppressWarnings("unchecked")
	public DSPanel createPanels(String label, int[] positions) {
		// Ensure loaded file has unique name
		Set<String> nameSet = new HashSet<String>();
		int n = context.getNumberOfLabels();
		for (int i = 0; i < n; i++) {
			nameSet.add(context.getLabel(i));
		}
		label = Util.getUniqueName(label, nameSet);
		DSPanel<DSGeneMarker> panel = new CSPanel<DSGeneMarker>(label);
		for (int position : positions) {
			DSGeneMarker marker = itemList.get(position);
			if (marker != null) {
				panel.add(marker);
			}
		}

		addPanel(panel); // redundancy between broadcast and script
		panel.setActive(true);
		publishGeneSelectorEvent(new GeneSelectorEvent(panel));
		return panel;
	}

	@Script
	@SuppressWarnings("unchecked")
	public DSPanel createPanel(String label, int position) {
		// Ensure loaded file has unique name
		Set<String> nameSet = new HashSet<String>();
		int n = context.getNumberOfLabels();
		for (int i = 0; i < n; i++) {
			nameSet.add(context.getLabel(i));
		}
		label = Util.getUniqueName(label, nameSet);
		DSPanel<DSGeneMarker> panel = new CSPanel<DSGeneMarker>(label);
		DSGeneMarker marker = itemList.get(position);
		if (marker != null) {
			panel.add(marker);
		}
		try {
			addPanel(panel); // redundancy between broadcast and script

		} catch (IndexOutOfBoundsException e) {

		}
		panel.setActive(true);
		publishGeneSelectorEvent(new GeneSelectorEvent(panel));
		return panel;
	}

	@Script
	@SuppressWarnings("unchecked")
	public DSPanel getPanel(String dir) {
		DSPanel<DSGeneMarker> panel = deserializePanel(new File(dir));
		panel.setActive(true);
		addPanel(panel); // redundancy between broadcast and script
		publishGeneSelectorEvent(new GeneSelectorEvent(panel));

		return panel;
	}

	@Subscribe
	public void receive(
			org.geworkbench.events.ProjectNodePostCompletedEvent pnce,
			Object source) {
		DSAncillaryDataSet result = pnce.getAncillaryDataSet();

		if ((result != null) && (result instanceof DSSignificanceResultSet)) {
			// if it's a significance result set, we put all markers to a newly
			// created Annotated Panel.
			DSAnnotatedPanel<DSGeneMarker, Float> panelSignificant = new CSAnnotPanel<DSGeneMarker, Float>(
					"Significant Genes");
			DSSignificanceResultSet temp = (DSSignificanceResultSet<DSGeneMarker>) result;
			DSPanel<DSGeneMarker> temp2 = temp.getSignificantMarkers();
			for (DSGeneMarker named : temp2) {
				panelSignificant.add(named);
			}

			// then, put that newly created Annotated Panel to GenePanel.

			/*
			 * in order to change the context without changing the focused node,
			 * we do following: 1. save current context, 2. change to the one
			 * need modify, 3. change back to current context.
			 */

			// 1. save current context
			DSAnnotationContext currentContext = context;
			// 2. change to the one need modify
			dataSetChanged(pnce.getAncillaryDataSet().getParentDataSet());

			publishSubpanelChangedEvent(new SubpanelChangedEvent(
					DSGeneMarker.class, panelSignificant,
					SubpanelChangedEvent.NEW));
			/*
			 * our geWorkbench will not publish event to ourself (ex: in this
			 * case, we want it to be received in our parent - selectorPanel) so
			 * we still need to call the receive() manually
			 */
			this.receive(new SubpanelChangedEvent(DSGeneMarker.class,
					panelSignificant, SubpanelChangedEvent.NEW), this);

			// 3. change it back
			context = currentContext;

		}
	}

	private class CustomizedRenderer extends SelectorTreeRenderer {
		private static final long serialVersionUID = -1175125397626147482L;

		public CustomizedRenderer() {
            super(GenePanel.this);
        }

        @Override
        public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
            Component comp = super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
//            if (value instanceof String) {
//            	if(value.equals(taggedSelection))
//            		cellLabel.setForeground(Color.BLUE);
//            }
            return comp;
        }
    }

	@SuppressWarnings("unchecked")
	@Override
	@Subscribe(Overflow.class)
	public void receive(org.geworkbench.events.SubpanelChangedEvent spe,
			Object source) {
    	// the proxy produced by cglib is something like this
    	//org.geworkbench.components.cytoscape.CytoscapeWidget$$EnhancerByCGLIB$$8bb8f936
   		if(source.getClass().getName().startsWith("org.geworkbench.components.cytoscape.CytoscapeWidget")){
    		
    		tagEventEnabled = false; // to prevent event cycle between GenePanel and CytoscapeWidget
    		spe.getPanel().setLabel("Cytoscape selection");
    	}
    	super.receive(spe, source);
    	tagEventEnabled = true;
    }

}