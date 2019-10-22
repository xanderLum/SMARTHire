package com.smarthire.main.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.smarthire.main.models.SocialMediaData;

@Repository
public class SocialMediaDataDaoImpl extends CRUDDaoImpl<SocialMediaData> implements SocialMediaDataDao {

	@Override
	public List<SocialMediaData> getSMDListOfUser(String username) {
		List<SocialMediaData> lsmd = null;
		try {
			Session session = getSession();
			// System.out.println("Here in getSMDListOfUser DAOImpl before");
			Criteria criteria = session.createCriteria(getPersistentClass()).add(Restrictions.eq("username", username))
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			lsmd = criteria.list();
			// System.out.println("Here in readUserSM getSMDListOfUser after");
			session.flush();
			session.close();
		} catch (Exception e) {
			System.out.println("Error in readUserSM getSMDListOfUser after: " + e);
			e.printStackTrace();
		}
		return lsmd;
	}

	@Override
	protected Class<?> getPersistentClass() {
		// TODO Auto-generated method stub
		return SocialMediaData.class;
	}

	@Override
	public SocialMediaData readStringId(String data_id) {
		Session session = getSession();
		SocialMediaData smd = (SocialMediaData) session.createCriteria(getPersistentClass())
				.add(Restrictions.eq("data_id", data_id)).uniqueResult();
		session.flush();
		session.close();
		return smd;
	}

}
