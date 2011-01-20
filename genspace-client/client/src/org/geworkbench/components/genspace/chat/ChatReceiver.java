package org.geworkbench.components.genspace.chat;

import java.util.HashMap;

import org.geworkbench.components.genspace.RuntimeEnvironmentSettings;
import org.geworkbench.components.genspace.ui.chat.ChatWindow;
import org.geworkbench.components.genspace.ui.chat.RosterFrame;
import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManager;
import org.jivesoftware.smack.ChatManagerListener;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.SASLAuthentication;
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
	public void login(String u, String p) {
		ConnectionConfiguration config = new ConnectionConfiguration(
				RuntimeEnvironmentSettings.XMPP_HOST, 5228);
		// SmackConfiguration.setPacketReplyTimeout(1500000);
		config.setSASLAuthenticationEnabled(false);

		connection = new XMPPConnection(config);

		SASLAuthentication.supportSASLMechanism("PLAIN", 0);
		try {
			connection.connect();
			connection.login(u, p);

		} catch (XMPPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Presence pr = new Presence(Presence.Type.available);
		pr.setStatus("On genSpace...");
		connection.sendPacket(pr);

		manager = connection.getChatManager();
		Roster r = connection.getRoster();
		r.setSubscriptionMode(Roster.SubscriptionMode.accept_all);
		rf = new RosterFrame();
		rf.setRoster(r);
		rf.setVisible(true);

		manager.addChatListener(this);
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
		connection.disconnect();
	}
}
