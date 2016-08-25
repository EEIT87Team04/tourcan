package com.tourcan.att.model;

import java.util.List;

import com.tourcan.region.model.RegionVO;


public class AttService {

	private AttDAO_interface dao;

	public AttService() {
		dao = new AttDAO();
	}
	
	public AttVO insert(String attName, Integer regionId, String attAddr, Boolean attEat, String attIntro,
			String appOpen, String attPhone, Double attPrice, Integer attStaytime, String attUrl, Double attLat,
			Double attLng){
		AttVO attVO = new AttVO();
		RegionVO regionVO = new RegionVO();
		regionVO.setRegion_id(regionId);

		attVO.setAtt_name(attName);
		attVO.setRegionVO(regionVO);;
		attVO.setAtt_addr(attAddr);
		attVO.setAtt_eat(attEat);
		attVO.setAtt_intro(attIntro);
		attVO.setAtt_open(appOpen);
		attVO.setAtt_phone(attPhone);
		attVO.setAtt_price(attPrice);
		attVO.setAtt_staytime(attStaytime);
		attVO.setAtt_url(attUrl);
		attVO.setAtt_lat(attLat);
		attVO.setAtt_lng(attLng);
		
		dao.insert(attVO);
		
		return attVO;
	}

	
	public AttVO updateMem(String att_name,Integer att_id,Integer att_staytime,Integer region_id,String att_addr, Double att_price,String att_phone,String att_url,Boolean att_eat,String att_intro,String att_open,Double att_lat,Double att_lng){
		
		AttVO attVO=new AttVO();
		RegionVO regionVO = new RegionVO();
		regionVO.setRegion_id(region_id);
		
		attVO.setAtt_name(att_name);
		attVO.setAtt_id(att_id);
		attVO.setAtt_staytime(att_staytime);
		attVO.setRegionVO(regionVO);
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
