package com.emp.dao.interfaces;

import javax.servlet.http.HttpSession;

import com.emp.entity.User;

public interface UserDao {

	
	
	
	
	public Boolean createAdminUser();
	public User findUserId(String adminId);
	public boolean update(String adminId,User user);
//	public Boolean addAdmin(User user);
//	public Boolean deleteUser(String id);
	public User adminLogin(String emailId , String password);  
      
}
