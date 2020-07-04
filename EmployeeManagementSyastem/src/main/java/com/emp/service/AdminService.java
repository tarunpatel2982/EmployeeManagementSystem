package com.emp.service;

import java.util.List;

import com.emp.entity.User;

public interface AdminService {

	 public Boolean addAdmin(User user);
	 public List<User> getAdminList();
	 public Boolean deleteAdmin(String userId) ;
}
