package com.emp.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;

public class EmployeeWorkDetail {

	
	@Id
	private String id;
	private String workID;
	private String employeeId;
	private String workDate;
	private String workDetail;
	private String workFeedBack;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getWorkID() {
		return workID;
	}
	public void setWorkID(String workID) {
		this.workID = workID;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	
	public String getWorkDate() {
		return workDate;
	}
	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}
	public String getWorkDetail() {
		return workDetail;
	}
	public void setWorkDetail(String workDetail) {
		this.workDetail = workDetail;
	}
	public String getWorkFeedBack() {
		return workFeedBack;
	}
	public void setWorkFeedBack(String workFeedBack) {
		this.workFeedBack = workFeedBack;
	}
	
	
	
	
}
