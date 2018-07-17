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
@NamedQueries({
	 @NamedQuery(query = "Select c.middleRate from CurrencyRates c where c.currencyCode = ?1 and c.applicationDate = ?2", 
  		   name = "TecajneListe.srednjiTecajPoDatumuIValuti"),
     @NamedQuery(query = "delete from CurrencyRates c", name = "CurrencyRates.deleteContentOfTableCurrencyRates"),
     @NamedQuery(query = "select c.applicationDate, c.currencyCode, c.middleRate from CurrencyRates c", 
    			name = "CurrencyRates.selectApplicationDateCurrencyCodeAndMiddleRate"),
     @NamedQuery(query = "select c.applicationDate from CurrencyRates c", 
		name = "CurrencyRates.selectApplicationDateFromCurrencyRates"),
}) 

public class CurrencyRates {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int rateDesignation;
	private int currencyNumber;
	private String currencyCode;
	private int numberOfUnits;
	
	@Temporal(TemporalType.DATE)	
	private Date creationDate;
	@Temporal(TemporalType.DATE)
	private Date applicationDate;
	
	@Column(columnDefinition="Decimal(8,6)")
	private double buyingRate;
	@Column(columnDefinition="Decimal(8,6)")
	private double middleRate;
	@Column(columnDefinition="Decimal(8,6)")
	private double sellingRate;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRateDesignation() {
		return rateDesignation;
	}
	public void setRateDesignation(int rateDesignation) {
		this.rateDesignation = rateDesignation;
	}
	public int getCurrencyNumber() {
		return currencyNumber;
	}
	public void setCurrencyNumber(int currencyNumber) {
		this.currencyNumber = currencyNumber;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public int getNumberOfUnits() {
		return numberOfUnits;
	}
	public void setNumberOfUnits(int numberOfUnits) {
		this.numberOfUnits = numberOfUnits;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Date getApplicationDate() {
		return applicationDate;
	}
	public void setApplicationDate(Date applicationDate) {
		this.applicationDate = applicationDate;
	}
	public double getBuyingRate() {
		return buyingRate;
	}
	public void setBuyingRate(double buyingRate) {
		this.buyingRate = buyingRate;
	}
	public double getMiddleRate() {
		return middleRate;
	}
	public void setMiddleRate(double middleRate) {
		this.middleRate = middleRate;
	}
	public double getSellingRate() {
		return sellingRate;
	}
	public void setSellingRate(double sellingRate) {
		this.sellingRate = sellingRate;
	}

	@Override
	public int hashCode() {
        return (int) applicationDate.hashCode() + currencyCode.hashCode() ;
    }

	@Override
    public boolean equals(Object obj) {
    	if (obj == this) return true;
    	if (!(obj instanceof CurrencyRates)) return false;
    	if (obj == null) return false;
    	CurrencyRates currencyRatesObj = (CurrencyRates) obj;
    	return currencyRatesObj.applicationDate == applicationDate && currencyRatesObj.currencyCode.equals(currencyCode);
    }
}