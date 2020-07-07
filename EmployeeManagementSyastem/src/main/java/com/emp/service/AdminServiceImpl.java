package com.emp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emp.dao.AdminDaoImpl;
import com.emp.entity.EmployeeWorkDetail;
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
	
	@Override
	public EmployeeWorkDetail findWorkId(String workID) {
		// TODO Auto-generated method stub
		return adminDaoImpl.findWorkId(workID);
	}
	@Override
	public List<EmployeeWorkDetail> getWorkDetailForAdmin() {
		// TODO Auto-generated method stub
		return adminDaoImpl.getWorkDetailForAdmin();
	}
	
	@Override
	public boolean employeeWorkFeedBack(EmployeeWorkDetail employeeWorkDetail) {
		// TODO Auto-generated method stub
		boolean status =false;
		try {
			adminDaoImpl.employeeWorkFeedBack(employeeWorkDetail);
			status=true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	

}
