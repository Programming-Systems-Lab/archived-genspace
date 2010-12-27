package genspace.db;

import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

public abstract class ServerHandler extends Thread{
	protected Socket socket = null;
	
	public ServerHandler(Socket s){
		socket = s;
	}
	
	public abstract void run();
	
	public void respond(Serializable s) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			System.out.println("Response: " + s);
			oos.writeObject(s);
			oos.flush();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
