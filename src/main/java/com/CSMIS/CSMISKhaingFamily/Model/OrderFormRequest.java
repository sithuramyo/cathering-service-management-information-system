package com.CSMIS.CSMISKhaingFamily.Model;

import java.util.List;

public class OrderFormRequest {
    private List<Integer> quantity;
    private List<String> dayOfWeek;
    private List<String> avoidMeat;
    
    private String totalCount;
    private String employeeCost;
    private String datCost;
    // Add other fields as needed
	public List<Integer> getQuantity() {
		return quantity;
	}
	public void setQuantity(List<Integer> quantity) {
		this.quantity = quantity;
	}
	public List<String> getDayOfWeek() {
		return dayOfWeek;
	}
	public void setDayOfWeek(List<String> dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
	public List<String> getAvoidMeat() {
		return avoidMeat;
	}
	public void setAvoidMeat(List<String> avoidMeat) {
		this.avoidMeat = avoidMeat;
	}
	
	public OrderFormRequest() {
		
	}
	public String getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}
	public String getEmployeeCost() {
		return employeeCost;
	}
	public void setEmployeeCost(String employeeCost) {
		this.employeeCost = employeeCost;
	}
	public String getDatCost() {
		return datCost;
	}
	public void setDatCost(String datCost) {
		this.datCost = datCost;
	}
	
    
    // Add getters and setters for the fields
}
