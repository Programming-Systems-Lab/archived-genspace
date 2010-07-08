package org.geworkbench.components.genspace;

import javax.swing.*;
import javax.swing.border.MatteBorder;

import org.apache.ojb.jdo.jdoql.ThisExpression;
import org.geworkbench.components.genspace.rating.WorkflowVisualizationPopup;
import org.geworkbench.components.genspace.ui.LoginManager;
import org.geworkbench.engine.config.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import org.geworkbench.engine.properties.PropertiesManager;


import java.io.*;
import java.net.*;
import java.util.*;



public class RealTimeWorkFlowSuggestion extends JPanel implements VisualPlugin{

	JRadioButton log, logAnon, noLog;
	ButtonGroup group;
	JPanel radioPanel, saveReset;
	JButton save, reset;
	private static ImageIcon arrow = new ImageIcon("arrow_right.png");
	
	
	//###
	private static String clientSideID = null;
	
	private static ArrayList <TransactionElement> currentWorkFlow = new ArrayList();
	private static String currentTid = null;
	
	private static boolean firstTimeFlag = true;//used for checking the beginning of new transactions
	private static ArrayList usedWorkFlowToday = new ArrayList();
	
	
	private static JPanel workflowViewerPanel = new JPanel();
	private static JPanel workflowNodePanel = new JPanel(new FlowLayout());
	private static JScrollPane workflowViewerScrollPane = new JScrollPane(workflowViewerPanel);
	private static JPanel workflowInfoPanel = new JPanel(new BorderLayout());
	
	private static WorkflowVisualizationPopup popup = new WorkflowVisualizationPopup();

	/**
	 * Components for the viewer panel
	 */
	
	private static JLabel viewerStatus = new JLabel();
	
	/**
	 * Components for the info panel
	 */
	private static JTextArea infoArea = new JTextArea();
	
	static final String PROPERTY_KEY = "genSpace_logging_preferences"; // the key in the properties file
	int preference; // the logging preference
	
	public RealTimeWorkFlowSuggestion() {	
		initComponents();
		
	}

	
	private void initComponents()
	{	  
        try {
        	
        	/*
        	
        	labelNC = new JLabel("Analysis Tools Suggestion Center:");
        	
        	labelNC1 = new JLabel("The genSpace server currently has the information for the following tools:");
        	
        	ArrayList allAnalysisTools = getAllAnalysisTools();
        	labelNC2 = new JLabel(allAnalysisTools.toString());
        	
        	labelNC3 = new JLabel("Top 3 most popular tools:");
        	ArrayList top3MostPopularTools =  getTop3MostPopularTools();
            labelNC4 = new JLabel(top3MostPopularTools.get(0) + " " + top3MostPopularTools.get(1) + " " + top3MostPopularTools.get(2));
        	
            
            labelNC5 = new JLabel("Top 3 most popular tools as work flow head:");
            ArrayList top3MostPopularWFHead = getTop3MostPopularWFHead(); 
            labelNC6 = new JLabel(top3MostPopularWFHead.get(0) + " " + top3MostPopularWFHead.get(1) + " " + top3MostPopularWFHead.get(2));
            
            labelNC7 = new JLabel("Top 3 most popular work flow :");
            ArrayList top3MostPopularWF = getTop3MostPopularWF();
            labelNC8 = new JLabel(top3MostPopularWF.get(0) + " " + top3MostPopularWF.get(1) + " " + top3MostPopularWF.get(2));
            
        	*/
        	
        	//ISBUPanel = new JPanel(new GridLayout(3,1));
            
        }
        catch (Exception e) {
        	e.printStackTrace();
        }
        
    /**
     * setup general layout of panel 
     * */
        
        //the viewer panel
        workflowViewerPanel.setLayout(new BorderLayout());
        workflowViewerPanel.setBorder(new MatteBorder(10,10,10,10, new Color(35, 35, 142)));
        workflowViewerPanel.setBackground( new Color(35, 35, 142));
        
        
        //setup viewer status
        viewerStatus.setForeground(Color.WHITE);
        viewerStatus.setText("No analysis has occured yet.");
        workflowViewerPanel.add(viewerStatus, BorderLayout.NORTH);
        workflowNodePanel.setBackground( new Color(35, 35, 142));
        workflowNodePanel.setBorder(new MatteBorder(10,10,10,10, new Color(35, 35, 142)));
        workflowViewerPanel.add(workflowNodePanel, BorderLayout.CENTER);
        
        
        //the info panel
        workflowInfoPanel.add(new JScrollPane(infoArea));
        infoArea.setFont(new Font( "Verdana", Font.PLAIN, 10 ));
		infoArea.append("You haven't use any tools!\n");
		infoArea.append("Next best rated tool to use: none.");
		
        
		//add both panels
		this.setLayout(new BorderLayout());
		JSplitPane splitter = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		splitter.setDividerLocation(100);
		splitter.setResizeWeight(0.5);

		JScrollPane scroller = new JScrollPane(workflowViewerPanel);
		scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        splitter.add(scroller);
        
        splitter.add(workflowInfoPanel);
        add(splitter, BorderLayout.CENTER);
        
        
      
	}
	
