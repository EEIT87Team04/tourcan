package com.tourcan.mem.model;

import java.sql.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MemHibernateDAO implements MemDAO {

	private SessionFactory factory;

	public void setSessionFactory(SessionFactory factory) {
		this.factory = factory;
	}

	@Override
	public void insert(MemVO memVO) {
		Session session = factory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.save(memVO);
		session.flush();
		tx.commit();
	}

	@Override
	public void update(MemVO memVO) {
		Session session = factory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.update(memVO);
		session.flush();
		tx.commit();
	}

	@Override
	public void delete(MemVO memVO) {
		Session session = factory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.delete(memVO);
		session.flush();
		tx.commit();
	}

	@Override
	public MemVO findById(Integer mem_id) {
		Session session = factory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		MemVO vo = (MemVO) session.get(MemVO.class, mem_id);
		session.flush();
		tx.commit();
		return vo;
	}

	@Override
	public MemVO findByUid(String mem_uid) {
		Session session = factory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		MemVO vo = (MemVO) session.createCriteria(MemVO.class).add(Restrictions.eq("mem_uid", mem_uid)).uniqueResult();
		// MemVO vo = (MemVO) session.get(MemVO.class, uid);
		session.flush();
		tx.commit();
		return vo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MemVO> findByName(String mem_name) {
		Session session = factory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from MemVO where mem_fname||mem_lname like :mem_name");
		query.setParameter("mem_name", "%" + mem_name + "%");
		List<MemVO> vo = query.list();
		session.flush();
		tx.commit();
		return vo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MemVO> getAll() {
		Session session = factory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from MemVO");
		List<MemVO> vo = query.list();
		session.flush();
		tx.commit();
		return vo;
	}

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		Integer id = 0;
		MemVO vo = null;
		List<MemVO> vos = null;
		MemDAO dao = context.getBean(MemHibernateDAO.class);
		
		System.out.println("------ INSERT ------");

		vo = context.getBean(MemVO.class);
		vo.setMem_account("tomcatuser");
		vo.setMem_bdate(new Date(new java.util.Date().getTime() - 12 * 365 * 86400 * 1000));
		vo.setMem_regtime(new Date(new java.util.Date().getTime()));
		vo.setMem_email("tomcat@example.com");
		vo.setMem_fname("T0mcat");
		vo.setMem_lname("Apache");
		vo.setMem_mobile("+12185654096");
		vo.setMem_nick("neko");
		vo.setMem_pwd("66666666");
		vo.setMem_sex(2);
		vo.setRegion_id(1);
		vo.setMem_uid("99999999999999999997");
		dao.insert(vo);
		vo = null;
		
		System.out.println("------ FINDBYUSERID ------");

		vo = dao.findByUid("99999999999999999997");
		if (vo != null) {
			id = vo.getMem_id();
			System.out.println(vo.getMem_id() + vo.getMem_lname() + vo.getMem_fname() + vo.getMem_email());
		} else
			System.out.println("User not exist.");
		
		System.out.println("------ UPDATE ------");

		vo.setMem_email("tomcat@example.org");
		vo.setMem_fname("Tomcat");
		dao.update(vo);
		vo = null;
		
		System.out.println("------ GETALL ------");

		vos = dao.getAll();
		if (vos.size() > 0)
			for (MemVO vo0 : vos)
				System.out.println(vo0.getMem_id() + vo0.getMem_lname() + vo0.getMem_fname() + vo0.getMem_email());
		else
			System.out.println("Ain't Nobody Here but Us Chickens.");
		vos = null;

		System.out.println("------ FINDBYID ------");
		
		vo = dao.findById(id);
		if (vo != null) {
			System.out.println(vo.getMem_id() + vo.getMem_lname() + vo.getMem_fname() + vo.getMem_email());
//			System.out.println("------ DELETE ------");
//			dao.delete(vo);
		} else
			System.out.println("User not exist.");
		
		System.out.println("------ FINDBYNAME ------");
		
		vos = dao.findByName("pac");
		if (vos.size() > 0)
			for (MemVO vo0 : vos)
				System.out.println(vo0.getMem_id() + vo0.getMem_lname() + vo0.getMem_fname() + vo0.getMem_email());
		else
			System.out.println("Ain't Nobody Here but Us Chickens.");
		vos = null;

		System.out.println("------ CLOSE ------");
		
		((ConfigurableApplicationContext) context).close();
	}

}
