package com.smarthire.xander.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.smarthire.main.dao.CRUDDaoImpl;
import com.smarthire.xander.models.Experience;

@Repository
public class ExperienceDaoImpl extends CRUDDaoImpl<Experience> implements ExperienceDao {

	@Override
	public List<Experience> getAllExperienceByUser(String username) {
		List<Experience> list = null;
		try {
			Session session = getSession();
			System.out.println("Here in getAllExperienceByUser DAOImpl before");
			Criteria criteria = (Criteria) session.createCriteria(getPersistentClass())
					.add(Restrictions.eq("username", username)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

			// uniqueResult();

			// .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			list = criteria.list();
			System.out.println("Here in getAllExperienceByUser DAOImpl after");
			session.flush();
			session.close();
		} catch (Exception e) {
			System.out.println("Error in  getAllExperienceByUser DAOImpl after: " + e);
			e.printStackTrace();
		}
		return list;

	}

	@Override
	protected Class<?> getPersistentClass() {
		// TODO Auto-generated method stub
		return Experience.class;
	}

	@Override
	public Experience read(String username, String employer) {
		Session session = getSession();
		Experience cs =(Experience) session.createCriteria(getPersistentClass())
				.add(Restrictions.eq("username",username)).add(Restrictions.eq("qualifications", employer)).uniqueResult();
		session.flush();
		session.close();
		return cs;	
	}

}
