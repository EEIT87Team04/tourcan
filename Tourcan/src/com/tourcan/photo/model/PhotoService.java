package com.tourcan.photo.model;

import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;
import org.json.JSONException;
import org.json.JSONObject;

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
	// public PhotoVO update(Integer photo_id,byte[] photo_file) {
	// PhotoVO photoVO = new PhotoVO();
	// photoVO.setPhoto_id(photo_id);
	// photoVO.setPhoto_file(photo_file);
	// dao.update(photoVO);
	// return photoVO;
	// }
	// -------------------------DELETE------------------------

	// ------------------------GET_BY_PHOTO_ID------------------------
	public String getImg(Integer photo_id) {
		PhotoVO photoVO = new PhotoVO();
		try {
			photoVO = dao.findById(photo_id);
			byte[] photo_file = photoVO.getPhoto_file();
			return Base64.encodeBase64String(photo_file);
		} catch (Exception e) {
			return null;
		}
	}

	// ------------------------GET_BY_ATT_ID------------------------
	public JSONObject getByAttId(Integer att_id) {
		JSONObject imgJson = new JSONObject();
		try {
			List<PhotoVO> imgList = dao.findByAttId(att_id);
			for (PhotoVO photoVO : imgList) {
				imgJson.append(photoVO.getAtt_id().toString(), Base64.encodeBase64String(photoVO.getPhoto_file()));
			}
			return imgJson;
		} catch (JSONException e) {
			return null;
		}
	}

	// ------------------------GET_BY_HOTEL_ID------------------------
	public JSONObject getByHotelId(Integer hotel_id) {
		JSONObject imgJson = new JSONObject();
		try {
			List<PhotoVO> imgList = dao.findByAttId(hotel_id);
			for (PhotoVO photoVO : imgList) {
				imgJson.append(photoVO.getHotel_id().toString(), Base64.encodeBase64String(photoVO.getPhoto_file()));
			}
			return imgJson;
		} catch (JSONException e) {
			return null;
		}
	}

	// ------------------------GET_ALL------------------------
	public JSONObject getAll() {
		JSONObject imgJson = new JSONObject();
		try {
			List<PhotoVO> imgList = dao.getAll();
			for (PhotoVO photoVO : imgList) {
				imgJson.append(photoVO.getPhoto_id().toString(), Base64.encodeBase64String(photoVO.getPhoto_file()));
			}
			return imgJson;
		} catch (JSONException e) {
			return null;
		}
	}
}
