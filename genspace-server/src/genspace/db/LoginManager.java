package genspace.db;

import genspace.common.Logger;

import java.sql.ResultSet;
import java.sql.Statement;

public class LoginManager extends DatabaseManager {

	

	/**
	 * This method adds a mapping of a genSpace login to an IM handle and service
	 * TODO: what about updates??
	 * 
	 * @return whether or not the update succeeded
	 */
	public boolean addToLoginTable(String genspaceLogin, String IMHandle, String IMService)
	{
		try
		{
			// get a database connection
			con = getConnection();

			if(con != null)
			{
				// create a Statement
				Statement stmt = con.createStatement();
				
				// first we check whether this user already has a mapping
				// TODO: is there a better way to do this? depends on what we do with updates, I guess
				String query = "SELECT count(*) FROM user_IM_handles WHERE username='" + genspaceLogin + "' AND IM_service='" + IMService + "'";
				ResultSet rs = stmt.executeQuery(query);
				// if anything comes back, the user is already in there
				rs.next();
				if (rs.getInt(1) > 0) return true;
				else
				{
					// now create the SQL query
					// TODO: should use a PreparedStatement
					query = "INSERT INTO user_IM_handles (username, IM_handle, IM_service) VALUES ('" + genspaceLogin + "', '" + IMHandle + "', '" + IMService + "')";
					System.out.println(query);
					
					// execute the query
					stmt.executeUpdate(query);
				
					return true;
				}
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

		return false;
	}

	/**
	 *  This method records when a user logs in.
	 */
	public void addLoginEvent(String username)
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
				String query = "INSERT INTO login_events (username, date) VALUES ('" + username + "', getdate())";

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
	 * This method looks up the user's genspace login using the IM handle and service
	 */
	public String lookupLogin(String IMHandle, String IMService)
	{
		try
		{
			con = getConnection();

			if (con != null)
			{
				// create a Statement
				Statement stmt = con.createStatement();

				// now create the SQL query
				String query = "SELECT username FROM user_IM_handles WHERE IM_handle ='" + IMHandle + "' AND IM_service='" + IMService + "'";

				// execute the query
				ResultSet rs = stmt.executeQuery(query);

				// there should only be ONE match
				if (rs.next())
				{
					return rs.getString("username");
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
	
}
