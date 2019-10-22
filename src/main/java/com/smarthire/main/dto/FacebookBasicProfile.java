package com.smarthire.main.dto;

public class FacebookBasicProfile {

	private String id;
	private String name;
	private String first_name;
	private String last_name;
	private String gender;
	public FacebookBasicProfile() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FacebookBasicProfile(String id, String name, String first_name, String last_name, String gender) {
		super();
		this.id = id;
		this.name = name;
		this.first_name = first_name;
		this.last_name = last_name;
		this.gender = gender;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
}
