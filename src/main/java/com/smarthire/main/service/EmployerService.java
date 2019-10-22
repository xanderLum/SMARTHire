package com.smarthire.main.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.smarthire.main.models.Employer;

public interface EmployerService {
	public ResponseEntity<Employer> create(Employer emp);
	public ResponseEntity<Employer> update(Employer emp);
	public ResponseEntity<Employer> delete(Long id);
	public ResponseEntity<List<Employer>> getList();
	public ResponseEntity<Employer> authenticate(String username,String password);
	public ResponseEntity<Employer> read(String username);
}
