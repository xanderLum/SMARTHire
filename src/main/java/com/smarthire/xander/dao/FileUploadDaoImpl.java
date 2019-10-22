package com.smarthire.xander.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.smarthire.main.dao.CRUDDaoImpl;
import com.smarthire.xander.models.Image;

@Repository
public class FileUploadDaoImpl extends CRUDDaoImpl<Image> implements FileUploadDao {

	@Override
	protected Class getPersistentClass() {
		// TODO Auto-generated method stub
		return Image.class;
	}

	@Override
	public Image getImgeByUsername(String username) {
		Session session = getSession();
		Transaction tx = null;
		Image cs = null; 
		try{
			System.out.println("HERE IN FILE_UPLOAD DAO getImageByUsername before");
			cs = (Image) session.createCriteria(getPersistentClass()).add(Restrictions.eq("username", username))
					.uniqueResult();
			//System.out.println("printing cs");
			//System.out.println("" + cs.toString());
		}catch(Exception e){
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		}
		/*finally {
			session.close();
		}*/
		session.flush();
		session.close();
		return cs;
	}

	@Override
	@Transactional
	public void save(Image uploadFile) {
		Session session = getSession();
		Transaction tx = null;
		try {
			//tx = session.beginTransaction();
			//sessionFactory.getCurrentSession().save(uploadFile);
			session.save(uploadFile);
			session.flush();
			//tx.rollback();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			//session.close();
		}
		//session.flush();
		session.close();
	}

	/*
	 * @Override public Image getImage(String username){
	 * 
	 * }
	 */

}
