package org.geworkbench.components.genspace.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
<<<<<<< HEAD
import java.util.List;
=======

>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
<<<<<<< HEAD

import org.geworkbench.components.genspace.chat.ChatReceiver;
import org.geworkbench.components.genspace.entity.User;
import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.RosterEntry;
import javax.swing.SwingWorker;

public class viewProfileTab extends SocialTab {
	boolean isFriend;

	public viewProfileTab(User u) {
//		this(p, p.profile.containsKey("friend")
//				&& p.profile.get("friend").equals("YES"), p.details != null
//				&& p.details.equals("YES"));
		//TODO
	}

	private viewProfileTab(User p, boolean isFriend, boolean showDetails) {
//		ProfileFacade f = new ProfileFacade();
//		p = f.getUsersProfile(p.subject);
//		this.p = p;
//		final Profile pp = p;
//
//		isFriend = p.profile.containsKey("friend")
//				&& p.profile.get("friend").equals("YES");
//		showDetails = p.details != null && p.details.equals("YES");
//		this.isFriend = isFriend;
//		String desc = ProfileFacade.renderProfileToHTML(p, showDetails);
//		JLabel profile = new JLabel(desc);
//		JLabel friend = new JLabel();
//		panel1 = new JPanel();
//		panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
//		panel1.add(profile);
//		JSeparator sep = new JSeparator(SwingConstants.HORIZONTAL);
//		panel1.add(sep);
//		if (isFriend) {
//			friend.setText("(" + p.profile.get("first_name") + " is a friend)");
//		} else {
//			friend.setText("(" + p.profile.get("first_name")
//					+ " is not a friend)");
//		}
//		panel1.add(friend);
//		if (isFriend) {
//			JButton removeFriend = new JButton("Remove friend");
//			removeFriend.addActionListener(new ActionListener() {
//
//				@Override
//				public void actionPerformed(ActionEvent e) {
//
//					SwingWorker<List<NetworkMessage>, Void> worker = new SwingWorker<List<NetworkMessage>, Void>() {
//
//						@Override
//						protected List<NetworkMessage> doInBackground()
//								throws Exception {
//							FriendFacade f = new FriendFacade();
//							Roster r = ChatReceiver.connection.getRoster();
//							RosterEntry e = r.getEntry(pp.subject
//									+ "@boris.cs.columbia.edu");
//							if (e != null)
//								ChatReceiver.connection.getRoster()
//										.removeEntry(e);
//							return f.deleteFriend(pp.subject);
//						}
//
//						@Override
//						protected void done() {
//							JOptionPane.showMessageDialog(panel1,
//									"You are no longer friends with "
//											+ pp.subject + "");
//						}
//
//					};
//					worker.execute();
//				}
//			});
//			panel1.add(removeFriend);
//		} else {
//			JButton addFriend = new JButton("Add as friend");
//			addFriend.addActionListener(new ActionListener() {
//
//				@Override
//				public void actionPerformed(ActionEvent e) {
//
//					SwingWorker<List<NetworkMessage>, Void> worker = new SwingWorker<List<NetworkMessage>, Void>() {
//
//						@Override
//						protected List<NetworkMessage> doInBackground()
//								throws Exception {
//							FriendFacade f = new FriendFacade();
//							ChatReceiver.connection.getRoster()
//									.createEntry(
//											pp.subject
//													+ "@boris.cs.columbia.edu",
//											pp.profile.get("first_name")
//													+ " "
//													+ pp.profile
//															.get("last_name"),
//											null);
//							return f.addFriend(pp.subject);
//						}
//
//						@Override
//						protected void done() {
//							JOptionPane
//									.showMessageDialog(
//											panel1,
//											"A friend sent for "
//													+ pp.subject
//													+ "'s approval. You will not become friends until he or her accepts your request.");
//						}
//
//					};
//					worker.execute();
//				}
//			});
//			panel1.add(addFriend);
//		}
=======
import javax.swing.SwingWorker;

import org.geworkbench.components.genspace.LoginFactory;
import org.geworkbench.components.genspace.entity.User;

public class viewProfileTab extends SocialTab {
	boolean isFriend;
	User u;

	public viewProfileTab(User p) {
		this.u = p;

		
		this.isFriend = LoginFactory.getUser().isFriendsWith(p) != null;
		
		String desc = p.toHTML();
		JLabel profile = new JLabel(desc);
		JLabel friend = new JLabel();
		panel1 = new JPanel();
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
		panel1.add(profile);
		JSeparator sep = new JSeparator(SwingConstants.HORIZONTAL);
		panel1.add(sep);
		if (isFriend) {
			friend.setText("(" + p.getShortName() + " is a friend)");
		} else {
			friend.setText("(" + p.getShortName()
					+ " is not a friend)");
		}
		panel1.add(friend);
		if (isFriend) {
			JButton removeFriend = new JButton("Remove friend");
			removeFriend.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {

						@Override
						protected Void doInBackground()
							 {
							LoginFactory.getFriendOps().removeFriend(u);
							return null;
							
						}

						@Override
						protected void done() {
							JOptionPane.showMessageDialog(panel1,
									"You are no longer friends with "
											+ u.getShortName() + "");
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

					SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {

						@Override
						protected Void doInBackground()
								 {
								LoginFactory.getFriendOps().addFriend(u);
								return null;
						}

						@Override
						protected void done() {
							JOptionPane
									.showMessageDialog(
											panel1,
											"A friend sent for "
													+ u.getShortName()
													+ "'s approval. You will not become friends until he or she accepts your request.");
						}

					};
					worker.execute();
				}
			});
			panel1.add(addFriend);
		}
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7
	}

	
	@Override
	public String getName() {
<<<<<<< HEAD
		// TODO Auto-generated method stub
=======
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7
//		return p.profile.get("first_name") + " " + p.profile.get("last_name")
//				+ "'s profile";
		return "";
	}
}
