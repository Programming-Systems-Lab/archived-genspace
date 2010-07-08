package genspace.scripts;

import java.sql.Connection;
import java.sql.PreparedStatement;

import genspace.db.DatabaseConnector;

/**
 * This script will update the tools table with newly added tools from the logged analysis received from the users (from the analysis_events table) 
 * @author swapneel
 *
 */
public class ToolUpdateScript extends Scripter {

	public void doThis() {
		
		System.out.println("Updating from ToolUpdateScript");

		DatabaseConnector db = null;
		try {
			// TODO Auto-generated method stub
			db = new DatabaseConnector();
			Connection con = db.getConnection();

			if (con != null) {
				PreparedStatement ps = con.prepareStatement("insert into tools (tool) SELECT distinct(analysis) FROM analysis_events where analysis not in (SELECT tool FROM tools)");

				ps.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.closeConnection();
		}
	}

	/**
	 * The timeout for the script update. Set to 1 min
	 */
	public long getTimeout() {
		return 1*1000*60;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ToolUpdateScript tus = new ToolUpdateScript();
		Thread t = new Thread(tus);
		t.start();
	}

}
