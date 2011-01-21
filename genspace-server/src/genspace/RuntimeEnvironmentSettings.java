package genspace;

public class RuntimeEnvironmentSettings {

	
	//Server startup ports
	public static final int LOGIN_SERVER_PORT = 22340;
	public static final int EVENT_SERVER_PORT = 22346;
	public static final int SOCIAL_NETWORK_SERVER_PORT = 22342;
	public static final int WORKFLOWVIS_SERVER_PORT = 22343;
	public static final int NETWORKVIS_SERVER_PORT = 22344;
	public static final int ISBU_SERVER_PORT = 22345;
	public static final int TOOL_SERVER_PORT = 22341;
	public static final int SECURITY_SERVER_PORT = 22347;
	public static final int WORKFLOW_REPOSITORY_PORT = 22349;
	public static final int SOCIAL_SERVER_PORT = 22348;
	
	//Database connection constants
	public static final String DB_HOST = "boris.cs.columbia.edu";
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
    
    //Constants for the Tigase XMPP database
    public static final String Tig_DB_URI="jdbc:mysql://boris.cs.columbia.edu/tigase_xmpp?username=student&password=password";
    public static final String TIG_DB_USER="student";
    public static final String TIG_DB_PASS="password";
    public static final String XMPP_HOST="lenox.cs.columbia.edu";
}
