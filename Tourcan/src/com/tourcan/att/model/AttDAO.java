package com.tourcan.att.model;

import java.util.List;

import org.hibernate.Session;

import hibernate.util.HibernateUtil;

public class AttDAO implements AttDAO_interface {

	@Override
	public void insert(AttVO attVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(AttVO attVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(AttVO attVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			AttVO attVO1 = (AttVO) session.get(AttVO.class, attVO.getAtt_id());
			session.delete(attVO1);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
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
