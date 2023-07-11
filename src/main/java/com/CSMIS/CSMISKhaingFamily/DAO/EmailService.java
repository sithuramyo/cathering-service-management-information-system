package com.CSMIS.CSMISKhaingFamily.DAO;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Configuration configuration;
    
    public EmailService(JavaMailSender javaMailSender2, Configuration configuration2) {
		// TODO Auto-generated constructor stub
	}

	public void sendOtp(String email,String otpCode) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException, MessagingException {
       
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED);
        Template template = configuration.getTemplate("email-otpcode.ftl");

        Map<String, Object> templateData = new HashMap<>();
        templateData.put("otpcode", otpCode);
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, templateData);

        helper.setTo(email);
        helper.setText(html, true);
        helper.setSubject("CSMIS-Catering System Infomation");
        javaMailSender.send(message);
    }
}
