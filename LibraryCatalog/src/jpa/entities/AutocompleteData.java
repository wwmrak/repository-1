package jpa.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "autocomplete_data")
@NamedQueries({
	@NamedQuery(name = "selectAutocompleteDataBooks", query = "SELECT a.book FROM AutocompleteData a"),
	@NamedQuery(name = "selectAutocompleteDataAuthors", query = "SELECT a.author FROM AutocompleteData a")
}) 
public class AutocompleteData {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	String author;
	String book;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
}
