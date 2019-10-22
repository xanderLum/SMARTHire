package com.smarthire.main.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.smarthire.main.models.SocialMediaAccess;

@Repository
public class SocialMediaAccessDaoImpl extends CRUDDaoImpl<SocialMediaAccess> implements SocialMediaAccessDao{


	public SocialMediaAccess readUserSM(String username, String social_media) {
		Session session = getSession();
		//System.out.println("Here in readUserSM DAOImpl before");
		SocialMediaAccess sma = null;
		sma =(SocialMediaAccess) session.createCriteria(getPersistentClass())
				.add(Restrictions.eq("username",username)).add(Restrictions.eq("social_media", social_media)).uniqueResult();
		//System.out.println("Here in readUserSM DAOImpl after");
		session.flush();
		session.close();
		
		return sma;		
	}
	
	public List<SocialMediaAccess> getSMAListOfUser(String username) {
		List<SocialMediaAccess> lsma = null;
		try{
			Session session = getSession();
			//System.out.println("Here in getSMAListOfUser DAOImpl before");
			
			Criteria criteria = session.createCriteria(getPersistentClass())
									.add(Restrictions.eq("username",username)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			lsma = criteria.list();
			//System.out.println("Here in getSMAListOfUser getSMAListOfUser after");
			session.flush();
			session.close();
		}
		catch(Exception e){
			System.out.println("Error in getSMAListOfUser getSMAListOfUser after: " + e);
			e.printStackTrace();
		}
		return lsma;
	}
	
	@Override
	protected Class<?> getPersistentClass() {
		// TODO Auto-generated method stub
		return SocialMediaAccess.class;
	}

	
	
}
