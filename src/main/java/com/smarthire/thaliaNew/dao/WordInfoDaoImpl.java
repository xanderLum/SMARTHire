package com.smarthire.thaliaNew.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.smarthire.main.dao.CRUDDaoImpl;
import com.smarthire.thaliaNew.Model.GlossInfo;
import com.smarthire.thaliaNew.Model.WordInfo;

@Repository
public class WordInfoDaoImpl extends CRUDDaoImpl<WordInfo> implements WordInfoDao{

	
	@Override
	protected Class<?> getPersistentClass() {
		return WordInfo.class;
	}

	
	@Override
	public List<WordInfo> readAllByfk(String socialMediaDataId) {
		
		List<WordInfo> list = null;
		Session session = getSession();
		//Transaction tx = null;
		try {
			//tx = session.beginTransaction();
			System.out.println("Here in getAllWordInfo DAOImpl before");
			
			Criteria criteria;
			criteria = (Criteria) session.createCriteria(getPersistentClass())
									.add(Restrictions.eq("data_id",socialMediaDataId)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
								
			list = criteria.list();
			System.out.println("Here in getAllWordInfo DAOImpl after");
			//tx.commit();
			session.flush();
			session.close();
		} catch (Exception e) {
			//if (tx != null)
			//	tx.rollback();
			e.printStackTrace();
		} finally {
			//session.close();
		}
		return list;
	}
	
	@Override
	public List<WordInfo> readByWordTag(String word, String tag) {
		// TODO Auto-generated method stub
		
		List<WordInfo> list = null;
		try{
			Session session = getSession();
			System.out.println("Here in getAllWordInfo DAOImpl before");
			
			Criteria criteria = (Criteria) session.createCriteria(getPersistentClass())
					.add(Restrictions.eq("word",word))
					.add(Restrictions.eq("postag",tag)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
									
			list = criteria.list();
			System.out.println("Here in getAllWordInfo DAOImpl after");
			session.flush();
			session.close();
		}
		catch(Exception e){
			System.out.println("Error in  getAllWordInfo DAOImpl after: " + e);
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public WordInfo readByfkWordTag(String socialMediaDataId, String word, String tag) {
		// TODO Auto-generated method stub
		
		Session session = getSession();
		//Transaction tx = null;
		WordInfo wi = null;
		try {
			wi =(WordInfo) session.createCriteria(getPersistentClass())
				.add(Restrictions.eq("data_id", socialMediaDataId))
				.add(Restrictions.eq("word", word))
				.add(Restrictions.eq("postag",tag)).uniqueResult();
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
		return wi;	
	}
}