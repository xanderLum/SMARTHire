package com.smarthire.main.dao;

import java.util.List;

import com.smarthire.main.models.JobSeekerSkills;

public interface JobSeekerSkillsDao extends CRUDDao<JobSeekerSkills>{
	public JobSeekerSkills checkSkillUser(String username, String skill_name);
	public List<JobSeekerSkills> getAllSkillsByUsername(String username);
}
