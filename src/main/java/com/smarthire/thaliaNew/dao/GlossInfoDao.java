package com.smarthire.thaliaNew.dao;

import java.util.List;

import com.smarthire.main.dao.CRUDDao;
import com.smarthire.thaliaNew.Model.GlossInfo;
import com.smarthire.thaliaNew.Model.WordInfo;

public interface GlossInfoDao extends CRUDDao<GlossInfo>{

	public List<GlossInfo> readAllByfk(Long fkId);
	public GlossInfo readByfkWSID(Long wordInfoId, String wsId);
}
