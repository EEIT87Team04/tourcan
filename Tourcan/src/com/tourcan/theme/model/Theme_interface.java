package com.tourcan.theme.model;

import java.util.List;

import com.tourcan.att.model.AttVO;

public interface Theme_interface  {
	public void insert(RespVO themeVO);
	public void update(RespVO themeVO);
	public void delete(Integer theme_id);
	public RespVO findById(Integer theme_id);
	public List<RespVO> findByTopic(String theme_topic);
	public List<RespVO> getAll();
}
