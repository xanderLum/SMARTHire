package com.smarthire.thaliaNew.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.smarthire.thaliaNew.Model.BackendProcessStatus;
import com.smarthire.xander.models.CHAR_CATEGORYSCORE;

public interface BackendProcessStatusService {
	public ResponseEntity<BackendProcessStatus> create(BackendProcessStatus bps);
	public ResponseEntity<BackendProcessStatus> update(BackendProcessStatus bps);
	public ResponseEntity<BackendProcessStatus> delete(Long id);
	public ResponseEntity<List<BackendProcessStatus>> getList();
	public ResponseEntity<BackendProcessStatus> read(Long id);
	public ResponseEntity<BackendProcessStatus> read(String socialMediaDataId);
}