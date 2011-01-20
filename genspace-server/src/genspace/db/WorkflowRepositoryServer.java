package genspace.db;

import genspace.RuntimeEnvironmentSettings;
import genspace.common.Logger;
import genspace.db.ToolServer.ToolServerHandler;

import java.io.*;
import java.net.*;
import java.util.*;


import org.geworkbench.components.genspace.bean.RatingBean;
import org.geworkbench.components.genspace.bean.User;
import org.geworkbench.components.genspace.bean.UserWorkflow;
import org.geworkbench.components.genspace.bean.Workflow;
import org.geworkbench.components.genspace.bean.WorkflowComment;
import org.geworkbench.components.genspace.bean.WorkflowInbox;

public class WorkflowRepositoryServer extends Server {

	public static final int DEFAULT_PORT = RuntimeEnvironmentSettings.WORKFLOW_REPOSITORY_PORT;

	public static void main(String args[]) throws Exception {

		int port = DEFAULT_PORT;
		if (args.length >= 1) {
			port = Integer.parseInt(args[0]);
		} else {
			System.out.println("Port not specified, using " + port	+ " as default");
		}

		WorkflowRepositoryServer s = new WorkflowRepositoryServer(port);
		s.run();

	}

	public WorkflowRepositoryServer(int port) {
		super(port);

	}

	public void run() {
		try {
			System.out.println("Workflow Repository Server started...");
			
			// TODO: need a graceful shutdown
			while (true) {
				try {
					// wait for a client
					Socket socket = server.accept();

					// spin off a new thread
					WFRHandler h = new WFRHandler(socket);
					h.start();

				} catch (Exception e) {
					e.printStackTrace();
					if (Logger.isLogError())
						Logger.logError(e);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

}

class WFRHandler extends ServerHandler {

	private Socket s;
	private String clientSideID = null;
	private org.apache.log4j.Logger log;

	public WFRHandler(Socket s) {
		super(s);
		this.s = s;
		log = util.MyLogger.getInstance(this.getClass().getName());
	}

	public void run() {
		try {
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			clientSideID = (String) ois.readObject();
			ArrayList<Object> params = (ArrayList<Object>) ois.readObject();
			log.info(clientSideID+" params:" + params+ ", size: "+params.size());
			UserManager um = new UserManager();
			if (clientSideID.contains("new_folder")) {
				String username = (String) params.get(0);
				String foldername = (String) params.get(1);
				um.createUserFolder(username, foldername);
				respond("");//Success, no error message
			}
			else if (clientSideID.contains("add_to_folder")) {
				String username = (String) params.get(0);
				int workflowId = (Integer) params.get(1);
				String foldername = (String) params.get(2);
				um.addWorkflowToFolder(username, workflowId, foldername);
				respond("");//Success, no error message
			}
			else if (clientSideID.contains("delete_workflow")) {
				String username = (String) params.get(0);
				int workflowId = (Integer) params.get(1);
				um.deleteWorkflow(username, workflowId);
				respond("");//Success, no error message
			}
			else if (clientSideID.contains("delete_folder")) {
				String username = (String) params.get(0);
				String foldername = (String) params.get(1);
				um.deleteFolder(username, foldername);
				respond("");//Success, no error message
			} 
			else if (clientSideID.contains("delete_inbox")) {
				String username = (String) params.get(0);
				WorkflowInbox wi = (WorkflowInbox) params.get(1);
				um.deleteWorkflowInbox(username, wi);
				respond("");//Success, no error message
			} 
			else if (clientSideID.contains("add_to_repository_from_inbox")) {
				String username = (String) params.get(0);
				WorkflowInbox wi = (WorkflowInbox) params.get(1);
				respond(um.addAndGetUserWorkflow(username, wi.workflow.ID, wi.name));
			} 
			else if (clientSideID.contains("send_workflow")) {
				String username = (String) params.get(0);
				String receiver = (String) params.get(1);
				WorkflowInbox wi = (WorkflowInbox) params.get(2);
				um.sendWorkflow(receiver, wi);
				respond("");
			} 
			else if (clientSideID.contains("get_user")) {
				String username = (String) params.get(0);
				User u = um.getUser(username);
				respond(u);
			} 
			else if (clientSideID.contains("new_comment")) {
				String username = (String) params.get(0);
				WorkflowComment wc = (WorkflowComment) params.get(1);
				Workflow w = (Workflow) params.get(2);
				int pk = um.storeComment(wc, w);
				respond(pk);
			} 
			else if (clientSideID.contains("remove_comment")) {
				String username = (String) params.get(0);
				WorkflowComment wc = (WorkflowComment) params.get(1);
				Workflow w = (Workflow) params.get(2);
				um.deleteComment(wc, w);
				respond("");
			} 
			else {
				System.err.println("Invalid Client side ID!!");
				respond("Invalid Client side ID!!");
			}

		} catch (Exception e) {
			e.printStackTrace();
			try{
				respond(e.getMessage());
			}
			catch(Exception e2){
				e2.printStackTrace();
			}
		}
	}
}
