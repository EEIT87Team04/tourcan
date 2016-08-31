package com.tourcan.photo.model;

public class PhotoService {

	PhotoDAO_interface dao;

	public PhotoService() {
		dao = new PhotoDAO();
	}

	// -------------------------INSERT------------------------
	public PhotoVO insert(Integer photo_id, Byte[] photo_file, Integer att_id, Integer hotel_id) {
		PhotoVO photoVO = new PhotoVO();

		photoVO.setPhoto_id(photo_id);
		photoVO.setPhoto_file(photo_file);
		photoVO.setAtt_id(att_id);
		photoVO.setHotel_id(hotel_id);

		dao.insert(photoVO);
		return photoVO;
	}

	public PhotoVO insert(Integer photo_id, Byte[] photo_file, Integer att_id) {
		PhotoVO photoVO = new PhotoVO();

		photoVO.setPhoto_id(photo_id);
		photoVO.setPhoto_file(photo_file);
		photoVO.setAtt_id(att_id);

		dao.insert(photoVO);
		return photoVO;
	}

	public PhotoVO insert(Integer photo_id, Byte[] photo_file) {
		PhotoVO photoVO = new PhotoVO();

		photoVO.setPhoto_id(photo_id);
		photoVO.setPhoto_file(photo_file);

		dao.insert(photoVO);
		return photoVO;
	}
	// -------------------------DELETE------------------------
	// -------------------------UPDATE------------------------
	// ------------------------GET_ALL------------------------
}
