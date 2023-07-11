package com.CSMIS.CSMISKhaingFamily.Controller;

import java.security.Principal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.CSMIS.CSMISKhaingFamily.DAO.AvoidCountRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.AvoidMeatRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.CreativeInvoiceRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.CreativeInvoiceService;
import com.CSMIS.CSMISKhaingFamily.DAO.HolidayRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.OperatorRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.OrderDetailService;
import com.CSMIS.CSMISKhaingFamily.DAO.OrderDetailViewRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.WeeklyInvoiceService;
import com.CSMIS.CSMISKhaingFamily.DAO.WeeklyOrderRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.WeeklyOrderService;
import com.CSMIS.CSMISKhaingFamily.Entity.AvoidMeat;
import com.CSMIS.CSMISKhaingFamily.Entity.HolidayData;
import com.CSMIS.CSMISKhaingFamily.Entity.Operator;
import com.CSMIS.CSMISKhaingFamily.Entity.OrderDetail;
import com.CSMIS.CSMISKhaingFamily.Entity.OrderDetailView;
import com.CSMIS.CSMISKhaingFamily.Entity.WeeklyOrder;
import com.CSMIS.CSMISKhaingFamily.Model.CalculateInvoice;
import com.CSMIS.CSMISKhaingFamily.Model.OrderFormRequest;

@Controller
public class OrderConfirmController {
    
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
	WeeklyOrderRepository weeklyOrderRepository;
	
	@Autowired
	OrderDetailViewRepository op;
	
	@Autowired
	OrderDetailService os;
	
	@Autowired
	WeeklyInvoiceService wiService;
	
	@Autowired
	HolidayRepository hRepo;
	
	@Autowired
	WeeklyOrderRepository wo;
	
