package com.tourcan.tripitem.model;

import java.util.List;

public interface TripitemDAO_interface {
	public void insert(TripitemVO tripitemVO);
	public void update(TripitemVO tripitemVO);
	public void delete(Integer tripitem_id);
	public TripitemVO findById(Integer tripitem_id);
	public List<TripitemVO> getAll();
}
