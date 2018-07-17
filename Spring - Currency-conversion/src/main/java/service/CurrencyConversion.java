package service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class CurrencyConversion {
	
	public void convertCurrency(AccessDatabase accessDatabaseObj) {
		Set<String> applicationDatesSet = new HashSet<String>();
		fillApplicationDatesSet(accessDatabaseObj, applicationDatesSet);
		
		Scanner in = new Scanner(System.in);
		Map<String, String> currencyInfoTempMap = queryUser(applicationDatesSet, in);
		String firstChosenCurrencyCode = currencyInfoTempMap.get("firstChosenCurrencyCode");
		String secondChosenCurrencyCode = currencyInfoTempMap.get("secondChosenCurrencyCode");
		String unitsNumberString = currencyInfoTempMap.get("unitsNumberString");
		String date = currencyInfoTempMap.get("date");
		
		List<List<Double>> middleRateOfSecondCurrencyList = accessDatabaseObj.conversionQuery(date, secondChosenCurrencyCode,
				firstChosenCurrencyCode);
	
		double middleRateOfFirstCurrency = middleRateOfSecondCurrencyList.get(0).get(0);
		double middleRateOfSecondCurrency = middleRateOfSecondCurrencyList.get(1).get(0);

		if (firstChosenCurrencyCode.equals("huf") || firstChosenCurrencyCode.equals("jpy")) {
			middleRateOfFirstCurrency = middleRateOfFirstCurrency / 100;
		}
		if (secondChosenCurrencyCode.equals("huf") || secondChosenCurrencyCode.equals("jpy")) {
			middleRateOfSecondCurrency = middleRateOfSecondCurrency / 100;
		}

		double valueOfSecondCurrency = Double.parseDouble(unitsNumberString) * middleRateOfFirstCurrency
				/ middleRateOfSecondCurrency;

		printInfoToUser(in, firstChosenCurrencyCode, secondChosenCurrencyCode, unitsNumberString,
				valueOfSecondCurrency);
	}

	Map<String, String> queryUser(Set<String> applicationDatesSet, Scanner in) {
		List<String> currencyCodesList = new ArrayList<String>();
		addCurrencyCodesToList(currencyCodesList);
		
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\nChoose the currency to convert to another currency."
				+ "\nCurrencies offered : AUD, CAD, CZK, DKK, HUF, JPY, NOK, SEK, CHF, GBP, USD, EUR, PLN ");
		String firstChosenCurrencyCode = in.nextLine();
		
		while (!currencyCodesList.contains(firstChosenCurrencyCode.toLowerCase())) {
			System.out.println("\nEnter the currency in right format");
			firstChosenCurrencyCode = in.nextLine();
		}		

		System.out.println("\nEnter a number of units to convert");
		String unitsNumberString = getUnitsNumberFromUser(in);

		System.out.println("\nChoose a currency to which to convert"
				+ "\nOffered currencies : AUD, CAD, CZK, DKK, HUF, JPY, NOK, SEK, CHF, GBP, USD, EUR, PLN ");
		String secondChosenCurrencyCode = in.nextLine();
		while (!currencyCodesList.contains(secondChosenCurrencyCode.toLowerCase())) {
			System.out.println("\nEnter the currency in the right format.");
			secondChosenCurrencyCode = in.nextLine();
		}
		
		System.out.println("\nEnter date of the conversion rates list. Enter in following format : dd.mm.yyyy"
				+ " (Npr. 28.08.2015) \nConversion rates for sunday and monday are not available. "
				+ "Conversion rates from previous friday apply");
		
		String date = in.nextLine();
		date = correctDate(applicationDatesSet, in, date);
		
		Map<String, String> currencyInfoTempMap = new HashMap<String, String>();
		currencyInfoTempMap.put("firstChosenCurrencyCode", firstChosenCurrencyCode);
		currencyInfoTempMap.put("secondChosenCurrencyCode", secondChosenCurrencyCode);
		currencyInfoTempMap.put("unitsNumberString", unitsNumberString);
		currencyInfoTempMap.put("date", date);
		
		return currencyInfoTempMap;
	}

	private String getUnitsNumberFromUser(Scanner in) {
		String unitsNumberString = null;
		int unitsNumber = 0;
		while (unitsNumber == 0) {  
		try {
				 unitsNumberString = in.nextLine();
				 unitsNumber = Integer.parseInt(unitsNumberString); 
			 }catch(NumberFormatException e) {
				 System.out.println("Enter the number in integer format");				 
			 }
		}
		return unitsNumberString;
	}

	private String correctDate(Set<String> applicationDatesSet, Scanner in, String date) {
		date = date.replace(".", "").replace("20", "");		
		
		while (!applicationDatesSet.contains(date)) {
			System.out.println("Conversion rates for that day don't exist");
			date = in.nextLine();
			date = date.replace(".", "").replace("20", "");
		}
		return date;
	}
	

	private void fillApplicationDatesSet(AccessDatabase accessDatabaseObj, Set<String> applicationDatesSet) {
		List<Date> applicationDatesList = accessDatabaseObj.checkDatesFromTableCurrencyRates();
		
		for (Date applicationDate : applicationDatesList) {
			String applicationDateString = new SimpleDateFormat("ddMMyy").format(applicationDate);
			applicationDatesSet.add(applicationDateString);
		}
	}

	private void printInfoToUser(Scanner in, String firstChosenCurrencyCode, String secondChosenCurrencyCode,
			String unitsNumberString, double valueOfSecondCurrency) {
		System.out.format("\n\n\n\n\nFor %s units of the currency %s you receive %f units of currency %s", unitsNumberString,
				firstChosenCurrencyCode, valueOfSecondCurrency, secondChosenCurrencyCode);
		System.out.println("\nPress a button to continue");
		in.nextLine();
	}

	private void addCurrencyCodesToList(List<String> currencyCodesList) {
		currencyCodesList.add("aud");
		currencyCodesList.add("cad");
		currencyCodesList.add("czk");
		currencyCodesList.add("dkk");
		currencyCodesList.add("huf");
		currencyCodesList.add("jpy");
		currencyCodesList.add("nok");
		currencyCodesList.add("sek");
		currencyCodesList.add("chf");
		currencyCodesList.add("gbp");
		currencyCodesList.add("usd");
		currencyCodesList.add("eur");
		currencyCodesList.add("pln");
	}
}

