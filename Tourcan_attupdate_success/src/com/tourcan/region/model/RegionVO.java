package com.tourcan.region.model;

import java.util.Set;

import com.google.gson.annotations.Expose;
import com.tourcan.att.model.AttVO;

public class RegionVO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	@Expose
	private Integer region_id;
	@Expose
	private String region_name;
	@Expose
	private Integer region_area;
	private Set<AttVO> atts;

	public Set<AttVO> getAtts() {
		return atts;
	}
	public void setAtts(Set<AttVO> atts) {
		this.atts = atts;
	}
	
	public Integer getRegion_id() {
		return region_id;
	}
	public void setRegion_id(Integer region_id) {
		this.region_id = region_id;
	}
	public String getRegion_name() {
		return region_name;
	}
	public void setRegion_name(String region_name) {
		this.region_name = region_name;
	}
	public Integer getRegion_area() {
		return region_area;
	}
	public void setRegion_area(Integer region_area) {
		this.region_area = region_area;
	}

}