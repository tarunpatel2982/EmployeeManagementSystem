package com.emp.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emp.dao.LoginDaoImpl;
import com.emp.entity.User;

@Service
public class LoginServiceImpl implements LoginService {

	
	@Autowired
	LoginDaoImpl loginDaoImpl;
	@Transactional
	@Override
	public User userLogin(String emailId, String password) {
		// TODO Auto-generated method stub
		return loginDaoImpl.userLogin(emailId, password);
	}

}
