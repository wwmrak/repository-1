package ejb.services.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

@Stateless
public class ParseJson {
	public Map<String, String> downloadDocAndParse(Map<String, String> personalInfoFromForm) {
		try {
			Map<Integer, String> namesSurnamesAndEmailsMap = new HashMap<Integer, String>();
			List<String> emailPagesList = new ArrayList<String>();
			
			String inputtedNameAndSurname = personalInfoFromForm.get("name").trim() + " " + personalInfoFromForm.get("surname").trim();
			String inputtedEmail = personalInfoFromForm.get("email");
			
			String downloadDoc = downlodJsonDoc();

			JSONArray rootNodes = (JSONArray) JSONValue.parseWithException(downloadDoc);
			for (int i = 0; i < 10; i++) {
				JSONObject oneUserNode = (JSONObject) rootNodes.get(i);

				emailPagesList.add(((String) oneUserNode.get("email")));
				namesSurnamesAndEmailsMap.put(i, ((String) oneUserNode.get("name")).toLowerCase()
						+ ((String) oneUserNode.get("email")));
			}

			if (namesSurnamesAndEmailsMap.containsValue(inputtedNameAndSurname.toLowerCase() 
					+ inputtedEmail)) {
				int userOrdinalNumber = 0;
				
				for (int userOrdinalNumberTemp : namesSurnamesAndEmailsMap.keySet()) {
					if (namesSurnamesAndEmailsMap.get(userOrdinalNumberTemp).equals(inputtedNameAndSurname.toLowerCase() 
							+ inputtedEmail)) {
						userOrdinalNumber = userOrdinalNumberTemp;
					}
				}

				Map<String, String> oneUserInfoMap = fillOneUserInfoMap(rootNodes, userOrdinalNumber);
				return oneUserInfoMap;
			}

			if ((emailPagesList.contains(inputtedEmail))
					&& (!namesSurnamesAndEmailsMap.containsValue(inputtedNameAndSurname.toLowerCase() 
							+ inputtedEmail))) {
				Map<String, String> errorMap = new HashMap<String, String>();
				errorMap.put("error", "userWithThatMailExistsOnWebPage");
				
				return errorMap;
			}
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	private String downlodJsonDoc() throws IOException, MalformedURLException {
		String url = "http://jsonplaceholder.typicode.com/users";
		String downloadDoc = IOUtils.toString(new URL(url));
		return downloadDoc;
	}

	public Map<String, String> fillOneUserInfoMap(JSONArray rootNodes, int userOrdinalNumber) {
		Map<String, String> oneUserInfoMap = new HashMap<String, String>();

		JSONObject oneUserNode = (JSONObject) rootNodes.get(userOrdinalNumber);
		oneUserInfoMap.put("name", (((String) oneUserNode.get("name")).split("\\s+")[0]));
		oneUserInfoMap.put("surname", ((String) oneUserNode.get("name")).split("\\s+")[1]);
		oneUserInfoMap.put("email", ((String) oneUserNode.get("email")));
		oneUserInfoMap.put("username", (String) oneUserNode.get("username"));

		JSONObject addressNode = (JSONObject) oneUserNode.get("address");
		oneUserInfoMap.put("street", (String) addressNode.get("street"));
		oneUserInfoMap.put("suite", (String) addressNode.get("suite"));
		oneUserInfoMap.put("city", (String) addressNode.get("city"));
		oneUserInfoMap.put("zipcode", (String) addressNode.get("zipcode"));

		JSONObject geoNode = (JSONObject) addressNode.get("geo");
		oneUserInfoMap.put("lat", (String) geoNode.get("lat"));
		oneUserInfoMap.put("lng", (String) geoNode.get("lng"));
		oneUserInfoMap.put("phone", (String) oneUserNode.get("phone"));
		oneUserInfoMap.put("website", (String) oneUserNode.get("website"));

		JSONObject companyNode = (JSONObject) oneUserNode.get("company");
		oneUserInfoMap.put("companyName", (String) companyNode.get("name"));
		oneUserInfoMap.put("catchPhrase", (String) companyNode.get("catchPhrase"));
		oneUserInfoMap.put("bs", (String) companyNode.get("bs"));
		
		return oneUserInfoMap;
	}
}
