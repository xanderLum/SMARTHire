package com.smarthire.thaliaNew.dao;

import java.util.List;

import com.smarthire.main.dao.CRUDDao;
import com.smarthire.thaliaNew.Model.BackendProcessStatus;

public interface BackendProcessStatusDao extends CRUDDao<BackendProcessStatus>{
	
	public BackendProcessStatus read(String socialMediaDataId);
}