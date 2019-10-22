package com.smarthire.main.dao;

import java.util.List;

import com.smarthire.main.models.SocialMediaAccess;

public interface SocialMediaAccessDao extends CRUDDao<SocialMediaAccess>{
	public SocialMediaAccess readUserSM(String username, String social_media);
	public List<SocialMediaAccess> getSMAListOfUser(String username);
}

