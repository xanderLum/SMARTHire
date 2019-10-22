package com.smarthire.thaliaNew.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.smarthire.thaliaNew.Model.GrammarError;


public interface GrammarErrorService {

	public ResponseEntity<GrammarError> create(GrammarError er);
	public ResponseEntity<GrammarError> update(GrammarError er);
	public ResponseEntity<GrammarError> delete(Long id);
	public ResponseEntity<List<GrammarError>> getList();
	public ResponseEntity<GrammarError> read(Long id);
	public ResponseEntity<List<GrammarError>> readAllByfk(Long fkId);
}
