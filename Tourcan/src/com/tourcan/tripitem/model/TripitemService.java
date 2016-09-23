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

	public TripitemVO findById(Integer tripitem_id) {
		return dao.findById(tripitem_id);
	}

	public List<TripitemVO> getAll() {
		return dao.getAll();
	}

	public List<TripitemVO> findByTripID(Integer trip_id) {
		return dao.findByTripID(trip_id);
	}

	public boolean deleteForTripID(Integer trip_id) {
		List<TripitemVO> itemlist = null;
		try {
			itemlist = dao.findByTripID(trip_id);
			for (TripitemVO tVO : itemlist) {
				Integer id = tVO.getTripitem_id();
				dao.delete(id);
			}
			return true;
		} catch (Exception e) {
			return false;
		}

	}

}
