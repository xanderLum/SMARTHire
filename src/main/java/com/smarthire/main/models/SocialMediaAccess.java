package com.smarthire.main.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SOCIAL_MEDIA_ACCESS")
public class SocialMediaAccess implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="sma_id") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long sma_id;
	
	@Column(name="username") 
	private String username;
	
	@Column(name="social_media")
	private String social_media;
	
	@Column(name="token_type")
	private String token_type;
	
	@Column(name="token_data")
	private String token_data;
	
	@Column(name="token_status")
	private String token_status;
	
	@Column(name="last_update")
	private String last_update;

	public SocialMediaAccess() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SocialMediaAccess(String username, String social_media, String token_type, String token_data,
			String token_status, String last_update) {
		super();
		this.username = username;
		this.social_media = social_media;
		this.token_type = token_type;
		this.token_data = token_data;
		this.token_status = token_status;
		this.last_update = last_update;
	}
	public Long getSma_id() {
		return sma_id;
	}
	public void setSma_id(Long sma_id) {
		this.sma_id = sma_id;
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
	public String getToken_type() {
		return token_type;
	}
	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}
	public String getToken_data() {
		return token_data;
	}
	public void setToken_data(String token_data) {
		this.token_data = token_data;
	}
	public String getToken_status() {
		return token_status;
	}
	public void setToken_status(String token_status) {
		this.token_status = token_status;
	}
	public String getLast_update() {
		return last_update;
	}
	public void setLast_update(String last_update) {
		this.last_update = last_update;
	}
	
	
}
