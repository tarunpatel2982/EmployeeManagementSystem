package com.emp.dao;

import com.emp.entity.User;

public interface LoginDao {

	
	public User userLogin(String emailId, String password);
}
