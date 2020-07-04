package com.emp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emp.dao.AdminDaoImpl;
import com.emp.entity.User;
@Service
public class AdminServiceImpl implements AdminService{

	
	@Autowired(required=true)
	AdminDaoImpl adminDaoImpl;
	
	@Override
	public Boolean addAdmin(User user) {
		// TODO Auto-generated method stub
		return adminDaoImpl.addAdmin(user);
	}
	@Override
	public List<User> getAdminList() {
		// TODO Auto-generated method stub
		return adminDaoImpl.getAdminList();
	}
	@Override
	public Boolean deleteAdmin(String userId) {
		// TODO Auto-generated method stub
		return adminDaoImpl.deleteAdmin(userId);
	}

}
