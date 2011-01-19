package org.geworkbench.components.genspace.workflowRepository;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;

import org.geworkbench.components.genspace.RuntimeEnvironmentSettings;
import org.geworkbench.components.genspace.ServerRequest;
import org.geworkbench.components.genspace.bean.User;
import org.geworkbench.components.genspace.bean.UserSession;
import org.geworkbench.components.genspace.bean.Workflow;
import org.geworkbench.components.genspace.bean.WorkflowComment;
import org.geworkbench.engine.config.VisualPlugin;

public class WorkflowCommentsPanel extends JPanel implements VisualPlugin, ActionListener {
	
    public  JTable table;
	private WorkflowRepository workflowRepository;
	final public JButton newButton = new JButton("New");
	final public JButton removeButton  = new JButton("Remove");
	public Workflow workflow;
	
	public WorkflowCommentsPanel(WorkflowRepository wr){
		super(new BorderLayout());
        //setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "Workflow Comments"));
        workflowRepository = wr;
        
        table = new JTable(new MyTableModel()){
        	
        	public String getToolTipText(MouseEvent e) {	
                java.awt.Point p = e.getPoint();
                int rowIndex = rowAtPoint(p);
                int colIndex = columnAtPoint(p);
                int realColumnIndex = convertColumnIndexToModel(colIndex);
                Object tip = getValueAt(rowIndex, realColumnIndex);
                if(tip != null && tip instanceof String && !tip.equals(""))
                	return (String)tip;
                else return super.getToolTipText();
            }
        };
        
        table.setPreferredScrollableViewportSize(new Dimension(200, 70));
        table.setFillsViewportHeight(true);
        table.getSelectionModel().addListSelectionListener(new RowListener());
    	table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getColumnModel().getSelectionModel().addListSelectionListener(new ColumnListener());
        JScrollPane jsp = new JScrollPane(table);
        
        TableColumn columnUser = table.getColumnModel().getColumn(0);
        columnUser.setPreferredWidth(100);
        columnUser.setMaxWidth(100);
        TableColumn columnDate = table.getColumnModel().getColumn(1);
        columnDate.setPreferredWidth(100);
        columnDate.setMaxWidth(100);
        table.setAutoResizeMode(WIDTH);
        add(jsp, BorderLayout.CENTER);
        
        
        

        newButton.addActionListener(this);
        removeButton.addActionListener(this);
		JPanel buttonPanel = new JPanel(new GridLayout(0,2));
		buttonPanel.add(newButton);
		buttonPanel.add(removeButton);
		add(buttonPanel, BorderLayout.NORTH);
	}
	
    public void setData(Workflow workflow){
    	this.workflow = workflow;
    	MyTableModel model = (MyTableModel) table.getModel();
    	if(workflow != null)
    		model.setData(workflow.comments);
    	else clearData();
    }
    
    public void clearData(){
    	workflow = null;
    	MyTableModel model = (MyTableModel) table.getModel();
    	model.setData(new ArrayList<WorkflowComment>());
    }

	public Component getComponent() {
		return this;
	}

