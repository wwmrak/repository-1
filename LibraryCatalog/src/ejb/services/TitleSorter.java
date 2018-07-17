package ejb.services;

import java.util.Comparator;
import jpa.entities.Book;

public class TitleSorter implements Comparator<Book> {

	public TitleSorter() {
	}

	public int compare(Book one, Book another) {
		return one.getBook().compareTo(another.getBook());
	}
}