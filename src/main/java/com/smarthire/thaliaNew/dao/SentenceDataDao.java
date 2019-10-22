package com.smarthire.thaliaNew.dao;

import java.util.List;

import com.smarthire.main.dao.CRUDDao;
import com.smarthire.thaliaNew.Model.SentenceData;

public interface SentenceDataDao extends CRUDDao<SentenceData>{
	
	public List<SentenceData> readAllByfk(String fkId);
}
