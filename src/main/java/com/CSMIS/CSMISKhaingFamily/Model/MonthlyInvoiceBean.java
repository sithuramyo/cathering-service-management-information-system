package com.CSMIS.CSMISKhaingFamily.Model;

public class MonthlyInvoiceBean {
	 
     private String description;
     private String cashier;
     private String receivedby;
     private String approvedby;
     private int totalpax;
     private Double price;
     private Double totalamount;
     private String paymentdate;
     private String status;
     private String paymentmethod;
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCashier() {
		return cashier;
	}
	public void setCashier(String cashier) {
		this.cashier = cashier;
	}
	public String getReceivedby() {
		return receivedby;
	}
	public void setReceivedby(String receivedby) {
		this.receivedby = receivedby;
	}
	public String getApprovedby() {
		return approvedby;
	}
	public void setApprovedby(String approvedby) {
		this.approvedby = approvedby;
	}
	public int getTotalpax() {
		return totalpax;
	}
	public void setTotalpax(int totalpax) {
		this.totalpax = totalpax;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getTotalamount() {
		return totalamount;
	}
	public void setTotalamount(Double totalamount) {
		this.totalamount = totalamount;
	}
	public String getPaymentdate() {
		return paymentdate;
	}
	public void setPaymentdate(String paymentdate) {
		this.paymentdate = paymentdate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPaymentmethod() {
		return paymentmethod;
	}
	public void setPaymentmethod(String paymentmethod) {
		this.paymentmethod = paymentmethod;
	}
	public MonthlyInvoiceBean() {
		
	}
     
     
     
}
