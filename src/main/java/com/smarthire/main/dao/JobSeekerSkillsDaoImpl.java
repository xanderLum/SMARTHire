package com.smarthire.main.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.smarthire.main.models.JobSeekerSkills;
import com.smarthire.xander.models.CHAR_CATEGORYSCORE;
import com.smarthire.xander.models.CategorySkills;

@Repository
public class JobSeekerSkillsDaoImpl extends CRUDDaoImpl<JobSeekerSkills> implements JobSeekerSkillsDao{

	public JobSeekerSkills checkSkillUser(String username, String skill_name) {
		//System.out.println("1Here get all skills user dao impl username: " + username + " skillname: " + skill_name);
		Session session = getSession();
		//System.out.println("2Here get all skills user dao impl");
		JobSeekerSkills jobSeekerSkills =(JobSeekerSkills) session.createCriteria(getPersistentClass())
				.add(Restrictions.eq("username",username)).add(Restrictions.eq("skill_name", skill_name)).uniqueResult();
		//System.out.println("3Here get all skills user dao impl");
		session.flush();
		session.close();
		return jobSeekerSkills;		
	}
	
	@Override
	protected Class<?> getPersistentClass() {
		return JobSeekerSkills.class;
	}

	@Override
	public List<JobSeekerSkills> getAllSkillsByUsername(String username) {
		List<JobSeekerSkills> list = null;
		try{
			Session session = getSession();
			System.out.println("Here in getAllSkillsByUsername DAOImpl before");
			
			Criteria criteria = (Criteria) session.createCriteria(getPersistentClass())
									.add(Restrictions.eq("username",username)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
									
									//uniqueResult();
									
									//.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			list = criteria.list();
			System.out.println("Here in getAllSkillsByUsername DAOImpl after");
			session.flush();
			session.close();
		}
		catch(Exception e){
			System.out.println("Error in  getAllSkillsByUsername DAOImpl after: " + e);
			e.printStackTrace();
		}
		return list;
	}
}
