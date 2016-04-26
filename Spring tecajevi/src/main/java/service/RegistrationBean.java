package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import repo.RazlikovnaTablicaRepository;
import repo.TecajneListeRepository;

@Component
public class RegistrationBean {
	
	@Autowired
	private TecajneListeRepository tecajneListeRepository;
	
	@Autowired
	private RazlikovnaTablicaRepository razlikovnaTablicaRepository;
	
	public RegistrationBean(){
	}

	public RazlikovnaTablicaRepository getRazlikovnaTablicaRepository() {
		return razlikovnaTablicaRepository;
	}

	public void setRazlikovnaTablicaRepository(RazlikovnaTablicaRepository razlikovnaTablicaRepository) {
		this.razlikovnaTablicaRepository = razlikovnaTablicaRepository;
	}
	
	public TecajneListeRepository getTecajneListeRepository() {
		return tecajneListeRepository;
	}

	public void setTecajneListeRepository(TecajneListeRepository tecajneListeRepository) {
		this.tecajneListeRepository = tecajneListeRepository;
	}
	
}