	public void actionPerformed(ActionEvent e) {
		final Object source = e.getSource();
		org.jdesktop.swingworker.SwingWorker<Void, Void> worker = new org.jdesktop.swingworker.SwingWorker<Void, Void>() {
	    	@Override
			public Void doInBackground() {
	    		String result = "";
	    		try{
	    			User u = UserSession.getInstance();
	    			if(u != null){
	    				MyTableModel model = (MyTableModel)table.getModel();
	    				if (source.equals(newButton)) {
		                	newComment(u, model);
		                }		
	    				else if (source.equals(removeButton)) {
		    				int i = table.getSelectedRow();
		    	        	if(i != -1){
		    	            	WorkflowComment wc = model.getWorkflowCommentAtRow(i);
		    	            	removeComment(u, wc, model);
			                }
		    	        	else result = "Select a comment to delete";
	    	        	}
	    			}
	    			else result = "Please login first.";
	    		}
	    		catch(Exception e){
	    			e.printStackTrace();
	    			result = e.getMessage();
	    		}
	    		if(result != null && !result.trim().equals(""))
	    			JOptionPane.showMessageDialog(null, result);
	    		return null;
	    	}

			private void removeComment(User u, WorkflowComment wc, MyTableModel model) throws Exception {
				if(!wc.username.equals(u.username)){
					throw new Exception("You can only delete your own comments.");
				}
				ArrayList<Object> params = new ArrayList<Object>();
				params.add(u.username);
				params.add(wc);
				params.add(workflow);
				
				String result = (String)ServerRequest.get(RuntimeEnvironmentSettings.WORKFLOW_REPOSITORY_SERVER, "remove_comment", params);
				if(result == null || result.equals("")){
					model.removeComment(wc);
				}
				else throw new Exception(result);
			}

			private void newComment(User u, MyTableModel model) throws Exception {
            	String comment = JOptionPane.showInputDialog("Input comment text");
            	if(comment != null && !comment.trim().equals("")){
            		WorkflowComment wc = new WorkflowComment();
            		wc.comment = comment;
            		wc.postedOn = new Date();
            		wc.username = u.username;
            		ArrayList<Object> params = new ArrayList<Object>();
					params.add(u.username);
					params.add(wc);
					params.add(workflow);
					Object result = ServerRequest.get(RuntimeEnvironmentSettings.WORKFLOW_REPOSITORY_SERVER, "new_comment", params);
					if(result instanceof Integer){
						wc.tableKey = (Integer)result;
						model.addComment(wc);
					}
					else throw new Exception(result.toString());
            	}
			}
			
			
		};
		worker.execute();
	}
	
	private class RowListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent event) {
        	int i = table.getSelectedRow();
        	if(i != -1){
        		//selected a row
        	}
            if (event.getValueIsAdjusting()) {
                return;
            }
        }
    }

    private class ColumnListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent event) {
            if (event.getValueIsAdjusting()) {
                return;
            }
        }
    }

    class MyTableModel extends AbstractTableModel {
        private String[] columnNames = {"User",
                                        "Date",
        								"Comment"};
        public ArrayList<WorkflowComment> data;
        
        public MyTableModel(){
        	super();
        	data = new ArrayList<WorkflowComment>();
        }
        
        public MyTableModel(ArrayList<WorkflowComment> wc){
        	data = wc;
        }
        
        public void setData(ArrayList<WorkflowComment> list){
        	data = list;
        	this.fireTableDataChanged();
        }
        
        public void addComment(WorkflowComment wc){
        	int index = data.size();
        	data.add(wc);
        	this.fireTableRowsInserted(index, index);
        }
        
        public void removeComment(WorkflowComment wc){
        	for(int i = 0; i < data.size(); i++){
        		WorkflowComment w = data.get(i);
        		if(w.username.equals(wc.username) && w.postedOn.equals(wc.postedOn) && w.comment.equals(wc.comment)){
        			data.remove(i);
        			this.fireTableRowsDeleted(i, i);
        			break;
        		}
        	}
        }
        
        public int getColumnCount() {
            return columnNames.length;
        }

        public int getRowCount() {
            return data.size();
        }

        @Override
		public String getColumnName(int col) {
            return columnNames[col];
        }

        public Object getValueAt(int row, int col) {
        	if(row >= 0 && row < data.size()){
        		WorkflowComment wi = data.get(row);
            	if(col == 0)
            		return wi.username;
            	else if(col == 1)
            		return wi.postedOn;
            	else
            		return wi.comment;
        	}
        	return null;
        }
        
        public WorkflowComment getWorkflowCommentAtRow(int row){
        	if(row < data.size())
        		return data.get(row);
        	else return null;
        }

        /*
         * JTable uses this method to determine the default renderer/
         * editor for each cell.  If we didn't implement this method,
         * then the last column would contain text ("true"/"false"),
         * rather than a check box.
         */
        @Override
		public Class getColumnClass(int c) {
        	if(data.size() > 0)
        		return getValueAt(0, c).getClass();
        	else return WorkflowComment.class;
        }

        /*
         * Don't need to implement this method unless your table's
         * editable.
         */
        @Override
		public boolean isCellEditable(int row, int col) {
            //Note that the data/cell address is constant,
            //no matter where the cell appears onscreen.
        	return false;
        }

        /*
         * Don't need to implement this method unless your table's
         * data can change.
         */
        @Override
		public void setValueAt(Object value, int row, int col) {
        	//not editable
        }

    }

}