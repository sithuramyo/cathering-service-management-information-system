package com.CSMIS.CSMISKhaingFamily.Controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.CSMIS.CSMISKhaingFamily.DAO.AvoidCountRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.AvoidMeatRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.CreativeInvoiceRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.CreativeInvoiceService;
import com.CSMIS.CSMISKhaingFamily.DAO.OperatorRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.OrderDetailRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.OrderDetailService;
import com.CSMIS.CSMISKhaingFamily.DAO.OrderDetailViewRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.RestaurantInfoRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.RestaurantInfoService;
import com.CSMIS.CSMISKhaingFamily.DAO.WeeklyInvoiceRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.WeeklyInvoiceService;
import com.CSMIS.CSMISKhaingFamily.DAO.WeeklyOrderRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.WeeklyOrderService;
import com.CSMIS.CSMISKhaingFamily.Entity.AvoidMeat;
import com.CSMIS.CSMISKhaingFamily.Entity.CreativeInvoice;
import com.CSMIS.CSMISKhaingFamily.Entity.Operator;
import com.CSMIS.CSMISKhaingFamily.Entity.OrderDetail;
import com.CSMIS.CSMISKhaingFamily.Entity.OrderDetailView;
import com.CSMIS.CSMISKhaingFamily.Entity.RestaurantInfo;
import com.CSMIS.CSMISKhaingFamily.Entity.WeeklyInvoice;
import com.CSMIS.CSMISKhaingFamily.Entity.WeeklyOrder;
import com.CSMIS.CSMISKhaingFamily.Model.CalculateInvoice;


@Controller
public class WeeklyInvoiceHistoryController {
	@Autowired
	AvoidMeatRepository avoidRepo;
	
	@Autowired
	AvoidCountRepository avoidcountRepo;
	
	@Autowired
	WeeklyInvoiceRepository weeklyInvoiceRepository;
	
	@Autowired
	CreativeInvoiceRepository ciRepository;

	@Autowired
	WeeklyInvoiceService wiService;
	
	@Autowired
	RestaurantInfoRepository resRepo;
	
	@Autowired
	OrderDetailViewRepository op;
	
	@Autowired
	OperatorRepository operatorRepository;
	
	@Autowired
	WeeklyOrderRepository wo;
	

	@Autowired
	OrderDetailService os;
	
	@Autowired
	RestaurantInfoService resService;
	
	@Autowired
	CreativeInvoiceService ciService;
	
	@Autowired
	OrderDetailRepository odRepo;
	
	@Autowired
	WeeklyOrderRepository weeklyOrderRepository;
	
	@Autowired
	WeeklyOrderService woService;
	
	@GetMapping("/admin/paidList")
	public String paidList(HttpSession session) {
		
		List<WeeklyInvoice> wi = wiService.getPaidList();
		int result = wi.size();
		session.setAttribute("result", result);
		session.setAttribute("hlist", wi);
		return "weeklyinvoicepaidhistory";
	}
	@GetMapping("/admin/unpaidList")
	public String unpaidList(HttpSession session) {
		List<WeeklyInvoice> wi = wiService.getUnPaidList();
		int result = wi.size();
		session.setAttribute("result", result);
		session.setAttribute("hlist", wi);
		return "weeklyinvoiceunpaidhistory";
	}
	
