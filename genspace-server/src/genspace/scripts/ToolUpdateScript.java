package genspace.scripts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import java.sql.ResultSet;
import java.util.Collection;
import java.util.HashMap;

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
				updateToolParameters(con);
				ps.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.closeConnection();
		}
	}
	
	/**
	 * Method added by Flavio to update tools with their parameters info
	 * For each tool and parameter String, the table tools_parameters contains the usage frequency for that tool with that parameters String
	 * Only the table tool_parameters is modified.
	 */
	private void updateToolParameters(Connection con) throws Exception{
		Statement st = con.createStatement();
		String query = "select * from [Genspace].[dbo].[analysis_events_parameters] join [Genspace].[dbo].[tools] on analysis = tool collate SQL_Latin1_General_CP1_CI_AS order by date, analysis, transaction_id";
		ResultSet rs = st.executeQuery(query);
		System.out.println("rs.fetch: "+rs.getFetchSize());
		
		//each to keep the count of how many times the same set of parameters appears
		HashMap<Integer, ToolParametersSet> paramSets = new HashMap<Integer, ToolParametersSet>();
		//A single parameters set consists of more rows
		//get the first row to initialize the first set of parameters which involves more rows
		if(rs.next()){
			ToolParametersSet current = getToolSetFromRS(rs);
			while(rs.next()){
				ToolParametersSet next = getToolSetFromRS(rs);
				if(next.sameSet(current)){
					//continue to add parameters to the same set
					current.addParameter(next.parameters);
				}
				else{
					//current set is finished update count
					ToolParametersSet t = paramSets.get(current.hashCode());
					if(t == null)
						paramSets.put(current.hashCode(), current);
					else t.count++;
					//a new set just started
					current = next;
				}
			}
		}
		
		//insert the sets into the db
		//if already there just update the count
		Collection<ToolParametersSet> c = paramSets.values();
		for(ToolParametersSet t: c){
			String sql = "insert into [Genspace].[dbo].[tool_parameters] values (?,?,?)"; 
			PreparedStatement p = con.prepareStatement(sql);
			p.setInt(1, t.id);
			p.setString(2, t.parameters);
			p.setInt(3, t.count);
			try{
				p.executeUpdate();
			}
			catch(SQLException e){
				//Already in the DB, so just update
				sql = "update [Genspace].[dbo].[tool_parameters] set usage_count = ? where tool_id = ?";
				p = con.prepareStatement(sql);
				p.setInt(1, t.count);
				p.setInt(2, t.id);
				p.executeUpdate();
			}
		}
	}

	private ToolParametersSet getToolSetFromRS(ResultSet rs) throws Exception {
		int id = rs.getInt("id");
		String param = rs.getString("parameter_key");
		String value = rs.getString("parameter_value");
		String tid = rs.getString("transaction_id");
		String date = rs.getString("date");
		ToolParametersSet current = new ToolParametersSet(id, param+" = "+value);
		current.tid = tid;
		current.date = date;
		return current;
	}


	/**
	 * The timeout for the script update. Set to 1 min
	 */
	public long getTimeout() {
		return 1*1000*60*60;
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
