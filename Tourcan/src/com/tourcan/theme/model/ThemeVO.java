package com.tourcan.theme.model;

import java.io.Serializable;
import java.sql.Date;

public class ThemeVO implements Serializable  {
	
	private Integer  theme_id;
	private String theme_article;
	private String theme_topic;
	private String theme_catalog;
	private Date theme_time;
	private Integer mem_id;
	
	
	
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
	public String getTheme_catalog() {
		return theme_catalog;
	}
	public void setTheme_catalog(String theme_catalog) {
		this.theme_catalog = theme_catalog;
	}
	public Date getTheme_time() {
		return theme_time;
	}
	public void setTheme_time(Date theme_time) {
		this.theme_time = theme_time;
	}
	public Integer getMem_id() {
		return mem_id;
	}
	public void setMem_id(Integer mem_id) {
		this.mem_id = mem_id;
	}
	
	
}
