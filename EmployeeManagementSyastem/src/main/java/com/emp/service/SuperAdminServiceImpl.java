package com.emp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emp.dao.AdminDaoImpl;
import com.emp.dao.SuperAdminDaoImpl;
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

}
