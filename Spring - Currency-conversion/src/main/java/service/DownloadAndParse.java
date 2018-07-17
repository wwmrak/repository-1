package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DownloadAndParse {
	private List<Map<String, String>> allDaysCurrencyInfoList = new ArrayList<Map<String, String>>();

	public static void main(String[] args) {
		new DownloadAndParse().startProgram();
	}
	
	public void startProgram() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("SpringContext.xml");
		
		new DownloadAndParse().getDownloadUrls();

		AccessDatabase accessDatabaseObj = (AccessDatabase) context.getBean("accessDatabase");
		accessDatabaseObj.createTableCurrencyRates(allDaysCurrencyInfoList);

		new CurrencyConversion().convertCurrency(accessDatabaseObj);

		accessDatabaseObj.createDifferenceTable();
	}

	public void getDownloadUrls() {
		SimpleDateFormat formatter = new SimpleDateFormat("ddMMyy");
		
		Calendar calendarStart = Calendar.getInstance();
		Calendar calendarEnd = Calendar.getInstance();
		setCalendarStartAndEnd(calendarStart, calendarEnd);

		for (Date date = calendarStart.getTime(); calendarStart.before(calendarEnd); calendarStart.add(Calendar.DATE, 1), date = calendarStart.getTime()) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			if ((calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
					|| (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY))
				continue;

			String url = "http://www.hnb.hr/tecajn/f" + formatter.format(date) + ".dat";
			download(url);
		}
	}
	
	public void download(String url) {
		try {
			URL urlObj = new URL(url);
			String dateFromUrl = url.substring(26, 32);

			HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
			
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			parseDocumentLine(dateFromUrl, in);
			
			in.close();
			con.disconnect();
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void parseDocumentLine(String dateFromUrl, BufferedReader in)  {
		String line;
		try {
			while ((line = in.readLine()) != null) {
				String[] oneLinePartsArray = line.split("\\s+");

				if (oneLinePartsArray[0].length() > 20) {
					String rateSheetDesignation = oneLinePartsArray[0];
					
					Map<String, String> rateSheetDesignationMap = new HashMap<String, String>(); 
					rateSheetDesignationMap.put("rateSheetDesignation", rateSheetDesignation);
					allDaysCurrencyInfoList.add(rateSheetDesignationMap);
					
					continue;
				}
				
				setLinePartsInList(oneLinePartsArray, dateFromUrl);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	void setLinePartsInList(String[] oneLinePartsArray, String dateFromUrl) {
		String currencyDesignation = oneLinePartsArray[0].substring(0, 3);
		String currencyCode = oneLinePartsArray[0].substring(3, 6);
		String currencyDesignation2 = oneLinePartsArray[0].substring(6, 9);
		String buyingCurrencyRate = oneLinePartsArray[1];
		String middleCurrencyRate = oneLinePartsArray[2];
		String sellingCurrencyRate = oneLinePartsArray[3];
		
		Map<String, String> oneCurrencyDailyInfoMap = new HashMap<String, String>();
		oneCurrencyDailyInfoMap.put("currencyCode", currencyCode);
		oneCurrencyDailyInfoMap.put("currencyDesignation", currencyDesignation);
		oneCurrencyDailyInfoMap.put("currencyDesignation2", currencyDesignation2);
		oneCurrencyDailyInfoMap.put("buyingCurrencyRate", buyingCurrencyRate);
		oneCurrencyDailyInfoMap.put("middleCurrencyRate", middleCurrencyRate);
		oneCurrencyDailyInfoMap.put("sellingCurrencyRate", sellingCurrencyRate);
		oneCurrencyDailyInfoMap.put("dateFromUrl", dateFromUrl);

		allDaysCurrencyInfoList.add(oneCurrencyDailyInfoMap);
	}
	
	void setCalendarStartAndEnd(Calendar calendarStart, Calendar calendarEnd) {
		Date startDate = null;
		Date endDate = null;
		SimpleDateFormat formatter = new SimpleDateFormat("ddMMyy");
		
		try {
			startDate = formatter.parse("010115");
			endDate = formatter.parse("310115");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		calendarStart.setTime(startDate);
		calendarEnd.setTime(endDate);
	}
}