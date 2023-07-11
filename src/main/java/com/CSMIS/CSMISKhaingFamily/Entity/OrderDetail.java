package com.CSMIS.CSMISKhaingFamily.Entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "orderdetail")
public class OrderDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int quantity;
	private String dates;
	private int extrapax;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "weekly_order_id", nullable = false)
	private WeeklyOrder weeklyOrder;

	// Constructors, getters, and setters

	public OrderDetail() {
	}

	public OrderDetail(int quantity, String dates,int extrapax,WeeklyOrder weeklyOrder) {
		this.quantity = quantity;
		this.dates = dates;
		this.extrapax = extrapax;
		this.weeklyOrder = weeklyOrder;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getExtrapax() {
		return extrapax;
	}

	public void setExtrapax(int extrapax) {
		this.extrapax = extrapax;
	}

	public void setDates(String dates) {
		this.dates = dates;
	}

	public WeeklyOrder getWeeklyOrder() {
		return weeklyOrder;
	}

	public void setWeeklyOrder(WeeklyOrder weeklyOrder) {
		this.weeklyOrder = weeklyOrder;
	}
}
