package org.geworkbench.components.genspace;

import javax.swing.*;

import org.geworkbench.components.genspace.workflowRepository.WorkflowRepository;
import java.awt.*;

/**
 * This is the main class for genspace. 
 * This is a visual plugin and will be a tabbed pane. All other genspace components will be part of the tabbed pane. 
 * @author sheths
 */
public class GenSpace {

	private JTabbedPane jtp;
	public JFrame jframe;
	private static GenSpace instance;
	private WorkflowRepository workflowRepository;
	 
	
	public GenSpace()
	{	
		instance = this;
		initComponents();
	}
	
	public static GenSpace getInstance(){
		return instance;
	}
	
	public WorkflowRepository getWorkflowRepository(){
		return workflowRepository;
	}

	
	private void initComponents() {
		jframe = new JFrame("genSpace");

		
		jtp = new JTabbedPane();
		
		WorkflowVisualization wv = new WorkflowVisualization();

		
		ISBUWorkFlowVisualization isbu= new ISBUWorkFlowVisualization();

		
		RealTimeWorkFlowSuggestion rtwfs = new RealTimeWorkFlowSuggestion();

		
		workflowRepository = new WorkflowRepository(jframe);

		
		
		
		
		org.geworkbench.components.genspace.ui.GenSpaceLogin login = new org.geworkbench.components.genspace.ui.GenSpaceLogin();
		//login.run();
		
		
		jtp.addTab("genSpace Login", login);
		jtp.addTab("Workflow Visualization", wv);
		jtp.addTab("Real Time Workflow Suggestion", rtwfs);
		jtp.addTab("Workflow Statistics", isbu);
		jtp.addTab("Workflow Repository", workflowRepository);
		//jtp.addTab("Message", new Message());		
		
		jframe.add(jtp);
		
		//jframe.setSize(800, 600);
		//Added by Flavio
		jframe.pack();
		jframe.setExtendedState(Frame.MAXIMIZED_BOTH);
		jframe.setVisible(true);
		
		
		//Moved here by Flavio
		Thread wv_thread = new Thread(wv);
		wv_thread.start();
		
		Thread isbu_thread = new Thread(isbu);
		isbu_thread.start();
		
		Thread rtwfs_thread = new Thread(rtwfs);
		rtwfs_thread.start();
		
		Thread wfr_thread = new Thread(workflowRepository);
		wfr_thread.start();
		
		
		/*
		System.out.println("wv: " + wv_thread.getId());
		System.out.println("isbu: " + isbu_thread.getId());
		System.out.println("rtwfs: " + rtwfs_thread.getId());
		System.out.println("login: " + login_thread.getId());
		*/
    }
}
