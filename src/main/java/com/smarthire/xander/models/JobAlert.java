package com.smarthire.xander.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="JOB_ALERT")
public class JobAlert implements Serializable{
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	
	@Column(name="alertName")
	private String alertName;
	
	@Column(name="job_post_id")
	private Long job_post_id;
	
	@Column(name="location")
	private String location;
	
	@Column(name="status")
	private String status;
	
	@Column(name="jsUsername")
	private String jsUsername;
	
	@Column(name="dateCreated")
	private String dateCreated;
	
	@Column(name="keywords")
	private String keywords;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getJob_post_id() {
		return job_post_id;
	}

	public void setJob_post_id(Long job_post_id) {
		this.job_post_id = job_post_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getJsUsername() {
		return jsUsername;
	}

	public void setJsUsername(String jsUsername) {
		this.jsUsername = jsUsername;
	}


	public JobAlert(Long id, String alertName, Long job_post_id, String location, String status, String jsUsername,
			String dateCreated, String keywords) {
		super();
		this.id = id;
		this.alertName = alertName;
		this.job_post_id = job_post_id;
		this.location = location;
		this.status = status;
		this.jsUsername = jsUsername;
		this.dateCreated = dateCreated;
		this.keywords = keywords;
	}

	@Override
	public String toString() {
		return "JobAlert [" + (id != null ? "id=" + id + ", " : "")
				+ (alertName != null ? "alertName=" + alertName + ", " : "")
				+ (job_post_id != null ? "job_post_id=" + job_post_id + ", " : "")
				+ (location != null ? "location=" + location + ", " : "")
				+ (status != null ? "status=" + status + ", " : "")
				+ (jsUsername != null ? "jsUsername=" + jsUsername + ", " : "")
				+ (dateCreated != null ? "dateCreated=" + dateCreated + ", " : "")
				+ (keywords != null ? "keywords=" + keywords : "") + "]";
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	public JobAlert() {
		super();
	}

	public String getAlertName() {
		return alertName;
	}

	public void setAlertName(String alertName) {
		this.alertName = alertName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	
	
	
	
	

}
