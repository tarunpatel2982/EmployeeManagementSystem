package com.emp.dao;

import com.emp.entity.User;

public interface SuperAdminDao {

	
	 public Boolean createSuperAdminUser();
	 public User findUserId(String userId);
	 public Boolean changeUserPassword(String userId,User user);
}

