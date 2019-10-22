package com.smarthire.xander.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.smarthire.xander.models.Experience;

public interface ExperienceService {
	public ResponseEntity<Experience> create(Experience jp);
	public ResponseEntity<Experience> update(Experience jp);
	public ResponseEntity<Experience> delete(Long id);
	public ResponseEntity<List<Experience>> getList();
	public ResponseEntity<Experience> read(Long id);
	public ResponseEntity<List<Experience>> getAllExperienceByUser(String username);
	public ResponseEntity<Experience> read(String username, String employer);
}
