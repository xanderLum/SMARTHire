package com.smarthire.xander.dao;

import java.util.List;

import com.smarthire.main.dao.CRUDDao;
import com.smarthire.xander.models.CategorySkills;

public interface CategorySkillsDao extends CRUDDao<CategorySkills>{
	public CategorySkills checkC(String skillName, String categoryName);
	public List<CategorySkills> getAllSkillsByCategory(String categoryName);
}
