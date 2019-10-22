package com.smarthire.main.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="JOB_POST")
public class JobPost implements Serializable{

	@Id
	@Column(name="job_post_id") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long job_post_id;

	@Column(name="job_title") 
	private String job_title;
	
	@Column(name="job_description") 
	private String job_description;
	
	@Column(name="location")
	private String location;
	
	@Column(name="job_type")
	private String job_type;
	
	@Column(name="category")
	private String category;
	
	@Column(name="job_status") 
	private String job_status;
	
	@Column(name="username") 
	private String username;
	
	@Column(name="date_posted") 
	private String date_posted;
	
	@Column(name="closing_date")
	private String closing_date;
	

	public JobPost() {
		super();
	}

	@Override
	public String toString() {
		return "JobPost [" + (job_post_id != null ? "job_post_id=" + job_post_id + ", " : "")
				+ (job_title != null ? "job_title=" + job_title + ", " : "")
				+ (job_description != null ? "job_description=" + job_description + ", " : "")
				+ (location != null ? "location=" + location + ", " : "")
				+ (job_type != null ? "job_type=" + job_type + ", " : "")
				+ (category != null ? "category=" + category + ", " : "")
				+ (job_status != null ? "job_status=" + job_status + ", " : "")
				+ (username != null ? "username=" + username + ", " : "")
				+ (date_posted != null ? "date_posted=" + date_posted + ", " : "")
				+ (closing_date != null ? "closing_date=" + closing_date : "") + "]";
	}



	public JobPost(Long job_post_id, String job_title, String job_description, String location, String job_type,
			String category, String job_status, String username, String date_posted, String closing_date) {
		super();
		this.job_post_id = job_post_id;
		this.job_title = job_title;
		this.job_description = job_description;
		this.location = location;
		this.job_type = job_type;
		this.category = category;
		this.job_status = job_status;
		this.username = username;
		this.date_posted = date_posted;
		this.closing_date = closing_date;
	}



	public Long getJob_post_id() {
		return job_post_id;
	}

	public void setJob_post_id(Long job_post_id) {
		this.job_post_id = job_post_id;
	}

	public String getJob_title() {
		return job_title;
	}

	public void setJob_title(String job_title) {
		this.job_title = job_title;
	}

	public String getJob_description() {
		return job_description;
	}

	public void setJob_description(String job_description) {
		this.job_description = job_description;
	}

	public String getJob_status() {
		return job_status;
	}

	public void setJob_status(String job_status) {
		this.job_status = job_status;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDate_posted() {
		return date_posted;
	}

	public void setDate_posted(String date_posted) {
		this.date_posted = date_posted;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public String getJob_type() {
		return job_type;
	}


	public void setJob_type(String job_type) {
		this.job_type = job_type;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public String getClosing_date() {
		return closing_date;
	}


	public void setClosing_date(String closing_date) {
		this.closing_date = closing_date;
	}
	
	
	
}
