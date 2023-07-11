package com.CSMIS.CSMISKhaingFamily.Controller;

import java.security.Principal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.CSMIS.CSMISKhaingFamily.DAO.AvoidCountRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.AvoidMeatRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.CreativeInvoiceRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.CreativeInvoiceService;
import com.CSMIS.CSMISKhaingFamily.DAO.OperatorRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.OrderDetailViewRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.RestaurantInfoRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.WeeklyInvoiceRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.WeeklyOrderRepository;
import com.CSMIS.CSMISKhaingFamily.Entity.CreativeInvoice;
import com.CSMIS.CSMISKhaingFamily.Entity.Operator;
import com.CSMIS.CSMISKhaingFamily.Entity.OrderDetail;
import com.CSMIS.CSMISKhaingFamily.Entity.OrderDetailView;
import com.CSMIS.CSMISKhaingFamily.Entity.RestaurantInfo;
import com.CSMIS.CSMISKhaingFamily.DAO.RestaurantService;
import com.CSMIS.CSMISKhaingFamily.Entity.WeeklyInvoice;
import com.CSMIS.CSMISKhaingFamily.Entity.WeeklyOrder;
import com.CSMIS.CSMISKhaingFamily.Model.CalculateInvoice;
import com.CSMIS.CSMISKhaingFamily.Model.CreativeInvoiceBean;
import com.CSMIS.CSMISKhaingFamily.Model.OrderDetailViewBean;
import com.CSMIS.CSMISKhaingFamily.Model.WeeklyInvoiceRequest;


@Controller
public class WeeklyInvoiceController {

	@Autowired
	AvoidMeatRepository avoidRepo;
	
	@Autowired 
	CreativeInvoiceService invoiceService;
	
	@Autowired 
	CreativeInvoiceRepository invoiceRepo;
	
	@Autowired
	OperatorRepository operatorRepository;
	
	@Autowired
	AvoidCountRepository avoidcountRepo;
	
	@Autowired
	OrderDetailViewRepository op;
	
	@Autowired
	WeeklyInvoiceRepository weeklyInvoiceRepository;
	
	@Autowired
	WeeklyOrderRepository weeklyOrderRepository;
	
	@Autowired
	RestaurantService rs;
	
	@Autowired
	RestaurantInfoRepository resRepo;
	
	@GetMapping("/admin/invoiceCreateError")
	public ModelAndView invoiceCreateFail(HttpSession session) {
		CreativeInvoiceBean ci = (CreativeInvoiceBean) session.getAttribute("ci");
		if(ci == null) {
			ci = new CreativeInvoiceBean ();
		}
		
		return new ModelAndView("invoicecreate","ci",ci);
	}
	
	@GetMapping("/admin/invoiceDuplicateError")
	public ModelAndView invoiceDuplicateError(HttpSession session) {
		CreativeInvoiceBean ci = (CreativeInvoiceBean) session.getAttribute("ci");
		if(ci == null) {
			ci = new CreativeInvoiceBean ();
		}
		
		return new ModelAndView("invoicecreate","ci",ci);
	}
	
	@GetMapping("/admin/invoiceCreateSuccess")
	public String invoiceCreateSuccess(HttpSession session) {
		
		return "weeklyinvoice";
	}
	
