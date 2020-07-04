package com.emp.service;

import java.util.List;

import com.emp.entity.EmployeeWorkDetail;
import com.emp.entity.User;

public interface EmployeeService {

	public Boolean addEmployee(User user);
	public List<User> getEmployeeList();
	public Boolean addWork(EmployeeWorkDetail  employeeWorkDetail);
	public List<EmployeeWorkDetail> getWorkDetail();
	public List<EmployeeWorkDetail> getWorkDetailForAdmin();
	
}
