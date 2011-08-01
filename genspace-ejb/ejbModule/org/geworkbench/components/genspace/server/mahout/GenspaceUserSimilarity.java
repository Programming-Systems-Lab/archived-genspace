package org.geworkbench.components.genspace.server.mahout;

import java.util.Collection;
import java.util.HashMap;
import java.util.Vector;

import org.apache.mahout.cf.taste.common.Refreshable;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.SpearmanCorrelationSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.similarity.PreferenceInferrer;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

public class GenspaceUserSimilarity implements UserSimilarity{

	private UserSimilarity euclideanDistance;
	private int filtering;
	private DataModel model;
	private HashMap<Long, Vector<Integer>> networkMap;
	
	public GenspaceUserSimilarity(DataModel model, int filtering, HashMap<Long, Vector<Integer>> networkMap) throws TasteException {
		//euclideanDistance = new EuclideanDistanceSimilarity(model);
		euclideanDistance = new SpearmanCorrelationSimilarity(model);
		this.filtering = filtering;
		this.model = model;
		this.networkMap = networkMap;
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
		Vector<Integer> networks1;
		Vector<Integer> networks2;
		if (filtering == 0)
			return euclideanSimilarity;
		else if (filtering == 1) {
			double r = 0;
			networks1 = networkMap.get(userID1);
			networks2 = networkMap.get(userID2);
			int size = networks1.size();
			int equalItems = 0;
			
			/**
			 * a1 + b = 1
			 * b = -1; => a = 2
			 */
			for (Integer i : networks1) {
				for (Integer j : networks2) {
					if (i.equals(j))
						equalItems++;
				}
			}
			double n = (double) equalItems / size;
			r = 2*n - 1;
			double similarity = euclideanSimilarity *3/4 + r*1/4;
			return similarity;
		}
		else
			return euclideanSimilarity;
	}
}
