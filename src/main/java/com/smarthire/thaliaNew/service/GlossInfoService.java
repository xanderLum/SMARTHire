package com.smarthire.thaliaNew.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.smarthire.thaliaNew.Model.GlossInfo;

public interface GlossInfoService {

	public ResponseEntity<GlossInfo> create(GlossInfo cp);
	public ResponseEntity<GlossInfo> update(GlossInfo cp);
	public ResponseEntity<GlossInfo> delete(Long id);
	public ResponseEntity<List<GlossInfo>> getList();
	public ResponseEntity<GlossInfo> read(Long id);
	public ResponseEntity<List<GlossInfo>> readAllByfk(Long fkId);
	public ResponseEntity<GlossInfo> readByfkWSId(Long fkId, String wsId);
//	public ResponseEntity<List<GlossInfo>> createList(List<GlossInfo> c);
}
