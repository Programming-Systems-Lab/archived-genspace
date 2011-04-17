package org.geworkbench.components.genspace.chat;

import java.util.HashMap;
<<<<<<< HEAD
=======
import java.util.Scanner;
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7
import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;

import org.geworkbench.components.genspace.GenSpace;
import org.geworkbench.components.genspace.RuntimeEnvironmentSettings;
import org.geworkbench.components.genspace.ui.chat.ChatWindow;
import org.geworkbench.components.genspace.ui.chat.RosterFrame;
<<<<<<< HEAD
=======
import org.geworkbench.components.genspace.ui.chat.ChatWindow.messageTypes;
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7
import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManager;
import org.jivesoftware.smack.ChatManagerListener;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.Roster;
<<<<<<< HEAD
import org.jivesoftware.smack.SASLAuthentication;
=======
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;

/**
 * The ChatReceiver is a generic singleton for chat connections, delegating all
 * messages and chats
 * 
 * @author jon
 * 
 */
public class ChatReceiver implements MessageListener, ChatManagerListener {
	public HashMap<String, ChatWindow> chats = new HashMap<String, ChatWindow>();
	public static ChatManager manager;
	public static XMPPConnection connection;
<<<<<<< HEAD

=======
	public static void main(String[] args) {
//		XMPPConnection.DEBUG_ENABLED = true;

		ChatReceiver rec = new ChatReceiver();
		rec.login("jontest", "test123");
		Scanner s = new Scanner(System.in);
		while(s.hasNextLine())
		{
				break;
		}
	}
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7
	public ChatReceiver() {
	}

	/**
	 * Login to the chat server
	 * 
	 * @param u
	 *            Username
	 * @param p
	 *            Password
	 */
	public void login(final String u, final String p) {
		final ChatReceiver thisRecvr = this;
		SwingWorker<Boolean, Void> worker = new SwingWorker<Boolean, Void>()
		{
			@Override
			protected void done() {
				try {
					if(get())
					{
						Presence pr = new Presence(Presence.Type.available);
						pr.setStatus("On genSpace...");
						connection.sendPacket(pr);

						manager = connection.getChatManager();
						Roster r = connection.getRoster();
						r.setSubscriptionMode(Roster.SubscriptionMode.accept_all);
						rf = new RosterFrame();
<<<<<<< HEAD
=======
						rf.setSize(240, 500);
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7
						rf.setRoster(r);
						rf.setVisible(true);

						manager.addChatListener(thisRecvr);
					}
				} catch (InterruptedException e) {
				} catch (ExecutionException e) {
				}
				super.done();
			}
			@Override
			protected Boolean doInBackground() throws Exception {
				ConnectionConfiguration config = new ConnectionConfiguration(
<<<<<<< HEAD
						RuntimeEnvironmentSettings.XMPP_HOST, 5228);
				// SmackConfiguration.setPacketReplyTimeout(1500000);
				config.setSASLAuthenticationEnabled(false);

				connection = new XMPPConnection(config);

				SASLAuthentication.supportSASLMechanism("PLAIN", 0);
				try {
					connection.connect();
=======
						RuntimeEnvironmentSettings.XMPP_HOST, 5222,"genspace");
				// SmackConfiguration.setPacketReplyTimeout(1500000);
//				config.setSASLAuthenticationEnabled(false);

				connection = new XMPPConnection(config);

//				SASLAuthentication.supportSASLMechanism("PLAIN", 0);
				try {
					connection.connect();
//					System.out.println("Connected");
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7
					connection.login(u, p);

				} catch (XMPPException e) {
					GenSpace.logger.error("Unable to connect to chat server",e);
					return false;
				}
				return true;
			}
			
		};
<<<<<<< HEAD
=======
		worker.execute();
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7
	}

	public RosterFrame rf;

	/**
	 * Callback for chats created
	 */
	@Override
	public void chatCreated(Chat c, boolean createdLocal) {
		if (createdLocal) {
			final ChatWindow nc = new ChatWindow();
			nc.setChat(c);
			nc.setVisible(true);
			chats.put(c.getParticipant(), nc);
		}
		c.addMessageListener(this);
	}

	/**
	 * Delegate messages to the correct chat window
	 */
	@Override
	public void processMessage(Chat c, Message m) {
<<<<<<< HEAD
=======
		if((m.getProperty("specialType") == null || m.getProperty("specialType").equals(ChatWindow.messageTypes.CHAT)) && (m.getBody() == null || m.getBody().equals("")))
			return;
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7
		if (chats.containsKey(c.getParticipant())) {
			chats.get(c.getParticipant()).processMessage(m);
		} else {
			final ChatWindow nc = new ChatWindow();
			nc.setChat(c);
			nc.setVisible(true);
			nc.processMessage(m);
			chats.put(c.getParticipant(), nc);

		}
	}

	public void logout() {
<<<<<<< HEAD
=======
		if(connection != null)
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7
		connection.disconnect();
	}
}
