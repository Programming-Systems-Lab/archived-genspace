package org.geworkbench.components.genspace.ui;

import javax.swing.*;

import org.geworkbench.components.genspace.bean.networks.Friend;
import org.geworkbench.components.genspace.bean.networks.FriendFacade;
import org.geworkbench.components.genspace.bean.networks.Network;
import org.geworkbench.components.genspace.bean.networks.NetworkFacade;
import org.geworkbench.components.genspace.bean.networks.NetworkMessage;
import org.geworkbench.components.genspace.bean.networks.Profile;
import org.geworkbench.components.genspace.bean.networks.ProfileFacade;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by IntelliJ IDEA. User: jon Date: Aug 28, 2010 Time: 12:56:05 PM To
 * change this template use File | Settings | File Templates.
 */
public class requestsTab extends SocialTab {
	private JList friendsList;
	private JList networksList;
	private JButton acceptFriendButton;
	private JButton rejectFriendButton;
	private JButton acceptButton;
	private JButton rejectButton;

	{
		// GUI initializer generated by IntelliJ IDEA GUI Designer
		// >>> IMPORTANT!! <<<
		// DO NOT EDIT OR ADD ANY CODE HERE!
		$$$setupUI$$$();
		initListeners();

	}
	ProfileFacade p = new ProfileFacade();
	NetworkFacade n = new NetworkFacade();
	FriendFacade f = new FriendFacade();

