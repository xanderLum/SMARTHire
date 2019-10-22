package com.smarthire.xander.dao;

import java.util.List;

import com.smarthire.main.dao.CRUDDao;
import com.smarthire.xander.models.Certificate;

public interface CertificateDao extends CRUDDao<Certificate> {
	public Certificate read(String username, String name);
	public List<Certificate> getAllCertificatesByUser(String username);
}
