package ejb.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import ejb.services.AccessDatabase;
import jpa.entities.PersonalInfo;

@Stateless
public class AccessDatabaseImpl implements AccessDatabase {
	@PersistenceContext(name = "EmployeeManagementUnit")
	private EntityManager entitymanager;	
	
	public void createRecord(String name, String surname, String address, String education, String department) {
		PersonalInfo persInfoObj = new PersonalInfo();
		persInfoObj.setIme(name);
		persInfoObj.setPrezime(surname);
		persInfoObj.setObrazovanje(education);
		persInfoObj.setAdresa(address);
		persInfoObj.setOdjel(department);
		
		entitymanager.persist(persInfoObj);
	}

	public void deleteRecord(String name, String surname) {
		entitymanager.createNamedQuery("deleteRecord").setParameter("name", name).setParameter("surname", surname)
			.executeUpdate();
	}
		
	public void changeRecord(String name, String surname, String address, String education, String department) {
		CriteriaBuilder criteriaBuilderObj = entitymanager.getCriteriaBuilder();
		CriteriaUpdate<PersonalInfo> criteriaUpdateObj = criteriaBuilderObj.createCriteriaUpdate(PersonalInfo.class);
		Root<PersonalInfo> e = criteriaUpdateObj.from(PersonalInfo.class);
		  
		criteriaUpdateObj.set("address", address);
		criteriaUpdateObj.set("education", education);
		criteriaUpdateObj.set("department", department);
		criteriaUpdateObj.where(criteriaBuilderObj.equal(e.get("name"), 
		     name));
		criteriaUpdateObj.where(criteriaBuilderObj.equal(e.get("surname"), 
			     surname));
		  
		entitymanager.createQuery(criteriaUpdateObj).executeUpdate();
	}
	
	public List<PersonalInfo> selectWholeTable() {
		@SuppressWarnings("unchecked")
		List<PersonalInfo> listOfRecords = entitymanager.createNamedQuery("selectAll")
			.getResultList();

		return listOfRecords;
	}
	
	public List<Integer> departmentsDisplay() {
		int employeesInDevelopment = ((Number)entitymanager.createNamedQuery("employeesInDevelopment")
				.getSingleResult()).intValue();
		int employeesInProduction = ((Number)entitymanager.createNamedQuery("employeesInProduction")
				.getSingleResult()).intValue();
		int employeesInMarketing = ((Number)entitymanager.createNamedQuery("employeesInMarketing")
				.getSingleResult()).intValue();
		
	    return new ArrayList<Integer>(Arrays.asList(employeesInDevelopment, employeesInProduction, employeesInMarketing));
	}
}
