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
	
	public void delete(Integer tripitem_id) {
		dao.delete(tripitem_id);
	}
	
	public TripitemVO findById(Integer tripitem_id){
		return dao.findById(tripitem_id);
	}
	
	public List<TripitemVO> getAll(){
		return dao.getAll();
	}
}
