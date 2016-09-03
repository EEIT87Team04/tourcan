package com.tourcan.theme.model;

import java.sql.Timestamp;
import java.util.List;


public class ThemeService {

	private Theme_interface dao;

	public ThemeService() {
		dao = new ThemeDAO();
	}
	
	
	
	public ThemeVO insert(String theme_article, String theme_topic, Integer themecatalog,Timestamp theme_time,Integer mem_id)
	{
		ThemeVO thVO=new ThemeVO();
		thVO.setTheme_article(theme_article);
		thVO.setTheme_topic(theme_topic);
		thVO.setTheme_catalog(themecatalog);
		thVO.setTheme_time(theme_time);
		thVO.setMem_id(mem_id);
		
		dao.insert(thVO);
		return thVO;
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
	public List<ThemeVO> getAll() {
		return dao.getAll();
	}



	
}
