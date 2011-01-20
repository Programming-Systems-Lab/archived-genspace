package org.geworkbench.components.genspace.workflowRepository;

/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
/*
 * TableSelectionDemo.java requires no other files.
 */

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import org.geworkbench.components.genspace.RuntimeEnvironmentSettings;
import org.geworkbench.components.genspace.ServerRequest;
import org.geworkbench.components.genspace.bean.User;
import org.geworkbench.components.genspace.bean.UserSession;
import org.geworkbench.components.genspace.bean.UserWorkflow;
import org.geworkbench.components.genspace.bean.WorkflowInbox;
import org.geworkbench.engine.config.VisualPlugin;

public class InboxTablePanel extends JPanel implements ActionListener,
		VisualPlugin {
	public JTable table;
	public WorkflowRepository workflowRepository;
	final public JButton addButton = new JButton("Add");
	final public JButton deleteButton = new JButton("Delete");

	public InboxTablePanel(WorkflowRepository wr) {
		super(new BorderLayout());
		// setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEmptyBorder(), "Workflow Inbox"));
		workflowRepository = wr;

		table = new JTable(new MyTableModel()) {

			@Override
			public String getToolTipText(MouseEvent e) {
				java.awt.Point p = e.getPoint();
				int rowIndex = rowAtPoint(p);
				int colIndex = columnAtPoint(p);
				int realColumnIndex = convertColumnIndexToModel(colIndex);
				Object tip = getValueAt(rowIndex, realColumnIndex);
				if (tip != null && tip instanceof String && !tip.equals(""))
					return (String) tip;
				else
					return super.getToolTipText();
			}
		};

		table.setPreferredScrollableViewportSize(new Dimension(200, 70));
		table.setFillsViewportHeight(true);
		table.getSelectionModel().addListSelectionListener(new RowListener());
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getColumnModel().getSelectionModel()
				.addListSelectionListener(new ColumnListener());
		add(new JScrollPane(table), BorderLayout.CENTER);

		addButton.addActionListener(this);
		deleteButton.addActionListener(this);
		JPanel buttonPanel = new JPanel(new GridLayout(0, 2));
		buttonPanel.add(addButton);
		buttonPanel.add(deleteButton);
		add(buttonPanel, BorderLayout.SOUTH);
	}

	@Override
	public Component getComponent() {
		return this;
	}

	public void setData(User u) {
		MyTableModel model = (MyTableModel) table.getModel();
		if (u != null)
			model.setData(u.inbox);
		else
			clearData();

	}

	public void clearData() {
		MyTableModel model = (MyTableModel) table.getModel();
		model.setData(new ArrayList<WorkflowInbox>());
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		final Object source = event.getSource();
		org.jdesktop.swingworker.SwingWorker<Void, Void> worker = new org.jdesktop.swingworker.SwingWorker<Void, Void>() {
			@Override
			public Void doInBackground() {
				try {
					User u = UserSession.getInstance();
					if (u != null) {
						int i = table.getSelectedRow();
						if (i != -1) {
							MyTableModel model = (MyTableModel) table
									.getModel();
							WorkflowInbox wi = model.getWorkflowAtRow(i);
							if (source.equals(addButton)) {
								addToRepository(u, wi, model);
							} else if (source.equals(deleteButton)) {
								removeFromInbox(u, wi, model);
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}

			private String removeFromInbox(User u, WorkflowInbox wi,
					MyTableModel model) {
				ArrayList<Object> params = new ArrayList<Object>();
				params.add(u.username);
				params.add(wi);
				String result = (String) ServerRequest.get(
						RuntimeEnvironmentSettings.WORKFLOW_REPOSITORY_SERVER,
						"delete_inbox", params);
				if (result == null || result.equals("")) {
					model.delWorkflowInbox(wi);
					return "";
				} else
					return result;
			}

			private String addToRepository(User u, WorkflowInbox wi,
					MyTableModel model) {
				ArrayList<Object> params = new ArrayList<Object>();
				params.add(u.username);
				params.add(wi);
				Object result = ServerRequest.get(
						RuntimeEnvironmentSettings.WORKFLOW_REPOSITORY_SERVER,
						"add_to_repository_from_inbox", params);
				if (result == null || result instanceof String) {
					// error
					return (String) result;
				} else {
					// Add
					UserWorkflow uw = (UserWorkflow) result;
					u.workflows.add(uw);
					workflowRepository.repositoryPanel.tree
							.recalculateAndReload();
					// delete
					result = removeFromInbox(u, wi, model);
					return (String) result;
				}
			}

		};
		worker.execute();
	}

	private class RowListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent event) {
			int i = table.getSelectedRow();
			if (i != -1) {
				MyTableModel model = (MyTableModel) table.getModel();
				WorkflowInbox wi = model.getWorkflowAtRow(i);
				workflowRepository.graphPanel.setAndPaintWorkflow(wi.workflow);
				workflowRepository.workflowDetailsPanel
						.setAndPrintWorkflow(wi.workflow);
				workflowRepository.workflowCommentsPanel.setData(wi.workflow);
			}
			if (event.getValueIsAdjusting()) {
				return;
			}
		}
	}

	private class ColumnListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent event) {
			if (event.getValueIsAdjusting()) {
				return;
			}
		}
	}

	class MyTableModel extends AbstractTableModel {
		private String[] columnNames = { "Name", "User", "Date" };
		public ArrayList<WorkflowInbox> data;

		public MyTableModel() {
			super();
			data = new ArrayList<WorkflowInbox>();
		}

		public MyTableModel(ArrayList<WorkflowInbox> ws) {
			data = ws;
		}

		public void setData(ArrayList<WorkflowInbox> list) {
			data = list;
			this.fireTableDataChanged();
		}

		public void addWorkflowInbox(WorkflowInbox wi) {
			int index = data.size();
			data.add(wi);
			this.fireTableRowsInserted(index, index);
		}

		public void delWorkflowInbox(WorkflowInbox wi) {
			for (int i = 0; i < data.size(); i++) {
				WorkflowInbox w = data.get(i);
				if (w.name.equals(wi.name) && w.sender.equals(wi.sender)
						&& w.date.equals(wi.date)) {
					data.remove(i);
					this.fireTableRowsDeleted(i, i);
					break;
				}
			}
		}

		@Override
		public int getColumnCount() {
			return columnNames.length;
		}

		@Override
		public int getRowCount() {
			return data.size();
		}

		@Override
		public String getColumnName(int col) {
			return columnNames[col];
		}

		@Override
		public Object getValueAt(int row, int col) {
			if (row >= 0 && row < data.size()) {
				WorkflowInbox wi = data.get(row);
				if (col == 0)
					return wi.name;
				else if (col == 1)
					return wi.sender;
				else
					return wi.date.toString();
			}
			return null;
		}

		public WorkflowInbox getWorkflowAtRow(int row) {
			if (row < data.size())
				return data.get(row);
			else
				return null;
		}

		/*
		 * JTable uses this method to determine the default renderer/ editor for
		 * each cell. If we didn't implement this method, then the last column
		 * would contain text ("true"/"false"), rather than a check box.
		 */
		@Override
		public Class getColumnClass(int c) {
			return getValueAt(0, c).getClass();
		}

		/*
		 * Don't need to implement this method unless your table's editable.
		 */
		@Override
		public boolean isCellEditable(int row, int col) {
			// Note that the data/cell address is constant,
			// no matter where the cell appears onscreen.
			return false;
		}

		/*
		 * Don't need to implement this method unless your table's data can
		 * change.
		 */
		@Override
		public void setValueAt(Object value, int row, int col) {
			// not editable
		}

	}

	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event-dispatching thread.
	 */
	private static void createAndShowGUI() {
		// Disable boldface controls.
		UIManager.put("swing.boldMetal", Boolean.FALSE);

		// Create and set up the window.
		JFrame frame = new JFrame("TableSelectionDemo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create and set up the content pane.
		InboxTablePanel newContentPane = new InboxTablePanel(null);
		newContentPane.setOpaque(true); // content panes must be opaque
		frame.setContentPane(newContentPane);

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				createAndShowGUI();
			}
		});
	}
}
