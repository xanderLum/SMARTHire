package com.smarthire.main.dto;

public class ChangeInfo {
	private String firstname;
	private String lastname;
	private String email_address;
	private String address;
	private String contactnumber;
	private int experience;
	public ChangeInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ChangeInfo(String firstname, String lastname, String email_address, String address, String contactnumber,
			int experience) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.email_address = email_address;
		this.address = address;
		this.contactnumber = contactnumber;
		this.experience = experience;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail_address() {
		return email_address;
	}
	public void setEmail_address(String email_address) {
		this.email_address = email_address;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContactnumber() {
		return contactnumber;
	}
	public void setContactnumber(String contactnumber) {
		this.contactnumber = contactnumber;
	}
	public int getExperience() {
		return experience;
	}
	public void setExperience(int experience) {
		this.experience = experience;
	}
	
	
}
