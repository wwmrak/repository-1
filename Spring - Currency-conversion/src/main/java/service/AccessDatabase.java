package service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import entity.DifferenceTable;
import entity.CurrencyRates;

@Component
public class AccessDatabase {
	@Autowired
	RegistrationBean registrationBeanObj;
		
	public void createTableCurrencyRates(List<Map<String, String>> allDaysCurrencyInfoList) {
		String rateSheetDesignation = "";
		Date applicationDate = null;

		for (Map<String, String> oneDayCurrencyInfoMap : allDaysCurrencyInfoList) {
			if (oneDayCurrencyInfoMap.size() < 2) {
				rateSheetDesignation = oneDayCurrencyInfoMap.get("rateSheetDesignation");
				continue;
			}

			try {
				applicationDate = new SimpleDateFormat("ddMMyy").parse(oneDayCurrencyInfoMap.get("dateFromUrl"));
			} catch (ParseException e) {
				e.printStackTrace();
			}

			Calendar cal = Calendar.getInstance();
			cal.setTime(applicationDate);
			cal.add(Calendar.DATE, -1);
			Date creationDate = cal.getTime();

			CurrencyRates currencyRatesObj = new CurrencyRates();
			fillCurrencyRatesProperties(rateSheetDesignation, applicationDate, oneDayCurrencyInfoMap, creationDate,
					currencyRatesObj);
			
			registrationBeanObj.getCurrencyRatesRepository().save(currencyRatesObj);
		}
	}

	private void fillCurrencyRatesProperties(String rateSheetDesignation, Date applicationDate,
			Map<String, String> oneDayCurrencyInfoMap, Date creationDate, CurrencyRates currencyRatesObj) {
		currencyRatesObj.setRateDesignation(Integer.parseInt(rateSheetDesignation.substring(0, 3)));
		currencyRatesObj.setCreationDate(creationDate);
		currencyRatesObj.setApplicationDate(applicationDate);
		currencyRatesObj.setCurrencyNumber(Integer.parseInt(oneDayCurrencyInfoMap.get("currencyDesignation")));
		currencyRatesObj.setCurrencyCode(oneDayCurrencyInfoMap.get("currencyCode"));
		currencyRatesObj.setNumberOfUnits(Integer.parseInt(oneDayCurrencyInfoMap.get("numberOfUnits")));
		currencyRatesObj.setBuyingRate(Double.parseDouble(oneDayCurrencyInfoMap.get("buyingCurrencyRate").replace(',', '.')));
		currencyRatesObj.setMiddleRate(Double.parseDouble(oneDayCurrencyInfoMap.get("middleCurrencyRate").replace(',', '.')));
		currencyRatesObj.setSellingRate(Double.parseDouble(oneDayCurrencyInfoMap.get("sellingCurrencyRate").replace(',', '.')));
	}
	
	public List<List<Double>> conversionQuery(String applicationDate, String currencyCodeEnding,
			String currencyCodeStarting) {
		List<Double> middleRateOfFirstCurrencyList = null;
		List<Double> middleRateOfSecondCurrencyList = null;
		
		try {
		middleRateOfFirstCurrencyList = registrationBeanObj.getCurrencyRatesRepository().getMiddleRateByDateAndCurrency(currencyCodeStarting, 
				new SimpleDateFormat("ddMMyy").parse(applicationDate));
		middleRateOfSecondCurrencyList = registrationBeanObj.getCurrencyRatesRepository().getMiddleRateByDateAndCurrency(currencyCodeEnding, 
				new SimpleDateFormat("ddMMyy").parse(applicationDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		List<List<Double>> listSrednjiTecaj2Valute = new ArrayList<List<Double>>();
		listSrednjiTecaj2Valute.add(middleRateOfFirstCurrencyList);
		listSrednjiTecaj2Valute.add(middleRateOfSecondCurrencyList);

		return listSrednjiTecaj2Valute;
	}

	public void createDifferenceTable() {
		List<Object[]> differenceRatesTablesList = registrationBeanObj.getCurrencyRatesRepository().selectCurrencyCodeApplicationDateAndMiddleRate();

		for (int i = 0; i < differenceRatesTablesList.size(); i++) {
			DifferenceTable differenceTableRow = new DifferenceTable();
			differenceTableRow.setApplicationDate((Date) differenceRatesTablesList.get(i)[0]);
			differenceTableRow.setCurrencyCode((String) differenceRatesTablesList.get(i)[1]);
			if (i < 13)
				differenceTableRow.setMiddleRateDifference(0);
			else
				differenceTableRow.setMiddleRateDifference((Double) differenceRatesTablesList.get(i)[2] - (Double) differenceRatesTablesList.get(i - 13)[2]);

			registrationBeanObj.getDifferenceTableRepository().save(differenceTableRow);
		}
	}
	
	public List<Date> checkDatesFromTableCurrencyRates() {
		List<Date> applicationDatesList = registrationBeanObj.getCurrencyRatesRepository().selectApplicationDateFromCurrencyRatesTable();
		
		return applicationDatesList;
	}
	
	public RegistrationBean getRegistrationBean() {
		return registrationBeanObj;
	}

	public void setRegistrationBean(RegistrationBean registrationBean) {
		this.registrationBeanObj = registrationBean;
	}
}
