package com.smarthire.xander.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.smarthire.main.dao.CRUDDaoImpl;
import com.smarthire.xander.models.Education;
import com.smarthire.xander.models.JobAlert;
import com.smarthire.xander.models.JobPostSkills;

@Repository
public class JobAlertDaoImpl extends CRUDDaoImpl<JobAlert> implements JobAlertDao{
	@Override
	public JobAlert readByJPId(Long jobpost_id) {
		Session session = getSession();
		JobAlert cs =(JobAlert) session.createCriteria(getPersistentClass())
				.add(Restrictions.eq("jobpost_id",jobpost_id)).uniqueResult();
		session.flush();
		session.close();
		
		return cs;	
	}

	@Override
	protected Class<?> getPersistentClass() {
		return JobAlert.class;
	}

	@Override
	public List<JobAlert> getAllAlertsByUsername(String username) {
		List<JobAlert> jpAlerts = null;
		System.out.println("username = "+username);
		try{
			Session session = getSession();			
			Criteria criteria = session.createCriteria(getPersistentClass())
									.add(Restrictions.eq("jsUsername",username)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			jpAlerts = criteria.list();
			System.out.println("jpAlerts inside try == "+jpAlerts.size());
			session.flush();
			session.close();
		}
		catch(Exception e){
			System.out.println("Error in getJobPostSkills " + e);
			e.printStackTrace();
		}
		System.out.println("alerts size = "+jpAlerts.size());
		return jpAlerts;
	}

	@Override
	public JobAlert checkIfExists(String jsUsername, Long job_post_id) {
		Session session = getSession();
		JobAlert cs =(JobAlert) session.createCriteria(getPersistentClass())
				.add(Restrictions.eq("jsUsername",jsUsername)).add(Restrictions.eq("job_post_id", job_post_id)).uniqueResult();
		session.flush();
		session.close();
		
		return cs;	
	}

}
