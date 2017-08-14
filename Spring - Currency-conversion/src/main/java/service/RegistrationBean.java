package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import repo.CurrencyRatesRepository;
import repo.DifferenceTableRepository;

@Component
public class RegistrationBean {
	public RegistrationBean(){
	}
	
	@Autowired
	private CurrencyRatesRepository currencyRatesRepository;
	@Autowired
	private DifferenceTableRepository DifferenceTableRepository;

	public CurrencyRatesRepository getCurrencyRatesRepository() {
		return currencyRatesRepository;
	}
	public void setCurrencyRatesRepository(CurrencyRatesRepository currencyRatesRepository) {
		this.currencyRatesRepository = currencyRatesRepository;
	}
	public DifferenceTableRepository getDifferenceTableRepository() {
		return DifferenceTableRepository;
	}
	public void setDifferenceTableRepository(DifferenceTableRepository differenceTableRepository) {
		DifferenceTableRepository = differenceTableRepository;
	}
}