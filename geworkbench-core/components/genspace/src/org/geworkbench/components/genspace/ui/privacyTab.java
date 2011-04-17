package org.geworkbench.components.genspace.ui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.SwingWorker;

import org.geworkbench.components.genspace.GenSpace;
import org.geworkbench.components.genspace.GenSpaceServerFactory;
import org.geworkbench.components.genspace.entity.Friend;
import org.geworkbench.components.genspace.entity.UserNetwork;

/**
 * Created by IntelliJ IDEA. User: jon Date: Aug 28, 2010 Time: 12:53:24 PM To
 * change this template use File | Settings | File Templates.
 */
public class privacyTab extends SocialTab {
	private JList listNetworks;
	private JList listFriends;
	private List<Friend> cachedFriends;
	private List<UserNetwork> cachedNetworks;
	
	private JButton saveButton;
	{
		// GUI initializer generated by IntelliJ IDEA GUI Designer
		// >>> IMPORTANT!! <<<
		// DO NOT EDIT OR ADD ANY CODE HERE!
		$$$setupUI$$$();
		initListeners();
	}

	/**
	 * Method generated by IntelliJ IDEA GUI Designer >>> IMPORTANT!! <<< DO NOT
	 * edit this method OR call it in your code!
	 * 
	 * @noinspection ALL
	 */
	private void $$$setupUI$$$() {
		panel1 = new JPanel();
		panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(4,
				3, new Insets(0, 0, 0, 0), -1, -1));
		final JLabel label1 = new JLabel();
		label1.setText("Select who may see your activities:");
		panel1.add(label1, new com.intellij.uiDesigner.core.GridConstraints(0,
				0, 1, 3,
				com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST,
				com.intellij.uiDesigner.core.GridConstraints.FILL_NONE,
				com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
				com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
				null, null, null, 0, false));
		final JLabel label2 = new JLabel();
		label2.setText("Members of Networks:");
		panel1.add(label2, new com.intellij.uiDesigner.core.GridConstraints(1,
				0, 1, 1,
				com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST,
				com.intellij.uiDesigner.core.GridConstraints.FILL_NONE,
				com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
				com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
				null, new Dimension(238, 16), null, 0, false));
		final JLabel label3 = new JLabel();
		label3.setText("Friends:");
		panel1.add(label3, new com.intellij.uiDesigner.core.GridConstraints(1,
				2, 1, 1,
				com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST,
				com.intellij.uiDesigner.core.GridConstraints.FILL_NONE,
				com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
				com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
				null, null, null, 0, false));
		final JScrollPane scrollPane1 = new JScrollPane();
		panel1.add(
				scrollPane1,
				new com.intellij.uiDesigner.core.GridConstraints(
						2,
						0,
						1,
						1,
						com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER,
						com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK
								| com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK
								| com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW,
						null, new Dimension(238, 223), null, 0, false));
		listNetworks = new JList();
		scrollPane1.setViewportView(listNetworks);
		final JScrollPane scrollPane2 = new JScrollPane();
		panel1.add(
				scrollPane2,
				new com.intellij.uiDesigner.core.GridConstraints(
						2,
						2,
						1,
						1,
						com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER,
						com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK
								| com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK
								| com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW,
						null, new Dimension(256, 223), null, 0, false));
		listFriends = new JList();
		scrollPane2.setViewportView(listFriends);
		saveButton = new JButton();
		saveButton.setText("Save");
		panel1.add(
				saveButton,
				new com.intellij.uiDesigner.core.GridConstraints(
						3,
						0,
						1,
						3,
						com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER,
						com.intellij.uiDesigner.core.GridConstraints.FILL_NONE,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
						null, null, null, 0, false));
	}

	private void initListeners() {
		saveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				final DefaultListModel model = (DefaultListModel) listNetworks
						.getModel();
				final HashMap<Integer, Boolean> s = new HashMap<Integer, Boolean>();
				for (Integer i : listNetworks.getSelectedIndices()) {
					s.put(i, true);
				}
				for (int i = 0; i < model.getSize(); i++) {
					if (!s.containsKey(i))
						s.put(i, false);
				}
				SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {

					@Override
					protected Void doInBackground() throws Exception {
						for (Integer i : s.keySet()) {
							GenSpaceServerFactory.getNetworkOps().updateNetworkVisibility(
									((UserNetwork) model.get(i)).getId(),
									s.get(i));
						}
						return null;
					}

				};
				worker.execute();

				final DefaultListModel model2 = (DefaultListModel) listFriends
						.getModel();
				final HashMap<Integer, Boolean> s2 = new HashMap<Integer, Boolean>();
				for (Integer i : listNetworks.getSelectedIndices()) {
					s2.put(i, true);
				}
				for (int i = 0; i < model.getSize(); i++) {
					if (!s2.containsKey(i))
						s2.put(i, false);
				}
				SwingWorker<Void, Void> worker2 = new SwingWorker<Void, Void>() {

					@Override
					protected Void doInBackground() throws Exception {
						for (Integer i : s2.keySet()) {
							GenSpaceServerFactory.getFriendOps().updateFriendVisibility(
									((Friend) model2.get(i)).getId(),
									s2.get(i));
						}
						return null;
					}

				};
				worker2.execute();
			}
		});
		listFriends.setCellRenderer(new ListCellRenderer() {

			@Override
			public Component getListCellRendererComponent(JList list,
					Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				JLabel renderer = (JLabel) new DefaultListCellRenderer()
						.getListCellRendererComponent(list, value, index,
								isSelected, cellHasFocus);
				renderer.setText(((Friend) value).getRightUser().getFullName());
				return renderer;
			}
		});
		listNetworks.setCellRenderer(new ListCellRenderer() {

			@Override
			public Component getListCellRendererComponent(JList list,
					Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				JLabel renderer = (JLabel) new DefaultListCellRenderer()
						.getListCellRendererComponent(list, value, index,
								isSelected, cellHasFocus);
				
				renderer.setText(((UserNetwork) value).getNetwork().getName());

				return renderer;
			}
		});
	}

	@Override
	public String getName() {
		return "Privacy";
	}


	@Override
	public void updateFormFields() {
		if (GenSpaceServerFactory.isLoggedIn()) {

			SwingWorker<List<Friend>, Void> worker = new SwingWorker<List<Friend>, Void>() {

				@Override
				protected List<Friend> doInBackground()
						throws Exception {
					return GenSpaceServerFactory.getFriendOps().getFriends();
				}

				@Override
				protected void done() {
					try {
						cachedFriends = get();
					} catch (InterruptedException e) {
						GenSpace.logger.error("Error",e);
					} catch (ExecutionException e) {
						GenSpace.logger.error("Error",e);
					}
					DefaultListModel m = new DefaultListModel();
					ArrayList<Integer> selected = new ArrayList<Integer>();
					int i = 0;
					for (Friend t : cachedFriends) {
						m.addElement(t);
						if (t.isVisible()) {
							selected.add(i);
						}
						i++;
					}
					listFriends.setModel(m);
					int[] t = new int[selected.size()];
					for (i = 0; i < selected.size(); i++) {
						t[i] = selected.get(i);
					}
					listFriends.setSelectedIndices(t);
				}

			};

			worker.execute();

			SwingWorker<List<UserNetwork>, Void> worker2 = new SwingWorker<List<UserNetwork>, Void>() {

				@Override
				protected List<UserNetwork> doInBackground()
						throws Exception {
					return GenSpaceServerFactory.getUser().getNetworks();
				}

				@Override
				protected void done() {
					try {
						cachedNetworks = get();
					} catch (InterruptedException e) {
						GenSpace.logger.error("Unable to talk to server", e);
					} catch (ExecutionException e) {
						GenSpace.logger.error("Unable to talk to server", e);
					}
					DefaultListModel model = new DefaultListModel();
					ArrayList<Integer> selected = new ArrayList<Integer>();
					int i = 0;
					for (UserNetwork t : cachedNetworks) {
						model.addElement(t);
						if (t.isVisible())
							selected.add(i);
						i++;
					}
					int[] t = new int[selected.size()];
					for (i = 0; i < selected.size(); i++) {
						t[i] = selected.get(i);
					}

					listNetworks.setModel(model);
					listNetworks.setSelectedIndices(t);
				}

			};
			worker2.execute();

		}
	}

}
