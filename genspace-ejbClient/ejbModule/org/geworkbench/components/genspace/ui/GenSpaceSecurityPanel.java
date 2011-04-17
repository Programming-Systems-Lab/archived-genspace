package org.geworkbench.components.genspace.ui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.geworkbench.components.genspace.GenSpace;
<<<<<<< HEAD
import org.geworkbench.components.genspace.LoginManager;
=======
import org.geworkbench.components.genspace.LoginFactory;
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7
import org.geworkbench.components.genspace.ui.chat.ChatWindow;
import org.geworkbench.engine.config.VisualPlugin;

public class GenSpaceSecurityPanel extends JPanel implements VisualPlugin,
		ActionListener {

<<<<<<< HEAD
=======
	/**
	 * 
	 */
	private static final long serialVersionUID = -9094613520505076713L;
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7
	private JButton logout;

	@Override
	public Component getComponent() {
		// TODO Auto-generated method stub
		return this;
	}

	public GenSpaceSecurityPanel(String uName) {
		JTabbedPane mainPanel = new JTabbedPane();
		DataVisibility dataPanel = new DataVisibility();
		mainPanel.addTab("Data Visibility", dataPanel);

		// NetworkVisibility nwPanel = new NetworkVisibility(uName);
		// mainPanel.addTab("User Visibility", nwPanel);

		GenSpaceGeneralProfile genPanel = new GenSpaceGeneralProfile();
		mainPanel.addTab("General Profile", genPanel);

		add(mainPanel);
		logout = new JButton("logout");
		add(logout);
		logout.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == logout) {
<<<<<<< HEAD
			GenSpaceLogin.chatHandler.logout();
			GenSpaceLogin.chatHandler.rf.setVisible(false);
			GenSpaceLogin.chatHandler.rf = null;
			GenSpace.getInstance().handleLogout();
=======
			
			GenSpace.getInstance().handleLogout();
			GenSpaceLogin.chatHandler.logout();
			GenSpaceLogin.chatHandler.rf.setVisible(false);
			GenSpaceLogin.chatHandler.rf = null;
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7
			for (ChatWindow w : GenSpaceLogin.chatHandler.chats.values()) {
				if (w != null) {
					w.setVisible(false);
					w = null;
				}
			}
			GenSpaceLogin.chatHandler.chats.clear();

<<<<<<< HEAD
			LoginManager.logout();
			GenSpaceLogin p = new GenSpaceLogin();
			GenSpace.getInstance().getWorkflowRepository().updateUser(null);
=======
			LoginFactory.logout();
			GenSpaceLogin p = new GenSpaceLogin();
			GenSpace.getInstance().getWorkflowRepository().updateUser();
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7

			GenSpace.networksPanels.updateFormFields();
			this.removeAll();
			this.add(p);
			this.setSize(500, 500);
			this.revalidate();
		}
	}

}
