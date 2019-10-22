package com.smarthire.main.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "EMPLOYER")
@PrimaryKeyJoinColumn(name = "username")
public class Employer extends Users implements Serializable {

	@Column(name = "company_name")
	private String company_name;

	@Column(name = "company_address")
	private String company_address;

	@Column(name = "designation")
	private String designation;

	@Column(name = "tagline")
	private String tagline;

	@Column(name="website")
	private String website;
	
	public Employer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employer(String company_name, String company_address, String designation, String tagline, String website) {
		super();
		this.company_name = company_name;
		this.company_address = company_address;
		this.designation = designation;
		this.tagline = tagline;
		this.website = website;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getCompany_address() {
		return company_address;
	}

	public void setCompany_address(String company_address) {
		this.company_address = company_address;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getTagline() {
		return tagline;
	}

	public void setTagline(String tagline) {
		this.tagline = tagline;
	}

	@Override
	public String toString() {
		return "Employer [" + (company_name != null ? "company_name=" + company_name + ", " : "")
				+ (company_address != null ? "company_address=" + company_address + ", " : "")
				+ (designation != null ? "designation=" + designation + ", " : "")
				+ (tagline != null ? "tagline=" + tagline + ", " : "")
				+ (website != null ? "website=" + website + ", " : "")
				+ (super.toString() != null ? "toString()=" + super.toString() : "") + "]";
	}

	
	
	

}
