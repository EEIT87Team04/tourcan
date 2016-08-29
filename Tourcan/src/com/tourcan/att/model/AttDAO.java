package com.tourcan.att.model;

import java.util.List;

import org.hibernate.Session;

import hibernate.util.HibernateUtil;

public class AttDAO implements AttDAO_interface {

	@Override
	public void insert(AttVO attVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(attVO);
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
			session.getTransaction().rollback();
			throw e;
		}
	}

	@Override
	public void update(AttVO attVO) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(AttVO attVO) {
		// TODO Auto-generated method stub

	}

	@Override
	public AttVO findById(Integer att_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AttVO> findByName(String att_name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AttVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
