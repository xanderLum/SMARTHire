package com.smarthire.xander.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.smarthire.main.dao.CRUDDaoImpl;
import com.smarthire.xander.models.Education;

@Repository
public class EducationDaoImpl extends CRUDDaoImpl<Education> implements EducationDao{

	@Override
	public Education read(String username, String schoolName) {
		Session session = getSession();
		Education cs =(Education) session.createCriteria(getPersistentClass())
				.add(Restrictions.eq("username",username)).add(Restrictions.eq("schoolName", schoolName)).uniqueResult();
		session.flush();
		session.close();
		
		return cs;	

	}

	@Override
	public List<Education> getAllEducationByUser(String username) {
			List<Education> list = null;
			try{
				Session session = getSession();
				System.out.println("Here in getAllEducationByUser DAOImpl before");
				
				Criteria criteria = (Criteria) session.createCriteria(getPersistentClass())
										.add(Restrictions.eq("username",username)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
										
										//uniqueResult();
										
										//.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
				list = criteria.list();
				System.out.println("Here in getAllEducationByUser DAOImpl after");
				session.flush();
				session.close();
			}
			catch(Exception e){
				System.out.println("Error in  getAllEducationByUser DAOImpl after: " + e);
				e.printStackTrace();
			}
			return list;
		
	}

	@Override
	protected Class<?> getPersistentClass() {
		// TODO Auto-generated method stub
		return Education.class;
	}

}
