package com.smarthire.main.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.smarthire.main.models.SocialMediaData;

public interface SocialMediaDataService {
	public ResponseEntity<SocialMediaData> create(SocialMediaData smd);
	public ResponseEntity<SocialMediaData> update(SocialMediaData smd);
	public ResponseEntity<SocialMediaData> read(Long id);
	public ResponseEntity<SocialMediaData> delete(Long id);
	public ResponseEntity<List<SocialMediaData>> getList();
	public ResponseEntity<List<SocialMediaData>> getSMDListOfUser(String username);
	public ResponseEntity<SocialMediaData> readStringId(String data_id);
}
