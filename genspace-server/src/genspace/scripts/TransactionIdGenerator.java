package genspace.scripts;

import genspace.db.DatabaseConnector;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Random;

public class TransactionIdGenerator {
	
	DatabaseConnector db = null;
	Connection con = null;
	private long timeThreshold = 1000L * 60 * 60 * 24 * 30; //1 hour
	private String sourceTableName = "[Genspace].[dbo].[analysis_events_users0515]";
	private String destinationTableName = "[Genspace].[dbo].[analysis_events_temp_destination]";
	
	private void getDatabaseConnection() {
		try {
			db = new DatabaseConnector();
			con = db.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	/**
	 * Gets the data from the SQL database
	 * @param sourceTableName The name of the table containing the data
	 */
	private ResultSet getData() {
		ResultSet rs = null;
		try {
			if (con != null) {
				PreparedStatement ps = con.prepareStatement("SELECT * FROM " + sourceTableName + "order by username, host, date");
				rs = ps.executeQuery();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return rs;
	}
	
	private void insertData(String userName, String hostName, Timestamp timestamp, String analysis, String dataset, String transaction_id) {
		try {
			if (con != null) {
				PreparedStatement stmt = con.prepareStatement("INSERT INTO" + destinationTableName + "(username, host, date, analysis, dataset, transaction_id) VALUES (?, ?, ?, ?, ?, ?)");
				
				stmt.setString(1, userName);
				stmt.setString(2, hostName);
				stmt.setTimestamp(3, timestamp);
				stmt.setString(4, analysis);
				stmt.setString(5, dataset);
				stmt.setString(6, transaction_id);
				
				// execute the query
				stmt.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	private void emptyDestinationTable() {
		try {
			if (con != null) {
				PreparedStatement ps = con.prepareStatement("DELETE FROM " + destinationTableName);
				ps.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * Generates transaction IDs
	 * @param rs The ResultSet containing the data
	 */
	private void generateTransactionIds(ResultSet rs) {
		try {
			String lastUserName = "";
			String lastHostName = "";
			String lastDataset = "";
			String currentTransactionId = "";
			Timestamp lastTimestamp = new Timestamp(0);
			
			while(rs.next()) {
				String userName = rs.getString(1);
				String hostName = rs.getString(2);
				Timestamp timestamp = rs.getTimestamp(3);
				String analysis = rs.getString(4);
				String dataset = rs.getString(5);
				
				if (userName.equals(lastUserName) && hostName.equals(lastHostName) && dataset.equals(lastDataset) && checkTimeDifference(timestamp, lastTimestamp)) {
					System.out.println(userName + "," + hostName + "," + timestamp.toString() + "," + analysis + "," + dataset + "," + currentTransactionId);
					insertData(userName, hostName, timestamp, analysis, dataset, currentTransactionId);
				} else {
					lastUserName = userName;
					lastHostName = hostName;
					lastDataset = dataset;
					lastTimestamp = timestamp;
					currentTransactionId = lastUserName + lastHostName + getNewTransactionId();
					System.out.println(userName + "," + hostName + "," + timestamp.toString() + "," + analysis + "," + dataset + "," + currentTransactionId);
					insertData(userName, hostName, timestamp, analysis, dataset, currentTransactionId);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method returns true is the time difference between the 2 dates is less than timeThreshold
	 * @param date The current Date
	 * @param lastDate The old Date
	 * @return
	 */
	private boolean checkTimeDifference (Timestamp date, Timestamp lastDate) {
		//System.out.println("a:" + date.getTime());
		//System.out.println("b:" + lastDate.getTime());
		//System.out.println("c:" + timeThreshold);
		if ((date.getTime() - lastDate.getTime()) <= timeThreshold) {
			return true;
		} else return false;
	}
	
	/**
	 * Closes the database connection
	 */
	private void closeConnection() {
		db.closeConnection();
	}
	
	/**
	 * This method generates a new random transaction id
	 */
	private String getNewTransactionId() {
		Random r = new Random();
		int i = Math.abs(r.nextInt());
		Integer j = new Integer(i);
		return j.toString();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TransactionIdGenerator tig = new TransactionIdGenerator();
		tig.getDatabaseConnection();
		tig.generateTransactionIds(tig.getData());
		tig.closeConnection();
	}
}
