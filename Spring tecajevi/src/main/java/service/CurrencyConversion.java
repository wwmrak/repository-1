package service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class CurrencyConversion {

	public void currencyConversion(CreateTable createTable1) {
		
		Set<String> hashsetDatumiPrimjene = new HashSet<String>();
		
		List<Date> listaDatumaPrimjene = createTable1.provjeraDatumaIzTablice();
		for (Date datumPrimjene : listaDatumaPrimjene) {
			String datumPrimjeneString = new SimpleDateFormat("ddMMyy").format(datumPrimjene);
			hashsetDatumiPrimjene.add(datumPrimjeneString);
		}
		
		Scanner in = new Scanner(System.in);

		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\nIzaberite valutu koju želite konvertirati u drugu valutu."
				+ "\nPonuđene valute su: AUD, CAD, CZK, DKK, HUF, JPY, NOK, SEK, CHF, GBP, USD, EUR, PLN ");
		String oznakaValutePocetna = in.nextLine();

		List<String> listaOznakaValute = new ArrayList<String>();
		listaOznakaValute.add("aud");
		listaOznakaValute.add("cad");
		listaOznakaValute.add("czk");
		listaOznakaValute.add("dkk");
		listaOznakaValute.add("huf");
		listaOznakaValute.add("jpy");
		listaOznakaValute.add("nok");
		listaOznakaValute.add("sek");
		listaOznakaValute.add("chf");
		listaOznakaValute.add("gbp");
		listaOznakaValute.add("usd");
		listaOznakaValute.add("eur");
		listaOznakaValute.add("pln");		
		while (!listaOznakaValute.contains(oznakaValutePocetna.toLowerCase())) {
			System.out.println("\nUnesite valutu u ispravnom formatu.");
			oznakaValutePocetna = in.nextLine();
		}		

		System.out.println("\nNavedite količinu jedinica odabrane valute koju treba konvertirati u željenu valutu (u integer formatu).");
		
		String jedinice = null;
		int jediniceInt = 0;
		while (jediniceInt == 0) {  
		try {
				 jedinice = in.nextLine();
				 jediniceInt = Integer.parseInt(jedinice); 
			 }catch(NumberFormatException e) {
				 System.out.println("Unesite količinu u integer formatu");				 
			 }
		}

		System.out.println("\nIzaberite valutu u koju želite konvertirati prvu izabranu valutu."
				+ "\nPonuđene valute su: AUD, CAD, CZK, DKK, HUF, JPY, NOK, SEK, CHF, GBP, USD, EUR, PLN ");
		String oznakaValuteZavrsna = in.nextLine();
		while (!listaOznakaValute.contains(oznakaValuteZavrsna.toLowerCase())) {
			System.out.println("\nUnesite valutu u ispravnom formatu.");
			oznakaValuteZavrsna = in.nextLine();
		}
		
		System.out.println("\nIzaberite datum tecaja koji ćemo primijeniti za konverziju. Upišite u formatu dd.mm.yyyy"
				+ " (Npr. 28.08.2015) \nZa nedjelju i ponedjeljak hnb ne izrađuje tecaj, stoga za te dane vrijedi tecaj prethodnog"
				+ " datuma za koji je tecaj izrađen");
		
		String datum = in.nextLine();
		datum = datum.replace(".", "").replace("20", "");		
		while (!hashsetDatumiPrimjene.contains(datum)) {
			System.out.println("Ne postoji tečaj za taj datum");
			datum = in.nextLine();
			datum = datum.replace(".", "").replace("20", "");
		}
		
		List<List<Double>> listSrednjiTecaj2Valute = createTable1.conversionQuery(datum, oznakaValuteZavrsna,
				oznakaValutePocetna);
	
		double srednjiTecajValutePocetne = listSrednjiTecaj2Valute.get(0).get(0);
		double srednjiTecajValuteZavršne = listSrednjiTecaj2Valute.get(1).get(0);

		if (oznakaValutePocetna.equals("huf") || oznakaValutePocetna.equals("jpy")) {
			srednjiTecajValutePocetne = srednjiTecajValutePocetne / 100;
		}

		if (oznakaValuteZavrsna.equals("huf") || oznakaValuteZavrsna.equals("jpy")) {
			srednjiTecajValuteZavršne = srednjiTecajValuteZavršne / 100;
		}

		double vrijednostZavršneValute = Double.parseDouble(jedinice) * srednjiTecajValutePocetne
				/ srednjiTecajValuteZavršne;

		System.out.format("\n\n\n\n\nZa %s jedinica valute %s dobije se %f jedinica valute %s", jedinice,
				oznakaValutePocetna, vrijednostZavršneValute, oznakaValuteZavrsna);
		
		System.out.println("\nStisnite gumb za nastavak");
		in.nextLine();

	}

}

