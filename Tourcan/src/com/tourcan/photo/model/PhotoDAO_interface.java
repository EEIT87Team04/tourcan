package com.tourcan.photo.model;

import java.util.List;

public interface PhotoDAO_interface {

	public void insert(PhotoVO photoVO);	
	public void delete(Integer photo_id);	
	public void update(PhotoVO photoVO);	
	public PhotoVO findById(Integer photo_id);
	public List<PhotoVO> findByAttId(Integer att_id);	
	public List<PhotoVO> findByHotleId(Integer hotel_id);	
	public List<PhotoVO> findByAttNHotleId(Integer att_id,Integer hotel_id);	
	public List<PhotoVO> getAll();	
	
}
