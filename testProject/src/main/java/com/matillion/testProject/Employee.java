package com.matillion.testProject;

/**
 * Class to represent Employee data
 * @author radhakrishnan
 *
 */
public class Employee {
	private int employeeId;
	private String fullName;
	private String positionTitle;
	private String department;
	private String EducationLevel;
	private String payType;
	private String managementRole;
	private double salary;
	
	@Override
	public String toString() {
		return this.fullName + "\t" + this.positionTitle + "\t" + this.department + "\t" + this.EducationLevel + "\t" + this.managementRole + "\t" + this.payType;
		
	}
	
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getPositionTitle() {
		return positionTitle;
	}
	public void setPositionTitle(String positionTitle) {
		this.positionTitle = positionTitle;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getEducationLevel() {
		return EducationLevel;
	}
	public void setEducationLevel(String educationLevel) {
		EducationLevel = educationLevel;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getManagementRole() {
		return managementRole;
	}
	public void setManagementRole(String managementRole) {
		this.managementRole = managementRole;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}

}