	@RequestMapping(value="/admin/creativeInvoice",method=RequestMethod.POST)
	public String invoiceRegister(@ModelAttribute ("ci") CreativeInvoiceBean bean,ModelMap model,HttpSession session,RedirectAttributes redireactAttributes, HttpServletRequest request) {
		
		
		//System.out.println(bean.getEnddate());
		if(bean == null || bean.equals("")) {
			System.out.println("Register Failed!");
		}
		
		else {
			
			String startDateStr = bean.getStartdate();
		    String endDateStr = bean.getEnddate();
		    
		    DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		    LocalDate startDate = LocalDate.parse(startDateStr, outputFormatter);
		    LocalDate endDate = LocalDate.parse(endDateStr, outputFormatter);
		    LocalDate currentDate = startDate;
		    OrderDetailView ov = new OrderDetailView();
		    List<OrderDetailView> ovlist = new ArrayList();
		    List<OrderDetail> olist = new ArrayList<>();
		    List<CalculateInvoice> clist = new ArrayList<CalculateInvoice>();
		    List<Double> empList = new ArrayList<>();
			List<Double> datList= new ArrayList<>();
		    int total = 0,result = 0;
	        double totalAmount = 0.0;
	        
	        int totalpax = 0;
	        while (!currentDate.isAfter(endDate)) {
	            System.out.println(currentDate);
	            
	            ov = op.findLastByDates(currentDate.toString());
	            if(ov != null) {
		        ovlist.add(ov);
	            }
	            currentDate = currentDate.plusDays(1);
	        }
	        if(ovlist.size() == 0) {
	        	session.setAttribute("ci", bean);
	        	redireactAttributes.addFlashAttribute("message", "Invoice voucher creation failed.");
	        	return "redirect:/admin/invoiceCreateError";
	        }
	         List<WeeklyInvoice> list = weeklyInvoiceRepository.findAll();
	         currentDate = LocalDate.now();
	         
	    
	         String checkdate = "";int paymentDate = 0;int invoiceCheckDate = 0;
	         for(int i = 0;i < list.size();i++) {
	        	 // Get the start of the current week (Monday)
		         LocalDate startOfWeek = currentDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
		         
		         // Get the end of the current week (Sunday)
		         LocalDate endOfWeek = currentDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
		         
		         // Iterate over the days of the current week
		         LocalDate dateFirst = startOfWeek;
	        	 while (!dateFirst.isAfter(endOfWeek)) {
		             System.out.println(dateFirst);
		             
		             checkdate = dateFirst.toString();
		             if(list.get(i).getPaymentdate().equals(checkdate)) {
		            	 paymentDate = 1;
		             }
		             
		             dateFirst = dateFirst.plusDays(1);
		         }
	         }
	         LocalDate today = LocalDate.now();
	         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	         String  formattedDate= today.format(formatter);
	         System.out.println("fDate:" + formattedDate);
	         while (!currentDate.isAfter(endDate)) {
	        	System.out.println("cDate :" + currentDate.toString());
	            	 if(currentDate.toString().equals(formattedDate)) {
	            		 invoiceCheckDate = 1;
	            	
					
	             }
		            currentDate = currentDate.plusDays(1);
		        }
	         
	         
             System.out.println("Payment date :" + paymentDate);
             System.out.println("Invoice date :" + invoiceCheckDate);
	         if(paymentDate == 1 && invoiceCheckDate == 1) {
	        	 session.setAttribute("ci", bean);
		        	redireactAttributes.addFlashAttribute("message", "You have already invoice for this week.");
		        	return "redirect:/admin/invoiceDuplicateError";
	         }
	         
	        else {
	        	CreativeInvoice ci = new CreativeInvoice();
	    		
	    		ci.setId(bean.getId());
	    		ci.setCashier(bean.getCashier());
	    		ci.setApprovedby(bean.getApprovedby());
	    		ci.setEnddate(bean.getEnddate());
	    		RestaurantInfo rs = resRepo.findLastRow();
if(rs!=null) {
	ci.setResid(rs.getId());
}
	    		ci.setPaymethod(bean.getPaymethod());
	    		ci.setStartdate(bean.getStartdate());
	    		ci.setReceivedby(bean.getReceivedby());
	    		
	    		ci = invoiceRepo.save(ci);
	        	
	        	for(int i = 0;i < ovlist.size();i++) {
		        	OrderDetail od = new OrderDetail();
		        	ovlist.get(i).getWeekly_order_id();
		        	empList.add(ovlist.get(i).getEmployeecost());
		        	datList.add(ovlist.get(i).getDatcost());
		        	totalpax = ovlist.get(i).getTotalpax();
		        	String date = ovlist.get(i).getDates();
		        	int quantity = ovlist.get(i).getQuantity();
		        	int extrapax = ovlist.get(i).getExtrapax();
		        	od.setDates(date);
		        	od.setExtrapax(extrapax);
		        	od.setQuantity(quantity);
		        	
		        	olist.add(od);
		        }
		        
		        for(int i = 0;i < olist.size();i++) {
		        	CalculateInvoice cal = new CalculateInvoice();
		        	cal.setDayOfWeek(olist.get(i).getDates());
		        	cal.setExtrapax(olist.get(i).getExtrapax());
		        	cal.setQuantity(olist.get(i).getQuantity());
		        	total += olist.get(i).getExtrapax() + olist.get(i).getQuantity();
		        	double dailyprice = total * (empList.get(i) + datList.get(i));
		        	cal.setEmpCost(empList.get(i));
		        	cal.setDatCost(datList.get(i));
		        	cal.setDailyPrice(dailyprice);
		        	totalAmount += dailyprice;
		        	
		        	clist.add(cal);
		        	System.out.println(cal.getDayOfWeek());
		        	System.out.println(cal.getExtrapax());
		        	System.out.println(cal.getQuantity());
		        	if(olist.get(i).getExtrapax() != 0) {
		        		result = 1;
		        }
		        }
			    String eDateString = bean.getEnddate();
			    String sDateString = bean.getStartdate(); // The input date string in "yyyy-MM-dd" format
			    SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd"); // The input date format
			    SimpleDateFormat outputDateFormat = new SimpleDateFormat("ddMMyyyy"); // The desired output date format

			    Date sdate = null;
			    Date edate = null;
			    try {
			        sdate = inputDateFormat.parse(sDateString);
			        edate = inputDateFormat.parse(eDateString);// Parse the input date string to a Date object
			    } catch (ParseException e) {
			        e.printStackTrace();
			    }

			    String startDateString = outputDateFormat.format(sdate);
			    String endDateString = outputDateFormat.format(edate);
			    String description = bean.getId() + " : " + startDateString + "~" + endDateString;
			    session.setAttribute("description", description);
			    currentDate = LocalDate.now();
			    
			    
			    session.setAttribute("result", result);
			    session.setAttribute("currentDate", currentDate);
			    session.setAttribute("totalAmount", totalAmount);
			    String starttoenddate = bean.getStartdate() + " - " + bean.getEnddate();
			    String invoiceDate = bean.getStartdate() + "~" + bean.getEnddate();
			    session.setAttribute("starttoenddate", starttoenddate);
			    session.setAttribute("invoiceDate", invoiceDate);
			    session.setAttribute("list", clist);
			    session.setAttribute("cashier", bean.getCashier());
			    session.setAttribute("paymentmethod", bean.getPaymethod());
			    session.setAttribute("approvedby", bean.getApprovedby());
			    session.setAttribute("receivedby", bean.getReceivedby());
			    if(rs!=null) {
			    	session.setAttribute("resId", rs.getId());
			    }
			    
			    session.setAttribute("resName", bean.getResturantname());
			    session.setAttribute("resPhone", bean.getResph());
			    session.setAttribute("resAddress", bean.getResaddress());
			    session.setAttribute("cInvoiceId", bean.getId());
			    session.setAttribute("datCost", clist.get(0).getDatCost());
			    session.setAttribute("employeeCost", clist.get(0).getEmpCost());
			    session.setAttribute("total", total);
			    session.setAttribute("orderId", ovlist.get(0).getWeekly_order_id());
			    System.out.println("Week of total price is " + totalAmount);
			    
			    
	        }
		}
		redireactAttributes.addFlashAttribute("successMessage", "Congratulations! Your invoice voucher has been successfully created! 🎉.");
    	return "redirect:/admin/invoiceCreateSuccess";
	}
	
