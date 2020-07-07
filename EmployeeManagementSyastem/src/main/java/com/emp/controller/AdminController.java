package com.emp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.emp.dao.AdminDaoImpl;
import com.emp.dao.EmployeeDaoImp;
import com.emp.entity.EmployeeWorkDetail;
import com.emp.entity.User;
import com.emp.service.AdminServiceImpl;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class AdminController {

	@Autowired(required=true)
	AdminServiceImpl adminServiceImpl;
	
	@RequestMapping(value = "/addAdmin",method = RequestMethod.POST)
	 public boolean addUser(@RequestBody User user)
	 {
//		System.out.println("addd ");
		
		  return adminServiceImpl.addAdmin(user);
		 
	 }
	
	@RequestMapping(value = "/getAdminlist",method = RequestMethod.GET)
	 public List<User> getAdminList(){
		 return adminServiceImpl.getAdminList();
	 }
	
	
	 @DeleteMapping("/deleteAdmin/{userId}")
		public String deleteAdmin(@PathVariable("userId") String userId ) {
		 adminServiceImpl.deleteAdmin(userId);
			return userId;
			
		}
	 
	
	 
	 @GetMapping("/getEmployeeWorkForAdmin")
	    public List<EmployeeWorkDetail> getWorkDetailForAdmin( ) {
//	            System.out.println("test" + employeeId);
	        return  adminServiceImpl.getWorkDetailForAdmin();
	    }
	 
	 @GetMapping("/getWorkId/{workID}")
	    public EmployeeWorkDetail getworkID(@PathVariable("workID") String workID ) {
//	            System.out.println("test" + userId);
	        return  adminServiceImpl.findWorkId(workID);
	    }
	 @PutMapping("/employeeWorkFeedBack")
	    public Boolean employeeWorkFeedBack(@RequestBody EmployeeWorkDetail employeeWorkDetail ) {
//	            System.out.println("test" + userId);
	            
//	            System.out.println("test" + user.getPassword());
	          
	        return  adminServiceImpl.employeeWorkFeedBack(employeeWorkDetail);
	    }
	
}
