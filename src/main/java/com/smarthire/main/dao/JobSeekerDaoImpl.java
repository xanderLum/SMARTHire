package com.smarthire.main.dao;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.smarthire.main.models.JobSeeker;

@Repository
public class JobSeekerDaoImpl extends CRUDDaoImpl<JobSeeker> implements JobSeekerDao{

	public JobSeeker authenticate(String username, String password) {
		Session session = getSession();
		JobSeeker jobSeeker =(JobSeeker) session.createCriteria(getPersistentClass())
				.add(Restrictions.eq("username",username)).add(Restrictions.eq("password", password)).uniqueResult();
		session.flush();
		session.close();
		return jobSeeker;		
	}
	
	@Override
	public JobSeeker read(String username) {
		//System.out.println("==========Here jobseeker read username=============: " + username);
		Session session = getSession();
		JobSeeker jobSeeker =(JobSeeker) session.createCriteria(getPersistentClass())
				.add(Restrictions.eq("username",username)).uniqueResult();
		session.flush();
		session.close();
		return jobSeeker;		
	}

	@Override
	protected Class<?> getPersistentClass() {
		return JobSeeker.class;
	}
}
