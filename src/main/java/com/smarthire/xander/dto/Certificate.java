package com.smarthire.xander.dto;

public class Certificate {
	private int uid;
	private String name;
	private String description;
	
	public Certificate(){}
	public Certificate(int uid, String name, String description) {
		super();
		this.uid = uid;
		this.name = name;
		this.description = description;
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
	@Override
	public String toString() {
		return "Certificate [uid=" + uid + ", " + (name != null ? "name=" + name + ", " : "")
				+ (description != null ? "description=" + description : "") + "]";
	}
	
	
	
}
