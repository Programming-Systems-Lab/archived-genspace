package edu.columbia.cs.mahout;

import java.util.Collection;

import org.apache.mahout.cf.taste.common.Refreshable;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.SpearmanCorrelationSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.similarity.PreferenceInferrer;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

public class GenspaceSimilarity implements UserSimilarity{

	private UserSimilarity euclideanDistance;
	private int filtering;
	private DataModel model;
	
	public GenspaceSimilarity(DataModel model, int filtering) throws TasteException {
		//euclideanDistance = new EuclideanDistanceSimilarity(model);
		euclideanDistance = new SpearmanCorrelationSimilarity(model);
		this.filtering = filtering;
		this.model = model;
	}
	
	@Override
	public void refresh(Collection<Refreshable> arg0) {
		euclideanDistance.refresh(arg0);		
	}

	@Override
	public void setPreferenceInferrer(PreferenceInferrer arg0) {
		euclideanDistance.setPreferenceInferrer(arg0);
		
	}

	@Override
	public double userSimilarity(long userID1, long userID2) throws TasteException {
		// TODO Auto-generated method stub
		double euclideanSimilarity = euclideanDistance.userSimilarity(userID1, userID2);
		Integer network1;
		Integer network2;
		if (filtering == MahoutTest.NO_FILTER)
			return euclideanSimilarity;
		else if (filtering == MahoutTest.NETWORK_FILTER) {
			double r = 0;
			network1 = MahoutTest.getMap().get(userID1);
			network2 = MahoutTest.getMap().get(userID2);
			if (network1.equals(network2))
				r = 1;
			else 
				r = -1;
			double similarity = euclideanSimilarity *3/4 + r*1/4;
			return similarity;
		}
		else
			return euclideanSimilarity;
	}
}
