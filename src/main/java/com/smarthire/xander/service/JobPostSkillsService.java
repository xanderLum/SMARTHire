package com.smarthire.xander.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.smarthire.xander.models.JobPostSkills;

public interface JobPostSkillsService {
	public ResponseEntity<JobPostSkills> create(JobPostSkills jp);
	public ResponseEntity<JobPostSkills> update(JobPostSkills jp);
	public ResponseEntity<JobPostSkills> delete(Long id);
	public ResponseEntity<List<JobPostSkills>> getList();
	public ResponseEntity<JobPostSkills> read(Long id);
	public ResponseEntity<List<JobPostSkills>> getListById(Long id);
	public ResponseEntity<List<JobPostSkills>> deleteAllByJobPostId(Long id);
}
