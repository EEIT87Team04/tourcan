package com.tourcan.region.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@XmlRootElement(name = "region")
@JsonIgnoreProperties(ignoreUnknown=true)
public class RegionVO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Integer region_id;
	private String region_name;
	private Integer region_area;

	public static long getSerialversionuid() {
		return serialVersionUID;
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
