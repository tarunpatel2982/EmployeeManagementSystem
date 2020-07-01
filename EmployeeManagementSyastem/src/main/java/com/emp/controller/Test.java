package com.emp.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.emp.dao.implementation.UserDaoImplementation;
import com.emp.entity.User;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class Test {

	@Autowired
	UserDaoImplementation userDaoImplementation;
	@RequestMapping(value = "/",method = RequestMethod.GET)
	public boolean createAdmin()
	{
		boolean status = false;
		try {
			userDaoImplementation.createAdminUser();
			status = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		System.out.println( " test status  : " + status);
		return status;
		
	}
	@GetMapping("/edit/{AdminId}")
    public User editAdmin(@PathVariable("AdminId") String adminId ) {
            System.out.println("test" + adminId);
        return  userDaoImplementation.findUserId(adminId);
    }
  	
	@PutMapping("/update/{AdminId}")
    public Boolean updateAdmin(@PathVariable("AdminId") String adminId, @RequestBody User user ) {
            System.out.println("test" + adminId);
          
        return  userDaoImplementation.update(adminId, user);
    }
	@PostMapping("/login" )
	 public ResponseEntity<User> Login(@RequestBody User user )
	 { 
		User status;  

       // Authenticate User.  
       status =userDaoImplementation.adminLogin(user.getEmailId(), user.getPassword());  
       
		 System.out.println("test 1 "+ user);
//		 AdminDetail adminDetail2 = admin.adminLogin(adminDetail.getEmailId(),adminDetail.getPassword());
		
		 System.out.println("test status : " + status);
		 if (status != null)   
		 {
			 System.out.println("if");
			 return new ResponseEntity<User>(status,  HttpStatus.OK);
				
		 }
		 else
		 {
			 System.out.println("else tse");
			 return new ResponseEntity<User>(status,  HttpStatus.OK);
				
				
		 }
			
		
	 }
	
	
	
	 
}
