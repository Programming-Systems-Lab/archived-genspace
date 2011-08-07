package genspace.db;

import genspace.GenSpaceServerFactory;
import genspace.common.Logger;
import genspace.wrapper.WorkflowWrapper;

import java.net.Socket;
import java.util.Scanner;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

import org.geworkbench.components.genspace.server.stubs.Tool;
import org.geworkbench.components.genspace.server.stubs.Workflow;
import org.geworkbench.components.genspace.server.stubs.WorkflowTool;

public class WorkflowVisualizationServer extends Server {

	public static final int DEFAULT_PORT = 22222;

	/* This is the main method for starting the Server */
	public static void main(String[] args)
	{
		int port = DEFAULT_PORT;
		if (args.length >= 1)
		{
			port = Integer.parseInt(args[0]);
		}
		else
		{
			System.out.println("Port not specified, using " + port + " as default");
		}

		WorkflowVisualizationServer s = new WorkflowVisualizationServer(port);
		s.run();
	}

	/**
	 * Creates a socket server listening on the specified port
	 * @param port the port to listen to
	 */
	public WorkflowVisualizationServer(int port)
	{
		super(port);
	}

	/**
	 * This method does all the work
	 */
	public void run()
	{
		while (true)
		{
			try
			{
				// wait for a client
				Socket socket = server.accept();
				//System.out.println("Connection established");

				// spin off a new thread
				WorkflowVisualizationServerThread h = new WorkflowVisualizationServerThread(socket);
				h.start();
			}
			catch (Exception e)
			{
				e.printStackTrace();
				if (Logger.isLogError()) Logger.logError(e);
			}
		}
	}


	/**
	 * A class to handle the actual work of dealing with events
	 *
	 */
	class WorkflowVisualizationServerThread extends Thread
	{
		Socket socket = null;
		private org.apache.log4j.Logger log;
		HashMap<String, Tool> toolsMap = new HashMap<String,Tool>();
		WorkflowVisualizationServerThread(Socket s)
		{
			socket = s;
			log = util.MyLogger.getInstance(this.getClass().getName());
			for(Tool t : GenSpaceServerFactory.getUsageOps().getAllTools())
			{
				toolsMap.put(t.getName(), t);
			}
		}

		public void run()
		{
			// the input stream for reading from the network
			Scanner in = null; 
			PrintWriter pw;

			try
			{
				// get the input stream
				in = new Scanner(socket.getInputStream());

				String type = in.nextLine();
				//    			System.out.println("Type: " + type);
				log.info("Type: " + type);



				if (type.equals("ALL")) {
					// read the analysis name
					String analysis = in.nextLine();
					//System.out.println("Analysis: " + analysis);
					log.info("Analysis: " + analysis);

					// read the username and whether or not to limit the search
					String login = in.nextLine();
					boolean myNetworks = in.nextBoolean();
					//System.out.println("Login: " + login + " " + myNetworks);


					pw = new PrintWriter(socket.getOutputStream());
					System.out.println(toolsMap);
					for(Workflow w : GenSpaceServerFactory.getUsageOps().getAllWorkflowsIncluding(toolsMap.get(analysis).getId()))
					{
						WorkflowWrapper wr = new WorkflowWrapper(w);
						wr.loadToolsFromCache();
						int i = 0;
						for(WorkflowTool wt : wr.getTools())
						{
							pw.write(wt.getTool().getName());
							if(i < wr.getTools().size() - 1)
								pw.write(",");
							i++;
						}
						pw.write("\n");
					}
					pw.close();
				} else if (type.equals("START")) {
					// read the analysis name
					String analysis = in.nextLine();
					//System.out.println("Analysis: " + analysis);
					log.info("Analysis: " + analysis);

					// read the username and whether or not to limit the search
					String login = in.nextLine();
					boolean myNetworks = in.nextBoolean();
					//System.out.println("Login: " + login + " " + myNetworks);

					List<Workflow> ws = GenSpaceServerFactory.getUsageOps().getMostPopularWorkflowStartingWith(toolsMap.get(analysis).getId());

					Workflow w = ws.get(0);
					pw = new PrintWriter(socket.getOutputStream());
					WorkflowWrapper wr = new WorkflowWrapper(w);
					wr.loadToolsFromCache();
					int i = 0;
					for(WorkflowTool wt : wr.getTools())
					{
						pw.write(wt.getTool().getName());
						if(i < wr.getTools().size() - 1)
							pw.write(",");
						i++;
					}
					pw.write("\n");
					pw.flush();
					pw.write("END\n");
					pw.close();
				} else if (type.equals("INCLUDE")) {
					// read the analysis name
					String analysis = in.nextLine();
					//System.out.println("Analysis: " + analysis);
					log.info("Analysis: " + analysis);

					// read the username and whether or not to limit the search
					String login = in.nextLine();
					boolean myNetworks = in.nextBoolean();
					//System.out.println("Login: " + login + " " + myNetworks);

					List<Workflow> ws = GenSpaceServerFactory.getUsageOps().getMostPopularWorkflowIncluding(toolsMap.get(analysis).getId());

					Workflow w = ws.get(0);
					pw = new PrintWriter(socket.getOutputStream());
					WorkflowWrapper wr = new WorkflowWrapper(w);
					wr.loadToolsFromCache();
					int i = 0;
					for(WorkflowTool wt : wr.getTools())
					{
						pw.write(wt.getTool().getName());
						if(i < wr.getTools().size() - 1)
							pw.write(",");
						i++;
					}
					pw.write("\n");
					pw.flush();
					pw.write("END\n");
					pw.close();
				}
				else if (type.equals("TOOLS"))
				{
					pw = new PrintWriter(socket.getOutputStream());
					for (String tool : toolsMap.keySet())
					{
						pw.println(tool);
						//System.out.println(tool);
						pw.flush();
					}
					pw.println("END");
					pw.flush();
				}

			}
			catch (Exception e)
			{
				e.printStackTrace();
				if (Logger.isLogError()) Logger.logError(e);
			}
		}
	}
}
