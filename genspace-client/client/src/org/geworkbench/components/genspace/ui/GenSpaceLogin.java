//package genspace.ui;
package org.geworkbench.components.genspace.ui;

import org.geworkbench.components.genspace.bean.RegisterBean;
import org.geworkbench.engine.config.VisualPlugin;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JLabel;

import sun.misc.BASE64Encoder;

import java.awt.Component;
import java.io.*;
import java.net.Socket;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This is an example geWorkbench component.
 *
 * 
 */
// This annotation lists the data set types that this component accepts.
// The component will only appear when a data set of the appropriate type is selected.
//@AcceptTypes({DSMicroarraySet.class})
public class GenSpaceLogin extends JPanel implements VisualPlugin, ActionListener {

    private JLabel l1, l2, l3;
    private JTextField tf;
    private JPasswordField pf;
    private JButton b1, b2, b3;
    private String filename = "genspace.txt";
    private String hash = "MD5";

    // to indicate whether the user is logged in or not
    public static boolean isLoggedIn = false;
    
    // the user's login ID
    public static String genspaceLogin = null;
     
    // a list of all ActionListeners waiting for login events
    private ArrayList<ActionListener> listeners = new ArrayList<ActionListener>();
    
    // the surrounding JFrame when this is used as a standalone window
    private JFrame frame;
    
    GenSpaceRegistration panel;
    
    /**
     * Constructor
     */
    public GenSpaceLogin() {
        l1 = new JLabel("Login");
        l2 = new JLabel("Password");
        l3 = new JLabel("");
        tf = new JTextField(10);
        pf = new JPasswordField(10);
        b1 = new JButton("Login");
        b2 = new JButton("Clear");
        b3 = new JButton("Register");
        l3.setVisible(true);
        add(l1);
        add(tf);
        add(l2);
        add(pf);
        add(b1);
        add(b2);
        add(b3);
        add(l3);
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
    }
    
    /**
     * Action Listener
     */
    public void actionPerformed(ActionEvent e) {
    	if (e.getSource() == b1) {
        		
    		b1.setEnabled(false);

    		try
    		{
    			StringBuffer errMsg = new StringBuffer();
    			if(isValid(errMsg))
    			{
    			//LoginManager manager = LoginManager.getInstance();
    			//manager.save(getBean());
    				LoginManager manager = new LoginManager(getBean());
    				
    				boolean userLogin = manager.userLogin();
    				
    				if (userLogin) { 
    					String msg="User Logged in.";
    					JOptionPane.showMessageDialog(this, msg);
    					GenSpaceSecurityPanel p = new GenSpaceSecurityPanel(getBean().getUsername());
    					this.removeAll();
    					this.add(p);
    					this.setSize(500, 500);
    					this.revalidate();

    				} else {
    					String msg="User Log in failed.";
    					 
    					JOptionPane.showMessageDialog(this, msg);
    				}
    			}
    			else {
    				JOptionPane.showMessageDialog(this, errMsg.toString(),
                            "Error Information", JOptionPane.INFORMATION_MESSAGE);
    				
    				this.revalidate();
    			}
    		}
    		catch (Exception ex) { }
    		b1.setEnabled(true);
        	
    		/*
    		if (check() == 0) {
    			l3.setText("Login Successful");
    			isLoggedIn = true;
    		}
    		else {
    			l3.setText("Login Failed");
    			isLoggedIn = false;
    		}
    		l3.setVisible(true);
    		*/
    	}
    	else if (e.getSource() == b2) {
    		tf.setText("");
    		pf.setText("");
    		l3.setVisible(false);
    	}
    	else if (e.getSource() == b3) {
    		callRegisterMember();
        	
    		if (create() == 0) {
    			l3.setText("Login Created");
    			isLoggedIn = true;
    		}
    		else {
    			l3.setText("Login Creation Failed");
    			isLoggedIn = false;
    		}
    		l3.setVisible(true);
    	}	


    	/*
    	// if they're logged in, do some bookkeeping and cleanup
    	if (isLoggedIn)
    	{
    		// may want a different event but it doesn't matter for now
    		notifyActionListeners(e);
    		// TODO: make sure this doesn't break everything when it's a genspace plugin
    		if (frame != null) frame.setVisible(false);
    	}
    	*/
    }

	private boolean empty(String str)
	{
		if("".equalsIgnoreCase(str) || null == str)
			return true;
		else
			return false;
	}
	
	public boolean isValid(StringBuffer msg) {

		 String id = tf.getText();
		 char input[] = pf.getPassword();
		 String pw = new String(input);

		 boolean valid = true;

		 if(empty(id))
		 {
		 	msg.append("UserId cannot be empty\n");
		 	valid = false;
		 }
		 if(empty(pw))
		 {
			 msg.append("Pasword cannot be empty\n");
			 valid = false;
		 }

		 Pattern pattern;
		 Matcher matcher;
			// user name special character validation
	     if(!empty(id))
	     {
			 pattern = 
		            Pattern.compile("[^0-9a-zA-Z()-_]");
	
		     matcher = 
		            pattern.matcher(id);
		     if(matcher.find()) 
		     {
		    	 msg.append("Invalid user name.\n");
		    	 valid = false;
		     }
	     }
		 

	     System.out.println("valid : " + valid);
		 return valid;
	}
    
