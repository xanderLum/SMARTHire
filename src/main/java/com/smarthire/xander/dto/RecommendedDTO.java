package com.smarthire.xander.dto;

import java.util.LinkedList;

import com.smarthire.main.models.JobSeeker;

public class RecommendedDTO {
	private double charScore;
	private double skillScore;
	private String username;
	private String type;
	private JobSeeker js;
	private LinkedList<String> matchedSkills;
	
	public JobSeeker getJs() {
		return js;
	}
	public void setJs(JobSeeker js) {
		this.js = js;
	}
	public double getCharScore() {
		return charScore;
	}
	public void setCharScore(double charScore) {
		this.charScore = charScore;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public double getSkillScore() {
		return skillScore;
	}
	public void setSkillScore(double skillScore) {
		this.skillScore = skillScore;
	}
	
	
	public LinkedList<String> getMatchedSkills() {
		return matchedSkills;
	}
	public void setMatchedSkills(LinkedList<String> matchedSkills) {
		this.matchedSkills = matchedSkills;
	}
	public RecommendedDTO(double charScore, double skillScore, String username, String type, JobSeeker js,
			LinkedList<String> matchedSkills) {
		super();
		this.charScore = charScore;
		this.skillScore = skillScore;
		this.username = username;
		this.type = type;
		this.js = js;
		this.matchedSkills = matchedSkills;
	}
	@Override
	public String toString() {
		return "RecommendedDTO [charScore=" + charScore + ", skillScore=" + skillScore + ", "
				+ (username != null ? "username=" + username + ", " : "") + (type != null ? "type=" + type + ", " : "")
				+ (js != null ? "js=" + js + ", " : "")
				+ (matchedSkills != null ? "matchedSkills=" + matchedSkills : "") + "]";
	}
	
	
	
	

	
}
