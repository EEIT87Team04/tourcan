package com.tourcan.theme.model;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import hibernate.util.HibernateUtil;

@SuppressWarnings("unused")
public class ThemeDAO implements Theme_interface {

	@Override
	public void insert(RespVO themeVO) {
		Session sion = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
		sion.beginTransaction();
		sion.saveOrUpdate(themeVO);
		sion.getTransaction().commit();;
		}catch (RuntimeException e) {
			sion.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void update(RespVO themeVO) {
		Session sion = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			sion.beginTransaction();
			sion.saveOrUpdate(themeVO);
			sion.getTransaction().commit();;
			}catch (RuntimeException e) {
				sion.getTransaction().rollback();
				throw e;
			}
		
	}

	@Override
	public void delete(Integer theme_id) {
		Session sion = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			sion.beginTransaction();
			RespVO themeVO= new RespVO();
			themeVO.setTheme_id(theme_id);
			sion.delete(themeVO);
			sion.getTransaction().commit();;
			}catch (RuntimeException e) {
				sion.getTransaction().rollback();
				
				throw e;
			}
		
	}

	@Override
	public RespVO findById(Integer theme_id) {
		RespVO themeVO =null;
		Session sion = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			sion.beginTransaction();
			themeVO = (RespVO) sion.get(RespVO.class,theme_id );
			sion.getTransaction().commit();;
			}catch (RuntimeException e) {
				sion.getTransaction().rollback();
				throw e;
			}
		return themeVO;
	}

	@Override
	public List<RespVO> findByTopic(String theme_topic) {
		List<RespVO> topic = null;
		Session sion = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = sion.beginTransaction();
		String topic2 ="%" +theme_topic+ "%";
		try{
//			sion.beginTransaction();
			String QueryTopic ="FROM ThemeVO WHERE theme_topic like :theme_topic";
			Query query =sion.createQuery(QueryTopic);
			query.setParameter("theme_topic",topic2);
			topic=query.list();
//			sion.getTransaction().commit();
			tx.commit();
			}catch (RuntimeException e) {
//				sion.getTransaction().rollback();
				tx.rollback();
				throw e;
			}
		return topic;
	}
	@Override
	public List<RespVO> getAll() {
		List<RespVO> list =null;
		Session session=HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
    	try {
//			session.beginTransaction();
			Query query =session.createQuery("from ThemeVO order by theme_id");
			list=query.list();
			tx.commit();
//			session.beginTransaction().commit();
		} catch (RuntimeException e) {
			tx.rollback();
//			session.beginTransaction().rollback();
			throw e;
			
		}
		return list;
	}


}
