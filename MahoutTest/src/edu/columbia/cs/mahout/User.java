package edu.columbia.cs.mahout;

public class User {
	
	private int userId;
	private int network;
	
	public User (int userId, int network) {
		this.userId = userId;
		this.network = network;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public int getNetwork() {
		return network;
	}
}
