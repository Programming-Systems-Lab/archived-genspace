package genspace.db;

import org.geworkbench.components.genspace.bean.*;

import java.io.*;
import java.net.*;
import java.util.Scanner;


public class SecurityServer extends Server{
	public static final int DEFAULT_PORT = 33334;
	
	public static void main(String[] args){
		int port = DEFAULT_PORT;
		if (args.length >= 1)
		{
			port = Integer.parseInt(args[0]);
		}
		else
		{
			System.out.println("Port not specified, using " + port + " as default");
		}
		
		SecurityServer ss = new SecurityServer(port);
		ss.run();
	}
	
	/**
     * Creates a socket server listening on the specified port
     * @param port the port to listen to
     */
	public SecurityServer(int port){
		super(port);
	}
	
	 /**
     * This method just listens for new connections and then hands them off to another thread (the Handler)
     */
	public void run(){
		while (true)
    	{
    		try
    		{
    			// wait for a client
    			Socket socket = server.accept();
    			System.out.println("Connection established");
    			
    			// spin off a new thread to deal with the work
    			Handler h = new Handler(socket);
    			h.start();

    		}
    		catch (Exception e)
    		{
    			e.printStackTrace();
               // if (Logger.isLogError()) Logger.logError(e);
    		}
    	}
	}
	
	class Handler extends Thread{
		Socket socket = null;
		private org.apache.log4j.Logger log;
		
		Handler(Socket s){
			socket = s;
			log = util.MyLogger.getInstance(this.getClass().getName());
		}
		
		void printBytes (byte[] bytes) {
			for (int i = 0; i<bytes.length; i++) {
				System.out.println ((char)bytes[i]);
			}
		}

