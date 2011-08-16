package org.geworkbench.components.genspace.server.mahout;

import java.util.List;
import java.util.Collections;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.FastIDSet;
import org.apache.mahout.cf.taste.impl.common.FullRunningAverage;
import org.apache.mahout.cf.taste.impl.common.RunningAverage;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.TopItems;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.PreferenceArray;
import org.apache.mahout.cf.taste.recommender.IDRescorer;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Rescorer;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.common.LongPair;

import com.google.common.base.Preconditions;

public class GenspaceItemRecommender extends GenericItemBasedRecommender{

	private ItemSimilarity similarity;
	
	public GenspaceItemRecommender(DataModel dataModel,
			ItemSimilarity similarity) {
		super(dataModel, similarity);
		this.similarity = similarity;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	  public List<RecommendedItem> recommend(long userID, int howMany, IDRescorer rescorer) throws TasteException {
	    Preconditions.checkArgument(howMany >= 1, "howMany must be at least 1");
	    PreferenceArray preferencesFromUser = getDataModel().getPreferencesFromUser(userID);
	    if (preferencesFromUser.length() == 0) {
	      return Collections.emptyList();
	    }
	   

	    FastIDSet possibleItemIDs = getAllOtherItems(userID, preferencesFromUser);

	    TopItems.Estimator<Long> estimator = new MultiMostSimilarEstimator(preferencesFromUser.getIDs(), similarity, null, true);

	    List<RecommendedItem> topItems = TopItems.getTopItems(howMany, possibleItemIDs.iterator(), rescorer,
	      estimator);

	    return topItems;
	  }
	
	public static final class MultiMostSimilarEstimator implements TopItems.Estimator<Long> {
	    
	    private final long[] toItemIDs;
	    private final ItemSimilarity similarity;
	    private final Rescorer<LongPair> rescorer;
	    private final boolean excludeItemIfNotSimilarToAll;
	    
	    MultiMostSimilarEstimator(long[] toItemIDs, ItemSimilarity similarity, Rescorer<LongPair> rescorer,
	        boolean excludeItemIfNotSimilarToAll) {
	      this.toItemIDs = toItemIDs;
	      this.similarity = similarity;
	      this.rescorer = rescorer;
	      this.excludeItemIfNotSimilarToAll = excludeItemIfNotSimilarToAll;
	    }
	    
	    @Override
	    public double estimate(Long itemID) throws TasteException {
	      RunningAverage average = new FullRunningAverage();
	      double[] similarities = similarity.itemSimilarities(itemID, toItemIDs);
	      for (int i = 0; i < toItemIDs.length; i++) {
	        long toItemID = toItemIDs[i];
	        LongPair pair = new LongPair(toItemID, itemID);
	        if (rescorer != null && rescorer.isFiltered(pair)) {
	          continue;
	        }
	        double estimate = similarities[i];
	        if (rescorer != null) {
	          estimate = rescorer.rescore(pair, estimate);
	        }
	        if (excludeItemIfNotSimilarToAll || !Double.isNaN(estimate)) {
	          average.addDatum(estimate);
	        }
	      }
	      double averageEstimate = average.getAverage();
	      return averageEstimate == 0 ? Double.NaN : averageEstimate;
	    }
	  }

}
