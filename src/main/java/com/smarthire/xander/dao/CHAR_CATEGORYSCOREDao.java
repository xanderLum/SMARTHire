package com.smarthire.xander.dao;

import java.util.List;

import com.smarthire.main.dao.CRUDDao;
import com.smarthire.xander.models.CHAR_CATEGORYSCORE;

public interface CHAR_CATEGORYSCOREDao extends CRUDDao<CHAR_CATEGORYSCORE> {
	public CHAR_CATEGORYSCORE read(String username, String categoryName);
	public List<CHAR_CATEGORYSCORE> getAllCharCatScoreByUser(String username);
}
