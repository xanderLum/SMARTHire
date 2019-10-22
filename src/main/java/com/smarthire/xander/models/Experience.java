package com.smarthire.xander.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Experience")
public class Experience implements Serializable {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;

	@Column(name = "username")
	private String username;

	@Column(name = "employer")
	private String employer;

	@Column(name = "jobTitle")
	private String jobTitle;

	@Column(name = "startDate")
	private String startDate;

	@Column(name = "endDate")
	private String endDate;

	@Column(name = "notes")
	private String notes;

	public Experience() {
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

	public String getEmployer() {
		return employer;
	}

	public void setEmployer(String employer) {
		this.employer = employer;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Experience(Long id, String username, String employer, String jobTitle, String startDate, String endDate,
			String notes) {
		super();
		this.id = id;
		this.username = username;
		this.employer = employer;
		this.jobTitle = jobTitle;
		this.startDate = startDate;
		this.endDate = endDate;
		this.notes = notes;
	}

	@Override
	public String toString() {
		return "Experience [" + (id != null ? "id=" + id + ", " : "")
				+ (username != null ? "username=" + username + ", " : "")
				+ (employer != null ? "employer=" + employer + ", " : "")
				+ (jobTitle != null ? "jobTitle=" + jobTitle + ", " : "")
				+ (startDate != null ? "startDate=" + startDate + ", " : "")
				+ (endDate != null ? "endDate=" + endDate + ", " : "") + (notes != null ? "notes=" + notes : "") + "]";
	}

}
