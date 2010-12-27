package genspace.scripts;

import genspace.db.ISBUDataAnalysis;

/**
 * This script will update the index used by ISBU 
 * UPDATE BY FLAVIO: it will also now store into the DB all the information associated with WFs such as
 * wf_info and list of tools to be used in the workflow repository functionalities
 * @author swapneel
 *
 */
public class IndexUpdateScript extends Scripter {

	public void doThis() {
		
		System.out.println("Updating from IndexUpdateScript");

		ISBUDataAnalysis ida = new ISBUDataAnalysis();
	}

	/**
	 * The timeout for the script update. Set to 1 day
	 */
	public long getTimeout() {
		return 1*1000*60*60*24;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IndexUpdateScript ius = new IndexUpdateScript();
		Thread t = new Thread(ius);
		t.start();
	}

}
