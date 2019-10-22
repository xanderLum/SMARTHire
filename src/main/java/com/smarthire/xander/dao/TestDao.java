package com.smarthire.xander.dao;

import java.util.List;

import com.smarthire.main.dao.CRUDDao;
import com.smarthire.xander.models.Test;

public interface TestDao extends CRUDDao<Test>{
	public Test read(String username, String name);
	public List<Test> getAllTestByUser(String username);
}
