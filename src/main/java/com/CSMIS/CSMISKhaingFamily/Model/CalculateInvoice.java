package com.CSMIS.CSMISKhaingFamily.Model;

public class CalculateInvoice {

	private Double totalAmount;
	private Double dailyPrice;
	private Double datCost;
	private Double empCost;
	private int quantity;
	private String currentDate;
	private String invoiceDate;
	private String resName;
	private String dayOfWeek;
	private String avoidMeat;
	private int extrapax;
	private String doorlogid;
	
	
	public String getDoorlogid() {
		return doorlogid;
	}
	public void setDoorlogid(String doorlogid) {
		this.doorlogid = doorlogid;
	}
	public int getExtrapax() {
		return extrapax;
	}
	public void setExtrapax(int extrapax) {
		this.extrapax = extrapax;
	}
	public String getAvoidMeat() {
		return avoidMeat;
	}
	public void setAvoidMeat(String avoidMeat) {
		this.avoidMeat = avoidMeat;
	}
	public String getDayOfWeek() {
		return dayOfWeek;
	}
	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Double getDailyPrice() {
		return dailyPrice;
	}
	public void setDailyPrice(Double dailyPrice) {
		this.dailyPrice = dailyPrice;
	}
	public Double getDatCost() {
		return datCost;
	}
	public void setDatCost(Double datCost) {
		this.datCost = datCost;
	}
	public Double getEmpCost() {
		return empCost;
	}
	public void setEmpCost(Double empCost) {
		this.empCost = empCost;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getCurrentDate() {
		return currentDate;
	}
	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}
	public String getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public String getResName() {
		return resName;
	}
	public void setResName(String resName) {
		this.resName = resName;
	}
	public CalculateInvoice() {
	
	}
	
}
