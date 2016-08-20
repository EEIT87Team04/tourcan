package com.tourcan.att.model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import hibernate.util.HibernateUtil;

@SuppressWarnings("unused")
public class AttDAO implements AttDAO_interface {

	@Override
	public void insert(AttVO attVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(AttVO attVO) {
		Session session=HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			session.saveOrUpdate(attVO);
			session.getTransaction().commit();
		}catch(RuntimeException e){
			session.getTransaction().rollback();
			throw e;
		}
		
	}

	@Override
	public void delete(AttVO attVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AttVO findById(Integer att_id) {
		AttVO attVO=null;
		Session session=HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			attVO = (AttVO)session.get(AttVO.class,att_id);
			session.getTransaction().commit();
		}catch(RuntimeException e){
			session.getTransaction().rollback();
			throw e;
		}
		return attVO;
	}

	@Override
	public List<AttVO> findByName(String att_name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AttVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}


}
