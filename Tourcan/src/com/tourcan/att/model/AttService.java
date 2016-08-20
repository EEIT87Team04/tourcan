package com.tourcan.att.model;

public class AttService {
	private AttDAO_interface dao;

	public AttService() {
		dao = new AttDAO_JDBC();
	}
	
	public AttVO updateMem(String att_name,Integer att_id,Integer att_staytime,Integer region_id,String att_addr, Double att_price,String att_phone,String att_url,Boolean att_eat,String att_intro,String att_open,Double att_lat,Double att_lng){
		
		AttVO attVO=new AttVO();
		attVO.setAtt_name(att_name);
		attVO.setAtt_id(att_id);
		attVO.setAtt_staytime(att_staytime);
		attVO.setRegion_id(region_id);
		attVO.setAtt_addr(att_addr);
		attVO.setAtt_price(att_price);
		attVO.setAtt_phone(att_phone);
		attVO.setAtt_url(att_url);
		attVO.setAtt_eat(att_eat);
		attVO.setAtt_intro(att_intro);
		attVO.setAtt_open(att_open);
		attVO.setAtt_lat(att_lat);
		attVO.setAtt_lng(att_lng);
		
		dao.update(attVO);
		
		return attVO;
	}
	
	public AttVO getOneMem(Integer att_id){
		return dao.findById(att_id);
	}
}
