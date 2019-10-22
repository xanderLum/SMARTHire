package com.smarthire.xander.dto;

public class Profile {
	private String name;
	private String shortName;
	private String title;
	private String description;
	
	
	public Profile(){}
	public Profile(String name, String shortName, String title, String description) {
		super();
		this.name = name;
		this.shortName = shortName;
		this.title = title;
		this.description = description;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Profile [" + (name != null ? "name=" + name + ", " : "")
				+ (shortName != null ? "shortName=" + shortName + ", " : "")
				+ (title != null ? "title=" + title + ", " : "")
				+ (description != null ? "description=" + description : "") + "]";
	}
	
	
	
}
