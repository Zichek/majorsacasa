package es.uji.ei1027.majorsacasa.model;

public class Company {
	
	private String name;
	private String CIF;
	private String password;
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	private String address;
	private String contactPersonName;
	private String contactPersonPhoneNumber;
	private String contactPersonEmail;
	private ServiceType serviceType;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCIF() {
		return CIF;
	}
	public void setCIF(String cIF) {
		CIF = cIF;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContactPersonName() {
		return contactPersonName;
	}
	public void setContactPersonName(String contactPersonName) {
		this.contactPersonName = contactPersonName;
	}
	public String getContactPersonEmail() {
		return contactPersonEmail;
	}
	public void setContactPersonEmail(String contactPersonEmail) {
		this.contactPersonEmail = contactPersonEmail;
	}
	public ServiceType getServiceType() {
		return serviceType;
	}
	public void setServiceType(ServiceType serviceType) {
		this.serviceType = serviceType;
	}
	public String getContactPersonPhoneNumber() {
		return contactPersonPhoneNumber;
	}
	public void setContactPersonPhoneNumber(String contactPersonPhoneNumber) {
		this.contactPersonPhoneNumber = contactPersonPhoneNumber;
	}
	@Override
	public String toString() {
		return "Company [name=" + name + ", CIF=" + CIF + ", password=" + password + ", address=" + address
				+ ", contactPersonName=" + contactPersonName + ", contactPersonPhoneNumber=" + contactPersonPhoneNumber
				+ ", contactPersonEmail=" + contactPersonEmail + ", serviceType=" + serviceType + "]";
	}
	
}
