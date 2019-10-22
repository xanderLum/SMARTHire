package com.smarthire.xander.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.smarthire.main.dao.CRUDDaoImpl;
import com.smarthire.xander.models.CHAR_CATEGORYSCORE;

@Repository
public class CHAR_CATEGORYSCOREDaoImpl extends CRUDDaoImpl<CHAR_CATEGORYSCORE> implements CHAR_CATEGORYSCOREDao{
	@Override
	public List<CHAR_CATEGORYSCORE> getAllCharCatScoreByUser(String username) {		
		List<CHAR_CATEGORYSCORE> list = null;
		try{
			Session session = getSession();
			System.out.println("Here in getAllCharCatScore DAOImpl before");
			
			Criteria criteria = (Criteria) session.createCriteria(getPersistentClass())
									.add(Restrictions.eq("username",username)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
									
									//uniqueResult();
									
									//.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			list = criteria.list();
			System.out.println("Here in getAllCharCatScore DAOImpl after");
			session.flush();
			session.close();
		}
		catch(Exception e){
			System.out.println("Error in  getAllCharCatScore DAOImpl after: " + e);
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	protected Class<?> getPersistentClass() {
		return CHAR_CATEGORYSCORE.class;
	}

	@Override
	public CHAR_CATEGORYSCORE read(String username, String categoryName) {
		Session session = getSession();
		CHAR_CATEGORYSCORE cs =(CHAR_CATEGORYSCORE) session.createCriteria(getPersistentClass())
				.add(Restrictions.eq("username",username)).add(Restrictions.eq("categoryName", categoryName)).uniqueResult();
		session.flush();
		session.close();
		return cs;	
	}


}
