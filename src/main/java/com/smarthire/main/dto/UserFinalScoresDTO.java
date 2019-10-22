package com.smarthire.main.dto;

public class UserFinalScoresDTO {
	private String username;
	private double final_score;
	
	public UserFinalScoresDTO() {
		super();
	}
	public UserFinalScoresDTO(String username, double final_score) {
		super();
		this.username = username;
		this.final_score = final_score;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public double getFinal_score() {
		return final_score;
	}
	public void setFinal_score(double final_score) {
		this.final_score = final_score;
	}
	
	
}
