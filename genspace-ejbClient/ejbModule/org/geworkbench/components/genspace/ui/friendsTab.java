package org.geworkbench.components.genspace.ui;

import javax.swing.*;

<<<<<<< HEAD
import org.geworkbench.components.genspace.LoginManager;
=======
import org.geworkbench.components.genspace.GenSpace;
import org.geworkbench.components.genspace.LoginFactory;
import org.geworkbench.components.genspace.entity.Network;
import org.geworkbench.components.genspace.entity.User;
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
<<<<<<< HEAD
=======
import java.util.Collections;
import java.util.Comparator;
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by IntelliJ IDEA. User: jon Date: Aug 28, 2010 Time: 12:20:54 PM To
 * change this template use File | Settings | File Templates.
 */
public class friendsTab extends SocialTab {
<<<<<<< HEAD
	private JList pendingRequestsList;
	private JButton acceptButton;
	private JButton rejectButton;
	private JList myFriendsList;
	private String network = null;
=======
//	private JList pendingRequestsList;
//	private JButton acceptButton;
//	private JButton rejectButton;
	private JList myFriendsList;
	private Network networkFilter = null;
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7

	public friendsTab() {

	}

<<<<<<< HEAD
	public friendsTab(String filteredNetwork, SocialNetworksHome parentFrame) {
		network = filteredNetwork;
=======
	public friendsTab(Network filteredNetwork, SocialNetworksHome parentFrame) {
		networkFilter = filteredNetwork;
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7
		this.parentFrame = parentFrame;
	}

	public void initComponents() {
		myFriendsList.setCellRenderer(new ListCellRenderer() {

			@Override
			public Component getListCellRendererComponent(JList list,
					Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				JPanel pan = new JPanel(); 
<<<<<<< HEAD
				//TODO
//				Friend t;
//				Profile p;
//				boolean visible;
//				if (network == null) {
//					t = (Friend) value;
//					p = t.profile;
//					visible = t.details.equals("YES");
//				} else {
//					p = (Profile) value;
//					visible = p.details.equals("YES");
//				}
//				pan.setLayout(new BoxLayout(pan, BoxLayout.Y_AXIS));
//
//				JLabel label = new JLabel(p.profile.get("first_name")
//						+ " " + p.profile.get("last_name"));
//				Font f = new Font(label.getFont().getName(), Font.BOLD, 18);
//				label.setFont(f);
//				label.setForeground(new Color(-16777012));
//
//				pan.add(label);
//				String byline = "";
//				if (p.profile.get("work_title") != "") {
//					byline += p.profile.get("work_title") + " ";
//					if (p.profile.get("lab_affiliation") != "")
//						byline += "at ";
//				}
//				if (p.profile.get("lab_affiliation") != "") {
//					byline += p.profile.get("lab_affiliation") + " ";
//				}
//				if (p.profile.get("city") != "") {
//					byline += p.profile.get("city");
//					if (p.profile.get("state") != "")
//						byline += ", ";
//					else
//						byline += " ";
//
//				}
//				if (p.profile.get("state") != "")
//					byline += p.profile.get("state");
//				JLabel label2 = new JLabel(byline);
//				if (visible)
//					pan.add(label2);
//				pan.add(new JSeparator(SwingConstants.HORIZONTAL));
//				if (isSelected)
//					pan.setBackground(new Color(251, 251, 228));
=======
				
				User u = (User) value;
				pan.setLayout(new BoxLayout(pan, BoxLayout.Y_AXIS));

				JLabel label = new JLabel(u.getFullName());
				Font f = new Font(label.getFont().getName(), Font.BOLD, 18);
				label.setFont(f);
				label.setForeground(new Color(-16777012));

				pan.add(label);
				String byline = "";
				if (u.getWorkTitle() != null && !u.getWorkTitle().equals("")) {
					byline += u.getWorkTitle() + " ";
					if (u.getLabAffiliation() != null && !u.getLabAffiliation().equals(""))
						byline += "at ";
				}
				if (u.getLabAffiliation() != null && !u.getLabAffiliation().equals(""))
					byline += u.getLabAffiliation() + " ";

				if (u.getCity() != null && !u.getCity().equals("")) {
					byline += u.getCity();
					if (u.getState() != null && !u.getState().equals(""))
						byline += ", ";
					else
						byline += " ";

				}
				if (u.getState() != null && !u.getState().equals(""))
					byline += u.getState();
				JLabel label2 = new JLabel(byline);
				if (LoginFactory.isVisible(u))
					pan.add(label2);
				pan.add(new JSeparator(SwingConstants.HORIZONTAL));
				if (isSelected)
					pan.setBackground(new Color(251, 251, 228));
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7
				return pan;
			}
		});
		myFriendsList.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
<<<<<<< HEAD
				// TODO Auto-generated method stub

=======
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7
			}

			@Override
			public void mousePressed(MouseEvent e) {
<<<<<<< HEAD
				// TODO Auto-generated method stub

=======
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7
			}

			@Override
			public void mouseExited(MouseEvent e) {
<<<<<<< HEAD
				// TODO Auto-generated method stub

=======
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7
			}

			@Override
			public void mouseEntered(MouseEvent e) {
<<<<<<< HEAD
				// TODO Auto-generated method stub

=======
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
<<<<<<< HEAD
					viewProfileTab viewProfileTab;
//					if (network == null)
//						viewProfileTab = new viewProfileTab(
//								(Friend) myFriendsList.getSelectedValue());
//					else
//						viewProfileTab = new viewProfileTab(
//								(Profile) myFriendsList.getSelectedValue());
					//TODO
//					parentFrame.setContent(viewProfileTab);
=======
					viewProfileTab viewProfileTab = new viewProfileTab(
							(User) myFriendsList.getSelectedValue());
					parentFrame.setContent(viewProfileTab);
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7
				}
			}
		});
	}

	{
		// GUI initializer generated by IntelliJ IDEA GUI Designer
		// >>> IMPORTANT!! <<<
		// DO NOT EDIT OR ADD ANY CODE HERE!
		$$$setupUI$$$();
		initComponents();
	}

	@Override
	public void updateFormFields() {
<<<<<<< HEAD
		if (LoginManager.isLoggedIn()) { //TODO
//			SwingWorker<List<NetworkMessage>, Void> worker = new SwingWorker<List<NetworkMessage>, Void>() {
//
//				@Override
//				protected List<NetworkMessage> doInBackground()
//						throws Exception {
//					if (network == null)
//						return f.getFriends();
//					else
//						return nf.listUsersByNetwork(network);
//				}
//
//				@Override
//				protected void done() {
//					List<NetworkMessage> lst = null;
//					try {
//						lst = get();
//					} catch (InterruptedException e) {
//						GenSpace.logger.error("Error",e);
//					} catch (ExecutionException e) {
//						GenSpace.logger.error("Error",e);
//					}
//					DefaultListModel model = new DefaultListModel();
//					for (NetworkMessage t : lst) {
//						model.addElement(t);
//					}
//					myFriendsList.setModel(model);
//				}
//
//			};
//			worker.execute();
=======
		if (LoginFactory.isLoggedIn()) {
			SwingWorker<List<User>, Void> worker = new SwingWorker<List<User>, Void>() {

				@Override
				protected List<User> doInBackground()
						throws Exception {
					System.out.println("Requesting friends");
					if (networkFilter == null)
						return LoginFactory.getFriendOps().getFriendsProfiles();
					else
						return LoginFactory.getNetworkOps().getProfilesByNetwork(networkFilter);
				}

				@Override
				protected void done() {
					List<User> lst = null;
					try {
						lst = get();
						System.out.println("Got " + lst);
					} catch (InterruptedException e) {
						GenSpace.logger.error("Error",e);
					} catch (ExecutionException e) {
						GenSpace.logger.error("Error",e);
					}
					lst.remove(LoginFactory.getUser());
					Collections.sort(lst,new Comparator<User>() {

						@Override
						public int compare(User o1, User o2) {
							return o1.compareTo(o2);
						}
					});
					DefaultListModel model = new DefaultListModel();
					if(lst != null)
						for (User t : lst) {
							model.addElement(t);
						}
					myFriendsList.setModel(model);
				}

			};
			worker.execute();
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7
		}
	}
	/**
	 * Method generated by IntelliJ IDEA GUI Designer >>> IMPORTANT!! <<< DO NOT
	 * edit this method OR call it in your code!
	 * 
	 * @noinspection ALL
	 */
	private void $$$setupUI$$$() {
		panel1 = new JPanel();
		panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(3,
				1, new Insets(0, 0, 0, 0), -1, -1));
		final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
		panel1.add(
				spacer1,
<<<<<<< HEAD
				new com.intellij.uiDesigner.core.GridConstraints(
						0,
						0,
						1,
						1,
						com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER,
						com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW,
						1, null, null, null, 0, false));
		myFriendsList = new JList();
		myFriendsList.setBackground(panel1.getBackground());
		JScrollPane jScrollPane1 = new JScrollPane();
		jScrollPane1.setViewportView(myFriendsList);
		panel1.add(
				jScrollPane1,
				new com.intellij.uiDesigner.core.GridConstraints(
						1,
						0,
						2,
						1,
						com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER,
						com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW,
						null, new Dimension(150, 50), null, 0, false));
=======
				new com.intellij.uiDesigner.core.GridConstraints(1, 0, 2, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
		myFriendsList = new JList();
		myFriendsList.setBackground(panel1.getBackground());
		panel1.add(
				myFriendsList,
				new com.intellij.uiDesigner.core.GridConstraints(1, 0, 2, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
		
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7
	}

	@Override
	public String getName() {
<<<<<<< HEAD
		// TODO Auto-generated method stub
		if (network != null)
			return "Users in network " + network;
=======
		if (networkFilter != null)
			return "Users in network " + networkFilter.getName();
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7
		else
			return "My Friends";
	}
}
