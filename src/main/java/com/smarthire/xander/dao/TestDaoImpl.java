package com.smarthire.xander.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.smarthire.main.dao.CRUDDaoImpl;
import com.smarthire.xander.models.Test;

@Repository
public class TestDaoImpl extends CRUDDaoImpl<Test> implements TestDao{
	@Override
	public Test read(String username, String name) {
		Session session = getSession();
		//Transaction tx = null;
		Test cs = null;
		try {
			//tx = session.beginTransaction();
			cs =(Test) session.createCriteria(getPersistentClass())
					.add(Restrictions.eq("username",username)).add(Restrictions.eq("name", name)).uniqueResult();
			// session.flush();			
		//	tx.rollback();
		} catch (Exception e) {
			//if (tx != null)
			//	tx.rollback();
			e.printStackTrace();
		} finally {
			//session.close();
		}
		session.flush();
		session.close();
		return cs;
		
		/*
		System.out.println("Crud Dao MAIN Read{Id}<T>");		
		return type;
		
		Session session = getSession();
		Test cs =(Test) session.createCriteria(getPersistentClass())
				.add(Restrictions.eq("username",username)).add(Restrictions.eq("name", name)).uniqueResult();
		return cs;	
		*/
		
	}

	@Override
	public List<Test> getAllTestByUser(String username) {
		Session session = getSession();
		List<Test> list = null;
		//Transaction tx = null;
		try {
		//	tx = session.beginTransaction();
			Criteria criteria = (Criteria) session.createCriteria(getPersistentClass())
					.add(Restrictions.eq("username",username)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			list = criteria.list();	
		//	tx.rollback();
		} catch (Exception e) {
		//	if (tx != null)
			//	tx.rollback();
			e.printStackTrace();
		} finally {
			//session.close();
		}
		
		session.flush();
		session.close();

		System.out.println("Crud Dao getAllTestByUser");	
		return list;
		
		/*
		try{
			Session session = getSession();
			System.out.println("Here in getAllTestByUser DAOImpl before");
			
			Criteria criteria = (Criteria) session.createCriteria(getPersistentClass())
									.add(Restrictions.eq("username",username)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
									
									//uniqueResult();
									
									//.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			list = criteria.list();
			System.out.println("Here in getAllTestByUser DAOImpl after");
		}
		catch(Exception e){
			System.out.println("Error in  getAllTestByUser DAOImpl after: " + e);
			e.printStackTrace();
		}
		return list;
		*/
	}

	@Override
	protected Class<?> getPersistentClass() {
		// TODO Auto-generated method stub
		return Test.class;
	}

}
