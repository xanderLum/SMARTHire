package com.smarthire.xander.models;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "FILES_UPLOAD")
public class Image implements Serializable{
	private Long id;
	private String fileName;
	private byte[] data;
	private String username;
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "fileName")
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Column(name = "data")
	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	@Column(name="username")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "Image [" + (id != null ? "id=" + id + ", " : "")
				+ (fileName != null ? "fileName=" + fileName + ", " : "")
				+ (data != null ? "data=" + Arrays.toString(data) + ", " : "")
				+ (username != null ? "username=" + username : "") + "]";
	}
	
	
	
	
}
