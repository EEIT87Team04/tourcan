package com.tourcan.att.model;

import java.util.List;

import com.tourcan.region.model.RegionVO;


public class AttService {

	private AttDAO_interface dao;

	public AttService() {
		dao = new AttDAO();
	}
	
	
	
	public AttVO getOneMem(Integer att_id){
		return dao.findById(att_id);
	}

	
	public AttVO getOne(Integer att_id){
		return dao.findById(att_id);
	}
	public List<AttVO> getAllByName(String att_name){
		return dao.findByName(att_name);
	}
	
	public List<AttVO> getAll(){
		return dao.getAll();

	}

	public void deleteAtt(Integer att_id) {
		dao.delete(att_id);
	}
}
