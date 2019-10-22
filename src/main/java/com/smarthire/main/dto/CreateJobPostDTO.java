package com.smarthire.main.dto;

public class CreateJobPostDTO {
	private Long job_post_id;

	private String job_title;
	
	private String job_description;
	
	private String skills;

	private String username;
	
	private String job_type;
	
	private String closing_date;
	
	private String job_category;
	
	private String location;
	
	public CreateJobPostDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	public CreateJobPostDTO(Long job_post_id, String job_title, String job_description, String skills, String username,
			String job_type, String closing_date, String job_category, String location) {
		super();
		this.job_post_id = job_post_id;
		this.job_title = job_title;
		this.job_description = job_description;
		this.skills = skills;
		this.username = username;
		this.job_type = job_type;
		this.closing_date = closing_date;
		this.job_category = job_category;
		this.location = location;
	}






	public String getJob_category() {
		return job_category;
	}




	public void setJob_category(String job_category) {
		this.job_category = job_category;
	}




	public String getLocation() {
		return location;
	}




	public void setLocation(String location) {
		this.location = location;
	}




	public String getClosing_date() {
		return closing_date;
	}




	public void setClosing_date(String closing_date) {
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

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getJob_type() {
		return job_type;
	}

	public void setJob_type(String job_type) {
		this.job_type = job_type;
	}



	@Override
	public String toString() {
		return "CreateJobPostDTO [" + (job_post_id != null ? "job_post_id=" + job_post_id + ", " : "")
				+ (job_title != null ? "job_title=" + job_title + ", " : "")
				+ (job_description != null ? "job_description=" + job_description + ", " : "")
				+ (skills != null ? "skills=" + skills + ", " : "")
				+ (username != null ? "username=" + username + ", " : "")
				+ (job_type != null ? "job_type=" + job_type + ", " : "")
				+ (closing_date != null ? "closing_date=" + closing_date + ", " : "")
				+ (job_category != null ? "job_category=" + job_category + ", " : "")
				+ (location != null ? "location=" + location : "") + "]";
	}

	
	
	
	
}
