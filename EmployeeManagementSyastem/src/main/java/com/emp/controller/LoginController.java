package com.emp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.emp.dao.LoginDaoImpl;
import com.emp.dao.SuperAdminDaoImpl;
import com.emp.entity.User;
import com.emp.service.LoginServiceImpl;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class LoginController {

	@Autowired(required=true)
	LoginServiceImpl loginServiceImpl;
	
	@PostMapping("/login" )
	 public ResponseEntity<User> Login(@RequestBody User user )
	 { 
		User status;  

      // Authenticate User.  
      status =  loginServiceImpl.userLogin(user.getEmailId(), user.getPassword());  
      
//		 System.out.println("test 1 "+ user);
		
//		 System.out.println("test status : " + status);
		 if (status != null)   
		 {
//			 System.out.println("if");
			 return new ResponseEntity<User>(status,  HttpStatus.OK);
				
		 }
		 else
		 {
//			 System.out.println("else tse");
			 return new ResponseEntity<User>(status,  HttpStatus.OK);
				
				
		 }
			
		
	 }
	

}
