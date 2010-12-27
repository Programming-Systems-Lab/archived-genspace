package genspace.db;

import genspace.common.Logger;
import genspace.db.util.UserWorkflowLoader;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.geworkbench.components.genspace.bean.RegisterBean;
import org.geworkbench.components.genspace.bean.User;
import org.geworkbench.components.genspace.bean.UserWorkflow;
import org.geworkbench.components.genspace.bean.Workflow;
import org.geworkbench.components.genspace.bean.WorkflowComment;
import org.geworkbench.components.genspace.bean.WorkflowInbox;

public class UserManager extends DatabaseManager {
	
	
	/**
	 *  This method adds a new user to the system.
	 *  TODO: incorporate this into the UI; return some sort of status indicator
	 
	public void addUser(User u)
	{
		try
		{
			// get a database connection
			con = getConnection();

			if(con != null)
			{
				// create a Statement
				Statement stmt = con.createStatement();

				// now create the SQL query
				// TODO: how do we get the id??
				String query = "INSERT INTO users (id, username, password, email, organization) VALUES ('" + u.getId() + "', '" + u.getUsername() + "', '" + u.getPassword() + "', '" + u.getEmail() + "', '" + u.getOrganization() + "')";

				// execute the query
				stmt.executeUpdate(query);
            }
            else System.out.println("Error: No active Connection");
        }
        catch(Exception e)
        {
			e.printStackTrace();
            if (Logger.isLogError()) Logger.logError(e);
		}
		finally
		{
			closeConnection();
		}
	}
	 */
	
	/**
	 * This method is used to get all of the Users in the system. It returns null
	 * if there is a database error.
	 * 
	 *  TODO: incorporate this into the UI
	 * 
	 * @return an array of all Users in the database
	 */
	public String[] getAllUsers()
	{
		try
		{
			con = getConnection();

			if (con != null)
			{
				// create a Statement
				Statement stmt = con.createStatement();

				// now create the SQL query
				// TODO: use the "users" table instead?
				//String query = "SELECT username  FROM users";
				String query = "select distinct username from analysis_events where username <> 'anonymous' order by username";

				// execute the query
				ResultSet rs = stmt.executeQuery(query);

				// we can't actually know the size of the ResultSet in advance
				// so we can't create an array... yet
				// use an ArrayList for now
				ArrayList<String> results = new ArrayList<String>();

				// iterate through the ResultSet and populate the ArrayList
				int count = 0;
				while (rs.next())
				{
					//int id = rs.getInt("id");
					String name = rs.getString("username");
					//String email = rs.getString("email");
					//String organization = rs.getString("organization");
					results.add(name);
					count++;
				}

				// now return the results
				if (count == 0)
				{
					return new String[0];
				}
				else
				{	
					return results.toArray(new String[count]);
				}
			}
		}
        catch(Exception e)
        {
			e.printStackTrace();
            if (Logger.isLogError()) Logger.logError(e);
		}
		finally
		{
			closeConnection();
		}

		return null;
	}


	/**
	 * This method is used to get one User by his/her ID. It returns null
	 * if there is a database error or if the user does not exist.
	 * 
	 *  TODO: incorporate this into the UI
	 *  
	public User getUserById(int id)
	{
		try
		{
			con = getConnection();

			if (con != null)
			{
				// create a Statement
				Statement stmt = con.createStatement();

				// now create the SQL query
				String query = "SELECT username, email, organization FROM users WHERE id = '" + id + "'";

				// execute the query
				ResultSet rs = stmt.executeQuery(query);

				// set this to null... if there is no match, this will be returned
				User user = null;
				// there should only be ONE match
				if (rs.next())
				{
					String name = rs.getString("username");
					String email = rs.getString("email");
					String organization = rs.getString("organization");
					user = new User(id, name, email, organization);
				}

				// now return the results
				return user;
			}
		}
        catch(Exception e)
        {
			e.printStackTrace();
            if (Logger.isLogError()) Logger.logError(e);
		}
		finally
		{
			closeConnection();
		}

		return null;
	}
	*/
	
	/**
	 * This method is used to get one User by his/her name. It returns null
	 * if there is a database error or if the user does not exist.
	 * 
	 *  TODO: incorporate this into the UI
	 *  
	public User getUserByName(String username)
	{
		try
		{
			con = getConnection();

			if (con != null)
			{
				// create a Statement
				Statement stmt = con.createStatement();

				// now create the SQL query
				String query = "SELECT id, email, organization FROM users WHERE username = '" + username + "'";

				// execute the query
				ResultSet rs = stmt.executeQuery(query);

				// set this to null... if there is no match, this will be returned
				User user = null;
				// there should only be ONE match
				if (rs.next())
				{
					int id = rs.getInt("id");
					String email = rs.getString("email");
					String organization = rs.getString("organization");
					user = new User(id, username, email, organization);
				}

				// now return the results
				return user;
			}
		}
        catch(Exception e)
        {
			e.printStackTrace();
            if (Logger.isLogError()) Logger.logError(e);
		}
		finally
		{
			closeConnection();
		}

		return null;
	}
	*/
	

