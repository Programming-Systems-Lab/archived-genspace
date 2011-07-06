package edu.columbia.cs.mahout;

import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

public class GenspaceRecommender extends GenericUserBasedRecommender{

	public GenspaceRecommender(DataModel dataModel,
			UserNeighborhood neighborhood, UserSimilarity similarity) {
		super(dataModel, neighborhood, similarity);
		// TODO Auto-generated constructor stub
	}

}
