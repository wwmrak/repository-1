package ejb.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import jpa.entities.Book;
import jpa.entities.BookInfo;

@Singleton
public class QueryDatabase {

	@PersistenceContext(name = "lms")
	private EntityManager entityManager;
	List<Book> booksList = new ArrayList<Book>();

	public List<Book> selectAuthorsBooks(String authorsName, String isBookBorrowed, String bookOrCollection) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Book> criteria = builder.createQuery(Book.class);
		Root<Book> root1 = criteria.from(Book.class);

		criteria.select(root1);
		if (isBookBorrowed == null)
			isBookBorrowed = "%";
		if (bookOrCollection == null)
			bookOrCollection = "%";

		criteria.where((builder.like(root1.get("status"), isBookBorrowed)),
				(builder.like(root1.get("author"), authorsName)),
				(builder.like(root1.get("contentType"), bookOrCollection)));

		booksList.clear();
		booksList = entityManager.createQuery(criteria).getResultList();
		return booksList;
	}
	
	public List<BookInfo> selectBookInfo(String book) {
		@SuppressWarnings("unchecked")
		List<BookInfo> bookInfoList = entityManager.createNamedQuery("selectBookInfo").setParameter("book", book)
				.getResultList();
		return bookInfoList;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<List<String>> getAutocompleteData() {
		List<String> autocompleteDataAuthors = entityManager.createNamedQuery("selectAutocompleteDataAuthors").getResultList();
		List<String> autocompleteDataBooks = entityManager.createNamedQuery("selectAutocompleteDataBooks").getResultList();
		
		return new ArrayList<List<String>>(Arrays.asList(autocompleteDataBooks, autocompleteDataAuthors));
	}
	
	public List<Book> getBooksList() {
		return booksList;
	}

	public void setBooksList(List<Book> booksList) {
		this.booksList = booksList;
	}
}