	@PostMapping("/admin/invoicePaid")
	@ResponseBody
	public ResponseEntity<?> invoidPaid(@RequestBody WeeklyInvoiceRequest request, HttpSession session) {
		System.out.println("here!!");
		String status = request.getStatus();
		String description = (String)session.getAttribute("description");
		String resId = (String)session.getAttribute("resId");
		String cId = (String)session.getAttribute("cInvoiceId");
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String paymentDate = currentDate.format(formatter);
		Double totalAmount = (Double) session.getAttribute("totalAmount");
		String invoiceDate = (String) session.getAttribute("invoiceDate");
		Double datCost = (Double) session.getAttribute("datCost");
		Double employeeCost = (Double) session.getAttribute("employeeCost");
		Integer total = (Integer) session.getAttribute("total");
		Long orderId = (Long) session.getAttribute("orderId");
		ArrayList<CalculateInvoice> list = (ArrayList<CalculateInvoice>) session.getAttribute("clist");

		WeeklyInvoice wi = new WeeklyInvoice();
		wi.setCreateinvoiceid(cId);
		wi.setDescription(description);
		wi.setInvoicedate(invoiceDate);
		wi.setPaymentdate(paymentDate);
		wi.setStatus(request.getStatus());
		wi.setResid(resId);
		wi.setTotalamount(totalAmount);
		wi.setTotalpax(total);
		wi.setDatcost(datCost);
		wi.setEmployeecost(employeeCost);
		wi.setOrderid(orderId);
		Double price = datCost + employeeCost;
		wi.setPrice(price);
		
		wi = weeklyInvoiceRepository.save(wi);
 		if(wi != null && status.equals("Paid")) {
 			String successMessage = "Paid Successful.";
	        return ResponseEntity.ok().body(successMessage);
 		}
 		if(wi == null && !status.equals("Paid")) {
 			String errorMessage = "Failed to paid.";
 	        return ResponseEntity.badRequest().body("{\"message\": \"" + errorMessage + "\"}");
 		}
 		
 		if(wi != null && status.equals("Unpaid")) {
 			String successMessage = "Save Successful.";
	        return ResponseEntity.ok().body(successMessage);
 		}
 		else {
 			String errorMessage = "Failed to save .";
 	        return ResponseEntity.badRequest().body("{\"message\": \"" + errorMessage + "\"}");
 		}
 		
 	}
	@RequestMapping(value="/admin/orderReport" , method=RequestMethod.GET)
	public String orderReport (Principal prin,ModelMap model,HttpSession session) {
		List<WeeklyOrder> list = weeklyOrderRepository.datesList();
		List<OrderDetailView> ovlist = new ArrayList();
		List<OrderDetailViewBean> beanList = new ArrayList();
		Double totalCost = 0.0;
		for(int i = 0;i < list.size();i++) {
			
			int quantity = 0;int extraPax = 0;
			OrderDetailViewBean ov = new OrderDetailViewBean();
			ovlist = op.findByWeeklyOrderId(list.get(i).getId());
			Operator operator = operatorRepository.findByemployeeEmail(prin.getName());
			for(int j = 0;j < ovlist.size();j++) {
				quantity += ovlist.get(j).getQuantity();
				extraPax += ovlist.get(j).getExtrapax();
			}
			ov.setDatcost(list.get(i).getDatcost());
			ov.setEmployeecost(list.get(i).getEmployeecost());
			ov.setTotalpax(list.get(i).getTotalpax());
			ov.setQuantity(quantity);
			ov.setExtrapax(extraPax);
			ov.setOrderdate(list.get(i).getOrderdate());
			ov.setTotalCost(list.get(i).getTotalCost());
			ov.setAdminName(operator.getEmployeeName());
			
			totalCost += list.get(i).getTotalCost();
			beanList.add(ov);
		}
		
		session.setAttribute("orlist", beanList);
		session.setAttribute("totalAmount", totalCost);
		return "orderReport";
	}
	
