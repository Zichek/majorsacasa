package es.uji.ei1027.majorsacasa.model;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class Request {
	private int number;
	private String elderlyDNI;
	private int contractNumber;
	private ServiceType serviceType;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate creationDate;
	private State state;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate approvedDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate rejectedDate;
	private String comments;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate endDate;
	
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
	public int getContractNumber() {
		return contractNumber;
	}
	public void setContractNumber(int contractNumber) {
		this.contractNumber = contractNumber;
	}
	public ServiceType getServiceType() {
		return serviceType;
	}
	public void setServiceType(ServiceType serviceType) {
		this.serviceType = serviceType;
	}
	public LocalDate getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}	
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	public LocalDate getApprovedDate() {
		return approvedDate;
	}
	public void setApprovedDate(LocalDate approvedDate) {
		this.approvedDate = approvedDate;
	}
	public LocalDate getRejectedDate() {
		return rejectedDate;
	}
	public void setRejectedDate(LocalDate rejectedDate) {
		this.rejectedDate = rejectedDate;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	@Override
	public String toString() {
		return "Request [Number=" + number + ", elderlyDNI=" + elderlyDNI + ", contractNumber=" + contractNumber
				+ ", serviceType=" + serviceType + ", creationDate=" + creationDate + ", state=" + state
				+ ", approvedDate=" + approvedDate + ", rejectedDate=" + rejectedDate + ", comments=" + comments
				+ ", endDate=" + endDate + "]";
	}
	
	
}