	@Autowired
	WeeklyOrderService weeklyOrderService;
	@RequestMapping(value="/admin/index" , method=RequestMethod.GET)
	public String index (ModelMap model,Principal prin,HttpSession session) {
		return "index";
	}
	@RequestMapping(value="/admin/setupOrder" , method=RequestMethod.GET)
	public String orderConfirm (ModelMap model,Principal prin,HttpSession session) {
//		 // Get the current date
//	    LocalDate currentDate = LocalDate.now();
//	    
//	    // Determine the first day of the week
//	    LocalDate firstDayOfWeek = currentDate;
//	    while (firstDayOfWeek.getDayOfWeek() != DayOfWeek.MONDAY) {
//	      firstDayOfWeek = firstDayOfWeek.minusDays(1);
//	    }
//	    
//	    // Determine the last day of the week
//	    LocalDate lastDayOfWeek = currentDate;
//	    while (lastDayOfWeek.getDayOfWeek() != DayOfWeek.SUNDAY) {
//	      lastDayOfWeek = lastDayOfWeek.plusDays(1);
//	    }
//	    
//	    // Get all weekdays (Monday to Friday) in the current week
//	    List<LocalDate> weekdays = new ArrayList<>();
//	    for (LocalDate date = firstDayOfWeek; !date.isAfter(lastDayOfWeek); date = date.plusDays(1)) {
//	      if (date.getDayOfWeek() != DayOfWeek.SATURDAY && date.getDayOfWeek() != DayOfWeek.SUNDAY) {
//	        weekdays.add(date);
//	      }
//	    }
//	      // Get the current date
		
//	    List<String> datesList = new ArrayList<>();
//		List<Integer> quantityList= new ArrayList<>();
//		List<Integer> extrapaxList= new ArrayList<>();
//	    String quantity = "";
//	    String startdateenddate = "";
//	    int totalpax = 0;
//	    String extrapax = "";
//	    int res = 0;
//	    String comparedate = nextWeekdays[0] + " - " + nextWeekdays[4];
//	    System.out.println("Start date and End date " + comparedate);
//	    List<WeeklyOrder> list1 = weeklyOrderRepository.dateList(comparedate);
//	    System.out.println(list1.size());
//	    if(list1.size() != 0) {
//	    	for(int i = 0; i < list1.size();i++) {
//	    	    if(comparedate.equals(list1.get(i).getStarttoenddate())) {
//	    	    	res = 1;
//	    	    	quantity = list1.get(i).getQuantity();
//	    	    	startdateenddate = list1.get(i).getWeekdays();
//	    	    	totalpax = list1.get(i).getTotalpax();
//	    	    	extrapax = list1.get(i).getExtrapax();
//	    	    }
//	    	    }
//	    
//	    
//	    if(res != 0) {
//	    	datesList = Arrays.asList(startdateenddate.split(","));
//		    quantityList = Arrays.stream(quantity.split(","))
//	                .map(Integer::parseInt)
//	                .collect(Collectors.toList());
//		    
//		    System.out.println("Quantity" + quantityList.size());
//		    System.out.println("DateList" + datesList.size());
//		    extrapaxList = Arrays.stream(extrapax.split(","))
//	                .map(Integer::parseInt)
//	                .collect(Collectors.toList());
//		    System.out.println("Extra Pax" + extrapaxList.size());
//	    }
//	    List<CalculateInvoice> list2 = new ArrayList<CalculateInvoice>();
//	    List<AvoidMeat> avoid1 = avoidRepo.findAll();
//	    String result1 = "";
//	    int avoidcount1 = 0;
//	    
//	    for(int i = 0;i < extrapaxList.size();i++) {
//	    	CalculateInvoice cal = new CalculateInvoice();
//	    	int result = 0;
//	    	if(extrapaxList.get(i) != 0) {
//	    		result = quantityList.get(i) + extrapaxList.get(i);
//	    		cal.setQuantity(result);
//	    	}
//	    	else {
//	    		try {
//	    		    cal.setQuantity(quantityList.get(i));
//	    		} catch (IndexOutOfBoundsException e) {
//	    		    System.out.println(e.getMessage());
//	    		}
//	    	}
//	    	cal.setDayOfWeek(datesList.get(i));
//	    	StringBuilder string = new StringBuilder();
//	    	for(int j = 0;j < avoid1.size();j++) {
//	    		
//	    		avoidcount1 = avoidcountRepo.countAvoid(datesList.get(i), avoid1.get(j).getMeatName());
//	    	    if(avoidcount1 != 0) {
//	    	    	string.append(avoid1.get(j).getMeatName())
//	                 .append(" - ")
//	                 .append(avoidcount1);
//
//	   
//	    	    	if (j < avoid1.size() - 1) {
//	    	    		string.append(",");
//	    	    	}
//	    	    }
//	    		//System.out.println( avoid.get(j).getMeatName() + " - " + avoidcount + ",");
//	    	}
//	    	
//	    	result1 = string.toString();
//	    	cal.setAvoidMeat(result1);
//	    	list2.add(cal);
//	    }
//	    if(res != 0) {
//	    	session.setAttribute("olist", list2);
//	    	session.setAttribute("starttoenddate", comparedate);
//	    	session.setAttribute("totalCount", totalpax);
//	    }
//	    
//	    
//	    }
//		LocalDate today = LocalDate.now();
//
//        // Get the date for next Monday
//        LocalDate nextMonday = today.with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY));
//
//        // Get the dates for the next week's weekdays
//        LocalDate[] nextWeekdays = new LocalDate[5];
//        nextWeekdays[0] = nextMonday;
        
//        OrderDetailView ov = new OrderDetailView();
//	    List<OrderDetailView> ovlist = new ArrayList();
//	    List<OrderDetail> olist = new ArrayList<>();
//	    List<CalculateInvoice> clist = new ArrayList<CalculateInvoice>();
//	    List<Double> empList = new ArrayList<>();
//		List<Double> datList= new ArrayList<>();
//	    int total = 0;
//        double totalAmount = 0.0;
//        
//        int totalpax = 0;
////        for (int i = 1; i < 5; i++) {
////            nextWeekdays[i] = nextWeekdays[i - 1].plusDays(1);
////           
////        }
//        LocalDate today = LocalDate.now();
//
//        LocalDate nextWeekStart = today.plusWeeks(1).with(DayOfWeek.MONDAY);
//        LocalDate[] nextWeekdays = new LocalDate[5];
//        for (int i = 0; i < 5; i++) {
//            LocalDate currentDay = nextWeekStart.plusDays(i);
//            nextWeekdays[i] = currentDay;
//            ov = op.findLastByDates(currentDay.toString());
//       	 if(ov != null){
//            ovlist.add(ov);
//       	 }
//          
//            System.out.println(currentDay);
//        }
//   
//        // Print the dates for the next week's weekdays
////        for (LocalDate date : nextWeekdays) {
////        	 ov = op.findLastByDates(date.toString());
////        	 if(ov != null){
////             ovlist.add(ov);
////        	 }
////            System.out.println(date);
////        }
//      System.out.println("Ovlist " + ovlist.size());
//       if(ovlist.size() != 0) {
//    	   String result= "";
//    	   int result1 = 0;
//    	   int avoidcount = 0;
//    	   for(int i = 0;i < ovlist.size();i++) {
//	        	OrderDetail od = new OrderDetail();
//	        	ovlist.get(i).getWeekly_order_id();
//	        	empList.add(ovlist.get(i).getEmployeecost());
//	        	datList.add(ovlist.get(i).getDatcost());
//	        	totalpax = ovlist.get(i).getTotalpax();
//	        	String date = ovlist.get(i).getDates();
//	        	int quantity = ovlist.get(i).getQuantity();
//	        	int extrapax = ovlist.get(i).getExtrapax();
//	        	od.setDates(date);
//	        	od.setExtrapax(extrapax);
//	        	od.setQuantity(quantity);
//	        	
//	        	olist.add(od);
//	        }
//    	   String startdatetoenddate = " ";
//	        for(int i = 0;i < olist.size();i++) {
//	        
//	        	List<AvoidMeat> avoid = avoidRepo.findAll();
//	        	StringBuilder stringBuilder = new StringBuilder();
//	        	String date = nextWeekdays[i].toString();
//	        	CalculateInvoice cal = new CalculateInvoice();
//	        	cal.setDayOfWeek(olist.get(i).getDates());
//	        	startdatetoenddate = nextWeekdays[0].toString() + " - " + nextWeekdays[4].toString();
//	        	total += olist.get(i).getExtrapax() + olist.get(i).getQuantity();
//	        	double dailyprice = total * (empList.get(i) + datList.get(i));
//	        	cal.setEmpCost(empList.get(i));
//	        	cal.setDatCost(datList.get(i));
//	        	cal.setDailyPrice(dailyprice);
//	        	totalAmount += dailyprice;
//	        	
//	        	for(int j = 0;j < avoid.size();j++) {
//		    		avoidcount = avoidcountRepo.countAvoid(date, avoid.get(j).getMeatName());
//		    	    if(avoidcount != 0) {
//		    	    	stringBuilder.append(avoid.get(j).getMeatName())
//		                 .append(" - ")
//		                 .append(avoidcount);
//
//		   
//		    	    	if (j < avoid.size() - 1) {
//		    	    		stringBuilder.append(",");
//		    	    	}
//		    	    }
//		    		//System.out.println( avoid.get(j).getMeatName() + " - " + avoidcount + ",");
//		    	}
//		    	
//	        	if(olist.get(i).getExtrapax() != 0) {
//		    		result1 = olist.get(i).getQuantity() + olist.get(i).getExtrapax();
//		    		cal.setQuantity(result1);
//		    	}
//		    	else {
//		    		try {
//		    		    cal.setQuantity(olist.get(i).getQuantity());
//		    		} catch (IndexOutOfBoundsException e) {
//		    		    System.out.println(e.getMessage());
//		    		}
//		    	}
//	        	
//		    	result = stringBuilder.toString();
//		    	cal.setAvoidMeat(result);
//		    	
//	        	clist.add(cal);
//	        	
//       }
//	        System.out.println("Total" + total);
//		    session.setAttribute("totalCount", total);
//		    session.setAttribute("starttoenddate", startdatetoenddate);
//		    session.setAttribute("olist", clist);
//		LocalDateTime now = LocalDateTime.now();
//        DayOfWeek currentDayOfWeek = now.getDayOfWeek();
//        
//        // Check if current day is after Friday and current time is after 2 PM
//        if (currentDayOfWeek.compareTo(DayOfWeek.FRIDAY) > 0 || 
//                (currentDayOfWeek.equals(DayOfWeek.FRIDAY) && now.getHour() >= 14)) {
//        	session.setAttribute("closeOrder", 1);
//            System.out.println("Today is greater than Friday 2 PM of the current week.");
//        }else {
//        	session.setAttribute("closeOrder", 0);
//        }
		
    	List<HolidayData> holidayList = hRepo.findAll();
    	LocalDate today1 = LocalDate.now();
        LocalDate friday = today1.with(DayOfWeek.FRIDAY);
    	
        int holidayCheck = 0;
    	for(int i = 0;i < holidayList.size();i++) {
    		String holidayDate = holidayList.get(i).getHolidayDate();
    		if(holidayDate.equals(friday.toString())) {
    			holidayCheck = 1;
    		}
    	}
    	System.out.println("HolidayCheck :" + holidayCheck);
    	if(holidayCheck != 0) {
    		LocalDateTime now = LocalDateTime.now();
        	DayOfWeek currentDayOfWeek = now.getDayOfWeek();

        	// Check if current day is after Friday, current time is after 2 PM, and before Sunday
        	if ((currentDayOfWeek.compareTo(DayOfWeek.FRIDAY) > 0 && currentDayOfWeek.compareTo(DayOfWeek.SUNDAY) < 0) ||
        	    (currentDayOfWeek.equals(DayOfWeek.FRIDAY) && now.getHour() >= 14)) {
        		session.setAttribute("sundayBefore", 0);
        		session.setAttribute("closeOrder", 1);
        	    System.out.println("Current day is after Friday, the time is after 2 PM, and before Sunday.");
        	} else {
        		session.setAttribute("sundayBefore", 1);
        		session.setAttribute("closeOrder", 0);
        	    System.out.println("Nope!");
        	}
    	}else {
    		
        	LocalDateTime now = LocalDateTime.now();
        	DayOfWeek currentDayOfWeek = now.getDayOfWeek();

        	// Check if current day is after Friday, current time is after 2 PM, and before Saturday
        	if ((currentDayOfWeek.compareTo(DayOfWeek.FRIDAY) > 0 && currentDayOfWeek.compareTo(DayOfWeek.SATURDAY) < 0) ||
        	    (currentDayOfWeek.equals(DayOfWeek.FRIDAY) && now.getHour() >= 14)) {
        		session.setAttribute("fridayBefore", 0);
        		session.setAttribute("closeOrder", 1);
        	    System.out.println("Current day is after Friday, the time is after 2 PM, and before Saturday.");
        	} else {
        		session.setAttribute("fridayBefore", 1);
        		session.setAttribute("closeOrder", 0);
        	    System.out.println("Nope!");
        	}

        	
    	}
		LocalDate currentDate = LocalDate.now();
    	LocalDate startOfWeek = currentDate.with(DayOfWeek.MONDAY);

    	DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    	int count1 = 0;
    	System.out.println("Days of the current week:");
    	for (int i = 0; i < 7; i++) {
    	    LocalDate currentDay = startOfWeek.plusDays(i);
    	    String formattedDate = currentDay.format(outputFormatter);
    	    System.out.println(formattedDate);
    	    count1 += weeklyOrderRepository.countActiveOrderDatesByDate(formattedDate);
    	}
    	
		if(count1 != 0){
			return "alreadyorder";
       }
		
	    else {
	    	System.out.println("Here!!");
	       
	            
	            LocalDate today = LocalDate.now();
	    //
	            LocalDate nextWeekStart = today.plusWeeks(1).with(DayOfWeek.MONDAY);
	            LocalDate[] nextWeekdays = new LocalDate[5];
	            for (int i = 0; i < 5; i++) {
	                LocalDate currentDay = nextWeekStart.plusDays(i);
	                nextWeekdays[i] = currentDay;
	             	System.out.println(currentDay);
	            }
	            
	            
	    	List<AvoidMeat> avoid = avoidRepo.findAll();
		    String result = " ";
		    List<CalculateInvoice> list = new ArrayList<CalculateInvoice>();
		    
		    int avoidcount = 0;
		    int totalCount = 0;
		    String startdatetoenddate = " ";
		    for(int i = 0;i < nextWeekdays.length;i++) {
		    	StringBuilder stringBuilder = new StringBuilder();
		    	CalculateInvoice cal = new CalculateInvoice();
		    	String date = nextWeekdays[i].toString();
		    	startdatetoenddate = nextWeekdays[0].toString() + " ~ " + nextWeekdays[4].toString();
		    	int count = invoiceRepo.countDates(date);
		    	System.out.println("Count:" + count);
		    	System.out.println("Date:" + date);
		    	totalCount += count;
		        
		    	for(int j = 0;j < avoid.size();j++) {
		    		avoidcount = avoidcountRepo.countAvoid(date, avoid.get(j).getMeatName());
		    	    if(avoidcount != 0) {
		    	    	stringBuilder.append(avoid.get(j).getMeatName())
		                 .append(" - ")
		                 .append(avoidcount);

		   
		    	    	if (j < avoid.size() - 1) {
		    	    		stringBuilder.append(",");
		    	    	}
		    	    }
		    		//System.out.println( avoid.get(j).getMeatName() + " - " + avoidcount + ",");
		    	}
		    	
		    	result = stringBuilder.toString();
		    	
		    	if(count != 0) {
		    		cal.setQuantity(count);
			    	cal.setAvoidMeat(result);
			    	cal.setDayOfWeek(date);
			    	
			    	list.add(cal);

		    	}
		    	
		    }
		    if(totalCount == 0) {
		    	return "warningorder";
		    }
		    else {
		    	System.out.println("Total Count :" + totalCount);
		    	session.setAttribute("starttoenddate", startdatetoenddate);
			    session.setAttribute("totalCount", totalCount);
			    WeeklyOrder wo = new WeeklyOrder();
			    
			   wo = weeklyOrderRepository.findTopByOrderByIdDesc();
			      
			      if(wo != null) {
			    	  session.setAttribute("datCost", wo.getDatcost());
				      session.setAttribute("empCost", wo.getEmployeecost());
				      System.out.println(wo.getDatcost());
				      System.out.println(wo.getEmployeecost());
				      
			      }else {
			    	  session.setAttribute("datCost", "1500.0");
				      session.setAttribute("empCost", "1000.0");
				     
				      
			      }
			    		  
			    session.setAttribute("olist", list);
		    }
	    
	    }
	    return "orderform";
		
	}
	
	
	@PostMapping("/admin/submitOrder")
	@ResponseBody
	public ResponseEntity<?> submitOrder(@RequestBody OrderFormRequest orderFormRequest, 
	                          
	                          HttpSession session,Principal prin,
	                          ModelMap model,RedirectAttributes redirectAttributes) {
	    // process the quantityList and totalCount
		double employeeCost = 0.0;
		double datCost = 0.0;
		System.out.println("Here!!");
		System.out.println(orderFormRequest.getEmployeeCost());
		System.out.println(orderFormRequest.getEmployeeCost());
		if (orderFormRequest.getEmployeeCost() != null && 
			    orderFormRequest.getDatCost() != null ) {
			    employeeCost = Double.parseDouble(orderFormRequest.getEmployeeCost());
			    datCost = Double.parseDouble(orderFormRequest.getDatCost());
			}
		 
	        
		else {
			String errorMessage = "Please enter the valid employee cost and dat cost.";
			return ResponseEntity.badRequest().body(errorMessage);
		}
		 LocalDateTime now = LocalDateTime.now();
         DayOfWeek currentDayOfWeek = now.getDayOfWeek();
         
         // Check if current day is after Friday and current time is after 2 PM
         if (currentDayOfWeek.compareTo(DayOfWeek.FRIDAY) > 0 || 
                 (currentDayOfWeek.equals(DayOfWeek.FRIDAY) && now.getHour() >= 14)) {
             System.out.println("Today is greater than Friday 2 PM of the current week.");
        
		System.out.println(orderFormRequest.getQuantity());
		System.out.println(orderFormRequest.getDayOfWeek());
		System.out.println(orderFormRequest.getAvoidMeat());
		List<Integer> quantityList1 = orderFormRequest.getQuantity();
		List<String> dateList1 = orderFormRequest.getDayOfWeek();
		List<String> avoidList1 = orderFormRequest.getAvoidMeat();
		
		int totalCount = Integer.parseInt(orderFormRequest.getTotalCount());
		
		List<CalculateInvoice> olist = (List<CalculateInvoice>) session.getAttribute("olist");
		System.out.println("O List :" + olist);
		//List<CalculateInvoice> list = new ArrayList<CalculateInvoice>();
		List<OrderDetail> list = new ArrayList();
		List<CalculateInvoice> clist = new ArrayList();
		int result = 0;
		String starttoenddate = (String)session.getAttribute("starttoenddate");
		WeeklyOrder wo = new WeeklyOrder();
		
		wo.setDatcost(datCost);
		wo.setEmployeecost(employeeCost);
	    wo.setStarttoenddate(starttoenddate);
	    
	   
        int registerCount = 0;int extrapax = 0;
	    for(int i = 0;i < dateList1.size();i++) {  	
	    	String date = dateList1.get(i);
	    	int count = invoiceRepo.countDates(date);
	    	registerCount += count;
	    }
	    System.out.println(registerCount);
	    System.out.println(totalCount);
		if(totalCount > registerCount) {
			for(int i = 0;i < dateList1.size();i++) {
				String date = dateList1.get(i);
		    	int count = invoiceRepo.countDates(date);
		    	registerCount += count;
				OrderDetail od = new OrderDetail();
				
				od.setDates(dateList1.get(i));
				od.setQuantity(count);
				CalculateInvoice cal = new CalculateInvoice();
				cal.setAvoidMeat(avoidList1.get(i));
				cal.setQuantity(count);
				cal.setDayOfWeek(dateList1.get(i));
				
			    
			    
				if(quantityList1.get(i) > count) {
					result = quantityList1.get(i) - count;
					od.setExtrapax(result);
					cal.setExtrapax(result);
					extrapax = 1;
				}
				else {
					od.setExtrapax(0);
					cal.setExtrapax(0);
				}
				od.setWeeklyOrder(wo);
				list.add(od);
				clist.add(cal);
			}
		}
		else {
			for(int i = 0;i < dateList1.size();i++) {
				OrderDetail od = new OrderDetail();
				od.setDates(dateList1.get(i));
				od.setQuantity(quantityList1.get(i));
				od.setWeeklyOrder(wo);
				
				CalculateInvoice cal = new CalculateInvoice();
				cal.setAvoidMeat(avoidList1.get(i));
				cal.setQuantity(quantityList1.get(i));
				cal.setDayOfWeek(dateList1.get(i));
				clist.add(cal);
				
				od.setExtrapax(0);
				
				list.add(od);
			
			}
	    
	}
		Operator operator = operatorRepository.findByemployeeEmail(prin.getName());
		session.setAttribute("adminEmail", operator.getEmployeeEmail());
		session.setAttribute("adminName", operator.getEmployeeName());
		session.setAttribute("companyName", "DIR-ACE Technology Ltd.");
		wo.setStatus("ACTIVE");
	    wo.setOrderDetails(list);
	    wo.setTotalpax(totalCount);
	    LocalDate today = LocalDate.now();
	    wo.setDoorlogid(operator.getDoorLogNo());
	    wo.setOrderdate(today.toString());
	    Double totalCost = wo.getDatcost() + wo.getEmployeecost();
		Double totalPrice = totalCount * totalCost;
		session.setAttribute("totalPrice", totalPrice);
		wo.setTotalCost(totalPrice);
	    wo = weeklyOrderRepository.save(wo);
	    session.setAttribute("result", extrapax);
	    session.setAttribute("empCost", wo.getEmployeecost());
	    session.setAttribute("datCost", wo.getDatcost());
	    session.setAttribute("today", today.toString());
	    session.setAttribute("totalCount", totalCount); 
		session.setAttribute("olist", clist);
		
	    if(wo != null) {
	    	String successMessage = "Order information saved successfully.";
	    	return ResponseEntity.ok().body(successMessage);
	    }
	    else {
	    	 String errorMessage = "Failed to save order information.";
 	         return ResponseEntity.badRequest().body(errorMessage);
	    }
	
//		for(int i = 0;i < quantityList1.size();i++) {
//			CalculateInvoice cal = new CalculateInvoice();
//			cal.setAvoidMeat(avoidList1.get(i));
//			cal.setQuantity(quantityList1.get(i));
//			cal.setDayOfWeek(dateList1.get(i));
//			list.add(cal);
//		}
//		List<CalculateInvoice> olist = (List<CalculateInvoice>) session.getAttribute("olist");
//		System.out.println("Order List Size :" + olist.size());
//		int totalCount1 = totalCount2;
//		int result = 0;
//		StringBuilder sb = new StringBuilder();
//		StringBuilder sb1 = new StringBuilder();
//		StringBuilder sb2 = new StringBuilder();
//		if(totalCount1 > quantity) {
//			for(int i = 0;i < olist.size();i++) {
//				if(quantityList1.get(i) > olist.get(i).getQuantity()) {
//					result = quantityList1.get(i) - olist.get(i).getQuantity();
//					 sb.append(result).append(",");
//				}
//				else {
//					sb.append("0").append(",");
//				}
//				System.out.println(olist.get(i).getDayOfWeek());
//				System.out.println(olist.get(i).getQuantity());
//				sb1.append(olist.get(i).getDayOfWeek()).append(",");
//				sb2.append(olist.get(i).getQuantity()).append(",");
//				
//			}
//		    
//		}
//		else {
//			for(int i = 0;i < olist.size();i++) {
//				if(quantityList1.get(i) > olist.get(i).getQuantity()) {
//					result = quantityList1.get(i) - olist.get(i).getQuantity();
//					 sb.append(result).append(",");
//				}
//				else {
//					sb.append("0").append(",");
//				}
//				
//				sb1.append(list.get(i).getDayOfWeek()).append(",");
//				sb2.append(list.get(i).getQuantity()).append(",");
//				
//			}
//		    
//		}
//		WeeklyOrder wi = new WeeklyOrder();
//		String extrapax = sb.toString();
//		String weekdays = sb1.toString();
//		String quantity1 = sb2.toString();
//		String starttoenddate = (String)session.getAttribute("starttoenddate");
//		//wi.setExtrapax(extrapax);
//		//wi.setWeekdays(weekdays);
//		//wi.setQuantity(quantity1);
//		wi.setStarttoenddate(starttoenddate);
//		wi.setTotalpax(totalCount1);
//		wi.setEmployeecost(employeeCost);
//		wi.setDatcost(datCost);
//		wi = weeklyOrderRepository.save(wi);
//	    System.out.println(totalCount1); 
//		session.setAttribute("totalCount", totalCount1); 
//		session.setAttribute("olist", list);
//		 if(wi != null) {
//		    	String successMessage = "Order information saved successfully.";
//		    	return ResponseEntity.ok().body(successMessage);
//		    }
//		    else {
//		    	 String errorMessage = "Failed to save order information.";
//	 	         return ResponseEntity.badRequest().body("{\"message\": \"" + errorMessage + "\"}");
//		    }
	    }else {
	    	 System.out.println("Today is not greater than Friday 2 PM of the current week.");
	    	String errorMessage = "Failed to save order information.";
	         return ResponseEntity.badRequest().body(errorMessage);
           
        } 
		
	}
	
