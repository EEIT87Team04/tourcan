package com.tourcan.resp.model;

import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.tourcan.theme.model.ThemeVO;

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
			System.out.println("i2="+resp_id);
			sion.beginTransaction();
			RespVO respVO= new RespVO();
			respVO.setResp_id(resp_id);
			sion.delete(respVO);
			sion.getTransaction().commit();;
			}catch (RuntimeException e) {
				sion.getTransaction().rollback();
				throw e;
			}
		
	}

	@Override
	public RespVO findById(Integer resp_id) {
		RespVO respVO =null;
		Session sion = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			sion.beginTransaction();
			respVO = (RespVO) sion.get(RespVO.class,resp_id );
			sion.getTransaction().commit();;
			}catch (RuntimeException e) {
				sion.getTransaction().rollback();
				throw e;
			}
		return respVO;
	}

	@Override
	public RespVO findByThID(Integer theme_id) {
		RespVO respVO =null;
		Session sion = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			sion.beginTransaction();
			respVO = (RespVO) sion.get(RespVO.class,theme_id );
			sion.getTransaction().commit();;
			}catch (RuntimeException e) {
				sion.getTransaction().rollback();
				throw e;
			}
		return respVO;
	}
	
	@Override
	public List<RespVO> findByTopic(String resp_topic) {
		List<RespVO> rtopic = null;
		Session sion = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tc= sion.beginTransaction();
		String topic3 ="%" +resp_topic+ "%";
//		System.out.println(topic2);
		try{
//			sion.beginTransaction();
			String QueryTopic ="FROM RespVO WHERE resp_topic like :resp_topic";
			System.out.println(QueryTopic);
			Query query =sion.createQuery(QueryTopic);
			query.setParameter("resp_topic",topic3);
			System.out.println(query);
			rtopic=query.list();
			tc.commit();
//			sion.getTransaction().commit();;
			}catch (RuntimeException e) {
//				sion.getTransaction().rollback();
				tc.rollback();
				throw e;
			}
		return rtopic;
	}

	@Override
	public Set<ThemeVO> getThemesByID(Integer theme_id) {
		Set<ThemeVO> set =findByThID(theme_id).getThemeno();
		return set;
	}




}
