package service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import entity.DifferenceTable;
import entity.CurrencyRates;

@Component
public class AccessDatabase {
	@Autowired
	RegistrationBean registrationBeanObj;
		
	public void createTableTecajneListe(List<ArrayList<String>> listOfRecords) {
		String brojTecajnice = "000";
		Date datumPrimjene = null;

		for (ArrayList<String> oneRecord : listOfRecords) {
			if (oneRecord.size() < 2) {
				brojTecajnice = oneRecord.get(0);
				continue;
			}

			try {
				datumPrimjene = new SimpleDateFormat("ddMMyy").parse(oneRecord.get(7));
			} catch (ParseException e) {
				e.printStackTrace();
			}

			Calendar cal = Calendar.getInstance();
			cal.setTime(datumPrimjene);
			cal.add(Calendar.DATE, -1);
			Date datumIzrade = cal.getTime();

			CurrencyRates currencyRatesObj = new CurrencyRates();
			currencyRatesObj.setRateDesignation(Integer.parseInt(brojTecajnice.substring(0, 3)));
			currencyRatesObj.setCreationDate(datumIzrade);
			currencyRatesObj.setApplicationDate(datumPrimjene);
			currencyRatesObj.setCurrencyNumber(Integer.parseInt(oneRecord.get(4)));
			currencyRatesObj.setCurrencyCode(oneRecord.get(5));
			currencyRatesObj.setNumberOfUnits(Integer.parseInt(oneRecord.get(6)));
			currencyRatesObj.setBuyingRate(Double.parseDouble(oneRecord.get(1).replace(',', '.')));
			currencyRatesObj.setMiddleRate(Double.parseDouble(oneRecord.get(2).replace(',', '.')));
			currencyRatesObj.setSellingRate(Double.parseDouble(oneRecord.get(3).replace(',', '.')));
			
			System.out.println(registrationBeanObj);
			registrationBeanObj.getCurrencyRatesRepository().save(currencyRatesObj);
		}
	}
	
	public List<List<Double>> conversionQuery(String applicationDate, String currencyCodeEnding,
			String currencyCodeStarting) {
		List<Double> listSrednjiTecajValute1 = null;
		List<Double> listSrednjiTecajValute2 = null;
		
		try {
		listSrednjiTecajValute1 = registrationBeanObj.getCurrencyRatesRepository().srednjiTecajPoDatumuIValuti(currencyCodeStarting, 
				new SimpleDateFormat("ddMMyy").parse(applicationDate));
		listSrednjiTecajValute2 = registrationBeanObj.getCurrencyRatesRepository().srednjiTecajPoDatumuIValuti(currencyCodeEnding, 
				new SimpleDateFormat("ddMMyy").parse(applicationDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		List<List<Double>> listSrednjiTecaj2Valute = new ArrayList<List<Double>>();
		listSrednjiTecaj2Valute.add(listSrednjiTecajValute1);
		listSrednjiTecaj2Valute.add(listSrednjiTecajValute2);

		return listSrednjiTecaj2Valute;
	}

	public void createRazlikovnaTablica() {
		
		List<Object[]> listaRazlikovnaTablica = registrationBeanObj.getCurrencyRatesRepository().selectOznakaValuteDatumPrimjeneSrednjiTecaj();

		System.out.println(listaRazlikovnaTablica.size());

		for (int i = 0; i < listaRazlikovnaTablica.size(); i++) {
			DifferenceTable differenceTableRow = new DifferenceTable();
			differenceTableRow.setApplicationDate((Date) listaRazlikovnaTablica.get(i)[0]);
			differenceTableRow.setApplicationDate((Date) listaRazlikovnaTablica.get(i)[0]);
			differenceTableRow.setCurrencyCode((String) listaRazlikovnaTablica.get(i)[1]);
			if (i < 13)
				differenceTableRow.setMiddleRateDifference(0);
			else
				differenceTableRow.setMiddleRateDifference((Double) listaRazlikovnaTablica.get(i)[2] - (Double) listaRazlikovnaTablica.get(i - 13)[2]);

			registrationBeanObj.getDifferenceTableRepository().save(differenceTableRow);
		}
	}
	
	public List<Date> provjeraDatumaIzTablice() {
		List<Date> listaDatumaPrimjene = registrationBeanObj.getCurrencyRatesRepository().selectDatumPrimjeneFromTecajneListe();
		System.out.println(listaDatumaPrimjene);
		
		return listaDatumaPrimjene;
	}
	
	public RegistrationBean getRegistrationBean() {
		return registrationBeanObj;
	}

	public void setRegistrationBean(RegistrationBean registrationBean) {
		this.registrationBeanObj = registrationBean;
	}
}
