package com.smarthire.main.dao;

import java.util.List;

import com.smarthire.main.models.JobPost;

public interface JobPostDao extends CRUDDao<JobPost>{
	public List<JobPost> getPostByEmployer(String username);
}
