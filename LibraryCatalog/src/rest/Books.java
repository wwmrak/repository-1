package rest;

import java.util.List;

import javax.ejb.EJB;

/**
 * @author Crunchify.com
 */

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import ejb.services.QueryDatabase;
import jpa.entities.Book;

@Path("/books")
public class Books {
	
@EJB
QueryDatabase queryDatabase;

	@GET
	@Produces("application/xml")
	public List<Book> convertCtoF1() {
		System.out.println();
		List<Book> listAuthorsBooks = queryDatabase.getBooksList();
		if (listAuthorsBooks == null) throw new NullPointerException();
		return listAuthorsBooks;
	}

	@Path("{bookNumber}")
	@GET
	@Produces("application/xml")
	public Book convertCtoFfromInput1(@PathParam("bookNumber") int bookNumber) {
		List<Book> listAuthorsBooks = queryDatabase.getBooksList();
		return listAuthorsBooks.get(bookNumber);
	}
}