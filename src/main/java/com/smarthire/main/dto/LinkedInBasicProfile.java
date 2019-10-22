package com.smarthire.main.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LinkedInBasicProfile{

	private String id;
	private String firstName;
	private String headline;
	private String lastName;
	private String siteStandardProfileRequest;
	private String url;
	private String industry;
	private String numConnections;
	private String publicProfileUrl;
	
	public LinkedInBasicProfile() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LinkedInBasicProfile(String id, String firstName, String headline, String lastName,
			String siteStandardProfileRequest, String url, String industry, String numConnections,
			String publicProfileUrl) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.headline = headline;
		this.lastName = lastName;
		this.siteStandardProfileRequest = siteStandardProfileRequest;
		this.url = url;
		this.industry = industry;
		this.numConnections = numConnections;
		this.publicProfileUrl = publicProfileUrl;
	}




	public String getPublicProfileUrl() {
		return publicProfileUrl;
	}

	public void setPublicProfileUrl(String publicProfileUrl) {
		this.publicProfileUrl = publicProfileUrl;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getHeadline() {
		return headline;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	
	public String getUrl() {
		return url;
	}

	@JsonProperty("siteStandardProfileRequest")
	public void setUrl(SiteStandardProfileRequest siteStandardProfileRequest) {
		this.url = siteStandardProfileRequest.getUrl();
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getNumConnections() {
		return numConnections;
	}

	public void setNumConnections(String numConnections) {
		this.numConnections = numConnections;
	}

	
}
