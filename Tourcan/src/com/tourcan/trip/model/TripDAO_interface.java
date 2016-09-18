package com.tourcan.trip.model;

import java.util.List;

public interface TripDAO_interface {
	public Integer insert(TripVO tripVO);
	public void update(TripVO tripVO);
	public void delete(Integer trip_id);
	public TripVO findById(Integer trip_id);
	public List<TripVO> findByName(String trip_name);
	public List<TripVO> getAll();
}
