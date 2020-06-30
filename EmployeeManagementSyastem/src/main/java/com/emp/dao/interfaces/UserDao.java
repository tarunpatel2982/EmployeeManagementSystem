package com.emp.dao.interfaces;

import javax.servlet.http.HttpSession;

import com.emp.entity.Admin;

public interface UserDao {

	
	
	
	
	public Boolean createAdminUser();
	public Admin findUserId(String adminId);
	public boolean update(String adminId,Admin admin);
	public Boolean addAdmin(Admin admin);
//	public Boolean deleteUser(String id);
	public String adminLogin(String emailId , String password);  
      
}
