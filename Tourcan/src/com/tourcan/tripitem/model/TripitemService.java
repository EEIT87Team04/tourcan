package com.tourcan.tripitem.model;

import java.util.List;

public class TripitemService {

	private TripitemDAO_interface dao;

	public TripitemService() {
		dao = new TripitemDAO();
	}
	
	public TripitemVO insert(TripitemVO tripitemVO) {
			dao.insert(tripitemVO);
			return tripitemVO;
	}
	
	public TripitemVO update(TripitemVO tripitemVO) {
		dao.update(tripitemVO);
		return tripitemVO;
	}
	
	public void delete(Integer tripitem_serial) {
		dao.delete(tripitem_serial);
	}
	
	public TripitemVO findById(Integer tripitem_serial){
		return dao.findById(tripitem_serial);
	}
	
	public List<TripitemVO> getAll(){
		return dao.getAll();
	}
}
