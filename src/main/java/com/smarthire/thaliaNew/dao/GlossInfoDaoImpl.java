package com.smarthire.thaliaNew.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.smarthire.main.dao.CRUDDaoImpl;
import com.smarthire.thaliaNew.Model.CategoryPoint;
import com.smarthire.thaliaNew.Model.GlossInfo;
import com.smarthire.thaliaNew.Model.WordInfo;
import com.smarthire.thaliaNew.Model.GlossInfo;

@Repository
public class GlossInfoDaoImpl extends CRUDDaoImpl<GlossInfo> implements GlossInfoDao{

	
	@Override
	protected Class<?> getPersistentClass() {
		return GlossInfo.class;
	}

	
	
	@Override
	public List<GlossInfo> readAllByfk(Long fkId) {
		// TODO Auto-generated method stub
		
		List<GlossInfo> list = null;
		Session session = getSession();
		//Transaction tx = null;
		try {
			//tx = session.beginTransaction();
			System.out.println("Here in getAllCharCatScore DAOImpl before");
			
			Criteria criteria = (Criteria) session.createCriteria(getPersistentClass())
									.add(Restrictions.eq("wordInfoId",fkId)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
									
			list = criteria.list();
			System.out.println("Here in getAllCharCatScore DAOImpl after");
			session.flush();
			session.close();
			//tx.commit();
		} catch (Exception e) {
			//if (tx != null)
			//	tx.rollback();
			System.out.println("Error in  getAllCharCatScore DAOImpl after: " + e);
			e.printStackTrace();
		} finally {
			//session.close();
		}
		return list;
	}
	
	@Override
	public GlossInfo readByfkWSID(Long wordInfoId, String wsId) {
		Session session = getSession();
		//Transaction tx = null;
		GlossInfo gi = null;
		try {
			gi =(GlossInfo) session.createCriteria(getPersistentClass())
				.add(Restrictions.eq("wordInfoId", wordInfoId))
				.add(Restrictions.eq("wordSenseID",wsId)).uniqueResult();
			session.flush();
			session.close();
		//	tx.commit();
		} catch (Exception e) {
			
		//	if (tx != null)
		//		tx.rollback();
			e.printStackTrace();
		} finally {
			//session.close();
		}
		return gi;	
	}
}