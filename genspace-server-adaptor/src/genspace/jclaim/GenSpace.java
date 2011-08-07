package genspace.jclaim;
import genspace.GenSpaceServerFactory;
import genspace.RuntimeEnvironmentSettings;
import genspace.db.EventServer;
import genspace.db.ISBUServer;
import genspace.db.SecurityServer;
import genspace.db.ToolServer;
import genspace.db.WorkflowVisualizationServer;
import genspace.scripts.ScriptRunner;

public class GenSpace {
	
	public static void main(String[] args) throws Exception {
		GenSpaceServerFactory.init();

		EventServer es = new EventServer(RuntimeEnvironmentSettings.EVENT_SERVER_PORT);
        es.start();
        WorkflowVisualizationServer wvs = new WorkflowVisualizationServer(RuntimeEnvironmentSettings.WORKFLOWVIS_SERVER_PORT);
        wvs.start();
        ToolServer ts = new ToolServer(RuntimeEnvironmentSettings.TOOL_SERVER_PORT);
        ts.start();
        ISBUServer isbus = new ISBUServer(RuntimeEnvironmentSettings.ISBU_SERVER_PORT);
        isbus.start();
        SecurityServer security = new SecurityServer(RuntimeEnvironmentSettings.SECURITY_SERVER_PORT);
        security.start();
//        ScriptRunner scriptrunner = new ScriptRunner();
//        while (true) { // Simplified version of "stick around" wait
//            System.out.println("waiting");
//            Thread.sleep(60*60*1000);
//        }
        
    } // main()

}
