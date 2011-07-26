package edu.columbia.cs.mahout;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Vector;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.jdbc.AbstractJDBCDataModel;
import org.apache.mahout.common.IOUtils;
import org.apache.mahout.cf.taste.impl.common.jdbc.AbstractJDBCComponent;

public class GenspaceMySQLJDBCDataModel extends AbstractJDBCDataModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private	String getNetworkSQL;
	private static final String getParent = "SELECT id, parent_id FROM workflow";

	public GenspaceMySQLJDBCDataModel(DataSource dataSource,
            String preferenceTable,
            String userIDColumn,
            String itemIDColumn,
            String preferenceColumn,
            String timestampColumn,
            String networkColumn) 
	{	
		super(dataSource, preferenceTable, userIDColumn, itemIDColumn, preferenceColumn,
		// getPreferenceSQL
		"SELECT " + preferenceColumn + " FROM " + preferenceTable + " WHERE " + userIDColumn + "=? AND "
		+ itemIDColumn + "=?",
		// getPreferenceTimeSQL
		"SELECT " + timestampColumn + " FROM " + preferenceTable + " WHERE " + userIDColumn + "=? AND "
		+ itemIDColumn + "=?",
		// getUserSQL
		"SELECT DISTINCT " + userIDColumn + ", " + itemIDColumn + ", " + preferenceColumn + " FROM " + preferenceTable
		+ " WHERE " + userIDColumn + "=? ORDER BY " + itemIDColumn,
		// getAllUsersSQL
		"SELECT DISTINCT " + userIDColumn + ", " + itemIDColumn + ", " + preferenceColumn + " FROM " + preferenceTable
		+ " ORDER BY " + userIDColumn + ", " + itemIDColumn,
		// getNumItemsSQL
		"SELECT COUNT(DISTINCT " + itemIDColumn + ") FROM " + preferenceTable,
		// getNumUsersSQL
		"SELECT COUNT(DISTINCT " + userIDColumn + ") FROM " + preferenceTable,
		// setPreferenceSQL
		"INSERT INTO " + preferenceTable + '(' + userIDColumn + ',' + itemIDColumn + ',' + preferenceColumn
		+ ") VALUES (?,?,?) ON DUPLICATE KEY UPDATE " + preferenceColumn + "=?",
		// removePreference SQL
		"DELETE FROM " + preferenceTable + " WHERE " + userIDColumn + "=? AND " + itemIDColumn + "=?",
		// getUsersSQL
		"SELECT DISTINCT " + userIDColumn + " FROM " + preferenceTable + " ORDER BY " + userIDColumn,
		// getItemsSQL
		"SELECT DISTINCT " + itemIDColumn + " FROM " + preferenceTable + " ORDER BY " + itemIDColumn,
		// getPrefsForItemSQL
		"SELECT DISTINCT " + userIDColumn + ", " + itemIDColumn + ", " + preferenceColumn + " FROM " + preferenceTable
		+ " WHERE " + itemIDColumn + "=? ORDER BY " + userIDColumn,
		// getNumPreferenceForItemSQL
		"SELECT COUNT(1) FROM " + preferenceTable + " WHERE " + itemIDColumn + "=?",
		// getNumPreferenceForItemsSQL
		"SELECT COUNT(1) FROM " + preferenceTable + " tp1 JOIN " + preferenceTable + " tp2 " + "USING ("
		+ userIDColumn + ") WHERE tp1." + itemIDColumn + "=? and tp2." + itemIDColumn + "=?",
		"SELECT MAX(" + preferenceColumn + ") FROM " + preferenceTable,
		"SELECT MIN(" + preferenceColumn + ") FROM " + preferenceTable);
		
		getNetworkSQL = "SELECT " + userIDColumn + ", " + networkColumn + " FROM " + preferenceTable;
	}
	
	public HashMap<Long, Long> getParentMap() throws TasteException {
		Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    try {
	    	HashMap<Long, Long> hashMap = new HashMap<Long, Long>();
	    	conn = super.getDataSource().getConnection();
		    stmt = conn.prepareStatement(getParent, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
		    rs = stmt.executeQuery();
		    while (rs.next()) {
		    	long user = rs.getLong(1);
		    	long parentId = rs.getLong(2);
		    	hashMap.put(user,parentId);
		    } 
		    return hashMap;
	    } catch (SQLException sqle) {
	    	//log.warn("Exception while retrieving prefs for item", sqle);
	    	throw new TasteException(sqle);
	    } finally {
	    	IOUtils.quietClose(rs, stmt, conn);
	    }
	}
	
	public HashMap<Long, Vector<Integer>> getUserNetworks() throws TasteException {
		
	    //log.debug("Retrieving network for user ID '{}'", userID);
	    Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    HashMap<Long, Vector<Integer>> hashMap = new HashMap<Long, Vector<Integer>>();
	    try {
	      conn = super.getDataSource().getConnection();
	      stmt = conn.prepareStatement(getNetworkSQL, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

	      //log.debug("Executing SQL query: {}", getNetworkSQL);
	      rs = stmt.executeQuery();
	      while (rs.next()) {
	        long userId = rs.getLong(1);
	        int networkId = rs.getInt(2);
	        if (hashMap.containsKey(userId)) {
	        	Vector<Integer> networks = hashMap.get(userId);
	        	networks.add(networkId);
	        } else {
	        	Vector<Integer> network = new Vector<Integer>();
	        	network.add(networkId);
	        	hashMap.put(userId, network);
	        }
	      } 
	        return hashMap;
	    } catch (SQLException sqle) {
	      //log.warn("Exception while retrieving prefs for item", sqle);
	      throw new TasteException(sqle);
	    } finally {
	      IOUtils.quietClose(rs, stmt, conn);
	    }
	  }

	@Override
	  protected int getFetchSize() {
	    // Need to return this for MySQL Connector/J to make it use streaming mode
	    return Integer.MIN_VALUE;
	  }
}

