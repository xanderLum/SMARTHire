package com.smarthire.xander.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.smarthire.xander.models.Test;

public interface TestService {
	public ResponseEntity<Test> create(Test jp);
	public ResponseEntity<Test> update(Test jp);
	public ResponseEntity<Test> delete(Long id);
	public ResponseEntity<List<Test>> getList();
	public ResponseEntity<Test> read(Long id);
	public ResponseEntity<Test> read(String username, String name);
	public ResponseEntity<List<Test>> getAllTestByUser(String username);
}
