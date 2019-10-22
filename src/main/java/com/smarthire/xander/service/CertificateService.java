package com.smarthire.xander.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.smarthire.xander.models.Certificate;

public interface CertificateService {
	public ResponseEntity<Certificate> create(Certificate jp);
	public ResponseEntity<Certificate> update(Certificate jp);
	public ResponseEntity<Certificate> delete(Long id);
	public ResponseEntity<List<Certificate>> getList();
	public ResponseEntity<Certificate> read(Long id);
	public ResponseEntity<Certificate> read(String username, String name);
	public ResponseEntity<List<Certificate>> getAllCertificatesByUser(String username);
}
