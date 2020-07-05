package com.emp.dao;

import javax.swing.text.StyledEditorKit.BoldAction;

import com.emp.entity.User;

public interface LoginDao {

	
	public User userLogin(String emailId, String password);
	
}
