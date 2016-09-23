package com.tourcan.mem.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tourcan.region.model.RegionVO;

@XmlRootElement(name = "member")
@JsonIgnoreProperties(ignoreUnknown=true)
public class MemVO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	// TODO rebuild DB to fit new design: mem_uid as primary key
	// private Integer mem_id; //replaced with mem_uid
	private String mem_lname;
	private String mem_fname;
	private String mem_nick;
	// private String mem_account; // replaced with Firebase
	// private String mem_pwd; // replaced with Firebase
	private String mem_email;
	private String mem_mobile;
	private byte[] mem_photo;
	private Integer mem_sex;
//	private Integer region_id; // still necessary?
	private RegionVO regionVO;
	private Date mem_bdate;
	private Date mem_regtime;
	private String mem_uid;

	// public Integer getMem_id() {
	// return mem_id;
	// }
	//
	// public void setMem_id(Integer mem_id) {
	// this.mem_id = mem_id;
	// }

	public String getMem_lname() {
		return mem_lname;
	}

	public void setMem_lname(String mem_lname) {
		this.mem_lname = mem_lname;
	}

	public String getMem_fname() {
		return mem_fname;
	}

	public void setMem_fname(String mem_fname) {
		this.mem_fname = mem_fname;
	}

	public String getMem_nick() {
		return mem_nick;
	}

	public void setMem_nick(String mem_nick) {
		this.mem_nick = mem_nick;
	}

	// public String getMem_account() {
	// return mem_account;
	// }
	//
	// public void setMem_account(String mem_account) {
	// this.mem_account = mem_account;
	// }
	//
	// public String getMem_pwd() {
	// return mem_pwd;
	// }
	//
	// public void setMem_pwd(String mem_pwd) {
	// this.mem_pwd = mem_pwd;
	// }

	public String getMem_email() {
		return mem_email;
	}

	public void setMem_email(String mem_email) {
		this.mem_email = mem_email;
	}

	public String getMem_mobile() {
		return mem_mobile;
	}

	public void setMem_mobile(String mem_mobile) {
		this.mem_mobile = mem_mobile;
	}

	public byte[] getMem_photo() {
		return mem_photo;
	}

	public void setMem_photo(byte[] mem_photo) {
		this.mem_photo = mem_photo;
	}

	public Integer getMem_sex() {
		return mem_sex;
	}

	public void setMem_sex(Integer mem_sex) {
		this.mem_sex = mem_sex;
	}

//	public Integer getRegion_id() {
//		return region_id;
//	}
//
//	public void setRegion_id(Integer region_id) {
//		this.region_id = region_id;
//	}

	public Date getMem_bdate() {
		return mem_bdate;
	}

	public void setMem_bdate(Date mem_bdate) {
		this.mem_bdate = mem_bdate;
	}

	public Date getMem_regtime() {
		return mem_regtime;
	}

	public void setMem_regtime(Date mem_regtime) {
		this.mem_regtime = mem_regtime;
	}

	public String getMem_uid() {
		return mem_uid;
	}

	public void setMem_uid(String mem_uid) {
		this.mem_uid = mem_uid;
	}

	public RegionVO getRegionVO() {
		return regionVO;
	}

	public void setRegionVO(RegionVO regionVO) {
		this.regionVO = regionVO;
	}

}
