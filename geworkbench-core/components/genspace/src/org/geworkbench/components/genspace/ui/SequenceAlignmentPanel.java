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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import org.geworkbench.components.genspace.server.stubs.Alignment;
import org.geworkbench.components.genspace.server.stubs.DnaSequence;
import org.geworkbench.components.genspace.server.stubs.MSARecommender;
import org.geworkbench.components.genspace.server.stubs.MSARecommenderService;
import org.geworkbench.engine.config.VisualPlugin;

@SuppressWarnings("serial")
public class SequenceAlignmentPanel extends JPanel implements VisualPlugin,
		Runnable {

	private static final class AlignmentsCell extends
			JPanel {

		public AlignmentsCell(List<Alignment> alignments) {
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			
			for (Alignment alignment : alignments) {
				final JButton label = new JButton(alignment.getEmblId());
				label.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						JOptionPane.showMessageDialog(label, "Hello");
					}
				});
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

	/**
	 * Constructor
	 */
	public SequenceAlignmentPanel() {
		run();
	}

	@Override
	public void run() {

		String afname = "test.aln";
		String color = "CLUSTAL";
		String protocol = "File";
		String format = new IdentifyFile().Identify(afname, protocol);
		FileLoader fileLoader = new FileLoader();
		AlignFrame af = fileLoader.LoadFileWaitTillLoaded(afname, protocol,
				format);
		alignment = af.getCurrentView().getAlignment();
		ColourSchemeI cs = ColourSchemeProperty.getColour(af.getViewport()
				.getAlignment(), color);
		af.changeColour(cs);
		af.toFront();
		af.setVisible(true);
		af.setClosable(true);
		af.setResizable(true);
		af.setMaximizable(true);
		af.setIconifiable(true);
		af.setFrameIcon(null);
		af.setPreferredSize(new Dimension(800, 400));

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
		splitPane.setLeftComponent(desktopPane);
		splitPane.setRightComponent(lowerPanel);

		setLayout(new BorderLayout());
		add(splitPane, BorderLayout.CENTER);
	}

	public void updateRecommendations() {
		List<DnaSequence> queries = new ArrayList<DnaSequence>();
		for (SequenceI seq : alignment.getSequencesArray()) {
			DnaSequence dnaSequence = new DnaSequence();
			dnaSequence.setAccessionNo(seq.getName());
			dnaSequence.setSequence(seq.deriveSequence().getDatasetSequence()
					.getSequenceAsString());
			queries.add(dnaSequence);
		}

		MSARecommender msaRecommender = (new MSARecommenderService())
				.getMSARecommenderPort();
		List<DnaSequence> recommendations = msaRecommender
				.getRecommendedSequences(queries);
		status.setText(recommendations.size()
				+ " recommendations for the current alignment");

		DefaultTableModel model = new DefaultTableModel(new String[] { "Id",
				"Alignments", "Sequence", "Action" }, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				if (getColumnName(column).equals("Action")) {
					return true;
				} else {
					return false;
				}
			}
		};

		for (final DnaSequence dnaSequence : recommendations) {
			Object[] values = new Object[4];
			values[0] = dnaSequence.getAccessionNo();
			values[1] = new AlignmentsCell(dnaSequence.getAlignments());
			values[2] = dnaSequence.getSequence();
			JButton button = new JButton("Add Sequence");
			button.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					Sequence jalSeq = new Sequence(
							dnaSequence.getAccessionNo(), dnaSequence
									.getSequence());
					alignment.addSequence(jalSeq);
					updateRecommendations();
				}
			});
			values[3] = button;
			model.addRow(values);
		}
		table.setModel(model);
		table.getColumn("Alignments").setCellRenderer(new TableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table,
					Object value, boolean isSelected, boolean hasFocus,
					int row, int column) {
				return (Component) value;
			}
		});
		table.getColumn("Alignments").setCellEditor(new ButtonEditor(new JCheckBox()));
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

	public static void main(String[] args) {
		SequenceAlignmentPanel panel = new SequenceAlignmentPanel();

		JFrame frame = new JFrame();
		frame.getContentPane().add(panel);
		frame.setSize(800, 800);
		frame.setTitle(panel.getName());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

		panel.updateRecommendations();
	}
}
