package com.smarthire.main.dao;

import com.smarthire.main.models.Employer;

public interface EmployerDao  extends CRUDDao<Employer>{
	public Employer authenticate(String username, String password);
	public Employer read(String username);
}
