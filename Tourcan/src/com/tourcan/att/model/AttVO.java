package com.tourcan.att.model;

import com.google.gson.annotations.Expose;
import com.tourcan.region.model.RegionVO;

public class AttVO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	// private Integer att_area;
	// @SerializedName("attName")
	@Expose
	private String att_name;

	// @SerializedName("attId")
	@Expose
	private Integer att_id;

	// @SerializedName("attStaytime")
	@Expose
	private Integer att_staytime;

	// @SerializedName("regionId")
	@Expose
	private Integer region_id;

	@Expose
	private RegionVO regionVO;

	// @SerializedName("attAddr")
	@Expose
	private String att_addr;

	// @SerializedName("attPrice")
	@Expose
	private Double att_price;

	// @SerializedName("attPhone")
	@Expose
	private String att_phone;

	// @SerializedName("attUrl")
	@Expose
	private String att_url;

	// @SerializedName("attEat")
	@Expose
	private Boolean att_eat;

	// @SerializedName("attIntro")
	@Expose
	private String att_intro;

	// @SerializedName("attOpen")
	@Expose
	private String att_open;

	// @SerializedName("attLat")
	@Expose
	private Double att_lat;

	// @SerializedName("attLng")
	@Expose
	private Double att_lng;

	public AttVO() {
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public RegionVO getRegionVO() {
		return regionVO;
	}

	public void setRegionVO(RegionVO regionVO) {
		this.regionVO = regionVO;
	}

	public String getAtt_name() {
		return att_name;
	}

	public void setAtt_name(String att_name) {
		this.att_name = att_name;
	}

	public Integer getAtt_id() {
		return att_id;
	}

	public void setAtt_id(Integer att_id) {
		this.att_id = att_id;
	}

	public Integer getAtt_staytime() {
		return att_staytime;
	}

	public void setAtt_staytime(Integer att_staytime) {
		this.att_staytime = att_staytime;
	}

	public String getAtt_addr() {
		return att_addr;
	}

	public void setAtt_addr(String att_addr) {
		this.att_addr = att_addr;
	}

	public Double getAtt_price() {
		return att_price;
	}

	public void setAtt_price(Double att_price) {
		this.att_price = att_price;
	}

	public String getAtt_phone() {
		return att_phone;
	}

	public void setAtt_phone(String att_phone) {
		this.att_phone = att_phone;
	}

	public String getAtt_url() {
		return att_url;
	}

	public void setAtt_url(String att_url) {
		this.att_url = att_url;
	}

	public Boolean getAtt_eat() {
		return att_eat;
	}

	public void setAtt_eat(Boolean att_eat) {
		this.att_eat = att_eat;
	}

	public String getAtt_intro() {
		return att_intro;
	}

	public void setAtt_intro(String att_intro) {
		this.att_intro = att_intro;
	}

	public String getAtt_open() {
		return att_open;
	}

	public void setAtt_open(String att_open) {
		this.att_open = att_open;
	}

	public Double getAtt_lat() {
		return att_lat;
	}

	public void setAtt_lat(Double att_lat) {
		this.att_lat = att_lat;
	}

	public Double getAtt_lng() {
		return att_lng;
	}

	public void setAtt_lng(Double att_lng) {
		this.att_lng = att_lng;
	}
	
	public Integer getRegion_id() {
		return region_id;
	}

	public void setRegion_id(Integer region_id) {
		this.region_id = region_id;
	}

}