	@RequestMapping(value="/admin/orderView" , method=RequestMethod.GET)
	public String orderView (Principal prin,ModelMap model,HttpSession session,RedirectAttributes redireactAttributes) {
		 // Compare the current date and time with the current week's Friday at 2 PM
      
    	redireactAttributes.addFlashAttribute("successMessage", "Congratulations! Your order has been successfully created! 🎉");
    	return "redirect:/admin/orderViewSuccess";
 
	}
	
	
	
	@GetMapping("/admin/orderViewSuccess")
	public String invoiceCreateFail(HttpSession session) {
		
		return "orderformview";
	}
	
	@PostMapping("/admin/deleteOrder")
	@ResponseBody
	public ResponseEntity<?> deleteOrder(@RequestParam("orderId") String orderId,HttpSession session) {
	   
		 System.out.println("Here!!");
		
		 Long id = Long.parseLong(orderId);
	    
	    // Use the status and id values as needed
//	    int i = os.deleteOrder(id);
//	    weeklyOrderRepository.deleteById(id);
		String status = "INACTIVE";
		System.out.println("Id" + id);

	    int i = weeklyOrderService.insertStatusWithId(status, id);
	    //attributes.addAttribute("id", id);
	    List<WeeklyOrder> orderList = wo.datesList();
	    int result = orderList.size();
		session.setAttribute("result", result);
		System.out.println("i" + id);
	    if (i > 0) {
	    	List<OrderDetailView> list = op.getAllDistinctWeeklyOrderIds();
	    	String successMessage = "Delete successful.";
	    	System.out.println(list.size());
		    Map<String, Object> response = new HashMap<>();
		    response.put("wi", list);
		    response.put("message", successMessage);
	        
	        return ResponseEntity.ok().body(response);
	    } else {
	    	String errorMessage = "Delete Failed.";
		   
		    return ResponseEntity.badRequest().body(errorMessage);
	    }
	}
	
}