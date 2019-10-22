package com.smarthire.xander.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.smarthire.xander.models.Education;

public interface EducationService {
	public ResponseEntity<Education> create(Education jp);
	public ResponseEntity<Education> update(Education jp);
	public ResponseEntity<Education> delete(Long id);
	public ResponseEntity<List<Education>> getList();
	public ResponseEntity<Education> read(Long id);
	public ResponseEntity<Education> read(String username, String schoolName);
	public ResponseEntity<List<Education>> getAllEducationByUser (String username);
}
