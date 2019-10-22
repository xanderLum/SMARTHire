package com.smarthire.xander.dao;

import java.util.List;

import com.smarthire.main.dao.CRUDDao;
import com.smarthire.xander.models.Experience;

public interface ExperienceDao extends CRUDDao<Experience>{
	public Experience read(String username, String employer);
	public List<Experience> getAllExperienceByUser(String username);
}
