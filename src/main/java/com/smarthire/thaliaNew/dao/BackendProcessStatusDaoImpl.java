package com.smarthire.thaliaNew.dao;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.smarthire.main.dao.CRUDDaoImpl;
import com.smarthire.thaliaNew.Model.BackendProcessStatus;

@Repository
public class BackendProcessStatusDaoImpl extends CRUDDaoImpl<BackendProcessStatus> implements BackendProcessStatusDao{

	
	@Override
	protected Class<?> getPersistentClass() {
		return BackendProcessStatus.class;
	}

	@Override
	public BackendProcessStatus read(String socialMediaDataId) {
		Session session = getSession();
		BackendProcessStatus bps = null;
		//Transaction tx = null;
		try {
		//	tx = session.beginTransaction();
			bps =(BackendProcessStatus) session.createCriteria(getPersistentClass())
				.add(Restrictions.eq("data_id", socialMediaDataId)).uniqueResult();
			session.flush();
			session.close();
			//	tx.commit();
			//tx.rollback();
		} catch (Exception e) {
			//if (tx != null)
			//	tx.rollback();
			e.printStackTrace();
		} finally {
			//session.close();
		}
		return bps;	
	}
	
	
}
