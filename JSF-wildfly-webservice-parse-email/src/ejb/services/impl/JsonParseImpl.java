package ejb.services.impl;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

@Stateless
public class JsonParseImpl {

	public List<String> jsonParse(List<String> listPodaciOsobeIzForme) {

		String unesenoImePrezime = listPodaciOsobeIzForme.get(0).trim() + " " + listPodaciOsobeIzForme.get(1).trim();
		String unesenEMail = listPodaciOsobeIzForme.get(2);

		System.out.println(unesenoImePrezime + unesenEMail);

		try {
			String url = "http://jsonplaceholder.typicode.com/users";

			String jsonString = IOUtils.toString(new URL(url));

			JSONArray genreJsonArray1 = (JSONArray) JSONValue.parseWithException(jsonString);

			Map<Integer, String> mapaIdImenaPrezimenaEmail = new HashMap<Integer, String>();
			List<String> listaEmailaSStranice = new ArrayList<String>();

			for (int i = 0; i < 10; i++) {
				JSONObject osobaRednogBroja = (JSONObject) genreJsonArray1.get(i);

				listaEmailaSStranice.add(((String) osobaRednogBroja.get("email")));
				mapaIdImenaPrezimenaEmail.put(i, ((String) osobaRednogBroja.get("name")).toLowerCase()
						+ ((String) osobaRednogBroja.get("email")));
			}
			
			System.out.println(mapaIdImenaPrezimenaEmail);
			System.out.println(unesenoImePrezime);
			System.out.println(mapaIdImenaPrezimenaEmail.containsValue(unesenoImePrezime.toLowerCase() 
					+ unesenEMail));
			System.out.println(unesenoImePrezime);
			System.out.println(listaEmailaSStranice);

			if (mapaIdImenaPrezimenaEmail.containsValue(unesenoImePrezime.toLowerCase() 
					+ unesenEMail)) {

				System.out.println("3333333333333333333333333333333333333333333333333333");
				
				int keyOsobe1 = 0;
				for (int keyOsobe : mapaIdImenaPrezimenaEmail.keySet()) {
					if (mapaIdImenaPrezimenaEmail.get(keyOsobe).equals(unesenoImePrezime.toLowerCase() 
							+ unesenEMail)) {
						keyOsobe1 = keyOsobe;
					}
				}
				System.out.println(keyOsobe1);

				List<String> podaciOJednojOsobiList = new ArrayList<String>();

				JSONObject osobaObjekt = (JSONObject) genreJsonArray1.get(keyOsobe1);

				podaciOJednojOsobiList.add(((String) osobaObjekt.get("name")).split("\\s+")[0]);
				podaciOJednojOsobiList.add(((String) osobaObjekt.get("name")).split("\\s+")[1]);
				podaciOJednojOsobiList.add(((String) osobaObjekt.get("email")));
				podaciOJednojOsobiList.add((String) osobaObjekt.get("username"));

				JSONObject adresaOsobeObjekt = (JSONObject) osobaObjekt.get("address");
				podaciOJednojOsobiList.add((String) adresaOsobeObjekt.get("street"));
				podaciOJednojOsobiList.add((String) adresaOsobeObjekt.get("suite"));
				podaciOJednojOsobiList.add((String) adresaOsobeObjekt.get("city"));
				podaciOJednojOsobiList.add((String) adresaOsobeObjekt.get("zipcode"));

				JSONObject geoOsobeObjekt = (JSONObject) adresaOsobeObjekt.get("geo");
				podaciOJednojOsobiList.add((String) geoOsobeObjekt.get("lat"));
				podaciOJednojOsobiList.add((String) geoOsobeObjekt.get("lng"));

				podaciOJednojOsobiList.add((String) osobaObjekt.get("phone"));
				podaciOJednojOsobiList.add((String) osobaObjekt.get("website"));

				JSONObject companyOsobeObjekt = (JSONObject) osobaObjekt.get("company");
				podaciOJednojOsobiList.add((String) companyOsobeObjekt.get("name"));
				podaciOJednojOsobiList.add((String) companyOsobeObjekt.get("catchPhrase"));
				podaciOJednojOsobiList.add((String) companyOsobeObjekt.get("bs"));
				System.out.println();

				return podaciOJednojOsobiList;
			}

			if ((listaEmailaSStranice.contains(unesenEMail))
					&& (!mapaIdImenaPrezimenaEmail.containsValue(unesenoImePrezime.toLowerCase() 
							+ unesenEMail))) {
				System.out.println("hhhhhhhhhhhhhhhhhhhhhhhh");

				List<String> listaError = new ArrayList<String>();
				listaError.add("osobaSTimMailomPostojiNaUrl");
				return listaError;
			}
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
