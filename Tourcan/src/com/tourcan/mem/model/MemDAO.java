package com.tourcan.mem.model;

import java.util.List;

public interface MemDAO {
	public void insert(MemVO memVO);
	public void update(MemVO memVO);
	public void delete(MemVO memVO);
	public MemVO findById(Integer id);
	public MemVO findByUid(String uid);
	public List<MemVO> findByName(String name);
	public List<MemVO> getAll();
}
