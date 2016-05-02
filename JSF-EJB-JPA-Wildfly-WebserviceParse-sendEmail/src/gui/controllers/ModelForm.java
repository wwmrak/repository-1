package gui.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ejb.services.impl.CreateTableImpl;
import ejb.services.impl.JsonParseImpl;
import ejb.services.impl.SendMailImpl;
import jpa.entities.OsobniPodaci;

@ManagedBean(eager = true)
@ViewScoped
public class ModelForm {
	
	private String ime;
	private String prezime;
	private String email;
	
	boolean osobaSTimMailomPostojiNaUrl;
	boolean sameEmailDifferentNameFromDB;
	boolean vrijemeIzmeduUnosaManjeOd1h;
	boolean mailSent;
	
	List<OsobniPodaci> listOfRecords;
	
	@EJB
	CreateTableImpl createTable;
	@EJB
	JsonParseImpl jsonParseObj;
	@EJB
	SendMailImpl sendMailObj;
	
	public void addRecord() {
		
		osobaSTimMailomPostojiNaUrl = false;
		sameEmailDifferentNameFromDB = false;
		vrijemeIzmeduUnosaManjeOd1h = false;
		mailSent = false;
		
		List<String> listPodaciOsobeIzForme = new ArrayList<String>();
		listPodaciOsobeIzForme.add(ime);
		listPodaciOsobeIzForme.add(prezime);
		listPodaciOsobeIzForme.add(email);
		
		List<String> podaciOJednojOsobiParse = jsonParseObj.jsonParse(listPodaciOsobeIzForme);
		
		if (podaciOJednojOsobiParse != null) {
			if (podaciOJednojOsobiParse.contains("osobaSTimMailomPostojiNaUrl")) {
				osobaSTimMailomPostojiNaUrl = true;
				return;
			}
		}
		
		if (podaciOJednojOsobiParse == null) {
			sameEmailDifferentNameFromDB = createTable.checkSameMailDifferentNameDB(listPodaciOsobeIzForme);
			if (sameEmailDifferentNameFromDB == true) return;
		}
			
		String check1 = createTable.checkDaliOsobaUTabliciIVrijeme(listPodaciOsobeIzForme);
		
		if (podaciOJednojOsobiParse == null) {
			if (check1.equals("proslo sat vremena i osoba u tablici")) { 
					sendMailObj.sendMail(listPodaciOsobeIzForme);
					mailSent = true;
					return;
			}
		}
		
		if (podaciOJednojOsobiParse != null) {
			if (check1.equals("proslo sat vremena i osoba u tablici")) { 
					podaciOJednojOsobiParse.add(ime);
					podaciOJednojOsobiParse.add(prezime);
					podaciOJednojOsobiParse.add(email);
					sendMailObj.sendMail(podaciOJednojOsobiParse);
					mailSent = true;
					return;
			}
		}
		
        if (check1.equals("nije proslo sat vremena")) {
        	vrijemeIzmeduUnosaManjeOd1h = true;
        	return;
        }
            		
        if (podaciOJednojOsobiParse == null) {
            	createTable.createRecordOsobniPodaci(listPodaciOsobeIzForme);
            	sendMailObj.sendMail(listPodaciOsobeIzForme);
            	mailSent = true;
        }
				
		
		if (podaciOJednojOsobiParse != null) {
			podaciOJednojOsobiParse.add(ime);
			podaciOJednojOsobiParse.add(prezime);
			podaciOJednojOsobiParse.add(email);
			
			createTable.createRecordOsobniPodaci(podaciOJednojOsobiParse);
			sendMailObj.sendMail(podaciOJednojOsobiParse);
			mailSent = true;
		}
		
		listOfRecords = createTable.selectAllFromTable(); 
	}
			
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public SendMailImpl getSendMailObj() {
		return sendMailObj;
	}

	public void setSendMailObj(SendMailImpl sendMailObj) {
		this.sendMailObj = sendMailObj;
	}

	public boolean isOsobaSTimMailomPostojiNaUrl() {
		return osobaSTimMailomPostojiNaUrl;
	}

	public void setOsobaSTimMailomPostojiNaUrl(boolean osobaSTimMailomPostojiNaUrl) {
		this.osobaSTimMailomPostojiNaUrl = osobaSTimMailomPostojiNaUrl;
	}

	public boolean isSameEmailDifferentMailFromDB() {
		return sameEmailDifferentNameFromDB;
	}

	public void setSameEmailDifferentMailFromDB(boolean sameEmailDifferentMailFromDB) {
		this.sameEmailDifferentNameFromDB = sameEmailDifferentMailFromDB;
	}

	public boolean isMailSent() {
		return mailSent;
	}

	public void setMailSent(boolean mailSent) {
		this.mailSent = mailSent;
	}

	public boolean isVrijemeIzmeduUnosaManjeOd1h() {
		return vrijemeIzmeduUnosaManjeOd1h;
	}

	public void setVrijemeIzmeduUnosaManjeOd1h(boolean vrijemeIzmeduUnosaManjeOd1h) {
		this.vrijemeIzmeduUnosaManjeOd1h = vrijemeIzmeduUnosaManjeOd1h;
	}

	public List<OsobniPodaci> getListOfRecords() {
		return listOfRecords;
	}

	public void setListOfRecords(List<OsobniPodaci> listOfRecords) {
		this.listOfRecords = listOfRecords;
	}
}