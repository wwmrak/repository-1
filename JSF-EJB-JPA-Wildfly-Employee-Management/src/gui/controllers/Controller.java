package gui.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ejb.services.AccessDatabase;
import jpa.entities.PersonalInfo;

@ManagedBean
@ViewScoped
public class Controller implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name2;
	private String name;
	private String surname;
	private String address;
	private String education;
	private String department;

	private boolean isVisibleWholeTable;
	private boolean isVisiblePartialTable;
	List<PersonalInfo> listOfRecords;
	List<EmployeesPerDepartment> employeesPerDepartmentList;
	
	@EJB
	private AccessDatabase accessDatabaseObj;

	public String addRecord() {
		accessDatabaseObj.createRecord(name, surname, address, education, department);
		return "mainPage";
	}
	public String recordChange() {
		accessDatabaseObj.changeRecord(name, surname, address, education, department);
		return "mainPage";
	}
	public String deleteRecord() {
		accessDatabaseObj.deleteRecord(name, surname);
		return "mainPage";
	}
	public void wholeTableDisplay() {
		isVisibleWholeTable = true;
		isVisiblePartialTable = false;
		listOfRecords = accessDatabaseObj.selectWholeTable();
	}
	
	public void departmentsDisplay() {
		isVisibleWholeTable = false;
		isVisiblePartialTable = true;
		List<Integer> employeesPerDepartment = accessDatabaseObj.departmentsDisplay();
		employeesPerDepartmentList = new ArrayList<EmployeesPerDepartment>(Arrays.asList(new EmployeesPerDepartment(employeesPerDepartment.get(0), 
				employeesPerDepartment.get(1), employeesPerDepartment.get(2))));
	}
	
	public List<EmployeesPerDepartment> getEmployeesPerDepartmentList() {
		return employeesPerDepartmentList;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getName2() {
		return name2;
	}
	public void setName2(String name2) {
		this.name2 = name2;
	}
	public List<PersonalInfo> getListOfRecords() {
		return listOfRecords;
	}
	public void setListOfRecords(List<PersonalInfo> listOfRecords) {
		this.listOfRecords = listOfRecords;
	}
	public List<EmployeesPerDepartment> getEmployeesPerDepartmentArray() {
		return employeesPerDepartmentList;
	}
	public boolean isVisibleWholeTable() {
		return isVisibleWholeTable;
	}
	public void setVisibleWholeTable(boolean isVisibleWholeTable) {
		this.isVisibleWholeTable = isVisibleWholeTable;
	}
	public boolean isVisiblePartialTable() {
		return isVisiblePartialTable;
	}
	public void setVisiblePartialTable(boolean isVisiblePartialTable) {
		this.isVisiblePartialTable = isVisiblePartialTable;
	}
	
	public static class EmployeesPerDepartment {
		private int employeesInDevelopment;
		private int employeesInProduction;
		private int employeesInMarketing;

		public EmployeesPerDepartment(int employeesInDevelopment, int employeesInProduction, int employeesInMarketing) {
			this.employeesInDevelopment = employeesInDevelopment;
			this.employeesInProduction = employeesInProduction;
			this.employeesInMarketing = employeesInMarketing;
		}

		public int getEmployeesInDevelopment() {
			return employeesInDevelopment;
		}
		public void setEmployeesInDevelopment(int employeesInDevelopment) {
			this.employeesInDevelopment = employeesInDevelopment;
		}
		public int getEmployeesInProduction() {
			return employeesInProduction;
		}
		public void setEmployeesInProduction(int employeesInProduction) {
			this.employeesInProduction = employeesInProduction;
		}
		public int getEmployeesInMarketing() {
			return employeesInMarketing;
		}
		public void setEmployeesInMarketing(int employeesInMarketing) {
			this.employeesInMarketing = employeesInMarketing;
		}
	}
}