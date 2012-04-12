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

	private UserSimilarity spearmanDistance;
	private int filtering;
	private HashMap<Long, Vector<Integer>> networkMap;
	private HashMap<Long, Integer> visibilityMap;
	
	public GenspaceUserSimilarity(DataModel model, int filtering, HashMap<Long, Vector<Integer>> networkMap, HashMap<Long, Integer> visibilityMap) throws TasteException {
		//euclideanDistance = new EuclideanDistanceSimilarity(model);
		spearmanDistance = new SpearmanCorrelationSimilarity(model);
		this.filtering = filtering;
		this.networkMap = networkMap;
		this.visibilityMap = visibilityMap;
	}
	
	@Override
	public void refresh(Collection<Refreshable> arg0) {
		spearmanDistance.refresh(arg0);		
	}

	@Override
	public void setPreferenceInferrer(PreferenceInferrer arg0) {
		spearmanDistance.setPreferenceInferrer(arg0);
		
	}

	@Override
	public double userSimilarity(long userID1, long userID2) throws TasteException {
		// TODO Auto-generated method stub
		double spearmanSimilarity = spearmanDistance.userSimilarity(userID1, userID2);
		Vector<Integer> networks1;
		Vector<Integer> networks2;
		if(visibilityMap.get(userID1) == 0 || visibilityMap.get(userID2) == 0)
		{
			return Double.NaN;
		}
			double r = 0; 
			networks1 = networkMap.get(userID1);
			networks2 = networkMap.get(userID2);
			
			int equalItems = 0;
			int size = 1;
			
			if (networks1 != null && networks2 != null) {		
				size = networks1.size();
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
			}
			if( (visibilityMap.get(userID1) == 1 || visibilityMap.get(userID2) == 1) && equalItems == 0)
				return Double.NaN;
			if(filtering == 1)
				if (equalItems != 0) {
					double n = (double) equalItems / size;
					r = 2*n - 1; // r is within [-1;1]
					double similarity = spearmanSimilarity *3/4 + r*1/4;
					return similarity;
				} else
					return Double.NaN;
//			System.out.println("Checking " + userID1 + ", " + userID2 +"->" + spearmanSimilarity);

			return spearmanSimilarity;
	}
}
