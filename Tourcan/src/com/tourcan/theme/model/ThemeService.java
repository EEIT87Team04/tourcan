package com.tourcan.theme.model;

import java.util.List;


public class ThemeService {

	private Theme_interface dao;

	public ThemeService() {
		dao = new ThemeDAO();
	}
	
	
	
	public void insert(ThemeVO themeVO) {
		dao.insert(themeVO);
	}



	public void update(ThemeVO themeVO) {
		dao.update(themeVO);
	}



	public void delete(ThemeVO themeVO) {
		dao.delete(themeVO);
	}



	public ThemeVO findById(Integer theme_id) {
		return dao.findById(theme_id);
	}



	public List<ThemeVO> findByTopic(String theme_topic) {
		return dao.findByTopic(theme_topic);
	}



	
}
