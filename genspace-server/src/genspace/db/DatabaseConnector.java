package genspace.db;
import java.sql.*;

import genspace.RuntimeEnvironmentSettings;
import genspace.common.Logger;

public class DatabaseConnector
{	
     protected java.sql.Connection  con = null;
     
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
               System.out.println("Error Trace in getConnection() : " + e.getMessage());
               if (Logger.isLogError()) Logger.logError(e);
         }
          return con;
      }

     /*
          Display the driver properties, database details
     */
     public void displayDbProperties(){
          java.sql.DatabaseMetaData dm = null;
          java.sql.ResultSet rs = null;
          try{
               con= this.getConnection();
               if(con!=null){
                    dm = con.getMetaData();
                    System.out.println("Driver Information");
                    System.out.println("\tDriver Name: "+ dm.getDriverName());
                    System.out.println("\tDriver Version: "+ dm.getDriverVersion ());
                    System.out.println("\nDatabase Information ");
                    System.out.println("\tDatabase Name: "+ dm.getDatabaseProductName());
                    System.out.println("\tDatabase Version: "+ dm.getDatabaseProductVersion());
                    System.out.println("Avalilable Catalogs ");
                    rs = dm.getCatalogs();
                    while(rs.next()){
                         System.out.println("\tcatalog: "+ rs.getString(1));
                    }
                    rs.close();
                    rs = null;
                    closeConnection();
               }else System.out.println("Error: No active Connection");
          }catch(Exception e){
               e.printStackTrace();
               if (Logger.isLogError()) Logger.logError(e);
          }
          dm=null;
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