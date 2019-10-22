package com.smarthire.xander.dao;

import java.util.List;

import com.smarthire.main.dao.CRUDDao;
import com.smarthire.xander.models.Category;

public interface CategoryDao extends CRUDDao<Category>{
	public List<Category> getListById(Long id);
}
