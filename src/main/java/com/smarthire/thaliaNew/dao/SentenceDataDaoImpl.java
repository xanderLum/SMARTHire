package com.smarthire.thaliaNew.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.smarthire.main.dao.CRUDDaoImpl;
import com.smarthire.thaliaNew.Model.SentenceData;

@Repository
public class SentenceDataDaoImpl extends CRUDDaoImpl<SentenceData> implements SentenceDataDao{

	@Override
	protected Class<?> getPersistentClass() {
		return SentenceData.class;
	}

	@Override
	public List<SentenceData> readAllByfk(String fkId) {
		// TODO Auto-generated method stub
		
		List<SentenceData> list = null;
		Session session = getSession();
		//Transaction tx = null;
		try {
			//tx = session.beginTransaction();
			System.out.println("Here in getAllCharCatScore DAOImpl before");
			
			Criteria criteria = (Criteria) session.createCriteria(getPersistentClass())
									.add(Restrictions.eq("data_id",fkId)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
									
			list = criteria.list();
			System.out.println("Here in getAllCharCatScore DAOImpl after");
			session.flush();
			session.close();
			//tx.commit();
		} catch (Exception e) {
			//if (tx != null)
			//	tx.rollback();
			e.printStackTrace();
		} finally {
			//session.close();
		}
		return list;
	}

}
