package com.emp.service;

import java.util.List;

import com.emp.entity.EmployeeWorkDetail;
import com.emp.entity.User;

public interface AdminService {

	 public Boolean addAdmin(User user);
	 public List<User> getAdminList();
	 public Boolean deleteAdmin(String userId) ;

		public List<EmployeeWorkDetail> getWorkDetailForAdmin();
		public EmployeeWorkDetail findWorkId(String workID);
		public boolean employeeWorkFeedBack(EmployeeWorkDetail employeeWorkDetail);
}
