package ejb.services;

import java.util.List;
import jpa.entities.PersonalInfo;

public interface AccessDatabase {
	public void createRecord(String name, String surname, String address, String education, String department); 
	public void deleteRecord(String name, String surname);	
	public void changeRecord(String name, String surname, String address, String education, String department);	
	public List<PersonalInfo> selectWholeTable();	
	public List<Integer> departmentsDisplay();
}
