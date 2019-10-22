package com.smarthire.main.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="JOB_POST_APPLICANTS")
public class JobPostApplicants implements Serializable{
	
	@Id
	@GeneratedValue
	@Column(name="jpa_id") 
	private Long jpa_id;
	
	@Column(name="job_post_id") 
	private Long job_post_id;
	
	@Column(name="username") 
	private String username;
	
	@Column(name="date_applied") 
	private String date_applied;

	public JobPostApplicants() {
		super();
	}
	public JobPostApplicants(Long job_post_id, String username, String date_applied) {
		super();
		this.job_post_id = job_post_id;
		this.username = username;
		this.date_applied = date_applied;
	}
	public Long getJpa_id() {
		return jpa_id;
	}
	public void setJpa_id(Long jpa_id) {
		this.jpa_id = jpa_id;
	}
	public Long getJob_post_id() {
		return job_post_id;
	}
	public void setJob_post_id(Long job_post_id) {
		this.job_post_id = job_post_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getDate_applied() {
		return date_applied;
	}
	public void setDate_applied(String date_applied) {
		this.date_applied = date_applied;
	}
}
