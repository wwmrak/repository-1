package ejb.services;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "autoriService", eager = true)
@ApplicationScoped
public class AuthorService {

	private String vrstaSearcha;

	private List<Author> knjige;
	private List<Author> autori;
	private List<Author> autoriKnjige;
	
	@EJB
	QueryDatabase queryDatabase;

	@PostConstruct
	public void init() {
//		 autori = new ArrayList<Author>();
//	        autori.add(new Author(0, "Dante, Alighieri", "Dante, Alighieri"));
//	        autori.add(new Author(1, "Andersen, Hans Christian", "Andersen, Hans Christian"));
//	        autori.add(new Author(2, "Balzac, Honore", "Balzac, Honore"));
//	        autori.add(new Author(3, "Dostoyevsky, Fyodor", "Dostoyevsky, Fyodor"));
//	        autori.add(new Author(4, "Hemingway, Ernest", "Hemingway, Ernest"));
//	        autori.add(new Author(5, "Joyce, James", "Joyce, James"));
//	        autori.add(new Author(6, "Shakespeare, William", "Shakespeare, William"));
//	        autori.add(new Author(7, "Tolstoj, Lav Nikolajevic", "Tolstoj, Lav Nikolajevic"));
//	        autori.add(new Author(8, "Hughes, John", "Hughes, John"));
//	        autori.add(new Author(9, "Hugo, Victor", "Hugo, Victor"));
//	        autori.add(new Author(10, "Horace", "Horace"));
//	        autori.add(new Author(11, "Hess, Hermann", "Hess, Hermann"));
//	        autori.add(new Author(12, "Hobb, Robin", "Hobb, Robin"));
//	        autori.add(new Author(13, "Tolkien, J.R.R.", "Tolkien, J.R.R."));
//	        autori.add(new Author(14, "Tate, James", "Tate, James"));
//	        autori.add(new Author(15, "Diogenes", "Diogenes"));
//	        autori.add(new Author(16, "Doyle, Arthur Conan", "Doyle, Arthur Conan"));
//	        autori.add(new Author(17, "Dumas, Alexandre", "Dumas, Alexandre"));
//	        autori.add(new Author(18, "Dickens, Charles", "Dickens, Charles"));
//	        autori.add(new Author(19, "Solzenitsyn, Alexander", "Solzenitsyn, Alexander"));
//	        autori.add(new Author(20, "Stephens, James", "Stephens, James"));
//	        autori.add(new Author(21, "Salinger, J.D.", "Salinger, J.D."));
//	        
//	        
//	        knjige = new ArrayList<Author>();
//	        knjige.add(new Author(0, "Diamond Eyes", "Diamond Eyes"));
//	        knjige.add(new Author(1, "Father Goriot", "Father Goriot"));
//	        knjige.add(new Author(2, "City of Bones", "City of Bones"));
//	        knjige.add(new Author(3, "Marked", "Marked"));
//	        knjige.add(new Author(4, "Da Vinci Code", "Da Vinci Code"));
//	        knjige.add(new Author(5, "Idiot", "Idiot"));
//	        knjige.add(new Author(6, "Medea", "Medea"));
//	        knjige.add(new Author(7, "Midnight Sun", "Midnight Sun"));
//	        knjige.add(new Author(8, "Little House on the Prairie", "Little House on the Prairie"));
//	        knjige.add(new Author(9, "Legend", "Legend"));
//	        knjige.add(new Author(10, "Legend", "Legend"));
//	        knjige.add(new Author(11, "Defiance", "Defiance"));
//	        knjige.add(new Author(12, "Deadly Design", "Deadly Design"));
//	        knjige.add(new Author(13, "Divergent", "Divergent"));
//	        knjige.add(new Author(14, "Schindler's List", "Schindler's List"));
//	        knjige.add(new Author(15, "Silence", "Silence"));
//	        knjige.add(new Author(16, "Summoning", "Summoning"));      
//
//	        
//	        
//	        autoriKnjige = new ArrayList<Author>();
//	        autoriKnjige.add(new Author(0, "Dante, Alighieri", "Dante, Alighieri"));
//	        autoriKnjige.add(new Author(1, "Andersen, Hans Christian", "Andersen, Hans Christian"));
//	        autoriKnjige.add(new Author(2, "Balzac, Honore", "Balzac, Honore"));
//	        autoriKnjige.add(new Author(3, "Dostoyevsky, Fyodor", "Dostoyevsky, Fyodor"));
//	        autoriKnjige.add(new Author(4, "Hemingway, Ernest", "Hemingway, Ernest"));
//	        autoriKnjige.add(new Author(5, "Joyce, James", "Joyce, James"));
//	        autoriKnjige.add(new Author(6, "Shakespeare, William", "Shakespeare, William"));
//	        autoriKnjige.add(new Author(7, "Tolstoj, Lav Nikolajevic", "Tolstoj, Lav Nikolajevic"));
//	        autoriKnjige.add(new Author(8, "Hughes, John", "Hughes, John"));
//	        autoriKnjige.add(new Author(9, "Hugo, Victor", "Hugo, Victor"));
//	        autoriKnjige.add(new Author(10, "Horace", "Horace"));
//	        autoriKnjige.add(new Author(11, "Hess, Hermann", "Hess, Hermann"));
//	        autoriKnjige.add(new Author(12, "Hobb, Robin", "Hobb, Robin"));
//	        autoriKnjige.add(new Author(13, "Tolkien, J.R.R.", "Tolkien, J.R.R."));
//	        autoriKnjige.add(new Author(14, "Tate, James", "Tate, James"));
//	        autoriKnjige.add(new Author(15, "Diogenes", "Diogenes"));
//	        autoriKnjige.add(new Author(16, "Doyle, Arthur Conan", "Doyle, Arthur Conan"));
//	        autoriKnjige.add(new Author(17, "Dumas, Alexandre", "Dumas, Alexandre"));
//	        autoriKnjige.add(new Author(18, "Dickens, Charles", "Dickens, Charles"));
//	        autoriKnjige.add(new Author(19, "Solzenitsyn, Alexander", "Solzenitsyn, Alexander"));
//	        autoriKnjige.add(new Author(20, "Stephens, James", "Stephens, James"));
//	        autoriKnjige.add(new Author(21, "Salinger, J.D.", "Salinger, J.D."));
//	        autoriKnjige.add(new Author(22, "Diamond Eyes", "Diamond Eyes"));
//	        autoriKnjige.add(new Author(23, "Father Goriot", "Father Goriot"));
//	        autoriKnjige.add(new Author(24, "City of Bones", "City of Bones"));
//	        autoriKnjige.add(new Author(25, "Marked", "Marked"));
//	        autoriKnjige.add(new Author(26, "Da Vinci Code", "Da Vinci Code"));
//	        autoriKnjige.add(new Author(27, "Idiot", "Idiot"));
//	        autoriKnjige.add(new Author(28, "Medea", "Medea"));
//	        autoriKnjige.add(new Author(29, "Midnight Sun", "Midnight Sun"));
//	        autoriKnjige.add(new Author(30, "Little House on the Prairie", "Little House on the Prairie"));
//	        autoriKnjige.add(new Author(31, "Legend", "Legend"));
//	        autoriKnjige.add(new Author(32, "Legend", "Legend"));
//	        autoriKnjige.add(new Author(33, "Defiance", "Defiance"));
//	        autoriKnjige.add(new Author(34, "Deadly Design", "Deadly Design"));
//	        autoriKnjige.add(new Author(35, "Divergent", "Divergent"));
//	        autoriKnjige.add(new Author(36, "Schindler's List", "Schindler's List"));
//	        autoriKnjige.add(new Author(37, "Silence", "Silence"));
//	        autoriKnjige.add(new Author(38, "Summoning", "Summoning"));
		ArrayList<List<String>> autocompleteDataLists = queryDatabase.getAutocompleteData();
		
		List<String> autocompleteDataBooks = autocompleteDataLists.get(0);
		List<String> autocompleteDataAuthors = autocompleteDataLists.get(1);
		
		autoriKnjige = new ArrayList<Author>();
		autori = new ArrayList<Author>();
		int authorAndBookCount = 0;
		int authorCount = 0;
		for (String author : autocompleteDataAuthors) {
			if (author != null) {
				autori.add(new Author(authorCount, author, author));
				authorCount++;
				autoriKnjige.add(new Author(authorAndBookCount, author, author));
				authorAndBookCount++;
			}
		}
		
		knjige = new ArrayList<Author>();
		int bookCount = 0;
		for (String book : autocompleteDataBooks) {
			if (book != null) {
				knjige.add(new Author(bookCount, book, book));
				bookCount++;
				autoriKnjige.add(new Author(authorAndBookCount, book, book));
				authorAndBookCount++;
			}
		}
	}

	public List<Author> getAutori() {
		return autori;
	}

	public void setAutori(List<Author> autori) {
		this.autori = autori;
	}

	public List<Author> getKnjige() {
		return knjige;
	}

	public void setKnjige(List<Author> knjige) {
		this.knjige = knjige;
	}

	public List<Author> getAutoriIKnjige() {
		return autoriKnjige;
	}

	public void setAutoriKnjige(List<Author> autoriKnjige) {
		this.autoriKnjige = autoriKnjige;
	}

	public String getVrstaSearcha() {
		return vrstaSearcha;
	}

	public void setVrstaSearcha(String vrstaSearcha) {
		this.vrstaSearcha = vrstaSearcha;
	}
}