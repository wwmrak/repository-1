package service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import entity.RazlikovnaTablica;
import entity.TecajneListe;

@Component
public class CreateTable {
	
	@Autowired
	RegistrationBean registrationBean;
		
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

			TecajneListe tecajneListe1 = new TecajneListe();

			tecajneListe1.setBrojTecajnice(Integer.parseInt(brojTecajnice.substring(0, 3)));
			tecajneListe1.setDatumIzrade(datumIzrade);
			tecajneListe1.setDatumPrimjene(datumPrimjene);
			tecajneListe1.setSifraValute(Integer.parseInt(oneRecord.get(4)));
			tecajneListe1.setOznakaValute(oneRecord.get(5));
			tecajneListe1.setBrojJedinica(Integer.parseInt(oneRecord.get(6)));
			tecajneListe1.setKupovniTecaj(Double.parseDouble(oneRecord.get(1).replace(',', '.')));
			tecajneListe1.setSrednjiTecaj(Double.parseDouble(oneRecord.get(2).replace(',', '.')));
			tecajneListe1.setProdajniTecaj(Double.parseDouble(oneRecord.get(3).replace(',', '.')));
			
			System.out.println("dfasfafdasdf");
			
			System.out.println(registrationBean);
			registrationBean.getTecajneListeRepository().save(tecajneListe1);
			
			System.out.println("dfasfafdasdf");
		}
	}
	
	public List<List<Double>> conversionQuery(String datumPrimjene, String oznakaValuteZavrsna,
			String oznakaValutePocetna) {
		
		List<Double> listSrednjiTecajValute1 = null;
		List<Double> listSrednjiTecajValute2 = null;
		
		try {
		listSrednjiTecajValute1 = registrationBean.getTecajneListeRepository().srednjiTecajPoDatumuIValuti(oznakaValutePocetna, 
				new SimpleDateFormat("ddMMyy").parse(datumPrimjene));
		
		listSrednjiTecajValute2 = registrationBean.getTecajneListeRepository().srednjiTecajPoDatumuIValuti(oznakaValuteZavrsna, 
				new SimpleDateFormat("ddMMyy").parse(datumPrimjene));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		List<List<Double>> listSrednjiTecaj2Valute = new ArrayList<List<Double>>();
		listSrednjiTecaj2Valute.add(listSrednjiTecajValute1);
		listSrednjiTecaj2Valute.add(listSrednjiTecajValute2);

		return listSrednjiTecaj2Valute;
	}

	public void createRazlikovnaTablica() {
		
		List<Object[]> listaRazlikovnaTablica = registrationBean.getTecajneListeRepository().selectOznakaValuteDatumPrimjeneSrednjiTecaj();

		System.out.println(listaRazlikovnaTablica.size());

		for (int i = 0; i < listaRazlikovnaTablica.size(); i++) {

			RazlikovnaTablica razlikovnaTablicaRedak = new RazlikovnaTablica();

			razlikovnaTablicaRedak.setDatumPrimjene((Date) listaRazlikovnaTablica.get(i)[0]);
			razlikovnaTablicaRedak.setDatumPrimjene((Date) listaRazlikovnaTablica.get(i)[0]);
			razlikovnaTablicaRedak.setOznakaValute((String) listaRazlikovnaTablica.get(i)[1]);
			if (i < 13)
				razlikovnaTablicaRedak.setSrednjiTecaj(0);
			else
				razlikovnaTablicaRedak.setSrednjiTecaj(
						(Double) listaRazlikovnaTablica.get(i)[2] - (Double) listaRazlikovnaTablica.get(i - 13)[2]);

			registrationBean.getRazlikovnaTablicaRepository().save(razlikovnaTablicaRedak);
		}
	}
	
	public List<Date> provjeraDatumaIzTablice() {

		List<Date> listaDatumaPrimjene = registrationBean.getTecajneListeRepository().selectDatumPrimjeneFromTecajneListe();
				
		System.out.println(listaDatumaPrimjene);
		return listaDatumaPrimjene;
	}
	
	public RegistrationBean getRegistrationBean() {
		return registrationBean;
	}

	public void setRegistrationBean(RegistrationBean registrationBean) {
		this.registrationBean = registrationBean;
	}
}
