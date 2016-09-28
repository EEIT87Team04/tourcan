package com.tourcan.admin.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

public class AdminFakeDAO implements AdminDAO {

	@SuppressWarnings("unused")
	private SessionFactory factory;
	private static final List<AdminVO> users = new ArrayList<AdminVO>();

	public void setSessionFactory(SessionFactory factory) {
		this.factory = factory;
	}

	static {
		AdminVO user;

		user = new AdminVO();
		user.setAdmin_account("root");
		user.setAdmin_id(1);
		user.setAdmin_level(0);
		user.setAdmin_name("Root");
		user.setAdmin_pwd("toor");
		users.add(user);

		user = new AdminVO();
		user.setAdmin_account("pingu8007");
		user.setAdmin_id(2);
		user.setAdmin_level(0);
		user.setAdmin_name("劉慶和");
		user.setAdmin_pwd("tourcan04");
		users.add(user);
		
		user = new AdminVO();
		user.setAdmin_account("opencan");
		user.setAdmin_id(3);
		user.setAdmin_level(0);
		user.setAdmin_name("管理員");
		user.setAdmin_pwd("open");
		users.add(user);

	}

	@Override
	public AdminVO findById(Integer id) {
		for (AdminVO user : users) {
			if (user.getAdmin_id().equals(id)) {
				return user;
			}
		}
		return null;
	}

	@Override
	public AdminVO findByAccount(String account) {
		for (AdminVO user : users) {
			if (user.getAdmin_account().equals(account)) {
				return user;
			}
		}
		return null;
	}
}
