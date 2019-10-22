package com.smarthire.main.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SiteStandardProfileRequest {
	
	@JsonProperty("url")
	private String url;

	public SiteStandardProfileRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SiteStandardProfileRequest(String url) {
		super();
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
