package com.smarthire.main.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.smarthire.main.models.JobPost;

@Repository
public class JobPostDaoImpl extends CRUDDaoImpl<JobPost> implements JobPostDao{

	@Override
	public List<JobPost> getPostByEmployer(String username) {
		List<JobPost> ljp = null;
		try{
			Session session = getSession();
			//System.out.println("Here in getPostByEmployer DAOImpl before");
			
			Criteria criteria = session.createCriteria(getPersistentClass())
									.add(Restrictions.eq("username",username)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			ljp = criteria.list();
			//System.out.println("Here in getPostByEmployer getPostByEmployer after");
			session.flush();
			session.close();
		}
		catch(Exception e){
			System.out.println("Error in getPostByEmployer getPostByEmployer after: " + e);
			e.printStackTrace();
		}
		return ljp;
	}

	@Override
	protected Class<?> getPersistentClass() {
		return JobPost.class;
	}
}
