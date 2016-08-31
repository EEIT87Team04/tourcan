package com.tourcan.att.model;

import com.tourcan.region.model.RegionVO;

public class AttService {

	public AttVO insert(String attName, Integer regionId, String attAddr, Boolean attEat, String attIntro,
			String appOpen, String attPhone, Double attPrice, Integer attStaytime, String attUrl, Double attLat,
			Double attLng) {
		AttDAO dao = new AttDAO();
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

		dao.insert(attVO);
		return attVO;
	}

	public AttVO insert(AttVO attVO) {
		AttDAO dao = new AttDAO();
		dao.insert(attVO);
		return attVO;
	}

}
