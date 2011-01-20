package org.geworkbench.components.genspace.bean.networks;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import org.geworkbench.components.genspace.RuntimeEnvironmentSettings;
import org.geworkbench.components.genspace.ui.GenSpaceLogin;

public abstract class Facade {
	private static final int PORT = RuntimeEnvironmentSettings.SOCIAL_SERVER
			.getPort();
	private static final String HOST = RuntimeEnvironmentSettings.SOCIAL_SERVER
			.getHost();

	public boolean amLoggedIn() {
		return GenSpaceLogin.isLoggedIn;
	}

	/**
	 * Send a network message and process the request.
	 * 
	 * DO NOT EVER, EVER, EVER CALL THIS DIRECTLY FROM A SWING EVENT RESPONDER
	 * 
	 * YOU MUST USE A SWINGWORKER!!!!!!!
	 * 
	 * @param m
	 * @return
	 */
	public NetworkMessage sendNetworkMessage(NetworkMessage m) {
		// First thing - set the sender on the message
		m.sender = GenSpaceLogin.genspaceLogin;
		// SwingWorker<, V>
		Socket s = null;
		NetworkMessage ret = null;
		try {
			s = new Socket(HOST, PORT);
			ObjectOutputStream os = new ObjectOutputStream(s.getOutputStream());
			os.writeObject(m);
			ObjectInputStream is = new ObjectInputStream(s.getInputStream());
			Object temp = is.readObject();
			ret = (NetworkMessage) temp;
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
}
// class NetworkSender extends SwingWorker<Void, V>