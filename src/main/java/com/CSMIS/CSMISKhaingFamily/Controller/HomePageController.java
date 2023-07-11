package com.CSMIS.CSMISKhaingFamily.Controller;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.CSMIS.CSMISKhaingFamily.DAO.AdminMenuImportRepo;
import com.CSMIS.CSMISKhaingFamily.DAO.AdminMenuImportService;
import com.CSMIS.CSMISKhaingFamily.DAO.AvoidCountRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.AvoidMeatRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.ContactInfoRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.CreativeInvoiceRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.EmailConfigRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.EmailService;
import com.CSMIS.CSMISKhaingFamily.DAO.FeedbackRepo;
import com.CSMIS.CSMISKhaingFamily.DAO.HolidayRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.LunchCountRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.OperatorRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.OperatorService;
import com.CSMIS.CSMISKhaingFamily.DAO.OrderDetailViewRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.PostService;
import com.CSMIS.CSMISKhaingFamily.DAO.RegisterCateringRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.RegisterDateRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.RegisteredeatRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.RegistereduneatRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.RestaurantInfoRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.RestaurantService;
import com.CSMIS.CSMISKhaingFamily.DAO.UnregisteredeatRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.WeeklyOrderRepository;
import com.CSMIS.CSMISKhaingFamily.Entity.ContactInfo;
import com.CSMIS.CSMISKhaingFamily.Entity.EmailConfig;
import com.CSMIS.CSMISKhaingFamily.Entity.Feedback;
import com.CSMIS.CSMISKhaingFamily.Entity.LunchCount;
import com.CSMIS.CSMISKhaingFamily.Entity.MenuImportData;
import com.CSMIS.CSMISKhaingFamily.Entity.Operator;
import com.CSMIS.CSMISKhaingFamily.Entity.OrderDetailView;
import com.CSMIS.CSMISKhaingFamily.Entity.RestaurantInfo;
import com.CSMIS.CSMISKhaingFamily.Entity.WeeklyOrder;
import com.CSMIS.CSMISKhaingFamily.Function.EmailScheduler;
import com.CSMIS.CSMISKhaingFamily.Function.SendEmailAdmin;

import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@Controller
public class HomePageController {
	@Autowired
	RegisterDateRepository registerdateRepository;
	
	  @Autowired
	  OperatorRepository operatorRepository;
	  
	  @Autowired
	  CreativeInvoiceRepository invoiceRepo;
	   
	  @Autowired
	  private EmailConfigRepository emailRepo;
	  
	  @Autowired
	  RegisterCateringRepository rcr;
	  
	  @Autowired
	  WeeklyOrderRepository weeklyorderRepository;
	  
	  @Autowired
	  HolidayRepository holidayRepository;
	  @Autowired
	  AvoidMeatRepository avoidRepo;
	  
	  @Autowired
	  LunchCountRepository lunchcountRepository;
	  @Autowired
	  OperatorService opservice;
	  
	  @Autowired
	  EmailService emailService;
	  
	  @Autowired
	  EmailScheduler emailScheduler;
	  
	  @Autowired
	  AvoidCountRepository avoidcountRepo;
	  
	  @Autowired
	  AdminMenuImportRepo repo;
	  
	  @Autowired
	  private SendEmailAdmin sendemailadmin;
	  
	  @Autowired
	  private OrderDetailViewRepository orderdetailviewRepository;
	  
	  @Autowired
	  private AdminMenuImportService service;
	  
	  @Autowired
	   private RegisteredeatRepository registeredeatRepository;
	   
	   @Autowired
	   private RegistereduneatRepository registereduneatRepository;
	   
	   @Autowired
	   private UnregisteredeatRepository unregisteredeatRepostory;
		
	   @Autowired
	   private RestaurantInfoRepository rRepo;
	   
	   @Autowired
	   private FeedbackRepo fRepo;
	   
		@Autowired
		WeeklyOrderRepository weeklyOrderRepository;
		
		@Autowired
		CreativeInvoiceRepository ciRepository;
		
		@Autowired
		ContactInfoRepository contactoInfo;
		
		 @Autowired
		  RestaurantService rs;
		 
		@Autowired
		PostService postService;
		private String cronExpression = "0 0 0 * * *"; // default cron expression
	    

		// Getter method for the dynamic cron expression
		public String getCronExpression() {
			return this.cronExpression;
		}

		// Helper method to create a cron trigger based on the current cron expression
		private CronTrigger cronTrigger() {
			return new CronTrigger(getCronExpression());
		}
	
	@GetMapping("/login")
 	public String landingpage() {
 		return "homepage";
 	}
	
