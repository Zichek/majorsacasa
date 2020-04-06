package es.uji.ei1027.majorsacasa.model;

public class SocialWorker {
	
	public String name;
	public String elderlyDNI;
	public String userCAS;
	public String pwd;
	public int phoneNumber;
	public String email;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getElderlyDNI() {
		return elderlyDNI;
	}
	public void setElderlyDNI(String elderlyDNI) {
		this.elderlyDNI = elderlyDNI;
	}
	public String getUserCAS() {
		return userCAS;
	}
	public void setUserCAS(String userCAS) {
		this.userCAS = userCAS;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public int getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "SocialWorker [name=" + name + ", elderlyDNI=" + elderlyDNI + ", userCAS=" + userCAS + ", pwd=" + pwd
				+ ", phoneNumber=" + phoneNumber + ", email=" + email + "]";
	}
	
	
}
