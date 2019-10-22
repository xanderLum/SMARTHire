package com.smarthire.main.dao;

import java.util.List;

import com.smarthire.main.models.SocialMediaData;

public interface SocialMediaDataDao extends CRUDDao<SocialMediaData>{
	public List<SocialMediaData> getSMDListOfUser(String username);
	public SocialMediaData readStringId(String strId);
}
