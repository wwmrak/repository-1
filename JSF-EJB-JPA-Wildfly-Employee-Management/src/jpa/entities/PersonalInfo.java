package jpa.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
@Entity
@Table
@NamedQueries({
	@NamedQuery(name = "deleteRecord", query = "DELETE from PersonalInfo p WHERE p.name =:name and p.surname=:surname"),
	@NamedQuery(name = "selectAll", query = "SELECT p from PersonalInfo p"),
	@NamedQuery(name = "selectEmployeesInDevelopment", query = "SELECT COUNT(p.department) FROM PersonalInfo p WHERE p.department='Development'"),
	@NamedQuery(name = "selectEmployeesInProduction", query = "SELECT COUNT(p.department) FROM PersonalInfo p WHERE p.department='Production'"),
	@NamedQuery(name = "selectEmployeesInMarketing", query = "SELECT COUNT(p.department) FROM PersonalInfo p WHERE p.department='Marketing'"),
}) 

public class PersonalInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id; 
	String name;
	String surname;
	String address;
	String education;
	String department;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
}
