package com.tourcan.att.model;

import java.util.List;

import com.tourcan.region.model.RegionVO;
import com.tourcan.util.ApplicationContextUtils;

public class AttService {
	private AttDAO_interface dao;

	public AttService() {
		// dao = new AttDAO();
		dao = (AttDAO_interface) ApplicationContextUtils.getContext().getBean("attDAO");
	}

	public AttVO insert(String attName, Integer regionId, String attAddr, Boolean attEat, String attIntro,
			String appOpen, String attPhone, Double attPrice, Integer attStaytime, String attUrl, Double attLat,
			Double attLng) {
		AttVO attVO = new AttVO();
		RegionVO regionVO = new RegionVO();
		regionVO.setRegion_id(regionId);

		attVO.setAtt_name(attName);
		attVO.setRegionVO(regionVO);
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

		try {
			dao.insert(attVO);
			return attVO;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public AttVO insert(AttVO attVO) {
		try {
			dao.insert(attVO);
			return attVO;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public AttVO update(String attName, Integer regionId, String attAddr, Boolean attEat, String attIntro,
			String appOpen, String attPhone, Double attPrice, Integer attStaytime, String attUrl, Double attLat,
			Double attLng, Integer attId) {

		AttVO attVO = new AttVO();
		RegionVO regionVO = new RegionVO();
		regionVO.setRegion_id(regionId);

		attVO.setAtt_id(attId);
		attVO.setAtt_name(attName);
		attVO.setRegionVO(regionVO);
		;
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

		dao.update(attVO);

		return attVO;
	}

	public AttVO getOneMem(Integer att_id) {
		return dao.findById(att_id);
	}

	public AttVO getOne(Integer att_id) {
		return dao.findById(att_id);
	}

	public List<AttVO> getAllByName(String att_name) {
		return dao.findByName(att_name);
	}

	public List<AttVO> getAll() {
		return dao.getAll();
	}

	public void deleteAtt(Integer attId) {
		dao.delete(attId);
	}

	public List<AttVO> getByRegionId(Integer region_id) {
		return dao.findByRegionId(region_id);
	}

}
