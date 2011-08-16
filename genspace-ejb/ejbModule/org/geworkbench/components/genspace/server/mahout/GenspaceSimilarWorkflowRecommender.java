package org.geworkbench.components.genspace.server.mahout;

import java.util.Collections;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.FastIDSet;
import org.apache.mahout.cf.taste.impl.model.BooleanUserPreferenceArray;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.TopItems;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.PreferenceArray;
import org.apache.mahout.cf.taste.recommender.IDRescorer;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;

import org.geworkbench.components.genspace.entity.Workflow;
import org.geworkbench.components.genspace.server.mahout.GenspaceItemRecommender.MultiMostSimilarEstimator;

import com.google.common.base.Preconditions;

public class GenspaceSimilarWorkflowRecommender extends GenericItemBasedRecommender{

	private List<Long> workflows;
	private ItemSimilarity similarity;
	
	public GenspaceSimilarWorkflowRecommender(DataModel dataModel,
			ItemSimilarity similarity, List<Long> workflows) {
		
		super(dataModel, similarity);
		this.workflows = workflows;
		this.similarity = similarity;
	}
	
	@Override
	  public List<RecommendedItem> recommend(long userID, int howMany, IDRescorer rescorer) throws TasteException {
	    Preconditions.checkArgument(howMany >= 1, "howMany must be at least 1");
	    
	    if (workflows.size() == 0) {
	      return Collections.emptyList();
	    }
	    
	    PreferenceArray preferencesFromUser = new BooleanUserPreferenceArray(workflows.size());
	    
	    for (int i=0; i< workflows.size(); i++) {
	    	preferencesFromUser.setUserID(i, userID);
	    	preferencesFromUser.setItemID(i, workflows.get(i));
	    }
	    FastIDSet possibleItemIDs = getAllOtherItems(userID, preferencesFromUser);
	    TopItems.Estimator<Long> estimator = new MultiMostSimilarEstimator(preferencesFromUser.getIDs(), similarity, null, true);
	    List<RecommendedItem> topItems = TopItems.getTopItems(howMany, possibleItemIDs.iterator(), rescorer,
		      estimator);
	    
	    return topItems;
	}
}
