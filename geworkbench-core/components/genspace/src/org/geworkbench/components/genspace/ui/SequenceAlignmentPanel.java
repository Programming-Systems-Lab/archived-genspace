//package genspace.ui;
package org.geworkbench.components.genspace.ui;

import jalview.datamodel.AlignmentI;
import jalview.datamodel.Sequence;
import jalview.datamodel.SequenceI;
import jalview.gui.AlignFrame;
import jalview.io.FileLoader;
import jalview.io.IdentifyFile;
import jalview.schemes.ColourSchemeI;
import jalview.schemes.ColourSchemeProperty;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import org.geworkbench.components.genspace.server.stubs.Alignment;
import org.geworkbench.components.genspace.server.stubs.MSARecommender;
import org.geworkbench.components.genspace.server.stubs.MSARecommenderService;
import org.geworkbench.components.genspace.server.stubs.ProteinSequence;
import org.geworkbench.components.genspace.server.stubs.Reference;
import org.geworkbench.engine.config.VisualPlugin;

@SuppressWarnings("serial")
public class SequenceAlignmentPanel extends JPanel implements VisualPlugin,
		Runnable {

	public static interface MSARecommenderCallback {
		void sequenceAdded(ProteinSequence proteinSequence);
	}
	
	private MSARecommenderCallback msaRecommenderCallback;
	
	public void setMsaRecommenderCallback(
			MSARecommenderCallback msaRecommenderCallback) {
		this.msaRecommenderCallback = msaRecommenderCallback;
	}

	private static final class AlignmentsEditor extends DefaultCellEditor {
		List<Alignment> alignments;
		public AlignmentsEditor(JCheckBox checkBox) {
			super(checkBox);
		}

		@SuppressWarnings("unchecked")
		public Component getTableCellEditorComponent(JTable table,
				Object value, boolean isSelected, int row, int column) {
			if (!(value instanceof List<?>)) {
				return super.getTableCellEditorComponent(table, value,
						isSelected, row, column);
			}
			alignments = (List<Alignment>) value;
			return new AlignmentsPanel(alignments);
		}
		
		@Override
		public Object getCellEditorValue() {
			return alignments;
		}
	}

	private static final class AlignmentsPanel extends JPanel {
		public AlignmentsPanel(List<Alignment> alignments) {
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

			for (Alignment alignment : alignments) {
				final JLabel label = new JLabel(alignment.getEmblId());
				StringBuilder sb = new StringBuilder("<HTML><B>");
				for (Reference ref : alignment.getReferences()) {
					String title = ref.getTitle();
					if (title.length() > 2) {
						sb.append(title).append(" ");
					}
					else {
						sb.append("[NO TITLE]; ");
					}
					sb.append(ref.getAuthors()).append(" ");
					sb.append(ref.getLocator()).append("<BR>");
				}
				label.setToolTipText(sb.append("</B></HTML>").toString());
				add(label);
			}
		}
	}

	private static final class ButtonEditor extends DefaultCellEditor {
		public ButtonEditor(JCheckBox checkBox) {
			super(checkBox);
		}

		public Component getTableCellEditorComponent(JTable table,
				Object value, boolean isSelected, int row, int column) {
			return (Component) value;
		}
	}

	private AlignmentI alignment;
	private JTable table;
	private JLabel status;
	private AlignFrame af;

	private static SequenceAlignmentPanel INSTANCE;
	public static SequenceAlignmentPanel getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new SequenceAlignmentPanel();
		}
		return INSTANCE;
	}
	
	/**
	 * Constructor
	 */
	private SequenceAlignmentPanel() {
		run();
	}

	@Override
	public void run() {

		alignment = new jalview.datamodel.Alignment(new SequenceI [] {});
		af = new AlignFrame(alignment, 800, 400);
		af.setPreferredSize(new Dimension(800, 400));
		alignment = af.getCurrentView().getAlignment();
		af.toFront();
		af.setVisible(true);
		af.setClosable(true);
		af.setResizable(true);
		af.setMaximizable(true);
		af.setIconifiable(true);
		af.setFrameIcon(null);

		table = new JTable();
		status = new JLabel();

		JPanel lowerPanel = new JPanel(new BorderLayout());
		lowerPanel.add(new JScrollPane(table,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED),
				BorderLayout.CENTER);
		lowerPanel.add(status, BorderLayout.SOUTH);

		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setDividerLocation(0.5);

		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setPreferredSize(new Dimension(800, 400));
		desktopPane.add(af);
		try {
			af.setMaximum(true);
		} catch (PropertyVetoException ignore) {
		}
		
		Dimension minimumSize = new Dimension(0, 200);
		desktopPane.setMinimumSize(minimumSize);
		lowerPanel.setMinimumSize(minimumSize);
		splitPane.setLeftComponent(desktopPane);
		splitPane.setRightComponent(lowerPanel);

		setLayout(new BorderLayout());
		add(splitPane, BorderLayout.CENTER);
	}

	public void updateRecommendations() {
		List<ProteinSequence> queries = new ArrayList<ProteinSequence>();
		for (SequenceI seq : alignment.getSequencesArray()) {
			ProteinSequence ProteinSequence = new ProteinSequence();
			ProteinSequence.setAccessionNo(seq.getName());
			ProteinSequence.setSequence(seq.deriveSequence()
					.getDatasetSequence().getSequenceAsString());
			queries.add(ProteinSequence);
		}

		MSARecommender msaRecommender = (new MSARecommenderService())
				.getMSARecommenderPort();
		List<ProteinSequence> recommendations = msaRecommender
				.getRecommendedSequences(queries);
		status.setText(recommendations.size()
				+ " recommendations for the current alignment");

		DefaultTableModel model = new DefaultTableModel(new String[] { "Id",
				"Alignments", "Sequence", "Action" }, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				if (getColumnName(column).equals("Alignments")
						|| getColumnName(column).equals("Action")) {
					return true;
				} else {
					return false;
				}
			}
		};

		for (final ProteinSequence proteinSequence : recommendations) {
			Object[] values = new Object[4];
			values[0] = proteinSequence.getAccessionNo();
			values[1] = proteinSequence.getAlignments();
			values[2] = proteinSequence.getSequence();
			JButton button = new JButton("Add Sequence");
			button.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					Sequence jalSeq = new Sequence(proteinSequence
							.getAccessionNo(), proteinSequence.getSequence());
					alignment.addSequence(jalSeq);
					updateRecommendations();
					if (msaRecommenderCallback != null) {
						msaRecommenderCallback.sequenceAdded(proteinSequence);
					}
				}
			});
			values[3] = button;
			model.addRow(values);
		}
		table.setModel(model);
		table.getColumn("Alignments").setCellRenderer(
				new DefaultTableCellRenderer() {
					@SuppressWarnings("unchecked")
					@Override
					public Component getTableCellRendererComponent(
							JTable table, Object value, boolean isSelected,
							boolean hasFocus, int row, int column) {
						if (!(value instanceof List<?>)) {
							return super.getTableCellRendererComponent(table,
									value, isSelected, hasFocus, row, column);
						}
						return new AlignmentsPanel((List<Alignment>) value);
					}
				});
		table.getColumn("Alignments").setCellEditor(
				new AlignmentsEditor(new JCheckBox()));
		table.getColumn("Action").setCellRenderer(new TableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table,
					Object value, boolean isSelected, boolean hasFocus,
					int row, int column) {
				return (Component) value;
			}
		});
		table.getColumn("Action").setCellEditor(
				new ButtonEditor(new JCheckBox()));
		table.setRowHeight(60);
		repaint();
	}

	/**
	 * This method fulfills the contract of the {@link VisualPlugin} interface.
	 * It returns the GUI component for this visual plugin.
	 */
	@Override
	public Component getComponent() {
		// In this case, this object is also the GUI component.
		return this;
	}

	public void setAlignment(jalview.datamodel.AlignmentI alignment) {
		for (SequenceI jalSeq : this.alignment.getSequencesArray()) {
			this.alignment.deleteSequence(jalSeq);
		}
		this.alignment.append(alignment);
		if (alignment.getSequences().size() > 0) {
			ColourSchemeI cs = ColourSchemeProperty.getColour(alignment,
					"CLUSTAL");
			af.changeColour(cs);
		}
		updateRecommendations();
	}
	
	public static void main(String[] args) {
		SequenceAlignmentPanel panel = new SequenceAlignmentPanel();

		JFrame frame = new JFrame();
		frame.getContentPane().add(panel);
		frame.setSize(800, 800);
		frame.setTitle(panel.getName());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

		String afname = "test.aln";
		String protocol = "File";
		String format = new IdentifyFile().Identify(afname, protocol);
		FileLoader fileLoader = new FileLoader();
		AlignFrame af = fileLoader.LoadFileWaitTillLoaded(afname, protocol,
				format);
		AlignmentI testAlignment = af.getCurrentView().getAlignment();

		panel.setAlignment(testAlignment);
	}

}
