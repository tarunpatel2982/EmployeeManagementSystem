package com.emp.service;

import com.emp.entity.User;

public interface LoginService {

	public User userLogin(String emailId, String password);
}
