package org.geworkbench.components.genspace.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import org.geworkbench.components.genspace.bean.networks.Friend;
import org.geworkbench.components.genspace.bean.networks.FriendFacade;
import org.geworkbench.components.genspace.bean.networks.NetworkMessage;
import org.geworkbench.components.genspace.bean.networks.Profile;
import org.geworkbench.components.genspace.bean.networks.ProfileFacade;
import org.geworkbench.components.genspace.chat.ChatReceiver;
import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.RosterEntry;
import javax.swing.SwingWorker;

public class viewProfileTab extends SocialTab {
	Profile p;
	boolean isFriend;

	public viewProfileTab(Profile p) {
		this(p, p.profile.containsKey("friend")
				&& p.profile.get("friend").equals("YES"), p.details != null
				&& p.details.equals("YES"));
	}

	private viewProfileTab(Profile p, boolean isFriend, boolean showDetails) {
		ProfileFacade f = new ProfileFacade();
		p = f.getUsersProfile(p.subject);
		this.p = p;
		final Profile pp = p;

		isFriend = p.profile.containsKey("friend")
				&& p.profile.get("friend").equals("YES");
		showDetails = p.details != null && p.details.equals("YES");
		this.isFriend = isFriend;
		String desc = ProfileFacade.renderProfileToHTML(p, showDetails);
		JLabel profile = new JLabel(desc);
		JLabel friend = new JLabel();
		panel1 = new JPanel();
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
		panel1.add(profile);
		JSeparator sep = new JSeparator(SwingConstants.HORIZONTAL);
		panel1.add(sep);
		if (isFriend) {
			friend.setText("(" + p.profile.get("first_name") + " is a friend)");
		} else {
			friend.setText("(" + p.profile.get("first_name")
					+ " is not a friend)");
		}
		panel1.add(friend);
		if (isFriend) {
			JButton removeFriend = new JButton("Remove friend");
			removeFriend.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					SwingWorker<List<NetworkMessage>, Void> worker = new SwingWorker<List<NetworkMessage>, Void>() {

						@Override
						protected List<NetworkMessage> doInBackground()
								throws Exception {
							FriendFacade f = new FriendFacade();
							Roster r = ChatReceiver.connection.getRoster();
							RosterEntry e = r.getEntry(pp.subject
									+ "@boris.cs.columbia.edu");
							if (e != null)
								ChatReceiver.connection.getRoster()
										.removeEntry(e);
							return f.deleteFriend(pp.subject);
						}

						@Override
						protected void done() {
							JOptionPane.showMessageDialog(panel1,
									"You are no longer friends with "
											+ pp.subject + "");
						}

					};
					worker.execute();
				}
			});
			panel1.add(removeFriend);
		} else {
			JButton addFriend = new JButton("Add as friend");
			addFriend.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					SwingWorker<List<NetworkMessage>, Void> worker = new SwingWorker<List<NetworkMessage>, Void>() {

						@Override
						protected List<NetworkMessage> doInBackground()
								throws Exception {
							FriendFacade f = new FriendFacade();
							ChatReceiver.connection.getRoster()
									.createEntry(
											pp.subject
													+ "@boris.cs.columbia.edu",
											pp.profile.get("first_name")
													+ " "
													+ pp.profile
															.get("last_name"),
											null);
							return f.addFriend(pp.subject);
						}

						@Override
						protected void done() {
							JOptionPane
									.showMessageDialog(
											panel1,
											"A friend sent for "
													+ pp.subject
													+ "'s approval. You will not become friends until he or her accepts your request.");
						}

					};
					worker.execute();
				}
			});
			panel1.add(addFriend);
		}
	}

	public viewProfileTab(Friend f) {
		this(f.profile, true, f.details.equals("YES"));
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return p.profile.get("first_name") + " " + p.profile.get("last_name")
				+ "'s profile";
	}
}
