package com.tourcan.trip.model;

public class TripService {

	private TripDAO_interface dao;

	public TripService() {
		dao = new TripDAO();
	}
}
