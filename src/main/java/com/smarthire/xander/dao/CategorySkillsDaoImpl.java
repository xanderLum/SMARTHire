package com.smarthire.xander.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.smarthire.main.dao.CRUDDaoImpl;
import com.smarthire.xander.models.CategorySkills;

@Repository
public class CategorySkillsDaoImpl extends CRUDDaoImpl<CategorySkills> implements CategorySkillsDao{
	@Override
	public CategorySkills checkC(String skillName, String categoryName) {
		Session session = getSession();
		CategorySkills cs =(CategorySkills) session.createCriteria(getPersistentClass())
				.add(Restrictions.eq("skillName",skillName)).add(Restrictions.eq("categoryName", categoryName)).uniqueResult();
		session.flush();
		session.close();
		return cs;	
	}

	@Override
	public List<CategorySkills> getAllSkillsByCategory(String categoryName) {
		List<CategorySkills> jpskills = null;
		try{
			Session session = getSession();			
			Criteria criteria = session.createCriteria(getPersistentClass())
									.add(Restrictions.eq("categoryName",categoryName)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			jpskills = criteria.list();
			session.flush();
			session.close();
		}
		catch(Exception e){
			System.out.println("Error in CategorySkills " + e);
			e.printStackTrace();
		}
		return jpskills;
	}

	@Override
	protected Class<?> getPersistentClass() {
		return CategorySkills.class;
	}

	

}
