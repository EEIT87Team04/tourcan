package com.tourcan.att.model;

public class AttService {

	private AttDAO_interface dao;

	public AttService() {
		dao = new AttDAO();
	}

	public void deleteAtt(Integer att_id) {
		dao.delete(att_id);
	}
}