		public void run(){
			int len = 0, off = 0;
			System.out.println("Started");
			try{
			

				InputStream is = socket.getInputStream();
				ByteArrayOutputStream b = new ByteArrayOutputStream();
				
				OutputStream os = socket.getOutputStream();
				OutputStreamWriter writer = new OutputStreamWriter(os);
				PrintWriter pw = new PrintWriter(writer, true);
				 
				while(true)
				{
					System.out.println("Waiting for bean");
					if(is.available() != 0)
						break;
				}

				
				byte[] buf = new byte[1024];
	
				while ((len=is.available())!=0) {
					is.read(buf);
					b.write(buf, off, len);
					off += len;
				}
				System.out.println(b.size());
				
				//System.out.println(buf.toString());
	
				Object obj = RegisterBean.read(b.toByteArray());
				
				String msg = ((SecurityMessageBean)obj).getMessage();
//				System.out.println(msg);
				log.info(msg);
				
				if( "Register".equalsIgnoreCase(msg)
					|| "Duplication".equalsIgnoreCase(msg)
					|| "login".equalsIgnoreCase(msg)
					)
					{
						RegisterBean r = (RegisterBean)obj;
						
						
						/* The following code is just for test purpose
						 * The de-serialized object needs to be passed to the SecurityServerManager
						 * to be written by Mayur
						 */
						System.out.println("Message which says what to do: "+r.getMessage());
						
						/* If msg is register should invoke the addToDB in the SecurityServerManager
						 * If it is login should invoke the login function in Manager
						 */
						/*System.out.println("First Name: "+r.getFName()+" Last Name: "+r.getLName()+" User: "+r.getUsername());
						System.out.println("Address: "+r.getAddr1()+" , "+r.getAddr2()+" , "+r.getCity()+" , "+r.getState()+" , "+r.getZipCode()+" \nPhone: "+r.getPhoneNumber());
						System.out.println("Affiliated to: "+r.getLabAffliation()+" Working as: "+r.getWorkTitle());*/
						RegistrationServerManager mgr = new RegistrationServerManager();
						
						if (r.getMessage().equals("Duplication")) {
							boolean flag = mgr.userDupCheck(r);
							
							//TODO: send this message to LoginMgr via socket
							System.out.println("No Duplication: "+flag);
							pw.println(flag);
						} else if (r.getMessage().equals("Register")) {
							boolean flag = mgr.register(r);
							
							//TODO: send this message to LoginMgr via socket
							System.out.println("Registered: "+flag);
							pw.println(flag);
						} else if (r.getMessage().equals("Login")) {
							boolean flag = mgr.login(r);
							
							//TODO: send this message to LoginMgr via socket
							System.out.println("Login: "+flag);				
							pw.println(flag);
						}
					}
					else if("datavisibilityedit".equalsIgnoreCase(msg))
					{
						DataVisibilityBean bean = (DataVisibilityBean)obj;
						DataVisibilityServerManager dvMgr = new DataVisibilityServerManager();
						
						//commented out for streamlined version
						//boolean success1 = dvMgr.removeSelectedNetworks(bean.getUsername());
						boolean success1 = true;
						boolean success2 = dvMgr.editLoggingOption(bean);
						boolean success3 = dvMgr.setDataVisibilityWithinNetworks(bean);
						pw.println(success1 && success2 && success3);
					}
					else if("datavisibilityload".equalsIgnoreCase(msg))
					{
						DataVisibilityBean bean = (DataVisibilityBean)obj;
						String userId = bean.getUsername();
						DataVisibilityServerManager dvMgr = new DataVisibilityServerManager();
						DataVisibilityBean beanResult = dvMgr.getBeanForUser(userId);
						byte[] byteArray = beanResult.write();
						os.write(byteArray);
						os.flush();
						System.out.println("Bean fetched and  written "+byteArray.length);
					}					
					else if("networkvisibilityedit".equalsIgnoreCase(msg))
					{
						NetworkVisibilityBean bean = (NetworkVisibilityBean)obj;
						NetworkVisibilityServerManager nwMgr = new NetworkVisibilityServerManager();
						boolean success1 = nwMgr.removeSelectedNetworks(bean.getUsername());
						boolean success2 = nwMgr.setUserVisibility(bean);
						boolean success3 = nwMgr.setUserVisibilityWithinNetworks(bean);
						pw.println(success1 && success2 && success3);
					}
					else if("networkvisibilityload".equalsIgnoreCase(msg))
					{
						NetworkVisibilityBean bean = (NetworkVisibilityBean)obj;
						String userId = bean.getUsername();
						NetworkVisibilityServerManager nwMgr = new NetworkVisibilityServerManager();
						NetworkVisibilityBean beanresult = nwMgr.getBeanForUser(userId);
						byte[] byteArray = beanresult.write();
						os.write(byteArray);
						System.out.println("Bean fetched and  written "+byteArray.length);
					}					
					else if("getallnetworks".equalsIgnoreCase(msg))
					{
						System.out.println("processing getAllNetworks");
						NetworkVisibilityServerManager nwMgr = new NetworkVisibilityServerManager();
						String[] networks = nwMgr.listNetworks();
						//BufferedWriter socketBw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
						pw.println(networks.length);
						for (int i =0 ; i < networks.length ; i++)
						{
							pw.println(networks[i]);
							System.out.println("Wrote "+networks[i]);
						}
					}	
					else if("GetUSerInfo".equalsIgnoreCase(msg))
					{
						RegisterBean bean = (RegisterBean)obj;
						String userId = bean.getUsername();
						RegistrationServerManager gmgr = new RegistrationServerManager();
//						System.out.println("Server msg called");
						bean = gmgr.getUserInfo(userId);
						byte[] byteArray = bean.write();
						os.write(byteArray);
						os.flush();
					}
					else if("Update".equalsIgnoreCase(msg))
					{
						System.out.println("Entered update msg code part");
						RegisterBean reg = (RegisterBean)obj;
						String userId = reg.getUsername();
						RegistrationServerManager rmgr = new RegistrationServerManager();
						boolean flag = rmgr.updateUserInfo(reg);
						pw.println(flag);	
						pw.flush();
						System.out.println("Finished with a "+flag);
					}
				
					else
					{
						System.out.println("Unknown Message "+ msg+ " received...Ignoring it.");
					}
			}
			catch(Exception s){
				s.printStackTrace();
			}
		}
	}
	
	
}
