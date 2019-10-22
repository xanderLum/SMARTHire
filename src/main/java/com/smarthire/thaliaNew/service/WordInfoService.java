package com.smarthire.thaliaNew.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.smarthire.thaliaNew.Model.WordInfo;


public interface WordInfoService {

	public ResponseEntity<WordInfo> create(WordInfo cp);
	public ResponseEntity<WordInfo> update(WordInfo cp);
	public ResponseEntity<WordInfo> delete(Long id);
	public ResponseEntity<List<WordInfo>> getList();
	public ResponseEntity<WordInfo> read(Long id);
	public ResponseEntity<List<WordInfo>> readAllByfk(String fkId);
	public ResponseEntity<List<WordInfo>> readAllByWordTag(String word, String tag);
	public ResponseEntity<WordInfo> readByfkWordTag(String socialMediaDataId, String word, String tag);
}
