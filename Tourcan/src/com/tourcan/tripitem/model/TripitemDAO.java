package com.tourcan.tripitem.model;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import hibernate.util.HibernateUtil;


public class TripitemDAO implements TripitemDAO_interface {

	@Override
	public void insert(TripitemVO tripitemVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(tripitemVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void update(TripitemVO tripitemVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(tripitemVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void delete(Integer tripitem_id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			TripitemVO tripitemVO=new TripitemVO();
			tripitemVO.setTripitem_id(tripitem_id);
			session.delete(tripitemVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public TripitemVO findById(Integer tripitem_id) {
		TripitemVO tripitemVO=null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			tripitemVO=(TripitemVO) session.get(TripitemVO.class, tripitem_id);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return tripitemVO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TripitemVO> getAll() {
			List<TripitemVO> list=null;
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			try {
				session.beginTransaction();
				Query query=session.createQuery("From TripitemVO order by tripitem_id");
				list=query.list();
				session.getTransaction().commit();
			} catch (RuntimeException ex) {
				session.getTransaction().rollback();
				throw ex;
			}
			return list;
		}

	@Override
	public List<TripitemVO> findByTripID(Integer trip_id) {
		List<TripitemVO> thlist =null;
		Integer tripID =trip_id;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
		session.beginTransaction();
		Query query=session.createQuery("From TripitemVO where trip_id =:trip_id");
		query.setParameter("trip_id", tripID);
		thlist=query.list();
		session.getTransaction().commit();
		}catch (Exception e) {
		session.getTransaction().rollback();	
			throw e;
		}
		return thlist;
	}
}
