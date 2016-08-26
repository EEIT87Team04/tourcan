package com.tourcan.att.model;

public class AttService {

	private AttDAO_interface dao;

	public AttService() {
		dao = new AttDAO();
	}

	public void deleteAtt(Integer attId) {
		dao.delete(attId);
	}
}
