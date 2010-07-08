package genspace.common;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * This class represents a single instance of a user executing some sort of analysis,
 * 
 * TODO: Conceivably we could add stuff later, like the name of the data file or parameters.
 *
 */



public class AnalysisEvent extends Event 
{
	/**
	 * The name of the analysis run.
	 */
	private String analysis;

	/**
	 * The name of the dataset.
	 */
	private String dataset;

	/**
	 * The transaction id
	 */
	private String transaction_id;

	/**
	 * contains the mappings for the old analysis names
	 * key = old name
	 * value = new name to be used in the database
	 */
	private static HashMap<String, String> mappings = new HashMap<String, String>();

	static {
		mappings.put("ARACNE", "ARACNE Analysis");
		mappings.put("MINDY", "MINDY Analysis");
		mappings.put("EvidenceIntegration", "Evidence Integration Analysis");
		mappings.put("T Test Analysis", "t Test Analysis");
		mappings.put("Hierachical Clustering", "Hierarchical Clustering");
		mappings.put("MatrixREDUCE", "MatrixREDUCE Analysis");
		mappings.put("Net Boost", "NetBoost Analysis");
		mappings.put("NetBoostAnalysis", "NetBoost Analysis");


//		commenting out for now
//		mappings.put("KNN Classifier", "KNN Analysis 3.0");
//		mappings.put("PCA Analysis", "PCA Analysis 3.0");
//		mappings.put("WV Classifier", "WV Analysis 3.0");
//		mappings.put("SMLR Classifier", "SMLR Analysis");

	}

	/**
	 * the map of the parameters
	 */
	private Map parameters;

	public AnalysisEvent(String user, String host, String time, String a, String d, String transaction_id, Map parameters)
	{
		super(user, host, time);
		if (mappings.containsKey(a)) {
			//we have a mapping for the analysis name, use this
			analysis = mappings.get(a);
		} else {
			analysis = a;
		}
		dataset = d;
		this.transaction_id = transaction_id;
		this.parameters = parameters;
	}

	public String getAnalysis()
	{
		return analysis;
	}

	public String getDataset()
	{
		return dataset;
	}

	public String getTransaction_id() {
		return transaction_id;
	}

	public Map getParameters() {
		return parameters;
	}

	public String toString()
	{
		return "User: " + user + "; Host: " + host + "; Time: " + time + "; Analysis: " + analysis + "; Dataset: " + dataset + "; Transaction_id: " + transaction_id + "; Parameters: " + parameters.toString();
	}
}
