package com.tourcan.att.model;

public class AttService {

	private AttDAO_interface dao;

	public AttService() {
		dao = new AttDAO();
	}

	public AttVO deleteAtt(Integer att_id) {

		AttVO attVO = new AttVO();
		attVO.setAtt_id(att_id);
		dao.delete(attVO);

		return attVO;
	}
}
