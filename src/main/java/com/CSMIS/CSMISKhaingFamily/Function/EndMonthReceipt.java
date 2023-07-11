package com.CSMIS.CSMISKhaingFamily.Function;

import java.io.IOException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.CSMIS.CSMISKhaingFamily.DAO.OperatorRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.RegisteredeatRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.RegistereduneatRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.UnregisteredeatRepository;
import com.CSMIS.CSMISKhaingFamily.Entity.Operator;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Service
public class EndMonthReceipt {
	@Autowired
	private OperatorRepository operatorRepository;

	@Autowired
    private JavaMailSender javaMailSender;
    
    @Autowired
    private Configuration configuration;
    
   @Autowired
   private RegisteredeatRepository registeredeatRepository;
   
   @Autowired
   private RegistereduneatRepository registereduneatRepository;
   
   @Autowired
   private UnregisteredeatRepository unregisteredeatRepostory;
    
    public void operatorendreceipt() throws MessagingException, IOException, TemplateException {
    	List<Operator> operators = operatorRepository.findAllEmployee();
    	
    	for (Operator operator : operators) {
    	    String doorLogNos = operator.getDoorLogNo();
    	    String email = operator.getEmployeeEmail();
    	    String staffid = operator.getStaffId();
    	    String name = operator.getEmployeeName();
    	    double reprice = registeredeatRepository.registeredeatprice(doorLogNos);
    	    double rneprice = registereduneatRepository.registereduneatprice(doorLogNos);
    	    double ueprice = unregisteredeatRepostory.unregisteredeatprice(doorLogNos);
    	    double total = reprice + rneprice + ueprice;
    	    sendmail(email,reprice,rneprice,ueprice,total,staffid,name,doorLogNos);
    	}
       
    }
    
    public void sendmail(String email,double reprice,double rneprice,double ueprice,double total,String staffid,String name,String doorLogNos) throws MessagingException, IOException, TemplateException {
    	final String emailToRecipient = email;
    	MimeMessage message = javaMailSender.createMimeMessage();
 	    MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED);
 	    Template template = configuration.getTemplate("email-monthlyreciept.ftl");
 	    LocalDateTime currentDateTime = LocalDateTime.now();
 	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM");
 	    DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	    String startDate = currentDateTime.with(TemporalAdjusters.firstDayOfMonth()).format(formatter1);
        String endDate = currentDateTime.with(TemporalAdjusters.lastDayOfMonth()).format(formatter1);
        String month = currentDateTime.format(formatter); 
 	    Map<String, Object> templateData = new HashMap<>();
 	    templateData.put("name", name);
 	    templateData.put("doorlogno", doorLogNos);
 	    templateData.put("staffid", staffid);
 	    templateData.put("month", month);
 	    templateData.put("startdate", startDate);
 	    templateData.put("enddate", endDate);
 	    templateData.put("re", reprice);
 	    templateData.put("rne", rneprice);
 	    templateData.put("ure", ueprice);
 	    templateData.put("total", total);
 	   String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, templateData);

       helper.setTo(emailToRecipient);
       helper.setText(html, true);
       helper.setSubject("CSMIS-Catering System Infomation");
       javaMailSender.send(message);
    }
}
