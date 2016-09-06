package com.tourcan.resp.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import com.google.gson.annotations.Expose;

public class RespVO implements Serializable  {
	@Expose
	private Integer  resp_id;
	@Expose
	private String resp_topic;
	@Expose
	private String resp_article;
	@Expose
	private Timestamp resp_time;
	@Expose
	private String mem_uid;
	@Expose
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

	public String getMem_uid() {
		return mem_uid;
	}
	public void setMem_uid(String mem_uid) {
		this.mem_uid = mem_uid;
	}
	public Integer getTheme_id() {
		return theme_id;
	}
	public void setTheme_id(Integer theme_id) {
		this.theme_id = theme_id;
	}

	
}
