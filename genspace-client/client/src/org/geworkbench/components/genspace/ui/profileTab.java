package org.geworkbench.components.genspace.ui;

import javax.swing.*;

import org.geworkbench.components.genspace.bean.networks.Profile;
import org.geworkbench.components.genspace.bean.networks.ProfileFacade;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutionException;

/**
 * Created by IntelliJ IDEA. User: jon Date: Aug 28, 2010 Time: 12:08:45 PM To
 * change this template use File | Settings | File Templates.
 */
public class profileTab extends SocialTab {
	private JTextField myNameTextField1;
	private JTextArea myResearchInterestsTextArea;
	private JTextField myLabTextField;
	private JTextField myNameTextField2;
	private JButton saveButton;
	private JTextField emailAddressTextField;
	private JTextField phoneTextField;
	private JTextField addressTextField;
	private JTextField address2TextField;
	private JTextField cityTextField;
	private JTextField stateTextField;
	private JTextField postalCodeTextField;
	protected JTextField jobTitleTextField;

	private void createUIComponents() {
		// TODO: place custom component creation code here
	}

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
		panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(11,
				5, new Insets(0, 0, 0, 0), -1, -1));
		final JLabel label1 = new JLabel();
		label1.setText("My Research Interests:");
		panel1.add(label1, new com.intellij.uiDesigner.core.GridConstraints(8,
				0, 1, 1,
				com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTHWEST,
				com.intellij.uiDesigner.core.GridConstraints.FILL_NONE,
				com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
				com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
				null, null, null, 0, false));
		final JLabel label2 = new JLabel();
		label2.setText("My Lab:");
		panel1.add(label2, new com.intellij.uiDesigner.core.GridConstraints(1,
				0, 1, 1,
				com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST,
				com.intellij.uiDesigner.core.GridConstraints.FILL_NONE,
				com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
				com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
				null, null, null, 0, false));
		final JLabel label3 = new JLabel();
		label3.setText("My Name:");
		panel1.add(label3, new com.intellij.uiDesigner.core.GridConstraints(0,
				0, 1, 1,
				com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST,
				com.intellij.uiDesigner.core.GridConstraints.FILL_NONE,
				com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
				com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
				null, null, null, 0, false));
		myNameTextField1 = new JTextField();
		panel1.add(
				myNameTextField1,
				new com.intellij.uiDesigner.core.GridConstraints(
						0,
						1,
						1,
						1,
						com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST,
						com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
						null, new Dimension(120, 28), null, 0, false));
		myResearchInterestsTextArea = new JTextArea();
		panel1.add(
				myResearchInterestsTextArea,
				new com.intellij.uiDesigner.core.GridConstraints(
						8,
						1,
						1,
						3,
						com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER,
						com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW,
						null, new Dimension(150, 50), null, 0, false));
		myNameTextField2 = new JTextField();
		panel1.add(
				myNameTextField2,
				new com.intellij.uiDesigner.core.GridConstraints(
						0,
						2,
						1,
						2,
						com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST,
						com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
						null, new Dimension(104, 28), null, 0, false));
		final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
		panel1.add(
				spacer1,
				new com.intellij.uiDesigner.core.GridConstraints(
						8,
						4,
						1,
						1,
						com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER,
						com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW,
						1, null, null, null, 0, false));
		saveButton = new JButton();
		saveButton.setText("Save");
		panel1.add(
				saveButton,
				new com.intellij.uiDesigner.core.GridConstraints(
						9,
						3,
						1,
						1,
						com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER,
						com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK
								| com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
						null, new Dimension(104, 29), null, 0, false));
		final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
		panel1.add(
				spacer2,
				new com.intellij.uiDesigner.core.GridConstraints(
						10,
						3,
						1,
						1,
						com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER,
						com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL,
						1,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW,
						null, new Dimension(104, 14), null, 0, false));
		myLabTextField = new JTextField();
		panel1.add(
				myLabTextField,
				new com.intellij.uiDesigner.core.GridConstraints(
						1,
						1,
						1,
						3,
						com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST,
						com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
						null, new Dimension(150, -1), null, 0, false));
		final JLabel label4 = new JLabel();
		label4.setText("Email Address:");
		panel1.add(label4, new com.intellij.uiDesigner.core.GridConstraints(3,
				0, 1, 1,
				com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST,
				com.intellij.uiDesigner.core.GridConstraints.FILL_NONE,
				com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
				com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
				null, null, null, 0, false));
		final JLabel label5 = new JLabel();
		label5.setText("Phone:");
		panel1.add(label5, new com.intellij.uiDesigner.core.GridConstraints(4,
				0, 1, 1,
				com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST,
				com.intellij.uiDesigner.core.GridConstraints.FILL_NONE,
				com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
				com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
				null, null, null, 0, false));
		final JLabel label6 = new JLabel();
		label6.setText("Address:");
		panel1.add(label6, new com.intellij.uiDesigner.core.GridConstraints(5,
				0, 1, 1,
				com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST,
				com.intellij.uiDesigner.core.GridConstraints.FILL_NONE,
				com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
				com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
				null, null, null, 0, false));
		emailAddressTextField = new JTextField();
		panel1.add(
				emailAddressTextField,
				new com.intellij.uiDesigner.core.GridConstraints(
						3,
						1,
						1,
						3,
						com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST,
						com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
						null, new Dimension(150, -1), null, 0, false));
		phoneTextField = new JTextField();
		panel1.add(
				phoneTextField,
				new com.intellij.uiDesigner.core.GridConstraints(
						4,
						1,
						1,
						3,
						com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST,
						com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
						null, new Dimension(150, -1), null, 0, false));
		addressTextField = new JTextField();
		panel1.add(
				addressTextField,
				new com.intellij.uiDesigner.core.GridConstraints(
						5,
						1,
						1,
						3,
						com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST,
						com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
						null, new Dimension(150, -1), null, 0, false));
		address2TextField = new JTextField();
		panel1.add(
				address2TextField,
				new com.intellij.uiDesigner.core.GridConstraints(
						6,
						1,
						1,
						3,
						com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST,
						com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
						null, new Dimension(150, -1), null, 0, false));
		final JLabel label7 = new JLabel();
		label7.setText("City, State, Postal Code:");
		panel1.add(label7, new com.intellij.uiDesigner.core.GridConstraints(7,
				0, 1, 1,
				com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST,
				com.intellij.uiDesigner.core.GridConstraints.FILL_NONE,
				com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
				com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
				null, null, null, 0, false));
		cityTextField = new JTextField();
		panel1.add(
				cityTextField,
				new com.intellij.uiDesigner.core.GridConstraints(
						7,
						1,
						1,
						1,
						com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST,
						com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
						null, new Dimension(120, 28), null, 0, false));
		stateTextField = new JTextField();
		panel1.add(
				stateTextField,
				new com.intellij.uiDesigner.core.GridConstraints(
						7,
						2,
						1,
						1,
						com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST,
						com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
						null, new Dimension(57, 28), null, 0, false));
		postalCodeTextField = new JTextField();
		panel1.add(
				postalCodeTextField,
				new com.intellij.uiDesigner.core.GridConstraints(
						7,
						3,
						1,
						1,
						com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST,
						com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
						null, new Dimension(104, 28), null, 0, false));
		final JLabel label8 = new JLabel();
		label8.setText("Job Title:");
		panel1.add(label8, new com.intellij.uiDesigner.core.GridConstraints(2,
				0, 1, 1,
				com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST,
				com.intellij.uiDesigner.core.GridConstraints.FILL_NONE,
				com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
				com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
				null, null, null, 0, false));
		jobTitleTextField = new JTextField();
		panel1.add(
				jobTitleTextField,
				new com.intellij.uiDesigner.core.GridConstraints(
						2,
						1,
						1,
						3,
						com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST,
						com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
						null, new Dimension(150, -1), null, 0, false));
		label1.setLabelFor(myResearchInterestsTextArea);
		label2.setLabelFor(myLabTextField);
		label3.setLabelFor(myNameTextField1);
		label4.setLabelFor(emailAddressTextField);
		label5.setLabelFor(phoneTextField);
		label6.setLabelFor(addressTextField);
		label8.setLabelFor(jobTitleTextField);
	}

	private void initListeners() {
		saveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				p.profile.put("first_name", myNameTextField1.getText());
				p.profile.put("work_title", jobTitleTextField.getText());
				p.profile.put("last_name", myNameTextField2.getText());
				p.profile.put("email", emailAddressTextField.getText());
				p.profile.put("phone", phoneTextField.getText());
				p.profile.put("addr1", addressTextField.getText());
				p.profile.put("addr2", address2TextField.getText());
				p.profile.put("city", cityTextField.getText());
				p.profile.put("state", stateTextField.getText());
				p.profile.put("zipcode", postalCodeTextField.getText());
				p.profile.put("lab_affiliation", myLabTextField.getText());
				p.profile.put("interests",
						myResearchInterestsTextArea.getText());
				f.updateMyProfile(p);
			}
		});

	}

	/**
	 * @noinspection ALL
	 */
	public JComponent $$$getRootComponent$$$() {
		return panel1;
	}

	@Override
	public String getName() {
		return "My Profile";
	}

	ProfileFacade f = new ProfileFacade();
	Profile p = new Profile();

	@Override
	public void updateFormFields() {
		if (f.amLoggedIn()) {
			SwingWorker<Profile, Void> worker = new SwingWorker<Profile, Void>() {

				@Override
				protected Profile doInBackground() throws Exception {
					return f.getMyProfile();
				}

				@Override
				protected void done() {
					try {
						p = get();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ExecutionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					myNameTextField1.setText(p.profile.get("first_name"));
					myNameTextField2.setText(p.profile.get("last_name"));
					emailAddressTextField.setText(p.profile.get("email"));
					phoneTextField.setText(p.profile.get("phone"));
					addressTextField.setText(p.profile.get("addr1"));
					address2TextField.setText(p.profile.get("addr2"));
					cityTextField.setText(p.profile.get("city"));
					stateTextField.setText(p.profile.get("state"));
					postalCodeTextField.setText(p.profile.get("zipcode"));
					myLabTextField.setText(p.profile.get("lab_affiliation"));
					myResearchInterestsTextArea.setText(p.profile
							.get("interests"));
					jobTitleTextField.setText(p.profile.get("work_title"));

				}

			};
			worker.execute();

		}
	}
}