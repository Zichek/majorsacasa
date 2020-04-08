package es.uji.ei1027.majorsacasa.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class Invoice {
	private int number;
	private String elderlyDNI;
	private int requestNumber;
	@DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
	private LocalDate date;
	private int amount;
	private String concept;
	
	
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getElderlyDNI() {
		return elderlyDNI;
	}
	public void setElderlyDNI(String elderlyDNI) {
		this.elderlyDNI = elderlyDNI;
	}
	public int getRequestNumber() {
		return requestNumber;
	}
	public void setRequestNumber(int requestNumber) {
		this.requestNumber = requestNumber;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getConcept() {
		return concept;
	}
	public void setConcept(String concept) {
		this.concept = concept;
	}
	@Override
	public String toString() {
		return "Invoice [number=" + number + ", elderlyDNI=" + elderlyDNI + ", requestNumber=" + requestNumber
				+ ", date=" + date + ", amount=" + amount + ", concept=" + concept + "]";
	}
	
	
}
