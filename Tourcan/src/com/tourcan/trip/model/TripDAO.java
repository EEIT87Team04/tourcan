package com.tourcan.trip.model;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import hibernate.util.HibernateUtil;

public class TripDAO implements TripDAO_interface {

	@Override
	public void insert(TripVO tripVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(tripVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void update(TripVO tripVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(tripVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void delete(Integer trip_id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			TripVO tripVO=new TripVO();
			tripVO.setTrip_id(trip_id);
			session.delete(tripVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public TripVO findById(Integer trip_id) {
		TripVO tripVO=null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			tripVO=(TripVO) session.get(TripVO.class, trip_id);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return tripVO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TripVO> findByName(String trip_name) {
		List<TripVO> list=null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		String likeString="%" + trip_name + "%";
		try {
			session.beginTransaction();
			String queryByName="From TripVO WHERE trip_name like :trip_name";
			Query query=session.createQuery(queryByName);
			query.setParameter("trip_name", likeString);
			list=query.list();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TripVO> getAll() {
		List<TripVO> list=null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query=session.createQuery("From TripVO order by trip_id");
			list=query.list();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
	}
}
