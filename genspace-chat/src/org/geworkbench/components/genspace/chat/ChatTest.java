package org.geworkbench.components.genspace.chat;

import java.util.Collection;

import org.geworkbench.components.genspace.ui.chat.RosterFrame;
import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManager;
import org.jivesoftware.smack.ChatManagerListener;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.SASLAuthentication;
import org.jivesoftware.smack.SmackConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;


public class ChatTest {
	
	public static void main(String[] args) {
//		 XMPPConnection.DEBUG_ENABLED = true;
		 XMPPConnection.DEBUG_ENABLED = true;
ChatReceiver t = new ChatReceiver();
	t.login("jon2", "test");
		while(true)
		{
		
		}
	}

	
}
