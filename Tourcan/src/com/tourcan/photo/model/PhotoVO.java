package com.tourcan.photo.model;

import java.io.Serializable;

public class PhotoVO implements Serializable {

	private Integer photo_id;
	private byte[] photo_file;
	private Integer att_id;
	private Integer hotel_id;

	public Integer getPhoto_id() {
		return photo_id;
	}

	public void setPhoto_id(Integer photo_id) {
		this.photo_id = photo_id;
	}

	public byte[] getPhoto_file() {
		return photo_file;
	}

	public void setPhoto_file(byte[] photo_file) {
		this.photo_file = photo_file;
	}

	public Integer getAtt_id() {
		return att_id;
	}

	public void setAtt_id(Integer att_id) {
		this.att_id = att_id;
	}

	public Integer getHotel_id() {
		return hotel_id;
	}

	public void setHotel_id(Integer hotel_id) {
		this.hotel_id = hotel_id;
	}

}
