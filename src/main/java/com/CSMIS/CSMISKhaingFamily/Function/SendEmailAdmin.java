package com.CSMIS.CSMISKhaingFamily.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.CSMIS.CSMISKhaingFamily.DAO.OperatorRepository;
import com.CSMIS.CSMISKhaingFamily.Entity.Operator;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

import java.io.IOException;
import java.util.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class SendEmailAdmin {
    @Autowired
    private OperatorRepository operatorRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Configuration configuration;

    public void sendadmin(String sentMail, String messagefor, String name)
            throws MessagingException, IOException, TemplateException {
        List<Operator> admin = operatorRepository.searchAdmin();
        for (Operator adminby : admin) {
            sendmailbyadmin(adminby.getEmployeeEmail(), sentMail, messagefor, name);
        }
    }

    public void sendmailbyadmin(String getEmail, String sentEmail, String messagefor, String name)
            throws MessagingException, TemplateNotFoundException, MalformedTemplateNameException, ParseException,
            IOException, TemplateException {
        final String emailToRecipient = getEmail;
        final String emailFromRecipient = sentEmail;
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED);
        Template template = configuration.getTemplate("email-contactus.ftl");

        Map<String, Object> templateData = new HashMap<>();
        templateData.put("name", name);
        templateData.put("message", messagefor);
        templateData.put("email", emailFromRecipient);
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, templateData);
        helper.setTo(emailToRecipient);
        helper.setText(html, true);
        helper.setSubject("CSMIS-Catering System Infomation");
        javaMailSender.send(message);
    }
}
