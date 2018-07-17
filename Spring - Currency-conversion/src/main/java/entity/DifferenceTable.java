package entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table
@NamedQuery(query = "delete from DifferenceTable d", name = "DifferenceTable.deleteContentOfDifferenceTable")
public class DifferenceTable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Temporal(TemporalType.DATE)
	private Date applicationDate;

	private String currencyCode;
	@Column(columnDefinition="Decimal(8,6)")
	private double middleRateDifference;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}	
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public double getMiddleRateDifference() {
		return middleRateDifference;
	}
	public void setMiddleRateDifference(double middleRateDifference) {
		this.middleRateDifference = middleRateDifference;
	}
	public Date getApplicationDate() {
		return applicationDate;
	}
	public void setApplicationDate(Date applicationDate) {
		this.applicationDate = applicationDate;
	}
	@Override
	public int hashCode() {
        return (int) applicationDate.hashCode() + currencyCode.hashCode() ;
    }
	@Override
    public boolean equals(Object obj) {
    	if (obj == this) return true;
    	if (!(obj instanceof DifferenceTable)) return false;
    	if (obj == null) return false;
    	DifferenceTable differenceTableObj = (DifferenceTable) obj;
    	return differenceTableObj.applicationDate == applicationDate && differenceTableObj.currencyCode.equals(currencyCode);
    }
}