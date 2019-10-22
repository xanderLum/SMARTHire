package com.smarthire.xander.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Test")
public class Test {
	@Id
	@GeneratedValue
	@Column(name = "testId")
	private Long testId;

	@Column(name = "username")
	private String username;

	@Column(name = "name")
	private String name;

	@Column(name = "score")
	private int score;
	
	@Column(name = "duration")
	private int duration;
	
	@Column(name = "isPassed")
	private boolean isPassed;
	
	@Column(name = "rank")
	private int rank;
	
	@Column(name = "percentile")
	private int percentile;
	
	@Column(name = "id")
	private int id;
	
	@Column(name = "dateTaken")
	private String dateTaken;
	
	@Column(name = "provider")
	private String provider;

	
	public Test() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Test(Long testId, String username, String name, int score, int duration, boolean isPassed, int rank,
			int percentile, int id, String dateTaken, String provider) {
		this.testId = testId;
		this.username = username;
		this.name = name;
		this.score = score;
		this.duration = duration;
		this.isPassed = isPassed;
		this.rank = rank;
		this.percentile = percentile;
		this.id = id;
		this.dateTaken = dateTaken;
		this.provider = provider;
	}

	public Long getTestId() {
		return testId;
	}

	public void setTestId(Long testId) {
		this.testId = testId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

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

	@Override
	public String toString() {
		return "Test [" + (testId != null ? "testId=" + testId + ", " : "")
				+ (username != null ? "username=" + username + ", " : "") + (name != null ? "name=" + name + ", " : "")
				+ "score=" + score + ", duration=" + duration + ", isPassed=" + isPassed + ", rank=" + rank
				+ ", percentile=" + percentile + ", id=" + id + ", "
				+ (dateTaken != null ? "dateTaken=" + dateTaken + ", " : "")
				+ (provider != null ? "provider=" + provider : "") + "]";
	}
	
	
}
