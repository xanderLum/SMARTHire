package com.smarthire.xander.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Education")
public class Education implements Serializable {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;

	@Column(name = "username")
	private String username;

	@Column(name = "schoolName")
	private String schoolName;

	@Column(name = "qualifications")
	private String qualifications;

	@Column(name = "startDate")
	private String startDate;

	@Column(name = "endDate")
	private String endDate;

	@Column(name = "notes")
	private String notes;

	public Education() {
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

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getQualifications() {
		return qualifications;
	}

	public void setQualifications(String qualifications) {
		this.qualifications = qualifications;
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

	public Education(Long id, String username, String schoolName, String qualifications, String startDate,
			String endDate, String notes) {
		super();
		this.id = id;
		this.username = username;
		this.schoolName = schoolName;
		this.qualifications = qualifications;
		this.startDate = startDate;
		this.endDate = endDate;
		this.notes = notes;
	}

	@Override
	public String toString() {
		return "Education [" + (id != null ? "id=" + id + ", " : "")
				+ (username != null ? "username=" + username + ", " : "")
				+ (schoolName != null ? "schoolName=" + schoolName + ", " : "")
				+ (qualifications != null ? "qualifications=" + qualifications + ", " : "")
				+ (startDate != null ? "startDate=" + startDate + ", " : "")
				+ (endDate != null ? "endDate=" + endDate + ", " : "") + (notes != null ? "notes=" + notes : "") + "]";
	}
	
	
	

}
