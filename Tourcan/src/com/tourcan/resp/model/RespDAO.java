package com.tourcan.resp.model;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import hibernate.util.HibernateUtil;

public class RespDAO implements Resp_interface {

	@Override
	public void insert(RespVO respVO) {
		Session sion = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
		sion.beginTransaction();
		sion.saveOrUpdate(respVO);
		sion.getTransaction().commit();;
		}catch (RuntimeException e) {
			sion.getTransaction().rollback();
			throw e;
		}
	}

	@Override
	public void update(RespVO respVO) {
		Session sion = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			sion.beginTransaction();
			sion.saveOrUpdate(respVO);
			sion.getTransaction().commit();;
			}catch (RuntimeException e) {
				sion.getTransaction().rollback();
				throw e;
			}
		
	}

	@Override
	public void delete(Integer resp_id) {
		Session sion = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			sion.beginTransaction();
			sion.delete(resp_id);
			sion.getTransaction().commit();;
			}catch (RuntimeException e) {
				sion.getTransaction().rollback();
				throw e;
			}
		
	}

	@Override
	public RespVO findById(Integer resp_id) {
		RespVO themeVO =null;
		Session sion = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			sion.beginTransaction();
			themeVO = (RespVO) sion.get(RespVO.class,resp_id );
			sion.getTransaction().commit();;
			}catch (RuntimeException e) {
				sion.getTransaction().rollback();
				throw e;
			}
		return themeVO;
	}

	@Override
	public List<RespVO> findByTopic(String resp_topic) {
		List<RespVO> topic = null;
		Session sion = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tc= sion.beginTransaction();
		String topic2 ="%" +resp_topic+ "%";
		try{
//			sion.beginTransaction();
			String QueryTopic ="FROM AttVO WHERE resp_topic like :resp_topic";
			Query query =sion.createQuery(QueryTopic);
			query.setParameter("resp_topic",topic2);
			topic=query.list();
			tc.commit();
//			sion.getTransaction().commit();;
			}catch (RuntimeException e) {
//				sion.getTransaction().rollback();
				tc.rollback();
				throw e;
			}
		return topic;
	}



}
