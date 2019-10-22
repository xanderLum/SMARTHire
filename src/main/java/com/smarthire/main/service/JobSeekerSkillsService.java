package com.smarthire.main.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.smarthire.main.models.JobSeekerSkills;

public interface JobSeekerSkillsService {
	public ResponseEntity<JobSeekerSkills> create(JobSeekerSkills js);
	public ResponseEntity<JobSeekerSkills> update(JobSeekerSkills js);
	public ResponseEntity<JobSeekerSkills> delete(Long id);
	public ResponseEntity<List<JobSeekerSkills>> getList();
	public ResponseEntity<JobSeekerSkills> read(Long id);
	public ResponseEntity<JobSeekerSkills> checkSkillUser(String username, String skill_name);
	public ResponseEntity<List<JobSeekerSkills>> getAllSkillsByUsername(String username);
}
