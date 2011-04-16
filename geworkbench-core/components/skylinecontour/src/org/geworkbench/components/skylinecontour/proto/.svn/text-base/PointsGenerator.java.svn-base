package org.geworkbench.components.skylinecontour.proto;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

import edu.columbia.cs.skys.data.Point;


public class PointsGenerator {

	public static final int TYPE_RANDOM = 0;
	public static final int TYPE_CORRELATED = 1;
	public static final int TYPE_ANTI_CORRELATED = 2;
	
	public static Point<Double, Double>[] generate(int num, double lowX, double lowY, double hiX, double hiY,  int type) {
		Point<Double, Double> [] points = new Point[num];
		Random random = new Random();
		double xrange = hiX - lowX;
		double yrange = hiY - lowY;
		double corrx, corry, factor;
		for (int i=0; i < num; i++) {
			double x = -1;
			double y = -1;
			switch (type) {
			case TYPE_RANDOM:
				x = random.nextDouble() * xrange;
				y = random.nextDouble() * yrange;
				break;
			case TYPE_CORRELATED:
				corrx = random.nextDouble() * xrange;
				corry = yrange*(corrx/xrange);
				factor = 50 * Math.sin(corrx/xrange * Math.PI);
				while (y < 0 || y >yrange) {
					y = corry + factor * random.nextGaussian();
				}				
				while (x < 0 || x >xrange) {
					x = corrx + factor * random.nextGaussian();
				}
				break;
			case TYPE_ANTI_CORRELATED:
				corrx = random.nextDouble() * xrange;
				corry = yrange - yrange*(corrx/xrange);
				factor = 50 * Math.sin(corrx/xrange * Math.PI);
				while (y < 0 || y >yrange) {
					y = corry + factor * random.nextGaussian();
				}				
				while (x < 0 || x >xrange) {
					x = corrx + factor * random.nextGaussian();
				}
				break;
			}			
			x += lowX;
			y += lowY;
			points[i] = new Point<Double, Double>(11000000+i,x,y);
		}
		Arrays.sort(points, new Comparator<Point<Double, Double>>() {
			public int compare(Point<Double, Double> o1, Point<Double, Double> o2) {
				double x1=o1.x();
				double x2=o2.x();
				if (x1==x2) return 0;
				if (x1<x2) return -1;
				return 1;
			}
		});
		return points;
		
	}
	
}
