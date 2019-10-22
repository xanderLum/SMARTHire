package com.smarthire.main.dto.fb;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Data {
	private String message;
	private String story;
	private String created_time;
	private String id;
	public Data() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Data(String message, String story, String created_time, String id) {
		super();
		this.message = message;
		this.story = story;
		this.created_time = created_time;
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStory() {
		return story;
	}
	public void setStory(String story) {
		this.story = story;
	}
	public String getCreated_time() {
		return created_time;
	}
	public void setCreated_time(String created_time) {
		this.created_time = created_time;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
