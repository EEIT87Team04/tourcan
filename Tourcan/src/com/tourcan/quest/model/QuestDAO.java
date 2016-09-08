package com.tourcan.quest.model;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import hibernate.util.HibernateUtil;

public class QuestDAO implements QuestDAO_interface {

	@Override
	public void insert(QuestVO questVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(questVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}		
	}

	@Override
	public void update(QuestVO questVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(questVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}		
	}

	@Override
	public void delete(Integer quest_id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			QuestVO questVO=new QuestVO();
			questVO.setQuest_id(quest_id);
			session.delete(questVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}		
	}

	@Override
	public QuestVO findById(Integer quest_id) {
		QuestVO questVO=null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			questVO=(QuestVO) session.get(QuestVO.class, quest_id);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return questVO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<QuestVO> findByName(String quest_topic) {
		List<QuestVO> list=null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		String likeString="%" + quest_topic + "%";
		try {
			session.beginTransaction();
			String queryByName="From QuestVO WHERE quest_topic like :quest_topic";
			Query query=session.createQuery(queryByName);
			query.setParameter("quest_topic", likeString);
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
	public List<QuestVO> getAll() {
		List<QuestVO> list=null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query=session.createQuery("From QuestVO order by quest_id");
			list=query.list();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
	}

	

}
