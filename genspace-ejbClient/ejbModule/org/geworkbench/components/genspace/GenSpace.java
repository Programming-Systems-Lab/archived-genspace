package org.geworkbench.components.genspace;

import java.awt.Dimension;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.geworkbench.components.genspace.entity.Tool;
import org.geworkbench.components.genspace.entity.User;
import org.geworkbench.components.genspace.ui.SocialNetworksHome;
import org.geworkbench.components.genspace.ui.WorkflowStatistics;
import org.geworkbench.components.genspace.workflowRepository.WorkflowRepository;

import com.sun.corba.ee.spi.orbutil.logex.Log;
import com.sun.enterprise.v3.server.CommonClassLoaderServiceImpl;
import com.sun.logging.LogDomains;

/**
 * This is the main class for genspace. This is a visual plugin and will be a
 * tabbed pane. All other genspace components will be part of the tabbed pane.
 * 
 * @author sheths
 */
public class GenSpace {

	private JTabbedPane jtp;
	public JFrame jframe;
	private static GenSpace instance;
	private WorkflowRepository workflowRepository;
	public static Logger logger = Logger.getLogger(GenSpace.class);
	private static InitialContext ctx;
	
	public static Object getRemote(String remoteName)
	{
		if(Thread.currentThread().getName().contains("AWT-EventQueue"))
		{
			System.err.println(">>>>>>>>>"+Thread.currentThread().getName());
			throw new IllegalThreadStateException("You may not attempt to access the remote server from an AWT/Swing worker thread");
		}	
		try {
			System.setProperty("org.omg.CORBA.ORBInitialHost", RuntimeEnvironmentSettings.SERVER);
			if(ctx == null)
			{
				ctx = new InitialContext();
			}
			return ctx.lookup("org.geworkbench.components.genspace.server."+remoteName+"Remote");
		} catch (NamingException e) {
			logger.error("Unable find remote object for " + remoteName,e);
		
		}
		return null;
	}
	
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		GenSpace g = new GenSpace();
	}

	public static SocialNetworksHome networksPanels = new SocialNetworksHome();

	public GenSpace() {
		LogDomains.getLogger(CommonClassLoaderServiceImpl.class,LogDomains.LOADER_LOGGER).setLevel(java.util.logging.Level.OFF);
		instance = this;
		initComponents();
	}
	public static void bringUpProfile(User u)
	{
		getInstance().jtp.setSelectedComponent(networksPanels.$$$getRootComponent$$$());
		networksPanels.bringUpProfile(u);
	}
	public static GenSpace getInstance() {
		return instance;
	}

	public WorkflowRepository getWorkflowRepository() {
		return workflowRepository;
	}
	JPanel needLoginPanel;
	public void handleLogin()
	{
		jtp.setComponentAt(5, workflowRepository);
	}
	public void handleLogout()
	{
		jtp.setComponentAt(5, needLoginPanel);
	}
	private void initComponents() {
		jframe = new JFrame("genSpace");

		jtp = new JTabbedPane();
		jtp.setSize(1024,768);
		jtp.setPreferredSize(new Dimension(1024,768));
		WorkflowVisualization wv = new WorkflowVisualization();

		WorkflowStatistics stats = new WorkflowStatistics();

		RealTimeWorkFlowSuggestion rtwfs = new RealTimeWorkFlowSuggestion();

		workflowRepository = new WorkflowRepository(jframe);
		needLoginPanel = new JPanel();
		needLoginPanel.add(new JLabel("Please login to genSpace to access this area."));
		org.geworkbench.components.genspace.ui.GenSpaceLogin login = new org.geworkbench.components.genspace.ui.GenSpaceLogin();
		// login.run();

		jtp.addTab("genSpace Login", login);
		jtp.addTab("Workflow Visualization", wv);
		jtp.addTab("Real Time Workflow Suggestion", rtwfs);
		jtp.addTab("Workflow Statistics", stats);
		jtp.addTab("Social Center", networksPanels.$$$getRootComponent$$$());
		jtp.addTab("Workflow Repository", needLoginPanel);
		// jtp.addTab("Message", new Message());
		jframe.setSize(1024,768);
		jframe.add(jtp);

		
		// Added by Flavio
		jframe.pack();
//		jframe.setExtendedState(Frame.MAXIMIZED_BOTH);
		jframe.setVisible(true);

		// Moved here by Flavio
		Thread wv_thread = new Thread(wv);
		wv_thread.start();

		Thread rtwfs_thread = new Thread(rtwfs);
		rtwfs_thread.start();

		Thread wfr_thread = new Thread(workflowRepository);
		wfr_thread.start();
		/*
		 * System.out.println("wv: " + wv_thread.getId());
		 * System.out.println("isbu: " + isbu_thread.getId());
		 * System.out.println("rtwfs: " + rtwfs_thread.getId());
		 * System.out.println("login: " + login_thread.getId());
		 */
	}
}
