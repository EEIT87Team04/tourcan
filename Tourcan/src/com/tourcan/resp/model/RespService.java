package com.tourcan.resp.model;

import java.util.List;


public class RespService {

	private Resp_interface dao;

	public RespService() {
		dao = new RespDAO();
	}
	
	
	public void insert(RespVO respVO) {
		dao.insert(respVO);
	}


	public void update(RespVO respVO) {
		dao.update(respVO);
	}


	public void delete(RespVO respVO) {
		dao.delete(respVO);
	}


	public RespVO findById(Integer resp_id) {
		return dao.findById(resp_id);
	}


	



	
}
