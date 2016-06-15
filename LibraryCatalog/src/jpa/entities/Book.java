package jpa.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.persistence.NamedQueries;

@XmlRootElement
@Entity
@Table
@NamedQueries({
	@NamedQuery(name = "selectAuthorsBooks", query = "SELECT b FROM Book b WHERE b.author =:author"),
	@NamedQuery(name = "selectAuthorsBooks2", query = "SELECT b FROM Book b WHERE b.author =:author")
}) 
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	String id;
	String author;

	String book;
	String year;
	String publisher;
	String language;
	String city;

	@Column(name = "domestic_or_foreign")
	String domesticOrForeign;
	@Column(name = "content_type")
	String contentType;
	String status;
	@Column(name = "public_type")
	String public_type;
	String classification;
	String edition;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getBook() {
		return book;
	}

	public void setBook(String book) {
		this.book = book;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDomesticOrForeign() {
		return domesticOrForeign;
	}

	public void setDomesticOrForeign(String domesticOrForeign) {
		this.domesticOrForeign = domesticOrForeign;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getPublic_type() {
		return public_type;
	}

	public void setPublic_type(String public_type) {
		this.public_type = public_type;
	}

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}
}