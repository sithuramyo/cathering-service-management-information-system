package com.CSMIS.CSMISKhaingFamily.Model;

import com.CSMIS.CSMISKhaingFamily.Entity.OrderDetail;

public class OrderDetailWithWeeklyOrderDTO {
	 private OrderDetail orderDetail;
	    private double employeecost;
	    private double datcost;
	    private int totalpax;
		public OrderDetail getOrderDetail() {
			return orderDetail;
		}
		public void setOrderDetail(OrderDetail orderDetail) {
			this.orderDetail = orderDetail;
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
		public OrderDetailWithWeeklyOrderDTO() {
			super();
			// TODO Auto-generated constructor stub
		}
	    
}
