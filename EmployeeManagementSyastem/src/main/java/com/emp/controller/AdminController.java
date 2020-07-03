package com.emp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.emp.dao.implementation.AdminDaoImpl;
import com.emp.dao.implementation.EmployeeDaoImp;
import com.emp.entity.User;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class AdminController {

	@Autowired
	AdminDaoImpl adminDaoImpl;
	
	@RequestMapping(value = "/addAdmin",method = RequestMethod.POST)
	 public boolean addUser(@RequestBody User user)
	 {
		System.out.println("addd ");
		  return adminDaoImpl.addAdmin(user);
		 
	 }
	
	@RequestMapping(value = "/getAdminlist",method = RequestMethod.GET)
	 public List<User> getAdminList(){
		 return adminDaoImpl.getAdminList();
	 }
	
	 @DeleteMapping("/deleteAdmin/{userId}")
		public String deleteAdmin(@PathVariable("userId") String userId ) {
		 adminDaoImpl.deleteAdmin(userId);
			return userId;
			
		}
	
}
