package com.tourcan.hotel.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tourcan.region.model.RegionVO;

@XmlRootElement(name = "hotel")
@JsonIgnoreProperties(ignoreUnknown=true)
public class HotelVO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	// private Integer att_area;
	private String hotel_name;
	private Integer hotel_id;
//	private Integer region_id;
	private RegionVO regionVO;
	private String hotel_addr;
	private Double hotel_price;
	private String hotel_phone;
	private Integer hotel_class;
	private String hotel_url;
	private Double hotel_lat;
	private Double hotel_lng;

	public String getHotel_name() {
		return hotel_name;
	}

	public void setHotel_name(String hotel_name) {
		this.hotel_name = hotel_name;
	}

	public Integer getHotel_id() {
		return hotel_id;
	}

	public void setHotel_id(Integer hotel_id) {
		this.hotel_id = hotel_id;
	}

//	public Integer getRegion_id() {
//		return region_id;
//	}
//
//	public void setRegion_id(Integer region_id) {
//		this.region_id = region_id;
//	}

	public String getHotel_addr() {
		return hotel_addr;
	}

	public void setHotel_addr(String hotel_addr) {
		this.hotel_addr = hotel_addr;
	}

	public Double getHotel_price() {
		return hotel_price;
	}

	public void setHotel_price(Double hotel_price) {
		this.hotel_price = hotel_price;
	}

	public String getHotel_phone() {
		return hotel_phone;
	}

	public void setHotel_phone(String hotel_phone) {
		this.hotel_phone = hotel_phone;
	}

	public Integer getHotel_class() {
		return hotel_class;
	}

	public void setHotel_class(Integer hotel_class) {
		this.hotel_class = hotel_class;
	}

	public String getHotel_url() {
		return hotel_url;
	}

	public void setHotel_url(String hotel_url) {
		this.hotel_url = hotel_url;
	}

	public Double getHotel_lat() {
		return hotel_lat;
	}

	public void setHotel_lat(Double hotel_lat) {
		this.hotel_lat = hotel_lat;
	}

	public Double getHotel_lng() {
		return hotel_lng;
	}

	public void setHotel_lng(Double hotel_lng) {
		this.hotel_lng = hotel_lng;
	}

	public RegionVO getRegionVO() {
		return regionVO;
	}

	public void setRegionVO(RegionVO regionVO) {
		this.regionVO = regionVO;
	}

}
