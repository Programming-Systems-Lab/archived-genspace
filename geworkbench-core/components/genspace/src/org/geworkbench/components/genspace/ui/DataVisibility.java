package org.geworkbench.components.genspace.ui;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.geworkbench.components.genspace.LoginFactory;
import org.geworkbench.components.genspace.ObjectHandler;
import org.geworkbench.engine.config.VisualPlugin;
import org.geworkbench.engine.properties.PropertiesManager;

/**
 * This class build the panel for setting user's log preferences and data
 * visibility.
 */
public class DataVisibility extends JPanel implements VisualPlugin,
		ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7554634436096470168L;

	static final String PROPERTY_KEY = "genSpace_logging_preferences"; // the
																		// key
																		// in
																		// the
																		// properties
																		// file

	private JComboBox logPreferences;

	private JComboBox dataVisibilityOptions;


	private JButton save;

	private String username = "";
	int preference;

	public DataVisibility() {
		// read the preferences from the properties file
		try {
			PropertiesManager properties = PropertiesManager.getInstance();
			String pref = properties.getProperty(DataVisibility.class,
					PROPERTY_KEY, null);

			username = LoginFactory.getUsername();

			if (pref == null) {
				// if the preferences are not set, then show the pop up window

				// ideally this should also be in the properties file
				String message = "geWorkbench now includes a component called genSpace,\n"
						+ "which will provide social networking capabilities and allow\n"
						+ "you to connect with other geWorkbench users.\n\n"
						+ "In order for it to be effective, genSpace must log which analysis\n"
						+ "tools you use during your geWorkbench session.\n\n"
						+ "Please go to the genSpace Logging Preference window to configure \n"
						+ "your preference. You can later change it at any time.";
				String title = "Please set your genSpace logging preferences.";
				JOptionPane.showMessageDialog(this, message, title,
						JOptionPane.INFORMATION_MESSAGE);

				// set the default to "log anonymously"
				pref = "1";
				// write it to the properties file
				properties
						.setProperty(DataVisibility.class, PROPERTY_KEY, pref);
			}

			// set the logging level
			ObjectHandler.setLogStatus(Integer.parseInt(pref));
			preference = Integer.parseInt(pref);
		} catch (Exception e) {
		}

		initComponents();
	}

	private void initComponents() {
		if(LoginFactory.isLoggedIn())
		{
			this.setSize(500, 600);

			GridBagLayout gridbag = new GridBagLayout();
			this.setLayout(gridbag);

			GridBagConstraints c = new GridBagConstraints();
			c.ipady = 5;
			JLabel blank = new JLabel(" ");

			logPreferences = new JComboBox();
			logPreferences.addItem("-- Select Log Preferences --");
			logPreferences.addItem("Log My Analysis Events");
			logPreferences.addItem("Log My Analysis Events Anonymously");
			logPreferences.addItem("Do Not Log My Analysis Events");
			int preference = LoginFactory.getUser().getLogData();
			logPreferences.setSelectedIndex(preference + 1);

			try {
				PropertiesManager properties = PropertiesManager.getInstance();
				properties.setProperty(DataVisibility.class, PROPERTY_KEY, ""
						+ (logPreferences.getSelectedIndex() - 1));
			} catch (Exception e) {
			}

			ObjectHandler.setLogStatus(preference);

			c.gridwidth = GridBagConstraints.REMAINDER;
			gridbag.setConstraints(logPreferences, c);
			add(logPreferences);
			logPreferences.addActionListener(this);

			c.gridwidth = GridBagConstraints.REMAINDER;
			gridbag.setConstraints(blank, c);
			add(blank);

			dataVisibilityOptions = new JComboBox();
			dataVisibilityOptions.addItem("-- Select Data Visibility Options --");
			dataVisibilityOptions.addItem("Data Not Visible At All");
			dataVisibilityOptions.addItem("Data Visible Within My Network");
			dataVisibilityOptions.addItem("Data Visible In Networks");
		}
		else
		{
			logPreferences = new JComboBox();
			logPreferences.addItem("-- Select Log Preferences --");
			logPreferences.addItem("Log My Analysis Events");
			logPreferences.addItem("Log My Analysis Events Anonymously");
			logPreferences.addItem("Do Not Log My Analysis Events");
			add(logPreferences);
			logPreferences.addActionListener(this);
		}
		save = new JButton("Save");
		add(save);
		save.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

//		String option = "";

		/*
		 * if (e.getSource() == dataVisibilityOptions) { option =
		 * dataVisibilityOptions.getSelectedItem().toString();
		 * networks.setEnabled(false); }
		 * 
		 * if (option.equals("Data Visible In Networks")) {
		 * networks.setEnabled(true); }
		 */

		if (e.getSource() == save) {

			javax.swing.SwingWorker<Void, Void> worker = new javax.swing.SwingWorker<Void, Void>() {
				@Override
				public Void doInBackground() {

					if (logPreferences.getSelectedIndex() == 0) {
						JOptionPane.showMessageDialog(null,
								"Please Select Log Preferences", "",
								JOptionPane.INFORMATION_MESSAGE);
					}
					else {

						preference = logPreferences.getSelectedIndex() - 1;
						ObjectHandler.setLogStatus(preference);

						// write it to the properties file
						try {
							PropertiesManager properties = PropertiesManager
									.getInstance();
							properties.setProperty(
									DataVisibility.class,
									PROPERTY_KEY,
									""
											+ (logPreferences
													.getSelectedIndex() - 1));
						} catch (Exception ex) {
						}


						// temporary hack to display success at the end
						// remove the next line when we use the full blown
						// version of the security module
						dataVisibilityOptions.setSelectedIndex(2);
						
						LoginFactory.getUser().setDataVisibility((short) (dataVisibilityOptions
								.getSelectedIndex() - 1));

						LoginFactory.getUser().setLogData((short) (logPreferences
								.getSelectedIndex() - 1));

						if (LoginFactory.userUpdate()) {
							String msg = "Data Visibility Saved";

							JOptionPane.showMessageDialog(null, msg);
						} else {
							String msg = "Data Visibility update failed";

							JOptionPane.showMessageDialog(null, msg);

						}
						// }
					}
					return null;
				}
			};
			worker.execute();
		}
	}


	/**
	 * This method fulfills the contract of the {@link VisualPlugin} interface.
	 * It returns the GUI component for this visual plugin.
	 */
	@Override
	public Component getComponent() {
		// In this case, this object is also the GUI component.
		return this;
	}

	public static void main(String args[]) {
		DataVisibility dv = new DataVisibility();
		JFrame test = new JFrame();
		test.add(dv);
		test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		test.setSize(400, 200);
		test.setVisible(true);
	}
}
