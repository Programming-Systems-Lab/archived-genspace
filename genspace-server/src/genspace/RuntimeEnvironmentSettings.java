package genspace;

import genspace.db.EventServer;
import genspace.db.ISBUServer;
import genspace.db.LoginServer;
import genspace.db.NetworkVisualizationServer;
import genspace.db.SocialNetworkServer;
import genspace.db.ToolServer;
import genspace.db.WorkflowVisualizationServer;



public class RuntimeEnvironmentSettings {

	
	//Server startup ports
	public static final int LOGIN_SERVER_PORT = 12340;
	public static final int EVENT_SERVER_PORT = 12346;
	public static final int SOCIAL_NETWORK_SERVER_PORT = 12342;
	public static final int WORKFLOWVIS_SERVER_PORT = 12343;
	public static final int NETWORKVIS_SERVER_PORT = 12344;
	public static final int ISBU_SERVER_PORT = 12345;
	public static final int TOOL_SERVER_PORT = 12341;
	public static final int SECURITY_SERVER_PORT = 12347;
	
	
	
	//Database connection constants
	public static final String DB_HOST = "bambi.cs.columbia.edu";
	public static final String DB_PORT = "1433";
	public static final String DB_USER = "student";
	public static final String DB_PASS = "password";
	public static final String DB_URL = "jdbc:sqlserver://";
	public static final String DB_NAME = "Genspace";
	
	public static final String DEFAULT_USER = "";
	
	//ISBU
	public static final String ISBU_FILE_PATH = "DataAnalysisResult.gs";
	
	//Constants for instant messaging system
	// Yahoo!
    public static final String IM_YAHOO_USER = "gen.space";
    public static final String IM_YAHOO_PASS = "gen123space";

    // MSN 
    public static final String IM_MSN_USER = "genspace@hotmail.com";
    public static final String IM_MSN_PASS = "gen123space";
    
    // Google - not currently working
    public static final String IM_GOOGLE_USER = "genspace@gmail.com";
    public static final String IM_GOOGLE_PASS = "gen123space";
    
    // AOL - not tested
    public static final String IM_AOL_USER = "genspac";
    public static final String IM_AOL_PASS = "g3n123sp4ce";
}
