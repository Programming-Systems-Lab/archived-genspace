package org.geworkbench.components.genspace.ui;

import javax.swing.*;

import org.geworkbench.components.genspace.bean.networks.NetworkFacade;
import org.geworkbench.components.genspace.bean.networks.NetworkMessage;
import org.geworkbench.components.genspace.bean.networks.ProfileFacade;
import org.geworkbench.components.genspace.ui.AutoCompleteCombo.Model;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by IntelliJ IDEA. User: jon Date: Aug 28, 2010 Time: 12:48:15 PM To
 * change this template use File | Settings | File Templates.
 */
public class networksTab extends SocialTab {
	private AutoCompleteCombo chooseNetwork;
	private JList lstMyNetworks;
	private JButton button1;
	private JButton button2;

	@Override
	public String getName() {
		return "My Networks";

	}

	public void initComponents() {
		lstMyNetworks.setCellRenderer(new ListCellRenderer() {

			@Override
			public Component getListCellRendererComponent(JList list,
					Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				JPanel pan = new JPanel();
				NetworkMessage t = (NetworkMessage) value;
				pan.setLayout(new BoxLayout(pan, BoxLayout.Y_AXIS));
				JLabel label = new JLabel(t.subject);
				Font f = new Font(label.getFont().getName(), Font.BOLD, 18);
				label.setFont(f);
				label.setForeground(new Color(-16777012));

				pan.add(label);
				JLabel label2 = new JLabel("Moderated by " + t.details);
				pan.add(label2);
				pan.add(new JSeparator(SwingConstants.HORIZONTAL));
				if (isSelected)
					pan.setBackground(new Color(251, 251, 228));
				return pan;
			}
		});
		lstMyNetworks.addMouseListener(new MouseListener() {

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
				if (e.getClickCount() == 2)
					parentFrame.setContent(new friendsTab(
							((NetworkMessage) lstMyNetworks.getSelectedValue()).subject,
							parentFrame));
			}
		});
		button1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				boolean found = false;
				for (NetworkMessage m : cachedAllNetworks) {
					if (m.subject.equals(chooseNetwork.getText())) {
						found = true;
						break;
					}
				}
				for (NetworkMessage m : cachedMyNetworks) {
					if (m.subject.equals(chooseNetwork.getText())) {
						return;
					}
				}
				if (found) {
					// Join
					f.joinNetwork(chooseNetwork.getText());
					JOptionPane
							.showMessageDialog(
									panel1,
									"A request has been sent to the network's owner for approval. It will not show up in your list until you have been approved.");
				} else {
					// create
					f.createNetwork(chooseNetwork.getText());
					JOptionPane.showMessageDialog(panel1, "The network "
							+ chooseNetwork.getText() + " has been created");
				}
				updateFormFields();
				chooseNetwork.setText("");
			}
		});
		button2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				NetworkMessage selected = (NetworkMessage) lstMyNetworks
						.getSelectedValue();
				if (selected != null)
					f.leaveNetwork(selected.subject);
				updateFormFields();
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

	/**
	 * Method generated by IntelliJ IDEA GUI Designer >>> IMPORTANT!! <<< DO NOT
	 * edit this method OR call it in your code!
	 * 
	 * @noinspection ALL
	 */
	private void $$$setupUI$$$() {
		panel1 = new JPanel();
		panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(6,
				2, new Insets(0, 0, 0, 0), -1, -1));
		final JLabel label1 = new JLabel();
		label1.setText("Join/Create a network:");
		panel1.add(label1, new com.intellij.uiDesigner.core.GridConstraints(1,
				1, 1, 1,
				com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST,
				com.intellij.uiDesigner.core.GridConstraints.FILL_NONE,
				com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
				com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
				null, null, null, 0, false));
		chooseNetwork = new AutoCompleteCombo();
		chooseNetwork.setSize(new Dimension(100, chooseNetwork.getWidth()));
		panel1.add(
				chooseNetwork,
				new com.intellij.uiDesigner.core.GridConstraints(
						2,
						1,
						1,
						1,
						com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER,
						com.intellij.uiDesigner.core.GridConstraints.FILL_NONE,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW,
						chooseNetwork.getSize(), null, null, 0, false));
		lstMyNetworks = new JList();
		lstMyNetworks.setBackground(panel1.getBackground());
		JScrollPane jScrollPane1 = new JScrollPane();
		jScrollPane1.setViewportView(lstMyNetworks);
		panel1.add(
				jScrollPane1,
				new com.intellij.uiDesigner.core.GridConstraints(
						0,
						0,
						6,
						1,
						com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER,
						com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW,
						null, new Dimension(320, 50), null, 0, false));
		button1 = new JButton();
		button1.setText("Go");
		panel1.add(
				button1,
				new com.intellij.uiDesigner.core.GridConstraints(
						3,
						1,
						1,
						1,
						com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER,
						com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK
								| com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
						null, null, null, 0, false));
		button2 = new JButton("Leave selected network");
		panel1.add(
				button2,
				new com.intellij.uiDesigner.core.GridConstraints(
						4,
						1,
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
						5,
						1,
						1,
						1,
						com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER,
						com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL,
						1,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW,
						null, null, null, 0, false));
		final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
		panel1.add(spacer2, new com.intellij.uiDesigner.core.GridConstraints(0,
				1, 1, 1,
				com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER,
				com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1,
				com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
				null, new Dimension(11, 24), null, 0, false));
	}

	private List<NetworkMessage> cachedAllNetworks;
	private List<NetworkMessage> cachedMyNetworks;
	private NetworkFacade f = new NetworkFacade();
	ProfileFacade p = new ProfileFacade();

	@Override
	public void updateFormFields() {
		if (p.amLoggedIn()) {
			SwingWorker<List<NetworkMessage>, Void> worker2 = new SwingWorker<List<NetworkMessage>, Void>() {

				@Override
				protected List<NetworkMessage> doInBackground()
						throws Exception {
					return f.listMyNetworks();
				}

				@Override
				protected void done() {
					try {
						cachedMyNetworks = get();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ExecutionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					DefaultListModel model = new DefaultListModel();
					for (NetworkMessage t : cachedMyNetworks) {
						model.addElement(t);
					}
					lstMyNetworks.setModel(model);
				}

			};
			worker2.execute();
			SwingWorker<List<NetworkMessage>, Void> worker = new SwingWorker<List<NetworkMessage>, Void>() {

				@Override
				protected List<NetworkMessage> doInBackground()
						throws Exception {
					return f.listAllNetworks();
				}

				@Override
				protected void done() {
					try {
						cachedAllNetworks = get();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ExecutionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Model m = (Model) chooseNetwork.getModel();
					m.data.clear();
					for (NetworkMessage t : cachedAllNetworks) {
						m.data.add(t.subject);
					}
					chooseNetwork.setText("");
				}

			};
			worker.execute();
		}
	}
}
