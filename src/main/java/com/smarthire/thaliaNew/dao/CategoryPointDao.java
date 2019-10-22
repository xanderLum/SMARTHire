package com.smarthire.thaliaNew.dao;

import java.util.List;

import com.smarthire.main.dao.CRUDDao;
import com.smarthire.thaliaNew.Model.BackendProcessStatus;
import com.smarthire.thaliaNew.Model.CategoryPoint;

public interface CategoryPointDao extends CRUDDao<CategoryPoint>{
	
	public List<CategoryPoint> readAllByfk(Long fkId);
}
