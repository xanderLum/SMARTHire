package com.smarthire.main.dto.fb;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FbUserData {
	private Posts posts;
	public FbUserData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FbUserData(Posts posts) {
		super();
		this.posts = posts;
	}
	public Posts getPosts() {
		return posts;
	}
	public void setPosts(Posts posts) {
		this.posts = posts;
	}
}
