package com.smarthire.main.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.smarthire.main.models.JobPostApplicants;
import com.smarthire.main.models.JobSeeker;

public interface JobPostApplicantsService {
	public ResponseEntity<JobPostApplicants> create(JobPostApplicants jpa);
	public ResponseEntity<JobPostApplicants> update(JobPostApplicants jpa);
	public ResponseEntity<JobPostApplicants> delete(Long id);
	public ResponseEntity<List<JobPostApplicants>> getList();
	public ResponseEntity<JobPostApplicants> read(Long id);
	public ResponseEntity<List<JobSeeker>> getAllApplicants(Long job_post_id);
	public ResponseEntity<List<JobPostApplicants>> getAllJobPosts(String username);
	public ResponseEntity<JobPostApplicants> checkExistApplication(Long job_post_id, String username);
}
