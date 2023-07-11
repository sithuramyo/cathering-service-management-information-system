package com.CSMIS.CSMISKhaingFamily.Model;

public class OrderDetailViewBean {

private Long id;
    
    private String dates;
    
    private int extrapax;
    
    private int quantity;
    
    private Long weekly_order_id;
    
    private double employeecost;
    
    private double datcost;
    
    private int totalpax;
    private String orderdate;
    private String doorlogid;
    private Double totalCost;
    private String adminName;
    private String starttoenddate;
    
	
	public String getStarttoenddate() {
		return starttoenddate;
	}
	public void setStarttoenddate(String starttoenddate) {
		this.starttoenddate = starttoenddate;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public Double getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDates() {
		return dates;
	}
	public void setDates(String dates) {
		this.dates = dates;
	}
	public int getExtrapax() {
		return extrapax;
	}
	public void setExtrapax(int extrapax) {
		this.extrapax = extrapax;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Long getWeekly_order_id() {
		return weekly_order_id;
	}
	public void setWeekly_order_id(Long weekly_order_id) {
		this.weekly_order_id = weekly_order_id;
	}
	public double getEmployeecost() {
		return employeecost;
	}
	public void setEmployeecost(double employeecost) {
		this.employeecost = employeecost;
	}
	public double getDatcost() {
		return datcost;
	}
	public void setDatcost(double datcost) {
		this.datcost = datcost;
	}
	public int getTotalpax() {
		return totalpax;
	}
	public void setTotalpax(int totalpax) {
		this.totalpax = totalpax;
	}
	public String getOrderdate() {
		return orderdate;
	}
	public void setOrderdate(String orderdate) {
		this.orderdate = orderdate;
	}
	public String getDoorlogid() {
		return doorlogid;
	}
	public void setDoorlogid(String doorlogid) {
		this.doorlogid = doorlogid;
	}
    
}
