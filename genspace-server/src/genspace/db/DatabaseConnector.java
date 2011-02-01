package genspace.db;
import java.sql.*;

import genspace.RuntimeEnvironmentSettings;
import genspace.common.Logger;

public class DatabaseConnector
{	
     protected java.sql.Connection  con = null;
     protected java.sql.Connection tigCon=null;
     
     // Informs the driver to use server a side-cursor,
     // which permits more than one active statement
     // on a connection.
     private final String selectMethod = "cursor";
     
     // Constructor
     public DatabaseConnector(){}

     private String getConnectionUrl(){
          // return url+serverName+":"+portNumber+";databaseName="+databaseName+";selectMethod="+selectMethod+";";
          return RuntimeEnvironmentSettings.DB_URL
          			+RuntimeEnvironmentSettings.DB_HOST
          			+ ":" + RuntimeEnvironmentSettings.DB_PORT
          			+ ";user=" + RuntimeEnvironmentSettings.DB_USER
          			+ ";password=" + RuntimeEnvironmentSettings.DB_PASS 
          			+ ";databaseName=" + RuntimeEnvironmentSettings.DB_NAME;
     }

     public synchronized java.sql.Connection getConnection(){
          try{
               Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
               con = java.sql.DriverManager.getConnection(getConnectionUrl(),
            		   RuntimeEnvironmentSettings.DB_USER,
            		   RuntimeEnvironmentSettings.DB_PASS);
               
               //if(con!=null) System.out.println("Connection Successful!");
               if (con == null) throw new SQLException("Connection is null");
          }catch(Exception e){
               e.printStackTrace();
               if (Logger.isLogError()) Logger.logError(e);
         }
          return con;
      }
     public synchronized java.sql.Connection getTigConnection(){
         try{
              Class.forName("com.mysql.jdbc.Driver");
              con = java.sql.DriverManager.getConnection(RuntimeEnvironmentSettings.Tig_DB_URI,RuntimeEnvironmentSettings.TIG_DB_USER,RuntimeEnvironmentSettings.TIG_DB_PASS);
              
              //if(con!=null) System.out.println("Connection Successful!");
              if (con == null) throw new SQLException("TigConnection is null");
         }catch(Exception e){
              e.printStackTrace();
              if (Logger.isLogError()) Logger.logError(e);
        }
         return con;
     }
     /*
          Display the driver properties, database details
     */
     public void displayDbProperties(){
          
     }

     public void closeConnection(){
          try{
               if(con!=null)
                    con.close();
               con=null;
          }catch(Exception e){
               e.printStackTrace();
               if (Logger.isLogError()) Logger.logError(e);
          }
     }


	public static void main(String[] args)
	{
		DatabaseConnector dc = new DatabaseConnector();
		dc.displayDbProperties();
	}

}