package com.emp.entity;

import org.springframework.data.annotation.Id;

public class Admin {

	@Id
	String Id;
	String adminId;
	String emailId;
	String password;
	String role;
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
		
}
