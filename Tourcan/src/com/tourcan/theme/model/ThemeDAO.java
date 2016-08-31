package com.tourcan.theme.model;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import hibernate.util.HibernateUtil;

public class ThemeDAO implements Theme_interface {

	@Override
	public void insert(ThemeVO themeVO) {
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
	public void update(ThemeVO themeVO) {
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
	public void delete(ThemeVO themeVO) {
		Session sion = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			sion.beginTransaction();
			sion.delete(themeVO);
			sion.getTransaction().commit();;
			}catch (RuntimeException e) {
				sion.getTransaction().rollback();
				throw e;
			}
		
	}

	@Override
	public ThemeVO findById(Integer theme_id) {
		ThemeVO themeVO =null;
		Session sion = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			sion.beginTransaction();
			themeVO = (ThemeVO) sion.get(ThemeVO.class,theme_id );
			sion.getTransaction().commit();;
			}catch (RuntimeException e) {
				sion.getTransaction().rollback();
				throw e;
			}
		return themeVO;
	}

	@Override
	public List<ThemeVO> findByTopic(String theme_topic) {
		List<ThemeVO> topic = null;
		Session sion = HibernateUtil.getSessionFactory().getCurrentSession();
		String topic2 ="%" +theme_topic+ "%";
		try{
			sion.beginTransaction();
			String QueryTopic ="FROM AttVO WHERE theme_topic like :theme_topic";
			Query query =sion.createQuery(QueryTopic);
			query.setParameter("theme_topic",topic2);
			topic=query.list();
			sion.getTransaction().commit();;
			}catch (RuntimeException e) {
				sion.getTransaction().rollback();
				throw e;
			}
		return topic;
	}



}
