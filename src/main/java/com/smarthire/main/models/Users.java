package com.smarthire.main.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name="USERS")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorValue("usergeneral")
public abstract class Users implements Serializable{
	
	@Id	
	@Column(name="username")
	private String username;
	
	@Column(name="email")
	private String email;
	
	@Column(name="password")
	private String password;
	
	@Column(name="role")
	private String role;
	
	@Column(name="firstname")
	private String firstname;
	
	@Column(name="lastname")
	private String lastname;
	
	@Column(name="contactNumber")
	private String contactnumber;
		
	@Column(name="address")
	private String address;
			
	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
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

	public String getContactnumber() {
		return contactnumber;
	}

	public void setContactnumber(String contactnumber) {
		this.contactnumber = contactnumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Users(String username, String email, String password, String role, String firstname, String lastname,
			String contactnumber, String address) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.role = role;
		this.firstname = firstname;
		this.lastname = lastname;
		this.contactnumber = contactnumber;
		this.address = address;
	}

	@Override
	public String toString() {
		return "Users [" + (username != null ? "username=" + username + ", " : "")
				+ (email != null ? "email=" + email + ", " : "")
				+ (password != null ? "password=" + password + ", " : "") + (role != null ? "role=" + role + ", " : "")
				+ (firstname != null ? "firstname=" + firstname + ", " : "")
				+ (lastname != null ? "lastname=" + lastname + ", " : "")
				+ (contactnumber != null ? "contactnumber=" + contactnumber + ", " : "")
				+ (address != null ? "address=" + address : "") + "]";
	}

	
	
	
	
}
