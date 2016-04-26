package entity;

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
@NamedQuery(query = "delete from RazlikovnaTablica e", name = "RazlikovnaTablica.deleteContentFromRazlikovnaTablica")
public class RazlikovnaTablica {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	
	@Temporal(TemporalType.DATE)
	private Date datumPrimjene;

	private String oznakaValute;
	@Column(columnDefinition="Decimal(8,6)")
	private double srednjiTecajRazlika;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}	

	public String getOznakaValute() {
		return oznakaValute;
	}

	public void setOznakaValute(String oznakaValute) {
		this.oznakaValute = oznakaValute;
	}

	public double getSrednjiTecaj() {
		return srednjiTecajRazlika;
	}

	public void setSrednjiTecaj(double srednjiTecaj) {
		this.srednjiTecajRazlika = srednjiTecaj;
	}
	
	public int getid() {
		return id;
	}

	public void setid(int id) {
		this.id = id;
	}

	public Date getDatumPrimjene() {
		return datumPrimjene;
	}

	public void setDatumPrimjene(Date datumPrimjene) {
		this.datumPrimjene = datumPrimjene;
	}
	
	@Override
	public int hashCode() {
        return (int) datumPrimjene.hashCode() + oznakaValute.hashCode() ;
    }
	
	@Override
    public boolean equals(Object obj) {
    	if (obj == this) return true;
    	if (!(obj instanceof RazlikovnaTablica)) return false;
    	if (obj == null) return false;
    	RazlikovnaTablica rt = (RazlikovnaTablica) obj;
    	return rt.datumPrimjene == datumPrimjene && rt.oznakaValute.equals(oznakaValute);
    }
}