	@Override
	public void updateFormFields() {
		if (p.amLoggedIn()) {

			SwingWorker<List<NetworkMessage>, Void> worker = new SwingWorker<List<NetworkMessage>, Void>() {

				@Override
				protected List<NetworkMessage> doInBackground()
						throws Exception {
					List<NetworkMessage> myNetworks = n.listMyNetworks();
					LinkedList<NetworkMessage> ret = new LinkedList<NetworkMessage>();
					for (NetworkMessage t : myNetworks) {
						Network nt = (Network) t;
						if (nt.creator.equals(p.getMyUsername()))
							ret.addAll(n.listPendingUsersByNetwork(t.subject));
					}
					return ret;
				}

				@Override
				protected void done() {
					List<NetworkMessage> requests = null;
					try {
						requests = get();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ExecutionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					DefaultListModel model = new DefaultListModel();
					for (NetworkMessage t : requests) {
						model.addElement(t);
					}
					networksList.setModel(model);
				}

			};
			worker.execute();

			SwingWorker<List<NetworkMessage>, Void> worker2 = new SwingWorker<List<NetworkMessage>, Void>() {

				@Override
				protected List<NetworkMessage> doInBackground()
						throws Exception {
					return f.getPendingRequests();
				}

				@Override
				protected void done() {
					List<NetworkMessage> requests = null;
					try {
						requests = get();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ExecutionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					DefaultListModel model = new DefaultListModel();
					for (NetworkMessage t : requests) {
						model.addElement(t);
					}
					friendsList.setModel(model);
				}

			};
			worker2.execute();

		}
	}

	private void initListeners() {
		friendsList.setCellRenderer(new ListCellRenderer() {

			@Override
			public Component getListCellRendererComponent(JList list,
					Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				// TODO Auto-generated method stub
				Friend n = (Friend) value;
				JLabel ret = (JLabel) new DefaultListCellRenderer()
						.getListCellRendererComponent(list, value, index,
								isSelected, cellHasFocus);
				ret.setText(n.profile.profile.get("first_name") + " "
						+ n.profile.profile.get("last_name") + " (" + n.subject
						+ ")");
				if (isSelected)
					ret.setBackground(new Color(205, 220, 243));
				return ret;
			}
		});
		networksList.setCellRenderer(new ListCellRenderer() {

			@Override
			public Component getListCellRendererComponent(JList list,
					Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				Profile n = (Profile) value;
				JLabel ret = (JLabel) new DefaultListCellRenderer()
						.getListCellRendererComponent(list, value, index,
								isSelected, cellHasFocus);
				ret.setText(n.details + ": " + n.profile.get("first_name")
						+ " " + n.profile.get("last_name") + " (" + n.subject
						+ ")");
				if (isSelected)
					ret.setBackground(new Color(205, 220, 243));
				return ret;
			}
		});
		acceptButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (networksList.getSelectedValue() != null) {
					n.acceptNetworkRequest(((Profile) networksList
							.getSelectedValue()).details,
							((Profile) networksList.getSelectedValue()).profile
									.get("username"));
					updateFormFields();
				}
			}
		});
		rejectButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (networksList.getSelectedValue() != null) {
					n.rejectNetworkRequest(((Profile) networksList
							.getSelectedValue()).details,
							((Profile) networksList.getSelectedValue()).profile
									.get("username"));
					updateFormFields();
				}
			}
		});
		acceptFriendButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				f.addFriend(((Friend) friendsList.getSelectedValue()).subject);
				updateFormFields();

			}
		});
		rejectFriendButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				f.addFriend(((Friend) friendsList.getSelectedValue()).subject);
				updateFormFields();
			}
		});

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
				5, new Insets(0, 0, 0, 0), -1, -1));
		final JLabel label1 = new JLabel();
		label1.setText("Friends");
		panel1.add(label1, new com.intellij.uiDesigner.core.GridConstraints(0,
				0, 1, 1,
				com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST,
				com.intellij.uiDesigner.core.GridConstraints.FILL_NONE,
				com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
				com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
				null, null, null, 0, false));
		final JLabel label2 = new JLabel();
		label2.setText("Network");
		panel1.add(label2, new com.intellij.uiDesigner.core.GridConstraints(0,
				3, 1, 1,
				com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST,
				com.intellij.uiDesigner.core.GridConstraints.FILL_NONE,
				com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
				com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
				null, null, null, 0, false));
		acceptFriendButton = new JButton();
		acceptFriendButton.setText("Accept Friend");
		panel1.add(
				acceptFriendButton,
				new com.intellij.uiDesigner.core.GridConstraints(
						2,
						0,
						1,
						1,
						com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER,
						com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK
								| com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
						null, null, null, 0, false));
		rejectFriendButton = new JButton();
		rejectFriendButton.setText("Reject Friend");
		panel1.add(
				rejectFriendButton,
				new com.intellij.uiDesigner.core.GridConstraints(
						2,
						1,
						1,
						1,
						com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER,
						com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK
								| com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
						null, null, null, 0, false));
		acceptButton = new JButton();
		acceptButton.setText("Accept");
		panel1.add(
				acceptButton,
				new com.intellij.uiDesigner.core.GridConstraints(
						2,
						3,
						1,
						1,
						com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER,
						com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK
								| com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
						null, null, null, 0, false));
		rejectButton = new JButton();
		rejectButton.setText("Reject");
		panel1.add(
				rejectButton,
				new com.intellij.uiDesigner.core.GridConstraints(
						2,
						4,
						1,
						1,
						com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER,
						com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK
								| com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
						null, null, null, 0, false));
		final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
		panel1.add(
				spacer1,
				new com.intellij.uiDesigner.core.GridConstraints(
						1,
						2,
						1,
						1,
						com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER,
						com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW,
						1, null, null, null, 0, false));
		final JScrollPane scrollPane1 = new JScrollPane();
		panel1.add(
				scrollPane1,
				new com.intellij.uiDesigner.core.GridConstraints(
						1,
						0,
						1,
						2,
						com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER,
						com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK
								| com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK
								| com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW,
						null, null, null, 0, false));
		friendsList = new JList();
		scrollPane1.setViewportView(friendsList);
		final JScrollPane scrollPane2 = new JScrollPane();
		panel1.add(
				scrollPane2,
				new com.intellij.uiDesigner.core.GridConstraints(
						1,
						3,
						1,
						2,
						com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER,
						com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK
								| com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK
								| com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW,
						null, null, null, 0, false));
		networksList = new JList();
		scrollPane2.setViewportView(networksList);
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Pending Requests";
	}
}