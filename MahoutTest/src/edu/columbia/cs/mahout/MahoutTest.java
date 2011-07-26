package edu.columbia.cs.mahout;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLBooleanPrefJDBCDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.CachingRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.slopeone.SlopeOneRecommender;
import org.apache.mahout.cf.taste.impl.similarity.CachingItemSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.CachingUserSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.impl.model.jdbc.ReloadFromJDBCDataModel;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.PreferenceArray;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
 

public class MahoutTest {
	
	public MahoutTest() { }

	private final static String USER = "root";
	private final static String PWD = "finance";
	private final static String ADDRESS = "localhost";
	private final static int PORT = 3306;
	private final static String DATABASE = "genspace";
	
	private final static int NEIGHBORHOOD = 5;
	private final static int RECOMMENDATIONS = 10;
	
	public final static int NO_FILTER = 0;
	public final static int NETWORK_FILTER = 1;
	public final static int DIVERSITY_FILTER = 2;
	
	private static HashMap<Long, Vector<Integer>> map;
	private static HashMap<Long, Long> parentMap;
	
	public static HashMap<Long, Vector<Integer>> getMap() {
		return map;
	}
	
	public static HashMap<Long, Long> getParentMap() {
		return parentMap;
	}
	
	public static void main (String[] args)
	{
		try {
			
			System.out.print("Filtering method (0 - No Filtering, 1 - Network Filtering): ");
            
	        Scanner scanner1 = new Scanner(System.in);
	    		
	    	String userInput1 = scanner1.nextLine();
	    	Integer filterMethod = Integer.parseInt(userInput1);
			
			MysqlConnectionPoolDataSource dataSource
			  = new MysqlConnectionPoolDataSource();
			dataSource.setUser(USER);
			dataSource.setPassword(PWD);
			dataSource.setServerName(ADDRESS);
			dataSource.setPort(PORT);
			dataSource.setDatabaseName(DATABASE);
			
			//MySQLJDBCDataModel mysqlModel = new MySQLJDBCDataModel(dataSource, "mahout_view", "taste_users_id", "workflow_id", "preference", "timestamp");
			GenspaceMySQLJDBCDataModel mysqlModel = 
				new GenspaceMySQLJDBCDataModel(dataSource, "mahout_view", "taste_users_id", "workflow_id", "preference", "timestamp", "network_id");
			ReloadFromJDBCDataModel model = new ReloadFromJDBCDataModel(mysqlModel);
			
			ItemSimilarity genspaceItemSimilarity = new GenspaceItemSimilarity(model, filterMethod); 
			
			map = mysqlModel.getUserNetworks();
			parentMap = mysqlModel.getParentMap();
			
	        UserSimilarity userSimilarity = new GenspaceSimilarity(model, filterMethod);
	        
	        CachingItemSimilarity cachedItemSimilarity = new CachingItemSimilarity(genspaceItemSimilarity, model);
	        
			CachingUserSimilarity cachedUserSimilarity = new CachingUserSimilarity(userSimilarity, model);
			
	        UserNeighborhood neighborhood = new NearestNUserNeighborhood(NEIGHBORHOOD, cachedUserSimilarity, model);
	            
	        // Slope one recommender
	        CachingRecommender cachingRecommender1 = 
	        	new CachingRecommender(new SlopeOneRecommender(model));
	            
	        // User similarity
	        CachingRecommender cachingRecommender2 = 
	        	new CachingRecommender(new GenericUserBasedRecommender(model, neighborhood, cachedUserSimilarity));
			
	        // Item similarity 
	        CachingRecommender cachingRecommender = 
	        	new CachingRecommender(new GenspaceItemRecommender(model, cachedItemSimilarity));
	        
	        while(true)
	        {
		        System.out.print("Enter user ID: ");
		            
		        Scanner scanner = new Scanner(System.in);
		    		
		    	String userInput = scanner.nextLine();
		    	Integer userId = Integer.parseInt(userInput);
		    	
	            long[] ids = neighborhood.getUserNeighborhood(userId);
	          	            
	            PreferenceArray array = model.getPreferencesFromUser(userId);
	            System.out.println(array.toString());
	            
	            List<RecommendedItem> recommendations;
	            
	            System.out.println("Slope one recommends:");
	            recommendations = cachingRecommender1.recommend(userId, RECOMMENDATIONS);
	            for (RecommendedItem recommendedItem : recommendations) {
	            	System.out.println(recommendedItem);
	            }
	            
	            
	            System.out.println("User similarity recommends:");
	            recommendations = cachingRecommender2.recommend(userId, RECOMMENDATIONS);
	            for (RecommendedItem recommendedItem : recommendations) {
	            	System.out.println(recommendedItem);
	            }
	            
	            System.out.println("Item similarity recommends:");
	            
	            recommendations = cachingRecommender.recommend(userId, RECOMMENDATIONS);
	            for (RecommendedItem recommendedItem : recommendations) {
	            	System.out.println(recommendedItem);
	            }
	            
	            System.out.println("People like you:");
	            for (Long id : ids) {
	            	System.out.println(id);
	            }
	        }
		} catch (Exception te)
		{
			te.printStackTrace();
		}
	}
}

