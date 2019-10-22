package com.smarthire.main.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.smarthire.main.models.JobPost;

public interface JobPostService {
	public ResponseEntity<JobPost> create(JobPost jp);
	public ResponseEntity<JobPost> update(JobPost jp);
	public ResponseEntity<JobPost> delete(Long id);
	public ResponseEntity<List<JobPost>> getList();
	public ResponseEntity<JobPost> read(Long id);
	public ResponseEntity<List<JobPost>> getPostByEmployer(String username);
}
