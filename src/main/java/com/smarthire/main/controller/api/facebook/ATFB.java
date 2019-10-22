package com.smarthire.main.controller.api.facebook;

public class ATFB {

	private String accessToken;
	private String userID;
	private String expiresIn;
	private String signedRequest;
	
	
	
	public ATFB() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ATFB(String accessToken, String userID, String expiresIn, String signedRequest) {
		super();
		this.accessToken = accessToken;
		this.userID = userID;
		this.expiresIn = expiresIn;
		this.signedRequest = signedRequest;
	}

	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(String expiresIn) {
		this.expiresIn = expiresIn;
	}
	public String getSignedRequest() {
		return signedRequest;
	}
	public void setSignedRequest(String signedRequest) {
		this.signedRequest = signedRequest;
	}
	
	
	
}
