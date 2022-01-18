package com.ShareClass;

public class SessionBeam {
	String userid="1001",userType="admin",username;
	int invoiceType,invoice;
	public Boolean PatientModule;
	public Boolean ManagementModule;
	public Boolean IndoorPharmacyModule;
	public Boolean OutdoorPharmacyModule;
	public Boolean RestaurantModule;
	public Boolean hospitalStockModule;
	public Boolean settingModule;
	public Boolean billingModule;
	public Boolean labModule;
	public Boolean accountsModule;
	public Boolean misModule;
	public void setUserName(String userName){
		username=userName;
	}
	public String getUserName(){
		return username;
	}
	public void setUserId(String userId){
		userid=userId;
	}
	public String getUserId(){
		return userid;
	}
	public void setInvoiceType(int InvoiceType){
		invoiceType=InvoiceType;
	}
	public int getInvoiceType(){
		return invoiceType;
	}
	public void setInvoiceNo(int Invoice){
		invoice=Invoice;
	}
	public int getInvoiceId(){
		return invoice;
	}
	
	public void setUserType(String UserType){
		userType="admin";
		this.userType=UserType;
	}
	public String getUserType(){
		return userType;
	}
}
