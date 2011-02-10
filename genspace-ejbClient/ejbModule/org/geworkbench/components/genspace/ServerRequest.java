package org.geworkbench.components.genspace;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;

import javax.net.ssl.SSLSocket;

public class ServerRequest {

	public static Serializable get(ServerConfig config, String command,
			ArrayList arg) {
		// send request to server
		Socket s = null;
		
		Serializable result = null;

		//TODO
//		try {
//			s = (SSLSocket) GenSpaceSSLSocketFactory.createSocket(config.getHost(), config.getPort());
//
//			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
//
//			// send the action keyword and the name of the tool
//			oos.writeObject(command);
//			oos.writeObject(arg);
//			oos.flush();
//
//			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
//			result = (Serializable) ois.readObject();
//
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		} finally {
//			try {
//				s.close();
//			} catch (Exception ex) {
//			}
//		}
		return result;
	}

	public static void main(String s[]) {

		ArrayList args = new ArrayList();
		args.add("ARACNE");

//		System.out.println(ServerRequest.get(
//				RuntimeEnvironmentSettings.TOOL_SERVER, "getToolId", args));

	}

}