    private static boolean isPasswordCorrect(char[] input) {
        boolean isCorrect = true;
        char[] correctPassword = { 'b', 'u', 'g', 'a', 'b', 'o', 'o' };
        String test = new String(input);
        System.out.println("co : " + test);

        if (input.length != correctPassword.length) {
            isCorrect = false;
        } else {
            isCorrect = Arrays.equals (input, correctPassword);
        }
        
        //Zero out the password.
        Arrays.fill(correctPassword,'0');

        return isCorrect;
    }

	private static String getEncryptPassword(String password) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		try {
			md.update(password.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return (new BASE64Encoder()).encode(md.digest());
	}
  
	private RegisterBean getBean()
	{
		RegisterBean bean = new RegisterBean();
		bean.setMessage("Login");	//This is set to Login for logging in. Appropriately set in Jpanel for registration/login
		bean.setUName(tf.getText());
		char[] pass = pf.getPassword();
		String test = "TEST";
		System.out.println("Pass 1: "+ pass);
		System.out.println("Pass 2: "+ getEncryptPassword(test));
		System.out.println("Pass 3: "+ getEncryptPassword(test));
		
		bean.setPassword(pass);
		System.out.println("co : " + isPasswordCorrect(pass));
		bean.setFName("");
		bean.setLName("");
		bean.setLabAffiliation("");
		bean.setEmail("");
		bean.setPhoneNumber("");
		bean.setAddr1("");
		bean.setAddr2("");
		bean.setCity("");
		bean.setState("");
		bean.setZipcode("");		
		return bean;
	}    
     
    /**
     * Checks if a login is valid
     * @return 0 is valid, -1 otherwise
     */
    private int check()
    {
    	return check(tf.getText(), new String(pf.getPassword()));
    }
    
    /**
     * Checks if a login is valid
     * @return 0 is valid, -1 otherwise
     */
    public int check(String login, String pass) {
    	try{
    		String encrypted_pass = getEncodedString(pass);
    		BufferedReader br = new BufferedReader(new FileReader(filename));
    		String file_login = br.readLine();
    		String file_encrypted_pass = br.readLine();
    		br.close();
    		if (login.equals(file_login) && encrypted_pass.equals(file_encrypted_pass)) {
    			genspaceLogin = login;
    			return 0;
    		}
    		else {
    			return -1;
    		}
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    		return -1;
    	}
    }

    /**
     * Converts a byte stream to a String
     * @param b The byte stream
     * @return The converted String
     */
    public String bytesToHex(byte[] b) {
		char hexDigit[] = {'0', '1', '2', '3', '4', '5', '6', '7',
				'8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
		StringBuffer buf = new StringBuffer();
		for (int j=0; j<b.length; j++) {
			buf.append(hexDigit[(b[j] >> 4) & 0x0f]);
			buf.append(hexDigit[b[j] & 0x0f]);
		}
		return buf.toString();
	}
    
    /**
     * Creates a Hash
     * @param clearText The Clear-Text String
     * @return The Hashed String
     */
    private String getEncodedString(String clearText) {
    	try {
	    	MessageDigest md = MessageDigest.getInstance(hash);
	    	md.update(clearText.getBytes());
	    	byte[] output = md.digest();
	    	return bytesToHex(output);
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    	}
    	return "";
    }
    
    /**
     * Creates a Login
     * @return 0 If Successful, -1 otherwise
     */
    private int create() 
    {
    	return create(tf.getText(), new String(pf.getPassword()));
    }

    /**
     * Creates a Login
     * @return 0 If Successful, -1 otherwise
     */
    public int create(String login, String pass) {
    	try{
    		String encrypted_pass = getEncodedString(pass);
    		FileWriter fw = new FileWriter(filename);
    		fw.write(login);
    		fw.write("\n");
    		fw.write(encrypted_pass);
    		fw.close();
    		genspaceLogin = login;
    		return 0;
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    		return -1;
    	}
    }

    /**
     * This method fulfills the contract of the {@link VisualPlugin} interface.
     * It returns the GUI component for this visual plugin.
     */
    public Component getComponent() {
        // In this case, this object is also the GUI component.
        return this;
    }


    
    /**
     * For notifying other components of a login event.
     */
    public void addActionListener(ActionListener al)
    {
    	listeners.add(al);
    }
    
    public void notifyActionListeners(ActionEvent e)
    {
    	for (ActionListener al : listeners)
    	{
    		al.actionPerformed(e);
    	}
    }
    
    private void callRegisterMember() {
		System.out.println("Register");
		
		/*
    	panel = new  GenSpaceRegistration();
    	panel.initFrame();
    	panel.showFrame();
    	
    	frame.dispose();
    	*/
		
		panel = new GenSpaceRegistration();
		this.removeAll();
		this.add(panel);
		this.setSize(500, 500);
		this.revalidate();
    }    
    
    /**
     * For when we want to show this panel in its own frame.
     */
    public void initFrame()
    {
    	frame = new JFrame();
    	frame.add(this);
    	frame.setSize(400,400);
    	frame.setLocation(0,0);
    	frame.setResizable(false);
    	frame.setTitle("Please login or register before starting jClaim");  
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * For when we want to show this panel in its own frame.
     */
    public void showFrame()
    {
    	frame.setVisible(true); 
    }
    
    /**
     * For when we want to show this panel in its own frame.
     */
    public void hideFrame()
    {
    	frame.setVisible(false);   
    }

    public static void main(String[] args)
    {
    	GenSpaceLogin login = new GenSpaceLogin();
   	
    	login.initFrame();
    	login.showFrame();
    }
}
