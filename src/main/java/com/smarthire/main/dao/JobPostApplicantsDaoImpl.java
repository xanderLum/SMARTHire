package com.smarthire.main.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.smarthire.main.models.JobPostApplicants;
import com.smarthire.xander.models.CHAR_CATEGORYSCORE;

@Repository
public class JobPostApplicantsDaoImpl extends CRUDDaoImpl<JobPostApplicants> implements JobPostApplicantsDao {

	@Override
	public List<JobPostApplicants> getAllJobPosts(String username) {
		List<JobPostApplicants> ljpa = null;
		try{
			Session session = getSession();
			//System.out.println("Here in getAllJobPosts DAOImpl before");
			Criteria criteria = session.createCriteria(getPersistentClass())
					.add(Restrictions.eq("username",username)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			ljpa = criteria.list();	
			session.flush();
			session.close();
		}
		catch(Exception e){
			System.out.println("Here in getAllJobPosts. Job Post APplicants Dao Impl catch:" + e);
		}
		return ljpa;
	}
	
	@Override
	public List<JobPostApplicants> getAllApplicants(Long job_post_id) {
		List<JobPostApplicants> ljpa = null;
		try{
			Session session = getSession();
		//	System.out.println("Here in getAllApplicants DAOImpl before");
			Criteria criteria = session.createCriteria(getPersistentClass())
					.add(Restrictions.eq("job_post_id",job_post_id)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			ljpa = criteria.list();	
			session.flush();
			session.close();
		}
		catch(Exception e){
			System.out.println("Here in getAllAPplicants. Job Post APplicants Dao Impl catch:" + e);
		}
		return ljpa;
	}
	@Override
	protected Class<?> getPersistentClass() {
		return JobPostApplicants.class;
	}

	@Override
	public JobPostApplicants checkExistApplication(Long job_post_id, String username) {
		Session session = getSession();
		JobPostApplicants cs =(JobPostApplicants) session.createCriteria(getPersistentClass())
				.add(Restrictions.eq("job_post_id",job_post_id)).add(Restrictions.eq("username", username)).uniqueResult();
		session.flush();
		session.close();
		return cs;	

	}
	
}
