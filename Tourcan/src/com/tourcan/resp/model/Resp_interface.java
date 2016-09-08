package com.tourcan.resp.model;

import java.util.List;
import java.util.Set;

import com.tourcan.theme.model.ThemeVO;

public interface Resp_interface  {
	public void insert(RespVO respVO);
	public void update(RespVO respVO);
	public void delete(Integer resp_id);
	public RespVO findById(Integer resp_id);
	public RespVO findByThID(Integer theme_id);
	public List<RespVO> findByTopic(String resp_topic);
	public Set<ThemeVO> getThemesByID(Integer theme_id);
}
