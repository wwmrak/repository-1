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
	@NamedQuery(name = "deleteRecord", query = "DELETE from OsobniPodaci o WHERE o.ime =:ime and o.prezime=:prezime"),
	@NamedQuery(name = "selectAll", query = "SELECT o from OsobniPodaci o"),
	@NamedQuery(name = "employeesInDevelopment", query = "SELECT COUNT(o.odjel) FROM OsobniPodaci o WHERE o.odjel='Razvoj'"),
	@NamedQuery(name = "employeesInProduction", query = "SELECT COUNT(o.odjel) FROM OsobniPodaci o WHERE o.odjel='Proizvodnja'"),
	@NamedQuery(name = "employeesInMarketing", query = "SELECT COUNT(o.odjel) FROM OsobniPodaci o WHERE o.odjel='Marketing'"),
}) 
public class OsobniPodaci {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id; 
	String ime;
	String prezime;
	String adresa;
	String obrazovanje;
	String odjel;
	
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getOdjel() {
		return odjel;
	}
	public void setOdjel(String odjel) {
		this.odjel = odjel;
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
	
}
