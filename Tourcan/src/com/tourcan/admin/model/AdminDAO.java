package com.tourcan.admin.model;

public interface AdminDAO {

	public AdminVO findById(Integer id);
	public AdminVO findByAccount(String account);

}
