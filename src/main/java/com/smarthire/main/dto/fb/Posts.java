package com.smarthire.main.dto.fb;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Posts {
	private List<Data> data;
	private Paging paging;
	public Posts() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Posts(List<Data> data, Paging paging) {
		super();
		this.data = data;
		this.paging = paging;
	}
	public List<Data> getData() {
		return data;
	}
	public void setData(List<Data> data) {
		this.data = data;
	}
	public Paging getPaging() {
		return paging;
	}
	public void setPaging(Paging paging) {
		this.paging = paging;
	}
	
}
