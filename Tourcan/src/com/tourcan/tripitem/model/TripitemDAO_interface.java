package com.tourcan.tripitem.model;

import java.util.List;

public interface TripitemDAO_interface {
	public void insert(TripitemVO tripitemVO);
	public void update(TripitemVO tripitemVO);
	public void delete(Integer tripitem_serial);
	public TripitemVO findById(Integer tripitem_serial);
	public List<TripitemVO> getAll();
}
