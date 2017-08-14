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
	 @NamedQuery(name = "selectRowsWithSpecificEmail", query = "SELECT p FROM PersonalInfo p where p.email=:email"),
	 @NamedQuery(name = "selectRowsWithSpecificEmaiNameAndSurname", query = "SELECT p FROM PersonalInfo p where "
	 		+ "p.email=:email and p.name=:name and p.surname=:surname"),
	 @NamedQuery(name = "selectDate", query = "SELECT p.date FROM PersonalInfo p where "
		 		+ "p.email=:email and p.name=:name and p.surname=:surname"),
	 @NamedQuery(name = "updateDateInRow", query = "UPDATE PersonalInfo p SET p.date = current_timestamp  WHERE p.email=:email"),
	 @NamedQuery(name = "selectEverythingFromTable", query = "select p from PersonalInfo p")
}) 
public class PersonalInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id; 
	String name;
	String surname;		
	String email;	
	String username;	
	String street;
	String apartment;
	String city;	
	String postNumber;
	String geoLat;
	String geoLng;	
	String telephone;
	String webPage;
	String company;
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getApartment() {
		return apartment;
	}
	public void setApartment(String apartment) {
		this.apartment = apartment;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPostNumber() {
		return postNumber;
	}
	public void setPostNumber(String postNumber) {
		this.postNumber = postNumber;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getWebPage() {
		return webPage;
	}
	public void setWebPage(String webPage) {
		this.webPage = webPage;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
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
