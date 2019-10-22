package com.smarthire.thaliaNew.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.smarthire.thaliaNew.Model.SentenceData;

public interface SentenceDataService {

	public ResponseEntity<SentenceData> create(SentenceData cp);
	public ResponseEntity<SentenceData> update(SentenceData cp);
	public ResponseEntity<SentenceData> delete(Long id);
	public ResponseEntity<List<SentenceData>> getList();
	public ResponseEntity<SentenceData> read(Long id);
	public ResponseEntity<List<SentenceData>> readAllByfk(String fkId);
}
