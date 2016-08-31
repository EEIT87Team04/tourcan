package com.tourcan.photo.model;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import hibernate.util.HibernateUtil;

public class PhotoDAO implements PhotoDAO_interface {

	private static final String QUERY_ALL = "FROM PhotoVO ORDER BY photo_id";
	private static final String QUERY_BY_HOTEL_ID = "FROM PhotoVO WHERE hotel_id = :hotel_id ORDER BY photo_id";

	@Override
	public void insert(PhotoVO photoVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			session.beginTransaction();
			session.saveOrUpdate(photoVO);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Integer photo_id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			PhotoVO photoVO = (PhotoVO) session.get(PhotoVO.class, photo_id);
			session.delete(photoVO);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
	}

	@Override
	public void update(PhotoVO photoVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			session.beginTransaction();
			session.saveOrUpdate(photoVO);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
	}

	@Override
	public PhotoVO findById(Integer photo_id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		PhotoVO photoVO = null;
		try {
			session.beginTransaction();
			photoVO = (PhotoVO) session.get(PhotoVO.class, photo_id);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return photoVO;
	}

	@Override
	public List<PhotoVO> findByAttId(Integer att_id) {
		return null;
	}

	@Override
	public List<PhotoVO> findByHotleId(Integer hotel_id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List<PhotoVO> photoList = null;
		try {
			session.beginTransaction();
			Query query = session.createQuery(QUERY_BY_HOTEL_ID);
			query.setParameter("hotel_id", hotel_id);
			photoList = query.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		
		return photoList;
	}

	@Override
	public List<PhotoVO> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List<PhotoVO> photoList = null;
		try {
			session.beginTransaction();
			Query query = session.createQuery(QUERY_ALL);
			photoList = query.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}

		return photoList;
	}

}
