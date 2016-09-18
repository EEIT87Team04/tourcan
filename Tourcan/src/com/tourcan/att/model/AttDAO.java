package com.tourcan.att.model;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import hibernate.util.HibernateUtil;

@SuppressWarnings("unused")
public class AttDAO implements AttDAO_interface {

	private static int STMT_SHIFT = 0; // 1 for JDBC, 0 for Hibernate
	private static final String Get_Img_Name = "SELECT att_id FROM AttVO where att_name=?";

	private SessionFactory factory;

	public void setSessionFactory(SessionFactory factory) {
		this.factory = factory;
	}

	@Override
	public Integer insert(AttVO attVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(attVO);
			session.getTransaction().commit();
			return attVO.getAtt_id();
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
			session.getTransaction().rollback();
			throw e;
		}
	}

	@Override
	public AttVO findById(Integer att_id) {
		AttVO attVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			attVO = (AttVO) session.get(AttVO.class, att_id);
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		}
		return attVO;
	}

	@Override
	public void delete(Integer attId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			AttVO attVO = new AttVO();
			attVO.setAtt_id(attId);
			session.delete(attVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}

	}

	@Override
	public void update(AttVO attVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(attVO);
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AttVO> findByName(String att_name) {
		// List<AttVO> name=null;
		List<AttVO> attname = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		// System.out.println("s2="+att_name);
		String s2 = "%" + att_name + "%";
		try {
			// session.beginTransaction();
			String queryByName = "FROM AttVO WHERE att_name like :att_name";
			Query qry = session.createQuery(queryByName);
			qry.setParameter("att_name", s2);
			attname = qry.list();
			tx.commit();
			// session.beginTransaction().commit();
		} catch (RuntimeException e) {
			tx.rollback();
			// session.beginTransaction().rollback();
			throw e;

		}
		return attname;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AttVO> getAll() {
		List<AttVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			// session.beginTransaction();
			Query query = session.createQuery("from AttVO order by att_id DESC");
			list = query.list();
			tx.commit();
			// session.beginTransaction().commit();
		} catch (RuntimeException e) {
			tx.rollback();
			// session.beginTransaction().rollback();
			throw e;

		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AttVO> findByRegionId(Integer region_id) {
		List<AttVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("FROM AttVO WHERE region_id=:region_id");
			query.setParameter("region_id", region_id);
			list = query.list();
			session.beginTransaction().commit();
		} catch (RuntimeException e) {
			session.beginTransaction().rollback();
			throw e;
		}
		return list;
	}
}