	@GetMapping("/admin/calWeeklyPaidInvoice/{id}")
	public String calweeklyPaidInvoiceHistory(@PathVariable Long id, HttpSession session) {
		System.out.println("Id :" + id);
		WeeklyInvoice wi = wiService.getInvoiceById(id);
		System.out.println(wiService.getInvoiceById(id) + " wiService.getInvoiceId");
		CreativeInvoice ci = ciRepository.getInvoiceInfo(wi.getCreateinvoiceid());
		System.out.println(wi.getResid() + " restaurant id");
		RestaurantInfo ri = resRepo.getResInfo(wi.getResid());
	

		
		
		String startDateStr = ci.getStartdate();
	    String endDateStr = ci.getEnddate();
	    OrderDetailView ov = new OrderDetailView();
	    List<OrderDetailView> ovlist = new ArrayList();
	    List<OrderDetail> olist = new ArrayList<>();
	    List<CalculateInvoice> clist = new ArrayList<CalculateInvoice>();
	    List<Double> empList = new ArrayList<>();
		List<Double> datList= new ArrayList<>();
	    int total = 0,result = 0;
        double totalAmount = 0.0;
        
        int totalpax = 0;
        
	    DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    LocalDate startDate = LocalDate.parse(startDateStr, outputFormatter);
	    LocalDate endDate = LocalDate.parse(endDateStr, outputFormatter);
	    LocalDate currentDate = startDate;
		 while (!currentDate.isAfter(endDate)) {
	            System.out.println(currentDate);
	            
	            ov = op.findLastByDates(currentDate.toString());
	            if(ov != null) {
		        ovlist.add(ov);
	            }
	            currentDate = currentDate.plusDays(1);
	        }
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
	      
		currentDate = LocalDate.now();
		session.setAttribute("id", wi.getId());
		if(ri != null) {
			session.setAttribute("resName",ri.getResturantname());
			session.setAttribute("resAddress", ri.getResaddress());
			session.setAttribute("resPhone",ri.getResph());
			session.setAttribute("resEmail",ri.getResemail());
		}
		
		session.setAttribute("currentDate", currentDate);
		session.setAttribute("totalAmount", wi.getTotalamount());
		session.setAttribute("result", result);
		
		session.setAttribute("invoiceDate", wi.getInvoicedate());
		session.setAttribute("list", clist);
		session.setAttribute("cashier", ci.getCashier());
		session.setAttribute("paymentmethod", ci.getPaymethod());
		session.setAttribute("approvedby", ci.getApprovedby());
		session.setAttribute("receivedby", ci.getReceivedby());
	    System.out.println("list :" + clist.size());
		return "invoicepaidlist";
	}
	@GetMapping("/admin/calWeeklyUnpaidInvoice/{id}")
	public String calweeklyUnpaidInvoiceHistory(@PathVariable Long id, HttpSession session) {
		System.out.println("Id :" + id);
		WeeklyInvoice wi = wiService.getInvoiceById(id);
		System.out.println(wiService.getInvoiceById(id) + " wiService.getInvoiceId");
		CreativeInvoice ci = ciRepository.getInvoiceInfo(wi.getCreateinvoiceid());
		System.out.println(wi.getResid() + " restaurant id");
	
			RestaurantInfo ri = resRepo.getResInfo(wi.getResid());	
		
		String startDateStr = ci.getStartdate();
	    String endDateStr = ci.getEnddate();
	    OrderDetailView ov = new OrderDetailView();
	    List<OrderDetailView> ovlist = new ArrayList();
	    List<OrderDetail> olist = new ArrayList<>();
	    List<CalculateInvoice> clist = new ArrayList<CalculateInvoice>();
	    List<Double> empList = new ArrayList<>();
		List<Double> datList= new ArrayList<>();
	    int total = 0,result = 0;
        double totalAmount = 0.0;
        
        int totalpax = 0;
        
	    DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    LocalDate startDate = LocalDate.parse(startDateStr, outputFormatter);
	    LocalDate endDate = LocalDate.parse(endDateStr, outputFormatter);
	    LocalDate currentDate = startDate;
		 while (!currentDate.isAfter(endDate)) {
	            System.out.println(currentDate);
	            
	            ov = op.findLastByDates(currentDate.toString());
	            if(ov != null) {
		        ovlist.add(ov);
	            }
	            currentDate = currentDate.plusDays(1);
	        }
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
		currentDate = LocalDate.now();
		session.setAttribute("id", wi.getId());
		
		session.setAttribute("currentDate", currentDate);
		session.setAttribute("totalAmount", wi.getTotalamount());
		session.setAttribute("result", result);
		
		if(ri != null) {
			session.setAttribute("resName",ri.getResturantname());
			session.setAttribute("resAddress", ri.getResaddress());
			session.setAttribute("resPhone",ri.getResph());
			session.setAttribute("resEmail",ri.getResemail());
		}

		session.setAttribute("invoiceDate", wi.getInvoicedate());
		session.setAttribute("list", clist);
		session.setAttribute("cashier", ci.getCashier());
		session.setAttribute("paymentmethod", ci.getPaymethod());
		session.setAttribute("approvedby", ci.getApprovedby());
		session.setAttribute("receivedby", ci.getReceivedby());
	        
		return "invoiceunpaidlist";
	}
	@PostMapping("/admin/invoicePaidOnly")
	@ResponseBody
	public ResponseEntity<?> invoiceSave(@RequestBody Map<String, Object> data, HttpSession session, RedirectAttributes attributes) {
	    System.out.println("Here!!");
	    String status = (String) data.get("status");
	    String idString = (String) data.get("id");
	    Long id = Long.parseLong(idString);
	    
	    // Use the status and id values as needed
	    int i = wiService.insertStatusWithId(status, id);
	   // attributes.addAttribute("id", id);
	    if (i > 0) {
		 	
	        String successMessage = "Save Successful.";
	        
	        return ResponseEntity.ok().body(successMessage);
	    } else {
	        String errorMessage = "Failed to save.";
	        return ResponseEntity.badRequest().body(errorMessage);
	    }
	  
	}

