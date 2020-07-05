package com.emp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emp.dao.AdminDaoImpl;
import com.emp.dao.SuperAdminDaoImpl;
import com.emp.entity.User;
@Service
public class SuperAdminServiceImpl implements SuperAdminService{

	
	@Autowired
	SuperAdminDaoImpl superAdminDaoImpl;
	@Transactional
	@Override
	public Boolean createSuperAdminUser() {
		// TODO Auto-generated method stub
		return superAdminDaoImpl.createSuperAdminUser();
	}
	@Override
	public User findUserId(String userId) {
		// TODO Auto-generated method stub
		return superAdminDaoImpl.findUserId(userId);
	}
	@Override
	public Boolean changeUserPassword(String userId, User user) {
		// TODO Auto-generated method stub
		return superAdminDaoImpl.changeUserPassword(userId, user);
	}

}
