package com.tourcan.tripitem.model;

import java.sql.Timestamp;

public class TripitemVO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer tripitem_serial;
	private String tripitem_traffic;
	private String tripitem_memo;
	private Integer tripitem_staytime;
	private Timestamp tripitem_begin;
	private Timestamp tripitem_end;
	private Integer trip_id;
	private Integer att_id;
	private Integer hotel_id;
	private Integer tripitem_id;
	
	public Integer getTripitem_serial() {
		return tripitem_serial;
	}
	public void setTripitem_serial(Integer tripitem_serial) {
		this.tripitem_serial = tripitem_serial;
	}
	public String getTripitem_traffic() {
		return tripitem_traffic;
	}
	public void setTripitem_traffic(String tripitem_traffic) {
		this.tripitem_traffic = tripitem_traffic;
	}
	public String getTripitem_memo() {
		return tripitem_memo;
	}
	public void setTripitem_memo(String tripitem_memo) {
		this.tripitem_memo = tripitem_memo;
	}
	public Integer getTripitem_staytime() {
		return tripitem_staytime;
	}
	public void setTripitem_staytime(Integer tripitem_staytime) {
		this.tripitem_staytime = tripitem_staytime;
	}
	public Timestamp getTripitem_begin() {
		return tripitem_begin;
	}
	public void setTripitem_begin(Timestamp tripitem_begin) {
		this.tripitem_begin = tripitem_begin;
	}
	public Timestamp getTripitem_end() {
		return tripitem_end;
	}
	public void setTripitem_end(Timestamp tripitem_end) {
		this.tripitem_end = tripitem_end;
	}
	public Integer getTrip_id() {
		return trip_id;
	}
	public void setTrip_id(Integer trip_id) {
		this.trip_id = trip_id;
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
	public Integer getTripitem_id() {
		return tripitem_id;
	}
	public void setTripitem_id(Integer tripitem_id) {
		this.tripitem_id = tripitem_id;
	}
	
}
