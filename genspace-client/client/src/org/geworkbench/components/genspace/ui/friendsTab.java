package org.geworkbench.components.genspace.ui;

import javax.swing.*;

import org.geworkbench.components.genspace.bean.networks.Friend;
import org.geworkbench.components.genspace.bean.networks.FriendFacade;
import org.geworkbench.components.genspace.bean.networks.NetworkFacade;
import org.geworkbench.components.genspace.bean.networks.NetworkMessage;
import org.geworkbench.components.genspace.bean.networks.Profile;
import org.geworkbench.components.genspace.bean.networks.ProfileFacade;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by IntelliJ IDEA. User: jon Date: Aug 28, 2010 Time: 12:20:54 PM To
 * change this template use File | Settings | File Templates.
 */
public class friendsTab extends SocialTab {
	private JList pendingRequestsList;
	private JButton acceptButton;
	private JButton rejectButton;
	private JList myFriendsList;
	private String network = null;
	FriendFacade f = new FriendFacade();

	public friendsTab() {

	}

	public friendsTab(String filteredNetwork, SocialNetworksHome parentFrame) {
		network = filteredNetwork;
		this.parentFrame = parentFrame;
	}

	public void initComponents() {
		myFriendsList.setCellRenderer(new ListCellRenderer() {

			@Override
			public Component getListCellRendererComponent(JList list,
					Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				JPanel pan = new JPanel();
				Friend t;
				Profile p;
				boolean visible;
				if (network == null) {
					t = (Friend) value;
					p = t.profile;
					visible = t.details.equals("YES");
				} else {
					p = (Profile) value;
					visible = p.details.equals("YES");
				}
				pan.setLayout(new BoxLayout(pan, BoxLayout.Y_AXIS));

				JLabel label = new JLabel(p.profile.get("first_name")
						+ " " + p.profile.get("last_name"));
				Font f = new Font(label.getFont().getName(), Font.BOLD, 18);
				label.setFont(f);
				label.setForeground(new Color(-16777012));

				pan.add(label);
				String byline = "";
				if (p.profile.get("work_title") != "") {
					byline += p.profile.get("work_title") + " ";
					if (p.profile.get("lab_affiliation") != "")
						byline += "at ";
				}
				if (p.profile.get("lab_affiliation") != "") {
					byline += p.profile.get("lab_affiliation") + " ";
				}
				if (p.profile.get("city") != "") {
					byline += p.profile.get("city");
					if (p.profile.get("state") != "")
						byline += ", ";
					else
						byline += " ";

				}
				if (p.profile.get("state") != "")
					byline += p.profile.get("state");
				JLabel label2 = new JLabel(byline);
				if (visible)
					pan.add(label2);
				pan.add(new JSeparator(SwingConstants.HORIZONTAL));
				if (isSelected)
					pan.setBackground(new Color(251, 251, 228));
				return pan;
			}
		});
		myFriendsList.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					viewProfileTab viewProfileTab;
					if (network == null)
						viewProfileTab = new viewProfileTab(
								(Friend) myFriendsList.getSelectedValue());
					else
						viewProfileTab = new viewProfileTab(
								(Profile) myFriendsList.getSelectedValue());
					parentFrame.setContent(viewProfileTab);
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
		if (f.amLoggedIn()) {
			SwingWorker<List<NetworkMessage>, Void> worker = new SwingWorker<List<NetworkMessage>, Void>() {

				@Override
				protected List<NetworkMessage> doInBackground()
						throws Exception {
					if (network == null)
						return f.getFriends();
					else
						return nf.listUsersByNetwork(network);
				}

				@Override
				protected void done() {
					List<NetworkMessage> lst = null;
					try {
						lst = get();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ExecutionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					DefaultListModel model = new DefaultListModel();
					for (NetworkMessage t : lst) {
						model.addElement(t);
					}
					myFriendsList.setModel(model);
				}

			};
			worker.execute();
		}
	}

	private ProfileFacade pf = new ProfileFacade();
	private NetworkFacade nf = new NetworkFacade();

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
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		if (network != null)
			return "Users in network " + network;
		else
			return "My Friends";
	}
}