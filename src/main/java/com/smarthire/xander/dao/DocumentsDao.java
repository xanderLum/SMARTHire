package com.smarthire.xander.dao;

import java.util.List;

import com.smarthire.main.dao.CRUDDao;
import com.smarthire.xander.models.Documents;

public interface DocumentsDao extends CRUDDao<Documents> {
	public Documents read(String username, String filename);
	public List<Documents> getAllDocumentsByUsername(String username);
}
