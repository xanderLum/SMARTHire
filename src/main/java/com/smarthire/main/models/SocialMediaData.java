package com.smarthire.main.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SOCIAL_MEDIA_DATA")
public class SocialMediaData implements Serializable {
	
	@Id
	@Column(name="data_id")
	private String data_id;
	
	@Column(name="username") 
	private String username;

	@Column(name="social_media")
	private String social_media;
	
	@Column(name="data")
	private String data;
	
	@Column(name="usable_data")
	private String usable_data;
	
	@Column(name="data_type")
	private String data_type;
	
	@Column(name="date_posted")
	private String date_posted;

	public SocialMediaData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SocialMediaData(String data_id, String username, String social_media, String data, String usable_data, String data_type,
			String date_posted) {
		super();
		this.data_id = data_id;
		this.username = username;
		this.social_media = social_media;
		this.data = data;
		this.usable_data = usable_data;
		this.data_type = data_type;
		this.date_posted = date_posted;
	}

	public String getData_id() {
		return data_id;
	}
	public void setData_id(String data_id) {
		this.data_id = data_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSocial_media() {
		return social_media;
	}
	public void setSocial_media(String social_media) {
		this.social_media = social_media;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getUsable_data() {
		return usable_data;
	}
	public void setUsable_data(String usable_data) {
		this.usable_data = usable_data;
	}
	public String getData_type() {
		return data_type;
	}
	public void setData_type(String data_type) {
		this.data_type = data_type;
	}
	public String getDate_posted() {
		return date_posted;
	}
	public void setDate_posted(String date_posted) {
		this.date_posted = date_posted;
	}
}