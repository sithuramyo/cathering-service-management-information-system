package com.CSMIS.CSMISKhaingFamily.Function;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ScheduledFuture;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.CSMIS.CSMISKhaingFamily.DAO.AvoidCountRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.AvoidMeatRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.CreativeInvoiceRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.EmailConfigRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.OperatorRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.UserRepository;
import com.CSMIS.CSMISKhaingFamily.Entity.AvoidMeat;
import com.CSMIS.CSMISKhaingFamily.Entity.EmailConfig;
import com.CSMIS.CSMISKhaingFamily.Entity.Operator;
import com.CSMIS.CSMISKhaingFamily.Model.CalculateInvoice;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;


@Component
public class EmailScheduler {
	 
	 
	 @Autowired
	 private UserRepository userRepo;
	 
	 @Autowired
	 private OperatorRepository opRepo;
	 
	 @Autowired
	 AvoidMeatRepository avoidRepo;
	 
	 @Autowired
	 private JavaMailSender javaMailSender;
	    
	 @Autowired
	 private Configuration configuration;
	 
	 @Autowired
	 private EmailConfigRepository emailRepo;
	 
	 @Autowired 
	 CreativeInvoiceRepository invoiceRepo;
     
	 @Autowired
	AvoidCountRepository avoidcountRepo;
	 public void sendEmail(String email) throws MessagingException, TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
		    MimeMessage message = javaMailSender.createMimeMessage();
		    MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED);
		    Template template = configuration.getTemplate("email-remind.ftl");

		    Map<String, Object> templateData = new HashMap<>();

		    // Get the current day and time
		    LocalDateTime currentDateTime = LocalDateTime.now();
		    LocalDateTime futureDateTime = currentDateTime.plusMinutes(30);

		    // Format the current and future date and time
		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy - hh:mm a");
		    String formattedFutureDateTime = futureDateTime.format(formatter);
		    templateData.put("futuredatetime", formattedFutureDateTime);
		    String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, templateData);

		    helper.setTo(email);
		    helper.setText(html, true);
		    helper.setSubject("CSMIS-Catering System Information");
		    javaMailSender.send(message);
		}
	    
        
		    
	        
	    private ScheduledFuture<?> scheduledFuture;
        
	    private final TaskScheduler taskScheduler;

	    public EmailScheduler(TaskScheduler taskScheduler) {
	        this.taskScheduler = taskScheduler;
	    }

	    public void scheduleTask(Trigger trigger, Runnable task) {
	        scheduledFuture = taskScheduler.schedule(task, trigger);
	    }
        
	    public void cancelScheduledTask() {
	        if (scheduledFuture != null) {
	            scheduledFuture.cancel(true);
	        }
	    }

	    public Trigger cronTrigger(String cronExpression) {
	        return new CronTrigger(cronExpression);
	    }
	    
	    public void scheduleEmailSending() throws MessagingException, TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
		 List<Operator> opData = opRepo.findAllEmployee();
		 
		 for(int i = 0;i < opData.size();i++) {
			 try {
			        if ("true".equals(opData.get(i).getToggle())) {
			            System.out.println(opData.get(i).getEmployeeEmail());
			            sendEmail(opData.get(i).getEmployeeEmail());
			        }
			    } catch (NullPointerException e) {
			        System.err.println("Caught NullPointerException while processing opData at index " + i + ": " + e.getMessage());
			    }
		 }
	    }

	    
	}

