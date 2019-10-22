package com.smarthire.xander.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CHAR_CATEGORYSCORE")
public class CHAR_CATEGORYSCORE implements Serializable {

	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="username")
	private String username;
	
	@Column(name="categoryName")
	private String categoryName;
	
	@Column(name="positive")
	private int positive;
	@Column(name="neutral")
	private int neutral;	
	@Column(name="negative")
	private int negative;
	@Column(name="totalPost")
	private int totalPost;
	
	@Column(name="categoryScore")
	private String categoryScore;

	public CHAR_CATEGORYSCORE() {
		super();
		// TODO Auto-generated constructor stub
	}
		
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public double getCategoryScore() {
		return this.categoryScore!= null ? Double.valueOf(this.categoryScore) : null;
	}

	public void setCategoryScore(double categoryScore) {
		this.categoryScore = categoryScore != Double.NaN ? String.valueOf(categoryScore) : null;
	}

	public int getPositive() {
		return positive;
	}

	public void setPositive(int positive) {
		this.positive = positive;
	}
	public int getNeutral() {
		return neutral;
	}

	public void setNeutral(int neutral) {
		this.neutral = neutral;
	}

	public int getNegative() {
		return negative;
	}

	public void setNegative(int negative) {
		this.negative = negative;
	}

	public int getTotalPost() {
		return totalPost;
	}

	public void setTotalPost(int totalPost) {
		this.totalPost = totalPost;
	}



	public CHAR_CATEGORYSCORE(Long id, String username, String categoryName, int positive, int neutral, int negative,
			int totalPost, String categoryScore) {
		super();
		this.id = id;
		this.username = username;
		this.categoryName = categoryName;
		this.positive = positive;
		this.neutral = neutral;
		this.negative = negative;
		this.totalPost = totalPost;
		this.categoryScore = categoryScore;
	}

	public CHAR_CATEGORYSCORE(String username, String categoryName, int positive, int neutral, int negative,
			int totalPost, String categoryScore) {
		super();
		this.id = id;
		this.username = username;
		this.categoryName = categoryName;
		this.positive = positive;
		this.neutral = neutral;
		this.negative = negative;
		this.totalPost = totalPost;
		this.categoryScore = categoryScore;
	}



	@Override
	public String toString() {
		return "CHAR_CATEGORYSCORE [" + (id != null ? "id=" + id + ", " : "")
				+ (username != null ? "username=" + username + ", " : "")
				+ (categoryName != null ? "categoryName=" + categoryName + ", " : "") + "positive=" + positive
				+ ", neutral=" + neutral + ", negative=" + negative + ", totalPost=" + totalPost + ", "
				+ (categoryScore != null ? "categoryScore=" + categoryScore : "") + "]";
	}
	
	
	
	
	
	
}
