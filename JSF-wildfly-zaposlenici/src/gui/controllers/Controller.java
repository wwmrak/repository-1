package gui.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ejb.services.QueryDatabase;
import jpa.entities.OsobniPodaci;

@ManagedBean
@ViewScoped
public class Controller implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String ime2;
	
	private String ime;
	private String prezime;
	private String adresa;
	private String obrazovanje;
	private String odjel;

	List<OsobniPodaci> listOfRecords;
	List<EmployeesPerDepartment> employeesPerDepartmentList;
	
	private boolean isVisibleWholeTable;
	private boolean isVisiblePartialTable;
	
	@EJB
	private QueryDatabase queryDatabase;

	public String addRecord() {
		queryDatabase.createRecord(ime, prezime, adresa, obrazovanje, odjel);
		return "mainPage";
	}

	public String recordChange() {
		queryDatabase.changeRecord(ime, prezime, adresa, obrazovanje, odjel);
		return "mainPage";
	}

	public String deleteRecord() {
		queryDatabase.deleteRecord(ime, prezime);
		return "mainPage";
	}

	public void wholeTableDisplay() {
		isVisibleWholeTable = true;
		isVisiblePartialTable = false;
		listOfRecords = queryDatabase.selectWholeTable();
	}

	public void divisionsDisplay() {
		
		isVisibleWholeTable = false;
		isVisiblePartialTable = true;
		List<Integer> employeesPerDepartment = queryDatabase.divisionsDisplay();
		employeesPerDepartmentList = new ArrayList<EmployeesPerDepartment>(Arrays.asList(new EmployeesPerDepartment(employeesPerDepartment.get(0), 
				employeesPerDepartment.get(1), employeesPerDepartment.get(2))));
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
	
	public List<EmployeesPerDepartment> getEmployeesPerDepartmentList() {
		return employeesPerDepartmentList;
	}
	
	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getObrazovanje() {
		return obrazovanje;
	}

	public void setObrazovanje(String obrazovanje) {
		this.obrazovanje = obrazovanje;
	}

	public String getOdjel() {
		return odjel;
	}

	public void setOdjel(String odjel) {
		this.odjel = odjel;
	}

	public List<OsobniPodaci> getListOfRecords() {
		return listOfRecords;
	}

	public void setListOfRecords(List<OsobniPodaci> listOfRecords) {
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

	public String getIme2() {
		return ime2;
	}

	public void setIme2(String ime2) {
		this.ime2 = ime2;
	}
}