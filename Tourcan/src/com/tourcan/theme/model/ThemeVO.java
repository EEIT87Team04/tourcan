package com.tourcan.theme.model;

import java.io.Serializable;
import java.sql.Timestamp;

import com.google.gson.annotations.Expose;
import com.tourcan.resp.model.RespVO;

public class ThemeVO implements Serializable  {
	
	private static final long serialVersionUID = 1L;
	@Expose
	private Integer  theme_id;
	@Expose
	private String theme_article;
	@Expose
	private String theme_topic;
//	@Expose
	private Integer theme_catalog;
	@Expose
	private Timestamp theme_time;
	@Expose
	private String mem_uid;
	
	private RespVO respVO;
	
	public ThemeVO(){};
	
	public RespVO getRespVO() {
		return respVO;
	}
	public void setRespVO(RespVO respVO) {
		this.respVO = respVO;
	}
	public Timestamp getTheme_time() {
		return theme_time;
	}
	public void setTheme_time(Timestamp theme_time) {
		this.theme_time = theme_time;
	}
	public Integer getTheme_id() {
		return theme_id;
	}
	public void setTheme_id(Integer theme_id) {
		this.theme_id = theme_id;
	}
	public String getTheme_article() {
		return theme_article;
	}
	public void setTheme_article(String theme_article) {
		this.theme_article = theme_article;
	}
	public String getTheme_topic() {
		return theme_topic;
	}
	public void setTheme_topic(String theme_topic) {
		this.theme_topic = theme_topic;
	}
	public Integer getTheme_catalog() {
		return theme_catalog;
	}
	public void setTheme_catalog(Integer themecatalog) {
		this.theme_catalog = themecatalog;
	}
	public String getMem_uid() {
		return mem_uid;
	}
	public void setMem_uid(String mem_uid) {
		this.mem_uid = mem_uid;
	}

	

	
}
