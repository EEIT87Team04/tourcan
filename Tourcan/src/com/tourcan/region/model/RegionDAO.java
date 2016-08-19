package com.tourcan.region.model;

import java.util.List;

import org.hibernate.classic.Session;

import hibernate.util.HibernateUtil;

public class RegionDAO implements RegionDAO_interface {

	@Override
	public void insert(RegionVO regionVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(regionVO);
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
			session.getTransaction().rollback();
			throw e;
		}
	}

	@Override
	public void update(RegionVO regionVO) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(RegionVO regionVO) {
		// TODO Auto-generated method stub

	}

	@Override
	public RegionVO findById(Integer region_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RegionVO> findByArea(Integer region_area) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RegionVO> findByName(String region_name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RegionVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