	@GetMapping("/admin/orderReportListSearchError")
	public String orderSearchError(HttpSession session) {
		
		return "orderReport";
	}
	
	@GetMapping("/admin/orderReportListSearchSuccess")
	public String orderSearchSuccess(HttpSession session) {
		
		return "orderReport";
	}
	@GetMapping("/admin/orderReportListResetSuccess")
	public String orderResetSuccess(HttpSession session) {
		
		return "orderReport";
	}
	@GetMapping("/admin/orderReportListSearchNullError")
	public String orderSearchNullError(HttpSession session) {
		
		return "orderReport";
	}
	
	@PostMapping("/admin/orderReportList")
	public String searchOrderReport(HttpServletRequest request,HttpSession session,RedirectAttributes redirectAttributes) {
		String startDate = request.getParameter("start_date");
		String endDate = request.getParameter("end_date");
		List<WeeklyOrder> list = new ArrayList();
		if(startDate == "" || endDate == "" ) {
				
			list = weeklyOrderRepository.datesList();
			List<OrderDetailView> ovlist = new ArrayList();
			List<OrderDetailViewBean> beanList = new ArrayList();
			Double totalCost = 0.0;
			for(int i = 0;i < list.size();i++) {
				
				int quantity = 0;int extraPax = 0;
				OrderDetailViewBean ov = new OrderDetailViewBean();
				ovlist = op.findByWeeklyOrderId(list.get(i).getId());
				for(int j = 0;j < ovlist.size();j++) {
					quantity += ovlist.get(j).getQuantity();
					extraPax += ovlist.get(j).getExtrapax();
				}
				ov.setDatcost(list.get(i).getDatcost());
				ov.setEmployeecost(list.get(i).getEmployeecost());
				ov.setTotalpax(list.get(i).getTotalpax());
				ov.setQuantity(quantity);
				ov.setExtrapax(extraPax);
				ov.setOrderdate(list.get(i).getOrderdate());
				ov.setTotalCost(list.get(i).getTotalCost());
				totalCost += list.get(i).getTotalCost();
				beanList.add(ov);
			}
			
			session.setAttribute("orlist", beanList);
			session.setAttribute("totalAmount", totalCost);
			redirectAttributes.addFlashAttribute("nullErrorMessage", "Please enter valid start date and end date.");
            return "redirect:/admin/orderReportListSearchNullError";
		}
		else {
			List<WeeklyOrder> wiList = weeklyOrderRepository.findByOrderdateBetween(startDate,endDate);

			List<OrderDetailView> ovlist = new ArrayList();
			List<OrderDetailViewBean> beanList = new ArrayList();
			Double totalCost = 0.0;
			for(int i = 0;i < wiList.size();i++) {
				
				int quantity = 0;int extraPax = 0;
				OrderDetailViewBean ov = new OrderDetailViewBean();
				ovlist = op.findByWeeklyOrderId(wiList.get(i).getId());
				for(int j = 0;j < ovlist.size();j++) {
					quantity += ovlist.get(j).getQuantity();
					extraPax += ovlist.get(j).getExtrapax();
				}
				ov.setDatcost(wiList.get(i).getDatcost());
				ov.setEmployeecost(wiList.get(i).getEmployeecost());
				ov.setTotalpax(wiList.get(i).getTotalpax());
				ov.setQuantity(quantity);
				ov.setExtrapax(extraPax);
				ov.setOrderdate(wiList.get(i).getOrderdate());
				ov.setTotalCost(wiList.get(i).getTotalCost());
				totalCost += wiList.get(i).getTotalCost();
				beanList.add(ov);
			}
			
			session.setAttribute("orlist", beanList);
			session.setAttribute("totalAmount", totalCost);
			if(beanList.size() == 0) {
				session.setAttribute("orlist", beanList);
				session.setAttribute("totalAmount", 0.0);
				redirectAttributes.addFlashAttribute("errorMessage", "Searching failed.");
	            return "redirect:/admin/orderReportListSearchError";
				
			}
			
			if(beanList.size() != 0) {
				session.setAttribute("orlist", beanList);
				session.setAttribute("totalAmount", totalCost);
				redirectAttributes.addFlashAttribute("successMessage", "Searching successful.");
	            return "redirect:/admin/orderReportListSearchSuccess";
			}
			
		}
		
		
		return "orderReport";
	}
	@GetMapping("/admin/orderReportListReset")
	public String resetOrderReportReset(RedirectAttributes redirectAttributes,HttpSession session) {
		
		redirectAttributes.addFlashAttribute("resetSuccessMessage", "Reset successful.");
		List<WeeklyOrder> list = weeklyOrderRepository.datesList();
		List<OrderDetailView> ovlist = new ArrayList();
		List<OrderDetailViewBean> beanList = new ArrayList();
		Double totalCost = 0.0;
		for(int i = 0;i < list.size();i++) {
			
			int quantity = 0;int extraPax = 0;
			OrderDetailViewBean ov = new OrderDetailViewBean();
			ovlist = op.findByWeeklyOrderId(list.get(i).getId());
			for(int j = 0;j < ovlist.size();j++) {
				quantity += ovlist.get(j).getQuantity();
				extraPax += ovlist.get(j).getExtrapax();
			}
			ov.setDatcost(list.get(i).getDatcost());
			ov.setEmployeecost(list.get(i).getEmployeecost());
			ov.setTotalpax(list.get(i).getTotalpax());
			ov.setQuantity(quantity);
			ov.setExtrapax(extraPax);
			ov.setOrderdate(list.get(i).getOrderdate());
			ov.setTotalCost(list.get(i).getTotalCost());
			totalCost += list.get(i).getTotalCost();
			beanList.add(ov);
		}
		System.out.println("here!!!!");
		System.out.println("Beanlist :" + beanList.size());
		session.setAttribute("orlist", beanList);
		session.setAttribute("totalAmount", totalCost);
		return "redirect:/admin/orderReportListResetSuccess";
	}
}
