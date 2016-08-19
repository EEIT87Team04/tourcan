package com.tourcan.hotel.model;

import java.util.List;

public interface HotelDAO {
	public void insert(HotelVO hotelVO);
	public void update(HotelVO hotelVO);
	public void delete(HotelVO hotelVO);
	public HotelVO findById(Integer hotel_id);
	public List<HotelVO> findByName(String hotel_name);
	public List<HotelVO> getAll();
}
