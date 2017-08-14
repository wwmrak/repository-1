package repo;

import java.util.Date;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import entity.CurrencyRates;

@Repository
public interface CurrencyRatesRepository extends CrudRepository<CurrencyRates, Long>{
	public List<Date> selectDatumPrimjeneFromTecajneListe();
	public List<Double> srednjiTecajPoDatumuIValuti(String oznakaValute, Date datumPrimjene);
	List<Object[]> selectOznakaValuteDatumPrimjeneSrednjiTecaj();
}