package com.smarthire.main.dao;

import com.smarthire.main.models.JobSeeker;

public interface JobSeekerDao extends CRUDDao<JobSeeker>{
	public JobSeeker authenticate(String username,String password);
	public JobSeeker read(String username);
}
