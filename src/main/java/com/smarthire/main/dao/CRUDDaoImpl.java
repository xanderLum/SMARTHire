package com.smarthire.main.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class CRUDDaoImpl<T> implements CRUDDao<T> {

	@Autowired
	protected SessionFactory sessionFactory;

	protected Session session;

	protected Session getSession() {
		/*if (session == null) {
			session = sessionFactory.openSession();
			// System.out.println("\t\t\tSession opened in CRUDDAOImpl
			// getSession");
			return session;
		} else {
			return session;

		}*/
		session = sessionFactory.openSession();
		return session;
	}
	protected abstract Class<?> getPersistentClass();
	public T create(T type) {
		Session session = getSession();
		Transaction tx = null;
		try {
		//	tx = session.beginTransaction();
			session.save(type);
			session.flush();
			session.close();
			//tx.rollback();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			//session.close();
		}

		System.out.println("Crud Dao MAIN Create<T>");
		return type;
	}

	public T update(T type) {
		Session session = getSession();
		Transaction tx = null;
		try {
			//tx = session.beginTransaction();
			session.clear();
			session.update(type);
			
			//tx.rollback();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			//session.close();
		}
		session.flush();
		session.close();
		System.out.println("Crud Dao MAIN Update<T>");
		return type;
	}

	public T merge(T type) {
		Session session = getSession();
		Transaction tx = null;
		try {
		//	tx = session.beginTransaction();
			session.merge(type);
			session.flush();
			session.close();
			//tx.rollback();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			//session.close();
		}

		System.out.println("Crud Dao MAIN Merge<T>");
		return type;
	}

	@SuppressWarnings("unchecked")
	public T read(Long id) {
		Session session = getSession();
		T type = null;
		Transaction tx = null;
		try {
		//	tx = session.beginTransaction();
			type = (T) session.createCriteria(getPersistentClass()).add(Restrictions.idEq(id)).uniqueResult();
			session.flush();
			session.close();
			// session.flush();
			//tx.rollback();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			//session.close();
		}

		System.out.println("Crud Dao MAIN Read{Id}<T>");
		return type;
	}

	@SuppressWarnings("unchecked")
	public T delete(Long id) {
		Session session = getSession();
		T type = null;
		Transaction tx = null;
		try {
		//	tx = session.beginTransaction();
			type = (T) session.load(getPersistentClass(), id);
			session.delete(type);
			session.flush();
			session.close();
			// session.flush();
			//tx.rollback();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			//session.close();
		}

		System.out.println("Crud Dao MAIN DELETE{Id}<T>");
		return type;
	}

	public List<T> getList() {
		Session session = getSession();
		List<T> list = null;

		Transaction tx = null;
		try {
		//	tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(getPersistentClass())
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			list = criteria.list();
			session.flush();
			session.close();
			//tx.rollback();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			//session.close();
		}

		System.out.println("Crud Dao MAIN GETLIST{Id}<T>");
		return list;
	}

}
