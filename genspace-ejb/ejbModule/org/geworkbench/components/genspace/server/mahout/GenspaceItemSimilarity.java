package org.geworkbench.components.genspace.server.mahout;

import java.util.Collection;
import java.util.HashMap;

import org.apache.mahout.cf.taste.common.Refreshable;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.similarity.AbstractItemSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.SpearmanCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;

public class GenspaceItemSimilarity extends AbstractItemSimilarity{

	private int PARENT_DEPTH = 3;
	private HashMap<Long, Long> parentMap;
	
	public GenspaceItemSimilarity(DataModel model, HashMap<Long, Long> parentMap) throws TasteException {
		super(model);
		this.parentMap = parentMap;
	}

	@Override
	public double[] itemSimilarities(long arg0, long[] arg1)
			throws TasteException {
		int length = arg1.length;
	    double[] result = new double[length];
	    for (int i = 0; i < length; i++) {
	      result[i] = itemSimilarity(arg0, arg1[i]);
	    }
	    return result;
	}

	@Override
	public double itemSimilarity(long arg0, long arg1) throws TasteException {
		// TODO Auto-generated method stub
		int r = 0;
		long currentId1 = arg0;
		long currentId2 = arg1;
		//System.out.println(MahoutTest.getParentMap().get(currentId1) + " " + MahoutTest.getParentMap().get(currentId2));
		for (int i=0; i< PARENT_DEPTH; i++) {
			if (!parentMap.containsKey(currentId1) || 
					!parentMap.containsKey(currentId2))
				break;
			currentId1 = parentMap.get(currentId1);
			currentId2 = parentMap.get(currentId2);
			if (currentId1 * currentId2 == 0)
				break;
			else if (currentId1 == currentId2) {
				r = i;
			} else if (currentId1 == arg1 || currentId2 == arg0) {
				r = i;
			}
		}
		if (r == 0)
			r = PARENT_DEPTH;
		// r is between PARENT_DEPTH and 0;
		double metrics = (double)-2 * r/PARENT_DEPTH + 1; // [-1;1]
		
		/*
		if (metrics > -1)
			System.out.println(metrics + " " + arg0 + " " + arg1);
		*/	
		return metrics;
		//return similarity.itemSimilarity(arg0, arg1) * 3/4 + metrics * 1/4;
	}
}