	public static void displayCWF() {
		
		//---------------
		String cwfSeparate = "";//to store the details of the current user transaction
		for (int i = 0; i < currentWorkFlow.size(); i ++) {
			cwfSeparate = cwfSeparate + "\t\t" +
										((TransactionElement) currentWorkFlow.get(i)).getHour() + 
			                            ":" + 
			                            ((TransactionElement) currentWorkFlow.get(i)).getMinute() + 
			                            ":" +
			                            ((TransactionElement) currentWorkFlow.get(i)).getSecond() + 
			                            " " + 
			                            ((TransactionElement) currentWorkFlow.get(i)).getToolName() + 
			                            "\n";
			                            
		}
		
		//-----------------
		String finishedWF = "";
		for (int i = 0; i < usedWorkFlowToday.size(); i ++) {
			ArrayList tempWF = (ArrayList)usedWorkFlowToday.get(i);
			
			for (int j = 0; j < tempWF.size(); j ++) {
				finishedWF = finishedWF +  tempWF.get(j) +  "->";
			}									  
		}
		
		infoArea.setText("");
		infoArea.append("Your current workflow activity so far: \n" + cwfSeparate + "\n\n");
		infoArea.append(finishedWF + "\n\n\n");
		
		ArrayList <String> currentWorkflowTools = new ArrayList();
		for (TransactionElement t : currentWorkFlow){
			currentWorkflowTools.add(t.getToolName());
		}
		
		ArrayList arguments = new ArrayList();
		arguments.add(currentWorkflowTools);
		arguments.add(LoginManager.getLoggedInUser());
		infoArea.append("Next best rated tool to use: " +
				ServerRequest.get(RuntimeEnvironmentSettings.ISBU_SERVER, "nextBestRatedTool", arguments) + 
				".\n\n");
	}
	
	
	
	
	
	
	
