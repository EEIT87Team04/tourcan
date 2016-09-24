package com.tourcan.admin.model;

import java.io.Serializable;

public class AdminVO implements Serializable {
	
	String admin_name;
	String admin_account;
	String admin_pwd;
	Integer admin_id;
	Integer admin_level;
	
	public AdminVO() {
		super();
	}
	public String getAdmin_name() {
		return admin_name;
	}
	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}
	public String getAdmin_account() {
		return admin_account;
	}
	public void setAdmin_account(String admin_account) {
		this.admin_account = admin_account;
	}
	public String getAdmin_pwd() {
		return admin_pwd;
	}
	public void setAdmin_pwd(String admin_pwd) {
		this.admin_pwd = admin_pwd;
	}
	public Integer getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(Integer admin_id) {
		this.admin_id = admin_id;
	}
	public Integer getAdmin_level() {
		return admin_level;
	}
	public void setAdmin_level(Integer admin_level) {
		this.admin_level = admin_level;
	}
}
