package com.emp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.emp.dao.implementation.EmployeeDaoImp;
import com.emp.entity.Admin;
import com.emp.entity.Employee;


@RestController
@CrossOrigin(origins="http://localhost:4200")
public class EmployeeController {

	@Autowired
	EmployeeDaoImp employeeDaoImp;
	
	@RequestMapping(value = "/addEmp",method = RequestMethod.POST)
	 public boolean addUser(@RequestBody Employee employee)
	 {
		System.out.println("addd ");
		  return employeeDaoImp.addEmployee(employee);
		 
	 }
	@RequestMapping(value = "/getEmp",method = RequestMethod.GET)
	 public List<Employee> getData(){
		 return employeeDaoImp.getEmployeeList();
	 }
	
	@GetMapping("/editEmp/{AdminId}")
    public Employee editEmployee(@PathVariable("employeeId") String employeeId ) {
            System.out.println("test" + employeeId);
        return  employeeDaoImp.findEmployeeId(employeeId);
    }
  	
	@PutMapping("/updateEmp/{employeeId}")
    public Boolean updateAdmin(@PathVariable("employeeId") String employeeId, @RequestBody Employee employee ) {
            System.out.println("test" + employeeId);
          
        return  employeeDaoImp.updateEmployee(employeeId, employee);
    }
	
}
