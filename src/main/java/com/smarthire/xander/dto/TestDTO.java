package com.smarthire.xander.dto;

public class TestDTO {
	private String name;
	private int score;
	private int duration;
	private boolean isPassed;
	private int rank;
	private int percentile;
	private int id;
	private String dateTaken;
	private String provider;
	private int visibility;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public boolean isPassed() {
		return isPassed;
	}
	public void setPassed(boolean isPassed) {
		this.isPassed = isPassed;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public int getPercentile() {
		return percentile;
	}
	public void setPercentile(int percentile) {
		this.percentile = percentile;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDateTaken() {
		return dateTaken;
	}
	public void setDateTaken(String dateTaken) {
		this.dateTaken = dateTaken;
	}
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public int getVisibility() {
		return visibility;
	}
	public void setVisibility(int visibility) {
		this.visibility = visibility;
	}
	@Override
	public String toString() {
		return "Test [" + (name != null ? "name=" + name + ", " : "") + "score=" + score + ", duration=" + duration
				+ ", isPassed=" + isPassed + ", rank=" + rank + ", percentile=" + percentile + ", id=" + id + ", "
				+ (dateTaken != null ? "dateTaken=" + dateTaken + ", " : "")
				+ (provider != null ? "provider=" + provider + ", " : "") + "visibility=" + visibility + "]";
	}
	
	
	
}
