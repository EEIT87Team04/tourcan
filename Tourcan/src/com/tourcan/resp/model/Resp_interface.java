package com.tourcan.resp.model;

import java.util.List;

import com.tourcan.att.model.AttVO;

public interface Resp_interface  {
	public void insert(RespVO respVO);
	public void update(RespVO respVO);
	public void delete(RespVO respVO);
	public RespVO findById(Integer resp_id);
	public List<RespVO> findByTopic(String resp_topic);
}