	/**
	 * This method tries to figure out who is currently online (ignoring the user who is making the query). 
	 * Right now it just looks to see who has logged in over the past hour.
	 * TODO: how can we know for sure?
	 */
	public String[] getActiveUsers(String username)
	{
		try
		{
			con = getConnection();

			if (con != null)
			{
				// create a Statement
				Statement stmt = con.createStatement();

				// now create the SQL query
				String query = "SELECT username FROM login_events WHERE datediff(minute, date, getdate()) < 60 AND username <> '" + username + "' ORDER BY username";

				// execute the query
				ResultSet rs = stmt.executeQuery(query);

				// we can't actually know the size of the ResultSet in advance
				// so we can't create an array... yet
				// use an ArrayList for now
				ArrayList<String> results = new ArrayList<String>();

				// iterate through the ResultSet and populate the ArrayList
				int count = 0;
				while (rs.next())
				{
					results.add(rs.getString("username"));
					count++;
				}

				// now return the results
				if (count == 0)
				{
					return new String[0];
				}
				else
				{
					return results.toArray(new String[count]);
				}
			}
		}
        catch(Exception e)
        {
			e.printStackTrace();
            if (Logger.isLogError()) Logger.logError(e);
		}
		finally
		{
			closeConnection();
		}

		return null;
	}
	

	/**
	 * This method updates the table that lists users' friends.
	 */
	public void addFriend(String user1, String user2)
	{
		try
		{
			// get a database connection
			con = getConnection();

			if(con != null)
			{
				// create a Statement
				Statement stmt = con.createStatement();

				// now create the SQL query
				String query = "INSERT INTO friends (user1, user2) VALUES ('" + user1 + "', '" + user2 + "')";

				// execute the query
				stmt.executeUpdate(query);
            }
            else System.out.println("Error: No active Connection");
        }
        catch(Exception e)
        {
			e.printStackTrace();
            if (Logger.isLogError()) Logger.logError(e);
		}
		finally
		{
			closeConnection();
		}
	}
	
	
	/**
	 * Returns the usernames of all of this users' friends.
	 */
	public String[] getFriends(String username)
	{
		try
		{
			con = getConnection();

			if (con != null)
			{
				// create a Statement
				Statement stmt = con.createStatement();

				// now create the SQL query
				String query = "SELECT distinct user2 FROM friends WHERE user1 = '" + username + "'";

				// execute the query
				ResultSet rs = stmt.executeQuery(query);

				// we can't actually know the size of the ResultSet in advance
				// so we can't create an array... yet
				// use an ArrayList for now
				ArrayList<String> results = new ArrayList<String>();

				// iterate through the ResultSet and populate the ArrayList
				int count = 0;
				while (rs.next())
				{
					results.add(rs.getString("user2"));
					count++;
				}

				// now do it again
				query = "SELECT distinct user1 FROM friends WHERE user2 = '" + username + "'";

				// execute the query
				rs = stmt.executeQuery(query);

				// iterate through the ResultSet and populate the ArrayList
				while (rs.next())
				{
					results.add(rs.getString("user1"));
					count++;
				}

				
				// now return the results
				if (count == 0)
				{
					return new String[0];
				}
				else
				{
					return results.toArray(new String[count]);
				}
			}
		}
        catch(Exception e)
        {
			e.printStackTrace();
            if (Logger.isLogError()) Logger.logError(e);
		}
		finally
		{
			closeConnection();
		}

		return null;
	}

	/**
	 * Added by Flavio
	 * This function is requested at login-time
	 * the login credentials have already been checked and they are correct
	 * Loads all the information associated with a user
	 * The user object will be then sent to the client
	 * @param string the registerBean provided by the client
	 * @return a filled user
	 */
	public User getUser(String username) {
		User u = null;
		try
		{
			con = getConnection();

			if (con != null)
			{
				u = UserWorkflowLoader.loadUser(con, username);
			}
		}
        catch(Exception e)
        {
			e.printStackTrace();
            if (Logger.isLogError()) Logger.logError(e);
		}
		finally
		{
			closeConnection();
		}
		return u;
	}
	
	/**
	 * Added by Flavio
	 * Store a new UserWorkflow for the given user
	 * The user just added the UserWorkflow to the repository
	 * it has to be added in the DB
	 * and then it has to be fully loaded and returned to the user itself
	 * @param string the registerBean provided by the client
	 * @return a filled user
	 * @throws Exception 
	 */
	public UserWorkflow addAndGetUserWorkflow(String username, int wfid, String name) throws Exception {
		UserWorkflow uw = null;
		try
		{
			con = getConnection();
			if (con != null)
			{
				con.setAutoCommit(false);
				UserWorkflowLoader.storeUserWorkflow(con, username, wfid, name);
				uw = UserWorkflowLoader.loadUserWorkflow(con, username, wfid);
				con.commit();
			}
		}
        catch(Exception e)
        {
			e.printStackTrace();
            if (Logger.isLogError()) Logger.logError(e);
            if(con != null)
            	con.rollback();
            throw e;
		}
		finally
		{
			closeConnection();
		}
		return uw;
	}
	
