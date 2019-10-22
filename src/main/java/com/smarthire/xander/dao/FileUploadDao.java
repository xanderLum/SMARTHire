package com.smarthire.xander.dao;

import com.smarthire.main.dao.CRUDDao;
import com.smarthire.xander.models.Image;

public interface FileUploadDao extends CRUDDao<Image>{
	public Image getImgeByUsername(String username);
	public void save(Image uploadFile);
}
