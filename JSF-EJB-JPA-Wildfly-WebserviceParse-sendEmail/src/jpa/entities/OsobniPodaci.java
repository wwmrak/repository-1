package jpa.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table
@NamedQueries({
	 @NamedQuery(name = "select rows with specific email", query = "SELECT o FROM OsobniPodaci o where o.email=:email"),
	 @NamedQuery(name = "select rows with specific email, name, surname", query = "SELECT o FROM OsobniPodaci o where "
	 		+ "o.email=:email and o.name=:name and o.prezime=:prezime"),
	 @NamedQuery(name = "select date", query = "SELECT o.date FROM OsobniPodaci o where "
		 		+ "o.email=:email and o.name=:name and o.prezime=:prezime"),
	 @NamedQuery(name = "update date in row", query = "UPDATE OsobniPodaci o SET o.date = current_timestamp  WHERE o.email=:email"),
	 @NamedQuery(name = "select * from table", query = "select o from OsobniPodaci o")
}) 
public class OsobniPodaci {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id; 
	String name;
	String prezime;
		
	String email;
	
	String username;
	
	String ulica;
	String apartman;
	String grad;	
	String postanskaAdresa;
	String geoLat;
	String geoLng;
	
	String brojTelefona;
	String webStranica;
	String imeTvrtke;
	String catchePhrase;
	String bs;
	
	@Column(name = "timeStamp", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	Date date;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUlica() {
		return ulica;
	}
	public void setUlica(String ulica) {
		this.ulica = ulica;
	}
	public String getApartman() {
		return apartman;
	}
	public void setApartman(String apartman) {
		this.apartman = apartman;
	}
	public String getGrad() {
		return grad;
	}
	public void setGrad(String grad) {
		this.grad = grad;
	}
	public String getPostanskaAdresa() {
		return postanskaAdresa;
	}
	public void setPostanskaAdresa(String postanskaAdresa) {
		this.postanskaAdresa = postanskaAdresa;
	}
	public String getGeoLat() {
		return geoLat;
	}
	public void setGeoLat(String geoLat) {
		this.geoLat = geoLat;
	}
	public String getGeoLng() {
		return geoLng;
	}
	public void setGeoLng(String geoLng) {
		this.geoLng = geoLng;
	}
	public String getBrojTelefona() {
		return brojTelefona;
	}
	public void setBrojTelefona(String brojTelefona) {
		this.brojTelefona = brojTelefona;
	}
	public String getWebStranica() {
		return webStranica;
	}
	public void setWebStranica(String webStranica) {
		this.webStranica = webStranica;
	}
	public String getImeTvrtke() {
		return imeTvrtke;
	}
	public void setImeTvrtke(String imeTvrtke) {
		this.imeTvrtke = imeTvrtke;
	}
	public String getCatchePhrase() {
		return catchePhrase;
	}
	public void setCatchePhrase(String catchePhrase) {
		this.catchePhrase = catchePhrase;
	}
	public String getBs() {
		return bs;
	}
	public void setBs(String bs) {
		this.bs = bs;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
