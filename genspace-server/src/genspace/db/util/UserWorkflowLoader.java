package genspace.db.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;

import org.geworkbench.components.genspace.bean.RatingBean;
import org.geworkbench.components.genspace.bean.Tool;
import org.geworkbench.components.genspace.bean.User;
import org.geworkbench.components.genspace.bean.UserWorkflow;
import org.geworkbench.components.genspace.bean.Workflow;
import org.geworkbench.components.genspace.bean.WorkflowComment;
import org.geworkbench.components.genspace.bean.WorkflowInbox;

/**
 * A bunch of static methods used by the the UserManager DB Manager to retrieve information associated with users
 * These functionalities are mostly related to Workflow Repository
 * the connection is instantiated in the DBManager and therefore also exceptions have to be catched there.
 * @author flavio
 *
 */
public class UserWorkflowLoader {
	
	public static User loadUser(Connection con, String username) throws SQLException {
		User u = null;
		String sql = "Select * from registration where username = ? ";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, username);
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()){
			u = new User();
			u.username = username;
			u.password = rs.getString("password");
			u.email = rs.getString("email");
			u.workflows = loadUserWorkflows(con, u);
			u.inbox = loadInbox(con, u);
			u.folders = loadFolders(con, u);
		}
		return u;
	}

	private static ArrayList<String> loadFolders(Connection con, User u) throws SQLException {
		ArrayList<String> res = new ArrayList<String>();
		String sql = "Select * from user_folder where user_id = ? ";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, u.username);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()){
			String folder = rs.getString("name");
			res.add(folder);
		}
		return res;
	}

	public static ArrayList<WorkflowInbox> loadInbox(Connection con, User u) throws SQLException {
		ArrayList<WorkflowInbox> res = new ArrayList<WorkflowInbox>();
		String sql = "Select * from workflow_incoming join workflow_info on workflow_id = id where receiver_user_id = ? ";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, u.username);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()){
			WorkflowInbox wi = loadWorkflownbox(con, rs);
			res.add(wi);
		}
		return res;
	}

	public static WorkflowInbox loadWorkflownbox(Connection con, ResultSet rs) throws SQLException {
		WorkflowInbox wi = new WorkflowInbox();
		wi.name = rs.getString("name");
		wi.date = rs.getDate("sent_date");
		wi.sender = rs.getString("sender_user_id");
		wi.workflow = loadWorkflow(con, rs);
		return wi;
	}

	public static ArrayList<UserWorkflow> loadUserWorkflows(Connection con, User u) throws SQLException {
		ArrayList<UserWorkflow> res = new ArrayList<UserWorkflow>();
		String sql = "Select * from user_workflow join workflow_info on workflow_id = id where user_id = ? ";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, u.username);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()){
			UserWorkflow uw = loadUserWorkflow(con, u.username, rs);
			res.add(uw);
		}
		return res;
	}
	
	public static UserWorkflow loadUserWorkflow(Connection con, String username, ResultSet rs) throws SQLException{
		UserWorkflow uw = new UserWorkflow();
		uw.username = username;
		uw.name = rs.getString("name");
		uw.folder = rs.getString("folder");
		uw.workflow = loadWorkflow(con, rs);
		return uw;
	}
	
	public static Workflow loadWorkflow(Connection con, ResultSet rs) throws SQLException{
		Workflow w = new Workflow();
		w.ID = rs.getInt("workflow_id");
		w.creatorUsername = rs.getString("creator");
		w.creationDate = rs.getDate("creation_date");
		w.creationTransactionId = rs.getString("creation_transaction_id");
		w.usageCount = rs.getInt("num_usage");
		w.cachedIdentifier = rs.getString("string_id");
		w.tools = loadWorkflowTools(con, w);
		w.comments = loadWorkflowComments(con, w);
		w.ratings = loadWorkflowRatings(con, w);
		return w;
	}

	
	
	public static ArrayList<RatingBean> loadWorkflowRatings(Connection con, Workflow w) throws SQLException {
		ArrayList<RatingBean> res = new ArrayList<RatingBean>();
		String sql = "Select * from workflow_ratings where id = ? ";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, w.ID);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()){
			RatingBean r = loadWorkflowRating(con, rs);
			res.add(r);
		}
		return res;
	}
	
	public static RatingBean loadWorkflowRating(Connection con, ResultSet rs) throws SQLException {
		RatingBean r = new RatingBean();
		r.setUsername(rs.getString("username"));
		r.setUserRating(rs.getDouble("rating"));
		r.dbPK = rs.getInt("PK");
		return r;
	}
	
	public static ArrayList<WorkflowComment> loadWorkflowComments(Connection con, Workflow w) throws SQLException {
		ArrayList<WorkflowComment> res = new ArrayList<WorkflowComment>();
		String sql = "Select * from workflow_comments where id = ? ";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, w.ID);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()){
			WorkflowComment wc = loadWorkflowComment(con, rs);
			res.add(wc);
		}
		return res;
	}

	public static WorkflowComment loadWorkflowComment(Connection con, ResultSet rs) throws SQLException {
		WorkflowComment wc = new WorkflowComment();
		wc.username = rs.getString("username");
		wc.comment = rs.getString("comment");
		wc.postedOn = rs.getDate("posted_on");
		wc.tableKey = rs.getInt("PK");
		return wc;
	}

	public static ArrayList<Tool> loadWorkflowTools(Connection con, Workflow workflow) throws SQLException {
		ArrayList<Tool> res = new ArrayList<Tool>();
		String sql = "Select * from workflow_tool join tools on tool_id = id where workflow_id = ? order by position ";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, workflow.ID);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()){
			Tool t = loadTool(con, rs);
			res.add(t);
		}
		return res;
	}

	public static Tool loadTool(Connection con, ResultSet rs) throws SQLException {
		Tool t = new Tool();
		t.id = rs.getInt("id");
		t.name = rs.getString("tool");
		t.description = rs.getString("description");
		return t;
	}

	public static void storeUserWorkflow(Connection con,
			String username, int wfid, String name) throws SQLException {
		
		String sql = "insert into user_workflow values (?, ?, ?, NULL)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, username);
		pstmt.setInt(2, wfid);
		pstmt.setString(3, name);
		pstmt.executeUpdate();
	}

	public static UserWorkflow loadUserWorkflow(Connection con,
			String username, int wfid) throws SQLException {
		UserWorkflow uw = null;
		String sql = "Select * from user_workflow join workflow_info on workflow_id = id where user_id = ? and workflow_id = ? ";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, username);
		pstmt.setInt(2, wfid);
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()){
			
			uw = loadUserWorkflow(con, username, rs);
			System.out.println(uw);
		}
		return uw;
	}

	public static void storeUserFolder(Connection con, String username,
			String foldername) throws SQLException {
		String sql = "insert into user_folder values (?, ?, NULL)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, username);
		pstmt.setString(2, foldername);
		pstmt.executeUpdate();		
	}

	public static void updateWorkflowFolder(Connection con, String username,
			int workflowId, String foldername) throws SQLException {
		String sql = "update user_workflow set folder = ? where user_id = ? and workflow_id = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, foldername);
		pstmt.setString(2, username);
		pstmt.setInt(3, workflowId);
		pstmt.executeUpdate();		
	}

	public static void deleteWorkflow(Connection con, String username, int workflowId) throws SQLException {
		String sql = "delete from user_workflow where user_id = ? and workflow_id = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, username);
		pstmt.setInt(2, workflowId);
		pstmt.executeUpdate();	
	}

	public static void deleteFolder(Connection con, String username, String foldername) throws SQLException {
		String sql = "delete from user_folder where user_id = ? and name = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, username);
		pstmt.setString(2, foldername);
		pstmt.executeUpdate();		
		sql = "delete from user_workflow where user_id = ? and folder = ?";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, username);
		pstmt.setString(2, foldername);
		pstmt.executeUpdate();	
	}

	public static void deleteWorkflowInbox(Connection con, String username, WorkflowInbox wi) throws Exception {
		String sql = "delete from workflow_incoming where workflow_id = ? and sender_user_id = ? and receiver_user_id = ? and sent_date = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, wi.workflow.ID);
		pstmt.setString(2, wi.sender);
		pstmt.setString(3, username);
		pstmt.setDate(4, new java.sql.Date(wi.date.getTime()));
		pstmt.executeUpdate();	
	}

	public static void addWorkflowInboxToRepository(Connection con, String username, WorkflowInbox wi) throws Exception{
		String sql = "insert into user_workflow values (?, ?, ?, NULL)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, username);
		pstmt.setInt(2, wi.workflow.ID);
		pstmt.setString(3, wi.name);
		pstmt.executeUpdate();	
	}

	public static void sendWorkflow(Connection con, String receiver,
			WorkflowInbox wi) throws Exception {
		String sql = "select username from registration where username = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, receiver);
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()){
			sql = "insert into workflow_incoming values (?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, wi.sender);
			pstmt.setString(2, receiver);
			pstmt.setInt(3, wi.workflow.ID);
			pstmt.setDate(4, new java.sql.Date(wi.date.getTime()));
			pstmt.setString(5, wi.name);
			pstmt.executeUpdate();
		}
		else throw new Exception("Invalid Username");
	}

	public static int storeComment(Connection con, WorkflowComment wc, Workflow w) throws Exception {
		String sql = "insert into workflow_comments values (?,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, w.ID);
		pstmt.setString(2, wc.comment);
		pstmt.setString(3, wc.username);
		pstmt.setDate(4, new java.sql.Date(wc.postedOn.getTime()));
		pstmt.executeUpdate();
		sql = "select @@IDENTITY";
		pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()){
			return rs.getInt(1);
		}
		else throw new Exception("Error with automatic index generation for workflow_comments");
	}

	public static void deleteComment(Connection con, WorkflowComment wc,
			Workflow w) throws Exception {
		String sql = "delete from workflow_comments where id = ? and username = ? and posted_on = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, w.ID);
		pstmt.setString(2, wc.username);
		pstmt.setDate(3, new Date(wc.postedOn.getTime()));
		pstmt.executeUpdate();	
		
	}

}
