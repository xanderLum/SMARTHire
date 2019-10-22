package com.smarthire.main.dto.fb;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Paging {
	private String previous;
	private String next;
	public Paging() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Paging(String previous, String next) {
		super();
		this.previous = previous;
		this.next = next;
	}
	public String getPrevious() {
		return previous;
	}
	public void setPrevious(String previous) {
		this.previous = previous;
	}
	public String getNext() {
		return next;
	}
	public void setNext(String next) {
		this.next = next;
	}
	
}
