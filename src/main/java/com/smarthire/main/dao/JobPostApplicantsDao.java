package com.smarthire.main.dao;

import java.util.List;

import com.smarthire.main.models.JobPostApplicants;

public interface JobPostApplicantsDao extends CRUDDao<JobPostApplicants> {

	List<JobPostApplicants> getAllApplicants(Long job_post_id);
	List<JobPostApplicants> getAllJobPosts(String username);
	JobPostApplicants checkExistApplication(Long job_post_id, String username);
}
