package com.emp.dao.interfaces;

import java.util.List;

import com.emp.entity.Admin;
import com.emp.entity.Employee;

public interface EmployeeDao {

	
	
	
	
	
	public Boolean addEmployee(Employee employee);
	public List<Employee> getEmployeeList();
	public Employee findEmployeeId(String employeeId);
	public boolean updateEmployee(String employeeId,Employee employee);
}
