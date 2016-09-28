package com.tourcan.theme.model;

import java.sql.Timestamp;
import java.util.List;


public class ThemeService {

	private Theme_interface dao;

	public ThemeService() {
		dao = new ThemeDAO();
	}

	public void update(ThemeVO themeVO) {
		dao.update(themeVO);
	}



	public void delete(Integer theme_id) {
		dao.delete(theme_id);
	}



	public ThemeVO findById(Integer theme_id) {
		return dao.findById(theme_id);
	}



	public List<ThemeVO> findByTopic(String theme_topic) {
		return dao.findByTopic(theme_topic);
	}
	public List<ThemeVO> getAll() {
		return dao.getAll();
	}



	public List<ThemeVO> findByMemuid(String mem_uid) {
		return dao.findByMemuid(mem_uid);
	}

	public void insert(ThemeVO themeVO) {
		 dao.insert(themeVO);
		
	}



	
}
