package main;

import java.sql.*;

public class Admin {
	private String fullName;
	private String curClassName;
	
	private ResultSet curClass;
	private ResultSet adminClasses;
	private ResultSet currStudents;
	
	public Admin (String fullName) {
		this.fullName = fullName;
	}
	
	public void setAdminClasses(ResultSet rs) {
		adminClasses = rs;
	}
	
	public void setCurClass(String curClass) {
		this.curClassName = curClass;
	}
	
	public String getCurClassName() {
		return curClassName;
	}
	
	public void setCurStudents(ResultSet rs) {
		currStudents = rs;
	}
	
	public ResultSet getCurStudents() {
		return currStudents;
	}
	
	public ResultSet getAdminClasses() {
		return adminClasses;
	}
	
	public String getFullName() {
		return fullName;
	}
	
	public void logOut() {
		fullName = null;
		
		adminClasses = null;
	}
}
