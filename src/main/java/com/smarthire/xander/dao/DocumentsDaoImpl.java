package com.smarthire.xander.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.smarthire.main.dao.CRUDDaoImpl;
import com.smarthire.xander.models.CHAR_CATEGORYSCORE;
import com.smarthire.xander.models.Documents;

@Repository
public class DocumentsDaoImpl extends CRUDDaoImpl<Documents> implements DocumentsDao {
	@Override
	public List<Documents> getAllDocumentsByUsername(String username) {
		List<Documents> list = null;
		try{
			Session session = getSession();
			System.out.println("Here in getAllCharCatScore DAOImpl before");
			
			Criteria criteria = (Criteria) session.createCriteria(getPersistentClass())
									.add(Restrictions.eq("username",username)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
									
			list = criteria.list();
			System.out.println("Here in getAllDocumentsByUsername DAOImpl after");
			session.flush();
			session.close();
		}
		catch(Exception e){
			System.out.println("Error in  getAllDocumentsByUsername DAOImpl after: " + e);
			e.printStackTrace();
		}
		return list;
	}

	@Override
	protected Class<?> getPersistentClass() {
		return Documents.class;
	}

	@Override
	public Documents read(String username, String filename) {
		Session session = getSession();
		Documents doc =(Documents) session.createCriteria(getPersistentClass())
				.add(Restrictions.eq("username",username)).add(Restrictions.eq("filename", filename)).uniqueResult();
		session.flush();
		session.close();
		return doc;	
	}
	
}
