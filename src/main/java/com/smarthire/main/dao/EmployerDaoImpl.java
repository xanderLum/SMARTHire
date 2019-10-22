package com.smarthire.main.dao;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.smarthire.main.models.Employer;

@Repository
public class EmployerDaoImpl extends CRUDDaoImpl<Employer> implements EmployerDao{

	@Override
	public Employer authenticate(String username, String password) {
		Session session = getSession();
		Employer employer =(Employer) session.createCriteria(getPersistentClass())
				.add(Restrictions.eq("username",username)).add(Restrictions.eq("password", password)).uniqueResult();
		session.flush();
		session.close();
		return employer;
	}

	@Override
	public Employer read(String username) {
		Session session = getSession();
		Employer employer =(Employer) session.createCriteria(getPersistentClass())
				.add(Restrictions.eq("username",username)).uniqueResult();
		session.flush();
		session.close();
		return employer;		
	}

	@Override
	protected Class<?> getPersistentClass() {
		return Employer.class;
	}

}
