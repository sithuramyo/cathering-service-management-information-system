package com.CSMIS.CSMISKhaingFamily.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "weeklyorder")
public class WeeklyOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private int totalpax;
    
    private String starttoenddate;
    private double employeecost;
    private double datcost;
    private String doorlogid;
    private String orderdate;
    private double totalCost;
    private String status;
    
    
    public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
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

	@OneToMany(mappedBy = "weeklyOrder", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderDetail> orderDetails;

    // Constructors, getters, and setters

    public WeeklyOrder() {
    }

    public WeeklyOrder(int totalpax, String starttoenddate, double employeecost, double datcost) {
       
        this.totalpax = totalpax;
        
        this.starttoenddate = starttoenddate;
        this.employeecost = employeecost;
        this.datcost = datcost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

   
    public int getTotalpax() {
        return totalpax;
    }

    public void setTotalpax(int totalpax) {
        this.totalpax = totalpax;
    }

    public String getStarttoenddate() {
        return starttoenddate;
    }

    public void setStarttoenddate(String starttoenddate) {
        this.starttoenddate = starttoenddate;
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

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
