package gui.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import ejb.services.impl.AccessDatabase;
import ejb.services.impl.ParseJson;
import ejb.services.impl.SendMail;
import jpa.entities.PersonalInfo;

@ManagedBean(eager = true)
@ViewScoped
public class FormController {
	List<PersonalInfo> listOfRecords;
	private String name;
	private String surname;
	private String email;
	
	boolean userWithThatEmailExistsOnWebPage;
	boolean sameEmailDifferentNameFromDB;
	boolean timeBetweenInputsLessThan1Hour;
	boolean mailSent;		
	
	@EJB
	AccessDatabase accessDatabaseObj;
	@EJB
	ParseJson parseJsonObj;
	@EJB
	SendMail sendMailObj;

	public void addRecord() {
		userWithThatEmailExistsOnWebPage = false;
		sameEmailDifferentNameFromDB = false;
		timeBetweenInputsLessThan1Hour = false;
		mailSent = false;
		
		Map<String, String> personalInfoFromForm = new HashMap<String, String>();
		personalInfoFromForm.put("name", name);
		personalInfoFromForm.put("surname", surname);
		personalInfoFromForm.put("email", email);
		
		Map<String, String> onePersonInfoMap = parseJsonObj.downloadDocAndParse(personalInfoFromForm);
				
		if (onePersonInfoMap != null && onePersonInfoMap.containsKey("error") && onePersonInfoMap.get("error").equals(("userWithThatMailExistsOnWebPage"))) {
			userWithThatEmailExistsOnWebPage = true;
			return;
		}
		
		if (onePersonInfoMap == null) {
			sameEmailDifferentNameFromDB = accessDatabaseObj.checkSameMailDifferentNameInDB(personalInfoFromForm);
			if (sameEmailDifferentNameFromDB == true) return;
		}
			
		String userInTableAndTimeCheck = accessDatabaseObj.checkIfUserInTableAndTime(personalInfoFromForm);
		
		if (onePersonInfoMap == null && userInTableAndTimeCheck.equals("one hour passed and user is in table")) { 
				sendMailObj.sendMail(personalInfoFromForm);
				mailSent = true;
				return;
		}
		
		if (onePersonInfoMap != null && userInTableAndTimeCheck.equals("one hour passed and user is in table")) { 
				onePersonInfoMap.put("name2", name);
				onePersonInfoMap.put("surname2", surname);
				onePersonInfoMap.put("email2", email);
				
				sendMailObj.sendMail(onePersonInfoMap);
				mailSent = true;
				return;
		}
		
        if (userInTableAndTimeCheck.equals("one hour hasn't passed")) {
        	timeBetweenInputsLessThan1Hour = true;
        	return;
        }
            		
        if (onePersonInfoMap == null) {
        	accessDatabaseObj.createPersonalInfoRecord(onePersonInfoMap);
            	sendMailObj.sendMail(onePersonInfoMap);
            	mailSent = true;
        }
				
		if (onePersonInfoMap != null) {
			onePersonInfoMap.put("name", name);
			onePersonInfoMap.put("surname", surname);
			onePersonInfoMap.put("email", email);
			
			accessDatabaseObj.createPersonalInfoRecord(onePersonInfoMap);
			sendMailObj.sendMail(onePersonInfoMap);
			mailSent = true;
		}
		listOfRecords = accessDatabaseObj.selectAllFromTable(); 
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public SendMail getSendMailObj() {
		return sendMailObj;
	}
	public void setSendMailObj(SendMail sendMailObj) {
		this.sendMailObj = sendMailObj;
	}
	public boolean isSameEmailDifferentMailFromDB() {
		return sameEmailDifferentNameFromDB;
	}
	public void setSameEmailDifferentMailFromDB(boolean sameEmailDifferentMailFromDB) {
		this.sameEmailDifferentNameFromDB = sameEmailDifferentMailFromDB;
	}
	public boolean isUserWithThatEmailExistsOnWebPage() {
		return userWithThatEmailExistsOnWebPage;
	}
	public void setUserWithThatEmailExistsOnWebPage(boolean userWithThatEmailExistsOnWebPage) {
		this.userWithThatEmailExistsOnWebPage = userWithThatEmailExistsOnWebPage;
	}
	public boolean isTimeBetweenInputsLessThan1Hour() {
		return timeBetweenInputsLessThan1Hour;
	}
	public void setTimeBetweenInputsLessThan1Hour(boolean timeBetweenInputsLessThan1Hour) {
		this.timeBetweenInputsLessThan1Hour = timeBetweenInputsLessThan1Hour;
	}
	public boolean isMailSent() {
		return mailSent;
	}
	public void setMailSent(boolean mailSent) {
		this.mailSent = mailSent;
	}
	public List<PersonalInfo> getListOfRecords() {
		return listOfRecords;
	}
	public void setListOfRecords(List<PersonalInfo> listOfRecords) {
		this.listOfRecords = listOfRecords;
	}
}