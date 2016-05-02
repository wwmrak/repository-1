package service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ReadURLAndParse {

	private static List<ArrayList<String>> listOfRecords = new ArrayList<ArrayList<String>>();

	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("SpringContext.xml");
		
		donwloadAndParse();

		CreateTable createTable = (CreateTable) context.getBean("createTable");
		
		createTable.createTableTecajneListe(listOfRecords);

		new CurrencyConversion().currencyConversion(createTable);

		createTable.createRazlikovnaTablica();
	}

	public static void donwloadAndParse() {

		SimpleDateFormat formatter = new SimpleDateFormat("ddMMyy");

		Date startDate = null;
		Date endDate = null;

		try {
			startDate = formatter.parse("010115");
			endDate = formatter.parse("310115");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Calendar start = Calendar.getInstance();
		start.setTime(startDate);
		Calendar end = Calendar.getInstance();
		end.setTime(endDate);

		for (Date date = start.getTime(); start.before(end); start.add(Calendar.DATE, 1), date = start.getTime()) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			if ((cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
					|| (cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY))
				continue;

			String url = "http://www.hnb.hr/tecajn/f" + formatter.format(date) + ".dat";
			dataDownloadAndParse(url);
		}
	}

	
	
	
	
	public static void dataDownloadAndParse(String url) {

		String inputLine;

		try {
			URL data = new URL(url);

			String datumPrimjeneFromUrl = url.substring(26, 32);

			// working behind firewall: unkomentirat donje linije
			// Proxy proxy = new Proxy(Proxy.Type.HTTP, new
			// InetSocketAddress("192.168.0.202", 8080)); */
			// HttpURLConnection con = (HttpURLConnection)
			// data.openConnection(proxy); */

			HttpURLConnection con = (HttpURLConnection) data.openConnection();

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

			while ((inputLine = in.readLine()) != null) {

				String[] record = inputLine.split("\\s+");

				if (record[0].length() > 20) {
					ArrayList<String> brojTecajnice = new ArrayList<String>();
					brojTecajnice.add(record[0]);
					listOfRecords.add(brojTecajnice);
					continue;
				}

				List<String> ListTecajInit = Arrays.asList(record);

				ArrayList<String> listTecajDaily = new ArrayList<String>();

				listTecajDaily.addAll(ListTecajInit);

				listTecajDaily.add(record[0].substring(0, 3));
				listTecajDaily.add(record[0].substring(3, 6));
				listTecajDaily.add(record[0].substring(6, 9));
				listTecajDaily.add(datumPrimjeneFromUrl);

				listOfRecords.add(listTecajDaily);
			}
			in.close();
			con.disconnect();
			return;

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}