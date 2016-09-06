package com.tourcan.trip.model;

import java.sql.Timestamp;

public class TripVO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private String trip_name;
	private Integer trip_id;
	private Timestamp trip_ctime;
	private Integer trip_price;
	private String mem_uid;

	public String getTrip_name() {
		return trip_name;
	}
	public void setTrip_name(String trip_name) {
		this.trip_name = trip_name;
	}
	public Integer getTrip_id() {
		return trip_id;
	}
	public void setTrip_id(Integer trip_id) {
		this.trip_id = trip_id;
	}
	public Timestamp getTrip_ctime() {
		return trip_ctime;
	}
	public void setTrip_ctime(Timestamp trip_ctime) {
		this.trip_ctime = trip_ctime;
	}
	public Integer getTrip_price() {
		return trip_price;
	}
	public void setTrip_price(Integer trip_price) {
		this.trip_price = trip_price;
	}
	public String getMem_uid() {
		return mem_uid;
	}
	public void setMem_uid(String mem_uid) {
		this.mem_uid = mem_uid;
	}
}
