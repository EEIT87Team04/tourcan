package com.tourcan.photo.model;

public class PhotoService {

	private PhotoDAO_interface dao;
	public PhotoService() {
		dao = new PhotoDAO();
	}

	// -------------------------INSERT------------------------
	public PhotoVO insert(byte[] photo_file, Integer att_id, Integer hotel_id) {
		PhotoVO photoVO = new PhotoVO();
		photoVO.setPhoto_file(photo_file);
		photoVO.setAtt_id(att_id);
		photoVO.setHotel_id(hotel_id);
		try {
			dao.insert(photoVO);
			return photoVO;
		} catch (Exception e) {
			return null;
		}
	}

	public PhotoVO insertByAttId(byte[] photo_file, Integer att_id) {
		PhotoVO photoVO = new PhotoVO();
		photoVO.setPhoto_file(photo_file);
		photoVO.setAtt_id(att_id);
		try {
			dao.insert(photoVO);
			return photoVO;
		} catch (Exception e) {
			return null;
		}
	}

	public PhotoVO insertByHotelId(byte[] photo_file, Integer hotel_id) {
		PhotoVO photoVO = new PhotoVO();
		photoVO.setPhoto_file(photo_file);
		photoVO.setHotel_id(hotel_id);
		try {
			dao.insert(photoVO);
			return photoVO;
		} catch (Exception e) {
			return null;
		}
	}
	
	public PhotoVO insert(byte[] photo_file) {
		PhotoVO photoVO = new PhotoVO();
		photoVO.setPhoto_file(photo_file);
		try {
			dao.insert(photoVO);
			return photoVO;
		} catch (Exception e) {
			return null;
		}
	}
	// -------------------------UPDATE------------------------
//	public PhotoVO update(Integer photo_id,byte[] photo_file) {
//		PhotoVO photoVO = new PhotoVO();
//		photoVO.setPhoto_id(photo_id);
//		photoVO.setPhoto_file(photo_file);
//		dao.update(photoVO);
//		return photoVO;
//	}
	// -------------------------DELETE------------------------
	// ------------------------GET_BY_PHOTO_ID------------------------
	public String getOne(Integer photo_id) {
		PhotoVO photoVO = new PhotoVO();
		photoVO = dao.findById(photo_id);
		
		
		
		return null;
	}
	
	
	// ------------------------GET_ALL------------------------
}
