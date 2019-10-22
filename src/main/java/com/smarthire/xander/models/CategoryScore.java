package com.smarthire.xander.models;

public class CategoryScore {
	private String categoryName;
	private int positive;
	private int neutral;
	private int negative;
	private int totalPost;
	
	
	public CategoryScore() {
		super();
	}
	
	public CategoryScore(String categoryName, int positive, int neutral, int negative, int totalPost) {
	        this.categoryName = categoryName;
	        this.positive = positive;
	        this.neutral = neutral;
	        this.negative = negative;
	        this.totalPost = totalPost;
	}
	 
	/*public CategoryScore(String categoryName, int noOfPlusPosts, int noOfNegaPosts, int totalNoOfPosts) {
		super();
		this.categoryName = categoryName;
		this.noOfPlusPosts = noOfPlusPosts;
		this.noOfNegaPosts = noOfNegaPosts;
		this.totalNoOfPosts = totalNoOfPosts;
	}*/
	
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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
	
	
	
	
}
