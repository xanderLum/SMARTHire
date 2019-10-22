package com.smarthire.main.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.smarthire.main.models.JobSeeker;

public interface JobSeekerService {

	public ResponseEntity<JobSeeker> create(JobSeeker js);
	public ResponseEntity<JobSeeker> update(JobSeeker js);
	public ResponseEntity<JobSeeker> delete(Long id);
	public ResponseEntity<List<JobSeeker>> getList();
	public ResponseEntity<JobSeeker> authenticate(String username,String password);
	public ResponseEntity<JobSeeker> read(String username);
}
