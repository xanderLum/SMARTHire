package com.smarthire.xander.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.smarthire.xander.models.Documents;
public interface DocumentsService {
	public ResponseEntity<Documents> create(Documents cs);
	public ResponseEntity<Documents> update(Documents cs);
	public ResponseEntity<Documents> delete(Long id);
	public ResponseEntity<List<Documents>> getList();
	public ResponseEntity<Documents> read(Long id);
	public ResponseEntity<Documents> read(String username, String filename);
	public ResponseEntity<List<Documents>> getAllDocumentsByUsername(String username);
}
