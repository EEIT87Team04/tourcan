package com.tourcan.trip.model;

import java.sql.Timestamp;
import java.util.List;

public class TripService {

	private TripDAO_interface dao;

	public TripService() {
		dao = new TripDAO();
	}

	public TripVO insertTrip(String trip_name, Timestamp trip_ctime, Integer trip_price, String mem_uid) {

		TripVO tripVO = new TripVO();

		tripVO.setTrip_name(trip_name);
		tripVO.setTrip_ctime(trip_ctime);
		tripVO.setTrip_price(trip_price);
		tripVO.setMem_uid(mem_uid);

		dao.insert(tripVO);

		return tripVO;
	}

	public TripVO updateTrip(String trip_name, Integer trip_id, Timestamp trip_ctime, Integer trip_price, String mem_uid) {

		TripVO tripVO = new TripVO();

		tripVO.setTrip_name(trip_name);
		tripVO.setTrip_id(trip_id);
		tripVO.setTrip_ctime(trip_ctime);
		tripVO.setTrip_price(trip_price);
		tripVO.setMem_uid(mem_uid);

		dao.update(tripVO);

		return tripVO;
	}
	
	public void deleteTrip(Integer trip_id){
		dao.delete(trip_id);
	}
	
	public TripVO findById(Integer trip_id){
		return dao.findById(trip_id);
	}
	
	public List<TripVO> findByName(String trip_name){
		return dao.findByName(trip_name);
	}
	
	public List<TripVO> getAll(){
		return dao.getAll();
	}
}
