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
	 @NamedQuery(name = "selectRowsWithSpecificEmailNameAndSurname", query = "SELECT p FROM PersonalInfo p where "
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
	String suite;
	String city;	
	String zipcode;
	String lat;
	String lng;	
	String phone;
	String website;
	String companyName;
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
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
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
	public String getSuite() {
		return suite;
	}
	public void setSuite(String suite) {
		this.suite = suite;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
}
