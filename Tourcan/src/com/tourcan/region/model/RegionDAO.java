package com.tourcan.region.model;

import java.util.List;

public interface RegionDAO {
	public void insert(RegionVO regionVO);
	public void update(RegionVO regionVO);
	public void delete(RegionVO regionVO);
	public RegionVO findById(Integer region_id);
	public List<RegionVO> findByArea(Integer region_area);
	public List<RegionVO> findByName(String region_name);
	public List<RegionVO> getAll();
}
