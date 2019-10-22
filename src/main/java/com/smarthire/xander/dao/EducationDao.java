package com.smarthire.xander.dao;

import java.util.List;

import com.smarthire.main.dao.CRUDDao;
import com.smarthire.xander.models.Education;

public interface EducationDao extends CRUDDao<Education>{
	public Education read(String username, String schoolName);
	public List<Education> getAllEducationByUser(String username);
}
