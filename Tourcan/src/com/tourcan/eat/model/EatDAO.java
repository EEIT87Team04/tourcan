package com.tourcan.eat.model;

import java.util.List;

import com.tourcan.att.model.AttVO;

public interface EatDAO {
	public AttVO findById(Integer att_id);

	public List<AttVO> findByName(String att_name);

	public List<AttVO> getAll();

	public List<AttVO> findByRegionId(Integer region_id);
}
