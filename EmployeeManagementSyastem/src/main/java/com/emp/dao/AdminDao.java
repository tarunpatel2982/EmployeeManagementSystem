package com.emp.dao;

import java.util.List;

import com.emp.entity.User;

public interface AdminDao {

	 public Boolean addAdmin(User user);
	 public List<User> getAdminList();
	 	public Boolean deleteAdmin(String userId) ;
}
