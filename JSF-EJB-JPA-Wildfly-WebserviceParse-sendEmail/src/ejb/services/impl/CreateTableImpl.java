package ejb.services.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import jpa.entities.OsobniPodaci;


@Stateless
public class CreateTableImpl {
	
	@PersistenceContext(name = "EmployeeApp")
	private EntityManager entitymanager;

	public boolean checkSameMailDifferentNameDB(List<String> listPodaciOsobeIzForme) {

		System.out.println();
		@SuppressWarnings("unchecked")
		List<Integer> rowsWithSpecificEmailList = entitymanager.createNamedQuery("select rows with specific email")
				.setParameter("email", listPodaciOsobeIzForme.get(2)).getResultList();

		int count = (int) rowsWithSpecificEmailList.size();

		if (count >= 1) {

			@SuppressWarnings("unchecked")
			List<Integer> rowsWithSpecificNameEmail = entitymanager
					.createNamedQuery("select rows with specific email, name, surname")
					.setParameter("name", listPodaciOsobeIzForme.get(0))
					.setParameter("prezime", listPodaciOsobeIzForme.get(1))
					.setParameter("email", listPodaciOsobeIzForme.get(2)).getResultList();

			int count1 = (int) rowsWithSpecificNameEmail.size();

			if (count1 == 0)
				return true;
		}
		return false;
	}
	
	
	public String checkDaliOsobaUTabliciIVrijeme(List<String> listPodaciOsobeIzForme) {

		@SuppressWarnings("unchecked")
		List<Integer> selectRows1 = entitymanager
				.createNamedQuery("select rows with specific email, name, surname")
				.setParameter("name", listPodaciOsobeIzForme.get(0))
				.setParameter("prezime", listPodaciOsobeIzForme.get(1))
				.setParameter("email", listPodaciOsobeIzForme.get(2)).getResultList();

		int count1 = (int) selectRows1.size();

		if (count1 == 0)
			return "osoba nije u tablici";

		@SuppressWarnings("unchecked")
		List<Date> dateList = (List<Date>) entitymanager.createNamedQuery("select date")
				.setParameter("name", listPodaciOsobeIzForme.get(0))
				.setParameter("prezime", listPodaciOsobeIzForme.get(1))
				.setParameter("email", listPodaciOsobeIzForme.get(2)).getResultList();

		System.out.println(new Date().getTime() / 1000 / 60);
		System.out.println(dateList.get(0).getTime() / 1000 / 60);

		int diff = (int) ((new Date().getTime() / 1000 / 60 - dateList.get(0).getTime() / 1000 / 60));
		if (diff < 60)
			return "nije proslo sat vremena";
		else {
			entitymanager.createNamedQuery("update date in row").setParameter("email", listPodaciOsobeIzForme.get(2))
					.executeUpdate();
		}
		return "proslo sat vremena i osoba u tablici";
	}
	
	public void createRecordOsobniPodaci(List<String> podaciOJednojOsobi) {

		OsobniPodaci osobniPodaci = new OsobniPodaci();

		osobniPodaci.setName(podaciOJednojOsobi.get(0));
		osobniPodaci.setPrezime(podaciOJednojOsobi.get(1));
		osobniPodaci.setEmail(podaciOJednojOsobi.get(2));

		if (podaciOJednojOsobi.size() > 3) {
			osobniPodaci.setUsername(podaciOJednojOsobi.get(3));
			osobniPodaci.setUlica(podaciOJednojOsobi.get(4));
			osobniPodaci.setApartman(podaciOJednojOsobi.get(5));
			osobniPodaci.setGrad(podaciOJednojOsobi.get(6));
			osobniPodaci.setPostanskaAdresa(podaciOJednojOsobi.get(7));
			osobniPodaci.setGeoLat(podaciOJednojOsobi.get(8));
			osobniPodaci.setGeoLng(podaciOJednojOsobi.get(9));
			osobniPodaci.setBrojTelefona(podaciOJednojOsobi.get(10));
			osobniPodaci.setWebStranica(podaciOJednojOsobi.get(11));
			osobniPodaci.setImeTvrtke(podaciOJednojOsobi.get(12));
			osobniPodaci.setCatchePhrase(podaciOJednojOsobi.get(13));
			osobniPodaci.setBs(podaciOJednojOsobi.get(14));
		}
		entitymanager.persist(osobniPodaci);
	}

	public List<OsobniPodaci> selectAllFromTable() {
		
		@SuppressWarnings("unchecked")
		List<OsobniPodaci> listOfRecords = entitymanager.createNamedQuery("select * from table")
			.getResultList();
		
		return listOfRecords;
		
	}

}
