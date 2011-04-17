package org.geworkbench.components.genspace.bean;

import java.io.Serializable;

<<<<<<< HEAD
public class RatingBean implements Serializable, Comparable {
=======
public class RatingBean implements Serializable, Comparable<RatingBean> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4515026175616921380L;
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7

	// the primary key of the object being rated
	private int identifier;

	// an average of all ratings for the given object (0 if none exist)
	private double overallRating;

	// the user's rating of the object (0 if currently not rated by user, -1 if
	// rating is not allowed)
	private double userRating = 0;

	// total number of ratings for the object
	private long totalRatings;

	private String username;

	public int dbPK;

	public RatingBean(int i, double o, double u, long t) {
		identifier = i;
		overallRating = o;
		userRating = u;
		totalRatings = t;
	}

	public RatingBean() {
	}

	public double getOverallRating() {
		return overallRating;
	}

	public double getUserRating() {
		return userRating;
	}

	public int getIdentifier() {
		return identifier;
	}

	public long getTotalRatings() {
		return totalRatings;
	}

	@Override
	public String toString() {
		return "(Avg:" + overallRating + " User:" + userRating
				+ " Total Times Rated:" + totalRatings + ")";

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setUserRating(double rating) {
		userRating = rating;
	}

	@Override
<<<<<<< HEAD
	public int compareTo(Object arg0) {
=======
	public int compareTo(RatingBean arg0) {
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7
		RatingBean other = (RatingBean) arg0;
		if (this.overallRating > other.overallRating)
			return 1;
		else if (this.overallRating < other.overallRating)
			return -1;
		else
			return 0;
	}
}