	public static void main(String[] args)
	{
		genspace.db.UserManager um = new genspace.db.UserManager();
		User u = um.getUser("swap");
		System.out.println(u);
	}

	public void createUserFolder(String username, String foldername) throws Exception {
		try
		{
			con = getConnection();
			if (con != null)
			{
				con.setAutoCommit(false);
				UserWorkflowLoader.storeUserFolder(con, username, foldername);
				con.commit();
			}
		}
        catch(Exception e)
        {
			e.printStackTrace();
            if (Logger.isLogError()) Logger.logError(e);
            if(con != null)
            	con.rollback();
            throw e;
		}
		finally
		{
			closeConnection();
		}
	}


	public void addWorkflowToFolder(String username, int workflowId, String foldername) throws Exception {
		try
		{
			con = getConnection();
			if (con != null)
			{
				con.setAutoCommit(false);
				UserWorkflowLoader.updateWorkflowFolder(con, username, workflowId, foldername);
				con.commit();
			}
		}
        catch(Exception e)
        {
			e.printStackTrace();
            if (Logger.isLogError()) Logger.logError(e);
            if(con != null)
            	con.rollback();
            throw e;
		}
		finally
		{
			closeConnection();
		}
		
	}


	public void deleteWorkflow(String username, int workflowId) throws Exception {
		try
		{
			con = getConnection();
			if (con != null)
			{
				con.setAutoCommit(false);
				UserWorkflowLoader.deleteWorkflow(con, username, workflowId);
				con.commit();
			}
		}
        catch(Exception e)
        {
			e.printStackTrace();
            if (Logger.isLogError()) Logger.logError(e);            
            if(con != null)
            	con.rollback();
            throw e;
		}
		finally
		{
			closeConnection();
		}
	}


	public void deleteFolder(String username, String foldername) throws Exception {
		try
		{
			con = getConnection();
			if (con != null)
			{
				con.setAutoCommit(false);
				UserWorkflowLoader.deleteFolder(con, username, foldername);
				con.commit();
			}
		}
        catch(Exception e)
        {
			e.printStackTrace();
            if (Logger.isLogError()) Logger.logError(e);            
            if(con != null)
            	con.rollback();
            throw e;
		}
		finally
		{
			closeConnection();
		}
	}


	public void deleteWorkflowInbox(String username, WorkflowInbox wi) throws Exception {
		try
		{
			con = getConnection();
			if (con != null)
			{
				con.setAutoCommit(false);
				UserWorkflowLoader.deleteWorkflowInbox(con, username, wi);
				con.commit();
			}
		}
        catch(Exception e)
        {
			e.printStackTrace();
            if (Logger.isLogError()) Logger.logError(e); 
            
            if(con != null)
            	con.rollback();
            throw e;
		}
		finally
		{
			closeConnection();
		}
	}


	public void addWorkflowInboxToRepository(String username, WorkflowInbox wi) throws Exception {
		try
		{
			
			con = getConnection();
			if (con != null)
			{
				con.setAutoCommit(false);
				UserWorkflowLoader.addWorkflowInboxToRepository(con, username, wi);
				con.commit();
			}
		}
        catch(Exception e)
        {
			e.printStackTrace();
            if (Logger.isLogError()) Logger.logError(e);            
            if(con != null)
            	con.rollback();
            throw e;
		}
		finally
		{
			closeConnection();
		}
	}


	public void sendWorkflow(String receiver, WorkflowInbox wi) throws Exception {
		try
		{
			con = getConnection();
			if (con != null)
			{
				con.setAutoCommit(false);
				UserWorkflowLoader.sendWorkflow(con, receiver, wi);
				con.commit();
			}
		}
        catch(Exception e)
        {
			e.printStackTrace();
            if (Logger.isLogError()) Logger.logError(e);            
            if(con != null)
            	con.rollback();
            throw e;
		}
		finally
		{
			closeConnection();
		}
	}


	public int storeComment(WorkflowComment wc, Workflow w) throws Exception {
		try
		{
			con = getConnection();
			con.setAutoCommit(false);
			int pk = UserWorkflowLoader.storeComment(con, wc, w);
			con.commit();
			return pk;
		}
        catch(Exception e)
        {
			e.printStackTrace();
            if (Logger.isLogError()) Logger.logError(e);            
            if(con != null)
            	con.rollback();
            throw e;
		}
		finally
		{
			closeConnection();
		}
	}


	public void deleteComment(WorkflowComment wc, Workflow w) throws Exception {
		try
		{
			con = getConnection();
			con.setAutoCommit(false);
			UserWorkflowLoader.deleteComment(con, wc, w);
			con.commit();
		}
	    catch(Exception e)
	    {
			e.printStackTrace();
	        if (Logger.isLogError()) Logger.logError(e);            
	        if(con != null)
	        	con.rollback();
	        throw e;
		}
		finally
		{
			closeConnection();
		}
		
	}
}
