package com.tourcan.region.model;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RegionHibernateDAO implements RegionDAO {

	private SessionFactory factory;

	public void setSessionFactory(SessionFactory factory) {
		this.factory = factory;
	}

	@Override
	public void insert(RegionVO regionVO) {
		Session session = factory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.save(regionVO);
		session.flush();
		tx.commit();
	}

	@Override
	public void update(RegionVO regionVO) {
		Session session = factory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.update(regionVO);
		session.flush();
		tx.commit();
	}

	@Override
	public void delete(RegionVO regionVO) {
		Session session = factory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.delete(regionVO);
		session.flush();
		tx.commit();
	}

	@Override
	public RegionVO findById(Integer region_id) {
		Session session = factory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		RegionVO vo = (RegionVO) session.get(RegionVO.class, region_id);
		session.flush();
		tx.commit();
		return vo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RegionVO> findByArea(Integer region_area) {
		Session session = factory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from RegionVO where region_area = :region_area");
		query.setParameter("region_area", region_area);
		List<RegionVO> vo = query.list();
		session.flush();
		tx.commit();
		return vo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RegionVO> findByName(String region_name) {
		Session session = factory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from RegionVO where region_name like :region_name");
		query.setParameter("region_name", "%" + region_name + "%");
		List<RegionVO> vo = query.list();
		session.flush();
		tx.commit();
		return vo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RegionVO> getAll() {
		Session session = factory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from RegionVO");
		List<RegionVO> vo = query.list();
		session.flush();
		tx.commit();
		return vo;
	}

//	public static void main(String[] args) {
//		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
//		// RegionVO vo = context.getBean(RegionVO.class);
//		RegionDAO dao = context.getBean(RegionHibernateDAO.class);
//
//		for (RegionVO att : dao.getAll())
//			System.out.println(att.getRegion_id() + ": " + att.getRegion_name() + " @ " + att.getRegion_area());
//		System.out.println("------------");
//		for (RegionVO att : dao.findByName("北"))
//			System.out.println(att.getRegion_id() + ": " + att.getRegion_name() + " @ " + att.getRegion_area());
//		System.out.println("------------");
//		for (RegionVO att : dao.findByArea(3))
//			System.out.println(att.getRegion_id() + ": " + att.getRegion_name() + " @ " + att.getRegion_area());
//		System.out.println("------------");
//		RegionVO att = dao.findById(0);
//		System.out.println(att.getRegion_id() + ": " + att.getRegion_name() + " @ " + att.getRegion_area());
//
//		((ConfigurableApplicationContext) context).close();
//	}

}
