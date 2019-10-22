package com.smarthire.main.dto;

public class JobsAppliedContent {

	private String job_title;
	private String job_description;
	private String date_applied;
	private String company;
	
	public JobsAppliedContent() {
		super();
	}
	public JobsAppliedContent(String job_title, String job_description, String date_applied, String company) {
		super();
		this.job_title = job_title;
		this.job_description = job_description;
		this.date_applied = date_applied;
		this.company = company;
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
	public String getDate_applied() {
		return date_applied;
	}
	public void setDate_applied(String date_applied) {
		this.date_applied = date_applied;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
}
