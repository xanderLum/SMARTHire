package com.smarthire.main.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity
@Table(name="JOBSEEKER")
@PrimaryKeyJoinColumn(name="username")
public class JobSeeker extends Users implements Serializable{
	
	@Column(name="professionalTitle")
	private String professionalTitle;
	
	@Column(name="description")
	private String description;

	public JobSeeker() {
		super();
		// TODO Auto-generated constructor stub
	}


	public JobSeeker(String username, String email, String password, String role, String firstname, String lastname,
			String contactnumber, String address) {
		super(username, email, password, role, firstname, lastname, contactnumber, address);
		// TODO Auto-generated constructor stub
	}

	public String getProfessionalTitle() {
		return professionalTitle;
	}

	public void setProfessionalTitle(String professionalTitle) {
		this.professionalTitle = professionalTitle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	@Override
	public String toString() {
		return "JobSeeker [" + (professionalTitle != null ? "professionalTitle=" + professionalTitle + ", " : "")
				+ (description != null ? "description=" + description + ", " : "")
				+ (super.toString() != null ? "toString()=" + super.toString() : "") + "]";
	}

	
	
	
	
}
