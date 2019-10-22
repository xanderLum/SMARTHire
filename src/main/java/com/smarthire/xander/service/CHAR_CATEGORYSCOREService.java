package com.smarthire.xander.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.smarthire.xander.models.CHAR_CATEGORYSCORE;

public interface CHAR_CATEGORYSCOREService {
	public ResponseEntity<CHAR_CATEGORYSCORE> create(CHAR_CATEGORYSCORE cs);
	public ResponseEntity<CHAR_CATEGORYSCORE> update(CHAR_CATEGORYSCORE cs);
	public ResponseEntity<CHAR_CATEGORYSCORE> delete(Long id);
	public ResponseEntity<List<CHAR_CATEGORYSCORE>> getList();
	public ResponseEntity<CHAR_CATEGORYSCORE> read(Long id);
	public ResponseEntity<CHAR_CATEGORYSCORE> read(String username, String categoryName);
	public ResponseEntity<List<CHAR_CATEGORYSCORE>> getAllCharCatScoreByUser(String username);
}
