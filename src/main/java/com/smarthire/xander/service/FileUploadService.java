package com.smarthire.xander.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.smarthire.xander.models.Image;

public interface FileUploadService {
	public ResponseEntity<Image> create(Image jp);
	public ResponseEntity<Image> update(Image jp);
	public ResponseEntity<Image> delete(Long id);
	public ResponseEntity<List<Image>> getList();
	public ResponseEntity<Image> read(Long id);
	public ResponseEntity<Image> getImgeByUsername(String username);
	public ResponseEntity<Image> save(Image img);
}
