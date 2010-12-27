package genspace.db;

import genspace.common.Logger;

import java.util.*;
import java.io.*;


/**
 * This class implement the main work flow of the "Data Analysis" engine in the ISBU feature of genSpace
 * @author cheng
 *
 */


public class ISBUDataAnalysis {
	
	private ISBUManager dbManager;
	private ObjectOutputStream output;
	
	private static final String FILE_PATH = "DataAnalysisResult.gs";
	
	//private ArrayList <String> globalAnalysisToolSet;
	
	/**
	 * Constructor: contain the main work flow of data analysis engine
	 * 
	 */
	public ISBUDataAnalysis() {
		
		System.out.println("Now we begin the data analysis process.");
		
		dbManager = new ISBUManager();
		
		
		System.out.println("Database manager initialized.");
		System.out.println();
		
		//next we do a series of analysis
		dbManager.getSetOfAnalysisTools();
		
		dbManager.getCompleteTransactionList();
		
		
		
		
		dbManager.buildWorkFlowIndex();
		
		
		dbManager.fillInIndex1SecondValue();
		
		dbManager.calculatePreviousAndNextTool();
		
		dbManager.indexSorting();
		
		dbManager.calculateTop3MostPopularWFsContainingEachTool();
		
		dbManager.fillInIndex1EighthValue();
		
		try{
			//Added by Flavio: Stores the entire workflow model into the DB (the central workflow entity is stored in the worflow_info table)
			//wrapping this function into try & catch to make sure that the rest of the script works correctly
			dbManager.storeWorkflowModel();
		}
		catch(Exception e){
			e.printStackTrace();
			if (Logger.isLogError()) Logger.logError(e);
			System.err.println("ERROR: storing the workflow model into the DB");
		}
		
		//next we store our analysis results (write into files)
		try {
			File fileToStoreIn = new File(FILE_PATH);
			output = new ObjectOutputStream(new FileOutputStream(fileToStoreIn));
			output.writeObject(dbManager);
			output.flush();
			output.close();
			
		}
		catch (IOException e) {
			e.printStackTrace();
			System.err.println("ERROR: writing analysis results into file.");
		}
		System.out.println();
		System.out.println("Analysis result successfully saved to : " + FILE_PATH);
		
		
	}
	
	
	
	
	
	
	
	
	public static void main(String args[]) {
		
		ISBUDataAnalysis dataAnalysisEngine = new ISBUDataAnalysis();
		
	}

}
