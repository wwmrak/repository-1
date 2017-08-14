package service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ReadURLAndParse {
	private static List<ArrayList<String>> listOfRecords = new ArrayList<ArrayList<String>>();

	public static void main(String[] args) {
//		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("SpringContext.xml");
		
		new ReadURLAndParse().getDownloadUrls();

//		AccessDatabase accessDatabaseObj = (AccessDatabase) context.getBean("createTable");
//		accessDatabaseObj.createTableTecajneListe(listOfRecords);

//		new CurrencyConversion().currencyConversion(accessDatabaseObj);

//		accessDatabaseObj.createRazlikovnaTablica();
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
		String line;
		try {
			URL urlObj = new URL(url);
			String dateFromUrl = url.substring(26, 32);

			// working behind firewall: uncomment lines below
			// Proxy proxy = new Proxy(Proxy.Type.HTTP, new
			// InetSocketAddress("192.168.0.202", 8080)); 
			// HttpURLConnection con = (HttpURLConnection)
			// data.openConnection(proxy); 

			HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			while ((line = in.readLine()) != null) {
				String[] oneLinePartsArray = line.split("\\s+");
				List<String> oneLinePartsList = Arrays.asList(oneLinePartsArray);

				//1.array u records list je to
				//rate designation : 001311220140101201514 (whole row)
				if (oneLinePartsArray[0].length() > 20) {
					String rateSheetDesignation = oneLinePartsArray[0];
					
					ArrayList<String> rateSheetDesignationList = new ArrayList<String>(); //broj tecajnice
					rateSheetDesignationList.add(rateSheetDesignation);
					listOfRecords.add(rateSheetDesignationList);
					
					continue;
				}
//				036AUD001       5,153033       5,168539       5,184045
				
				ArrayList<String> oneCurrencyDailyInfoList = new ArrayList<String>();
				oneCurrencyDailyInfoList.addAll(oneLinePartsList);
				oneCurrencyDailyInfoList.add(oneLinePartsArray[0].substring(0, 3));
				oneCurrencyDailyInfoList.add(oneLinePartsArray[0].substring(3, 6));
				oneCurrencyDailyInfoList.add(oneLinePartsArray[0].substring(6, 9));
				oneCurrencyDailyInfoList.add(dateFromUrl);

				listOfRecords.add(oneCurrencyDailyInfoList);
			}
			in.close();
			con.disconnect();
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
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