package com.tourcan.eat.model;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tourcan.att.model.AttVO;

public class EatHibernateDAO implements EatDAO {

	private SessionFactory factory;

	public void setSessionFactory(SessionFactory factory) {
		this.factory = factory;
	}

	@Override
	public AttVO findById(Integer att_id) {
		AttVO attVO = null;
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			session.enableFilter("Eatable");
			attVO = (AttVO) session.get(AttVO.class, att_id);
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		}
		return attVO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AttVO> findByName(String att_name) {
		List<AttVO> attname = null;
		Session session = factory.getCurrentSession();
		String s2 = "%" + att_name + "%";
		try {
			session.beginTransaction();
			session.enableFilter("Eatable");
			Query qry = session.createQuery("FROM AttVO WHERE att_name like :att_name");
			qry.setParameter("att_name", s2);
			attname = qry.list();
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;

		}
		return attname;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AttVO> getAll() {
		List<AttVO> list = null;
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			session.enableFilter("Eatable");
			Query query = session.createQuery("from AttVO order by att_id DESC");
			list = query.list();
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AttVO> findByRegionId(Integer region_id) {
		List<AttVO> list = null;
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			session.enableFilter("Eatable");
			Query query = session.createQuery("FROM AttVO WHERE region_id=:region_id");
			query.setParameter("region_id", region_id);
			list = query.list();
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		}
		return list;
	}
}
