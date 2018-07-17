package ejb.services;

import java.util.Comparator;
import jpa.entities.Book;

public class YearSorter implements Comparator<Book> {

	public int compare(Book one, Book another) {
		int returnVal = 0;

		if (Integer.parseInt(one.getYear()) < Integer.parseInt(another.getYear())) {
			returnVal = -1;
		} else if (Integer.parseInt(one.getYear()) > Integer.parseInt(another.getYear())) {
			returnVal = 1;
		} else if (Integer.parseInt(one.getYear()) == Integer.parseInt(another.getYear())) {
			returnVal = 0;
		}
		return returnVal;
	}
}