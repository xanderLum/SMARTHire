package com.smarthire.thaliaNew.dao;

import java.util.List;

import com.smarthire.main.dao.CRUDDao;
import com.smarthire.thaliaNew.Model.GrammarError;

public interface GrammarErrorDao extends CRUDDao<GrammarError>{

	public List<GrammarError> readAllByfk(Long fkId);

}
