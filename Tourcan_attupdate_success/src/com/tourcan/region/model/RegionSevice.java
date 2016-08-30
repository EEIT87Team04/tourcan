package com.tourcan.region.model;


import com.tourcan.att.model.AttVO;

public class RegionSevice {

	private RegionDAO_interface dao;
	
	public RegionSevice() {
		dao = new RegionDAO();
	}
	
public RegionVO updateMem(Integer region_id,String region_name,Integer region_area){
		
	    RegionVO regionVO=new RegionVO();
		regionVO.setRegion_id(region_id);
		regionVO.setRegion_name(region_name);
		regionVO.setRegion_area(region_area);
		
		dao.update(regionVO);
		
		return regionVO;
	}
	
	public RegionVO getOneMem(Integer region_id){
		return dao.findById(region_id);
	}
}
