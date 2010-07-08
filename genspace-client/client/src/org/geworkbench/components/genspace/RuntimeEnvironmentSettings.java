package org.geworkbench.components.genspace;

public class RuntimeEnvironmentSettings {
	
	public static final String YORK = "york.cs.columbia.edu";
	public static final String BORIS = "boris.cs.columbia.edu";
	public static final String BAMBI = "bambi.cs.columbia.edu";
	
	public static final String PROD_HOST = BAMBI;
	public static final String DEVEL_HOST = BORIS;

	public static final ServerConfig EVENT_SERVER = new ServerConfig(PROD_HOST, 12346);
	public static final ServerConfig WORKFLOW_SERVER = new ServerConfig(PROD_HOST, 22222);
	public static final ServerConfig NETWORK_VIS_SERVER = new ServerConfig(DEVEL_HOST, 8282);
	public static final ServerConfig LOOKUP_SERVER = new ServerConfig(PROD_HOST, 12346);
	public static final ServerConfig ISBU_SERVER = new ServerConfig(BORIS, 12345);
	public static final ServerConfig TOOL_SERVER = new ServerConfig(BORIS, 12346);
	
	
	public static final String LOOKUP_HOST = "york.cs.columbia.edu";
	public static final int LOOKUP_PORT = 44444;

	public static final String DEFAULT_USER = "";
	
	public static final String GS_WEB_ROOT = "http://lenox.cs.columbia.edu/genspace/";
}
