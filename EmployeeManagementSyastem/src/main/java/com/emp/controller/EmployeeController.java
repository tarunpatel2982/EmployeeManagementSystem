package com.emp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.emp.entity.User;
import com.emp.service.EmployeeServiceImpl;
import com.emp.dao.EmployeeDaoImp;
import com.emp.entity.EmployeeWorkDetail;


@RestController
@CrossOrigin(origins="http://localhost:4200")
public class EmployeeController {

	@Autowired(required=true)
	EmployeeServiceImpl employeeServiceImpl;
	
	@RequestMapping(value = "/addEmployee",method = RequestMethod.POST)
	 public boolean addUser(@RequestBody User user)
	 {
//		System.out.println("addd " + user.getPhoneNo());
		  return employeeServiceImpl.addEmployee(user);
		 
	 }
	@RequestMapping(value = "/getEmployeelist",method = RequestMethod.GET)
	 public List<User> getEmployeeList(){
		 return employeeServiceImpl.getEmployeeList();
	 }
	
	 @DeleteMapping("/deleteEmployee/{userId}")
		public String deleteEmployee(@PathVariable("userId") String userId ) {
		 employeeServiceImpl.deleteEmployee(userId);
			return userId;
			
		} 
	 
	@RequestMapping(value = "/addWorkDetail",method = RequestMethod.POST)
	 public boolean addWorkDetail(@RequestBody EmployeeWorkDetail employeeWorkDetail)
	 {
//		System.out.println("addd ");
		  return employeeServiceImpl.addWork(employeeWorkDetail);
		 
	 }
	
	@GetMapping("/getEmployeeWork")
    public List<EmployeeWorkDetail> getWorkDetail( ) {
            
        return  employeeServiceImpl.getWorkDetail();
    }
	
	


}
