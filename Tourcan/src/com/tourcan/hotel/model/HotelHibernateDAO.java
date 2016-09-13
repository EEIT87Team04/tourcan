package com.tourcan.hotel.model;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//@SuppressWarnings("unused")
public class HotelHibernateDAO implements HotelDAO {

	private SessionFactory factory;

	public void setSessionFactory(SessionFactory factory) {
		this.factory = factory;
	}

	@Override
	public void insert(HotelVO hotelVO) {
		Session session = factory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.save(hotelVO);
		session.flush();
		tx.commit();
	}

	@Override
	public void update(HotelVO hotelVO) {
		Session session = factory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.update(hotelVO);
		session.flush();
		tx.commit();
	}

	@Override
	public void delete(HotelVO hotelVO) {
		Session session = factory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.delete(hotelVO);
		session.flush();
		tx.commit();
	}

	@Override
	public HotelVO findById(Integer hotel_id) {
		Session session = factory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		HotelVO vo = (HotelVO) session.get(HotelVO.class, hotel_id);
		session.flush();
		tx.commit();
		return vo;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<HotelVO> findByRegionId(Integer region_id) {
		Session session = factory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from HotelVO where region_id = :region_id");
		query.setParameter("region_id", region_id);
		List<HotelVO> vo = query.list();
		session.flush();
		tx.commit();
		return vo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HotelVO> findByName(String hotel_name) {
		Session session = factory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from HotelVO where hotel_name like :hotel_name");
		query.setParameter("hotel_name", "%" + hotel_name + "%");
		List<HotelVO> vo = query.list();
		session.flush();
		tx.commit();
		return vo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HotelVO> getAll() {
		Session session = factory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from HotelVO");
		List<HotelVO> vo = query.list();
		session.flush();
		tx.commit();
		return vo;
	}

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		HotelVO vo = context.getBean(HotelVO.class);
		HotelDAO dao = context.getBean(HotelHibernateDAO.class);

		// vo.setHotel_name("Hello, World.");
		// vo.setHotel_class(6);
		// vo.setHotel_addr("Not available.");
		// vo.setHotel_lat(0.0);
		// vo.setHotel_lng(0.0);
		// vo.setHotel_phone("66666666");
		// vo.setHotel_price(123.45);
		// vo.setHotel_url("http://example.com");
		// vo.setRegion_id(0);
		// dao.insert(vo);

		vo = dao.findById(19);
		// vo.setHotel_lat(60.0);
		// vo.setHotel_lng(75.0);
		// vo.setHotel_phone("23332333");
		// dao.update(vo);
		if (vo != null)
			System.out.println(vo.getHotel_id() + vo.getHotel_name() + vo.getHotel_url());

		// List<HotelVO> vos = dao.findByName("Wo");
		// List<HotelVO> vos = dao.getAll();
		// for (HotelVO vo0 : vos)
		// System.out.println(vo0.getHotel_id() + vo0.getHotel_name());

		// vo.setHotel_id(18);
		// dao.delete(vo);

		((ConfigurableApplicationContext) context).close();
	}

}
