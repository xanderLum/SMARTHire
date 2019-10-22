package com.smarthire.xander.dto;

public class ObjectScore {
	private String username;
	private double score;
	
	
	public ObjectScore(String username, double score) {
		super();
		this.username = username;
		this.score = score;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	
	
}
