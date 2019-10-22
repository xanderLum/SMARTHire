package com.smarthire.thaliaNew.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.smarthire.main.dao.CRUDDaoImpl;
import com.smarthire.thaliaNew.Model.CategoryPoint;
import com.smarthire.thaliaNew.Model.GrammarError;
import com.smarthire.thaliaNew.Model.GrammarError;

@Repository
public class GrammarErrorDaoImpl extends CRUDDaoImpl<GrammarError> implements GrammarErrorDao{

	
	@Override
	protected Class<?> getPersistentClass() {
		return GrammarError.class;
	}


	@Override
	public List<GrammarError> readAllByfk(Long fkId) {
		// TODO Auto-generated method stub
		
		List<GrammarError> list = null;
		Session session = getSession();
		//Transaction tx = null;
		try {
			//tx = session.beginTransaction();
			System.out.println("Here in getAllCharCatScore DAOImpl before");
			
			Criteria criteria = (Criteria) session.createCriteria(getPersistentClass())
									.add(Restrictions.eq("sentenceDataId",fkId)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
									
			list = criteria.list();
			System.out.println("Here in getAllCharCatScore DAOImpl after");
			session.flush();
			session.close();
			//tx.commit();
		} catch (Exception e) {
			//if (tx != null)
				//tx.rollback();
			System.out.println("Error in Creating Grammar Error..");
			e.printStackTrace();
		} finally {
			//session.close();
		}
		return list;
	}
}