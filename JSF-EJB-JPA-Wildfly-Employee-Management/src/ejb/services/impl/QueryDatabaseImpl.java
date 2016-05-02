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
import ejb.services.QueryDatabase;
import jpa.entities.OsobniPodaci;

@Stateless
public class QueryDatabaseImpl implements QueryDatabase {
	
	@PersistenceContext(name = "EmployeeApp")
	private EntityManager entitymanager;	
	
	public void createRecord(String ime, String prezime, String adresa, String obrazovanje, String odjel) {

		OsobniPodaci osobniPodaci = new OsobniPodaci();
		
		osobniPodaci.setIme(ime);
		osobniPodaci.setPrezime(prezime);
		osobniPodaci.setObrazovanje(obrazovanje);
		osobniPodaci.setAdresa(adresa);
		osobniPodaci.setOdjel(odjel);
		
		entitymanager.persist(osobniPodaci);
	}

	public void deleteRecord(String ime, String prezime) {
		entitymanager.createNamedQuery("deleteRecord").setParameter("ime", ime).setParameter("prezime", prezime)
			.executeUpdate();
	}
		
	public void changeRecord(String ime, String prezime, String adresa, String obrazovanje, String odjel) {
		CriteriaBuilder cb = entitymanager.getCriteriaBuilder();
		  
		// create update
		CriteriaUpdate<OsobniPodaci> update = cb.createCriteriaUpdate(OsobniPodaci.class);
		  
		// set the root class
		Root<OsobniPodaci> e = update.from(OsobniPodaci.class);
		  
		update.set("adresa", adresa);
		update.set("obrazovanje", obrazovanje);
		update.set("odjel", odjel);
		update.where(cb.equal(e.get("ime"), 
		     ime));
		update.where(cb.equal(e.get("prezime"), 
			     prezime));
		  
		entitymanager.createQuery(update).executeUpdate();
	}
	
	public List<OsobniPodaci> selectWholeTable() {
		
		@SuppressWarnings("unchecked")
		List<OsobniPodaci> listOfRecords = entitymanager.createNamedQuery("selectAll")
			.getResultList();

		return listOfRecords;
	}
	
	public List<Integer> divisionsDisplay() {
		int employeesInDevelopment = ((Number)entitymanager.createNamedQuery("employeesInDevelopment")
				.getSingleResult()).intValue();
		int employeesInProduction = ((Number)entitymanager.createNamedQuery("employeesInProduction")
				.getSingleResult()).intValue();
		int employeesInMarketing = ((Number)entitymanager.createNamedQuery("employeesInMarketing")
				.getSingleResult()).intValue();
		
	    return new ArrayList<Integer>(Arrays.asList(employeesInDevelopment, employeesInProduction, employeesInMarketing));
	}
}
