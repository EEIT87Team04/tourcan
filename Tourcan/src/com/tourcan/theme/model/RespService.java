package com.tourcan.theme.model;

import java.sql.Timestamp;
import java.util.List;


public class RespService {

	private Theme_interface dao;

	public RespService() {
		dao = new ThemeDAO();
	}
	
	
	
	public RespVO insert(String theme_topic, Integer themecatalog, String theme_article,Timestamp theme_time,Integer mem_id)
	{
		RespVO thVO=new RespVO();
		thVO.setTheme_article(theme_article);
		thVO.setTheme_topic(theme_topic);
		thVO.setTheme_catalog(themecatalog);
		thVO.setTheme_time(theme_time);
		thVO.setMem_id(mem_id);
		
		dao.insert(thVO);
		return thVO;
	}






	public void update(RespVO themeVO) {
		dao.update(themeVO);
	}



	public void delete(Integer theme_id) {
		dao.delete(theme_id);
	}



	public RespVO findById(Integer theme_id) {
		return dao.findById(theme_id);
	}



	public List<RespVO> findByTopic(String theme_topic) {
		return dao.findByTopic(theme_topic);
	}
	public List<RespVO> getAll() {
		return dao.getAll();
	}



	
}
