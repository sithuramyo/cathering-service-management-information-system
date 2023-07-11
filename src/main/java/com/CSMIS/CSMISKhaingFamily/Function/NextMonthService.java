package com.CSMIS.CSMISKhaingFamily.Function;

import java.io.IOException;
import java.time.LocalDate;
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
import com.CSMIS.CSMISKhaingFamily.Entity.*;
import com.CSMIS.CSMISKhaingFamily.DAO.OperatorRepository;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
@Service
public class NextMonthService {
		@Autowired
		private OperatorRepository operatorRepository;
	
		@Autowired
	    private JavaMailSender javaMailSender;
	    
	    @Autowired
	    private Configuration configuration;
	
	public void operatoremail() throws MessagingException, IOException, TemplateException {	
	List<Operator> operator = operatorRepository.findAllEmployee();
	LocalDate currentDate = LocalDate.now();
    LocalDate endOfMonth = currentDate.with(TemporalAdjusters.lastDayOfMonth());
    LocalDate lastWeekStart = endOfMonth.minusDays(6);
    for (Operator operator2 : operator) {
    	sendMail(operator2.getEmployeeEmail(),lastWeekStart);
	}	
	}
	
	public void sendMail(String getEmail, LocalDate lastweek) throws MessagingException, IOException, TemplateException {
	    final String emailToRecipient = getEmail;
	    final LocalDate lastweekdaytoRecipient = lastweek;

	    MimeMessage message = javaMailSender.createMimeMessage();
	    MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED);
	    Template template = configuration.getTemplate("email-nextmonth.ftl");

	    Map<String, Object> templateData = new HashMap<>();
	    templateData.put("lastweek", lastweekdaytoRecipient);
	    String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, templateData);

	       helper.setTo(emailToRecipient);
	       helper.setText(html, true);
	       helper.setSubject("CSMIS-Catering System Infomation");
	       javaMailSender.send(message);
	}

}
