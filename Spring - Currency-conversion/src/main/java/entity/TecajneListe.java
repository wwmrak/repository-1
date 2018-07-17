package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table
@NamedQueries({
	 @NamedQuery(query = "Select e.srednjiTecaj from TecajneListe e where e.oznakaValute = ?1 and e.datumPrimjene = ?2", 
  		   name = "TecajneListe.srednjiTecajPoDatumuIValuti"),
     @NamedQuery(query = "delete from TecajneListe e", name = "TecajneListe.deleteContentFromTableTecajneListe"),
     @NamedQuery(query = "select e.datumPrimjene, e.oznakaValute, e.srednjiTecaj from TecajneListe e", 
    			name = "TecajneListe.selectOznakaValuteDatumPrimjeneSrednjiTecaj"),
     @NamedQuery(query = "select e.datumPrimjene from TecajneListe e", 
		name = "TecajneListe.selectDatumPrimjeneFromTecajneListe"),
}) 

public class TecajneListe {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private int brojTecajnice;
	
	@Temporal(TemporalType.DATE)	
	private Date datumIzrade;
	
	
	@Temporal(TemporalType.DATE)
	private Date datumPrimjene;

	private int sifraValute;
	private String oznakaValute;
	private int brojJedinica;
	
	@Column(columnDefinition="Decimal(8,6)")
	private double kupovniTecaj;
	@Column(columnDefinition="Decimal(8,6)")
	private double srednjiTecaj;
	@Column(columnDefinition="Decimal(8,6)")
	private double prodajniTecaj;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSifraValute() {
		return sifraValute;
	}

	public void setSifraValute(int sifraValute) {
		this.sifraValute = sifraValute;
	}

	public String getOznakaValute() {
		return oznakaValute;
	}

	public void setOznakaValute(String oznakaValute) {
		this.oznakaValute = oznakaValute;
	}

	public int getBrojJedinica() {
		return brojJedinica;
	}

	public void setBrojJedinica(int brojJedinica) {
		this.brojJedinica = brojJedinica;
	}

	
	public double getKupovniTecaj() {
		return kupovniTecaj;
	}

	public void setKupovniTecaj(double kupovniTecaj) {
		this.kupovniTecaj = kupovniTecaj;
	}

	public double getSrednjiTecaj() {
		return srednjiTecaj;
	}

	public void setSrednjiTecaj(double srednjiTecaj) {
		this.srednjiTecaj = srednjiTecaj;
	}

	public double getProdajniTecaj() {
		return prodajniTecaj;
	}

	public void setProdajniTecaj(double prodajniTecaj) {
		this.prodajniTecaj = prodajniTecaj;
	}
	
	public int getid() {
		return id;
	}

	public void setid(int eid) {
		this.id = eid;
	}

	public Date getDatumIzrade() {
		return datumIzrade;
	}

	public void setDatumIzrade(Date datumIzrade) {
		this.datumIzrade = datumIzrade;
	}

	public Date getDatumPrimjene() {
		return datumPrimjene;
	}

	public void setDatumPrimjene(Date datumPrimjene) {
		this.datumPrimjene = datumPrimjene;
	}

	public int getBrojTecajnice() {
		return brojTecajnice;
	}

	public void setBrojTecajnice(int brojTecajnice) {
		this.brojTecajnice = brojTecajnice;
	}
	
	@Override
	public int hashCode() {
        return (int) datumPrimjene.hashCode() + oznakaValute.hashCode() ;
    }

	@Override
    public boolean equals(Object obj) {
    	if (obj == this) return true;
    	if (!(obj instanceof TecajneListe)) return false;
    	if (obj == null) return false;
    	TecajneListe tl = (TecajneListe) obj;
    	return tl.datumPrimjene == datumPrimjene && tl.oznakaValute.equals(oznakaValute);
    }

}