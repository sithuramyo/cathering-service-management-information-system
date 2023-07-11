package com.CSMIS.CSMISKhaingFamily.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "orderdetailview")
public class OrderDetailView {

    @Id
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
    private String status;
    private String starttoenddate;
    
    // Constructors, getters, and setters

    public String getStarttoenddate() {
		return starttoenddate;
	}


	public void setStarttoenddate(String starttoenddate) {
		this.starttoenddate = starttoenddate;
	}


	public String getStatus() {
		return status;
	}

	
	public void setStatus(String status) {
		this.status = status;
	}

	public String getDoorlogid() {
		return doorlogid;
	}

	public void setDoorlogid(String doorlogid) {
		this.doorlogid = doorlogid;
	}

	public String getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(String orderdate) {
		this.orderdate = orderdate;
	}

	public OrderDetailView() {
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

	public OrderDetailView(Long id, String dates, int extrapax, int quantity, Long weekly_order_id, double employeecost,
			double datcost, int totalpax, String orderdate, String doorlogid) {
		super();
		this.id = id;
		this.dates = dates;
		this.extrapax = extrapax;
		this.quantity = quantity;
		this.weekly_order_id = weekly_order_id;
		this.employeecost = employeecost;
		this.datcost = datcost;
		this.totalpax = totalpax;
		this.orderdate = orderdate;
		this.doorlogid = doorlogid;
	}
    
}
