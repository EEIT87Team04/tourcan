package com.tourcan.att.model;

public class AttService {

	public AttVO insert(String attName, Integer regionId, String attAddr, Boolean attEat, String attIntro,
			String appOpen, String attPhone, Double attPrice, Integer attStaytime, String attUrl, Double attLat,
			Double attLng) {
		AttVO attvo = new AttVO();

		attvo.setAtt_name(attName);
		attvo.setRegion_id(regionId);
		attvo.setAtt_addr(attAddr);
		attvo.setAtt_eat(attEat);
		attvo.setAtt_intro(attIntro);
		attvo.setAtt_open(appOpen);
		attvo.setAtt_phone(attPhone);
		attvo.setAtt_price(attPrice);
		attvo.setAtt_staytime(attStaytime);
		attvo.setAtt_url(attUrl);
		attvo.setAtt_lat(attLat);
		attvo.setAtt_lng(attLng);
		
		return attvo;
	}

}
