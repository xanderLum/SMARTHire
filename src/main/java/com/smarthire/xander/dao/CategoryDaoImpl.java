package com.smarthire.xander.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.smarthire.main.dao.CRUDDaoImpl;
import com.smarthire.xander.models.Category;

@Repository
public class CategoryDaoImpl extends CRUDDaoImpl<Category> implements CategoryDao {

	@Override
	public List<Category> getListById(Long id) {
		Session session = getSession();
		List<Category> category = null;

		//Transaction tx = null;
		try {
		//	tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(getPersistentClass()).add(Restrictions.eq("id", id))
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			category = criteria.list();
		//	tx.rollback();
		} catch (Exception e) {
		///	if (tx != null)
		//		tx.rollback();
			e.printStackTrace();
		} finally {
			//session.close();
		}
		
/*
		try {
			Session session = getSession();
			Criteria criteria = session.createCriteria(getPersistentClass()).add(Restrictions.eq("id", id))
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			category = criteria.list();
		} catch (Exception e) {
			System.out.println("Error in getJobPostSkills " + e);
			e.printStackTrace();
		}
		*/
		session.flush();
		session.close();
		return category;
	}

	@Override
	protected Class<?> getPersistentClass() {
		// TODO Auto-generated method stub
		return Category.class;
	}

}
