package es.uji.ei1027.majorsacasa.model;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class Contract {
	private int number;
	private String companyCIF;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateBeginning;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateEnding;
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
	public LocalDate getDateBeginning() {
		return dateBeginning;
	}
	public void setDateBeginning(LocalDate dateBeginning) {
		this.dateBeginning = dateBeginning;
	}
	public LocalDate getDateEnding() {
		return dateEnding;
	}
	public void setDateEnding(LocalDate dateEnding) {
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


