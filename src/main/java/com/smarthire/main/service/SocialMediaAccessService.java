package com.smarthire.main.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.smarthire.main.models.SocialMediaAccess;

public interface SocialMediaAccessService {
	public ResponseEntity<SocialMediaAccess> create(SocialMediaAccess sma);
	public ResponseEntity<SocialMediaAccess> update(SocialMediaAccess sma);
	public ResponseEntity<SocialMediaAccess> read(Long id);
	public ResponseEntity<SocialMediaAccess> readUserSM(String username, String social_media);
	public ResponseEntity<SocialMediaAccess> delete(Long id);
	public ResponseEntity<List<SocialMediaAccess>> getList();
	public ResponseEntity<List<SocialMediaAccess>> getSMAListOfUser(String username);
}
