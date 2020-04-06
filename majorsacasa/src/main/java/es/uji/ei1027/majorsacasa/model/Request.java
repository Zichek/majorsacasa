package es.uji.ei1027.majorsacasa.model;

import java.sql.Date;

public class Request {
	private int number;
	private String elderlyDNI;
	private int contractNumber;
	private String serviceType;
	private Date creationDate;
	private String state;
	private Date approvedDate;
	private Date rejectedDate;
	private String comments;
	private Date endDate;
	
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
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceTyoe(String serviceType) {
		this.serviceType = serviceType;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Date getApprovedDate() {
		return approvedDate;
	}
	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}
	public Date getRejectedDate() {
		return rejectedDate;
	}
	public void setRejectedDate(Date rejectedDate) {
		this.rejectedDate = rejectedDate;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
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
