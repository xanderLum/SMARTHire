package com.smarthire.thaliaNew.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.smarthire.main.dao.CRUDDaoImpl;
import com.smarthire.thaliaNew.Model.BackendProcessStatus;
import com.smarthire.thaliaNew.Model.CategoryPoint;
import com.smarthire.thaliaNew.Model.CategoryPoint;

@Repository
public class CategoryPointDaoImpl extends CRUDDaoImpl<CategoryPoint> implements CategoryPointDao{

	
	@Override
	protected Class<?> getPersistentClass() {
		return CategoryPoint.class;
	}
	
	@Override
	public List<CategoryPoint> readAllByfk(Long fkId) {
		// TODO Auto-generated method stub
		
		List<CategoryPoint> list = null;
		Session session = getSession();
		//Transaction tx = null;
		try{
			//tx = session.beginTransaction();
			System.out.println("Here in getAllCharCatScore DAOImpl before");
			
			Criteria criteria = (Criteria) session.createCriteria(getPersistentClass())
									.add(Restrictions.eq("partPointerId",fkId)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
									
			list = criteria.list();
			
			System.out.println("Here in getAllCharCatScore DAOImpl after");
			session.flush();
			session.close();
			//tx.commit();
		}
		catch(Exception e){
			System.out.println("Error in  getAllCharCatScore DAOImpl after: " + e);
			e.printStackTrace();
			//if (tx != null)
			//	tx.rollback();
			e.printStackTrace();
		} finally {
			//session.close();
		}
		return list;
	}

	
}
