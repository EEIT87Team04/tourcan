package com.tourcan.admin.model;

import org.hibernate.SessionFactory;

public class AdminHibernateDAO implements AdminDAO {
	
	private SessionFactory factory;
	private	AdminVO adminVO;

	public void setSessionFactory(SessionFactory factory) {
		this.factory = factory;
	}
	
	@Override
	public AdminVO findById() {
		
		adminVO.setAdmin_account("pingu");
		adminVO.setAdmin_id(0);
		adminVO.setAdmin_level(0);
		adminVO.setAdmin_name("pingu");
		adminVO.setAdmin_pwd("tourcan04");
		
		return adminVO;
	}

}
