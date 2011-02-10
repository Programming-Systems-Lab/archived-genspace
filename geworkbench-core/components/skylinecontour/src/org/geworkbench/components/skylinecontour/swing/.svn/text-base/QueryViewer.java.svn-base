package org.geworkbench.components.skylinecontour.swing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import edu.columbia.cs.skys.cache.CacheException;
import edu.columbia.cs.skys.data.Point;
import org.geworkbench.components.skylinecontour.query.QueryManager;
import org.geworkbench.components.skylinecontour.query.QuerySession;


/**
 * $Id: QueryViewer.java,v 1.3 2009-11-18 16:57:42 jiz Exp $
 *
 * Simple class to view skyline of a query.
 *
 * @author William Mee <wjm2107@cs.columbia.edu>
 * Copyright (c) 2008 The Trustees of Columbia University
 */
public class QueryViewer {


	private Point<?,?>[] filterPoints(Point<?,?> [] points) {
		if (points==null || points.length==0) {
			return points;
		}
		List<Point<?,?>> filteredPoints = new ArrayList<Point<?,?>>(points.length);
		for (Point<?,?> point: points) {
			if (point.xToDouble()==0 && point.yToDouble()==0) {
				continue;
			}
			filteredPoints.add(point);
		}
		return filteredPoints.toArray(new Point<?,?>[0]);
	}
	
	public void view(String keywordQuery) {

        GenericSkylineViewer slviewer = GenericSkylineViewer.createAndShowGUI(null);
		
		try {
			QueryManager qm = new QueryManager();
			int reqId = -1;	
			// start a query session
			QuerySession qs = qm.spawnQuerySession(keywordQuery, reqId);
			qs.setMaxBatches(2);
			qs.runQuery();
			//Set<Point<Integer,Integer>> batchPoints = null;
			Point<?,?>[] batchPoints = null;
			do {
				batchPoints = qs.readNextBatch();
				batchPoints = filterPoints(batchPoints);
				if ((batchPoints!=null) && (batchPoints.length > 0)) {
					Arrays.sort(batchPoints,new PointComparator());
					slviewer.extend(batchPoints);
				} else {
					/*
					if (batchPoints.size()>0) {
						// sort the points
				        Point<?,?> [] pointsArray = new Point<?,?>[batchPoints.size()];
				        batchPoints.toArray(pointsArray);
				        Arrays.sort(pointsArray,new Comparator<Point<?,?>>() {
							@Override
							public int compare(Point<?, ?> o1, Point<?, ?> o2) {
								if (o1.xToDouble()==o2.xToDouble()) {
									return 0;
								}
								if (o1.xToDouble()<o2.xToDouble()) {
									return -1;
								}
								return 1;
							}
				        });
				        sliewer.extend(pointsArray);
					} else {
				        */
					// sleep
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// interrupted
					}
				}
			} while (batchPoints!=null);
	
		} catch (CacheException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	class PointComparator implements Comparator<Point<?,?>> {

			@Override 
			public int compare(Point<?,?> arg0, Point<?,?> arg1){
				double x0=arg0.xToDouble();
				double x1=arg1.xToDouble();
				if (x0==x1) {
					return 0;
				}
				if (x0<x1) {
					return -1;
				}
				return 1;
			}
			
		}
		
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		QueryViewer viewer = new QueryViewer();
		if (args.length==1) {
			viewer.view(args[0]);
		} else {
			
			//viewer.view("Phosphotransferases (Alcohol Group Acceptor)");  
			//viewer.view("Asthma");
			//viewer.view("antiphospholipid antibodies");  
			//viewer.view("Systemic Lupus");  
			//viewer.view("Alzheimer Disease");

			//viewer.view("CD4-Positive T-Lymphocytes");
			//viewer.view("low-density lipoprotein");
			//viewer.view("G-Protein-Coupled receptors");
			//viewer.view("Gram-Negative Bacterial Infections");
			viewer.view("hepatitis human");
		}
	}
}