	public static void updateCWFStatus(int hour, int minute, int second, String toolName, String transactionID) {
		
		viewerStatus.setText("Recently used " + toolName);
		//first we check that whether we have started a new transaction (a new work flow)
		if (firstTimeFlag == true) { //we are about to begin the first transaction today, now currentTid is still "null", rather than an earlier value
			currentTid = transactionID;
			TransactionElement element = new TransactionElement(hour, minute, second, toolName);
			
			
			currentWorkFlow.add(element);
			
			
			
			
			displayCWF();
			firstTimeFlag = false;
		}
		else {//if this is now the first time...
			
			//we will compare the incoming id with the "old" one
			
			if (transactionID.equals(currentTid)) { //we are still in the same transaction, just go ahead
				
				TransactionElement element = new TransactionElement(hour, minute, second, toolName);
				
				currentWorkFlow.add(element);
				workflowNodePanel.add(new JLabel(arrow));
				
				displayCWF();
				
			}
			else { //if we are starting a new transaction (wf)
				
				//save the current WF into historical record
				usedWorkFlowToday.add(currentWorkFlow); 
				
				//and then empty it
				currentWorkFlow.clear();
				workflowNodePanel.removeAll();
				
				//and then add in new work flow node
				
				TransactionElement element = new TransactionElement(hour, minute, second, toolName);
				
				currentWorkFlow.add(element);
				
				displayCWF();
			}
			
			
		}
		
		
		WorkflowViewerPanelNode newNode 
		= new WorkflowViewerPanelNode(toolName, currentWorkFlow.size() - 1);

		newNode.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent event) {
				popup.showToolOptions();
				popup.showToolRating();
				popup.showWorkflowOptions();
				popup.showWorkflowRating();
				
				WorkflowViewerPanelNode node = (WorkflowViewerPanelNode)event.getSource();
				
				ArrayList<String> workflow = new ArrayList();
				for (int i = 0; i <= node.getIndex(); i++)
					workflow.add(currentWorkFlow.get(i).getToolName());
				
				popup.initialize(node.getText(), workflow);
				popup.show(node, event.getX(), event.getY());
				
			}

			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {	}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			
		});
		workflowNodePanel.add(newNode);
		
		
		
	}
	
	
	

	
	
	
	
	/*
	
	
    public void actionPerformed(ActionEvent e) 
    {
    	if (e.getSource() == save) {
    		//System.out.println("Save pressed with " + group.getSelection().getActionCommand());
    		preference = Integer.parseInt(group.getSelection().getActionCommand());
    		ObjectHandler.setLogStatus(preference);
    		save.setEnabled(false);
    		// write it to the properties file
    		try
    		{
    			PropertiesManager properties = PropertiesManager.getInstance();
    			properties.setProperty(GenSpaceLogPreferences.class, PROPERTY_KEY, group.getSelection().getActionCommand());
    		}
    		catch (Exception ex) { }
    		
    	}
    	else if (e.getSource() == reset) {
    		//System.out.println("Reset pressed");
    		logAnon.setSelected(true);
    		save.setEnabled(true);
    	}
    	else if (e.getSource() == log) {
    		if (preference == 0) {
    			save.setEnabled(false);
    		}
    		else {
    			save.setEnabled(true);
    		}
    	}
    	else if (e.getSource() == logAnon) {
    		if (preference == 1) {
    			save.setEnabled(false);
    		}
    		else {
    			save.setEnabled(true);
    		}
    	}
    	else if (e.getSource() == noLog) {
    		if (preference == 2) {
    			save.setEnabled(false);
    		}
    		else {
    			save.setEnabled(true);
    		}
    	}
    }
    
    */
    
	
    
    public Component getComponent() {
        // In this case, this object is also the GUI component.
        return this;
    }
    
    public static void main(String args[]){
    	JFrame theFrame = new JFrame();
    	theFrame.setTitle("Real-time workflow evaluation plugin tester");
    	RealTimeWorkFlowSuggestion plugin = new RealTimeWorkFlowSuggestion();
    	theFrame.add(plugin);
    	plugin.setVisible(true);
    
    	theFrame.setSize(700, 300);
    	theFrame.setVisible(true);
    	
    	plugin.updateCWFStatus(10, 20, 30, "ARACNE", "ABCDEFG");
    	plugin.updateCWFStatus(10, 20, 30, "ARACNE", "ABCDEFG");
    	//plugin.updateCWFStatus(10, 20, 30, "T Test Analysis", "ABCDEFG");
    }

}

class WorkflowViewerPanelNode extends JButton {
	
	private int index;
	
	public WorkflowViewerPanelNode(String title, int index){
		super(title);
		this.putClientProperty("is3DEnabled", Boolean.FALSE);
		this.index = index;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
}