	@PostMapping("/admin/invoicePayNowOnly")
	@ResponseBody
	public ResponseEntity<?> invoicePayNow(@RequestBody Map<String, Object> data, HttpSession session, RedirectAttributes attributes) {
		 System.out.println("Here!!");
		 String status = (String) data.get("status");
		 String idString = (String) data.get("id");
		 Long id = Long.parseLong(idString);
	    
	    // Use the status and id values as needed
	    int i = wiService.insertStatusWithId(status, id);
	    //attributes.addAttribute("id", id);
	    if (i > 0) {
		 	
	        String successMessage = "Paid Successful.";
	        
	        return ResponseEntity.ok().body(successMessage);
	    } else {
	        String errorMessage = "Failed to paid.";
	        return ResponseEntity.badRequest().body(errorMessage);
	    }
	  
	}

	@PostMapping("/admin/searchUnpaidList")
	@ResponseBody
	public ResponseEntity<?> searchUnpaidByDate(@RequestBody Map<String, String> formData, HttpSession session) {
	  String startDate = formData.get("startDate");
	  String endDate = formData.get("endDate");

	  // Perform your database query or any other operations based on the start date and end date values
	  List<WeeklyInvoice> wi = weeklyInvoiceRepository.searchUnpaidInvoice(startDate, endDate);
	  
	  
	  
	  if(startDate == "" || endDate == "") {
		  wi = wiService.getUnPaidList();
		  String errorMessage = "Please enter valid start date and end date.";
		  System.out.println(wi.size());
		    Map<String, Object> response = new HashMap<>();
		    response.put("wi", wi);
		    response.put("message", errorMessage);
		    return ResponseEntity.badRequest().body(response);
	  }
	  if (wi.size() != 0) {
	    String successMessage = "Search successful.";
	    Map<String, Object> response = new HashMap<>();
	    response.put("wi", wi);
	    response.put("message", successMessage);
	    return ResponseEntity.ok().body(response);
	  } else {
	    String errorMessage = "Searching Failed.";
	    Map<String, Object> response = new HashMap<>();
	    response.put("wi", wi);
	    response.put("message", errorMessage);
	    return ResponseEntity.badRequest().body(response);
	  }
	  
	}
	 @GetMapping("/admin/UnpaidListReset") 
	 @ResponseBody// Replace "your_controller_url" with the desired URL mapping
	  public ResponseEntity<?> getAllUnpaidWeeklyInvoices() {
	    // Retrieve the data from the repository
	    List<WeeklyInvoice> wi1 = wiService.getUnPaidList();
	    if (wi1 != null) {
	    	String successMessage = "Reset successful.";
	    	System.out.println(wi1.size());
		    Map<String, Object> response = new HashMap<>();
		    response.put("wi", wi1);
		    response.put("message", successMessage);
	        
	        return ResponseEntity.ok().body(response);
	    } else {
	        String errorMessage = "Failed to reset.";
	        return ResponseEntity.badRequest().body(errorMessage);
	    }
	 }
	 @PostMapping("/admin/searchPaidList")
		@ResponseBody
		public ResponseEntity<?> searchByDate(@RequestBody Map<String, String> formData, HttpSession session) {
		  String startDate = formData.get("startDate");
		  String endDate = formData.get("endDate");

		  // Perform your database query or any other operations based on the start date and end date values
		  List<WeeklyInvoice> wi = weeklyInvoiceRepository.searchPaidInvoice(startDate, endDate);
		  
		  
		  
		  if(startDate == "" || endDate == "") {
			  wi = wiService.getPaidList();
			  String errorMessage = "Please enter valid start date and end date.";
			  System.out.println(wi.size());
			    Map<String, Object> response = new HashMap<>();
			    response.put("wi", wi);
			    response.put("message", errorMessage);
			    return ResponseEntity.badRequest().body(response);
		  }
		  if (wi.size() != 0) {
		    String successMessage = "Search successful.";
		    Map<String, Object> response = new HashMap<>();
		    response.put("wi", wi);
		    response.put("message", successMessage);
		    return ResponseEntity.ok().body(response);
		  } else {
		    String errorMessage = "Searching Failed.";
		    Map<String, Object> response = new HashMap<>();
		    response.put("wi", wi);
		    response.put("message", errorMessage);
		    return ResponseEntity.badRequest().body(response);
		  }
		  
		}
		 @GetMapping("/admin/PaidListReset") 
		 @ResponseBody// Replace "your_controller_url" with the desired URL mapping
		  public ResponseEntity<?> getAllWeeklyInvoices(HttpSession session) {
		    // Retrieve the data from the repository
		    List<WeeklyInvoice> wi1 = wiService.getPaidList();
		    
		    if (wi1 != null) {
		    	System.out.println("wi1 size :" + wi1.size());
		    	String successMessage = "Reset successful.";
		    	System.out.println(wi1.size());
			    Map<String, Object> response = new HashMap<>();
			    response.put("wi", wi1);
			    response.put("message", successMessage);
		        
		        return ResponseEntity.ok().body(response);
		    } else {
		        String errorMessage = "Failed to reset.";
		        return ResponseEntity.badRequest().body(errorMessage);
		    }
		 }
		 @GetMapping("/admin/orderList")
			public String orderList(HttpSession session) {
			    List<OrderDetailView> list = op.getAllDistinctWeeklyOrderIds();
			    System.out.println("Orderlist :"  + list.size());
			    int result = list.size();
				session.setAttribute("result", result);
				session.setAttribute("list", list);
				return "orderhistory";
			}
		
		
		 @PostMapping("/admin/searchOrderList")
			@ResponseBody
			public ResponseEntity<?> searchByDateOrder(@RequestBody Map<String, String> formData, HttpSession session) {
			  String startDate = formData.get("startDate");
			  String endDate = formData.get("endDate");
			  List<OrderDetailView> ov = new ArrayList();
			  if(startDate == "" || endDate == "") {
				  ov = op.getAllDistinctWeeklyOrderIds();	
				  String errorMessage = "Please enter valid start date and end date.";
				  System.out.println(ov.size());
				    Map<String, Object> response = new HashMap<>();
				    response.put("wi", ov);
				    response.put("message", errorMessage);
				    return ResponseEntity.badRequest().body(response);
			  }
			  // Perform your database query or any other operations based on the start date and end date values
			 System.out.println("Start Date:" + startDate);
			 System.out.println("End Date:" + endDate);
			  List<WeeklyOrder> wi = wo.findByOrderdateBetween(startDate,endDate);
			  
			  for(int i = 0;i < wi.size();i++) {
				  OrderDetailView o = new OrderDetailView();
				  o.setOrderdate(wi.get(i).getOrderdate());
				  o.setWeekly_order_id(wi.get(i).getId());
				  o.setStarttoenddate(wi.get(i).getStarttoenddate());
				  ov.add(o);
			  }
			  System.out.println("Wi Size():" + wi.size());
			  
			 
			  if (ov.size() != 0) {
			    String successMessage = "Search successful.";
			    Map<String, Object> response = new HashMap<>();
			    response.put("wi", ov);
			    response.put("message", successMessage);
			    return ResponseEntity.ok().body(response);
			  } else {
			    String errorMessage = "Searching Failed.";
			    Map<String, Object> response = new HashMap<>();
			    response.put("wi", ov);
			    response.put("message", errorMessage);
			    return ResponseEntity.badRequest().body(response);
			  }
			  
			}
			 @GetMapping("/admin/orderListReset") 
			 @ResponseBody// Replace "your_controller_url" with the desired URL mapping
			  public ResponseEntity<?> getAllOrderList() {
			    // Retrieve the data from the repository
				 List<OrderDetailView> wi1 = op.getAllDistinctWeeklyOrderIds();
			    if (wi1 != null) {
			    	String successMessage = "Reset successful.";
			    	System.out.println(wi1.size());
				    Map<String, Object> response = new HashMap<>();
				    response.put("wi", wi1);
				    response.put("message", successMessage);
			        
			        return ResponseEntity.ok().body(response);
			    } else {
			        String errorMessage = "Failed to reset.";
			        return ResponseEntity.badRequest().body(errorMessage);
			    }
			 }
			 @GetMapping("/admin/calOrder/{id}")
				public String calOrderHistory(@PathVariable Long id, HttpSession session) {
					
					List<OrderDetailView> olist = new ArrayList();
					olist = op.findByWeeklyOrderId(id);
					Operator operator = operatorRepository.searchEmployee(olist.get(0).getDoorlogid());
					System.out.println("Olist" + olist.size());
					
					int avoidcount = 0;String result = "";
					List<AvoidMeat> avoid = avoidRepo.findAll();
					int count = 0;Double price = 0.0;Double totalPrice = 0.0;int totalCount = 0;
					List<CalculateInvoice> clist = new ArrayList();
					for(int i = 0;i < olist.size();i++) {
						StringBuilder stringBuilder = new StringBuilder();
						CalculateInvoice ci = new CalculateInvoice();
						ci.setQuantity(olist.get(i).getQuantity()+olist.get(i).getExtrapax());
						ci.setDayOfWeek(olist.get(i).getDates());
						System.out.println(olist.get(i).getDates());
						String date = olist.get(i).getDates();
						count = ci.getQuantity();
						totalCount += count;
						price = olist.get(i).getDatcost() + olist.get(i).getEmployeecost();
						totalPrice += count * price;
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
				    	ci.setAvoidMeat(result);
				    	System.out.println("Result :" + result);
			        	clist.add(ci);
						
					}
					LocalDate today = LocalDate.now();
					session.setAttribute("olist", clist);
					session.setAttribute("today",olist.get(0).getOrderdate());
					session.setAttribute("empCost", olist.get(0).getEmployeecost());
					session.setAttribute("datCost", olist.get(0).getDatcost());
					session.setAttribute("adminEmail", operator.getEmployeeEmail());
					session.setAttribute("adminName", operator.getEmployeeName());
					session.setAttribute("companyName", "DIR-ACE Technology Ltd.");
					session.setAttribute("totalPrice", totalPrice);
					session.setAttribute("totalCount", totalCount);
					return "orderhistoryview";
				}
			 @PostMapping("/admin/deleteUnpaidInvoice")
				@ResponseBody
				public ResponseEntity<?> deleteOrder(@RequestParam("invoiceUnPaidId") String orderId,HttpSession session) {
				 System.out.println("Here!!");
				
				 Long id = Long.parseLong(orderId);
				 WeeklyInvoice list = weeklyInvoiceRepository.weeklyInvoiceList(id);
				 int i = ciService.deleteOrder(list.getCreateinvoiceid());
				 
			    // Use the status and id values as needed
			    
			    int k = wiService.deleteOrder(id);
			    //attributes.addAttribute("id", id);
			    if (i > 0 && k > 0) {
			    	List<WeeklyInvoice> wi = wiService.getUnPaidList();
			    	String successMessage = "Delete successful.";
			    	System.out.println(wi.size());
				    Map<String, Object> response = new HashMap<>();
				    response.put("wi", wi);
				    response.put("message", successMessage);
			        
			        return ResponseEntity.ok().body(response);
			    } else {
			    	String errorMessage = "Delete Failed.";
				   
				    return ResponseEntity.badRequest().body(errorMessage);
			    }
				}
			 @PostMapping("/admin/deletePaidInvoice")
				@ResponseBody
				public ResponseEntity<?> deleteInvoice(@RequestParam("invoicePaidId") String orderId,HttpSession session) {
				   
					 System.out.println("Here!!");
					 System.out.println("There/;");	 
					 Long id = Long.parseLong(orderId);
					 WeeklyInvoice list = weeklyInvoiceRepository.weeklyInvoiceList(id);
					    // Use the status and id values as needed
					    int i = ciService.deleteOrder(list.getCreateinvoiceid());
					    
				    
				    int k = wiService.deleteOrder(id);
				    //attributes.addAttribute("id", id);
				    if (i > 0 && k > 0) {
				    	List<WeeklyInvoice> wi = wiService.getPaidList();
				    	String successMessage = "Delete successful.";
				    	System.out.println(wi.size());
					    Map<String, Object> response = new HashMap<>();
					    response.put("wi", wi);
					    response.put("message", successMessage);
				        
				        return ResponseEntity.ok().body(response);
				    } else {
				    	String errorMessage = "Delete Failed.";
					   
					    return ResponseEntity.badRequest().body(errorMessage);
				    }
				}
			 @GetMapping("/admin/deleteAllOrder") 
			 @ResponseBody
			  public ResponseEntity<?> getAllOrder() {
			    // Retrieve the data from the repository
				 List<WeeklyOrder> orderList = wo.datesList();
				 
				 int i = woService.updateStatus();
				 
			    if (i > 0) {
			    	List<OrderDetailView> list = op.getAllDistinctWeeklyOrderIds();	
			    	String successMessage = "Delete successful.";
			    	System.out.println(list.size());
				    Map<String, Object> response = new HashMap<>();
				    response.put("wi", list);
				    response.put("message", successMessage);
				    
			        return ResponseEntity.ok().body(response);
			    } else {
			        String errorMessage = "Failed to delete.";
			        return ResponseEntity.badRequest().body(errorMessage);
			    }
			 }
			 @GetMapping("/admin/deleteAllPaid") 
			 @ResponseBody
			  public ResponseEntity<?> deleteAllPaid(HttpSession session) {
			    // Retrieve the data from the repository
				
				 
				 List<WeeklyInvoice> list = wiService.getPaidList();
				 int k = 0;
				 System.out.println("list.size()" + list.size());
				 for(int j = 0;j < list.size();j++) {
					  k = ciService.deleteOrder(list.get(j).getCreateinvoiceid());
					  System.out.println("creative :" + list.get(j).getCreateinvoiceid());
				 }
				 int i = wiService.deleteAllPaidInvoice();
				 System.out.println("i" + i);
				 System.out.println("k" + k);
			    if (i > 0 && k > 0) {
			    	
			    	System.out.println("s");
			    	List<WeeklyInvoice> wi = wiService.getPaidList();
			    	String successMessage = "Delete successful.";
			    	System.out.println(wi.size());
				    Map<String, Object> response = new HashMap<>();
				    response.put("wi", wi);
				    response.put("message", successMessage);
			    	
			        return ResponseEntity.ok().body(response);
			    } else {
			    	System.out.println("e");
			        String errorMessage = "Failed to delete.";
			        return ResponseEntity.badRequest().body(errorMessage);
			    }
			 }
			 @GetMapping("/admin/deleteAllUnpaid") 
			 @ResponseBody// Replace "your_controller_url" with the desired URL mapping
			  public ResponseEntity<?> deleteAllUnpaid() {
			    // Retrieve the data from the repository
				
				 
				 List<WeeklyInvoice> wi = wiService.getUnPaidList();
				 int k = 0;
				 for(int j = 0;j < wi.size();j++) {
					  k = ciService.deleteOrder(wi.get(j).getCreateinvoiceid());
					 
				 }
				 int i = wiService.deleteAllUnPaidInvoice();
			    if (i > 0 && k > 0) {
			    	List<WeeklyInvoice> list = wiService.getUnPaidList();
			    	String successMessage = "Delete successful.";
			    	System.out.println(list.size());
				    Map<String, Object> response = new HashMap<>();
				    response.put("wi", list);
				    response.put("message", successMessage);
			    	
			        return ResponseEntity.ok().body(response);
			    } else {
			        String errorMessage = "Failed to delete.";
			        return ResponseEntity.badRequest().body(errorMessage);
			    }
			 }
}