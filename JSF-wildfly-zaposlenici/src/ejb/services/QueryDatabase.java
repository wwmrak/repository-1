package ejb.services;

import java.util.List;
import jpa.entities.OsobniPodaci;

public interface QueryDatabase {
	
	public void createRecord(String ime, String prezime, String adresa, String obrazovanje, String odjel); 
	public void deleteRecord(String ime, String prezime);	
	public void changeRecord(String ime, String prezime, String adresa, String obrazovanje, String odjel);	
	public List<OsobniPodaci> selectWholeTable();	
	public List<Integer> divisionsDisplay();

}
