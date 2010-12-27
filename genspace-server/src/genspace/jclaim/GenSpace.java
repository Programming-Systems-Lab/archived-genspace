package genspace.jclaim;
import genspace.RuntimeEnvironmentSettings;
import genspace.common.Logger;
import genspace.db.ISBUServer;
import genspace.db.LoginServer;
import genspace.db.EventServer;
import genspace.db.SecurityServer;
import genspace.db.SocialNetworkServer;
import genspace.db.NetworkVisualizationServer;
import genspace.db.ToolServer;
import genspace.db.WorkflowRepositoryServer;
import genspace.db.WorkflowVisualizationServer;
import genspace.scripts.ScriptRunner;

import javax.swing.Icon;
import java.util.ArrayList;

import com.itbs.aimcer.bean.*;
import com.itbs.aimcer.commune.Connection;
import com.itbs.aimcer.commune.MessageSupport;
import com.itbs.aimcer.commune.joscar.OscarConnection;
import com.itbs.aimcer.commune.msn.*;
import com.itbs.aimcer.commune.smack.*;
import com.itbs.aimcer.commune.ymsg.*;
import com.itbs.util.GeneralUtils;

class GroupImplFactory implements GroupFactory {
            public Group create(String group) {
                return new GroupImpl();
            }
            public Group create(Group group) {
                return new GroupImpl();
            }
			public GroupList getGroupList() {
				// TODO Auto-generated method stub
				return groupList;
			}
			static GroupListImpl groupList = new GroupListImpl();
		}
    class GroupImpl implements Group {
        public int size() { return 0; }
        public void clear(Connection connection) { }
        public Nameable get(int index) { return null; }
        public Nameable add(Nameable contact) { return null; }
        public boolean remove(Nameable contact) { return false; }
        public String getName() { return "Group"; }
		public Nameable[] toArray() {
			// TODO Auto-generated method stub
			return null;
		}
    }


    class ContactImplFactory implements ContactFactory {
            public Contact create(Nameable buddy, Connection connection) {
                return create(buddy.getName(), connection);
            }


            public Contact create(String name, Connection connection) {
                return new ContactImpl(connection, name);
            }


            public Contact get(String name, Connection connection) {
                return new ContactImpl(connection, name);
            }
    }


    class ContactImpl implements Contact {
        private final Status status = new StatusImpl(this);
        private final Connection connection;
        private final String name;


        public ContactImpl(Connection connection, String name) {
            this.connection = connection;
            this.name = name;
        }
        public void statusChanged() {}
        public Icon getIcon() { return null; }
        public void setIcon(Icon icon) {}
        public Icon getPicture() { return null; }
        public void setPicture(Icon icon) { }
        public String getDisplayName() { return name; }
        public void setDisplayName(String name) {}
        public Status getStatus() { return status; }
        public Connection getConnection() { return connection; }
        public String getName() { return name; }
    }



public class GenSpace {
	
	public static void main(String[] args) throws Exception {

//		ArrayList<MessageSupport> connections = new ArrayList<MessageSupport>();
//		
//		// Yahoo!
//        MessageSupport yconn = new YMsgConnection();
//        yconn.setUserName(RuntimeEnvironmentSettings.IM_YAHOO_USER); 
//        yconn.setPassword(RuntimeEnvironmentSettings.IM_YAHOO_PASS);
//        connections.add(yconn);
//
//        // MSN 
//        MessageSupport mconn = new MSNConnection();
//        mconn.setUserName(RuntimeEnvironmentSettings.IM_MSN_USER); 
//        mconn.setPassword(RuntimeEnvironmentSettings.IM_MSN_PASS);
//        connections.add(mconn);
//        
//        // Google - not currently working
//		MessageSupport gconn = new GoogleConnection();
//        gconn.setUserName(RuntimeEnvironmentSettings.IM_GOOGLE_USER); // for Google
//        gconn.setPassword(RuntimeEnvironmentSettings.IM_GOOGLE_PASS);
//        connections.add(gconn);
//        
//        // AOL - not tested??
//		//MessageSupport aconn = new AimConnection();
//        //aconn.setUserName(RuntimeEnvironmentSettings.IM_AOL_USER); 
//        //aconn.setPassword(RuntimeEnvironmentSettings.IM_AOL_PASS);
//        //connections.add(aconn);
//        OscarConnection  aConn = new OscarConnection();
//        aConn.setUserName(RuntimeEnvironmentSettings.IM_AOL_USER);
//        aConn.setPassword(RuntimeEnvironmentSettings.IM_AOL_PASS);
//        connections.add(aConn);
//        
//
//        BotEventHandler handler = new BotEventHandler();
//
//        try {
//        	for (MessageSupport conn : connections)
//        	{
//        		conn.assignGroupFactory(new GroupImplFactory());
//        		conn.assignContactFactory(new ContactImplFactory());
//        		conn.addEventListener(handler);
//        		conn.connect();
//        		System.out.println("Connected: " + conn.getServiceName());
//        	}
//        } catch (Exception e) {
//            System.out.println("Failed to create required pieces.  Shutting down.");
//            e.printStackTrace();
//            if (Logger.isLogError()) Logger.logError(e);
//            return; // No point waiting if connection isn't available
//        }
        
//        // start the server to listen for login messages 
//        LoginServer ls = new LoginServer(RuntimeEnvironmentSettings.LOGIN_SERVER_PORT);
//        ls.start();
        
        // start the server to listen for incoming events
        EventServer es = new EventServer(RuntimeEnvironmentSettings.EVENT_SERVER_PORT);
        es.start();

//        // start the server to listen for social networking commands
//        SocialNetworkServer sns = new SocialNetworkServer(RuntimeEnvironmentSettings.SOCIAL_NETWORK_SERVER_PORT);
//        sns.start();
        
        // start the server to listen for workflow visualization requests
        WorkflowVisualizationServer wvs = new WorkflowVisualizationServer(RuntimeEnvironmentSettings.WORKFLOWVIS_SERVER_PORT);
        wvs.start();
        
//        // start the server to listen for network visualization requests
//        NetworkVisualizationServer nvs = new NetworkVisualizationServer(RuntimeEnvironmentSettings.NETWORKVIS_SERVER_PORT);
//        nvs.start();
        
        // start the server to listen for tool meta info requests
        ToolServer ts = new ToolServer(RuntimeEnvironmentSettings.TOOL_SERVER_PORT);
        ts.start();
        
       ISBUServer isbus = new ISBUServer(RuntimeEnvironmentSettings.ISBU_SERVER_PORT);
       isbus.start();
        
        SecurityServer security = new SecurityServer(RuntimeEnvironmentSettings.SECURITY_SERVER_PORT);
        security.start();
        
//		Added by Flavio
//		accepts all requests from the Workflow Repository tab
//		however, "adding a workflow to the repository" from other tabs is handled by the ISBUServer
        WorkflowRepositoryServer wfr = new WorkflowRepositoryServer(RuntimeEnvironmentSettings.WORKFLOW_REPOSITORY_PORT);
        wfr.start();
        
        // start the scripts
        ScriptRunner scriptrunner = new ScriptRunner();
        while (true) { // Simplified version of "stick around" wait
            System.out.println("waiting");
            GeneralUtils.sleep(60*60*1000);
        }
        
    } // main()

}
