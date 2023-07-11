package com.CSMIS.CSMISKhaingFamily.Controller;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.CSMIS.CSMISKhaingFamily.DAO.EmailConfigRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.OperatorRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.ToogleService;
import com.CSMIS.CSMISKhaingFamily.Entity.EmailConfig;
import com.CSMIS.CSMISKhaingFamily.Entity.Operator;
import com.CSMIS.CSMISKhaingFamily.Function.EmailScheduler;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;




@Controller
@EnableScheduling
public class EmailController {
	@Autowired
	private ToogleService toogleService;
	
	@Autowired
	private EmailScheduler emailScheduler;
	
	@Autowired
    private EmailConfigRepository emailRepo;
	
	 @Autowired
	 OperatorRepository operatorRepository;
	// Define a variable to hold the dynamic cron expression
	private String cronExpression = "0 0 0 * * *"; // default cron expression
    
	// Define the method to be executed on a schedule
	@Scheduled(cron = "#{@emailController.getCronExpression}")
	public void scheduledMethod() {
		System.out.println("Executing scheduled method at " + new Date());
	}
	
	// Setter method for the dynamic cron expression
	@PostMapping("/admin/remindSchedule")
	@ResponseBody
	public ResponseEntity<?> setCronExpression(@RequestParam("datetime") String datetime,HttpSession session) {
		String cron = (String)session.getAttribute("cronExpression");
		if( cron != null) {
			this.cronExpression = cron;
		}
		// Convert the input datetime string to LocalDateTime object
		if(datetime == "") {
			 String errorMessage = "Please enter a valid date and time.";
		        return ResponseEntity.badRequest().body(errorMessage);
		}
		
		LocalDateTime inputDatetime = LocalDateTime.parse(datetime, DateTimeFormatter.ISO_LOCAL_DATE_TIME);

		// Convert the LocalDateTime object to the default time zone
		ZoneId defaultZoneId = TimeZone.getDefault().toZoneId();
		LocalDateTime localDatetime = inputDatetime.atZone(defaultZoneId).toLocalDateTime();

		// Extract the minute, hour, day of month, month, and day of week from the LocalDateTime object
		int minute = localDatetime.getMinute();
		int hour = localDatetime.getHour();
		int dayOfMonth = localDatetime.getDayOfMonth();
		int month = localDatetime.getMonthValue();
		int dayOfWeek = localDatetime.getDayOfWeek().getValue();

		// Generate the cron expression based on the input datetime
		String cronExpression = String.format("0 %d %d %d %d %d", minute, hour, dayOfMonth, month, dayOfWeek);
		
		if(cron == null) {
			this.cronExpression = cronExpression;
		}
			
		// Set the cron expression to the instance variable
		
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
				} catch (ParseException e) {
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

    
		 if (cronExpression != "") {
			 	
			 String successMessage = "Remind date and time set up successfully.";
		        Map<String, String> jsonResponse = new HashMap<>();
		       
		        jsonResponse.put("message", successMessage);
		        return ResponseEntity.ok().body(jsonResponse);
		    } else {
		        String errorMessage = "Failed to set remind date and time.";
		        return ResponseEntity.badRequest().body(errorMessage);
		    }
		}
	// Getter method for the dynamic cron expression
	public String getCronExpression() {
		return this.cronExpression;
	}

	// Helper method to create a cron trigger based on the current cron expression
	private CronTrigger cronTrigger() {
		return new CronTrigger(getCronExpression());
	}
	
	@GetMapping("/admin/adminSetting")
    public ModelAndView adminSetting(HttpSession session) {
    String email = (String)session.getAttribute("email");
    Operator op = operatorRepository.SelectOne(email);
    List<EmailConfig> config = emailRepo.findAll();
    if(op != null && config!=null) {
    	session.setAttribute("configEmail", config.get(0).getUsername());
        session.setAttribute("configPas", config.get(0).getPassword());
    
    session.setAttribute("toggle", op.getToggle());
    System.out.println(op.getToggle());
    }else {
      session.setAttribute("toggle", "false");
    }
    return new ModelAndView("adminsetting","op",op);
    }
  
  @GetMapping("/operator/operatorSetting")
    public ModelAndView operatorSetting(HttpSession session) {
    String email = (String)session.getAttribute("email");
    Operator op = operatorRepository.SelectOne(email);
    if(op != null) {
      
      session.setAttribute("toggle", op.getToggle());
      System.out.println(op.getToggle());
      }else {
        session.setAttribute("toggle", "false");
      }

        return new ModelAndView("operatorsetting","op",op);
    }
	
	@GetMapping("/admin/adminDashboard")
    public String adminDashboard() {
        return "admindashboard";
    }
	@PostMapping("/admin/sendEmail")
	@ResponseBody
	public ResponseEntity<?> sendEmail(@RequestParam("username") String username, @RequestParam("password") String password) throws MessagingException {
	    if(username == "" || password == "") {
	    	 String errorMessage = "Please enter username and password.";
 	         return ResponseEntity.badRequest().body(errorMessage);
	    }
	    
	    EmailConfig email = new EmailConfig();
	    email.setHost("smtp.gmail.com");
	    email.setPort(587);
	    email.setUsername(username);
	    email.setPassword(password);
	    email.setCronExpression("0 0 0 * * *");
	    email = emailRepo.save(email);
	    
	    if (email != null) {
	        // Return the email form HTML and success message as a JSON response
	    	String successMessage = "Email Configuration is successful.";
	        Map<String, String> jsonResponse = new HashMap<>();
	       
	        jsonResponse.put("message", successMessage);
	        return ResponseEntity.ok().body(jsonResponse);
	       
	       
	    } else {
	    	String successMessage = "Failed to save email configuration";
	        Map<String, String> jsonResponse = new HashMap<>();
	       
	        jsonResponse.put("message", successMessage);
	        return ResponseEntity.badRequest().body(jsonResponse);
	    }
	}

	 @PostMapping("/toogle")
	    public String showForgotPasswordForm(HttpServletRequest request,HttpSession session) {
		 String switchValue = request.getParameter("switchValue");
		 String email = (String)session.getAttribute("email");
		 int i = toogleService.updateToogleWithEmail(switchValue, email);
		 if(i > 0) {
			 System.out.println("Success!");
		 }else {
			 System.out.println("Failed!");
		 }
	     return "redirect:/";
	    }
}
