package com.emp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.emp.dao.SuperAdminDaoImpl;
import com.emp.service.SuperAdminServiceImpl;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class SuperAdminController {

	
	@Autowired(required=true)
	SuperAdminServiceImpl superAdminServiceImpl;
	@RequestMapping(value = "/",method = RequestMethod.GET)
	public boolean createSuperAdminUser()
	{
		boolean status = false;
		try {
			superAdminServiceImpl.createSuperAdminUser();
			status = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		System.out.println( " test status  : " + status);
		return status;
		
	}
	
}
