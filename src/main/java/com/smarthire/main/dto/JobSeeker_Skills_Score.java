package com.smarthire.main.dto;

public class JobSeeker_Skills_Score {
	private String username;
	private String skills_name;
	private int scores;
	public JobSeeker_Skills_Score() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public JobSeeker_Skills_Score(String username, String skills_name, int scores) {
		super();
		this.username = username;
		this.skills_name = skills_name;
		this.scores = scores;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSkills_name() {
		return skills_name;
	}
	public void setSkills_name(String skills_name) {
		this.skills_name = skills_name;
	}
	public int getScores() {
		return scores;
	}
	public void setScores(int scores) {
		this.scores = scores;
	}	
}
