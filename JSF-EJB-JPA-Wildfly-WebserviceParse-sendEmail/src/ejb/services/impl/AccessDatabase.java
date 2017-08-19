package ejb.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.json.simple.JSONObject;

import jpa.entities.PersonalInfo;

@Stateless
public class AccessDatabase {
	@PersistenceContext(name = "personalInfoUnit")
	private EntityManager entitymanager;

	//done
	public boolean checkSameMailDifferentNameInDB(Map<String, String> personalInfoFromForm) {
		@SuppressWarnings("unchecked")
		List<Integer> emailsList = entitymanager.createNamedQuery("selectRowsWithSpecificEmail")
				.setParameter("email", personalInfoFromForm.get("email")).getResultList();

		int emailsRowCount = (int) emailsList.size();
		if (emailsRowCount >= 1) {
			@SuppressWarnings("unchecked")
			List<Integer> usersList = entitymanager
					.createNamedQuery("selectRowsWithSpecificEmailNameAndSurname")
					.setParameter("name", personalInfoFromForm.get("name"))
					.setParameter("surname", personalInfoFromForm.get("surname"))
					.setParameter("email", personalInfoFromForm.get("email")).getResultList();

			int usersRowCount = (int) usersList.size();
			if (usersRowCount == 0)
				return true;
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public String checkIfUserInTableAndTime(Map<String, String> personalInfoFromForm) {
		List<Integer> usersList = entitymanager
				.createNamedQuery("selectRowsWithSpecificEmailNameAndSurname")
				.setParameter("name", personalInfoFromForm.get("name"))
				.setParameter("surname", personalInfoFromForm.get("surname"))
				.setParameter("email", personalInfoFromForm.get("email")).getResultList();

		int usersCount = (int) usersList.size();
		if (usersCount == 0)
			return "user is not in table";

		List<Date> datesList = (List<Date>) entitymanager.createNamedQuery("selectDate")
				.setParameter("name", personalInfoFromForm.get("name"))
				.setParameter("surname", personalInfoFromForm.get("surname"))
				.setParameter("email", personalInfoFromForm.get("email")).getResultList();

		int timeDifference = (int) ((new Date().getTime() / 1000 / 60 - datesList.get(0).getTime() / 1000 / 60));
		if (timeDifference < 60)
			return "one hour hasn't passed";
		else {
			entitymanager.createNamedQuery("updateDateInRow").setParameter("email", personalInfoFromForm.get("email"))
					.executeUpdate();
		}
		
		return "one hour passed and user is in table";
	}
	
	public void createPersonalInfoRecord(Map<String, String> onePersonInfoMap) {
		PersonalInfo personalInfoObj = new PersonalInfo();
		personalInfoObj.setName(onePersonInfoMap.get("name"));
		personalInfoObj.setSurname(onePersonInfoMap.get("surname"));
		personalInfoObj.setEmail(onePersonInfoMap.get("email"));

		if (onePersonInfoMap.size() > 3) {
			personalInfoObj.setUsername(onePersonInfoMap.get("username"));
			personalInfoObj.setStreet(onePersonInfoMap.get("street"));
			personalInfoObj.setApartment(onePersonInfoMap.get("suite"));
			personalInfoObj.setCity(onePersonInfoMap.get("city"));
			personalInfoObj.setPostNumber(onePersonInfoMap.get("zipcode"));
			personalInfoObj.setGeoLat(onePersonInfoMap.get("lat"));
			personalInfoObj.setGeoLng(onePersonInfoMap.get("lng"));
			personalInfoObj.setTelephone(onePersonInfoMap.get("phone"));
			personalInfoObj.setWebPage(onePersonInfoMap.get("website"));
			personalInfoObj.setCompany(onePersonInfoMap.get("companyName"));
			personalInfoObj.setCatchePhrase(onePersonInfoMap.get("catchPhrase"));
			personalInfoObj.setBs(onePersonInfoMap.get("bs"));
		}
		entitymanager.persist(personalInfoObj);
	}

	public List<PersonalInfo> selectAllFromTable() {
		@SuppressWarnings("unchecked")
		List<PersonalInfo> rowsList = entitymanager.createNamedQuery("selectEverythingFromTable")
			.getResultList();
		
		return rowsList;
	}
}
