package com.smarthire.xander.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.smarthire.main.dao.CRUDDaoImpl;
import com.smarthire.xander.models.JobPostSkills;

@Repository
public class JobPostSkillsDaoImpl extends CRUDDaoImpl<JobPostSkills> implements JobPostSkillsDao{

	@Override
	public List<JobPostSkills> getListById(Long job_post_id) {
		List<JobPostSkills> jpskills = null;
		try{
			Session session = getSession();			
			Criteria criteria = session.createCriteria(getPersistentClass())
									.add(Restrictions.eq("job_post_id",job_post_id)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			jpskills = criteria.list();
			session.flush();
			session.close();
		}
		catch(Exception e){
			System.out.println("Error in getJobPostSkills " + e);
			e.printStackTrace();
		}
		return jpskills;
	}

	@Override
	protected Class<?> getPersistentClass() {
		return JobPostSkills.class;
	}

	@Override
	public List<JobPostSkills> deleteAllByJobPostId(Long job_post_id) {
		// TODO Auto-generated method stub
		List<JobPostSkills> jpskills = null;
		try{
			
			Session session = getSession();			
			Criteria criteria = session.createCriteria(getPersistentClass())
									.add(Restrictions.eq("job_post_id",job_post_id)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			jpskills = criteria.list();
			
			System.out.println("Ready to delete..jobPostSkills");
			String hql = "delete from Student where job_post_id= :job_post_id";
			session.createQuery(hql).setLong("job_post_id", job_post_id).executeUpdate();
			System.out.println("Deleted!");
			session.flush();
			session.close();
		}
		catch(Exception e){
			System.out.println("Error in deleteJobPostSkills " + e);
			e.printStackTrace();
		}
		return jpskills;
	}

	
}
