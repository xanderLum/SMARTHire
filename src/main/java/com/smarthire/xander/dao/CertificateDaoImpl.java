package com.smarthire.xander.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.smarthire.main.dao.CRUDDaoImpl;
import com.smarthire.xander.models.Certificate;

@Repository
public class CertificateDaoImpl extends CRUDDaoImpl<Certificate> implements CertificateDao{

	@Override
	public Certificate read(String username, String name) {
		Session session = getSession();
		//Transaction tx = null;
		Certificate cs = null;
		try {
		//	tx = session.beginTransaction();
			cs =(Certificate) session.createCriteria(getPersistentClass())
					.add(Restrictions.eq("username",username)).add(Restrictions.eq("name", name)).uniqueResult();
			
		//	tx.rollback();
		} catch (Exception e) {
		//	if (tx != null)
		//		tx.rollback();
			e.printStackTrace();
		} finally {
		//	session.close();
		}
		session.flush();
		session.close();
		return cs;
		
		/*
		Session session = getSession();
		Certificate cs =(Certificate) session.createCriteria(getPersistentClass())
				.add(Restrictions.eq("username",username)).add(Restrictions.eq("name", name)).uniqueResult();
		return cs;
		*/	
	}

	@Override
	public List<Certificate> getAllCertificatesByUser(String username) {
		Session session = getSession();
		List<Certificate> list = null;

		Transaction tx = null;
		try {
		//	tx = session.beginTransaction();
			Criteria criteria = (Criteria) session.createCriteria(getPersistentClass())
					.add(Restrictions.eq("username",username)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
					
			list = criteria.list();
			//tx.rollback();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			//session.close();
		}
		session.flush();
		session.close();
		
		return list;
		/*
		try{
			Session session = getSession();
			System.out.println("Here in Certificate DAOImpl before");
			
			Criteria criteria = (Criteria) session.createCriteria(getPersistentClass())
									.add(Restrictions.eq("username",username)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
									
									//uniqueResult();
									
									//.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			list = criteria.list();
			System.out.println("Here in getAllCertificatesByUser DAOImpl after");
		}
		catch(Exception e){
			System.out.println("Error in  getAllCertificatesByUser DAOImpl after: " + e);
			e.printStackTrace();
		}
		return list;
		*/
	}

	@Override
	protected Class<?> getPersistentClass() {
		// TODO Auto-generated method stub
		return Certificate.class;
	}

}
