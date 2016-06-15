
package gui.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.event.ActionEvent;

import ejb.services.Author;
import ejb.services.AuthorService;
import ejb.services.QueryDatabase;
import ejb.services.TitleSorter;
import ejb.services.YearSorter;
import jpa.entities.Book;
import jpa.entities.BookInfo;

@ManagedBean(eager = true)
@ViewScoped
public class CatalogController {

	private Author autor1;
	String vrstaSearcha;
	private boolean accordianRender;
	private boolean datalistRender;

	private String isBookBorrowed;
	private String bookOrCollection;
	private String menuListingSelect;
	private boolean menuListingView;
	private int rowsNumber;

	@ManagedProperty("#{autoriService}")
	private AuthorService service;

	@EJB
	QueryDatabase queryDatabase;

	@PostConstruct
	public void init1() {
		vrstaSearcha = "katalog";
	}

	public void selectMenuItem() {
		if (menuListingSelect == null)
			return;

		if (menuListingSelect.equals("default"))
			commandButtonClick();
		else if (menuListingSelect.equals("title"))
			Collections.sort(listAuthorsBooks, new TitleSorter());
		else if (menuListingSelect.equals("year"))
			Collections.sort(listAuthorsBooks, new YearSorter());
	}

	public void emptyMethod() {
	}

	public List<Author> completeAutorKnjiga(String query) {
		List<Author> sviAutori = null;
		if (vrstaSearcha.equals("katalog")) {
			sviAutori = service.getAutoriIKnjige();
		} else if (vrstaSearcha.equals("autori")) {
			sviAutori = service.getAutori();
		}
		if (vrstaSearcha.equals("knjige")) {
			sviAutori = service.getKnjige();
		}

		List<Author> filtriraniAutori = new ArrayList<Author>();

		for (int i = 0; i < sviAutori.size(); i++) {
			Author skin = sviAutori.get(i);
			if (skin.getName().toLowerCase().startsWith(query)) {
				filtriraniAutori.add(skin);
			}
		}
		return filtriraniAutori;
	}

	public void checkBorrowed(ActionEvent event) {
		UIComponent component = (UIComponent) event.getSource();
		String value = (String) component.getAttributes().get("value");

		if (value.equals("Borrowed")) {
			isBookBorrowed = "Borrowed";
		} else if (value.equals("Not borrowed")) {
			isBookBorrowed = "Not borrowed";
		}
		commandButtonClick();
	}

	public void checkBookOrCollection(ActionEvent event) {
		UIComponent component = (UIComponent) event.getSource();
		String value = (String) component.getAttributes().get("value");

		if (value.equals("Book")) {
			bookOrCollection = "Book";
		} else if (value.equals("Collection")) {
			bookOrCollection = "Collection";
		}
		commandButtonClick();
	}

	List<Book> listAuthorsBooks = new ArrayList<Book>();
	List<String> listOfYears = new ArrayList<String>();
	List<String> listOfPublishers = new ArrayList<String>();
	List<String> listOfDemographicsClass = new ArrayList<String>();
	List<String> listOfStatuses = new ArrayList<String>();
	List<String> listOfLanguages = new ArrayList<String>();
	List<String> listOfPublishingCities = new ArrayList<String>();
	List<String> domesticVsForeignLiterature = new ArrayList<String>();
	List<String> listOfContentsClass = new ArrayList<String>();

	public void commandButtonClick() {
		listOfYears.clear();
		listOfPublishers.clear();
		listOfDemographicsClass.clear();
		listOfStatuses.clear();
		listOfLanguages.clear();
		listOfPublishingCities.clear();
		domesticVsForeignLiterature.clear();
		listOfContentsClass.clear();

		listAuthorsBooks = queryDatabase.selectAuthorsBooks(autor1.getName(), isBookBorrowed, bookOrCollection);
		rowsNumber = listAuthorsBooks.size();

		for (Book bookOfAuthor : listAuthorsBooks) {
			if (!listOfYears.contains(bookOfAuthor.getYear()))
				listOfYears.add(bookOfAuthor.getYear());
			if (!listOfPublishingCities.contains(bookOfAuthor.getCity()))
				listOfPublishingCities.add(bookOfAuthor.getCity());
			if (!listOfStatuses.contains(bookOfAuthor.getStatus()))
				listOfStatuses.add(bookOfAuthor.getStatus());
			if (!listOfPublishers.contains(bookOfAuthor.getPublisher()))
				listOfPublishers.add(bookOfAuthor.getPublisher());
			if (!listOfLanguages.contains(bookOfAuthor.getLanguage()))
				listOfLanguages.add(bookOfAuthor.getLanguage());
			if (!listOfDemographicsClass.contains(bookOfAuthor.getPublic_type()))
				listOfDemographicsClass.add(bookOfAuthor.getPublic_type());
			if (!domesticVsForeignLiterature.contains(bookOfAuthor.getDomesticOrForeign()))
				domesticVsForeignLiterature.add(bookOfAuthor.getDomesticOrForeign());
			if (!listOfContentsClass.contains(bookOfAuthor.getContentType()))
				listOfContentsClass.add(bookOfAuthor.getContentType());
		}
		accordianRender = true;
		datalistRender = true;
		menuListingView = true;
	}

