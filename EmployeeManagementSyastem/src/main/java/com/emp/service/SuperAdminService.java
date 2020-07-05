package com.emp.service;

import com.emp.entity.User;

public interface SuperAdminService {

	 public Boolean createSuperAdminUser();
	 public User findUserId(String userId);
	 public Boolean changeUserPassword(String userId,User user);
}
