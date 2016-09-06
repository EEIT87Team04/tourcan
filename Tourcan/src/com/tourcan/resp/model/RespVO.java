package com.tourcan.resp.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class RespVO implements Serializable  {
	
	private Integer  resp_id;
	private String resp_topic;
	private String resp_article;
	private Timestamp resp_time;
	private Integer mem_id;
	private Integer theme_id;
	
	
	public Integer getResp_id() {
		return resp_id;
	}
	public void setResp_id(Integer resp_id) {
		this.resp_id = resp_id;
	}
	public String getResp_topic() {
		return resp_topic;
	}
	public void setResp_topic(String resp_topic) {
		this.resp_topic = resp_topic;
	}
	public String getResp_article() {
		return resp_article;
	}
	public void setResp_article(String resp_article) {
		this.resp_article = resp_article;
	}
	public Timestamp getResp_time() {
		return resp_time;
	}
	public void setResp_time(Timestamp resp_time) {
		this.resp_time = resp_time;
	}
	public Integer getMem_id() {
		return mem_id;
	}
	public void setMem_id(Integer mem_id) {
		this.mem_id = mem_id;
	}
	public Integer getTheme_id() {
		return theme_id;
	}
	public void setTheme_id(Integer theme_id) {
		this.theme_id = theme_id;
	}

	
}
