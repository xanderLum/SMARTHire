package com.smarthire.xander.dao;

import java.util.List;

import com.smarthire.main.dao.CRUDDao;
import com.smarthire.xander.models.JobAlert;

public interface JobAlertDao extends CRUDDao<JobAlert>{
	public JobAlert readByJPId(Long jobpost_id);
	public List<JobAlert> getAllAlertsByUsername(String username);
	public JobAlert checkIfExists(String jsUsername, Long job_post_id);
}