	List<String> bookInformation = new ArrayList<>();
	List<BookInfo> bookDetailedInfo;
	Book bookInfo;

	public void clearBookInfo() {
		bookInfo = null;
	}

	public void accessInformationReach(ActionEvent event) {
		bookInfo = null;
		UIComponent component = (UIComponent) event.getSource();
		String value = (String) component.getAttributes().get("value");

		System.out.println();
		for (Book book1 : listAuthorsBooks) {
			if (book1.getBook().equals(value))
				bookInfo = book1;
		}

		bookDetailedInfo = null;
		bookDetailedInfo = queryDatabase.selectBookInfo(value);
	}

	public AuthorService getService() {
		return service;
	}

	public void setService(AuthorService service) {
		this.service = service;
	}

	public Author getAutor1() {
		return autor1;
	}

	public void setAutor1(Author autor1) {
		this.autor1 = autor1;
	}

	public String getVrstaSearcha() {
		return vrstaSearcha;
	}

	public void setVrstaSearcha(String vrstaSearcha) {
		service.setVrstaSearcha(vrstaSearcha);
		this.vrstaSearcha = vrstaSearcha;
	}

	public boolean isAccordianRender() {
		return accordianRender;
	}

	public void setAccordianRender(boolean accordianRender) {
		this.accordianRender = accordianRender;
	}

	public boolean isDatalistRender() {
		return datalistRender;
	}

	public void setDatalistRender(boolean datalistRender) {
		this.datalistRender = datalistRender;
	}

	public List<String> getListOfYears() {
		return listOfYears;
	}

	public void setListOfYears(List<String> listOfYears) {
		this.listOfYears = listOfYears;
	}

	public List<String> getListOfPublishers() {
		return listOfPublishers;
	}

	public void setListOfPublishers(List<String> listOfPublishers) {
		this.listOfPublishers = listOfPublishers;
	}

	public List<String> getListOfDemographicsClass() {
		return listOfDemographicsClass;
	}

	public void setListOfDemographicsClass(List<String> listOfDemographicsClass) {
		this.listOfDemographicsClass = listOfDemographicsClass;
	}

	public List<String> getListOfStatuses() {
		return listOfStatuses;
	}

	public void setListOfStatuses(List<String> listOfStatuses) {
		this.listOfStatuses = listOfStatuses;
	}

	public List<String> getListOfLanguages() {
		return listOfLanguages;
	}

	public void setListOfLanguages(List<String> listOfLanguages) {
		this.listOfLanguages = listOfLanguages;
	}

	public List<String> getListOfPublishingCities() {
		return listOfPublishingCities;
	}

	public void setListOfPublishingCities(List<String> listOfPublishingCities) {
		this.listOfPublishingCities = listOfPublishingCities;
	}

	public List<String> getDomesticVsForeignLiterature() {
		return domesticVsForeignLiterature;
	}

	public void setDomesticVsForeignLiterature(List<String> domesticVsForeignLiterature) {
		this.domesticVsForeignLiterature = domesticVsForeignLiterature;
	}

	public List<String> getListOfContentsClass() {
		return listOfContentsClass;
	}

	public void setListOfContentsClass(List<String> listOfContentsClass) {
		this.listOfContentsClass = listOfContentsClass;
	}

	public String getIsBookBorrowed() {
		return isBookBorrowed;
	}

	public void setIsBookBorrowed(String isBookBorrowed) {
		this.isBookBorrowed = isBookBorrowed;
	}

	public String getBookOrCollection() {
		return bookOrCollection;
	}

	public void setBookOrCollection(String bookOrCollection) {
		this.bookOrCollection = bookOrCollection;
	}

	public List<Book> getListAuthorsBooks() {
		return listAuthorsBooks;
	}

	public void setListAuthorsBooks(List<Book> listAuthorsBooks) {
		this.listAuthorsBooks = listAuthorsBooks;
	}

	public Book getBookInfo() {
		return bookInfo;
	}

	public void setBookInfo(Book bookInfo) {
		this.bookInfo = bookInfo;
	}

	public List<BookInfo> getBookDetailedInfo() {
		return bookDetailedInfo;
	}

	public void setBookDetailedInfo(List<BookInfo> bookDetailedInfo) {
		this.bookDetailedInfo = bookDetailedInfo;
	}

	public boolean isMenuListingView() {
		return menuListingView;
	}

	public void setMenuListingView(boolean menuListingView) {
		this.menuListingView = menuListingView;
	}

	public String getMenuListingSelect() {
		return menuListingSelect;
	}

	public void setMenuListingSelect(String menuListingSelect) {
		this.menuListingSelect = menuListingSelect;
	}

	public int getRowsNumber() {
		return rowsNumber;
	}

	public void setRowsNumber(int rowsNumber) {
		this.rowsNumber = rowsNumber;
	}

	public void changeIsBookBorrowed() {
		isBookBorrowed = null;
		commandButtonClick();
	}

	public void changeBookOrCollection() {
		bookOrCollection = null;
		commandButtonClick();
	}
}