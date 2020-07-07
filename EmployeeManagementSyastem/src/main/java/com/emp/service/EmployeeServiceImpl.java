package com.emp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emp.dao.AdminDaoImpl;
import com.emp.dao.EmployeeDaoImp;
import com.emp.entity.EmployeeWorkDetail;
import com.emp.entity.User;
@Service
public class EmployeeServiceImpl implements EmployeeService{

	
	@Autowired
	EmployeeDaoImp employeeDaoImp;
	
	@Override
	public Boolean addEmployee(User user) {
		// TODO Auto-generated method stub
		return employeeDaoImp.addEmployee(user);
	}
	
	@Override
	public List<User> getEmployeeList() {
		// TODO Auto-generated method stub
		return employeeDaoImp.getEmployeeList();
	}
	
	@Override
	public Boolean deleteEmployee(String userId) {
		// TODO Auto-generated method stub
		return employeeDaoImp.deleteEmployee(userId);
	}
	
	@Override
	public Boolean addWork(EmployeeWorkDetail employeeWorkDetail) {
		// TODO Auto-generated method stub
		return employeeDaoImp.addWork(employeeWorkDetail);
	}
	
	@Override
	public List<EmployeeWorkDetail> getWorkDetail() {
		// TODO Auto-generated method stub
		return employeeDaoImp.getWorkDetail();
	}
	
	
}