	 @GetMapping("/")
	 	public String homePage(Principal prin, Model model,RedirectAttributes redirectAttributes,HttpSession session) throws ParseException {
			Operator operator = operatorRepository.findByemployeeEmail(prin.getName());
			session.setAttribute("email", operator.getEmployeeEmail());
			session.setAttribute("operatorname", operator.getEmployeeName());
			session.setAttribute("empName", operator.getEmployeeName());
			session.setAttribute("operator", operator);
			session.setAttribute("empName", operator.getEmployeeName());
			String resId = rs.generateUserId();
			session.setAttribute("resId", resId);
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String encodedPassword = operator.getEmployeePassword();
			String rawPassword = "12345";
			

			
			WeeklyOrder wo = weeklyOrderRepository.findTopByOrderByIdDesc();
			if(wo != null) {
			session.setAttribute("datCost", wo.getDatcost());
			session.setAttribute("empCost", wo.getEmployeecost());
			}else {
				session.setAttribute("datCost", "1500.0");
				session.setAttribute("empCost", "1000.0");
			}
			if(operator.getStatus().equals("INACTIVE")) {
				redirectAttributes.addFlashAttribute("INACTIVEError", "Sorry!  Your account is INACTIVE");
				return "redirect:/login";
			}else {
				if (operator.getRole().equals("ADMIN")) {
					if (encoder.matches(rawPassword, encodedPassword)) {
			            System.out.println("OP" + operator.getEmployeePassword());
			            model.addAttribute("pasMessage", "Your password is not safe.Should change your password.");
			        }
					session.setAttribute("staffid", operator.getStaffId());
				    session.setAttribute("role", operator.getRole());
				    session.setAttribute("empemail", operator.getEmployeeEmail());
				    
				    List<MenuImportData> list = service.getAllCatering();
				    if (list == null || list.isEmpty()) {
				        model.addAttribute("message", "There are no menus available.");
				        
				    } else {
				        MenuImportData latestCatering = list.get(list.size() - 1);
				        model.addAttribute("latestCatering", latestCatering);
				    }
				    double reprice = registeredeatRepository.registeredeatprice(operator.getDoorLogNo());
				    double rneprice = registereduneatRepository.registereduneatprice(operator.getDoorLogNo());
				    double ueprice = unregisteredeatRepostory.unregisteredeatprice(operator.getDoorLogNo());
				    double total = reprice + rneprice + ueprice;
				    double registercost = reprice+rneprice;
		    	    model.addAttribute("reprice", reprice);
		    	    model.addAttribute("rneprice",rneprice);
		    	    model.addAttribute("ueprice", ueprice);
		    	    model.addAttribute("registercost", registercost);
		    	    model.addAttribute("total", total);
				   
				    session.setAttribute("operator", operator);
				    
				    List<Feedback> feedbackList = fRepo.findAll();
				    Feedback restaurant = null;
				    if (!feedbackList.isEmpty()) {
				        restaurant = feedbackList.get(0);
				    }
			    	model.addAttribute("flist", feedbackList);

				    session.setAttribute("restaurant", restaurant);

		            sentModelToHomePage(model);
		            	return "admindashboard";
		              
				} else {
					if (encoder.matches(rawPassword, encodedPassword)) {
			            System.out.println("OP" + operator.getEmployeePassword());
			            model.addAttribute("pasMessage", "Your password is not safe.Should change your password.");
			        }
					
					session.setAttribute("staffid", operator.getStaffId());
				    List<MenuImportData> list = service.getAllCatering();  
				    if (list == null || list.isEmpty()) {
				        model.addAttribute("message", "There are no menus available.");
				        
				    } else {
				    	MenuImportData latestCatering = list.get(list.size() - 1);
				        model.addAttribute("latestCatering", latestCatering);
				        session.setAttribute("result", 1);
				    }
				    double reprice = registeredeatRepository.registeredeatprice(operator.getDoorLogNo());
				    double rneprice = registereduneatRepository.registereduneatprice(operator.getDoorLogNo());
				    double ueprice = unregisteredeatRepostory.unregisteredeatprice(operator.getDoorLogNo());
				    double total = reprice + rneprice + ueprice;
				    double registercost = reprice+rneprice;
		    	    model.addAttribute("reprice", reprice);
		    	    model.addAttribute("rneprice",rneprice);
		    	    model.addAttribute("ueprice", ueprice);
		    	    model.addAttribute("registercost", registercost);
		    	    model.addAttribute("total", total);
				    model.addAttribute("annoucelist", postService.getAllActivePosts());
				    
				    List<String> datelist = registerdateRepository.datesinCurrentMonths(operator.getDoorLogNo()); 
				    List<RestaurantInfo> activeRestaurants = rRepo.findActiveRestaurants();
				    RestaurantInfo latestRestaurant = null;
				    if (!activeRestaurants.isEmpty()) {
				    	latestRestaurant  = activeRestaurants.get(activeRestaurants.size() - 1);
				        session.setAttribute("restaurant", latestRestaurant);
				    }
				    
			       session.setAttribute("error", latestRestaurant);
				    System.out.println(latestRestaurant +"whoo");

	               session.setAttribute("operator", operator);
	               model.addAttribute("registrationdateslist", datelist);
	               model.addAttribute("holidaydateslist",holidayRepository.GetAllHolidayDate() );
	               model.addAttribute("annoucelist",postService.getAllActivePosts());
			              
	               return "operatordashboard";
				}

			}
		}
	 @PostConstruct
	    public void executeAtStartup() {
		  
			List<EmailConfig> config = emailRepo.findAll();
			System.out.println(config);
			if(config.isEmpty() || config.get(0).getCronExpression() == "" || config.get(0).getCronExpression() == null) {
				
				emailRepo.updateCronExpression("0 0 0 * * *");
			}
			else{
				this.cronExpression = config.get(0).getCronExpression();
			}
			
			emailRepo.updateCronExpression(this.cronExpression);
			
			// Cancel any previously scheduled task
			emailScheduler.cancelScheduledTask();
	        
			// Schedule a new task using the updated cron expression
			emailScheduler.scheduleTask(cronTrigger(), new Runnable() {
			    @Override
			    public void run() {
			        // Code to be executed on schedule
			    	System.out.println("Executing scheduled method at " + new Date());
			    	
			    	try {
						emailScheduler.scheduleEmailSending();
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (MessagingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (TemplateNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (MalformedTemplateNameException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (freemarker.core.ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (TemplateException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    }
			});

	    
	        // Code to be executed at application startup
	        // This method will be executed every time the application starts
	        // You can replace this with your desired logic
	        System.out.println("Executing at application startup");
	    }
	@GetMapping("/logout")
    public String logout() {
        return "homepage";
    }
	@GetMapping("/admin/lunchRegister")
	public String dateRegister() {
	    return "lunchregister";
	}

 
	@GetMapping("/otpError")
	public String otpError(HttpSession session) {
		
		return "forgot_password_form";
	}
	@GetMapping("/otpSuccess")
	public String otpSuccess(HttpSession session) {
		
		return "otp_form";
	}
 
 
 @GetMapping("/otpAcceptError")
	public String otpAcceptError(HttpSession session) {
		
		return "otp_form";
	}
	@GetMapping("/otpAcceptOtpError")
	public String otpAcceptSuccess(HttpSession session) {
		
		return "otp_form";
	}
	
	@GetMapping("/otpResetPassword")
	public String otpResetPassword(Model model,HttpSession session) {
		String otp = (String) session.getAttribute("otp");
		String email = (String) session.getAttribute("email");
		model.addAttribute("otp", otp);
		model.addAttribute("email", email);
		return "changePw";
	}
		
 @PostMapping("/otpAccept")
    public String acceptOtp(HttpServletRequest request,Model model,HttpSession session,RedirectAttributes redirectAttributes) {
	 String otp = request.getParameter("otp");
	 String email = request.getParameter("email");
	 session.setAttribute("otp", otp);
	 session.setAttribute("email", email);
       Operator user  = operatorRepository.findByemployeeEmail(email);
       String number = "";
       if(user != null) {
    	    number = user.getOtpCode().split("-")[0];
       }
       System.out.println("First " + number);
       System.out.println("Second " + otp);
       if(!number.equals(otp) || !user.getEmployeeEmail().equals(email)) {
    	   
    	   redirectAttributes.addFlashAttribute("errorMessage", "Invalid Email or OTP!");
           return "redirect:/otpAcceptError";
        
        }
       else {
    	   Instant otpCreationTime =  user.getOtpCreationTime();// Retrieve the OTP creation time from the database
		   Instant currentTime = Instant.now();
		   Duration duration = Duration.between(otpCreationTime, currentTime);
		   if (duration.toMinutes() > 3) {
			   redirectAttributes.addFlashAttribute("errorOtpMessage", "OTP code has expired!");
	           return "redirect:/otpAcceptOtpError";
			   
		   }
       }
       Operator operator = operatorRepository.findByemployeeEmail(email);
		
		if(operator.getRole().equals("ADMIN")) {
			System.out.println(operator.getRole());
			session.setAttribute("email",operator.getEmployeeEmail());
			return "changePw";
		}else {
			session.setAttribute("email",operator.getEmployeeEmail());
			
	
			return "changePw";
		}
    }
 
 @PostMapping("/reset_password")
 public String processResetPassword(HttpServletRequest request, Model model,HttpSession session,RedirectAttributes redirectAttributes) {
     String email = (String) session.getAttribute("email");
     String password = request.getParameter("password");
     String cpassword = request.getParameter("cpassword");
     System.out.println("Pas:" + password);
     System.out.println("CPas:" + cpassword);
     String real = "";
     if(password.equals(cpassword))
     {
    	 real = password;
     }else {
    	 System.out.println("Here!!");
    	 redirectAttributes.addFlashAttribute("errorOtpMessage", "Password does not Match");
         return "redirect:/otpResetPassword";
    	
     }
    	 
     Operator customer = operatorRepository.findByemployeeEmail(email);
     model.addAttribute("title", "Reset your password");
      
     if (customer == null) {
         model.addAttribute("message", "Invalid Token");
         return "message";
     } else {           
       opservice.updatePassword(customer, real);
          
         model.addAttribute("message", "You have successfully changed your password.");
     }
      
     return "redirect:/login";
 }
	@GetMapping("/forgot_password")
    public String showForgotPasswordForm() {
        return "forgot_password_form";
    }
 
 @PostMapping("/otpSend")
    public String sendOtp(HttpServletRequest request,Model model,HttpSession session) throws MessagingException, TemplateNotFoundException, MalformedTemplateNameException, freemarker.core.ParseException, IOException, TemplateException {
	 String email = request.getParameter("email");
	 String otpCode = String.valueOf(new Random().nextInt(999999));
       System.out.println(email);
        Instant otpCreationTime = Instant.now();
        
        int i = opservice.insertUserWithPas(otpCode,otpCreationTime,email);
        if(i == 0) {
        	model.addAttribute("message", "Invalid Email!");
        	return"forgot_password_form";
        }
        emailService.sendOtp(email,otpCode);
		model.addAttribute("message", "We have sent a OTP code to your email. Please check!");  
        return "otp_form";
    }
 public void sentModelToHomePage(Model model) {
	    
	    model.addAttribute("totalregister", rcr.count());  
	    model.addAttribute("countNotiOperator", operatorRepository.countNotiOperator());  
	    
	    WeeklyOrder weeklyOrders = weeklyorderRepository.getNewOrder();
	    int newOrderTotalPax = weeklyOrders!=null ? weeklyOrders.getTotalpax():0;
	    System.out.println(newOrderTotalPax);
	    model.addAttribute("neworder", newOrderTotalPax);

	    
	     List<OrderDetailView> orderdetaillist = orderdetailviewRepository.findAll();
	     if (!orderdetaillist.isEmpty()) {
	         model.addAttribute("lunchcostlist", orderdetaillist);
	     }
	        List<OrderDetailView> lunchcost = orderdetailviewRepository.getLastFiveRowsLunchCost();
	        model.addAttribute("lunchcostenddate", lunchcost.isEmpty() ? 0 : lunchcost.get(0).getDates()); 
	        model.addAttribute("lunchcoststartdate", lunchcost.isEmpty() ? 0 : lunchcost.get(lunchcost.size() - 1).getDates());
	        
	        model.addAttribute("lunchlist", !lunchcountRepository.findAll().isEmpty()?lunchcountRepository.findAll():new ArrayList());
	        List<LunchCount> lunchCounts = lunchcountRepository.getLastFiveRowsLunchCount();
	        model.addAttribute("lunchcountenddate", lunchCounts.isEmpty() ? 0 : lunchCounts.get(0).getDate());
	        model.addAttribute("lunchcountstartdate", lunchCounts.isEmpty() ? 0 : lunchCounts.get(lunchCounts.size() - 1).getDate());

	  
 }
 @GetMapping("/contactus")
 public String contactus(Model model) {
	 ContactInfo cf = contactoInfo.findTopByOrderByIdDesc();
	
		 model.addAttribute("address", cf.getAddress());
		 model.addAttribute("email", cf.getEmail());
		 model.addAttribute("phone", cf.getPhone());
	 
  return "contactus";
 }
 @PostMapping("/sendemail")
 public String sendEmailByAdmin(@RequestParam("name") String name,
                       @RequestParam("email") String email,
                       @RequestParam("message") String message) throws MessagingException, IOException, TemplateException {
 System.out.printf("Email: %s, Message: %s, Name: %s%n", email, message, name);
     sendemailadmin.sendadmin(email, message, name);
     return "Success";
 }
}
