package com.smarthire.xander.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.smarthire.xander.models.JobAlert;

public interface JobAlertService {
	public ResponseEntity<JobAlert> create(JobAlert jp);
	public ResponseEntity<JobAlert> update(JobAlert jp);
	public ResponseEntity<JobAlert> delete(Long id);
	public ResponseEntity<List<JobAlert>> getList();
	public ResponseEntity<JobAlert> read(Long id);
	public ResponseEntity<JobAlert> readByJPId(Long job_post_id);
	public ResponseEntity<List<JobAlert>> getAllAlertsByUsername(String username);
	public ResponseEntity<JobAlert> checkIfExists(String jsUsername, Long job_post_id);
}
