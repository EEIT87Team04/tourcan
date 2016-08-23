package com.tourcan.att.model;

import java.util.List;

public interface AttDAO_interface {
	public void insert(AttVO attVO);
	public void update(AttVO attVO);
	public void delete(Integer att_id);
	public AttVO findById(Integer att_id);
	public List<AttVO> findByName(String att_name);
	public List<AttVO> getAll();
	

}
