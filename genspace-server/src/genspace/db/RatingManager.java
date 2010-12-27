package genspace.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import genspace.RuntimeEnvironmentSettings;
import org.geworkbench.components.genspace.bean.RatingBean;

public class RatingManager extends DatabaseConnector{
	
	public RatingBean writeRating(int id, int rating, String table, String user){
		//do not allow guests to rate
		if (user.equals(RuntimeEnvironmentSettings.DEFAULT_USER)) return getRating(id, table, user);
		
		try {
			PreparedStatement pstmt = 
				getConnection().prepareStatement("INSERT INTO " + table + " (id, rating, username) VALUES (?, ?, ?)");
			
			pstmt.setInt(1, id);
			pstmt.setInt(2, rating);
			pstmt.setString(3, user);
			
			pstmt.execute();
			
			return getRating(id, table, user);
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	
	public RatingBean getRating(int identifier, String table, String user){
		double overallRating = 0;
		double userRating = 0;
		long total = 0;
		
		try{
			Statement overall = getConnection().createStatement();
			ResultSet r = overall.executeQuery("SELECT * FROM " + table + " where id = " + identifier);
			NetworkVisibilityServerManager security = new NetworkVisibilityServerManager();
			
			while (r.next()){
				
				String userA = r.getString("username").trim();
				//if(security.isUserAVisibileToUserB(userA, user)){
					if (true) {
						total++;
					overallRating += r.getInt("rating");
					
					if (!user.equals(RuntimeEnvironmentSettings.DEFAULT_USER) && 
							userA.equals(user))
						userRating = r.getInt("rating");
					
				}
			}
			
			//if there are ratings, average it
			if (total != 0) overallRating /= total;
			
			//if user is not logged in, return -1 for userRating so they can't rate this object
			if(user.equals(RuntimeEnvironmentSettings.DEFAULT_USER))
				userRating = -1;
			
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return new RatingBean(identifier, overallRating, userRating, total);
	}
	
	public static void main(String[] args){
		RatingManager rm = new RatingManager();
		System.out.println(rm.getRating(3, "tool_ratings", "nankin"));
	}
	 
}