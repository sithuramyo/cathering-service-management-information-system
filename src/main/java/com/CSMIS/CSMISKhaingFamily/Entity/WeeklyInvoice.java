package com.CSMIS.CSMISKhaingFamily.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="weeklyinvoice")
public class WeeklyInvoice {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private Double employeecost;
	private Double datcost;
	
	private String paymentdate;
	
	private Double totalamount;
	private String invoicedate;
	@Column(name = "status", columnDefinition = "VARCHAR(255) DEFAULT 'unpaid'")
	private String status;
	private String description;
	private Integer totalpax;
	private Double price;
	private String resid;
	private String createinvoiceid;
	private Long orderid;
	
	
	public Long getOrderid() {
		return orderid;
	}
	public void setOrderid(Long orderid) {
		this.orderid = orderid;
	}
	public Double getEmployeecost() {
		return employeecost;
	}
	public void setEmployeecost(Double employeecost) {
		this.employeecost = employeecost;
	}
	public Double getDatcost() {
		return datcost;
	}
	public void setDatcost(Double datcost) {
		this.datcost = datcost;
	}
	public String getResid() {
		return resid;
	}
	public void setResid(String resid) {
		this.resid = resid;
	}
	public String getCreateinvoiceid() {
		return createinvoiceid;
	}
	public void setCreateinvoiceid(String createinvoiceid) {
		this.createinvoiceid = createinvoiceid;
	}
	public Integer getTotalpax() {
		return totalpax;
	}
	public void setTotalpax(Integer totalpax) {
		this.totalpax = totalpax;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getInvoicedate() {
		return invoicedate;
	}
	public void setInvoicedate(String invoicedate) {
		this.invoicedate = invoicedate;
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
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	
	public WeeklyInvoice() {
		
	}
	
	
}
