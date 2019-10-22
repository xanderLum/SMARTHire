package com.smarthire.xander.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Certificate")
public class Certificate implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="username")
	private String username;
	
	@Column(name="uid")
	private int uid;
	
	@Column(name="name")
	private String name;
	
	@Column(name="description")
	private String description;

	
	public Certificate() {
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

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public Certificate(Long id, String username, int uid, String name, String description) {
		super();
		this.id = id;
		this.username = username;
		this.uid = uid;
		this.name = name;
		this.description = description;
	}


	@Override
	public String toString() {
		return "Certificate [" + (id != null ? "id=" + id + ", " : "")
				+ (username != null ? "username=" + username + ", " : "") + "uid=" + uid + ", "
				+ (name != null ? "name=" + name + ", " : "")
				+ (description != null ? "description=" + description : "") + "]";
	}

	
	
}
