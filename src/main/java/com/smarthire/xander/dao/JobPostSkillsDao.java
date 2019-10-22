package com.smarthire.xander.dao;

import java.util.List;

import com.smarthire.main.dao.CRUDDao;
import com.smarthire.main.models.JobSeekerSkills;
import com.smarthire.xander.models.JobPostSkills;

public interface JobPostSkillsDao extends CRUDDao<JobPostSkills>{
	public List<JobPostSkills> getListById(Long id);
	public List<JobPostSkills> deleteAllByJobPostId(Long job_post_id);
}
