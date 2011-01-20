package genspace;
import genspace.db.EventServer;
import genspace.db.ISBUServer;
import genspace.db.SecurityServer;
import genspace.db.ToolServer;
import genspace.db.WorkflowRepositoryServer;
import genspace.db.WorkflowVisualizationServer;
import genspace.networks.NetworkServer;
import genspace.scripts.ScriptRunner;


public class GenSpace {
	
	public static void main(String[] args) throws Exception {


        
        // start the server to listen for incoming events
        EventServer es = new EventServer(RuntimeEnvironmentSettings.EVENT_SERVER_PORT);
        es.start();
        
        // start the server to listen for workflow visualization requests
        WorkflowVisualizationServer wvs = new WorkflowVisualizationServer(RuntimeEnvironmentSettings.WORKFLOWVIS_SERVER_PORT);
        wvs.start();
     
        // start the server to listen for tool meta info requests
        ToolServer ts = new ToolServer(RuntimeEnvironmentSettings.TOOL_SERVER_PORT);
        ts.start();
        
       ISBUServer isbus = new ISBUServer(RuntimeEnvironmentSettings.ISBU_SERVER_PORT);
       isbus.start();
        
        SecurityServer security = new SecurityServer(RuntimeEnvironmentSettings.SECURITY_SERVER_PORT);
        security.start();
        NetworkServer ns = new NetworkServer(RuntimeEnvironmentSettings.SOCIAL_SERVER_PORT);
        ns.start();

        WorkflowRepositoryServer wfr = new WorkflowRepositoryServer(RuntimeEnvironmentSettings.WORKFLOW_REPOSITORY_PORT);
        wfr.start();
        
        // start the scripts
        ScriptRunner scriptrunner = new ScriptRunner();
        while (true) { // Simplified version of "stick around" wait
            System.out.println("waiting");
            Thread.sleep(60*60*1000);
        }
        
    } // main()

}
