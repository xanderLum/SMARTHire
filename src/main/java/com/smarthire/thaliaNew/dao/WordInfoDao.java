package com.smarthire.thaliaNew.dao;

import java.util.List;

import com.smarthire.main.dao.CRUDDao;
import com.smarthire.thaliaNew.Model.WordInfo;

public interface WordInfoDao extends CRUDDao<WordInfo>{

	public List<WordInfo> readAllByfk(String socialMediaDataId);
	public List<WordInfo> readByWordTag(String word, String tag);
	public WordInfo readByfkWordTag(String socialMediaDataId, String word, String tag);
}
