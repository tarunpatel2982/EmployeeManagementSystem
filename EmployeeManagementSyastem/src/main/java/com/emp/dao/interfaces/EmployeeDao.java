package com.emp.dao.interfaces;

import java.util.List;

import com.emp.entity.User;
import com.emp.entity.EmployeeDetail;

public interface EmployeeDao {

	
	
	
	
	
	public Boolean addEmployee(User user);
	public List<User> getEmployeeList();
	public User findEmployeeId(String employeeId);
	public boolean updateEmployee(String employeeId,EmployeeDetail employeeDetail);
}
