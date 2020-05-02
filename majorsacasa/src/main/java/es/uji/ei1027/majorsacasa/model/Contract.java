package es.uji.ei1027.majorsacasa.model;

import java.sql.Date;

public class Contract {
	private int number;
	private String companyCIF;
	private Date dateBeginning;
	private Date dateEnding;
	private String description;
	private ServiceType serviceType;
	private int quantityServices;
	private String unitsOfMeasure;
	private int priceUnit;
	
	
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getCompanyCIF() {
		return companyCIF;
	}
	public void setCompanyCIF(String companyCIF) {
		this.companyCIF = companyCIF;
	}
	public Date getDateBeginning() {
		return dateBeginning;
	}
	public void setDateBeginning(Date dateBeginning) {
		this.dateBeginning = dateBeginning;
	}
	public Date getDateEnding() {
		return dateEnding;
	}
	public void setDateEnding(Date dateEnding) {
		this.dateEnding = dateEnding;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getQuantityServices() {
		return quantityServices;
	}
	public void setQuantityServices(int quantityServices) {
		this.quantityServices = quantityServices;
	}
	
	public String getUnitsOfMeasure() {
		return unitsOfMeasure;
	}
	public void setUnitsOfMeasure(String unitsOfMeasure) {
		this.unitsOfMeasure = unitsOfMeasure;
	}
	public int getPriceUnit() {
		return priceUnit;
	}
	public void setPriceUnit(int priceUnit) {
		this.priceUnit = priceUnit;
	}
	public ServiceType getServiceType() {
		return serviceType;
	}
	public void setServiceType(ServiceType serviceType) {
		this.serviceType = serviceType;
	}
	@Override
	public String toString() {
		return "Contract [number=" + number + ", companyCIF=" + companyCIF + ", dateBeginning=" + dateBeginning
				+ ", dateEnding=" + dateEnding + ", description=" + description + ", serviceType=" + serviceType
				+ ", quantityServices=" + quantityServices + ", unitsOfMesure=" + unitsOfMeasure + ", priceUnit="
				+ priceUnit + "]";
	}
	
}


